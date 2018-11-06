package com.helio4.bancol.avaluos.dto;


import java.math.BigDecimal;
import java.util.Date;

public class UvrDTO {
	
	private Long id;
	private Date fechaUvr;
	private BigDecimal valor;
	
	public UvrDTO () {}
	
	public UvrDTO(Long id, Date fechaUvr, BigDecimal valor) {
		super();
		this.id = id;
		this.fechaUvr = fechaUvr;
		this.valor = valor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaUvr() {
		return fechaUvr;
	}

	public void setFechaUvr(Date fechaUvr) {
		this.fechaUvr = fechaUvr;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	public String toString() {
		String value = "id:" + this.id + " fecha: "+this.fechaUvr+" uvr:"+this.valor;
		return value;
	}
}
