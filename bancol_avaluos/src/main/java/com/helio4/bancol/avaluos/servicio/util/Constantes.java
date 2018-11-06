package com.helio4.bancol.avaluos.servicio.util;

import com.helio4.bancol.avaluos.modelo.EstadoAprobado;
import com.helio4.bancol.avaluos.modelo.EstadoAsignado;
import com.helio4.bancol.avaluos.modelo.EstadoAvaluo;
import com.helio4.bancol.avaluos.modelo.EstadoCancelado;
import com.helio4.bancol.avaluos.modelo.EstadoCitaProgramada;
import com.helio4.bancol.avaluos.modelo.EstadoCorreciones;
import com.helio4.bancol.avaluos.modelo.EstadoDevuelto;
import com.helio4.bancol.avaluos.modelo.EstadoEnProceso;
import com.helio4.bancol.avaluos.modelo.EstadoEnviado;
import com.helio4.bancol.avaluos.modelo.EstadoPendienteDocumentos;
import com.helio4.bancol.avaluos.modelo.EstadoPendientePago;
import com.helio4.bancol.avaluos.modelo.EstadoPorAprobarDevolucion;
import com.helio4.bancol.avaluos.modelo.EstadoPorAsignar;
import com.helio4.bancol.avaluos.modelo.EstadoPorProgramarCita;
import com.helio4.bancol.avaluos.modelo.EstadoPorRevisarComite;

public class Constantes {

	// ---------------NUEVOS----------------
	public static final String PERMISO_CREAR_ROL = "Crear Rol";
	public static final String PERMISO_CREAR_USUARIO = "Crear Usuario";
	public static final String PERMISO_CREAR_NUEVA_SOLICITUD = "Crear Nueva Solicitud";
	public static final String PERMISO_EDITAR_SOLICITUD = "Editar Solicitud";
	public static final String PERMISO_GUARDAR_SOLICITUD = "Guardar Solicitud";
	public static final String PERMISO_ASIGNAR_SOLICITUD = "Asignar Solicitud";
	public static final String PERMISO_REASIGNAR_SOLICITUD = "Reasignar Solicitud";
	public static final String PERMISO_ACEPTAR_CASO = "Aceptar Caso";
	public static final String PERMISO_RECHAZAR_CASO = "Rechazar Caso";
	public static final String PERMISO_PROGRAMAR_CITA = "Programar Cita";
	public static final String PERMISO_REPROGRAMAR_CITA = "Reprogramar Cita";
	public static final String PERMISO_CONFIRMAR_VISITA = "Confirmar Visita";
	public static final String PERMISO_CONFIRMAR_DOCUMENTOS = "Confirmar Documentos";
	public static final String PERMISO_CONFIRMAR_PAGO = "Confirmar Pago";
	public static final String PERMISO_INGRESAR_INFORME = "Ingresar Informe";
	public static final String PERMISO_REVISAR_GUARDAR_AVALUO = "Revisar/Guardar Avaluo";
	public static final String PERMISO_CARGAR_FOTOS = "Cargar Fotos";
	public static final String PERMISO_ENVIAR_NOTIFICACIONES_HONORARIOS = "Enviar Notificaciones Honorarios";
	public static final String PERMISO_ENVIAR = "Enviar";
	public static final String PERMISO_VER_CORRECCIONES_SOLICITADAS = "Ver Correcciones Solicitadas";
	public static final String PERMISO_SOLICITAR_DEVOLUCION = "Solicitar Devolucion";
	public static final String PERMISO_DEVOLVER_CASO = "Devolver Caso";
	public static final String PERMISO_REACTIVAR_CASO = "Reactivar Caso";
	public static final String PERMISO_SOLICITAR_CORRECCIONES = "Solicitar Correcciones";
	public static final String PERMISO_COMITE = "Comite";
	public static final String PERMISO_ENVIAR_A_COMITE = "Enviar a Comite";
	public static final String PERMISO_DESCARGAR_PDF_SIN_FIRMAS = "Descargar PDF Sin Firmas";
	public static final String PERMISO_VER_INFORMACION_DE_PERITO = "Ver Informacion de Perito";
	public static final String PERMISO_EDITAR_AVALUO_DESPUES_DE_APROBADO = "Editar Avaluo Despues de Aprobado";
	public static final String PERMISO_APROBAR_AVALUO = "Aprobar Avaluo";
	public static final String PERMISO_VER_INFORMES_SIN_EDITAR = "Ver Informes sin Editar";
	public static final String PERMISO_DESCARGAR_PDF_CON_FIRMAS = "Descargar PDF con Firmas";
	public static final String PERMISO_CANCELAR_AVALUO = "Cancelar Avaluo";
	public static final String PERMISO_EXPORTAR_BUA = "Exportar BUA";
	public static final String PERMISO_VER_AGENDA = "Ver Agenda";
	public static final String PERMISO_ESTUDIO_MERCADO = "Estudio Mercado";
	public static final String PERMISO_RECHAZAR_DEVOLUCION = "Rechazar Devolucion";
	public static final String PERMISO_VER_FOTOS = "Ver Fotos";
	public static final String PERMISO_CORREGIR_INFORME = "Corregir Informe";
	public static final String PERMISO_ABOGADO = "Abogado";
	public static final String PERMISO_COBRAR_IVA = "Cobrar Iva";
	public static final String PERMISO_REGISTRO = "Registro";
	public static final String PERMISO_DESCARGAR_PDF_ANEXO_METODOLOGIAS = "Descargar PDF anexo metodologias";
	public static final String PERMISO_DATOS_BANCARIOS = "Datos Bancarios";
	public static final String PERMISO_EXPORTAR_REPORTES = "Exportar reportes";
	public static final String PERMISO_EDITAR_CLIENTE = "Editar cliente";
	// ---------------VIEJOS----------------
	public static final String PERMISO_CREAR_ENTIDADES = "Crear Entidades";
	public static final String PERMISO_CREAR_SEGMENTOS = "Crear Segmentos";
	public static final String PERMISO_CREAR_SUCURSALES = "Crear Sucursales";
	public static final String PERMISO_CAMBIAR_FECHA_APROBACION = "Cambiar fecha de aprobacion";
	// ---------------Tipos de avaluo--------------
	public static final String TIPO_AVALUO_HIPOTECARIO = "Hipotecario";
	public static final String TIPO_AVALUO_COMERCIAL_RURAL = "Comercial Rural";
	public static final String TIPO_AVALUO_COMERCIAL_URBANO = "Comercial Urbano";
	public static final String TIPO_AVALUO_CONSTRUCTOR = "Constructor";
	public static final String TIPO_AVALUO_PROYECTOS = "Proyectos";
	public static final String TIPO_AVALUO_REMATE = "Remates";
	public static final String TIPO_AVALUO_ACTUALIZACION = "Actualizaciones";
	
