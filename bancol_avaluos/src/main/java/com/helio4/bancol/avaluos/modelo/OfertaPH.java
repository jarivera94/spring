package com.helio4.bancol.avaluos.modelo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "oferta_ph")
@PrimaryKeyJoinColumn(name = "oferta_id")
public class OfertaPH extends Oferta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7932533134049433097L;

	@Column(name = "piso")
	private Integer piso;
	@Column(name = "area")
	private BigDecimal area;
	@Column(name = "tipo_area")
	private Integer tipoArea;
	@Column(name = "garajes")
	private Integer garajes;

	@Column(name = "edificio_conjunto")
	private Integer edificioConjunto;
	@Column(name = "superficie")
	private BigDecimal superficie;
	@Column(name = "ubicacion_piso")
	private BigDecimal ubicacionPiso;

	@Column(name = "precio_unitario_garaje")
	private BigDecimal precioUnitarioGaraje;

	@Column(name = "valor_administracion")
	private BigDecimal valorAdministracion;

	@Column(name = "valor_metro_homogenizado_sin_garaje")
	private BigDecimal valorMetroHomogenizadoSinGaraje;

	@Column(name = "area_libre")
	private BigDecimal areaLibre;

	@Column(name = "precio_unitario_administracion_m2")
	private BigDecimal precioUnitarioAdministracionM2;

	@Column(name = "valor_m2_sin_garaje_no_homogenizado")
	private BigDecimal valorM2SinGarajeNoHomogenizado;
	@Column(name = "valor_m2_homogenizado_sin_garaje_y_area_libre")
	private BigDecimal valorM2HomogenizadoSinGarajeYAreaLibre;

	public Integer getPiso() {
		return piso;
	}

	public void setPiso(Integer piso) {
		this.piso = piso;
	}

	public BigDecimal getArea() {
		return area;
	}

	public void setArea(BigDecimal area) {
		this.area = area;
	}

	public Integer getTipoArea() {
		return tipoArea;
	}

	public void setTipoArea(Integer tipoArea) {
		this.tipoArea = tipoArea;
	}

	public Integer getGarajes() {
		return garajes;
	}

	public void setGarajes(Integer garajes) {
		this.garajes = garajes;
	}

	public Integer getEdificioConjunto() {
		return edificioConjunto;
	}

	public void setEdificioConjunto(Integer edificioConjunto) {
		this.edificioConjunto = edificioConjunto;
	}

	public BigDecimal getSuperficie() {
		return superficie;
	}

	public void setSuperficie(BigDecimal superficie) {
		this.superficie = superficie;
	}

	public BigDecimal getUbicacionPiso() {
		return ubicacionPiso;
	}

	public void setUbicacionPiso(BigDecimal ubicacionPiso) {
		this.ubicacionPiso = ubicacionPiso;
	}

	public BigDecimal getPrecioUnitarioGaraje() {
		return precioUnitarioGaraje;
	}

	public void setPrecioUnitarioGaraje(BigDecimal precioUnitarioGaraje) {
		this.precioUnitarioGaraje = precioUnitarioGaraje;
	}

	public BigDecimal getValorAdministracion() {
		return valorAdministracion;
	}

	public void setValorAdministracion(BigDecimal valorAdministracion) {
		this.valorAdministracion = valorAdministracion;
	}

	public BigDecimal getValorMetroHomogenizadoSinGaraje() {
		return valorMetroHomogenizadoSinGaraje;
	}

	public void setValorMetroHomogenizadoSinGaraje(BigDecimal valorMetroHomogenizadoSinGaraje) {
		this.valorMetroHomogenizadoSinGaraje = valorMetroHomogenizadoSinGaraje;
	}

	public BigDecimal getAreaLibre() {
		return areaLibre;
	}

	public void setAreaLibre(BigDecimal areaLibre) {
		this.areaLibre = areaLibre;
	}

	public BigDecimal getPrecioUnitarioAdministracionM2() {
		return precioUnitarioAdministracionM2;
	}

	public void setPrecioUnitarioAdministracionM2(BigDecimal precioUnitarioAdministracionM2) {
		this.precioUnitarioAdministracionM2 = precioUnitarioAdministracionM2;
	}

	public BigDecimal getValorM2SinGarajeNoHomogenizado() {
		return valorM2SinGarajeNoHomogenizado;
	}

	public void setValorM2SinGarajeNoHomogenizado(BigDecimal valorM2SinGarajeNoHomogenizado) {
		this.valorM2SinGarajeNoHomogenizado = valorM2SinGarajeNoHomogenizado;
	}

	public BigDecimal getValorM2HomogenizadoSinGarajeYAreaLibre() {
		return valorM2HomogenizadoSinGarajeYAreaLibre;
	}

	public void setValorM2HomogenizadoSinGarajeYAreaLibre(BigDecimal valorM2HomogenizadoSinGarajeYAreaLibre) {
		this.valorM2HomogenizadoSinGarajeYAreaLibre = valorM2HomogenizadoSinGarajeYAreaLibre;
	}

}
