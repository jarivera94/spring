package com.helio4.bancol.avaluos.modelo.api;

public class PeritoRequest {

	private String tipoDocumento;
	private Long numeroDocumento;

	public PeritoRequest() {
		// TODO Auto-generated constructor stub
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

	@Override
	public String toString() {
		return "PeritoRequest [tipoDocumento=" + tipoDocumento + ", numeroDocumento=" + numeroDocumento + "]";
	}

}
