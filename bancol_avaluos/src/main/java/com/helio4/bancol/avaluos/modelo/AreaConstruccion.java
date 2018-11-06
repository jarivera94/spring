package com.helio4.bancol.avaluos.modelo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="area_construccion")
@PrimaryKeyJoinColumn(name="area_id")
public class AreaConstruccion extends Area {

	@Column(name = "tipo_de_construccion")
	private Integer tipoDeConstruccion;
	@Column(name = "clase_de_inmueble")
	private Integer claseDeInmueble;
	@Column(name = "numero_de_sotanos")
	private Integer numeroDeSotanos;
	@Column(name = "observaciones_numero_de_sotanos")
	private String observacionesNumeroDeSotanos;
	@Column(name = "uso_del_inmueble")
	private Integer usoDelInmueble;
	@Column(name = "estado_de_la_construccion")
	private Integer estadoDeLaConstruccion;
	@Column(name = "observaciones_estado_de_la_construccion")
	private String observacionesEstadoDeLaConstruccion;
	@Column(name = "estado_de_conservacion")
	private BigDecimal estadoDeConservacion;
	@Column(name="estado_conservacion_construccion")
	private Integer estadoConservacionConstruccion;
	@Column(name = "observaciones_estado_de_la_conservacion")
	private String observacionesEstadoDeLaConservacion;
	@Column(name = "porcentaje_avance")
	private BigDecimal porcentajeAvance;
	@Column(name = "numero_de_pisos")
	private BigDecimal numeroDePisos;
	@Column(name = "descripcion_numero_de_pisos")
	private String descripcionNumeroDePisos;
	@Column(name = "estado_de_obra")
	private Integer estadoDeObra;
	@Column(name = "descripcion_estado_de_obra")
	private String descripcionEstadoDeObra;
	@Column(name = "ano_de_construccion")
	private Integer anoDeConstruccion;
	@Column(name = "obseraciones_ano_de_construccion")
	private String obseracionesAnoDeConstruccion;
	@Column(name = "vetustez")
	private BigDecimal vetustez;
	@Column(name = "descripcion_vetustez")
	private String descripcionVetustez;
	@Column(name = "pisos")
	private Integer pisos;
	@Column(name = "descripcion_pisos")
	private String descripcionPisos;
	@Column(name = "estructura")
	private Integer estructura;
	@Column(name = "descripcion_estructura")
	private String descripcionEstructura;
	@Column(name = "reparados")
	private Integer reparados;
	@Column(name = "descripcion_reparados")
	private String descripcionReparados;
	@Column(name = "cubierta")
	private Integer cubierta;
	@Column(name = "descripcion_cubierta")
	private String descripcionCubierta;
	@Column(name = "fachada")
	private Integer fachada;
	@Column(name = "descripcion_fachada")
	private String descripcionFachada;
	@Column(name = "tipo_de_fachada")
	private Integer tipoDeFachada;
	@Column(name = "descripcion_tipo_de_fachada")
	private String descripcionTipoDeFachada;
	@Column(name = "estructura_reforzada")
	private Integer estructuraReforzada;
	@Column(name = "descripcion_estructura_reforzada")
	private String descripcionEstructuraReforzada;
	@Column(name = "danos_previos")
	private Integer danosPrevios;
	@Column(name = "descripcion_danos_previos")
	private String descripcionDanosPrevios;
	@Column(name = "material_de_construccion")
	private Integer materialDeConstruccion;
	@Column(name = "descripcion_material_de_construccion")
	private String descripcionMaterialDeConstruccion;
	@Column(name = "ventaneria")
	private Integer ventaneria;
	@Column(name = "descripcion_ventaneria")
	private String descripcionVentaneria;
	@Column(name = "iluminacion")
	private Integer iluminacion;
	@Column(name = "descripcion_iluminacion")
	private String descripcionIluminacion;
	@Column(name = "ventilacion")
	private Integer ventilacion;
	@Column(name = "descripcion_ventilacion")
	private String descripcionVentilacion;
	@Column(name = "irregularidad_planta")
	private Integer irregularidadPlanta;
	@Column(name = "descripcion_irregularidad_de_planta")
	private String descripcionIrregularidadDePlanta;
	@Column(name = "irregularidad_altura")
	private Integer irregularidadAltura;
	@Column(name = "descripcion_irregularidad_altura")
	private String descripcionIrregularidadAltura;
	@Column(name = "salas")
	private Integer salas;
	@Column(name = "comedores")
	private Integer comedores;
	@Column(name = "estudios")
	private Integer estudios;
	@Column(name = "jardines")
	private Integer jardines;
	@Column(name = "bano_social")
	private Integer banoSocial;
	@Column(name = "estar_habitacion")
	private Integer estarHabitacion;
	@Column(name = "habitaciones")
	private Integer habitaciones;
	@Column(name = "depositos")
	private Integer depositos;
	@Column(name = "banos_privados")
	private Integer banosPrivados;
	@Column(name = "cocinas")
	private Integer cocinas;
	@Column(name = "cuarto_de_servicio")
	private Integer cuartoDeServicio;
	@Column(name = "oficinas")
	private Integer oficinas;
	@Column(name = "bano_servicio")
	private Integer banoServicio;
	@Column(name = "patio_interno")
	private Integer patioInterno;
	@Column(name = "terrazas")
	private Integer terrazas;
	@Column(name = "bodegas")
	private Integer bodegas;
	@Column(name = "zona_de_ropas")
	private Integer zonaDeRopas;
	@Column(name = "balcones")
	private Integer balcones;
	@Column(name = "closeths")
	private Integer closeths;
	@Column(name = "locales")
	private Integer locales;
	@Column(name = "zonas_verdes_privadas")
	private Integer zonasVerdesPrivadas;
	@Column(name = "observaciones_dependencias")
	private String observacionesDependencias;
	@Column(name = "estado_acabados_pisos")
	private Integer estadoAcabadosPisos;
	@Column(name = "estado_acabados_muros")
	private Integer estadoAcabadosMuros;
	@Column(name = "estado_acabados_techos")
	private Integer estadoAcabadosTechos;
	@Column(name = "estado_acabados_carpinteria_metal")
	private Integer estadoAcabadosCarpinteriaMetal;
	@Column(name = "estado_acabados_carpinteria_madera")
	private Integer estadoAcabadosCarpinteriaMadera;
	@Column(name = "estado_acabados_banos")
	private Integer estadoAcabadosBanos;
	@Column(name = "estado_acabados_cocina")
	private Integer estadoAcabadosCocina;
	@Column(name = "calidad_acabados_pisos")
	private String calidadAcabadosPisos;
	@Column(name = "calidad_acabados_muros")
	private String calidadAcabadosMuros;
	@Column(name = "calidad_acabados_techos")
	private String calidadAcabadosTechos;
	@Column(name = "calidad_acabados_carpinteria_metal")
	private String calidadAcabadosCarpinteriaMetal;
	@Column(name = "calidad_acabados_carpinteria_madera")
	private String calidadAcabadosCarpinteriaMadera;
	@Column(name = "calidad_acabados_banos")
	private String calidadAcabadosBanos;
	@Column(name = "calidad_acabados_cocina")
	private String calidadAcabadosCocina;
	@Column(name = "observaciones_acabados")
	private String observacionesAcabados;
	
