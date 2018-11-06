package com.helio4.bancol.avaluos.modelo.api;

public class ClienteRequest {

	private String tipoDocumento;
	private Long numeroDocumento;
	private String nombre;
	private String razonSocial;

	public ClienteRequest() {
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Long getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(Long numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "ClienteRequest [tipoDocumento=" + tipoDocumento + ", numeroDocumento=" + numeroDocumento + ", nombre="
				+ nombre + ", razonSocial=" + razonSocial + "]";
	}
	
}
