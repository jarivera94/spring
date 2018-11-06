package com.helio4.bancol.avaluos.modelo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "comparacion_mercado_ph_renta")
public class ComparacionMercadoPhRenta extends ComparacionMercadoPh implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7312165146797484654L;
	
	@Column(name="tasaAplicada")
	private BigDecimal tasaAplicada;
	@Column(name="rentaBrutaMensual")
	private BigDecimal rentaBrutaMensual;
	@Column(name="deducciones")
	private BigDecimal deducciones;
	@Column(name="renta_neta_mensual")
	private BigDecimal rentaNetaMensual;
	@Column(name="renta_neta_anual")
	private BigDecimal rentaNetaAnual;
	@Column(name="valor_capitalizacion")
	private BigDecimal valorCapitalizacion;
	@Column(name="valor_m2_homogenizado_sin_garaje")
	private BigDecimal valorM2HomogenizadoSinGaraje;
		
	public BigDecimal getTasaAplicada() {
		return tasaAplicada;
	}
	public void setTasaAplicada(BigDecimal tasaAplicada) {
		this.tasaAplicada = tasaAplicada;
	}
	public BigDecimal getRentaBrutaMensual() {
		return rentaBrutaMensual;
	}
	public void setRentaBrutaMensual(BigDecimal rentaBrutaMensual) {
		this.rentaBrutaMensual = rentaBrutaMensual;
	}
	public BigDecimal getDeducciones() {
		return deducciones;
	}
	public void setDeducciones(BigDecimal deducciones) {
		this.deducciones = deducciones;
	}
	public BigDecimal getRentaNetaMensual() {
		return rentaNetaMensual;
	}
	public void setRentaNetaMensual(BigDecimal rentaNetaMensual) {
		this.rentaNetaMensual = rentaNetaMensual;
	}
	public BigDecimal getRentaNetaAnual() {
		return rentaNetaAnual;
	}
	public void setRentaNetaAnual(BigDecimal rentaNetaAnual) {
		this.rentaNetaAnual = rentaNetaAnual;
	}
	public BigDecimal getValorCapitalizacion() {
		return valorCapitalizacion;
	}
	public void setValorCapitalizacion(BigDecimal valorCapitalizacion) {
		this.valorCapitalizacion = valorCapitalizacion;
	}
	public BigDecimal getValorM2HomogenizadoSinGaraje() {
		return valorM2HomogenizadoSinGaraje;
	}
	public void setValorM2HomogenizadoSinGaraje(BigDecimal valorM2HomogenizadoSinGaraje) {
		this.valorM2HomogenizadoSinGaraje = valorM2HomogenizadoSinGaraje;
	}

}
