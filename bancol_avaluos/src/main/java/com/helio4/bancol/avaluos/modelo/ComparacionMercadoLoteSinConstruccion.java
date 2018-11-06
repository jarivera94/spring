package com.helio4.bancol.avaluos.modelo;
import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.helio4.bancol.avaluos.dto.ComparacionMercadoLoteSinConstruccionDTO;

@Entity
@Table(name = "comparacion_mercado_lote_sin_construccion")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "metodo_valuacion_id")
public class ComparacionMercadoLoteSinConstruccion extends MetodoValuacion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="area_lote")
	private BigDecimal areaLote;
	@Column(name="promedio_precio_unitario_m2")
	private BigDecimal promedioPrecioUnitarioM2;
	@Column(name="promedio_valor_m2_ajustado")
	private BigDecimal promedioValorM2Ajustado;
	@Column(name="promedio_valor_comercial")
	private BigDecimal promedioValorComercial;
	@Column(name="factor_superficie")
	private BigDecimal factorSuperficie;
	@Column(name="media_artimetica")
	private BigDecimal mediaArtimetica;
	@Column(name="desviacion_estandar")
	private BigDecimal desviacionEstandar;
	@Column(name="variacion")
	private BigDecimal variacion;
	

	public BigDecimal getAreaLote() {
		return areaLote;
	}
	public void setAreaLote(BigDecimal areaLote) {
		this.areaLote = areaLote;
	}
	public BigDecimal getPromedioPrecioUnitarioM2() {
		return promedioPrecioUnitarioM2;
	}
	public void setPromedioPrecioUnitarioM2(BigDecimal promedioPrecioUnitarioM2) {
		this.promedioPrecioUnitarioM2 = promedioPrecioUnitarioM2;
	}
	public BigDecimal getPromedioValorM2Ajustado() {
		return promedioValorM2Ajustado;
	}
	public void setPromedioValorM2Ajustado(BigDecimal promedioValorM2Ajustado) {
		this.promedioValorM2Ajustado = promedioValorM2Ajustado;
	}
	public BigDecimal getPromedioValorComercial() {
		return promedioValorComercial;
	}
	public void setPromedioValorComercial(BigDecimal promedioValorComercial) {
		this.promedioValorComercial = promedioValorComercial;
	}
	public BigDecimal getFactorSuperficie() {
		return factorSuperficie;
	}
	public void setFactorSuperficie(BigDecimal factorSuperficie) {
		this.factorSuperficie = factorSuperficie;
	}
	public BigDecimal getMediaArtimetica() {
		return mediaArtimetica;
	}
	public void setMediaArtimetica(BigDecimal mediaArtimetica) {
		this.mediaArtimetica = mediaArtimetica;
	}
	public BigDecimal getDesviacionEstandar() {
		return desviacionEstandar;
	}
	public void setDesviacionEstandar(BigDecimal desviacionEstandar) {
		this.desviacionEstandar = desviacionEstandar;
	}
	public BigDecimal getVariacion() {
		return variacion;
	}
	public void setVariacion(BigDecimal variacion) {
		this.variacion = variacion;
	}
	
	public void cargarLoteDTO(ComparacionMercadoLoteSinConstruccionDTO comparacion){
		super.cargarMetodoDTO(comparacion);
		
		this.areaLote = comparacion.getAreaLote();
		this.promedioPrecioUnitarioM2 = comparacion.getPromedioPrecioUnitarioM2();
		this.promedioValorM2Ajustado = comparacion.getPromedioValorM2Ajustado();
		this.promedioValorComercial = comparacion.getPromedioValorComercial();
		this.factorSuperficie = comparacion.getFactorSuperficie();
		this.mediaArtimetica = comparacion.getMediaArtimetica();
		this.desviacionEstandar =comparacion.getDesviacionEstandar();
		this.variacion = comparacion.getVariacion();
		
	}
	
}
