package com.helio4.bancol.avaluos.controlador;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import com.helio4.bancol.avaluos.dominio.BitacoraController;
import com.helio4.bancol.avaluos.dto.AvaluoDTO;
import com.helio4.bancol.avaluos.dto.BitacoraDTO;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;

@Controller
@Scope("view")
@Qualifier("bitacoraBean")
public class BitacoraBean implements Serializable{
	
	private static final long serialVersionUID = -936181369084783760L;
	private static Logger log = LoggerFactory.getLogger(BitacoraBean.class);
	
	@Autowired
	private ListadoAvaluosBean listadoAvaluosBean;
	
	@Autowired
	private BitacoraController bitacoraController;
	
	private AvaluoDTO avaluo;
	private List<BitacoraDTO> bitacoras;
	private String observaciones;
	private Boolean notificacionCreador;
	private Boolean notificacionPerito;
	private Boolean notificacionRevisor;
	private Boolean notificacionCliente;
	private Boolean notificacionPersonaRecibePerito;
	private Boolean notificacionAsesor;
	private Boolean notificacionSeguidor;
	
	@PostConstruct
	public void init() {
		avaluo = listadoAvaluosBean.getAvaluo();
		bitacoras = bitacoraController.encontrarBitacoraPorAvaluo(avaluo.getId());
	}
	
	public void agregarAnotacion(){
		BitacoraDTO bitacora = new BitacoraDTO();
		bitacora.setId(null);
		
		bitacora.setNotificacionAsesor(notificacionAsesor);
		bitacora.setNotificacionCliente(notificacionCliente);
		bitacora.setNotificacionCreador(notificacionCreador);
		bitacora.setNotificacionPerito(notificacionPerito);
		bitacora.setNotificacionPersonaRecibePerito(notificacionPersonaRecibePerito);
		bitacora.setNotificacionRevisor(notificacionRevisor);
		bitacora.setNotificacionSeguidor(notificacionSeguidor);
		
		bitacora.setAvaluo(avaluo.getId());
		bitacora.setEstado(avaluo.getEstado().getEstado());
		bitacora.setFecha(new Date());
		bitacora.setObservaciones(observaciones);
		bitacora.setUsuario((UsuarioDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		bitacora = bitacoraController.guardar(bitacora, avaluo);
		bitacoras.add(bitacora);
		observaciones = null;
		notificacionCreador = null;
		notificacionPerito = null;
		notificacionRevisor = null;
		notificacionCliente = null;
		notificacionPersonaRecibePerito = null;
		notificacionAsesor = null;
		notificacionSeguidor = null;
	}
	
	public void cancelar(){
		String uri = FacesContext.getCurrentInstance().getExternalContext().encodeActionURL(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/pages/avaluos/listadoAvaluos.xhtml");
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(uri);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public AvaluoDTO getAvaluo() {
		return avaluo;
	}

	public void setAvaluo(AvaluoDTO avaluo) {
		this.avaluo = avaluo;
	}

	public List<BitacoraDTO> getBitacoras() {
		return bitacoras;
	}

	public void setBitacoras(List<BitacoraDTO> bitacoras) {
		this.bitacoras = bitacoras;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Boolean getNotificacionCreador() {
		return notificacionCreador;
	}

	public void setNotificacionCreador(Boolean notificacionCreador) {
		this.notificacionCreador = notificacionCreador;
	}

	public Boolean getNotificacionPerito() {
		return notificacionPerito;
	}

	public void setNotificacionPerito(Boolean notificacionPerito) {
		this.notificacionPerito = notificacionPerito;
	}

	public Boolean getNotificacionRevisor() {
		return notificacionRevisor;
	}

	public void setNotificacionRevisor(Boolean notificacionRevisor) {
		this.notificacionRevisor = notificacionRevisor;
	}

	public Boolean getNotificacionCliente() {
		return notificacionCliente;
	}

	public void setNotificacionCliente(Boolean notificacionCliente) {
		this.notificacionCliente = notificacionCliente;
	}

	public Boolean getNotificacionPersonaRecibePerito() {
		return notificacionPersonaRecibePerito;
	}

	public void setNotificacionPersonaRecibePerito(
			Boolean notificacionPersonaRecibePerito) {
		this.notificacionPersonaRecibePerito = notificacionPersonaRecibePerito;
	}

	public Boolean getNotificacionAsesor() {
		return notificacionAsesor;
	}

	public void setNotificacionAsesor(Boolean notificacionAsesor) {
		this.notificacionAsesor = notificacionAsesor;
	}

	public Boolean getNotificacionSeguidor() {
		return notificacionSeguidor;
	}

	public void setNotificacionSeguidor(Boolean notificacionSeguidor) {
		this.notificacionSeguidor = notificacionSeguidor;
	}

}
