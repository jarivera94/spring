package com.helio4.bancol.avaluos.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class AreaLoteAvaluoDTO implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private BigDecimal area;
	private BigDecimal frente;
	private BigDecimal fondo;
	private BigDecimal relacion;
	private Long avaluoId;

	private AvaluoDTO avaluo;

	public AreaLoteAvaluoDTO(Long id, BigDecimal area, BigDecimal frente, BigDecimal fondo, BigDecimal relacion,
			Long avaluoId, AvaluoDTO avaluo) {
		super();
		this.id = id;
		this.area = area;
		this.frente = frente;
		this.fondo = fondo;
		this.relacion = relacion;
		this.avaluoId = avaluoId;
		this.avaluo = avaluo;
	}
	
	public AreaLoteAvaluoDTO(Long avaluoId) {
		super();
		this.avaluoId = avaluoId;
	}

	public AreaLoteAvaluoDTO() {
		super();
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

	public BigDecimal getRelacion() {
		return relacion;
	}

	public void setRelacion(BigDecimal relacion) {
		this.relacion = relacion;
	}

	public Long getAvaluoId() {
		return avaluoId;
	}

	public void setAvaluoId(Long avaluoId) {
		this.avaluoId = avaluoId;
	}

	public AvaluoDTO getAvaluo() {
		return avaluo;
	}

	public void setAvaluo(AvaluoDTO avaluo) {
		this.avaluo = avaluo;
	}

	@Override
	public String toString() {
		return "AreaLoteAvaluoDTO [id=" + id + ", area=" + area + ", frente=" + frente + ", fondo=" + fondo
				+ ", relacion=" + relacion + ", avaluoId=" + avaluoId + ", avaluo=" + avaluo + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((area == null) ? 0 : area.hashCode());
		result = prime * result + ((avaluo == null) ? 0 : avaluo.hashCode());
		result = prime * result + ((avaluoId == null) ? 0 : avaluoId.hashCode());
		result = prime * result + ((fondo == null) ? 0 : fondo.hashCode());
		result = prime * result + ((frente == null) ? 0 : frente.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((relacion == null) ? 0 : relacion.hashCode());
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
		AreaLoteAvaluoDTO other = (AreaLoteAvaluoDTO) obj;
		if (area == null) {
			if (other.area != null)
				return false;
		} else if (!area.equals(other.area))
			return false;
		if (avaluo == null) {
			if (other.avaluo != null)
				return false;
		} else if (!avaluo.equals(other.avaluo))
			return false;
		if (avaluoId == null) {
			if (other.avaluoId != null)
				return false;
		} else if (!avaluoId.equals(other.avaluoId))
			return false;
		if (fondo == null) {
			if (other.fondo != null)
				return false;
		} else if (!fondo.equals(other.fondo))
			return false;
		if (frente == null) {
			if (other.frente != null)
				return false;
		} else if (!frente.equals(other.frente))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (relacion == null) {
			if (other.relacion != null)
				return false;
		} else if (!relacion.equals(other.relacion))
			return false;
		return true;
	}

	@Override
	public Object clone() {
		AreaLoteAvaluoDTO clone = new AreaLoteAvaluoDTO(this.id, this.area, this.frente, this.fondo, this.relacion,
				this.avaluoId, this.avaluo);
		return clone;
	}

}
