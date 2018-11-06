package com.helio4.bancol.avaluos.dto;

import com.helio4.bancol.avaluos.controlador.ListaDesplegable;
import com.helio4.bancol.avaluos.servicio.util.Constantes;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import javax.persistence.Column;

public class FormatoInformeHipotecarioDTO extends FormatoInformeDTO implements Serializable, Cloneable{

    private static final long serialVersionUID = 1L;

    private BigDecimal valorComercial;
    private BigDecimal valorCortesia;
    private Integer metodoDeValuacion;
    private String justificacionDeMetodologia;
    private String documentosConsultados;
    private Boolean acueductoEnElSector;
    private Boolean acueductoEnElPredio;
    private Boolean alcantarilladoEnElSector;
    private Boolean alcantarilladoEnElPredio;
    private Boolean energiaEnElSector;
    private Boolean energiaEnElPredio;
    private Boolean gasEnElSector;
    private Boolean gasEnElPredio;
    private Boolean telefonoEnElSector;
    private Boolean telefonoEnElPredio;
    private Integer estrato;
    private Boolean barrioLegal;
    private ListaUsoPredominanteInmueble usoPredominanteInmueble;
    private String descripcionUsoPredominanteMixto;
    private ListaTopografiaSector topografiaSector;
    private ListaEstado transporte;
    private String perspectivasDeValorizacion;
    private ListaEstado condicionesDeSalubridad;
    private Boolean impactoAmbientalAire;
    private Boolean impactoAmbientalBasura;
    private Boolean impactoAmbientalInseguridad;
    private Boolean impactoAmbientalRuido;
    private Boolean impactoAmbientalAguasServidas;
    private Boolean impactoAmbientalOtro;
    private String explicacionImpactoAmbientalAire ="";
    private String explicacionImpactoAmbientalBasura;
    private String explicacionImpactoAmbientalInseguridad = "";
    private String explicacionImpactoAmbientalRuido = "";
    private String explicacionImpactoAmbientalAguasServidas;
    private String explicacionOtro;
    private ListaEstado estadoDeLasVias;
    private Boolean viasPavimentadas;
    private Boolean andenesEnLasVias;
    private Boolean sardinelesEnLasVias;
    private Boolean parques;
    private Boolean paradero;
    private Boolean alumbrado;
    private Boolean zonasVerdesPublicas;
    private Boolean arborizacion;
    private Boolean alamedas;
    private Boolean cicloRutas;
    private ListaClaseInmueble claseInmueble;
    private String descripcionOtrosTipoInmueble = "";
    private ListaUsoActual  usoActual;
    private String descripcionOtrosUsoInmueble = "";
    private ListaTipoVivienda tipoDeVivienda;
    private String descripcionOtrosTipoVivienda = "";
    private String chip;
    private String matriculaInmobiliariaPrincipal2 = "";
    private String numeroDeEscritura;
    private String numeroNotariaDeEscritura;
    private Date fechaDeEscritura;
    private String departamentoNotaria;
    private DivipolaDTO ciudadNotaria;

    private String matriculaInmobiliariaDeposito1;
    private String matriculaInmobiliariaDeposito2;
    private String matriculaInmobiliariaDeposito3;
    private String matriculaInmobiliariaDeposito4;
    private String matriculaInmobiliariaDeposito5;
    private String numeroDeposito1;
    private String numeroDeposito2;
    private String numeroDeposito3;
    private String numeroDeposito4;
    private String numeroDeposito5;
    private String numeroDepositoExclusivo1;
    private String numeroDepositoExclusivo2;
    private String numeroDepositoExclusivo3;
    private String numeroDepositoExclusivo4;
    private String numeroDepositoExclusivo5;
    private Integer numeroDePisos;
    private Integer numeroSotanos;
    private Integer vetustez;
    private ListaEstadoConservacion estadoDeConservacion;
    private String licenciaConstruccion;
    private ListaTipoLicencia tipoLicencia;
    private String numeroLicencia = "";
    private ListaEstadoConstruccion estadoDeConstruccion;
    private Boolean estadoDeObra;
    private Integer anoDeConstruccion;
    private Integer porcentajeAvance;
    private Boolean remodelado;
    private Date fechaRemodelacion;
    private ListaEstructura estructura;
    private ListaReparado reparados;
    private ListaFachada fachada;
    private ListaTipoFachada tipoFachada;
    private ListaEstructuraReforzada estructuraReforzada;
    private ListaDanoPrevio danosPrevios;
    private ListaMaterialConstructor materialConstructor;
    private ListaDetalleMaterial detalleMaterial;
    private ListaIrregularidad irregularidadPlanta;
    private ListaIrregularidad irregularidadAltura;
    private ListaCubierta cubierta;
    private ListaPisoBodega pisosBodega;
    private String tipologiaViviendaUnicaFamiliar;
    private Integer sala;
    private Integer deposito;
    private Integer comedor;
    private Integer estudio;
    private Integer jardin;
    private Integer banoSocial;
    private Integer estarHabitacion;
    private Integer habitaciones;
    private Integer depositosPrivados;
    private Integer depositosExclusivos;
    private Integer numeroTotalDepositos;
    private Integer banoPrivado;
    private Integer cocina;
    private Integer cuartoServicio;
    private Integer oficina;
    private Integer banoServicio;
    private Integer patioInterior;
    private Integer terraza;
    private Integer bodega;
    private Integer zonaDeRopas;
    private Integer balcon;
    private Integer closet;
    private Integer local;
    private Integer zonaVerdePrivada;
    private ListaEstado iluminacion;
    private ListaEstado ventilacion;
    private Integer numeroTotalDeGarajes;
    private Integer totalCuposParquedaro;
    private ListaEstadosAcabados estadoAcabadosPisos;
    private ListaEstadosAcabados estadoAcabadosMuros;
    private ListaEstadosAcabados estadoAcabadosTechos;
    private ListaEstadosAcabados estadoAcabadosMadera;
    private ListaEstadosAcabados estadoAcabadosMetal;
    private ListaEstadosAcabados estadoAcabadosBanos;
    private ListaEstadosAcabados estadoAcabadosCocina;
    private ListaCalidadAcabados calidadAcabadosPisos;
    private ListaCalidadAcabados calidadAcabadosMuros;
    private ListaCalidadAcabados calidadAcabadosTechos;
    private ListaCalidadAcabados calidadAcabadosMadera;
    private ListaCalidadAcabados calidadAcabadosMetal;
    private ListaCalidadAcabados calidadAcabadosBanos;
    private ListaCalidadAcabadosCocina calidadAcabadosCocina;
    private Boolean sometidoAPropiedadHorizontal;
    private Boolean conjuntoCerrado;
    private ListaUbicacionInmueble ubicacionDelInmueble;
    private Integer numeroPiso;
    private Integer numeroDeEdificios;
    private Integer unidadesPorPiso;
    private Integer totalUnidades;
    private BigDecimal coeficiente;
    private Boolean porteria;
    private Boolean citofono;
    private Boolean bicicletero;
    private Boolean piscina;
    private Boolean tanqueDeAgua;
    private Boolean clubHouse;
    private Boolean garajeVisitantes;
    private Boolean juegosNinos;
    private Boolean canchaMultiple;
    private Boolean bombaEyectora;
    private Boolean aireAcondicionadoCentral;
    private Boolean canchaSquash;
    private Boolean zonasVerdesComunales;
    private Boolean gimnasio;
    private Boolean golfito;
    private Boolean salonComunal;
    private Boolean shutBasuras;
    private Boolean equipoDePresionConstante;
    private Boolean plantaElectrica;
    private Boolean ascensor;
    private Integer numeroDeAscensores;
    private String otros;
    private String actualidadEdificadora;
    private String entregaDeDocumentos;
    private String comportamientoOfertaDemanda;
    private Double tiempoEsperadoDeComercializacion;
    private BigDecimal porcentajeTerreno;
    private BigDecimal valorProporcionalTerreno;
    private BigDecimal valorProporcionalConstruccion;
    private BigDecimal valorIntegralTerreno;
    private BigDecimal valorIntegralConstruccion;
    private String observaciones;
    private String direccionAnexos = "";
    private String otrasDirecciones = "";
    private BigDecimal areaConstruida;
    private BigDecimal areaPrivada;
    private String observacionesGarajes = "";
    private String ocupante="";
    private String condicionPh ="";
    private Boolean teatrino;
    private Boolean sauna;
    private Boolean calefaccion;
    private Boolean terrazaComunal;
    private Boolean turco;
    private Boolean bbq;
    private Boolean guarderia;
    private Boolean jardinInfantil;
    private String vigilanciaPrivada;
    private Boolean administracion;
    private Boolean EstadoVigilancia;
    private Integer valorAdministracion;
    private String decretoAcuerdo;
    private String observacionDecretoAcuerdo;
    private String alturaPermitida;
    private String observacionAlturaPermitida;
    private String usoPrincipal;
    private String observacionUsoPrincipal;
    private String aislamientoPosterior;
    private String observacionAislamientoPosterior;
    private String aislamientoLateral;
    private String observacionAislamientoLateral;
    private String anteJardin;
    private String observacionAnteJardin;
    private String indiceOcupacion;
    private String observacionIndiceOcupacion;
    private String indiceConstruccion;
    private String observacionIndiceConstruccion;
    private String predioSubdivididoFisicamente;
    private String observacionPredioSubdivididoFisicamente;
    
    //Estado de Conservación
    private String estadoConservacionAlcantarrillado;
    private String estadoConservacionAcueducto;
    private String estadoConservacionGas;
    private String estadoConservacionEnergia;
    private String estadoConservacionTelefono;
    private String estadoConservacionViasPavimentadas;
    private String estadoConservacionSardinelesEnLasVias;
    private String estadoConservacionAndenesEnLasVias;
    
    private String demandaInteres;
    
    //Nivel de equipamiento
    private String nivelEquipamientoComercial;
    private String nivelEquipamientoEscolar;
    private String nivelEquipamientoAsistencial;
    private String nivelEquipamientoEstacionamiento;
    private String nivelEquipamientoAreasVerdes;
    private String nivelEquipamientoZonasRecreativas;
    
    //Distancia aproximada
    private String distanciaAproximadaComercial;
    private String distanciaAproximadaEscolar;
    private String distanciaAproximadaAsistencial;
    private String distanciaAproximadaEstacionamiento;
    private String distanciaAproximadaAreasVerdes;
    private String distanciaAproximadaZonasRecreativas;
    
    
    private String descripcionGeneralSector;
    private String observacionesGeneralesConstruccion;
    private String observacionesGeneralesInmueble;
    private String observacionesEstructura;
    private String observacionesDependencias;
    private String observacionesAcabados;
    private String descripcionGeneral;
    
    private String areaActividad;
    private String usoPrincipalPh;
    private Boolean rph;
    
    private Boolean mostrarPredioSubdivididoFisicamente;
    private Integer unidades;
    private Integer contadoresAgua;
    private Integer contadoresLuz;
    private Integer accesos;
    
    /** Credito Hipotecario Vivienda  */
    private Boolean tfc1;
    /** Retanqueo Vivienda */
    private Boolean tfc2;
    /** Reforma o Remodelacion Vivienda */
    private Boolean tfc3;
    /** Constructor Individual */
    private Boolean tfc4;
    /** Leasing Habitacional Persona Natural */
    private Boolean tfc5;
    /** Leasing Inmobiliario Vivienda persona Juridica */
    private Boolean tfc6;
    /** Credito Hipotecario  Diferente de Vivienda */
    private Boolean tfc7;
    /** Retanqueo  Diferente de Vivienda */
    private Boolean tfc8;
    /** Leasing Diferente de Vivienda Persona Natural */
    private Boolean tfc9;
    /** Leasing Inmobiliario Difernete de  Vivienda persona Juridica */
    private Boolean tfc10;

    public FormatoInformeHipotecarioDTO(Long avaluoId) {
        super(avaluoId);
    }

