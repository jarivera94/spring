package com.helio4.bancol.avaluos.controlador;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SortedMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import com.helio4.bancol.avaluos.dominio.Comparador;
import com.helio4.bancol.avaluos.dominio.EditarClienteController;
import com.helio4.bancol.avaluos.dominio.ListasGeograficasController;
import com.helio4.bancol.avaluos.dominio.ModificacionController;
import com.helio4.bancol.avaluos.dominio.ParametrosController;
import com.helio4.bancol.avaluos.dto.AvaluoDTO;
import com.helio4.bancol.avaluos.dto.ClienteDTO;
import com.helio4.bancol.avaluos.dto.DivipolaDTO;
import com.helio4.bancol.avaluos.dto.ModificacionDTO;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;
import com.helio4.bancol.avaluos.servicio.datos.AvaluoService;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;


@Controller
@Scope("view")
@Qualifier("editarClienteBean")
public class EditarClienteBean  implements Serializable {
	
	private static Logger log = LoggerFactory.getLogger( SolicitudBean.class );
	
	private static final long serialVersionUID = 99911070872543692L;
	/**
	 * Numero de cedula/nit/cedula extranjeria del cliente que se desea editar.
	 * */
	public Long numero;
	/**
	 * Tipo de documento
	 * */
	public int tipoDocumento;
	
	/**
	 * Cliente que se va a editar.
	 * */
	public ClienteDTO cliente;
	
	private String tipoViaSolicitante;
	private String numeroViaSolicitante;
	private List<String> complementoViaSolicitante;
	private String numeroViaGeneradoraSolicitante;
	private List<String> complementoViaGeneradoraSolicitante;
	private String placaSolicitante;
	private String complementoPlacaSolicitante;
	private String adicionalDireccionSolicitante;

	private List<String> complementoVia;
	private List<String> complementoViaGeneradora;
	private List<DivipolaDTO> ciudadesSolicitante;
	private String departamentoCliente;
	private SortedMap<String,String> departamentos;
	private SortedMap<String, String> paises;
	
	private final String[] letrasComplemento = {"A", "B", "C", "D", "E",
			"F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
			"S", "T", "U", "V", "W", "X", "Y", "Z"};

	private final String[] puntosCardinales = {"Norte", "Sur", "Este",
			"Oeste", "Bis", "Noreste", "Sureste", "Noroeste", "Suroeste",
			"Par", "Impar"};

	private final String[] valoresUnidadAlterno = {"Manzana", "Bloque",
			"Interior", "Edificio", "Torre", "Apartamento", "Casa", "Oficina",
			"Bodega", "Suite", "Local", "Garaje", "Consultorio", "Deposito",
			"Etapa", "Piso", "Nivel", "Agrupacion", "Unidad", "Tipo",
			"Sector", "Lote", "Superlote", "Conjunto", "Vivienda", "Módulo"};

	/**
	 * Cliente con la informacion original sin modificar.
	 * */
	private ClienteDTO clienteOriginal;
	
	@Autowired
	private ListadoAvaluosBean listadoAvaluosBean;
	
	@Autowired
	private EditarClienteController editarClienteController;
	
	@Autowired
	private ParametrosController parametrosController;
	
	@Autowired
	private ModificacionController modificacionesController;
	
	@Autowired
	private AvaluoService avaluoService;
	
	@Autowired
	private ListasGeograficasController listasGeograficasController;
	
	@PostConstruct
	public void init(){
		this.paises = listasGeograficasController.paises();
		this.departamentos = listasGeograficasController.departamentos();
	}
	public void buscarCliente() {
		this.cliente = this.editarClienteController.buscarCliente(this.tipoDocumento, this.numero);
		if(this.cliente != null) {
			try {
				this.clienteOriginal = (ClienteDTO) this.cliente.clone();
				if( this.cliente.getDivipola() !=null ){
					this.departamentoCliente = this.cliente.getDivipola().getDepartamento();
					this.ciudadesSolicitante = listasGeograficasController.ciudadesPorDepartamento(departamentoCliente);
				}
				this.separarDireccionCliente(this.cliente);
				
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}
		else {
			String tipoDocumento = (this.tipoDocumento == 21 ? "C.C" : this.tipoDocumento == 22 ? "C.Ext." : this.tipoDocumento == 23 ? "NIT." : "" );
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL,"Cliente ",
					"No se encontro ningun cliente con Tipo de documento:" + tipoDocumento+ ", Número de documento: "+this.numero);
	        FacesContext.getCurrentInstance().addMessage(null, message);
		}
	} 
	public void limpiarCliente() {
		this.cliente = null;
		this.clienteOriginal = null;
	}

