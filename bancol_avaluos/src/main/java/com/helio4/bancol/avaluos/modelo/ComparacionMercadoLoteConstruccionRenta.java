package com.helio4.bancol.avaluos.modelo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.helio4.bancol.avaluos.dto.ComparacionMercadoLoteConstruccionRentaDTO;

@Entity
@Table(name = "comparacion_mercado_lote_construccion_renta")
@PrimaryKeyJoinColumn(name = "metodo_valuacion_id")
public class ComparacionMercadoLoteConstruccionRenta extends ComparacionMercadoLoteConstruccion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8763155141055052251L;
	
	@Column(name="promedio_valor_unitario_resultante_m2_mes")
	private BigDecimal promedioValorUnitarioResultanteM2Mes;
	@Column(name="promedio_renta_mensual")
	private BigDecimal promedioRentaMensual;
	@Column(name="promedio_area_construccion_area_terreno")
	private BigDecimal promedioAreaConstruccionAreaTerreno;
	@Column(name="tasa_aplicada")
	private BigDecimal tasaAplicada;
	@Column(name="renta_bruta_mensual")
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

	public BigDecimal getPromedioValorUnitarioResultanteM2Mes() {
		return promedioValorUnitarioResultanteM2Mes;
	}
	public void setPromedioValorUnitarioResultanteM2Mes(BigDecimal promedioValorUnitarioResultanteM2Mes) {
		this.promedioValorUnitarioResultanteM2Mes = promedioValorUnitarioResultanteM2Mes;
	}
	public BigDecimal getPromedioRentaMensual() {
		return promedioRentaMensual;
	}
	public void setPromedioRentaMensual(BigDecimal promedioRentaMensual) {
		this.promedioRentaMensual = promedioRentaMensual;
	}
	public BigDecimal getPromedioAreaConstruccionAreaTerreno() {
		return promedioAreaConstruccionAreaTerreno;
	}
	public void setPromedioAreaConstruccionAreaTerreno(BigDecimal promedioAreaConstruccionAreaTerreno) {
		this.promedioAreaConstruccionAreaTerreno = promedioAreaConstruccionAreaTerreno;
	}
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
	
	public void cargarLoteConstruccionRentaDTO(ComparacionMercadoLoteConstruccionRentaDTO comparacion){
		
		super.cargarLoteConstruccionDTO(comparacion);
		
		this.promedioValorUnitarioResultanteM2Mes = comparacion.getPromedioValorUnitarioResultanteM2Mes();
		this.promedioRentaMensual = comparacion.getPromedioRentaMensual();
		this.promedioAreaConstruccionAreaTerreno = comparacion.getPromedioAreaConstruccionAreaTerreno();
		this.tasaAplicada = comparacion.getTasaAplicada();
		this.rentaBrutaMensual = comparacion.getRentaBrutaMensual();
		this.deducciones = comparacion.getDeducciones();
		this.rentaNetaMensual = comparacion.getRentaNetaMensual();
		this.rentaNetaAnual =comparacion.getRentaNetaAnual();
		this.valorCapitalizacion = comparacion.getValorCapitalizacion();
		this.valorM2HomogenizadoSinGaraje = comparacion.getValorM2HomogenizadoSinGaraje();
		
	}
	
}
