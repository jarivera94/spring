package com.helio4.bancol.avaluos.modelo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "oferta_lote_con_construccion_renta")
@PrimaryKeyJoinColumn(name = "oferta_id")
public class OfertaLoteConConstruccionRenta extends OfertaLoteConConstruccion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3613539271399603426L;

	@Column(name = "valor_unitario_m2")
	private BigDecimal valorUnitarioM2;

	@Column(name = "valor_unitario_resultante_m2")
	private BigDecimal valorUnitarioResultanteM2;

	public OfertaLoteConConstruccionRenta() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public OfertaLoteConConstruccionRenta(BigDecimal valorUnitarioM2, BigDecimal valorUnitarioResultanteM2) {
		super();
		this.valorUnitarioM2 = valorUnitarioM2;
		this.valorUnitarioResultanteM2 = valorUnitarioResultanteM2;
	}

	public BigDecimal getValorUnitarioM2() {
		return valorUnitarioM2;
	}

	public void setValorUnitarioM2(BigDecimal valorUnitarioM2) {
		this.valorUnitarioM2 = valorUnitarioM2;
	}

	public BigDecimal getValorUnitarioResultanteM2() {
		return valorUnitarioResultanteM2;
	}

	public void setValorUnitarioResultanteM2(BigDecimal valorUnitarioResultanteM2) {
		this.valorUnitarioResultanteM2 = valorUnitarioResultanteM2;
	}

}
