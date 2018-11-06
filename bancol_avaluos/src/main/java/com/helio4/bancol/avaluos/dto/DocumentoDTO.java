package com.helio4.bancol.avaluos.dto;

import java.io.Serializable;


public class DocumentoDTO implements Serializable{
	

	private static final long serialVersionUID = -4453170731469912377L;

	private Long id;
	
	private String nombre;
	
	private String rutaUbicacion;
	
	private String tipoDocumento;
	
	private Long avaluoId;
	
	
	public DocumentoDTO(){}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getRutaUbicacion() {
		return rutaUbicacion;
	}


	public void setRutaUbicacion(String rutaUbicacion) {
		this.rutaUbicacion = rutaUbicacion;
	}


	public String getTipoDocumento() {
		return tipoDocumento;
	}


	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}


	public Long getAvaluoId() {
		return avaluoId;
	}


	public void setAvaluoId(Long avaluoId) {
		this.avaluoId = avaluoId;
	}
	

}
