package com.helio4.bancol.avaluos.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import org.primefaces.model.map.MapModel;

import com.google.common.collect.ImmutableSet;

public class InmuebleDTO {

	private Long id;
	private String matriculaInmobiliaria;
	private String cedulaCatastral;
	private String numeroDeEscritura;
	private String chip;
	private Integer numeroDeNotariaEscritura;
	private String departamentoEscritura;
	private String municipioEscritura;
	private Date fechaEscritura;
	private BigDecimal latitud;
	private BigDecimal longitud;
	private Long avaluoId;
	private Set<PropietarioDTO> propietarios;
	private ImmutableSet<PropietarioDTO> propietariosOriginales;
	
	private MapModel mapModel;

	public InmuebleDTO(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMatriculaInmobiliaria() {
		return matriculaInmobiliaria;
	}

	public void setMatriculaInmobiliaria(String matriculaInmobiliaria) {
		this.matriculaInmobiliaria = matriculaInmobiliaria;
	}

	public String getCedulaCatastral() {
		return cedulaCatastral;
	}

	public void setCedulaCatastral(String cedulaCatastral) {
		this.cedulaCatastral = cedulaCatastral;
	}

	public String getNumeroDeEscritura() {
		return numeroDeEscritura;
	}

	public void setNumeroDeEscritura(String numeroDeEscritura) {
		this.numeroDeEscritura = numeroDeEscritura;
	}

	public String getChip() {
		return chip;
	}

	public void setChip(String chip) {
		this.chip = chip;
	}

	public Integer getNumeroDeNotariaEscritura() {
		return numeroDeNotariaEscritura;
	}

	public void setNumeroDeNotariaEscritura(Integer numeroDeNotariaEscritura) {
		this.numeroDeNotariaEscritura = numeroDeNotariaEscritura;
	}

	public String getDepartamentoEscritura() {
		return departamentoEscritura;
	}

	public void setDepartamentoEscritura(String departamentoEscritura) {
		this.departamentoEscritura = departamentoEscritura;
	}

	public String getMunicipioEscritura() {
		return municipioEscritura;
	}

	public void setMunicipioEscritura(String municipioEscritura) {
		this.municipioEscritura = municipioEscritura;
	}

	public Date getFechaEscritura() {
		return fechaEscritura;
	}

	public void setFechaEscritura(Date fechaEscritura) {
		this.fechaEscritura = fechaEscritura;
	}

	public BigDecimal getLatitud() {
		return latitud;
	}

	public void setLatitud(BigDecimal latitud) {
		this.latitud = latitud;
	}

	public BigDecimal getLongitud() {
		return longitud;
	}

	public void setLongitud(BigDecimal longitud) {
		this.longitud = longitud;
	}

	public Long getAvaluoId() {
		return avaluoId;
	}

	public void setAvaluoId(Long avaluoId) {
		this.avaluoId = avaluoId;
	}

	public Set<PropietarioDTO> getPropietarios() {
		return propietarios;
	}

	public void setPropietarios(Set<PropietarioDTO> propietarios) {
		this.propietarios = propietarios;
	}

	public ImmutableSet<PropietarioDTO> getPropietariosOriginales() {
		return propietariosOriginales;
	}

	public void setPropietariosOriginales(ImmutableSet<PropietarioDTO> propietariosOriginales) {
		this.propietariosOriginales = propietariosOriginales;
	}

	public MapModel getMapModel() {
		return mapModel;
	}

	public void setMapModel(MapModel mapModel) {
		this.mapModel = mapModel;
	}

}