    public FormatoInformeHipotecarioDTO(Long avaluoId,
            BigDecimal valorComercial, BigDecimal valorCortesia,
            Integer metodoDeValuacion, String justificacionDeMetodologia,
            String documentosConsultados,
            Boolean acueductoEnElSector, Boolean acueductoEnElPredio,
            Boolean alcantarilladoEnElSector, Boolean alcantarilladoEnElPredio,
            Boolean energiaEnElSector, Boolean energiaEnElPredio,
            Boolean gasEnElSector, Boolean gasEnElPredio,
            Boolean telefonoEnElSector, Boolean telefonoEnElPredio,
            Integer estrato, Boolean barrioLegal,
            ListaUsoPredominanteInmueble usoPredominanteInmueble,
            String descripcionUsoPredominanteMixto, ListaTopografiaSector topografiaSector,
            ListaEstado transporte, String perspectivasDeValorizacion,
            ListaEstado condicionesDeSalubridad, Boolean impactoAmbientalAire,
            Boolean impactoAmbientalBasura,
            Boolean impactoAmbientalInseguridad, Boolean impactoAmbientalRuido,
            Boolean impactoAmbientalAguasServidas,
            Boolean impactoAmbientalOtro,
            String explicacionImpactoAmbientalAire,
            String explicacionImpactoAmbientalBasura,
            String explicacionImpactoAmbientalInseguridad,
            String explicacionImpactoAmbientalRuido,
            String explicacionImpactoAmbientalAguasServidas,
            String explicacionOtro, ListaEstado estadoDeLasVias,
            Boolean viasPavimentadas, Boolean andenesEnLasVias,
            Boolean sardinelesEnLasVias, Boolean parques, Boolean paradero,
            Boolean alumbrado, Boolean zonasVerdesPublicas,
            Boolean arborizacion, Boolean alamedas, Boolean cicloRutas,
            ListaClaseInmueble claseInmueble, String descripcionOtrosTipoInmueble,
            ListaUsoActual  usoActual, String descripcionOtrosUsoInmueble,
            ListaTipoVivienda tipoDeVivienda, String descripcionOtrosTipoVivienda,
            String chip, String matriculaInmobiliariaPrincipal2,
            String numeroDeEscritura, String numeroNotariaDeEscritura,
            Date fechaDeEscritura, String departamentoNotaria,
            DivipolaDTO ciudadNotaria,
            String matriculaInmobiliariaDeposito1,
            String matriculaInmobiliariaDeposito2,
            String matriculaInmobiliariaDeposito3,
            String matriculaInmobiliariaDeposito4,
            String matriculaInmobiliariaDeposito5, String numeroDeposito1,
            String numeroDeposito2, String numeroDeposito3,
            String numeroDeposito4, String numeroDeposito5,
            String numeroDepositoExclusivo1, String numeroDepositoExclusivo2,
            String numeroDepositoExclusivo3, String numeroDepositoExclusivo4,
            String numeroDepositoExclusivo5, Integer numeroDePisos,
            Integer numeroSotanos, Integer vetustez,
            ListaEstadoConservacion estadoDeConservacion, String licenciaConstruccion, ListaTipoLicencia tipoLicencia,
            String numeroLicencia, ListaEstadoConstruccion estadoDeConstruccion,
            Boolean estadoDeObra, Integer anoDeConstruccion,
            Integer porcentajeAvance, Boolean remodelado, Date fechaRemodelacion, ListaEstructura estructura,
            ListaReparado reparados, ListaFachada fachada, ListaTipoFachada tipoFachada,
            ListaEstructuraReforzada estructuraReforzada, ListaDanoPrevio danosPrevios,
            ListaMaterialConstructor materialConstructor, ListaDetalleMaterial detalleMaterial,
            ListaIrregularidad irregularidadPlanta, ListaIrregularidad irregularidadAltura,
            ListaCubierta cubierta, ListaPisoBodega pisosBodega, String tipologiaViviendaUnicaFamiliar, Integer sala,
            Integer deposito, Integer comedor, Integer estudio, Integer jardin,
            Integer banoSocial, Integer estarHabitacion, Integer habitaciones,
            Integer depositosPrivados, Integer depositosExclusivos,
            Integer numeroTotalDepositos, Integer banoPrivado, Integer cocina,
            Integer cuartoServicio, Integer oficina, Integer banoServicio,
            Integer patioInterior, Integer terraza, Integer bodega,
            Integer zonaDeRopas, Integer balcon, Integer closet, Integer local,
            Integer zonaVerdePrivada, ListaEstado iluminacion, ListaEstado ventilacion,
            Integer numeroTotalDeGarajes,
            Integer totalCuposParquedaro, ListaEstadosAcabados estadoAcabadosPisos,
            ListaEstadosAcabados estadoAcabadosMuros, ListaEstadosAcabados estadoAcabadosTechos,
            ListaEstadosAcabados estadoAcabadosMadera, ListaEstadosAcabados estadoAcabadosMetal,
            ListaEstadosAcabados estadoAcabadosBanos, ListaEstadosAcabados estadoAcabadosCocina,
            ListaCalidadAcabados calidadAcabadosPisos, ListaCalidadAcabados calidadAcabadosMuros,
            ListaCalidadAcabados calidadAcabadosTechos, ListaCalidadAcabados calidadAcabadosMadera,
            ListaCalidadAcabados calidadAcabadosMetal, ListaCalidadAcabados calidadAcabadosBanos,
            ListaCalidadAcabadosCocina calidadAcabadosCocina,
            Boolean sometidoAPropiedadHorizontal, Boolean conjuntoCerrado,
            ListaUbicacionInmueble ubicacionDelInmueble, Integer numeroPiso, Integer numeroDeEdificios,
            Integer unidadesPorPiso, Integer totalUnidades,
            BigDecimal coeficiente, Boolean porteria, Boolean citofono,
            Boolean bicicletero, Boolean piscina, Boolean tanqueDeAgua,
            Boolean clubHouse, Boolean garajeVisitantes, Boolean juegosNinos,
            Boolean canchaMultiple, Boolean bombaEyectora,
            Boolean aireAcondicionadoCentral, Boolean canchaSquash,
            Boolean zonasVerdesComunales, Boolean gimnasio, Boolean golfito,
            Boolean salonComunal, Boolean shutBasuras,
            Boolean equipoDePresionConstante, Boolean plantaElectrica,
            Boolean ascensor, Integer numeroDeAscensores, String otros,
            String actualidadEdificadora, String entregaDeDocumentos,
            String comportamientoOfertaDemanda,
            Double tiempoEsperadoDeComercializacion,
            BigDecimal porcentajeTerreno, BigDecimal valorProporcionalTerreno,
            BigDecimal valorProporcionalConstruccion,
            BigDecimal valorIntegralTerreno,
            BigDecimal valorIntegralConstruccion, String observaciones,
            String direccionAnexos, String otrasDirecciones,
            BigDecimal areaConstruida, BigDecimal areaPrivada,
            String observacionesGarajes) {
        super(avaluoId);
        this.valorComercial = valorComercial;
        this.valorCortesia = valorCortesia;
        this.metodoDeValuacion = metodoDeValuacion;
        this.justificacionDeMetodologia = justificacionDeMetodologia;
        this.documentosConsultados = documentosConsultados;
        this.acueductoEnElSector = acueductoEnElSector;
        this.acueductoEnElPredio = acueductoEnElPredio;
        this.alcantarilladoEnElSector = alcantarilladoEnElSector;
        this.alcantarilladoEnElPredio = alcantarilladoEnElPredio;
        this.energiaEnElSector = energiaEnElSector;
        this.energiaEnElPredio = energiaEnElPredio;
        this.gasEnElSector = gasEnElSector;
        this.gasEnElPredio = gasEnElPredio;
        this.telefonoEnElSector = telefonoEnElSector;
        this.telefonoEnElPredio = telefonoEnElPredio;
        this.estrato = estrato;
        this.barrioLegal = barrioLegal;
        this.usoPredominanteInmueble = usoPredominanteInmueble;
        this.descripcionUsoPredominanteMixto = descripcionUsoPredominanteMixto;
        this.topografiaSector = topografiaSector;
        this.transporte = transporte;
        this.perspectivasDeValorizacion = perspectivasDeValorizacion;
        this.condicionesDeSalubridad = condicionesDeSalubridad;
        this.impactoAmbientalAire = impactoAmbientalAire;
        this.impactoAmbientalBasura = impactoAmbientalBasura;
        this.impactoAmbientalInseguridad = impactoAmbientalInseguridad;
        this.impactoAmbientalRuido = impactoAmbientalRuido;
        this.impactoAmbientalAguasServidas = impactoAmbientalAguasServidas;
        this.impactoAmbientalOtro = impactoAmbientalOtro;
        this.explicacionImpactoAmbientalAire = explicacionImpactoAmbientalAire;
        this.explicacionImpactoAmbientalBasura = explicacionImpactoAmbientalBasura;
        this.explicacionImpactoAmbientalInseguridad = explicacionImpactoAmbientalInseguridad;
        this.explicacionImpactoAmbientalRuido = explicacionImpactoAmbientalRuido;
        this.explicacionImpactoAmbientalAguasServidas = explicacionImpactoAmbientalAguasServidas;
        this.explicacionOtro = explicacionOtro;
        this.estadoDeLasVias = estadoDeLasVias;
        this.viasPavimentadas = viasPavimentadas;
        this.andenesEnLasVias = andenesEnLasVias;
        this.sardinelesEnLasVias = sardinelesEnLasVias;
        this.parques = parques;
        this.paradero = paradero;
        this.alumbrado = alumbrado;
        this.zonasVerdesPublicas = zonasVerdesPublicas;
        this.arborizacion = arborizacion;
        this.alamedas = alamedas;
        this.cicloRutas = cicloRutas;
        this.claseInmueble = claseInmueble;
        this.descripcionOtrosTipoInmueble = descripcionOtrosTipoInmueble;
        this.usoActual = usoActual;
        this.descripcionOtrosUsoInmueble = descripcionOtrosUsoInmueble;
        this.tipoDeVivienda = tipoDeVivienda;
        this.descripcionOtrosTipoVivienda = descripcionOtrosTipoVivienda;
        this.chip = chip;
        this.matriculaInmobiliariaPrincipal2 = matriculaInmobiliariaPrincipal2;
        this.numeroDeEscritura = numeroDeEscritura;
        this.numeroNotariaDeEscritura = numeroNotariaDeEscritura;
        this.fechaDeEscritura = (fechaDeEscritura!=null? new Date(fechaDeEscritura.getTime()): null);
        this.departamentoNotaria = departamentoNotaria;
        this.ciudadNotaria = ciudadNotaria;
        this.matriculaInmobiliariaDeposito1 = matriculaInmobiliariaDeposito1;
        this.matriculaInmobiliariaDeposito2 = matriculaInmobiliariaDeposito2;
        this.matriculaInmobiliariaDeposito3 = matriculaInmobiliariaDeposito3;
        this.matriculaInmobiliariaDeposito4 = matriculaInmobiliariaDeposito4;
        this.matriculaInmobiliariaDeposito5 = matriculaInmobiliariaDeposito5;
        this.numeroDeposito1 = numeroDeposito1;
        this.numeroDeposito2 = numeroDeposito2;
        this.numeroDeposito3 = numeroDeposito3;
        this.numeroDeposito4 = numeroDeposito4;
        this.numeroDeposito5 = numeroDeposito5;
        this.numeroDepositoExclusivo1 = numeroDepositoExclusivo1;
        this.numeroDepositoExclusivo2 = numeroDepositoExclusivo2;
        this.numeroDepositoExclusivo3 = numeroDepositoExclusivo3;
        this.numeroDepositoExclusivo4 = numeroDepositoExclusivo4;
        this.numeroDepositoExclusivo5 = numeroDepositoExclusivo5;
        this.numeroDePisos = numeroDePisos;
        this.numeroSotanos = numeroSotanos;
        this.vetustez = vetustez;
        this.estadoDeConservacion = estadoDeConservacion;
        this.licenciaConstruccion = licenciaConstruccion;
        this.tipoLicencia = tipoLicencia;
        this.numeroLicencia = numeroLicencia;
        this.estadoDeConstruccion = estadoDeConstruccion;
        this.estadoDeObra = estadoDeObra;
        this.anoDeConstruccion = anoDeConstruccion;
        this.porcentajeAvance = porcentajeAvance;
        this.remodelado = remodelado;
        this.fechaRemodelacion = (fechaRemodelacion!=null? new Date(fechaRemodelacion.getTime()): null);
        this.estructura = estructura;
        this.reparados = reparados;
        this.fachada = fachada;
        this.tipoFachada = tipoFachada;
        this.estructuraReforzada = estructuraReforzada;
        this.danosPrevios = danosPrevios;
        this.materialConstructor = materialConstructor;
        this.detalleMaterial = detalleMaterial;
        this.irregularidadPlanta = irregularidadPlanta;
        this.irregularidadAltura = irregularidadAltura;
        this.cubierta = cubierta;
        this.pisosBodega = pisosBodega;
        this.tipologiaViviendaUnicaFamiliar = tipologiaViviendaUnicaFamiliar;
        this.sala = sala;
        this.deposito = deposito;
        this.comedor = comedor;
        this.estudio = estudio;
        this.jardin = jardin;
        this.banoSocial = banoSocial;
        this.estarHabitacion = estarHabitacion;
        this.habitaciones = habitaciones;
        this.depositosPrivados = depositosPrivados;
        this.depositosExclusivos = depositosExclusivos;
        this.numeroTotalDepositos = numeroTotalDepositos;
        this.banoPrivado = banoPrivado;
        this.cocina = cocina;
        this.cuartoServicio = cuartoServicio;
        this.oficina = oficina;
        this.banoServicio = banoServicio;
        this.patioInterior = patioInterior;
        this.terraza = terraza;
        this.bodega = bodega;
        this.zonaDeRopas = zonaDeRopas;
        this.balcon = balcon;
        this.closet = closet;
        this.local = local;
        this.zonaVerdePrivada = zonaVerdePrivada;
        this.iluminacion = iluminacion;
        this.ventilacion =  ventilacion;
        this.numeroTotalDeGarajes = numeroTotalDeGarajes;
        this.totalCuposParquedaro = totalCuposParquedaro;
        this.estadoAcabadosPisos = estadoAcabadosPisos;
        this.estadoAcabadosMuros = estadoAcabadosMuros;
        this.estadoAcabadosTechos = estadoAcabadosTechos;
        this.estadoAcabadosMadera = estadoAcabadosMadera;
        this.estadoAcabadosMetal = estadoAcabadosMetal;
        this.estadoAcabadosBanos = estadoAcabadosBanos;
        this.estadoAcabadosCocina = estadoAcabadosCocina;
        this.calidadAcabadosPisos = calidadAcabadosPisos;
        this.calidadAcabadosMuros = calidadAcabadosMuros;
        this.calidadAcabadosTechos = calidadAcabadosTechos;
        this.calidadAcabadosMadera = calidadAcabadosMadera;
        this.calidadAcabadosMetal = calidadAcabadosMetal;
        this.calidadAcabadosBanos = calidadAcabadosBanos;
        this.calidadAcabadosCocina = calidadAcabadosCocina;
        this.sometidoAPropiedadHorizontal = sometidoAPropiedadHorizontal;
        this.conjuntoCerrado = conjuntoCerrado;
        this.ubicacionDelInmueble = ubicacionDelInmueble;
        this.numeroPiso = numeroPiso;
        this.numeroDeEdificios = numeroDeEdificios;
        this.unidadesPorPiso = unidadesPorPiso;
        this.totalUnidades = totalUnidades;
        this.coeficiente = coeficiente;
        this.porteria = porteria;
        this.citofono = citofono;
        this.bicicletero = bicicletero;
        this.piscina = piscina;
        this.tanqueDeAgua = tanqueDeAgua;
        this.clubHouse = clubHouse;
        this.garajeVisitantes = garajeVisitantes;
        this.juegosNinos = juegosNinos;
        this.canchaMultiple = canchaMultiple;
        this.bombaEyectora = bombaEyectora;
        this.aireAcondicionadoCentral = aireAcondicionadoCentral;
        this.canchaSquash = canchaSquash;
        this.zonasVerdesComunales = zonasVerdesComunales;
        this.gimnasio = gimnasio;
        this.golfito = golfito;
        this.salonComunal = salonComunal;
        this.shutBasuras = shutBasuras;
        this.equipoDePresionConstante = equipoDePresionConstante;
        this.plantaElectrica = plantaElectrica;
        this.ascensor = ascensor;
        this.numeroDeAscensores = numeroDeAscensores;
        this.otros = otros;
        this.actualidadEdificadora = actualidadEdificadora;
        this.entregaDeDocumentos = entregaDeDocumentos;
        this.comportamientoOfertaDemanda = comportamientoOfertaDemanda;
        this.tiempoEsperadoDeComercializacion = tiempoEsperadoDeComercializacion;
        this.porcentajeTerreno = porcentajeTerreno;
        this.valorProporcionalTerreno = valorProporcionalTerreno;
        this.valorProporcionalConstruccion = valorProporcionalConstruccion;
        this.valorIntegralTerreno = valorIntegralTerreno;
        this.valorIntegralConstruccion = valorIntegralConstruccion;
        this.observaciones = observaciones;
        this.direccionAnexos = direccionAnexos;
        this.otrasDirecciones = otrasDirecciones;
        this.areaConstruida = areaConstruida;
        this.areaPrivada = areaPrivada;
        this.observacionesGarajes = observacionesGarajes;
    }

    public BigDecimal getValorComercial() {
        return valorComercial;
    }

    public void setValorComercial(BigDecimal valorComercial) {
        this.valorComercial = valorComercial;
    }

    public BigDecimal getValorCortesia() {
        return valorCortesia;
    }

    public void setValorCortesia(BigDecimal valorCortesia) {
        this.valorCortesia = valorCortesia;
    }

    public Integer getMetodoDeValuacion() {
        return metodoDeValuacion;
    }

    public void setMetodoDeValuacion(Integer metodoDeValuacion) {
        this.metodoDeValuacion = metodoDeValuacion;
    }

    public String getJustificacionDeMetodologia() {
        return justificacionDeMetodologia;
    }

    public void setJustificacionDeMetodologia(String justificacionDeMetodologia) {
        this.justificacionDeMetodologia = justificacionDeMetodologia;
    }

    public String getDocumentosConsultados() {
        return documentosConsultados;
    }

    public void setDocumentosConsultados(String documentosConsultados) {
        this.documentosConsultados = documentosConsultados;
    }

    public Boolean getAcueductoEnElSector() {
        return acueductoEnElSector;
    }

    public void setAcueductoEnElSector(Boolean acueductoEnElSector) {
        this.acueductoEnElSector = acueductoEnElSector;
    }

    public Boolean getAcueductoEnElPredio() {
        return acueductoEnElPredio;
    }

    public void setAcueductoEnElPredio(Boolean acueductoEnElPredio) {
        this.acueductoEnElPredio = acueductoEnElPredio;
    }

    public Boolean getAlcantarilladoEnElSector() {
        return alcantarilladoEnElSector;
    }

    public void setAlcantarilladoEnElSector(Boolean alcantarilladoEnElSector) {
        this.alcantarilladoEnElSector = alcantarilladoEnElSector;
    }

    public Boolean getAlcantarilladoEnElPredio() {
        return alcantarilladoEnElPredio;
    }

    public void setAlcantarilladoEnElPredio(Boolean alcantarilladoEnElPredio) {
        this.alcantarilladoEnElPredio = alcantarilladoEnElPredio;
    }

    public Boolean getEnergiaEnElSector() {
        return energiaEnElSector;
    }

    public void setEnergiaEnElSector(Boolean energiaEnElSector) {
        this.energiaEnElSector = energiaEnElSector;
    }

    public Boolean getEnergiaEnElPredio() {
        return energiaEnElPredio;
    }

    public void setEnergiaEnElPredio(Boolean energiaEnElPredio) {
        this.energiaEnElPredio = energiaEnElPredio;
    }

    public Boolean getGasEnElSector() {
        return gasEnElSector;
    }

    public void setGasEnElSector(Boolean gasEnElSector) {
        this.gasEnElSector = gasEnElSector;
    }

    public Boolean getGasEnElPredio() {
        return gasEnElPredio;
    }

    public void setGasEnElPredio(Boolean gasEnElPredio) {
        this.gasEnElPredio = gasEnElPredio;
    }

    public Boolean getTelefonoEnElSector() {
        return telefonoEnElSector;
    }

    public void setTelefonoEnElSector(Boolean telefonoEnElSector) {
        this.telefonoEnElSector = telefonoEnElSector;
    }

    public Boolean getTelefonoEnElPredio() {
        return telefonoEnElPredio;
    }

    public void setTelefonoEnElPredio(Boolean telefonoEnElPredio) {
        this.telefonoEnElPredio = telefonoEnElPredio;
    }

    public Integer getEstrato() {
        return estrato;
    }

    public void setEstrato(Integer estrato) {
        this.estrato = estrato;
    }

    public Boolean getBarrioLegal() {
        return barrioLegal;
    }

    public void setBarrioLegal(Boolean barrioLegal) {
        this.barrioLegal = barrioLegal;
    }

    public ListaUsoPredominanteInmueble getUsoPredominanteInmueble() {
        return usoPredominanteInmueble;
    }

    public void setUsoPredominanteInmueble(ListaUsoPredominanteInmueble usoPredominanteInmueble) {
        this.usoPredominanteInmueble = usoPredominanteInmueble;
    }

    public ListaTopografiaSector getTopografiaSector() {
        return topografiaSector;
    }

    public void setTopografiaSector(ListaTopografiaSector topografiaSector) {
        this.topografiaSector = topografiaSector;
    }

    public ListaEstado getTransporte() {
        return transporte;
    }

    public void setTransporte(ListaEstado transporte) {
        this.transporte = transporte;
    }

    public String getPerspectivasDeValorizacion() {
        return perspectivasDeValorizacion;
    }

    public void setPerspectivasDeValorizacion(String perspectivasDeValorizacion) {
        this.perspectivasDeValorizacion = perspectivasDeValorizacion;
    }

    public ListaEstado getCondicionesDeSalubridad() {
        return condicionesDeSalubridad;
    }

    public void setCondicionesDeSalubridad(ListaEstado condicionesDeSalubridad) {
        this.condicionesDeSalubridad = condicionesDeSalubridad;
    }

    public Boolean getImpactoAmbientalAire() {
        return impactoAmbientalAire;
    }

    public void setImpactoAmbientalAire(Boolean impactoAmbientalAire) {
        this.impactoAmbientalAire = impactoAmbientalAire;
    }

    public Boolean getImpactoAmbientalBasura() {
        return impactoAmbientalBasura;
    }

    public void setImpactoAmbientalBasura(Boolean impactoAmbientalBasura) {
        this.impactoAmbientalBasura = impactoAmbientalBasura;
    }

    public Boolean getImpactoAmbientalInseguridad() {
        return impactoAmbientalInseguridad;
    }

    public void setImpactoAmbientalInseguridad(Boolean impactoAmbientalInseguridad) {
        this.impactoAmbientalInseguridad = impactoAmbientalInseguridad;
    }

    public Boolean getImpactoAmbientalRuido() {
        return impactoAmbientalRuido;
    }

    public void setImpactoAmbientalRuido(Boolean impactoAmbientalRuido) {
        this.impactoAmbientalRuido = impactoAmbientalRuido;
    }

    public Boolean getImpactoAmbientalAguasServidas() {
        return impactoAmbientalAguasServidas;
    }

    public void setImpactoAmbientalAguasServidas(
            Boolean impactoAmbientalAguasServidas) {
        this.impactoAmbientalAguasServidas = impactoAmbientalAguasServidas;
            }

    public Boolean getImpactoAmbientalOtro() {
        return impactoAmbientalOtro;
    }

    public void setImpactoAmbientalOtro(Boolean impactoAmbientalOtro) {
        this.impactoAmbientalOtro = impactoAmbientalOtro;
    }

    public String getExplicacionImpactoAmbientalAire() {
        return explicacionImpactoAmbientalAire;
    }

    public void setExplicacionImpactoAmbientalAire(
            String explicacionImpactoAmbientalAire) {
        this.explicacionImpactoAmbientalAire = explicacionImpactoAmbientalAire;
            }

    public String getExplicacionImpactoAmbientalBasura() {
        return explicacionImpactoAmbientalBasura;
    }

    public void setExplicacionImpactoAmbientalBasura(
            String explicacionImpactoAmbientalBasura) {
        this.explicacionImpactoAmbientalBasura = explicacionImpactoAmbientalBasura;
            }

    public String getExplicacionImpactoAmbientalInseguridad() {
        return explicacionImpactoAmbientalInseguridad;
    }

    public void setExplicacionImpactoAmbientalInseguridad(
            String explicacionImpactoAmbientalInseguridad) {
        this.explicacionImpactoAmbientalInseguridad = explicacionImpactoAmbientalInseguridad;
            }

    public String getExplicacionImpactoAmbientalRuido() {
        return explicacionImpactoAmbientalRuido;
    }

    public void setExplicacionImpactoAmbientalRuido(
            String explicacionImpactoAmbientalRuido) {
        this.explicacionImpactoAmbientalRuido = explicacionImpactoAmbientalRuido;
            }

    public String getExplicacionImpactoAmbientalAguasServidas() {
        return explicacionImpactoAmbientalAguasServidas;
    }

    public void setExplicacionImpactoAmbientalAguasServidas(
            String explicacionImpactoAmbientalAguasServidas) {
        this.explicacionImpactoAmbientalAguasServidas = explicacionImpactoAmbientalAguasServidas;
            }

    public String getExplicacionOtro() {
        return explicacionOtro;
    }

    public void setExplicacionOtro(String explicacionOtro) {
        this.explicacionOtro = explicacionOtro;
    }

    public ListaEstado getEstadoDeLasVias() {
        return estadoDeLasVias;
    }

    public void setEstadoDeLasVias(ListaEstado estadoDeLasVias) {
        this.estadoDeLasVias = estadoDeLasVias;
    }

