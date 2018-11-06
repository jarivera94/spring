package com.helio4.bancol.avaluos.modelo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "comparacion_mercado_ph")
@PrimaryKeyJoinColumn(name = "metodo_valuacion_id")
public class ComparacionMercadoPh extends MetodoValuacion implements Serializable {
	private static final long serialVersionUID = 1L;
	@Column(name = "desviacion")
	private BigDecimal desviacion;
	@Column(name = "promedio_mc")
	private BigDecimal promedioM2;
	@Column(name = "coeficiente_variacion")
	private BigDecimal coeficienteVariacion;
	@Column(name = "numero_datos")
	private Integer numeroDatos;
	@Column(name = "raiz")
	private BigDecimal raiz;
	@Column(name = "tstudent")
	private BigDecimal tstudent;
	@Column(name = "limite_inferior")
	private BigDecimal limiteInferior;
	@Column(name = "limite_superior")
	private BigDecimal limiteSuperior;

	// sujeto
	@Column(name = "piso")
	private Integer piso;

	@Column(name = "area")
	private BigDecimal area;

	@Column(name = "garajes")
	private Integer garajes;

	@Column(name = "edad")
	private Integer edad;

	@Column(name = "valor_administracion")
	private BigDecimal valorAdministracion;

	@Column(name = "area_libre")
	private BigDecimal areaLibre;

	@Column(name = "precio_unitario_administracion_m2")
	private BigDecimal precioUnitarioAdministracionM2;

    @Column(name = "promedio_valor_unitario_garaje")
  	private BigDecimal promedioValorUnitarioGaraje;

    @Column(name = "promedio_valor_m2_homogenizado_sin_garaje")
    private BigDecimal promedioValorM2HomogenizadoSinGaraje;

    @Column(name = "promedio_valor_comercial")
    private BigDecimal promedioValorComercial;

    @Column(name = "promedio_valor_m2_sin_garaje_no_homogenizado")
    private BigDecimal promedioValorM2SinGarajeNoHomogenizado;

    @Column(name = "promedio_valor_m2_homogenizado_sin_garaje_area_libre")
    private BigDecimal promedioValorM2HomogenizadoSinGarajeAreaLibre;
    
    @Column(name = "factor_superficie")
    private BigDecimal factorSuperficie;
    
    @Column(name = "factor_edad")
    private BigDecimal factorEdad;
    
    @Column(name = "valor_m2_area_libre")
    private BigDecimal valorM2AreaLibre;
    
    @Column(name = "valor_unitario_garaje")
    private BigDecimal valorUnitarioGaraje;
    
    @Column(name = "tipo_proyecto")
    private Integer tipoProyecto;
    
    @Column(name = "valor_m2_homogenizado_GJ")
    private BigDecimal valorm2homogenizadoGJ;

	public ComparacionMercadoPh() {

	}

	public BigDecimal getDesviacion() {
		return desviacion;
	}

	public void setDesviacion(BigDecimal desviacion) {
		this.desviacion = desviacion;
	}

	public BigDecimal getPromedioM2() {
		return promedioM2;
	}

	public void setPromedioM2(BigDecimal promedioM2) {
		this.promedioM2 = promedioM2;
	}

	public BigDecimal getCoeficienteVariacion() {
		return coeficienteVariacion;
	}

	public void setCoeficienteVariacion(BigDecimal coeficienteVariacion) {
		this.coeficienteVariacion = coeficienteVariacion;
	}

	public Integer getNumeroDatos() {
		return numeroDatos;
	}

	public void setNumeroDatos(Integer numeroDatos) {
		this.numeroDatos = numeroDatos;
	}

	public BigDecimal getRaiz() {
		return raiz;
	}

	public void setRaiz(BigDecimal raiz) {
		this.raiz = raiz;
	}

	public BigDecimal getLimiteInferior() {
		return limiteInferior;
	}

	public void setLimiteInferior(BigDecimal limiteInferior) {
		this.limiteInferior = limiteInferior;
	}

	public BigDecimal getLimiteSuperior() {
		return limiteSuperior;
	}

