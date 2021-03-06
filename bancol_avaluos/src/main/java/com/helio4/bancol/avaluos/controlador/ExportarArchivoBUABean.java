package com.helio4.bancol.avaluos.controlador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.google.common.io.ByteStreams;
import com.helio4.bancol.avaluos.dominio.ExportarArchivoBUAController;
import com.helio4.bancol.avaluos.dto.EntidadDTO;

@Controller
@Scope("view")
@Qualifier("exportarArchivoBUABean")
public class ExportarArchivoBUABean {

	private static Logger log = LoggerFactory.getLogger(ExportarArchivoBUABean.class);

	private Date fechaInicio;
	private Date fechaFinal;
	private StreamedContent archivoBua;
	private List<EntidadDTO> entidades;
	private EntidadDTO entidadSeleccionada;

	@Autowired
	private ListadoAvaluosBean listadoAvaluosBean;

	@Autowired
	private ExportarArchivoBUAController exportarArchivoBUAController;

	private String resultado;

	@PostConstruct
	public void init() {
		try {
			log.debug("Inicializando ExportarArchivoBUABean: ");
			fechaInicio = new Date(System.currentTimeMillis());
			fechaFinal = new Date(System.currentTimeMillis());
			entidades = exportarArchivoBUAController.encontrarEntidadesConCodigoBUA();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	public void exportarArchivoBUA() {
		if (entidadSeleccionada != null) {
			if (fechaInicio.before(fechaFinal) || fechaInicio.equals(fechaFinal)) {
				resultado = exportarArchivoBUAController.exportarArchivo(entidadSeleccionada, fechaInicio, fechaFinal);

				if (resultado != null) {
					// TODO Escribir archivo en la respuesta de la peticion
					if (resultado == "") {
						FacesContext.getCurrentInstance().addMessage("growl",
								new FacesMessage("No se encontraron avaluos", "No existen avaluos en las fechas seleccionadas."));
					} else {
						try {
							setArchivoBua(new DefaultStreamedContent(new FileInputStream(new File(resultado)), "", resultado));
						} catch (FileNotFoundException e) {
							FacesContext.getCurrentInstance().addMessage("growl",
									new FacesMessage("Error", "El archivo BUA con nombre "
											+ exportarArchivoBUAController.getNombreArchivo() + " no se puede encontrar"));
						}
						FacesContext.getCurrentInstance().addMessage("growl",
								new FacesMessage("Archivo exportado", "El archivo BUA con nombre "
										+ exportarArchivoBUAController.getNombreArchivo() + " fue exportado exitosamente"));
					}
				} else {
					FacesContext.getCurrentInstance().addMessage("growl",
							new FacesMessage("Error", "El archivo BUA no fue exportado."));
				}
			} else {
				FacesContext.getCurrentInstance().addMessage("growl",
						new FacesMessage("Error", "La fecha de inicio debe ser menor o igual a la fecha final"));
			}
		} else {
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Error", "Debes seleccionar la entidad"));
		}
	}

	public void descargarArchivo() throws IOException {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();

		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(new File(resultado));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ec.responseReset(); // Some JSF component library or some Filter might have set some headers in the
												// buffer beforehand. We want to get rid of them, else it may collide.
		ec.setResponseContentType("application/zip"); // Check http://www.iana.org/assignments/media-types for all types.
																									// Use if necessary ExternalContext#getMimeType() for auto-detection
																									// based on filename.
		try {
			ec.setResponseContentLength((int) fileInputStream.getChannel().size());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Set it with the file size. This header is optional. It will work if it's
			// omitted, but the download progress will be unknown.
		resultado = "CBUA" + resultado.split("CBUA")[1];
		ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + resultado + "\""); // The Save As popup
																																																// magic is done here.
																																																// You can give it any
																																																// file name you want,
																																																// this only won't work
																																																// in MSIE, it will use
																																																// current request URL
																																																// as file name instead.

		OutputStream output = ec.getResponseOutputStream();
		// Now you can write the InputStream of the file to the above OutputStream the
		// usual way.
		// ...
		ByteStreams.copy(fileInputStream, output);

		fc.responseComplete(); // Important! Otherwise JSF will attempt to render the response which obviously
														// will fail since it's already written with a file and closed.
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public StreamedContent getArchivoBua() {
		return archivoBua;
	}

	public void setArchivoBua(StreamedContent archivoBua) {
		this.archivoBua = archivoBua;
	}

	public List<EntidadDTO> getEntidades() {
		return entidades;
	}

	public void setEntidades(List<EntidadDTO> entidades) {
		this.entidades = entidades;
	}

	public EntidadDTO getEntidadSeleccionada() {
		return entidadSeleccionada;
	}

	public void setEntidadSeleccionada(EntidadDTO entidadSeleccionada) {
		this.entidadSeleccionada = entidadSeleccionada;
	}

}
