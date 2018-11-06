package com.helio4.bancol.avaluos.modelo;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "formato_informe_hipotecario")
@PrimaryKeyJoinColumn(name = "formato_informe_id")
public class FormatoInformeHipotecario extends FormatoInforme {

    @Column(name = "valor_comercial")
    private BigDecimal valorComercial;
    @Column(name = "valor_cortesia")
    private BigDecimal valorCortesia;
    @Column(name = "metodo_de_valuacion")
    private Integer metodoDeValuacion;
    @Column(name = "justificacion_de_metodologia")
    private String justificacionDeMetodologia;
    @Column(name = "documentos_consultados")
    private String documentosConsultados;
    @Column(name = "acueducto_en_el_sector")
    private Boolean acueductoEnElSector;
    @Column(name = "acueducto_en_el_predio")
    private Boolean acueductoEnElPredio;
    @Column(name = "alcantarillado_en_el_sector")
    private Boolean alcantarilladoEnElSector;
    @Column(name = "alcantarillado_en_el_predio")
    private Boolean alcantarilladoEnElPredio;
    @Column(name = "energia_en_el_sector")
    private Boolean energiaEnElSector;
    @Column(name = "energia_en_el_predio")
    private Boolean energiaEnElPredio;
    @Column(name = "gas_en_el_sector")
    private Boolean gasEnElSector;
    @Column(name = "gas_en_el_predio")
    private Boolean gasEnElPredio;
    @Column(name = "telefono_en_el_sector")
    private Boolean telefonoEnElSector;
    @Column(name = "telefono_en_el_predio")
    private Boolean telefonoEnElPredio;
    @Column(name = "estrato")
    private Integer estrato;
    @Column(name = "barrio_legal")
    private Boolean barrioLegal;
    @Column(name = "uso_predominante_inmueble")
    private Integer usoPredominanteInmueble;
    @Column(name = "desc_uso_predominante_inmueble_mixto")
    private String descUsoPredominanteInmuebleMixto;
    @Column(name = "topografia_sector")
    private Integer topografiaSector;
    @Column(name = "transporte")
    private Integer transporte;
    @Column(name = "perspectivas_de_valorizacion")
    private String perspectivasDeValorizacion;
    @Column(name = "condiciones_de_salubridad")
    private Integer condicionesDeSalubridad;
    @Column(name = "impacto_ambiental_aire")
    private Boolean impactoAmbientalAire;
    @Column(name = "impacto_ambiental_basura")
    private Boolean impactoAmbientalBasura;
    @Column(name = "impacto_ambiental_inseguridad")
    private Boolean impactoAmbientalInseguridad;
    @Column(name = "impacto_ambiental_ruido")
    private Boolean impactoAmbientalRuido;
    @Column(name = "impacto_ambiental_aguas_servidas")
    private Boolean impactoAmbientalAguasServidas;
    @Column(name = "impacto_ambiental_otro")
    private Boolean impactoAmbientalOtro;

    @Column(name = "explicacion_impacto_ambiental_aire")
    private String explicacionImpactoAmbientalAire;
    @Column(name = "explicacion_impacto_ambiental_basura")
    private String explicacionImpactoAmbientalBasura;
    @Column(name = "explicacion_impacto_ambiental_inseguridad")
    private String explicacionImpactoAmbientalInseguridad;
    @Column(name = "explicacion_impacto_ambiental_ruido")
    private String explicacionImpactoAmbientalRuido;
    @Column(name = "explicacion_impacto_ambiental_aguas_servidas")
    private String explicacionImpactoAmbientalAguasServidas;