	// ---------------Campos--------------
	public static final String PERMISO_CAMBIAR_CAMPOS_CLAVES = "Cambiar Campos claves";
	public static final String PERMISO_MODIFICAR_ID_SISGEN = "Modificar ID Sisgen";
	// ---------------ESTADOS----------------

	public static final Integer[] estadosEditarSolicitud = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 13, 14 };
	public static final Integer[] estadosAsignarsolicitud = { 1 };
	public static final Integer[] estadosReAsignarSolicitud = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 13, 14 };
	public static final Integer[] estadosAceptarCaso = { 2 };
	public static final Integer[] estadosRechazarCaso = { 2 };
	public static final Integer[] estadosProgramarCita = { 3 };
	public static final Integer[] estadosReProgramarCita = { 4 };
	public static final Integer[] estadosConfirmarDocumentos = { 5 };
	public static final Integer[] estadosConfirmarPago = { 7 };
	public static final Integer[] estadosIngresarInforme = { 4, 5, 6 };
	public static final Integer[] estadosRevisarGuardarAvaluo = { 8 };
	public static final Integer[] estadosCargarFotos = { 4, 5, 6 };
	public static final Integer[] estadosVerCorreccionesSolicitadas = { 9 };
	public static final Integer[] estadosSolicitarDevolucion = { 4, 5, 6, 7 };
	public static final Integer[] estadosDevolverCaso = { 10 };
	public static final Integer[] estadosReactivarCaso = { 13 };
	public static final Integer[] estadosComite = { 10 };
	public static final Integer[] estadosVerInformacionDePerito = { 7 };
	public static final Integer[] estadosEditarAvaluoDespuesDeAprobado = {};
	public static final Integer[] estadosVerInformesSinEditar = { 5, 6, 7, 8, 9, 12, 13 };
	public static final Integer[] estadosDescargarPDFConFirmas = {};
	public static final Integer[] estadosRechazarDevolucion = { 12 };
	public static final Integer[] estadosCorregirInforme = { 9 };

	// motivos del avaluo
	public static final Integer AVALUO_GARANTIA = 0;
	public static final Integer AVALUO_CAMBIO_GARANTIA = 3;
	public static final Integer AVALUO_COMPRA_CARTERA = 4;

	// opciones de redireccionamiento despues de loggeo via remota
	public static final String OPC_HIPOTECARIO = "HIPOTECARIO";
	public static final String OPC_HIPOTECARIO_URL = "/pages/informes/sisgem/listado_avaluos_sisgem.xhtml";

	public static final Integer HORAS_LABORALES = 8;

	public enum Estado {

		PorAsignar("Por asignar", 1),
		Asignado("Asignado", 2),
		PorProgramarCita("Por programar", 3),
		CitaProgramada("Cita programada", 4),
		PendienteDocumentos("Pendiente por documentos", 5),
		EnProceso("En proceso", 6),
		PendientePago("Pendiente por pago", 7),
		Enviado("Enviado", 8),
		Correciones("Correcciones", 9),
		ParaComite("Para comité", 10),
		Aprobado("Aprobado", 11),
		PendienteDevolucion("Por aprobar devolución", 12),
		Devuelto("Devuelto", 13),
		Cancelado("Cancelado", 14);

		private final String value;
		private final int key;

		Estado(String value, int key) {
			this.value = value;
			this.key = key;
		}

		public String value() {
			return value;
		}

		public int key() {
			return key;
		}

		@Override
		public String toString() {
			return value();
		}

		public static Estado fromKey(int key) {
			switch (key) {
			case 1:
				return PorAsignar;
			case 2:
				return Asignado;
			case 3:
				return PorProgramarCita;
			case 4:
				return CitaProgramada;
			case 5:
				return PendienteDocumentos;
			case 6:
				return EnProceso;
			case 7:
				return PendientePago;
			case 8:
				return Enviado;
			case 9:
				return Correciones;
			case 10:
				return ParaComite;
			case 11:
				return Aprobado;
			case 12:
				return PendienteDevolucion;
			case 13:
				return Devuelto;
			case 14:
				return Cancelado;
			default:
				return null;
			}
		}

		public static Estado fromClass(Class<? extends EstadoAvaluo> class1) {
			if (class1.equals(EstadoPorAsignar.class)) {
				return Estado.PorAsignar;
			} else if (class1.equals(EstadoAsignado.class)) {
				return Estado.Asignado;
			} else if (class1.equals(EstadoPorProgramarCita.class)) {
				return Estado.PorProgramarCita;
			} else if (class1.equals(EstadoCitaProgramada.class)) {
				return Estado.CitaProgramada;
			} else if (class1.equals(EstadoPendienteDocumentos.class)) {
				return Estado.PendienteDocumentos;
			} else if (class1.equals(EstadoEnProceso.class)) {
				return Estado.EnProceso;
			} else if (class1.equals(EstadoPendientePago.class)) {
				return Estado.PendientePago;
			} else if (class1.equals(EstadoEnviado.class)) {
				return Estado.Enviado;
			} else if (class1.equals(EstadoCorreciones.class)) {
				return Estado.Correciones;
			} else if (class1.equals(EstadoPorRevisarComite.class)) {
				return Estado.ParaComite;
			} else if (class1.equals(EstadoAprobado.class)) {
				return Estado.Aprobado;
			} else if (class1.equals(EstadoPorAprobarDevolucion.class)) {
				return Estado.PendienteDevolucion;
			} else if (class1.equals(EstadoDevuelto.class)) {
				return Estado.Devuelto;
			} else if (class1.equals(EstadoCancelado.class)) {
				return Estado.Cancelado;
			}
			return null;
		}
	}

	public static final Integer MOTIVO_NINGUNO = 0;
	public static final Integer MOTIVO_CAMBIO_GARANTIA = 3;
	public static final Integer MOTIVO_COMPRA_CARTERA = 4;

    public static final String OBJETO_AVALUO_REMATE = "Remate";
    public static final String OBJETO_AVALUO_DACION_EN_PAGO = "Dación en pago";
    public static final String OBJETO_AVALUO_ORIGINACION = "Originación";
    public static final String OBJETO_AVALUO_ACTUALIZACION = "Actualización";
    public static final String OBJETO_AVALUO_REMODELACION = "Remodelación";
    public static final String OBJETO_AVALUO_CONSTRUCTOR_INDIVIDUAL = "Constructor Individual";
    
    public static final String ENTIDAD_DACIONES_EN_PAGO = "Daciones en Pago";
    
    /**
     * Constantes de enumaeraciones
     * */
    
    public static final String TIPO_SECTOR_URBANO = "Urbano";
  	public static final String TIPO_SECTOR_RURAL = "Rural";
  	
  	public static final String ESTADO_VIA_BUENO = "Bueno";
 	public static final String ESTADO_VIA_REGULAR = "Regular";
 	public static final String ESTADO_VIA_MALO = "Malo";
 	
    public static final String TIPOGRAFIA_SECTOR_PLANO = "Plano";
    public static final String TIPOGRAFIA_SECTOR_LIGERA = "Ligera";
    public static final String TIPOGRAFIA_SECTOR_INCLINADO = "Inclinado";
    public static final String TIPOGRAFIA_SECTOR_ACCIDENTADA = "Accidentada";
    
    public static final String USO_PREDOMINANTE_INMUEBLE_VIVIENDA = "Vivienda";
    public static final String USO_PREDOMINANTE_INMUEBLE_COMERCIO = "Comercio";
    public static final String USO_PREDOMINANTE_INMUEBLE_BODEGA = "Bodega";
    public static final String USO_PREDOMINANTE_INMUEBLE_OFICINA = "Oficina";
    public static final String USO_PREDOMINANTE_INMUEBLE_MULTIHABITACIONAL = "V. Multihabitacional";
    public static final String USO_PREDOMINANTE_INMUEBLE_OTRO = "Otro";
    public static final String USO_PREDOMINANTE_INMUEBLE_EDUCACION = "Educación";
    public static final String USO_PREDOMINANTE_INMUEBLE_SALUD = "Salud";
    public static final String USO_PREDOMINANTE_INMUEBLE_HOTELERO = "Hotelero";
    public static final String USO_PREDOMINANTE_INMUEBLE_INDUSTRIAL = "Industrial";
    public static final String USO_PREDOMINANTE_INMUEBLE_IGLESIA = "Iglesia";
    public static final String USO_PREDOMINANTE_INMUEBLE_PARQUEADERO = "Parqueadero";
    public static final String USO_PREDOMINANTE_INMUEBLE_AUDITORIO = "Auditorio";
    public static final String USO_PREDOMINANTE_INMUEBLE_INSTALACION_DEPORTIVA = "Instalación Deportiva";
    public static final String USO_PREDOMINANTE_INMUEBLE_MIXTO = "Mixto";
    
    public static final String USO_PREDOMINANTE_INMUEBLE_SIN_USO_ERIAZO = "Sin uso - Eriazo";
    public static final String USO_PREDOMINANTE_INMUEBLE_SIN_USO_VIVIENDA = "Sin uso - Vivienda";
    public static final String USO_PREDOMINANTE_INMUEBLE_SEGUNDA_VIVIENDA = "2a. Vivienda";
    public static final String USO_PREDOMINANTE_INMUEBLE_TURISMO = "Turismo";
    public static final String USO_PREDOMINANTE_INMUEBLE_INDUSTRIA_ALIMENTOS = "Ind. Alimentos";
    public static final String USO_PREDOMINANTE_INMUEBLE_INDUSTRIA_ENERGIA_Y_SANITARIOS = "Ind. Energía y Sanitarias";
    public static final String USO_PREDOMINANTE_INMUEBLE_INDUSTRIA_MADERA_Y_MUEBLES = "Ind. Madera y Muebles";
    public static final String USO_PREDOMINANTE_INMUEBLE_INDUSTRIA_METALMECANICA = "Ind. Metalmecánica";
    public static final String USO_PREDOMINANTE_INMUEBLE_INDUSTRIA_PAPEL_E_IMPRENTA = "Ind. Papel e Imprenta";
    public static final String USO_PREDOMINANTE_INMUEBLE_INDUSTRIA_QUIMICA = "Ind. Química";
    public static final String USO_PREDOMINANTE_INMUEBLE_INDUSTRIA_TEXTIL_Y_CUERO = "Ind. Textil y Cuero";
    public static final String USO_PREDOMINANTE_INMUEBLE_INDUSTRIA_OTRAS = "Ind. Otras";
    public static final String USO_PREDOMINANTE_INMUEBLE_TRANSPORTE = "Transporte";
    public static final String USO_PREDOMINANTE_INMUEBLE_COMUNICACIONES = "Comunicaciones";
    public static final String USO_PREDOMINANTE_INMUEBLE_CONSTRUCCION_VIVIENDAS = "Construcción Viviendas";
    public static final String USO_PREDOMINANTE_INMUEBLE_CONSTRUCCION_OTRAS_OBRAS = "Construcción Otras Obras";
    
    
    public static final String CLASE_INUMUEBLE_UNIFAMILIAR = "Unifamiliar";
    public static final String CLASE_INUMUEBLE_BIFAMILIAR = "Bifamiliar";
    public static final String CLASE_INUMUEBLE_MULTIFAMILIAR = "Multifamiliar";
    public static final String CLASE_INUMUEBLE_VIVIENDA_SUBDIVIDIDA = "Viv. Subdividida";
    public static final String CLASE_INUMUEBLE_INDUSTRIAL = "Industrial";
    public static final String CLASE_INUMUEBLE_COMERCIAL = "Comercial";
    public static final String CLASE_INUMUEBLE_OFICINAS = "Ed. Oficinas";
    public static final String CLASE_INUMUEBLE_OTRO = "Otro";
    
    public static final String TIPO_VIVIENDA_VIS = "VIS";
    public static final String TIPO_VIVIENDA_NO_VIS = "No VIS";
    public static final String TIPO_VIVIENDA_VIP = "VIP";
    
    public static final String  UBICACION_INMUEBLE_INTERIOR = "Interior";
    public static final String  UBICACION_INMUEBLE_EXTERIOR = "Exterior";
    
    public static final String  ESTADO_CONSTRUCCION_NUEVO = "Nueva";
    public static final String  ESTADO_CONSTRUCCION_USADO = "Usada";
    
    public static final String ESTADO_CONSERVACION_OPTIMO = "Optimo";
    public static final String ESTADO_CONSERVACION_BUENO = "Bueno";
    public static final String ESTADO_CONSERVACION_REGULAR = "Regular";
    public static final String ESTADO_CONSERVACION_MALO = "Malo";
    public static final String ESTADO_CONSERVACION_DEMOLICION = "Demolición";
    
    public static final String TIPO_LICENCIA_VIS = "VIS";
    public static final String TIPO_LICENCIA_NO_VIS = "No VIS";
    public static final String TIPO_LICENCIA_VIP = "VIP";
    
    public static final String PISO_BODEGA_REFORZADO = "Reforzado";
    public static final String PISO_BODEGA_NO_REFORZAD0 = "No Reforzado"; 
    
    public static final String ESTRUCTURA_MURO_CARGA = "Muro de carga";
    public static final String ESTRUCTURA_MAMPOSTERIA_ESTRUCTURAL = "Mamposteria Estructural";
    public static final String ESTRUCTURA_TRADICIONAL = "Tradicional";
    public static final String ESTRUCTURA_METALICA = "Metalica";
    public static final String ESTRUCTURA_INDSUSTRIALIZADA = "Industrializada";
    public static final String ESTRUCTURA_OTROS	 = "Otros";
    
    public static final String REPARADO_NO_DISPONIBLE = "No disponible";
    public static final String REPARADO_REPARADOS = "Reparados";
    public static final String REPARADO_NO_REPARADOS = "No Reparados";
		
    public static final String CUBIERTA_PLACA_CONCRETO = "Placa concreto imp.";
    public static final String CUBIERTA_TEJA_PLASTICA = "Teja plástica";
    public static final String CUBIERTA_TEJA_BARRO= "Teja de barro";
    public static final String CUBIERTA_TEJA_FIBROCEMENTO = "Teja fibrocemento";
    public static final String CUBIERTA_TEJA_METALICA = "Teja metálica";
    public static final String CUBIERTA_OTROS = "Otros";
    
    public static final String FACHADA_LADRILLO_VISTA = "Ladrillo a la vista";
    public static final String FACHADA_PANETE_PINTURA = "Pañete y pintura";
    public static final String FACHADA_GRANIPLAST = "Graniplast";
    public static final String FACHADA_FLOTANTE = "Flotante";
    public static final String FACHADA_CONCRETO_TEXTURADO = "Concreto Texturado";
    public static final String FACHADA_OTROS = "Otros";
    
    public static final String TIPO_FACHADA_SENCILLA = "De 0 a 3 metros";
    public static final String TIPO_FACHADA_NORMAL = "De 3 a 6 metros";
    public static final String TIPO_FACHADA_LUJOSA = "Mayor a 6 metros";
    
    public static final String ESTRUCTURA_REFORZADA_TRABES_COLADO_SITIO = "Trabes coladas en sitio";
    public static final String ESTRUCTURA_REFORZADA_TRABES_PREFABRICADO= "Trabes prefabricada";
    public static final String ESTRUCTURA_REFORZADA_NO_TIENE_TRABES = "No tiene trabes";
    public static final String ESTRUCTURA_REFORZADA_NO_DISPONIBLE = "No disponible";
    
    public static final String DANOS_PREVIOS_NO_DISPONIBLE = "No disponible";
    public static final String DANOS_PREVIOS_CON_DANOS_PREVIOS = "Con daños previos";
    public static final String DANOS_PREVIOS_SIN_DANOS_PREVIOS = "Sin daños previos";
    
    public static final String MATERIAL_CONSTRUCTOR_ADOBE = "Adobe, bahareque o tapia";
    public static final String MATERIAL_CONSTRUCTOR_MADERA = "Madera";
    public static final String MATERIAL_CONSTRUCTOR_MIXTAS = "Mixtas u Otro";
    public static final String MATERIAL_CONSTRUCTOR_MAMPOSTERIA = "Mampostería";
    public static final String MATERIAL_CONSTRUCTOR_CONCRETO_REFORZADO = "Concreto Reforzado";
    public static final String MATERIAL_CONSTRUCTOR_ACERO = "Acero";
    public static final String MATERIAL_CONSTRUCTOR_NO_EXISTE = "No Existe";
    
    
    public static final String DETALLE_MATERIAL_PANELES_PREFABRICADOS = "Paneles prefabricados";
    public static final String DETALLE_MATERIAL_MAMPOSTERIA_NO_REFORZADA = "Mampostería no reforzada";
    public static final String DETALLE_MATERIAL_BAHAREQUE = "Bahareque";
    public static final String DETALLE_MATERIAL_PORTICOS = "Pórticos";
    public static final String DETALLE_MATERIAL_SISTEMA_DUAL = "Sistema dual o combinado";
    public static final String DETALLE_MATERIAL_RETICULAR_CELULADO = "Reticular celulado";
    public static final String DETALLE_MATERIAL_PORTICOS_ARRIOSTRADOS = "Pórticos arriostrados";
    public static final String DETALLE_MATERIAL_PORTICOS_PANELES_MADERA = "Pórticos y paneles en madera";
    public static final String DETALLE_MATERIAL_ADOBE= "Adobe";
    public static final String DETALLE_MATERIAL_TAPIA = "Tapia";
    public static final String DETALLE_MATERIAL_MUROS = "Muros";
    public static final String DETALLE_MATERIAL_MAPOSTERIA_CONFINADA= "Mampostería confinada";
    public static final String DETALLE_MATERIAL_MAMPOSTERIA_REFORZADA = "Mampostería reforzada";
    public static final String DETALLE_MATERIAL_PORTICOS_NO_ARRIOSTRADOS = "Pórticos no arriostrados";
    public static final String DETALLE_MATERIAL_MAMPOSTERIA_CONFINADA = "Mampostería confinada";
    public static final String DETALLE_MATERIAL_NO_DISPONIBLE = "No disponible";
    
    
    public static final String  IRREGULARIDAD_PLANTA_SIN_IRREGULARIDAD = "Sin irregularidad";
    public static final String  IRREGULARIDAD_PLANTA_NO_DISPONIBILIDAD = "No disponible";
    public static final String  IRREGULARIDAD_PLANTA_CON_IRREGULARIDAD = "Con irregularidad";
    
    public static final String ESTADO_ACABADO_BUENO = "Bueno";
    public static final String ESTADO_ACABADO_REGULAR = "Regular";
    public static final String ESTADO_ACABADO_MALO = "Malo";
    public static final String ESTADO_ACABADO_SIN_ACABADOS = "Sin acabados";
    
    public static final String CALIDAD_ACABADO_SENCILLO = "Sencillo";
    public static final String CALIDAD_ACABADO_NORMAL = "Normal";
    public static final String CALIDAD_ACABADO_LUJOSO = "Lujoso";
    public static final String CALIDAD_ACABADO_SIN_ACABADOS = "Sin Acabados";

    public static final String CALIDAD_ACABADOS_COCINA_SEMI_INTEGRAL = "Semi-Integral";
    public static final String CALIDAD_ACABADOS_COCINA_INTEGRAL = "Integral";
    
    public static final String NO_TIENE = "No Tiene";
    public static final String TIPO_GARAJE_PRIVADO = "Privado";
    public static final String TIPO_GARAJE_EXCLUSIVO = "Exclusivo";
    public static final String TIPO_GARAJE_COMUNAL = "Comunal";
    public static final String SELECCCIONE = "Seleccione ...";
    
    public static final String CALIFICACION_GARANTIA_FAVORABLE = "Favorable";
    public static final String CALIFICACION_GARANTIA_DESFAVORABLE = "Desfavorable";
    public static final String CALIFICACION_GARANTIA_NO_APLICA = "No Aplica";
    public static final String FAVORABLE_CON_LIMITACIONES = "Favorable con Limitaciones";
    
    public static final String NO_APLICA = "N/A";
    public static final String SIN_INFORMACION = "Sin información";
    
    //constantes para acion de WS
    public static final String ACCION_REGISTRAR = "REGISTRAR";
    public static final String ACCION_ACTUALIZAR = "ACTUALIZAR";
    public static final String ACCION_ACTUALIZAR_ID_SISGEN = "ACTUALIZAR_ID_SISGEN";
    
    public enum FormatoInforme {
        FormatoInformeHipotecario("Formato hipotecario", 1)/*,
        FormatoInformeComercial("Formato comercial", 2)*/;
        private final String value;
		private final int key;

		FormatoInforme(String value, int key) {
			this.value = value;
			this.key = key;
		}

		public String value() {
			return value;
		}

		public int key() {
			return key;
		}

		@Override
		public String toString() {
			return value();
		}

		public static FormatoInforme fromKey(int key) {
			switch (key) {
			case 1:
				return FormatoInformeHipotecario;
			/* case 2: return FormatoInformeComercial; */
			default:
				return null;
			}
		}
	}
    
	public enum TipoDocumento {
		CC("Cedula de ciudadania", "CC", 21), CE("Cedula de extrangeria", "CE", 22), NIT("Nit", "NIT", 23);

		private final String label;
		private final String stringValue;
		private final int key;

		TipoDocumento(String label, String stringValue, int key) {
			this.label = label;
			this.stringValue = stringValue;
			this.key = key;
		}

		public String label() {
			return label;
		}

		public String stringValue() {
			return stringValue;
		}

		public int key() {
			return key;
		}

		public static Integer keyByStringValue(String stringValue) {

			if (TipoDocumento.CC.stringValue().equals(stringValue)) {
				return TipoDocumento.CC.key;
			} else if (TipoDocumento.CE.stringValue().equals(stringValue)) {
				return TipoDocumento.CE.key;
			} else if (TipoDocumento.NIT.stringValue().equals(stringValue)) {
				return TipoDocumento.NIT.key;
			}

			return null;
		}

	}

}
