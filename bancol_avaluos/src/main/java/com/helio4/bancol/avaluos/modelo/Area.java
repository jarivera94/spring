package com.helio4.bancol.avaluos.modelo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "area")
@Inheritance(strategy=InheritanceType.JOINED)
public class Area {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="area_id")
	private Long id;
	@Column
	private String nombre;
	@Column
	private String descripcion;
	@Column(name="descripcion_numerica")
	private Integer descripcionNumerica;
	@Column(name="tamano_area")
	private BigDecimal area  = BigDecimal.ZERO;
	@Column(name = "unidad_de_medida")
	private Integer unidadDeMedida;
	@Column(name = "valor_unitario")
	private BigDecimal valorUnitario = BigDecimal.ZERO;
	@Column(name = "valor_total")
	private BigDecimal valorTotal = BigDecimal.ZERO;
	@Column(name="coeficiente_copropiedad")
	private BigDecimal coeficienteCopropiedad;
	@Column(name="area_metros")
	private BigDecimal areaEnMetros;
	@Column
	private BigDecimal porcentaje;
	@Column(name="porcentaje_valor_proporcional_terreno")
	private BigDecimal porcentajeValorProporcionalTerreno;
	@Column(name="porcentaje_valor_proporcional_construccion")
	private BigDecimal porcentajeValorProporcionalConstruccion;
	@Column(name="valor_reposicion")
	private BigDecimal valorReposicion;
	@Column(name="costo_total_reposicion")
	private BigDecimal costoTotalReposicion;
	@Column(name="valor_razonable")
	private BigDecimal valorRazonable;
	@Column(name="valor_residual_construccion")
	private BigDecimal valorResidualConstruccion;
	@Column(name="tipo_de_area")
	private Integer tipoArea;
	@ManyToOne
	@JoinColumn(name="avaluo_id", nullable=false)
	private Avaluo avaluo;
	
	/*campos fito y corvini*/
	@Column(name="calificacion")
	private BigDecimal calificacion;
	
	@Column(name="depreciacion")
	private BigDecimal depreciacion;
	
	@Column(name="costo_reposicion")
	private BigDecimal costoReposicion;
	
	@Column(name="valor_depreciacion")
	private BigDecimal valorDepreciacion;
	
	@Column(name="valor_final")
	private BigDecimal valorFinal;
	
	@Column(name="valor_adoptado")
	private BigDecimal valorAdoptado;
	
	@Column(name="valor_construccion")
	private BigDecimal valorConstruccion;
	
	@Column(name="edad")
	private BigDecimal edad;
	
	@Column(name="vida_util")
	private BigDecimal vidaUtil;
	
	@Column(name="valor_reposicion_final")
	private BigDecimal valorReposicionFinal;
	
	/* end campos fito y corvini*/

	public Area() {
	}

	public void update(Long id, String nombre, String descripcion,
			Integer descripcionNumerica, BigDecimal area,
			Integer unidadDeMedida, BigDecimal valorUnitario,
			BigDecimal valorTotal, BigDecimal coeficienteCopropiedad,
			BigDecimal areaEnMetros, BigDecimal porcentaje,
			BigDecimal porcentajeValorProporcionalTerreno,
			BigDecimal porcentajeValorProporcionalConstruccion,
			BigDecimal valorReposicion, BigDecimal costoTotalReposicion,
			BigDecimal valorRazonable, BigDecimal valorResidualConstruccion,
			Integer tipoArea, Avaluo avaluo) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.descripcionNumerica = descripcionNumerica;
		this.area = area;
		this.unidadDeMedida = unidadDeMedida;
		this.valorUnitario = valorUnitario;
		this.valorTotal = valorTotal;
		this.coeficienteCopropiedad = coeficienteCopropiedad;
		this.areaEnMetros = areaEnMetros;
		this.porcentaje = porcentaje;
		this.porcentajeValorProporcionalTerreno = porcentajeValorProporcionalTerreno;
		this.porcentajeValorProporcionalConstruccion = porcentajeValorProporcionalConstruccion;
		this.valorReposicion = valorReposicion;
		this.costoTotalReposicion = costoTotalReposicion;
		this.valorRazonable = valorRazonable;
		this.valorResidualConstruccion = valorResidualConstruccion;
		this.tipoArea = tipoArea;
		this.avaluo = avaluo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getDescripcionNumerica() {
		return descripcionNumerica;
	}

	public void setDescripcionNumerica(Integer descripcionNumerica) {
		this.descripcionNumerica = descripcionNumerica;
	}

	public BigDecimal getArea() {
		return area;
	}

	public void setArea(BigDecimal area) {
		this.area = area;
	}

	public Integer getUnidadDeMedida() {
		return unidadDeMedida;
	}

	public void setUnidadDeMedida(Integer unidadDeMedida) {
		this.unidadDeMedida = unidadDeMedida;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public BigDecimal getCoeficienteCopropiedad() {
		return coeficienteCopropiedad;
	}

	public void setCoeficienteCopropiedad(BigDecimal coeficienteCopropiedad) {
		this.coeficienteCopropiedad = coeficienteCopropiedad;
	}

	public BigDecimal getAreaEnMetros() {
		return areaEnMetros;
	}

	public void setAreaEnMetros(BigDecimal areaEnMetros) {
		this.areaEnMetros = areaEnMetros;
	}

	public BigDecimal getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(BigDecimal porcentaje) {
		this.porcentaje = porcentaje;
	}

	public BigDecimal getPorcentajeValorProporcionalTerreno() {
		return porcentajeValorProporcionalTerreno;
	}

	public void setPorcentajeValorProporcionalTerreno(
			BigDecimal porcentajeValorProporcionalTerreno) {
		this.porcentajeValorProporcionalTerreno = porcentajeValorProporcionalTerreno;
	}

	public BigDecimal getPorcentajeValorProporcionalConstruccion() {
		return porcentajeValorProporcionalConstruccion;
	}

	public void setPorcentajeValorProporcionalConstruccion(
			BigDecimal porcentajeValorProporcionalConstruccion) {
		this.porcentajeValorProporcionalConstruccion = porcentajeValorProporcionalConstruccion;
	}

	public BigDecimal getValorReposicion() {
		return valorReposicion;
	}

	public void setValorReposicion(BigDecimal valorReposicion) {
		this.valorReposicion = valorReposicion;
	}

	public BigDecimal getCostoTotalReposicion() {
		return costoTotalReposicion;
	}

	public void setCostoTotalReposicion(BigDecimal costoTotalReposicion) {
		this.costoTotalReposicion = costoTotalReposicion;
	}

	public BigDecimal getValorRazonable() {
		return valorRazonable;
	}

	public void setValorRazonable(BigDecimal valorRazonable) {
		this.valorRazonable = valorRazonable;
	}

	public BigDecimal getValorResidualConstruccion() {
		return valorResidualConstruccion;
	}

	public void setValorResidualConstruccion(BigDecimal valorResidualConstruccion) {
		this.valorResidualConstruccion = valorResidualConstruccion;
	}

	public Integer getTipoArea() {
		return tipoArea;
	}

	public void setTipoArea(Integer tipoArea) {
		this.tipoArea = tipoArea;
	}

	public Avaluo getAvaluo() {
		return avaluo;
	}

	public void setAvaluo(Avaluo avaluo) {
		this.avaluo = avaluo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((area == null) ? 0 : area.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result
				+ ((tipoArea == null) ? 0 : tipoArea.hashCode());
		result = prime * result
				+ ((unidadDeMedida == null) ? 0 : unidadDeMedida.hashCode());
		result = prime * result
				+ ((valorUnitario == null) ? 0 : valorUnitario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Area other = (Area) obj;
		if (area == null) {
			if (other.area != null)
				return false;
		} else if (!area.equals(other.area))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (tipoArea == null) {
			if (other.tipoArea != null)
				return false;
		} else if (!tipoArea.equals(other.tipoArea))
			return false;
		if (unidadDeMedida == null) {
			if (other.unidadDeMedida != null)
				return false;
		} else if (!unidadDeMedida.equals(other.unidadDeMedida))
			return false;
		if (valorUnitario == null) {
			if (other.valorUnitario != null)
				return false;
		} else if (!valorUnitario.equals(other.valorUnitario))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Area [id=" + id + ", nombre=" + nombre + ", descripcion="
				+ descripcion + ", area=" + area + ", unidadDeMedida="
				+ unidadDeMedida + ", valorUnitario=" + valorUnitario
				+ ", valorTotal=" + valorTotal + ", coeficienteCopropiedad="
				+ coeficienteCopropiedad + ", valorProporcional="
				+ areaEnMetros + ", porcentaje=" + porcentaje
				+ ", valorReposicion=" + valorReposicion
				+ ", costoTotalReposicion=" + costoTotalReposicion
				+ ", valorRazonable=" + valorRazonable
				+ ", valorResidualConstruccion=" + valorResidualConstruccion
				+ ", tipoArea=" + tipoArea + ", avaluo=" + avaluo + "]";
	}

	public BigDecimal getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(BigDecimal calificacion) {
		this.calificacion = calificacion;
	}

	public BigDecimal getEdad() {
		return edad;
	}

	public void setEdad(BigDecimal edad) {
		this.edad = edad;
	}

	public BigDecimal getVidaUtil() {
		return vidaUtil;
	}

	public void setVidaUtil(BigDecimal vidaUtil) {
		this.vidaUtil = vidaUtil;
	}

	public BigDecimal getDepreciacion() {
		return depreciacion;
	}

	public void setDepreciacion(BigDecimal depreciacion) {
		this.depreciacion = depreciacion;
	}

	public BigDecimal getCostoReposicion() {
		return costoReposicion;
	}

	public void setCostoReposicion(BigDecimal costoReposicion) {
		this.costoReposicion = costoReposicion;
	}

	public BigDecimal getValorDepreciacion() {
		return valorDepreciacion;
	}

	public void setValorDepreciacion(BigDecimal valorDepreciacion) {
		this.valorDepreciacion = valorDepreciacion;
	}

	public BigDecimal getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(BigDecimal valorFinal) {
		this.valorFinal = valorFinal;
	}

	public BigDecimal getValorAdoptado() {
		return valorAdoptado;
	}

	public void setValorAdoptado(BigDecimal valorAdoptado) {
		this.valorAdoptado = valorAdoptado;
	}

	public BigDecimal getValorConstruccion() {
		return valorConstruccion;
	}

	public void setValorConstruccion(BigDecimal valorConstruccion) {
		this.valorConstruccion = valorConstruccion;
	}

	public BigDecimal getValorReposicionFinal() {
		return valorReposicionFinal;
	}

	public void setValorReposicionFinal(BigDecimal valorReposicionFinal) {
		this.valorReposicionFinal = valorReposicionFinal;
	}

}