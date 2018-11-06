package com.helio4.bancol.avaluos.modelo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ExplotacionEconomica {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "explotacion_economica_id")
	private Long id;
	@Column(name = "etapa")
	private String etapa;
	@Column(name = "descripcion")
	private String descripcion;
	@Column(name = "unidad_de_medida")
	private Integer unidadDeMedida;
	@Column(name = "area")
	private BigDecimal area;
	@Column(name = "porcentaje_de_participacion")
	private BigDecimal porcentajeDeParticipacion;
	@Column(name = "fecha_de_siembra")
	private Date fechaDeSiembra;
	@Column(name = "antiguedad")
	private BigDecimal antiguedad;
	@Column(name = "estado_fitosanitario")
	private Integer estadoFitosanitario;
	@ManyToOne
	@JoinColumn(name = "avaluo_id", nullable=false)
	private AvaluoComercial avaluo;
	
	public ExplotacionEconomica() {
	}

	public ExplotacionEconomica(String etapa, String descripcion,
			Integer unidadDeMedida, BigDecimal area,
			BigDecimal porcentajeDeParticipacion, Date fechaDeSiembra,
			BigDecimal antiguedad, Integer estadoFitosanitario,
			AvaluoComercial avaluo) {
		super();
		this.etapa = etapa;
		this.descripcion = descripcion;
		this.unidadDeMedida = unidadDeMedida;
		this.area = area;
		this.porcentajeDeParticipacion = porcentajeDeParticipacion;
		this.fechaDeSiembra = fechaDeSiembra;
		this.antiguedad = antiguedad;
		this.estadoFitosanitario = estadoFitosanitario;
		this.avaluo = avaluo;
	}

	public void update(String etapa, String descripcion,
			Integer unidadDeMedida, BigDecimal area,
			BigDecimal porcentajeDeParticipacion, Date fechaDeSiembra,
			BigDecimal antiguedad, Integer estadoFitosanitario,
			AvaluoComercial avaluo) {
		this.etapa = etapa;
		this.descripcion = descripcion;
		this.unidadDeMedida = unidadDeMedida;
		this.area = area;
		this.porcentajeDeParticipacion = porcentajeDeParticipacion;
		this.fechaDeSiembra = fechaDeSiembra;
		this.antiguedad = antiguedad;
		this.estadoFitosanitario = estadoFitosanitario;
		this.avaluo = avaluo;
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

	public Integer getUnidadDeMedida() {
		return unidadDeMedida;
	}

	public void setUnidadDeMedida(Integer unidadDeMedida) {
		this.unidadDeMedida = unidadDeMedida;
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

	public Integer getEstadoFitosanitario() {
		return estadoFitosanitario;
	}

	public void setEstadoFitosanitario(Integer estadoFitosanitario) {
		this.estadoFitosanitario = estadoFitosanitario;
	}

	public AvaluoComercial getAvaluo() {
		return avaluo;
	}

	public void setAvaluo(AvaluoComercial avaluo) {
		this.avaluo = avaluo;
	}

}
