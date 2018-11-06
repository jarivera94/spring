package com.helio4.bancol.avaluos.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

public class AvaluoConstructorDTO extends AvaluoDTO {

	private static final long serialVersionUID = 1L;
	private String tipoDeConstruccion;
	private String vereda; // ok
	private String nombreDePredio; // ok
	private String localizacion; // ok
	private String descripcionGeneralDelSector; // ok
	private Boolean alcantarilladoSector; // ok
	private Boolean aguaSector; // ok
	private Boolean energiaSector; // ok
	private Boolean gasSector; // ok
	private Boolean telefonoSector; // ok
	private String observacionesServiviosPublicos;// ok
	private Integer estrato;// ok
	private Boolean zonaObjetivo;// ok
	private String usoPredominanteDelSector;// ok
	private String observacionesUsoPredominanteDelSector;// ok
	private Integer perspectivasDeValorizacion;// ok
	private String observacionesPerspectivasDeValorizacion;// ok
	private String descripcionInmueble;// ok
	private String linderosParticularesDelInmueble;// ok
	private String fuente;// ok
	private String normatividadVigente;// ok
	private Integer ubicacionInmediataDelInmueble;// ok
	private String observacionesUbicacionInmediataDelInmueble;// ok
	private String relieve;// ok
	private String formaGeometrica;// ok
	private Boolean regular;// ok
	private Boolean irregular;
	private BigDecimal frente;// ok
	private BigDecimal fondo;// ok
	private BigDecimal relacionFrente;// ok
	private Integer usoInmueble;// ok
	private Integer claseInmueble;// ok
	private String estadoInmueble;// ok
	private Integer ubicacionLocal;// ok
	private Boolean requiereReparaciones;// ok
	private String cualesReaparaciones;// ok
	private Boolean alcantarillado;// ok
	private Boolean energia;// ok
	private Boolean agua;// ok
	private Boolean telefono;// ok
	private Boolean gas; // ok
	private Integer conceptoDeComercializacion;// ok
	private String observacionesConceptoDeComercializacion;// ok
	private Boolean sometidoAPropiedadHorizontal;// ok
	private String conjuntoOAgrupacion;// ok
	private Integer numeroDeEdificios;// ok
	private Integer totalUnidades;// ok
	private String unidad;// ok
	private Integer unidadesPorPiso;// ok
	private Integer ubicacionPropiedadHorizontal;
	private String registroDePropiedadHorizontal;// ok
	private Boolean zonasVerdes;// ok
	private Boolean piscina;// ok
	private Boolean salonSocial;// ok
	private Boolean juegosInfantiles;// ok
	private Boolean ascensor;// ok
	private Integer ubicacionDelSector;// ok
	private Integer vecindario;// ok
	private Integer desarrolloYProyeccionUrbana;// ok
	private Integer viasDeAcceso;// ok
	private Integer trasporteUrbano;// ok
	private Integer alumbradoYAlcantarillado;// ok
	private Integer callesYAceras;// ok
	private Integer proporcionZonaSocial;// ok//ok
	private Integer proporcionZonaServicios;// ok
	private Integer acabados;// ok
	private Integer disenoYDistribucion;// ok
	private Integer estadoGeneralDelInmueble;// ok
	private String observacionesAnalisisTecnico;// ok
	private Integer banoServicios;// ok
	private Integer zonaDeRopas;// ok
	private Integer numeroDePisos;// ok
	private Integer numeroDeSotanos;// ok
	private Integer anoDeConstruccion;// ok
	private String pisos;// ok
	private String muros;// ok
	private String techos;// ok
	private String banos;// ok
	private String cocina;// ok
	
	private Integer habitaciones; //ok
	private Integer estarHabitacion; //ok
	private Integer cuartoServicio; // ok
	private Integer closet; // ok
	private Integer sala; // ok
	private Integer comedor; // ok
	private Integer banoPrivado; // ok
	private Integer banoSocial; // ok
	private Integer estudio; // ok
	private Integer balcon; // ok
	private Integer terraza; // ok
	private Integer patioInterior; //ok
	private Integer jardin; //ok
	private Integer zonaVerdePrivada;
	private Integer local; //ok
	private Integer oficina; //ok
	private Integer bodega; //ok
	
