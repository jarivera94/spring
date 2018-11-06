package com.helio4.bancol.avaluos.controlador;

import java.io.Serializable;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.helio4.bancol.avaluos.dominio.EntidadesController;
import com.helio4.bancol.avaluos.dominio.SegmentoController;
import com.helio4.bancol.avaluos.dto.EntidadDTO;
import com.helio4.bancol.avaluos.dto.SegmentoDTO;
import com.helio4.bancol.avaluos.servicio.excepciones.EntidadNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.SegmentoNotFoundException;

@Controller
@Scope("view")
@Qualifier("segmentosBean")
public class SegmentosBean implements Serializable {

	private static Logger log = LoggerFactory.getLogger( SegmentosBean.class );
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Listas
	 */
	private List<SegmentoDTO> listSegmentos;
	private SegmentoDTO segmentoSeleccionado;
	private List<SegmentoDTO> listFiltrarSegmentos;
	private List<EntidadDTO> listEntidades;
	private SegmentoDTO newSegmento;
	
	private String fechaCreacion;
	
	@Autowired
	private SegmentoController segmentoController;
	@Autowired 
	private EntidadesController entidadesController;
	
	/**
	 * PostConstruct - Cargue Inicial
	 * @throws Exception
	 */
	@PostConstruct
	public void init() {
            
            try {
                listarSegmentos();
		listarEntidades();
		newSegmento = new SegmentoDTO();
		fechaActual();
            } catch (Exception e) {
                log.error(e.getMessage());
            }
	}
	
	/**
	 * ------------------------ Metodos del SegmentoesBean. -----------------------------------------------
	 */

	/**
	 * Formateo de la fecha actual para mostrar en la Vista (String) 
	 * Formateo para el campo en la DB (Date)
	 */
	private void fechaActual() {
		Date date = new Date();		
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		// Formateo de Fecha para mostrar en la vista - (String)
		setFechaCreacion(sm.format(date.getTime()));
		newSegmento.setFechaCreacion(getFechaCreacion());
	}

	/**
	 * validarNomSegmento
	 * @param segmentoDTO Objeto de la Segmento
	 * @return Confirmaci�n
	 */
	private Boolean validarNomSegmento(SegmentoDTO segmentoDTO) {
		// Variable comienza en false que indica que no existen repetidos.
		Boolean repetir = false;
		for (SegmentoDTO segmento : getListSegmentos()) {
			// Si son iguales si existen repetidos y no recorre mas la lista
			if ((segmentoDTO.getNombre().trim()).equals(segmento.getNombre().trim())) {
				repetir = true;
				break;
			}
		}
		return repetir;
	}
	
	/**
	 * validarNomSegmentoActualizar
	 * @param segmentoDTO Objeto
	 * @return Confirmacion
	 */
	private Boolean validarNomSegmentoActualizar(SegmentoDTO segmentoDTO) {		
		Boolean repetir = false;
		int conNombre = 0;
		for (SegmentoDTO segmento : getListSegmentos()) {
			if (segmentoDTO.getNombre().trim().equals(segmento.getNombre().trim())) {
				conNombre += 1;
				if (conNombre > 1) {
					repetir = true;
					break;
				}
			}
		}
		return repetir;
	}
	
