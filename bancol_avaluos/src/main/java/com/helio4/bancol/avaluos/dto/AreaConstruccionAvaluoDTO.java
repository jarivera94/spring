package com.helio4.bancol.avaluos.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class AreaConstruccionAvaluoDTO implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private BigDecimal areaPrivada;
	private BigDecimal areaConstruida;
	private Boolean areaLibre;
	private String usoAreaLibre;
	private BigDecimal areaCatastral;
	private BigDecimal areaMedidaInspeccion;
	private BigDecimal areaValorada;
	private BigDecimal arealicenciaConstruccion;
	private BigDecimal areaSuseptibleLegalizacion;
	private Long avaluoId;

	private AvaluoDTO avaluo;

	public AreaConstruccionAvaluoDTO(Integer id, BigDecimal areaPrivada, BigDecimal areaConstruida, Boolean areaLibre,
			String usoAreaLibre, BigDecimal areaCatastral, BigDecimal areaMedidaInspeccion, BigDecimal areaValorada,
			BigDecimal arealicenciaConstruccion, BigDecimal areaSuseptibleLegalizacion, Long avaluoId,
			AvaluoDTO avaluo) {
		super();
		this.id = id;
		this.areaPrivada = areaPrivada;
		this.areaConstruida = areaConstruida;
		this.areaLibre = areaLibre;
		this.usoAreaLibre = usoAreaLibre;
		this.areaCatastral = areaCatastral;
		this.areaMedidaInspeccion = areaMedidaInspeccion;
		this.areaValorada = areaValorada;
		this.arealicenciaConstruccion = arealicenciaConstruccion;
		this.areaSuseptibleLegalizacion = areaSuseptibleLegalizacion;
		this.avaluoId = avaluoId;
		this.avaluo = avaluo;
	}

	public AreaConstruccionAvaluoDTO() {
		super();
	}

	public AreaConstruccionAvaluoDTO(Long avaluoId) {
		super();
		this.avaluoId = avaluoId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getAreaPrivada() {
		return areaPrivada;
	}

	public void setAreaPrivada(BigDecimal areaPrivada) {
		this.areaPrivada = areaPrivada;
	}

	public BigDecimal getAreaConstruida() {
		return areaConstruida;
	}

	public void setAreaConstruida(BigDecimal areaConstruida) {
		this.areaConstruida = areaConstruida;
	}

	public Boolean getAreaLibre() {
		return areaLibre;
	}

	public void setAreaLibre(Boolean areaLibre) {
		this.areaLibre = areaLibre;
	}

	public String getUsoAreaLibre() {
		return usoAreaLibre;
	}

	public void setUsoAreaLibre(String usoAreaLibre) {
		this.usoAreaLibre = usoAreaLibre;
	}

	public BigDecimal getAreaCatastral() {
		return areaCatastral;
	}

	public void setAreaCatastral(BigDecimal areaCatastral) {
		this.areaCatastral = areaCatastral;
	}

	public BigDecimal getAreaMedidaInspeccion() {
		return areaMedidaInspeccion;
	}

	public void setAreaMedidaInspeccion(BigDecimal areaMedidaInspeccion) {
		this.areaMedidaInspeccion = areaMedidaInspeccion;
	}

	public BigDecimal getAreaValorada() {
		return areaValorada;
	}

	public void setAreaValorada(BigDecimal areaValorada) {
		this.areaValorada = areaValorada;
	}

	public BigDecimal getArealicenciaConstruccion() {
		return arealicenciaConstruccion;
	}

	public void setArealicenciaConstruccion(BigDecimal arealicenciaConstruccion) {
		this.arealicenciaConstruccion = arealicenciaConstruccion;
	}

	public BigDecimal getAreaSuseptibleLegalizacion() {
		return areaSuseptibleLegalizacion;
	}

	public void setAreaSuseptibleLegalizacion(BigDecimal areaSuseptibleLegalizacion) {
		this.areaSuseptibleLegalizacion = areaSuseptibleLegalizacion;
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
		return "AreaConstruccionAvaluoDTO [id=" + id + ", areaPrivada=" + areaPrivada + ", areaConstruida="
				+ areaConstruida + ", areaLibre=" + areaLibre + ", usoAreaLibre=" + usoAreaLibre + ", areaCatastral="
				+ areaCatastral + ", areaMedidaInspeccion=" + areaMedidaInspeccion + ", areaValorada=" + areaValorada
				+ ", arealicenciaConstruccion=" + arealicenciaConstruccion + ", areaSuseptibleLegalizacion="
				+ areaSuseptibleLegalizacion + ", avaluoId=" + avaluoId + ", avaluo=" + avaluo + "]";
	}

	@Override
	public Object clone() {
		// TODO Auto-generated method stub
		AreaConstruccionAvaluoDTO clone = new AreaConstruccionAvaluoDTO(id, areaPrivada, areaConstruida, areaLibre,
				usoAreaLibre, areaCatastral, areaMedidaInspeccion, areaValorada, arealicenciaConstruccion,
				areaSuseptibleLegalizacion, avaluoId, avaluo);
		return clone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((areaCatastral == null) ? 0 : areaCatastral.hashCode());
		result = prime * result + ((areaConstruida == null) ? 0 : areaConstruida.hashCode());
		result = prime * result + ((areaLibre == null) ? 0 : areaLibre.hashCode());
		result = prime * result + ((areaMedidaInspeccion == null) ? 0 : areaMedidaInspeccion.hashCode());
		result = prime * result + ((areaPrivada == null) ? 0 : areaPrivada.hashCode());
		result = prime * result + ((areaSuseptibleLegalizacion == null) ? 0 : areaSuseptibleLegalizacion.hashCode());
		result = prime * result + ((areaValorada == null) ? 0 : areaValorada.hashCode());
		result = prime * result + ((arealicenciaConstruccion == null) ? 0 : arealicenciaConstruccion.hashCode());
		result = prime * result + ((avaluo == null) ? 0 : avaluo.hashCode());
		result = prime * result + ((avaluoId == null) ? 0 : avaluoId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((usoAreaLibre == null) ? 0 : usoAreaLibre.hashCode());
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
		AreaConstruccionAvaluoDTO other = (AreaConstruccionAvaluoDTO) obj;
		if (areaCatastral == null) {
			if (other.areaCatastral != null)
				return false;
		} else if (!areaCatastral.equals(other.areaCatastral))
			return false;
		if (areaConstruida == null) {
			if (other.areaConstruida != null)
				return false;
		} else if (!areaConstruida.equals(other.areaConstruida))
			return false;
		if (areaLibre == null) {
			if (other.areaLibre != null)
				return false;
		} else if (!areaLibre.equals(other.areaLibre))
			return false;
		if (areaMedidaInspeccion == null) {
			if (other.areaMedidaInspeccion != null)
				return false;
		} else if (!areaMedidaInspeccion.equals(other.areaMedidaInspeccion))
			return false;
		if (areaPrivada == null) {
			if (other.areaPrivada != null)
				return false;
		} else if (!areaPrivada.equals(other.areaPrivada))
			return false;
		if (areaSuseptibleLegalizacion == null) {
			if (other.areaSuseptibleLegalizacion != null)
				return false;
		} else if (!areaSuseptibleLegalizacion.equals(other.areaSuseptibleLegalizacion))
			return false;
		if (areaValorada == null) {
			if (other.areaValorada != null)
				return false;
		} else if (!areaValorada.equals(other.areaValorada))
			return false;
		if (arealicenciaConstruccion == null) {
			if (other.arealicenciaConstruccion != null)
				return false;
		} else if (!arealicenciaConstruccion.equals(other.arealicenciaConstruccion))
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (usoAreaLibre == null) {
			if (other.usoAreaLibre != null)
				return false;
		} else if (!usoAreaLibre.equals(other.usoAreaLibre))
			return false;
		return true;
	}

}
