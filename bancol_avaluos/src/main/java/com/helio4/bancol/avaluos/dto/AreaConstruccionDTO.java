package com.helio4.bancol.avaluos.dto;

import java.math.BigDecimal;

import com.helio4.bancol.avaluos.dto.AvaluoDTO.MBR;

public class AreaConstruccionDTO extends AreaDTO {

	private Integer tipoDeConstruccion;
	private Integer claseDeInmueble;
	private Integer numeroDeSotanos;
	private String observacionesNumeroDeSotanos;
	private Integer usoDelInmueble;
	private Integer estadoDeLaConstruccion;
	private String observacionesEstadoDeLaConstruccion;
	private BigDecimal estadoDeConservacion;
	private Integer estadoConservacionConstruccion;
	private String observacionesEstadoDeLaConservacion;
	private BigDecimal porcentajeAvance;
	private BigDecimal numeroDePisos;
	private String descripcionNumeroDePisos;
	private Integer estadoDeObra;
	private String descripcionEstadoDeObra;
	private Integer anoDeConstruccion;
	private String obseracionesAnoDeConstruccion;
	private BigDecimal vetustez;
	private String descripcionVetustez;
	private Integer pisos;
	private String descripcionPisos;
	private Integer estructura;
	private String descripcionEstructura;
	private Integer reparados;
	private String descripcionReparados;
	private Integer cubierta;
	private String descripcionCubierta;
	private Integer fachada;
	private String descripcionFachada;
	private Integer tipoDeFachada;
	private String descripcionTipoDeFachada;
	private Integer estructuraReforzada;
	private String descripcionEstructuraReforzada;
	private Integer danosPrevios;
	private String descripcionDanosPrevios;
	private Integer materialDeConstruccion;
	private String descripcionMaterialDeConstruccion;
	private Integer ventaneria;
	private String descripcionVentaneria;
	private Integer iluminacion;
	private String descripcionIluminacion;
	private Integer ventilacion;
	private String descripcionVentilacion;
	private Integer irregularidadPlanta;
	private String descripcionIrregularidadDePlanta;
	private Integer irregularidadAltura;
	private String descripcionIrregularidadAltura;
	private Integer salas;
	private Integer comedores;
	private Integer estudios;
	private Integer jardines;
	private Integer banoSocial;
	private Integer estarHabitacion;
	private Integer habitaciones;
	private Integer depositos;
	private Integer banosPrivados;
	private Integer cocinas;
	private Integer cuartoDeServicio;
	private Integer oficinas;
	private Integer banoServicio;
	private Integer patioInterno;
	private Integer terrazas;
	private Integer bodegas;
	private Integer zonaDeRopas;
	private Integer balcones;
	private Integer closeths;
	private Integer locales;
	private Integer zonasVerdesPrivadas;
	private String observacionesDependencias;
	private Integer estadoAcabadosPisos;
	private Integer estadoAcabadosMuros;
	private Integer estadoAcabadosTechos;
	private Integer estadoAcabadosCarpinteriaMetal;
	private Integer estadoAcabadosCarpinteriaMadera;
	private Integer estadoAcabadosBanos;
	private Integer estadoAcabadosCocina;
	private String calidadAcabadosPisos;
	private String calidadAcabadosMuros;
	private String calidadAcabadosTechos;
	private String calidadAcabadosCarpinteriaMetal;
	private String calidadAcabadosCarpinteriaMadera;
	private String calidadAcabadosBanos;
	private String calidadAcabadosCocina;
	private String observacionesAcabados;
	
	public AreaConstruccionDTO() {
	}

