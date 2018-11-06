package com.helio4.bancol.avaluos.modelo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "oferta_lote_con_construccion_venta")
@PrimaryKeyJoinColumn(name = "oferta_id")
public class OfertaLoteConConstruccionVenta extends OfertaLoteConConstruccion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8095892805506289483L;

	@Column(name = "valor_construccion_m2")
	private BigDecimal valorConstruccionM2;

	@Column(name = "valor_unitario_m2_homogenizado")
	private BigDecimal valorUnitarioM2Homogenizado;

	public OfertaLoteConConstruccionVenta() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BigDecimal getValorConstruccionM2() {
		return valorConstruccionM2;
	}

	public void setValorConstruccionM2(BigDecimal valorConstruccionM2) {
		this.valorConstruccionM2 = valorConstruccionM2;
	}

	public BigDecimal getValorUnitarioM2Homogenizado() {
		return valorUnitarioM2Homogenizado;
	}

	public void setValorUnitarioM2Homogenizado(BigDecimal valorUnitarioM2Homogenizado) {
		this.valorUnitarioM2Homogenizado = valorUnitarioM2Homogenizado;
	}

}
