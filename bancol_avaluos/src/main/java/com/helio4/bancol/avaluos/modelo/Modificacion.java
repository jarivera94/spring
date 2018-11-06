package com.helio4.bancol.avaluos.modelo;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "modificacion")
public class Modificacion {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name = "avaluo_id")
	private Long avaluo;
	
	@Column(name = "fecha")
	private Date fecha;
	
	@Column(name = "campo")
	private String campo;
	
	@Column(name = "anterior")
	private String anterior;
	
	@Column(name = "nuevo")
	private String nuevo;
	
	@ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.EAGER)
	@JoinColumns ({
		@JoinColumn(name = "tipo_documento_usuario", referencedColumnName = "tipo_documento_identificacion"),
		@JoinColumn(name = "numero_documento_usuario", referencedColumnName = "numero_documento")
	})
	private Usuario usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAvaluo() {
		return avaluo;
	}

	public void setAvaluo(Long avaluo) {
		this.avaluo = avaluo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public String getAnterior() {
		return anterior;
	}

	public void setAnterior(String anterior) {
		this.anterior = anterior;
	}

	public String getNuevo() {
		return nuevo;
	}

	public void setNuevo(String nuevo) {
		this.nuevo = nuevo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
