package com.helio4.bancol.avaluos.dto;

import java.math.BigDecimal;

public class PropietarioDTO {

	private Integer tipoDocumentoIdentificacion;
	private Long numeroDocumento;
	private String nombresApellidosPropietario;
	private BigDecimal porcentajeDePropiedad;
	
	public PropietarioDTO() {
	}

	public Integer getTipoDocumentoIdentificacion() {
		return tipoDocumentoIdentificacion;
	}

	public void setTipoDocumentoIdentificacion(
			Integer tipoDocumentoIdentificacion) {
		this.tipoDocumentoIdentificacion = tipoDocumentoIdentificacion;
	}

	public Long getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(Long numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getNombresApellidosPropietario() {
		return nombresApellidosPropietario;
	}

	public void setNombresApellidosPropietario(String nombresApellidosPropietario) {
		this.nombresApellidosPropietario = nombresApellidosPropietario;
	}

	public BigDecimal getPorcentajeDePropiedad() {
		return porcentajeDePropiedad;
	}

	public void setPorcentajeDePropiedad(BigDecimal porcentajeDePropiedad) {
		this.porcentajeDePropiedad = porcentajeDePropiedad;
	}

}