	public AreaConstruccion() {
	}

	public AreaConstruccion(Integer tipoDeConstruccion,
			Integer claseDeInmueble, Integer numeroDeSotanos,
			String observacionesNumeroDeSotanos, Integer usoDelInmueble,
			Integer estadoDeLaConstruccion,
			String observacionesEstadoDeLaConstruccion,
			BigDecimal estadoDeConservacion,
			Integer estadoConservacionConstruccion,
			String observacionesEstadoDeLaConservacion,
			BigDecimal porcentajeAvance, BigDecimal numeroDePisos,
			String descripcionNumeroDePisos, Integer estadoDeObra,
			String descripcionEstadoDeObra, Integer anoDeConstruccion,
			String obseracionesAnoDeConstruccion, BigDecimal vetustez,
			String descripcionVetustez, Integer pisos, String descripcionPisos,
			Integer estructura, String descripcionEstructura,
			Integer reparados, String descripcionReparados, Integer cubierta,
			String descripcionCubierta, Integer fachada,
			String descripcionFachada, Integer tipoDeFachada,
			String descripcionTipoDeFachada, Integer estructuraReforzada,
			String descripcionEstructuraReforzada, Integer danosPrevios,
			String descripcionDanosPrevios, Integer materialDeConstruccion,
			String descripcionMaterialDeConstruccion, Integer ventaneria,
			String descripcionVentaneria, Integer iluminacion,
			String descripcionIluminacion, Integer ventilacion,
			String descripcionVentilacion, Integer irregularidadPlanta,
			String descripcionIrregularidadDePlanta,
			Integer irregularidadAltura, String descripcionIrregularidadAltura,
			Integer salas, Integer comedores, Integer estudios,
			Integer jardines, Integer banoSocial, Integer estarHabitacion,
			Integer habitaciones, Integer depositos, Integer banosPrivados,
			Integer cocinas, Integer cuartoDeServicio, Integer oficinas,
			Integer banoServicio, Integer patioInterno, Integer terrazas,
			Integer bodegas, Integer zonaDeRopas, Integer balcones,
			Integer closeths, Integer locales, Integer zonasVerdesPrivadas,
			String observacionesDependencias, Integer estadoAcabadosPisos,
			Integer estadoAcabadosMuros, Integer estadoAcabadosTechos,
			Integer estadoAcabadosCarpinteriaMetal,
			Integer estadoAcabadosCarpinteriaMadera,
			Integer estadoAcabadosBanos, Integer estadoAcabadosCocina,
			String calidadAcabadosPisos, String calidadAcabadosMuros,
			String calidadAcabadosTechos,
			String calidadAcabadosCarpinteriaMetal,
			String calidadAcabadosCarpinteriaMadera,
			String calidadAcabadosBanos, String calidadAcabadosCocina,
			String observacionesAcabados, Area area, AvaluoComercial avaluo) {
		super();
		this.tipoDeConstruccion = tipoDeConstruccion;
		this.claseDeInmueble = claseDeInmueble;
		this.numeroDeSotanos = numeroDeSotanos;
		this.observacionesNumeroDeSotanos = observacionesNumeroDeSotanos;
		this.usoDelInmueble = usoDelInmueble;
		this.estadoDeLaConstruccion = estadoDeLaConstruccion;
		this.observacionesEstadoDeLaConstruccion = observacionesEstadoDeLaConstruccion;
		this.estadoDeConservacion = estadoDeConservacion;
		this.estadoConservacionConstruccion = estadoConservacionConstruccion;
		this.observacionesEstadoDeLaConservacion = observacionesEstadoDeLaConservacion;
		this.porcentajeAvance = porcentajeAvance;
		this.numeroDePisos = numeroDePisos;
		this.descripcionNumeroDePisos = descripcionNumeroDePisos;
		this.estadoDeObra = estadoDeObra;
		this.descripcionEstadoDeObra = descripcionEstadoDeObra;
		this.anoDeConstruccion = anoDeConstruccion;
		this.obseracionesAnoDeConstruccion = obseracionesAnoDeConstruccion;
		this.vetustez = vetustez;
		this.descripcionVetustez = descripcionVetustez;
		this.pisos = pisos;
		this.descripcionPisos = descripcionPisos;
		this.estructura = estructura;
		this.descripcionEstructura = descripcionEstructura;
		this.reparados = reparados;
		this.descripcionReparados = descripcionReparados;
		this.cubierta = cubierta;
		this.descripcionCubierta = descripcionCubierta;
		this.fachada = fachada;
		this.descripcionFachada = descripcionFachada;
		this.tipoDeFachada = tipoDeFachada;
		this.descripcionTipoDeFachada = descripcionTipoDeFachada;
		this.estructuraReforzada = estructuraReforzada;
		this.descripcionEstructuraReforzada = descripcionEstructuraReforzada;
		this.danosPrevios = danosPrevios;
		this.descripcionDanosPrevios = descripcionDanosPrevios;
		this.materialDeConstruccion = materialDeConstruccion;
		this.descripcionMaterialDeConstruccion = descripcionMaterialDeConstruccion;
		this.ventaneria = ventaneria;
		this.descripcionVentaneria = descripcionVentaneria;
		this.iluminacion = iluminacion;
		this.descripcionIluminacion = descripcionIluminacion;
		this.ventilacion = ventilacion;
		this.descripcionVentilacion = descripcionVentilacion;
		this.irregularidadPlanta = irregularidadPlanta;
		this.descripcionIrregularidadDePlanta = descripcionIrregularidadDePlanta;
		this.irregularidadAltura = irregularidadAltura;
		this.descripcionIrregularidadAltura = descripcionIrregularidadAltura;
		this.salas = salas;
		this.comedores = comedores;
		this.estudios = estudios;
		this.jardines = jardines;
		this.banoSocial = banoSocial;
		this.estarHabitacion = estarHabitacion;
		this.habitaciones = habitaciones;
		this.depositos = depositos;
		this.banosPrivados = banosPrivados;
		this.cocinas = cocinas;
		this.cuartoDeServicio = cuartoDeServicio;
		this.oficinas = oficinas;
		this.banoServicio = banoServicio;
		this.patioInterno = patioInterno;
		this.terrazas = terrazas;
		this.bodegas = bodegas;
		this.zonaDeRopas = zonaDeRopas;
		this.balcones = balcones;
		this.closeths = closeths;
		this.locales = locales;
		this.zonasVerdesPrivadas = zonasVerdesPrivadas;
		this.observacionesDependencias = observacionesDependencias;
		this.estadoAcabadosPisos = estadoAcabadosPisos;
		this.estadoAcabadosMuros = estadoAcabadosMuros;
		this.estadoAcabadosTechos = estadoAcabadosTechos;
		this.estadoAcabadosCarpinteriaMetal = estadoAcabadosCarpinteriaMetal;
		this.estadoAcabadosCarpinteriaMadera = estadoAcabadosCarpinteriaMadera;
		this.estadoAcabadosBanos = estadoAcabadosBanos;
		this.estadoAcabadosCocina = estadoAcabadosCocina;
		this.calidadAcabadosPisos = calidadAcabadosPisos;
		this.calidadAcabadosMuros = calidadAcabadosMuros;
		this.calidadAcabadosTechos = calidadAcabadosTechos;
		this.calidadAcabadosCarpinteriaMetal = calidadAcabadosCarpinteriaMetal;
		this.calidadAcabadosCarpinteriaMadera = calidadAcabadosCarpinteriaMadera;
		this.calidadAcabadosBanos = calidadAcabadosBanos;
		this.calidadAcabadosCocina = calidadAcabadosCocina;
		this.observacionesAcabados = observacionesAcabados;
	}