	private Integer garajePrivado;// ok
	private Integer garajeExlusivo;// ok
	private Integer bahiaComunal;// ok
	private Boolean garajeDoble;
	private Boolean garajeParalelo;
	private String matriculaInmobiliariaGaraje1;
	private String matriculaInmobiliariaGaraje2;
	private String matriculaInmobiliariaGaraje3;
	private String matriculaInmobiliariaGaraje4;
	private String matriculaInmobiliariaGaraje5;
	private Boolean garajeParalelo1;
	private Boolean garajeParalelo2;
	private Boolean garajeParalelo3;
	private Boolean garajeParalelo4;
	private Boolean garajeParalelo5;
	private Boolean garajeCubierto1;
	private Boolean garajeCubierto2;
	private Boolean garajeCubierto3;
	private Boolean garajeCubierto4;
	private Boolean garajeCubierto5;
	private Boolean garajeServidumbre1;
	private Boolean garajeServidumbre2;
	private Boolean garajeServidumbre3;
	private Boolean garajeServidumbre4;
	private Boolean garajeServidumbre5;
	private Integer numeroParqueadero1;
	private Integer numeroParqueadero2;
	private Integer numeroParqueadero3;
	private Integer numeroParqueadero4;
	private Integer numeroParqueadero5;
	private String matriculaInmobiliariaDeposito1;
	private String matriculaInmobiliariaDeposito2;
	private String matriculaInmobiliariaDeposito3;
	private String matriculaInmobiliariaDeposito4;
	private String matriculaInmobiliariaDeposito5;
	private Integer numeroDeposito1;
	private Integer numeroDeposito2;
	private Integer numeroDeposito3;
	private Integer numeroDeposito4;
	private Integer numeroDeposito5;
	private Integer numeroTotalDeGarajes;
	private Integer totalCuposParquedaro;
	private Integer depositosPrivados;
	private Integer depositosExclusivos;
	private Integer numeroTotalDepositos;
	private Integer totalCuposDeParqueo;// ok
	private BigDecimal loteProyectoM2;// ok
	private BigDecimal valorLote;// ok
	private BigDecimal costosDirectos;// ok
	private BigDecimal porcentajeCostosDirectos;// ok
	private BigDecimal costosIndirectos;// ok
	private BigDecimal porcentajeCostosIndirectos;// ok
	private BigDecimal costosDelProyecto;// ok
	private BigDecimal porcentajeDeCostosDelProyecto;// ok
	private BigDecimal valorSolicitado;// ok
	private BigDecimal porcentajeAFinanciar;// ok
	private BigDecimal valorMaxAFinanciar;// ok
	private BigDecimal porcentajeFinanciado;// ok
	private Integer programacionEnMeses;// ok
	private BigDecimal factor;// ok
	private BigDecimal valorSolicitadoTotal;// ok
	private BigDecimal costosFinancieros;// ok
	private BigDecimal costosTotalDelProyecto;// ok
	private String observacionesInformeTecnico;// ok
	private Integer conceptoTecnico;// ok
	private String descripcionAmpliacion;// ok
	private String descripcionDelProyecto;// ok
	private String consideracionesDelAvaluo;// ok
	private String documentosConsultados;
	private Date fechaAporteDeDocumentos;
	private String otrosDocumentos;
	private String observacionesDeTitulacion;
	private Boolean remosionMasas;
	private Boolean inundacion;
	private Boolean ordenPublico;
	private Boolean otro;
	private String obsRemosionMasas;
	private String obsInundacion;
	private String obsOrdenPublico;
	private String obsOtro;
	private CronogramaObraDTO cronogramaObra;
	private Set<AumentoPresupuestoDTO> aumentosPresupuesto;
	private Set<ProrrogaDTO> prorrogas;

	public AvaluoConstructorDTO() {
		super();
	}

