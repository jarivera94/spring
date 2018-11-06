package com.helio4.bancol.avaluos.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sucursal")
public class Sucursal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sucursal_id")
	private Long id;
	@Column(nullable = false)
	private String codigo;
	@Column(nullable = false)
	private String nombre;
	@Column(name = "fecha_creacion")
	private Date fechaCreacion;	
	@Column(name = "activo")
	private Boolean activo;
	@ManyToOne
	@JoinColumn(name = "entidad_id", nullable = false)
	private Entidad entidad;

	public Sucursal() {
		super();
	}

	public Sucursal(Long id, String codigo, String nombre, Date fechaCreacion,
			Boolean activo, Entidad entidad) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.nombre = nombre;
		this.entidad = entidad;
		this.fechaCreacion = fechaCreacion;
		this.activo = activo;
	}

	public void update(Sucursal sucursal) {
		this.codigo = sucursal.codigo;
		this.nombre = sucursal.nombre;
		this.entidad = sucursal.entidad;
		this.fechaCreacion = sucursal.fechaCreacion;
		this.activo = sucursal.activo;
	}

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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Entidad getEntidad() {
		return entidad;
	}

	public void setEntidad(Entidad entidad) {
		this.entidad = entidad;
	}

}