	public void update(Integer tipoDeConstruccion,
			Integer claseDeInmueble, Integer numeroDeSotanos,
			String observacionesNumeroDeSotanos, Integer usoDelInmueble,
			Integer estadoDeLaConstruccion,
			String observacionesEstadoDeLaConstruccion,
			BigDecimal estadoDeConservacion,
			Integer estadoConservacionConstruccion,
			String observacionesEstadoDeLaConservacion,
			BigDecimal porcentajeAvance, BigDecimal numeroDePisos,
			String descripcionNumeroDePisos, Integer estadoDeObra,
			String descripcionEstadoDeObra, Integer anoDeConstruccion,
			String obseracionesAnoDeConstruccion, BigDecimal vetustez,
			String descripcionVetustez, Integer pisos, String descripcionPisos,
			Integer estructura, String descripcionEstructura,
			Integer reparados, String descripcionReparados, Integer cubierta,
			String descripcionCubierta, Integer fachada,
			String descripcionFachada, Integer tipoDeFachada,
			String descripcionTipoDeFachada, Integer estructuraReforzada,
			String descripcionEstructuraReforzada, Integer danosPrevios,
			String descripcionDanosPrevios, Integer materialDeConstruccion,
			String descripcionMaterialDeConstruccion, Integer ventaneria,
			String descripcionVentaneria, Integer iluminacion,
			String descripcionIluminacion, Integer ventilacion,
			String descripcionVentilacion, Integer irregularidadPlanta,
			String descripcionIrregularidadDePlanta,
			Integer irregularidadAltura, String descripcionIrregularidadAltura,
			Integer salas, Integer comedores, Integer estudios,
			Integer jardines, Integer banoSocial, Integer estarHabitacion,
			Integer habitaciones, Integer depositos, Integer banosPrivados,
			Integer cocinas, Integer cuartoDeServicio, Integer oficinas,
			Integer banoServicio, Integer patioInterno, Integer terrazas,
			Integer bodegas, Integer zonaDeRopas, Integer balcones,
			Integer closeths, Integer locales, Integer zonasVerdesPrivadas,
			String observacionesDependencias, Integer estadoAcabadosPisos,
			Integer estadoAcabadosMuros, Integer estadoAcabadosTechos,
			Integer estadoAcabadosCarpinteriaMetal,
			Integer estadoAcabadosCarpinteriaMadera,
			Integer estadoAcabadosBanos, Integer estadoAcabadosCocina,
			String calidadAcabadosPisos, String calidadAcabadosMuros,
			String calidadAcabadosTechos,
			String calidadAcabadosCarpinteriaMetal,
			String calidadAcabadosCarpinteriaMadera,
			String calidadAcabadosBanos, String calidadAcabadosCocina,
			String observacionesAcabados, Area area, AvaluoComercial avaluo) {
		this.tipoDeConstruccion = tipoDeConstruccion;
		this.claseDeInmueble = claseDeInmueble;
		this.numeroDeSotanos = numeroDeSotanos;
		this.observacionesNumeroDeSotanos = observacionesNumeroDeSotanos;
		this.usoDelInmueble = usoDelInmueble;
		this.estadoDeLaConstruccion = estadoDeLaConstruccion;
		this.observacionesEstadoDeLaConstruccion = observacionesEstadoDeLaConstruccion;
		this.estadoDeConservacion = estadoDeConservacion;
		this.estadoConservacionConstruccion = estadoConservacionConstruccion;
		this.observacionesEstadoDeLaConservacion = observacionesEstadoDeLaConservacion;
		this.porcentajeAvance = porcentajeAvance;
		this.numeroDePisos = numeroDePisos;
		this.descripcionNumeroDePisos = descripcionNumeroDePisos;
		this.estadoDeObra = estadoDeObra;
		this.descripcionEstadoDeObra = descripcionEstadoDeObra;
		this.anoDeConstruccion = anoDeConstruccion;
		this.obseracionesAnoDeConstruccion = obseracionesAnoDeConstruccion;
		this.vetustez = vetustez;
		this.descripcionVetustez = descripcionVetustez;
		this.pisos = pisos;
		this.descripcionPisos = descripcionPisos;
		this.estructura = estructura;
		this.descripcionEstructura = descripcionEstructura;
		this.reparados = reparados;
		this.descripcionReparados = descripcionReparados;
		this.cubierta = cubierta;
		this.descripcionCubierta = descripcionCubierta;
		this.fachada = fachada;
		this.descripcionFachada = descripcionFachada;
		this.tipoDeFachada = tipoDeFachada;
		this.descripcionTipoDeFachada = descripcionTipoDeFachada;
		this.estructuraReforzada = estructuraReforzada;
		this.descripcionEstructuraReforzada = descripcionEstructuraReforzada;
		this.danosPrevios = danosPrevios;
		this.descripcionDanosPrevios = descripcionDanosPrevios;
		this.materialDeConstruccion = materialDeConstruccion;
		this.descripcionMaterialDeConstruccion = descripcionMaterialDeConstruccion;
		this.ventaneria = ventaneria;
		this.descripcionVentaneria = descripcionVentaneria;
		this.iluminacion = iluminacion;
		this.descripcionIluminacion = descripcionIluminacion;
		this.ventilacion = ventilacion;
		this.descripcionVentilacion = descripcionVentilacion;
		this.irregularidadPlanta = irregularidadPlanta;
		this.descripcionIrregularidadDePlanta = descripcionIrregularidadDePlanta;
		this.irregularidadAltura = irregularidadAltura;
		this.descripcionIrregularidadAltura = descripcionIrregularidadAltura;
		this.salas = salas;
		this.comedores = comedores;
		this.estudios = estudios;
		this.jardines = jardines;
		this.banoSocial = banoSocial;
		this.estarHabitacion = estarHabitacion;
		this.habitaciones = habitaciones;
		this.depositos = depositos;
		this.banosPrivados = banosPrivados;
		this.cocinas = cocinas;
		this.cuartoDeServicio = cuartoDeServicio;
		this.oficinas = oficinas;
		this.banoServicio = banoServicio;
		this.patioInterno = patioInterno;
		this.terrazas = terrazas;
		this.bodegas = bodegas;
		this.zonaDeRopas = zonaDeRopas;
		this.balcones = balcones;
		this.closeths = closeths;
		this.locales = locales;
		this.zonasVerdesPrivadas = zonasVerdesPrivadas;
		this.observacionesDependencias = observacionesDependencias;
		this.estadoAcabadosPisos = estadoAcabadosPisos;
		this.estadoAcabadosMuros = estadoAcabadosMuros;
		this.estadoAcabadosTechos = estadoAcabadosTechos;
		this.estadoAcabadosCarpinteriaMetal = estadoAcabadosCarpinteriaMetal;
		this.estadoAcabadosCarpinteriaMadera = estadoAcabadosCarpinteriaMadera;
		this.estadoAcabadosBanos = estadoAcabadosBanos;
		this.estadoAcabadosCocina = estadoAcabadosCocina;
		this.calidadAcabadosPisos = calidadAcabadosPisos;
		this.calidadAcabadosMuros = calidadAcabadosMuros;
		this.calidadAcabadosTechos = calidadAcabadosTechos;
		this.calidadAcabadosCarpinteriaMetal = calidadAcabadosCarpinteriaMetal;
		this.calidadAcabadosCarpinteriaMadera = calidadAcabadosCarpinteriaMadera;
		this.calidadAcabadosBanos = calidadAcabadosBanos;
		this.calidadAcabadosCocina = calidadAcabadosCocina;
		this.observacionesAcabados = observacionesAcabados;
	}

