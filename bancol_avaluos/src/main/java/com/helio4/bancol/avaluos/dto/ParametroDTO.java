package com.helio4.bancol.avaluos.dto;

import com.helio4.bancol.avaluos.modelo.Parametro;

public class ParametroDTO {

	private Long id;
	private String nombre;
	private String valor;
	
	public ParametroDTO() {
	}

	public ParametroDTO(Parametro parametro) {
		super();
		this.id = parametro.getId();
		this.nombre = parametro.getNombre();
		this.valor = parametro.getValor();
	}

	public Long getId() {
		return this.id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getValor() {
		return this.valor;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}