    @Column(name = "explicacion_i_a_otro")
    private String explicacionOtro;
    @Column(name = "estado_de_las_vias")
    private Integer estadoDeLasVias;
    @Column(name = "vias_pavimentadas")
    private Boolean viasPavimentadas;
    @Column(name = "andenes_en_las_vias")
    private Boolean andenesEnLasVias;
    @Column(name = "sardineles_en_las_vias")
    private Boolean sardinelesEnLasVias;
    @Column
    private Boolean parques;
    @Column
    private Boolean paradero;
    @Column
    private Boolean alumbrado;
    @Column(name = "zonas_verdes_publicas")
    private Boolean zonasVerdesPublicas;
    @Column
    private Boolean arborizacion;
    @Column
    private Boolean alamedas;
    @Column(name = "ciclo_rutas")
    private Boolean cicloRutas;
    @Column(name = "clase_inmueble")
    private Integer claseInmueble;
    @Column(name = "descripcion_otros_tipo_inmueble")
    private String descripcionOtrosTipoInmueble;
    @Column(name = "uso_actual")
    private Integer usoActual;
    @Column(name = "descripcion_otros_uso_inmueble")
    private String descripcionOtrosUsoInmueble;
    @Column(name = "tipo_de_vivienda")
    private Integer tipoDeVivienda;
    @Column(name = "descripcion_otros_tipo_vivienda")
    private String descripcionOtrosTipoVivienda;
    @Column
    private String chip;
    @Column(name = "matricula_inmobiliaria_principal_2")
    private String matriculaInmobiliariaPrincipal2;
    @Column(name = "numero_de_escritura")
    private String numeroDeEscritura;
    @Column(name = "numero_notaria_de_escritura")
    private String numeroNotariaDeEscritura;
    @Column(name = "fecha_de_escritura")
    private Date fechaDeEscritura;
    @Column(name = "departamento_notaria")
    private String departamentoNotaria;
    @ManyToOne
    @JoinColumn(name = "ciudad_notaria")
    private Divipola ciudadNotaria;
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
    private String numeroDeposito1;
    @Column(name = "numero_deposito_2")
    private String numeroDeposito2;
    @Column(name = "numero_deposito_3")
    private String numeroDeposito3;
    @Column(name = "numero_deposito_4")
    private String numeroDeposito4;
    @Column(name = "numero_deposito_5")
    private String numeroDeposito5;
    @Column(name = "numero_deposito_exclusivo_1")
    private String numeroDepositoExclusivo1;
    @Column(name = "numero_deposito_exclusivo_2")
    private String numeroDepositoExclusivo2;
    @Column(name = "numero_deposito_exclusivo_3")
    private String numeroDepositoExclusivo3;
    @Column(name = "numero_deposito_exclusivo_4")
    private String numeroDepositoExclusivo4;
    @Column(name = "numero_deposito_exclusivo_5")
    private String numeroDepositoExclusivo5;
    @Column(name = "numero_de_pisos")
    private Integer numeroDePisos;
    @Column(name = "numero_sotanos")
    private Integer numeroSotanos;
    @Column
    private Integer vetustez;
    @Column(name = "estado_de_conservacion")
    private Integer estadoDeConservacion;
    @Column(name = "licencia_construccion")
    private String licenciaConstruccion;
    @Column(name = "tipo_licencia")
    private Integer tipoLicencia;
    @Column(name = "numero_licencia")
    private String numeroLicencia;
    @Column(name = "estado_de_construccion")
    private Integer estadoDeConstruccion;
    @Column(name = "estado_de_obra")
    private Integer estadoDeObra;
    @Column(name = "ano_de_construccion")
    private Integer anoDeConstruccion;
    @Column(name = "porcentaje_avance")
    private Integer porcentajeAvance;
    @Column
    private Boolean remodelado;
    @Column(name = "fecha_remodelacion")
    private Date fechaRemodelacion;
    @Column
    private Integer estructura;
    @Column
    private Integer reparados;
    @Column
    private Integer fachada;
    @Column(name = "tipo_fachada")
    private Integer tipoFachada;
    @Column(name = "estructura_reforzada")
    private Integer estructuraReforzada;
    @Column(name = "danos_previos")
    private Integer danosPrevios;
    @Column(name = "material_constructor")
    private Integer materialConstructor;
    @Column(name = "detalle_material")
    private Integer detalleMaterial;
    @Column(name = "irregularidad_planta")
    private Integer irregularidadPlanta;
    @Column(name = "irregularidad_altura")
    private Integer irregularidadAltura;
    @Column
    private Integer cubierta;
    @Column(name = "pisos_bodega")
    private Integer pisosBodega;
    @Column(name = "tipologia_vivienda_unica_familiar")
    private String tipologiaViviendaUnicaFamiliar;
    @Column
    private Integer sala;
    @Column
    private Integer deposito;
    @Column
    private Integer comedor;
    @Column
    private Integer estudio;
    @Column
    private Integer jardin;
    @Column(name = "bano_social")
    private Integer banoSocial;
    @Column(name = "estar_habitacion")
    private Integer estarHabitacion;
    @Column
    private Integer habitaciones;
    @Column(name = "depositos_privados")
    private Integer depositosPrivados;
    @Column(name = "depositos_exclusivos")
    private Integer depositosExclusivos;
    @Column(name = "numero_total_depositos")
    private Integer numeroTotalDepositos;
    @Column(name = "bano_privado")
    private Integer banoPrivado;
    @Column
    private Integer cocina;
    @Column(name = "cuarto_servicio")
    private Integer cuartoServicio;
    @Column
    private Integer oficina;
    @Column(name = "bano_servicio")
    private Integer banoServicio;
    @Column(name = "patio_interior")
    private Integer patioInterior;
    @Column
    private Integer terraza;
    @Column
    private Integer bodega;
    @Column(name = "zona_de_ropas")
    private Integer zonaDeRopas;
    @Column
    private Integer balcon;
    @Column
    private Integer closet;
    @Column
    private Integer local;
    @Column(name = "zona_verde_privada")
    private Integer zonaVerdePrivada;
    @Column
    private Integer iluminacion;
    @Column
    private Integer ventilacion;
    @Column(name = "garaje_privado")
    private Integer garajePrivado;
    @Column(name = "garaje_exclusivo")
    private Integer garajeExclusivo;
    @Column(name = "bahia_comunal")
    private Integer bahiaComunal;
    @Column(name = "garaje_doble_1")
    private Boolean garajeDoble1;
    @Column(name = "garaje_doble_2")
    private Boolean garajeDoble2;
    @Column(name = "garaje_doble_3")
    private Boolean garajeDoble3;
    @Column(name = "garaje_doble_4")
    private Boolean garajeDoble4;
    @Column(name = "garaje_doble_5")
    private Boolean garajeDoble5;
    @Column(name = "garaje_paralelo")
    private Boolean garajeParalelo;
    @Column(name = "numero_total_de_garajes")
    private Integer numeroTotalDeGarajes;
    @Column(name = "total_cupos_parquedaro")
    private Integer totalCuposParquedaro;
    @Column(name = "estado_acabados_pisos")
    private Integer estadoAcabadosPisos;
    @Column(name = "estado_acabados_muros")
    private Integer estadoAcabadosMuros;
    @Column(name = "estado_acabados_techos")
    private Integer estadoAcabadosTechos;
    @Column(name = "estado_acabados_madera")
    private Integer estadoAcabadosMadera;
    @Column(name = "estado_acabados_metal")
    private Integer estadoAcabadosMetal;
    @Column(name = "estado_acabados_banos")
    private Integer estadoAcabadosBanos;
    @Column(name = "estado_acabados_cocina")
    private Integer estadoAcabadosCocina;
    @Column(name = "calidad_acabados_pisos")
    private Integer calidadAcabadosPisos;
    @Column(name = "calidad_acabados_muros")
    private Integer calidadAcabadosMuros;
    @Column(name = "calidad_acabados_techos")
    private Integer calidadAcabadosTechos;
    @Column(name = "calidad_acabados_madera")
    private Integer calidadAcabadosMadera;
    @Column(name = "calidad_acabados_metal")
    private Integer calidadAcabadosMetal;
    @Column(name = "calidad_acabados_banos")
    private Integer calidadAcabadosBanos;
    @Column(name = "calidad_acabados_cocina")
    private Integer calidadAcabadosCocina;
    @Column(name = "sometido_a_propiedad_horizontal")
    private Boolean sometidoAPropiedadHorizontal;
    @Column(name = "conjunto_cerrado")
    private Boolean conjuntoCerrado;
    @Column(name = "ubicacion_del_inmueble")
    private Integer ubicacionDelInmueble;
    @Column(name = "numero_piso")
    private Integer numeroPiso;
    @Column(name = "numero_de_edificios")
    private Integer numeroDeEdificios;
    @Column(name = "unidades_por_piso")
    private Integer unidadesPorPiso;
    @Column(name = "total_unidades")
    private Integer totalUnidades;
    @Column(name = "coeficiente")
    private BigDecimal coeficiente;
    @Column
    private Boolean porteria;
    @Column
    private Boolean citofono;
    @Column
    private Boolean bicicletero;
    @Column
    private Boolean piscina;
    @Column(name = "tanque_de_agua")
    private Boolean tanqueDeAgua;
    @Column(name = "club_house")
    private Boolean clubHouse;
    @Column(name = "garaje_visitantes")
    private Boolean garajeVisitantes;
    @Column(name = "juegos_ninos")
    private Boolean juegosNinos;
    @Column(name = "cancha_multiple")
    private Boolean canchaMultiple;
    @Column(name = "bomba_eyectora")
    private Boolean bombaEyectora;
    @Column(name = "aire_acondicionado_central")
    private Boolean aireAcondicionadoCentral;
    @Column(name = "cancha_squash")
    private Boolean canchaSquash;
    @Column(name = "zonas_verdes_comunales")
    private Boolean zonasVerdesComunales;
    @Column
    private Boolean gimnasio;
    @Column
    private Boolean golfito;
    @Column(name = "salon_comunal")
    private Boolean salonComunal;
    @Column(name = "shut_basuras")
    private Boolean shutBasuras;
    @Column(name = "equipo_de_presion_constante")
    private Boolean equipoDePresionConstante;
    @Column(name = "planta_electrica")
    private Boolean plantaElectrica;
    @Column
    private Boolean ascensor;
    @Column(name = "numero_de_ascensores")
    private Integer numeroDeAscensores;
    @Column
    private String otros;
    @Column(name = "actualidad_edificadora")
    private String actualidadEdificadora;
    @Column(name = "entrega_de_documentos")
    private String entregaDeDocumentos;
    @Column(name = "comportamiento_oferta_demanda")
    private String comportamientoOfertaDemanda;
    @Column(name = "tiempo_esperado_de_comercializacion")
    private Double tiempoEsperadoDeComercializacion;
    @Column(name = "porcentaje_terreno")
    private BigDecimal porcentajeTerreno;
    @Column(name = "valor_proporcional_terreno")
    private BigDecimal valorProporcionalTerreno;
    @Column(name = "valor_proporcional_construccion")
    private BigDecimal valorProporcionalConstruccion;
    @Column(name = "valor_integral_terreno")
    private BigDecimal valorIntegralTerreno;
    @Column(name = "valor_integral_construccion")
    private BigDecimal valorIntegralConstruccion;
    @Column
    private String observaciones;
    @Column(name = "direccion_anexos")
    private String direccionAnexos;
    @Column(name = "otras_direcciones")
    private String otrasDirecciones;
    @Column(name = "area_construida")
    private BigDecimal areaConstruida;
    @Column(name = "area_privada")
    private BigDecimal areaPrivada;

