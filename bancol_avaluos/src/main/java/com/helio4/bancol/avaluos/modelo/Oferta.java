package com.helio4.bancol.avaluos.modelo;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Oferta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "oferta_id")
	private Long id;

	@Column(name = "ciudad")
	private String ciudad;

	@Column(name = "barrio")
	private String barrio;

	@Column(name = "conjunto")
	private String conjunto;

	@Column(name = "telefono")
	private String telefono;

	@Column(name = "celular")
	private String celular;

	@Column(name = "valor_depurado")
	private BigDecimal valorDepurado;

	@Column(name = "garajes_depositos")
	private BigDecimal garajesDepositos;

	@Column(name = "valor_oferta")
	private BigDecimal valorOferta;

	@Column(name = "porcentaje_depurado")
	private BigDecimal porcentajeDepurado;

	@Column(name = "area_construida")
	private Integer areaConstruida;

	@Column(name = "valor_mc_construccion")
	private BigDecimal valorM2Construccion;

	@Column(name = "construccion")
	private BigDecimal construccion;

	@Column(name = "observaciones")
	private String observaciones;

	@Column(name = "estado_conservacion")
	private BigDecimal estadoConservacion;

	@Column(name = "edad_inmueble")
	private Integer edadInmueble;

	@Column(name = "vida_util")
	private Integer vidaUtil;

	@Column(name = "valor_reposicion")
	private BigDecimal valorReposicion;

	@Column(name = "area_terreno")
	private BigDecimal areaTerreno;

	@Column(name = "total_terreno")
	private BigDecimal totalTerreno;

	@Column(name = "valor_mc_terreno")
	private BigDecimal valorM2Terreno;

	@Column(name = "valor_final_depurado")
	private BigDecimal valorFinalDepurado;

	@Column(name = "valor_integral_construccion")
	private BigDecimal valorIntegralConstruccion;

	@ManyToOne
	@JoinColumn(name = "divipola", nullable = true)
	private Divipola divipola;

	@ManyToOne
	@JoinColumn(name = "metodo_valuacion_id", nullable = false)
	private MetodoValuacion metodoValuacion;

	// Nuevas metodologías
	@Column(name = "localizacion")
	private String localizacion;
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "fuente")
	private String fuente;

	@Column(name = "tipo_oferta")
	private Integer tipoOferta;

	@Column(name = "edad")
	private Integer edad;
	@Column(name = "ubicacion")
	private Integer ubicacion;
	@Column(name = "acabados")
	private Integer acabados;
	@Column(name = "negociacion")
	private BigDecimal negociacion;

	@Column(name = "edad_homogenizada")
	private BigDecimal edadHomogenizada;

	@Column(name = "factor_homogenizacion")
	private BigDecimal factorHomogenizacion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBarrio() {
		return barrio;
	}

	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}

	public String getConjunto() {
		return conjunto;
	}

	public void setConjunto(String conjunto) {
		this.conjunto = conjunto;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public BigDecimal getValorDepurado() {
		return valorDepurado;
	}

	public void setValorDepurado(BigDecimal valorDepurado) {
		this.valorDepurado = valorDepurado;
	}

	public BigDecimal getGarajesDepositos() {
		return garajesDepositos;
	}

	public void setGarajesDepositos(BigDecimal garajesDepositos) {
		this.garajesDepositos = garajesDepositos;
	}

	public BigDecimal getValorOferta() {
		return valorOferta;
	}

	public void setValorOferta(BigDecimal valorOferta) {
		this.valorOferta = valorOferta;
	}

	public BigDecimal getPorcentajeDepurado() {
		return porcentajeDepurado;
	}

	public void setPorcentajeDepurado(BigDecimal porcentajeDepurado) {
		this.porcentajeDepurado = porcentajeDepurado;
	}

	public Integer getAreaConstruida() {
		return areaConstruida;
	}

	public void setAreaConstruida(Integer areaConstruida) {
		this.areaConstruida = areaConstruida;
	}

	public BigDecimal getValorM2Construccion() {
		return valorM2Construccion;
	}

	public void setValorM2Construccion(BigDecimal valorM2Construccion) {
		this.valorM2Construccion = valorM2Construccion;
	}

	public BigDecimal getConstruccion() {
		return construccion;
	}

	public void setConstruccion(BigDecimal construccion) {
		this.construccion = construccion;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public BigDecimal getEstadoConservacion() {
		return estadoConservacion;
	}

	public void setEstadoConservacion(BigDecimal estadoConservacion) {
		this.estadoConservacion = estadoConservacion;
	}

	public Integer getEdadInmueble() {
		return edadInmueble;
	}

	public void setEdadInmueble(Integer edadInmueble) {
		this.edadInmueble = edadInmueble;
	}

	public Integer getVidaUtil() {
		return vidaUtil;
	}

	public void setVidaUtil(Integer vidaUtil) {
		this.vidaUtil = vidaUtil;
	}

	public BigDecimal getValorReposicion() {
		return valorReposicion;
	}

	public void setValorReposicion(BigDecimal valorReposicion) {
		this.valorReposicion = valorReposicion;
	}

	public BigDecimal getAreaTerreno() {
		return areaTerreno;
	}

	public void setAreaTerreno(BigDecimal areaTerreno) {
		this.areaTerreno = areaTerreno;
	}

	public BigDecimal getTotalTerreno() {
		return totalTerreno;
	}

	public void setTotalTerreno(BigDecimal totalTerreno) {
		this.totalTerreno = totalTerreno;
	}

	public BigDecimal getValorM2Terreno() {
		return valorM2Terreno;
	}

	public void setValorM2Terreno(BigDecimal valorM2Terreno) {
		this.valorM2Terreno = valorM2Terreno;
	}

	public BigDecimal getValorFinalDepurado() {
		return valorFinalDepurado;
	}

	public void setValorFinalDepurado(BigDecimal valorFinalDepurado) {
		this.valorFinalDepurado = valorFinalDepurado;
	}

	public MetodoValuacion getMetodoValuacion() {
		return metodoValuacion;
	}

	public void setMetodoValuacion(MetodoValuacion metodoValuacion) {
		this.metodoValuacion = metodoValuacion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public BigDecimal getValorIntegralConstruccion() {
		return valorIntegralConstruccion;
	}

	public void setValorIntegralConstruccion(BigDecimal valorIntegralConstruccion) {
		this.valorIntegralConstruccion = valorIntegralConstruccion;
	}

	public Divipola getDivipola() {
		return divipola;
	}

	public void setDivipola(Divipola divipola) {
		this.divipola = divipola;
	}

	public String getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public Integer getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Integer ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Integer getAcabados() {
		return acabados;
	}

	public void setAcabados(Integer acabados) {
		this.acabados = acabados;
	}

	public BigDecimal getNegociacion() {
		return negociacion;
	}

	public void setNegociacion(BigDecimal negociacion) {
		this.negociacion = negociacion;
	}

	public BigDecimal getEdadHomogenizada() {
		return edadHomogenizada;
	}

	public void setEdadHomogenizada(BigDecimal edadHomogenizada) {
		this.edadHomogenizada = edadHomogenizada;
	}

	public BigDecimal getFactorHomogenizacion() {
		return factorHomogenizacion;
	}

	public void setFactorHomogenizacion(BigDecimal factorHomogenizacion) {
		this.factorHomogenizacion = factorHomogenizacion;
	}

	public Integer getTipoOferta() {
		return tipoOferta;
	}

	public void setTipoOferta(Integer tipoOferta) {
		this.tipoOferta = tipoOferta;
	}

	public String getFuente() {
		return fuente;
	}

	public void setFuente(String fuente) {
		this.fuente = fuente;
	}

}
