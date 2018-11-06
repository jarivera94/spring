package com.helio4.bancol.avaluos.dto;

import java.io.Serializable;
import java.math.BigDecimal;


public class PisoFactorDTO implements Serializable,Cloneable {
	
	private static final long serialVersionUID = -8585776466842794261L;
	private Long id;
	private Integer desde;	
	private Integer hasta;
	private BigDecimal porcentaje;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getDesde() {
		return desde;
	}

	public void setDesde(Integer desde) {
		this.desde = desde;
	}

	public Integer getHasta() {
		return hasta;
	}

	public void setHasta(Integer hasta) {
		this.hasta = hasta;
	}

	public BigDecimal getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(BigDecimal porcentaje) {
		this.porcentaje = porcentaje;
	}

	public String toString(){
		return id.toString();
	}
	
}
