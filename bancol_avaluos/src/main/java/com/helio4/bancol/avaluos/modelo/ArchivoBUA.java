		package com.helio4.bancol.avaluos.modelo;

import java.math.BigDecimal;
import java.util.Date;

import com.helio4.bancol.avaluos.servicio.util.Constantes;

public class ArchivoBUA {

	public static final Integer SISTEMA_COORDENADAS = 3;
	public static final String USUARIO_BUA = "830006973";
	public static final String NOMBRE_ARCHIVO_BUA = "INMUEBLE_";
	public static final String EXTENSION_ARCHIVO_BUA = ".txt";

	private Integer andenesEnLasVias; //1 C_ANDENES
	private Integer calidadAcabadosBanos; //2 C_CALIDBANIO
	private Integer calidadAcabadosMadera; //3 C_CALIDCMADER
	private Integer calidadAcabadosMetal; //4 C_CALIDCMETAL
	private Integer calidadAcabadosCocina; //5 C_CALIDCOCINA
	private Integer calidadAcabadosMuros; //6 C_CALIDMURO
	private Integer calidadAcabadosPisos; //7 C_CALIDPISO
	private Integer calidadAcabadosTechos; //8 C_CALIDTECHO
	private Integer ciudadNotaria; //9 C_CIUDADESCRITURA
	private Integer claseInmueble; //10 C_CLASEINMUEBLE
	private Integer conjuntoCerrado; //11 C_CONJAGRUPCERR
	private Integer cubierta; //12 C_CUBIERTA
	private Integer departamentoNotaria; //13 C_DEPTOESCRITURA
	private Integer descripcion1; //14 C_DESCRIPLIQ1
	private Integer descripcion10; //15 C_DESCRIPLIQ10
	private Integer descripcion2; //16 C_DESCRIPLIQ2
	private Integer descripcion3; //17 C_DESCRIPLIQ3
	private Integer descripcion4; //18 C_DESCRIPLIQ4
	private Integer descripcion5; //19 C_DESCRIPLIQ5
	private Integer descripcion6; //20 C_DESCRIPLIQ6
	private Integer descripcion7; //21 C_DESCRIPLIQ7
	private Integer descripcion8; //22 C_DESCRIPLIQ8
	private Integer descripcion9; //23 C_DESCRIPLIQ9
	private Integer estadoAcabadosBanos; //24 C_ESTBANIOS
	private Integer estadoAcabadosCocina; //25 C_ESTCOCINA
	private Integer estadoDeConservacion; //26 C_ESTCONSERVACION
	private Integer estadoAcabadosMadera; //27 C_ESTMADERA
	private Integer estadoAcabadosMetal; //28 C_ESTMETAL
	private Integer estadoAcabadosMuros; //29 C_ESTMUROS
	private Integer estadoAcabadosPisos; //30 C_ESTPISOS
	private Integer estrato; //31 C_ESTRATO
	private Integer estructura; //32 C_ESTRUCTURA
	private Integer estadoAcabadosTechos; //33 C_ESTTECHOS
	private Integer estadoDeLasVias; //34 C_ESTVIAACCESO
	private Integer fachada; //35 C_FACHADA
	private Integer ciudad; //36 C_IDCIUDAD
	private Integer departamento; //37 C_IDDEPARTAMENTO
	private Integer metodoDeValuacion; //38 C_IDMETODOLOGIA
	private Integer objetoDelAvaluo; //39 C_IDOBJETOAVALUO
	private Integer clienteTipoDocumento; //40 C_IDTIPOIDENTIFICACION
	private Integer iluminacion; //41 C_ILUMINACION
	private Integer barrioLegal; //42 C_LEGALIDAD
	private Integer viasPavimentadas; //43 C_PAVIMENTADA
	private Integer pisosBodega; //44 C_PISOSBODEGA
	private Integer sometidoAPropiedadHorizontal; //45 C_PROPHORZ
	private Integer condicionesDeSalubridad; //46 C_SALUBRIDAD
	private Integer sardinelesEnLasVias; //47 C_SARDENELES
	private Integer tipoFachada; //48 C_TIPOFACHADA
	private Integer tipoDeVivienda; //49 C_TIPOVIVIENDA
	private Integer topografiaSector; //50 C_TOPOGRAFIA
	private Integer transporte; //51 C_TRANSPORTE
	private Integer ubicacionDelInmueble; //52 C_UBICACIONINM
	private Integer ubicacion; //53 C_UBICACION2
	private Integer usoPredominanteInmueble; //54 C_USOINMUEBLE
	private Integer ventilacion; //55 C_VENTILACION
	private Date fechaAvaluo; //56 F_FECHAAVALUO
	private Date fechaDeEscritura; //57 F_FECHAESCRITURA
	private Integer aireAcondicionadoCentral; //58 K_AACOND
	private Integer acueductoEnElPredio; //59 K_ACUEDUCTOPREDIO
	private Integer acueductoEnElSector; //60 K_ACUEDUCTOSECTOR
	private Integer impactoAmbientalAguasServidas; //61 K_AGUASHERV
	private Integer alamedas; //62 K_ALAMEDAS
	private Integer alcantarilladoEnElPredio; //63 K_ALCANTAPREDIO
	private Integer alcantarilladoEnElSector; //64 K_ALCANTASECTOR
	private Integer ambientalAlumbrado; //65 K_ALUMBRADO
	private Integer ambientalArborizacion; //66 K_AMBARBORIZA
	private Integer ambientalParques; //67 K_AMBPARQUES
	private Integer ambientalZonasVerdes; //68 K_AMBZVERDE
	private Integer arborizacion; //69 K_ARBORIZACION
	private Integer ascensor; //70 K_ASCENSOR
	private Integer impactoAmbientalBasura; //71 K_BASURA
	private Integer bicicletero; //72 K_BICICLETERO
	private Integer bombaEyectora; //73 K_BOMBA
	private Integer canchaMultiple; //74 K_CANCHAMULT
	private Integer cicloRutas; //75 K_CICLORUTAS
	private Integer citofono; //76 K_CITOFONO
	private Integer clubHouse; //77 K_CLUBHOUSE
	private Integer comercio; //78 K_COMERCIO
	private Integer energiaEnElPredio; //79 K_ELECTRICAPREDIO
	private Integer energiaEnElSector; //80 K_ELECTRICASECTOR
	private Integer estadoDeObra; //81 K_ENOBRA
	private Integer remodelado; //82 K_ESTREMODELA
	private Integer terminada; //83 K_ESTTERMINADA
	private Integer terminado; //84 K_ESTTERMINADO
	private Integer gasEnElPredio; //85 K_GASPREDIO
	private Integer gasEnElSector; //86 K_GASSECTOR
	private Integer gimnasio; //87 K_GIMNASIO
	private Integer garajeVisitantes; //88 K_GJVISITA
	private Integer golfito; //89 K_GOLFITO
	private Integer industria; //90 K_INDUSTRIA
	private Integer impactoAmbientalInseguridad; //91 K_INSERGURIDAD
	private Integer juegosNinos; //92 K_JUEGONINOS
	private Integer otrosUsos; //93 K_OTROSUSOS
	private Integer paradero; //94 K_PARADERO
	private Integer parques; //95 K_PARQUES
	private Integer piscina; //96 K_PISCINA
	private Integer plantaElectrica; //97 K_PLANTA
	private Integer impactoAmbientalAire; //98 K_PORAIRE
	private Integer porteria; //99 K_PORTERIA
	private Integer equipoDePresionConstante; //100 K_PRESION
	private Integer impactoAmbientalRuido; //101 K_RUIDO
	private Integer salonComunal; //102 K_SALONCOMN
	private Integer shutBasuras; //103 K_SHUT
	private Integer sinTerminar; //104 K_SINTERMINAR
	private Integer canchaSquash; //105 K_SQUASH
	private Integer tanqueDeAgua; //106 K_TANQUEAGUA
	private Integer telefonoEnElPredio; //107 K_TELPREDIO
	private Integer telefonoEnElSector; //108 K_TELSECTOR
	private Integer vivienda; //109 K_VIVIENDA
	private Integer zonasVerdesPublicas; //110 K_ZONASVERDES
	private Integer zonasVerdesComunales; //111 K_ZVERDES
	private BigDecimal area1; //112 N_AREALIQ1
	private BigDecimal area10; //113 N_AREALIQ10
	private BigDecimal area2; //114 N_AREALIQ2
	private BigDecimal area3; //115 N_AREALIQ3
	private BigDecimal area4; //116 N_AREALIQ4
	private BigDecimal area5; //117 N_AREALIQ5
	private BigDecimal area6; //118 N_AREALIQ6
	private BigDecimal area7; //119 N_AREALIQ7
	private BigDecimal area8; //120 N_AREALIQ8
	private BigDecimal area9; //121 N_AREALIQ9
	private BigDecimal valorAvaluoEnUvr; //122 N_AVALUOUVR
	private Integer bahiaComunal; //123 N_BAHIACOMUNAL
	private Integer balcon; //124 N_BALCON
	private Integer banoPrivado; //125 N_BANIOPRIVADO
	private Integer banoServicio; //126 N_BANIOSERVICIO
	private Integer banoSocial; //127 N_BANIOSOCIAL
	private Integer bodega; //128 N_BODEGA
	private Integer cocina; //129 N_COCINA
	private Integer comedor; //130 N_COMEDOR
	private Long avaluoId; //131 N_CONSECUTIVOBANCO
	private Integer cuartoServicio; //132 N_CUARTOSERV
	private Integer cubierto; //133 N_CUBIERTO
	private Integer deposito; //134 N_DEPOSITO
	private Integer descubierto; //135 N_DESCUBIERTO
	private Integer doble; //136 N_DOBLE
	private Integer estarHab; //137 N_ESTARHAB
	private Integer estudio; //138 N_ESTUDIO
	private Integer habitaciones; //139 N_HABITACIONES
	private Integer clienteNumeroDocumento; //140 N_IDENTIFICACION
	private Integer jardin; //141 N_JARDIN
	private Integer local; //142 N_LOCAL
	private Integer numeroDeAscensores; //143 N_NUMASCENSORES
	private Integer numeroDeEdificios; //144 N_NUMEDIF
	private Integer oficina; //145 N_OFICINA
	private Integer patioInterior; //146 N_PATIOINT
	private Integer numeroDePisos; //147 N_PISOS
	private Integer privado; //148 N_PRIVADO
	private Integer sala; //149 N_SALA
	private Integer sencillo; //150 N_SENCILLO
	private Integer servidumbre; //151 N_SERVIDUMBRE
	private Integer numeroSotanos; //152 N_SOTANOS
	private Integer terraza; //153 N_TERRAZA
	private double tiempoEsperadoDeComercializacion; //154 N_TIPOCOMERCIALIZA
	private BigDecimal valorTotalAvaluo; //155 N_TOTALAVALUO
	private Integer numeroTotalDeGarajes; //156 N_TOTALGARAJES
	private Integer totalUnidades; //157 N_TOTALUND
	private Integer unidadesPorPiso; //158 N_UNDPISO
	private Integer usoExclusivo; //159 N_USOEXCLUSIVO
	private BigDecimal valorAsegurable; //160 N_VALORASEGURABLE
	private BigDecimal valorTotal1; //161 N_VALTOT1
	private BigDecimal valorTotal10; //162 N_VALTOT10
	private BigDecimal valorTotal2; //163 N_VALTOT2
	private BigDecimal valorTotal3; //164 N_VALTOT3
	private BigDecimal valorTotal4; //165 N_VALTOT4
	private BigDecimal valorTotal5; //166 N_VALTOT5
	private BigDecimal valorTotal6; //167 N_VALTOT6
	private BigDecimal valorTotal7; //168 N_VALTOT7
	private BigDecimal valorTotal8; //169 N_VALTOT8
	private BigDecimal valorTotal9; //170 N_VALTOT9
	private BigDecimal valorUvr; //171 N_VALUVRDIA
	private BigDecimal valorUnitario1; //172 N_VAL1
	private BigDecimal valorUnitario10; //173 N_VAL10
	private BigDecimal valorUnitario2; //174 N_VAL2
	private BigDecimal valorUnitario3; //175 N_VAL3
	private BigDecimal valorUnitario4; //176 N_VAL4
	private BigDecimal valorUnitario5; //177 N_VAL5
	private BigDecimal valorUnitario6; //178 N_VAL6
	private BigDecimal valorUnitario7; //179 N_VAL7
	private BigDecimal valorUnitario8; //180 N_VAL8
	private BigDecimal valorUnitario9; //181 N_VAL9
	private BigDecimal vetustez; //182 N_VETUSTEZ
	private Integer zonaVerdePrivada; //183 N_ZVERDEPRIV
	private Integer calificacionGarantia; //184 R_CALIFICACION
	private Integer estadoDeConstruccion; //185 R_ESTCONS
	private String actualidadEdificadora; //186 T_ACTEDIFICADORA
	private String impactoAmbientalOtro; //187 T_AMBNEGOTRO
	private String otro; //188 T_AMBOTRO
	private Integer porcentajeAvance; //189 T_AVANCEOBRA
	private String barrio; //190 T_BARRIO
	private String chip; //191 T_CHIP
	private String comportamientoOfertaDemanda; //192 T_COMPORTAOD
	private String tipoCubierta; //193 T_CUBIERTA
	private String direccionAnexos; //194 T_DIRANEXOS
	private String direccionInmueble; //195 T_DIRINMUEBLE
	private String tipoEstructura; //196 T_ESTRUCTURA
	private String tipoFachada2; //197 T_FACHADA
	private String justificacionDeMetodologia; //198 T_JUSTIFICACION
	private String matriculaInmobiliariaPrincipal1; //199 T_MINMBPPAL1
	private String matriculaInmobiliariaPrincipal2; //200 T_MINMBPPAL2
	private String matriculaInmobiliariaDeposito1; //201 T_MINMOBDP1
	private String matriculaInmobiliariaDeposito2; //202 T_MINMOBDP2
	private String matriculaInmobiliariaGaraje1; //203 T_MINMOBGJ1
	private String matriculaInmobiliariaGaraje2; //204 T_MINMOBGJ2
	private String matriculaInmobiliariaGaraje3; //205 T_MINMOBGJ3
	private String matriculaInmobiliariaGaraje4; //206 T_MINMOBGJ4
	private String matriculaInmobiliariaGaraje5; //207 T_MINMOBGJ5
	private String nombreDelConjunto; //208 T_NOMBCONJEDIF
	private String nombreSolicitante; //209 T_NOMBRESOLICITANTE
	private String numeroNotariaDeEscritura; //210 T_NOTARIA
	private String numeroDeEscritura; //211 T_NUMESCRITURA
	private String observaciones; //212 T_OBSERVAVALUO
	private String otrasDirecciones; //213 T_OTRASDIR
	private String descripcionClaseInmueble; //214 T_DESCRIPCION_CLASE_INMUEBLE
	private String otrosDotacion; //215 T_OTROSDOTACION
	private String otrosDotacion2; //216 T_OTROSDOTACION2
	private String textoOtrosUsos; //217 T_OTROSUSOS
	private String descripcionOtrosUsoInmueble; //218 T_OTROUSOINM
	private String perspectivasDeValorizacion; //219 T_PERSPECTIVAS
	private Integer altura; //220 C_ALTURA
	private Integer ubicacionLocal; //221 C_UBICACION3
	private String otroSistemaDeCoordenadas; //222 T_SISTEMA
	private Integer reparados; //223 C_REPARADOS
	private Integer irregularidadAltura; //224 C_IRRALTURA
	private Integer irregularidadPlanta; //225 C_IRRPLANTA
	private Integer estructuraReforzada; //226 C_ESTRUCTURAREFORZADA
	private Integer sistemaDeCoordenadas; //227 C_SISTEMA
	private Integer danosPrevios; //228 C_DANOPREVIO
	private String latitud; //229 T_LATITUD
	private Integer anoDeConstruccion; //230 N_ANOCONSTRUCCION
	private Integer materialDeConstruccion; //231 C_MATERIAL
	private String longitud; //232 T_LONGITUD
	private Integer tipoEstructural; //233 C_DETALLEMATERIAL
	private Integer categoria; //234 IDCATEGORIA (Tipo de inmueble)
	private String usuario; //235 T_USUARIO