	/**
	 * Guarda la informacion del cliente que se esta editando
	 * */
	public void guardar() {
		UsuarioDTO usuario = (UsuarioDTO) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		ClienteDTO clienteExistente = this.editarClienteController
				.buscarCliente(this.cliente.getTipoDocumentoIdentificacion(), this.cliente.getNumeroDocumento());
		
		/**
		 * Se verifica que no exista un cliente con el mismo tipo de documento y numero de documento en el sistema.
		 * */
		if(  clienteExistente == null || (clienteExistente!=null && 
				clienteExistente.getTipoDocumentoIdentificacion() == this.clienteOriginal.getTipoDocumentoIdentificacion() 
				&& clienteExistente.getNumeroDocumento().equals(this.clienteOriginal.getNumeroDocumento())) ){
			this.cliente = this.editarClienteController.guardar(this.clienteOriginal,this.cliente);
			
			List<AvaluoDTO> avaluosCliente = this.avaluoService.encontrarAvaluosAprobadosDeCliente(
					this.cliente.getTipoDocumentoIdentificacion(),
					this.cliente.getNumeroDocumento());
			for( AvaluoDTO avaluoDTO: avaluosCliente) {
				avaluoDTO.setCliente(this.cliente);
				try {
					this.avaluoService.actualizar(avaluoDTO);
				} catch (AvaluoNotFoundException e) {
					e.printStackTrace();
				}
				List<ModificacionDTO> modificaciones = Comparador.comparar(
						this.clienteOriginal, this.cliente, avaluoDTO.getId(), usuario,null);
				if( modificaciones != null && !modificaciones.isEmpty() ) {
					this.modificacionesController.guardar(modificaciones);
					//windows environment
					String path = parametrosController.obtenerValor("rutaReportes");
					String url = path + "imagenes\\" + avaluoDTO.getId() + ".pdf";
					File pdfPrevio = new File(url);
					if(pdfPrevio !=null && pdfPrevio.exists() && !pdfPrevio.isDirectory()) { 
					   pdfPrevio.delete();
					}
				}
			}
			try {
				this.clienteOriginal = (ClienteDTO) this.cliente.clone();
				this.cliente = null;
				this.clienteOriginal = null;
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,"Cliente ",
					"La información del cliente se guardo exitosamente");
	        FacesContext.getCurrentInstance().addMessage(null, message);
		}else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Cliente ",
					"Ya existe un cliente con el mismo tipo de documento y Número de documento");
	        FacesContext.getCurrentInstance().addMessage(null, message);
		}

	}
	
	public void onDepartamentoSolicitanteChanged() {
		ciudadesSolicitante = listasGeograficasController.ciudadesPorDepartamento(this.getDepartamentoCliente());
	}
 

	public List<String> completarComplemento(String valor) {
		List<String> resultado = new ArrayList<String>();
		for (String letra:letrasComplemento) {
			if (letra.startsWith(valor.toUpperCase())) {
				resultado.add(letra);
			}
		}
		for (String punto:puntosCardinales) {
			if (punto.startsWith(valor.toUpperCase())) {
				resultado.add(punto);
			}
		}
		return resultado;
	}

	public List<String> completarComplementoPlaca(String valor) {
		List<String> resultado = new ArrayList<String>();
		for (String letra:valoresUnidadAlterno) {
			if (letra.startsWith(valor)) {
				resultado.add(letra);
			}
		}
		return resultado;
	}
	
	private void separarDireccionCliente(ClienteDTO cliente) {
		String direccionOrignial = cliente.getDireccionDeContactoSolicitante() == null ? ""  : cliente.getDireccionDeContactoSolicitante()  ;
		Pattern patronTipoVia = Pattern.compile("[A-Z]{2}");
		String direccionCliente = cliente.getDireccionDeContactoSolicitante();
		if( direccionCliente!=null && !direccionCliente.isEmpty() && !direccionCliente.equals("  #  - ")) {
			Matcher matcher = patronTipoVia.matcher(direccionCliente);
			if(matcher.find()) {
				tipoViaSolicitante = matcher.group(0);
				direccionCliente = direccionCliente.replace(tipoViaSolicitante, "").trim();
			}else{
                log.error("La dirección: {} del cliente: {} es invalida",
                        cliente.getDireccionDeContactoSolicitante(),
                        cliente.getNumeroDocumento());
                adicionalDireccionSolicitante = adicionalDireccionSolicitante == null ? "" : adicionalDireccionSolicitante;
                adicionalDireccionSolicitante += direccionOrignial;
                return;
                
			}
			Pattern patronNumeroVia =  Pattern.compile("[0-9]+");
			matcher = patronNumeroVia.matcher(direccionCliente);
			if (matcher.find()) {
				numeroViaSolicitante = matcher.group(0);
				direccionCliente = direccionCliente.replace(numeroViaSolicitante, "").trim();
			}else {
				//throw new IllegalArgumentException("La dirección del cliente "+cliente+" es invalida");
				adicionalDireccionSolicitante = adicionalDireccionSolicitante == null ? "" : adicionalDireccionSolicitante;
				adicionalDireccionSolicitante += direccionOrignial;
	            return;
			}
			Pattern patronComplementoVia =  Pattern.compile("[a-zA-Z ]+");
			matcher = patronComplementoVia.matcher(direccionCliente);
			if (matcher.find()) {
				String stringComplementoViaSolicitante = matcher.group(0).trim().length() > 0 ? matcher.group(0).trim() : "";
				complementoViaSolicitante = stringComplementoViaSolicitante.isEmpty() ? new ArrayList<String>() : convertirALista(stringComplementoViaSolicitante);
				direccionCliente = ! complementoViaSolicitante.isEmpty() ? direccionCliente.replace(stringComplementoViaSolicitante, "").trim() : direccionCliente;
			}
			Pattern patronNumeral =  Pattern.compile("#");
			matcher = patronNumeral.matcher(direccionCliente);
			if (matcher.find()) {
				direccionCliente = direccionCliente.replace("#", "").trim();
			}
			Pattern patronNumeroViaGeneradora =  Pattern.compile("[0-9]+");
			matcher = patronNumeroViaGeneradora.matcher(direccionCliente);
			if (matcher.find()) {
				numeroViaGeneradoraSolicitante = matcher.group(0);
				direccionCliente = direccionCliente.replace(numeroViaGeneradoraSolicitante, "").trim();
			}
			Pattern patronComplementoViaGeneradora =  Pattern.compile("[a-zA-Z ]+");
			matcher = patronComplementoViaGeneradora.matcher(direccionCliente);
			if (matcher.find()) {
				String stringComplementoViaGeneradoraSolicitante = matcher.group(0).trim().length() > 0 ? matcher.group(0).trim() : "";
				complementoViaGeneradoraSolicitante = stringComplementoViaGeneradoraSolicitante.isEmpty() ? new ArrayList<String>() : convertirALista(stringComplementoViaGeneradoraSolicitante);
				direccionCliente = ! complementoViaGeneradoraSolicitante.isEmpty() ?  direccionCliente.replace(stringComplementoViaGeneradoraSolicitante, "").trim() : direccionCliente;
			}
			Pattern patronGuion =  Pattern.compile("-");
			matcher = patronGuion.matcher(direccionCliente);
			if (matcher.find()) {
				direccionCliente = direccionCliente.replace("-", "").trim();
			}
			Pattern patronPlaca =  Pattern.compile("[0-9]+");
			matcher = patronPlaca.matcher(direccionCliente);
			if (matcher.find()) {
				placaSolicitante = matcher.group(0);
				direccionCliente = direccionCliente.replace(placaSolicitante, "").trim();
			}
			Pattern patronComplementoPlaca =  Pattern.compile("[A-Za-z]{2,11} [0-9]{1,6}( [A-Za-z]{2,11} [0-9]{1,6})*");
			matcher = patronComplementoPlaca.matcher(direccionCliente);
			if (matcher.find()) {
				complementoPlacaSolicitante = matcher.group(0).trim();
				direccionCliente = direccionCliente.replace(complementoPlacaSolicitante, "").trim();
			}
			adicionalDireccionSolicitante = direccionCliente;
		}
	}
	private List<String> convertirALista(String string) {
		if (string.isEmpty()) {
			return new ArrayList<String>();
		}
		return Arrays.asList(string.split(" "));
	}

	public void cancelar(){
		String uri = FacesContext.getCurrentInstance().getExternalContext().encodeActionURL(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/pages/avaluos/listadoAvaluos.xhtml");
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(uri);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public int getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(int tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public ClienteDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDTO cliente) {
		this.cliente= cliente;
	}

	public String getTipoViaSolicitante() {
		return tipoViaSolicitante;
	}

	public void setTipoViaSolicitante(String tipoViaSolicitante) {
		this.tipoViaSolicitante = tipoViaSolicitante;
	}

	public String getNumeroViaSolicitante() {
		return numeroViaSolicitante;
	}

	public void setNumeroViaSolicitante(String numeroViaSolicitante) {
		this.numeroViaSolicitante = numeroViaSolicitante;
	}

	public List<String> getComplementoViaSolicitante() {
		return complementoViaSolicitante;
	}

	public void setComplementoViaSolicitante(List<String> complementoViaSolicitante) {
		this.complementoViaSolicitante = complementoViaSolicitante;
	}

	public String getNumeroViaGeneradoraSolicitante() {
		return numeroViaGeneradoraSolicitante;
	}

	public void setNumeroViaGeneradoraSolicitante(String numeroViaGeneradoraSolicitante) {
		this.numeroViaGeneradoraSolicitante = numeroViaGeneradoraSolicitante;
	}

	public List<String> getComplementoViaGeneradoraSolicitante() {
		return complementoViaGeneradoraSolicitante;
	}

	public void setComplementoViaGeneradoraSolicitante(List<String> complementoViaGeneradoraSolicitante) {
		this.complementoViaGeneradoraSolicitante = complementoViaGeneradoraSolicitante;
	}

	public String getPlacaSolicitante() {
		return placaSolicitante;
	}

	public void setPlacaSolicitante(String placaSolicitante) {
		this.placaSolicitante = placaSolicitante;
	}

	public String getComplementoPlacaSolicitante() {
		return complementoPlacaSolicitante;
	}

	public void setComplementoPlacaSolicitante(String complementoPlacaSolicitante) {
		this.complementoPlacaSolicitante = complementoPlacaSolicitante;
	}

	public String getAdicionalDireccionSolicitante() {
		return adicionalDireccionSolicitante;
	}

	public void setAdicionalDireccionSolicitante(String adicionalDireccionSolicitante) {
		this.adicionalDireccionSolicitante = adicionalDireccionSolicitante;
	}

	public List<String> getComplementoVia() {
		return complementoVia;
	}

	public void setComplementoVia(List<String> complementoVia) {
		this.complementoVia = complementoVia;
	}

	public List<String> getComplementoViaGeneradora() {
		return complementoViaGeneradora;
	}

	public void setComplementoViaGeneradora(List<String> complementoViaGeneradora) {
		this.complementoViaGeneradora = complementoViaGeneradora;
	}

	public ClienteDTO getClienteOriginal() {
		return clienteOriginal;
	}

	public void setClienteOriginal(ClienteDTO clienteOriginal) {
		this.clienteOriginal = clienteOriginal;
	}

	public ListadoAvaluosBean getListadoAvaluosBean() {
		return listadoAvaluosBean;
	}

	public void setListadoAvaluosBean(ListadoAvaluosBean listadoAvaluosBean) {
		this.listadoAvaluosBean = listadoAvaluosBean;
	}

	public EditarClienteController getEditarClienteController() {
		return editarClienteController;
	}

	public void setEditarClienteController(EditarClienteController editarClienteController) {
		this.editarClienteController = editarClienteController;
	}

	public ParametrosController getParametrosController() {
		return parametrosController;
	}

	public void setParametrosController(ParametrosController parametrosController) {
		this.parametrosController = parametrosController;
	}

	public ModificacionController getModificacionesController() {
		return modificacionesController;
	}

	public void setModificacionesController(ModificacionController modificacionesController) {
		this.modificacionesController = modificacionesController;
	}

	public AvaluoService getAvaluoService() {
		return avaluoService;
	}

	public void setAvaluoService(AvaluoService avaluoService) {
		this.avaluoService = avaluoService;
	}

	public List<DivipolaDTO> getCiudadesSolicitante() {
		return ciudadesSolicitante;
	}

	public void setCiudadesSolicitante(List<DivipolaDTO> ciudadesSolicitante) {
		this.ciudadesSolicitante = ciudadesSolicitante;
	}

	public String getDepartamentoCliente() {
		return departamentoCliente;
	}

	public void setDepartamentoCliente(String departamentoCliente) {
		this.departamentoCliente = departamentoCliente;
	}

	public SortedMap<String,String> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(SortedMap<String,String> departamentos) {
		this.departamentos = departamentos;
	}
	public SortedMap<String, String> getPaises() {
		return paises;
	}
	public void setPaises(SortedMap<String, String> paises) {
		this.paises = paises;
	}
}
