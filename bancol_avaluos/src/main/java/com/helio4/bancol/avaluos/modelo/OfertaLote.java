package com.helio4.bancol.avaluos.modelo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "oferta_lote")
@PrimaryKeyJoinColumn(name = "oferta_id")
public class OfertaLote extends Oferta implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7932533134049433197L;
	
	@Column(name="comparacion")
	private Integer comparacion;

	@Column(name="area_lote_m2")
	private BigDecimal areaLoteM2;
	
	@Column(name="precio_unitario_m2")	
	private BigDecimal precioUnitarioM2;
	
	@Column(name="valor_m2_ajustado")
	private BigDecimal valorM2Ajustado;

	public OfertaLote() {
		super();
	}
	
	public OfertaLote(Integer comparacion, BigDecimal areaLoteM2, BigDecimal precioUnitarioM2,
			BigDecimal valorM2Ajustado) {
		super();
		this.comparacion = comparacion;
		this.areaLoteM2 = areaLoteM2;
		this.precioUnitarioM2 = precioUnitarioM2;
		this.valorM2Ajustado = valorM2Ajustado;
	}
	
	public Integer getComparacion() {
		return comparacion;
	}

	public void setComparacion(Integer comparacion) {
		this.comparacion = comparacion;
	}

	public BigDecimal getAreaLoteM2() {
		return areaLoteM2;
	}

	public void setAreaLoteM2(BigDecimal areaLoteM2) {
		this.areaLoteM2 = areaLoteM2;
	}

	public BigDecimal getPrecioUnitarioM2() {
		return precioUnitarioM2;
	}

	public void setPrecioUnitarioM2(BigDecimal precioUnitarioM2) {
		this.precioUnitarioM2 = precioUnitarioM2;
	}

	public BigDecimal getValorM2Ajustado() {
		return valorM2Ajustado;
	}

	public void setValorM2Ajustado(BigDecimal valorM2Ajustado) {
		this.valorM2Ajustado = valorM2Ajustado;
	}

}