	public Integer getTipoDeConstruccion() {
		return tipoDeConstruccion;
	}

	public void setTipoDeConstruccion(Integer tipoDeConstruccion) {
		this.tipoDeConstruccion = tipoDeConstruccion;
	}

	public Integer getClaseDeInmueble() {
		return claseDeInmueble;
	}

	public void setClaseDeInmueble(Integer claseDeInmueble) {
		this.claseDeInmueble = claseDeInmueble;
	}

	public Integer getNumeroDeSotanos() {
		return numeroDeSotanos;
	}

	public void setNumeroDeSotanos(Integer numeroDeSotanos) {
		this.numeroDeSotanos = numeroDeSotanos;
	}

	public String getObservacionesNumeroDeSotanos() {
		return observacionesNumeroDeSotanos;
	}

	public void setObservacionesNumeroDeSotanos(String observacionesNumeroDeSotanos) {
		this.observacionesNumeroDeSotanos = observacionesNumeroDeSotanos;
	}

	public Integer getUsoDelInmueble() {
		return usoDelInmueble;
	}

	public void setUsoDelInmueble(Integer usoDelInmueble) {
		this.usoDelInmueble = usoDelInmueble;
	}

	public Integer getEstadoDeLaConstruccion() {
		return estadoDeLaConstruccion;
	}