    @Column(name="observaciones_garajes")
    private String observacionesGarajes;
    
    @Column(name="ocupante")
    private String ocupante;    
    @Column(name="condicion_ph")
    private String condicionPh;
    @Column(name="teatrino")
    private Boolean teatrino;
    @Column(name="sauna")
    private Boolean sauna;
    @Column(name="calefacion")
    private Boolean calefaccion;
    @Column(name="terraza_comunal")
    private Boolean terrazaComunal;
    @Column(name="turco")
    private Boolean turco;
    @Column(name="bbq")
    private Boolean bbq;
    @Column(name="guarderia")
    private Boolean guarderia;
    @Column(name="jardin_infantil")
    private Boolean jardinInfantil;
    @Column(name="vigilancia_privada")
    private String vigilanciaPrivada;
    @Column(name="administracion")
    private Boolean administracion;
    @Column(name="valor_administracion")
    private Integer valorAdministracion;
    @Column(name="decreto_acuerdo")
    private String decretoAcuerdo;
    @Column(name="observaciones_decreto_acuerdo")
    private String observacionDecretoAcuerdo;
    @Column(name="altura_permitida")
    private String alturaPermitida;
    @Column(name="observaciones_altura_permitida")
    private String observacionAlturaPermitida;
    @Column(name="uso_principal")
    private String usoPrincipal;
    @Column(name="observaciones_uso_principal")
    private String observacionUsoPrincipal;
    @Column(name="aislamiento_posterior")
    private String aislamientoPosterior;
    @Column(name="observaciones_aislamiento_posterior")
    private String observacionAislamientoPosterior;
    @Column(name="aislamiento_lateral")
    private String aislamientoLateral;
    @Column(name="observaciones_aislamiento_lateral")
    private String observacionAislamientoLateral;
    @Column(name="antejardin")
    private String anteJardin;
    @Column(name="observaciones_antejardin")
    private String observacionAnteJardin;
    @Column(name="indice_ocupacion")
    private String indiceOcupacion;
    @Column(name="observaciones_indice_ocupacion")
    private String observacionIndiceOcupacion;
    @Column(name="indice_construccion")
    private String indiceConstruccion;
    @Column(name="observaciones_indice_construccion")
    private String observacionIndiceConstruccion;
    @Column(name="predio_subdividido_fisicamente")
    private String predioSubdivididoFisicamente;
    @Column(name="observaciones_predio_subdividido_fisicamente")
    private String observacionPredioSubdivididoFisicamente;
    
    @Column(name="estado_conservacion_alcantarrillado")
    private String estadoConservacionAlcantarrillado;
    @Column(name="estado_conservacion_acueducto")
    private String estadoConservacionAcueducto;
    @Column(name="estado_conservacion_gas")
    private String estadoConservacionGas;
    @Column(name="estado_conservacion_energia")
    private String estadoConservacionEnergia;
    
    
    @Column(name="estado_conservacion_telefono")
    private String estadoConservacionTelefono;
    @Column(name="estado_conservacion_vias_pavimentadas")
    private String estadoConservacionViasPavimentadas;
    @Column(name="estado_conservacion_sardineles_en_las_vias")
    private String estadoConservacionSardinelesEnLasVias;
    @Column(name="estado_conservacion_andenes_en_las_vias")
    private String estadoConservacionAndenesEnLasVias;
    
    @Column(name="demanda_interes")
    private String demandaInteres;
    
