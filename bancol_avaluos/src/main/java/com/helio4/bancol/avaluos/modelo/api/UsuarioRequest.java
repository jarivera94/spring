package com.helio4.bancol.avaluos.modelo.api;

public class UsuarioRequest {

	Long numeroDocumento;

	public UsuarioRequest() {
		// TODO Auto-generated constructor stub
	}

	public Long getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(Long numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	@Override
	public String toString() {
		return "UsuarioRequest [numeroDocumento=" + numeroDocumento + "]";
	}

	
	
	
}