	public void setEstadoDeLaConstruccion(Integer estadoDeLaConstruccion) {
		this.estadoDeLaConstruccion = estadoDeLaConstruccion;
	}

	public String getObservacionesEstadoDeLaConstruccion() {
		return observacionesEstadoDeLaConstruccion;
	}

	public void setObservacionesEstadoDeLaConstruccion(
			String observacionesEstadoDeLaConstruccion) {
		this.observacionesEstadoDeLaConstruccion = observacionesEstadoDeLaConstruccion;
	}

	public BigDecimal getEstadoDeConservacion() {
		return estadoDeConservacion;
	}

	public void setEstadoDeConservacion(BigDecimal estadoDeConservacion) {
		this.estadoDeConservacion = estadoDeConservacion;
	}

	public Integer getEstadoConservacionConstruccion() {
		return estadoConservacionConstruccion;
	}

	public void setEstadoConservacionConstruccion(Integer estadoConservacionConstruccion) {
		this.estadoConservacionConstruccion = estadoConservacionConstruccion;
	}

	public String getObservacionesEstadoDeLaConservacion() {
		return observacionesEstadoDeLaConservacion;
	}

	public void setObservacionesEstadoDeLaConservacion(
			String observacionesEstadoDeLaConservacion) {
		this.observacionesEstadoDeLaConservacion = observacionesEstadoDeLaConservacion;
	}

	public BigDecimal getPorcentajeAvance() {
		return porcentajeAvance;
	}

	public void setPorcentajeAvance(BigDecimal porcentajeAvance) {
		this.porcentajeAvance = porcentajeAvance;
	}

	public BigDecimal getNumeroDePisos() {
		return numeroDePisos;
	}

	public void setNumeroDePisos(BigDecimal numeroDePisos) {
		this.numeroDePisos = numeroDePisos;
	}

	public String getDescripcionNumeroDePisos() {
		return descripcionNumeroDePisos;
	}

	public void setDescripcionNumeroDePisos(String descripcionNumeroDePisos) {
		this.descripcionNumeroDePisos = descripcionNumeroDePisos;
	}

	public Integer getEstadoDeObra() {
		return estadoDeObra;
	}

	public void setEstadoDeObra(Integer estadoDeObra) {
		if(new Integer(0).equals(estadoDeObra)){
			estadoDeObra = null;
		}
		this.estadoDeObra = estadoDeObra;
	}

	public String getDescripcionEstadoDeObra() {
		return descripcionEstadoDeObra;
	}

