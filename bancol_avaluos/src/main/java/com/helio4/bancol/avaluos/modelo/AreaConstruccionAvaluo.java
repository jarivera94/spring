package com.helio4.bancol.avaluos.modelo;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "area_construccion_avaluo")
public class AreaConstruccionAvaluo {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name="area_privada")
	private BigDecimal areaPrivada;
	@Column(name="area_construida")
	private BigDecimal areaConstruida;
	@Column(name="area_libre")
	private Boolean areaLibre;
	@Column(name="uso_area_libre")
	private String usoAreaLibre;
	@Column(name="area_catastral")
	private BigDecimal areaCatastral;
	@Column(name="area_medida_inspeccion")
	private BigDecimal areaMedidaInspeccion;
	@Column(name="area_valorada")
	private BigDecimal areaValorada;
	@Column(name="area_licencia_construccion")
	private BigDecimal arealicenciaConstruccion;
	@Column(name="area_susceptible_legalizar")
	private BigDecimal areaSuseptibleLegalizacion;
	
	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "avaluo_id")
	private Avaluo avaluo;

	public AreaConstruccionAvaluo() {
		// TODO Auto-generated constructor stub
	}
	
	public AreaConstruccionAvaluo(Integer id, BigDecimal areaPrivada, BigDecimal areaConstruida, Boolean areaLibre,
			String usoAreaLibre, BigDecimal areaCatastral, BigDecimal areaMedidaInspeccion, BigDecimal areaValorada,
			BigDecimal arealicenciaConstruccion, BigDecimal areaSuseptibleLegalizacion, Avaluo avaluo) {
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
		this.avaluo = avaluo;
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

	public Avaluo getAvaluo() {
		return avaluo;
	}

	public void setAvaluo(Avaluo avaluo) {
		this.avaluo = avaluo;
	}	
	
}