	public AvaluoConstructorDTO(AvaluoDTO avaluoDTO) {
		super(avaluoDTO.getId(), avaluoDTO.isSoloAvaluo(), avaluoDTO.getEntidad(), avaluoDTO.getCodigoExterno(),
				avaluoDTO.getTipoDeInmueble(),
				avaluoDTO.getTipoAvaluo(),	avaluoDTO.getObjetoDelAvaluo(),
				avaluoDTO.getMotivo(), avaluoDTO.getCambioGarantia(),
				avaluoDTO.getCambioGarantiaAvaluo(), avaluoDTO.getDivipola(),
				avaluoDTO.getSector(),	avaluoDTO.getConjunto(),
				avaluoDTO.getBarrio(), avaluoDTO.getTipoVia(),
				avaluoDTO.getNumeroVia(), avaluoDTO.getComplementoVia(),
				avaluoDTO.getNumeroViaGeneradora(),
				avaluoDTO.getComplementoViaGeneradora(),
				avaluoDTO.getPlaca(), avaluoDTO.getComplementoPlaca(),
				avaluoDTO.getAdicionalDireccion(), avaluoDTO.getDireccionInmueble(),
				avaluoDTO.getNombreRecibe(), avaluoDTO.getTelefonoRecibe(),
				avaluoDTO.getCorreoElectronicoRecibe(), avaluoDTO.getNombreAsesor(),
				avaluoDTO.getSucursalAsesor(), avaluoDTO.getCelularAsesor(),
				avaluoDTO.getCorreoElectronicoAsesor(),
				avaluoDTO.getTelefonoAsesor(),
				avaluoDTO.getObservacionesSolicitante(), avaluoDTO.getLatitud(),
				avaluoDTO.getLongitud(), avaluoDTO.getValorSolicitado(),
				avaluoDTO.getValorTotalAvaluo(), avaluoDTO.getValorUvr(),
				avaluoDTO.getValorAvaluoEnUvr(), avaluoDTO.getCalificacionGarantia(),
				avaluoDTO.getValorAsegurable(), avaluoDTO.getValorLiquidacion(),
				avaluoDTO.getAreas(), avaluoDTO.getFotografias(),
				avaluoDTO.getMetodosValuacion(), avaluoDTO.getGarajes(),avaluoDTO.getEstado(),
				avaluoDTO.getFechaEstado(), avaluoDTO.getCliente(),
				avaluoDTO.getPerito(), avaluoDTO.getFormatoInforme(),
				avaluoDTO.getValorHonorarios(), avaluoDTO.getGastosTranslado(), 
				avaluoDTO.getVereda(), avaluoDTO.getNombrePredio(), avaluoDTO.getIva(),
				avaluoDTO.getAreaTotal(), avaluoDTO.getMatriculaInmobiliariaPrincipal1(), avaluoDTO.getMatriculaInmobiliariaPrincipal2(),
				avaluoDTO.getSemaforo(), 
				avaluoDTO.getDescripcionMotivo(),
				avaluoDTO.getDireccionInmuebleInforme(), avaluoDTO.getDivipolaInforme(),
				avaluoDTO.getComplementoViaInforme(),  avaluoDTO.getComplementoViaGeneradoraInforme(),
				avaluoDTO.getTipoViaInforme(), avaluoDTO.getNumeroViaGeneradoraInforme(), 
				avaluoDTO.getPlacaInforme(),avaluoDTO.getNumeroViaInforme(), 
				avaluoDTO.getAdicionalDireccionInforme(),avaluoDTO.getTipoCredito(), avaluoDTO.getAvaluoSisgenId());
	}

