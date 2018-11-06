package com.helio4.bancol.avaluos.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Modelo tabla Matricula
 * 
 * @author <a href="mailto:j.j.o.c007@gmail.com">Juan Jose Orjuela Castillo</a>
 * 
 */
@Entity
@Table(name = "matricula")
public class Matricula {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "codigo")
	private String codigo;
	@Column(name = "tipo_inmueble")
	private String tipoInmueble;
	@Column(name = "numero")
	private Integer numero;
	@ManyToOne
	@JoinColumn(name = "avaluo_id", nullable = false)
	private Avaluo avaluo;

	public Matricula() {
	}

	public Matricula(Long id, String codigo, String tipoInmueble, Integer numero, Avaluo avaluo) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.tipoInmueble = tipoInmueble;
		this.numero = numero;
		this.avaluo = avaluo;
	}

	public void update(String codigo, String tipoInmueble, Integer numero, Avaluo avaluo) {
		this.codigo = codigo;
		this.tipoInmueble = tipoInmueble;
		this.numero = numero;
		this.avaluo = avaluo;
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

	public String getTipoInmueble() {
		return tipoInmueble;
	}

	public void setTipoInmueble(String tipoInmueble) {
		this.tipoInmueble = tipoInmueble;
	}

	public Avaluo getAvaluo() {
		return avaluo;
	}

	public void setAvaluo(Avaluo avaluo) {
		this.avaluo = avaluo;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	
}
