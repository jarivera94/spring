package com.helio4.bancol.avaluos.servicio;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.helio4.bancol.avaluos.servicio.datos.FotografiaService;
import com.helio4.bancol.avaluos.servicio.datos.ParametroService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.supercsv.cellprocessor.FmtDate;
import org.supercsv.cellprocessor.FmtNumber;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.Truncate;
import org.supercsv.cellprocessor.constraint.Equals;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.helio4.bancol.avaluos.dto.AvaluoHipotecarioDTO;
import com.helio4.bancol.avaluos.dto.FotografiaDTO;
import com.helio4.bancol.avaluos.dto.ParametroDTO;
import com.helio4.bancol.avaluos.ensamblador.ArchivoBUAEnsamblador;
import com.helio4.bancol.avaluos.modelo.ArchivoBUA;
import com.helio4.bancol.avaluos.servicio.util.Zip;

@Component(value="csvBUAService")
public class CsvBUAService implements BUAService {

    private static Logger log = LoggerFactory.getLogger( CsvBUAService.class );

    private static final CsvPreference PIPE_DELIMITED = new CsvPreference.Builder('"', '|', "\n").build();
    public static final int MAX_LINES_PER_FILE = 20;

    @Autowired
    private ArchivoBUAEnsamblador archivoBUAEnsamblador;

    @Autowired
    private ParametroService parametroService;

    @Autowired
    private FotografiaService fotografiaService;

