package com.helio4.bancol.avaluos.modelo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name="formato_informe_comercial")
@PrimaryKeyJoinColumn(name="formato_informe_id")
public class FormatoInformeComercial extends FormatoInforme {

	@Column(name = "vereda")
	private String vereda;
	@Column(name = "nombre_predio")
	private String nombrePredio;
	@Column(name = "localizacion")
	private String localizacion;
	@Column(name = "destinatario_de_la_valuacion")
	private String destinatarioDeLaValuacion;
	@Column(name = "observaciones_tipo_inmueble")
	private String observacionesTipoInmueble;
	@Column(name = "uso_actual_del_inmueble")
	private Integer usoActualDelInmueble;
	@Column(name = "observaciones_uso_actual_del_inmueble")
	private String observacionesUsoActualDelInmueble;
	@Column(name = "uso_proyectado_del_inmueble")
	private Integer usoProyectadoDelInmueble;
	@Column(name = "observaciones_uso_proyectado_del_inmueble")
	private String observacionesUsoProyectadoDelInmueble;
	@Column(name = "periodo_de_mercadeo")
	private Integer periodoDeMercadeo;
	@Column(name = "observaciones_periodo_de_mercadeo")
	private String observacionesPeriodoDeMercadeo;
	@Column(name = "documentos_consultados")
	private String documentosConsultados;
	@Column(name = "fecha_aporte_de_documentos")
	private Date fechaAporteDeDocumentos;
	@Column(name = "otros_documentos")
	private String otrosDocumentos;
	@Column(name = "observaciones_de_titulacion")
	private String observacionesDeTitulacion;
	@Column(name = "descripcion_general_municipio_o_sector")
	private String descripcionGeneralMunicipioOSector;
	@Column(name = "ubicacion_del_sector")
	private String ubicacionDelSector;
	@Column(name = "limite_norte")
	private String limiteNorte;
	@Column(name = "limite_sur")
	private String limiteSur;
	@Column(name = "limite_oriente")
	private String limiteOriente;
	@Column(name = "limite_occidente")
	private String limiteOccidente;
	@Column(name = "barrios_cercanos")
	private String barriosCercanos;
	@Column(name = "uso_predominante_del_sector")
	private String usoPredominanteDelSector;
	@Column(name = "comercializacion")
	private String comercializacion;
	@Column(name = "tiempo_esperado_de_comercializacion")
	private Integer tiempoEsperadoDeComercializacion;
	@Column(name = "estrato")
	private Integer estrato;
	@Column(name = "caracteristicas_socio_economicas")
	private String caracteristicasSocioEconomicas;
	@Column(name = "orden_publico")
	private String ordenPublico;
	@Column(name = "observaciones_vias_de_acceso")
	private String observacionesViasDeAcceso;
	@Column(name = "equipamiento_de_red_vial")
	private String equipamientoDeRedVial;
	@Column(name = "estado_de_conservacion")
	private String estadoDeConservacion;
	@Column(name = "uso_del_suelo")
	private String usoDelSuelo;
	@Column(name = "infraestructura_urbanistica")
	private String infraestructuraUrbanistica;
	@Column(name = "infraestructura_dotacional")
	private String infraestructuraDotacional;
	@Column(name = "amoblamiento_urbano")
	private String amoblamientoUrbano;
	@Column(name = "andenes_y_sardineles")
	private String andenesYSardineles;
	@Column(name = "zonas_verdes")
	private String zonasVerdes;
	@Column(name = "topografia")
	private String topografia;
	@Column(name = "alumbrado_publico")
	private String alumbradoPublico;
	@Column(name = "alcantarillado")
	private Boolean alcantarillado;
	@Column(name = "acueducto")
	private Boolean acueducto;
	@Column(name = "energia")
	private Boolean energia;
	@Column(name = "gas")
	private Boolean gas;
	@Column(name = "telefono")
	private Boolean telefono;
	@Column(name = "observaciones_servicios_publicos")
	private String observacionesServiciosPublicos;
	@Column(name = "transporte_publico")
	private String transportePublico;
	@Column(name = "frecuencia")
	private String frecuencia;
	@Column(name = "cubrimiento")
	private String cubrimiento;
	@Column(name = "perspectivas_de_valorizacion")
	private Integer perspectivasDeValorizacion;
	@Column(name = "observaciones_perspectivas_de_valorizacion")
	private String observacionesPerspectivasDeValorizacion;
	@Column(name = "normatividad")
	private String normatividad;
	@Column(name = "descripcion_del_inmueble")
	private String descripcionDelInmueble;
	@Column(name = "localizacion_especifaca_del_inmueble")
	private String localizacionEspecifacaDelInmueble;
	@Column(name = "distacia_partiendo_del_casco_urbano")
	private Integer distaciaPartiendoDelCascoUrbano;
	@Column(name = "lindero_norte")
	private String linderoNorte;
	@Column(name = "lindero_sur")
	private String linderoSur;
	@Column(name = "lindero_oriente")
	private String linderoOriente;
	@Column(name = "lindero_occidente")
	private String linderoOccidente;
	@Column(name = "fuente_linderos")
	private String fuenteLinderos;
	@Column(name = "descripcion_vias_de_acceso_internas")
	private String descripcionViasDeAccesoInternas;
	@Column(name = "frente_sobre_la_via")
	private Boolean frenteSobreLaVia;
	@Column(name = "metros")
	private BigDecimal metros;
	@Column(name = "cercas_perimedales")
	private String cercasPerimedales;
	@Column(name = "servidumbres")
	private Boolean servidumbres;
	@Column(name = "propiedad_horizontal")
	private Boolean propiedadHorizontal;
	@Column(name = "descripcion_de_la_propiedad_horizontal")
	private String descripcionDeLaPropiedadHorizontal;
	@Column(name = "vida_util")
	private Integer vidaUtil;
	@Column(name = "vida_del_inmueble")
	private Integer vidaDelInmueble;
	@Column(name = "vida_remanente")
	private Integer vidaRemanente;
	@Column(name = "justificacion_vida_util")
	private String justificacionVidaUtil;
	@Column(name = "estructura_edificio")
	private Integer estructuraEdificio;
	@Column(name = "descripcion_estructura")
	private String descripcionEstructura;
	@Column(name = "placas_de_entrepiso")
	private Integer placasDeEntrepiso;
	@Column(name = "observaciones_placas_entre_piso")
	private String observacionesPlacasEntrePiso;
	@Column(name = "fachada")
	private Integer fachada;
	@Column(name = "descripcion_fachada")
	private String descripcionFachada;
	@Column(name = "cubierta")
	private Integer cubierta;
	@Column(name = "descripcion_cubierta")
	private String descripcionCubierta;
	@Column(name = "escaleras")
	private Integer escaleras;
	@Column(name = "descripcion_escaleras")
	private String descripcionEscaleras;
	@Column(name = "estructura")
	private String estructura;
	@Column(name = "categoria_acabados")
	private String categoriaAcabados;
	@Column(name = "distribucion")
	private String distribucion;
	@Column(name = "estado_de_conservacion_sector_rural")
	private String estadoDeConservacionSectorRural;
	@Column(name = "equipamiento_comunal")
	private String equipamientoComunal;
	@Column(name = "descripcion_del_suelo")
	private String descripcionDelSuelo;
	@Column(name = "relieve")
	private Integer relieve;
	@Column(name = "erosion")
	private Boolean erosion;
	@Column(name = "temperatura")
	private BigDecimal temperatura;
	@Column(name = "piso_termico")
	private Long pisoTermico;
	@Column(name = "altura_msnm")
	private BigDecimal alturaMsnm;
	@Column(name = "precipitacion_anual_mm")
	private BigDecimal precipitacionAnualMm;
	@Column(name = "forma_geometrica")
	private Integer formaGeometrica;
	@Column(name = "regular")
	private Boolean regular;
	@Column(name = "irregular")
	private Boolean irregular;
	@Column(name = "inundabilidad")
	private String inundabilidad;
	@Column(name = "distribucion_de_lluvias")
	private String distribucionDeLluvias;
	@Column(name = "pedregosidad")
	private String pedregosidad;
	@Column(name = "fertilidad_natural")
	private String fertilidadNatural;
	@Column(name = "nivel_freatico")
	private BigDecimal nivelFreatico;
	@Column(name = "clase_agrologica")
	private String claseAgrologica;
	@Column(name = "valor_potencial")
	private BigDecimal valorPotencial;
	@Column(name = "capa_vegetal")
	private String capaVegetal;
	@Column(name = "condiciones_agronomicas")
	private Integer condicionesAgronomicas;
	@Column(name = "condiciones_agrologicas")
	private Integer condicionesAgrologicas;
	@Column(name = "descripcion_cultivos")
	private String descripcionCultivos;
	@Column(name = "recursos_hidricos")
	private String recursosHidricos;
	@Column(name = "irrigacion")
	private String irrigacion;
	@Column(name = "restricciones_fisicas")
	private String restriccionesFisicas;
	@Column(name = "otros_cutivos")
	private String otrosCutivos;
	@Column(name = "bosques")
	private Boolean bosques;
	@Column(name = "cultivos_comerciales")
	private Boolean cultivosComerciales;
	@Column(name = "de_proteccion")
	private Boolean deProteccion;
	@Column(name = "impacto_ambiental")
	private String impactoAmbiental;
	@Column(name = "frente")
	private BigDecimal frente;
	@Column(name = "fondo")
	private BigDecimal fondo;
	@Column(name = "relacion_frente_fondo")
	private BigDecimal relacionFrenteFondo;
	@Column(name = "observacion_distribucion_areas_no_construidas")
	private String observacionDistribucionDeAreasNoConstruidas;
	@Column(name = "construccion")
	private Boolean construccion;
	@Column(name = "vida_util_construccion")
	private Integer vidaUtilConstruccion;
	@Column(name = "vida_del_inmueble_construccion")
	private Integer vidaDelInmuebleConstruccion;
	@Column(name = "vida_remanente_construccion")
	private Integer vidaRemanenteConstruccion;
	@Column(name = "observaciones_edad")
	private String observacionesEdad;
	@Column(name = "remodelado")
	private Boolean remodelado;
	@Column(name = "alcantarillado_construccion")
	private Boolean alcantarilladoConstruccion;
	@Column(name = "acueducto_construccion")
	private Boolean acueductoConstruccion;
	@Column(name = "energia_construccion")
	private Boolean energiaConstruccion;
	@Column(name = "gas_construccion")
	private Boolean gasConstruccion;
	@Column(name = "telefono_construccion")
	private Boolean telefonoConstruccion;
	@Column(name = "observaciones_servicios_publicos_construccion")
	private String observacionesServiciosPublicosConstruccion;
	@Column(name = "frente_construido")
	private BigDecimal frenteConstruido;
	@Column(name = "fondo_construido")
	private BigDecimal fondoConstruido;
	@Column(name = "relacion_frente_fondo_construido")
	private BigDecimal relacionFrenteFondoConstruido;
	@Column(name = "area_privada_construido")
	private BigDecimal areaPrivadaConstruido;
	@Column(name = "coeficiente_de_copropiedad_construido")
	private BigDecimal coeficienteDeCopropiedadConstruido;
	@Column(name = "area_total_construida")
	private BigDecimal areaTotalConstruida;
	@Column(name = "fuente_construido")
	private String fuenteConstruido;
	@Column(name = "observaciones_distribucion_areas_construidas")
	private String observacionesDistribucionAreasConstruidas;
	@Column(name = "problemas_de_estabilidad_de_suelos")
	private String problemasDeEstabilidadDeSuelos;
	@Column(name = "impacto_ambiental_y_condiciones_de_salubridad")
	private String impactoAmbientalYCondicionesDeSalubridad;
	@Column(name = "servidumbres_cesiones_y_afectaciones_viales")
	private String servidumbresCesionesYAfectacionesViales;
	@Column(name = "seguridad")
	private String seguridad;
	@Column(name = "problematicas_socio_economicas")
	private String problematicasSocioEconomicas;
	@Column(name = "descripcion_hipotesis_especiales_inusuales_extraordinarias")
	private String descripcionDeLasHipotesisEspecialesInusualesOExtraordinarias;
	@Column(name = "consideraciones_generales")
	private String consideracionesGenerales;
	@Column(name = "consideraciones_generales_de_localizacion")
	private String consideracionesGeneralesDeLocalizacion;
	@Column(name = "consideraciones_generales_del_sector")
	private String consideracionesGeneralesDelSector;
	@Column(name = "consideraciones_generales_de_la_capacidad_de_terreno")
	private String consideracionesGeneralesDeLaCapacidadDeTerreno;
	@Column(name = "consideraciones_infraestructura_vial_capacidad_agua")
	private String consideracionesGeneralesDeLaInfraestructuraVialYCapacidadDeAgua;
	@Column(name = "consideraciones_generales_de_la_construccion")
	private String consideracionesGeneralesDeLaConstruccion;
	@Column(name = "casos_especiales_de_afectacion")
	private String casosEspecialesDeAfectacion;
	@Column(name = "investigacion_economica")
	private String investigacionEconomica;
	@Column(name = "comportamiento_de_oferta_y_demanda")
	private String comportamientoDeOfertaYDemanda;
	@Column(name = "concepto_de_garantia")
	private Integer conceptoDeGarantia;
	@Column(name = "observaciones_concepto_garantia")
	private String observacionesConceptoGarantia;
	@Column(name = "valor_razonable_del_inmueble")
	private BigDecimal valorRazonableDelInmueble;
	@Column(name = "valor_integral_sobre_el_terreno")
	private BigDecimal valorIntegralSobreElTerreno;
	@Column(name = "valor_integral_sobre_el_construccion")
	private BigDecimal valorIntegralSobreElConstruccion;
	@Column(name = "porcentaje_final")
	private BigDecimal porcentajeFinal;
	@Column(name = "area_construida")
	private BigDecimal areaConstruida;
	@Column(name = "area_infraestructura")
	private BigDecimal areaInfraestructura;
	@Column(name = "area_cultivos_avaluables")
	private BigDecimal areaCultivosAvaluables;
	@OneToMany(mappedBy="avaluo", fetch=FetchType.LAZY)
	private Set<Inmueble> inmuebles;
	@OneToMany(mappedBy="avaluo", fetch=FetchType.LAZY)
	private Set<ExplotacionEconomica> explotacionesEconomicas;
	@OneToMany(mappedBy="avaluo", fetch=FetchType.LAZY)
	private Set<Servidumbre> servidumbrez;
	@OneToMany(mappedBy="avaluo", fetch=FetchType.LAZY)
	private Set<ViaAcceso> viasAcceso;
	@OneToMany(mappedBy="avaluo", fetch=FetchType.LAZY)
	private Set<MetodoValuacion> metodosValuacion;