	public AreaConstruccionDTO(Integer tipoDeConstruccion,
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
			String observacionesAcabados, AreaDTO area, Long avaluoId) {
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

	public Integer getTipoDeConstruccion() {
		return tipoDeConstruccion;
	}

	public void setTipoDeConstruccion(Integer tipoDeConstruccion) {
		if(new Integer(0).equals(tipoDeConstruccion)){
			tipoDeConstruccion = null;
		}
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
		if(new Integer(0).equals(usoDelInmueble)){
			usoDelInmueble = null;
		}
		this.usoDelInmueble = usoDelInmueble;
	}

	public Integer getEstadoDeLaConstruccion() {
		return estadoDeLaConstruccion;
	}

	public void setEstadoDeLaConstruccion(Integer estadoDeLaConstruccion) {
		if(new Integer(0).equals(estadoDeLaConstruccion)){
			estadoDeLaConstruccion = null;
		}
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
		if(new Integer(0).equals(estadoConservacionConstruccion)){
			estadoConservacionConstruccion = null;
		}
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

	public MBR getEstadoDeObra() {
		return MBR.fromKey(estadoDeObra == null ? 0 : estadoDeObra);
	}

	public void setEstadoDeObra(MBR estadoDeObra) {
		this.estadoDeObra = estadoDeObra.getKey();
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
		if(new Integer(0).equals(pisos)){
			pisos = null;
		}
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
		if(new Integer(0).equals(estructura)){
			estructura = null;
		}
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
		if(new Integer(0).equals(reparados)){
			reparados = null;
		}
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
		if(new Integer(0).equals(cubierta)){
			cubierta = null;
		}
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
		if(new Integer(0).equals(fachada)){
			fachada = null;
		}
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
		if(new Integer(0).equals(tipoDeFachada)){
			tipoDeFachada = null;
		}
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
		if(new Integer(0).equals(estructuraReforzada)){
			estructuraReforzada = null;
		}
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
		if(new Integer(0).equals(danosPrevios)){
			danosPrevios = null;
		}
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
		if(new Integer(0).equals(materialDeConstruccion)){
			materialDeConstruccion = null;
		}
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

	public MBR getIluminacion() {
		return MBR.fromKey(iluminacion == null ? 0 : iluminacion);
	}

	public void setIluminacion(MBR iluminacion) {
		this.iluminacion = iluminacion.getKey();
	}

	public String getDescripcionIluminacion() {
		return descripcionIluminacion;
	}

	public void setDescripcionIluminacion(String descripcionIluminacion) {
		this.descripcionIluminacion = descripcionIluminacion;
	}

	public MBR getVentilacion() {
		return MBR.fromKey(ventilacion == null? 0 : ventilacion);
	}

	public void setVentilacion(MBR ventilacion) {
		this.ventilacion = ventilacion.getKey();
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
		if(new Integer(0).equals(irregularidadPlanta)){
			irregularidadPlanta = null;
		}
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
		if(new Integer(0).equals(irregularidadAltura)){
			irregularidadAltura = null;
		}
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

	public MBR getEstadoAcabadosPisos() {
		return MBR.fromKey(estadoAcabadosPisos == null ? 0 : estadoAcabadosPisos);
	}

	public void setEstadoAcabadosPisos(MBR estadoAcabadosPisos) {
		this.estadoAcabadosPisos = estadoAcabadosPisos.getKey();
	}

	public MBR getEstadoAcabadosMuros() {
		return MBR.fromKey(estadoAcabadosMuros == null ? 0 : estadoAcabadosMuros);
	}

	public void setEstadoAcabadosMuros(MBR estadoAcabadosMuros) {
		this.estadoAcabadosMuros = estadoAcabadosMuros.getKey();
	}

	public MBR getEstadoAcabadosTechos() {
		return MBR.fromKey(estadoAcabadosTechos == null ? 0 : estadoAcabadosTechos);
	}

	public void setEstadoAcabadosTechos(MBR estadoAcabadosTechos) {
		this.estadoAcabadosTechos = estadoAcabadosTechos.getKey();
	}

	public MBR getEstadoAcabadosCarpinteriaMetal() {
		return MBR.fromKey(estadoAcabadosCarpinteriaMetal == null ? 0 : estadoAcabadosCarpinteriaMetal);
	}

	public void setEstadoAcabadosCarpinteriaMetal(
			MBR estadoAcabadosCarpinteriaMetal) {
		this.estadoAcabadosCarpinteriaMetal = estadoAcabadosCarpinteriaMetal.getKey();
	}

	public MBR getEstadoAcabadosCarpinteriaMadera() {
		return MBR.fromKey(estadoAcabadosCarpinteriaMadera == null ? 0 : estadoAcabadosCarpinteriaMadera);
	}

	public void setEstadoAcabadosCarpinteriaMadera(
			MBR estadoAcabadosCarpinteriaMadera) {
		this.estadoAcabadosCarpinteriaMadera = estadoAcabadosCarpinteriaMadera.getKey();
	}

	public MBR getEstadoAcabadosBanos() {
		return MBR.fromKey(estadoAcabadosBanos == null ? 0 : estadoAcabadosBanos);
	}

	public void setEstadoAcabadosBanos(MBR estadoAcabadosBanos) {
		this.estadoAcabadosBanos = estadoAcabadosBanos.getKey();
	}

	public MBR getEstadoAcabadosCocina() {
		return MBR.fromKey(estadoAcabadosCocina == null ? 0 : estadoAcabadosCocina);
	}

	public void setEstadoAcabadosCocina(MBR estadoAcabadosCocina) {
		this.estadoAcabadosCocina = estadoAcabadosCocina.getKey();
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
