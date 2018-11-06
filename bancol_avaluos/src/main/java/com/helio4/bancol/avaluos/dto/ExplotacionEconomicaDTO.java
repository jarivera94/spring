package com.helio4.bancol.avaluos.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.helio4.bancol.avaluos.dto.AreaDTO.UnidadDeMedida;
import com.helio4.bancol.avaluos.dto.AvaluoDTO.MBR;

public class ExplotacionEconomicaDTO {

	private Long id;
	private String etapa;
	private String descripcion;
	private Integer unidadDeMedida;
	private BigDecimal area;
	private BigDecimal porcentajeDeParticipacion;
	private Date fechaDeSiembra;
	private BigDecimal antiguedad;
	private Integer estadoFitosanitario;
	private Long avaluoId;
	
	public ExplotacionEconomicaDTO() {
	}

	public ExplotacionEconomicaDTO(Long id, String etapa, String descripcion,
			Integer unidadDeMedida, BigDecimal area,
			BigDecimal porcentajeDeParticipacion, Date fechaDeSiembra,
			BigDecimal antiguedad, Integer estadoFitosanitario, Long avaluoId) {
		super();
		this.id = id;
		this.etapa = etapa;
		this.descripcion = descripcion;
		this.unidadDeMedida = unidadDeMedida;
		this.area = area;
		this.porcentajeDeParticipacion = porcentajeDeParticipacion;
		this.fechaDeSiembra = fechaDeSiembra;
		this.antiguedad = antiguedad;
		this.estadoFitosanitario = estadoFitosanitario;
		this.avaluoId = avaluoId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEtapa() {
		return etapa;
	}

	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public UnidadDeMedida getUnidadDeMedida() {
		return UnidadDeMedida.fromKey(unidadDeMedida == null? 0 : unidadDeMedida);
	}

	public void setUnidadDeMedida(UnidadDeMedida unidadDeMedida) {
		this.unidadDeMedida = unidadDeMedida.getKey();
	}

	public BigDecimal getArea() {
		return area;
	}

	public void setArea(BigDecimal area) {
		this.area = area;
	}

	public BigDecimal getPorcentajeDeParticipacion() {
		return porcentajeDeParticipacion;
	}

	public void setPorcentajeDeParticipacion(BigDecimal porcentajeDeParticipacion) {
		this.porcentajeDeParticipacion = porcentajeDeParticipacion;
	}

	public Date getFechaDeSiembra() {
		return fechaDeSiembra;
	}

	public void setFechaDeSiembra(Date fechaDeSiembra) {
		this.fechaDeSiembra = fechaDeSiembra;
	}

	public BigDecimal getAntiguedad() {
		return antiguedad;
	}

	public void setAntiguedad(BigDecimal antiguedad) {
		this.antiguedad = antiguedad;
	}

	public MBR getEstadoFitosanitario() {
		return MBR.fromKey(estadoFitosanitario  == null ? 0 : estadoFitosanitario);
	}

	public void setEstadoFitosanitario(MBR estadoFitosanitario) {
		this.estadoFitosanitario = estadoFitosanitario.getKey();
	}

	public Long getAvaluoId() {
		return avaluoId;
	}

	public void setAvaluoId(Long avaluoId) {
		this.avaluoId = avaluoId;
	}

}
