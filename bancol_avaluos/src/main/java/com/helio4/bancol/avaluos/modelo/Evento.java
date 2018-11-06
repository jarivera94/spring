package com.helio4.bancol.avaluos.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "evento")
public class Evento {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="evento_id")
	private Long id;
	@Column(name = "fecha_hora_inicio")
	private Date fechaHoraInicio;
	@Column(name = "fecha_hora_fin")
	private Date fechaHoraFin;
	@Column
	private String descripcion;
	@ManyToOne
	@JoinColumns ({
		@JoinColumn(name = "tipo_documento_usuario", referencedColumnName = "tipo_documento_identificacion"),
		@JoinColumn(name = "numero_documento_usuario", referencedColumnName = "numero_documento")
	})
	private Usuario usuario;
	@ManyToOne
	@JoinColumn(name="avaluo_id", nullable=false)
	private Avaluo avaluo;

	public Evento() {
	}

	public void update(Date fechaHoraInicio, Date fechaHoraFin,
			String descripcion, Usuario usuario,
			Avaluo avaluo) {
		this.fechaHoraInicio = fechaHoraInicio;
		this.fechaHoraFin = fechaHoraFin;
		this.descripcion = descripcion;
		this.usuario = usuario;
		this.avaluo = avaluo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaHoraInicio() {
		return fechaHoraInicio;
	}

	public void setFechaHoraInicio(Date fechaHoraInicio) {
		this.fechaHoraInicio = fechaHoraInicio;
	}

	public Date getFechaHoraFin() {
		return fechaHoraFin;
	}

	public void setFechaHoraFin(Date fechaHoraFin) {
		this.fechaHoraFin = fechaHoraFin;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Avaluo getAvaluo() {
		return avaluo;
	}

	public void setAvaluo(Avaluo avaluo) {
		this.avaluo = avaluo;
	}
}