    /**
     * Sets up the processors used for the examples. There are x CSV columns, so x processors are defined. All values
     * are converted to Strings before writing (there's no need to convert them), and null values will be written as
     * empty columns (no need to convert them to "").
     *
     * @return the cell processors
     */
    private static CellProcessor[] getProcessors() {
    	 
    	
        DecimalFormat decimalFormat = new DecimalFormat("#0.0000");
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
    	simbolos.setDecimalSeparator('.');
    	decimalFormat.setDecimalFormatSymbols(simbolos);
    	decimalFormat.setDecimalSeparatorAlwaysShown(true);

        final CellProcessor[] processors = new CellProcessor[] {
            new NotNull(), //1 C_ANDENES andenes_en_las_vias
                new Optional(), //2 C_CALIDBANIO calidad_acabados_banos
                new Optional(), //3 C_CALIDCMADER calidad_acabados_madera
                new Optional(), //4 C_CALIDCMETAL calidad_acabados_metal
                new Optional(), //5 C_CALIDCOCINA calidad_acabados_cocina
                new Optional(), //6 C_CALIDMURO calidad_acabados_muros
                new Optional(), //7 C_CALIDPISO calidad_acabados_pisos
                new Optional(), //8 C_CALIDTECHO calidad_acabados_techos
                new NotNull(), //9 C_CIUDADESCRITURA ciudad_notaria
                new Optional(), //10 C_CLASEINMUEBLE clase_inmueble
                new Optional(), //11 C_CONJAGRUPCERR conjunto_cerrado
                new Optional(), //12 C_CUBIERTA cubierta
                new NotNull(), //13 C_DEPTOESCRITURA departamento_notaria
                new Optional(), //14 C_DESCRIPLIQ1 DESCRIPCION 1
                new Optional(), //15 C_DESCRIPLIQ10 DESCRIPCION 10
                new Optional(), //16 C_DESCRIPLIQ2 DESCRIPCION 2
                new Optional(), //17 C_DESCRIPLIQ3 DESCRIPCION 3
                new Optional(), //18 C_DESCRIPLIQ4 DESCRIPCION 4
                new Optional(), //19 C_DESCRIPLIQ5 DESCRIPCION 5
                new Optional(), //20 C_DESCRIPLIQ6 DESCRIPCION 6
                new Optional(), //21 C_DESCRIPLIQ7 DESCRIPCION 7
                new Optional(), //22 C_DESCRIPLIQ8 DESCRIPCION 8
                new Optional(), //23 C_DESCRIPLIQ9 DESCRIPCION 9
                new Optional(), //24 C_ESTBANIOS estado_acabados_banos
                new Optional(), //25 C_ESTCOCINA estado_acabados_cocina
                new Optional(), //26 C_ESTCONSERVACION estado_de_conservacion
                new Optional(), //27 C_ESTMADERA estado_acabados_madera
                new Optional(), //28 C_ESTMETAL estado_acabados_metal
                new Optional(), //29 C_ESTMUROS estado_acabados_muros
                new Optional(), //30 C_ESTPISOS estado_acabados_pisos
                new NotNull(), //31 C_ESTRATO estrato
                new Optional(), //32 C_ESTRUCTURA estructura
                new Optional(), //33 C_ESTTECHOS estado_acabados_techos
                new Optional(), //34 C_ESTVIAACCESO estado_de_las_vias
                new Optional(), //35 C_FACHADA fachada
                new NotNull(), //36 C_IDCIUDAD ciudad
                new NotNull(), //37 C_IDDEPARTAMENTO departamento
                new NotNull(), //38 C_IDMETODOLOGIA metodo_de_valuacion
                new NotNull(), //39 C_IDOBJETOAVALUO objeto_del_avaluo
                new NotNull(), //40 C_IDTIPOIDENTIFICACION cliente_tipo_documento
                new Optional(), //41 C_ILUMINACION iluminacion
                new NotNull(), //42 C_LEGALIDAD barrio_legal
                new NotNull(), //43 C_PAVIMENTADA vias_pavimentadas
                new Optional(), //44 C_PISOSBODEGA pisos_bodega
                new NotNull(), //45 C_PROPHORZ sometido_a_propiedad_horizontal
                new Optional(), //46 C_SALUBRIDAD condiciones_de_salubridad
                new NotNull(), //47 C_SARDENELES sardineles_en_las_vias
                new Optional(), //48 C_TIPOFACHADA tipo_fachada
                new Optional(new Truncate(30)), //49 C_TIPOVIVIENDA tipo_de_vivienda
                new NotNull(), //50 C_TOPOGRAFIA topografia_sector
                new NotNull(), //51 C_TRANSPORTE transporte
                new Optional(), //52 C_UBICACIONINM ubicacion_del_inmueble
                new Optional(), //53 C_UBICACION2 Ubicación
                new NotNull(), //54 C_USOINMUEBLE uso_predominante_inmueble
                new Optional(), //55 C_VENTILACION ventilacion
                new FmtDate("dd-MM-yyyy HH:mm:ss"), //56 F_FECHAAVALUO Fecha Avalúo
                new FmtDate("dd-MM-yyyy HH:mm:ss"), //57 F_FECHAESCRITURA fecha_de_escritura
                new Optional(), //58 K_AACOND aire_acondicionado_central
                new Optional(), //59 K_ACUEDUCTOPREDIO acueducto_en_el_predio
                new Optional(), //60 K_ACUEDUCTOSECTOR acueducto_en_el_sector
                new Optional(), //61 K_AGUASHERV impacto_ambiental_aguas_servidas
                new Optional(), //62 K_ALAMEDAS alamedas
                new Optional(), //63 K_ALCANTAPREDIO alcantarillado_en_el_predio
                new Optional(), //64 K_ALCANTASECTOR alcantarillado_en_el_sector
                new Optional(), //65 K_ALUMBRADO alumbrado
                new Optional(), //66 K_AMBARBORIZA Arborización
                new Optional(), //67 K_AMBPARQUES Parques
                new Optional(), //68 K_AMBZVERDE Z. Verdes
                new Optional(), //69 K_ARBORIZACION arborizacion
                new Optional(), //70 K_ASCENSOR ascensor
                new Optional(), //71 K_BASURA impacto_ambiental_basura
                new Optional(), //72 K_BICICLETERO bicicletero
                new Optional(), //73 K_BOMBA bomba_eyectora
                new Optional(), //74 K_CANCHAMULT cancha_multiple
                new Optional(), //75 K_CICLORUTAS ciclo_rutas
                new Optional(), //76 K_CITOFONO citofono
                new Optional(), //77 K_CLUBHOUSE club_house
                new Optional(), //78 K_COMERCIO Comercio
                new Optional(), //79 K_ELECTRICAPREDIO energia_en_el_predio
                new Optional(), //80 K_ELECTRICASECTOR energia_en_el_sector
                new Optional(), //81 K_ENOBRA estado_de_obra
                new Optional(), //82 K_ESTREMODELA remodelado
                new Optional(), //83 K_ESTTERMINADA Terminada
                new Optional(), //84 K_ESTTERMINADO Terminado
                new Optional(), //85 K_GASPREDIO gas_en_el_predio
                new Optional(), //86 K_GASSECTOR gas_en_el_sector
                new Optional(), //87 K_GIMNASIO gimnasio
                new Optional(), //88 K_GJVISITA garaje_visitantes
                new Optional(), //89 K_GOLFITO golfito
                new Optional(), //90 K_INDUSTRIA Industria
                new Optional(), //91 K_INSERGURIDAD impacto_ambiental_inseguridad
                new Optional(), //92 K_JUEGONINOS juegos_ninos
                new Optional(), //93 K_OTROSUSOS Otros Usos
                new Optional(), //94 K_PARADERO paradero
                new Optional(), //95 K_PARQUES parques
                new Optional(), //96 K_PISCINA piscina
                new Optional(), //97 K_PLANTA planta_electrica
                new Optional(), //98 K_PORAIRE impacto_ambiental_aire
                new Optional(), //99 K_PORTERIA porteria
                new Optional(), //100 K_PRESION equipo_de_presion_constante
                new Optional(), //101 K_RUIDO impacto_ambiental_ruido
                new Optional(), //102 K_SALONCOMN salon_comunal
                new Optional(), //103 K_SHUT shut_basuras
                new Optional(), //104 K_SINTERMINAR Sin Terminar
                new Optional(), //105 K_SQUASH cancha_squash
                new Optional(), //106 K_TANQUEAGUA tanque_de_agua
                new Optional(), //107 K_TELPREDIO telefono_en_el_predio
                new Optional(), //108 K_TELSECTOR telefono_en_el_sector
                new Optional(), //109 K_VIVIENDA Vivienda
                new Optional(), //110 K_ZONASVERDES zonas_verdes_publicas
                new Optional(), //111 K_ZVERDES zonas_verdes_comunales
                new Optional(new FmtNumber(decimalFormat)), //112 N_AREALIQ1 Area(m2) 1
                new Optional(new FmtNumber(decimalFormat)), //113 N_AREALIQ10 Area(m2) 10
                new Optional(new FmtNumber(decimalFormat)), //114 N_AREALIQ2 Area(m2) 2
                new Optional(new FmtNumber(decimalFormat)), //115 N_AREALIQ3 Area(m2) 3
                new Optional(new FmtNumber(decimalFormat)), //116 N_AREALIQ4 Area(m2) 4
                new Optional(new FmtNumber(decimalFormat)), //117 N_AREALIQ5 Area(m2) 5
                new Optional(new FmtNumber(decimalFormat)), //118 N_AREALIQ6 Area(m2) 6
                new Optional(new FmtNumber(decimalFormat)), //119 N_AREALIQ7 Area(m2) 7
                new Optional(new FmtNumber(decimalFormat)), //120 N_AREALIQ8 Area(m2) 8
                new Optional(new FmtNumber(decimalFormat)), //121 N_AREALIQ9 Area(m2) 9
                new NotNull(new FmtNumber(decimalFormat)), //122 N_AVALUOUVR valor_avaluo_en_uvr
                new Optional(), //123 N_BAHIACOMUNAL bahia_comunal
                new Optional(), //124 N_BALCON balcon
                new Optional(), //125 N_BANIOPRIVADO bano_privado
                new Optional(), //126 N_BANIOSERVICIO bano_servicio
                new Optional(), //127 N_BANIOSOCIAL bano_social
                new Optional(), //128 N_BODEGA bodega
                new Optional(), //129 N_COCINA cocina
                new Optional(), //130 N_COMEDOR comedor
                new NotNull(), //131 AVALUO_ID avaluoId
                new Optional(), //132 N_CUARTOSERV cuarto_servicio
                new Optional(), //133 N_CUBIERTO Cubierto
                new Optional(), //134 N_DEPOSITO Depósito
                new Optional(), //135 N_DESCUBIERTO Descubierto
                new Optional(), //136 N_DOBLE Doble
                new Optional(), //137 N_ESTARHAB Estar Hab.
                new Optional(), //138 N_ESTUDIO estudio
                new Optional(), //139 N_HABITACIONES habitaciones
                new NotNull(), //140 N_IDENTIFICACION cliente_numero_documento
                new Optional(), //141 N_JARDIN jardin
                new Optional(), //142 N_LOCAL local
                new Optional(), //143 N_NUMASCENSORES numero_de_ascensores
                new NotNull(new Truncate(2)), //144 N_NUMEDIF numero_de_edificios
                new Optional(), //145 N_OFICINA oficina
                new Optional(), //146 N_PATIOINT patio_interior
                new Optional(), //147 N_PISOS numero_de_pisos
                new Optional(), //148 N_PRIVADO Privado
                new Optional(), //149 N_SALA sala
                new Optional(), //150 N_SENCILLO Sencillo
                new Optional(), //151 N_SERVIDUMBRE Servidumbre
                new Optional(), //152 N_SOTANOS numero_sotanos
                new Optional(), //153 N_TERRAZA terraza
                new NotNull(), //154 N_TIPOCOMERCIALIZA tiempo_esperado_de_comercializacion
                new NotNull(new FmtNumber(decimalFormat)), //155 N_TOTALAVALUO valor_total_avaluo
                new Optional(), //156 N_TOTALGARAJES numero_total_de_garajes
                new Optional(), //157 N_TOTALUND total_unidades
                new Optional(), //158 N_UNDPISO unidades_por_piso
                new Optional(), //159 N_USOEXCLUSIVO Uso Exclusivo
                new Optional(new FmtNumber(decimalFormat)), //160 N_VALORASEGURABLE valor_asegurable
                new Optional(new FmtNumber(decimalFormat)), //161 N_VALTOT1 Valor Total 1
                new Optional(new FmtNumber(decimalFormat)), //162 N_VALTOT10 Valor Total 10
                new Optional(new FmtNumber(decimalFormat)), //163 N_VALTOT2 Valor Total 2
                new Optional(new FmtNumber(decimalFormat)), //164 N_VALTOT3 Valor Total 3
                new Optional(new FmtNumber(decimalFormat)), //165 N_VALTOT4 Valor Total 4
                new Optional(new FmtNumber(decimalFormat)), //166 N_VALTOT5 Valor Total 5
                new Optional(new FmtNumber(decimalFormat)), //167 N_VALTOT6 Valor Total 6
                new Optional(new FmtNumber(decimalFormat)), //168 N_VALTOT7 Valor Total 7
                new Optional(new FmtNumber(decimalFormat)), //169 N_VALTOT8 Valor Total 8
                new Optional(new FmtNumber(decimalFormat)), //170 N_VALTOT9 Valor Total 9
                new NotNull(new FmtNumber(decimalFormat)), //171 N_VALUVRDIA valor_uvr
                new Optional(new FmtNumber(decimalFormat)), //172 N_VAL1 Valor unitario 1
                new Optional(new FmtNumber(decimalFormat)), //173 N_VAL10 Valor unitario 10
                new Optional(new FmtNumber(decimalFormat)), //174 N_VAL2 Valor unitario 2
                new Optional(new FmtNumber(decimalFormat)), //175 N_VAL3 Valor unitario 3
                new Optional(new FmtNumber(decimalFormat)), //176 N_VAL4 Valor unitario 4
                new Optional(new FmtNumber(decimalFormat)), //177 N_VAL5 Valor unitario 5
                new Optional(new FmtNumber(decimalFormat)), //178 N_VAL6 Valor unitario 6
                new Optional(new FmtNumber(decimalFormat)), //179 N_VAL7 Valor unitario 7
                new Optional(new FmtNumber(decimalFormat)), //180 N_VAL8 Valor unitario 8
                new Optional(new FmtNumber(decimalFormat)), //181 N_VAL9 Valor unitario 9
                new Optional(new FmtNumber(decimalFormat)), //182 N_VETUSTEZ vetustez
                new Optional(), //183 N_ZVERDEPRIV zona_verde_privada
                new NotNull(), //184 R_CALIFICACION calificacion_garantia
                new Optional(), //185 R_ESTCONS estado_de_construccion
                new NotNull(new Truncate(500)), //186 T_ACTEDIFICADORA actualidad_edificadora
                new Optional(new Truncate(100)), //187 T_AMBNEGOTRO impacto_ambiental_otro
                new Optional(new Truncate(100)), //188 T_AMBOTRO Otro
                new Optional(), //189 T_AVANCEOBRA porcentaje_avance
                new NotNull(new Truncate(30)), //190 T_BARRIO barrio
                new Optional(new Truncate(20)), //191 T_CHIP chip
                new NotNull(new Truncate(500)), //192 T_COMPORTAOD comportamiento_oferta_demanda
                new Optional(new Truncate(30)), //193 T_CUBIERTA Cubierta
                new Optional(new Truncate(100)), //194 T_DIRANEXOS direccion_anexos
                new Optional(new Truncate(100)), //195 T_DIRINMUEBLE direccion_inmueble
                new Optional(new Truncate(30)), //196 T_ESTRUCTURA Estructura
                new Optional(), //197 T_FACHADA Fachada
                new NotNull(new Truncate(500)), //198 T_JUSTIFICACION justificacion_de_metodologia
                new NotNull(new Truncate(20)), //199 T_MINMBPPAL1 matricula_inmobiliaria_principal_1
                new Optional(new Truncate(20)), //200 T_MINMBPPAL2 matricula_inmobiliaria_principal_2
                new Optional(new Truncate(20)), //201 T_MINMOBDP1 matricula_inmobiliaria_deposito_1
                new Optional(new Truncate(20)), //202 T_MINMOBDP2 matricula_inmobiliaria_deposito_2
                new Optional(new Truncate(20)), //203 T_MINMOBGJ1 matricula_inmobiliaria_garaje_1
                new Optional(new Truncate(20)), //204 T_MINMOBGJ2 matricula_inmobiliaria_garaje_2
                new Optional(new Truncate(20)), //205 T_MINMOBGJ3 matricula_inmobiliaria_garaje_3
                new Optional(new Truncate(20)), //206 T_MINMOBGJ4 matricula_inmobiliaria_garaje_4
                new Optional(new Truncate(20)), //207 T_MINMOBGJ5 matricula_inmobiliaria_garaje_5
                new Optional(new Truncate(30)), //208 T_NOMBCONJEDIF nombre_del_conjunto
                new Optional(), //209 T_NOMBRESOLICITANTE entidad_id
                new NotNull(new Truncate(5)), //210 T_NOTARIA numero_notaria_de_escritura
                new NotNull(new Truncate(5)), //211 T_NUMESCRITURA numero_de_escritura
                new NotNull(new Truncate(2000)), //212 T_OBSERVAVALUO observaciones
                new Optional(new Truncate(100)), //213 T_OTRASDIR otras_direcciones
                new Optional(new Truncate(30)), //214 T_DESCRIPCION_CLASE_INMUEBLE
                new Optional(new Truncate(30)), //215 T_OTROSDOTACION Otros Dotacion
                new Optional(new Truncate(30)), //216 T_OTROSDOTACION2 Otros Dotacion 2
                new Optional(), //217 T_OTROSUSOS Otros Usos
                new Optional(new Truncate(30)), //218 T_DESCRIPCIONOTROUSOINM descripcion_otros_uso_inmueble
                new NotNull(new Truncate(500)), //219 T_PERSPECTIVAS perspectivas_de_valorizacion
                new Optional(), //220 C_ALTURA Altura
                new Optional(), //221 C_UBICACION3 Ubicación Local
                new Optional(new Truncate(20)), //222 T_SISTEMA Otro Sistema de Coordenadas
                new Optional(), //223 C_REPARADOS Reparados
                new Optional(), //224 C_IRRALTURA Irregularidad Altura
                new Optional(), //225 C_IRRPLANTA Irregularidad Planta
                new Optional(), //226 C_ESTRUCTURAREFORZADA estructura_reforzada
                new Equals(3), //227 C_SISTEMA Sistema de Coordenadas
                new Optional(), //228 C_DANOPREVIO Daños Previos
                new Optional(), //229 T_LATITUD latitud
                new Optional(), //230 N_ANOCONSTRUCCION ano_de_construccion
                new Optional(), //231 C_MATERIAL Material de Construcción
                new Optional(), //232 T_LONGITUD longitud
                new Optional(), //233 C_DETALLEMATERIAL ESTRUCTURA
                new NotNull(new Truncate(30)), //234 IDCATEGORIA CATEGORIA (Tipo de inmueble)
                new Equals("830006973") //235 T_USUARIO Usuario Avalúo
        };
        return processors;
    }


