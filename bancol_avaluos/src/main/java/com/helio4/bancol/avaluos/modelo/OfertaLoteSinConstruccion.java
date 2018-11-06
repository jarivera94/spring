package com.helio4.bancol.avaluos.modelo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "oferta_lote_sin_construccion")
@PrimaryKeyJoinColumn(name = "oferta_id")
public class OfertaLoteSinConstruccion extends OfertaLote implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3902884825689247438L;

	@Column(name = "ubicacion_lote")
	private Integer ubicacionLote;

	@Column(name = "forma")
	private Integer forma;

	@Column(name = "factor_homologacion_forma")
	private Integer factorHomologacionForma;

	@Column(name = "factor_homologacion_tipografia")
	private Integer factorHomologacionTipografia;

	@Column(name = "factor_homologacion_superficie")
	private BigDecimal factorHomologacionSuperficie;
	
	@Column(name = "factor_homologacion_ubicacion")
	private Integer factorHomologacionUbicacion;

	public OfertaLoteSinConstruccion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OfertaLoteSinConstruccion(Integer ubicacionLote, Integer forma, Integer factorHomologacionForma,
			Integer factorHomologacionTipografia, BigDecimal factorHomologacionSuperficie,
			Integer factorHomologacionUbicacion) {
		super();
		this.ubicacionLote = ubicacionLote;
		this.forma = forma;
		this.factorHomologacionForma = factorHomologacionForma;
		this.factorHomologacionTipografia = factorHomologacionTipografia;
		this.factorHomologacionSuperficie = factorHomologacionSuperficie;
		this.factorHomologacionUbicacion = factorHomologacionUbicacion;
	}

	public Integer getUbicacionLote() {
		return ubicacionLote;
	}

	public void setUbicacionLote(Integer ubicacionLote) {
		this.ubicacionLote = ubicacionLote;
	}

	public Integer getForma() {
		return forma;
	}

	public void setForma(Integer forma) {
		this.forma = forma;
	}

	public Integer getFactorHomologacionForma() {
		return factorHomologacionForma;
	}

	public void setFactorHomologacionForma(Integer factorHomologacionForma) {
		this.factorHomologacionForma = factorHomologacionForma;
	}

	public Integer getFactorHomologacionTipografia() {
		return factorHomologacionTipografia;
	}

	public void setFactorHomologacionTipografia(Integer factorHomologacionTipografia) {
		this.factorHomologacionTipografia = factorHomologacionTipografia;
	}

	public BigDecimal getFactorHomologacionSuperficie() {
		return factorHomologacionSuperficie;
	}

	public void setFactorHomologacionSuperficie(BigDecimal factorHomologacionSuperficie) {
		this.factorHomologacionSuperficie = factorHomologacionSuperficie;
	}

	public Integer getFactorHomologacionUbicacion() {
		return factorHomologacionUbicacion;
	}

	public void setFactorHomologacionUbicacion(Integer factorHomologacionUbicacion) {
		this.factorHomologacionUbicacion = factorHomologacionUbicacion;
	}
	
}