	/**
	 * Mensaje a mostrar en el grow
	 * @param mensaje a Mostrar
	 * @param segmentoDTO Objeto
	 */
	private void mensajeGrow(String mensaje, SegmentoDTO segmentoDTO) {
		FacesMessage msg = new FacesMessage(mensaje, segmentoDTO.getNombre());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	/**
	 * Metodo Crear segmento
	 * @throws SegmentoNotFoundException
	 */
	public void crearSegmento() throws EntidadNotFoundException {
		
		if (validarCampos(getNewSegmento())) {
			if (validarNomSegmento(getNewSegmento())) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,"Advertencia: ","El segmento ya existe en GIA"));
			} else {	
				if (segmentoController.crearSegmento(getNewSegmento())) {
					mensajeGrow("Segmento Creado Satisfactoriamente", getNewSegmento());
					RequestContext.getCurrentInstance().execute("PF('segCrearDialog').hide()");
					setNewSegmento(new SegmentoDTO());
				} else {
					mensajeGrow("Segmento No Fue Creado", getNewSegmento());
				}
			}
			listarSegmentos();
		}
		
	}
	
	/**
	 * Validacion de que los campos sean diligenciados.
	 * @return Confirmaci�n de la validaci�n.
	 */
	private Boolean validarCampos(SegmentoDTO segmentoDTO) {
		Boolean validacionOk = true;		
		if (getNewSegmento() == null) {
			mensajeGrow("Se Deben Digitar los Campos de la Segmento", segmentoDTO);
			validacionOk = false;
			return validacionOk;
		} else {			
			if (getNewSegmento().getNombre().isEmpty() || getNewSegmento().getNombre() == null) {
				validacionOk = false;
				mensajeGrow("Se Debe Digitar el Campo Nombre", getNewSegmento());
			}			
		}
		return validacionOk;
	}
	
	/**
	 * Metodo actualizar Segmento
	 * @throws SegmentoNotFoundException
	 */
	public void actualizarSegmento(RowEditEvent event) throws SegmentoNotFoundException {
		if (validarNomSegmentoActualizar(getSegmentoSeleccionado())) {
			mensajeGrow("Segmento Ya Existe: ", getSegmentoSeleccionado());			
		} else { 
			try {
				segmentoController.actualizarTodo(getSegmentoSeleccionado());
				mensajeGrow("Segmento Actualizado: ", getSegmentoSeleccionado());
			} catch (SegmentoNotFoundException e) {
				mensajeGrow("Segmento No Fue Actualizado: ", getSegmentoSeleccionado());
			}	
		}
		listarSegmentos();
	}	

	/**
	 * Mensaje de Cancelar Edici�n
	 * @param event Cancelar
	 */
	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Editar Cancelado", getSegmentoSeleccionado().getNombre());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}	
	
	/**
	 * Lista la Segmentos existentes en la DB.
	 */
	private void listarSegmentos() {
		setListSegmentos(new ArrayList<SegmentoDTO>());
		getListSegmentos().addAll(segmentoController.encontrarTodos());
	}
	
	/**
	 * Metodo que elimina el segmento seleccionado.
	 * @throws SegmentoNotFoundException
	 */
	public void eliminarSegmento(SegmentoDTO segmentoSeleccionado) throws SegmentoNotFoundException {
		if (segmentoSeleccionado != null) {
			SegmentoDTO segmentoAEliminar = segmentoController.eliminarSegmento(segmentoSeleccionado);
			mensajeGrow("Segmento Eliminado: ", segmentoAEliminar);
		} else {
			SegmentoDTO segmentoDTO = new SegmentoDTO();
			mensajeGrow("Debe Selecionar un Segmento", segmentoDTO);
		}
		listarSegmentos();
	}
	
	/**
	 * Evento del cancelar del Edit
	 * @param event Click
	 */
	public void onDateSelect(SelectEvent event) {
		/**
		FacesContext facesContext = FacesContext.getCurrentInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
		*/
	}
	
	/**
	 * Lista la Entidades existentes en la DB.
	 */
	private void listarEntidades() {
		setListEntidades(new ArrayList<EntidadDTO>());
		getListEntidades().addAll(entidadesController.encontrarTodos());
	}

	/**
	 * --------------------------   Getter and Setter  ------------------------------------------------------
	 */
	public List<SegmentoDTO> getListSegmentos() {
		return listSegmentos;
	}

	public void setListSegmentos(List<SegmentoDTO> listSegmentos) {
		this.listSegmentos = listSegmentos;
	}

	/**
	 * @return the segmentoSeleccionado
	 */
	public SegmentoDTO getSegmentoSeleccionado() {
		return segmentoSeleccionado;
	}

	/**
	 * @param segmentoSeleccionado the segmentoSeleccionado to set
	 */
	public void setSegmentoSeleccionado(SegmentoDTO segmentoSeleccionado) {
		this.segmentoSeleccionado = segmentoSeleccionado;
	}

	/**
	 * @return the listFiltrarSegmentos
	 */
	public List<SegmentoDTO> getListFiltrarSegmentos() {
		return listFiltrarSegmentos;
	}

	/**
	 * @param listFiltrarSegmentos the listFiltrarSegmentos to set
	 */
	public void setListFiltrarSegmentos(List<SegmentoDTO> listFiltrarSegmentos) {
		this.listFiltrarSegmentos = listFiltrarSegmentos;
	}

	/**
	 * @return the listEntidades
	 */
	public List<EntidadDTO> getListEntidades() {
		return listEntidades;
	}

	/**
	 * @param listEntidades the listEntidades to set
	 */
	public void setListEntidades(List<EntidadDTO> listEntidades) {
		this.listEntidades = listEntidades;
	}

	public SegmentoDTO getNewSegmento() {
		return newSegmento;
	}

	public void setNewSegmento(SegmentoDTO newSegmento) {
		this.newSegmento = newSegmento;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}	
}