    public Boolean getViasPavimentadas() {
        return viasPavimentadas;
    }

    public void setViasPavimentadas(Boolean viasPavimentadas) {
        this.viasPavimentadas = viasPavimentadas;
    }

    public Boolean getAndenesEnLasVias() {
        return andenesEnLasVias;
    }

    public void setAndenesEnLasVias(Boolean andenesEnLasVias) {
        this.andenesEnLasVias = andenesEnLasVias;
    }

    public Boolean getSardinelesEnLasVias() {
        return sardinelesEnLasVias;
    }

    public void setSardinelesEnLasVias(Boolean sardinelesEnLasVias) {
        this.sardinelesEnLasVias = sardinelesEnLasVias;
    }

    public Boolean getParques() {
        return parques;
    }

    public void setParques(Boolean parques) {
        this.parques = parques;
    }

    public Boolean getParadero() {
        return paradero;
    }

    public void setParadero(Boolean paradero) {
        this.paradero = paradero;
    }

    public Boolean getAlumbrado() {
        return alumbrado;
    }

    public void setAlumbrado(Boolean alumbrado) {
        this.alumbrado = alumbrado;
    }

    public Boolean getZonasVerdesPublicas() {
        return zonasVerdesPublicas;
    }

    public void setZonasVerdesPublicas(Boolean zonasVerdesPublicas) {
        this.zonasVerdesPublicas = zonasVerdesPublicas;
    }

    public Boolean getArborizacion() {
        return arborizacion;
    }

    public void setArborizacion(Boolean arborizacion) {
        this.arborizacion = arborizacion;
    }

    public Boolean getAlamedas() {
        return alamedas;
    }

    public void setAlamedas(Boolean alamedas) {
        this.alamedas = alamedas;
    }

    public Boolean getCicloRutas() {
        return cicloRutas;
    }

    public void setCicloRutas(Boolean cicloRutas) {
        this.cicloRutas = cicloRutas;
    }

    public ListaClaseInmueble getClaseInmueble() {
        return claseInmueble;
    }

    public void setClaseInmueble(ListaClaseInmueble claseInmueble) {
        this.claseInmueble = claseInmueble;
    }

    public String getDescripcionOtrosTipoInmueble() {
        return descripcionOtrosTipoInmueble;
    }

    public void setDescripcionOtrosTipoInmueble(String descripcionOtrosTipoInmueble) {
        this.descripcionOtrosTipoInmueble = descripcionOtrosTipoInmueble;
    }

    public ListaUsoActual   getUsoActual() {
        return usoActual;
    }

    public void setUsoActual(ListaUsoActual  usoActual) {
        this.usoActual = usoActual;
    }

    public String getDescripcionOtrosUsoInmueble() {
        return descripcionOtrosUsoInmueble;
    }

    public void setDescripcionOtrosUsoInmueble(String descripcionOtrosUsoInmueble) {
        this.descripcionOtrosUsoInmueble = descripcionOtrosUsoInmueble;
    }

    public ListaTipoVivienda getTipoDeVivienda() {
        return tipoDeVivienda;
    }

    public void setTipoDeVivienda(ListaTipoVivienda tipoDeVivienda) {
        this.tipoDeVivienda = tipoDeVivienda;
    }

    public String getDescripcionOtrosTipoVivienda() {
        return descripcionOtrosTipoVivienda;
    }

    public void setDescripcionOtrosTipoVivienda(String descripcionOtrosTipoVivienda) {
        this.descripcionOtrosTipoVivienda = descripcionOtrosTipoVivienda;
    }

    public String getChip() {
        return chip;
    }

    public void setChip(String chip) {
        this.chip = chip;
    }

    public String getMatriculaInmobiliariaPrincipal2() {
        return matriculaInmobiliariaPrincipal2;
    }

    public void setMatriculaInmobiliariaPrincipal2(
            String matriculaInmobiliariaPrincipal2) {
        this.matriculaInmobiliariaPrincipal2 = matriculaInmobiliariaPrincipal2;
            }

    public String getNumeroDeEscritura() {
        return numeroDeEscritura;
    }

    public void setNumeroDeEscritura(String numeroDeEscritura) {
        this.numeroDeEscritura = numeroDeEscritura;
    }

    public String getNumeroNotariaDeEscritura() {
        return numeroNotariaDeEscritura;
    }

    public void setNumeroNotariaDeEscritura(String numeroNotariaDeEscritura) {
        this.numeroNotariaDeEscritura = numeroNotariaDeEscritura;
    }

    public Date getFechaDeEscritura() {
        return fechaDeEscritura;
    }

    public void setFechaDeEscritura(Date fechaDeEscritura) {
        this.fechaDeEscritura = fechaDeEscritura;
    }

    public String getDepartamentoNotaria() {
        return departamentoNotaria;
    }

    public void setDepartamentoNotaria(String departamentoNotaria) {
        this.departamentoNotaria = departamentoNotaria;
    }

    public DivipolaDTO getCiudadNotaria() {
        return ciudadNotaria;
    }