    public FormatoInformeComercial() {
        super();
    }

	public FormatoInformeComercial(Avaluo avaluo) {
		super(avaluo);
	}

	public FormatoInformeComercial(
			Avaluo avaluo,
			String vereda,
			String nombrePredio,
			String localizacion,
			String destinatarioDeLaValuacion,
			String observacionesTipoInmueble,
			Integer usoActualDelInmueble,
			String observacionesUsoActualDelInmueble,
			Integer usoProyectadoDelInmueble,
			String observacionesUsoProyectadoDelInmueble,
			Integer periodoDeMercadeo,
			String observacionesPeriodoDeMercadeo,
			String documentosConsultados,
			Date fechaAporteDeDocumentos,
			String otrosDocumentos,
			String observacionesDeTitulacion,
			String descripcionGeneralMunicipioOSector,
			String ubicacionDelSector,
			String limiteNorte,
			String limiteSur,
			String limiteOriente,
			String limiteOccidente,
			String barriosCercanos,
			String usoPredominanteDelSector,
			String comercializacion,
			Integer tiempoEsperadoDeComercializacion,
			Integer estrato,
			String caracteristicasSocioEconomicas,
			String ordenPublico,
			String observacionesViasDeAcceso,
			String equipamientoDeRedVial,
			String estadoDeConservacion,
			String usoDelSuelo,
			String infraestructuraUrbanistica,
			String infraestructuraDotacional,
			String amoblamientoUrbano,
			String andenesYSardineles,
			String zonasVerdes,
			String topografia,
			String alumbradoPublico,
			Boolean alcantarillado,
			Boolean acueducto,
			Boolean energia,
			Boolean gas,
			Boolean telefono,
			String observacionesServiciosPublicos,
			String transportePublico,
			String frecuencia,
			String cubrimiento,
			Integer perspectivasDeValorizacion,
			String observacionesPerspectivasDeValorizacion,
			String normatividad,
			String descripcionDelInmueble,
			String localizacionEspecifacaDelInmueble,
			Integer distaciaPartiendoDelCascoUrbano,
			String linderoNorte,
			String linderoSur,
			String linderoOriente,
			String linderoOccidente,
			String fuenteLinderos,
			String descripcionViasDeAccesoInternas,
			Boolean frenteSobreLaVia,
			BigDecimal metros,
			String cercasPerimedales,
			Boolean servidumbres,
			Boolean propiedadHorizontal,
			String descripcionDeLaPropiedadHorizontal,
			Integer vidaUtil,
			Integer vidaDelInmueble,
			Integer vidaRemanente,
			String justificacionVidaUtil,
			Integer estructuraEdificio,
			String descripcionEstructura,
			Integer placasDeEntrepiso,
			String observacionesPlacasEntrePiso,
			Integer fachada,
			String descripcionFachada,
			Integer cubierta,
			String descripcionCubierta,
			Integer escaleras,
			String descripcionEscaleras,
			String estructura,
			String categoriaAcabados,
			String distribucion,
			String estadoDeConservacionSectorRural,
			String equipamientoComunal,
			String descripcionDelSuelo,
			Integer relieve,
			Boolean erosion,
			BigDecimal temperatura,
			Long pisoTermico,
			BigDecimal alturaMsnm,
			BigDecimal precipitacionAnualMm,
			Integer formaGeometrica,
			Boolean regular,
			Boolean irregular,
			String inundabilidad,
			String distribucionDeLluvias,
			String pedregosidad,
			String fertilidadNatural,
			BigDecimal nivelFreatico,
			String claseAgrologica,
			BigDecimal valorPotencial,
			String capaVegetal,
			Integer condicionesAgronomicas,
			Integer condicionesAgrologicas,
			String descripcionCultivos,
			String recursosHidricos,
			String irrigacion,
			String restriccionesFisicas,
			String otrosCutivos,
			Boolean bosques,
			Boolean cultivosComerciales,
			Boolean deProteccion,
			String impactoAmbiental,
			BigDecimal frente,
			BigDecimal fondo,
			BigDecimal relacionFrenteFondo,
			String observacionDistribucionDeAreasNoConstruidas,
			Boolean construccion,
			Integer vidaUtilConstruccion,
			Integer vidaDelInmuebleConstruccion,
			Integer vidaRemanenteConstruccion,
			String observacionesEdad,
			Boolean remodelado,
			Boolean alcantarilladoConstruccion,
			Boolean acueductoConstruccion,
			Boolean energiaConstruccion,
			Boolean gasConstruccion,
			Boolean telefonoConstruccion,
			String observacionesServiciosPublicosConstruccion,
			BigDecimal frenteConstruido,
			BigDecimal fondoConstruido,
			BigDecimal relacionFrenteFondoConstruido,
			BigDecimal areaPrivadaConstruido,
			BigDecimal coeficienteDeCopropiedadConstruido,
			BigDecimal areaTotalConstruida,
			String fuenteConstruido,
			String observacionesDistribucionAreasConstruidas,
			String problemasDeEstabilidadDeSuelos,
			String impactoAmbientalYCondicionesDeSalubridad,
			String servidumbresCesionesYAfectacionesViales,
			String seguridad,
			String problematicasSocioEconomicas,
			String descripcionDeLasHipotesisEspecialesInusualesOExtraordinarias,
			String consideracionesGenerales,
			String consideracionesGeneralesDeLocalizacion,
			String consideracionesGeneralesDelSector,
			String consideracionesGeneralesDeLaCapacidadDeTerreno,
			String consideracionesGeneralesDeLaInfraestructuraVialYCapacidadDeAgua,
			String consideracionesGeneralesDeLaConstruccion,
			String casosEspecialesDeAfectacion, String investigacionEconomica,
			String comportamientoDeOfertaYDemanda, Integer conceptoDeGarantia,
			String observacionesConceptoGarantia,
			BigDecimal valorRazonableDelInmueble,
			BigDecimal valorIntegralSobreElTerreno,
			BigDecimal valorIntegralSobreElConstruccion,
			BigDecimal porcentajeFinal) {
		super(avaluo);
		this.vereda = vereda;
		this.nombrePredio = nombrePredio;
		this.localizacion = localizacion;
		this.destinatarioDeLaValuacion = destinatarioDeLaValuacion;
		this.observacionesTipoInmueble = observacionesTipoInmueble;
		this.usoActualDelInmueble = usoActualDelInmueble;
		this.observacionesUsoActualDelInmueble = observacionesUsoActualDelInmueble;
		this.usoProyectadoDelInmueble = usoProyectadoDelInmueble;
		this.observacionesUsoProyectadoDelInmueble = observacionesUsoProyectadoDelInmueble;
		this.periodoDeMercadeo = periodoDeMercadeo;
		this.observacionesPeriodoDeMercadeo = observacionesPeriodoDeMercadeo;
		this.documentosConsultados = documentosConsultados;
		this.fechaAporteDeDocumentos = fechaAporteDeDocumentos;
		this.otrosDocumentos = otrosDocumentos;
		this.observacionesDeTitulacion = observacionesDeTitulacion;
		this.descripcionGeneralMunicipioOSector = descripcionGeneralMunicipioOSector;
		this.ubicacionDelSector = ubicacionDelSector;
		this.limiteNorte = limiteNorte;
		this.limiteSur = limiteSur;
		this.limiteOriente = limiteOriente;
		this.limiteOccidente = limiteOccidente;
		this.barriosCercanos = barriosCercanos;
		this.usoPredominanteDelSector = usoPredominanteDelSector;
		this.comercializacion = comercializacion;
		this.tiempoEsperadoDeComercializacion = tiempoEsperadoDeComercializacion;
		this.estrato = estrato;
		this.caracteristicasSocioEconomicas = caracteristicasSocioEconomicas;
		this.ordenPublico = ordenPublico;
		this.observacionesViasDeAcceso = observacionesViasDeAcceso;
		this.equipamientoDeRedVial = equipamientoDeRedVial;
		this.estadoDeConservacion = estadoDeConservacion;
		this.usoDelSuelo = usoDelSuelo;
		this.infraestructuraUrbanistica = infraestructuraUrbanistica;
		this.infraestructuraDotacional = infraestructuraDotacional;
		this.amoblamientoUrbano = amoblamientoUrbano;
		this.andenesYSardineles = andenesYSardineles;
		this.zonasVerdes = zonasVerdes;
		this.topografia = topografia;
		this.alumbradoPublico = alumbradoPublico;
		this.alcantarillado = alcantarillado;
		this.acueducto = acueducto;
		this.energia = energia;
		this.gas = gas;
		this.telefono = telefono;
		this.observacionesServiciosPublicos = observacionesServiciosPublicos;
		this.transportePublico = transportePublico;
		this.frecuencia = frecuencia;
		this.cubrimiento = cubrimiento;
		this.perspectivasDeValorizacion = perspectivasDeValorizacion;
		this.observacionesPerspectivasDeValorizacion = observacionesPerspectivasDeValorizacion;
		this.normatividad = normatividad;
		this.descripcionDelInmueble = descripcionDelInmueble;
		this.localizacionEspecifacaDelInmueble = localizacionEspecifacaDelInmueble;
		this.distaciaPartiendoDelCascoUrbano = distaciaPartiendoDelCascoUrbano;
		this.linderoNorte = linderoNorte;
		this.linderoSur = linderoSur;
		this.linderoOriente = linderoOriente;
		this.linderoOccidente = linderoOccidente;
		this.fuenteLinderos = fuenteLinderos;
		this.descripcionViasDeAccesoInternas = descripcionViasDeAccesoInternas;
		this.frenteSobreLaVia = frenteSobreLaVia;
		this.metros = metros;
		this.cercasPerimedales = cercasPerimedales;
		this.servidumbres = servidumbres;
		this.propiedadHorizontal = propiedadHorizontal;
		this.descripcionDeLaPropiedadHorizontal = descripcionDeLaPropiedadHorizontal;
		this.vidaUtil = vidaUtil;
		this.vidaDelInmueble = vidaDelInmueble;
		this.vidaRemanente = vidaRemanente;
		this.justificacionVidaUtil = justificacionVidaUtil;
		this.estructuraEdificio = estructuraEdificio;
		this.descripcionEstructura = descripcionEstructura;
		this.placasDeEntrepiso = placasDeEntrepiso;
		this.observacionesPlacasEntrePiso = observacionesPlacasEntrePiso;
		this.fachada = fachada;
		this.descripcionFachada = descripcionFachada;
		this.cubierta = cubierta;
		this.descripcionCubierta = descripcionCubierta;
		this.escaleras = escaleras;
		this.descripcionEscaleras = descripcionEscaleras;
		this.estructura = estructura;
		this.categoriaAcabados = categoriaAcabados;
		this.distribucion = distribucion;
		this.estadoDeConservacionSectorRural = estadoDeConservacionSectorRural;
		this.equipamientoComunal = equipamientoComunal;
		this.descripcionDelSuelo = descripcionDelSuelo;
		this.relieve = relieve;
		this.erosion = erosion;
		this.temperatura = temperatura;
		this.pisoTermico = pisoTermico;
		this.alturaMsnm = alturaMsnm;
		this.precipitacionAnualMm = precipitacionAnualMm;
		this.formaGeometrica = formaGeometrica;
		this.regular = regular;
		this.irregular = irregular;
		this.inundabilidad = inundabilidad;
		this.distribucionDeLluvias = distribucionDeLluvias;
		this.pedregosidad = pedregosidad;
		this.fertilidadNatural = fertilidadNatural;
		this.nivelFreatico = nivelFreatico;
		this.claseAgrologica = claseAgrologica;
		this.valorPotencial = valorPotencial;
		this.capaVegetal = capaVegetal;
		this.condicionesAgronomicas = condicionesAgronomicas;
		this.condicionesAgrologicas = condicionesAgrologicas;
		this.descripcionCultivos = descripcionCultivos;
		this.recursosHidricos = recursosHidricos;
		this.irrigacion = irrigacion;
		this.restriccionesFisicas = restriccionesFisicas;
		this.otrosCutivos = otrosCutivos;
		this.bosques = bosques;
		this.cultivosComerciales = cultivosComerciales;
		this.deProteccion = deProteccion;
		this.impactoAmbiental = impactoAmbiental;
		this.frente = frente;
		this.fondo = fondo;
		this.relacionFrenteFondo = relacionFrenteFondo;
		this.observacionDistribucionDeAreasNoConstruidas = observacionDistribucionDeAreasNoConstruidas;
		this.construccion = construccion;
		this.vidaUtilConstruccion = vidaUtilConstruccion;
		this.vidaDelInmuebleConstruccion = vidaDelInmuebleConstruccion;
		this.vidaRemanenteConstruccion = vidaRemanenteConstruccion;
		this.observacionesEdad = observacionesEdad;
		this.remodelado = remodelado;
		this.alcantarilladoConstruccion = alcantarilladoConstruccion;
		this.acueductoConstruccion = acueductoConstruccion;
		this.energiaConstruccion = energiaConstruccion;
		this.gasConstruccion = gasConstruccion;
		this.telefonoConstruccion = telefonoConstruccion;
		this.observacionesServiciosPublicosConstruccion = observacionesServiciosPublicosConstruccion;
		this.frenteConstruido = frenteConstruido;
		this.fondoConstruido = fondoConstruido;
		this.relacionFrenteFondoConstruido = relacionFrenteFondoConstruido;
		this.areaPrivadaConstruido = areaPrivadaConstruido;
		this.coeficienteDeCopropiedadConstruido = coeficienteDeCopropiedadConstruido;
		this.areaTotalConstruida = areaTotalConstruida;
		this.fuenteConstruido = fuenteConstruido;
		this.observacionesDistribucionAreasConstruidas = observacionesDistribucionAreasConstruidas;
		this.problemasDeEstabilidadDeSuelos = problemasDeEstabilidadDeSuelos;
		this.impactoAmbientalYCondicionesDeSalubridad = impactoAmbientalYCondicionesDeSalubridad;
		this.servidumbresCesionesYAfectacionesViales = servidumbresCesionesYAfectacionesViales;
		this.seguridad = seguridad;
		this.problematicasSocioEconomicas = problematicasSocioEconomicas;
		this.descripcionDeLasHipotesisEspecialesInusualesOExtraordinarias = descripcionDeLasHipotesisEspecialesInusualesOExtraordinarias;
		this.consideracionesGenerales = consideracionesGenerales;
		this.consideracionesGeneralesDeLocalizacion = consideracionesGeneralesDeLocalizacion;
		this.consideracionesGeneralesDelSector = consideracionesGeneralesDelSector;
		this.consideracionesGeneralesDeLaCapacidadDeTerreno = consideracionesGeneralesDeLaCapacidadDeTerreno;
		this.consideracionesGeneralesDeLaInfraestructuraVialYCapacidadDeAgua = consideracionesGeneralesDeLaInfraestructuraVialYCapacidadDeAgua;
		this.consideracionesGeneralesDeLaConstruccion = consideracionesGeneralesDeLaConstruccion;
		this.casosEspecialesDeAfectacion = casosEspecialesDeAfectacion;
		this.investigacionEconomica = investigacionEconomica;
		this.comportamientoDeOfertaYDemanda = comportamientoDeOfertaYDemanda;
		this.conceptoDeGarantia = conceptoDeGarantia;
		this.observacionesConceptoGarantia = observacionesConceptoGarantia;
		this.valorRazonableDelInmueble = valorRazonableDelInmueble;
		this.valorIntegralSobreElTerreno = valorIntegralSobreElTerreno;
		this.valorIntegralSobreElConstruccion = valorIntegralSobreElConstruccion;
		this.porcentajeFinal = porcentajeFinal;
	}

