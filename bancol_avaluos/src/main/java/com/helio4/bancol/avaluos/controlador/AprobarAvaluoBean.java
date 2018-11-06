package com.helio4.bancol.avaluos.controlador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.io.FileUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import com.helio4.bancol.avaluos.dominio.AprobarAvaluoController;
import com.helio4.bancol.avaluos.dominio.AvaluoController;
import com.helio4.bancol.avaluos.dominio.ParametrosController;
import com.helio4.bancol.avaluos.dominio.TarifaUVRController;
import com.helio4.bancol.avaluos.dto.AvaluoDTO;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;
import com.helio4.bancol.avaluos.modelo.Motivo;
import com.helio4.bancol.avaluos.servicio.datos.MotivoService;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.TarifaNotFoundException;
import com.helio4.bancol.avaluos.servicio.util.Constantes.Estado;
import com.helio4.bancol.avaluos.servicio.util.DateUtils;
import com.helio4.bancol.avaluos.servicio.util.PDFDigitalSign;

@Controller
@Scope("view")
@Qualifier("aprobarAvaluoBean")
public class AprobarAvaluoBean implements Serializable {

	private static Logger log = LoggerFactory.getLogger(AprobarAvaluoBean.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AvaluoDTO avaluo;
	private String path;
	private boolean mostrarBotonAprobar;

	@Autowired
	private ListadoAvaluosBean listadoAvaluosBean;
	@Autowired
	private AprobarAvaluoController aprobarAvaluoController;
	@Autowired
	private AvaluoController avaluoController;
	@Autowired
	ParametrosController parametrosController;
	@Autowired
	private TarifaUVRController tarifaUvrController;
	@Autowired
	private MotivoService motivoService;

	@PostConstruct
	public void init() {
		avaluo = listadoAvaluosBean.getAvaluo();
		mostrarBotonAprobar = true;
		path = parametrosController.obtenerValor("rutaReportes");
	}

	// En este punto de ejecución no se deben lanzar excepciones si no
	// capturarlas y manejarlas
	public StreamedContent getFile() {
		Date fecha = new Date();
		if (tarifaUvrController.encontrarPorFecha(
				DateUtils.getFechaFormateada(DateUtils.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS, fecha)) != null) {
			File archivoPdfNuevo = null;
			try {
				String pathPDF = path + "imagenes" + File.separator + this.listadoAvaluosBean.getAvaluo().getId()
						+ ".pdf";
				log.debug("Path PDF aprobarAvaluo:  {}", pathPDF);
				avaluo = aprobarAvaluoController.aprobarAvaluo(avaluo,
						(UsuarioDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
				if (avaluo == null) {
					FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Error",
							"No se puede aprobar el avalúo porque el avalúo ya esta aprobado"));
					return null;
				}
				this.listadoAvaluosBean.generarPDF();

				log.debug("Path Firma aprobarAvaluo:  {}", path);
				/*AFVV 2017-09-26: Se quita la firma digital por vencimiento de certificado*/
		        /*PDFDigitalSign digitalSign = new PDFDigitalSign(path);
				digitalSign.firmar(pathPDF);*/

				Motivo motivoAvaluo = motivoService.getMotivosById(avaluo.getMotivo().longValue());

				File archivoPdf = new File(pathPDF);
				String prefijoEntidad = avaluoController.cargarPrefijoEntidad(avaluo.getEntidad().getId());
				String pathNuevoPDF = path + "imagenes/" + prefijoEntidad + "-" + avaluo.getCodigoExterno()
						+ (motivoAvaluo.getPrefijo() != null ? "-" : "")
						+ (motivoAvaluo.getPrefijo() != null ? motivoAvaluo.getPrefijo() : "") + ".pdf";

				archivoPdfNuevo = new File(pathNuevoPDF);

				FileUtils.copyFile(archivoPdf, archivoPdfNuevo);

				aprobarAvaluoController.notificarAvaluoAprobado(avaluo,
						(UsuarioDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal(), pathPDF);

			} catch (AvaluoNotFoundException e1) {
				FacesContext.getCurrentInstance().addMessage("growl",
						new FacesMessage("Error", "Error al exportar el pdf del avalo el avaluo no se encontró"));
				e1.printStackTrace();
			} catch (TarifaNotFoundException e) {
				FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Error",
						"La tarifa para la entidad " + avaluo.getEntidad().getNombre() + " no se encontró"));
				e.printStackTrace();
				return new DefaultStreamedContent();
			} catch (IOException e) {
				FacesContext.getCurrentInstance().addMessage("growl",
						new FacesMessage("Error", "Ocurrio un error con el archivo PDF."));
				e.printStackTrace();
			}
			mostrarBotonAprobar = false;
			StreamedContent file = new DefaultStreamedContent();
			InputStream stream;

			avaluo.getEntidad().setPrefijo(avaluoController.cargarPrefijoEntidad(avaluo.getEntidad().getId()));

			try {
				stream = new FileInputStream(archivoPdfNuevo);
				file = new DefaultStreamedContent(stream, "application/pdf", archivoPdfNuevo.getName());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			return file;
		} else {
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_WARN,
					"No existe UVR para este día", "Por favor contacte al administrador del sistema."));
			return null;
		}
	}

	public void volverInicio() {
		if (avaluo == null) {
			FacesContext.getCurrentInstance().addMessage("growl",
					new FacesMessage("Error", "No se pudó aprobar el avaluo."));
			return;
		} else if (avaluo.getEstado().equals(Estado.Aprobado)) {
			// Enviar mensaje
			listadoAvaluosBean.setInformeLiquidado(avaluo.getCodigoExterno());
		}

		String uri = FacesContext.getCurrentInstance().getExternalContext()
				.encodeActionURL(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()
						+ "/pages/avaluos/listadoAvaluos.xhtml");
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(uri);
		} catch (IOException e) {
			// TODO Reireccionar a página no encontrada
			e.printStackTrace();
		}
	}

	public AvaluoDTO getAvaluo() {
		return avaluo;
	}

	public void setAvaluo(AvaluoDTO avaluo) {
		this.avaluo = avaluo;
	}

	public boolean getMostrarBotonAprobar() {
		return mostrarBotonAprobar;
	}

	public void setMostrarBotonAprobar(boolean mostrarBotonAprobar) {
		this.mostrarBotonAprobar = mostrarBotonAprobar;
	}

}
