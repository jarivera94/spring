package com.helio4.bancol.avaluos.modelo;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "area_lote_avaluo")
public class AreaLoteAvaluo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	@Column(name="area")
	private BigDecimal area;
	@Column(name="frente")
	private BigDecimal frente;
	@Column(name="fondo")
	private BigDecimal fondo;
	@Column(name="relacion")
	private BigDecimal relacion;

	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "avaluo_id")
	private Avaluo avaluo;

	public AreaLoteAvaluo() {
		// TODO Auto-generated constructor stub
	}

	public AreaLoteAvaluo(Long id, BigDecimal area, BigDecimal frente, BigDecimal fondo, BigDecimal relacion,
			Avaluo avaluo) {
		super();
		this.id = id;
		this.area = area;
		this.frente = frente;
		this.fondo = fondo;
		this.relacion = relacion;
		this.avaluo = avaluo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getArea() {
		return area;
	}

	public void setArea(BigDecimal area) {
		this.area = area;
	}

	public BigDecimal getFrente() {
		return frente;
	}

	public void setFrente(BigDecimal frente) {
		this.frente = frente;
	}

	public BigDecimal getFondo() {
		return fondo;
	}

	public void setFondo(BigDecimal fondo) {
		this.fondo = fondo;
	}

	public Avaluo getAvaluo() {
		return avaluo;
	}

	public void setAvaluo(Avaluo avaluo) {
		this.avaluo = avaluo;
	}

	public BigDecimal getRelacion() {
		return relacion;
	}

	public void setRelacion(BigDecimal relacion) {
		this.relacion = relacion;
	}
	
}