	public ArchivoBUA() {
	}

	public ArchivoBUA(Integer andenesEnLasVias, Integer ciudadNotaria,
			Integer departamentoNotaria, Integer estrato, Integer ciudad, Integer departamento,
			Integer metodoDeValuacion, Integer objetoDelAvaluo,
			Integer clienteTipoDocumento, Integer barrioLegal, Integer viasPavimentadas,
			Integer sometidoAPropiedadHorizontal, Integer sardinelesEnLasVias,
			Integer topografiaSector, Integer transporte, Integer usoPredominanteInmueble,
			BigDecimal valorAvaluoEnUvr, Long avaluoId,
			Integer clienteNumeroDocumento, double tiempoEsperadoDeComercializacion,
			BigDecimal valorTotalAvaluo, BigDecimal valorUvr,
			Integer calificacionGarantia, String actualidadEdificadora,
			String barrio, String comportamientoOfertaDemanda,
			String justificacionDeMetodologia,
			String matriculaInmobiliariaPrincipal1, String nombreSolicitante,
			String numeroNotariaDeEscritura, String numeroDeEscritura,
			String observaciones, String perspectivasDeValorizacion,
			Integer categoria) {
		super();
		this.andenesEnLasVias = andenesEnLasVias;
		this.ciudadNotaria = ciudadNotaria;
		this.departamentoNotaria = departamentoNotaria;
		this.estrato = estrato;
		this.ciudad = ciudad;
		this.departamento = departamento;
		this.metodoDeValuacion = metodoDeValuacion;
		this.objetoDelAvaluo = objetoDelAvaluo;
		this.clienteTipoDocumento = clienteTipoDocumento;
		this.barrioLegal = barrioLegal;
		this.viasPavimentadas = viasPavimentadas;
		this.sometidoAPropiedadHorizontal = sometidoAPropiedadHorizontal;
		this.sardinelesEnLasVias = sardinelesEnLasVias;
		this.topografiaSector = topografiaSector;
		this.transporte = transporte;
		this.usoPredominanteInmueble = usoPredominanteInmueble;
		this.valorAvaluoEnUvr = valorAvaluoEnUvr;
		this.avaluoId = avaluoId;
		this.clienteNumeroDocumento = clienteNumeroDocumento;
		this.tiempoEsperadoDeComercializacion = tiempoEsperadoDeComercializacion;
		this.valorTotalAvaluo = valorTotalAvaluo;
		this.valorUvr = valorUvr;
		this.calificacionGarantia = calificacionGarantia;
		this.actualidadEdificadora = actualidadEdificadora;
		this.barrio = barrio;
		this.comportamientoOfertaDemanda = comportamientoOfertaDemanda;
		//si la justificacion no se diligencio se envia a bua sin informacion
		this.justificacionDeMetodologia = (justificacionDeMetodologia == null || ( justificacionDeMetodologia != null && justificacionDeMetodologia.isEmpty())) 
				?  Constantes.SIN_INFORMACION : justificacionDeMetodologia;
		this.matriculaInmobiliariaPrincipal1 = matriculaInmobiliariaPrincipal1;
		this.setNombreSolicitante(nombreSolicitante);
		this.numeroNotariaDeEscritura = numeroNotariaDeEscritura;
		this.numeroDeEscritura = numeroDeEscritura;
		this.observaciones = observaciones;
		this.perspectivasDeValorizacion = perspectivasDeValorizacion;
		this.categoria = categoria;
	}