	public void setDescripcionEstadoDeObra(String descripcionEstadoDeObra) {
		this.descripcionEstadoDeObra = descripcionEstadoDeObra;
	}

	public Integer getAnoDeConstruccion() {
		return anoDeConstruccion;
	}

	public void setAnoDeConstruccion(Integer anoDeConstruccion) {
		this.anoDeConstruccion = anoDeConstruccion;
	}

	public String getObseracionesAnoDeConstruccion() {
		return obseracionesAnoDeConstruccion;
	}

	public void setObseracionesAnoDeConstruccion(
			String obseracionesAnoDeConstruccion) {
		this.obseracionesAnoDeConstruccion = obseracionesAnoDeConstruccion;
	}

	public BigDecimal getVetustez() {
		return vetustez;
	}

	public void setVetustez(BigDecimal vetustez) {
		this.vetustez = vetustez;
	}

	public String getDescripcionVetustez() {
		return descripcionVetustez;
	}

	public void setDescripcionVetustez(String descripcionVetustez) {
		this.descripcionVetustez = descripcionVetustez;
	}

	public Integer getPisos() {
		return pisos;
	}

	public void setPisos(Integer pisos) {
		this.pisos = pisos;
	}

	public String getDescripcionPisos() {
		return descripcionPisos;
	}

	public void setDescripcionPisos(String descripcionPisos) {
		this.descripcionPisos = descripcionPisos;
	}

	public Integer getEstructura() {
		return estructura;
	}

	public void setEstructura(Integer estructura) {
		this.estructura = estructura;
	}

	public String getDescripcionEstructura() {
		return descripcionEstructura;
	}

	public void setDescripcionEstructura(String descripcionEstructura) {
		this.descripcionEstructura = descripcionEstructura;
	}

	public Integer getReparados() {
		return reparados;
	}

	public void setReparados(Integer reparados) {
		this.reparados = reparados;
	}

	public String getDescripcionReparados() {
		return descripcionReparados;
	}

	public void setDescripcionReparados(String descripcionReparados) {
		this.descripcionReparados = descripcionReparados;
	}

	public Integer getCubierta() {
		return cubierta;
	}

	public void setCubierta(Integer cubierta) {
		this.cubierta = cubierta;
	}

	public String getDescripcionCubierta() {
		return descripcionCubierta;
	}

	public void setDescripcionCubierta(String descripcionCubierta) {
		this.descripcionCubierta = descripcionCubierta;
	}

	public Integer getFachada() {
		return fachada;
	}

	public void setFachada(Integer fachada) {
		this.fachada = fachada;
	}

	public String getDescripcionFachada() {
		return descripcionFachada;
	}

	public void setDescripcionFachada(String descripcionFachada) {
		this.descripcionFachada = descripcionFachada;
	}

	public Integer getTipoDeFachada() {
		return tipoDeFachada;
	}

	public void setTipoDeFachada(Integer tipoDeFachada) {
		this.tipoDeFachada = tipoDeFachada;
	}

	public String getDescripcionTipoDeFachada() {
		return descripcionTipoDeFachada;
	}

	public void setDescripcionTipoDeFachada(String descripcionTipoDeFachada) {
		this.descripcionTipoDeFachada = descripcionTipoDeFachada;
	}

	public Integer getEstructuraReforzada() {
		return estructuraReforzada;
	}

	public void setEstructuraReforzada(Integer estructuraReforzada) {
		this.estructuraReforzada = estructuraReforzada;
	}

	public String getDescripcionEstructuraReforzada() {
		return descripcionEstructuraReforzada;
	}

	public void setDescripcionEstructuraReforzada(
			String descripcionEstructuraReforzada) {
		this.descripcionEstructuraReforzada = descripcionEstructuraReforzada;
	}

	public Integer getDanosPrevios() {
		return danosPrevios;
	}

	public void setDanosPrevios(Integer danosPrevios) {
		this.danosPrevios = danosPrevios;
	}

	public String getDescripcionDanosPrevios() {
		return descripcionDanosPrevios;
	}

	public void setDescripcionDanosPrevios(String descripcionDanosPrevios) {
		this.descripcionDanosPrevios = descripcionDanosPrevios;
	}

	public Integer getMaterialDeConstruccion() {
		return materialDeConstruccion;
	}

	public void setMaterialDeConstruccion(Integer materialDeConstruccion) {
		this.materialDeConstruccion = materialDeConstruccion;
	}

	public String getDescripcionMaterialDeConstruccion() {
		return descripcionMaterialDeConstruccion;
	}

	public void setDescripcionMaterialDeConstruccion(
			String descripcionMaterialDeConstruccion) {
		this.descripcionMaterialDeConstruccion = descripcionMaterialDeConstruccion;
	}

	public Integer getVentaneria() {
		return ventaneria;
	}

	public void setVentaneria(Integer ventaneria) {
		this.ventaneria = ventaneria;
	}

	public String getDescripcionVentaneria() {
		return descripcionVentaneria;
	}

	public void setDescripcionVentaneria(String descripcionVentaneria) {
		this.descripcionVentaneria = descripcionVentaneria;
	}

	public Integer getIluminacion() {
		return iluminacion;
	}

	public void setIluminacion(Integer iluminacion) {
		if(new Integer(0).equals(iluminacion)){
			iluminacion = null;
		}
		this.iluminacion = iluminacion;
	}

