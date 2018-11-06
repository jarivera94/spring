package com.helio4.bancol.avaluos.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import com.helio4.bancol.avaluos.dto.OfertaDTO.FactorHomologacion;
import com.helio4.bancol.avaluos.modelo.OfertaLoteConConstruccion;

public class OfertaLoteConConstruccionDTO extends OfertaLoteDTO implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	protected Long idOfertaLoteConConstruccionId;
	protected BigDecimal areaConstruidaM2;
	protected Integer factorHomologacionConservacion;
	protected BigDecimal factorHomologacionConstruccion;
	protected BigDecimal factorHomologacionLote;
	protected BigDecimal areaConstruccion;

	public OfertaLoteConConstruccionDTO() {
		
		this.ubicacion = FactorHomologacion.S.getKey();
		this.factorHomologacionConservacion = FactorHomologacion.S.getKey();
		this.acabados = FactorHomologacion.S.getKey();
		
	}

	public OfertaLoteConConstruccionDTO(OfertaLoteConConstruccion oferta) {

		super(oferta);

		this.setIdOfertaLoteConConstruccionId(oferta.getId());
		this.setAreaConstruidaM2(oferta.getAreaConstruidaM2());
		this.setFactorHomologacionConservacion(oferta.getFactorHomologacionConservacion());
		this.setFactorHomologacionConstruccion(oferta.getFactorHomologacionConstruccion());
		this.setFactorHomologacionLote(oferta.getFactorHomologacionLote());
		this.setAreaConstruccion(oferta.getAreaConstruccion());

	}

	public OfertaLoteConConstruccionDTO(Long idOfertaLoteConConstruccionId, BigDecimal areaConstruidaM2,
			Integer factorHomologacionConservacion, BigDecimal factorHomologacionConstruccion,
			BigDecimal factorHomologacionLote, BigDecimal areaConstruccion) {
		super();
		this.idOfertaLoteConConstruccionId = idOfertaLoteConConstruccionId;
		this.areaConstruidaM2 = areaConstruidaM2;
		this.factorHomologacionConservacion = factorHomologacionConservacion;
		this.factorHomologacionConstruccion = factorHomologacionConstruccion;
		this.factorHomologacionLote = factorHomologacionLote;
		this.areaConstruccion = areaConstruccion;
	}

	public void setFactorHomologacionConservacion(Integer factorHomologacionConservacion) {
		this.factorHomologacionConservacion = factorHomologacionConservacion;
	}

	public FactorHomologacion getFactorHomologacionConservacion() {
		return FactorHomologacion.fromKey(factorHomologacionConservacion == null ? 0 : factorHomologacionConservacion);
	}

	public void setFactorHomologacionConservacion(FactorHomologacion factorHomologacionConservacion) {
		this.factorHomologacionConservacion = factorHomologacionConservacion == null ? 0
				: factorHomologacionConservacion.getKey();
	}

	public Long getIdOfertaLoteConConstruccionId() {
		return idOfertaLoteConConstruccionId;
	}

	public void setIdOfertaLoteConConstruccionId(Long idOfertaLoteConConstruccionId) {
		this.idOfertaLoteConConstruccionId = idOfertaLoteConConstruccionId;
	}

	public BigDecimal getAreaConstruidaM2() {
		return areaConstruidaM2;
	}

	public void setAreaConstruidaM2(BigDecimal areaConstruidaM2) {
		this.areaConstruidaM2 = areaConstruidaM2;
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

	@Override
	public String toString() {
		return "OfertaLoteConConstruccionDTO [id=" + idOfertaLoteConConstruccionId + ", areaConstruidaM2="
				+ areaConstruidaM2 + ", factorHomologacionConservacion=" + factorHomologacionConservacion
				+ ", factorHomologacionConstruccion=" + factorHomologacionConstruccion + ", factorHomologacionLote="
				+ factorHomologacionLote + ", areaConstruccion=" + areaConstruccion + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((areaConstruccion == null) ? 0 : areaConstruccion.hashCode());
		result = prime * result + ((areaConstruidaM2 == null) ? 0 : areaConstruidaM2.hashCode());
		result = prime * result
				+ ((factorHomologacionConservacion == null) ? 0 : factorHomologacionConservacion.hashCode());
		result = prime * result
				+ ((factorHomologacionConstruccion == null) ? 0 : factorHomologacionConstruccion.hashCode());
		result = prime * result + ((factorHomologacionLote == null) ? 0 : factorHomologacionLote.hashCode());
		result = prime * result
				+ ((idOfertaLoteConConstruccionId == null) ? 0 : idOfertaLoteConConstruccionId.hashCode());
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
		OfertaLoteConConstruccionDTO other = (OfertaLoteConConstruccionDTO) obj;
		if (areaConstruccion == null) {
			if (other.areaConstruccion != null)
				return false;
		} else if (!areaConstruccion.equals(other.areaConstruccion))
			return false;
		if (areaConstruidaM2 == null) {
			if (other.areaConstruidaM2 != null)
				return false;
		} else if (!areaConstruidaM2.equals(other.areaConstruidaM2))
			return false;
		if (factorHomologacionConservacion == null) {
			if (other.factorHomologacionConservacion != null)
				return false;
		} else if (!factorHomologacionConservacion.equals(other.factorHomologacionConservacion))
			return false;
		if (factorHomologacionConstruccion == null) {
			if (other.factorHomologacionConstruccion != null)
				return false;
		} else if (!factorHomologacionConstruccion.equals(other.factorHomologacionConstruccion))
			return false;
		if (factorHomologacionLote == null) {
			if (other.factorHomologacionLote != null)
				return false;
		} else if (!factorHomologacionLote.equals(other.factorHomologacionLote))
			return false;
		if (idOfertaLoteConConstruccionId == null) {
			if (other.idOfertaLoteConConstruccionId != null)
				return false;
		} else if (!idOfertaLoteConConstruccionId.equals(other.idOfertaLoteConConstruccionId))
			return false;
		return true;
	}

	@Override
	public Object clone() {
		OfertaLoteConConstruccionDTO clone = new OfertaLoteConConstruccionDTO(idOfertaLoteConConstruccionId,
				areaConstruidaM2, factorHomologacionConservacion, factorHomologacionConstruccion,
				factorHomologacionLote, areaConstruccion);
		return clone;
	}

	public void calcularFactorHomologacionConstruccion(BigDecimal areaConstruidaM2Sujeto, BigDecimal areaLoteM2Sujeto,
			BigDecimal factorHomogenizacionConstruccion) {

		if (areaConstruidaM2Sujeto != null && areaLoteM2Sujeto != null && factorHomogenizacionConstruccion != null
				&& this.areaConstruidaM2 != null && this.areaLoteM2 != null && this.areaLoteM2.compareTo(BigDecimal.ZERO) != 0 
				&& areaConstruidaM2Sujeto.compareTo(BigDecimal.ZERO) != 0 && areaLoteM2Sujeto.compareTo(BigDecimal.ZERO) != 0) {

			this.factorHomologacionConstruccion = BigDecimal.valueOf(Math.pow((this.areaConstruidaM2.divide(this.areaLoteM2, 8, RoundingMode.HALF_UP))
					.divide(areaConstruidaM2Sujeto.divide(areaLoteM2Sujeto, 8, RoundingMode.HALF_UP), 8, RoundingMode.HALF_UP).doubleValue(), factorHomogenizacionConstruccion.doubleValue()));
		} else {
			this.factorHomologacionConstruccion = BigDecimal.ZERO;
		}

	}

	public void calcularFactorHomologacionLote(BigDecimal factorHomogenizacionLoteMetodo, BigDecimal areaLoteSujeto) {
		
		if(factorHomogenizacionLoteMetodo != null && factorHomogenizacionLoteMetodo.compareTo(BigDecimal.ONE) == 0){
			this.factorHomologacionLote = BigDecimal.ONE;
		}else if (this.areaLoteM2 != null && this.areaLoteM2.compareTo(BigDecimal.ZERO) != 0 && areaLoteSujeto != null && areaLoteSujeto.compareTo(BigDecimal.ZERO) != 0) {
			this.factorHomologacionLote = BigDecimal.valueOf(Math.pow(areaLoteM2.divide(areaLoteSujeto, 8, RoundingMode.HALF_UP).doubleValue(), factorHomogenizacionLoteMetodo.doubleValue()));
		} else {
			this.factorHomologacionLote = BigDecimal.ZERO;
		}
	}
	
	public void calcularAreaconstruccionAreaTerreno() {
		if (this.areaConstruidaM2 != null  && this.areaLoteM2 != null && this.areaLoteM2.compareTo(BigDecimal.ZERO) != 0) {
			this.areaConstruccion = this.areaConstruidaM2.divide(areaLoteM2, 8, RoundingMode.HALF_UP);
		} else {
			this.areaConstruccion = BigDecimal.ZERO;
		}
	}

}
