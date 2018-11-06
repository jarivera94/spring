package com.helio4.bancol.avaluos.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Prorroga {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="prorroga_id")
	private Long id;
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	@Column(name="fecha_aprovacion")
	private Date fechaAprovacion;
	@Column(name="fecha_prorroga")
	private Date fechaProrroga;
	@Column(name="justificacion")
	private String justificacion;
	@ManyToOne
	@JoinColumn(name="avaluo_id", nullable=false)
	private AvaluoConstructor avaluo;
	
	public Prorroga() {
		super();
	}

	public Prorroga(Long id, Date fechaCreacion, Date fechaAprovacion,
			Date fechaProrroga, String justificacion, AvaluoConstructor avaluo) {
		super();
		this.id = id;
		this.fechaCreacion = fechaCreacion;
		this.fechaAprovacion = fechaAprovacion;
		this.fechaProrroga = fechaProrroga;
		this.justificacion = justificacion;
		this.avaluo = avaluo;
	}

	public void update(Long id, Date fechaCreacion, Date fechaAprovacion,
			Date fechaProrroga, String justificacion, AvaluoConstructor avaluo) {
		this.id = id;
		this.fechaCreacion = fechaCreacion;
		this.fechaAprovacion = fechaAprovacion;
		this.fechaProrroga = fechaProrroga;
		this.justificacion = justificacion;
		this.avaluo = avaluo;
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

	public Date getFechaProrroga() {
		return fechaProrroga;
	}

	public void setFechaProrroga(Date fechaProrroga) {
		this.fechaProrroga = fechaProrroga;
	}

	public String getJustificacion() {
		return justificacion;
	}

	public void setJustificacion(String justificacion) {
		this.justificacion = justificacion;
	}

	public AvaluoConstructor getAvaluo() {
		return avaluo;
	}

	public void setAvaluo(AvaluoConstructor avaluo) {
		this.avaluo = avaluo;
	}
	
}
