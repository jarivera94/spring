package com.helio4.bancol.avaluos.controlador;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DualListModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.helio4.bancol.avaluos.dominio.EntidadesController;
import com.helio4.bancol.avaluos.dto.EntidadDTO;
import com.helio4.bancol.avaluos.servicio.excepciones.EntidadNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.SegmentoNotFoundException;

@Controller
@Scope("view")
@Qualifier("entidadesBean")
public class EntidadesBean implements Serializable {

	private static Logger log = LoggerFactory.getLogger(EntidadesBean.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Listas
	 */
	private List<EntidadDTO> listEntidades;
	private EntidadDTO entidadSeleccionada;
	private List<EntidadDTO> listFiltrarEntidades;
	private EntidadDTO newEntidad;
	private DualListModel<EntidadDTO> entidadesM;
	private List<EntidadDTO> entidadsource;
	private List<EntidadDTO> entidadtarget;

	private String fechaCreacion;
	private boolean tieneCodigoBUA;

	@Autowired
	private EntidadesController entidadController;

	/**
	 * PostConstruct - Cargue Inicial
	 * 
	 * @throws Exception
	 */
	@PostConstruct
	public void init() {
		try {
			log.debug("Inicializando listas de entidades desde la base de datos");
			listarEntidades();
			newEntidad = new EntidadDTO();
			fechaActual();
			entidadsource = new ArrayList<EntidadDTO>();
			entidadsource = listEntidades;
			entidadtarget = new ArrayList<EntidadDTO>();
			entidadesM = new DualListModel<EntidadDTO>(entidadsource, entidadtarget);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	/**
	 * --------------------- Metodos EntidadesBean
	 * ---------------------------------------------
	 */

	/**
	 * Formateo de la fecha actual para mostrar en la Vista (String) Formateo para
	 * el campo en la DB (Date)
	 */
	private void fechaActual() {
		Date date = new Date();
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		// Formateo de Fecha para mostrar en la vista - (String)
		setFechaCreacion(sm.format(date.getTime()));
		// Formateo de Fecha campo db - (Date)
		try {
			newEntidad.setFechaCreacion(sm.parse(getFechaCreacion()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;
	}

	/**
	 * validarNomNitRepetidoEntidad
	 * 
	 * @param entidadDTO Objeto de la Entidad
	 * @return Confirmaci�n
	 */
	private Boolean validarNomNitRepetidoEntidad(EntidadDTO entidadDTO) {
		// Variable comienza en false que indica que no existen repetidos.
		Boolean repetir = false;
		for (EntidadDTO entidad : getListEntidades()) {
			// Si son iguales si existen repetidos y no recorre mas la lista
			if ((entidadDTO.getNombre().trim()).equals(entidad.getNombre().trim())) {
				repetir = true;
				break;
			}

			if (entidad.getNit() != null && entidadDTO.getNit().trim().equals(entidad.getNit().trim())) {
				repetir = true;
				break;
			}

			if ((entidadDTO.getPrefijo().trim()).equals(entidad.getPrefijo().trim())) {
				repetir = true;
				break;
			}
		}
		return repetir;
	}

	/**
	 * validarNomNitRepetidoEntidadActualizar
	 * 
	 * @param entidadDTO
	 * @return Confirmacion
	 */
	private Boolean validarNomNitPreRepetidoEntidadActualizar(EntidadDTO entidadDTO) {
		Boolean repetir = false;
		int contNit = 0, conNombre = 0, conPrefijo = 0;
		for (EntidadDTO entidad : getListEntidades()) {
			if (entidad.getNit() != null && entidadDTO.getNit().trim().equals(entidad.getNit().trim())) {
				contNit += 1;
				if (contNit > 1) {
					repetir = true;
					break;
				}
			}

			if (entidadDTO.getNombre().trim().equals(entidad.getNombre().trim())) {
				conNombre += 1;
				if (conNombre > 1) {
					repetir = true;
					break;
				}
			}

			if (entidadDTO.getPrefijo().trim().equals(entidad.getPrefijo().trim())) {
				conPrefijo += 1;
				if (conPrefijo > 1) {
					repetir = true;
					break;
				}
			}
		}
		return repetir;
	}

	/**
	 * Mensaje para mostrar el Grow
	 * 
	 * @param mensaje
	 */
	private void mensajeGrow(String mensaje, EntidadDTO entidadDTO) {
		FacesMessage msg = new FacesMessage(mensaje, entidadDTO.getNit() + " " + entidadDTO.getNombre());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	/**
	 * Metodo para crear la entidad.
	 * 
	 * @throws SegmentoNotFoundException
	 */
	public void crearEntidad() throws EntidadNotFoundException {
		if (validarCampos(getNewEntidad())) {
			if (validarNomNitRepetidoEntidad(getNewEntidad())) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia: ", "Nombre, Nit o Prejifo ya existe en GIA"));
			} else {
				if (entidadController.crearEntidad(getNewEntidad())) {
					mensajeGrow("Entidad Creada Satisfactoriamente", getNewEntidad());
					RequestContext.getCurrentInstance().execute("PF('entCrearDialog').hide()");
					setNewEntidad(new EntidadDTO());
					resetCodigoBUA();
				} else {
					mensajeGrow("Entidad No Fue Creada", getNewEntidad());
				}
			}
			listarEntidades();
			// newEntidad = new EntidadDTO();
			// fechaActual();
		}

	}

	/**
	 * Validacion de que los campos sean diligenciados.
	 * 
	 * @return Confirmaci�n de la validaci�n.
	 */
	private Boolean validarCampos(EntidadDTO entidadDTO) {
		Boolean validacionOk = true;
		String mensajeCampos = "";
		if (entidadDTO == null) {
			mensajeGrow("Se Deben Digitar los Campos de la Entidad", entidadDTO);
			validacionOk = false;
			return validacionOk;
		} else {
			if (entidadDTO.getNit().isEmpty() || entidadDTO.getNit() == null) {
				validacionOk = false;
				mensajeCampos += "Nit - ";
			}
			if (entidadDTO.getNombre().isEmpty() || entidadDTO.getNombre() == null) {
				validacionOk = false;
				mensajeCampos += "Nombre - ";
			}
			if (entidadDTO.getPrefijo().isEmpty() || entidadDTO.getPrefijo() == null) {
				validacionOk = false;
				mensajeCampos += "Prefijo - ";
			}
			if (entidadDTO.getPorcentajeValorAsegurable() <= 0.0 || entidadDTO.getPorcentajeValorAsegurable() == null) {
				validacionOk = false;
				mensajeCampos += " Porcentaje Valor Asegurable";
			}
			if (entidadDTO.getDuracionSemaforoAmarillo() <= 0) {
				validacionOk = false;
				mensajeCampos += "Semaforo Amarillo - ";
			}
			if (entidadDTO.getDuracionSemaforoRojo() <= 0) {
				validacionOk = false;
				mensajeCampos += "Semaforo Rojo - ";
			}
			if (entidadDTO.getDuracionSemaforoVerde() <= 0) {
				validacionOk = false;
				mensajeCampos += "Semaforo Verde - ";
			}
			if ((tieneCodigoBUA) && (entidadDTO.getCodigoBUA() == null || entidadDTO.getCodigoBUA() == 0)) {
				validacionOk = false;
				mensajeCampos += "Codigo BUA - ";
			}
			if ((!tieneCodigoBUA) && entidadDTO.getCodigoBUA() == null) {
				entidadDTO.setCodigoBUA(0);
			}

			if (!validacionOk) {
				mensajeGrow("Se Deben Digitar los Campos: " + mensajeCampos, entidadDTO);
			}
		}
		return validacionOk;
	}

	/**
	 * Metodo actualizar Entidad
	 * 
	 * @param event Seleccion
	 * @throws EntidadNotFoundException
	 */
	public void actualizarEntidad(RowEditEvent event) throws EntidadNotFoundException {
		if (validarCampos(getEntidadSeleccionada())) {
			if (validarNomNitPreRepetidoEntidadActualizar(getEntidadSeleccionada())) {
				mensajeGrow("Esta entidad ya existe en GIA: ", getEntidadSeleccionada());
			} else {
				if (entidadController.actualizarTodo(getEntidadSeleccionada())) {
					mensajeGrow("Entidad Actualizada: ", getEntidadSeleccionada());
				} else {
					mensajeGrow("Entidad No Fue Actualizada", getEntidadSeleccionada());
				}
			}
			// RequestContext.getCurrentInstance().update(":form:ent");
			setListEntidades(new ArrayList<EntidadDTO>());
		}
		listarEntidades();
	}

	/**
	 * Mensaje de Cancelar Edici�n
	 * 
	 * @param event Cancelar
	 */
	public void onRowCancel(RowEditEvent event) {
		mensajeGrow("Edicion Cancelada", getEntidadSeleccionada());
	}

	/**
	 * Metodo que elimina la entidad seleccionada.
	 * 
	 * @throws EntidadNotFoundException
	 */
	public void eliminarEntidad(EntidadDTO entidadSeleccionada) throws EntidadNotFoundException {
		if (entidadSeleccionada != null) {
			EntidadDTO entidadAEliminar = entidadController.eliminarEntidad(entidadSeleccionada);
			// Muestra el mensaje de la Entidad Eliminada
			if ((entidadAEliminar.getAvaluosRelacionados() == null) && (entidadAEliminar.getSegmentosRelacionados() == null)
					&& (entidadAEliminar.getTarifasRelacionados() == null)
					&& (entidadAEliminar.getSucursalesRelacionadas() == null)) {
				mensajeGrow("Entidad Eliminada: ", entidadSeleccionada);
			}

			// Muestra el mensaje de las sucursales relacionados a la Entidad
			if (entidadAEliminar.getSucursalesRelacionadas() != null) {
				mensajeGrow("Sucursales Relacionadas: " + entidadAEliminar.getSucursalesRelacionadas(), entidadAEliminar);
			}

			// Muestra el mensaje de los avaluos relacionados a la Entidad
			if (entidadAEliminar.getAvaluosRelacionados() != null) {
				mensajeGrow("Avaluos Relacionados: " + entidadAEliminar.getAvaluosRelacionados(), entidadAEliminar);
			}

			// Muestra el mensaje de los segmentos relacionados a la Entidad
			if (entidadAEliminar.getSegmentosRelacionados() != null) {
				mensajeGrow("Segmentos Relacionados: " + entidadAEliminar.getSegmentosRelacionados(), entidadAEliminar);
			}

			// Muestra el mensaje de las tarifas relacionados a la Entidad
			if (entidadAEliminar.getTarifasRelacionados() != null) {
				mensajeGrow("Tarifas Relacionadas: " + entidadAEliminar.getTarifasRelacionados(), entidadAEliminar);
			}
		} else {
			EntidadDTO entidadDTO = new EntidadDTO();
			mensajeGrow("Debe Selecionar una Entidad", entidadDTO);
		}
		listarEntidades();
	}

	/**
	 * Evento del cancelar del Edit
	 * 
	 * @param event Click
	 */
	public void onDateSelect(SelectEvent event) {
		/**
		 * FacesContext facesContext = FacesContext.getCurrentInstance();
		 * SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		 * facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
		 * "Date Selected", format.format(event.getObject())));
		 */
	}

	/**
	 * Lista la Entidades existentes en la DB.
	 */
	private void listarEntidades() {
		setListEntidades(new ArrayList<EntidadDTO>());
		getListEntidades().addAll(entidadController.encontrarTodos());
		if (!getListEntidades().isEmpty()) {
			for (EntidadDTO entidadDTO : getListEntidades()) {
				// Conversión de decimales.
				Double porcentaje = entidadDTO.getPorcentajeValorAsegurable() * 100;
				double por = Math.round(porcentaje * Math.pow(10, 2)) / Math.pow(10, 2);
				entidadDTO.setPorcentajeValorAsegurable(por);
			}
		}
	}

	public void resetCodigoBUA() {
		tieneCodigoBUA = false;
		setNewEntidad(new EntidadDTO());
	}

	/**
	 * -------------------- Getter and Setter
	 * ---------------------------------------
	 */
	public List<EntidadDTO> getListEntidades() {
		return listEntidades;
	}

	public void setListEntidades(List<EntidadDTO> listEntidades) {
		this.listEntidades = listEntidades;
	}

	public EntidadDTO getEntidadSeleccionada() {
		return entidadSeleccionada;
	}

	public void setEntidadSeleccionada(EntidadDTO entidadSeleccionada) {
		this.entidadSeleccionada = entidadSeleccionada;
	}

	public List<EntidadDTO> getListFiltrarEntidades() {
		return listFiltrarEntidades;
	}

	public void setListFiltrarEntidades(List<EntidadDTO> listFiltrarEntidades) {
		this.listFiltrarEntidades = listFiltrarEntidades;
	}

	public EntidadDTO getNewEntidad() {
		return newEntidad;
	}

	public void setNewEntidad(EntidadDTO newEntidad) {
		this.newEntidad = newEntidad;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public DualListModel<EntidadDTO> getEntidadesM() {
		return entidadesM;
	}

	public void setEntidadesM(DualListModel<EntidadDTO> entidadesM) {
		this.entidadesM = entidadesM;
	}

	public List<EntidadDTO> getEntidadsource() {
		return entidadsource;
	}

	public void setEntidadsource(ArrayList<EntidadDTO> entidadsource) {
		this.entidadsource = entidadsource;
	}

	public List<EntidadDTO> getEntidadtarget() {
		return entidadtarget;
	}

	public void setEntidadtarget(ArrayList<EntidadDTO> entidadtarget) {
		this.entidadtarget = entidadtarget;
	}

	public boolean isTieneCodigoBUA() {
		return tieneCodigoBUA;
	}

	public void setTieneCodigoBUA(boolean tieneCodigoBUA) {
		this.tieneCodigoBUA = tieneCodigoBUA;
	}

}