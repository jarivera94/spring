package com.helio4.bancol.avaluos.modelo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "comparacion_mercado_ph_venta")
public class ComparacionMercadoPhVenta extends ComparacionMercadoPh implements Serializable {

	private static final long serialVersionUID = 2665211288523862331L;

	@Column(name = "promedio_m2_homogenizado")
	private BigDecimal promedioM2Homogenizado;
	@Column(name = "promedio_m2")
	private BigDecimal promedioM2;
	@Column(name = "media_aritmetica")
	private BigDecimal mediaAritmetica;
	@Column(name = "desviacion_estandar")
	private BigDecimal desviacionEstandar;
	@Column(name = "coeficiente_variacion")
	private BigDecimal coeficienteVariacion;

	public ComparacionMercadoPhVenta() {

	}

	public BigDecimal getPromedioM2Homogenizado() {
		return promedioM2Homogenizado;
	}

	public void setPromedioM2Homogenizado(BigDecimal promedioM2Homogenizado) {
		this.promedioM2Homogenizado = promedioM2Homogenizado;
	}

	public BigDecimal getPromedioM2() {
		return promedioM2;
	}

	public void setPromedioM2(BigDecimal promedioM2) {
		this.promedioM2 = promedioM2;
	}

	public BigDecimal getMediaAritmetica() {
		return mediaAritmetica;
	}

	public void setMediaAritmetica(BigDecimal mediaAritmetica) {
		this.mediaAritmetica = mediaAritmetica;
	}

	public BigDecimal getDesviacionEstandar() {
		return desviacionEstandar;
	}

	public void setDesviacionEstandar(BigDecimal desviacionEstandar) {
		this.desviacionEstandar = desviacionEstandar;
	}

	public BigDecimal getCoeficienteVariacion() {
		return coeficienteVariacion;
	}

	public void setCoeficienteVariacion(BigDecimal coeficienteVariacion) {
		this.coeficienteVariacion = coeficienteVariacion;
	}

}
