package com.helio4.bancol.avaluos.dto;

import java.io.Serializable;
import java.math.BigDecimal;


public class CalificacionHomologacionDTO implements Serializable,Cloneable {

	private static final long serialVersionUID = -8534711621181988590L;
	private Long id;
	private String codigo;
	private String calificacion;
	private BigDecimal factor;
		

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(String calificacion) {
		this.calificacion = calificacion;
	}

	public BigDecimal getFactor() {
		return factor;
	}

	public void setFactor(BigDecimal factor) {
		this.factor = factor;
	}

	public String toString(){
		return id.toString();
	}
	
}
