package com.helio4.bancol.avaluos.dto;

import java.util.Date;

import com.helio4.bancol.avaluos.modelo.Usuario;
import com.helio4.bancol.avaluos.servicio.util.Constantes;
import com.helio4.bancol.avaluos.servicio.util.Constantes.Estado;

public class BitacoraDTO {
	
	private Long id;
	
	private Long avaluo;
	
	private Constantes.Estado estado;
	
	private Date fecha;
	
	private UsuarioDTO usuario;
	
	private String observaciones;
	
	private Boolean notificacionCreador;
	
	private Boolean notificacionPerito;
	
	private Boolean notificacionRevisor;
	
	private Boolean notificacionCliente;
	
	private Boolean notificacionPersonaRecibePerito;
	
	private Boolean notificacionAsesor;
	
	private Boolean notificacionSeguidor;

	public BitacoraDTO(){}
	
	public BitacoraDTO( Integer estado,Date fecha,Usuario usuario, String observaciones,
			boolean nCreador, boolean nPerito, boolean nRevisor,boolean nCliente, boolean nPersonaPerito, boolean nAsesor, boolean nSeguidor ){
		this.estado = Estado.fromKey( estado );
		this.fecha = fecha;
		this.observaciones = observaciones;
		this.usuario = new UsuarioDTO();
		this.usuario.setNombres(usuario.getNombres());
		this.usuario.setApellidos(usuario.getApellidos());
		RolDTO rolDTO = new RolDTO();
		rolDTO.setId(usuario.getRol().getId());
		rolDTO.setNombre(usuario.getRol().getNombre());
		this.usuario.setRol(rolDTO);
		this.notificacionCreador = nCreador;
		this.notificacionPerito = nPerito;
		this.notificacionRevisor = nRevisor;
		this.notificacionCliente = nCliente;
		this.notificacionPersonaRecibePerito = nPersonaPerito;
		this.notificacionAsesor = nAsesor;
		this.notificacionSeguidor = nSeguidor;
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAvaluo() {
		return avaluo;
	}

	public void setAvaluo(Long avaluo) {
		this.avaluo = avaluo;
	}

	public Constantes.Estado getEstado() {
		return estado;
	}

	public void setEstado(Constantes.Estado estado) {
		this.estado = estado;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
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