	public AvaluoConstructorDTO(String tipoDeConstruccion, String vereda,
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
			Integer usoInmueble, Integer claseInmueble, String estadoInmueble,
			Integer ubicacionLocal, Boolean requiereReparaciones,
			String cualesReaparaciones, Boolean alcantarillado,
			Boolean energia, Boolean agua, Boolean telefono,
			Integer conceptoDeComercializacion,
			String observacionesConceptoDeComercializacion,
			Boolean sometidoAPropiedadHorizontal, String conjuntoOAgrupacion,
			Integer numeroDeEdificios, Integer totalUnidades, String unidad,
			Integer unidadesPorPiso, String registroDePropiedadHorizontal,
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
			Integer bahiaComunal, Boolean garajeDoble, Boolean garajeParalelo,
			Integer numeroTotalDeGarajes, String noDeParqueadero,
			String matriculaInmobiliariaGaraje, Integer totalCuposDeParqueo,
			Integer totalGarajes, Boolean depositoPrivado,
			Boolean depositoExlusivo, String noDeDeposito,
			String matriculaInmobiliariaDeposito, BigDecimal loteProyectoM2,
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
			CronogramaObraDTO cronogramaObra,
			Set<AumentoPresupuestoDTO> aumentosPresupuesto,
			Set<ProrrogaDTO> prorrogas) {
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
		this.conceptoDeComercializacion = conceptoDeComercializacion;
		this.observacionesConceptoDeComercializacion = observacionesConceptoDeComercializacion;
		this.sometidoAPropiedadHorizontal = sometidoAPropiedadHorizontal;
		this.conjuntoOAgrupacion = conjuntoOAgrupacion;
		this.numeroDeEdificios = numeroDeEdificios;
		this.totalUnidades = totalUnidades;
		this.unidad = unidad;
		this.unidadesPorPiso = unidadesPorPiso;
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

	public void setDescripcionGeneralDelSector(
			String descripcionGeneralDelSector) {
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

	public void setSometidoAPropiedadHorizontal(
			Boolean sometidoAPropiedadHorizontal) {
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

	public void setDesarrolloYProyeccionUrbana(
			Integer desarrolloYProyeccionUrbana) {
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

	public void setObservacionesAnalisisTecnico(
			String observacionesAnalisisTecnico) {
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

	public void setPorcentajeCostosIndirectos(
			BigDecimal porcentajeCostosIndirectos) {
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

	public void setObservacionesInformeTecnico(
			String observacionesInformeTecnico) {
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

	public CronogramaObraDTO getCronogramaObra() {
		return cronogramaObra;
	}

	public void setCronogramaObra(CronogramaObraDTO cronogramaObra) {
		this.cronogramaObra = cronogramaObra;
	}

	public Set<AumentoPresupuestoDTO> getAumentosPresupuesto() {
		return aumentosPresupuesto;
	}

	public void setAumentosPresupuesto(
			Set<AumentoPresupuestoDTO> aumentosPresupuesto) {
		this.aumentosPresupuesto = aumentosPresupuesto;
	}

	public Set<ProrrogaDTO> getProrrogas() {
		return prorrogas;
	}

	public void setProrrogas(Set<ProrrogaDTO> prorrogas) {
		this.prorrogas = prorrogas;
	}

	public Boolean getGas() {
		return gas;
	}

	public void setGas(Boolean gas) {
		this.gas = gas;
	}

	public Integer getUbicacionPropiedadHorizontal() {
		return ubicacionPropiedadHorizontal;
	}

	public void setUbicacionPropiedadHorizontal(
			Integer ubicacionPropiedadHorizontal) {
		this.ubicacionPropiedadHorizontal = ubicacionPropiedadHorizontal;
	}

	public Integer getNumeroTotalDeGarajes() {
		return numeroTotalDeGarajes;
	}

	public void setNumeroTotalDeGarajes(Integer numeroTotalDeGarajes) {
		this.numeroTotalDeGarajes = numeroTotalDeGarajes;
	}

	public String getMatriculaInmobiliariaGaraje1() {
		return matriculaInmobiliariaGaraje1;
	}

	public void setMatriculaInmobiliariaGaraje1(
			String matriculaInmobiliariaGaraje1) {
		this.matriculaInmobiliariaGaraje1 = matriculaInmobiliariaGaraje1;
	}

	public String getMatriculaInmobiliariaGaraje2() {
		return matriculaInmobiliariaGaraje2;
	}

	public void setMatriculaInmobiliariaGaraje2(
			String matriculaInmobiliariaGaraje2) {
		this.matriculaInmobiliariaGaraje2 = matriculaInmobiliariaGaraje2;
	}

	public String getMatriculaInmobiliariaGaraje3() {
		return matriculaInmobiliariaGaraje3;
	}

	public void setMatriculaInmobiliariaGaraje3(
			String matriculaInmobiliariaGaraje3) {
		this.matriculaInmobiliariaGaraje3 = matriculaInmobiliariaGaraje3;
	}

	public String getMatriculaInmobiliariaGaraje4() {
		return matriculaInmobiliariaGaraje4;
	}

	public void setMatriculaInmobiliariaGaraje4(
			String matriculaInmobiliariaGaraje4) {
		this.matriculaInmobiliariaGaraje4 = matriculaInmobiliariaGaraje4;
	}

	public String getMatriculaInmobiliariaGaraje5() {
		return matriculaInmobiliariaGaraje5;
	}

	public void setMatriculaInmobiliariaGaraje5(
			String matriculaInmobiliariaGaraje5) {
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

	public Integer getComedor() {
		return comedor;
	}

	public void setComedor(Integer comedor) {
		this.comedor = comedor;
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

	public Integer getBalcon() {
		return balcon;
	}

	public void setBalcon(Integer balcon) {
		this.balcon = balcon;
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
