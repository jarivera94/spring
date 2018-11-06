package com.helio4.bancol.avaluos.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import com.helio4.bancol.avaluos.dto.AvaluoDTO.MBR;

public class FormatoInformeComercialDTO extends FormatoInformeDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String vereda;
	private String nombrePredio;
	private String localizacion;
	private String destinatarioDeLaValuacion;
	private String observacionesTipoInmueble;
	private Integer usoActualDelInmueble;
	private String observacionesUsoActualDelInmueble;
	private Integer usoProyectadoDelInmueble;
	private String observacionesUsoProyectadoDelInmueble;
	private Integer periodoDeMercadeo;
	private String observacionesPeriodoDeMercadeo;
	private String documentosConsultados;
	private Date fechaAporteDeDocumentos;
	private String otrosDocumentos;
	private String observacionesDeTitulacion;
	private String descripcionGeneralMunicipioOSector;
	private String ubicacionDelSector;
	private String limiteNorte;
	private String limiteSur;
	private String limiteOriente;
	private String limiteOccidente;
	private String barriosCercanos;
	private String usoPredominanteDelSector;
	private String comercializacion;
	private Integer tiempoEsperadoDeComercializacion;
	private Integer estrato;
	private String caracteristicasSocioEconomicas;
	private String ordenPublico;
	private String observacionesViasDeAcceso;
	private String equipamientoDeRedVial;
	private String estadoDeConservacion;
	private String usoDelSuelo;
	private String infraestructuraUrbanistica;
	private String infraestructuraDotacional;
	private String amoblamientoUrbano;
	private String andenesYSardineles;
	private String zonasVerdes;
	private String topografia;
	private String alumbradoPublico;
	private Boolean alcantarillado;
	private Boolean acueducto;
	private Boolean energia;
	private Boolean gas;
	private Boolean telefono;
	private String observacionesServiciosPublicos;
	private String transportePublico;
	private String frecuencia;
	private String cubrimiento;
	private Integer perspectivasDeValorizacion;
	private String observacionesPerspectivasDeValorizacion;
	private String normatividad;
	private String descripcionDelInmueble;
	private String localizacionEspecifacaDelInmueble;
	private Integer distaciaPartiendoDelCascoUrbano;
	private String linderoNorte;
	private String linderoSur;
	private String linderoOriente;
	private String linderoOccidente;
	private String fuenteLinderos;
	private String descripcionViasDeAccesoInternas;
	private Boolean frenteSobreLaVia;
	private BigDecimal metros;
	private String cercasPerimedales;
	private Boolean servidumbres;
	private Boolean propiedadHorizontal;
	private String descripcionDeLaPropiedadHorizontal;
	private Integer vidaUtil;
	private Integer vidaDelInmueble;
	private Integer vidaRemanente;
	private String justificacionVidaUtil;
	private Integer estructuraEdificio;
	private String descripcionEstructura;
	private Integer placasDeEntrepiso;
	private String observacionesPlacasEntrePiso;
	private Integer fachada;
	private String descripcionFachada;
	private Integer cubierta;
	private String descripcionCubierta;
	private Integer escaleras;
	private String descripcionEscaleras;
	private String estructura;
	private String categoriaAcabados;
	private String distribucion;
	private String estadoDeConservacionSectorRural;
	private String equipamientoComunal;
	private String descripcionDelSuelo;
	private Integer relieve;
	private Boolean erosion;
	private BigDecimal temperatura;
	private Long pisoTermico;
	private BigDecimal alturaMsnm;
	private BigDecimal precipitacionAnualMm;
	private Integer formaGeometrica;
	private Boolean regular;
	private Boolean irregular;
	private String inundabilidad;
	private String distribucionDeLluvias;
	private String pedregosidad;
	private String fertilidadNatural;
	private BigDecimal nivelFreatico;
	private String claseAgrologica;
	private BigDecimal valorPotencial;
	private String capaVegetal;
	private Integer condicionesAgronomicas;
	private Integer condicionesAgrologicas;
	private String descripcionCultivos;
	private String recursosHidricos;
	private String irrigacion;
	private String restriccionesFisicas;
	private String otrosCutivos;
	private Boolean bosques;
	private Boolean cultivosComerciales;
	private Boolean deProteccion;
	private String impactoAmbiental;
	private BigDecimal frente;
	private BigDecimal fondo;
	private BigDecimal relacionFrenteFondo;
	private String observacionDistribucionDeAreasNoConstruidas;
	private Boolean construccion;
	private Integer vidaUtilConstruccion;
	private Integer vidaDelInmuebleConstruccion;
	private Integer vidaRemanenteConstruccion;
	private String observacionesEdad;
	private Boolean remodelado;
	private Boolean alcantarilladoConstruccion;
	private Boolean acueductoConstruccion;
	private Boolean energiaConstruccion;
	private Boolean gasConstruccion;
	private Boolean telefonoConstruccion;
	private String observacionesServiciosPublicosConstruccion;
	private BigDecimal frenteConstruido;
	private BigDecimal fondoConstruido;
	private BigDecimal relacionFrenteFondoConstruido;
	private BigDecimal areaPrivadaConstruido;
	private BigDecimal coeficienteDeCopropiedadConstruido;
	private BigDecimal areaTotalConstruida;
	private String fuenteConstruido;
	private String observacionesDistribucionAreasConstruidas;
	private String problemasDeEstabilidadDeSuelos;
	private String impactoAmbientalYCondicionesDeSalubridad;
	private String servidumbresCesionesYAfectacionesViales;
	private String seguridad;
	private String problematicasSocioEconomicas;
	private String descripcionDeLasHipotesisEspecialesInusualesOExtraordinarias;
	private String consideracionesGenerales;
	private String consideracionesGeneralesDeLocalizacion;
	private String consideracionesGeneralesDelSector;
	private String consideracionesGeneralesDeLaCapacidadDeTerreno;
	private String consideracionesGeneralesDeLaInfraestructuraVialYCapacidadDeAgua;
	private String consideracionesGeneralesDeLaConstruccion;
	private String casosEspecialesDeAfectacion;
	private String investigacionEconomica;
	private String comportamientoDeOfertaYDemanda;
	private Integer conceptoDeGarantia;
	private String observacionesConceptoGarantia;
	private BigDecimal valorComercialDelInmueble;
	private BigDecimal valorIntegralSobreElTerreno;
	private BigDecimal valorIntegralSobreElConstruccion;
	private BigDecimal porcentajeFinal;
	private BigDecimal areaConstruida;
	private BigDecimal areaInfraestructura;
	private BigDecimal areaCultivosAvaluables;
	private Set<InmuebleDTO> inmuebles;
	private Set<ExplotacionEconomicaDTO> explotacionesEconomicas;
	private Set<ServidumbreDTO> servidumbrez;
	private Set<ViaAccesoDTO> viasAcceso;
	private Set<MetodoValuacionDTO> metodosValuacion;
	private Set<AreaConstruccionDTO> areasConstruccion;
	
	public FormatoInformeComercialDTO(Long avaluoId) {
		super(avaluoId);
	}

	public String getVereda() {
		return vereda;
	}

	public void setVereda(String vereda) {
		this.vereda = vereda;
	}

	public String getNombrePredio() {
		return nombrePredio;
	}

	public void setNombrePredio(String nombrePredio) {
		this.nombrePredio = nombrePredio;
	}

	public String getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	public String getDestinatarioDeLaValuacion() {
		return destinatarioDeLaValuacion;
	}

	public void setDestinatarioDeLaValuacion(String destinatarioDeLaValuacion) {
		this.destinatarioDeLaValuacion = destinatarioDeLaValuacion;
	}

	public String getObservacionesTipoInmueble() {
		return observacionesTipoInmueble;
	}

	public void setObservacionesTipoInmueble(
			String observacionesTipoInmueble) {
		this.observacionesTipoInmueble = observacionesTipoInmueble;
	}

	public Integer getUsoActualDelInmueble() {
		return usoActualDelInmueble;
	}

	public void setUsoActualDelInmueble(Integer usoActualDelInmueble) {
		if(new Integer(0).equals(usoActualDelInmueble)){
			usoActualDelInmueble = null;
		}
		this.usoActualDelInmueble = usoActualDelInmueble;
	}

	public String getObservacionesUsoActualDelInmueble() {
		return observacionesUsoActualDelInmueble;
	}

	public void setObservacionesUsoActualDelInmueble(
			String observacionesUsoActualDelInmueble) {
		this.observacionesUsoActualDelInmueble = observacionesUsoActualDelInmueble;
	}

	public Integer getUsoProyectadoDelInmueble() {
		return usoProyectadoDelInmueble;
	}

	public void setUsoProyectadoDelInmueble(Integer usoProyectadoDelInmueble) {
		if(new Integer(0).equals(usoProyectadoDelInmueble)){
			usoProyectadoDelInmueble = null;
		}
		this.usoProyectadoDelInmueble = usoProyectadoDelInmueble;
	}

	public String getObservacionesUsoProyectadoDelInmueble() {
		return observacionesUsoProyectadoDelInmueble;
	}

	public void setObservacionesUsoProyectadoDelInmueble(
			String observacionesUsoProyectadoDelInmueble) {
		this.observacionesUsoProyectadoDelInmueble = observacionesUsoProyectadoDelInmueble;
	}

	public Integer getPeriodoDeMercadeo() {
		return periodoDeMercadeo;
	}

	public void setPeriodoDeMercadeo(Integer periodoDeMercadeo) {
		this.periodoDeMercadeo = periodoDeMercadeo;
	}

	public String getObservacionesPeriodoDeMercadeo() {
		return observacionesPeriodoDeMercadeo;
	}

	public void setObservacionesPeriodoDeMercadeo(
			String observacionesPeriodoDeMercadeo) {
		this.observacionesPeriodoDeMercadeo = observacionesPeriodoDeMercadeo;
	}

	public String getDocumentosConsultados() {
		return documentosConsultados;
	}

	public void setDocumentosConsultados(String documentosConsultados) {
		this.documentosConsultados = documentosConsultados;
	}

	public Date getFechaAporteDeDocumentos() {
		return fechaAporteDeDocumentos;
	}

	public void setFechaAporteDeDocumentos(Date fechaAporteDeDocumentos) {
		this.fechaAporteDeDocumentos = fechaAporteDeDocumentos;
	}

	public String getOtrosDocumentos() {
		return otrosDocumentos;
	}

	public void setOtrosDocumentos(String otrosDocumentos) {
		this.otrosDocumentos = otrosDocumentos;
	}

	public String getObservacionesDeTitulacion() {
		return observacionesDeTitulacion;
	}

	public void setObservacionesDeTitulacion(String observacionesDeTitulacion) {
		this.observacionesDeTitulacion = observacionesDeTitulacion;
	}

	public String getDescripcionGeneralMunicipioOSector() {
		return descripcionGeneralMunicipioOSector;
	}

	public void setDescripcionGeneralMunicipioOSector(
			String descripcionGeneralMunicipioOSector) {
		this.descripcionGeneralMunicipioOSector = descripcionGeneralMunicipioOSector;
	}

	public String getUbicacionDelSector() {
		return ubicacionDelSector;
	}

	public void setUbicacionDelSector(String ubicacionDelSector) {
		this.ubicacionDelSector = ubicacionDelSector;
	}

	public String getLimiteNorte() {
		return limiteNorte;
	}

	public void setLimiteNorte(String limiteNorte) {
		this.limiteNorte = limiteNorte;
	}

	public String getLimiteSur() {
		return limiteSur;
	}

	public void setLimiteSur(String limiteSur) {
		this.limiteSur = limiteSur;
	}

	public String getLimiteOriente() {
		return limiteOriente;
	}

	public void setLimiteOriente(String limiteOriente) {
		this.limiteOriente = limiteOriente;
	}

	public String getLimiteOccidente() {
		return limiteOccidente;
	}

	public void setLimiteOccidente(String limiteOccidente) {
		this.limiteOccidente = limiteOccidente;
	}

	public String getBarriosCercanos() {
		return barriosCercanos;
	}

	public void setBarriosCercanos(String barriosCercanos) {
		this.barriosCercanos = barriosCercanos;
	}

	public String getUsoPredominanteDelSector() {
		return usoPredominanteDelSector;
	}

	public void setUsoPredominanteDelSector(String usoPredominanteDelSector) {
		this.usoPredominanteDelSector = usoPredominanteDelSector;
	}

	public String getComercializacion() {
		return comercializacion;
	}

	public void setComercializacion(String comercializacion) {
		this.comercializacion = comercializacion;
	}

	public Integer getTiempoEsperadoDeComercializacion() {
		return tiempoEsperadoDeComercializacion;
	}

	public void setTiempoEsperadoDeComercializacion(
			Integer tiempoEsperadoDeComercializacion) {
		this.tiempoEsperadoDeComercializacion = tiempoEsperadoDeComercializacion;
	}

	public Integer getEstrato() {
		return estrato;
	}

	public void setEstrato(Integer estrato) {
		if(new Integer(0).equals(estrato)){
			estrato = null;
		}
		this.estrato = estrato;
	}

	public String getCaracteristicasSocioEconomicas() {
		return caracteristicasSocioEconomicas;
	}

	public void setCaracteristicasSocioEconomicas(
			String caracteristicasSocioEconomicas) {
		this.caracteristicasSocioEconomicas = caracteristicasSocioEconomicas;
	}

	public String getOrdenPublico() {
		return ordenPublico;
	}

	public void setOrdenPublico(String ordenPublico) {
		this.ordenPublico = ordenPublico;
	}

	public String getObservacionesViasDeAcceso() {
		return observacionesViasDeAcceso;
	}

	public void setObservacionesViasDeAcceso(String observacionesViasDeAcceso) {
		this.observacionesViasDeAcceso = observacionesViasDeAcceso;
	}

	public String getEquipamientoDeRedVial() {
		return equipamientoDeRedVial;
	}

	public void setEquipamientoDeRedVial(String equipamientoDeRedVial) {
		this.equipamientoDeRedVial = equipamientoDeRedVial;
	}

	public String getEstadoDeConservacion() {
		return estadoDeConservacion;
	}

	public void setEstadoDeConservacion(String estadoDeConservacion) {
		this.estadoDeConservacion = estadoDeConservacion;
	}

	public String getUsoDelSuelo() {
		return usoDelSuelo;
	}

	public void setUsoDelSuelo(String usoDelSuelo) {
		this.usoDelSuelo = usoDelSuelo;
	}

	public String getInfraestructuraUrbanistica() {
		return infraestructuraUrbanistica;
	}

	public void setInfraestructuraUrbanistica(String infraestructuraUrbanistica) {
		this.infraestructuraUrbanistica = infraestructuraUrbanistica;
	}

	public String getInfraestructuraDotacional() {
		return infraestructuraDotacional;
	}

	public void setInfraestructuraDotacional(String infraestructuraDotacional) {
		this.infraestructuraDotacional = infraestructuraDotacional;
	}

	public String getAmoblamientoUrbano() {
		return amoblamientoUrbano;
	}

	public void setAmoblamientoUrbano(String amoblamientoUrbano) {
		this.amoblamientoUrbano = amoblamientoUrbano;
	}

	public String getAndenesYSardineles() {
		return andenesYSardineles;
	}

	public void setAndenesYSardineles(String andenesYSardineles) {
		this.andenesYSardineles = andenesYSardineles;
	}

	public String getZonasVerdes() {
		return zonasVerdes;
	}

	public void setZonasVerdes(String zonasVerdes) {
		this.zonasVerdes = zonasVerdes;
	}

	public String getTopografia() {
		return topografia;
	}

	public void setTopografia(String topografia) {
		this.topografia = topografia;
	}

	public String getAlumbradoPublico() {
		return alumbradoPublico;
	}

	public void setAlumbradoPublico(String alumbradoPublico) {
		this.alumbradoPublico = alumbradoPublico;
	}

	public Boolean getAlcantarillado() {
		return alcantarillado;
	}

	public void setAlcantarillado(Boolean alcantarillado) {
		this.alcantarillado = alcantarillado;
	}

	public Boolean getAcueducto() {
		return acueducto;
	}

	public void setAcueducto(Boolean acueducto) {
		this.acueducto = acueducto;
	}

	public Boolean getEnergia() {
		return energia;
	}

	public void setEnergia(Boolean energia) {
		this.energia = energia;
	}

	public Boolean getGas() {
		return gas;
	}

	public void setGas(Boolean gas) {
		this.gas = gas;
	}

	public Boolean getTelefono() {
		return telefono;
	}

	public void setTelefono(Boolean telefono) {
		this.telefono = telefono;
	}

	public String getObservacionesServiciosPublicos() {
		return observacionesServiciosPublicos;
	}

	public void setObservacionesServiciosPublicos(
			String observacionesServiciosPublicos) {
		this.observacionesServiciosPublicos = observacionesServiciosPublicos;
	}

	public String getTransportePublico() {
		return transportePublico;
	}

	public void setTransportePublico(String transportePublico) {
		this.transportePublico = transportePublico;
	}

	public String getFrecuencia() {
		return frecuencia;
	}

	public void setFrecuencia(String frecuencia) {
		this.frecuencia = frecuencia;
	}

	public String getCubrimiento() {
		return cubrimiento;
	}

	public void setCubrimiento(String cubrimiento) {
		this.cubrimiento = cubrimiento;
	}

	public MBR getPerspectivasDeValorizacion() {
		return MBR.fromKey(perspectivasDeValorizacion == null ? 0 : perspectivasDeValorizacion);
	}

	public void setPerspectivasDeValorizacion(MBR perspectivasDeValorizacion) {
		this.perspectivasDeValorizacion = perspectivasDeValorizacion.getKey();
	}

	public String getObservacionesPerspectivasDeValorizacion() {
		return observacionesPerspectivasDeValorizacion;
	}

	public void setObservacionesPerspectivasDeValorizacion(
			String observacionesPerspectivasDeValorizacion) {
		this.observacionesPerspectivasDeValorizacion = observacionesPerspectivasDeValorizacion;
	}

	public String getNormatividad() {
		return normatividad;
	}

	public void setNormatividad(String normatividad) {
		this.normatividad = normatividad;
	}

	public String getDescripcionDelInmueble() {
		return descripcionDelInmueble;
	}

	public void setDescripcionDelInmueble(String descripcionDelInmueble) {
		this.descripcionDelInmueble = descripcionDelInmueble;
	}

	public String getLocalizacionEspecifacaDelInmueble() {
		return localizacionEspecifacaDelInmueble;
	}

	public void setLocalizacionEspecifacaDelInmueble(
			String localizacionEspecifacaDelInmueble) {
		this.localizacionEspecifacaDelInmueble = localizacionEspecifacaDelInmueble;
	}

	public Integer getDistaciaPartiendoDelCascoUrbano() {
		return distaciaPartiendoDelCascoUrbano;
	}

	public void setDistaciaPartiendoDelCascoUrbano(
			Integer distaciaPartiendoDelCascoUrbano) {
		this.distaciaPartiendoDelCascoUrbano = distaciaPartiendoDelCascoUrbano;
	}

	public String getLinderoNorte() {
		return linderoNorte;
	}

	public void setLinderoNorte(String linderoNorte) {
		this.linderoNorte = linderoNorte;
	}

	public String getLinderoSur() {
		return linderoSur;
	}

	public void setLinderoSur(String linderoSur) {
		this.linderoSur = linderoSur;
	}

	public String getLinderoOriente() {
		return linderoOriente;
	}

	public void setLinderoOriente(String linderoOriente) {
		this.linderoOriente = linderoOriente;
	}

	public String getLinderoOccidente() {
		return linderoOccidente;
	}

	public void setLinderoOccidente(String linderoOccidente) {
		this.linderoOccidente = linderoOccidente;
	}

	public String getFuenteLinderos() {
		return fuenteLinderos;
	}

	public void setFuenteLinderos(String fuenteLinderos) {
		this.fuenteLinderos = fuenteLinderos;
	}

	public String getDescripcionViasDeAccesoInternas() {
		return descripcionViasDeAccesoInternas;
	}

	public void setDescripcionViasDeAccesoInternas(
			String descripcionViasDeAccesoInternas) {
		this.descripcionViasDeAccesoInternas = descripcionViasDeAccesoInternas;
	}

	public Boolean getFrenteSobreLaVia() {
		return frenteSobreLaVia;
	}

	public void setFrenteSobreLaVia(Boolean frenteSobreLaVia) {
		this.frenteSobreLaVia = frenteSobreLaVia;
	}

	public BigDecimal getMetros() {
		return metros;
	}

	public void setMetros(BigDecimal metros) {
		this.metros = metros;
	}

	public String getCercasPerimedales() {
		return cercasPerimedales;
	}

	public void setCercasPerimedales(String cercasPerimedales) {
		this.cercasPerimedales = cercasPerimedales;
	}

	public Boolean getServidumbres() {
		return servidumbres;
	}

	public void setServidumbres(Boolean servidumbres) {
		this.servidumbres = servidumbres;
	}

	public Boolean getPropiedadHorizontal() {
		return propiedadHorizontal;
	}

	public void setPropiedadHorizontal(Boolean propiedadHorizontal) {
		this.propiedadHorizontal = propiedadHorizontal;
	}

	public String getDescripcionDeLaPropiedadHorizontal() {
		return descripcionDeLaPropiedadHorizontal;
	}

	public void setDescripcionDeLaPropiedadHorizontal(
			String descripcionDeLaPropiedadHorizontal) {
		this.descripcionDeLaPropiedadHorizontal = descripcionDeLaPropiedadHorizontal;
	}

	public Integer getVidaUtil() {
		return vidaUtil;
	}

	public void setVidaUtil(Integer vidaUtil) {
		this.vidaUtil = vidaUtil;
	}

	public Integer getVidaDelInmueble() {
		return vidaDelInmueble;
	}

	public void setVidaDelInmueble(Integer vidaDelInmueble) {
		this.vidaDelInmueble = vidaDelInmueble;
	}

	public Integer getVidaRemanente() {
		return vidaRemanente;
	}

	public void setVidaRemanente(Integer vidaRemanente) {
		this.vidaRemanente = vidaRemanente;
	}

	public String getJustificacionVidaUtil() {
		return justificacionVidaUtil;
	}

	public void setJustificacionVidaUtil(String justificacionVidaUtil) {
		this.justificacionVidaUtil = justificacionVidaUtil;
	}

	public Integer getEstructuraEdificio() {
		return estructuraEdificio;
	}

	public void setEstructuraEdificio(Integer estructuraEdificio) {
		this.estructuraEdificio = estructuraEdificio;
	}

	public String getDescripcionEstructura() {
		return descripcionEstructura;
	}

	public void setDescripcionEstructura(String descripcionEstructura) {
		this.descripcionEstructura = descripcionEstructura;
	}

	public Integer getPlacasDeEntrepiso() {
		return placasDeEntrepiso;
	}

	public void setPlacasDeEntrepiso(Integer placasDeEntrepiso) {
		this.placasDeEntrepiso = placasDeEntrepiso;
	}

	public String getObservacionesPlacasEntrePiso() {
		return observacionesPlacasEntrePiso;
	}

	public void setObservacionesPlacasEntrePiso(String observacionesPlacasEntrePiso) {
		this.observacionesPlacasEntrePiso = observacionesPlacasEntrePiso;
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

	public Integer getEscaleras() {
		return escaleras;
	}

	public void setEscaleras(Integer escaleras) {
		this.escaleras = escaleras;
	}

	public String getDescripcionEscaleras() {
		return descripcionEscaleras;
	}

	public void setDescripcionEscaleras(String descripcionEscaleras) {
		this.descripcionEscaleras = descripcionEscaleras;
	}

	public String getEstructura() {
		return estructura;
	}

	public void setEstructura(String estructura) {
		this.estructura = estructura;
	}

	public String getCategoriaAcabados() {
		return categoriaAcabados;
	}

	public void setCategoriaAcabados(String categoriaAcabados) {
		this.categoriaAcabados = categoriaAcabados;
	}

	public String getDistribucion() {
		return distribucion;
	}

	public void setDistribucion(String distribucion) {
		this.distribucion = distribucion;
	}

	public String getEstadoDeConservacionSectorRural() {
		return estadoDeConservacionSectorRural;
	}

	public void setEstadoDeConservacionSectorRural(
			String estadoDeConservacionSectorRural) {
		this.estadoDeConservacionSectorRural = estadoDeConservacionSectorRural;
	}

	public String getEquipamientoComunal() {
		return equipamientoComunal;
	}

	public void setEquipamientoComunal(String equipamientoComunal) {
		this.equipamientoComunal = equipamientoComunal;
	}

	public String getDescripcionDelSuelo() {
		return descripcionDelSuelo;
	}

	public void setDescripcionDelSuelo(String descripcionDelSuelo) {
		this.descripcionDelSuelo = descripcionDelSuelo;
	}

	public Integer getRelieve() {
		return relieve;
	}

	public void setRelieve(Integer relieve) {
		this.relieve = relieve;
	}

	public Boolean getErosion() {
		return erosion;
	}

	public void setErosion(Boolean erosion) {
		this.erosion = erosion;
	}

	public BigDecimal getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(BigDecimal temperatura) {
		this.temperatura = temperatura;
	}

	public Long getPisoTermico() {
		return pisoTermico;
	}

	public void setPisoTermico(Long pisoTermico) {
		this.pisoTermico = pisoTermico;
	}

	public BigDecimal getAlturaMsnm() {
		return alturaMsnm;
	}

	public void setAlturaMsnm(BigDecimal alturaMsnm) {
		this.alturaMsnm = alturaMsnm;
	}

	public BigDecimal getPrecipitacionAnualMm() {
		return precipitacionAnualMm;
	}

	public void setPrecipitacionAnualMm(BigDecimal precipitacionAnualMm) {
		this.precipitacionAnualMm = precipitacionAnualMm;
	}

	public Integer getFormaGeometrica() {
		return formaGeometrica;
	}

	public void setFormaGeometrica(Integer formaGeometrica) {
		if(new Integer(0).equals(formaGeometrica)){
			formaGeometrica = null;
		}
		this.formaGeometrica = formaGeometrica;
	}

	public Boolean getRegular() {
		return regular;
	}

	public void setRegular(Boolean regular) {
		this.regular = regular;
	}

	public Boolean getIrregular() {
		return irregular;
	}

	public void setIrregular(Boolean irregular) {
		this.irregular = irregular;
	}

	public String getInundabilidad() {
		return inundabilidad;
	}

	public void setInundabilidad(String inundabilidad) {
		this.inundabilidad = inundabilidad;
	}

	public String getDistribucionDeLluvias() {
		return distribucionDeLluvias;
	}

	public void setDistribucionDeLluvias(String distribucionDeLluvias) {
		this.distribucionDeLluvias = distribucionDeLluvias;
	}

	public String getPedregosidad() {
		return pedregosidad;
	}

	public void setPedregosidad(String pedregosidad) {
		this.pedregosidad = pedregosidad;
	}

	public String getFertilidadNatural() {
		return fertilidadNatural;
	}

	public void setFertilidadNatural(String fertilidadNatural) {
		this.fertilidadNatural = fertilidadNatural;
	}

	public BigDecimal getNivelFreatico() {
		return nivelFreatico;
	}

	public void setNivelFreatico(BigDecimal nivelFreatico) {
		this.nivelFreatico = nivelFreatico;
	}

	public String getClaseAgrologica() {
		return claseAgrologica;
	}

	public void setClaseAgrologica(String claseAgrologica) {
		this.claseAgrologica = claseAgrologica;
	}

	public BigDecimal getValorPotencial() {
		return valorPotencial;
	}

	public void setValorPotencial(BigDecimal valorPotencial) {
		this.valorPotencial = valorPotencial;
	}

	public String getCapaVegetal() {
		return capaVegetal;
	}

	public void setCapaVegetal(String capaVegetal) {
		this.capaVegetal = capaVegetal;
	}

	public Integer getCondicionesAgronomicas() {
		return condicionesAgronomicas;
	}

	public void setCondicionesAgronomicas(Integer condicionesAgronomicas) {
		if(new Integer(0).equals(condicionesAgronomicas)){
			condicionesAgronomicas = null;
		}
		this.condicionesAgronomicas = condicionesAgronomicas;
	}

	public Integer getCondicionesAgrologicas() {
		return condicionesAgrologicas;
	}

	public void setCondicionesAgrologicas(Integer condicionesAgrologicas) {
		if(new Integer(0).equals(condicionesAgrologicas)){
			condicionesAgrologicas = null;
		}
		this.condicionesAgrologicas = condicionesAgrologicas;
	}

	public String getDescripcionCultivos() {
		return descripcionCultivos;
	}

	public void setDescripcionCultivos(String descripcionCultivos) {
		this.descripcionCultivos = descripcionCultivos;
	}

	public String getRecursosHidricos() {
		return recursosHidricos;
	}

	public void setRecursosHidricos(String recursosHidricos) {
		this.recursosHidricos = recursosHidricos;
	}

	public String getIrrigacion() {
		return irrigacion;
	}

	public void setIrrigacion(String irrigacion) {
		this.irrigacion = irrigacion;
	}

	public String getRestriccionesFisicas() {
		return restriccionesFisicas;
	}

	public void setRestriccionesFisicas(String restriccionesFisicas) {
		this.restriccionesFisicas = restriccionesFisicas;
	}

	public String getOtrosCutivos() {
		return otrosCutivos;
	}

	public void setOtrosCutivos(String otrosCutivos) {
		this.otrosCutivos = otrosCutivos;
	}

	public Boolean getBosques() {
		return bosques;
	}

	public void setBosques(Boolean bosques) {
		this.bosques = bosques;
	}

	public Boolean getCultivosComerciales() {
		return cultivosComerciales;
	}

	public void setCultivosComerciales(Boolean cultivosComerciales) {
		this.cultivosComerciales = cultivosComerciales;
	}

	public Boolean getDeProteccion() {
		return deProteccion;
	}

	public void setDeProteccion(Boolean deProteccion) {
		this.deProteccion = deProteccion;
	}

	public String getImpactoAmbiental() {
		return impactoAmbiental;
	}

	public void setImpactoAmbiental(String impactoAmbiental) {
		this.impactoAmbiental = impactoAmbiental;
	}

	public BigDecimal getFrente() {
		return frente;
	}

	public void setFrente(BigDecimal frente) {
		this.frente = frente;
	}

	public BigDecimal getFondo() {
		return fondo;
	}

	public void setFondo(BigDecimal fondo) {
		this.fondo = fondo;
	}

	public BigDecimal getRelacionFrenteFondo() {
		return relacionFrenteFondo;
	}

	public void setRelacionFrenteFondo(BigDecimal relacionFrenteFondo) {
		this.relacionFrenteFondo = relacionFrenteFondo;
	}

	public String getObservacionDistribucionDeAreasNoConstruidas() {
		return observacionDistribucionDeAreasNoConstruidas;
	}

	public void setObservacionDistribucionDeAreasNoConstruidas(
			String observacionDistribucionDeAreasNoConstruidas) {
		this.observacionDistribucionDeAreasNoConstruidas = observacionDistribucionDeAreasNoConstruidas;
	}

	public Boolean getConstruccion() {
		return construccion;
	}

	public void setConstruccion(Boolean construccion) {
		this.construccion = construccion;
	}

	public Integer getVidaUtilConstruccion() {
		return vidaUtilConstruccion;
	}

	public void setVidaUtilConstruccion(Integer vidaUtilConstruccion) {
		this.vidaUtilConstruccion = vidaUtilConstruccion;
	}

	public Integer getVidaDelInmuebleConstruccion() {
		return vidaDelInmuebleConstruccion;
	}

	public void setVidaDelInmuebleConstruccion(Integer vidaDelInmuebleConstruccion) {
		this.vidaDelInmuebleConstruccion = vidaDelInmuebleConstruccion;
	}

	public Integer getVidaRemanenteConstruccion() {
		return vidaRemanenteConstruccion;
	}

	public void setVidaRemanenteConstruccion(Integer vidaRemanenteConstruccion) {
		this.vidaRemanenteConstruccion = vidaRemanenteConstruccion;
	}

	public String getObservacionesEdad() {
		return observacionesEdad;
	}

	public void setObservacionesEdad(String observacionesEdad) {
		this.observacionesEdad = observacionesEdad;
	}

	public Boolean getRemodelado() {
		return remodelado;
	}

	public void setRemodelado(Boolean remodelado) {
		this.remodelado = remodelado;
	}

	public Boolean getAlcantarilladoConstruccion() {
		return alcantarilladoConstruccion;
	}

	public void setAlcantarilladoConstruccion(Boolean alcantarilladoConstruccion) {
		this.alcantarilladoConstruccion = alcantarilladoConstruccion;
	}

	public Boolean getAcueductoConstruccion() {
		return acueductoConstruccion;
	}

	public void setAcueductoConstruccion(Boolean acueductoConstruccion) {
		this.acueductoConstruccion = acueductoConstruccion;
	}

	public Boolean getEnergiaConstruccion() {
		return energiaConstruccion;
	}

	public void setEnergiaConstruccion(Boolean energiaConstruccion) {
		this.energiaConstruccion = energiaConstruccion;
	}

	public Boolean getGasConstruccion() {
		return gasConstruccion;
	}

	public void setGasConstruccion(Boolean gasConstruccion) {
		this.gasConstruccion = gasConstruccion;
	}

	public Boolean getTelefonoConstruccion() {
		return telefonoConstruccion;
	}

	public void setTelefonoConstruccion(Boolean telefonoConstruccion) {
		this.telefonoConstruccion = telefonoConstruccion;
	}

	public String getObservacionesServiciosPublicosConstruccion() {
		return observacionesServiciosPublicosConstruccion;
	}

	public void setObservacionesServiciosPublicosConstruccion(
			String observacionesServiciosPublicosConstruccion) {
		this.observacionesServiciosPublicosConstruccion = observacionesServiciosPublicosConstruccion;
	}

	public BigDecimal getFrenteConstruido() {
		return frenteConstruido;
	}

	public void setFrenteConstruido(BigDecimal frenteConstruido) {
		this.frenteConstruido = frenteConstruido;
	}

	public BigDecimal getFondoConstruido() {
		return fondoConstruido;
	}

	public void setFondoConstruido(BigDecimal fondoConstruido) {
		this.fondoConstruido = fondoConstruido;
	}

	public BigDecimal getRelacionFrenteFondoConstruido() {
		return relacionFrenteFondoConstruido;
	}

	public void setRelacionFrenteFondoConstruido(
			BigDecimal relacionFrenteFondoConstruido) {
		this.relacionFrenteFondoConstruido = relacionFrenteFondoConstruido;
	}

	public BigDecimal getAreaPrivadaConstruido() {
		return areaPrivadaConstruido;
	}

	public void setAreaPrivadaConstruido(BigDecimal areaPrivadaConstruido) {
		this.areaPrivadaConstruido = areaPrivadaConstruido;
	}

	public BigDecimal getCoeficienteDeCopropiedadConstruido() {
		return coeficienteDeCopropiedadConstruido;
	}

	public void setCoeficienteDeCopropiedadConstruido(
			BigDecimal coeficienteDeCopropiedadConstruido) {
		this.coeficienteDeCopropiedadConstruido = coeficienteDeCopropiedadConstruido;
	}

	public BigDecimal getAreaTotalConstruida() {
		return areaTotalConstruida;
	}

	public void setAreaTotalConstruida(BigDecimal areaTotalConstruida) {
		this.areaTotalConstruida = areaTotalConstruida;
	}

	public String getFuenteConstruido() {
		return fuenteConstruido;
	}

	public void setFuenteConstruido(String fuenteConstruido) {
		this.fuenteConstruido = fuenteConstruido;
	}

	public String getObservacionesDistribucionAreasConstruidas() {
		return observacionesDistribucionAreasConstruidas;
	}

	public void setObservacionesDistribucionAreasConstruidas(
			String observacionesDistribucionAreasConstruidas) {
		this.observacionesDistribucionAreasConstruidas = observacionesDistribucionAreasConstruidas;
	}

	public String getProblemasDeEstabilidadDeSuelos() {
		return problemasDeEstabilidadDeSuelos;
	}

	public void setProblemasDeEstabilidadDeSuelos(
			String problemasDeEstabilidadDeSuelos) {
		this.problemasDeEstabilidadDeSuelos = problemasDeEstabilidadDeSuelos;
	}

	public String getImpactoAmbientalYCondicionesDeSalubridad() {
		return impactoAmbientalYCondicionesDeSalubridad;
	}

	public void setImpactoAmbientalYCondicionesDeSalubridad(
			String impactoAmbientalYCondicionesDeSalubridad) {
		this.impactoAmbientalYCondicionesDeSalubridad = impactoAmbientalYCondicionesDeSalubridad;
	}

	public String getServidumbresCesionesYAfectacionesViales() {
		return servidumbresCesionesYAfectacionesViales;
	}

	public void setServidumbresCesionesYAfectacionesViales(
			String servidumbresCesionesYAfectacionesViales) {
		this.servidumbresCesionesYAfectacionesViales = servidumbresCesionesYAfectacionesViales;
	}

	public String getSeguridad() {
		return seguridad;
	}

	public void setSeguridad(String seguridad) {
		this.seguridad = seguridad;
	}

	public String getProblematicasSocioEconomicas() {
		return problematicasSocioEconomicas;
	}

	public void setProblematicasSocioEconomicas(String problematicasSocioEconomicas) {
		this.problematicasSocioEconomicas = problematicasSocioEconomicas;
	}

	public String getDescripcionDeLasHipotesisEspecialesInusualesOExtraordinarias() {
		return descripcionDeLasHipotesisEspecialesInusualesOExtraordinarias;
	}

	public void setDescripcionDeLasHipotesisEspecialesInusualesOExtraordinarias(
			String descripcionDeLasHipotesisEspecialesInusualesOExtraordinarias) {
		this.descripcionDeLasHipotesisEspecialesInusualesOExtraordinarias = descripcionDeLasHipotesisEspecialesInusualesOExtraordinarias;
	}

	public String getConsideracionesGenerales() {
		return consideracionesGenerales;
	}

	public void setConsideracionesGenerales(String consideracionesGenerales) {
		this.consideracionesGenerales = consideracionesGenerales;
	}

	public String getConsideracionesGeneralesDeLocalizacion() {
		return consideracionesGeneralesDeLocalizacion;
	}

	public void setConsideracionesGeneralesDeLocalizacion(
			String consideracionesGeneralesDeLocalizacion) {
		this.consideracionesGeneralesDeLocalizacion = consideracionesGeneralesDeLocalizacion;
	}

	public String getConsideracionesGeneralesDelSector() {
		return consideracionesGeneralesDelSector;
	}

	public void setConsideracionesGeneralesDelSector(
			String consideracionesGeneralesDelSector) {
		this.consideracionesGeneralesDelSector = consideracionesGeneralesDelSector;
	}

	public String getConsideracionesGeneralesDeLaCapacidadDeTerreno() {
		return consideracionesGeneralesDeLaCapacidadDeTerreno;
	}

	public void setConsideracionesGeneralesDeLaCapacidadDeTerreno(
			String consideracionesGeneralesDeLaCapacidadDeTerreno) {
		this.consideracionesGeneralesDeLaCapacidadDeTerreno = consideracionesGeneralesDeLaCapacidadDeTerreno;
	}

	public String getConsideracionesGeneralesDeLaInfraestructuraVialYCapacidadDeAgua() {
		return consideracionesGeneralesDeLaInfraestructuraVialYCapacidadDeAgua;
	}

	public void setConsideracionesGeneralesDeLaInfraestructuraVialYCapacidadDeAgua(
			String consideracionesGeneralesDeLaInfraestructuraVialYCapacidadDeAgua) {
		this.consideracionesGeneralesDeLaInfraestructuraVialYCapacidadDeAgua = consideracionesGeneralesDeLaInfraestructuraVialYCapacidadDeAgua;
	}

	public String getConsideracionesGeneralesDeLaConstruccion() {
		return consideracionesGeneralesDeLaConstruccion;
	}

	public void setConsideracionesGeneralesDeLaConstruccion(
			String consideracionesGeneralesDeLaConstruccion) {
		this.consideracionesGeneralesDeLaConstruccion = consideracionesGeneralesDeLaConstruccion;
	}

	public String getCasosEspecialesDeAfectacion() {
		return casosEspecialesDeAfectacion;
	}

	public void setCasosEspecialesDeAfectacion(String casosEspecialesDeAfectacion) {
		this.casosEspecialesDeAfectacion = casosEspecialesDeAfectacion;
	}

	public String getInvestigacionEconomica() {
		return investigacionEconomica;
	}

	public void setInvestigacionEconomica(String investigacionEconomica) {
		this.investigacionEconomica = investigacionEconomica;
	}

	public String getComportamientoDeOfertaYDemanda() {
		return comportamientoDeOfertaYDemanda;
	}

	public void setComportamientoDeOfertaYDemanda(
			String comportamientoDeOfertaYDemanda) {
		this.comportamientoDeOfertaYDemanda = comportamientoDeOfertaYDemanda;
	}

	public Integer getConceptoDeGarantia() {
		return conceptoDeGarantia;
	}

	public void setConceptoDeGarantia(Integer conceptoDeGarantia) {
		if(new Integer(0).equals(conceptoDeGarantia)){
			conceptoDeGarantia = null;
		}
		this.conceptoDeGarantia = conceptoDeGarantia;
	}

	public String getObservacionesConceptoGarantia() {
		return observacionesConceptoGarantia;
	}

	public void setObservacionesConceptoGarantia(
			String observacionesConceptoGarantia) {
		this.observacionesConceptoGarantia = observacionesConceptoGarantia;
	}

	public BigDecimal getValorComercialDelInmueble() {
		return valorComercialDelInmueble;
	}

	public void setValorComercialDelInmueble(BigDecimal valorComercialDelInmueble) {
		this.valorComercialDelInmueble = valorComercialDelInmueble;
	}

	public BigDecimal getValorIntegralSobreElTerreno() {
		return valorIntegralSobreElTerreno;
	}

	public void setValorIntegralSobreElTerreno(
			BigDecimal valorIntegralSobreElTerreno) {
		this.valorIntegralSobreElTerreno = valorIntegralSobreElTerreno;
	}

	public BigDecimal getValorIntegralSobreElConstruccion() {
		return valorIntegralSobreElConstruccion;
	}

	public void setValorIntegralSobreElConstruccion(
			BigDecimal valorIntegralSobreElConstruccion) {
		this.valorIntegralSobreElConstruccion = valorIntegralSobreElConstruccion;
	}

	public BigDecimal getPorcentajeFinal() {
		return porcentajeFinal;
	}

	public void setPorcentajeFinal(BigDecimal porcentajeFinal) {
		this.porcentajeFinal = porcentajeFinal;
	}

	public BigDecimal getAreaConstruida() {
		return areaConstruida;
	}

	public void setAreaConstruida(BigDecimal areaConstruida){
		this.areaConstruida = areaConstruida;
	}

	public BigDecimal getAreaInfraestructura() {
		return areaInfraestructura;
	}

	public void setAreaInfraestructura(BigDecimal areaInfraestructura){
		this.areaInfraestructura = areaInfraestructura;
	}

	public BigDecimal getAreaCultivosAvaluables() {
		return areaCultivosAvaluables;
	}

	public void setAreaCultivosAvaluables(BigDecimal areaCultivosAvaluables){
		this.areaCultivosAvaluables = areaCultivosAvaluables;
	}

	public Set<InmuebleDTO> getInmuebles() {
		return inmuebles;
	}

	public void setInmuebles(Set<InmuebleDTO> inmuebles) {
		this.inmuebles = inmuebles;
	}

	public Set<ExplotacionEconomicaDTO> getExplotacionesEconomicas() {
		return explotacionesEconomicas;
	}

	public void setExplotacionesEconomicas(
			Set<ExplotacionEconomicaDTO> explotacionesEconomicas) {
		this.explotacionesEconomicas = explotacionesEconomicas;
	}

	public Set<ServidumbreDTO> getServidumbrez() {
		return servidumbrez;
	}

	public void setServidumbrez(Set<ServidumbreDTO> servidumbrez) {
		this.servidumbrez = servidumbrez;
	}

	public Set<ViaAccesoDTO> getViasAcceso() {
		return viasAcceso;
	}

	public void setViasAcceso(Set<ViaAccesoDTO> viasAcceso) {
		this.viasAcceso = viasAcceso;
	}

	public Set<MetodoValuacionDTO> getMetodosValuacion() {
		return metodosValuacion;
	}

	public void setMetodosValuacion(Set<MetodoValuacionDTO> metodosValuacion) {
		this.metodosValuacion = metodosValuacion;
	}

	public Set<AreaConstruccionDTO> getAreasConstruccion() {
		return areasConstruccion;
	}

	public void setAreasConstruccion(Set<AreaConstruccionDTO> areasConstruccion) {
		this.areasConstruccion = areasConstruccion;
	}

}
