package com.helio4.bancol.avaluos.dto;

import java.util.Date;

public class ModificacionDTO {
	
	private Long id;
	
	private Long avaluo;
	
	private Date fecha;
	
	private String campo;
	
	private String anterior;
	
	private String nuevo;
	
	private UsuarioDTO usuario;

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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public String getAnterior() {
		return anterior;
	}

	public void setAnterior(String anterior) {
		this.anterior = anterior;
	}

	public String getNuevo() {
		return nuevo;
	}

	public void setNuevo(String nuevo) {
		this.nuevo = nuevo;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}
	@Override
	public String toString() {
		return "ModificacionDTO[ avaluo: " +this.avaluo+" ,campo: "+this.campo+" ,anterior: "+ this.anterior+ " ,nuevo:"+ this.nuevo+" usuario"+this.usuario.getNombreUsuario()+"]";
	}
}