	public Integer getAndenesEnLasVias() {
		return andenesEnLasVias;
	}
	public void setAndenesEnLasVias(Integer andenesEnLasVias) {
		this.andenesEnLasVias = andenesEnLasVias;
	}
	public Integer getCalidadAcabadosBanos() {
		return calidadAcabadosBanos;
	}
	public void setCalidadAcabadosBanos(Integer calidadAcabadosBanos) {
		this.calidadAcabadosBanos = calidadAcabadosBanos;
	}
	public Integer getCalidadAcabadosMadera() {
		return calidadAcabadosMadera;
	}
	public void setCalidadAcabadosMadera(Integer calidadAcabadosMadera) {
		this.calidadAcabadosMadera = calidadAcabadosMadera;
	}
	public Integer getCalidadAcabadosMetal() {
		return calidadAcabadosMetal;
	}
	public void setCalidadAcabadosMetal(Integer calidadAcabadosMetal) {
		this.calidadAcabadosMetal = calidadAcabadosMetal;
	}
	public Integer getCalidadAcabadosCocina() {
		return calidadAcabadosCocina;
	}
	public void setCalidadAcabadosCocina(Integer calidadAcabadosCocina) {
		this.calidadAcabadosCocina = calidadAcabadosCocina;
	}
	public Integer getCalidadAcabadosMuros() {
		return calidadAcabadosMuros;
	}
	public void setCalidadAcabadosMuros(Integer calidadAcabadosMuros) {
		this.calidadAcabadosMuros = calidadAcabadosMuros;
	}
	public Integer getCalidadAcabadosPisos() {
		return calidadAcabadosPisos;
	}
	public void setCalidadAcabadosPisos(Integer calidadAcabadosPisos) {
		this.calidadAcabadosPisos = calidadAcabadosPisos;
	}
	public Integer getCalidadAcabadosTechos() {
		return calidadAcabadosTechos;
	}
	public void setCalidadAcabadosTechos(Integer calidadAcabadosTechos) {
		this.calidadAcabadosTechos = calidadAcabadosTechos;
	}
	public Integer getCiudadNotaria() {
		return ciudadNotaria;
	}
	public void setCiudadNotaria(Integer ciudadNotaria) {
		this.ciudadNotaria = ciudadNotaria;
	}
	public Integer getClaseInmueble() {
		return claseInmueble;
	}
	public void setClaseInmueble(Integer claseInmueble) {
		this.claseInmueble = claseInmueble;
	}
	public Integer getConjuntoCerrado() {
		return conjuntoCerrado;
	}
	public void setConjuntoCerrado(Integer conjuntoCerrado) {
		this.conjuntoCerrado = conjuntoCerrado;
	}
	public Integer getCubierta() {
		return cubierta;
	}
	public void setCubierta(Integer cubierta) {
		this.cubierta = cubierta;
	}
	public Integer getDepartamentoNotaria() {
		return departamentoNotaria;
	}
	public void setDepartamentoNotaria(Integer departamentoNotaria) {
		this.departamentoNotaria = departamentoNotaria;
	}
	public Integer getDescripcion1() {
		return descripcion1;
	}
	public void setDescripcion1(Integer descripcion1) {
		this.descripcion1 = descripcion1;
	}
	public Integer getDescripcion10() {
		return descripcion10;
	}
	public void setDescripcion10(Integer descripcion10) {
		this.descripcion10 = descripcion10;
	}
	public Integer getDescripcion2() {
		return descripcion2;
	}
	public void setDescripcion2(Integer descripcion2) {
		this.descripcion2 = descripcion2;
	}
	public Integer getDescripcion3() {
		return descripcion3;
	}
	public void setDescripcion3(Integer descripcion3) {
		this.descripcion3 = descripcion3;
	}
	public Integer getDescripcion4() {
		return descripcion4;
	}
	public void setDescripcion4(Integer descripcion4) {
		this.descripcion4 = descripcion4;
	}
	public Integer getDescripcion5() {
		return descripcion5;
	}
	public void setDescripcion5(Integer descripcion5) {
		this.descripcion5 = descripcion5;
	}
	public Integer getDescripcion6() {
		return descripcion6;
	}
	public void setDescripcion6(Integer descripcion6) {
		this.descripcion6 = descripcion6;
	}
	public Integer getDescripcion7() {
		return descripcion7;
	}
	public void setDescripcion7(Integer descripcion7) {
		this.descripcion7 = descripcion7;
	}
	public Integer getDescripcion8() {
		return descripcion8;
	}
	public void setDescripcion8(Integer descripcion8) {
		this.descripcion8 = descripcion8;
	}
	public Integer getDescripcion9() {
		return descripcion9;
	}
	public void setDescripcion9(Integer descripcion9) {
		this.descripcion9 = descripcion9;
	}
	public Integer getEstadoAcabadosBanos() {
		return estadoAcabadosBanos;
	}
	public void setEstadoAcabadosBanos(Integer estadoAcabadosBanos) {
		this.estadoAcabadosBanos = estadoAcabadosBanos;
	}
	public Integer getEstadoAcabadosCocina() {
		return estadoAcabadosCocina;
	}
	public void setEstadoAcabadosCocina(Integer estadoAcabadosCocina) {
		this.estadoAcabadosCocina = estadoAcabadosCocina;
	}
	public Integer getEstadoDeConservacion() {
		return estadoDeConservacion;
	}
	public void setEstadoDeConservacion(Integer estadoDeConservacion) {
		this.estadoDeConservacion = estadoDeConservacion;
	}
	public Integer getEstadoAcabadosMadera() {
		return estadoAcabadosMadera;
	}
	public void setEstadoAcabadosMadera(Integer estadoAcabadosMadera) {
		this.estadoAcabadosMadera = estadoAcabadosMadera;
	}
	public Integer getEstadoAcabadosMetal() {
		return estadoAcabadosMetal;
	}
	public void setEstadoAcabadosMetal(Integer estadoAcabadosMetal) {
		this.estadoAcabadosMetal = estadoAcabadosMetal;
	}
	public Integer getEstadoAcabadosMuros() {
		return estadoAcabadosMuros;
	}
	public void setEstadoAcabadosMuros(Integer estadoAcabadosMuros) {
		this.estadoAcabadosMuros = estadoAcabadosMuros;
	}
	public Integer getEstadoAcabadosPisos() {
		return estadoAcabadosPisos;
	}
	public void setEstadoAcabadosPisos(Integer estadoAcabadosPisos) {
		this.estadoAcabadosPisos = estadoAcabadosPisos;
	}
	public Integer getEstrato() {
		return estrato;
	}
	public void setEstrato(Integer estrato) {
		this.estrato = estrato;
	}
	public Integer getEstructura() {
		return estructura;
	}
	public void setEstructura(Integer estructura) {
		this.estructura = estructura;
	}
	public Integer getEstadoAcabadosTechos() {
		return estadoAcabadosTechos;
	}
	public void setEstadoAcabadosTechos(Integer estadoAcabadosTechos) {
		this.estadoAcabadosTechos = estadoAcabadosTechos;
	}
	public Integer getEstadoDeLasVias() {
		return estadoDeLasVias;
	}
	public void setEstadoDeLasVias(Integer estadoDeLasVias) {
		this.estadoDeLasVias = estadoDeLasVias;
	}
	public Integer getFachada() {
		return fachada;
	}
	public void setFachada(Integer fachada) {
		this.fachada = fachada;
	}
	public Integer getCiudad() {
		return ciudad;
	}
	public void setCiudad(Integer ciudad) {
		this.ciudad = ciudad;
	}
	public Integer getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Integer departamento) {
		this.departamento = departamento;
	}
	public Integer getMetodoDeValuacion() {
		return metodoDeValuacion;
	}
	public void setMetodoDeValuacion(Integer metodoDeValuacion) {
		this.metodoDeValuacion = metodoDeValuacion;
	}
	public Integer getObjetoDelAvaluo() {
		return objetoDelAvaluo;
	}
	public void setObjetoDelAvaluo(Integer objetoDelAvaluo) {
		this.objetoDelAvaluo = objetoDelAvaluo;
	}
	public Integer getClienteTipoDocumento() {
		return clienteTipoDocumento;
	}
	public void setClienteTipoDocumento(Integer clienteTipoDocumento) {
		this.clienteTipoDocumento = clienteTipoDocumento;
	}
	public Integer getIluminacion() {
		return iluminacion;
	}
	public void setIluminacion(Integer iluminacion) {
		this.iluminacion = iluminacion;
	}
	public Integer getBarrioLegal() {
		return barrioLegal;
	}
	public void setBarrioLegal(Integer barrioLegal) {
		this.barrioLegal = barrioLegal;
	}
	public Integer getViasPavimentadas() {
		return viasPavimentadas;
	}
	public void setViasPavimentadas(Integer viasPavimentadas) {
		this.viasPavimentadas = viasPavimentadas;
	}
	public Integer getPisosBodega() {
		return pisosBodega;
	}
	public void setPisosBodega(Integer pisosBodega) {
		this.pisosBodega = pisosBodega;
	}
	public Integer getSometidoAPropiedadHorizontal() {
		return sometidoAPropiedadHorizontal;
	}
	public void setSometidoAPropiedadHorizontal(Integer sometidoAPropiedadHorizontal) {
		this.sometidoAPropiedadHorizontal = sometidoAPropiedadHorizontal;
	}
	public Integer getCondicionesDeSalubridad() {
		return condicionesDeSalubridad;
	}
	public void setCondicionesDeSalubridad(Integer condicionesDeSalubridad) {
		this.condicionesDeSalubridad = condicionesDeSalubridad;
	}
	public Integer getSardinelesEnLasVias() {
		return sardinelesEnLasVias;
	}
	public void setSardinelesEnLasVias(Integer sardinelesEnLasVias) {
		this.sardinelesEnLasVias = sardinelesEnLasVias;
	}
	public Integer getTipoFachada() {
		return tipoFachada;
	}
	public void setTipoFachada(Integer tipoFachada) {
		this.tipoFachada = tipoFachada;
	}
	public Integer getTipoDeVivienda() {
		return tipoDeVivienda;
	}
	public void setTipoDeVivienda(Integer tipoDeVivienda) {
		this.tipoDeVivienda = tipoDeVivienda;
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
	public Integer getUbicacionDelInmueble() {
		return ubicacionDelInmueble;
	}
	public void setUbicacionDelInmueble(Integer ubicacionDelInmueble) {
		this.ubicacionDelInmueble = ubicacionDelInmueble;
	}
	public Integer getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(Integer ubicacion) {
		this.ubicacion = ubicacion;
	}
	public Integer getUsoPredominanteInmueble() {
		return usoPredominanteInmueble;
	}
	public void setUsoPredominanteInmueble(Integer usoPredominanteInmueble) {
		this.usoPredominanteInmueble = usoPredominanteInmueble;
	}
	public Integer getVentilacion() {
		return ventilacion;
	}
	public void setVentilacion(Integer ventilacion) {
		this.ventilacion = ventilacion;
	}
	public Date getFechaAvaluo() {
		return fechaAvaluo;
	}
	public void setFechaAvaluo(Date fechaAvaluo) {
		this.fechaAvaluo = fechaAvaluo;
	}
	public Date getFechaDeEscritura() {
		return fechaDeEscritura;
	}
	public void setFechaDeEscritura(Date fechaDeEscritura) {
		this.fechaDeEscritura = fechaDeEscritura;
	}
	public Integer getAireAcondicionadoCentral() {
		return aireAcondicionadoCentral;
	}
	public void setAireAcondicionadoCentral(Integer aireAcondicionadoCentral) {
		this.aireAcondicionadoCentral = aireAcondicionadoCentral;
	}
	public Integer getAcueductoEnElPredio() {
		return acueductoEnElPredio;
	}
	public void setAcueductoEnElPredio(Integer acueductoEnElPredio) {
		this.acueductoEnElPredio = acueductoEnElPredio;
	}
	public Integer getAcueductoEnElSector() {
		return acueductoEnElSector;
	}
	public void setAcueductoEnElSector(Integer acueductoEnElSector) {
		this.acueductoEnElSector = acueductoEnElSector;
	}
	public Integer getImpactoAmbientalAguasServidas() {
		return impactoAmbientalAguasServidas;
	}
	public void setImpactoAmbientalAguasServidas(Integer impactoAmbientalAguasServidas) {
		this.impactoAmbientalAguasServidas = impactoAmbientalAguasServidas;
	}
	public Integer getAlamedas() {
		return alamedas;
	}
	public void setAlamedas(Integer alamedas) {
		this.alamedas = alamedas;
	}
	public Integer getAlcantarilladoEnElPredio() {
		return alcantarilladoEnElPredio;
	}
	public void setAlcantarilladoEnElPredio(Integer alcantarilladoEnElPredio) {
		this.alcantarilladoEnElPredio = alcantarilladoEnElPredio;
	}
	public Integer getAlcantarilladoEnElSector() {
		return alcantarilladoEnElSector;
	}
	public void setAlcantarilladoEnElSector(Integer alcantarilladoEnElSector) {
		this.alcantarilladoEnElSector = alcantarilladoEnElSector;
	}
	public Integer getAmbientalAlumbrado() {
		return ambientalAlumbrado;
	}
	public void setAmbientalAlumbrado(Integer ambientalAlumbrado) {
		this.ambientalAlumbrado = ambientalAlumbrado;
	}
	public Integer getAmbientalArborizacion() {
		return ambientalArborizacion;
	}
	public void setAmbientalArborizacion(Integer ambientalArborizacion) {
		this.ambientalArborizacion = ambientalArborizacion;
	}
	public Integer getAmbientalParques() {
		return ambientalParques;
	}
	public void setAmbientalParques(Integer ambientalParques) {
		this.ambientalParques = ambientalParques;
	}
	public Integer getAmbientalZonasVerdes() {
		return ambientalZonasVerdes;
	}
	public void setAmbientalZonasVerdes(Integer ambientalZonasVerdes) {
		this.ambientalZonasVerdes = ambientalZonasVerdes;
	}
	public Integer getArborizacion() {
		return arborizacion;
	}
	public void setArborizacion(Integer arborizacion) {
		this.arborizacion = arborizacion;
	}
	public Integer getAscensor() {
		return ascensor;
	}
	public void setAscensor(Integer ascensor) {
		this.ascensor = ascensor;
	}
	public Integer getImpactoAmbientalBasura() {
		return impactoAmbientalBasura;
	}
	public void setImpactoAmbientalBasura(Integer impactoAmbientalBasura) {
		this.impactoAmbientalBasura = impactoAmbientalBasura;
	}
	public Integer getBicicletero() {
		return bicicletero;
	}
	public void setBicicletero(Integer bicicletero) {
		this.bicicletero = bicicletero;
	}
	public Integer getBombaEyectora() {
		return bombaEyectora;
	}
	public void setBombaEyectora(Integer bombaEyectora) {
		this.bombaEyectora = bombaEyectora;
	}
	public Integer getCanchaMultiple() {
		return canchaMultiple;
	}
	public void setCanchaMultiple(Integer canchaMultiple) {
		this.canchaMultiple = canchaMultiple;
	}
	public Integer getCicloRutas() {
		return cicloRutas;
	}
	public void setCicloRutas(Integer cicloRutas) {
		this.cicloRutas = cicloRutas;
	}
	public Integer getCitofono() {
		return citofono;
	}
	public void setCitofono(Integer citofono) {
		this.citofono = citofono;
	}
	public Integer getClubHouse() {
		return clubHouse;
	}
	public void setClubHouse(Integer clubHouse) {
		this.clubHouse = clubHouse;
	}
	public Integer getComercio() {
		return comercio;
	}
	public void setComercio(Integer comercio) {
		this.comercio = comercio;
	}
	public Integer getEnergiaEnElPredio() {
		return energiaEnElPredio;
	}
	public void setEnergiaEnElPredio(Integer energiaEnElPredio) {
		this.energiaEnElPredio = energiaEnElPredio;
	}
	public Integer getEnergiaEnElSector() {
		return energiaEnElSector;
	}
	public void setEnergiaEnElSector(Integer energiaEnElSector) {
		this.energiaEnElSector = energiaEnElSector;
	}
	public Integer getEstadoDeObra() {
		return estadoDeObra;
	}
	public void setEstadoDeObra(Integer estadoDeObra) {
		this.estadoDeObra = estadoDeObra;
	}
	public Integer getRemodelado() {
		return remodelado;
	}
	public void setRemodelado(Integer remodelado) {
		this.remodelado = remodelado;
	}
	public Integer getTerminada() {
		return terminada;
	}
	public void setTerminada(Integer terminada) {
		this.terminada = terminada;
	}
	public Integer getTerminado() {
		return terminado;
	}
	public void setTerminado(Integer terminado) {
		this.terminado = terminado;
	}
	public Integer getGasEnElPredio() {
		return gasEnElPredio;
	}
	public void setGasEnElPredio(Integer gasEnElPredio) {
		this.gasEnElPredio = gasEnElPredio;
	}
	public Integer getGasEnElSector() {
		return gasEnElSector;
	}
	public void setGasEnElSector(Integer gasEnElSector) {
		this.gasEnElSector = gasEnElSector;
	}
	public Integer getGimnasio() {
		return gimnasio;
	}
	public void setGimnasio(Integer gimnasio) {
		this.gimnasio = gimnasio;
	}
	public Integer getGarajeVisitantes() {
		return garajeVisitantes;
	}
	public void setGarajeVisitantes(Integer garajeVisitantes) {
		this.garajeVisitantes = garajeVisitantes;
	}
	public Integer getGolfito() {
		return golfito;
	}
	public void setGolfito(Integer golfito) {
		this.golfito = golfito;
	}
	public Integer getIndustria() {
		return industria;
	}
	public void setIndustria(Integer industria) {
		this.industria = industria;
	}
	public Integer getImpactoAmbientalInseguridad() {
		return impactoAmbientalInseguridad;
	}
	public void setImpactoAmbientalInseguridad(Integer impactoAmbientalInseguridad) {
		this.impactoAmbientalInseguridad = impactoAmbientalInseguridad;
	}
	public Integer getJuegosNinos() {
		return juegosNinos;
	}
	public void setJuegosNinos(Integer juegosNinos) {
		this.juegosNinos = juegosNinos;
	}
	public Integer getOtrosUsos() {
		return otrosUsos;
	}
	public void setOtrosUsos(Integer otrosUsos) {
		this.otrosUsos = otrosUsos;
	}
	public Integer getParadero() {
		return paradero;
	}
	public void setParadero(Integer paradero) {
		this.paradero = paradero;
	}
	public Integer getParques() {
		return parques;
	}
	public void setParques(Integer parques) {
		this.parques = parques;
	}
	public Integer getPiscina() {
		return piscina;
	}
	public void setPiscina(Integer piscina) {
		this.piscina = piscina;
	}
	public Integer getPlantaElectrica() {
		return plantaElectrica;
	}
	public void setPlantaElectrica(Integer plantaElectrica) {
		this.plantaElectrica = plantaElectrica;
	}
	public Integer getImpactoAmbientalAire() {
		return impactoAmbientalAire;
	}
	public void setImpactoAmbientalAire(Integer impactoAmbientalAire) {
		this.impactoAmbientalAire = impactoAmbientalAire;
	}
	public Integer getPorteria() {
		return porteria;
	}
	public void setPorteria(Integer porteria) {
		this.porteria = porteria;
	}
	public Integer getEquipoDePresionConstante() {
		return equipoDePresionConstante;
	}
	public void setEquipoDePresionConstante(Integer equipoDePresionConstante) {
		this.equipoDePresionConstante = equipoDePresionConstante;
	}
	public Integer getImpactoAmbientalRuido() {
		return impactoAmbientalRuido;
	}
	public void setImpactoAmbientalRuido(Integer impactoAmbientalRuido) {
		this.impactoAmbientalRuido = impactoAmbientalRuido;
	}
	public Integer getSalonComunal() {
		return salonComunal;
	}
	public void setSalonComunal(Integer salonComunal) {
		this.salonComunal = salonComunal;
	}
	public Integer getShutBasuras() {
		return shutBasuras;
	}
	public void setShutBasuras(Integer shutBasuras) {
		this.shutBasuras = shutBasuras;
	}
	public Integer getSinTerminar() {
		return sinTerminar;
	}
	public void setSinTerminar(Integer sinTerminar) {
		this.sinTerminar = sinTerminar;
	}
	public Integer getCanchaSquash() {
		return canchaSquash;
	}
	public void setCanchaSquash(Integer canchaSquash) {
		this.canchaSquash = canchaSquash;
	}
	public Integer getTanqueDeAgua() {
		return tanqueDeAgua;
	}
	public void setTanqueDeAgua(Integer tanqueDeAgua) {
		this.tanqueDeAgua = tanqueDeAgua;
	}
	public Integer getTelefonoEnElPredio() {
		return telefonoEnElPredio;
	}
	public void setTelefonoEnElPredio(Integer telefonoEnElPredio) {
		this.telefonoEnElPredio = telefonoEnElPredio;
	}
	public Integer getTelefonoEnElSector() {
		return telefonoEnElSector;
	}
	public void setTelefonoEnElSector(Integer telefonoEnElSector) {
		this.telefonoEnElSector = telefonoEnElSector;
	}
	public Integer getVivienda() {
		return vivienda;
	}
	public void setVivienda(Integer vivienda) {
		this.vivienda = vivienda;
	}
	public Integer getZonasVerdesPublicas() {
		return zonasVerdesPublicas;
	}
	public void setZonasVerdesPublicas(Integer zonasVerdesPublicas) {
		this.zonasVerdesPublicas = zonasVerdesPublicas;
	}
	public Integer getZonasVerdesComunales() {
		return zonasVerdesComunales;
	}
	public void setZonasVerdesComunales(Integer zonasVerdesComunales) {
		this.zonasVerdesComunales = zonasVerdesComunales;
	}
	public BigDecimal getArea1() {
		return area1;
	}
	public void setArea1(BigDecimal area1) {
		this.area1 = area1;
	}
	public BigDecimal getArea10() {
		return area10;
	}
	public void setArea10(BigDecimal area10) {
		this.area10 = area10;
	}
	public BigDecimal getArea2() {
		return area2;
	}
	public void setArea2(BigDecimal area2) {
		this.area2 = area2;
	}
	public BigDecimal getArea3() {
		return area3;
	}
	public void setArea3(BigDecimal area3) {
		this.area3 = area3;
	}
	public BigDecimal getArea4() {
		return area4;
	}
	public void setArea4(BigDecimal area4) {
		this.area4 = area4;
	}
	public BigDecimal getArea5() {
		return area5;
	}
	public void setArea5(BigDecimal area5) {
		this.area5 = area5;
	}
	public BigDecimal getArea6() {
		return area6;
	}
	public void setArea6(BigDecimal area6) {
		this.area6 = area6;
	}
	public BigDecimal getArea7() {
		return area7;
	}
	public void setArea7(BigDecimal area7) {
		this.area7 = area7;
	}
	public BigDecimal getArea8() {
		return area8;
	}
	public void setArea8(BigDecimal area8) {
		this.area8 = area8;
	}
	public BigDecimal getArea9() {
		return area9;
	}
	public void setArea9(BigDecimal area9) {
		this.area9 = area9;
	}
	public BigDecimal getValorAvaluoEnUvr() {
		return valorAvaluoEnUvr;
	}
	public void setValorAvaluoEnUvr(BigDecimal valorAvaluoEnUvr) {
		this.valorAvaluoEnUvr = valorAvaluoEnUvr;
	}
	public Integer getBahiaComunal() {
		return bahiaComunal;
	}
	public void setBahiaComunal(Integer bahiaComunal) {
		this.bahiaComunal = bahiaComunal;
	}
	public Integer getBalcon() {
		return balcon;
	}
	public void setBalcon(Integer balcon) {
		this.balcon = balcon;
	}
	public Integer getBanoPrivado() {
		return banoPrivado;
	}
	public void setBanoPrivado(Integer banoPrivado) {
		this.banoPrivado = banoPrivado;
	}
	public Integer getBanoServicio() {
		return banoServicio;
	}
	public void setBanoServicio(Integer banoServicio) {
		this.banoServicio = banoServicio;
	}
	public Integer getBanoSocial() {
		return banoSocial;
	}
	public void setBanoSocial(Integer banoSocial) {
		this.banoSocial = banoSocial;
	}
	public Integer getBodega() {
		return bodega;
	}
	public void setBodega(Integer bodega) {
		this.bodega = bodega;
	}
	public Integer getCocina() {
		return cocina;
	}
	public void setCocina(Integer cocina) {
		this.cocina = cocina;
	}
	public Integer getComedor() {
		return comedor;
	}
	public void setComedor(Integer comedor) {
		this.comedor = comedor;
	}
	
	public Long getAvaluoId() {
		return avaluoId;
	}

	public void setAvaluoId(Long avaluoId) {
		this.avaluoId = avaluoId;
	}

	public Integer getCuartoServicio() {
		return cuartoServicio;
	}
	public void setCuartoServicio(Integer cuartoServicio) {
		this.cuartoServicio = cuartoServicio;
	}
	public Integer getCubierto() {
		return cubierto;
	}
	public void setCubierto(Integer cubierto) {
		this.cubierto = cubierto;
	}
	public Integer getDeposito() {
		return deposito;
	}
	public void setDeposito(Integer deposito) {
		this.deposito = deposito;
	}
	public Integer getDescubierto() {
		return descubierto;
	}
	public void setDescubierto(Integer descubierto) {
		this.descubierto = descubierto;
	}
	public Integer getDoble() {
		return doble;
	}
	public void setDoble(Integer doble) {
		this.doble = doble;
	}
	public Integer getEstarHab() {
		return estarHab;
	}
	public void setEstarHab(Integer estarHab) {
		this.estarHab = estarHab;
	}
	public Integer getEstudio() {
		return estudio;
	}
	public void setEstudio(Integer estudio) {
		this.estudio = estudio;
	}
	public Integer getHabitaciones() {
		return habitaciones;
	}
	public void setHabitaciones(Integer habitaciones) {
		this.habitaciones = habitaciones;
	}
	public Integer getClienteNumeroDocumento() {
		return clienteNumeroDocumento;
	}
	public void setClienteNumeroDocumento(Integer clienteNumeroDocumento) {
		this.clienteNumeroDocumento = clienteNumeroDocumento;
	}
	public Integer getJardin() {
		return jardin;
	}
	public void setJardin(Integer jardin) {
		this.jardin = jardin;
	}
	public Integer getLocal() {
		return local;
	}
	public void setLocal(Integer local) {
		this.local = local;
	}
	public Integer getNumeroDeAscensores() {
		return numeroDeAscensores;
	}
	public void setNumeroDeAscensores(Integer numeroDeAscensores) {
		this.numeroDeAscensores = numeroDeAscensores;
	}
	public Integer getNumeroDeEdificios() {
		return numeroDeEdificios;
	}
	public void setNumeroDeEdificios(Integer numeroDeEdificios) {
		this.numeroDeEdificios = numeroDeEdificios;
	}
	public Integer getOficina() {
		return oficina;
	}
	public void setOficina(Integer oficina) {
		this.oficina = oficina;
	}
	public Integer getPatioInterior() {
		return patioInterior;
	}
	public void setPatioInterior(Integer patioInterior) {
		this.patioInterior = patioInterior;
	}
	public Integer getNumeroDePisos() {
		return numeroDePisos;
	}
	public void setNumeroDePisos(Integer numeroDePisos) {
		this.numeroDePisos = numeroDePisos;
	}
	public Integer getPrivado() {
		return privado;
	}
	public void setPrivado(Integer privado) {
		this.privado = privado;
	}
	public Integer getSala() {
		return sala;
	}
	public void setSala(Integer sala) {
		this.sala = sala;
	}
	public Integer getSencillo() {
		return sencillo;
	}
	public void setSencillo(Integer sencillo) {
		this.sencillo = sencillo;
	}
	public Integer getServidumbre() {
		return servidumbre;
	}
	public void setServidumbre(Integer servidumbre) {
		this.servidumbre = servidumbre;
	}
	public Integer getNumeroSotanos() {
		return numeroSotanos;
	}
	public void setNumeroSotanos(Integer numeroSotanos) {
		this.numeroSotanos = numeroSotanos;
	}
	public Integer getTerraza() {
		return terraza;
	}
	public void setTerraza(Integer terraza) {
		this.terraza = terraza;
	}
	public double getTiempoEsperadoDeComercializacion() {
		return tiempoEsperadoDeComercializacion;
	}
	public void setTiempoEsperadoDeComercializacion(
			double tiempoEsperadoDeComercializacion) {
		this.tiempoEsperadoDeComercializacion = tiempoEsperadoDeComercializacion;
	}
	public BigDecimal getValorTotalAvaluo() {
		return valorTotalAvaluo;
	}
	public void setValorTotalAvaluo(BigDecimal valorTotalAvaluo) {
		this.valorTotalAvaluo = valorTotalAvaluo;
	}
	public Integer getNumeroTotalDeGarajes() {
		return numeroTotalDeGarajes;
	}
	public void setNumeroTotalDeGarajes(Integer numeroTotalDeGarajes) {
		this.numeroTotalDeGarajes = numeroTotalDeGarajes;
	}
	public Integer getTotalUnidades() {
		return totalUnidades;
	}
	public void setTotalUnidades(Integer totalUnidades) {
		this.totalUnidades = totalUnidades;
	}
	public Integer getUnidadesPorPiso() {
		return unidadesPorPiso;
	}
	public void setUnidadesPorPiso(Integer unidadesPorPiso) {
		this.unidadesPorPiso = unidadesPorPiso;
	}
	public Integer getUsoExclusivo() {
		return usoExclusivo;
	}
	public void setUsoExclusivo(Integer usoExclusivo) {
		this.usoExclusivo = usoExclusivo;
	}
	public BigDecimal getValorAsegurable() {
		return valorAsegurable;
	}
	public void setValorAsegurable(BigDecimal valorAsegurable) {
		this.valorAsegurable = valorAsegurable;
	}
	public BigDecimal getValorTotal1() {
		return valorTotal1;
	}
	public void setValorTotal1(BigDecimal valorTotal1) {
		this.valorTotal1 = valorTotal1;
	}
	public BigDecimal getValorTotal10() {
		return valorTotal10;
	}
	public void setValorTotal10(BigDecimal valorTotal10) {
		this.valorTotal10 = valorTotal10;
	}
	public BigDecimal getValorTotal2() {
		return valorTotal2;
	}
	public void setValorTotal2(BigDecimal valorTotal2) {
		this.valorTotal2 = valorTotal2;
	}
	public BigDecimal getValorTotal3() {
		return valorTotal3;
	}
	public void setValorTotal3(BigDecimal valorTotal3) {
		this.valorTotal3 = valorTotal3;
	}
	public BigDecimal getValorTotal4() {
		return valorTotal4;
	}
	public void setValorTotal4(BigDecimal valorTotal4) {
		this.valorTotal4 = valorTotal4;
	}
	public BigDecimal getValorTotal5() {
		return valorTotal5;
	}
	public void setValorTotal5(BigDecimal valorTotal5) {
		this.valorTotal5 = valorTotal5;
	}
	public BigDecimal getValorTotal6() {
		return valorTotal6;
	}
	public void setValorTotal6(BigDecimal valorTotal6) {
		this.valorTotal6 = valorTotal6;
	}
	public BigDecimal getValorTotal7() {
		return valorTotal7;
	}
	public void setValorTotal7(BigDecimal valorTotal7) {
		this.valorTotal7 = valorTotal7;
	}
	public BigDecimal getValorTotal8() {
		return valorTotal8;
	}
	public void setValorTotal8(BigDecimal valorTotal8) {
		this.valorTotal8 = valorTotal8;
	}
	public BigDecimal getValorTotal9() {
		return valorTotal9;
	}
	public void setValorTotal9(BigDecimal valorTotal9) {
		this.valorTotal9 = valorTotal9;
	}
	public BigDecimal getValorUvr() {
		return valorUvr;
	}
	public void setValorUvr(BigDecimal valorUvr) {
		this.valorUvr = valorUvr;
	}
	public BigDecimal getValorUnitario1() {
		return valorUnitario1;
	}
	public void setValorUnitario1(BigDecimal valorUnitario1) {
		this.valorUnitario1 = valorUnitario1;
	}
	public BigDecimal getValorUnitario10() {
		return valorUnitario10;
	}
	public void setValorUnitario10(BigDecimal valorUnitario10) {
		this.valorUnitario10 = valorUnitario10;
	}
	public BigDecimal getValorUnitario2() {
		return valorUnitario2;
	}
	public void setValorUnitario2(BigDecimal valorUnitario2) {
		this.valorUnitario2 = valorUnitario2;
	}
	public BigDecimal getValorUnitario3() {
		return valorUnitario3;
	}
	public void setValorUnitario3(BigDecimal valorUnitario3) {
		this.valorUnitario3 = valorUnitario3;
	}
	public BigDecimal getValorUnitario4() {
		return valorUnitario4;
	}
	public void setValorUnitario4(BigDecimal valorUnitario4) {
		this.valorUnitario4 = valorUnitario4;
	}
	public BigDecimal getValorUnitario5() {
		return valorUnitario5;
	}
	public void setValorUnitario5(BigDecimal valorUnitario5) {
		this.valorUnitario5 = valorUnitario5;
	}
	public BigDecimal getValorUnitario6() {
		return valorUnitario6;
	}
	public void setValorUnitario6(BigDecimal valorUnitario6) {
		this.valorUnitario6 = valorUnitario6;
	}
	public BigDecimal getValorUnitario7() {
		return valorUnitario7;
	}
	public void setValorUnitario7(BigDecimal valorUnitario7) {
		this.valorUnitario7 = valorUnitario7;
	}
	public BigDecimal getValorUnitario8() {
		return valorUnitario8;
	}
	public void setValorUnitario8(BigDecimal valorUnitario8) {
		this.valorUnitario8 = valorUnitario8;
	}
	public BigDecimal getValorUnitario9() {
		return valorUnitario9;
	}
	public void setValorUnitario9(BigDecimal valorUnitario9) {
		this.valorUnitario9 = valorUnitario9;
	}
	public BigDecimal getVetustez() {
		return vetustez;
	}
	public void setVetustez(BigDecimal vetustez) {
		this.vetustez = vetustez;
	}
	public Integer getZonaVerdePrivada() {
		return zonaVerdePrivada;
	}
	public void setZonaVerdePrivada(Integer zonaVerdePrivada) {
		this.zonaVerdePrivada = zonaVerdePrivada;
	}
	public Integer getCalificacionGarantia() {
		return calificacionGarantia;
	}
	public void setCalificacionGarantia(Integer calificacionGarantia) {
		this.calificacionGarantia = calificacionGarantia;
	}
	public Integer getEstadoDeConstruccion() {
		return estadoDeConstruccion;
	}
	public void setEstadoDeConstruccion(Integer estadoDeConstruccion) {
		this.estadoDeConstruccion = estadoDeConstruccion;
	}
	public String getActualidadEdificadora() {
		return actualidadEdificadora;
	}
	public void setActualidadEdificadora(String actualidadEdificadora) {
		this.actualidadEdificadora = actualidadEdificadora;
	}
	public String getImpactoAmbientalOtro() {
		return impactoAmbientalOtro;
	}
	public void setImpactoAmbientalOtro(String impactoAmbientalOtro) {
		this.impactoAmbientalOtro = impactoAmbientalOtro;
	}
	public String getOtro() {
		return otro;
	}
	public void setOtro(String otro) {
		this.otro = otro;
	}
	public Integer getPorcentajeAvance() {
		return porcentajeAvance;
	}
	public void setPorcentajeAvance(Integer porcentajeAvance) {
		this.porcentajeAvance = porcentajeAvance;
	}
	public String getBarrio() {
		return barrio;
	}
	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}
	public String getChip() {
		return chip;
	}
	public void setChip(String chip) {
		this.chip = chip;
	}
	public String getComportamientoOfertaDemanda() {
		return comportamientoOfertaDemanda;
	}
	public void setComportamientoOfertaDemanda(String comportamientoOfertaDemanda) {
		this.comportamientoOfertaDemanda = comportamientoOfertaDemanda;
	}
	public String getTipoCubierta() {
		return tipoCubierta;
	}
	public void setTipoCubierta(String tipoCubierta) {
		this.tipoCubierta = tipoCubierta;
	}
	public String getDireccionAnexos() {
		return direccionAnexos;
	}
	public void setDireccionAnexos(String direccionAnexos) {
		this.direccionAnexos = direccionAnexos;
	}
	public String getDireccionInmueble() {
		return direccionInmueble;
	}
	public void setDireccionInmueble(String direccionInmueble) {
		this.direccionInmueble = direccionInmueble;
	}
	public String getTipoEstructura() {
		return tipoEstructura;
	}
	public void setTipoEstructura(String tipoEstructura) {
		this.tipoEstructura = tipoEstructura;
	}
	public String getTipoFachada2() {
		return tipoFachada2;
	}

	public void setTipoFachada2(String tipoFachada2) {
		this.tipoFachada2 = tipoFachada2;
	}

	public String getJustificacionDeMetodologia() {
		return justificacionDeMetodologia;
	}
	public void setJustificacionDeMetodologia(String justificacionDeMetodologia) {
		this.justificacionDeMetodologia = justificacionDeMetodologia;
	}
	public String getMatriculaInmobiliariaPrincipal1() {
		return matriculaInmobiliariaPrincipal1;
	}
	public void setMatriculaInmobiliariaPrincipal1(
			String matriculaInmobiliariaPrincipal1) {
		this.matriculaInmobiliariaPrincipal1 = matriculaInmobiliariaPrincipal1;
	}
	public String getMatriculaInmobiliariaPrincipal2() {
		return matriculaInmobiliariaPrincipal2;
	}
	public void setMatriculaInmobiliariaPrincipal2(
			String matriculaInmobiliariaPrincipal2) {
		this.matriculaInmobiliariaPrincipal2 = matriculaInmobiliariaPrincipal2;
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
	public String getNombreDelConjunto() {
		return nombreDelConjunto;
	}
	public void setNombreDelConjunto(String nombreDelConjunto) {
		this.nombreDelConjunto = nombreDelConjunto;
	}
	public String getNumeroNotariaDeEscritura() {
		return numeroNotariaDeEscritura;
	}
	public void setNumeroNotariaDeEscritura(String numeroNotariaDeEscritura) {
		this.numeroNotariaDeEscritura = numeroNotariaDeEscritura;
	}
	public String getNumeroDeEscritura() {
		return numeroDeEscritura;
	}
	public void setNumeroDeEscritura(String numeroDeEscritura) {
		this.numeroDeEscritura = numeroDeEscritura;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public String getOtrasDirecciones() {
		return otrasDirecciones;
	}
	public void setOtrasDirecciones(String otrasDirecciones) {
		this.otrasDirecciones = otrasDirecciones;
	}
	public String getOtrosDotacion() {
		return otrosDotacion;
	}
	public void setOtrosDotacion(String otrosDotacion) {
		this.otrosDotacion = otrosDotacion;
	}
	public String getOtrosDotacion2() {
		return otrosDotacion2;
	}
	public void setOtrosDotacion2(String otrosDotacion2) {
		this.otrosDotacion2 = otrosDotacion2;
	}
	public String getTextoOtrosUsos() {
		return textoOtrosUsos;
	}
	public void setTextoOtrosUsos(String textoOtrosUsos) {
		this.textoOtrosUsos = textoOtrosUsos;
	}
	public String getDescripcionOtrosUsoInmueble() {
		return descripcionOtrosUsoInmueble;
	}
	public void setDescripcionOtrosUsoInmueble(String descripcionOtrosUsoInmueble) {
		this.descripcionOtrosUsoInmueble = descripcionOtrosUsoInmueble;
	}
	public String getPerspectivasDeValorizacion() {
		return perspectivasDeValorizacion;
	}
	public void setPerspectivasDeValorizacion(String perspectivasDeValorizacion) {
		this.perspectivasDeValorizacion = perspectivasDeValorizacion;
	}
	public Integer getAltura() {
		return altura;
	}
	public void setAltura(Integer altura) {
		this.altura = altura;
	}
	public Integer getUbicacionLocal() {
		return ubicacionLocal;
	}
	public void setUbicacionLocal(Integer ubicacionLocal) {
		this.ubicacionLocal = ubicacionLocal;
	}
	public String getOtroSistemaDeCoordenadas() {
		return otroSistemaDeCoordenadas;
	}
	public void setOtroSistemaDeCoordenadas(String otroSistemaDeCoordenadas) {
		this.otroSistemaDeCoordenadas = otroSistemaDeCoordenadas;
	}
	public Integer getReparados() {
		return reparados;
	}
	public void setReparados(Integer reparados) {
		this.reparados = reparados;
	}
	public Integer getIrregularidadAltura() {
		return irregularidadAltura;
	}
	public void setIrregularidadAltura(Integer irregularidadAltura) {
		this.irregularidadAltura = irregularidadAltura;
	}
	public Integer getIrregularidadPlanta() {
		return irregularidadPlanta;
	}
	public void setIrregularidadPlanta(Integer irregularidadPlanta) {
		this.irregularidadPlanta = irregularidadPlanta;
	}
	public Integer getEstructuraReforzada() {
		return estructuraReforzada;
	}
	public void setEstructuraReforzada(Integer estructuraReforzada) {
		this.estructuraReforzada = estructuraReforzada;
	}
	public Integer getSistemaDeCoordenadas() {
		return sistemaDeCoordenadas;
	}
	public void setSistemaDeCoordenadas(Integer sistemaDeCoordenadas) {
		this.sistemaDeCoordenadas = sistemaDeCoordenadas;
	}
	public Integer getDanosPrevios() {
		return danosPrevios;
	}
	public void setDanosPrevios(Integer danosPrevios) {
		this.danosPrevios = danosPrevios;
	}
	public String getLatitud() {
		return latitud;
	}
	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}
	public Integer getAnoDeConstruccion() {
		return anoDeConstruccion;
	}
	public void setAnoDeConstruccion(Integer anoDeConstruccion) {
		this.anoDeConstruccion = anoDeConstruccion;
	}
	public Integer getMaterialDeConstruccion() {
		return materialDeConstruccion;
	}
	public void setMaterialDeConstruccion(Integer materialDeConstruccion) {
		this.materialDeConstruccion = materialDeConstruccion;
	}
	public String getLongitud() {
		return longitud;
	}
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}
	public Integer getTipoEstructural() {
		return tipoEstructural;
	}
	public void setTipoEstructural(Integer tipoEstructural) {
		this.tipoEstructural = tipoEstructural;
	}
	public Integer getCategoria() {
		return categoria;
	}
	public void setCategoria(Integer categoria) {
		this.categoria = categoria;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getNombreSolicitante() {
		return nombreSolicitante;
	}

	public void setNombreSolicitante(String nombreSolicitante) {
		this.nombreSolicitante = nombreSolicitante;
	}

	public String getDescripcionClaseInmueble() {
		return descripcionClaseInmueble;
	}

	public void setDescripcionClaseInmueble(String descripcionClaseInmueble) {
		this.descripcionClaseInmueble = descripcionClaseInmueble;
	}


}