    //Nivel de equipamiento
    @Column(name="nivel_equipamiento_comercial")
    private String nivelEquipamientoComercial;
    @Column(name="nivel_equipamiento_escolar")
    private String nivelEquipamientoEscolar;
    @Column(name="nivel_equipamiento_asistencial")
    private String nivelEquipamientoAsistencial;
    @Column(name="nivel_equipamiento_estacionamiento")
    private String nivelEquipamientoEstacionamiento;
    @Column(name="nivel_equipamiento_areas_verdes")
    private String nivelEquipamientoAreasVerdes;
    @Column(name="nivel_equipamiento_zonas_recreativas")
    private String nivelEquipamientoZonasRecreativas;
    
    //Distancia aproximada
    @Column(name="distancia_aproximada_comercial")
    private String distanciaAproximadaComercial;
    @Column(name="distancia_aproximada_escolar")
    private String distanciaAproximadaEscolar;
    @Column(name="distancia_aproximada_asistencial")
    private String distanciaAproximadaAsistencial;
    @Column(name="distancia_aproximada_estacionamiento")
    private String distanciaAproximadaEstacionamiento;
    @Column(name="distancia_aproximada_areas_verdes")
    private String distanciaAproximadaAreasVerdes;
    @Column(name="distancia_aproximada_zonas_recreativas")
    private String distanciaAproximadaZonasRecreativas;
    
    @Column(name="descripcion_general_sector")
    private String descripcionGeneralSector;
    @Column(name="observaciones_generales_construccion")
    private String observacionesGeneralesConstruccion;
    @Column(name="observaciones_generales_inmueble")
    private String observacionesGeneralesInmueble;
    @Column(name="observaciones_estructura")
    private String observacionesEstructura;
    @Column(name="observaciones_dependencias")
    private String observacionesDependencias;
    @Column(name="observaciones_acabados")
    private String observacionesAcabados;
    @Column(name="descripcion_general")
    private String descripcionGeneral;
    
    @Column(name="area_actividad")
    private String areaActividad;
    
    @Column(name="uso_principal_ph")
    private String usoPrincipalPh;
    
    @Column(name = "rph")
    private Boolean rph;

    @Column(name = "mostrar_predio_subdividido_fisicamente")
    private Boolean mostrarPredioSubdivididoFisicamente;
    
    @Column(name = "unidades")
    private Integer unidades;
    
    @Column(name = "contadores_agua")
    private Integer contadoresAgua;
    
    @Column(name = "contadores_luz")
    private Integer contadoresLuz;
    
    @Column(name = "accesorios")
    private Integer accesos;
    
    
    /** Credito Hipotecario Vivienda  */
    @Column(name = "tfc1")
    private Boolean tfc1;
    /** Retanqueo Vivienda */
    @Column(name = "tfc2")
    private Boolean tfc2;
    /** Reforma o Remodelacion Vivienda */
    @Column(name = "tfc3")
    private Boolean tfc3;
    /** Constructor Individual */
    @Column(name = "tfc4")
    private Boolean tfc4;
    /** Leasing Habitacional Persona Natural */
    @Column(name = "tfc5")
    private Boolean tfc5;
    /** Leasing Inmobiliario Vivienda persona Juridica */
    @Column(name = "tfc6")
    private Boolean tfc6;
    /** Credito Hipotecario  Diferente de Vivienda */
    @Column(name = "tfc7")
    private Boolean tfc7;
    /** Retanqueo  Diferente de Vivienda */
    @Column(name = "tfc8")
    private Boolean tfc8;
    /** Leasing Diferente de Vivienda Persona Natural */
    @Column(name = "tfc9")
    private Boolean tfc9;
    /** Leasing Inmobiliario Difernete de  Vivienda persona Juridica */
    @Column(name = "tfc10")
    private Boolean tfc10;
    
    
    public FormatoInformeHipotecario() {
        super();
    }

