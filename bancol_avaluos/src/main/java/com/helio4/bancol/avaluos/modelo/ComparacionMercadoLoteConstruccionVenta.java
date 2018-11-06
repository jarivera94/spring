package com.helio4.bancol.avaluos.modelo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.helio4.bancol.avaluos.dto.ComparacionMercadoLoteConstruccionVentaDTO;

@Entity
@Table(name = "comparacion_mercado_lote_construccion_venta")
@PrimaryKeyJoinColumn(name = "metodo_valuacion_id")
public class ComparacionMercadoLoteConstruccionVenta extends ComparacionMercadoLoteConstruccion
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2637115129607312679L;

	@Column(name = "promedio_area_construccion_area_terreno")
	private BigDecimal promedioAreaConstruccionAreaTerreno;
	@Column(name = "promedio_precio_unitario")
	private BigDecimal promedioPrecioUnitario;
	@Column(name = "promedio_precio_unitario_m2_homogenizado")
	private BigDecimal promedioPrecioUnitarioM2Homogenizado;
	@Column(name = "promedio_m2_construccion_terreno_homogenizado")
	private BigDecimal promedioM2ConstruccionTerrenoHomogenizado;
	@Column(name = "promedio_m2_construccion_terreno")
	private BigDecimal promedioM2ConstruccionTerreno;
	@Column(name = "media_artimetica")
	private BigDecimal mediaArtimetica;
	@Column(name = "desviacion_estandar")
	private BigDecimal desviacionEstandar;
	@Column(name = "coheficiente_variacion")
	private BigDecimal coheficienteVariacion;
	@Column(name="promedio_valor_m2_ajustado")
	private BigDecimal promedioValorM2Ajustado;
	@Column(name="promedio_valor_comercial")
	private BigDecimal promedioValorComercial;

	
	public BigDecimal getPromedioAreaConstruccionAreaTerreno() {
		return promedioAreaConstruccionAreaTerreno;
	}

	public void setPromedioAreaConstruccionAreaTerreno(BigDecimal promedioAreaConstruccionAreaTerreno) {
		this.promedioAreaConstruccionAreaTerreno = promedioAreaConstruccionAreaTerreno;
	}

	public BigDecimal getPromedioPrecioUnitario() {
		return promedioPrecioUnitario;
	}

	public void setPromedioPrecioUnitario(BigDecimal promedioPrecioUnitario) {
		this.promedioPrecioUnitario = promedioPrecioUnitario;
	}

	public BigDecimal getPromedioPrecioUnitarioM2Homogenizado() {
		return promedioPrecioUnitarioM2Homogenizado;
	}

	public void setPromedioPrecioUnitarioM2Homogenizado(BigDecimal promedioPrecioUnitarioM2Homogenizado) {
		this.promedioPrecioUnitarioM2Homogenizado = promedioPrecioUnitarioM2Homogenizado;
	}

	public BigDecimal getPromedioM2ConstruccionTerrenoHomogenizado() {
		return promedioM2ConstruccionTerrenoHomogenizado;
	}

	public void setPromedioM2ConstruccionTerrenoHomogenizado(BigDecimal promedioM2ConstruccionTerrenoHomogenizado) {
		this.promedioM2ConstruccionTerrenoHomogenizado = promedioM2ConstruccionTerrenoHomogenizado;
	}

	public BigDecimal getPromedioM2ConstruccionTerreno() {
		return promedioM2ConstruccionTerreno;
	}

	public void setPromedioM2ConstruccionTerreno(BigDecimal promedioM2ConstruccionTerreno) {
		this.promedioM2ConstruccionTerreno = promedioM2ConstruccionTerreno;
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

	public BigDecimal getCoheficienteVariacion() {
		return coheficienteVariacion;
	}

	public void setCoheficienteVariacion(BigDecimal coheficienteVariacion) {
		this.coheficienteVariacion = coheficienteVariacion;
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

	public void cargarLoteConstruccionVentaDTO(ComparacionMercadoLoteConstruccionVentaDTO comparacion){
		
		super.cargarLoteConstruccionDTO(comparacion);
		
		this.promedioAreaConstruccionAreaTerreno = comparacion.getPromedioAreaConstruccionAreaTerreno();
		this.promedioPrecioUnitario = comparacion.getPromedioPrecioUnitario();
		this.promedioPrecioUnitarioM2Homogenizado = comparacion.getPromedioPrecioUnitarioM2Homogenizado();
		this.promedioM2ConstruccionTerrenoHomogenizado =comparacion.getPromedioM2ConstruccionTerrenoHomogenizado();
		this.promedioM2ConstruccionTerreno = comparacion.getPromedioM2ConstruccionTerreno();
		this.mediaArtimetica =comparacion.getMediaArtimetica();
		this.desviacionEstandar = comparacion.getDesviacionEstandar();
		this.coheficienteVariacion = comparacion.getCoheficienteVariacion();
		this.promedioValorM2Ajustado =  comparacion.getPromedioValorM2Ajustado();
		this.promedioValorComercial = comparacion.getPromedioValorComercial();
		
	}

}
