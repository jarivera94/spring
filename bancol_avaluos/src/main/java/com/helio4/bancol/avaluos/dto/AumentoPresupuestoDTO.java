package com.helio4.bancol.avaluos.dto;

import java.util.Date;

public class AumentoPresupuestoDTO {
	
	private Long id;
	private Date fechaCreacion;
	private Date fechaAprovacion;
	private Date valorAumento;
	private String justificacion;
	private Long avaluoId;
	
	public AumentoPresupuestoDTO() {
		super();
	}

	public AumentoPresupuestoDTO(Long id, Date fechaCreacion,
			Date fechaAprovacion, Date valorAumento, String justificacion,
			Long avaluoId) {
		super();
		this.id = id;
		this.fechaCreacion = fechaCreacion;
		this.fechaAprovacion = fechaAprovacion;
		this.valorAumento = valorAumento;
		this.justificacion = justificacion;
		this.avaluoId = avaluoId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaAprovacion() {
		return fechaAprovacion;
	}

	public void setFechaAprovacion(Date fechaAprovacion) {
		this.fechaAprovacion = fechaAprovacion;
	}

	public Date getValorAumento() {
		return valorAumento;
	}

	public void setValorAumento(Date valorAumento) {
		this.valorAumento = valorAumento;
	}

	public String getJustificacion() {
		return justificacion;
	}

	public void setJustificacion(String justificacion) {
		this.justificacion = justificacion;
	}

	public Long getAvaluoId() {
		return avaluoId;
	}

	public void setAvaluoId(Long avaluoId) {
		this.avaluoId = avaluoId;
	}

}