	public String getDescripcionIluminacion() {
		return descripcionIluminacion;
	}

	public void setDescripcionIluminacion(String descripcionIluminacion) {
		this.descripcionIluminacion = descripcionIluminacion;
	}

	public Integer getVentilacion() {
		return ventilacion;
	}

	public void setVentilacion(Integer ventilacion) {
		if(new Integer(0).equals(ventilacion)){
			ventilacion = null;
		}
		this.ventilacion = ventilacion;
	}

	public String getDescripcionVentilacion() {
		return descripcionVentilacion;
	}

	public void setDescripcionVentilacion(String descripcionVentilacion) {
		this.descripcionVentilacion = descripcionVentilacion;
	}

	public Integer getIrregularidadPlanta() {
		return irregularidadPlanta;
	}

	public void setIrregularidadPlanta(Integer irregularidadPlanta) {
		this.irregularidadPlanta = irregularidadPlanta;
	}

	public String getDescripcionIrregularidadDePlanta() {
		return descripcionIrregularidadDePlanta;
	}

	public void setDescripcionIrregularidadDePlanta(
			String descripcionIrregularidadDePlanta) {
		this.descripcionIrregularidadDePlanta = descripcionIrregularidadDePlanta;
	}

	public Integer getIrregularidadAltura() {
		return irregularidadAltura;
	}

	public void setIrregularidadAltura(Integer irregularidadAltura) {
		this.irregularidadAltura = irregularidadAltura;
	}

	public String getDescripcionIrregularidadAltura() {
		return descripcionIrregularidadAltura;
	}

	public void setDescripcionIrregularidadAltura(
			String descripcionIrregularidadAltura) {
		this.descripcionIrregularidadAltura = descripcionIrregularidadAltura;
	}

	public Integer getSalas() {
		return salas;
	}

	public void setSalas(Integer salas) {
		this.salas = salas;
	}

	public Integer getComedores() {
		return comedores;
	}

	public void setComedores(Integer comedores) {
		this.comedores = comedores;
	}

	public Integer getEstudios() {
		return estudios;
	}

	public void setEstudios(Integer estudios) {
		this.estudios = estudios;
	}

	public Integer getJardines() {
		return jardines;
	}

	public void setJardines(Integer jardines) {
		this.jardines = jardines;
	}

	public Integer getBanoSocial() {
		return banoSocial;
	}

	public void setBanoSocial(Integer banoSocial) {
		this.banoSocial = banoSocial;
	}

	public Integer getEstarHabitacion() {
		return estarHabitacion;
	}

	public void setEstarHabitacion(Integer estarHabitacion) {
		this.estarHabitacion = estarHabitacion;
	}

	public Integer getHabitaciones() {
		return habitaciones;
	}

	public void setHabitaciones(Integer habitaciones) {
		this.habitaciones = habitaciones;
	}

	public Integer getDepositos() {
		return depositos;
	}

	public void setDepositos(Integer depositos) {
		this.depositos = depositos;
	}

	public Integer getBanosPrivados() {
		return banosPrivados;
	}

	public void setBanosPrivados(Integer banosPrivados) {
		this.banosPrivados = banosPrivados;
	}

	public Integer getCocinas() {
		return cocinas;
	}

	public void setCocinas(Integer cocinas) {
		this.cocinas = cocinas;
	}

	public Integer getCuartoDeServicio() {
		return cuartoDeServicio;
	}

	public void setCuartoDeServicio(Integer cuartoDeServicio) {
		this.cuartoDeServicio = cuartoDeServicio;
	}

	public Integer getOficinas() {
		return oficinas;
	}

	public void setOficinas(Integer oficinas) {
		this.oficinas = oficinas;
	}

	public Integer getBanoServicio() {
		return banoServicio;
	}

	public void setBanoServicio(Integer banoServicio) {
		this.banoServicio = banoServicio;
	}

	public Integer getPatioInterno() {
		return patioInterno;
	}

	public void setPatioInterno(Integer patioInterno) {
		this.patioInterno = patioInterno;
	}

	public Integer getTerrazas() {
		return terrazas;
	}

	public void setTerrazas(Integer terrazas) {
		this.terrazas = terrazas;
	}

	public Integer getBodegas() {
		return bodegas;
	}

	public void setBodegas(Integer bodegas) {
		this.bodegas = bodegas;
	}

	public Integer getZonaDeRopas() {
		return zonaDeRopas;
	}

	public void setZonaDeRopas(Integer zonaDeRopas) {
		this.zonaDeRopas = zonaDeRopas;
	}

	public Integer getBalcones() {
		return balcones;
	}

	public void setBalcones(Integer balcones) {
		this.balcones = balcones;
	}

	public Integer getCloseths() {
		return closeths;
	}

	public void setCloseths(Integer closeths) {
		this.closeths = closeths;
	}

	public Integer getLocales() {
		return locales;
	}

	public void setLocales(Integer locales) {
		this.locales = locales;
	}

	public Integer getZonasVerdesPrivadas() {
		return zonasVerdesPrivadas;
	}

	public void setZonasVerdesPrivadas(Integer zonasVerdesPrivadas) {
		this.zonasVerdesPrivadas = zonasVerdesPrivadas;
	}