	public void update(
			String vereda,
			String nombrePredio,
			String localizacion,
			String destinatarioDeLaValuacion,
			String observacionesTipoInmueble,
			Integer usoActualDelInmueble,
			String observacionesUsoActualDelInmueble,
			Integer usoProyectadoDelInmueble,
			String observacionesUsoProyectadoDelInmueble,
			Integer periodoDeMercadeo,
			String observacionesPeriodoDeMercadeo,
			String documentosConsultados,
			Date fechaAporteDeDocumentos,
			String otrosDocumentos,
			String observacionesDeTitulacion,
			String descripcionGeneralMunicipioOSector,
			String ubicacionDelSector,
			String limiteNorte,
			String limiteSur,
			String limiteOriente,
			String limiteOccidente,
			String barriosCercanos,
			String usoPredominanteDelSector,
			String comercializacion,
			Integer tiempoEsperadoDeComercializacion,
			Integer estrato,
			String caracteristicasSocioEconomicas,
			String ordenPublico,
			String observacionesViasDeAcceso,
			String equipamientoDeRedVial,
			String estadoDeConservacion,
			String usoDelSuelo,
			String infraestructuraUrbanistica,
			String infraestructuraDotacional,
			String amoblamientoUrbano,
			String andenesYSardineles,
			String zonasVerdes,
			String topografia,
			String alumbradoPublico,
			Boolean alcantarillado,
			Boolean acueducto,
			Boolean energia,
			Boolean gas,
			Boolean telefono,
			String observacionesServiciosPublicos,
			String transportePublico,
			String frecuencia,
			String cubrimiento,
			Integer perspectivasDeValorizacion,
			String observacionesPerspectivasDeValorizacion,
			String normatividad,
			String descripcionDelInmueble,
			String localizacionEspecifacaDelInmueble,
			Integer distaciaPartiendoDelCascoUrbano,
			String linderoNorte,
			String linderoSur,
			String linderoOriente,
			String linderoOccidente,
			String fuenteLinderos,
			String descripcionViasDeAccesoInternas,
			Boolean frenteSobreLaVia,
			BigDecimal metros,
			String cercasPerimedales,
			Boolean servidumbres,
			Boolean propiedadHorizontal,
			String descripcionDeLaPropiedadHorizontal,
			Integer vidaUtil,
			Integer vidaDelInmueble,
			Integer vidaRemanente,
			String justificacionVidaUtil,
			Integer estructuraEdificio,
			String descripcionEstructura,
			Integer placasDeEntrepiso,
			String observacionesPlacasEntrePiso,
			Integer fachada,
			String descripcionFachada,
			Integer cubierta,
			String descripcionCubierta,
			Integer escaleras,
			String descripcionEscaleras,
			String estructura,
			String categoriaAcabados,
			String distribucion,
			String estadoDeConservacionSectorRural,
			String equipamientoComunal,
			String descripcionDelSuelo,
			Integer relieve,
			Boolean erosion,
			BigDecimal temperatura,
			Long pisoTermico,
			BigDecimal alturaMsnm,
			BigDecimal precipitacionAnualMm,
			Integer formaGeometrica,
			Boolean regular,
			Boolean irregular,
			String inundabilidad,
			String distribucionDeLluvias,
			String pedregosidad,
			String fertilidadNatural,
			BigDecimal nivelFreatico,
			String claseAgrologica,
			BigDecimal valorPotencial,
			String capaVegetal,
			Integer condicionesAgronomicas,
			Integer condicionesAgrologicas,
			String descripcionCultivos,
			String recursosHidricos,
			String irrigacion,
			String restriccionesFisicas,
			String otrosCutivos,
			Boolean bosques,
			Boolean cultivosComerciales,
			Boolean deProteccion,
			String impactoAmbiental,
			BigDecimal frente,
			BigDecimal fondo,
			BigDecimal relacionFrenteFondo,
			String observacionDistribucionDeAreasNoConstruidas,
			Boolean construccion,
			Integer vidaUtilConstruccion,
			Integer vidaDelInmuebleConstruccion,
			Integer vidaRemanenteConstruccion,
			String observacionesEdad,
			Boolean remodelado,
			Boolean alcantarilladoConstruccion,
			Boolean acueductoConstruccion,
			Boolean energiaConstruccion,
			Boolean gasConstruccion,
			Boolean telefonoConstruccion,
			String observacionesServiciosPublicosConstruccion,
			BigDecimal frenteConstruido,
			BigDecimal fondoConstruido,
			BigDecimal relacionFrenteFondoConstruido,
			BigDecimal areaPrivadaConstruido,
			BigDecimal coeficienteDeCopropiedadConstruido,
			BigDecimal areaTotalConstruida,
			String fuenteConstruido,
			String observacionesDistribucionAreasConstruidas,
			String problemasDeEstabilidadDeSuelos,
			String impactoAmbientalYCondicionesDeSalubridad,
			String servidumbresCesionesYAfectacionesViales,
			String seguridad,
			String problematicasSocioEconomicas,
			String descripcionDeLasHipotesisEspecialesInusualesOExtraordinarias,
			String consideracionesGenerales,
			String consideracionesGeneralesDeLocalizacion,
			String consideracionesGeneralesDelSector,
			String consideracionesGeneralesDeLaCapacidadDeTerreno,
			String consideracionesGeneralesDeLaInfraestructuraVialYCapacidadDeAgua,
			String consideracionesGeneralesDeLaConstruccion,
			String casosEspecialesDeAfectacion, String investigacionEconomica,
			String comportamientoDeOfertaYDemanda, Integer conceptoDeGarantia,
			String observacionesConceptoGarantia,
			BigDecimal valorRazonableDelInmueble,
			BigDecimal valorIntegralSobreElTerreno,
			BigDecimal valorIntegralSobreElConstruccion,
			BigDecimal porcentajeFinal) {
		this.vereda = vereda;
		this.nombrePredio = nombrePredio;
		this.localizacion = localizacion;
		this.destinatarioDeLaValuacion = destinatarioDeLaValuacion;
		this.observacionesTipoInmueble = observacionesTipoInmueble;
		this.usoActualDelInmueble = usoActualDelInmueble;
		this.observacionesUsoActualDelInmueble = observacionesUsoActualDelInmueble;
		this.usoProyectadoDelInmueble = usoProyectadoDelInmueble;
		this.observacionesUsoProyectadoDelInmueble = observacionesUsoProyectadoDelInmueble;
		this.periodoDeMercadeo = periodoDeMercadeo;
		this.observacionesPeriodoDeMercadeo = observacionesPeriodoDeMercadeo;
		this.documentosConsultados = documentosConsultados;
		this.fechaAporteDeDocumentos = fechaAporteDeDocumentos;
		this.otrosDocumentos = otrosDocumentos;
		this.observacionesDeTitulacion = observacionesDeTitulacion;
		this.descripcionGeneralMunicipioOSector = descripcionGeneralMunicipioOSector;
		this.ubicacionDelSector = ubicacionDelSector;
		this.limiteNorte = limiteNorte;
		this.limiteSur = limiteSur;
		this.limiteOriente = limiteOriente;
		this.limiteOccidente = limiteOccidente;
		this.barriosCercanos = barriosCercanos;
		this.usoPredominanteDelSector = usoPredominanteDelSector;
		this.comercializacion = comercializacion;
		this.tiempoEsperadoDeComercializacion = tiempoEsperadoDeComercializacion;
		this.estrato = estrato;
		this.caracteristicasSocioEconomicas = caracteristicasSocioEconomicas;
		this.ordenPublico = ordenPublico;
		this.observacionesViasDeAcceso = observacionesViasDeAcceso;
		this.equipamientoDeRedVial = equipamientoDeRedVial;
		this.estadoDeConservacion = estadoDeConservacion;
		this.usoDelSuelo = usoDelSuelo;
		this.infraestructuraUrbanistica = infraestructuraUrbanistica;
		this.infraestructuraDotacional = infraestructuraDotacional;
		this.amoblamientoUrbano = amoblamientoUrbano;
		this.andenesYSardineles = andenesYSardineles;
		this.zonasVerdes = zonasVerdes;
		this.topografia = topografia;
		this.alumbradoPublico = alumbradoPublico;
		this.alcantarillado = alcantarillado;
		this.acueducto = acueducto;
		this.energia = energia;
		this.gas = gas;
		this.telefono = telefono;
		this.observacionesServiciosPublicos = observacionesServiciosPublicos;
		this.transportePublico = transportePublico;
		this.frecuencia = frecuencia;
		this.cubrimiento = cubrimiento;
		this.perspectivasDeValorizacion = perspectivasDeValorizacion;
		this.observacionesPerspectivasDeValorizacion = observacionesPerspectivasDeValorizacion;
		this.normatividad = normatividad;
		this.descripcionDelInmueble = descripcionDelInmueble;
		this.localizacionEspecifacaDelInmueble = localizacionEspecifacaDelInmueble;
		this.distaciaPartiendoDelCascoUrbano = distaciaPartiendoDelCascoUrbano;
		this.linderoNorte = linderoNorte;
		this.linderoSur = linderoSur;
		this.linderoOriente = linderoOriente;
		this.linderoOccidente = linderoOccidente;
		this.fuenteLinderos = fuenteLinderos;
		this.descripcionViasDeAccesoInternas = descripcionViasDeAccesoInternas;
		this.frenteSobreLaVia = frenteSobreLaVia;
		this.metros = metros;
		this.cercasPerimedales = cercasPerimedales;
		this.servidumbres = servidumbres;
		this.propiedadHorizontal = propiedadHorizontal;
		this.descripcionDeLaPropiedadHorizontal = descripcionDeLaPropiedadHorizontal;
		this.vidaUtil = vidaUtil;
		this.vidaDelInmueble = vidaDelInmueble;
		this.vidaRemanente = vidaRemanente;
		this.justificacionVidaUtil = justificacionVidaUtil;
		this.estructuraEdificio = estructuraEdificio;
		this.descripcionEstructura = descripcionEstructura;
		this.placasDeEntrepiso = placasDeEntrepiso;
		this.observacionesPlacasEntrePiso = observacionesPlacasEntrePiso;
		this.fachada = fachada;
		this.descripcionFachada = descripcionFachada;
		this.cubierta = cubierta;
		this.descripcionCubierta = descripcionCubierta;
		this.escaleras = escaleras;
		this.descripcionEscaleras = descripcionEscaleras;
		this.estructura = estructura;
		this.categoriaAcabados = categoriaAcabados;
		this.distribucion = distribucion;
		this.estadoDeConservacionSectorRural = estadoDeConservacionSectorRural;
		this.equipamientoComunal = equipamientoComunal;
		this.descripcionDelSuelo = descripcionDelSuelo;
		this.relieve = relieve;
		this.erosion = erosion;
		this.temperatura = temperatura;
		this.pisoTermico = pisoTermico;
		this.alturaMsnm = alturaMsnm;
		this.precipitacionAnualMm = precipitacionAnualMm;
		this.formaGeometrica = formaGeometrica;
		this.regular = regular;
		this.irregular = irregular;
		this.inundabilidad = inundabilidad;
		this.distribucionDeLluvias = distribucionDeLluvias;
		this.pedregosidad = pedregosidad;
		this.fertilidadNatural = fertilidadNatural;
		this.nivelFreatico = nivelFreatico;
		this.claseAgrologica = claseAgrologica;
		this.valorPotencial = valorPotencial;
		this.capaVegetal = capaVegetal;
		this.condicionesAgronomicas = condicionesAgronomicas;
		this.condicionesAgrologicas = condicionesAgrologicas;
		this.descripcionCultivos = descripcionCultivos;
		this.recursosHidricos = recursosHidricos;
		this.irrigacion = irrigacion;
		this.restriccionesFisicas = restriccionesFisicas;
		this.otrosCutivos = otrosCutivos;
		this.bosques = bosques;
		this.cultivosComerciales = cultivosComerciales;
		this.deProteccion = deProteccion;
		this.impactoAmbiental = impactoAmbiental;
		this.frente = frente;
		this.fondo = fondo;
		this.relacionFrenteFondo = relacionFrenteFondo;
		this.observacionDistribucionDeAreasNoConstruidas = observacionDistribucionDeAreasNoConstruidas;
		this.construccion = construccion;
		this.vidaUtilConstruccion = vidaUtilConstruccion;
		this.vidaDelInmuebleConstruccion = vidaDelInmuebleConstruccion;
		this.vidaRemanenteConstruccion = vidaRemanenteConstruccion;
		this.observacionesEdad = observacionesEdad;
		this.remodelado = remodelado;
		this.alcantarilladoConstruccion = alcantarilladoConstruccion;
		this.acueductoConstruccion = acueductoConstruccion;
		this.energiaConstruccion = energiaConstruccion;
		this.gasConstruccion = gasConstruccion;
		this.telefonoConstruccion = telefonoConstruccion;
		this.observacionesServiciosPublicosConstruccion = observacionesServiciosPublicosConstruccion;
		this.frenteConstruido = frenteConstruido;
		this.fondoConstruido = fondoConstruido;
		this.relacionFrenteFondoConstruido = relacionFrenteFondoConstruido;
		this.areaPrivadaConstruido = areaPrivadaConstruido;
		this.coeficienteDeCopropiedadConstruido = coeficienteDeCopropiedadConstruido;
		this.areaTotalConstruida = areaTotalConstruida;
		this.fuenteConstruido = fuenteConstruido;
		this.observacionesDistribucionAreasConstruidas = observacionesDistribucionAreasConstruidas;
		this.problemasDeEstabilidadDeSuelos = problemasDeEstabilidadDeSuelos;
		this.impactoAmbientalYCondicionesDeSalubridad = impactoAmbientalYCondicionesDeSalubridad;
		this.servidumbresCesionesYAfectacionesViales = servidumbresCesionesYAfectacionesViales;
		this.seguridad = seguridad;
		this.problematicasSocioEconomicas = problematicasSocioEconomicas;
		this.descripcionDeLasHipotesisEspecialesInusualesOExtraordinarias = descripcionDeLasHipotesisEspecialesInusualesOExtraordinarias;
		this.consideracionesGenerales = consideracionesGenerales;
		this.consideracionesGeneralesDeLocalizacion = consideracionesGeneralesDeLocalizacion;
		this.consideracionesGeneralesDelSector = consideracionesGeneralesDelSector;
		this.consideracionesGeneralesDeLaCapacidadDeTerreno = consideracionesGeneralesDeLaCapacidadDeTerreno;
		this.consideracionesGeneralesDeLaInfraestructuraVialYCapacidadDeAgua = consideracionesGeneralesDeLaInfraestructuraVialYCapacidadDeAgua;
		this.consideracionesGeneralesDeLaConstruccion = consideracionesGeneralesDeLaConstruccion;
		this.casosEspecialesDeAfectacion = casosEspecialesDeAfectacion;
		this.investigacionEconomica = investigacionEconomica;
		this.comportamientoDeOfertaYDemanda = comportamientoDeOfertaYDemanda;
		this.conceptoDeGarantia = conceptoDeGarantia;
		this.observacionesConceptoGarantia = observacionesConceptoGarantia;
		this.valorRazonableDelInmueble = valorRazonableDelInmueble;
		this.valorIntegralSobreElTerreno = valorIntegralSobreElTerreno;
		this.valorIntegralSobreElConstruccion = valorIntegralSobreElConstruccion;
		this.porcentajeFinal = porcentajeFinal;
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

	public Integer getPerspectivasDeValorizacion() {
		return perspectivasDeValorizacion;
	}

	public void setPerspectivasDeValorizacion(Integer perspectivasDeValorizacion) {
		if (perspectivasDeValorizacion == 0) {
			this.perspectivasDeValorizacion = null;
		}else{
			this.perspectivasDeValorizacion = perspectivasDeValorizacion;
		}
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
		this.condicionesAgronomicas = condicionesAgronomicas;
	}

	public Integer getCondicionesAgrologicas() {
		return condicionesAgrologicas;
	}

	public void setCondicionesAgrologicas(Integer condicionesAgrologicas) {
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
		this.conceptoDeGarantia = conceptoDeGarantia;
	}

	public String getObservacionesConceptoGarantia() {
		return observacionesConceptoGarantia;
	}

	public void setObservacionesConceptoGarantia(
			String observacionesConceptoGarantia) {
		this.observacionesConceptoGarantia = observacionesConceptoGarantia;
	}

	public BigDecimal getValorRazonableDelInmueble() {
		return valorRazonableDelInmueble;
	}

	public void setValorRazonableDelInmueble(BigDecimal valorRazonableDelInmueble) {
		this.valorRazonableDelInmueble = valorRazonableDelInmueble;
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
	
	public Set<Inmueble> getInmuebles() {
		return inmuebles;
	}

	public void setInmuebles(Set<Inmueble> inmuebles) {
		this.inmuebles = inmuebles;
	}

	public Set<ExplotacionEconomica> getExplotacionesEconomicas() {
		return explotacionesEconomicas;
	}

	public void setExplotacionesEconomicas(
			Set<ExplotacionEconomica> explotacionesEconomicas) {
		this.explotacionesEconomicas = explotacionesEconomicas;
	}

	public Set<Servidumbre> getServidumbrez() {
		return servidumbrez;
	}

	public void setServidumbrez(Set<Servidumbre> servidumbrez) {
		this.servidumbrez = servidumbrez;
	}

	public Set<ViaAcceso> getViasAcceso() {
		return viasAcceso;
	}

	public void setViasAcceso(Set<ViaAcceso> viasAcceso) {
		this.viasAcceso = viasAcceso;
	}

	public Set<MetodoValuacion> getMetodosValuacion() {
		return metodosValuacion;
	}

	public void setMetodosValuacion(Set<MetodoValuacion> metodosValuacion) {
		this.metodosValuacion = metodosValuacion;
	}

}