    public String exportartArchivo(final List<AvaluoHipotecarioDTO> avaluosHipotecariosDTOs, String nombreArchivo) {
        log.debug("Escribiendo archivo bua de los avaluos  {}",
                avaluosHipotecariosDTOs);
        ICsvBeanWriter beanWriter = null;
        File file = null;
        String zipFile = null;
        try {
            ParametroDTO parametroImagenes = parametroService.encontrarPorNombre("rutaImagenes");
            ParametroDTO parametroBUA = parametroService.encontrarPorNombre("ruta_BUA");
            int consecutivo = 1;
            file = new File(parametroImagenes.getValor()+File.separator+nombreArchivo+"_0"+consecutivo+ArchivoBUA.EXTENSION_ARCHIVO_BUA);
            while(file.exists()){
            	consecutivo += 1;
            	file = new File(parametroImagenes.getValor()+File.separator+nombreArchivo+"_0"+consecutivo+ArchivoBUA.EXTENSION_ARCHIVO_BUA);
            }
            beanWriter = new CsvBeanWriter(new FileWriter(file),
                    PIPE_DELIMITED);

            // the header elements are used to map the bean values to each column (names must match)
            final String[] header = new String[] { "andenesEnLasVias",
                "calidadAcabadosBanos", "calidadAcabadosMadera",
                "calidadAcabadosMetal", "calidadAcabadosCocina",
                "calidadAcabadosMuros", "calidadAcabadosPisos",
                "calidadAcabadosTechos", "ciudadNotaria", "claseInmueble",
                "conjuntoCerrado", "cubierta", "departamentoNotaria",
                "descripcion1", "descripcion10", "descripcion2",
                "descripcion3", "descripcion4", "descripcion5",
                "descripcion6", "descripcion7", "descripcion8",
                "descripcion9", "estadoAcabadosBanos",
                "estadoAcabadosCocina", "estadoDeConservacion",
                "estadoAcabadosMadera", "estadoAcabadosMetal",
                "estadoAcabadosMuros", "estadoAcabadosPisos", "estrato",
                "estructura", "estadoAcabadosTechos", "estadoDeLasVias",
                "fachada", "ciudad", "departamento",
                "metodoDeValuacion", "objetoDelAvaluo",
                "clienteTipoDocumento", "iluminacion", "barrioLegal",
                "viasPavimentadas", "pisosBodega",
                "sometidoAPropiedadHorizontal", "condicionesDeSalubridad",
                "sardinelesEnLasVias", "tipoFachada", "tipoDeVivienda",
                "topografiaSector", "transporte", "ubicacionDelInmueble",
                "ubicacion", "usoPredominanteInmueble", "ventilacion",
                "fechaAvaluo", "fechaDeEscritura",
                "aireAcondicionadoCentral", "acueductoEnElPredio",
                "acueductoEnElSector", "impactoAmbientalAguasServidas",
                "alamedas", "alcantarilladoEnElPredio",
                "alcantarilladoEnElSector", "ambientalAlumbrado",
                "ambientalArborizacion", "ambientalParques",
                "ambientalZonasVerdes", "arborizacion", "ascensor",
                "impactoAmbientalBasura", "bicicletero", "bombaEyectora",
                "canchaMultiple", "cicloRutas", "citofono",
                "clubHouse", "comercio", "energiaEnElPredio",
                "energiaEnElSector", "estadoDeObra", "remodelado",
                "terminada", "terminado", "gasEnElPredio",
                "gasEnElSector", "gimnasio", "garajeVisitantes",
                "golfito", "industria", "impactoAmbientalInseguridad",
                "juegosNinos", "otrosUsos", "paradero",
                "parques", "piscina", "plantaElectrica",
                "impactoAmbientalAire", "porteria",
                "equipoDePresionConstante", "impactoAmbientalRuido",
                "salonComunal", "shutBasuras", "sinTerminar",
                "canchaSquash", "tanqueDeAgua", "telefonoEnElPredio",
                "telefonoEnElSector", "vivienda", "zonasVerdesPublicas",
                "zonasVerdesComunales", "area1", "area10",
                "area2", "area3", "area4", "area5",
                "area6", "area7", "area8", "area9",
                "valorAvaluoEnUvr", "bahiaComunal", "balcon",
                "banoPrivado", "banoServicio", "banoSocial",
                "bodega", "cocina", "comedor",
                "avaluoId", "cuartoServicio", "cubierto",
                "deposito", "descubierto", "doble",
                "estarHab", "estudio", "habitaciones",
                "clienteNumeroDocumento", "jardin", "local",
                "numeroDeAscensores", "numeroDeEdificios", "oficina",
                "patioInterior", "numeroDePisos", "privado",
                "sala", "sencillo", "servidumbre",
                "numeroSotanos", "terraza",
                "tiempoEsperadoDeComercializacion", "valorTotalAvaluo",
                "numeroTotalDeGarajes", "totalUnidades",
                "unidadesPorPiso", "usoExclusivo", "valorAsegurable",
                "valorTotal1", "valorTotal10", "valorTotal2",
                "valorTotal3", "valorTotal4", "valorTotal5",
                "valorTotal6", "valorTotal7", "valorTotal8",
                "valorTotal9", "valorUvr", "valorUnitario1",
                "valorUnitario10", "valorUnitario2", "valorUnitario3",
                "valorUnitario4", "valorUnitario5", "valorUnitario6",
                "valorUnitario7", "valorUnitario8", "valorUnitario9",
                "vetustez", "zonaVerdePrivada", "calificacionGarantia",
                "estadoDeConstruccion", "actualidadEdificadora",
                "impactoAmbientalOtro", "otro", "porcentajeAvance",
                "barrio", "chip", "comportamientoOfertaDemanda",
                "tipoCubierta", "direccionAnexos", "direccionInmueble",
                "tipoEstructura", "tipoFachada2",
                "justificacionDeMetodologia", "matriculaInmobiliariaPrincipal1",
                "matriculaInmobiliariaPrincipal2", "matriculaInmobiliariaDeposito1",
                "matriculaInmobiliariaDeposito2", "matriculaInmobiliariaGaraje1",
                "matriculaInmobiliariaGaraje2", "matriculaInmobiliariaGaraje3",
                "matriculaInmobiliariaGaraje4", "matriculaInmobiliariaGaraje5",
                "nombreDelConjunto", "nombreSolicitante",
                "numeroNotariaDeEscritura", "numeroDeEscritura",
                "observaciones", "otrasDirecciones", "descripcionClaseInmueble",
                "otrosDotacion", "otrosDotacion2", "textoOtrosUsos",
                "descripcionOtrosUsoInmueble", "perspectivasDeValorizacion",
                "altura", "ubicacionLocal", "otroSistemaDeCoordenadas",
                "reparados", "irregularidadAltura", "irregularidadPlanta",
                "estructuraReforzada", "sistemaDeCoordenadas",
                "danosPrevios", "latitud", "anoDeConstruccion",
                "materialDeConstruccion", "longitud", "tipoEstructural",
                "categoria", "usuario" };
            final CellProcessor[] processors = getProcessors();

            List<String> srcFiles = new ArrayList<String>();

            for (AvaluoHipotecarioDTO avaluoHipotecarioDTO:avaluosHipotecariosDTOs) {
                List<FotografiaDTO> fotografias = fotografiaService.buscarFotografiasAvaluo(avaluoHipotecarioDTO.getId());

                for(FotografiaDTO foto:fotografias){
                    srcFiles.add(foto.getRutaUbicacion());
                }

                beanWriter.write(archivoBUAEnsamblador.crearArchivoBUA(avaluoHipotecarioDTO), header, processors);
            }

            // write the beans
            beanWriter.close();
            srcFiles.add(file.getAbsolutePath());

            SimpleDateFormat sm = new SimpleDateFormat("ddMMyyyy");

            String fecha = sm.format(new Date());
            File fileZip = new File(parametroBUA.getValor()+File.separator+"CBUA001007"+fecha+".zip");
            zipFile = fileZip.getAbsolutePath();
            Zip.compressFiles(srcFiles, zipFile);

        } catch (IOException e) {
            log.error("IOExeption: error escribiendo archivo BUA {}",
                    nombreArchivo, e);
        }
        finally {
            if( beanWriter != null ) {
                try {
                    beanWriter.close();
                } catch (IOException e) {
                    log.error("IOExeption: error cerrando escritura de archivo BUA {}",
                            nombreArchivo, e);
                }
            }
        }
        return zipFile == null ? null : zipFile;
    }

    @Override
    public String exportartCsvBUA(
            List<AvaluoHipotecarioDTO> avaluosHipotecariosDTOs,
            String consecutivo) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYYMMdd");
        String nombreArchivo = ArchivoBUA.NOMBRE_ARCHIVO_BUA + dateFormat.format(new Date(System.currentTimeMillis())) + "_" + consecutivo;
        return exportartArchivo(avaluosHipotecariosDTOs, nombreArchivo);
    }



    @Override
    public String exportartCsvBUA(
            List<AvaluoHipotecarioDTO> avaluosHipotecariosDTOs) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYYMMdd");
        String nombreArchivo = ArchivoBUA.NOMBRE_ARCHIVO_BUA + dateFormat.format(new Date(System.currentTimeMillis()));
        return exportartArchivo(avaluosHipotecariosDTOs, nombreArchivo);
    }

}
