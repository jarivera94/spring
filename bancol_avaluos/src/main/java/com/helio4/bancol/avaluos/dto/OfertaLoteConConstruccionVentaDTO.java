package com.helio4.bancol.avaluos.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import com.helio4.bancol.avaluos.modelo.OfertaLoteConConstruccionVenta;

public class OfertaLoteConConstruccionVentaDTO extends OfertaLoteConConstruccionDTO implements Serializable, Cloneable {

	protected static final long serialVersionUID = 1L;

	protected BigDecimal valorConstruccionM2;
	protected BigDecimal valorUnitarioM2Homogenizado;

	public OfertaLoteConConstruccionVentaDTO() {
	}
	
	public OfertaLoteConConstruccionVentaDTO(OfertaLoteConConstruccionVenta oferta) {
				
		super(oferta);
		
		this.setValorConstruccionM2(oferta.getValorConstruccionM2());
		this.setValorUnitarioM2Homogenizado(oferta.getValorUnitarioM2Homogenizado());
		
	}
	
	public OfertaLoteConConstruccionVentaDTO(Long id) {
		super();
		this.id = id;
	}

	public OfertaLoteConConstruccionVentaDTO(BigDecimal valorConstruccionM2,
			BigDecimal valorUnitarioM2Homogenizado) {
		super();
		this.valorConstruccionM2 = valorConstruccionM2;
		this.valorUnitarioM2Homogenizado = valorUnitarioM2Homogenizado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "OfertaLoteConConstruccionVentaDTO [id=" + id + ", valorConstruccionM2="
				+ valorConstruccionM2 + ", valorUnitarioM2Homogenizado=" + valorUnitarioM2Homogenizado + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((valorConstruccionM2 == null) ? 0 : valorConstruccionM2.hashCode());
		result = prime * result + ((valorUnitarioM2Homogenizado == null) ? 0 : valorUnitarioM2Homogenizado.hashCode());
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
		OfertaLoteConConstruccionVentaDTO other = (OfertaLoteConConstruccionVentaDTO) obj;
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
		OfertaLoteConConstruccionVentaDTO clone = new OfertaLoteConConstruccionVentaDTO(this.valorConstruccionM2, this.valorUnitarioM2Homogenizado);
		return clone;
	}
	
	public void calcularValorM2Ajustado() {

		if (this.valorOferta != null && negociacion != null && areaConstruidaM2 != null && valorConstruccionM2 != null
				&& areaLoteM2 != null && areaLoteM2.compareTo(BigDecimal.ZERO)!= 0) {

			this.valorM2Ajustado = this.valorOferta.multiply(negociacion)
					.subtract(areaConstruidaM2.multiply(valorConstruccionM2)).divide(areaLoteM2, RoundingMode.HALF_UP);
		} else {
			this.valorM2Ajustado = BigDecimal.ZERO;
		}

	}
	
	public void calcularPrecioUnitarioM2() {

		if (this.valorOferta != null && areaConstruidaM2 != null && areaConstruidaM2.compareTo(BigDecimal.ZERO)!= 0) {
			this.precioUnitarioM2 = this.valorOferta.divide(areaConstruidaM2, RoundingMode.HALF_UP);
		} else {
			this.precioUnitarioM2 = BigDecimal.ZERO;
		}

	}
	
	public void calcularPrecioUnitarioM2Homogenizado() {
		if (valorOferta != null && factorHomogenizacion != null && areaConstruidaM2 != null) {
			this.valorUnitarioM2Homogenizado = this.valorOferta.multiply(this.factorHomogenizacion)
					.divide(areaConstruidaM2, RoundingMode.HALF_UP);
		} else {
			this.valorUnitarioM2Homogenizado = BigDecimal.ZERO;
		}

	}

}
