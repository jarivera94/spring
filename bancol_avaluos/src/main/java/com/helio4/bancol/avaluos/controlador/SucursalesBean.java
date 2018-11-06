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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.helio4.bancol.avaluos.dominio.EntidadesController;
import com.helio4.bancol.avaluos.dominio.SucursalesController;
import com.helio4.bancol.avaluos.dto.EntidadDTO;
import com.helio4.bancol.avaluos.dto.SucursalDTO;
import com.helio4.bancol.avaluos.servicio.excepciones.EntidadNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.SucursalNotFoundException;

@Controller
@Scope("view")
@Qualifier("sucursalesBean")
public class SucursalesBean implements Serializable {

	private static Logger log = LoggerFactory.getLogger(SucursalesBean.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Listas
	 */
	private List<SucursalDTO> listSucursales;
	private SucursalDTO sucursalSeleccionada;
	private List<SucursalDTO> listFiltrarSucursales;
	private List<EntidadDTO> listEntidades;
	private SucursalDTO newSucursal;
	
	private String fechaCreacion;

	@Autowired
	private SucursalesController sucursalController;
	@Autowired
	private EntidadesController entidadController;
	private SucursalDTO sucursalAEliminar;


	@PostConstruct
	public void init() {
            
            try {
                listarSucursales();
		listarEntidades();
		newSucursal = new SucursalDTO();
		fechaActual();
            } catch (Exception e) {
                log.error(e.getMessage());
            }
	}

	private void fechaActual() {
		Date date = new Date();		
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		// Formateo de Fecha para mostrar en la vista - (String)
		setFechaCreacion(sm.format(date.getTime()));
		// Formateo de Fecha campo db - (Date)
		newSucursal.setFechaCreacion(getFechaCreacion());
	}
	
	public void crearSucursal() throws SucursalNotFoundException {
		if (validarCampos(getNewSucursal())) {
			if (validarSucursalRepetidaParaEntidad(getNewSucursal())) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,"Advertencia: ","Esta sucursal ya existe para la entidad"));
			} else {
				fechaActual();
				if (sucursalController.crearSucursal(getNewSucursal())) {
					mensajeGrow("Sucursal::Codigo " + getNewSucursal().getCodigo() + " Creada Satisfactoriamente");
					RequestContext.getCurrentInstance().execute("PF('sucCrearDialog').hide()");
					setNewSucursal(new SucursalDTO());
				} else {
					mensajeGrow("Sucursal::Codigo " + getNewSucursal().getCodigo() + " No Fue Creada");
				}
			}
			listarSucursales();
		}
		
	}
	
	public void actualizarSucursal(RowEditEvent event) throws SucursalNotFoundException {
		if (validarEntidadRepetidaSucursalActualizar(getSucursalSeleccionada())) {
			mensajeGrow("Sucursal::Codigo "+ getSucursalSeleccionada().getCodigo() + " ya existe para la entidad: " + getSucursalSeleccionada().getNombreEntidad());
		} else {
			try {
				sucursalController.actualizarTodo(getSucursalSeleccionada());
				mensajeGrow("Sucursal::Codigo " + getSucursalSeleccionada().getCodigo() + " actualizada");
			} catch (EntidadNotFoundException e) {
				mensajeGrow("Sucursal::Codigo " + getSucursalSeleccionada().getCodigo() + " no fue actualizada");
			}
		}
		listarSucursales();
	}	
	
	public void eliminarSucursal(SucursalDTO sucursalSeleccionada) throws SucursalNotFoundException {
		if (sucursalSeleccionada != null) {
			sucursalAEliminar = sucursalController.eliminarSucursal(sucursalSeleccionada);
			if (sucursalAEliminar != null) {
				mensajeGrow("Sucursal::Codigo " + sucursalAEliminar.getCodigo() + " eliminada");
			} else {
				mensajeGrow("Sucursal::Codigo " + sucursalAEliminar.getCodigo() + " no eliminada");
			}
			
		} else {
			SucursalDTO sucursalDTO = new SucursalDTO();
			mensajeGrow("Debe Selecionar una Sucursal" + sucursalDTO);
		}
		listarSucursales();
	}
	
	private Boolean validarCampos(SucursalDTO sucursalDTO) {
		Boolean validacionOk = true;
		String mensajeCampos = "";
		if (getNewSucursal() == null) {
			mensajeGrow("Se Deben Digitar los Campos de la Sucursal::Codigo " + sucursalDTO.getCodigo());
			validacionOk = false;
			return validacionOk;
		} else {
			getNewSucursal().setId(this.listSucursales.get(this.listSucursales.size() - 1).getId() + 1);
			if (getNewSucursal().getCodigo().isEmpty() || getNewSucursal().getCodigo() == null) {
				validacionOk = false;
				mensajeCampos += "Codigo, ";
			}
//			else{
//				getNewSucursal().setId(Long.parseLong(getNewSucursal().getCodigo()));
//			}
			if (getNewSucursal().getNombre().isEmpty() || getNewSucursal().getNombre() == null) {
				validacionOk = false;
				mensajeCampos += "Nombre, ";
			}
			if (getNewSucursal().getEntidadId() < 0 || getNewSucursal().getEntidadId() == null) {
				validacionOk = false;
				mensajeCampos += "Entidad, ";
			}else{
				for(EntidadDTO dto:this.listEntidades){
					if(dto.getId() == getNewSucursal().getEntidadId()){
						getNewSucursal().setNombreEntidad(dto.getNombre());
						break;
					}
				}
			}
			
			if (!validacionOk) {
				mensajeGrow("Se Deben Digitar los Campos: " + mensajeCampos + " para esta nueva sucursal");
			}
		}
		return validacionOk;
	}
	
	private Boolean validarSucursalRepetidaParaEntidad(SucursalDTO sucursalDTO) {
		// Variable comienza en false que indica que no existen repetidos.
		Boolean repetir = false;
		for(SucursalDTO dtoSucursalExistente: this.listSucursales){
			if(sucursalDTO.getEntidadId().equals(dtoSucursalExistente.getEntidadId()) && !sucursalDTO.getId().equals(dtoSucursalExistente.getId())){
				if(sucursalDTO.getCodigo().equals(dtoSucursalExistente.getCodigo())){
					repetir = true;
					break;
				}
			}
		}
		return repetir;
	}
	
	private Boolean validarEntidadRepetidaSucursalActualizar(SucursalDTO sucursalDTO) {		
		Boolean repetir = false;
		listarSucursales();
		for(SucursalDTO dtoSucursalExistente:this.listSucursales){
			if(sucursalDTO.getEntidadId().equals(dtoSucursalExistente.getEntidadId()) && !sucursalDTO.getId().equals(dtoSucursalExistente.getId())){
				if(sucursalDTO.getCodigo().equals(dtoSucursalExistente.getCodigo())){
					repetir = true;
					break;
				}
			}
		}
		return repetir;
	}
	
	private void mensajeGrow(String mensaje) {
		FacesMessage msg = new FacesMessage(mensaje);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void onRowCancel(RowEditEvent event) {
		mensajeGrow("Edicion cancelada para Sucursal::Codigo: "+ getSucursalSeleccionada());		
	}
	
	private void listarSucursales() {
		this.listSucursales = new ArrayList<SucursalDTO>();
		this.listSucursales.addAll(sucursalController.encontrarTodos());
	}

	private void listarEntidades() {
		setListEntidades(new ArrayList<EntidadDTO>());
		getListEntidades().addAll(entidadController.encontrarTodos());
	}
	
	/**
	 * ----------------- Getter and Setter --------------------------------
	 */
	public List<SucursalDTO> getListSucursales() {
		return listSucursales;
	}

	public void setListSucursales(List<SucursalDTO> listSucursales) {
		this.listSucursales = listSucursales;
	}

	public List<EntidadDTO> getListEntidades() {
		return listEntidades;
	}

	public void setListEntidades(List<EntidadDTO> listEntidades) {
		this.listEntidades = listEntidades;
	}

	public SucursalDTO getSucursalSeleccionada() {
		return sucursalSeleccionada;
	}

	public void setSucursalSeleccionada(SucursalDTO sucursalSeleccionada) {
		this.sucursalSeleccionada = sucursalSeleccionada;
	}

	public List<SucursalDTO> getListFiltrarSucursales() {
		return listFiltrarSucursales;
	}

	public void setListFiltrarSucursales(List<SucursalDTO> listFiltrarSucursales) {
		this.listFiltrarSucursales = listFiltrarSucursales;
	}

	public SucursalDTO getNewSucursal() {
		return newSucursal;
	}

	public void setNewSucursal(SucursalDTO newSucursal) {
		this.newSucursal = newSucursal;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
}