    public void setCiudadNotaria(DivipolaDTO ciudadNotaria) {
        this.ciudadNotaria = ciudadNotaria;
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

    public String getNumeroDeposito1() {
		return numeroDeposito1;
	}

	public void setNumeroDeposito1(String numeroDeposito1) {
		this.numeroDeposito1 = numeroDeposito1;
	}

	public String getNumeroDeposito2() {
		return numeroDeposito2;
	}

	public void setNumeroDeposito2(String numeroDeposito2) {
		this.numeroDeposito2 = numeroDeposito2;
	}

	public String getNumeroDeposito3() {
		return numeroDeposito3;
	}

	public void setNumeroDeposito3(String numeroDeposito3) {
		this.numeroDeposito3 = numeroDeposito3;
	}

	public String getNumeroDeposito4() {
		return numeroDeposito4;
	}

	public void setNumeroDeposito4(String numeroDeposito4) {
		this.numeroDeposito4 = numeroDeposito4;
	}

	public String getNumeroDeposito5() {
		return numeroDeposito5;
	}

	public void setNumeroDeposito5(String numeroDeposito5) {
		this.numeroDeposito5 = numeroDeposito5;
	}

	public String getNumeroDepositoExclusivo1() {
		return numeroDepositoExclusivo1;
	}

	public void setNumeroDepositoExclusivo1(String numeroDepositoExclusivo1) {
		this.numeroDepositoExclusivo1 = numeroDepositoExclusivo1;
	}

	public String getNumeroDepositoExclusivo2() {
		return numeroDepositoExclusivo2;
	}

	public void setNumeroDepositoExclusivo2(String numeroDepositoExclusivo2) {
		this.numeroDepositoExclusivo2 = numeroDepositoExclusivo2;
	}

	public String getNumeroDepositoExclusivo3() {
		return numeroDepositoExclusivo3;
	}

	public void setNumeroDepositoExclusivo3(String numeroDepositoExclusivo3) {
		this.numeroDepositoExclusivo3 = numeroDepositoExclusivo3;
	}

	public String getNumeroDepositoExclusivo4() {
		return numeroDepositoExclusivo4;
	}

	public void setNumeroDepositoExclusivo4(String numeroDepositoExclusivo4) {
		this.numeroDepositoExclusivo4 = numeroDepositoExclusivo4;
	}

	public String getNumeroDepositoExclusivo5() {
		return numeroDepositoExclusivo5;
	}

	public void setNumeroDepositoExclusivo5(String numeroDepositoExclusivo5) {
		this.numeroDepositoExclusivo5 = numeroDepositoExclusivo5;
	}

	public Integer getNumeroDePisos() {
        return numeroDePisos;
    }

    public void setNumeroDePisos(Integer numeroDePisos) {
        if(new Integer(0).equals(numeroDePisos)){
            numeroDePisos = null;
        }
        this.numeroDePisos = numeroDePisos;
    }

    public Integer getNumeroSotanos() {
        return numeroSotanos;
    }

    public void setNumeroSotanos(Integer numeroSotanos) {
        if(new Integer(0).equals(numeroSotanos)){
            numeroSotanos = null;
        }
        this.numeroSotanos = numeroSotanos;
    }

    public Integer getVetustez() {
        return vetustez;
    }

    public void setVetustez(Integer vetustez) {
        /*if(new Integer(0).equals(vetustez)){
          vetustez = null;
          }*/
        this.vetustez = vetustez;
    }

    public ListaEstadoConservacion getEstadoDeConservacion() {
        return estadoDeConservacion;
    }

    public void setEstadoDeConservacion(ListaEstadoConservacion estadoDeConservacion) {
        this.estadoDeConservacion = estadoDeConservacion;
    }

    public ListaEstadoConstruccion getEstadoDeConstruccion() {
        return estadoDeConstruccion;
    }

    public void setEstadoDeConstruccion(ListaEstadoConstruccion estadoDeConstruccion) {
        this.estadoDeConstruccion = estadoDeConstruccion;
    }

    public Boolean getEstadoDeObra() {
        if(estadoDeObra == null)
            estadoDeObra = new Boolean(false);
        return estadoDeObra;
    }

    public void setEstadoDeObra(Boolean estadoDeObra) {
        this.estadoDeObra = estadoDeObra;
    }

    public Integer getAnoDeConstruccion() {
        return anoDeConstruccion;
    }

    public void setAnoDeConstruccion(Integer anoDeConstruccion) {
        this.anoDeConstruccion = anoDeConstruccion;
    }

    public Integer getPorcentajeAvance() {
        return porcentajeAvance;
    }

    public void setPorcentajeAvance(Integer porcentajeAvance) {
        this.porcentajeAvance = porcentajeAvance;
    }

    public Boolean getRemodelado() {
        return remodelado;
    }

    public void setRemodelado(Boolean remodelado) {
        this.remodelado = remodelado;
    }

    public Date getFechaRemodelacion() {
		return fechaRemodelacion;
	}

	public void setFechaRemodelacion(Date fechaRemodelacion) {
		this.fechaRemodelacion = fechaRemodelacion;
	}

	public ListaEstructura getEstructura() {
        return estructura;
    }

    public void setEstructura(ListaEstructura estructura) {
        this.estructura = estructura;
    }

    public ListaEstructuraReforzada getEstructuraReforzada() {
        return estructuraReforzada;
    }

    public void setEstructuraReforzada(ListaEstructuraReforzada estructuraReforzada) {
        this.estructuraReforzada = estructuraReforzada;
    }

    public ListaFachada getFachada() {
        return fachada;
    }

    public void setFachada(ListaFachada fachada) {
        this.fachada = fachada;
    }

    public ListaTipoFachada getTipoFachada() {
        return tipoFachada;
    }

    public void setTipoFachada(ListaTipoFachada tipoFachada) {
        this.tipoFachada = tipoFachada;
    }

    public ListaCubierta getCubierta() {
    	if (new Integer(0).equals(fachada)) {
    		 this.fachada = null;
        }
        return cubierta;
    }

    public void setCubierta(ListaCubierta cubierta) {
        this.cubierta = cubierta;
    }

    public ListaPisoBodega getPisosBodega() {
        return pisosBodega;
    }

    public void setPisosBodega(ListaPisoBodega pisosBodega) {
        this.pisosBodega = pisosBodega;
    }

    public String getTipologiaViviendaUnicaFamiliar() {
		return tipologiaViviendaUnicaFamiliar;
	}

	public void setTipologiaViviendaUnicaFamiliar(String tipologiaViviendaUnicaFamiliar) {
		this.tipologiaViviendaUnicaFamiliar = tipologiaViviendaUnicaFamiliar;
	}

	public Integer getSala() {
        return sala;
    }

    public void setSala(Integer sala) {
        if(new Integer(0).equals(sala)){
            sala = null;
        }
        this.sala = sala;
    }

    public Integer getDeposito() {
        return deposito;
    }

    public void setDeposito(Integer deposito) {
        this.deposito = deposito;
    }

    public Integer getComedor() {
        return comedor;
    }

    public void setComedor(Integer comedor) {
        if(new Integer(0).equals(comedor)){
            comedor = null;
        }
        this.comedor = comedor;
    }

    public Integer getEstudio() {
        return estudio;
    }

    public void setEstudio(Integer estudio) {
        if(new Integer(0).equals(estudio)){
            estudio = null;
        }
        this.estudio = estudio;
    }

    public Integer getJardin() {
        return jardin;
    }

    public void setJardin(Integer jardin) {
        if(new Integer(0).equals(jardin)){
            jardin = null;
        }
        this.jardin = jardin;
    }

    public Integer getBanoSocial() {
        return banoSocial;
    }

    public void setBanoSocial(Integer banoSocial) {
        if(new Integer(0).equals(banoSocial)){
            banoSocial = null;
        }
        this.banoSocial = banoSocial;
    }

    public Integer getEstarHabitacion() {
        return estarHabitacion;
    }

    public void setEstarHabitacion(Integer estarHabitacion) {
        if(new Integer(0).equals(estarHabitacion)){
            estarHabitacion = null;
        }
        this.estarHabitacion = estarHabitacion;
    }

    public Integer getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(Integer habitaciones) {
        if(new Integer(0).equals(habitaciones)){
            habitaciones = null;
        }
        this.habitaciones = habitaciones;
    }

    public Integer getDepositosPrivados() {
        return depositosPrivados;
    }

    public void setDepositosPrivados(Integer depositosPrivados) {
        if(new Integer(0).equals(depositosPrivados)){
            depositosPrivados = null;
        }
        this.depositosPrivados = depositosPrivados;
    }

    public Integer getDepositosExclusivos() {
        return depositosExclusivos;
    }

    public void setDepositosExclusivos(Integer depositosExclusivos) {
        if(new Integer(0).equals(depositosExclusivos)){
            depositosExclusivos = null;
        }
        this.depositosExclusivos = depositosExclusivos;
    }

    public Integer getNumeroTotalDepositos() {
        return numeroTotalDepositos;
    }

    public void setNumeroTotalDepositos(Integer numeroTotalDepositos) {
        this.numeroTotalDepositos = numeroTotalDepositos;
    }

    public Integer getBanoPrivado() {
        return banoPrivado;
    }

    public void setBanoPrivado(Integer banoPrivado) {
        if(new Integer(0).equals(banoPrivado)){
            banoPrivado = null;
        }
        this.banoPrivado = banoPrivado;
    }

    public Integer getCocina() {
        return cocina;
    }

    public void setCocina(Integer cocina) {
        if(new Integer(0).equals(cocina)){
            cocina = null;
        }
        this.cocina = cocina;
    }

    public Integer getCuartoServicio() {
        return cuartoServicio;
    }

    public void setCuartoServicio(Integer cuartoServicio) {
        if(new Integer(0).equals(cuartoServicio)){
            cuartoServicio = null;
        }
        this.cuartoServicio = cuartoServicio;
    }

    public Integer getOficina() {
        return oficina;
    }

    public void setOficina(Integer oficina) {
        if(new Integer(0).equals(oficina)){
            oficina = null;
        }
        this.oficina = oficina;
    }

    public Integer getBanoServicio() {
        return banoServicio;
    }

    public void setBanoServicio(Integer banoServicio) {
        if(new Integer(0).equals(banoServicio)){
            banoServicio = null;
        }
        this.banoServicio = banoServicio;
    }

    public Integer getPatioInterior() {
        return patioInterior;
    }

    public void setPatioInterior(Integer patioInterior) {
        if(new Integer(0).equals(patioInterior)){
            patioInterior = null;
        }
        this.patioInterior = patioInterior;
    }

    public Integer getTerraza() {
        return terraza;
    }

    public void setTerraza(Integer terraza) {
        if(new Integer(0).equals(terraza)){
            terraza = null;
        }
        this.terraza = terraza;
    }

    public Integer getBodega() {
        return bodega;
    }

    public void setBodega(Integer bodega) {
        if(new Integer(0).equals(bodega)){
            bodega = null;
        }
        this.bodega = bodega;
    }

    public Integer getZonaDeRopas() {
        return zonaDeRopas;
    }

    public void setZonaDeRopas(Integer zonaDeRopas) {
        if(new Integer(0).equals(zonaDeRopas)){
            zonaDeRopas = null;
        }
        this.zonaDeRopas = zonaDeRopas;
    }

    public Integer getBalcon() {
        return balcon;
    }

    public void setBalcon(Integer balcon) {
        if(new Integer(0).equals(balcon)){
            balcon = null;
        }
        this.balcon = balcon;
    }

    public Integer getCloset() {
        return closet;
    }

    public void setCloset(Integer closet) {
        if(new Integer(0).equals(closet)){
            closet = null;
        }
        this.closet = closet;
    }

    public Integer getLocal() {
        return local;
    }

    public void setLocal(Integer local) {
        if(new Integer(0).equals(local)){
            local = null;
        }
        this.local = local;
    }

    public Integer getZonaVerdePrivada() {
        return zonaVerdePrivada;
    }

    public void setZonaVerdePrivada(Integer zonaVerdePrivada) {
        if(new Integer(0).equals(zonaVerdePrivada)){
            zonaVerdePrivada = null;
        }
        this.zonaVerdePrivada = zonaVerdePrivada;
    }

    public ListaEstado getIluminacion() {
        return iluminacion;
    }

    public void setIluminacion(ListaEstado iluminacion) {
        this.iluminacion = iluminacion;
    }

    public ListaEstado getVentilacion() {
        return ventilacion;
    }

    public void setVentilacion(ListaEstado ventilacion) {
        this.ventilacion = ventilacion;
    }
    public Integer getNumeroTotalDeGarajes() {
        return numeroTotalDeGarajes;
    }

    public void setNumeroTotalDeGarajes(Integer numeroTotalDeGarajes) {
        this.numeroTotalDeGarajes = numeroTotalDeGarajes;
    }

    public Integer getTotalCuposParquedaro() {
        return totalCuposParquedaro;
    }

    public void setTotalCuposParquedaro(Integer totalCuposParquedaro) {
        this.totalCuposParquedaro = totalCuposParquedaro;
    }

    public ListaEstadosAcabados getEstadoAcabadosPisos() {
        return estadoAcabadosPisos;
    }

    public void setEstadoAcabadosPisos(ListaEstadosAcabados estadoAcabadosPisos) {
        this.estadoAcabadosPisos = estadoAcabadosPisos;
    }

    public ListaEstadosAcabados getEstadoAcabadosMuros() {
        return estadoAcabadosMuros;
    }

    public void setEstadoAcabadosMuros(ListaEstadosAcabados estadoAcabadosMuros) {
        this.estadoAcabadosMuros = estadoAcabadosMuros;
    }

    public ListaEstadosAcabados getEstadoAcabadosTechos() {
        return estadoAcabadosTechos;
    }

    public void setEstadoAcabadosTechos(ListaEstadosAcabados estadoAcabadosTechos) {
        this.estadoAcabadosTechos = estadoAcabadosTechos;
    }

    public ListaEstadosAcabados getEstadoAcabadosMadera() {
        return estadoAcabadosMadera;
    }

    public void setEstadoAcabadosMadera(ListaEstadosAcabados estadoAcabadosMadera) {
        this.estadoAcabadosMadera = estadoAcabadosMadera;
    }

    public ListaEstadosAcabados getEstadoAcabadosMetal() {
        return estadoAcabadosMetal;
    }

    public void setEstadoAcabadosMetal(ListaEstadosAcabados estadoAcabadosMetal) {
        this.estadoAcabadosMetal = estadoAcabadosMetal;
    }

    public ListaEstadosAcabados getEstadoAcabadosBanos() {
        return estadoAcabadosBanos;
    }

    public void setEstadoAcabadosBanos(ListaEstadosAcabados estadoAcabadosBanos) {
        this.estadoAcabadosBanos = estadoAcabadosBanos;
    }

    public ListaEstadosAcabados getEstadoAcabadosCocina() {
        return estadoAcabadosCocina;
    }

    public void setEstadoAcabadosCocina(ListaEstadosAcabados estadoAcabadosCocina) {
        this.estadoAcabadosCocina = estadoAcabadosCocina;
    }

    public ListaCalidadAcabados getCalidadAcabadosPisos() {
        return calidadAcabadosPisos;
    }

    public void setCalidadAcabadosPisos(ListaCalidadAcabados calidadAcabadosPisos) {
        this.calidadAcabadosPisos = calidadAcabadosPisos;
    }

    public ListaCalidadAcabados getCalidadAcabadosMuros() {
        return calidadAcabadosMuros;
    }

    public void setCalidadAcabadosMuros(ListaCalidadAcabados calidadAcabadosMuros) {
        this.calidadAcabadosMuros = calidadAcabadosMuros;
    }

    public ListaCalidadAcabados getCalidadAcabadosTechos() {
        return calidadAcabadosTechos;
    }

    public void setCalidadAcabadosTechos(ListaCalidadAcabados calidadAcabadosTechos) {
        this.calidadAcabadosTechos = calidadAcabadosTechos;
    }

    public ListaCalidadAcabados getCalidadAcabadosMadera() {
        return calidadAcabadosMadera;
    }

    public void setCalidadAcabadosMadera(ListaCalidadAcabados calidadAcabadosMadera) {
        this.calidadAcabadosMadera = calidadAcabadosMadera;
    }

    public ListaCalidadAcabados  getCalidadAcabadosMetal() {
        return calidadAcabadosMetal;
    }

    public void setCalidadAcabadosMetal(ListaCalidadAcabados calidadAcabadosMetal) {
        this.calidadAcabadosMetal = calidadAcabadosMetal;
    }

    public ListaCalidadAcabados getCalidadAcabadosBanos() {
        return calidadAcabadosBanos;
    }

    public void setCalidadAcabadosBanos(ListaCalidadAcabados calidadAcabadosBanos) {
        this.calidadAcabadosBanos = calidadAcabadosBanos;
    }

    public ListaCalidadAcabadosCocina getCalidadAcabadosCocina() {
        return calidadAcabadosCocina;
    }

    public void setCalidadAcabadosCocina(ListaCalidadAcabadosCocina calidadAcabadosCocina) {
        this.calidadAcabadosCocina = calidadAcabadosCocina;
    }

    public Boolean getSometidoAPropiedadHorizontal() {
        return sometidoAPropiedadHorizontal;
    }

    public void setSometidoAPropiedadHorizontal(Boolean sometidoAPropiedadHorizontal) {
        this.sometidoAPropiedadHorizontal = sometidoAPropiedadHorizontal;
    }

    public Boolean getConjuntoCerrado() {
        if(conjuntoCerrado == null){
            conjuntoCerrado = new Boolean(false);
        }
        return conjuntoCerrado;
    }

    public void setConjuntoCerrado(Boolean conjuntoCerrado) {
        this.conjuntoCerrado = conjuntoCerrado;
    }

    public ListaUbicacionInmueble getUbicacionDelInmueble() {
        return ubicacionDelInmueble;
    }

    public void setUbicacionDelInmueble(ListaUbicacionInmueble ubicacionDelInmueble) {
        this.ubicacionDelInmueble = ubicacionDelInmueble;
    }

    public Integer getNumeroPiso() {
		return numeroPiso;
	}

	public void setNumeroPiso(Integer numeroPiso) {
		this.numeroPiso = numeroPiso;
	}

	public Integer getNumeroDeEdificios() {
        return numeroDeEdificios;
    }

    public void setNumeroDeEdificios(Integer numeroDeEdificios) {
    	if(numeroDeEdificios != null && numeroDeEdificios == 0){
    		this.numeroDeEdificios = null;
    	}else{
    		this.numeroDeEdificios = numeroDeEdificios;
    	}
    }

    public Integer getUnidadesPorPiso() {
        return unidadesPorPiso;
    }

    public void setUnidadesPorPiso(Integer unidadesPorPiso) {
        if(unidadesPorPiso != null && unidadesPorPiso == 0){
        	this.unidadesPorPiso = null;
        }else {
        	this.unidadesPorPiso = unidadesPorPiso;
        }
    }

    public Integer getTotalUnidades() {
        return totalUnidades;
    }

    public void setTotalUnidades(Integer totalUnidades) {
        if(totalUnidades != null && totalUnidades == 0){
        	this.totalUnidades = null;
        }else{
        	this.totalUnidades = totalUnidades;
        }
    }

    public Boolean getPorteria() {
        return porteria;
    }

    public void setPorteria(Boolean porteria) {
        this.porteria = porteria;
    }

    public Boolean getCitofono() {
        return citofono;
    }

    public void setCitofono(Boolean citofono) {
        this.citofono = citofono;
    }

    public Boolean getBicicletero() {
        return bicicletero;
    }

    public void setBicicletero(Boolean bicicletero) {
        this.bicicletero = bicicletero;
    }

    public Boolean getPiscina() {
        return piscina;
    }

    public void setPiscina(Boolean piscina) {
        this.piscina = piscina;
    }

    public Boolean getTanqueDeAgua() {
        return tanqueDeAgua;
    }

    public void setTanqueDeAgua(Boolean tanqueDeAgua) {
        this.tanqueDeAgua = tanqueDeAgua;
    }

    public Boolean getClubHouse() {
        return clubHouse;
    }

    public void setClubHouse(Boolean clubHouse) {
        this.clubHouse = clubHouse;
    }

    public Boolean getGarajeVisitantes() {
        return garajeVisitantes;
    }

    public void setGarajeVisitantes(Boolean garajeVisitantes) {
        this.garajeVisitantes = garajeVisitantes;
    }

    public Boolean getJuegosNinos() {
        return juegosNinos;
    }

    public void setJuegosNinos(Boolean juegosNinos) {
        this.juegosNinos = juegosNinos;
    }

    public Boolean getCanchaMultiple() {
        return canchaMultiple;
    }

    public void setCanchaMultiple(Boolean canchaMultiple) {
        this.canchaMultiple = canchaMultiple;
    }

    public Boolean getBombaEyectora() {
        return bombaEyectora;
    }

    public void setBombaEyectora(Boolean bombaEyectora) {
        this.bombaEyectora = bombaEyectora;
    }

    public Boolean getAireAcondicionadoCentral() {
        return aireAcondicionadoCentral;
    }

    public void setAireAcondicionadoCentral(Boolean aireAcondicionadoCentral) {
        this.aireAcondicionadoCentral = aireAcondicionadoCentral;
    }

    public Boolean getCanchaSquash() {
        return canchaSquash;
    }

    public void setCanchaSquash(Boolean canchaSquash) {
        this.canchaSquash = canchaSquash;
    }

    public Boolean getZonasVerdesComunales() {
        return zonasVerdesComunales;
    }

    public void setZonasVerdesComunales(Boolean zonasVerdesComunales) {
        this.zonasVerdesComunales = zonasVerdesComunales;
    }

    public Boolean getGimnasio() {
        return gimnasio;
    }

    public void setGimnasio(Boolean gimnasio) {
        this.gimnasio = gimnasio;
    }

    public Boolean getGolfito() {
        return golfito;
    }

    public void setGolfito(Boolean golfito) {
        this.golfito = golfito;
    }

    public Boolean getSalonComunal() {
        return salonComunal;
    }

    public void setSalonComunal(Boolean salonComunal) {
        this.salonComunal = salonComunal;
    }

    public Boolean getShutBasuras() {
        return shutBasuras;
    }

    public void setShutBasuras(Boolean shutBasuras) {
        this.shutBasuras = shutBasuras;
    }

    public Boolean getEquipoDePresionConstante() {
        return equipoDePresionConstante;
    }

    public void setEquipoDePresionConstante(Boolean equipoDePresionConstante) {
        this.equipoDePresionConstante = equipoDePresionConstante;
    }

    public Boolean getPlantaElectrica() {
        return plantaElectrica;
    }

    public void setPlantaElectrica(Boolean plantaElectrica) {
        this.plantaElectrica = plantaElectrica;
    }

    public Boolean getAscensor() {
        return ascensor;
    }

    public void setAscensor(Boolean ascensor) {
        this.ascensor = ascensor;
    }

    public Integer getNumeroDeAscensores() {
        return numeroDeAscensores;
    }

    public void setNumeroDeAscensores(Integer numeroDeAscensores) {
        this.numeroDeAscensores = numeroDeAscensores;
    }

    public String getOtros() {
        return otros;
    }

    public void setOtros(String otros) {
        this.otros = otros;
    }

    public String getActualidadEdificadora() {
        return actualidadEdificadora;
    }

    public void setActualidadEdificadora(String actualidadEdificadora) {
        this.actualidadEdificadora = actualidadEdificadora;
    }

    public String getEntregaDeDocumentos() {
        return entregaDeDocumentos;
    }

    public void setEntregaDeDocumentos(String entregaDeDocumentos) {
        this.entregaDeDocumentos = entregaDeDocumentos;
    }

    public String getComportamientoOfertaDemanda() {
        return comportamientoOfertaDemanda;
    }

    public void setComportamientoOfertaDemanda(String comportamientoOfertaDemanda) {
        this.comportamientoOfertaDemanda = comportamientoOfertaDemanda;
    }

    public Double getTiempoEsperadoDeComercializacion() {
        return tiempoEsperadoDeComercializacion;
    }

    /**
     * Assigns the {@link Double tiempoEsperadoDeComercializacion} if is different from null
     * and is greater than 0.
     * @param tiempoEsperadoDeComercializacion the new value
     */
    public void setTiempoEsperadoDeComercializacion(Double tiempoEsperadoDeComercializacion) {
        if(tiempoEsperadoDeComercializacion != null && tiempoEsperadoDeComercializacion.compareTo(0D) <= 0){
            return;
        }
        this.tiempoEsperadoDeComercializacion = tiempoEsperadoDeComercializacion;
    }

    public BigDecimal getPorcentajeTerreno() {
        return porcentajeTerreno;
    }

    public void setPorcentajeTerreno( BigDecimal porcentajeTerreno) {
    	if( porcentajeTerreno != null 
                && BigDecimal.ZERO.compareTo(porcentajeTerreno) == 0 ){
            return;
    	}
        this.porcentajeTerreno = porcentajeTerreno;
    }

    public BigDecimal getValorProporcionalTerreno() {
        return valorProporcionalTerreno;
    }

    public void setValorProporcionalTerreno(
            BigDecimal valorProporcionalTerreno) {
        this.valorProporcionalTerreno = valorProporcionalTerreno;
            }

    public BigDecimal getValorProporcionalConstruccion() {
        return valorProporcionalConstruccion;
    }

    public void setValorProporcionalConstruccion(
            BigDecimal valorProporcionalConstruccion) {
        if (valorProporcionalConstruccion != null) {
            this.valorProporcionalConstruccion = valorProporcionalConstruccion.setScale(4, RoundingMode.HALF_UP);
        }else{
            this.valorProporcionalConstruccion = valorProporcionalConstruccion;
        }
            }

    public BigDecimal getValorIntegralTerreno() {
        return valorIntegralTerreno;
    }

    public void setValorIntegralTerreno(BigDecimal valorIntegralTerreno) {
        this.valorIntegralTerreno = valorIntegralTerreno;
    }

    public BigDecimal getValorIntegralConstruccion() {
        return valorIntegralConstruccion;
    }

    public void setValorIntegralConstruccion(
            BigDecimal valorIntegralConstruccion) {
        this.valorIntegralConstruccion = valorIntegralConstruccion;
            }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getDireccionAnexos() {
        return direccionAnexos;
    }

    public void setDireccionAnexos(String direccionAnexos) {
        this.direccionAnexos = direccionAnexos;
    }

    public String getOtrasDirecciones() {
        return otrasDirecciones;
    }

    public void setOtrasDirecciones(String otrasDirecciones) {
        this.otrasDirecciones = otrasDirecciones;
    }

    public String getLicenciaConstruccion() {
		return licenciaConstruccion;
	}

	public void setLicenciaConstruccion(String licenciaConstruccion) {
		this.licenciaConstruccion = licenciaConstruccion;
	}

	public ListaTipoLicencia getTipoLicencia() {
        return tipoLicencia;
    }

    public void setTipoLicencia(ListaTipoLicencia tipoLicencia) {
        this.tipoLicencia = tipoLicencia;
    }

    public String getNumeroLicencia() {
        return numeroLicencia;
    }

    public void setNumeroLicencia(String numeroLicencia) {
        this.numeroLicencia = numeroLicencia;
    }

    public ListaReparado getReparados() {
        return reparados;
    }

    public void setReparados(ListaReparado reparados) {
        this.reparados = reparados;
    }

    public ListaDanoPrevio getDanosPrevios() {
        return danosPrevios;
    }

    public void setDanosPrevios(ListaDanoPrevio danosPrevios) {
        this.danosPrevios = danosPrevios;
    }

    public ListaMaterialConstructor getMaterialConstructor() {
        return materialConstructor;
    }

    public void setMaterialConstructor(ListaMaterialConstructor materialConstructor) {
        this.materialConstructor = materialConstructor;
    }

    public ListaDetalleMaterial getDetalleMaterial() {
        return detalleMaterial;
    }

    public void setDetalleMaterial(ListaDetalleMaterial detalleMaterial) {
        this.detalleMaterial = detalleMaterial;
    }

    public ListaIrregularidad getIrregularidadPlanta() {
        return irregularidadPlanta;
    }

    public void setIrregularidadPlanta(ListaIrregularidad irregularidadPlanta) {
        this.irregularidadPlanta = irregularidadPlanta;
    }

    public ListaIrregularidad getIrregularidadAltura() {
        return irregularidadAltura;
    }

    public void setIrregularidadAltura(ListaIrregularidad irregularidadAltura) {
        this.irregularidadAltura = irregularidadAltura;
    }

    public BigDecimal getCoeficiente() {
        return coeficiente;
    }

    public void setCoeficiente(BigDecimal coeficiente) {
        this.coeficiente = coeficiente;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public BigDecimal getAreaConstruida() {
        return areaConstruida;
    }

    public void setAreaConstruida(BigDecimal areaConstruida) {
        this.areaConstruida = areaConstruida;
    }

    public BigDecimal getAreaPrivada() {
        return areaPrivada;
    }

    public void setAreaPrivada(BigDecimal areaPrivada) {
        this.areaPrivada = areaPrivada;
    }

    public String getDescripcionUsoPredominanteMixto() {
        return descripcionUsoPredominanteMixto;
    }

    public void setDescripcionUsoPredominanteMixto(
            String descripcionUsoPredominanteMixto) {
        this.descripcionUsoPredominanteMixto = descripcionUsoPredominanteMixto;
            }

    @Override
    public Object clone() throws CloneNotSupportedException {
        // Clonacion profunda
        FormatoInformeHipotecarioDTO clone = new FormatoInformeHipotecarioDTO(
                getAvaluoId(), valorComercial, valorCortesia, metodoDeValuacion,
                justificacionDeMetodologia, documentosConsultados,
                acueductoEnElSector, acueductoEnElPredio,
                alcantarilladoEnElSector, alcantarilladoEnElPredio,
                energiaEnElSector, energiaEnElPredio, gasEnElSector,
                gasEnElPredio, telefonoEnElSector, telefonoEnElPredio, estrato,
                barrioLegal, usoPredominanteInmueble,
                descripcionUsoPredominanteMixto, topografiaSector, transporte,
                perspectivasDeValorizacion, condicionesDeSalubridad,
                impactoAmbientalAire, impactoAmbientalBasura,
                impactoAmbientalInseguridad, impactoAmbientalRuido,
                impactoAmbientalAguasServidas, impactoAmbientalOtro,
                explicacionImpactoAmbientalAire,
                explicacionImpactoAmbientalBasura,
                explicacionImpactoAmbientalInseguridad,
                explicacionImpactoAmbientalRuido,
                explicacionImpactoAmbientalAguasServidas, explicacionOtro,
                estadoDeLasVias, viasPavimentadas, andenesEnLasVias,
                sardinelesEnLasVias, parques, paradero, alumbrado,
                zonasVerdesPublicas, arborizacion, alamedas, cicloRutas,
                claseInmueble, descripcionOtrosTipoInmueble, usoActual,
                descripcionOtrosUsoInmueble, tipoDeVivienda,
                descripcionOtrosTipoVivienda, chip,
                matriculaInmobiliariaPrincipal2, numeroDeEscritura,
                numeroNotariaDeEscritura, fechaDeEscritura,
                departamentoNotaria, ciudadNotaria, matriculaInmobiliariaDeposito1,
                matriculaInmobiliariaDeposito2, matriculaInmobiliariaDeposito3,
                matriculaInmobiliariaDeposito4, matriculaInmobiliariaDeposito5,
                numeroDeposito1, numeroDeposito2, numeroDeposito3,
                numeroDeposito4, numeroDeposito5, numeroDepositoExclusivo1,
                numeroDepositoExclusivo2, numeroDepositoExclusivo3,
                numeroDepositoExclusivo4, numeroDepositoExclusivo5,
                numeroDePisos, numeroSotanos, vetustez, estadoDeConservacion,
                licenciaConstruccion, tipoLicencia, numeroLicencia, estadoDeConstruccion,
                estadoDeObra, anoDeConstruccion, porcentajeAvance, remodelado,
                fechaRemodelacion, estructura, reparados, fachada, tipoFachada,
                estructuraReforzada, danosPrevios, materialConstructor,
                detalleMaterial, irregularidadPlanta, irregularidadAltura,
                cubierta, pisosBodega, tipologiaViviendaUnicaFamiliar, sala, deposito, comedor, estudio,
                jardin, banoSocial, estarHabitacion, habitaciones,
                depositosPrivados, depositosExclusivos, numeroTotalDepositos,
                banoPrivado, cocina, cuartoServicio, oficina, banoServicio,
                patioInterior, terraza, bodega, zonaDeRopas, balcon, closet,
                local, zonaVerdePrivada, iluminacion, ventilacion,
                numeroTotalDeGarajes, totalCuposParquedaro,
                estadoAcabadosPisos, estadoAcabadosMuros, estadoAcabadosTechos,
                estadoAcabadosMadera, estadoAcabadosMetal, estadoAcabadosBanos,
                estadoAcabadosCocina, calidadAcabadosPisos,
                calidadAcabadosMuros, calidadAcabadosTechos,
                calidadAcabadosMadera, calidadAcabadosMetal,
                calidadAcabadosBanos, calidadAcabadosCocina,
                sometidoAPropiedadHorizontal, conjuntoCerrado,
                ubicacionDelInmueble, numeroPiso, numeroDeEdificios, unidadesPorPiso,
                totalUnidades, coeficiente, porteria, citofono, bicicletero,
                piscina, tanqueDeAgua, clubHouse, garajeVisitantes,
                juegosNinos, canchaMultiple, bombaEyectora,
                aireAcondicionadoCentral, canchaSquash, zonasVerdesComunales,
                gimnasio, golfito, salonComunal, shutBasuras,
                equipoDePresionConstante, plantaElectrica, ascensor,
                numeroDeAscensores, otros,
                actualidadEdificadora, entregaDeDocumentos,
                comportamientoOfertaDemanda, tiempoEsperadoDeComercializacion,
                porcentajeTerreno, valorProporcionalTerreno,
                valorProporcionalConstruccion, valorIntegralTerreno,
                valorIntegralConstruccion, observaciones, direccionAnexos,
                otrasDirecciones, areaConstruida, areaPrivada,
                observacionesGarajes);
        return clone;
    }

    @Override
    public String toString() {
        return "FormatoAvaluoHipotecarioDTO [valorComercial=" + valorComercial
            + ", valorCortesia=" + valorCortesia
            + ", metodoDeValuacion=" + metodoDeValuacion
            + ", justificacionDeMetodologia=" + justificacionDeMetodologia
            + ", acueductoEnElSector=" + acueductoEnElSector
            + ", acueductoEnElPredio=" + acueductoEnElPredio
            + ", alcantarilladoEnElSector=" + alcantarilladoEnElSector
            + ", alcantarilladoEnElPredio=" + alcantarilladoEnElPredio
            + ", energiaEnElSector=" + energiaEnElSector
            + ", energiaEnElPredio=" + energiaEnElPredio
            + ", gasEnElSector=" + gasEnElSector + ", gasEnElPredio="
            + gasEnElPredio + ", telefonoEnElSector=" + telefonoEnElSector
            + ", telefonoEnElPredio=" + telefonoEnElPredio + ", estrato="
            + estrato + ", barrioLegal=" + barrioLegal
            + ", usoPredominanteInmueble=" + usoPredominanteInmueble
            + ", topografiaSector=" + topografiaSector + ", transporte="
            + transporte + ", perspectivasDeValorizacion="
            + perspectivasDeValorizacion + ", condicionesDeSalubridad="
            + condicionesDeSalubridad + ", impactoAmbientalAire="
            + impactoAmbientalAire + ", impactoAmbientalBasura="
            + impactoAmbientalBasura + ", impactoAmbientalInseguridad="
            + impactoAmbientalInseguridad + ", impactoAmbientalRuido="
            + impactoAmbientalRuido + ", impactoAmbientalAguasServidas="
            + impactoAmbientalAguasServidas + ", impactoAmbientalOtro="
            + impactoAmbientalOtro + ", explicacionOtro=" + explicacionOtro
            + ", estadoDeLasVias=" + estadoDeLasVias
            + ", viasPavimentadas=" + viasPavimentadas
            + ", andenesEnLasVias=" + andenesEnLasVias
            + ", sardinelesEnLasVias=" + sardinelesEnLasVias + ", parques="
            + parques + ", paradero=" + paradero + ", alumbrado="
            + alumbrado + ", zonasVerdesPublicas=" + zonasVerdesPublicas
            + ", arborizacion=" + arborizacion + ", alamedas=" + alamedas
            + ", cicloRutas=" + cicloRutas + ", claseInmueble="
            + claseInmueble + ", descripcionOtrosTipoInmueble="
            + descripcionOtrosTipoInmueble + ", usoActual=" + usoActual
            + ", descripcionOtrosUsoInmueble="
            + descripcionOtrosUsoInmueble + ", tipoDeVivienda="
            + tipoDeVivienda + ", descripcionOtrosTipoVivienda="
            + descripcionOtrosTipoVivienda + ", chip=" + chip
            + ", matriculaInmobiliariaPrincipal2="
            + matriculaInmobiliariaPrincipal2 + ", numeroDeEscritura="
            + numeroDeEscritura + ", numeroNotariaDeEscritura="
            + numeroNotariaDeEscritura + ", fechaDeEscritura="
            + fechaDeEscritura + ", departamentoNotaria="
            + departamentoNotaria + ", ciudadNotaria=" + ciudadNotaria
            + ", matriculaInmobiliariaDeposito1="
            + matriculaInmobiliariaDeposito1
            + ", matriculaInmobiliariaDeposito2="
            + matriculaInmobiliariaDeposito2
            + ", matriculaInmobiliariaDeposito3="
            + matriculaInmobiliariaDeposito3
            + ", matriculaInmobiliariaDeposito4="
            + matriculaInmobiliariaDeposito4
            + ", matriculaInmobiliariaDeposito5="
            + matriculaInmobiliariaDeposito5 + ", numeroDeposito1="
            + numeroDeposito1 + ", numeroDeposito2=" + numeroDeposito2
            + ", numeroDeposito3=" + numeroDeposito3 + ", numeroDeposito4="
            + numeroDeposito4 + ", numeroDeposito5=" + numeroDeposito5
            + ", numeroDePisos=" + numeroDePisos + ", numeroSotanos="
            + numeroSotanos + ", vetustez=" + vetustez
            + ", estadoDeConservacion=" + estadoDeConservacion
            + ", tipoLicencia=" + tipoLicencia + ", numeroLicencia="
            + numeroLicencia + ", estadoDeConstruccion="
            + estadoDeConstruccion + ", estadoDeObra=" + estadoDeObra
            + ", anoDeConstruccion=" + anoDeConstruccion
            + ", porcentajeAvance=" + porcentajeAvance + ", remodelado="
            + remodelado + ", estructura=" + estructura + ", reparados="
            + reparados + ", fachada=" + fachada + ", tipoFachada="
            + tipoFachada + ", estructuraReforzada=" + estructuraReforzada
            + ", danosPrevios=" + danosPrevios + ", materialConstructor="
            + materialConstructor + ", detalleMaterial=" + detalleMaterial
            + ", irregularidadPlanta=" + irregularidadPlanta
            + ", irregularidadAltura=" + irregularidadAltura
            + ", cubierta=" + cubierta + ", pisosBodega=" + pisosBodega
            + ", sala=" + sala + ", deposito=" + deposito + ", comedor="
            + comedor + ", estudio=" + estudio + ", jardin=" + jardin
            + ", banoSocial=" + banoSocial + ", estarHabitacion="
            + estarHabitacion + ", habitaciones=" + habitaciones
            + ", depositosPrivados=" + depositosPrivados
            + ", depositosExclusivos=" + depositosExclusivos
            + ", numeroTotalDepositos=" + numeroTotalDepositos
            + ", banoPrivado=" + banoPrivado + ", cocina=" + cocina
            + ", cuartoServicio=" + cuartoServicio + ", oficina=" + oficina
            + ", banoServicio=" + banoServicio + ", patioInterior="
            + patioInterior + ", terraza=" + terraza + ", bodega=" + bodega
            + ", zonaDeRopas=" + zonaDeRopas + ", balcon=" + balcon
            + ", closet=" + closet + ", local=" + local
            + ", zonaVerdePrivada=" + zonaVerdePrivada + ", iluminacion="
            + iluminacion + ", ventilacion=" + ventilacion
            + ", numeroTotalDeGarajes="
            + numeroTotalDeGarajes + ", totalCuposParquedaro="
            + totalCuposParquedaro + ", estadoAcabadosPisos="
            + estadoAcabadosPisos + ", estadoAcabadosMuros="
            + estadoAcabadosMuros + ", estadoAcabadosTechos="
            + estadoAcabadosTechos + ", estadoAcabadosMadera="
            + estadoAcabadosMadera + ", estadoAcabadosMetal="
            + estadoAcabadosMetal + ", estadoAcabadosBanos="
            + estadoAcabadosBanos + ", estadoAcabadosCocina="
            + estadoAcabadosCocina + ", calidadAcabadosPisos="
            + calidadAcabadosPisos + ", calidadAcabadosMuros="
            + calidadAcabadosMuros + ", calidadAcabadosTechos="
            + calidadAcabadosTechos + ", calidadAcabadosMadera="
            + calidadAcabadosMadera + ", calidadAcabadosMetal="
            + calidadAcabadosMetal + ", calidadAcabadosBanos="
            + calidadAcabadosBanos + ", calidadAcabadosCocina="
            + calidadAcabadosCocina + ", sometidoAPropiedadHorizontal="
            + sometidoAPropiedadHorizontal + ", conjuntoCerrado="
            + conjuntoCerrado + ", ubicacionDelInmueble="
            + ubicacionDelInmueble + ", numeroPiso="+ numeroPiso + ", numeroDeEdificios="
            + numeroDeEdificios + ", unidadesPorPiso=" + unidadesPorPiso
            + ", totalUnidades=" + totalUnidades + ", coeficiente="
            + coeficiente + ", porteria=" + porteria + ", citofono="
            + citofono + ", bicicletero=" + bicicletero + ", piscina="
            + piscina + ", tanqueDeAgua=" + tanqueDeAgua + ", clubHouse="
            + clubHouse + ", garajeVisitantes=" + garajeVisitantes
            + ", juegosNinos=" + juegosNinos + ", canchaMultiple="
            + canchaMultiple + ", bombaEyectora=" + bombaEyectora
            + ", aireAcondicionadoCentral=" + aireAcondicionadoCentral
            + ", canchaSquash=" + canchaSquash + ", zonasVerdesComunales="
            + zonasVerdesComunales + ", gimnasio=" + gimnasio
            + ", golfito=" + golfito + ", salonComunal=" + salonComunal
            + ", shutBasuras=" + shutBasuras
            + ", equipoDePresionConstante=" + equipoDePresionConstante
            + ", plantaElectrica=" + plantaElectrica + ", ascensor="
            + ascensor + ", numeroDeAscensores=" + numeroDeAscensores
            + ", otros=" + otros + ", actualidadEdificadora="
            + actualidadEdificadora + ", entregaDeDocumentos="
            + entregaDeDocumentos + ", comportamientoOfertaDemanda="
            + comportamientoOfertaDemanda
            + ", tiempoEsperadoDeComercializacion="
            + tiempoEsperadoDeComercializacion + ", observaciones="
            + observaciones + ", direccionAnexos=" + direccionAnexos
            + ", otrasDirecciones=" + otrasDirecciones + "]";
    }

    public String getObservacionesGarajes() {
        return observacionesGarajes;
    }

    public void setObservacionesGarajes(String observacionesGarajes) {
        this.observacionesGarajes = observacionesGarajes;
    }    

    public String getOcupante() {
		return ocupante;
	}

	public void setOcupante(String ocupante) {
		this.ocupante = ocupante;
	}

	public String getCondicionPh() {
		return condicionPh;
	}

	public void setCondicionPh(String condicionPh) {
		this.condicionPh = condicionPh;
	}

	public Boolean getTeatrino() {
		return teatrino;
	}

	public void setTeatrino(Boolean teatrino) {
		this.teatrino = teatrino;
	}

	public Boolean getSauna() {
		return sauna;
	}

	public void setSauna(Boolean sauna) {
		this.sauna = sauna;
	}

	public Boolean getCalefaccion() {
		return calefaccion;
	}

	public void setCalefaccion(Boolean calefaccion) {
		this.calefaccion = calefaccion;
	}

	public Boolean getTerrazaComunal() {
		return terrazaComunal;
	}

	public void setTerrazaComunal(Boolean terrazaComunal) {
		this.terrazaComunal = terrazaComunal;
	}

	public Boolean getTurco() {
		return turco;
	}

	public void setTurco(Boolean turco) {
		this.turco = turco;
	}

	public Boolean getBbq() {
		return bbq;
	}

	public void setBbq(Boolean bbq) {
		this.bbq = bbq;
	}

	public Boolean getGuarderia() {
		return guarderia;
	}

	public void setGuarderia(Boolean guarderia) {
		this.guarderia = guarderia;
	}

	public Boolean getJardinInfantil() {
		return jardinInfantil;
	}

	public void setJardinInfantil(Boolean jardinInfantil) {
		this.jardinInfantil = jardinInfantil;
	}

	public String getVigilanciaPrivada() {
		return vigilanciaPrivada;
	}

	public void setVigilanciaPrivada(String vigilanciaPrivada) {
		this.vigilanciaPrivada = vigilanciaPrivada;
	}

	public Boolean getAdministracion() {
		return administracion;
	}

	public void setAdministracion(Boolean administracion) {
		this.administracion = administracion;
	}

	public Boolean getEstadoVigilancia() {
		return EstadoVigilancia;
	}

	public void setEstadoVigilancia(Boolean estadoVigilancia) {
		EstadoVigilancia = estadoVigilancia;
	}

	public Integer getValorAdministracion() {
		return valorAdministracion;
	}

	public void setValorAdministracion(Integer valorAdministracion) {
		this.valorAdministracion = valorAdministracion;
	}

	public String getDecretoAcuerdo() {
		return decretoAcuerdo;
	}

	public void setDecretoAcuerdo(String decretoAcuerdo) {
		this.decretoAcuerdo = decretoAcuerdo;
	}

	public String getObservacionDecretoAcuerdo() {
		return observacionDecretoAcuerdo;
	}

	public void setObservacionDecretoAcuerdo(String observacionDecretoAcuerdo) {
		this.observacionDecretoAcuerdo = observacionDecretoAcuerdo;
	}

	public String getAlturaPermitida() {
		return alturaPermitida;
	}

	public void setAlturaPermitida(String alturaPermitida) {
		this.alturaPermitida = alturaPermitida;
	}

	public String getObservacionAlturaPermitida() {
		return observacionAlturaPermitida;
	}

	public void setObservacionAlturaPermitida(String observacionAlturaPermitida) {
		this.observacionAlturaPermitida = observacionAlturaPermitida;
	}

	public String getUsoPrincipal() {
		return usoPrincipal;
	}

	public void setUsoPrincipal(String usoPrincipal) {
		this.usoPrincipal = usoPrincipal;
	}

	public String getObservacionUsoPrincipal() {
		return observacionUsoPrincipal;
	}

	public void setObservacionUsoPrincipal(String observacionUsoPrincipal) {
		this.observacionUsoPrincipal = observacionUsoPrincipal;
	}

	public String getAislamientoPosterior() {
		return aislamientoPosterior;
	}

	public void setAislamientoPosterior(String aislamientoPosterior) {
		this.aislamientoPosterior = aislamientoPosterior;
	}

	public String getObservacionAislamientoPosterior() {
		return observacionAislamientoPosterior;
	}

	public void setObservacionAislamientoPosterior(String observacionAislamientoPosterior) {
		this.observacionAislamientoPosterior = observacionAislamientoPosterior;
	}

	public String getAislamientoLateral() {
		return aislamientoLateral;
	}

	public void setAislamientoLateral(String aislamientoLateral) {
		this.aislamientoLateral = aislamientoLateral;
	}

	public String getObservacionAislamientoLateral() {
		return observacionAislamientoLateral;
	}

	public void setObservacionAislamientoLateral(String observacionAislamientoLateral) {
		this.observacionAislamientoLateral = observacionAislamientoLateral;
	}

	public String getAnteJardin() {
		return anteJardin;
	}

	public void setAnteJardin(String anteJardin) {
		this.anteJardin = anteJardin;
	}

	public String getObservacionAnteJardin() {
		return observacionAnteJardin;
	}

	public void setObservacionAnteJardin(String observacionAnteJardin) {
		this.observacionAnteJardin = observacionAnteJardin;
	}

	public String getIndiceOcupacion() {
		return indiceOcupacion;
	}

	public void setIndiceOcupacion(String indiceOcupacion) {
		this.indiceOcupacion = indiceOcupacion;
	}

	public String getObservacionIndiceOcupacion() {
		return observacionIndiceOcupacion;
	}

	public void setObservacionIndiceOcupacion(String observacionIndiceOcupacion) {
		this.observacionIndiceOcupacion = observacionIndiceOcupacion;
	}

	public String getIndiceConstruccion() {
		return indiceConstruccion;
	}

	public void setIndiceConstruccion(String indiceConstruccion) {
		this.indiceConstruccion = indiceConstruccion;
	}

	public String getObservacionIndiceConstruccion() {
		return observacionIndiceConstruccion;
	}

	public void setObservacionIndiceConstruccion(String observacionIndiceConstruccion) {
		this.observacionIndiceConstruccion = observacionIndiceConstruccion;
	}

	public String getPredioSubdivididoFisicamente() {
		return predioSubdivididoFisicamente;
	}

	public void setPredioSubdivididoFisicamente(String predioSubdivididoFisicamente) {
		this.predioSubdivididoFisicamente = predioSubdivididoFisicamente;
	}

	public String getObservacionPredioSubdivididoFisicamente() {
		return observacionPredioSubdivididoFisicamente;
	}

	public void setObservacionPredioSubdivididoFisicamente(String observacionPredioSubdivididoFisicamente) {
		this.observacionPredioSubdivididoFisicamente = observacionPredioSubdivididoFisicamente;
	}

	public String getEstadoConservacionAlcantarrillado() {
		return estadoConservacionAlcantarrillado;
	}

	public void setEstadoConservacionAlcantarrillado(String estadoConservacionAlcantarrillado) {
		this.estadoConservacionAlcantarrillado = estadoConservacionAlcantarrillado;
	}

	public String getEstadoConservacionAcueducto() {
		return estadoConservacionAcueducto;
	}

	public void setEstadoConservacionAcueducto(String estadoConservacionAcueducto) {
		this.estadoConservacionAcueducto = estadoConservacionAcueducto;
	}

	public String getEstadoConservacionGas() {
		return estadoConservacionGas;
	}

	public void setEstadoConservacionGas(String estadoConservacionGas) {
		this.estadoConservacionGas = estadoConservacionGas;
	}

	public String getEstadoConservacionEnergia() {
		return estadoConservacionEnergia;
	}

	public void setEstadoConservacionEnergia(String estadoConservacionEnergia) {
		this.estadoConservacionEnergia = estadoConservacionEnergia;
	}

	public String getEstadoConservacionTelefono() {
		return estadoConservacionTelefono;
	}

	public void setEstadoConservacionTelefono(String estadoConservacionTelefono) {
		this.estadoConservacionTelefono = estadoConservacionTelefono;
	}

	public String getEstadoConservacionViasPavimentadas() {
		return estadoConservacionViasPavimentadas;
	}

	public void setEstadoConservacionViasPavimentadas(String estadoConservacionViasPavimentadas) {
		this.estadoConservacionViasPavimentadas = estadoConservacionViasPavimentadas;
	}

	public String getEstadoConservacionSardinelesEnLasVias() {
		return estadoConservacionSardinelesEnLasVias;
	}

	public void setEstadoConservacionSardinelesEnLasVias(String estadoConservacionSardinelesEnLasVias) {
		this.estadoConservacionSardinelesEnLasVias = estadoConservacionSardinelesEnLasVias;
	}

	public String getEstadoConservacionAndenesEnLasVias() {
		return estadoConservacionAndenesEnLasVias;
	}

	public void setEstadoConservacionAndenesEnLasVias(String estadoConservacionAndenesEnLasVias) {
		this.estadoConservacionAndenesEnLasVias = estadoConservacionAndenesEnLasVias;
	}

	public String getDemandaInteres() {
		return demandaInteres;
	}

	public void setDemandaInteres(String demandaInteres) {
		this.demandaInteres = demandaInteres;
	}

	public String getNivelEquipamientoComercial() {
		return nivelEquipamientoComercial;
	}

	public void setNivelEquipamientoComercial(String nivelEquipamientoComercial) {
		this.nivelEquipamientoComercial = nivelEquipamientoComercial;
	}

	public String getNivelEquipamientoEscolar() {
		return nivelEquipamientoEscolar;
	}

	public void setNivelEquipamientoEscolar(String nivelEquipamientoEscolar) {
		this.nivelEquipamientoEscolar = nivelEquipamientoEscolar;
	}

	public String getNivelEquipamientoAsistencial() {
		return nivelEquipamientoAsistencial;
	}

	public void setNivelEquipamientoAsistencial(String nivelEquipamientoAsistencial) {
		this.nivelEquipamientoAsistencial = nivelEquipamientoAsistencial;
	}

	public String getNivelEquipamientoEstacionamiento() {
		return nivelEquipamientoEstacionamiento;
	}

	public void setNivelEquipamientoEstacionamiento(String nivelEquipamientoEstacionamiento) {
		this.nivelEquipamientoEstacionamiento = nivelEquipamientoEstacionamiento;
	}

	public String getNivelEquipamientoAreasVerdes() {
		return nivelEquipamientoAreasVerdes;
	}

	public void setNivelEquipamientoAreasVerdes(String nivelEquipamientoAreasVerdes) {
		this.nivelEquipamientoAreasVerdes = nivelEquipamientoAreasVerdes;
	}

	public String getNivelEquipamientoZonasRecreativas() {
		return nivelEquipamientoZonasRecreativas;
	}

	public void setNivelEquipamientoZonasRecreativas(String nivelEquipamientoZonasRecreativas) {
		this.nivelEquipamientoZonasRecreativas = nivelEquipamientoZonasRecreativas;
	}

	public String getDistanciaAproximadaComercial() {
		return distanciaAproximadaComercial;
	}

	public void setDistanciaAproximadaComercial(String distanciaAproximadaComercial) {
		this.distanciaAproximadaComercial = distanciaAproximadaComercial;
	}

	public String getDistanciaAproximadaEscolar() {
		return distanciaAproximadaEscolar;
	}

	public void setDistanciaAproximadaEscolar(String distanciaAproximadaEscolar) {
		this.distanciaAproximadaEscolar = distanciaAproximadaEscolar;
	}

	public String getDistanciaAproximadaAsistencial() {
		return distanciaAproximadaAsistencial;
	}

	public void setDistanciaAproximadaAsistencial(String distanciaAproximadaAsistencial) {
		this.distanciaAproximadaAsistencial = distanciaAproximadaAsistencial;
	}

	public String getDistanciaAproximadaEstacionamiento() {
		return distanciaAproximadaEstacionamiento;
	}

	public void setDistanciaAproximadaEstacionamiento(String distanciaAproximadaEstacionamiento) {
		this.distanciaAproximadaEstacionamiento = distanciaAproximadaEstacionamiento;
	}

	public String getDistanciaAproximadaAreasVerdes() {
		return distanciaAproximadaAreasVerdes;
	}

	public void setDistanciaAproximadaAreasVerdes(String distanciaAproximadaAreasVerdes) {
		this.distanciaAproximadaAreasVerdes = distanciaAproximadaAreasVerdes;
	}

	public String getDistanciaAproximadaZonasRecreativas() {
		return distanciaAproximadaZonasRecreativas;
	}

	public void setDistanciaAproximadaZonasRecreativas(String distanciaAproximadaZonasRecreativas) {
		this.distanciaAproximadaZonasRecreativas = distanciaAproximadaZonasRecreativas;
	}

	public String getDescripcionGeneralSector() {
		return descripcionGeneralSector;
	}

	public void setDescripcionGeneralSector(String descripcionGeneralSector) {
		this.descripcionGeneralSector = descripcionGeneralSector;
	}

	public String getObservacionesGeneralesConstruccion() {
		return observacionesGeneralesConstruccion;
	}

	public void setObservacionesGeneralesConstruccion(String observacionesGeneralesConstruccion) {
		this.observacionesGeneralesConstruccion = observacionesGeneralesConstruccion;
	}

	public String getObservacionesGeneralesInmueble() {
		return observacionesGeneralesInmueble;
	}

	public void setObservacionesGeneralesInmueble(String observacionesGeneralesInmueble) {
		this.observacionesGeneralesInmueble = observacionesGeneralesInmueble;
	}

	public String getObservacionesEstructura() {
		return observacionesEstructura;
	}

	public void setObservacionesEstructura(String observacionesEstructura) {
		this.observacionesEstructura = observacionesEstructura;
	}

	public String getObservacionesDependencias() {
		return observacionesDependencias;
	}

	public void setObservacionesDependencias(String observacionesDependencias) {
		this.observacionesDependencias = observacionesDependencias;
	}

	public String getObservacionesAcabados() {
		return observacionesAcabados;
	}

	public void setObservacionesAcabados(String observacionesAcabados) {
		this.observacionesAcabados = observacionesAcabados;
	}

	public String getDescripcionGeneral() {
		return descripcionGeneral;
	}

	public void setDescripcionGeneral(String descripcionGeneral) {
		this.descripcionGeneral = descripcionGeneral;
	}

	public String getAreaActividad() {
		return areaActividad;
	}

	public void setAreaActividad(String areaActividad) {
		this.areaActividad = areaActividad;
	}

	public String getUsoPrincipalPh() {
		return usoPrincipalPh;
	}

	public void setUsoPrincipalPh(String usoPrincipalPh) {
		this.usoPrincipalPh = usoPrincipalPh;
	}

	public Boolean getRph() {
		return rph;
	}

	public void setRph(Boolean rph) {
		this.rph = rph;
	}


	public Boolean getMostrarPredioSubdivididoFisicamente() {
		return mostrarPredioSubdivididoFisicamente;
	}

	public void setMostrarPredioSubdivididoFisicamente(Boolean mostrarPredioSubdivididoFisicamente) {
		this.mostrarPredioSubdivididoFisicamente = mostrarPredioSubdivididoFisicamente;
	}

	public Integer getUnidades() {
		return unidades;
	}

	public void setUnidades(Integer unidades) {
		this.unidades = unidades;
	}

	public Integer getContadoresAgua() {
		return contadoresAgua;
	}

	public void setContadoresAgua(Integer contadoresAgua) {
		this.contadoresAgua = contadoresAgua;
	}

	public Integer getContadoresLuz() {
		return contadoresLuz;
	}

	public void setContadoresLuz(Integer contadoresLuz) {
		this.contadoresLuz = contadoresLuz;
	}
	
	public Integer getAccesos() {
		return accesos;
	}

	public void setAccesos(Integer accesos) {
		this.accesos = accesos;
	}

	public Boolean getTfc1() {
		return tfc1;
	}

	public void setTfc1(Boolean tfc1) {
		this.tfc1 = tfc1;
	}

	public Boolean getTfc2() {
		return tfc2;
	}

	public void setTfc2(Boolean tfc2) {
		this.tfc2 = tfc2;
	}

	public Boolean getTfc3() {
		return tfc3;
	}

	public void setTfc3(Boolean tfc3) {
		this.tfc3 = tfc3;
	}

	public Boolean getTfc4() {
		return tfc4;
	}

	public void setTfc4(Boolean tfc4) {
		this.tfc4 = tfc4;
	}

	public Boolean getTfc5() {
		return tfc5;
	}

	public void setTfc5(Boolean tfc5) {
		this.tfc5 = tfc5;
	}

	public Boolean getTfc6() {
		return tfc6;
	}

	public void setTfc6(Boolean tfc6) {
		this.tfc6 = tfc6;
	}

	public Boolean getTfc7() {
		return tfc7;
	}

	public void setTfc7(Boolean tfc7) {
		this.tfc7 = tfc7;
	}

	public Boolean getTfc8() {
		return tfc8;
	}

	public void setTfc8(Boolean tfc8) {
		this.tfc8 = tfc8;
	}

	public Boolean getTfc9() {
		return tfc9;
	}

	public void setTfc9(Boolean tfc9) {
		this.tfc9 = tfc9;
	}

	public Boolean getTfc10() {
		return tfc10;
	}

	public void setTfc10(Boolean tfc10) {
		this.tfc10 = tfc10;
	}

	/** Enumeracion empleada  para las listas que tienen las opciones
     *  	-Bueno,
     *  	-regular,
     *  	-malo
     * */
    public enum ListaEstado implements  ListaDesplegable {
		Seleccione(Constantes.SELECCCIONE,0),
		Bueno(Constantes.ESTADO_VIA_BUENO, 1),
		Regular(Constantes.ESTADO_VIA_REGULAR, 2),
		Malo(Constantes.ESTADO_VIA_MALO, 3);

		private final String value;
		private final int key;

		ListaEstado(String value, int key) {
			this.value = value;
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public int getKey() {
			return key;
		}

		public static ListaEstado fromKey(int key) {
			switch (key) {
			case 0: return Seleccione;
			case 1: return Bueno;
			case 2: return Regular;
			case 3: return Malo;
			default:
				return null;
			}
		}

		@Override
		public String toString() {
			switch (this) {
			case Seleccione: return Constantes.SELECCCIONE;
			case Bueno: return Constantes.ESTADO_VIA_BUENO;
			case Regular: return Constantes.ESTADO_VIA_REGULAR;
			case Malo: return Constantes.ESTADO_VIA_MALO;
			default:
				return "";
			}
		}
	}

    /**
     * Enumeracion empleada  para las listas de topografia de sector
     * */
    public enum ListaTopografiaSector implements  ListaDesplegable {
		Seleccione(Constantes.SELECCCIONE,0),
		Plano(Constantes.TIPOGRAFIA_SECTOR_PLANO, 1),
		Ligera(Constantes.TIPOGRAFIA_SECTOR_LIGERA, 2),
		Inclinado(Constantes.TIPOGRAFIA_SECTOR_INCLINADO, 3),
		Accidentada(Constantes.TIPOGRAFIA_SECTOR_ACCIDENTADA, 4);

		private final String value;
		private final int key;

		ListaTopografiaSector(String value, int key) {
			this.value = value;
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public int getKey() {
			return key;
		}

		public static ListaTopografiaSector fromKey(int key) {
			switch (key) {
			case 0: return Seleccione;
			case 1: return Plano;
			case 2: return Ligera;
			case 3: return Inclinado;
			case 4: return Accidentada;
			default:
				return null;
			}
		}

		@Override
		public String toString() {
			switch (this) {
			case Seleccione: return Constantes.SELECCCIONE;
			case Plano: return Constantes.TIPOGRAFIA_SECTOR_PLANO;
			case Ligera: return Constantes.TIPOGRAFIA_SECTOR_LIGERA;
			case Inclinado: return Constantes.TIPOGRAFIA_SECTOR_INCLINADO;
			case Accidentada: return Constantes.TIPOGRAFIA_SECTOR_ACCIDENTADA;
			default:
				return "";
			}
		}
	}

    /**
     * Enumeracion empleada  para las listas de uso predominante inmueble
     * */
    public enum ListaUsoPredominanteInmueble implements  ListaDesplegable {
		Seleccione(Constantes.SELECCCIONE,0),
		Vivienda(Constantes.USO_PREDOMINANTE_INMUEBLE_VIVIENDA, 1),
		Comercio(Constantes.USO_PREDOMINANTE_INMUEBLE_COMERCIO, 2),
		Bodega(Constantes.USO_PREDOMINANTE_INMUEBLE_BODEGA, 3),
		Oficina(Constantes.USO_PREDOMINANTE_INMUEBLE_OFICINA, 4),
		Multihabitacional(Constantes.USO_PREDOMINANTE_INMUEBLE_MULTIHABITACIONAL, 5),
		Otro(Constantes.USO_PREDOMINANTE_INMUEBLE_OTRO, 6),
		Educacion(Constantes.USO_PREDOMINANTE_INMUEBLE_EDUCACION, 80),
		Salud(Constantes.USO_PREDOMINANTE_INMUEBLE_SALUD, 81),
		Hotelero(Constantes.USO_PREDOMINANTE_INMUEBLE_HOTELERO, 82),
		Industrial(Constantes.USO_PREDOMINANTE_INMUEBLE_INDUSTRIAL, 83),
		Iglesia(Constantes.USO_PREDOMINANTE_INMUEBLE_IGLESIA, 84),
		Parqueadero(Constantes.USO_PREDOMINANTE_INMUEBLE_PARQUEADERO, 85),
		Auditorio(Constantes.USO_PREDOMINANTE_INMUEBLE_AUDITORIO, 86),
		InstalacionDeportiva(Constantes.USO_PREDOMINANTE_INMUEBLE_INSTALACION_DEPORTIVA, 87),
		Mixto(Constantes.USO_PREDOMINANTE_INMUEBLE_MIXTO, 100);


		private final String value;
		private final int key;

		ListaUsoPredominanteInmueble(String value, int key) {
			this.value = value;
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public int getKey() {
			return key;
		}

		public static ListaUsoPredominanteInmueble fromKey(int key) {
			switch (key) {
			case 0: return Seleccione;
			case 1: return Vivienda;
			case 2: return Comercio;
			case 3: return Bodega;
			case 5: return Multihabitacional;
			case 6: return Otro;
			case 80: return Educacion;
			case 81: return Salud;
			case 82: return Hotelero;
			case 83: return Industrial;
			case 84: return Iglesia;
			case 85: return Parqueadero;
			case 86: return Auditorio;
			case 87: return InstalacionDeportiva;
			case 100: return Mixto;

			default:
				return null;
			}
		}

		@Override
		public String toString() {
			switch (this) {
			case Seleccione: return Constantes.SELECCCIONE;
			case Vivienda: return Constantes.USO_PREDOMINANTE_INMUEBLE_VIVIENDA;
			case Comercio: return Constantes.USO_PREDOMINANTE_INMUEBLE_COMERCIO;
			case Bodega: return Constantes.USO_PREDOMINANTE_INMUEBLE_BODEGA;
			case Multihabitacional: return Constantes.USO_PREDOMINANTE_INMUEBLE_MULTIHABITACIONAL;
			case Otro: return Constantes.USO_PREDOMINANTE_INMUEBLE_OTRO;
			case Salud: return Constantes.USO_PREDOMINANTE_INMUEBLE_SALUD;
			case Hotelero: return Constantes.USO_PREDOMINANTE_INMUEBLE_HOTELERO;
			case Industrial: return Constantes.USO_PREDOMINANTE_INMUEBLE_INDUSTRIAL;
			case Iglesia: return Constantes.USO_PREDOMINANTE_INMUEBLE_IGLESIA;
			case Parqueadero: return Constantes.USO_PREDOMINANTE_INMUEBLE_PARQUEADERO;
			case Auditorio: return Constantes.USO_PREDOMINANTE_INMUEBLE_AUDITORIO;
			case InstalacionDeportiva: return Constantes.USO_PREDOMINANTE_INMUEBLE_INSTALACION_DEPORTIVA;
			case  Mixto: return Constantes.USO_PREDOMINANTE_INMUEBLE_MIXTO;
			default:
				return "";
			}
		}
	}


    /**
     * Enumeracion empleada  para las listas de uso clase  inmueble
     * */
    public enum ListaClaseInmueble implements  ListaDesplegable {
		Seleccione(Constantes.SELECCCIONE,0),
    	Unifamiliar(Constantes.CLASE_INUMUEBLE_UNIFAMILIAR, 1),
    	Bifamiliar(Constantes.CLASE_INUMUEBLE_BIFAMILIAR, 2),
    	Multifamiliar(Constantes.CLASE_INUMUEBLE_MULTIFAMILIAR, 3),
    	ViviendaSubdividida(Constantes.CLASE_INUMUEBLE_VIVIENDA_SUBDIVIDIDA, 4),
    	Industrial(Constantes.CLASE_INUMUEBLE_INDUSTRIAL, 5),
		Comercial(Constantes.CLASE_INUMUEBLE_COMERCIAL, 6),
		Oficinas(Constantes.CLASE_INUMUEBLE_OFICINAS,7),
		Otro(Constantes.CLASE_INUMUEBLE_OTRO, 8);

		private final String value;
		private final int key;

		 ListaClaseInmueble(String value, int key) {
			this.value = value;
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public int getKey() {
			return key;
		}

		public static  ListaClaseInmueble fromKey(int key) {
			switch (key) {
			case 0: return Seleccione;
			case 1: return Unifamiliar;
			case 2: return Bifamiliar;
			case 3: return Multifamiliar;
			case 4: return ViviendaSubdividida;
			case 5: return Industrial;
			case 6: return Comercial;
			case 7: return Oficinas;
			case 8: return Otro;

			default:
				return null;
			}
		}

		@Override
		public String toString() {
			switch (this) {
			case  Seleccione: return Constantes.SELECCCIONE;
			case  Unifamiliar: return Constantes.CLASE_INUMUEBLE_UNIFAMILIAR;
			case  Bifamiliar: return Constantes.CLASE_INUMUEBLE_BIFAMILIAR;
			case  Multifamiliar:  return Constantes.CLASE_INUMUEBLE_MULTIFAMILIAR;
			case  ViviendaSubdividida:return Constantes.CLASE_INUMUEBLE_VIVIENDA_SUBDIVIDIDA;
			case  Industrial: return Constantes.CLASE_INUMUEBLE_INDUSTRIAL;
			case  Comercial: return Constantes.CLASE_INUMUEBLE_COMERCIAL;
			case  Oficinas: return Constantes.CLASE_INUMUEBLE_OFICINAS;
			case  Otro: return Constantes.CLASE_INUMUEBLE_OTRO;
			default:
				return "";
			}
		}

	}
    /**
     * Enumeracion para las opciones de las listas de tipo de vivienda.
     * */
    public enum ListaTipoVivienda implements  ListaDesplegable {
		Seleccione(Constantes.SELECCCIONE,0),
		Vis(Constantes.TIPO_VIVIENDA_VIS, 1),
		NoVis(Constantes.TIPO_VIVIENDA_NO_VIS, 2),
		Vip(Constantes.TIPO_VIVIENDA_VIP, 3);


		private final String value;
		private final int key;

		ListaTipoVivienda(String value, int key) {
			this.value = value;
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public int getKey() {
			return key;
		}

		public static  ListaTipoVivienda fromKey(int key) {
			switch (key) {
			case 0: return Seleccione;
			case 1: return Vis;
			case 2: return NoVis;
			case 3: return Vip;
			default:
				return null;
			}
		}

		@Override
		public String toString() {
			switch (this) {
			case Seleccione: return Constantes.SELECCCIONE;
			case Vis: return Constantes.TIPO_VIVIENDA_VIS;
			case NoVis: return Constantes.TIPO_VIVIENDA_NO_VIS;
			case Vip: return Constantes.TIPO_VIVIENDA_VIP;
			default:
				return "";
			}
		}
	}

    /**
     * Enumeracion para las opciones de las listas de ubicacion del inmueble
     * */
    public enum ListaUbicacionInmueble implements  ListaDesplegable {
		Seleccione(Constantes.SELECCCIONE,0),
    	Interior(Constantes.UBICACION_INMUEBLE_INTERIOR, 1),
    	Exterior(Constantes.UBICACION_INMUEBLE_EXTERIOR, 2);

		private final String value;
		private final int key;

		 ListaUbicacionInmueble (String value, int key) {
			this.value = value;
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public int getKey() {
			return key;
		}

		public static   ListaUbicacionInmueble  fromKey(int key) {
			switch (key) {
			case 0: return Seleccione;
			case 1: return Interior;
			case 2: return Exterior;
			default:
				return null;
			}
		}

		@Override
		public String toString() {
			switch (this) {
			case Seleccione: return Constantes.SELECCCIONE;
			case Interior: return Constantes.UBICACION_INMUEBLE_INTERIOR;
			case Exterior: return Constantes.UBICACION_INMUEBLE_EXTERIOR;
			default:
				return "";
			}
		}
	}

    /**
     * Enumeracion para las opciones de las listas de ubicacion del inmueble
     * */
    public enum ListaEstadoConstruccion implements  ListaDesplegable {
		Seleccione(Constantes.SELECCCIONE,0),
    	Nueva(Constantes.ESTADO_CONSTRUCCION_NUEVO, 1),
    	Usada(Constantes.ESTADO_CONSTRUCCION_USADO, 2);

		private final String value;
		private final int key;

		ListaEstadoConstruccion (String value, int key) {
			this.value = value;
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public int getKey() {
			return key;
		}

		public static ListaEstadoConstruccion fromKey(int key) {
			switch (key) {
			case 0: return Seleccione;
			case 1: return Nueva;
			case 2: return Usada;
			default:
				return null;
			}
		}

		@Override
		public String toString() {
			switch (this) {
			case Seleccione: return Constantes.SELECCCIONE;
			case Nueva: return Constantes.ESTADO_CONSTRUCCION_NUEVO;
			case Usada: return Constantes.ESTADO_CONSTRUCCION_USADO;
			default:
				return "";
			}
		}
	}

    /**
     * Enumeracion para las opciones de las listas de estados de conservacion
     * */
    public enum ListaEstadoConservacion implements  ListaDesplegable {
		Seleccione(Constantes.SELECCCIONE,0),
    	Optimo(Constantes.ESTADO_CONSERVACION_OPTIMO, 1),
    	Bueno(Constantes.ESTADO_CONSERVACION_BUENO, 2),
    	Regular(Constantes.ESTADO_CONSERVACION_REGULAR, 3),
    	Malo(Constantes.ESTADO_CONSERVACION_MALO, 4),
    	Demolicion(Constantes.ESTADO_CONSERVACION_DEMOLICION, 5);

		private final String value;
		private final int key;

		ListaEstadoConservacion (String value, int key) {
			this.value = value;
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public int getKey() {
			return key;
		}

		public static ListaEstadoConservacion fromKey(int key) {
			switch (key) {
			case 0: return Seleccione;
			case 1: return Optimo;
			case 2: return Bueno;
			case 3: return Regular;
			case 4: return Malo;
			case 5: return Demolicion;

			default:
				return null;
			}
		}

		@Override
		public String toString() {
			switch (this) {
			case Seleccione: return Constantes.SELECCCIONE;
			case Optimo: return Constantes.ESTADO_CONSERVACION_OPTIMO;
			case Bueno: return Constantes.ESTADO_CONSERVACION_BUENO;
			case Malo: return Constantes.ESTADO_CONSERVACION_MALO;
			case Regular: return Constantes.ESTADO_CONSERVACION_REGULAR;
			case Demolicion: return Constantes.ESTADO_CONSERVACION_DEMOLICION;
			default:
				return "";
			}
		}
	}
    /**
     * Enumeracion para las opciones de las listas de tipos de licencia
     * */
    public enum ListaTipoLicencia implements  ListaDesplegable {
		Seleccione(Constantes.SELECCCIONE,0),
    	Vis(Constantes.TIPO_LICENCIA_VIS, 1),
    	NoVis(Constantes.TIPO_LICENCIA_NO_VIS, 2),
    	Vip(Constantes.TIPO_LICENCIA_VIP, 3);

		private final String value;
		private final int key;

		ListaTipoLicencia (String value, int key) {
			this.value = value;
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public int getKey() {
			return key;
		}

		public static ListaTipoLicencia fromKey(int key) {
			switch (key) {
			case 0: return Seleccione;
			case 1: return Vis;
			case 2: return NoVis;
			case 3: return Vip;

			default:
				return null;
			}
		}

		@Override
		public String toString() {
			switch (this) {
			case Seleccione: return Constantes.SELECCCIONE;
			case Vis: return Constantes.TIPO_LICENCIA_VIS;
			case NoVis: return Constantes.TIPO_LICENCIA_NO_VIS;
			case Vip: return Constantes.TIPO_LICENCIA_VIP;
			default:
				return "";
			}
		}
	}

    /**
     * Enumeracion para las opciones de las listas de tipos de licencia
     * */
    public enum ListaPisoBodega implements  ListaDesplegable {
		Seleccione(Constantes.SELECCCIONE,0),
    	Reforzado(Constantes.PISO_BODEGA_REFORZADO, 1),
    	NoReforzado(Constantes.PISO_BODEGA_NO_REFORZAD0, 2);

		private final String value;
		private final int key;

		ListaPisoBodega (String value, int key) {
			this.value = value;
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public int getKey() {
			return key;
		}

		public static ListaPisoBodega fromKey(int key) {
			switch (key) {
			case 0: return Seleccione;
			case 1: return Reforzado;
			case 2: return NoReforzado;

			default:
				return null;
			}
		}

		@Override
		public String toString() {
			switch (this) {
			case Seleccione: return Constantes.SELECCCIONE;
			case Reforzado: return Constantes.PISO_BODEGA_REFORZADO;
			case NoReforzado: return Constantes.PISO_BODEGA_NO_REFORZAD0;
			default:
				return "";
			}
		}
	}

    /**
     * Enumeracion para las opciones de las listas de estructuras
     * */
    public enum ListaEstructura implements  ListaDesplegable {
		Seleccione(Constantes.SELECCCIONE,0),
    	MuroCarga(Constantes.ESTRUCTURA_MURO_CARGA, 1),
    	MamposteriaEstructural(Constantes.ESTRUCTURA_MAMPOSTERIA_ESTRUCTURAL, 2),
    	Tradicional(Constantes.ESTRUCTURA_TRADICIONAL, 3),
    	Metalica(Constantes.ESTRUCTURA_METALICA, 4),
    	Industrializada(Constantes.ESTRUCTURA_INDSUSTRIALIZADA, 5),
    	Otros(Constantes.ESTRUCTURA_OTROS, 6);


		private final String value;
		private final int key;

		ListaEstructura (String value, int key) {
			this.value = value;
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public int getKey() {
			return key;
		}

		public static ListaEstructura fromKey(int key) {
			switch (key) {
			case 0: return Seleccione;
			case 1: return MuroCarga;
			case 2: return MamposteriaEstructural;
			case 3: return Tradicional;
			case 4: return Metalica;
			case 5: return Industrializada;
			case 6: return Otros;
			default:
				return null;
			}
		}

		@Override
		public String toString() {
			switch (this) {
			case Seleccione: return Constantes.SELECCCIONE;
			case MuroCarga: return Constantes.ESTRUCTURA_MURO_CARGA;
			case MamposteriaEstructural: return Constantes.ESTRUCTURA_MAMPOSTERIA_ESTRUCTURAL;
			case Tradicional: return Constantes.ESTRUCTURA_TRADICIONAL;
			case Metalica: return Constantes.ESTRUCTURA_METALICA;
			case Industrializada: return Constantes.ESTRUCTURA_INDSUSTRIALIZADA;
			case Otros: return Constantes.ESTRUCTURA_OTROS;
			default:
				return "";
			}
		}
	}
    /**
     * Enumeracion para las opciones de las  Reparados
     * */
    public enum ListaReparado implements  ListaDesplegable {
		Seleccione(Constantes.SELECCCIONE,0),
    	NoDisponible(Constantes.REPARADO_NO_DISPONIBLE, 1),
    	Reparados(Constantes.REPARADO_REPARADOS, 2),
    	NoReparados(Constantes.REPARADO_NO_REPARADOS, 3);

		private final String value;
		private final int key;

		ListaReparado (String value, int key) {
			this.value = value;
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public int getKey() {
			return key;
		}

		public static ListaReparado fromKey(int key) {
			switch (key) {
			case 0: return ListaReparado.Seleccione;
			case 1: return NoDisponible;
			case 2: return Reparados;
			case 3: return NoReparados;
			default:
				return null;
			}
		}

		@Override
		public String toString() {
			switch (this) {
			case Seleccione: return Constantes.SELECCCIONE;
			case NoDisponible: return Constantes.REPARADO_NO_DISPONIBLE;
			case Reparados: return Constantes.REPARADO_REPARADOS;
			case NoReparados: return Constantes.REPARADO_NO_REPARADOS;
			default:
				return "";
			}
		}
	}

    /**
     * Enumeracion para las opciones de las  Reparados
     * */
    public enum ListaCubierta implements  ListaDesplegable {
		Seleccione(Constantes.SELECCCIONE,0),
    	PlacaConcreto(Constantes.CUBIERTA_PLACA_CONCRETO, 1),
    	TejaPlastica(Constantes.CUBIERTA_TEJA_PLASTICA, 2),
    	TejaBarro(Constantes.CUBIERTA_TEJA_BARRO, 3),
    	TejaFibrocemento(Constantes.CUBIERTA_TEJA_FIBROCEMENTO, 4),
    	TejaMetalica(Constantes.CUBIERTA_TEJA_METALICA, 5),
    	Otros(Constantes.CUBIERTA_OTROS, 6);


		private final String value;
		private final int key;

		ListaCubierta (String value, int key) {
			this.value = value;
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public int getKey() {
			return key;
		}

		public static ListaCubierta fromKey(int key) {
			switch (key) {
			case 0: return ListaCubierta.Seleccione;
			case 1: return PlacaConcreto;
			case 2: return TejaPlastica;
			case 3: return TejaBarro;
			case 4: return TejaFibrocemento;
			case 5: return TejaMetalica;
			case 6: return Otros;
			default:
				return null;
			}
		}

		@Override
		public String toString() {
			switch (this) {
			case Seleccione: return Constantes.SELECCCIONE;
			case PlacaConcreto: return Constantes.CUBIERTA_PLACA_CONCRETO;
			case TejaPlastica: return Constantes.CUBIERTA_TEJA_PLASTICA;
			case TejaBarro: return Constantes.CUBIERTA_TEJA_BARRO;
			case TejaFibrocemento: return Constantes.CUBIERTA_TEJA_FIBROCEMENTO;
			case TejaMetalica: return Constantes.CUBIERTA_TEJA_METALICA;
			case Otros: return Constantes.CUBIERTA_OTROS;
			default:
				return "";
			}
		}
	}

    /**
     * Enumeracion para las opciones de Fachadas
     * */
    public enum ListaFachada implements  ListaDesplegable {
		Seleccione(Constantes.SELECCCIONE,0),
    	LadrilloVista(Constantes.FACHADA_LADRILLO_VISTA, 41),
    	PanetePintura(Constantes.FACHADA_PANETE_PINTURA, 42),
    	Graniplast(Constantes.FACHADA_GRANIPLAST, 43),
    	Flotantecemento(Constantes.FACHADA_FLOTANTE, 44),
    	ConcretoTexturado(Constantes.FACHADA_CONCRETO_TEXTURADO, 45),
    	Otros(Constantes.FACHADA_OTROS, 46);

		private final String value;
		private final int key;

		ListaFachada (String value, int key) {
			this.value = value;
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public int getKey() {
			return key;
		}

		public static ListaFachada fromKey(int key) {
			switch (key) {
			case 0: return Seleccione;
			case 41: return LadrilloVista;
			case 42: return PanetePintura;
			case 43: return Graniplast;
			case 44: return Flotantecemento;
			case 45: return ConcretoTexturado;
			case 46: return Otros;
			default:
				return null;
			}
		}

		@Override
		public String toString() {
			switch (this) {
			case Seleccione: return Constantes.SELECCCIONE;
			case LadrilloVista: return Constantes.FACHADA_LADRILLO_VISTA;
			case PanetePintura: return Constantes.FACHADA_PANETE_PINTURA;
			case Graniplast: return Constantes.FACHADA_GRANIPLAST;
			case Flotantecemento: return Constantes.FACHADA_FLOTANTE;
			case ConcretoTexturado: return Constantes.FACHADA_CONCRETO_TEXTURADO;
			case Otros: return Constantes.FACHADA_OTROS;
			default:
				return "";
			}
		}
	}

    /**
     * Enumeracion para las opciones de tipo de fachada
     * */
    public enum ListaTipoFachada implements  ListaDesplegable {
		Seleccione(Constantes.SELECCCIONE,0),
    	Sencilla(Constantes.TIPO_FACHADA_SENCILLA, 1),
    	Normal(Constantes.TIPO_FACHADA_NORMAL, 2),
    	Lujosa(Constantes.TIPO_FACHADA_LUJOSA,3);

		private final String value;
		private final int key;

		ListaTipoFachada (String value, int key) {
			this.value = value;
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public int getKey() {
			return key;
		}

		public static ListaTipoFachada fromKey(int key) {
			switch (key) {
			case 0: return ListaTipoFachada.Seleccione;
			case 1: return Sencilla;
			case 2: return Normal;
			case 3: return Lujosa;
			default:
				return null;
			}
		}

		@Override
		public String toString() {
			switch (this) {
			case Seleccione: return Constantes.SELECCCIONE;
			case Sencilla: return Constantes.TIPO_FACHADA_SENCILLA;
			case Normal: return Constantes.TIPO_FACHADA_NORMAL;
			case Lujosa: return Constantes.TIPO_FACHADA_LUJOSA;
			default:
				return "";
			}
		}
	}

    /**
     * Enumeracion para las opciones de Estructura reforzada
     * */
    public enum ListaEstructuraReforzada implements  ListaDesplegable {
		Seleccione(Constantes.SELECCCIONE,0),
    	TrabesColadosEnSitio(Constantes.ESTRUCTURA_REFORZADA_TRABES_COLADO_SITIO, 2),
    	TrabesPrefabricados(Constantes.ESTRUCTURA_REFORZADA_TRABES_PREFABRICADO, 3),
    	NoTieneTrabes(Constantes.ESTRUCTURA_REFORZADA_NO_TIENE_TRABES,4),
    	NoDisponible(Constantes.ESTRUCTURA_REFORZADA_NO_DISPONIBLE ,1);

		private final String value;
		private final int key;

		ListaEstructuraReforzada (String value, int key) {
			this.value = value;
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public int getKey() {
			return key;
		}

		public static ListaEstructuraReforzada fromKey(int key) {
			switch (key) {
			case 0: return Seleccione;
			case 1: return NoDisponible;
			case 2: return TrabesColadosEnSitio;
			case 3: return TrabesPrefabricados;
			case 4: return NoTieneTrabes;
			default:
				return null;
			}
		}

		@Override
		public String toString() {
			switch (this) {
			case Seleccione: return Constantes.SELECCCIONE;
			case TrabesColadosEnSitio: return Constantes.ESTRUCTURA_REFORZADA_TRABES_COLADO_SITIO;
			case TrabesPrefabricados: return Constantes.ESTRUCTURA_REFORZADA_TRABES_PREFABRICADO;
			case NoTieneTrabes: return Constantes.ESTRUCTURA_REFORZADA_NO_TIENE_TRABES;
			case NoDisponible: return Constantes.ESTRUCTURA_REFORZADA_NO_DISPONIBLE;
			default:
				return "";
			}
		}
	}
    /**
     * Enumeracion para las opciones de Danos previos
     * */
    public enum ListaDanoPrevio implements  ListaDesplegable {
		Seleccione(Constantes.SELECCCIONE,0),
    	NoDisponible(Constantes.DANOS_PREVIOS_NO_DISPONIBLE, 1),
    	ConDanosPrevios(Constantes.DANOS_PREVIOS_CON_DANOS_PREVIOS, 2),
    	SinDanosPrevios(Constantes.DANOS_PREVIOS_SIN_DANOS_PREVIOS,3);


		private final String value;
		private final int key;

		ListaDanoPrevio (String value, int key) {
			this.value = value;
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public int getKey() {
			return key;
		}

		public static ListaDanoPrevio fromKey(int key) {
			switch (key) {
			case 0: return Seleccione;
			case 1: return NoDisponible;
			case 2: return ConDanosPrevios;
			case 3: return SinDanosPrevios;
			default:
				return null;
			}
		}

		@Override
		public String toString() {
			switch (this) {
			case Seleccione: return Constantes.SELECCCIONE;
			case ConDanosPrevios: return Constantes.DANOS_PREVIOS_CON_DANOS_PREVIOS;
			case SinDanosPrevios: return Constantes.DANOS_PREVIOS_SIN_DANOS_PREVIOS;
			case NoDisponible: return Constantes.DANOS_PREVIOS_NO_DISPONIBLE;
			default:
				return "";
			}
		}
	}
    /**
     * Enumeracion para las opciones de Material constructor
     * */
    public enum ListaMaterialConstructor implements  ListaDesplegable {
		Seleccione(Constantes.SELECCCIONE,0),
    	Adobe(Constantes.MATERIAL_CONSTRUCTOR_ADOBE, 31),
    	Madera(Constantes.MATERIAL_CONSTRUCTOR_MADERA, 30),
    	MixtasUOtro(Constantes.MATERIAL_CONSTRUCTOR_MIXTAS, 32),
    	Mamposteria(Constantes.MATERIAL_CONSTRUCTOR_MAMPOSTERIA,28),
    	ConcretoReforzado(Constantes.MATERIAL_CONSTRUCTOR_CONCRETO_REFORZADO,27),
    	Acero(Constantes.MATERIAL_CONSTRUCTOR_ACERO,29),
    	NoExiste(Constantes.MATERIAL_CONSTRUCTOR_NO_EXISTE,51);

		private final String value;
		private final int key;

		ListaMaterialConstructor (String value, int key) {
			this.value = value;
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public int getKey() {
			return key;
		}

		public static ListaMaterialConstructor fromKey(int key) {
			switch (key) {
			case 0: return Seleccione;
			case 31: return Adobe;
			case 30: return Madera;
			case 32: return MixtasUOtro;
			case 28: return Mamposteria;
			case 27: return ConcretoReforzado;
			case 29: return Acero;
			case 51: return NoExiste;

			default:
				return null;
			}
		}

		@Override
		public String toString() {
			switch (this) {
			case Seleccione: return Constantes.SELECCCIONE;
			case Adobe: return Constantes.MATERIAL_CONSTRUCTOR_ADOBE;
			case Madera: return Constantes.MATERIAL_CONSTRUCTOR_MADERA;
			case MixtasUOtro: return Constantes.MATERIAL_CONSTRUCTOR_MIXTAS;
			case Mamposteria: return Constantes.MATERIAL_CONSTRUCTOR_MAMPOSTERIA;
			case ConcretoReforzado: return Constantes.MATERIAL_CONSTRUCTOR_CONCRETO_REFORZADO;
			case Acero: return Constantes.MATERIAL_CONSTRUCTOR_ACERO;
			case NoExiste: return Constantes.MATERIAL_CONSTRUCTOR_NO_EXISTE;
			default:
				return "";
			}
		}
	}

    /**
     * Enumeracion para las opciones de Material constructor
     * */
    public enum ListaDetalleMaterial implements  ListaDesplegable {
		Seleccione(Constantes.SELECCCIONE,0),
    	PanelesPrefabricados(Constantes.DETALLE_MATERIAL_PANELES_PREFABRICADOS, 4),
    	MamposteriaNoReforzada(Constantes.DETALLE_MATERIAL_MAMPOSTERIA_CONFINADA, 9),
    	Bahereque(Constantes.DETALLE_MATERIAL_BAHAREQUE, 18),
    	Porticos(Constantes.DETALLE_MATERIAL_PORTICOS,1),
    	SistemaDual(Constantes.DETALLE_MATERIAL_SISTEMA_DUAL,3),
    	ReticularCelulado(Constantes.DETALLE_MATERIAL_RETICULAR_CELULADO,5),
    	PorticosArriostrados(Constantes.DETALLE_MATERIAL_PORTICOS_ARRIOSTRADOS,11),
    	PorticosPanelesMadera(Constantes.DETALLE_MATERIAL_PORTICOS_PANELES_MADERA,14),
    	Adobe(Constantes.DETALLE_MATERIAL_ADOBE,16),
    	Tapia(Constantes.DETALLE_MATERIAL_TAPIA,17),
    	Muros(Constantes.DETALLE_MATERIAL_MUROS,2),
    	MamposteriaConfinada(Constantes.DETALLE_MATERIAL_MAMPOSTERIA_CONFINADA,7),
    	MamposteriaReforzada(Constantes.DETALLE_MATERIAL_MAMPOSTERIA_REFORZADA,8),
    	PorticosNoArriostrados(Constantes.DETALLE_MATERIAL_PORTICOS_NO_ARRIOSTRADOS,12),
    	NoDisponible(Constantes.DETALLE_MATERIAL_NO_DISPONIBLE,21);


		private final String value;
		private final int key;

		ListaDetalleMaterial  (String value, int key) {
			this.value = value;
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public int getKey() {
			return key;
		}

		public static ListaDetalleMaterial  fromKey(int key) {
			switch (key) {
			case 0: return Seleccione;
			case 4: return PanelesPrefabricados;
			case 9: return MamposteriaNoReforzada;
			case 18: return Bahereque;
			case 1 : return Porticos;
			case 3: return SistemaDual;
			case 5: return ReticularCelulado;
			case 11: return PorticosArriostrados;
			case 14: return PorticosPanelesMadera;
			case 16: return Adobe;
			case 17: return Tapia;
			case 2: return Muros;
			case 7: return MamposteriaConfinada;
			case 8: return MamposteriaReforzada;
			case 12: return PorticosNoArriostrados;
			case 21: return NoDisponible;

			default:
				return null;
			}
		}

		@Override
		public String toString() {
			switch (this) {
			case Seleccione: return Constantes.SELECCCIONE;
			case PanelesPrefabricados: return Constantes.DETALLE_MATERIAL_PANELES_PREFABRICADOS;
			case Bahereque: return Constantes.DETALLE_MATERIAL_BAHAREQUE;
			case Porticos: return Constantes.DETALLE_MATERIAL_PORTICOS;
			case SistemaDual: return Constantes.DETALLE_MATERIAL_SISTEMA_DUAL;
			case ReticularCelulado: return Constantes.DETALLE_MATERIAL_RETICULAR_CELULADO;
			case PorticosArriostrados: return Constantes.DETALLE_MATERIAL_PORTICOS_ARRIOSTRADOS;
			case PorticosPanelesMadera: return Constantes.DETALLE_MATERIAL_PORTICOS_PANELES_MADERA;
			case Adobe: return Constantes.DETALLE_MATERIAL_ADOBE;
			case Tapia: return Constantes.DETALLE_MATERIAL_TAPIA;
			case Muros: return Constantes.DETALLE_MATERIAL_MUROS;
			case MamposteriaConfinada: return Constantes.DETALLE_MATERIAL_MAMPOSTERIA_CONFINADA;
			case MamposteriaReforzada: return Constantes.DETALLE_MATERIAL_MAPOSTERIA_CONFINADA;
			case PorticosNoArriostrados: return Constantes.DETALLE_MATERIAL_PORTICOS_NO_ARRIOSTRADOS;
			case NoDisponible: return Constantes.DETALLE_MATERIAL_NO_DISPONIBLE;

			default:
				return "";
			}
		}
	}

    /**
     * Enumeracion para las opciones de Danos previos
     * */
    public enum ListaIrregularidad implements  ListaDesplegable {
		Seleccione(Constantes.SELECCCIONE,0),
    	SinIrregularidad(Constantes.IRREGULARIDAD_PLANTA_SIN_IRREGULARIDAD , 3),
    	NoDisponible(Constantes.IRREGULARIDAD_PLANTA_NO_DISPONIBILIDAD , 1),
    	ConIrregularidad(Constantes.IRREGULARIDAD_PLANTA_CON_IRREGULARIDAD ,2);

		private final String value;
		private final int key;

		ListaIrregularidad (String value, int key) {
			this.value = value;
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public int getKey() {
			return key;
		}

		public static ListaIrregularidad fromKey(int key) {
			switch (key) {
			case 0: return Seleccione;
			case 3: return SinIrregularidad;
			case 1: return NoDisponible;
			case 2: return ConIrregularidad;
			default:
				return null;
			}
		}

		@Override
		public String toString() {
			switch (this) {
			case Seleccione: return Constantes.SELECCCIONE;
			case SinIrregularidad: return Constantes.IRREGULARIDAD_PLANTA_SIN_IRREGULARIDAD ;
			case ConIrregularidad: return Constantes.IRREGULARIDAD_PLANTA_CON_IRREGULARIDAD ;
			case NoDisponible: return Constantes.IRREGULARIDAD_PLANTA_NO_DISPONIBILIDAD ;
			default:
				return "";
			}
		}
	}

    /**
     * Enumeracion para las opciones de estados de acabados
     * */
    public enum ListaEstadosAcabados implements  ListaDesplegable {
		Seleccione(Constantes.SELECCCIONE,0),
    	Bueno(Constantes.ESTADO_ACABADO_BUENO  , 1),
    	Regular(Constantes.ESTADO_ACABADO_REGULAR  , 2),
    	Malo(Constantes.ESTADO_ACABADO_MALO ,3),
    	SinAcabados(Constantes.ESTADO_ACABADO_SIN_ACABADOS  ,4);

		private final String value;
		private final int key;

		ListaEstadosAcabados (String value, int key) {
			this.value = value;
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public int getKey() {
			return key;
		}

		public static ListaEstadosAcabados fromKey(int key) {
			switch (key) {
			case 0: return ListaEstadosAcabados.Seleccione;
			case 1: return Bueno;
			case 2: return Regular;
			case 3: return Malo;
			case 4: return SinAcabados;
			default:
				return null;
			}
		}

		@Override
		public String toString() {
			switch (this) {
			case Seleccione: return Constantes.SELECCCIONE;
			case Bueno: return Constantes.ESTADO_ACABADO_BUENO  ;
			case Regular: return Constantes.ESTADO_ACABADO_REGULAR ;
			case Malo: return Constantes.ESTADO_ACABADO_MALO  ;
			case SinAcabados: return Constantes.ESTADO_ACABADO_SIN_ACABADOS  ;

			default:
				return "";
			}
		}
	}

    /**
     * Enumeracion para las opciones de Calidad de acabados
     * */
    public enum ListaCalidadAcabados implements  ListaDesplegable {
		Seleccione(Constantes.SELECCCIONE,0),
    	Sencillo(Constantes.CALIDAD_ACABADO_SENCILLO  , 1),
    	Normal(Constantes.CALIDAD_ACABADO_NORMAL  , 2),
    	Lujoso(Constantes.CALIDAD_ACABADO_LUJOSO ,3),
    	SinAcabados(Constantes.CALIDAD_ACABADO_SIN_ACABADOS ,21);

		private final String value;
		private final int key;

		ListaCalidadAcabados (String value, int key) {
			this.value = value;
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public int getKey() {
			return key;
		}

		public static ListaCalidadAcabados fromKey(int key) {
			switch (key) {
			case 0: return ListaCalidadAcabados.Seleccione;
			case 1: return Sencillo;
			case 2: return Normal;
			case 3: return Lujoso;
			case 21: return SinAcabados;
			default:
				return null;
			}
		}

		@Override
		public String toString() {
			switch (this) {
			case Seleccione: return Constantes.SELECCCIONE;
			case Sencillo: return Constantes.CALIDAD_ACABADO_SENCILLO ;
			case Normal: return Constantes.CALIDAD_ACABADO_NORMAL ;
			case Lujoso: return Constantes.CALIDAD_ACABADO_LUJOSO  ;
			case SinAcabados: return Constantes.CALIDAD_ACABADO_SIN_ACABADOS  ;

			default:
				return "";
			}
		}
	}


    /**
     * Enumeracion para las opciones de Calidad de acabados
     * */
    public enum ListaCalidadAcabadosCocina implements  ListaDesplegable {
		Seleccione(Constantes.SELECCCIONE,0),
    	Sencillo(Constantes.CALIDAD_ACABADO_SENCILLO  , 1),
    	SemiIntegral(Constantes.CALIDAD_ACABADOS_COCINA_SEMI_INTEGRAL  , 2),
    	Integral(Constantes.CALIDAD_ACABADOS_COCINA_INTEGRAL ,3),
    	SinAcabados(Constantes.CALIDAD_ACABADO_SIN_ACABADOS ,10);


		private final String value;
		private final int key;

		ListaCalidadAcabadosCocina (String value, int key) {
			this.value = value;
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public int getKey() {
			return key;
		}

		public static ListaCalidadAcabadosCocina fromKey(int key) {
			switch (key) {
			case 0: return Seleccione;
			case 1: return Sencillo;
			case 2: return SemiIntegral ;
			case 3: return Integral;
			case 10: return SinAcabados;
			default:
				return null;
			}
		}

		@Override
		public String toString() {
			switch (this) {
			case Seleccione: return Constantes.SELECCCIONE;
			case Sencillo: return Constantes.CALIDAD_ACABADO_SENCILLO ;
			case SemiIntegral: return Constantes.CALIDAD_ACABADOS_COCINA_SEMI_INTEGRAL ;
			case Integral: return Constantes.CALIDAD_ACABADOS_COCINA_INTEGRAL  ;
			case SinAcabados: return Constantes.CALIDAD_ACABADO_SIN_ACABADOS  ;

			default:
				return "";
			}
		}
	}

    /**
     * Enumeracion para las opciones de Calidad de acabados
     * */
    public enum ListaUsoActual implements  ListaDesplegable {
		Seleccione(Constantes.SELECCCIONE,0, false),
		Vivienda(Constantes.USO_PREDOMINANTE_INMUEBLE_VIVIENDA, 1, false),
		Comercio(Constantes.USO_PREDOMINANTE_INMUEBLE_COMERCIO, 2, false),
		Bodega(Constantes.USO_PREDOMINANTE_INMUEBLE_BODEGA, 3, false),
		Oficina(Constantes.USO_PREDOMINANTE_INMUEBLE_OFICINA, 4, false),
		Multihabitacional(Constantes.USO_PREDOMINANTE_INMUEBLE_MULTIHABITACIONAL, 5, false),
		Otro(Constantes.USO_PREDOMINANTE_INMUEBLE_OTRO, 6, false),
		
		SinUsoEriazo(Constantes.USO_PREDOMINANTE_INMUEBLE_SIN_USO_ERIAZO, 51, false),
    	SinUsoVivienda(Constantes.USO_PREDOMINANTE_INMUEBLE_SIN_USO_VIVIENDA, 52, false),
    	SegundaVivienda(Constantes.USO_PREDOMINANTE_INMUEBLE_SEGUNDA_VIVIENDA, 53, false),
    	Turismo(Constantes.USO_PREDOMINANTE_INMUEBLE_TURISMO, 54, false),
    	IndustriaAlimentos(Constantes.USO_PREDOMINANTE_INMUEBLE_INDUSTRIA_ALIMENTOS, 55, false),
    	IndustriaEnergiaySanitarias(Constantes.USO_PREDOMINANTE_INMUEBLE_INDUSTRIA_ENERGIA_Y_SANITARIOS, 56, false),
    	IndustriaMaderaYMuebles(Constantes.USO_PREDOMINANTE_INMUEBLE_INDUSTRIA_MADERA_Y_MUEBLES, 57, false),
    	IndustriaMetalmecanica(Constantes.USO_PREDOMINANTE_INMUEBLE_INDUSTRIA_METALMECANICA, 58, false),
    	IndustriaPapelEImprenta(Constantes.USO_PREDOMINANTE_INMUEBLE_INDUSTRIA_PAPEL_E_IMPRENTA, 59, false),
    	IndustriaQuimica(Constantes.USO_PREDOMINANTE_INMUEBLE_INDUSTRIA_QUIMICA, 60, false),
    	IndustriaTextilYCuero(Constantes.USO_PREDOMINANTE_INMUEBLE_INDUSTRIA_TEXTIL_Y_CUERO, 61, false),
    	IndustriaOtras(Constantes.USO_PREDOMINANTE_INMUEBLE_INDUSTRIA_OTRAS, 62, false),
    	Transporte(Constantes.USO_PREDOMINANTE_INMUEBLE_TRANSPORTE, 63, false),
    	Comunicaciones(Constantes.USO_PREDOMINANTE_INMUEBLE_COMUNICACIONES, 64, false),
    	ConstruccionViviendas(Constantes.USO_PREDOMINANTE_INMUEBLE_CONSTRUCCION_VIVIENDAS, 65, false),
    	ConstruccionOtrasObras(Constantes.USO_PREDOMINANTE_INMUEBLE_CONSTRUCCION_OTRAS_OBRAS, 66, false),
		
		Educacion(Constantes.USO_PREDOMINANTE_INMUEBLE_EDUCACION, 80, false),
		Salud(Constantes.USO_PREDOMINANTE_INMUEBLE_SALUD, 81, false),
		Hotelero(Constantes.USO_PREDOMINANTE_INMUEBLE_HOTELERO, 82, false),
		Industrial(Constantes.USO_PREDOMINANTE_INMUEBLE_INDUSTRIAL, 83, true),
		Iglesia(Constantes.USO_PREDOMINANTE_INMUEBLE_IGLESIA, 84, false),
		Parqueadero(Constantes.USO_PREDOMINANTE_INMUEBLE_PARQUEADERO, 85, false),
		Auditorio(Constantes.USO_PREDOMINANTE_INMUEBLE_AUDITORIO, 86, false),
		InstalacionDeportiva(Constantes.USO_PREDOMINANTE_INMUEBLE_INSTALACION_DEPORTIVA, 87, false);

		private final String value;
		private final int key;
		private final boolean disabled;

		ListaUsoActual (String value, int key, boolean disabled) {
			this.value = value;
			this.key = key;
			this.disabled = disabled;
		}

		public String getValue() {
			return value;
		}

		public int getKey() {
			return key;
		}
		
		public boolean getDisabled() {
			return disabled;
		}

		public static  ListaUsoActual  fromKey(int key) {
			switch (key) {
			case 0: return ListaUsoActual.Seleccione;
			case 1: return Vivienda;
			case 2: return Comercio;
			case 3: return Bodega;
			case 4: return Oficina;
			case 5: return Multihabitacional;
			case 6: return Otro;
			
			case 51: return SinUsoEriazo;
			case 52: return SinUsoVivienda;
			case 53: return SegundaVivienda;
			case 54: return Turismo;
			case 55: return IndustriaAlimentos;
			case 56: return IndustriaEnergiaySanitarias;
			case 57: return IndustriaMaderaYMuebles;
			case 58: return IndustriaMetalmecanica;
			case 59: return IndustriaPapelEImprenta;
			case 60: return IndustriaQuimica;
			case 61: return IndustriaTextilYCuero;
			case 62: return IndustriaOtras;
			case 63: return Transporte;
			case 64: return Comunicaciones;
			case 65: return ConstruccionViviendas;
			case 66: return ConstruccionOtrasObras;
			
			case 80: return Educacion;
			case 81: return Salud;
			case 82: return Hotelero;
			case 83: return Industrial;
			case 84: return Iglesia;
			case 85: return Parqueadero;
			case 86: return Auditorio;
			case 87: return InstalacionDeportiva;

			default:
				return null;
			}
		}

		@Override
		public String toString() {
			switch (this) {
			case Seleccione: return Constantes.SELECCCIONE;
			case Vivienda: return Constantes.USO_PREDOMINANTE_INMUEBLE_VIVIENDA;
			case Comercio: return Constantes.USO_PREDOMINANTE_INMUEBLE_COMERCIO;
			case Bodega: return Constantes.USO_PREDOMINANTE_INMUEBLE_BODEGA;
			case Multihabitacional: return Constantes.USO_PREDOMINANTE_INMUEBLE_MULTIHABITACIONAL;
			case Otro: return Constantes.USO_PREDOMINANTE_INMUEBLE_OTRO;
			case Salud: return Constantes.USO_PREDOMINANTE_INMUEBLE_SALUD;
			case Hotelero: return Constantes.USO_PREDOMINANTE_INMUEBLE_HOTELERO;
			case Industrial: return Constantes.USO_PREDOMINANTE_INMUEBLE_INDUSTRIAL;
			case Iglesia: return Constantes.USO_PREDOMINANTE_INMUEBLE_IGLESIA;
			case Parqueadero: return Constantes.USO_PREDOMINANTE_INMUEBLE_PARQUEADERO;
			case Auditorio: return Constantes.USO_PREDOMINANTE_INMUEBLE_AUDITORIO;
			case InstalacionDeportiva: return Constantes.USO_PREDOMINANTE_INMUEBLE_INSTALACION_DEPORTIVA;
			default:
				return "";
			}
		}
	}
}
