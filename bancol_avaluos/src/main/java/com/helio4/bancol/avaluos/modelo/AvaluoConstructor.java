package com.helio4.bancol.avaluos.modelo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name="avaluo_constructor")
@PrimaryKeyJoinColumn(name="avaluo_id")
public class AvaluoConstructor extends Avaluo {
	
	@Column(name="tipo_de_construccion")
	private String tipoDeConstruccion;
	@Column(name="vereda")
	private String vereda;
	@Column(name="nombre_de_predio")
	private String nombreDePredio;
	@Column(name="localizacion")
	private String localizacion;	
	@Column(name="descripcion_general_del_sector")
	private String descripcionGeneralDelSector;
	@Column(name="alcantarillado_sector")
	private Boolean alcantarilladoSector;
	@Column(name="agua_sector")
	private Boolean aguaSector;
	@Column(name="energia_sector")
	private Boolean energiaSector;
	@Column(name="gas_sector")
	private Boolean gasSector;
	@Column(name="telefono_sector")
	private Boolean telefonoSector;
	@Column(name="observaciones_servivios_publicos")
	private String observacionesServiviosPublicos;
	@Column(name="estrato")
	private Integer estrato;
	@Column(name="zona_objetivo")
	private Boolean zonaObjetivo;
	@Column(name="uso_predominante_del_sector")
	private String usoPredominanteDelSector;
	@Column(name="observaciones_uso_predominante_del_sector")
	private String observacionesUsoPredominanteDelSector;
	@Column(name="perspectivas_de_valorizacion")
	private Integer perspectivasDeValorizacion;
	@Column(name="observaciones_perspectivas_de_valorizacion")
	private String observacionesPerspectivasDeValorizacion;
	@Column(name="descripcion_inmueble")
	private String descripcionInmueble;
	@Column(name="linderos_particulares_del_inmueble")
	private String linderosParticularesDelInmueble;
	@Column(name="fuente")
	private String fuente;
	@Column(name="normatividad_vigente")
	private String normatividadVigente;
	@Column(name="ubicacion_inmediata_del_inmueble")
	private Integer ubicacionInmediataDelInmueble;
	@Column(name="observaciones_ubicacion_inmediata_del_inmueble")
	private String observacionesUbicacionInmediataDelInmueble;
	@Column(name="relieve")
	private String relieve;
	@Column(name="forma_geometrica")
	private String formaGeometrica;
	@Column(name="regular")
	private Boolean regular;
	@Column(name="irregular")
	private Boolean irregular;
	@Column(name="frente")
	private BigDecimal frente;
	@Column(name="fondo")
	private BigDecimal fondo;
	@Column(name="relacion_frente")
	private BigDecimal relacionFrente;
	@Column(name="uso_inmueble")
	private Integer usoInmueble;
	@Column(name="clase_inmueble")
	private Integer claseInmueble;
	@Column(name="estado_inmueble")
	private String estadoInmueble;	
	@Column(name="ubicacion_local")
	private Integer ubicacionLocal;
	@Column(name="requiere_reparaciones")
	private Boolean requiereReparaciones;
	@Column(name="cuales_reaparaciones")
	private String cualesReaparaciones;
	@Column(name="alcantarillado")
	private Boolean alcantarillado;
	@Column(name="energia")
	private Boolean energia;
	@Column(name="agua")
	private Boolean agua;
	@Column(name="telefono")
	private Boolean telefono;
	@Column(name="gas")
	private Boolean gas;
	@Column(name="concepto_de_comercializacion")
	private Integer conceptoDeComercializacion;
	@Column(name="observaciones_concepto_de_comercializacion")
	private String observacionesConceptoDeComercializacion;
	@Column(name="sometido_a_propiedad_horizontal")
	private Boolean sometidoAPropiedadHorizontal;
	@Column(name="conjunto_o_agrupacion")
	private String conjuntoOAgrupacion;
	@Column(name="numero_de_edificios")
	private Integer numeroDeEdificios;
	@Column(name="total_unidades")
	private Integer totalUnidades;
	@Column(name="unidad")
	private String unidad;
	@Column(name="unidades_por_piso")
	private Integer unidadesPorPiso;
	@Column(name="ubicacion_propiedad_horizontal")
	private Integer ubicacionPropiedadHorizontal;
	@Column(name="registro_de_propiedad_horizontal")
	private String registroDePropiedadHorizontal;
	@Column(name="zonas_verdes")
	private Boolean zonasVerdes;
	@Column(name="piscina")
	private Boolean piscina;
	@Column(name="salon_social")
	private Boolean salonSocial;
	@Column(name="juegos_infantiles")
	private Boolean juegosInfantiles;
	@Column(name="ascensor")
	private Boolean ascensor;
	@Column(name="ubicacion_del_sector")
	private Integer ubicacionDelSector;
	@Column(name="vecindario")
	private Integer vecindario;
	@Column(name="desarrollo_y_proyeccion_urbana")
	private Integer desarrolloYProyeccionUrbana;
	@Column(name="vias_de_acceso")
	private Integer viasDeAcceso;
	@Column(name="trasporte_urbano")
	private Integer trasporteUrbano;
	@Column(name="alumbrado_y_alcantarillado")
	private Integer alumbradoYAlcantarillado;
	@Column(name="calles_y_aceras")
	private Integer callesYAceras;
	@Column(name="proporcion_zona_social")
	private Integer proporcionZonaSocial;
	@Column(name="proporcion_zona_servicios")
	private Integer proporcionZonaServicios;
	@Column(name="acabados")
	private Integer acabados;
	@Column(name="diseno_y_distribucion")
	private Integer disenoYDistribucion;
	@Column(name="estado_general_del_inmueble")
	private Integer estadoGeneralDelInmueble;
	@Column(name="observaciones_analisis_tecnico")
	private String observacionesAnalisisTecnico;
	@Column(name="bano_servicios")
	private Integer banoServicios;			
	@Column(name="zona_de_ropas")
	private Integer zonaDeRopas;		
	@Column(name="numero_de_pisos")
	private Integer numeroDePisos;
	@Column(name="numero_de_sotanos")
	private Integer numeroDeSotanos;
	@Column(name="ano_de_construccion")
	private Integer anoDeConstruccion;
	@Column(name="pisos")
	private String pisos;
	@Column(name="muros")
	private String muros;
	@Column(name="techos")
	private String techos;
	@Column(name="banos")
	private String banos;
	@Column(name="cocina")
	private String cocina;
	
	@Column(name = "habitaciones")
	private Integer habitaciones;
	@Column(name = "estar_habitacion")
	private Integer estarHabitacion;
	@Column(name = "cuarto_servicio")
	private Integer cuartoServicio;
	@Column(name = "closet")
	private Integer closet;
	@Column(name = "sala")
	private Integer sala;
	@Column(name = "comedor")
	private Integer comedor;
	@Column(name = "bano_privado")
	private Integer banoPrivado;
	@Column(name = "bano_social")
	private Integer banoSocial;
	@Column(name = "estudio")
	private Integer estudio;
	@Column(name = "balcon")
	private Integer balcon;
	@Column(name = "terraza")
	private Integer terraza;
	@Column(name = "patio_interior")
	private Integer patioInterior;
	@Column(name = "jardin")
	private Integer jardin;
	@Column(name = "zona_verde_privada")
	private Integer zonaVerdePrivada;
	@Column(name = "local")
	private Integer local;
	@Column(name = "oficina")
	private Integer oficina;
	@Column(name = "bodega")
	private Integer bodega;
	
	@Column(name="garaje_privado")
	private Integer garajePrivado;
	@Column(name="garaje_exlusivo")
	private Integer garajeExlusivo;
	@Column(name="bahia_comunal")
	private Integer bahiaComunal;
	@Column(name = "garaje_doble")
	private Boolean garajeDoble;
	@Column(name = "garaje_paralelo")
	private Boolean garajeParalelo;
	