    public FormatoInformeHipotecario(Avaluo avaluo) {
        super(avaluo);
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

    public Integer getUsoPredominanteInmueble() {
        return usoPredominanteInmueble;
    }

    public void setUsoPredominanteInmueble(Integer usoPredominanteInmueble) {
        this.usoPredominanteInmueble = usoPredominanteInmueble;
    }

    public Integer getTopografiaSector() {
        return topografiaSector;
    }

    public void setTopografiaSector(Integer topografiaSector) {
        this.topografiaSector = topografiaSector;
    }

    public Integer getTransporte() {
        return transporte;
    }

    public void setTransporte(Integer transporte) {
        this.transporte = transporte;
    }

    public String getPerspectivasDeValorizacion() {
        return perspectivasDeValorizacion;
    }

    public void setPerspectivasDeValorizacion(String perspectivasDeValorizacion) {
        this.perspectivasDeValorizacion = perspectivasDeValorizacion;
    }

    public Integer getCondicionesDeSalubridad() {
        return condicionesDeSalubridad;
    }

    public void setCondicionesDeSalubridad(Integer condicionesDeSalubridad) {
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

    public void setImpactoAmbientalInseguridad(
            Boolean impactoAmbientalInseguridad) {
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

    public Integer getEstadoDeLasVias() {
        return estadoDeLasVias;
    }

    public void setEstadoDeLasVias(Integer estadoDeLasVias) {
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

    public Integer getClaseInmueble() {
        return claseInmueble;
    }

    public void setClaseInmueble(Integer claseInmueble) {
        if (new Integer(0).equals(claseInmueble)) {
            claseInmueble = null;
        }
        this.claseInmueble = claseInmueble;
    }

    public String getDescripcionOtrosTipoInmueble() {
        return descripcionOtrosTipoInmueble;
    }

    public void setDescripcionOtrosTipoInmueble(
            String descripcionOtrosTipoInmueble) {
        this.descripcionOtrosTipoInmueble = descripcionOtrosTipoInmueble;
    }

    public Integer getUsoActual() {
        return usoActual;
    }

    public void setUsoActual(Integer usoActual) {
        this.usoActual = usoActual;
    }

    public String getDescripcionOtrosUsoInmueble() {
        return descripcionOtrosUsoInmueble;
    }

    public void setDescripcionOtrosUsoInmueble(
            String descripcionOtrosUsoInmueble) {
        this.descripcionOtrosUsoInmueble = descripcionOtrosUsoInmueble;
    }

    public Integer getTipoDeVivienda() {
        return tipoDeVivienda;
    }

    public void setTipoDeVivienda(Integer tipoDeVivienda) {
        if (new Integer(0).equals(tipoDeVivienda)) {
            tipoDeVivienda = null;
        }
        this.tipoDeVivienda = tipoDeVivienda;
    }

    public String getDescripcionOtrosTipoVivienda() {
        return descripcionOtrosTipoVivienda;
    }

    public void setDescripcionOtrosTipoVivienda(
            String descripcionOtrosTipoVivienda) {
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

    public Divipola getCiudadNotaria() {
        return ciudadNotaria;
    }

    public void setCiudadNotaria(Divipola ciudadNotaria) {
        this.ciudadNotaria = ciudadNotaria;
    }
//
//    public String getMatriculaInmobiliariaGaraje1() {
//        return matriculaInmobiliariaGaraje1;
//    }
//
//    public void setMatriculaInmobiliariaGaraje1(
//            String matriculaInmobiliariaGaraje1) {
//        this.matriculaInmobiliariaGaraje1 = matriculaInmobiliariaGaraje1;
//    }
//
//    public String getMatriculaInmobiliariaGaraje2() {
//        return matriculaInmobiliariaGaraje2;
//    }
//
//    public void setMatriculaInmobiliariaGaraje2(
//            String matriculaInmobiliariaGaraje2) {
//        this.matriculaInmobiliariaGaraje2 = matriculaInmobiliariaGaraje2;
//    }
//
//    public String getMatriculaInmobiliariaGaraje3() {
//        return matriculaInmobiliariaGaraje3;
//    }
//
//    public void setMatriculaInmobiliariaGaraje3(
//            String matriculaInmobiliariaGaraje3) {
//        this.matriculaInmobiliariaGaraje3 = matriculaInmobiliariaGaraje3;
//    }
//
//    public String getMatriculaInmobiliariaGaraje4() {
//        return matriculaInmobiliariaGaraje4;
//    }
//
//    public void setMatriculaInmobiliariaGaraje4(
//            String matriculaInmobiliariaGaraje4) {
//        this.matriculaInmobiliariaGaraje4 = matriculaInmobiliariaGaraje4;
//    }
//
//    public String getMatriculaInmobiliariaGaraje5() {
//        return matriculaInmobiliariaGaraje5;
//    }
//
//    public void setMatriculaInmobiliariaGaraje5(
//            String matriculaInmobiliariaGaraje5) {
//        this.matriculaInmobiliariaGaraje5 = matriculaInmobiliariaGaraje5;
//    }
//
//    public Boolean getGarajeParalelo1() {
//        return garajeParalelo1;
//    }
//
//    public void setGarajeParalelo1(Boolean garajeParalelo1) {
//        this.garajeParalelo1 = garajeParalelo1;
//    }
//
//    public Boolean getGarajeParalelo2() {
//        return garajeParalelo2;
//    }
//
//    public void setGarajeParalelo2(Boolean garajeParalelo2) {
//        this.garajeParalelo2 = garajeParalelo2;
//    }
//
//    public Boolean getGarajeParalelo3() {
//        return garajeParalelo3;
//    }
//
//    public void setGarajeParalelo3(Boolean garajeParalelo3) {
//        this.garajeParalelo3 = garajeParalelo3;
//    }
//
//    public Boolean getGarajeParalelo4() {
//        return garajeParalelo4;
//    }
//
//    public void setGarajeParalelo4(Boolean garajeParalelo4) {
//        this.garajeParalelo4 = garajeParalelo4;
//    }
//
//    public Boolean getGarajeParalelo5() {
//        return garajeParalelo5;
//    }
//
//    public void setGarajeParalelo5(Boolean garajeParalelo5) {
//        this.garajeParalelo5 = garajeParalelo5;
//    }
//
//    public Boolean getGarajeCubierto1() {
//        return garajeCubierto1;
//    }
//
//    public void setGarajeCubierto1(Boolean garajeCubierto1) {
//        this.garajeCubierto1 = garajeCubierto1;
//    }
//
//    public Boolean getGarajeCubierto2() {
//        return garajeCubierto2;
//    }
//
//    public void setGarajeCubierto2(Boolean garajeCubierto2) {
//        this.garajeCubierto2 = garajeCubierto2;
//    }
//
//    public Boolean getGarajeCubierto3() {
//        return garajeCubierto3;
//    }
//
//    public void setGarajeCubierto3(Boolean garajeCubierto3) {
//        this.garajeCubierto3 = garajeCubierto3;
//    }
//
//    public Boolean getGarajeCubierto4() {
//        return garajeCubierto4;
//    }
//
//    public void setGarajeCubierto4(Boolean garajeCubierto4) {
//        this.garajeCubierto4 = garajeCubierto4;
//    }
//
//    public Boolean getGarajeCubierto5() {
//        return garajeCubierto5;
//    }
//
//    public void setGarajeCubierto5(Boolean garajeCubierto5) {
//        this.garajeCubierto5 = garajeCubierto5;
//    }
//
//    public String getNumeroParqueadero1() {
//        return numeroParqueadero1;
//    }
//
//    public void setNumeroParqueadero1(String numeroParqueadero1) {
//        this.numeroParqueadero1 = numeroParqueadero1;
//    }
//
//    public String getNumeroParqueadero2() {
//        return numeroParqueadero2;
//    }
//
//    public void setNumeroParqueadero2(String numeroParqueadero2) {
//        this.numeroParqueadero2 = numeroParqueadero2;
//    }
//
//    public String getNumeroParqueadero3() {
//        return numeroParqueadero3;
//    }
//
//    public void setNumeroParqueadero3(String numeroParqueadero3) {
//        this.numeroParqueadero3 = numeroParqueadero3;
//    }
//
//    public String getNumeroParqueadero4() {
//        return numeroParqueadero4;
//    }
//
//    public void setNumeroParqueadero4(String numeroParqueadero4) {
//        this.numeroParqueadero4 = numeroParqueadero4;
//    }
//
//    public String getNumeroParqueadero5() {
//        return numeroParqueadero5;
//    }
//
//    public void setNumeroParqueadero5(String numeroParqueadero5) {
//        this.numeroParqueadero5 = numeroParqueadero5;
//    }
//
//    public String getNumeroParqueaderoExclusivo1() {
//        return numeroParqueaderoExclusivo1;
//    }
//
//    public void setNumeroParqueaderoExclusivo1(
//            String numeroParqueaderoExclusivo1) {
//        if ("0".equals(numeroParqueaderoExclusivo1)) {
//            numeroParqueaderoExclusivo1 = null;
//        }
//        this.numeroParqueaderoExclusivo1 = numeroParqueaderoExclusivo1;
//    }
//
//    public String getNumeroParqueaderoExclusivo2() {
//        return numeroParqueaderoExclusivo2;
//    }
//
//    public void setNumeroParqueaderoExclusivo2(
//            String numeroParqueaderoExclusivo2) {
//        if ("0".equals(numeroParqueaderoExclusivo2)) {
//            numeroParqueaderoExclusivo2 = null;
//        }
//        this.numeroParqueaderoExclusivo2 = numeroParqueaderoExclusivo2;
//    }
//
//    public String getNumeroParqueaderoExclusivo3() {
//        return numeroParqueaderoExclusivo3;
//    }
//
//    public void setNumeroParqueaderoExclusivo3(
//            String numeroParqueaderoExclusivo3) {
//        if ("0".equals(numeroParqueaderoExclusivo3)) {
//            numeroParqueaderoExclusivo3 = null;
//        }
//        this.numeroParqueaderoExclusivo3 = numeroParqueaderoExclusivo3;
//    }
//
//    public String getNumeroParqueaderoExclusivo4() {
//        return numeroParqueaderoExclusivo4;
//    }
//
//    public void setNumeroParqueaderoExclusivo4(
//            String numeroParqueaderoExclusivo4) {
//        if ("0".equals(numeroParqueaderoExclusivo4)) {
//            numeroParqueaderoExclusivo4 = null;
//        }
//        this.numeroParqueaderoExclusivo4 = numeroParqueaderoExclusivo4;
//    }
//
//    public String getNumeroParqueaderoExclusivo5() {
//        return numeroParqueaderoExclusivo5;
//    }
//
//    public void setNumeroParqueaderoExclusivo5(
//            String numeroParqueaderoExclusivo5) {
//        if ("0".equals(numeroParqueaderoExclusivo5)) {
//            numeroParqueaderoExclusivo5 = null;
//        }
//        this.numeroParqueaderoExclusivo5 = numeroParqueaderoExclusivo5;
//    }
//
//    public Boolean getGarajeServidumbre1() {
//        return garajeServidumbre1;
//    }
//
//    public void setGarajeServidumbre1(Boolean garajeServidumbre1) {
//        this.garajeServidumbre1 = garajeServidumbre1;
//    }
//
//    public Boolean getGarajeServidumbre2() {
//        return garajeServidumbre2;
//    }
//
//    public void setGarajeServidumbre2(Boolean garajeServidumbre2) {
//        this.garajeServidumbre2 = garajeServidumbre2;
//    }
//
//    public Boolean getGarajeServidumbre3() {
//        return garajeServidumbre3;
//    }
//
//    public void setGarajeServidumbre3(Boolean garajeServidumbre3) {
//        this.garajeServidumbre3 = garajeServidumbre3;
//    }
//
//    public Boolean getGarajeServidumbre4() {
//        return garajeServidumbre4;
//    }
//
//    public void setGarajeServidumbre4(Boolean garajeServidumbre4) {
//        this.garajeServidumbre4 = garajeServidumbre4;
//    }
//
//    public Boolean getGarajeServidumbre5() {
//        return garajeServidumbre5;
//    }
//
//    public void setGarajeServidumbre5(Boolean garajeServidumbre5) {
//        this.garajeServidumbre5 = garajeServidumbre5;
//    }

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
        this.numeroDePisos = numeroDePisos;
    }

    public Integer getNumeroSotanos() {
        return numeroSotanos;
    }

    public void setNumeroSotanos(Integer numeroSotanos) {
        this.numeroSotanos = numeroSotanos;
    }

    public Integer getVetustez() {
        return vetustez;
    }

    public void setVetustez(Integer vetustez) {
        this.vetustez = vetustez;
    }

    public Integer getEstadoDeConservacion() {
        return estadoDeConservacion;
    }

    public void setEstadoDeConservacion(Integer estadoDeConservacion) {
        this.estadoDeConservacion = estadoDeConservacion;
    }

    public Integer getEstadoDeConstruccion() {
        return estadoDeConstruccion;
    }

    public void setEstadoDeConstruccion(Integer estadoDeConstruccion) {
        this.estadoDeConstruccion = estadoDeConstruccion;
    }

    public Integer getEstadoDeObra() {
        return estadoDeObra;
    }

    public void setEstadoDeObra(Integer estadoDeObra) {
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

	public Integer getEstructura() {
        return estructura;
    }

    public void setEstructura(Integer estructura) {
        this.estructura = estructura;
    }

    public Integer getEstructuraReforzada() {
        return estructuraReforzada;
    }

    public void setEstructuraReforzada(Integer estructuraReforzada) {
        this.estructuraReforzada = estructuraReforzada;
    }

    public Integer getFachada() {
        return fachada;
    }

    public void setFachada(Integer fachada) {
        if (new Integer(0).equals(fachada)) {
            fachada = null;
        }
        this.fachada = fachada;
    }

    public Integer getTipoFachada() {
        return tipoFachada;
    }

    public void setTipoFachada(Integer tipoFachada) {
        this.tipoFachada = tipoFachada;
    }

    public Integer getCubierta() {
        return cubierta;
    }

    public void setCubierta(Integer cubierta) {
        if (new Integer(0).equals(cubierta)) {
            cubierta = null;
        }
        this.cubierta = cubierta;
    }

    public Integer getPisosBodega() {
        return pisosBodega;
    }

    public void setPisosBodega(Integer pisosBodega) {
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
        this.comedor = comedor;
    }

    public Integer getEstudio() {
        return estudio;
    }

    public void setEstudio(Integer estudio) {
        this.estudio = estudio;
    }

    public Integer getJardin() {
        return jardin;
    }

    public void setJardin(Integer jardin) {
        this.jardin = jardin;
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

    public Integer getBanoPrivado() {
        return banoPrivado;
    }

    public void setBanoPrivado(Integer banoPrivado) {
        this.banoPrivado = banoPrivado;
    }

    public Integer getCocina() {
        return cocina;
    }

    public void setCocina(Integer cocina) {
        this.cocina = cocina;
    }

    public Integer getCuartoServicio() {
        return cuartoServicio;
    }

    public void setCuartoServicio(Integer cuartoServicio) {
        this.cuartoServicio = cuartoServicio;
    }

    public Integer getOficina() {
        return oficina;
    }

    public void setOficina(Integer oficina) {
        this.oficina = oficina;
    }

    public Integer getBanoServicio() {
        return banoServicio;
    }

    public void setBanoServicio(Integer banoServicio) {
        this.banoServicio = banoServicio;
    }

    public Integer getPatioInterior() {
        return patioInterior;
    }

    public void setPatioInterior(Integer patioInterior) {
        this.patioInterior = patioInterior;
    }

    public Integer getTerraza() {
        return terraza;
    }

    public void setTerraza(Integer terraza) {
        this.terraza = terraza;
    }

    public Integer getBodega() {
        return bodega;
    }

    public void setBodega(Integer bodega) {
        this.bodega = bodega;
    }

    public Integer getZonaDeRopas() {
        return zonaDeRopas;
    }

    public void setZonaDeRopas(Integer zonaDeRopas) {
        this.zonaDeRopas = zonaDeRopas;
    }

    public Integer getBalcon() {
        return balcon;
    }

    public void setBalcon(Integer balcon) {
        this.balcon = balcon;
    }

    public Integer getCloset() {
        return closet;
    }

    public void setCloset(Integer closet) {
        this.closet = closet;
    }

    public Integer getLocal() {
        return local;
    }

    public void setLocal(Integer local) {
        this.local = local;
    }

    public Integer getZonaVerdePrivada() {
        return zonaVerdePrivada;
    }

    public void setZonaVerdePrivada(Integer zonaVerdePrivada) {
        this.zonaVerdePrivada = zonaVerdePrivada;
    }

    public Integer getIluminacion() {
        return iluminacion;
    }

    public void setIluminacion(Integer iluminacion) {
        this.iluminacion = iluminacion;
    }

    public Integer getVentilacion() {
        return ventilacion;
    }

    public void setVentilacion(Integer ventilacion) {
        this.ventilacion = ventilacion;
    }

    public Integer getGarajePrivado() {
        return garajePrivado;
    }

    public void setGarajePrivado(Integer garajePrivado) {
        this.garajePrivado = garajePrivado;
    }

    public Integer getGarajeExclusivo() {
        return garajeExclusivo;
    }

    public void setGarajeExclusivo(Integer garajeExclusivo) {
        this.garajeExclusivo = garajeExclusivo;
    }

    public Integer getBahiaComunal() {
        return bahiaComunal;
    }

    public void setBahiaComunal(Integer bahiaComunal) {
        this.bahiaComunal = bahiaComunal;
    }

    public Boolean getGarajeDoble1() {
        return garajeDoble1;
    }

    public void setGarajeDoble1(Boolean garajeDoble1) {
        this.garajeDoble1 = garajeDoble1;
    }

    public Boolean getGarajeDoble2() {
        return garajeDoble2;
    }

    public void setGarajeDoble2(Boolean garajeDoble2) {
        this.garajeDoble2 = garajeDoble2;
    }

    public Boolean getGarajeDoble3() {
        return garajeDoble3;
    }

    public void setGarajeDoble3(Boolean garajeDoble3) {
        this.garajeDoble3 = garajeDoble3;
    }

    public Boolean getGarajeDoble4() {
        return garajeDoble4;
    }

    public void setGarajeDoble4(Boolean garajeDoble4) {
        this.garajeDoble4 = garajeDoble4;
    }

    public Boolean getGarajeDoble5() {
        return garajeDoble5;
    }

    public void setGarajeDoble5(Boolean garajeDoble5) {
        this.garajeDoble5 = garajeDoble5;
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

    public Integer getTotalCuposParquedaro() {
        return totalCuposParquedaro;
    }

    public void setTotalCuposParquedaro(Integer totalCuposParquedaro) {
        this.totalCuposParquedaro = totalCuposParquedaro;
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

    public Integer getEstadoAcabadosMadera() {
        return estadoAcabadosMadera;
    }

    public void setEstadoAcabadosMadera(Integer estadoAcabadosMadera) {
        if (new Integer(0).equals(estadoAcabadosMadera)) {
            estadoAcabadosMadera = null;
        }
        this.estadoAcabadosMadera = estadoAcabadosMadera;
    }

    public Integer getEstadoAcabadosMetal() {
        return estadoAcabadosMetal;
    }

    public void setEstadoAcabadosMetal(Integer estadoAcabadosMetal) {
        if (new Integer(0).equals(estadoAcabadosMetal)) {
            estadoAcabadosMetal = null;
        }
        this.estadoAcabadosMetal = estadoAcabadosMetal;
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

    public Integer getCalidadAcabadosPisos() {
        return calidadAcabadosPisos;
    }

    public void setCalidadAcabadosPisos(Integer calidadAcabadosPisos) {
        if (new Integer(0).equals(calidadAcabadosPisos)) {
            calidadAcabadosPisos = null;
        }
        this.calidadAcabadosPisos = calidadAcabadosPisos;
    }

    public Integer getCalidadAcabadosMuros() {
        return calidadAcabadosMuros;
    }

    public void setCalidadAcabadosMuros(Integer calidadAcabadosMuros) {
        if (new Integer(0).equals(calidadAcabadosMuros)) {
            calidadAcabadosMuros = null;
        }
        this.calidadAcabadosMuros = calidadAcabadosMuros;
    }

    public Integer getCalidadAcabadosTechos() {
        return calidadAcabadosTechos;
    }

    public void setCalidadAcabadosTechos(Integer calidadAcabadosTechos) {
        if (new Integer(0).equals(calidadAcabadosTechos)) {
            calidadAcabadosTechos = null;
        }
        this.calidadAcabadosTechos = calidadAcabadosTechos;
    }

    public Integer getCalidadAcabadosMadera() {
        return calidadAcabadosMadera;
    }

    public void setCalidadAcabadosMadera(Integer calidadAcabadosMadera) {
        if (new Integer(0).equals(calidadAcabadosMadera)) {
            calidadAcabadosMadera = null;
        }
        this.calidadAcabadosMadera = calidadAcabadosMadera;
    }

    public Integer getCalidadAcabadosMetal() {
        return calidadAcabadosMetal;
    }

    public void setCalidadAcabadosMetal(Integer calidadAcabadosMetal) {
        if (new Integer(0).equals(calidadAcabadosMetal)) {
            calidadAcabadosMetal = null;
        }
        this.calidadAcabadosMetal = calidadAcabadosMetal;
    }

    public Integer getCalidadAcabadosBanos() {
        return calidadAcabadosBanos;
    }

    public void setCalidadAcabadosBanos(Integer calidadAcabadosBanos) {
        if (new Integer(0).equals(calidadAcabadosBanos)) {
            calidadAcabadosBanos = null;
        }
        this.calidadAcabadosBanos = calidadAcabadosBanos;
    }

    public Integer getCalidadAcabadosCocina() {
        return calidadAcabadosCocina;
    }

    public void setCalidadAcabadosCocina(Integer calidadAcabadosCocina) {
        if (new Integer(0).equals(calidadAcabadosCocina)) {
            calidadAcabadosCocina = null;
        }
        this.calidadAcabadosCocina = calidadAcabadosCocina;
    }

    public Boolean getSometidoAPropiedadHorizontal() {
        return sometidoAPropiedadHorizontal;
    }

    public void setSometidoAPropiedadHorizontal(
            Boolean sometidoAPropiedadHorizontal) {
        this.sometidoAPropiedadHorizontal = sometidoAPropiedadHorizontal;
    }

    public Boolean getConjuntoCerrado() {
        return conjuntoCerrado;
    }

    public void setConjuntoCerrado(Boolean conjuntoCerrado) {
        this.conjuntoCerrado = conjuntoCerrado;
    }

    public Integer getUbicacionDelInmueble() {
        return ubicacionDelInmueble;
    }

    public Integer getNumeroPiso() {
		return numeroPiso;
	}

	public void setNumeroPiso(Integer numeroPiso) {
		this.numeroPiso = numeroPiso;
	}

	public void setUbicacionDelInmueble(Integer ubicacionDelInmueble) {
        this.ubicacionDelInmueble = ubicacionDelInmueble;
    }

    public Integer getNumeroDeEdificios() {
        return numeroDeEdificios;
    }

    public void setNumeroDeEdificios(Integer numeroDeEdificios) {
        this.numeroDeEdificios = numeroDeEdificios;
    }

    public Integer getUnidadesPorPiso() {
        return unidadesPorPiso;
    }

    public void setUnidadesPorPiso(Integer unidadesPorPiso) {
        this.unidadesPorPiso = unidadesPorPiso;
    }

    public Integer getTotalUnidades() {
        return totalUnidades;
    }

    public void setTotalUnidades(Integer totalUnidades) {
        this.totalUnidades = totalUnidades;
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

    public void setComportamientoOfertaDemanda(
            String comportamientoOfertaDemanda) {
        this.comportamientoOfertaDemanda = comportamientoOfertaDemanda;
    }

    public Double getTiempoEsperadoDeComercializacion() {
        return tiempoEsperadoDeComercializacion;
    }

    public void setTiempoEsperadoDeComercializacion(
            Double tiempoEsperadoDeComercializacion) {
        this.tiempoEsperadoDeComercializacion = tiempoEsperadoDeComercializacion;
    }

    public BigDecimal getPorcentajeTerreno() {
        return porcentajeTerreno;
    }

    public void setPorcentajeTerreno(BigDecimal porcentajeTerreno) {
        this.porcentajeTerreno = porcentajeTerreno;
    }

    public BigDecimal getValorProporcionalTerreno() {
        return valorProporcionalTerreno;
    }

    public void setValorProporcionalTerreno(BigDecimal valorProporcionalTerreno) {
        this.valorProporcionalTerreno = valorProporcionalTerreno;
    }

    public BigDecimal getValorProporcionalConstruccion() {
        return valorProporcionalConstruccion;
    }

    public void setValorProporcionalConstruccion(
            BigDecimal valorProporcionalConstruccion) {
        this.valorProporcionalConstruccion = valorProporcionalConstruccion;
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

	public Integer getTipoLicencia() {
        return tipoLicencia;
    }

    public void setTipoLicencia(Integer tipoLicencia) {
        this.tipoLicencia = tipoLicencia;
    }

    public String getNumeroLicencia() {
        return numeroLicencia;
    }

    public void setNumeroLicencia(String numeroLicencia) {
        this.numeroLicencia = numeroLicencia;
    }

    public Integer getReparados() {
        return reparados;
    }

    public void setReparados(Integer reparados) {
        this.reparados = reparados;
    }

    public Integer getDanosPrevios() {
        return danosPrevios;
    }

    public void setDanosPrevios(Integer danosPrevios) {
        this.danosPrevios = danosPrevios;
    }

    public Integer getMaterialConstructor() {
        return materialConstructor;
    }

    public void setMaterialConstructor(Integer materialConstructor) {
        this.materialConstructor = materialConstructor;
    }

    public Integer getDetalleMaterial() {
        return detalleMaterial;
    }

    public void setDetalleMaterial(Integer detalleMaterial) {
        this.detalleMaterial = detalleMaterial;
    }

    public Integer getIrregularidadPlanta() {
        return irregularidadPlanta;
    }

    public void setIrregularidadPlanta(Integer irregularidadPlanta) {
        this.irregularidadPlanta = irregularidadPlanta;
    }

    public Integer getIrregularidadAltura() {
        return irregularidadAltura;
    }

    public void setIrregularidadAltura(Integer irregularidadAltura) {
        this.irregularidadAltura = irregularidadAltura;
    }

    public BigDecimal getCoeficiente() {
        return coeficiente;
    }

    public void setCoeficiente(BigDecimal coeficiente) {
        this.coeficiente = coeficiente;
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

    public String getDescUsoPredominanteInmuebleMixto() {
        return descUsoPredominanteInmuebleMixto;
    }

    public void setDescUsoPredominanteInmuebleMixto(
            String descUsoPredominanteInmuebleMixto) {
        this.descUsoPredominanteInmuebleMixto = descUsoPredominanteInmuebleMixto;
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
	
}
