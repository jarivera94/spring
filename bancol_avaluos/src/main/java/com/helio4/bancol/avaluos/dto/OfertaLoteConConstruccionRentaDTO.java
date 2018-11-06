package com.helio4.bancol.avaluos.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import com.helio4.bancol.avaluos.modelo.OfertaLoteConConstruccionRenta;

public class OfertaLoteConConstruccionRentaDTO extends OfertaLoteConConstruccionDTO implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	protected Long ofertaLoteConConstruccionRentaId;
	protected BigDecimal valorUnitarioM2;
	protected BigDecimal valorUnitarioResultanteM2;

	public OfertaLoteConConstruccionRentaDTO() {
		super();
	}

	public OfertaLoteConConstruccionRentaDTO(OfertaLoteConConstruccionRenta oferta) {
		
		super(oferta);
		
		this.setOfertaLoteConConstruccionRentaId(oferta.getId());
		this.setValorUnitarioM2(oferta.getValorUnitarioM2());
		this.setValorUnitarioResultanteM2(oferta.getValorUnitarioResultanteM2());
		
	}
	
	public OfertaLoteConConstruccionRentaDTO(Long id) {
		super();
		this.id = id;
	}
	
	public OfertaLoteConConstruccionRentaDTO(Long ofertaLoteConConstruccionRentaId, BigDecimal valorUnitarioM2,
			BigDecimal valorUnitarioResultanteM2) {
		super();
		this.ofertaLoteConConstruccionRentaId = ofertaLoteConConstruccionRentaId;
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

	public Long getOfertaLoteConConstruccionRentaId() {
		return ofertaLoteConConstruccionRentaId;
	}

	public void setOfertaLoteConConstruccionRentaId(Long ofertaLoteConConstruccionRentaId) {
		this.ofertaLoteConConstruccionRentaId = ofertaLoteConConstruccionRentaId;
	}

	@Override
	public String toString() {
		return "OfertaLoteConConstruccionRentaDTO [id=" + ofertaLoteConConstruccionRentaId + ", valorUnitarioM2="
				+ valorUnitarioM2 + ", valorUnitarioResultanteM2=" + valorUnitarioResultanteM2 + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((ofertaLoteConConstruccionRentaId == null) ? 0 : ofertaLoteConConstruccionRentaId.hashCode());
		result = prime * result + ((valorUnitarioM2 == null) ? 0 : valorUnitarioM2.hashCode());
		result = prime * result + ((valorUnitarioResultanteM2 == null) ? 0 : valorUnitarioResultanteM2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OfertaLoteConConstruccionRentaDTO other = (OfertaLoteConConstruccionRentaDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public Object clone() {
		// TODO Auto-generated method stub
		OfertaLoteConConstruccionRentaDTO clone = new OfertaLoteConConstruccionRentaDTO(
				this.ofertaLoteConConstruccionRentaId, this.valorUnitarioM2, this.valorUnitarioResultanteM2);
		return clone;
	}
	
	public void calcularPrecioUnitarioM2() {
		if (this.valorOferta != null && this.areaConstruidaM2 != null && !BigDecimal.ZERO.equals(this.areaConstruidaM2)) {
			this.precioUnitarioM2 = this.valorOferta.divide(this.areaConstruidaM2, RoundingMode.HALF_UP);
		} else {
			this.precioUnitarioM2 = BigDecimal.ZERO;
		}
	}
	

	public void calcularValorUnitarioResultanteM2() {
		if (this.precioUnitarioM2 != null && this.factorHomogenizacion != null) {
			this.valorUnitarioResultanteM2 = this.precioUnitarioM2.multiply(this.factorHomogenizacion);
		} else {
			this.valorUnitarioResultanteM2 = BigDecimal.ZERO;
		}
	}

}