	public String getObservacionesDependencias() {
		return observacionesDependencias;
	}

	public void setObservacionesDependencias(String observacionesDependencias) {
		this.observacionesDependencias = observacionesDependencias;
	}

	public Integer getEstadoAcabadosPisos() {
		return estadoAcabadosPisos;
	}

	public void setEstadoAcabadosPisos(Integer estadoAcabadosPisos) {
		if (new Integer(0).equals(estadoAcabadosPisos)) {
			estadoAcabadosPisos = null;
		}
		this.estadoAcabadosPisos = estadoAcabadosPisos;
	}

	public Integer getEstadoAcabadosMuros() {
		return estadoAcabadosMuros;
	}

	public void setEstadoAcabadosMuros(Integer estadoAcabadosMuros) {
		if (new Integer(0).equals(estadoAcabadosMuros)) {
			estadoAcabadosMuros = null;
		}
		this.estadoAcabadosMuros = estadoAcabadosMuros;
	}

	public Integer getEstadoAcabadosTechos() {
		return estadoAcabadosTechos;
	}

	public void setEstadoAcabadosTechos(Integer estadoAcabadosTechos) {
		if (new Integer(0).equals(estadoAcabadosTechos)) {
			estadoAcabadosTechos = null;
		}
		this.estadoAcabadosTechos = estadoAcabadosTechos;
	}

	public Integer getEstadoAcabadosCarpinteriaMetal() {
		return estadoAcabadosCarpinteriaMetal;
	}

	public void setEstadoAcabadosCarpinteriaMetal(
			Integer estadoAcabadosCarpinteriaMetal) {
		if (new Integer(0).equals(estadoAcabadosCarpinteriaMetal)) {
			estadoAcabadosCarpinteriaMetal = null;
		}
		this.estadoAcabadosCarpinteriaMetal = estadoAcabadosCarpinteriaMetal;
	}

	public Integer getEstadoAcabadosCarpinteriaMadera() {
		return estadoAcabadosCarpinteriaMadera;
	}

	public void setEstadoAcabadosCarpinteriaMadera(
			Integer estadoAcabadosCarpinteriaMadera) {
		if (new Integer(0).equals(estadoAcabadosCarpinteriaMadera)) {
			estadoAcabadosCarpinteriaMadera = null;
		}
		this.estadoAcabadosCarpinteriaMadera = estadoAcabadosCarpinteriaMadera;
	}

	public Integer getEstadoAcabadosBanos() {
		return estadoAcabadosBanos;
	}

	public void setEstadoAcabadosBanos(Integer estadoAcabadosBanos) {
		if (new Integer(0).equals(estadoAcabadosBanos)) {
			estadoAcabadosBanos = null;
		}
		this.estadoAcabadosBanos = estadoAcabadosBanos;
	}

	public Integer getEstadoAcabadosCocina() {
		return estadoAcabadosCocina;
	}

	public void setEstadoAcabadosCocina(Integer estadoAcabadosCocina) {
		if (new Integer(0).equals(estadoAcabadosCocina)) {
			estadoAcabadosCocina = null;
		}
		this.estadoAcabadosCocina = estadoAcabadosCocina;
	}

	public String getCalidadAcabadosPisos() {
		return calidadAcabadosPisos;
	}

	public void setCalidadAcabadosPisos(String calidadAcabadosPisos) {
		this.calidadAcabadosPisos = calidadAcabadosPisos;
	}

	public String getCalidadAcabadosMuros() {
		return calidadAcabadosMuros;
	}

	public void setCalidadAcabadosMuros(String calidadAcabadosMuros) {
		this.calidadAcabadosMuros = calidadAcabadosMuros;
	}

	public String getCalidadAcabadosTechos() {
		return calidadAcabadosTechos;
	}

	public void setCalidadAcabadosTechos(String calidadAcabadosTechos) {
		this.calidadAcabadosTechos = calidadAcabadosTechos;
	}

	public String getCalidadAcabadosCarpinteriaMetal() {
		return calidadAcabadosCarpinteriaMetal;
	}

	public void setCalidadAcabadosCarpinteriaMetal(
			String calidadAcabadosCarpinteriaMetal) {
		this.calidadAcabadosCarpinteriaMetal = calidadAcabadosCarpinteriaMetal;
	}

	public String getCalidadAcabadosCarpinteriaMadera() {
		return calidadAcabadosCarpinteriaMadera;
	}

	public void setCalidadAcabadosCarpinteriaMadera(
			String calidadAcabadosCarpinteriaMadera) {
		this.calidadAcabadosCarpinteriaMadera = calidadAcabadosCarpinteriaMadera;
	}

	public String getCalidadAcabadosBanos() {
		return calidadAcabadosBanos;
	}

	public void setCalidadAcabadosBanos(String calidadAcabadosBanos) {
		this.calidadAcabadosBanos = calidadAcabadosBanos;
	}

	public String getCalidadAcabadosCocina() {
		return calidadAcabadosCocina;
	}

	public void setCalidadAcabadosCocina(String calidadAcabadosCocina) {
		this.calidadAcabadosCocina = calidadAcabadosCocina;
	}

	public String getObservacionesAcabados() {
		return observacionesAcabados;
	}

	public void setObservacionesAcabados(String observacionesAcabados) {
		this.observacionesAcabados = observacionesAcabados;
	}

}
