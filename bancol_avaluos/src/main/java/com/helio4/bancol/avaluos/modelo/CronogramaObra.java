package com.helio4.bancol.avaluos.modelo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class CronogramaObra {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cronograma_obra_id")
	private Long id;
	@Column(name="no_de_capitulo")
	private Integer noDeCapitulo;
	@Column(name="nombre_capitulo")
	private String nombreCapitulo;
	@Column(name="valor_presupuesto")
	private BigDecimal valorPresupuesto;
	@Column(name="costo_total")
	private BigDecimal costoTotal;
	@Column(name="porcentaje_de_avance")
	private BigDecimal porcentajeDeAvance;
	@Column(name="invesion_ejecutada")
	private BigDecimal invesionEjecutada;
	@Column(name="inversion_po_ejecutar")
	private BigDecimal inversionPoEjecutar;
	@Column(name="fecha_inicio")
	private Date fechaInicio;
	@Column(name="fecha_fin")
	private Date fechaFin;
	@Column(name="porcentaje_de_avance_1")
	private BigDecimal porcentajeDeAvance1;
	@Column(name="porcentaje_de_avance_2")
	private BigDecimal porcentajeDeAvance2;
	@Column(name="porcentaje_de_avance_3")
	private BigDecimal porcentajeDeAvance3;
	@Column(name="porcentaje_de_avance_4")
	private BigDecimal porcentajeDeAvance4;
	@Column(name="valor_presupuesto_aumento")
	private BigDecimal valorPresupuestoAumento;
	@Column(name="fecha_fin_prorroga")
	private Date fechaFinProrroga;
	@Column(name="tipo_costo")
	private Integer tipoCosto;
	@OneToOne
	@JoinColumn(name="avaluo_id", nullable=false)
	private AvaluoConstructor avaluo;	
	
	public CronogramaObra() {
		super();
	}

	public CronogramaObra(Long id, Integer noDeCapitulo, String nombreCapitulo,
			BigDecimal valorPresupuesto, BigDecimal costoTotal,
			BigDecimal porcentajeDeAvance, BigDecimal invesionEjecutada,
			BigDecimal inversionPoEjecutar, Date fechaInicio, Date fechaFin,
			BigDecimal porcentajeDeAvance1, BigDecimal porcentajeDeAvance2,
			BigDecimal porcentajeDeAvance3, BigDecimal porcentajeDeAvance4,
			BigDecimal valorPresupuestoAumento, Date fechaFinProrroga, Integer tipoCosto,
			AvaluoConstructor avaluo) {
		super();
		this.id = id;
		this.noDeCapitulo = noDeCapitulo;
		this.nombreCapitulo = nombreCapitulo;
		this.valorPresupuesto = valorPresupuesto;
		this.costoTotal = costoTotal;
		this.porcentajeDeAvance = porcentajeDeAvance;
		this.invesionEjecutada = invesionEjecutada;
		this.inversionPoEjecutar = inversionPoEjecutar;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.porcentajeDeAvance1 = porcentajeDeAvance1;
		this.porcentajeDeAvance2 = porcentajeDeAvance2;
		this.porcentajeDeAvance3 = porcentajeDeAvance3;
		this.porcentajeDeAvance4 = porcentajeDeAvance4;
		this.valorPresupuestoAumento = valorPresupuestoAumento;
		this.fechaFinProrroga = fechaFinProrroga;
		this.tipoCosto = tipoCosto;
		this.avaluo = avaluo;
	}
	
	public void update(Long id, Integer noDeCapitulo, String nombreCapitulo,
			BigDecimal valorPresupuesto, BigDecimal costoTotal,
			BigDecimal porcentajeDeAvance, BigDecimal invesionEjecutada,
			BigDecimal inversionPoEjecutar, Date fechaInicio, Date fechaFin,
			BigDecimal porcentajeDeAvance1, BigDecimal porcentajeDeAvance2,
			BigDecimal porcentajeDeAvance3, BigDecimal porcentajeDeAvance4,
			BigDecimal valorPresupuestoAumento, Date fechaFinProrroga, Integer tipoCosto,
			AvaluoConstructor avaluo) {
		this.id = id;
		this.noDeCapitulo = noDeCapitulo;
		this.nombreCapitulo = nombreCapitulo;
		this.valorPresupuesto = valorPresupuesto;
		this.costoTotal = costoTotal;
		this.porcentajeDeAvance = porcentajeDeAvance;
		this.invesionEjecutada = invesionEjecutada;
		this.inversionPoEjecutar = inversionPoEjecutar;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.porcentajeDeAvance1 = porcentajeDeAvance1;
		this.porcentajeDeAvance2 = porcentajeDeAvance2;
		this.porcentajeDeAvance3 = porcentajeDeAvance3;
		this.porcentajeDeAvance4 = porcentajeDeAvance4;
		this.valorPresupuestoAumento = valorPresupuestoAumento;
		this.fechaFinProrroga = fechaFinProrroga;
		this.tipoCosto = tipoCosto;
		this.avaluo = avaluo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNoDeCapitulo() {
		return noDeCapitulo;
	}

	public void setNoDeCapitulo(Integer noDeCapitulo) {
		this.noDeCapitulo = noDeCapitulo;
	}

	public String getNombreCapitulo() {
		return nombreCapitulo;
	}

	public void setNombreCapitulo(String nombreCapitulo) {
		this.nombreCapitulo = nombreCapitulo;
	}

	public BigDecimal getValorPresupuesto() {
		return valorPresupuesto;
	}

	public void setValorPresupuesto(BigDecimal valorPresupuesto) {
		this.valorPresupuesto = valorPresupuesto;
	}

	public BigDecimal getCostoTotal() {
		return costoTotal;
	}

	public void setCostoTotal(BigDecimal costoTotal) {
		this.costoTotal = costoTotal;
	}

	public BigDecimal getPorcentajeDeAvance() {
		return porcentajeDeAvance;
	}

	public void setPorcentajeDeAvance(BigDecimal porcentajeDeAvance) {
		this.porcentajeDeAvance = porcentajeDeAvance;
	}

	public BigDecimal getInvesionEjecutada() {
		return invesionEjecutada;
	}

	public void setInvesionEjecutada(BigDecimal invesionEjecutada) {
		this.invesionEjecutada = invesionEjecutada;
	}

	public BigDecimal getInversionPoEjecutar() {
		return inversionPoEjecutar;
	}

	public void setInversionPoEjecutar(BigDecimal inversionPoEjecutar) {
		this.inversionPoEjecutar = inversionPoEjecutar;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public BigDecimal getPorcentajeDeAvance1() {
		return porcentajeDeAvance1;
	}

	public void setPorcentajeDeAvance1(BigDecimal porcentajeDeAvance1) {
		this.porcentajeDeAvance1 = porcentajeDeAvance1;
	}

	public BigDecimal getPorcentajeDeAvance2() {
		return porcentajeDeAvance2;
	}

	public void setPorcentajeDeAvance2(BigDecimal porcentajeDeAvance2) {
		this.porcentajeDeAvance2 = porcentajeDeAvance2;
	}

	public BigDecimal getPorcentajeDeAvance3() {
		return porcentajeDeAvance3;
	}

	public void setPorcentajeDeAvance3(BigDecimal porcentajeDeAvance3) {
		this.porcentajeDeAvance3 = porcentajeDeAvance3;
	}

	public BigDecimal getPorcentajeDeAvance4() {
		return porcentajeDeAvance4;
	}

	public void setPorcentajeDeAvance4(BigDecimal porcentajeDeAvance4) {
		this.porcentajeDeAvance4 = porcentajeDeAvance4;
	}

	public BigDecimal getValorPresupuestoAumento() {
		return valorPresupuestoAumento;
	}

	public void setValorPresupuestoAumento(BigDecimal valorPresupuestoAumento) {
		this.valorPresupuestoAumento = valorPresupuestoAumento;
	}

	public Date getFechaFinProrroga() {
		return fechaFinProrroga;
	}

	public void setFechaFinProrroga(Date fechaFinProrroga) {
		this.fechaFinProrroga = fechaFinProrroga;
	}
	
	public Integer getTipoCosto() {
		return tipoCosto;
	}

	public void setTipoCosto(Integer tipoCosto) {
		this.tipoCosto = tipoCosto;
	}

	public AvaluoConstructor getAvaluo() {
		return avaluo;
	}

	public void setAvaluo(AvaluoConstructor avaluo) {
		this.avaluo = avaluo;
	}

}