	@Column(name = "matricula_inmobiliaria_garaje_1")
	private String matriculaInmobiliariaGaraje1;
	@Column(name = "matricula_inmobiliaria_garaje_2")
	private String matriculaInmobiliariaGaraje2;
	@Column(name = "matricula_inmobiliaria_garaje_3")
	private String matriculaInmobiliariaGaraje3;
	@Column(name = "matricula_inmobiliaria_garaje_4")
	private String matriculaInmobiliariaGaraje4;
	@Column(name = "matricula_inmobiliaria_garaje_5")
	private String matriculaInmobiliariaGaraje5;
	@Column(name = "garaje_paralelo_1")
	private Boolean garajeParalelo1;
	@Column(name = "garaje_paralelo_2")
	private Boolean garajeParalelo2;
	@Column(name = "garaje_paralelo_3")
	private Boolean garajeParalelo3;
	@Column(name = "garaje_paralelo_4")
	private Boolean garajeParalelo4;
	@Column(name = "garaje_paralelo_5")
	private Boolean garajeParalelo5;
	@Column(name = "garaje_cubierto_1")
	private Boolean garajeCubierto1;
	@Column(name = "garaje_cubierto_2")
	private Boolean garajeCubierto2;
	@Column(name = "garaje_cubierto_3")
	private Boolean garajeCubierto3;
	@Column(name = "garaje_cubierto_4")
	private Boolean garajeCubierto4;
	@Column(name = "garaje_cubierto_5")
	private Boolean garajeCubierto5;
	@Column(name="garaje_servidumbre_1")
	private Boolean garajeServidumbre1;
	@Column(name="garaje_servidumbre_2")
	private Boolean garajeServidumbre2;
	@Column(name="garaje_servidumbre_3")
	private Boolean garajeServidumbre3;
	@Column(name="garaje_servidumbre_4")
	private Boolean garajeServidumbre4;
	@Column(name="garaje_servidumbre_5")
	private Boolean garajeServidumbre5;
	@Column(name = "numero_parqueadero_1")
	private Integer numeroParqueadero1;
	@Column(name = "numero_parqueadero_2")
	private Integer numeroParqueadero2;
	@Column(name = "numero_parqueadero_3")
	private Integer numeroParqueadero3;
	@Column(name = "numero_parqueadero_4")
	private Integer numeroParqueadero4;
	@Column(name = "numero_parqueadero_5")
	private Integer numeroParqueadero5;
	@Column(name = "matricula_inmobiliaria_deposito_1")
	private String matriculaInmobiliariaDeposito1;
	@Column(name = "matricula_inmobiliaria_deposito_2")
	private String matriculaInmobiliariaDeposito2;
	@Column(name = "matricula_inmobiliaria_deposito_3")
	private String matriculaInmobiliariaDeposito3;
	@Column(name = "matricula_inmobiliaria_deposito_4")
	private String matriculaInmobiliariaDeposito4;
	@Column(name = "matricula_inmobiliaria_deposito_5")
	private String matriculaInmobiliariaDeposito5;
	@Column(name = "numero_deposito_1")
	private Integer numeroDeposito1;
	@Column(name = "numero_deposito_2")
	private Integer numeroDeposito2;
	@Column(name = "numero_deposito_3")
	private Integer numeroDeposito3;
	@Column(name = "numero_deposito_4")
	private Integer numeroDeposito4;
	@Column(name = "numero_deposito_5")
	private Integer numeroDeposito5;
	@Column(name = "numero_total_de_garajes")
	private Integer numeroTotalDeGarajes;
	@Column(name = "total_cupos_parquedaro")
	private Integer totalCuposParquedaro;
	@Column(name = "depositos_privados")
	private Integer depositosPrivados;
	@Column(name = "depositos_exclusivos")
	private Integer depositosExclusivos;
	@Column(name = "numero_total_depositos")
	private Integer numeroTotalDepositos;
	@Column(name="total_cupos_de_parqueo")
	private Integer totalCuposDeParqueo;
	@Column(name="lote_proyecto_m2")
	private BigDecimal loteProyectoM2;
	@Column(name="valor_lote")
	private BigDecimal valorLote;
	@Column(name="costos_directos")
	private BigDecimal costosDirectos;
	@Column(name="porcentaje_costos_directos")
	private BigDecimal porcentajeCostosDirectos;
	@Column(name="costos_indirectos")
	private BigDecimal costosIndirectos;
	@Column(name="porcentaje_costos_indirectos")
	private BigDecimal porcentajeCostosIndirectos;
	@Column(name="costos_del_proyecto")
	private BigDecimal costosDelProyecto;
	@Column(name="porcentaje_de_costos_del_proyecto")
	private BigDecimal porcentajeDeCostosDelProyecto;
	@Column(name="valor_solicitado")
	private BigDecimal valorSolicitado;
	@Column(name="porcentaje_a_financiar")
	private BigDecimal porcentajeAFinanciar;
	@Column(name="valor_max_a_financiar")
	private BigDecimal valorMaxAFinanciar;
	@Column(name="porcentaje_financiado")
	private BigDecimal porcentajeFinanciado;
	@Column(name="programacion_en_meses")
	private Integer programacionEnMeses;
	@Column(name="factor")
	private BigDecimal factor;
	@Column(name="valor_solicitado_total")
	private BigDecimal valorSolicitadoTotal;
	@Column(name="costos_financieros")
	private BigDecimal costosFinancieros;
	@Column(name="costos_total_del_proyecto")
	private BigDecimal costosTotalDelProyecto;
	@Column(name="observaciones_informe_tecnico")
	private String observacionesInformeTecnico;
	@Column(name="concepto_tecnico")
	private Integer conceptoTecnico;
	@Column(name="descripcion_ampliacion")
	private String descripcionAmpliacion;
	@Column(name="descripcion_del_proyecto")
	private String descripcionDelProyecto;
	@Column(name="consideraciones_del_avaluo")
	private String consideracionesDelAvaluo;
	@Column(name = "documentos_consultados")
	private String documentosConsultados;
	@Column(name = "fecha_aporte_de_documentos")
	private Date fechaAporteDeDocumentos;
	@Column(name = "otros_documentos")
	private String otrosDocumentos;
	@Column(name = "observaciones_de_titulacion")
	private String observacionesDeTitulacion;
	@Column(name = "remosion_masas")
	private Boolean remosionMasas;
	@Column(name = "inundacion")
	private Boolean inundacion;
	@Column(name = "orden_publico")
	private Boolean ordenPublico;
	@Column(name = "otro")
	private Boolean otro;
	@Column(name = "obs_remosion_masas")
	private String obsRemosionMasas;
	@Column(name = "obs_inundacion")
	private String obsInundacion;
	@Column(name = "obs_orden_publico")
	private String obsOrdenPublico;
	@Column(name = "obs_otro")
	private String obsOtro;
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "cronograma_obra_id", nullable=true)
	private CronogramaObra cronogramaObra;
	@OneToMany(mappedBy="avaluo", fetch=FetchType.LAZY)
	private Set<AumentoPresupuesto> aumentosPresupuesto;
	@OneToMany(mappedBy="avaluo", fetch=FetchType.LAZY)
	private Set<Prorroga> prorrogas;
	
	public AvaluoConstructor() {
		super();
	}

	public AvaluoConstructor(String tipoDeConstruccion, String vereda,
			String nombreDePredio, String localizacion,
			String descripcionGeneralDelSector, Boolean alcantarilladoSector,
			Boolean aguaSector, Boolean energiaSector, Boolean gasSector,
			Boolean telefonoSector, String observacionesServiviosPublicos,
			Integer estrato, Boolean zonaObjetivo,
			String usoPredominanteDelSector,
			String observacionesUsoPredominanteDelSector,
			Integer perspectivasDeValorizacion,
			String observacionesPerspectivasDeValorizacion,
			String descripcionInmueble, String linderosParticularesDelInmueble,
			String fuente, String normatividadVigente,
			Integer ubicacionInmediataDelInmueble,
			String observacionesUbicacionInmediataDelInmueble, String relieve,
			String formaGeometrica, Boolean regular, Boolean irregular,
			BigDecimal frente, BigDecimal fondo, BigDecimal relacionFrente,
			Integer usoInmueble, Integer claseInmueble, String estadoInmueble, Long tipoInmuebleId,
			Integer ubicacionLocal, Boolean requiereReparaciones,
			String cualesReaparaciones, Boolean alcantarillado,
			Boolean energia, Boolean agua, Boolean telefono, Boolean gas,
			Integer conceptoDeComercializacion,
			String observacionesConceptoDeComercializacion,
			Boolean sometidoAPropiedadHorizontal, String conjuntoOAgrupacion,
			Integer numeroDeEdificios, Integer totalUnidades, String unidad,
			Integer unidadesPorPiso, Integer ubicacionPropiedadHorizontal, String registroDePropiedadHorizontal,
			Boolean zonasVerdes, Boolean piscina, Boolean salonSocial,
			Boolean juegosInfantiles, Boolean ascensor,
			Integer ubicacionDelSector, Integer vecindario,
			Integer desarrolloYProyeccionUrbana, Integer viasDeAcceso,
			Integer trasporteUrbano, Integer alumbradoYAlcantarillado,
			Integer callesYAceras, Integer proporcionZonaSocial,
			Integer proporcionZonaServicios, Integer acabados,
			Integer disenoYDistribucion, Integer estadoGeneralDelInmueble,
			String observacionesAnalisisTecnico, Integer banoServicios,
			Integer zonaDeRopas, Integer numeroDePisos,
			Integer numeroDeSotanos, Integer anoDeConstruccion, String pisos,
			String muros, String techos, String banos, String cocina, Integer habitaciones,
			Integer estarHabitacion, Integer cuartoServicio, Integer closet, Integer sala, Integer comedor,
			Integer banoPrivado, Integer banoSocial, Integer estudio, Integer balcon, Integer terraza,
			Integer patioInterior, Integer jardin, Integer zonaVerdePrivada,
			Integer local, Integer oficina, Integer bodega, Integer garajePrivado, Integer garajeExlusivo,
			Integer bahiaComunal, Boolean garajeDoble, Boolean garajeParalelo, Integer numeroTotalDeGarajes, Integer totalCuposDeParqueo,
			BigDecimal loteProyectoM2, BigDecimal valorLote, BigDecimal costosDirectos,
			BigDecimal porcentajeCostosDirectos, BigDecimal costosIndirectos,
			BigDecimal porcentajeCostosIndirectos,
			BigDecimal costosDelProyecto,
			BigDecimal porcentajeDeCostosDelProyecto,
			BigDecimal valorSolicitado, BigDecimal porcentajeAFinanciar,
			BigDecimal valorMaxAFinanciar, BigDecimal porcentajeFinanciado,
			Integer programacionEnMeses, BigDecimal factor,
			BigDecimal valorSolicitadoTotal, BigDecimal costosFinancieros,
			BigDecimal costosTotalDelProyecto,
			String observacionesInformeTecnico, Integer conceptoTecnico,
			String descripcionAmpliacion, String descripcionDelProyecto,
			String consideracionesDelAvaluo, String documentosConsultados, Date fechaAporteDeDocumentos, 
			String otrosDocumentos, String observacionesDeTitulacion,  
			Boolean remosionMasas, Boolean inundacion, Boolean ordenPublico, Boolean otro,
			String obsRemosionMasas, String obsInundacion, String obsOrdenPublico, String obsOtro,
			CronogramaObra cronogramaObra,
			Set<AumentoPresupuesto> aumentosPresupuesto, Set<Prorroga> prorrogas) {
		super();
		this.tipoDeConstruccion = tipoDeConstruccion;
		this.vereda = vereda;
		this.nombreDePredio = nombreDePredio;
		this.localizacion = localizacion;		
		this.descripcionGeneralDelSector = descripcionGeneralDelSector;
		this.alcantarilladoSector = alcantarilladoSector;
		this.aguaSector = aguaSector;
		this.energiaSector = energiaSector;
		this.gasSector = gasSector;
		this.telefonoSector = telefonoSector;
		this.observacionesServiviosPublicos = observacionesServiviosPublicos;
		this.estrato = estrato;
		this.zonaObjetivo = zonaObjetivo;
		this.usoPredominanteDelSector = usoPredominanteDelSector;
		this.observacionesUsoPredominanteDelSector = observacionesUsoPredominanteDelSector;
		this.perspectivasDeValorizacion = perspectivasDeValorizacion;
		this.observacionesPerspectivasDeValorizacion = observacionesPerspectivasDeValorizacion;
		this.descripcionInmueble = descripcionInmueble;
		this.linderosParticularesDelInmueble = linderosParticularesDelInmueble;
		this.fuente = fuente;
		this.normatividadVigente = normatividadVigente;
		this.ubicacionInmediataDelInmueble = ubicacionInmediataDelInmueble;
		this.observacionesUbicacionInmediataDelInmueble = observacionesUbicacionInmediataDelInmueble;
		this.relieve = relieve;
		this.formaGeometrica = formaGeometrica;
		this.regular = regular;
		this.irregular = irregular;
		this.frente = frente;
		this.fondo = fondo;
		this.relacionFrente = relacionFrente;
		this.usoInmueble = usoInmueble;
		this.claseInmueble = claseInmueble;
		this.estadoInmueble = estadoInmueble;
		this.ubicacionLocal = ubicacionLocal;
		this.requiereReparaciones = requiereReparaciones;
		this.cualesReaparaciones = cualesReaparaciones;
		this.alcantarillado = alcantarillado;
		this.energia = energia;
		this.agua = agua;
		this.telefono = telefono;
		this.gas = gas;
		this.conceptoDeComercializacion = conceptoDeComercializacion;
		this.observacionesConceptoDeComercializacion = observacionesConceptoDeComercializacion;
		this.sometidoAPropiedadHorizontal = sometidoAPropiedadHorizontal;
		this.conjuntoOAgrupacion = conjuntoOAgrupacion;
		this.numeroDeEdificios = numeroDeEdificios;
		this.totalUnidades = totalUnidades;
		this.unidad = unidad;
		this.unidadesPorPiso = unidadesPorPiso;
		this.ubicacionPropiedadHorizontal = ubicacionPropiedadHorizontal;
		this.registroDePropiedadHorizontal = registroDePropiedadHorizontal;
		this.zonasVerdes = zonasVerdes;
		this.piscina = piscina;
		this.salonSocial = salonSocial;
		this.juegosInfantiles = juegosInfantiles;
		this.ascensor = ascensor;
		this.ubicacionDelSector = ubicacionDelSector;
		this.vecindario = vecindario;
		this.desarrolloYProyeccionUrbana = desarrolloYProyeccionUrbana;
		this.viasDeAcceso = viasDeAcceso;
		this.trasporteUrbano = trasporteUrbano;
		this.alumbradoYAlcantarillado = alumbradoYAlcantarillado;
		this.callesYAceras = callesYAceras;
		this.proporcionZonaSocial = proporcionZonaSocial;
		this.proporcionZonaServicios = proporcionZonaServicios;
		this.acabados = acabados;
		this.disenoYDistribucion = disenoYDistribucion;
		this.estadoGeneralDelInmueble = estadoGeneralDelInmueble;
		this.observacionesAnalisisTecnico = observacionesAnalisisTecnico;
		this.banoServicios = banoServicios;
		this.zonaDeRopas = zonaDeRopas;
		this.numeroDePisos = numeroDePisos;
		this.numeroDeSotanos = numeroDeSotanos;
		this.anoDeConstruccion = anoDeConstruccion;
		this.pisos = pisos;
		this.muros = muros;
		this.techos = techos;
		this.banos = banos;
		this.cocina = cocina;
		this.habitaciones = habitaciones;
		this.estarHabitacion = estarHabitacion;
		this.cuartoServicio = cuartoServicio;
		this.closet = closet;
		this.sala = sala;
		this.comedor = comedor;
		this.banoPrivado = banoPrivado;
		this.banoSocial = banoSocial;
		this.estudio = estudio;
		this.balcon = balcon;
		this.terraza = terraza;
		this.patioInterior = patioInterior;
		this.jardin = jardin;
		this.zonaVerdePrivada = zonaVerdePrivada;
		this.local = local;
		this.oficina = oficina;
		this.bodega = bodega;
		this.garajePrivado = garajePrivado;
		this.garajeExlusivo = garajeExlusivo;
		this.bahiaComunal = bahiaComunal;
		this.garajeDoble = garajeDoble;
		this.garajeParalelo = garajeParalelo;
		this.numeroTotalDeGarajes = numeroTotalDeGarajes;
		this.totalCuposDeParqueo = totalCuposDeParqueo;
		this.loteProyectoM2 = loteProyectoM2;
		this.valorLote = valorLote;
		this.costosDirectos = costosDirectos;
		this.porcentajeCostosDirectos = porcentajeCostosDirectos;
		this.costosIndirectos = costosIndirectos;
		this.porcentajeCostosIndirectos = porcentajeCostosIndirectos;
		this.costosDelProyecto = costosDelProyecto;
		this.porcentajeDeCostosDelProyecto = porcentajeDeCostosDelProyecto;
		this.valorSolicitado = valorSolicitado;
		this.porcentajeAFinanciar = porcentajeAFinanciar;
		this.valorMaxAFinanciar = valorMaxAFinanciar;
		this.porcentajeFinanciado = porcentajeFinanciado;
		this.programacionEnMeses = programacionEnMeses;
		this.factor = factor;
		this.valorSolicitadoTotal = valorSolicitadoTotal;
		this.costosFinancieros = costosFinancieros;
		this.costosTotalDelProyecto = costosTotalDelProyecto;
		this.observacionesInformeTecnico = observacionesInformeTecnico;
		this.conceptoTecnico = conceptoTecnico;
		this.descripcionAmpliacion = descripcionAmpliacion;
		this.descripcionDelProyecto = descripcionDelProyecto;
		this.consideracionesDelAvaluo = consideracionesDelAvaluo;
		this.documentosConsultados = documentosConsultados;
		this.fechaAporteDeDocumentos = fechaAporteDeDocumentos;
		this.otrosDocumentos = otrosDocumentos;
		this.observacionesDeTitulacion = observacionesDeTitulacion;
		this.remosionMasas = remosionMasas;
		this.inundacion = inundacion;
		this.ordenPublico = ordenPublico;
		this.otro = otro;
		this.obsRemosionMasas = obsRemosionMasas;
		this.obsInundacion = obsInundacion;
		this.obsOrdenPublico = obsOrdenPublico;
		this.obsOtro = obsOtro;
		this.cronogramaObra = cronogramaObra;
		this.aumentosPresupuesto = aumentosPresupuesto;
		this.prorrogas = prorrogas;
	}

	public void update(String tipoDeConstruccion, String vereda,
			String nombreDePredio, String localizacion,
			String descripcionGeneralDelSector, Boolean alcantarilladoSector,
			Boolean aguaSector, Boolean energiaSector, Boolean gasSector,
			Boolean telefonoSector, String observacionesServiviosPublicos,
			Integer estrato, Boolean zonaObjetivo,
			String usoPredominanteDelSector,
			String observacionesUsoPredominanteDelSector,
			Integer perspectivasDeValorizacion,
			String observacionesPerspectivasDeValorizacion,
			String descripcionInmueble, String linderosParticularesDelInmueble,
			String fuente, String normatividadVigente,
			Integer ubicacionInmediataDelInmueble,
			String observacionesUbicacionInmediataDelInmueble, String relieve,
			String formaGeometrica, Boolean regular, Boolean irregular,
			BigDecimal frente, BigDecimal fondo, BigDecimal relacionFrente,
			Integer usoInmueble, Integer claseInmueble, String estadoInmueble, Long tipoInmuebleId,
			Integer ubicacionLocal, Boolean requiereReparaciones,
			String cualesReaparaciones, Boolean alcantarillado,
			Boolean energia, Boolean agua, Boolean telefono, Boolean gas,
			Integer conceptoDeComercializacion,
			String observacionesConceptoDeComercializacion,
			Boolean sometidoAPropiedadHorizontal, String conjuntoOAgrupacion,
			Integer numeroDeEdificios, Integer totalUnidades, String unidad,
			Integer unidadesPorPiso, Integer ubicacionPropiedadHorizontal, String registroDePropiedadHorizontal,
			Boolean zonasVerdes, Boolean piscina, Boolean salonSocial,
			Boolean juegosInfantiles, Boolean ascensor,
			Integer ubicacionDelSector, Integer vecindario,
			Integer desarrolloYProyeccionUrbana, Integer viasDeAcceso,
			Integer trasporteUrbano, Integer alumbradoYAlcantarillado,
			Integer callesYAceras, Integer proporcionZonaSocial,
			Integer proporcionZonaServicios, Integer acabados,
			Integer disenoYDistribucion, Integer estadoGeneralDelInmueble,
			String observacionesAnalisisTecnico, Integer banoServicios,
			Integer zonaDeRopas, Integer numeroDePisos,
			Integer numeroDeSotanos, Integer anoDeConstruccion, String pisos,
			String muros, String techos, String banos, String cocina, Integer habitaciones,
			Integer estarHabitacion, Integer cuartoServicio, Integer closet, Integer sala, Integer comedor,
			Integer banoPrivado, Integer banoSocial, Integer estudio, Integer balcon, Integer terraza,
			Integer patioInterior, Integer jardin, Integer zonaVerdePrivada, 
			Integer local, Integer oficina, Integer bodega, Integer garajePrivado, Integer garajeExlusivo,
			Integer bahiaComunal, Boolean garajeDoble, Boolean garajeParalelo, Integer numeroTotalDeGarajes, String noDeParqueadero,
			String matriculaInmobiliariaGaraje, Integer totalCuposDeParqueo,
			Integer totalGarajes, Boolean depositoPrivado,
			Boolean depositoExlusivo, String noDeDeposito,
			String matriculaInmobiliariaDeposito,Integer totalDepositos ,BigDecimal loteProyectoM2,
			BigDecimal valorLote, BigDecimal costosDirectos,
			BigDecimal porcentajeCostosDirectos, BigDecimal costosIndirectos,
			BigDecimal porcentajeCostosIndirectos,
			BigDecimal costosDelProyecto,
			BigDecimal porcentajeDeCostosDelProyecto,
			BigDecimal valorSolicitado, BigDecimal porcentajeAFinanciar,
			BigDecimal valorMaxAFinanciar, BigDecimal porcentajeFinanciado,
			Integer programacionEnMeses, BigDecimal factor,
			BigDecimal valorSolicitadoTotal, BigDecimal costosFinancieros,
			BigDecimal costosTotalDelProyecto,
			String observacionesInformeTecnico, Integer conceptoTecnico,
			String descripcionAmpliacion, String descripcionDelProyecto,
			String consideracionesDelAvaluo, String documentosConsultados, Date fechaAporteDeDocumentos, 
			String otrosDocumentos, String observacionesDeTitulacion, 
			Boolean remosionMasas, Boolean inundacion, Boolean ordenPublico, Boolean otro,
			String obsRemosionMasas, String obsInundacion, String obsOrdenPublico, String obsOtro,
			CronogramaObra cronogramaObra,
			Set<AumentoPresupuesto> aumentosPresupuesto, Set<Prorroga> prorrogas) {
		this.tipoDeConstruccion = tipoDeConstruccion;
		this.vereda = vereda;
		this.nombreDePredio = nombreDePredio;
		this.localizacion = localizacion;
		this.descripcionGeneralDelSector = descripcionGeneralDelSector;
		this.alcantarilladoSector = alcantarilladoSector;
		this.aguaSector = aguaSector;
		this.energiaSector = energiaSector;
		this.gasSector = gasSector;
		this.telefonoSector = telefonoSector;
		this.observacionesServiviosPublicos = observacionesServiviosPublicos;
		this.estrato = estrato;
		this.zonaObjetivo = zonaObjetivo;
		this.usoPredominanteDelSector = usoPredominanteDelSector;
		this.observacionesUsoPredominanteDelSector = observacionesUsoPredominanteDelSector;
		this.perspectivasDeValorizacion = perspectivasDeValorizacion;
		this.observacionesPerspectivasDeValorizacion = observacionesPerspectivasDeValorizacion;
		this.descripcionInmueble = descripcionInmueble;
		this.linderosParticularesDelInmueble = linderosParticularesDelInmueble;
		this.fuente = fuente;
		this.normatividadVigente = normatividadVigente;
		this.ubicacionInmediataDelInmueble = ubicacionInmediataDelInmueble;
		this.observacionesUbicacionInmediataDelInmueble = observacionesUbicacionInmediataDelInmueble;
		this.relieve = relieve;
		this.formaGeometrica = formaGeometrica;
		this.regular = regular;
		this.irregular = irregular;
		this.frente = frente;
		this.fondo = fondo;
		this.relacionFrente = relacionFrente;
		this.usoInmueble = usoInmueble;
		this.claseInmueble = claseInmueble;
		this.estadoInmueble = estadoInmueble;
		this.ubicacionLocal = ubicacionLocal;
		this.requiereReparaciones = requiereReparaciones;
		this.cualesReaparaciones = cualesReaparaciones;
		this.alcantarillado = alcantarillado;
		this.energia = energia;
		this.agua = agua;
		this.telefono = telefono;
		this.gas = gas;
		this.conceptoDeComercializacion = conceptoDeComercializacion;
		this.observacionesConceptoDeComercializacion = observacionesConceptoDeComercializacion;
		this.sometidoAPropiedadHorizontal = sometidoAPropiedadHorizontal;
		this.conjuntoOAgrupacion = conjuntoOAgrupacion;
		this.numeroDeEdificios = numeroDeEdificios;
		this.totalUnidades = totalUnidades;
		this.unidad = unidad;
		this.unidadesPorPiso = unidadesPorPiso;
		this.ubicacionPropiedadHorizontal = ubicacionPropiedadHorizontal;
		this.registroDePropiedadHorizontal = registroDePropiedadHorizontal;
		this.zonasVerdes = zonasVerdes;
		this.piscina = piscina;
		this.salonSocial = salonSocial;
		this.juegosInfantiles = juegosInfantiles;
		this.ascensor = ascensor;
		this.ubicacionDelSector = ubicacionDelSector;
		this.vecindario = vecindario;
		this.desarrolloYProyeccionUrbana = desarrolloYProyeccionUrbana;
		this.viasDeAcceso = viasDeAcceso;
		this.trasporteUrbano = trasporteUrbano;
		this.alumbradoYAlcantarillado = alumbradoYAlcantarillado;
		this.callesYAceras = callesYAceras;
		this.proporcionZonaSocial = proporcionZonaSocial;
		this.proporcionZonaServicios = proporcionZonaServicios;
		this.acabados = acabados;
		this.disenoYDistribucion = disenoYDistribucion;
		this.estadoGeneralDelInmueble = estadoGeneralDelInmueble;
		this.observacionesAnalisisTecnico = observacionesAnalisisTecnico;
		this.banoServicios = banoServicios;
		this.zonaDeRopas = zonaDeRopas;
		this.numeroDePisos = numeroDePisos;
		this.numeroDeSotanos = numeroDeSotanos;
		this.anoDeConstruccion = anoDeConstruccion;
		this.pisos = pisos;
		this.muros = muros;
		this.techos = techos;
		this.banos = banos;
		this.cocina = cocina;
		this.habitaciones = habitaciones;
		this.estarHabitacion = estarHabitacion;
		this.cuartoServicio = cuartoServicio;
		this.closet = closet;
		this.sala = sala;
		this.comedor = comedor;
		this.banoPrivado = banoPrivado;
		this.banoSocial = banoSocial;
		this.estudio = estudio;
		this.balcon = balcon;
		this.terraza = terraza;
		this.patioInterior = patioInterior;
		this.jardin = jardin;
		this.zonaVerdePrivada = zonaVerdePrivada;
		this.local = local;
		this.oficina = oficina;
		this.bodega = bodega;
		this.garajePrivado = garajePrivado;
		this.garajeExlusivo = garajeExlusivo;
		this.bahiaComunal = bahiaComunal;
		this.garajeDoble = garajeDoble;
		this.garajeParalelo = garajeParalelo;
		this.numeroTotalDeGarajes = numeroTotalDeGarajes;
		this.totalCuposDeParqueo = totalCuposDeParqueo;
		this.loteProyectoM2 = loteProyectoM2;
		this.valorLote = valorLote;
		this.costosDirectos = costosDirectos;
		this.porcentajeCostosDirectos = porcentajeCostosDirectos;
		this.costosIndirectos = costosIndirectos;
		this.porcentajeCostosIndirectos = porcentajeCostosIndirectos;
		this.costosDelProyecto = costosDelProyecto;
		this.porcentajeDeCostosDelProyecto = porcentajeDeCostosDelProyecto;
		this.valorSolicitado = valorSolicitado;
		this.porcentajeAFinanciar = porcentajeAFinanciar;
		this.valorMaxAFinanciar = valorMaxAFinanciar;
		this.porcentajeFinanciado = porcentajeFinanciado;
		this.programacionEnMeses = programacionEnMeses;
		this.factor = factor;
		this.valorSolicitadoTotal = valorSolicitadoTotal;
		this.costosFinancieros = costosFinancieros;
		this.costosTotalDelProyecto = costosTotalDelProyecto;
		this.observacionesInformeTecnico = observacionesInformeTecnico;
		this.conceptoTecnico = conceptoTecnico;
		this.descripcionAmpliacion = descripcionAmpliacion;
		this.descripcionDelProyecto = descripcionDelProyecto;
		this.consideracionesDelAvaluo = consideracionesDelAvaluo;
		this.documentosConsultados = documentosConsultados;
		this.fechaAporteDeDocumentos = fechaAporteDeDocumentos;
		this.otrosDocumentos = otrosDocumentos;
		this.observacionesDeTitulacion = observacionesDeTitulacion;
		this.remosionMasas = remosionMasas;
		this.inundacion = inundacion;
		this.ordenPublico = ordenPublico;
		this.otro = otro;
		this.obsRemosionMasas = obsRemosionMasas;
		this.obsInundacion = obsInundacion;
		this.obsOrdenPublico = obsOrdenPublico;
		this.obsOtro = obsOtro;
		this.cronogramaObra = cronogramaObra;
		this.aumentosPresupuesto = aumentosPresupuesto;
		this.prorrogas = prorrogas;
	}

	public String getTipoDeConstruccion() {
		return tipoDeConstruccion;
	}

	public void setTipoDeConstruccion(String tipoDeConstruccion) {
		this.tipoDeConstruccion = tipoDeConstruccion;
	}

	public String getVereda() {
		return vereda;
	}

	public void setVereda(String vereda) {
		this.vereda = vereda;
	}

	public String getNombreDePredio() {
		return nombreDePredio;
	}

	public void setNombreDePredio(String nombreDePredio) {
		this.nombreDePredio = nombreDePredio;
	}

	public String getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	public String getDescripcionGeneralDelSector() {
		return descripcionGeneralDelSector;
	}

	public void setDescripcionGeneralDelSector(String descripcionGeneralDelSector) {
		this.descripcionGeneralDelSector = descripcionGeneralDelSector;
	}

	public Boolean getAlcantarilladoSector() {
		return alcantarilladoSector;
	}

	public void setAlcantarilladoSector(Boolean alcantarilladoSector) {
		this.alcantarilladoSector = alcantarilladoSector;
	}

	public Boolean getAguaSector() {
		return aguaSector;
	}

	public void setAguaSector(Boolean aguaSector) {
		this.aguaSector = aguaSector;
	}

	public Boolean getEnergiaSector() {
		return energiaSector;
	}

	public void setEnergiaSector(Boolean energiaSector) {
		this.energiaSector = energiaSector;
	}

	public Boolean getGasSector() {
		return gasSector;
	}

	public void setGasSector(Boolean gasSector) {
		this.gasSector = gasSector;
	}

	public Boolean getTelefonoSector() {
		return telefonoSector;
	}

	public void setTelefonoSector(Boolean telefonoSector) {
		this.telefonoSector = telefonoSector;
	}

	public String getObservacionesServiviosPublicos() {
		return observacionesServiviosPublicos;
	}

	public void setObservacionesServiviosPublicos(
			String observacionesServiviosPublicos) {
		this.observacionesServiviosPublicos = observacionesServiviosPublicos;
	}

	public Integer getEstrato() {
		return estrato;
	}

	public void setEstrato(Integer estrato) {
		this.estrato = estrato;
	}

	public Boolean getZonaObjetivo() {
		return zonaObjetivo;
	}

	public void setZonaObjetivo(Boolean zonaObjetivo) {
		this.zonaObjetivo = zonaObjetivo;
	}

	public String getUsoPredominanteDelSector() {
		return usoPredominanteDelSector;
	}

	public void setUsoPredominanteDelSector(String usoPredominanteDelSector) {
		this.usoPredominanteDelSector = usoPredominanteDelSector;
	}

	public String getObservacionesUsoPredominanteDelSector() {
		return observacionesUsoPredominanteDelSector;
	}

	public void setObservacionesUsoPredominanteDelSector(
			String observacionesUsoPredominanteDelSector) {
		this.observacionesUsoPredominanteDelSector = observacionesUsoPredominanteDelSector;
	}

	public Integer getPerspectivasDeValorizacion() {
		return perspectivasDeValorizacion;
	}

	public void setPerspectivasDeValorizacion(Integer perspectivasDeValorizacion) {
		this.perspectivasDeValorizacion = perspectivasDeValorizacion;
	}

	public String getObservacionesPerspectivasDeValorizacion() {
		return observacionesPerspectivasDeValorizacion;
	}

	public void setObservacionesPerspectivasDeValorizacion(
			String observacionesPerspectivasDeValorizacion) {
		this.observacionesPerspectivasDeValorizacion = observacionesPerspectivasDeValorizacion;
	}

	public String getDescripcionInmueble() {
		return descripcionInmueble;
	}

	public void setDescripcionInmueble(String descripcionInmueble) {
		this.descripcionInmueble = descripcionInmueble;
	}

	public String getLinderosParticularesDelInmueble() {
		return linderosParticularesDelInmueble;
	}

	public void setLinderosParticularesDelInmueble(
			String linderosParticularesDelInmueble) {
		this.linderosParticularesDelInmueble = linderosParticularesDelInmueble;
	}

	public String getFuente() {
		return fuente;
	}

	public void setFuente(String fuente) {
		this.fuente = fuente;
	}

	public String getNormatividadVigente() {
		return normatividadVigente;
	}

	public void setNormatividadVigente(String normatividadVigente) {
		this.normatividadVigente = normatividadVigente;
	}

	public Integer getUbicacionInmediataDelInmueble() {
		return ubicacionInmediataDelInmueble;
	}

	public void setUbicacionInmediataDelInmueble(
			Integer ubicacionInmediataDelInmueble) {
		this.ubicacionInmediataDelInmueble = ubicacionInmediataDelInmueble;
	}

	public String getObservacionesUbicacionInmediataDelInmueble() {
		return observacionesUbicacionInmediataDelInmueble;
	}

	public void setObservacionesUbicacionInmediataDelInmueble(
			String observacionesUbicacionInmediataDelInmueble) {
		this.observacionesUbicacionInmediataDelInmueble = observacionesUbicacionInmediataDelInmueble;
	}

	public String getRelieve() {
		return relieve;
	}

	public void setRelieve(String relieve) {
		this.relieve = relieve;
	}

	public String getFormaGeometrica() {
		return formaGeometrica;
	}

	public void setFormaGeometrica(String formaGeometrica) {
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

	public BigDecimal getRelacionFrente() {
		return relacionFrente;
	}

	public void setRelacionFrente(BigDecimal relacionFrente) {
		this.relacionFrente = relacionFrente;
	}

	public Integer getUsoInmueble() {
		return usoInmueble;
	}

	public void setUsoInmueble(Integer usoInmueble) {
		this.usoInmueble = usoInmueble;
	}

	public Integer getClaseInmueble() {
		return claseInmueble;
	}

	public void setClaseInmueble(Integer claseInmueble) {
		this.claseInmueble = claseInmueble;
	}

	public String getEstadoInmueble() {
		return estadoInmueble;
	}

	public void setEstadoInmueble(String estadoInmueble) {
		this.estadoInmueble = estadoInmueble;
	}

	public Integer getUbicacionLocal() {
		return ubicacionLocal;
	}

	public void setUbicacionLocal(Integer ubicacionLocal) {
		this.ubicacionLocal = ubicacionLocal;
	}

	public Boolean getRequiereReparaciones() {
		return requiereReparaciones;
	}

	public void setRequiereReparaciones(Boolean requiereReparaciones) {
		this.requiereReparaciones = requiereReparaciones;
	}

	public String getCualesReaparaciones() {
		return cualesReaparaciones;
	}

	public void setCualesReaparaciones(String cualesReaparaciones) {
		this.cualesReaparaciones = cualesReaparaciones;
	}

	public Boolean getAlcantarillado() {
		return alcantarillado;
	}

	public void setAlcantarillado(Boolean alcantarillado) {
		this.alcantarillado = alcantarillado;
	}

	public Boolean getEnergia() {
		return energia;
	}

	public void setEnergia(Boolean energia) {
		this.energia = energia;
	}

	public Boolean getAgua() {
		return agua;
	}

	public void setAgua(Boolean agua) {
		this.agua = agua;
	}

	public Boolean getTelefono() {
		return telefono;
	}

	public void setTelefono(Boolean telefono) {
		this.telefono = telefono;
	}

	public Integer getConceptoDeComercializacion() {
		return conceptoDeComercializacion;
	}

	public void setConceptoDeComercializacion(Integer conceptoDeComercializacion) {
		this.conceptoDeComercializacion = conceptoDeComercializacion;
	}

	public String getObservacionesConceptoDeComercializacion() {
		return observacionesConceptoDeComercializacion;
	}

	public void setObservacionesConceptoDeComercializacion(
			String observacionesConceptoDeComercializacion) {
		this.observacionesConceptoDeComercializacion = observacionesConceptoDeComercializacion;
	}

	public Boolean getSometidoAPropiedadHorizontal() {
		return sometidoAPropiedadHorizontal;
	}

	public void setSometidoAPropiedadHorizontal(Boolean sometidoAPropiedadHorizontal) {
		this.sometidoAPropiedadHorizontal = sometidoAPropiedadHorizontal;
	}

	public String getConjuntoOAgrupacion() {
		return conjuntoOAgrupacion;
	}

	public void setConjuntoOAgrupacion(String conjuntoOAgrupacion) {
		this.conjuntoOAgrupacion = conjuntoOAgrupacion;
	}

	public Integer getNumeroDeEdificios() {
		return numeroDeEdificios;
	}

	public void setNumeroDeEdificios(Integer numeroDeEdificios) {
		this.numeroDeEdificios = numeroDeEdificios;
	}

	public Integer getTotalUnidades() {
		return totalUnidades;
	}

	public void setTotalUnidades(Integer totalUnidades) {
		this.totalUnidades = totalUnidades;
	}

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	public Integer getUnidadesPorPiso() {
		return unidadesPorPiso;
	}

	public void setUnidadesPorPiso(Integer unidadesPorPiso) {
		this.unidadesPorPiso = unidadesPorPiso;
	}
	
	public Integer getUbicacionPropiedadHorizontal() {
		return ubicacionPropiedadHorizontal;
	}

	public void setUbicacionPropiedadHorizontal(Integer ubicacionPropiedadHorizontal) {
		this.ubicacionPropiedadHorizontal = ubicacionPropiedadHorizontal;
	}

	public String getRegistroDePropiedadHorizontal() {
		return registroDePropiedadHorizontal;
	}

	public void setRegistroDePropiedadHorizontal(
			String registroDePropiedadHorizontal) {
		this.registroDePropiedadHorizontal = registroDePropiedadHorizontal;
	}

	public Boolean getZonasVerdes() {
		return zonasVerdes;
	}

	public void setZonasVerdes(Boolean zonasVerdes) {
		this.zonasVerdes = zonasVerdes;
	}

	public Boolean getPiscina() {
		return piscina;
	}

	public void setPiscina(Boolean piscina) {
		this.piscina = piscina;
	}

	public Boolean getSalonSocial() {
		return salonSocial;
	}

	public void setSalonSocial(Boolean salonSocial) {
		this.salonSocial = salonSocial;
	}

	public Boolean getJuegosInfantiles() {
		return juegosInfantiles;
	}

	public void setJuegosInfantiles(Boolean juegosInfantiles) {
		this.juegosInfantiles = juegosInfantiles;
	}

	public Boolean getAscensor() {
		return ascensor;
	}

	public void setAscensor(Boolean ascensor) {
		this.ascensor = ascensor;
	}

	public Integer getUbicacionDelSector() {
		return ubicacionDelSector;
	}

	public void setUbicacionDelSector(Integer ubicacionDelSector) {
		this.ubicacionDelSector = ubicacionDelSector;
	}

	public Integer getVecindario() {
		return vecindario;
	}

	public void setVecindario(Integer vecindario) {
		this.vecindario = vecindario;
	}

	public Integer getDesarrolloYProyeccionUrbana() {
		return desarrolloYProyeccionUrbana;
	}

	public void setDesarrolloYProyeccionUrbana(Integer desarrolloYProyeccionUrbana) {
		this.desarrolloYProyeccionUrbana = desarrolloYProyeccionUrbana;
	}

	public Integer getViasDeAcceso() {
		return viasDeAcceso;
	}

	public void setViasDeAcceso(Integer viasDeAcceso) {
		this.viasDeAcceso = viasDeAcceso;
	}

	public Integer getTrasporteUrbano() {
		return trasporteUrbano;
	}

	public void setTrasporteUrbano(Integer trasporteUrbano) {
		this.trasporteUrbano = trasporteUrbano;
	}

	public Integer getAlumbradoYAlcantarillado() {
		return alumbradoYAlcantarillado;
	}

	public void setAlumbradoYAlcantarillado(Integer alumbradoYAlcantarillado) {
		this.alumbradoYAlcantarillado = alumbradoYAlcantarillado;
	}

	public Integer getCallesYAceras() {
		return callesYAceras;
	}

	public void setCallesYAceras(Integer callesYAceras) {
		this.callesYAceras = callesYAceras;
	}

	public Integer getProporcionZonaSocial() {
		return proporcionZonaSocial;
	}

	public void setProporcionZonaSocial(Integer proporcionZonaSocial) {
		this.proporcionZonaSocial = proporcionZonaSocial;
	}

	public Integer getProporcionZonaServicios() {
		return proporcionZonaServicios;
	}

	public void setProporcionZonaServicios(Integer proporcionZonaServicios) {
		this.proporcionZonaServicios = proporcionZonaServicios;
	}

	public Integer getAcabados() {
		return acabados;
	}

	public void setAcabados(Integer acabados) {
		this.acabados = acabados;
	}

	public Integer getDisenoYDistribucion() {
		return disenoYDistribucion;
	}

	public void setDisenoYDistribucion(Integer disenoYDistribucion) {
		this.disenoYDistribucion = disenoYDistribucion;
	}

	public Integer getEstadoGeneralDelInmueble() {
		return estadoGeneralDelInmueble;
	}

	public void setEstadoGeneralDelInmueble(Integer estadoGeneralDelInmueble) {
		this.estadoGeneralDelInmueble = estadoGeneralDelInmueble;
	}

	public String getObservacionesAnalisisTecnico() {
		return observacionesAnalisisTecnico;
	}

	public void setObservacionesAnalisisTecnico(String observacionesAnalisisTecnico) {
		this.observacionesAnalisisTecnico = observacionesAnalisisTecnico;
	}

	public Integer getBanoServicios() {
		return banoServicios;
	}

	public void setBanoServicios(Integer banoServicios) {
		this.banoServicios = banoServicios;
	}

	public Integer getZonaDeRopas() {
		return zonaDeRopas;
	}

	public void setZonaDeRopas(Integer zonaDeRopas) {
		this.zonaDeRopas = zonaDeRopas;
	}

	public Integer getNumeroDePisos() {
		return numeroDePisos;
	}

	public void setNumeroDePisos(Integer numeroDePisos) {
		this.numeroDePisos = numeroDePisos;
	}

	public Integer getNumeroDeSotanos() {
		return numeroDeSotanos;
	}

	public void setNumeroDeSotanos(Integer numeroDeSotanos) {
		this.numeroDeSotanos = numeroDeSotanos;
	}

	public Integer getAnoDeConstruccion() {
		return anoDeConstruccion;
	}

	public void setAnoDeConstruccion(Integer anoDeConstruccion) {
		this.anoDeConstruccion = anoDeConstruccion;
	}

	public String getPisos() {
		return pisos;
	}

	public void setPisos(String pisos) {
		this.pisos = pisos;
	}

	public String getMuros() {
		return muros;
	}

	public void setMuros(String muros) {
		this.muros = muros;
	}

	public String getTechos() {
		return techos;
	}

	public void setTechos(String techos) {
		this.techos = techos;
	}

	public String getBanos() {
		return banos;
	}

	public void setBanos(String banos) {
		this.banos = banos;
	}

	public String getCocina() {
		return cocina;
	}

	public void setCocina(String cocina) {
		this.cocina = cocina;
	}

	public Integer getGarajePrivado() {
		return garajePrivado;
	}

	public void setGarajePrivado(Integer garajePrivado) {
		this.garajePrivado = garajePrivado;
	}

	public Integer getGarajeExlusivo() {
		return garajeExlusivo;
	}

	public void setGarajeExlusivo(Integer garajeExlusivo) {
		this.garajeExlusivo = garajeExlusivo;
	}

	public Integer getBahiaComunal() {
		return bahiaComunal;
	}

	public void setBahiaComunal(Integer bahiaComunal) {
		this.bahiaComunal = bahiaComunal;
	}
	
	public Boolean getGarajeDoble() {
		return garajeDoble;
	}

	public void setGarajeDoble(Boolean garajeDoble) {
		this.garajeDoble = garajeDoble;
	}

	public Boolean getGarajeParalelo() {
		return garajeParalelo;
	}

	public void setGarajeParalelo(Boolean garajeParalelo) {
		this.garajeParalelo = garajeParalelo;
	}
	
	public Integer getNumeroTotalDeGarajes() {
		return numeroTotalDeGarajes;
	}

	public void setNumeroTotalDeGarajes(Integer numeroTotalDeGarajes) {
		this.numeroTotalDeGarajes = numeroTotalDeGarajes;
	}

	public Integer getTotalCuposDeParqueo() {
		return totalCuposDeParqueo;
	}

	public void setTotalCuposDeParqueo(Integer totalCuposDeParqueo) {
		this.totalCuposDeParqueo = totalCuposDeParqueo;
	}

	public BigDecimal getLoteProyectoM2() {
		return loteProyectoM2;
	}

	public void setLoteProyectoM2(BigDecimal loteProyectoM2) {
		this.loteProyectoM2 = loteProyectoM2;
	}

	public BigDecimal getValorLote() {
		return valorLote;
	}

	public void setValorLote(BigDecimal valorLote) {
		this.valorLote = valorLote;
	}

	public BigDecimal getCostosDirectos() {
		return costosDirectos;
	}

	public void setCostosDirectos(BigDecimal costosDirectos) {
		this.costosDirectos = costosDirectos;
	}

	public BigDecimal getPorcentajeCostosDirectos() {
		return porcentajeCostosDirectos;
	}

	public void setPorcentajeCostosDirectos(BigDecimal porcentajeCostosDirectos) {
		this.porcentajeCostosDirectos = porcentajeCostosDirectos;
	}

	public BigDecimal getCostosIndirectos() {
		return costosIndirectos;
	}

	public void setCostosIndirectos(BigDecimal costosIndirectos) {
		this.costosIndirectos = costosIndirectos;
	}

	public BigDecimal getPorcentajeCostosIndirectos() {
		return porcentajeCostosIndirectos;
	}

	public void setPorcentajeCostosIndirectos(BigDecimal porcentajeCostosIndirectos) {
		this.porcentajeCostosIndirectos = porcentajeCostosIndirectos;
	}

	public BigDecimal getCostosDelProyecto() {
		return costosDelProyecto;
	}

	public void setCostosDelProyecto(BigDecimal costosDelProyecto) {
		this.costosDelProyecto = costosDelProyecto;
	}

	public BigDecimal getPorcentajeDeCostosDelProyecto() {
		return porcentajeDeCostosDelProyecto;
	}

	public void setPorcentajeDeCostosDelProyecto(
			BigDecimal porcentajeDeCostosDelProyecto) {
		this.porcentajeDeCostosDelProyecto = porcentajeDeCostosDelProyecto;
	}

	public BigDecimal getValorSolicitado() {
		return valorSolicitado;
	}

	public void setValorSolicitado(BigDecimal valorSolicitado) {
		this.valorSolicitado = valorSolicitado;
	}

	public BigDecimal getPorcentajeAFinanciar() {
		return porcentajeAFinanciar;
	}

	public void setPorcentajeAFinanciar(BigDecimal porcentajeAFinanciar) {
		this.porcentajeAFinanciar = porcentajeAFinanciar;
	}

	public BigDecimal getValorMaxAFinanciar() {
		return valorMaxAFinanciar;
	}

	public void setValorMaxAFinanciar(BigDecimal valorMaxAFinanciar) {
		this.valorMaxAFinanciar = valorMaxAFinanciar;
	}

	public BigDecimal getPorcentajeFinanciado() {
		return porcentajeFinanciado;
	}

	public void setPorcentajeFinanciado(BigDecimal porcentajeFinanciado) {
		this.porcentajeFinanciado = porcentajeFinanciado;
	}

	public Integer getProgramacionEnMeses() {
		return programacionEnMeses;
	}

	public void setProgramacionEnMeses(Integer programacionEnMeses) {
		this.programacionEnMeses = programacionEnMeses;
	}

	public BigDecimal getFactor() {
		return factor;
	}

	public void setFactor(BigDecimal factor) {
		this.factor = factor;
	}

	public BigDecimal getValorSolicitadoTotal() {
		return valorSolicitadoTotal;
	}

	public void setValorSolicitadoTotal(BigDecimal valorSolicitadoTotal) {
		this.valorSolicitadoTotal = valorSolicitadoTotal;
	}

	public BigDecimal getCostosFinancieros() {
		return costosFinancieros;
	}

	public void setCostosFinancieros(BigDecimal costosFinancieros) {
		this.costosFinancieros = costosFinancieros;
	}

	public BigDecimal getCostosTotalDelProyecto() {
		return costosTotalDelProyecto;
	}

	public void setCostosTotalDelProyecto(BigDecimal costosTotalDelProyecto) {
		this.costosTotalDelProyecto = costosTotalDelProyecto;
	}

	public String getObservacionesInformeTecnico() {
		return observacionesInformeTecnico;
	}

	public void setObservacionesInformeTecnico(String observacionesInformeTecnico) {
		this.observacionesInformeTecnico = observacionesInformeTecnico;
	}

	public Integer getConceptoTecnico() {
		return conceptoTecnico;
	}

	public void setConceptoTecnico(Integer conceptoTecnico) {
		this.conceptoTecnico = conceptoTecnico;
	}

	public String getDescripcionAmpliacion() {
		return descripcionAmpliacion;
	}

	public void setDescripcionAmpliacion(String descripcionAmpliacion) {
		this.descripcionAmpliacion = descripcionAmpliacion;
	}

	public String getDescripcionDelProyecto() {
		return descripcionDelProyecto;
	}

	public void setDescripcionDelProyecto(String descripcionDelProyecto) {
		this.descripcionDelProyecto = descripcionDelProyecto;
	}

	public String getConsideracionesDelAvaluo() {
		return consideracionesDelAvaluo;
	}

	public void setConsideracionesDelAvaluo(String consideracionesDelAvaluo) {
		this.consideracionesDelAvaluo = consideracionesDelAvaluo;
	}

	public CronogramaObra getCronogramaObra() {
		return cronogramaObra;
	}

	public void setCronogramaObra(CronogramaObra cronogramaObra) {
		this.cronogramaObra = cronogramaObra;
	}

	public Set<AumentoPresupuesto> getAumentosPresupuesto() {
		return aumentosPresupuesto;
	}

	public void setAumentosPresupuesto(Set<AumentoPresupuesto> aumentosPresupuesto) {
		this.aumentosPresupuesto = aumentosPresupuesto;
	}

	public Set<Prorroga> getProrrogas() {
		return prorrogas;
	}

	public void setProrrogas(Set<Prorroga> prorrogas) {
		this.prorrogas = prorrogas;
	}
	
	public Boolean getGas() {
		return gas;
	}

	public void setGas(Boolean gas) {
		this.gas = gas;
	}
	
	public String getMatriculaInmobiliariaGaraje1() {
		return matriculaInmobiliariaGaraje1;
	}

	public void setMatriculaInmobiliariaGaraje1(String matriculaInmobiliariaGaraje1) {
		this.matriculaInmobiliariaGaraje1 = matriculaInmobiliariaGaraje1;
	}

	public String getMatriculaInmobiliariaGaraje2() {
		return matriculaInmobiliariaGaraje2;
	}

	public void setMatriculaInmobiliariaGaraje2(String matriculaInmobiliariaGaraje2) {
		this.matriculaInmobiliariaGaraje2 = matriculaInmobiliariaGaraje2;
	}

	public String getMatriculaInmobiliariaGaraje3() {
		return matriculaInmobiliariaGaraje3;
	}

	public void setMatriculaInmobiliariaGaraje3(String matriculaInmobiliariaGaraje3) {
		this.matriculaInmobiliariaGaraje3 = matriculaInmobiliariaGaraje3;
	}

	public String getMatriculaInmobiliariaGaraje4() {
		return matriculaInmobiliariaGaraje4;
	}

	public void setMatriculaInmobiliariaGaraje4(String matriculaInmobiliariaGaraje4) {
		this.matriculaInmobiliariaGaraje4 = matriculaInmobiliariaGaraje4;
	}

	public String getMatriculaInmobiliariaGaraje5() {
		return matriculaInmobiliariaGaraje5;
	}

	public void setMatriculaInmobiliariaGaraje5(String matriculaInmobiliariaGaraje5) {
		this.matriculaInmobiliariaGaraje5 = matriculaInmobiliariaGaraje5;
	}
	
	public Boolean getGarajeParalelo1() {
		return garajeParalelo1;
	}

	public void setGarajeParalelo1(Boolean garajeParalelo1) {
		this.garajeParalelo1 = garajeParalelo1;
	}

	public Boolean getGarajeParalelo2() {
		return garajeParalelo2;
	}

	public void setGarajeParalelo2(Boolean garajeParalelo2) {
		this.garajeParalelo2 = garajeParalelo2;
	}

	public Boolean getGarajeParalelo3() {
		return garajeParalelo3;
	}

	public void setGarajeParalelo3(Boolean garajeParalelo3) {
		this.garajeParalelo3 = garajeParalelo3;
	}

	public Boolean getGarajeParalelo4() {
		return garajeParalelo4;
	}

	public void setGarajeParalelo4(Boolean garajeParalelo4) {
		this.garajeParalelo4 = garajeParalelo4;
	}

	public Boolean getGarajeParalelo5() {
		return garajeParalelo5;
	}

	public void setGarajeParalelo5(Boolean garajeParalelo5) {
		this.garajeParalelo5 = garajeParalelo5;
	}

	public Boolean getGarajeCubierto1() {
		return garajeCubierto1;
	}

	public void setGarajeCubierto1(Boolean garajeCubierto1) {
		this.garajeCubierto1 = garajeCubierto1;
	}

	public Boolean getGarajeCubierto2() {
		return garajeCubierto2;
	}

	public void setGarajeCubierto2(Boolean garajeCubierto2) {
		this.garajeCubierto2 = garajeCubierto2;
	}

	public Boolean getGarajeCubierto3() {
		return garajeCubierto3;
	}

	public void setGarajeCubierto3(Boolean garajeCubierto3) {
		this.garajeCubierto3 = garajeCubierto3;
	}

	public Boolean getGarajeCubierto4() {
		return garajeCubierto4;
	}

	public void setGarajeCubierto4(Boolean garajeCubierto4) {
		this.garajeCubierto4 = garajeCubierto4;
	}

	public Boolean getGarajeCubierto5() {
		return garajeCubierto5;
	}

	public void setGarajeCubierto5(Boolean garajeCubierto5) {
		this.garajeCubierto5 = garajeCubierto5;
	}

	public Boolean getGarajeServidumbre1() {
		return garajeServidumbre1;
	}

	public void setGarajeServidumbre1(Boolean garajeServidumbre1) {
		this.garajeServidumbre1 = garajeServidumbre1;
	}

	public Boolean getGarajeServidumbre2() {
		return garajeServidumbre2;
	}

	public void setGarajeServidumbre2(Boolean garajeServidumbre2) {
		this.garajeServidumbre2 = garajeServidumbre2;
	}

	public Boolean getGarajeServidumbre3() {
		return garajeServidumbre3;
	}

	public void setGarajeServidumbre3(Boolean garajeServidumbre3) {
		this.garajeServidumbre3 = garajeServidumbre3;
	}

	public Boolean getGarajeServidumbre4() {
		return garajeServidumbre4;
	}

	public void setGarajeServidumbre4(Boolean garajeServidumbre4) {
		this.garajeServidumbre4 = garajeServidumbre4;
	}

	public Boolean getGarajeServidumbre5() {
		return garajeServidumbre5;
	}

	public void setGarajeServidumbre5(Boolean garajeServidumbre5) {
		this.garajeServidumbre5 = garajeServidumbre5;
	}

	public Integer getNumeroParqueadero1() {
		return numeroParqueadero1;
	}

	public void setNumeroParqueadero1(Integer numeroParqueadero1) {
		this.numeroParqueadero1 = numeroParqueadero1;
	}

	public Integer getNumeroParqueadero2() {
		return numeroParqueadero2;
	}

	public void setNumeroParqueadero2(Integer numeroParqueadero2) {
		this.numeroParqueadero2 = numeroParqueadero2;
	}

	public Integer getNumeroParqueadero3() {
		return numeroParqueadero3;
	}

	public void setNumeroParqueadero3(Integer numeroParqueadero3) {
		this.numeroParqueadero3 = numeroParqueadero3;
	}

	public Integer getNumeroParqueadero4() {
		return numeroParqueadero4;
	}

	public void setNumeroParqueadero4(Integer numeroParqueadero4) {
		this.numeroParqueadero4 = numeroParqueadero4;
	}

	public Integer getNumeroParqueadero5() {
		return numeroParqueadero5;
	}

	public void setNumeroParqueadero5(Integer numeroParqueadero5) {
		this.numeroParqueadero5 = numeroParqueadero5;
	}

	public String getMatriculaInmobiliariaDeposito1() {
		return matriculaInmobiliariaDeposito1;
	}

	public void setMatriculaInmobiliariaDeposito1(
			String matriculaInmobiliariaDeposito1) {
		this.matriculaInmobiliariaDeposito1 = matriculaInmobiliariaDeposito1;
	}

	public String getMatriculaInmobiliariaDeposito2() {
		return matriculaInmobiliariaDeposito2;
	}

	public void setMatriculaInmobiliariaDeposito2(
			String matriculaInmobiliariaDeposito2) {
		this.matriculaInmobiliariaDeposito2 = matriculaInmobiliariaDeposito2;
	}

	public String getMatriculaInmobiliariaDeposito3() {
		return matriculaInmobiliariaDeposito3;
	}

	public void setMatriculaInmobiliariaDeposito3(
			String matriculaInmobiliariaDeposito3) {
		this.matriculaInmobiliariaDeposito3 = matriculaInmobiliariaDeposito3;
	}

	public String getMatriculaInmobiliariaDeposito4() {
		return matriculaInmobiliariaDeposito4;
	}

	public void setMatriculaInmobiliariaDeposito4(
			String matriculaInmobiliariaDeposito4) {
		this.matriculaInmobiliariaDeposito4 = matriculaInmobiliariaDeposito4;
	}

	public String getMatriculaInmobiliariaDeposito5() {
		return matriculaInmobiliariaDeposito5;
	}

	public void setMatriculaInmobiliariaDeposito5(
			String matriculaInmobiliariaDeposito5) {
		this.matriculaInmobiliariaDeposito5 = matriculaInmobiliariaDeposito5;
	}

	public Integer getNumeroDeposito1() {
		return numeroDeposito1;
	}

	public void setNumeroDeposito1(Integer numeroDeposito1) {
		this.numeroDeposito1 = numeroDeposito1;
	}

	public Integer getNumeroDeposito2() {
		return numeroDeposito2;
	}

	public void setNumeroDeposito2(Integer numeroDeposito2) {
		this.numeroDeposito2 = numeroDeposito2;
	}

	public Integer getNumeroDeposito3() {
		return numeroDeposito3;
	}

	public void setNumeroDeposito3(Integer numeroDeposito3) {
		this.numeroDeposito3 = numeroDeposito3;
	}

	public Integer getNumeroDeposito4() {
		return numeroDeposito4;
	}

	public void setNumeroDeposito4(Integer numeroDeposito4) {
		this.numeroDeposito4 = numeroDeposito4;
	}

	public Integer getNumeroDeposito5() {
		return numeroDeposito5;
	}

	public void setNumeroDeposito5(Integer numeroDeposito5) {
		this.numeroDeposito5 = numeroDeposito5;
	}

	public Integer getTotalCuposParquedaro() {
		return totalCuposParquedaro;
	}

	public void setTotalCuposParquedaro(Integer totalCuposParquedaro) {
		this.totalCuposParquedaro = totalCuposParquedaro;
	}

	public Integer getDepositosPrivados() {
		return depositosPrivados;
	}

	public void setDepositosPrivados(Integer depositosPrivados) {
		this.depositosPrivados = depositosPrivados;
	}

	public Integer getDepositosExclusivos() {
		return depositosExclusivos;
	}

	public void setDepositosExclusivos(Integer depositosExclusivos) {
		this.depositosExclusivos = depositosExclusivos;
	}

	public Integer getNumeroTotalDepositos() {
		return numeroTotalDepositos;
	}

	public void setNumeroTotalDepositos(Integer numeroTotalDepositos) {
		this.numeroTotalDepositos = numeroTotalDepositos;
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

	public Integer getHabitaciones() {
		return habitaciones;
	}

	public void setHabitaciones(Integer habitaciones) {
		this.habitaciones = habitaciones;
	}

	public Integer getEstarHabitacion() {
		return estarHabitacion;
	}

	public void setEstarHabitacion(Integer estarHabitacion) {
		this.estarHabitacion = estarHabitacion;
	}

	public Integer getCuartoServicio() {
		return cuartoServicio;
	}

	public void setCuartoServicio(Integer cuartoServicio) {
		this.cuartoServicio = cuartoServicio;
	}

	public Integer getCloset() {
		return closet;
	}

	public void setCloset(Integer closet) {
		this.closet = closet;
	}

	public Integer getSala() {
		return sala;
	}

	public void setSala(Integer sala) {
		this.sala = sala;
	}

	public Integer getBanoPrivado() {
		return banoPrivado;
	}

	public void setBanoPrivado(Integer banoPrivado) {
		this.banoPrivado = banoPrivado;
	}

	public Integer getBanoSocial() {
		return banoSocial;
	}

	public void setBanoSocial(Integer banoSocial) {
		this.banoSocial = banoSocial;
	}

	public Integer getEstudio() {
		return estudio;
	}

	public void setEstudio(Integer estudio) {
		this.estudio = estudio;
	}

	public Integer getTerraza() {
		return terraza;
	}

	public void setTerraza(Integer terraza) {
		this.terraza = terraza;
	}

	public Integer getPatioInterior() {
		return patioInterior;
	}

	public void setPatioInterior(Integer patioInterior) {
		this.patioInterior = patioInterior;
	}

	public Integer getJardin() {
		return jardin;
	}

	public void setJardin(Integer jardin) {
		this.jardin = jardin;
	}

	public Integer getZonaVerdePrivada() {
		return zonaVerdePrivada;
	}

	public void setZonaVerdePrivada(Integer zonaVerdePrivada) {
		this.zonaVerdePrivada = zonaVerdePrivada;
	}

	public Integer getLocal() {
		return local;
	}

	public void setLocal(Integer local) {
		this.local = local;
	}

	public Integer getOficina() {
		return oficina;
	}

	public void setOficina(Integer oficina) {
		this.oficina = oficina;
	}

	public Integer getBodega() {
		return bodega;
	}

	public void setBodega(Integer bodega) {
		this.bodega = bodega;
	}

	public Integer getComedor() {
		return comedor;
	}

	public void setComedor(Integer comedor) {
		this.comedor = comedor;
	}

	public Integer getBalcon() {
		return balcon;
	}

	public void setBalcon(Integer balcon) {
		this.balcon = balcon;
	}

	public Boolean getRemosionMasas() {
		return remosionMasas;
	}

	public void setRemosionMasas(Boolean remosionMasas) {
		this.remosionMasas = remosionMasas;
	}

	public Boolean getInundacion() {
		return inundacion;
	}

	public void setInundacion(Boolean inundacion) {
		this.inundacion = inundacion;
	}

	public Boolean getOrdenPublico() {
		return ordenPublico;
	}

	public void setOrdenPublico(Boolean ordenPublico) {
		this.ordenPublico = ordenPublico;
	}

	public Boolean getOtro() {
		return otro;
	}

	public void setOtro(Boolean otro) {
		this.otro = otro;
	}

	public String getObsRemosionMasas() {
		return obsRemosionMasas;
	}

	public void setObsRemosionMasas(String obsRemosionMasas) {
		this.obsRemosionMasas = obsRemosionMasas;
	}

	public String getObsInundacion() {
		return obsInundacion;
	}

	public void setObsInundacion(String obsInundacion) {
		this.obsInundacion = obsInundacion;
	}

	public String getObsOrdenPublico() {
		return obsOrdenPublico;
	}

	public void setObsOrdenPublico(String obsOrdenPublico) {
		this.obsOrdenPublico = obsOrdenPublico;
	}

	public String getObsOtro() {
		return obsOtro;
	}

	public void setObsOtro(String obsOtro) {
		this.obsOtro = obsOtro;
	}

}