	public void setLimiteSuperior(BigDecimal limiteSuperior) {
		this.limiteSuperior = limiteSuperior;
	}

	public BigDecimal getTstudent() {
		return tstudent;
	}

	public void setTstudent(BigDecimal tstudent) {
		this.tstudent = tstudent;
	}

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

	public Integer getGarajes() {
		return garajes;
	}

	public void setGarajes(Integer garajes) {
		this.garajes = garajes;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public BigDecimal getValorAdministracion() {
		return valorAdministracion;
	}

	public void setValorAdministracion(BigDecimal valorAdministracion) {
		this.valorAdministracion = valorAdministracion;
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

	public BigDecimal getPromedioValorUnitarioGaraje() {
		return promedioValorUnitarioGaraje;
	}

	public void setPromedioValorUnitarioGaraje(BigDecimal promedioValorUnitarioGaraje) {
		this.promedioValorUnitarioGaraje = promedioValorUnitarioGaraje;
	}

	public BigDecimal getPromedioValorM2HomogenizadoSinGaraje() {
		return promedioValorM2HomogenizadoSinGaraje;
	}

	public void setPromedioValorM2HomogenizadoSinGaraje(BigDecimal promedioValorM2HomogenizadoSinGaraje) {
		this.promedioValorM2HomogenizadoSinGaraje = promedioValorM2HomogenizadoSinGaraje;
	}

	public BigDecimal getPromedioValorComercial() {
		return promedioValorComercial;
	}

	public void setPromedioValorComercial(BigDecimal promedioValorComercial) {
		this.promedioValorComercial = promedioValorComercial;
	}

	public BigDecimal getPromedioValorM2SinGarajeNoHomogenizado() {
		return promedioValorM2SinGarajeNoHomogenizado;
	}

	public void setPromedioValorM2SinGarajeNoHomogenizado(BigDecimal promedioValorM2SinGarajeNoHomogenizado) {
		this.promedioValorM2SinGarajeNoHomogenizado = promedioValorM2SinGarajeNoHomogenizado;
	}

	public BigDecimal getPromedioValorM2HomogenizadoSinGarajeAreaLibre() {
		return promedioValorM2HomogenizadoSinGarajeAreaLibre;
	}

	public void setPromedioValorM2HomogenizadoSinGarajeAreaLibre(BigDecimal promedioValorM2HomogenizadoSinGarajeAreaLibre) {
		this.promedioValorM2HomogenizadoSinGarajeAreaLibre = promedioValorM2HomogenizadoSinGarajeAreaLibre;
	}

	public BigDecimal getFactorSuperficie() {
		return factorSuperficie;
	}

	public void setFactorSuperficie(BigDecimal factorSuperficie) {
		this.factorSuperficie = factorSuperficie;
	}

	public BigDecimal getFactorEdad() {
		return factorEdad;
	}

	public void setFactorEdad(BigDecimal factorEdad) {
		this.factorEdad = factorEdad;
	}

	public BigDecimal getValorM2AreaLibre() {
		return valorM2AreaLibre;
	}

	public void setValorM2AreaLibre(BigDecimal valorM2AreaLibre) {
		this.valorM2AreaLibre = valorM2AreaLibre;
	}

	public BigDecimal getValorUnitarioGaraje() {
		return valorUnitarioGaraje;
	}

	public void setValorUnitarioGaraje(BigDecimal valorUnitarioGaraje) {
		this.valorUnitarioGaraje = valorUnitarioGaraje;
	}

	public Integer getTipoProyecto() {
		return tipoProyecto;
	}

	public void setTipoProyecto(Integer tipoProyecto) {
		this.tipoProyecto = tipoProyecto;
	}

	public BigDecimal getValorm2homogenizadoGJ() {
		return valorm2homogenizadoGJ;
	}

	public void setValorm2homogenizadoGJ(BigDecimal valorm2homogenizadoGJ) {
		this.valorm2homogenizadoGJ = valorm2homogenizadoGJ;
	}
	
	
	
}
