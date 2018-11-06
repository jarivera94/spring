package com.helio4.bancol.avaluos.modelo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "oferta_lote_con_construccion")
@PrimaryKeyJoinColumn(name = "oferta_id")
public class OfertaLoteConConstruccion extends OfertaLote implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8827894157384558872L;

	@Column(name = "area_construida_m2")
	private BigDecimal areaConstruidaM2;

	@Column(name = "factor_homologacion_conservacion")
	private Integer factorHomologacionConservacion;

	@Column(name = "factor_homologacion_construccion")
	private BigDecimal factorHomologacionConstruccion;

	@Column(name = "factor_homologacion_lote")
	private BigDecimal factorHomologacionLote;

	@Column(name = "area_construccion")
	private BigDecimal areaConstruccion;

	public OfertaLoteConConstruccion() {
		super();
	}

	public OfertaLoteConConstruccion(BigDecimal areaConstruidaM2, Integer factorHomologacionConservacion,
			BigDecimal factorHomologacionConstruccion, BigDecimal factorHomologacionLote, BigDecimal areaConstruccion) {
		super();
		this.areaConstruidaM2 = areaConstruidaM2;
		this.factorHomologacionConservacion = factorHomologacionConservacion;
		this.factorHomologacionConstruccion = factorHomologacionConstruccion;
		this.factorHomologacionLote = factorHomologacionLote;
		this.areaConstruccion = areaConstruccion;
	}
	
	public Integer getFactorHomologacionConservacion() {
		return factorHomologacionConservacion;
	}

	public void setFactorHomologacionConservacion(Integer factorHomologacionConservacion) {
		this.factorHomologacionConservacion = factorHomologacionConservacion;
	}

	public BigDecimal getFactorHomologacionConstruccion() {
		return factorHomologacionConstruccion;
	}

	public void setFactorHomologacionConstruccion(BigDecimal factorHomologacionConstruccion) {
		this.factorHomologacionConstruccion = factorHomologacionConstruccion;
	}

	public BigDecimal getFactorHomologacionLote() {
		return factorHomologacionLote;
	}

	public void setFactorHomologacionLote(BigDecimal factorHomologacionLote) {
		this.factorHomologacionLote = factorHomologacionLote;
	}

	public BigDecimal getAreaConstruccion() {
		return areaConstruccion;
	}

	public void setAreaConstruccion(BigDecimal areaConstruccion) {
		this.areaConstruccion = areaConstruccion;
	}

	public BigDecimal getAreaConstruidaM2() {
		return areaConstruidaM2;
	}

	public void setAreaConstruidaM2(BigDecimal areaConstruidaM2) {
		this.areaConstruidaM2 = areaConstruidaM2;
	}
	
}
