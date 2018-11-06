package com.helio4.bancol.avaluos.modelo.api;


public class EntidadRequest {

	private Long codigoEntidad;
	private Long codigoSegmento;
	private Long codigoMotivo;
	
	public EntidadRequest() {
		// TODO Auto-generated constructor stub
	}

	public Long getCodigoEntidad() {
		return codigoEntidad;
	}

	public void setCodigoEntidad(Long codigoEntidad) {
		this.codigoEntidad = codigoEntidad;
	}

	public Long getCodigoSegmento() {
		return codigoSegmento;
	}

	public void setCodigoSegmento(Long codigoSegmento) {
		this.codigoSegmento = codigoSegmento;
	}

	public Long getCodigoMotivo() {
		return codigoMotivo;
	}

	public void setCodigoMotivo(Long codigoMotivo) {
		this.codigoMotivo = codigoMotivo;
	}

	@Override
	public String toString() {
		return "EntidadRequest [codigoEntidad=" + codigoEntidad + ", codigoSegmento=" + codigoSegmento + "]";
	}
	
}
