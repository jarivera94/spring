package com.helio4.bancol.avaluos.controlador;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import com.helio4.bancol.avaluos.dominio.AprobarAvaluoController;
import com.helio4.bancol.avaluos.dominio.AvaluoController;
import com.helio4.bancol.avaluos.dominio.Comparador;
import com.helio4.bancol.avaluos.dominio.EditarClienteController;
import com.helio4.bancol.avaluos.dominio.EstadoAvaluoController;
import com.helio4.bancol.avaluos.dominio.FitoCorviniController;
import com.helio4.bancol.avaluos.dominio.FotografiasController;
import com.helio4.bancol.avaluos.dominio.InformeHipotecarioController;
import com.helio4.bancol.avaluos.dominio.ListasController;
import com.helio4.bancol.avaluos.dominio.ListasGeograficasController;
import com.helio4.bancol.avaluos.dominio.ModificacionController;
import com.helio4.bancol.avaluos.dominio.ParametrosController;
import com.helio4.bancol.avaluos.dominio.TiposAvaluoController;
import com.helio4.bancol.avaluos.dominio.gmap.GLatLng;
import com.helio4.bancol.avaluos.dto.AreaDTO;
import com.helio4.bancol.avaluos.dto.AreaDTO.DescripcionAreaNoPH;
import com.helio4.bancol.avaluos.dto.AreaDTO.DescripcionAreaPH;
import com.helio4.bancol.avaluos.dto.AreaDTO.TipoArea;
import com.helio4.bancol.avaluos.dto.AvaluoDTO;
import com.helio4.bancol.avaluos.dto.AvaluoDTO.ListaCalificacionGarantia;
import com.helio4.bancol.avaluos.dto.AvaluoDTO.ListaSector;
import com.helio4.bancol.avaluos.dto.AvaluoHipotecarioDTO;
import com.helio4.bancol.avaluos.dto.AvaluoRemateDTO;
import com.helio4.bancol.avaluos.dto.ClienteDTO;
import com.helio4.bancol.avaluos.dto.ComparacionMercadoLoteConstruccionRentaDTO;
import com.helio4.bancol.avaluos.dto.ComparacionMercadoLoteConstruccionVentaDTO;
import com.helio4.bancol.avaluos.dto.ComparacionMercadoLoteVentaDTO;
import com.helio4.bancol.avaluos.dto.ComparacionMercadoNOPHDTO;
import com.helio4.bancol.avaluos.dto.ComparacionMercadoPHDTO;
import com.helio4.bancol.avaluos.dto.ComparacionMercadoPHRentaDTO;
import com.helio4.bancol.avaluos.dto.ComparacionMercadoPHVentaDTO;
import com.helio4.bancol.avaluos.dto.DivipolaDTO;
import com.helio4.bancol.avaluos.dto.EntidadDTO;
import com.helio4.bancol.avaluos.dto.FormatoInformeHipotecarioDTO;
import com.helio4.bancol.avaluos.dto.FormatoInformeHipotecarioDTO.ListaCalidadAcabados;
import com.helio4.bancol.avaluos.dto.FormatoInformeHipotecarioDTO.ListaCalidadAcabadosCocina;
import com.helio4.bancol.avaluos.dto.FormatoInformeHipotecarioDTO.ListaClaseInmueble;
import com.helio4.bancol.avaluos.dto.FormatoInformeHipotecarioDTO.ListaCubierta;
import com.helio4.bancol.avaluos.dto.FormatoInformeHipotecarioDTO.ListaDanoPrevio;
import com.helio4.bancol.avaluos.dto.FormatoInformeHipotecarioDTO.ListaDetalleMaterial;
import com.helio4.bancol.avaluos.dto.FormatoInformeHipotecarioDTO.ListaEstado;
import com.helio4.bancol.avaluos.dto.FormatoInformeHipotecarioDTO.ListaEstadoConservacion;
import com.helio4.bancol.avaluos.dto.FormatoInformeHipotecarioDTO.ListaEstadoConstruccion;
import com.helio4.bancol.avaluos.dto.FormatoInformeHipotecarioDTO.ListaEstadosAcabados;
import com.helio4.bancol.avaluos.dto.FormatoInformeHipotecarioDTO.ListaEstructura;
import com.helio4.bancol.avaluos.dto.FormatoInformeHipotecarioDTO.ListaEstructuraReforzada;
import com.helio4.bancol.avaluos.dto.FormatoInformeHipotecarioDTO.ListaFachada;
import com.helio4.bancol.avaluos.dto.FormatoInformeHipotecarioDTO.ListaIrregularidad;
import com.helio4.bancol.avaluos.dto.FormatoInformeHipotecarioDTO.ListaMaterialConstructor;
import com.helio4.bancol.avaluos.dto.FormatoInformeHipotecarioDTO.ListaPisoBodega;
import com.helio4.bancol.avaluos.dto.FormatoInformeHipotecarioDTO.ListaReparado;
import com.helio4.bancol.avaluos.dto.FormatoInformeHipotecarioDTO.ListaTipoFachada;
import com.helio4.bancol.avaluos.dto.FormatoInformeHipotecarioDTO.ListaTipoLicencia;
import com.helio4.bancol.avaluos.dto.FormatoInformeHipotecarioDTO.ListaTipoVivienda;
import com.helio4.bancol.avaluos.dto.FormatoInformeHipotecarioDTO.ListaTopografiaSector;
import com.helio4.bancol.avaluos.dto.FormatoInformeHipotecarioDTO.ListaUbicacionInmueble;
import com.helio4.bancol.avaluos.dto.FormatoInformeHipotecarioDTO.ListaUsoActual;
import com.helio4.bancol.avaluos.dto.FormatoInformeHipotecarioDTO.ListaUsoPredominanteInmueble;
import com.helio4.bancol.avaluos.dto.FotografiaDTO;
import com.helio4.bancol.avaluos.dto.GarajeDTO;
import com.helio4.bancol.avaluos.dto.GarajeDTO.ListaTipoGaraje;
import com.helio4.bancol.avaluos.dto.MatriculaDTO;
import com.helio4.bancol.avaluos.dto.MetodoValuacionDTO;
import com.helio4.bancol.avaluos.dto.MetodoValuacionDTO.MetodoValuacionEnum;
import com.helio4.bancol.avaluos.dto.ModificacionDTO;
import com.helio4.bancol.avaluos.dto.SegmentoDTO;
import com.helio4.bancol.avaluos.dto.SubsidioDTO;
import com.helio4.bancol.avaluos.dto.TipoAvaluoDTO;
import com.helio4.bancol.avaluos.dto.TipoInmuebleDTO;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;
import com.helio4.bancol.avaluos.dto.UvrDTO;
import com.helio4.bancol.avaluos.modelo.Motivo;
import com.helio4.bancol.avaluos.servicio.datos.MatriculaService;
import com.helio4.bancol.avaluos.servicio.datos.MotivoService;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.ClienteNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.TarifaNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.TiempoComercializacionNotFoundException;
import com.helio4.bancol.avaluos.servicio.util.Constantes;
import com.helio4.bancol.avaluos.servicio.util.Constantes.Estado;
import com.helio4.bancol.avaluos.servicio.util.MapUtils;
import com.helio4.bancol.avaluos.servicio.util.PDFDigitalSign;

@Controller
@Scope("view")
@Qualifier("informeHipotecarioBean")
public class InformeHipotecarioBean implements Serializable {

	/*
	 * public static final Integer METODO_VALUACION_REPOSICION = 23; public static
	 * final Integer METODO_VALUACION_COMPARACION_MERCADO = 21;
	 */

	private static final long serialVersionUID = 8162462580526975454L;
	private static Logger log = LoggerFactory.getLogger(InformeHipotecarioBean.class);
	private static final Integer[] numerosGarajes = { 1, 2, 3, 4, 5 };

	private AvaluoDTO avaluoHipotecarioDTO;
	private AvaluoDTO avaluoHipotecarioDTOClone;
	private FormatoInformeHipotecarioDTO informeHipotecarioDTO;
	private String departamentoInmueble;
	private String departamentoCliente;
	private SortedMap<String, String> departamentos;
	private List<DivipolaDTO> ciudadesSolicitante;
	private List<DivipolaDTO> ciudadesNotaria;
	private List<DivipolaDTO> ciudadesInmueble;
	private SortedMap<String, String> paises;
	private String centroMapa;
	private double lat;
	private double lng;
	private List<EntidadDTO> entidades;
	private List<TipoAvaluoDTO> tiposAvaluo;
	private List<TipoInmuebleDTO> tiposInmueble;
	private List<String> sucursales;
	private List<SegmentoDTO> segmentos;

	private String tipoViaSolicitante;
	private String numeroViaSolicitante;
	private List<String> complementoViaSolicitante;
	private String numeroViaGeneradoraSolicitante;
	private List<String> complementoViaGeneradoraSolicitante;
	private String placaSolicitante;
	private String complementoPlacaSolicitante;
	private String adicionalDireccionSolicitante;

	private List<String> complementoVia;
	private List<String> complementoViaGeneradora;
	private String direccionCompleta;

	private boolean nuevaArea;
	private AreaDTO areaDTO;
	private List<AreaDTO> listaAreas;
	private List<AreaDTO> areasConstruidasNoPH;
	private List<AreaDTO> areasPisos;
	private List<AreaDTO> areasGarajes;
	private List<AreaDTO> areasDepositos;

	private BigDecimal subTotalHonorarios;
	private BigDecimal ivaHonorarios;
	private BigDecimal totalHonorarios;

	private UvrDTO uvr;

	private boolean entidadBancolombia;
	private boolean avaluoEnRevision;
	private boolean avaluoEnCorrecion;
	private boolean mostrarCorrecciones;
	private String correciones;

	private boolean modificarInforme;
	private boolean enviarInforme;
	private boolean esMixtoUsoPredominante;

	private String path;

	private UsuarioDTO usuarioActivo;

	private Date fechaElaboracionInforme;
	private MapModel mapModel;

	private List<AvaluoDTO> avaluosAnteriores;

	private ListaDesplegable[] descripcionAreas;

	private List<FotografiaDTO> fotografias;

	private boolean nuevoMetodoValuacion;
	private MetodoValuacionDTO metodoValuacion;
	private List<MetodoValuacionDTO.MetodoValuacionEnum> metodosValuacionDisponibles;

	private final String[] letrasComplemento = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
			"O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

	private final String[] puntosCardinales = { "Norte", "Sur", "Este", "Oeste", "Bis", "Noreste", "Sureste", "Noroeste",
			"Suroeste", "Par", "Impar" };

	private final Integer[] numerosMatriculas = { 1, 2, 3, 4 };

	private final String[] valoresUnidadAlterno = { "Manzana", "Bloque", "Interior", "Edificio", "Torre", "Apartamento",
			"Casa", "Oficina", "Bodega", "Suite", "Local", "Garaje", "Consultorio", "Deposito", "Etapa", "Piso", "Nivel",
			"Agrupacion", "Unidad", "Tipo", "Sector", "Lote", "Superlote", "Conjunto", "Vivienda", "Módulo" };
	private final NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault());
	private AreaDTO areaTerrenoDTO;
	private AreaDTO areaPrivada;
	private List<AreaDTO> areasPrivadas;
	private ArrayList<BigDecimal> calificaciones;

	private FitoCorviniController fitoCorviniController = new FitoCorviniController();

	/**
	 * Variable que indica si se debe mostrar la información del método de
	 * reposición(fito y corvini).
	 */
	private boolean mostrarMetodoReposicion = Boolean.FALSE;
	private boolean mostrarMetodoComparacionMercadoPHVenta = Boolean.FALSE;
	private boolean mostrarMetodoComparacionMercadoPHRenta = Boolean.FALSE;
	private boolean mostrarMetodoComparacionMercadoLoteVentaSinContruccion = Boolean.FALSE;
	private boolean mostrarMetodoComparacionMercadoLoteVentaConContruccion = Boolean.FALSE;
	private boolean mostrarMetodoComparacionMercadoLoteRentaConContruccion = Boolean.FALSE;
	private boolean mostrarAreasLotePH = Boolean.TRUE;

	private boolean accionAgregarMetodologia = Boolean.FALSE;
	private boolean mostrarMensajeValidacionSujetoPHVenta;
	private boolean mostrarMensajeValidacionSujetoPHRenta;
	private boolean mostrarMensajeValidacionSujetoLoteVenta;
	private boolean mostrarMensajeValidacionSujetoLoteconstruccionVenta;
	private boolean mostrarMensajeValidacionSujetoLoteconstruccionRenta;

	private boolean mostrarObservacionAlturaPermitida;
	private boolean mostrarObservacionAislamientoPosterior;
	private boolean mostrarObservacionAislamientoLateral;
	private boolean mostrarObservacionAnteJardin;
	private boolean mostrarObservacionIndiceOcupacion;
	private boolean mostrarObservacionIndiceConstruccion;

	/**
	 * Variable auxiliar para mostrar/ocultar el boton enviar notificacion de
	 * honorarios cuando no es cobrado por bancol
	 */
	private boolean cobradoPorBancol;
	private boolean avaluoRemate;
	/**
	 * Metodología que se encarga de manejar comparación de mercado ya sea para ph o
	 * no ph.
	 */
	private MetodoValuacionDTO comparacionMercado = null;
	private BigDecimal sumatoria = BigDecimal.ZERO;
	private Boolean errorDireccionInmueble = Boolean.FALSE;
	private boolean coordenadasValidas = false;

	private final ComparacionMercadoBean comparacionMercadoBean;

	@Autowired
	private ComparacionMercadoPHVentaBean comparacionMercadoPHVentaBean;

	@Autowired
	private ComparacionMercadoPHRentaBean comparacionMercadoPHRentaBean;

	@Autowired
	private ComparacionMercadoLoteVentaBean comparacionMercadoLoteVentaBean;

	@Autowired
	private ComparacionMercadoLoteConstruccionVentaBean comparacionMercadoLoteConstruccionVentaBean;

	@Autowired
	private ComparacionMercadoLoteConstruccionRentaBean comparacionMercadoLoteConstruccionRentaBean;

	@Autowired
	private MatriculaService matriculaService;

	private final AvaluoController avaluoController;

	private final InformeHipotecarioController informeHipotecarioController;

	private final ListasGeograficasController listasGeograficasController;

	private final ListadoAvaluosBean listadoAvaluosBean;

	private final TiposAvaluoController tiposAvaluoController;

	private final ListasController listasController;

	private final FotografiasController fotografiasController;

	private final AprobarAvaluoController aprobarAvaluoController;

	private final ModificacionController modificacionController;

	private final EstadoAvaluoController estadoAvaluoController;

	private final ParametrosController parametrosController;

	private final EditarClienteController clienteController;

	private final MotivoService motivoService;

	/**
	 * Metodo seleccionado a ser eliminado.
	 */
	private MetodoValuacionDTO metodoValuacionEliminar;

	/**
	 * Tipo de garaje seeleccionado.
	 */
	private ListaTipoGaraje tipoGaraje;

	/** Cliente asociado al avaluo actual */
	private ClienteDTO clienteActual;

	private boolean clienteNuevo = Boolean.FALSE;
	private boolean coordenadasAjustadas = false;

	private Motivo motivo;

	@Autowired
	public InformeHipotecarioBean(AvaluoController avaluoController, ComparacionMercadoBean comparacionMercadoBean,
			InformeHipotecarioController informeHipotecarioController,
			ListasGeograficasController listasGeograficasController, ListadoAvaluosBean listadoAvaluosBean,
			TiposAvaluoController tiposAvaluoController, ParametrosController parametrosController,
			ListasController listasController, FotografiasController fotografiasController,
			AprobarAvaluoController aprobarAvaluoController, ModificacionController modificacionController,
			EditarClienteController clienteController, EstadoAvaluoController estadoAvaluoController,
			MessageSource messageSource, MotivoService motivoService) {
		this.avaluoController = avaluoController;
		this.comparacionMercadoBean = comparacionMercadoBean;
		this.informeHipotecarioController = informeHipotecarioController;
		this.listasGeograficasController = listasGeograficasController;
		this.listadoAvaluosBean = listadoAvaluosBean;
		this.tiposAvaluoController = tiposAvaluoController;
		this.parametrosController = parametrosController;
		this.listasController = listasController;
		this.fotografiasController = fotografiasController;
		this.aprobarAvaluoController = aprobarAvaluoController;
		this.modificacionController = modificacionController;
		this.clienteController = clienteController;
		this.estadoAvaluoController = estadoAvaluoController;
		this.motivoService = motivoService;
	}

	/**
	 * Carga completamente el avaluo desde la base de datos Obtiene el formato de
	 * informe del avaluo y lo clona para poder identificar las modificaciones que
	 * se hagan una vez el avaluo este aprobado. Inicializa las listas desplegables
	 * usadas en el la pagina hipotecario.xhtml
	 */
	@PostConstruct
	public void init() {
		log.debug("Inicializando InformeHipotecarioBean");
		/*
		 * String sessionId =
		 * RequestContextHolder.currentRequestAttributes().getSessionId();
		 * log.info("SessionId {}", sessionId);
		 */
		usuarioActivo = (UsuarioDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		avaluoHipotecarioDTO = listadoAvaluosBean.getAvaluo();

		avaluoHipotecarioDTO = informeHipotecarioController.encontrarAvaluo(avaluoHipotecarioDTO.getId());

		comprobarPermisosEstadoAvaluo();
		// modificarInforme =
		// informeHipotecarioController.bloquearInforme(sessionId,
		// avaluoHipotecarioDTO.getId());

		informeHipotecarioDTO = (FormatoInformeHipotecarioDTO) avaluoHipotecarioDTO.getFormatoInforme();
		accionAgregarMetodologia = avaluoHipotecarioDTO.getMetodosValuacion() != null
				&& !avaluoHipotecarioDTO.getMetodosValuacion().isEmpty() ? true : false;
		this.cargarMetodologiasSugeridas();
		this.cargarAreasLotePH();
		avaluoRemate = avaluoHipotecarioDTO.getClass().equals(AvaluoRemateDTO.class);

		// Obtener datos para ser mostrados en la pagina
		fechaElaboracionInforme = informeHipotecarioController.fechaElaboracionInforme(avaluoHipotecarioDTO);

		if (avaluoRemate) {
			setCobradoPorBancol(true);
		} else {
			separarDireccion(avaluoHipotecarioDTO.getCliente().getDireccionDeContactoSolicitante());
			setCobradoPorBancol(avaluoController.esCobradoPorBancol(avaluoHipotecarioDTO));
		}

		cargarListasParametros();

		verificarDireccionInmueble();

		/** Inicializar la colección de areas del avaluo si es nulo */
		if (avaluoHipotecarioDTO.getAreas() == null) {
			Set<AreaDTO> areas = new HashSet<>();
			avaluoHipotecarioDTO.setAreas(areas);
		}
		this.areaDTO = new AreaDTO();
		separarAreas();
		inciarDescripcionAreas();

		/** Cargar los avaluos anteriores del cliente desde la base de datos */
		avaluosAnteriores = avaluoController.obtenerAvaluosAnteriores(
				avaluoHipotecarioDTO.getCliente().getTipoDocumentoIdentificacion(),
				avaluoHipotecarioDTO.getCliente().getNumeroDocumento(), avaluoHipotecarioDTO.getId());
		if (informeHipotecarioDTO.getPorcentajeAvance() == null) {
			informeHipotecarioDTO.setPorcentajeAvance(0);
		}

		if (informeHipotecarioDTO.getDepositosExclusivos() == null) {
			informeHipotecarioDTO.setDepositosExclusivos(0);
		}
		if (informeHipotecarioDTO.getDepositosPrivados() == null) {
			informeHipotecarioDTO.setDepositosPrivados(0);
		}

		if (informeHipotecarioDTO.getEstrato() == null) {
			informeHipotecarioDTO.setEstrato(1);
		}

		uvr = informeHipotecarioController.getUvrActual();
		if (uvr != null) {
			avaluoHipotecarioDTO.setValorUvr(uvr.getValor());
		} else {
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Error al cargar valor de UVR",
					"Contacte con el administrador del sistema para corregir los valores de UVR."));
		}
		if (avaluoEnCorrecion) {
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('correcionesDialog').show();");
		}
		fotografias = fotografiasController.buscarFotografiasAvaluo(avaluoHipotecarioDTO);

		this.calificaciones = new ArrayList<>();
		for (double i = 0; i <= 5; i += 0.5) {
			BigDecimal value = new BigDecimal(i);
			value = value.setScale(1);
			this.calificaciones.add(value);
		}
		this.cargarMetodosValuacion();
		this.cargarGarajes();
		path = parametrosController.obtenerValor("rutaReportes");

		validarCambiosCamposSujeto(comparacionMercadoPHVentaBean.getMetodoValuacion());
		validarCambiosCamposSujeto(comparacionMercadoPHRentaBean.getMetodoValuacion());
		validarCambiosCamposSujeto(comparacionMercadoLoteVentaBean.getMetodoValuacion());
		validarCambiosCamposSujeto(comparacionMercadoLoteConstruccionVentaBean.getMetodoValuacion());
		validarCambiosCamposSujeto(comparacionMercadoLoteConstruccionRentaBean.getMetodoValuacion());

		try {
			if (this.avaluoHipotecarioDTO != null && this.avaluoHipotecarioDTO instanceof AvaluoHipotecarioDTO) {
				avaluoHipotecarioDTOClone = (AvaluoHipotecarioDTO) avaluoHipotecarioDTO.clone();
			} else if (this.avaluoHipotecarioDTO != null && this.avaluoHipotecarioDTO instanceof AvaluoRemateDTO) {
				avaluoHipotecarioDTOClone = (AvaluoRemateDTO) avaluoHipotecarioDTO.clone();
			}
		} catch (Exception e) {
			log.error("No se pudo clonar el objeto avaluoHipotecarioDTO", e);
		}
		try {
			this.clienteActual = (ClienteDTO) this.avaluoHipotecarioDTO.getCliente().clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		validarCamposNormasSuelo(false);
		cargarMotivo();
	}

	public void cargarMotivo() {
		motivo = motivoService.getMotivosById(this.avaluoHipotecarioDTO.getMotivo().longValue());
	}

	/*
	 * public void desbloquearInforme() {
	 * informeHipotecarioController.desBloquearInforme(avaluoHipotecarioDTO.
	 * getId()); }
	 */

	private void verificarDireccionInmueble() {
		/** En todos los casos se debe inicialiar el mapa */
		mapModel = new DefaultMapModel();
		/** Carga el departamento del inmueble y las ciudades */
		departamentoInmueble = (avaluoHipotecarioDTO.getDivipolaInforme() != null
				&& avaluoHipotecarioDTO.getDivipolaInforme().getDepartamento() != null)
						? avaluoHipotecarioDTO.getDivipolaInforme().getDepartamento()
						: null;
		if (departamentoInmueble != null) {
			ciudadesInmueble = listasGeograficasController.ciudadesPorDepartamento(departamentoInmueble);
		}
		complementoVia = avaluoHipotecarioDTO.getComplementoViaInforme() != null
				? convertirALista(avaluoHipotecarioDTO.getComplementoViaInforme())
				: new ArrayList<>();
		complementoViaGeneradora = avaluoHipotecarioDTO.getComplementoViaGeneradoraInforme() != null
				? convertirALista(avaluoHipotecarioDTO.getComplementoViaGeneradoraInforme())
				: new ArrayList<>();
		cargarMapa();
	}

	private void cargarMapa() {
		LatLng coordenadas = (avaluoHipotecarioDTO.getLatitud() == null || avaluoHipotecarioDTO.getLongitud() == null)
				? coordenadasDeUbicacion()
				: new LatLng(avaluoHipotecarioDTO.getLatitud().doubleValue(), avaluoHipotecarioDTO.getLongitud().doubleValue());
		Marker marker = new Marker(coordenadas, "Codigo solicitud: " + avaluoHipotecarioDTO.getCodigoExterno());
		marker.setDraggable(true);
		mapModel.addOverlay(marker);

		if (coordenadas != null) {
			centroMapa = Double.toString(coordenadas.getLat()) + ", " + Double.toString(coordenadas.getLng());
		}
	}

	/**
	 * Inicializar listas de parametros desde la base de datos
	 */
	private void cargarListasParametros() {
		/** Inicializar las listas de departamento y municipio del cliente */
		if (avaluoHipotecarioDTO.getCliente().getDivipola() != null) {
			departamentoCliente = avaluoHipotecarioDTO.getCliente().getDivipola().getDepartamento();
			ciudadesSolicitante = listasGeograficasController.ciudadesPorDepartamento(departamentoCliente);
		}
		entidades = avaluoController.entidades();
		segmentos = listasController.segmentosPorEntidad(avaluoHipotecarioDTO.getEntidad().getId());
		tiposAvaluo = tiposAvaluoController.tiposAvaluo();
		tiposInmueble = listasController.tiposInmueble();
		sucursales = avaluoController.sucursales();
		departamentos = listasGeograficasController.departamentos();
		paises = listasGeograficasController.paises();
		if (informeHipotecarioDTO.getCiudadNotaria() != null) {
			onDepartamentoNotariaChanged();
		}

		/*
		 * diferenciaFormulaSencilla = new
		 * BigDecimal(Float.parseFloat(parametrosController.
		 * obtenerValor("Diferencia Fórmula Sencilla"))); porcentajeFormulaSencilla =
		 * new BigDecimal(Float.parseFloat(parametrosController.
		 * obtenerValor("Porcentaje Fórmula Sencilla"))); diferenciaFormulaTabla = new
		 * BigDecimal(Float.parseFloat(parametrosController.
		 * obtenerValor("Diferencia Fórmula Tabla"))); porcentajeFormulaTabla = new
		 * BigDecimal(Float.parseFloat(parametrosController.
		 * obtenerValor("Porcentaje Fórmula Tabla")));
		 * 
		 * diferenciaFormulaSencilla.setScale(2, RoundingMode.HALF_EVEN);
		 * diferenciaFormulaSencilla.setScale(2, RoundingMode.HALF_EVEN);
		 * diferenciaFormulaSencilla.setScale(2, RoundingMode.HALF_EVEN);
		 * diferenciaFormulaSencilla.setScale(2, RoundingMode.HALF_EVEN);
		 */
	}

	/**
	 * Comprueba el estado del avaluo y los permisos del usuario que ingresa, y de
	 * acuerdo a esto ajusta las variables
	 * <ul>
	 * <li>{@link #modificarInforme}: define si se muestra o se oculta el botón de
	 * guardar y atoguardado del informe.
	 * <li>{@link #avaluoEnRevision} define si el avaluo esta en revisión.
	 * <li>{@link #avaluoEnCorrecion} define si el avaluo esta en correción.
	 * <li>{@link #mostrarCorrecciones} define si se deben mostrar las correciones
	 * solicitadas al perito.
	 * </ul>
	 * Adicionalmente si se deben mostrar las correciones se almacenan las
	 * correciones en la variable {@link #correciones}.
	 */
	private void comprobarPermisosEstadoAvaluo() {
		if (avaluoHipotecarioDTO.getEstado().getEstado().equals(Constantes.Estado.EnProceso)
				|| avaluoHipotecarioDTO.getEstado().getEstado().equals(Constantes.Estado.PendienteDocumentos)) {
			if (listadoAvaluosBean.isIngresarInforme()) {
				modificarInforme = true;
				return;
			}
		}
		if ((avaluoHipotecarioDTO.getEstado().getEstado().equals(Constantes.Estado.Enviado)
				|| avaluoHipotecarioDTO.getEstado().getEstado().equals(Constantes.Estado.ParaComite))) {
			if ((listadoAvaluosBean.isComite() || listadoAvaluosBean.isRevisarAvaluos())) {
				avaluoEnRevision = true;
				modificarInforme = true;
				cargarCorrecciones();
				return;
			} else {
				redireccionarAvisoPermisos();
				return;
			}
		}
		if (avaluoHipotecarioDTO.getEstado().getEstado().equals(Constantes.Estado.Correciones)) {
			if (listadoAvaluosBean.isCorregirInforme()) {
				avaluoEnCorrecion = true;
				modificarInforme = true;
				cargarCorrecciones();
				return;
			} else {
				redireccionarAvisoPermisos();
				return;
			}
		}
		if (avaluoHipotecarioDTO.getEstado().getEstado().equals(Constantes.Estado.Aprobado)) {
			if (listadoAvaluosBean.isEditarAvaluoDespuesDeAprobado()) {
				modificarInforme = true;
				return;
			} else {
				redireccionarAvisoPermisos();
				return;
			}
		}
		redireccionarAvisoPermisos();
	}

	private void cargarCorrecciones() {
		correciones = (avaluoHipotecarioDTO.getEstado().getJustificacionRechazo() == null)
				? estadoAvaluoController.buscarUltimasCorreccionesSolicitadas(avaluoHipotecarioDTO.getId())
				: avaluoHipotecarioDTO.getEstado().getJustificacionRechazo();
		if ("".equals(correciones)) {
			correciones = "No hay correcciones pendientes";
		}
	}

	private void redireccionarAvisoPermisos() {

		String uri = FacesContext.getCurrentInstance().getExternalContext()
				.encodeActionURL(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()
						+ "/pages/informes/error_informe.xhtml");
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(uri);
		} catch (IOException e) {
			// TODO Reireccionar a página no encontrada
			e.printStackTrace();
		}
	}

	public void onDireccionUsuarioChanged() {
		avaluoHipotecarioDTO.setComplementoVia(convertirAString(complementoVia));
		avaluoHipotecarioDTO.setComplementoViaGeneradora(convertirAString(complementoViaGeneradora));
		direccionCompleta = avaluoHipotecarioDTO.getTipoVia()
				.concat(" " + avaluoHipotecarioDTO.getNumeroVia() == null ? ""
						: " " + avaluoHipotecarioDTO.getNumeroVia().split("_")[0])
				.concat(avaluoHipotecarioDTO.getComplementoVia() == null ? "" : " " + avaluoHipotecarioDTO.getComplementoVia())
				.concat(avaluoHipotecarioDTO.getNumeroViaGeneradora() == null ? ""
						: " # " + avaluoHipotecarioDTO.getNumeroViaGeneradora().split("_")[0])
				.concat(avaluoHipotecarioDTO.getComplementoViaGeneradora() == null ? ""
						: " " + avaluoHipotecarioDTO.getComplementoViaGeneradora())
				.concat(avaluoHipotecarioDTO.getPlaca() == null ? "" : " - " + avaluoHipotecarioDTO.getPlaca())
				.concat(avaluoHipotecarioDTO.getAdicionalDireccion() == null ? ""
						: " " + avaluoHipotecarioDTO.getAdicionalDireccion());
		if (!"".equals(direccionCompleta)) {
			if (direccionCompleta.contains("null")) {
				direccionCompleta.split("null")[0].trim();
			}
		}
	}

	private LatLng coordenadasDeUbicacion() {
		GLatLng gLatLng = avaluoController.calcularCoordenadasAvaluo(avaluoHipotecarioDTO, departamentoInmueble, true);

		LatLng latLng = null;

		if (gLatLng != null) {
			System.out.println(
					"InformeHipotecarioBean.coordenadasDeUbicacion Lat: " + gLatLng.getLat() + " Lng: " + gLatLng.getLng());
			avaluoHipotecarioDTO.setLatitud(new BigDecimal(gLatLng.getLat()));
			avaluoHipotecarioDTO.setLongitud(new BigDecimal(gLatLng.getLng()));
			latLng = new LatLng(gLatLng.getLat(), gLatLng.getLng());
		} else {
			System.out.println("InformeHipotecarioBean.coordenadasDeUbicacion Default");
			latLng = new LatLng(4.645370, -74.094915);
		}
		return latLng;
	}

	private void separarAreas() {
		listaAreas = new ArrayList<>(avaluoHipotecarioDTO.getAreas());
		Collections.sort(listaAreas);
		/** Inicializar la colecciones de areas del avaluo */
		this.areasPrivadas = new ArrayList<>();
		this.areasConstruidasNoPH = new ArrayList<>();
		this.areasPisos = new ArrayList<>();
		this.areasGarajes = new ArrayList<>();
		this.areasDepositos = new ArrayList<>();
		for (AreaDTO area : avaluoHipotecarioDTO.getAreas()) {
			if (area.getDescripcionNumerica() != null) {
				if (area.getDescripcionNumerica().equals(DescripcionAreaPH.AreaPrivada)
						&& informeHipotecarioDTO.getSometidoAPropiedadHorizontal() != null
						&& informeHipotecarioDTO.getSometidoAPropiedadHorizontal()) {
					areaPrivada = area;
					// verificacion para las areas ingresadas manualemente, para
					// que
					// no se muestre en fito y corvini.
					if (area.getDescripcion() != null && !area.getDescripcion().isEmpty()) {
						this.areasPrivadas.add(areaPrivada);
					}

				} else if (area.getDescripcionNumerica().equals(DescripcionAreaPH.Garaje)) {
					areasGarajes.add(area);
				} else if (area.getDescripcionNumerica().equals(DescripcionAreaPH.Deposito)) {
					areasDepositos.add(area);
				} else if (area.getDescripcionNumerica().equals(DescripcionAreaNoPH.AreaConstruccion)) {
					// verificacion realizada para fito y corvini,para que las
					// areas
					// para que no se muestren las areas agregadas manualmente.
					if (area.getDescripcion() != null && !area.getDescripcion().isEmpty()) {
						areasConstruidasNoPH.add(area);
					}
					if (area.getDescripcion() != null && area.getDescripcion().matches("[0-9]+")) {
						areasPisos.add(area);
					}
				} else if (area.getDescripcionNumerica().equals(DescripcionAreaNoPH.AreaTerreno)
						&& TipoArea.Terreno.equals(area.getTipoArea()) && area.getNombre().equals("Area de terreno")) {
					areaTerrenoDTO = area;
				}
			}
		}

		if (areaTerrenoDTO == null && (informeHipotecarioDTO.getSometidoAPropiedadHorizontal() == null
				|| !informeHipotecarioDTO.getSometidoAPropiedadHorizontal())) {
			// agregar area de terreno
			listaAreas.add(crearAreaDeTerreno());
		}
		calcularValores();
	}

	private AreaDTO crearAreaDeTerreno() {
		areaTerrenoDTO = new AreaDTO();
		areaTerrenoDTO.setNombre("Area de terreno");
		areaTerrenoDTO.setPorcentaje(BigDecimal.ZERO);
		areaTerrenoDTO.setAvaluoId(avaluoHipotecarioDTO.getId());
		areaTerrenoDTO.setTipoArea(TipoArea.Terreno);
		areaTerrenoDTO.setDescripcion(Integer.toString(1));
		areaTerrenoDTO.setDescripcionNumerica(DescripcionAreaNoPH.AreaTerreno);
		return areaTerrenoDTO;
	}

	public void cambioDeTab(TabChangeEvent event) {
		if (nuevaArea) {
			cancelarNuevaArea();
			RequestContext.getCurrentInstance().update("informeHipotecario:accordionPanel:valorAvaluo");
		}
		if (nuevoMetodoValuacion) {
			this.cancelarMetodoValuacion();
			RequestContext.getCurrentInstance().update("informeHipotecario:accordionPanel:metodosDeValuacion");
		}
		this.sumatoria = BigDecimal.ZERO;
		RequestContext.getCurrentInstance().scrollTo(event.getTab().getClientId());
		RequestContext.getCurrentInstance().addCallbackParam("clientId", event.getTab().getClientId());
	}

	public void onDepartamentoSolicitanteChanged() {
		ciudadesSolicitante = listasGeograficasController.ciudadesPorDepartamento(departamentoCliente);
	}

	public void onDepartamentoNotariaChanged() {
		ciudadesNotaria = listasGeograficasController
				.ciudadesEnDepartamento(informeHipotecarioDTO.getDepartamentoNotaria());
	}

	public void onDepartamentoInmuebleChanged() {
		ciudadesInmueble = listasGeograficasController.ciudadesPorDepartamento(departamentoInmueble);
		if (ciudadesInmueble != null && !ciudadesInmueble.isEmpty()) {
			avaluoHipotecarioDTO.setDivipolaInforme(ciudadesInmueble.get(0));
			actualizarMapa(coordenadasDeUbicacion());
			actualizarTiempoComercializacion();
		} else {
			centroMapa = null;
		}
	}

	public void ciudadCambiada() {
		actualizarMapa(coordenadasDeUbicacion());
		actualizarTiempoComercializacion();
	}

	public void actualizarVetusdez() {
		Integer nuevaVetusdez = informeHipotecarioController.actualizarVetusdez(
				informeHipotecarioController.fechaElaboracionInforme(avaluoHipotecarioDTO),
				informeHipotecarioDTO.getAnoDeConstruccion());
		informeHipotecarioDTO.setVetustez(nuevaVetusdez);
		this.recalcularFitoCorvini();
		this.actualizarMetodologiasCamposSujeto();
	}

	public void actualizarInformacionAvaluo() {
		concatenarDireccionInmueble();
		concatenarDireccionSolicitante();
		if (avaluoHipotecarioDTO.getAreas() != null) {
			avaluoHipotecarioDTO.getAreas().clear();
			avaluoHipotecarioDTO.getAreas().addAll(listaAreas);
		} else {
			avaluoHipotecarioDTO.setAreas(new HashSet<AreaDTO>(listaAreas));
		}
	}

	public void guardarSinEnviar() {
		log.info("Guardando sin enviar informe");
		recalcularMetodologias();
		String informeGuardado = null;
		try {
			actualizarInformacionAvaluo();
			actualizarMetodosValuacion();
			if (this.clienteNuevo) {
				this.clienteController.crear(this.avaluoHipotecarioDTO.getCliente());
			}
			informeGuardado = informeHipotecarioController.guardarSinEnviar(this.avaluoHipotecarioDTO).getCodigoExterno();

			if (Estado.Aprobado.equals(avaluoHipotecarioDTO.getEstado().getEstado())) {
				this.verificarModificaciones();
			}

			listadoAvaluosBean.setAvaluo(avaluoHipotecarioDTO);

		} catch (AvaluoNotFoundException e1) {
			log.debug("No se encontró el avaluo {}", avaluoHipotecarioDTO);
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Error", "No se encontró el avaluo"));
		} catch (ClienteNotFoundException e1) {
			log.debug("No se encontró el cliente {}", avaluoHipotecarioDTO.getCliente());
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Error", "No se encontró el cliente"));
		}
		if (informeGuardado == null) {
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Error", "No se encontró el avaluo"));
		} else {
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Informe Guardado",
					"El informe de la solicitud con número: " + informeGuardado + " fue guardado exitosamente"));
			// Hacer una nueva copia de las areas originales debido a que el
			// listado en la base de datos ahora es distinto
			try {
				if (this.avaluoHipotecarioDTO != null
						&& this.avaluoHipotecarioDTO.getClass().equals(AvaluoHipotecarioDTO.class)) {
					avaluoHipotecarioDTOClone = (AvaluoHipotecarioDTO) avaluoHipotecarioDTO.clone();
				} else if (this.avaluoHipotecarioDTO != null && this.avaluoHipotecarioDTO instanceof AvaluoRemateDTO) {
					avaluoHipotecarioDTOClone = (AvaluoRemateDTO) avaluoHipotecarioDTO.clone();
				}
			} catch (Exception e) {
				log.error("No se pudo clonar el objeto avaluoHipotecarioDTO", e);
			}
		}
	}

	public void autoGuardar() {
		/**
		 * Funcionalidad de bloquear el informe desactivada
		 */
		/*
		 * if (timer != null) { timer.cancel(); } TimerTask timerTask = new TimerTask()
		 * {
		 * 
		 * @Override public void run() { desbloquearInforme(); } }; timer = new
		 * Timer("desbloquearTimer"); timer.schedule(timerTask, 130000);
		 */
		guardarSinEnviar();
	}

	/**
	 * Metodo que se encarga de verificar si se han generado modificaicones sobre el
	 * avaluo en estado aprobado.
	 */
	private void verificarModificaciones() {

		// Modificaciones del avaluo
		Set<String> funcionesIgnorarAvaluo = new LinkedHashSet<String>();
		String[] funciones = { "FormatoInforme", "GastosTranslado", "Garajes", "Areas", "Fotografias", "Semaforo",
				"Usuario", "Creador", "GastosTranslado", "Iva", "ValorLiquidacion", "ValorHonorarios", "ValorAvaluoEnUvr",
				"ValorAsegurable", "AreaTotal", "Cliente", "DuracionPausaSemaforo", "MetodosValuacion", "Tarifa",
				"AreaConstruccionAvaluoDTO", "AreaLoteAvaluoDTO", "TipoSubsidio", "Matriculas" };
		funcionesIgnorarAvaluo.addAll(Arrays.asList(funciones));

		List<ModificacionDTO> modificaciones = Comparador.comparar(avaluoHipotecarioDTOClone, avaluoHipotecarioDTO,
				avaluoHipotecarioDTO.getId(), usuarioActivo, funcionesIgnorarAvaluo);

		// modificaicones informe
		Set<String> funcionesIgnorarInforme = new LinkedHashSet<String>();
		String[] funcionesInforme = { "JustificacionDeMetodologia" };
		funcionesIgnorarInforme.addAll(Arrays.asList(funcionesInforme));

		List<ModificacionDTO> modificacionesInforme = Comparador.comparar(avaluoHipotecarioDTOClone.getFormatoInforme(),
				avaluoHipotecarioDTO.getFormatoInforme(), avaluoHipotecarioDTO.getId(), usuarioActivo, funcionesIgnorarInforme);
		modificaciones.addAll(modificacionesInforme);

		Set<String> funcionesIgnorarCliente = new LinkedHashSet<String>();
		funcionesIgnorarCliente.add("DireccionDeContactoSolicitante");
		List<ModificacionDTO> modificacionesCliente = Comparador.comparar(this.avaluoHipotecarioDTOClone.getCliente(),
				this.avaluoHipotecarioDTO.getCliente(), avaluoHipotecarioDTO.getId(), usuarioActivo, funcionesIgnorarCliente);
		modificaciones.addAll(modificacionesCliente);
		modificaciones.addAll(this.verificarModificacionesMetodologias());

		/*
		 * // modificaciones en los metodos los garajes // activar hasta que esten bien
		 * if (this.avaluoHipotecarioDTOClone.getGarajes() != null &&
		 * avaluoHipotecarioDTO.getGarajes() != null) {
		 * this.verificarModificacionesGarajes(); }
		 */
		// modificaciones en las areas.
		if (this.avaluoHipotecarioDTOClone.getAreas() != null && avaluoHipotecarioDTO.getAreas() != null) {
			modificaciones.addAll(this.verificarModificacionesAreas());
		}

		// si se genero alguna modificacion,se modifica el pdf.
		if (modificaciones != null && !modificaciones.isEmpty()) {
			modificacionController.guardar(modificaciones);
			// se clona el objeto de nuevo para que este ahora sea el estado
			// actual del avaluo.
			try {
				if (this.avaluoHipotecarioDTO != null
						&& this.avaluoHipotecarioDTO.getClass().equals(AvaluoHipotecarioDTO.class)) {
					avaluoHipotecarioDTOClone = (AvaluoHipotecarioDTO) avaluoHipotecarioDTO.clone();
				} else if (this.avaluoHipotecarioDTO != null && this.avaluoHipotecarioDTO instanceof AvaluoRemateDTO) {
					avaluoHipotecarioDTOClone = (AvaluoRemateDTO) avaluoHipotecarioDTO.clone();
				}
			} catch (Exception e) {
				log.error("No se pudo clonar el objeto avaluoHipotecarioDTO", e);
			}
			// windows environment
			String url = path + "imagenes\\" + avaluoHipotecarioDTO.getId() + ".pdf";
			// unix environments
			// String url = path + "imagenes/" + avaluoHipotecarioDTO.getId() +
			// ".pdf";

			// se elimina el pdf que se habia generado al ser aprobado o en una
			// modificacion anterior.
			File pdfPrevio = new File(url);
			if (pdfPrevio.exists() && !pdfPrevio.isDirectory()) {
				pdfPrevio.delete();
			}
			this.listadoAvaluosBean.generarPDF();
			PDFDigitalSign digitalSign = new PDFDigitalSign(path);
			digitalSign.firmar(url);
		}
	}

	private List<ModificacionDTO> verificarModificacionesMetodologias() {
		List<ModificacionDTO> modificaciones = new ArrayList<ModificacionDTO>();
		// Set<MetodoValuacionDTO> metodosModificados =
		// Sets.intersection(this.avaluoHipotecarioDTO.getMetodosValuacion(),
		// this.avaluoHipotecarioDTOClone.getMetodosValuacion());
		//
		// if (metodosModificados != null) {
		// for(MetodoValuacionDTO modificado : metodosModificados) {
		// for( MetodoValuacionDTO
		// original:this.avaluoHipotecarioDTOClone.getMetodosValuacion()){
		// if (modificado.getId() == original.getId()) {
		// List<ModificacionDTO> modificionesMetodo =
		// Comparador.comparar(original, modificado,
		// avaluoHipotecarioDTO.getId(), usuarioActivo,null);
		// modificaciones.addAll(modificionesMetodo);
		// }
		// }
		// }
		// }
		return modificaciones;
	}

	/**
	 * Metodo que se encarga de verificar si existen modificaciones en los garajes.
	 */
	private List<ModificacionDTO> verificarModificacionesGarajes() {
		List<ModificacionDTO> modificacionesGarajes = new ArrayList<ModificacionDTO>();

		List<GarajeDTO> garajesBorrados = null;
		if (this.avaluoHipotecarioDTOClone.getGarajes() != null && this.avaluoHipotecarioDTO.getGarajes() != null) {
			garajesBorrados = new ArrayList<GarajeDTO>(this.avaluoHipotecarioDTOClone.getGarajes());
			garajesBorrados.removeAll(this.avaluoHipotecarioDTO.getGarajes());
		}
		List<GarajeDTO> garajesNuevos = new ArrayList<>(this.avaluoHipotecarioDTO.getGarajes());
		garajesNuevos.retainAll(this.avaluoHipotecarioDTOClone.getGarajes());

		List<GarajeDTO> garajesModificados = new ArrayList<>(this.avaluoHipotecarioDTOClone.getGarajes());
		garajesModificados.retainAll(this.avaluoHipotecarioDTO.getGarajes());

		// if (garajesBorrados!=null && !garajesBorrados.isEmpty()) {
		// List<ModificacionDTO> modificacionGarajesBorrados =
		// Comparador.objetosBorrados(garajesBorrados,
		// avaluoHipotecarioDTO.getId(), usuarioActivo);
		// modificacionesGarajes.addAll(modificacionGarajesBorrados);
		// }
		// if (garajesNuevos!=null & !garajesNuevos.isEmpty()) {
		// List<ModificacionDTO> modificacionGarajesNuevos =
		// Comparador.objetosNuevos(garajesNuevos,avaluoHipotecarioDTO.getId(),
		// usuarioActivo);
		// modificacionesGarajes.addAll(modificacionGarajesNuevos);
		// }
		// if (garajesModificados != null && !garajesModificados.isEmpty() ) {
		// List<ModificacionDTO> modificacionesCamposGarajes =
		// Comparador.camposPorObjeto(avaluoHipotecarioDTO.getGarajes(),
		// garajesModificados, avaluoHipotecarioDTO.getId(),usuarioActivo);
		// modificacionesGarajes.addAll(modificacionesCamposGarajes);
		// }
		return modificacionesGarajes;
	}

	/**
	 * Metodo que se encarga de verificar si existen modificaciones en las Areas.
	 */
	private List<ModificacionDTO> verificarModificacionesAreas() {
		List<ModificacionDTO> modificacionesPorArea = new ArrayList<ModificacionDTO>();
		if (avaluoHipotecarioDTOClone.getAreas() != null && !avaluoHipotecarioDTOClone.getAreas().isEmpty()) {
			Set<AreaDTO> areasBorradas = Sets.difference(avaluoHipotecarioDTOClone.getAreas(),
					avaluoHipotecarioDTO.getAreas());
			Set<AreaDTO> areasNuevas = Sets.difference(avaluoHipotecarioDTO.getAreas(), avaluoHipotecarioDTOClone.getAreas());
			// Set<AreaDTO> areasModificadas = Sets.intersection(
			// avaluoHipotecarioDTO.getAreas(),
			// avaluoHipotecarioDTOClone.getAreas()
			// );
			//
			List<AreaDTO> areasModificadas = avaluoHipotecarioDTO.getAreas().stream()
					.filter(avaluoHipotecarioDTOClone.getAreas()::contains).collect(Collectors.toList());
			if (areasBorradas != null && !areasBorradas.isEmpty()) {
				// not implemented yet
			}
			if (areasNuevas != null && !areasNuevas.isEmpty()) {
				// not implemented yet
			}
			if (areasModificadas != null && !areasModificadas.isEmpty()) {
				for (AreaDTO areaOriginal : avaluoHipotecarioDTOClone.getAreas()) {
					for (AreaDTO areaModificada : areasModificadas) {
						if (areaOriginal.getId() == areaModificada.getId()) {
							String campo = "";
							if (areaOriginal.getArea().compareTo(areaModificada.getArea()) != 0) {
								campo = areaOriginal.getNombre() + " ," + " Area";
								ModificacionDTO modificacionArea = new ModificacionDTO();
								modificacionArea.setCampo(campo);
								modificacionArea.setAvaluo(areaOriginal.getAvaluoId());
								modificacionArea.setFecha(new Date());
								modificacionArea.setAnterior(String.valueOf(areaOriginal.getArea()));
								modificacionArea.setNuevo(String.valueOf(areaModificada.getArea()));
								modificacionArea.setUsuario(usuarioActivo);
								modificacionesPorArea.add(modificacionArea);
							}
							if (areaOriginal.getUnidadDeMedida().getKey() != areaModificada.getUnidadDeMedida().getKey()) {
								campo = areaOriginal.getNombre() + " ," + " Unidad de Medida";
								ModificacionDTO modificacionUnidadMedida = new ModificacionDTO();
								modificacionUnidadMedida.setCampo(campo);
								modificacionUnidadMedida.setAvaluo(areaOriginal.getAvaluoId());
								modificacionUnidadMedida.setFecha(new Date());
								modificacionUnidadMedida.setAnterior(String.valueOf(areaOriginal.getUnidadDeMedida().getValue()));
								modificacionUnidadMedida.setNuevo(String.valueOf(areaModificada.getUnidadDeMedida().getValue()));
								modificacionUnidadMedida.setUsuario(usuarioActivo);
								modificacionesPorArea.add(modificacionUnidadMedida);
							}
							if (areaOriginal.getValorUnitario().compareTo(areaModificada.getValorUnitario()) != 0) {
								campo = areaOriginal.getNombre() + " ," + " Valor unitario";
								ModificacionDTO modificacionValorUnitario = new ModificacionDTO();
								modificacionValorUnitario.setCampo(campo);
								modificacionValorUnitario.setAvaluo(areaOriginal.getAvaluoId());
								modificacionValorUnitario.setFecha(new Date());
								modificacionValorUnitario.setAnterior(String.valueOf(areaOriginal.getValorUnitario()));
								modificacionValorUnitario.setNuevo(String.valueOf(areaModificada.getValorUnitario()));
								modificacionValorUnitario.setUsuario(usuarioActivo);
								modificacionesPorArea.add(modificacionValorUnitario);
							}
							if (areaOriginal.getValorTotal().compareTo(areaModificada.getValorTotal()) != 0) {
								campo = areaOriginal.getNombre() + " ," + " Valor total";
								ModificacionDTO modificacionValorTotal = new ModificacionDTO();
								modificacionValorTotal.setCampo(campo);
								modificacionValorTotal.setAvaluo(areaOriginal.getAvaluoId());
								modificacionValorTotal.setFecha(new Date());
								modificacionValorTotal.setAnterior(String.valueOf(areaOriginal.getValorTotal()));
								modificacionValorTotal.setNuevo(String.valueOf(areaModificada.getValorTotal()));
								modificacionValorTotal.setUsuario(usuarioActivo);
								modificacionesPorArea.add(modificacionValorTotal);

							}
							if (areaOriginal.getPorcentaje().compareTo(areaModificada.getPorcentaje()) != 0) {
								campo = areaOriginal.getNombre() + " ," + " Porcentaje";
								ModificacionDTO modificacionPorcentaje = new ModificacionDTO();
								modificacionPorcentaje.setCampo(campo);
								modificacionPorcentaje.setAvaluo(areaOriginal.getAvaluoId());
								modificacionPorcentaje.setFecha(new Date());
								modificacionPorcentaje.setAnterior(String.valueOf(areaOriginal.getPorcentaje()));
								modificacionPorcentaje.setNuevo(String.valueOf(areaModificada.getPorcentaje()));
								modificacionPorcentaje.setUsuario(usuarioActivo);
								modificacionesPorArea.add(modificacionPorcentaje);

							}
							// List<ModificacionDTO> modificacionesCamposarea =
							// Comparador.comparar(areaOriginal,areaModificada,
							// avaluoHipotecarioDTO.getId(),usuarioActivo,null);
							// modificacionesPorArea.addAll(modificacionesCamposarea);
						}
					}
				}

			}
		}
		return modificacionesPorArea;
	}

	private void actualizarMetodosValuacion() {
		if (this.avaluoHipotecarioDTO.getMetodosValuacion() != null) {
			for (int i = 0; i < this.avaluoHipotecarioDTO.getMetodosValuacion().size(); i++) {
				MetodoValuacionDTO metodo = this.avaluoHipotecarioDTO.getMetodosValuacion().get(i);
				String metodoString = metodo.getMetodoUsado().getValue();
				if (metodoString.equals(MetodoValuacionEnum.ComparaciondeMercado.getValue())) {
					metodo = this.comparacionMercadoBean.getMetodoValuacion();
				}

				if (metodoString.equals(MetodoValuacionEnum.ComparaciondeMercadoPHVenta.getValue())) {
					metodo = this.comparacionMercadoPHVentaBean.getMetodoValuacion();
				}

				if (metodoString.equals(MetodoValuacionEnum.ComparaciondeMercadoPHRenta.getValue())) {
					metodo = this.comparacionMercadoPHRentaBean.getMetodoValuacion();
				}

				if (metodoString.equals(MetodoValuacionEnum.ComparaciondeMercadoLoteVentaSinContruccion.getValue())) {
					metodo = this.comparacionMercadoLoteVentaBean.getMetodoValuacion();
				}

				if (metodoString.equals(MetodoValuacionEnum.ComparaciondeMercadoLoteVentaConstruccion.getValue())) {
					metodo = this.comparacionMercadoLoteConstruccionVentaBean.getMetodoValuacion();
				}

				if (metodoString.equals(MetodoValuacionEnum.ComparaciondeMercadoLoteRentaContruccion.getValue())) {
					metodo = this.comparacionMercadoLoteConstruccionRentaBean.getMetodoValuacion();
				}

				metodo.setAvaluoId(avaluoHipotecarioDTO.getId());
				this.avaluoHipotecarioDTO.getMetodosValuacion().set(i, metodo);
			}
		}

	}

	public void cerrar() {

		String uri = FacesContext.getCurrentInstance().getExternalContext()
				.encodeActionURL(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()
						+ "/pages/avaluos/listadoAvaluos.xhtml");
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(uri);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return True si la direccion es incorrecta, False si se diligencio alguna de
	 *         las dos direcciones.
	 */
	public boolean dirreccionInmuebleIncorrecta() {
		Boolean direccionPartes = Boolean.FALSE;
		Boolean direccionAdicional = Boolean.FALSE;
		String tipoVia = this.avaluoHipotecarioDTO.getTipoViaInforme() == null ? ""
				: this.avaluoHipotecarioDTO.getTipoViaInforme();
		String numeroViaGeneradora = this.avaluoHipotecarioDTO.getNumeroViaGeneradoraInforme() == null ? ""
				: this.avaluoHipotecarioDTO.getNumeroViaGeneradoraInforme();
		String placa = this.avaluoHipotecarioDTO.getPlacaInforme() == null ? ""
				: this.avaluoHipotecarioDTO.getPlacaInforme();
		String via = this.avaluoHipotecarioDTO.getNumeroViaInforme() == null ? ""
				: this.avaluoHipotecarioDTO.getNumeroViaInforme();
		if (tipoVia.isEmpty() || numeroViaGeneradora.isEmpty() || placa.isEmpty() || via.isEmpty())
			direccionPartes = Boolean.TRUE;
		String adicional = this.avaluoHipotecarioDTO.getAdicionalDireccionInforme() == null ? ""
				: this.avaluoHipotecarioDTO.getAdicionalDireccionInforme();
		if (adicional.isEmpty()) {
			direccionAdicional = Boolean.TRUE;
		}
		return direccionPartes && direccionAdicional;
	}

	public void enviarAvaluo(boolean validarSubsidio) {
		guardarSinEnviar();
		if (!verificarValidezInforme(validarSubsidio)) {
			return;
		}
		actualizarInformacionAvaluo();
		String informeEnviado = null;
		try {
			AvaluoDTO avaluoHipotecarioResultado = informeHipotecarioController.enviarAvaluo(avaluoHipotecarioDTO,
					this.avaluoHipotecarioDTOClone, usuarioActivo);
			if (avaluoHipotecarioResultado != null) {
				informeEnviado = avaluoHipotecarioResultado.getCodigoExterno();
				listadoAvaluosBean.setInformeEnviado(informeEnviado);
			} else {
				listadoAvaluosBean.setMensajeError("No se pudo enviar el informe porque el informe ya ha sido enviado.");
			}
		} catch (AvaluoNotFoundException e1) {
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Error", "No se encontró el avaluo"));
		} catch (ClienteNotFoundException e1) {
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Error", "No se encontró el cliente"));
		}

		String uri = FacesContext.getCurrentInstance().getExternalContext()
				.encodeActionURL(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()
						+ "/pages/avaluos/listadoAvaluos.xhtml");
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(uri);
		} catch (IOException e) {
			// TODO Reireccionar a página no encontrada
			e.printStackTrace();
		}
	}

	/**
	 * Verifica la validez del informe el informe es invalido si:
	 * <ul>
	 * <li>Dirección incorrecta: La dirección es incorrecta cuando tiene un campo
	 * entre (vía, número via, placa) si no tiene adicional de la dirección.
	 * <li>Metodologías de valuación ausentes o incompletas: Si no tiene ninguna
	 * métodología de valuación o es comparación de mercado y no tiene ninguna
	 * oferta.
	 * <li>Número de fotografías: El número de fotografias es 0 o es menor de 3 para
	 * avaluo tipo remates y 8 para avaluo hipotecario.
	 * <li>Número de areas: El número de areas es 0.
	 * <li>Valor de las areas y valores totales: Si las areas del informe no tienen
	 * ningun valor o el valor total del avaluo es 0.
	 * <li>Valor total del avaluo no excede el valor maximo o es menor que el valor
	 * minimo del {@link SubsidioDTO}.
	 * <li>El marcador generado por el aplicativo fue ajustado por el usuario.
	 * </ul>
	 * 
	 * @return un true si el informe es valido false si no lo es.
	 * @param validarSubsidio si se debe o no validar los valores del
	 *                        {@link SubsidioDTO}
	 */

	private boolean verificarValidezInforme(boolean validarSubsidio) {
		boolean resultado = true;
		Boolean dirreccionIncorrecta = this.dirreccionInmuebleIncorrecta();
		if (dirreccionIncorrecta) {
			this.errorDireccionInmueble = Boolean.TRUE;
			FacesContext.getCurrentInstance().addMessage("errorMessageInmueble",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Diligencie la direccion del inmueble"));
			resultado = false;
		}

		/*
		 * se verifica si existe una metodologia de comparacion de mercado si existe se
		 * valida que antes de enviar el informe tenga minimo 3 ofertas.
		 */
		Boolean comparacionMercado = Boolean.FALSE;
		if (this.avaluoHipotecarioDTO.getMetodosValuacion() != null) {
			for (MetodoValuacionDTO metodo : this.avaluoHipotecarioDTO.getMetodosValuacion()) {
				String metodoString = metodo.getMetodoUsado().getValue();
				if (metodoString.equals(MetodoValuacionEnum.ComparaciondeMercado.getValue())) {
					comparacionMercado = Boolean.TRUE;
					break;
				}
			}
		}
		if (comparacionMercado) {
			if (!this.comparacionMercadoBean.ofertasNecesarias()) {
				resultado = false;
			}
		}

		int fotografiasRequeridas = this.listadoAvaluosBean.getAvaluo() == null ? 8
				: this.listadoAvaluosBean.getAvaluo().getTipoAvaluo().getNombre().equals(Constantes.TIPO_AVALUO_REMATE) ? 3 : 8;
		if (fotografias.size() < fotografiasRequeridas) {
			FacesContext.getCurrentInstance().addMessage("growl",
					new FacesMessage("El informe no se puede enviar", "El informe sólo tiene " + fotografias.size()
							+ " fotografias debe tener mínimo " + fotografiasRequeridas + "."));
			resultado = false;
		}
		if (listaAreas.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("growl",
					new FacesMessage("El informe debe tener al menos un área."));
			resultado = false;
		} else {
			for (AreaDTO areaDTO : listaAreas) {
				if (areaDTO.getValorTotal() == null || areaDTO.getValorTotal().compareTo(BigDecimal.ZERO) <= 0) {
					// Mostrar error;
					FacesContext.getCurrentInstance().addMessage("growl",
							new FacesMessage("El area, " + areaDTO.getNombre() + " debe tener un valor total mayor que cero."));
					resultado = false;
				}
				if (areaDTO.getUnidadDeMedida().equals(AreaDTO.UnidadDeMedida.Seleccione)) {
					FacesContext.getCurrentInstance().addMessage("growl",
							new FacesMessage("El area, " + areaDTO.getNombre() + " debe tener una unidad de medida."));
					resultado = false;
				}
			}
		}
		if (avaluoHipotecarioDTO.getMetodosValuacion() == null || avaluoHipotecarioDTO.getMetodosValuacion().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("growl",
					new FacesMessage("El informe debe tener al menos un método de valuación."));
			resultado = false;
		}
		if (!avaluoEnCorrecion && !avaluoEnRevision && !coordenadasAjustadas) {
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Ajustar coordenadas",
					"Recuerde precisar la ubicación del inmueble mediante los campos de coordenadas o moviendo el indicador del mapa a la ubicación exacta del inmueble en el capitulo de Titulación."));
			resultado = false;
		}
		if (resultado && validarSubsidio
				&& (avaluoHipotecarioDTO.getValorTotalAvaluo().compareTo(avaluoHipotecarioDTO.getTipoSubsidio().getMaximo()) > 0
						|| avaluoHipotecarioDTO.getValorTotalAvaluo()
								.compareTo(avaluoHipotecarioDTO.getTipoSubsidio().getMinimo()) < 0)) {
			RequestContext.getCurrentInstance().execute("PF('subsidioFueraRango').show();");
			resultado = false;
		}
		return resultado;
	}

	public void enviarNotificacionHonorarios(boolean validarSubsidio) {

		guardarSinEnviar();

		if (!verificarValidezInforme(validarSubsidio)) {
			return;
		}
		actualizarInformacionAvaluo();
		/** Esta es una validación para poder enviar el informe? */
		if (avaluoHipotecarioDTO.getValorLiquidacion() != null) {
			try {
				informeHipotecarioController.enviarNotificacionHonorarios(this.avaluoHipotecarioDTO,
						this.avaluoHipotecarioDTOClone, usuarioActivo);
				listadoAvaluosBean.setInformeHonorariosEnviado(avaluoHipotecarioDTO.getCodigoExterno());
			} catch (AvaluoNotFoundException e) {
				FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Error", "No se encontró el avaluo"));
			} catch (ClienteNotFoundException e) {
				FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Error", "No se encontró el cliente"));
			}
		} else {
			FacesContext.getCurrentInstance().addMessage("growl",
					new FacesMessage("No es posible enviar notificación", "El valor de la liquidación es invalido"));
			return;
		}

		String uri = FacesContext.getCurrentInstance().getExternalContext()
				.encodeActionURL(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()
						+ "/pages/avaluos/listadoAvaluos.xhtml");
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(uri);
		} catch (IOException e) {
			// TODO Reireccionar a página no encontrada
			e.printStackTrace();
		}
	}

	public void enviarAvaluoAComite() {

		recalcularMetodologias();

		informeHipotecarioController.enviarAvaluoAComite(avaluoHipotecarioDTO, usuarioActivo);
		listadoAvaluosBean.setInformeEnviadoAComite(avaluoHipotecarioDTO.getCodigoExterno());
		String uri = FacesContext.getCurrentInstance().getExternalContext()
				.encodeActionURL(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()
						+ "/pages/avaluos/listadoAvaluos.xhtml");
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(uri);
		} catch (IOException e) {
			// TODO Reireccionar a página no encontrada
			e.printStackTrace();
		}
	}

	public void solcitarCorreciones() {
		log.debug("Solicitando correciones");

		guardarSinEnviar();
		actualizarInformacionAvaluo();

		avaluoController.solicitarCorreciones(avaluoHipotecarioDTO, usuarioActivo, correciones);
		listadoAvaluosBean.setCorrecionesSolicitadas(avaluoHipotecarioDTO.getCodigoExterno());
		String uri = FacesContext.getCurrentInstance().getExternalContext()
				.encodeActionURL(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()
						+ "/pages/avaluos/listadoAvaluos.xhtml");
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(uri);
		} catch (IOException e) {
			// TODO Reireccionar a página no encontrada
			e.printStackTrace();
		}
	}

	public void aprobarAvaluo() {
		guardarSinEnviar();
		actualizarInformacionAvaluo();

		log.debug("Aprobando avaluo {}", avaluoHipotecarioDTO);

		guardarSinEnviar();
		actualizarInformacionAvaluo();

		String uri = FacesContext.getCurrentInstance().getExternalContext()
				.encodeActionURL(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()
						+ "/pages/avaluos/aprobarAvaluo.xhtml");
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(uri);
		} catch (IOException e) {
			// TODO Reireccionar a página no encontrada
			e.printStackTrace();
		}
	}

	/**
	 * Concatena la dirección del inmueble y actualiza el mapa
	 */
	public void actualizarDireccion() {
		concatenarDireccionInmueble();
		if (avaluoHipotecarioDTO.getSector() != null) {
			if (avaluoHipotecarioDTO.getSector().equals(1)) {
				if (avaluoHipotecarioDTO.getTipoViaInforme() != null && !avaluoHipotecarioDTO.getTipoViaInforme().isEmpty()
						&& avaluoHipotecarioDTO.getNumeroViaInforme() != null
						&& !avaluoHipotecarioDTO.getNumeroViaInforme().isEmpty()
						&& avaluoHipotecarioDTO.getNumeroViaGeneradoraInforme() != null
						&& !avaluoHipotecarioDTO.getNumeroViaGeneradoraInforme().isEmpty()
						&& avaluoHipotecarioDTO.getPlacaInforme() != null && !avaluoHipotecarioDTO.getPlacaInforme().isEmpty()) {
					actualizarMapa(coordenadasDeUbicacion());
				}
			} else {
				if (avaluoHipotecarioDTO.getDivipolaInforme() != null && departamentoInmueble != null) {
					actualizarMapa(coordenadasDeUbicacion());
				} else {
					FacesContext.getCurrentInstance().addMessage("mensajeCoordenadas",
							new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia",
									"Se debe seleccionar el departamento y municipio para calcular las coordenadas"));
				}
			}
		} else {
			FacesContext.getCurrentInstance().addMessage("mensajeCoordenadas", new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Advertencia", "Se debe seleccionar el sector para calcular las coordenadas"));
		}
	}

	/**
	 * Actualiza el mapa a partir de las coordenadas de ubicación
	 * 
	 * @param coordenadas coordenadas para actualizar el mapa
	 */
	private void actualizarMapa(LatLng coordenadas) {
		if (!mapModel.getMarkers().isEmpty()) {
			mapModel.getMarkers().clear();
		}
		Marker marker = new Marker(coordenadas, "Codigo solicitud: " + avaluoHipotecarioDTO.getCodigoExterno().toString());
		marker.setDraggable(true);
		mapModel.addOverlay(marker);

		if (coordenadas != null) {
			centroMapa = Double.toString(coordenadas.getLat()) + ", " + Double.toString(coordenadas.getLng());
		}
		coordenadasAjustadas = false;
	}

	/**
	 * Actualiza el marcador del mapa a partir de las coordenadas ingresadas por el
	 * usuario.
	 */
	public void actualizarCoordenadas() {
		if (avaluoHipotecarioDTO.getLatitud() != null && avaluoHipotecarioDTO.getLongitud() != null) {
			try {

				this.coordenadasValidas = MapUtils.isCoordinatesValid(avaluoHipotecarioDTO.getLatitud().toString(),
						avaluoHipotecarioDTO.getLongitud().toString());
				if (this.coordenadasValidas) {
					if (!mapModel.getMarkers().isEmpty()) {
						mapModel.getMarkers().clear();
					}
					this.centroMapa = String.valueOf(avaluoHipotecarioDTO.getLatitud()) + ", "
							+ String.valueOf(avaluoHipotecarioDTO.getLongitud());
					LatLng coordenadas = new LatLng(avaluoHipotecarioDTO.getLatitud().doubleValue(),
							avaluoHipotecarioDTO.getLongitud().doubleValue());
					Marker marker = new Marker(coordenadas,
							"Codigo solicitud: " + avaluoHipotecarioDTO.getCodigoExterno().toString());
					marker.setDraggable(true);
					mapModel.addOverlay(marker);
					coordenadasAjustadas = true;
				} else {
					System.err
							.println("Las coordenadas no son validas, no se pudo obtener resultados de google (latitud,longitud) ("
									+ avaluoHipotecarioDTO.getLatitud() + "," + avaluoHipotecarioDTO.getLongitud() + ")");
					FacesContext.getCurrentInstance().addMessage("errorMessageCoordenadas",
							new FacesMessage(FacesMessage.SEVERITY_ERROR, "Coordenadas",
									"La latitude y logitud ingresada no generan un resultado valido."));
				}
			} catch (Exception e) {
			}
		}

	}

	/**
	 * Agrega un marcador al mapa con la información de las coordenadas
	 */
	public void agregarMarcador() {
		if (!mapModel.getMarkers().isEmpty()) {
			mapModel.getMarkers().clear();
		}
		LatLng coordenadas = new LatLng(lat, lng);
		Marker marker = new Marker(coordenadas, "Codigo solicitud: " + avaluoHipotecarioDTO.getCodigoExterno().toString());
		marker.setDraggable(true);
		mapModel.addOverlay(marker);

		centroMapa = Double.toString(coordenadas.getLat()) + ", " + Double.toString(coordenadas.getLng());
		avaluoHipotecarioDTO.setLatitud(new BigDecimal(coordenadas.getLat(), new MathContext(7, RoundingMode.CEILING)));
		avaluoHipotecarioDTO.setLongitud(new BigDecimal(coordenadas.getLng(), new MathContext(7, RoundingMode.CEILING)));
		coordenadasAjustadas = true;
	}

	public void complementoModificado(SelectEvent event) {
		concatenarDireccionInmueble();
	}

	/**
	 * Mueve el marcador cuando es arrastrado por el usuario
	 * 
	 * @param event evento de arrastrado
	 */
	public void onMarkerDrag(MarkerDragEvent event) {
		Marker marker = event.getMarker();

		avaluoHipotecarioDTO.setLatitud(BigDecimal.valueOf(marker.getLatlng().getLat()));
		avaluoHipotecarioDTO.setLongitud(BigDecimal.valueOf(marker.getLatlng().getLng()));
		log.info("El marcador quedo en: {}, {}", marker.getLatlng().getLat(), marker.getLatlng().getLng());
		coordenadasAjustadas = true;
	}

	public void actualizarValorPredio(String valor, boolean valorBoleano) {
		if (!valorBoleano) {
			switch (valor) {
			case "alcantarillado":
				informeHipotecarioDTO.setAlcantarilladoEnElPredio(Boolean.FALSE);
				break;
			case "acueducto":
				informeHipotecarioDTO.setAcueductoEnElPredio(Boolean.FALSE);
				break;
			case "gas":
				informeHipotecarioDTO.setGasEnElPredio(Boolean.FALSE);
				break;
			case "telefono":
				informeHipotecarioDTO.setTelefonoEnElPredio(Boolean.FALSE);
				break;
			case "energia":
				informeHipotecarioDTO.setEnergiaEnElPredio(Boolean.FALSE);
				break;
			default:

			}
		}
	}

	public void onEstadoDeConstruccion() {
		/*
		 * if (informeHipotecarioDTO.getEstadoDeConstruccion().getKey() == 1) {
		 * informeHipotecarioDTO.setAnoDeConstruccion(Calendar.getInstance()
		 * .get(Calendar.YEAR)); informeHipotecarioDTO.setVetustez(0); }
		 */
		if (informeHipotecarioDTO.getEstadoDeConstruccion() != null
				&& informeHipotecarioDTO.getEstadoDeConstruccion().equals(ListaEstadoConstruccion.Nueva)) {
			this.informeHipotecarioDTO.setRemodelado(Boolean.FALSE);
			this.informeHipotecarioDTO.setEstadoDeConservacion(null);
		}
	}

	public void onRemodelado() {
		if (!informeHipotecarioDTO.getRemodelado()) {
			informeHipotecarioDTO.setFechaRemodelacion(null);
		}
	}

	public void actualizarDepositos(String actualizado) {
		if ("Privado".equals(actualizado) && informeHipotecarioDTO.getDepositosPrivados() != null
				&& informeHipotecarioDTO.getDepositosPrivados() > 0) {
			informeHipotecarioDTO.setDepositosExclusivos(null);
			informeHipotecarioDTO.setNumeroTotalDepositos(informeHipotecarioDTO.getDepositosPrivados());
			limpiarCamposDepositos(numerosGarajes.length - informeHipotecarioDTO.getDepositosPrivados());
		} else if ("Exclusivo".equals(actualizado) && informeHipotecarioDTO.getDepositosExclusivos() != null
				&& informeHipotecarioDTO.getDepositosExclusivos() > 0) {
			informeHipotecarioDTO.setDepositosPrivados(null);
			informeHipotecarioDTO.setNumeroTotalDepositos(informeHipotecarioDTO.getDepositosExclusivos());
			limpiarCamposDepositos(0);
		} else {
			informeHipotecarioDTO.setDepositosPrivados(null);
			informeHipotecarioDTO.setDepositosExclusivos(null);
			informeHipotecarioDTO.setNumeroTotalDepositos(null);
			// limpiar todos los campos de números de garaje y matriculas
			// inmobilias
			limpiarCamposDepositos(0);
		}
		if (informeHipotecarioDTO.getSometidoAPropiedadHorizontal() != null
				&& informeHipotecarioDTO.getSometidoAPropiedadHorizontal()) {
			actualizarAreasPorDeposito();
		}
	}

	private void limpiarCamposDepositos(int numeroDepositos) {
		Class<FormatoInformeHipotecarioDTO> formatoInformeHipotecarioClass = FormatoInformeHipotecarioDTO.class;
		for (int i = numerosGarajes.length; i > numerosGarajes.length - numeroDepositos; i--) {
			try {
				Method setNumeroDeposito = formatoInformeHipotecarioClass
						.getDeclaredMethod("setNumeroDeposito" + Integer.toString(i), new Class[] { Integer.class });
				setNumeroDeposito.invoke(informeHipotecarioDTO, new Object[] { null });

				Method setMatriculaDeposito = formatoInformeHipotecarioClass
						.getDeclaredMethod("setMatriculaInmobiliariaDeposito" + Integer.toString(i), new Class[] { String.class });
				setMatriculaDeposito.invoke(informeHipotecarioDTO, new Object[] { null });
			} catch (NoSuchMethodException e) {
				log.error(
						"NoSuchMethodException: Error al invocar metodos setNumeroDeposito{}, o setMatriculaInmobiliariaDeposito{}",
						Integer.toString(i), Integer.toString(i));
			} catch (SecurityException e) {
				log.error(
						"SecurityException: Error al invocar metodos setNumeroDeposito{} o setMatriculaInmobiliariaDeposito{}",
						Integer.toString(i), Integer.toString(i));
			} catch (IllegalAccessException e) {
				log.error(
						"IllegalAccessException: Error al invocar metodos setNumeroDeposito{} o setMatriculaInmobiliariaDeposito{}",
						Integer.toString(i), Integer.toString(i));
			} catch (IllegalArgumentException e) {
				log.error(
						"IllegalArgumentException: Error al invocar metodos setNumeroDeposito{} o setMatriculaInmobiliariaDeposito{}",
						Integer.toString(i), Integer.toString(i));
			} catch (InvocationTargetException e) {
				log.error(
						"InvocationTargetException: Error al invocar metodos setNumeroDeposito{}, o setMatriculaInmobiliariaDeposito{}",
						Integer.toString(i), Integer.toString(i));
			}
		}
	}

	public void actualizarAreasConstruidasPorPiso(Integer numeroPisosAgregar) {
		if (informeHipotecarioDTO.getSometidoAPropiedadHorizontal() == null
				|| !informeHipotecarioDTO.getSometidoAPropiedadHorizontal()) {
			if (numeroPisosAgregar != null && numeroPisosAgregar > 0) {
				if (areasPisos != null && !areasPisos.isEmpty()) {
					listaAreas.removeAll(areasPisos);
					areasConstruidasNoPH.removeAll(areasPisos);
					if (areasPisos.size() > informeHipotecarioDTO.getNumeroDePisos()) {
						Predicate<AreaDTO> pisosRetenidos = new Predicate<AreaDTO>() {

							@Override
							public boolean apply(AreaDTO input) {
								return Integer.parseInt(input.getDescripcion()) <= informeHipotecarioDTO.getNumeroDePisos();
							}
						};
						List<AreaDTO> areasResultado = ImmutableList.copyOf(Collections2.filter(areasPisos, pisosRetenidos));
						areasPisos.clear();
						areasPisos.addAll(areasResultado);
					} else if (areasPisos.size() < informeHipotecarioDTO.getNumeroDePisos()) {
						for (int i = 1; i <= informeHipotecarioDTO.getNumeroDePisos(); i++) {
							if (comprobarExistenciaArea(DescripcionAreaNoPH.AreaConstruccion, Integer.toString(i),
									areasPisos) == null) {
								AreaDTO area = new AreaDTO();
								area.setDescripcionNumerica(DescripcionAreaNoPH.AreaConstruccion);
								area.setTipoArea(TipoArea.Construccion);
								area.setNombre("Piso " + Integer.toString(i));
								area.setDescripcion(Integer.toString(i));
								area.setPorcentaje(BigDecimal.ZERO);
								area.setPorcentaje(BigDecimal.ZERO);
								area.setAvaluoId(avaluoHipotecarioDTO.getId());
								areasPisos.add(area);
							}
						}
					}
					listaAreas.addAll(areasPisos);
					Collections.sort(listaAreas);
					areasConstruidasNoPH.addAll(areasPisos);
				} else {
					areasPisos = new ArrayList<AreaDTO>();
					for (int i = 1; i <= informeHipotecarioDTO.getNumeroDePisos(); i++) {
						AreaDTO area = new AreaDTO();
						area.setDescripcionNumerica(DescripcionAreaNoPH.AreaConstruccion);
						area.setTipoArea(TipoArea.Construccion);
						area.setNombre("Piso " + Integer.toString(i));
						area.setDescripcion(Integer.toString(i));
						area.setPorcentaje(BigDecimal.ZERO);
						area.setAvaluoId(avaluoHipotecarioDTO.getId());
						areasPisos.add(area);
					}
					listaAreas.addAll(areasPisos);
					areasConstruidasNoPH.addAll(areasPisos);
				}
			} else {
				if (areasPisos != null && !areasPisos.isEmpty()) {
					listaAreas.removeAll(areasPisos);
					areasPisos.clear();
				}
				if (this.areasConstruidasNoPH != null && !this.areasConstruidasNoPH.isEmpty()) {
					this.areasConstruidasNoPH.clear();
				}
			}
		} else {
			if (areasPisos != null && !areasPisos.isEmpty()) {
				listaAreas.removeAll(areasPisos);
				areasPisos.clear();
			}
			if (areaPrivada == null && !listaAreas.contains(areaPrivada)) {
				log.warn("Agregando area privada {}", avaluoHipotecarioDTO.getId());
				areaPrivada = new AreaDTO();
				areaPrivada.setDescripcionNumerica(DescripcionAreaPH.AreaPrivada);
				areaPrivada.setNombre("Area privada");
				areaPrivada.setPorcentaje(BigDecimal.ZERO);
				areaPrivada.setDescripcion(Integer.toString(1));
				areaPrivada.setAvaluoId(avaluoHipotecarioDTO.getId());
				listaAreas.add(areaPrivada);
				this.areasPrivadas.add(areaPrivada);
			}
		}
	}

	public void actualizarAreasPorGaraje() {
		if (this.tipoGaraje != null && this.tipoGaraje.getKey() == 1
				&& informeHipotecarioDTO.getNumeroTotalDeGarajes() != null
				&& informeHipotecarioDTO.getNumeroTotalDeGarajes() > 0) {
			if (areasGarajes != null && !areasGarajes.isEmpty()) {
				listaAreas.removeAll(areasGarajes);
				if (areasGarajes.size() > informeHipotecarioDTO.getNumeroTotalDeGarajes()) {
					Predicate<AreaDTO> garajesRetenidos = new Predicate<AreaDTO>() {

						@Override
						public boolean apply(AreaDTO input) {
							return Integer.parseInt(input.getDescripcion()) <= informeHipotecarioDTO.getNumeroTotalDeGarajes();
						}
					};
					List<AreaDTO> areasResultado = ImmutableList.copyOf(Collections2.filter(areasGarajes, garajesRetenidos));
					areasGarajes.clear();
					areasGarajes.addAll(areasResultado);
				} else if (areasGarajes.size() < informeHipotecarioDTO.getNumeroTotalDeGarajes()) {
					for (int i = 1; i <= informeHipotecarioDTO.getNumeroTotalDeGarajes(); i++) {
						if (comprobarExistenciaArea(DescripcionAreaPH.Garaje, Integer.toString(i), areasGarajes) == null) {
							agregarAreaGaraje(Integer.toString(i));
						}
					}
				}
				listaAreas.addAll(areasGarajes);
			} else {
				areasGarajes = new ArrayList<AreaDTO>();
				for (int i = 1; i <= informeHipotecarioDTO.getNumeroTotalDeGarajes(); i++) {
					agregarAreaGaraje(Integer.toString(i));
				}
				listaAreas.addAll(areasGarajes);
			}
		} else if (areasGarajes != null && !areasGarajes.isEmpty()) {
			listaAreas.removeAll(areasGarajes);
			areasGarajes.clear();
		}
	}

	private void agregarAreaGaraje(String numeroGaraje) {
		AreaDTO area = new AreaDTO();
		area.setDescripcionNumerica(DescripcionAreaPH.Garaje);
		area.setTipoArea(TipoArea.Construccion);
		String nombreGaraje = obtenerNombreGaraje(numeroGaraje);
		area.setNombre(nombreGaraje == null ? "Garaje " + numeroGaraje : nombreGaraje);
		area.setPorcentaje(BigDecimal.ZERO);
		area.setDescripcion(numeroGaraje);
		area.setAvaluoId(avaluoHipotecarioDTO.getId());
		this.areasGarajes.add(area);
	}

	private String obtenerNombreGaraje(String numeroGaraje) {
		String resultado = null;
		try {
			List<GarajeDTO> garajes = new ArrayList<GarajeDTO>();
			garajes.addAll(this.avaluoHipotecarioDTO.getGarajes());
			GarajeDTO garaje = garajes.get(Integer.parseInt(numeroGaraje) - 1);
			resultado = garaje.getNumeroGaraje() == null || garaje.getNumeroGaraje().equals("") ? "Garaje " + numeroGaraje
					: String.valueOf(garaje.getNumeroGaraje());
		} catch (Exception e) {
			log.error("Exception: Error al obtener el numero de Garaje: {}", numeroGaraje);
		}
		return resultado;
	}

	public void actualizarAreasPorDeposito() {
		if (informeHipotecarioDTO.getNumeroTotalDepositos() != null && informeHipotecarioDTO.getNumeroTotalDepositos() > 0
				&& informeHipotecarioDTO.getDepositosExclusivos() == null
				|| (informeHipotecarioDTO.getDepositosExclusivos() != null
						&& informeHipotecarioDTO.getDepositosExclusivos() < 0)) {
			if (areasDepositos != null && !areasDepositos.isEmpty()) {
				listaAreas.removeAll(areasDepositos);
				if (areasDepositos.size() > informeHipotecarioDTO.getNumeroTotalDepositos()) {
					Predicate<AreaDTO> depositosRetenidos = new Predicate<AreaDTO>() {

						@Override
						public boolean apply(AreaDTO input) {
							return Integer.parseInt(input.getDescripcion()) <= informeHipotecarioDTO.getNumeroTotalDepositos();
						}
					};
					List<AreaDTO> areasResultado = ImmutableList.copyOf(Collections2.filter(areasDepositos, depositosRetenidos));
					areasDepositos.clear();
					areasDepositos.addAll(areasResultado);
				} else if (areasDepositos.size() < informeHipotecarioDTO.getNumeroTotalDepositos()) {
					for (int i = 1; i <= informeHipotecarioDTO.getNumeroTotalDepositos(); i++) {
						if (comprobarExistenciaArea(DescripcionAreaPH.Deposito, Integer.toString(i), areasDepositos) == null) {
							AreaDTO area = new AreaDTO();
							area.setDescripcionNumerica(DescripcionAreaPH.Deposito);
							area.setTipoArea(TipoArea.Construccion);
							area.setNombre("Deposito " + Integer.toString(i));
							area.setDescripcion(Integer.toString(i));
							area.setPorcentaje(BigDecimal.ZERO);
							area.setAvaluoId(avaluoHipotecarioDTO.getId());
							areasDepositos.add(area);
						}
					}
				}
				listaAreas.addAll(areasDepositos);
			} else {
				areasDepositos = new ArrayList<AreaDTO>();
				for (int i = 1; i <= informeHipotecarioDTO.getNumeroTotalDepositos(); i++) {
					AreaDTO area = new AreaDTO();
					area.setDescripcionNumerica(DescripcionAreaPH.Deposito);
					area.setTipoArea(TipoArea.Construccion);
					area.setNombre("Deposito " + Integer.toString(i));
					area.setDescripcion(Integer.toString(i));
					area.setPorcentaje(BigDecimal.ZERO);
					area.setAvaluoId(avaluoHipotecarioDTO.getId());
					areasDepositos.add(area);
				}
				listaAreas.addAll(areasDepositos);
			}
		} else if (informeHipotecarioDTO.getNumeroTotalDepositos() == null
				|| informeHipotecarioDTO.getNumeroTotalDepositos() == 0
				|| informeHipotecarioDTO.getDepositosExclusivos() == null
				|| informeHipotecarioDTO.getDepositosExclusivos() > 0) {
			if (areasDepositos != null && !areasDepositos.isEmpty()) {
				listaAreas.removeAll(areasDepositos);
				areasDepositos.clear();
			}
		}
	}

	private AreaDTO comprobarExistenciaArea(ListaDesplegable descripcionNumerica, String descripcion,
			List<AreaDTO> areas) {
		for (AreaDTO area : areas) {
			if (area != null && area.getDescripcion() != null && area.getDescripcionNumerica() != null
					&& area.getDescripcion().equals(descripcion) && area.getDescripcionNumerica().equals(descripcionNumerica)) {
				return area;
			}
		}
		return null;
	}

	public void actualizarNumeroParqueadero(String numeroParqueadero, String nombreParqueadero) {
		AreaDTO area = comprobarExistenciaArea(DescripcionAreaPH.Garaje, numeroParqueadero, areasGarajes);
		if (area != null && nombreParqueadero != null) {
			area.setNombre(nombreParqueadero);
		}
	}

	public void actualizarNombreDeposito(String numeroDeposito, String nombreNuevo) {
		AreaDTO area = comprobarExistenciaArea(DescripcionAreaPH.Deposito, numeroDeposito, areasDepositos);
		if (area != null && nombreNuevo != null) {
			area.setNombre(nombreNuevo.toString());
		}
	}

	public void verificarMatriculaDuplicada(String clientId, String matriculaInmobiliaria) {
		if (!clientId.contains("Principal1")
				&& matriculaInmobiliaria.equals(avaluoHipotecarioDTO.getMatriculaInmobiliariaPrincipal1())) {
			FacesContext.getCurrentInstance().addMessage("growl",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Matricula inmobiliaria duplicada",
							"Matricula inmobiliaria duplicada con la matricula inmobiliaria principal"));
		}
		if (!clientId.contains("Principal2")
				&& matriculaInmobiliaria.equals(avaluoHipotecarioDTO.getMatriculaInmobiliariaPrincipal2())) {
			FacesContext.getCurrentInstance().addMessage("growl",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Matricula inmobiliaria duplicada",
							"Matricula inmobiliaria duplicada con la matricula inmobiliaria principal 2"));
		}

		if (!clientId.contains("matriculaInmobiliariaGaraje")
				&& avaluoHipotecarioDTO.getGarajes().toString().contains("matriculaInmobiliaria=" + matriculaInmobiliaria)) {
			FacesContext.getCurrentInstance().addMessage("growl",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Matricula inmobiliaria duplicada",
							"Matricula inmobiliaria duplicada con la matricula inmobiliaria de un garaje"));
		}
		if (!clientId.contains("Deposito1")
				&& matriculaInmobiliaria.equals(informeHipotecarioDTO.getMatriculaInmobiliariaDeposito1())) {
			FacesContext.getCurrentInstance().addMessage("growl",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Matricula inmobiliaria duplicada",
							"Matricula inmobiliaria duplicada con la matricula inmobiliaria deposito 1"));
		}
		if (!clientId.contains("Deposito2")
				&& matriculaInmobiliaria.equals(informeHipotecarioDTO.getMatriculaInmobiliariaDeposito2())) {
			FacesContext.getCurrentInstance().addMessage("growl",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Matricula inmobiliaria duplicada",
							"Matricula inmobiliaria duplicada con la matricula inmobiliaria deposito 2"));
		}
		if (!clientId.contains("Deposito3")
				&& matriculaInmobiliaria.equals(informeHipotecarioDTO.getMatriculaInmobiliariaDeposito3())) {
			FacesContext.getCurrentInstance().addMessage("growl",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Matricula inmobiliaria duplicada",
							"Matricula inmobiliaria duplicada con la matricula inmobiliaria deposito 3"));
		}
		if (!clientId.contains("Deposito4")
				&& matriculaInmobiliaria.equals(informeHipotecarioDTO.getMatriculaInmobiliariaDeposito4())) {
			FacesContext.getCurrentInstance().addMessage("growl",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Matricula inmobiliaria duplicada",
							"Matricula inmobiliaria duplicada con la matricula inmobiliaria deposito 4"));
		}
		if (!clientId.contains("Deposito5")
				&& matriculaInmobiliaria.equals(informeHipotecarioDTO.getMatriculaInmobiliariaDeposito5())) {
			FacesContext.getCurrentInstance().addMessage("growl",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Matricula inmobiliaria duplicada",
							"Matricula inmobiliaria duplicada con la matricula inmobiliaria deposito 5"));
		}
	}

	public void actualizarNombreDeposito5(ValueChangeEvent event) {
		if (event.getPhaseId() != PhaseId.INVOKE_APPLICATION) {
			event.setPhaseId(PhaseId.INVOKE_APPLICATION);
			event.queue();
			return;
		}
		String oldValue = event.getOldValue() != null ? Integer.toString((Integer) event.getOldValue()) : null;
		String newValue = Integer.toString((Integer) event.getNewValue());
		AreaDTO area = comprobarExistenciaArea(DescripcionAreaPH.Deposito,
				oldValue == null ? Integer.toString(5) : oldValue, areasDepositos);
		if (area != null) {
			area.setNombre(newValue);
		}
	}

	public void cambioTipoInmueble() {
		definirTipoCredito();
		actualizarTiempoComercializacion();
	}

	public void definirTipoCredito() {
		if (avaluoHipotecarioDTO.getTipoDeInmueble() != null
				&& (new Long(2).equals(avaluoHipotecarioDTO.getTipoDeInmueble().getId())
						|| new Long(4).equals(avaluoHipotecarioDTO.getTipoDeInmueble().getId()))
				&& informeHipotecarioDTO.getCocina() != null && informeHipotecarioDTO.getCocina() > 3) {
			avaluoHipotecarioDTO.setTipoCredito("Diferente de Vivienda");
		}
		calcularValores();
	}

	public void agregarArea() {
		if (nuevaArea) {
			nuevaArea = false;
			areaDTO.setAvaluoId(avaluoHipotecarioDTO.getId());
			areaDTO.setValorTotal(avaluoController.calcularValorTotalArea(areaDTO));
			if (areaDTO.getDescripcionNumerica().equals(DescripcionAreaNoPH.AreaConstruccion)) {
				areasConstruidasNoPH.add(areaDTO);
			} else if (areaDTO.getDescripcionNumerica().equals(DescripcionAreaNoPH.AreaTerreno)) {
			}
			listaAreas.add(areaDTO);
			Collections.sort(listaAreas);
			calcularValores();
		} else {
			nuevaArea = true;
			areaDTO = new AreaDTO();

		}
	}

	/**
	 * Calcula el valor comercial del avaluo y los valores propocionales y
	 * integrales del terreno y de la construcción y los valores de honorarios
	 * totales y del perito.
	 *
	 */
	public void calcularValores() {

		avaluoHipotecarioDTO.setValorTotalAvaluo(informeHipotecarioController.calcularValorTotal(listaAreas));
		log.debug("Total value with fractions: {}", avaluoHipotecarioDTO.getValorTotalAvaluo());
		avaluoHipotecarioDTO.setValorAvaluoEnUvr(informeHipotecarioController
				.calcularValorEnUVR(avaluoHipotecarioDTO.getValorUvr(), avaluoHipotecarioDTO.getValorTotalAvaluo()));
		BigDecimal areaTotal = listaAreas.stream().filter(areaDTO1 -> areaDTO1.getArea() != null).map(AreaDTO::getArea)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		BigDecimal sumaAreasConstruidas = listaAreas.stream()
				.filter(areaDTO1 -> areaDTO1.getDescripcionNumerica().equals(DescripcionAreaNoPH.AreaConstruccion))
				.map(AreaDTO::getArea).reduce(BigDecimal.ZERO, BigDecimal::add);
		BigDecimal sumaAreasPrivadas = listaAreas.stream()
				.filter(areaDTO1 -> areaDTO1.getDescripcionNumerica().equals(DescripcionAreaPH.AreaPrivada))
				.map(AreaDTO::getArea).reduce(BigDecimal.ZERO, BigDecimal::add);
		BigDecimal totalAreasPH = listaAreas.stream()
				.filter(areaDTO1 -> !areaDTO1.getDescripcionNumerica().equals(DescripcionAreaPH.AreaLibre))
				.map(AreaDTO::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
		BigDecimal totalAreasLibresPH = listaAreas.stream()
				.filter(areaDTO1 -> areaDTO1.getDescripcionNumerica().equals(DescripcionAreaPH.AreaLibre))
				.map(AreaDTO::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
		BigDecimal totalAreasConstruidas = listaAreas.stream()
				.filter(areaDTO1 -> areaDTO1.getDescripcionNumerica().equals(DescripcionAreaNoPH.AreaConstruccion))
				.map(AreaDTO::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
		BigDecimal totalAreasOtros = listaAreas.stream()
				.filter(areaDTO1 -> areaDTO1.getDescripcionNumerica().equals(DescripcionAreaNoPH.Otros))
				.map(AreaDTO::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
		BigDecimal totalAreasOtroPh = listaAreas.stream()
				.filter(areaDTO1 -> areaDTO1.getDescripcionNumerica().equals(DescripcionAreaPH.Otro))
				.map(AreaDTO::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
		BigDecimal totalAreasTerreno = listaAreas.stream()
				.filter(area -> area.getDescripcionNumerica().equals(DescripcionAreaNoPH.AreaTerreno))
				.map(AreaDTO::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
		BigDecimal totalAreasPrivadas = listaAreas.stream()
				.filter(area -> area.getDescripcionNumerica().equals(DescripcionAreaPH.AreaPrivada)).map(AreaDTO::getValorTotal)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		listaAreas.forEach(areaDTO1 -> areaDTO1
				.setPorcentaje(avaluoController.calcularPorcentajeArea(areaDTO1, avaluoHipotecarioDTO.getValorTotalAvaluo())));
		BigDecimal sumaAreasTerreno = listaAreas.stream()
				.filter(area -> area.getDescripcionNumerica().equals(DescripcionAreaNoPH.AreaTerreno)).map(AreaDTO::getArea)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		if (areaTotal.compareTo(BigDecimal.ZERO) > 0) {
			avaluoHipotecarioDTO.setAreaTotal(areaTotal);
		}
		if (sumaAreasConstruidas.compareTo(BigDecimal.ZERO) > 0) {
			informeHipotecarioDTO.setAreaConstruida(sumaAreasConstruidas);
		}
		if (sumaAreasPrivadas.compareTo(BigDecimal.ZERO) > 0) {
			informeHipotecarioDTO.setAreaPrivada(sumaAreasPrivadas);
		}
		// Calcular valor proporcional y integral del terreno y de la
		// construcción

		if (informeHipotecarioDTO.getSometidoAPropiedadHorizontal() != null
				&& informeHipotecarioDTO.getSometidoAPropiedadHorizontal()) {

			avaluoHipotecarioDTO.setValorAsegurable(informeHipotecarioController
					.calcularValorAsegurable(avaluoHipotecarioDTO.getEntidad(), totalAreasPrivadas.add(totalAreasOtroPh)));
			if (informeHipotecarioDTO.getPorcentajeTerreno() != null
					&& informeHipotecarioDTO.getPorcentajeTerreno().compareTo(BigDecimal.ZERO) > 0
					&& totalAreasPH.compareTo(BigDecimal.ZERO) > 0) {
				final BigDecimal porcentajeTerrenoDecimal = informeHipotecarioDTO.getPorcentajeTerreno()
						.divide(new BigDecimal(100), 4, RoundingMode.HALF_EVEN);
				informeHipotecarioDTO
						.setValorProporcionalTerreno(totalAreasPH.add(totalAreasLibresPH).multiply(porcentajeTerrenoDecimal));
				final BigDecimal porcentajeConstruccion = new BigDecimal(1).subtract(porcentajeTerrenoDecimal);
				informeHipotecarioDTO
						.setValorProporcionalConstruccion(totalAreasPH.add(totalAreasLibresPH).multiply(porcentajeConstruccion));
				if (sumaAreasPrivadas.compareTo(BigDecimal.ZERO) != 0) {
					informeHipotecarioDTO.setValorIntegralConstruccion(
							totalAreasPH.add(totalAreasLibresPH).divide(sumaAreasPrivadas, 4, RoundingMode.HALF_EVEN));
				}
			} else {
				informeHipotecarioDTO.setValorProporcionalTerreno(BigDecimal.ZERO);
				informeHipotecarioDTO.setValorProporcionalConstruccion(BigDecimal.ZERO);
				informeHipotecarioDTO.setValorIntegralConstruccion(BigDecimal.ZERO);
			}
		} else {

			avaluoHipotecarioDTO.setValorAsegurable(informeHipotecarioController
					.calcularValorAsegurable(avaluoHipotecarioDTO.getEntidad(), totalAreasConstruidas.add(totalAreasOtros)));
			if (areaTotal.compareTo(BigDecimal.ZERO) > 0) {
				informeHipotecarioDTO.setPorcentajeTerreno(sumaAreasTerreno.multiply(BigDecimal.valueOf(100)).divide(areaTotal,
						new MathContext(9, RoundingMode.HALF_EVEN)));
			}
			if (informeHipotecarioDTO.getPorcentajeTerreno() != null) {
				informeHipotecarioDTO.setValorProporcionalConstruccion(totalAreasConstruidas.add(totalAreasOtros));
				informeHipotecarioDTO.setValorProporcionalTerreno(totalAreasTerreno);
			} else {
				informeHipotecarioDTO.setValorProporcionalTerreno(BigDecimal.ZERO);
				informeHipotecarioDTO.setValorProporcionalConstruccion(BigDecimal.ZERO);
			}
			if (sumaAreasTerreno.compareTo(BigDecimal.ZERO) > 0) {
				informeHipotecarioDTO.setValorIntegralTerreno(
						avaluoHipotecarioDTO.getValorTotalAvaluo().divide(sumaAreasTerreno, 4, RoundingMode.HALF_EVEN));
			} else {
				informeHipotecarioDTO.setValorIntegralTerreno(BigDecimal.ZERO);
			}
			if (sumaAreasConstruidas.compareTo(BigDecimal.ZERO) > 0) {
				informeHipotecarioDTO.setValorIntegralConstruccion(
						avaluoHipotecarioDTO.getValorTotalAvaluo().divide(sumaAreasConstruidas, 4, RoundingMode.HALF_EVEN));
			} else {
				informeHipotecarioDTO.setValorIntegralConstruccion(BigDecimal.ZERO);
			}
		}

		// Calcular honorarios
		BigDecimal valorLiquidacion = null;
		try {
			valorLiquidacion = aprobarAvaluoController.calcularValorLiquidacion(avaluoHipotecarioDTO);
		} catch (TarifaNotFoundException e) {
			log.error("TarifaNotFoundException: No se encontró la tarifa asociada a la entidad y tipo de avaluo", e);
		}
		if (valorLiquidacion != null) {
			avaluoHipotecarioDTO.setValorLiquidacion(valorLiquidacion);
			/**
			 * IMPORTANTE el valor de los honorarios se deja igual al valor de liquidación
			 * mientras se define el porcentaje por perito por parte de Bancol
			 */

			avaluoHipotecarioDTO.setValorHonorarios(valorLiquidacion);
			/*
			 * try { avaluoHipotecarioDTO .setValorHonorarios(aprobarAvaluoController
			 * .calcularValorHonorarios( valorLiquidacion, aprobarAvaluoController
			 * .tarifaPorIdTipoAvaluoIdEntidad( new Long( avaluoHipotecarioDTO
			 * .getTipoAvaluo() .getId()), avaluoHipotecarioDTO .getEntidad() .getId()))); }
			 * catch (TarifaNotFoundException e) { log.
			 * error("TarifaNotFoundException: No se encontró la tarifa asociada a la entidad y tipo de avaluo"
			 * , e); FacesContext .getCurrentInstance() .addMessage( "growl", new
			 * FacesMessage(
			 * "No se encontró la tarifa asociada a la entidad y tipo de avaluo." ));
			 * return; }
			 */
			if (avaluoHipotecarioDTO.getGastosTranslado() != null
					&& avaluoHipotecarioDTO.getGastosTranslado().compareTo(BigDecimal.ZERO) > 0) {
				subTotalHonorarios = avaluoHipotecarioDTO.getValorHonorarios().add(avaluoHipotecarioDTO.getGastosTranslado());
			} else {
				subTotalHonorarios = avaluoHipotecarioDTO.getValorHonorarios();
			}
			if (avaluoHipotecarioDTO.getPerito().getIva() != null && avaluoHipotecarioDTO.getPerito().getIva()) { // Usuario
				// tiene
				// IVA
				ivaHonorarios = subTotalHonorarios
						.multiply(new BigDecimal(Float.parseFloat(parametrosController.obtenerValor("valor_iva"))));
				avaluoHipotecarioDTO.setIva(ivaHonorarios);
			} else {
				ivaHonorarios = BigDecimal.ZERO;
			}
			if (ivaHonorarios != null) {
				totalHonorarios = ivaHonorarios.add(subTotalHonorarios);
			} else {
				totalHonorarios = subTotalHonorarios;
			}
		}
	}

	public void cancelarNuevaArea() {
		nuevaArea = false;
		areaDTO = new AreaDTO();
	}

	public void editarArea(RowEditEvent event) {
		Object object = event.getObject();
		if (object.getClass().equals(AreaDTO.class)) {
			AreaDTO area = (AreaDTO) object;
			area.setValorTotal(avaluoController.calcularValorTotalArea(area));
			calcularValores();
		}
		this.recalcularFitoCorvini();
	}

	public void cancelarArea(RowEditEvent event) {
	}

	public void borrarArea(AreaDTO area) {
		if (informeHipotecarioDTO.getSometidoAPropiedadHorizontal() != null
				&& informeHipotecarioDTO.getSometidoAPropiedadHorizontal()) {
			if (area.equals(areaPrivada)) {
				FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("No es posible eliminar el area",
						"El area que esta tratando de eliminar corresponde al área privada del inmueble."));
				return;
			}
			if (area.getDescripcion() != null && !"".equals(area.getDescripcion()) && areasGarajes.contains(area)) {
				FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("No es posible eliminar el area",
						"El area que esta tratando de eliminar corresponde a un garaje del inmueble."));
				return;
			}
			if (area.getDescripcion() != null && !"".equals(area.getDescripcion()) && areasDepositos.contains(area)) {
				FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("No es posible eliminar el area",
						"El area que esta tratando de eliminar corresponde a un deposito del inmueble."));
				return;
			}
		} else {
			if (areasPisos.contains(area)) {
				FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("No es posible eliminar el area",
						"El area que esta tratando de eliminar corresponde a un piso del inmueble."));
				return;
			}
			if (area.equals(areaTerrenoDTO)) {
				FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("No es posible eliminar el area",
						"El area de terreno es obligatoria para construcciones no PH."));
				return;
			}
		}

		listaAreas.remove(area);
		calcularValores();
		this.recalcularFitoCorvini();
	}

	public void cancelarMetodoValuacion() {
		this.nuevoMetodoValuacion = Boolean.FALSE;
		this.metodoValuacion = null;
	}

	public void cargarAreasLotePH() {

		if (informeHipotecarioDTO != null && Boolean.TRUE.equals(informeHipotecarioDTO.getSometidoAPropiedadHorizontal())
				&& informeHipotecarioDTO.getCondicionPh() != null
				&& informeHipotecarioDTO.getCondicionPh().equals("Construccion")) {
			mostrarAreasLotePH = Boolean.FALSE;
		} else {
			mostrarAreasLotePH = Boolean.TRUE;
		}

	}

	public void cargarMetodologiasSugeridas() {

		if (avaluoHipotecarioDTO.getMetodosValuacion() == null || !this.accionAgregarMetodologia) {
			avaluoHipotecarioDTO.setMetodosValuacion(new ArrayList<MetodoValuacionDTO>());
		}

		if (informeHipotecarioDTO != null && avaluoHipotecarioDTO.getMetodosValuacion().isEmpty()
				&& !accionAgregarMetodologia) {

			if (Boolean.TRUE.equals(informeHipotecarioDTO.getSometidoAPropiedadHorizontal())) {

				if (informeHipotecarioDTO.getCondicionPh() != null
						&& informeHipotecarioDTO.getCondicionPh().equals("Construccion")) {

					metodoValuacion = new ComparacionMercadoPHVentaDTO(MetodoValuacionEnum.ComparaciondeMercadoPHVenta.getKey(),
							avaluoHipotecarioDTO.getId());
					comparacionMercadoPHVentaBean.setInformeHipotecarioDTO(informeHipotecarioDTO);
					comparacionMercadoPHVentaBean.setAvaluoHipotecarioDTO(avaluoHipotecarioDTO);
					comparacionMercadoPHVentaBean.setMetodoValuacion((ComparacionMercadoPHVentaDTO) metodoValuacion);
					comparacionMercadoPHVentaBean.setAreas(listaAreas);
					comparacionMercadoPHVentaBean.setTipoGaraje(tipoGaraje);
					avaluoHipotecarioDTO.getMetodosValuacion().add(metodoValuacion);

					metodoValuacion = new ComparacionMercadoPHRentaDTO(MetodoValuacionEnum.ComparaciondeMercadoPHRenta.getKey(),
							avaluoHipotecarioDTO.getId());
					comparacionMercadoPHRentaBean.setInformeHipotecarioDTO(informeHipotecarioDTO);
					comparacionMercadoPHRentaBean.setAvaluoHipotecarioDTO(avaluoHipotecarioDTO);
					comparacionMercadoPHRentaBean.setAreas(listaAreas);
					comparacionMercadoPHRentaBean.setTipoGaraje(tipoGaraje);
					comparacionMercadoPHRentaBean.setMetodoValuacion((ComparacionMercadoPHRentaDTO) metodoValuacion);
					avaluoHipotecarioDTO.getMetodosValuacion().add(metodoValuacion);

				} else if (informeHipotecarioDTO.getCondicionPh() != null
						&& informeHipotecarioDTO.getCondicionPh().equals("Terreno")) {

					metodoValuacion = new ComparacionMercadoLoteVentaDTO(
							MetodoValuacionEnum.ComparaciondeMercadoLoteVentaSinContruccion.getKey(), avaluoHipotecarioDTO.getId());
					comparacionMercadoLoteVentaBean.setInformeHipotecarioDTO(informeHipotecarioDTO);
					comparacionMercadoLoteVentaBean.setAreas(listaAreas);
					comparacionMercadoLoteVentaBean.setMetodoValuacion((ComparacionMercadoLoteVentaDTO) metodoValuacion);
					avaluoHipotecarioDTO.getMetodosValuacion().add(metodoValuacion);

					metodoValuacion = new ComparacionMercadoLoteConstruccionVentaDTO(
							MetodoValuacionEnum.ComparaciondeMercadoLoteVentaConstruccion.getKey(), avaluoHipotecarioDTO.getId());
					comparacionMercadoLoteConstruccionVentaBean.setInformeHipotecarioDTO(informeHipotecarioDTO);
					comparacionMercadoLoteConstruccionVentaBean.setAreas(listaAreas);
					comparacionMercadoLoteConstruccionVentaBean
							.setMetodoValuacion((ComparacionMercadoLoteConstruccionVentaDTO) metodoValuacion);
					avaluoHipotecarioDTO.getMetodosValuacion().add(metodoValuacion);

					metodoValuacion = new ComparacionMercadoLoteConstruccionRentaDTO(
							MetodoValuacionEnum.ComparaciondeMercadoLoteRentaContruccion.getKey(), avaluoHipotecarioDTO.getId());
					comparacionMercadoLoteConstruccionRentaBean.setInformeHipotecarioDTO(informeHipotecarioDTO);
					comparacionMercadoLoteConstruccionRentaBean.setAreas(listaAreas);
					comparacionMercadoLoteConstruccionRentaBean
							.setMetodoValuacion((ComparacionMercadoLoteConstruccionRentaDTO) metodoValuacion);
					avaluoHipotecarioDTO.getMetodosValuacion().add(metodoValuacion);

				}

			} else {

				metodoValuacion = new ComparacionMercadoLoteVentaDTO(
						MetodoValuacionEnum.ComparaciondeMercadoLoteVentaSinContruccion.getKey(), avaluoHipotecarioDTO.getId());
				comparacionMercadoLoteVentaBean.setInformeHipotecarioDTO(informeHipotecarioDTO);
				comparacionMercadoLoteVentaBean.setMetodoValuacion((ComparacionMercadoLoteVentaDTO) metodoValuacion);
				comparacionMercadoLoteVentaBean.setAreas(listaAreas);
				avaluoHipotecarioDTO.getMetodosValuacion().add(metodoValuacion);

				metodoValuacion = new ComparacionMercadoLoteConstruccionVentaDTO(
						MetodoValuacionEnum.ComparaciondeMercadoLoteVentaConstruccion.getKey(), avaluoHipotecarioDTO.getId());
				comparacionMercadoLoteConstruccionVentaBean.setInformeHipotecarioDTO(informeHipotecarioDTO);
				comparacionMercadoLoteConstruccionVentaBean.setAreas(listaAreas);
				comparacionMercadoLoteConstruccionVentaBean
						.setMetodoValuacion((ComparacionMercadoLoteConstruccionVentaDTO) metodoValuacion);
				avaluoHipotecarioDTO.getMetodosValuacion().add(metodoValuacion);

				metodoValuacion = new ComparacionMercadoLoteConstruccionRentaDTO(
						MetodoValuacionEnum.ComparaciondeMercadoLoteRentaContruccion.getKey(), avaluoHipotecarioDTO.getId());
				comparacionMercadoLoteConstruccionRentaBean.setInformeHipotecarioDTO(informeHipotecarioDTO);
				comparacionMercadoLoteConstruccionRentaBean
						.setMetodoValuacion((ComparacionMercadoLoteConstruccionRentaDTO) metodoValuacion);
				comparacionMercadoLoteConstruccionRentaBean.setAreas(listaAreas);
				avaluoHipotecarioDTO.getMetodosValuacion().add(metodoValuacion);

			}

			actualizarMetodosValuacionDisponibles();
		}

		this.verificarMetodosDeValuacion();

	}

	public void agregarMetodoValuacion() {
		accionAgregarMetodologia = true;
		if (nuevoMetodoValuacion) {
			nuevoMetodoValuacion = false;
			// solo aplica si el metodo de valuacion es comparacion de mercado.
			if (metodoValuacion.getMetodoUsado().getValue().equals(MetodoValuacionEnum.ComparaciondeMercado.getValue())) {
				metodoValuacion = (this.informeHipotecarioDTO.getSometidoAPropiedadHorizontal()
						? new ComparacionMercadoPHDTO(metodoValuacion)
						: new ComparacionMercadoNOPHDTO(metodoValuacion));
			} else if (metodoValuacion.getMetodoUsado().getValue()
					.equals(MetodoValuacionEnum.ComparaciondeMercadoPHVenta.getValue())) {
				metodoValuacion = new ComparacionMercadoPHVentaDTO(metodoValuacion);
				comparacionMercadoPHVentaBean.setInformeHipotecarioDTO(informeHipotecarioDTO);
				comparacionMercadoPHVentaBean.setAvaluoHipotecarioDTO(avaluoHipotecarioDTO);
				comparacionMercadoPHVentaBean.setAreas(listaAreas);
				comparacionMercadoPHVentaBean.setTipoGaraje(tipoGaraje);
				comparacionMercadoPHVentaBean.setMetodoValuacion((ComparacionMercadoPHVentaDTO) metodoValuacion);
			} else if (metodoValuacion.getMetodoUsado().getValue()
					.equals(MetodoValuacionEnum.ComparaciondeMercadoPHRenta.getValue())) {
				metodoValuacion = new ComparacionMercadoPHRentaDTO(metodoValuacion);
				comparacionMercadoPHRentaBean.setAvaluoHipotecarioDTO(avaluoHipotecarioDTO);
				comparacionMercadoPHRentaBean.setInformeHipotecarioDTO(informeHipotecarioDTO);
				comparacionMercadoPHRentaBean.setAreas(listaAreas);
				comparacionMercadoPHRentaBean.setTipoGaraje(tipoGaraje);
				comparacionMercadoPHRentaBean.setMetodoValuacion((ComparacionMercadoPHRentaDTO) metodoValuacion);
			} else if (metodoValuacion.getMetodoUsado().getValue()
					.equals(MetodoValuacionEnum.ComparaciondeMercadoLoteVentaSinContruccion.getValue())) {
				metodoValuacion = new ComparacionMercadoLoteVentaDTO(metodoValuacion);
				comparacionMercadoLoteVentaBean.setInformeHipotecarioDTO(informeHipotecarioDTO);
				comparacionMercadoLoteVentaBean.setAreas(listaAreas);
				comparacionMercadoLoteVentaBean.setMetodoValuacion((ComparacionMercadoLoteVentaDTO) metodoValuacion);
			} else if (metodoValuacion.getMetodoUsado().getValue()
					.equals(MetodoValuacionEnum.ComparaciondeMercadoLoteVentaConstruccion.getValue())) {
				metodoValuacion = new ComparacionMercadoLoteConstruccionVentaDTO(metodoValuacion);
				comparacionMercadoLoteConstruccionVentaBean.setInformeHipotecarioDTO(informeHipotecarioDTO);
				comparacionMercadoLoteConstruccionVentaBean.setAreas(listaAreas);
				comparacionMercadoLoteConstruccionVentaBean
						.setMetodoValuacion((ComparacionMercadoLoteConstruccionVentaDTO) metodoValuacion);
			} else if (metodoValuacion.getMetodoUsado().getValue()
					.equals(MetodoValuacionEnum.ComparaciondeMercadoLoteRentaContruccion.getValue())) {
				metodoValuacion = new ComparacionMercadoLoteConstruccionRentaDTO(metodoValuacion);
				comparacionMercadoLoteConstruccionRentaBean.setInformeHipotecarioDTO(informeHipotecarioDTO);
				comparacionMercadoLoteConstruccionRentaBean.setAreas(listaAreas);
				comparacionMercadoLoteConstruccionRentaBean
						.setMetodoValuacion((ComparacionMercadoLoteConstruccionRentaDTO) metodoValuacion);
			}

			metodoValuacion.setAvaluoId(avaluoHipotecarioDTO.getId());
			avaluoHipotecarioDTO.getMetodosValuacion().add(metodoValuacion);
			actualizarMetodosValuacionDisponibles();
		} else {
			nuevoMetodoValuacion = true;
			if (avaluoHipotecarioDTO.getMetodosValuacion() == null) {
				avaluoHipotecarioDTO.setMetodosValuacion(new ArrayList<MetodoValuacionDTO>());
			}
			metodoValuacion = new MetodoValuacionDTO();
		}
		this.verificarMetodosDeValuacion();
		llenarCamposSujetoAvaluo(metodoValuacion);
	}

	public void cancelarNuevoMetodoValuacion() {
		nuevoMetodoValuacion = false;
	}

	public void editarMetodoDeValuacion(RowEditEvent event) {
		// TODO [Gerson] cuando se edita y se selecciona un metodo ya existente
		// en la lista
		// se debe dar una notificación y revertir el cambio.
		actualizarMetodosValuacionDisponibles();
	}

	public void cancelarMetodoDeValuacion(RowEditEvent event) {
	}

	public void abrirMensajeBorrarMetodo(MetodoValuacionDTO metodoValuacionSeleccionado) {
		this.metodoValuacionEliminar = metodoValuacionSeleccionado;
	}

	public void cancelarMensajeBorrarMetodo() {
		this.metodoValuacionEliminar = null;
	}

	public void borrarMetodoValuacion() {
		String metodoString = metodoValuacionEliminar.getMetodoUsado().getValue();
		if (metodoString.equals(MetodoValuacionEnum.MetodoReposicion.getValue())) {
			this.inicializarFitoCorvini();
		}
		if (metodoString.equals(MetodoValuacionEnum.ComparaciondeMercado.getValue())) {
			this.comparacionMercadoBean.borrarMetodoValuacion();
		}
		avaluoHipotecarioDTO.getMetodosValuacion().remove(metodoValuacionEliminar);
		this.verificarMetodosDeValuacion();
		actualizarMetodosValuacionDisponibles();
		informeHipotecarioController.borrarMetodoValuacion(metodoValuacionEliminar);
	}

	public int ordenarTablaAreas(Object descripcionArea1, Object descripcionArea2) {
		if (descripcionArea1.equals(descripcionArea2)) {
			return 0;
		}
		if (informeHipotecarioDTO.getSometidoAPropiedadHorizontal() != null
				&& informeHipotecarioDTO.getSometidoAPropiedadHorizontal()) {
			if ((descripcionArea1.equals(DescripcionAreaPH.AreaPrivada)
					&& !descripcionArea2.equals(DescripcionAreaPH.AreaPrivada))
					|| (descripcionArea1.equals(DescripcionAreaPH.AreaLibre) && descripcionArea2.equals(DescripcionAreaPH.Garaje))
					|| (descripcionArea1.equals(DescripcionAreaPH.Garaje) && descripcionArea2.equals(DescripcionAreaPH.Deposito))
					|| (descripcionArea1.equals(DescripcionAreaPH.AreaLibre)
							&& descripcionArea2.equals(DescripcionAreaPH.Deposito))
					|| (descripcionArea1.equals(DescripcionAreaPH.Deposito) && descripcionArea2.equals(DescripcionAreaPH.Otro))) {
				return -1;
			}
			if (!descripcionArea1.equals(DescripcionAreaPH.AreaPrivada)
					&& descripcionArea2.equals(DescripcionAreaPH.AreaPrivada)
					|| (descripcionArea1.equals(DescripcionAreaPH.Garaje) && descripcionArea2.equals(DescripcionAreaPH.AreaLibre))
					|| (descripcionArea1.equals(DescripcionAreaPH.Deposito) && descripcionArea2.equals(DescripcionAreaPH.Garaje))
					|| (descripcionArea1.equals(DescripcionAreaPH.Deposito)
							&& descripcionArea2.equals(DescripcionAreaPH.AreaLibre))
					|| (descripcionArea1.equals(DescripcionAreaPH.Otro) && descripcionArea2.equals(DescripcionAreaPH.Deposito))) {
				return 1;
			}
		} else {
			if (descripcionArea1.equals(DescripcionAreaNoPH.AreaTerreno)
					&& !descripcionArea2.equals(DescripcionAreaNoPH.AreaTerreno)
					|| (descripcionArea1.equals(DescripcionAreaNoPH.AreaConstruccion)
							&& descripcionArea2.equals(DescripcionAreaNoPH.Otros))) {
				return -1;
			}
			if (!descripcionArea1.equals(DescripcionAreaNoPH.AreaTerreno)
					&& descripcionArea2.equals(DescripcionAreaNoPH.AreaTerreno)
					|| descripcionArea1.equals(DescripcionAreaNoPH.Otros)
							&& descripcionArea2.equals(DescripcionAreaNoPH.AreaConstruccion)) {
				return 1;
			}
		}
		return 0;
	}

	public void inciarDescripcionAreas() {
		if (informeHipotecarioDTO.getSometidoAPropiedadHorizontal() != null
				&& informeHipotecarioDTO.getSometidoAPropiedadHorizontal()) {
			descripcionAreas = AreaDTO.DescripcionAreaPH.values();
		} else {
			descripcionAreas = AreaDTO.DescripcionAreaNoPH.values();
		}
	}

	/**
	 * Limpia la tabla de areas y si se cambia de NO PH a PH se cambia las
	 * descripciones de las areas, se agrega un area construida (El area privada) se
	 * limpia la referencia al area de terreno, se vacia la lista de las areas
	 * construidas NO PH y por último se actualiza las areas por cada garaje y por
	 * cada deposito.
	 * 
	 * Si se cambia de PH a NO PH se cambian las descripciones de areas se limpian
	 * las listas de garajes y de despositos, se limpia la referencia al objeto del
	 * area privada, se limpian los campos del panel de información de PH, se agrega
	 * una nueva area de terrreno, y por último se actualizan las areas por piso.
	 * 
	 * En los dos casos se recalculan los valores totales del avaluo.
	 */
	public void cambiarTipoConstruccion() {
		// Limpiar la tabla de areas
		listaAreas.clear();
		if (informeHipotecarioDTO.getSometidoAPropiedadHorizontal() != null
				&& informeHipotecarioDTO.getSometidoAPropiedadHorizontal()) {
			descripcionAreas = AreaDTO.DescripcionAreaPH.values();
			actualizarAreasConstruidasPorPiso(1);
			areaTerrenoDTO = null;
			this.areasConstruidasNoPH.clear();
			actualizarAreasPorGaraje();
			actualizarAreasPorDeposito();
		} else {
			descripcionAreas = AreaDTO.DescripcionAreaNoPH.values();
			removerAreasDeGaraje();
			removerAreasDeDeposito();
			areaPrivada = null;
			areasPrivadas.clear();
			limpiarCamposPH();
			listaAreas.add(crearAreaDeTerreno());
			actualizarAreasConstruidasPorPiso(informeHipotecarioDTO.getNumeroDePisos());
		}
		calcularValores();
		if (this.comparacionMercadoBean.getMetodoValuacion() != null) {
			avaluoHipotecarioDTO.getMetodosValuacion().remove(this.comparacionMercadoBean.getMetodoValuacion());
			informeHipotecarioController.borrarMetodoValuacion(this.comparacionMercadoBean.getMetodoValuacion());
			this.comparacionMercado = this.comparacionMercadoBean
					.cambiarTipoConstruccion(informeHipotecarioDTO.getSometidoAPropiedadHorizontal());
			avaluoHipotecarioDTO.getMetodosValuacion().add(this.comparacionMercado);
		}
		this.comparacionMercadoBean.setResultado(null);
		this.comparacionMercadoBean.setMostrarResultado(Boolean.FALSE);
		cargarMetodologiasSugeridas();
		cargarAreasLotePH();
	}

	private void limpiarCamposPH() {
		informeHipotecarioDTO.setConjuntoCerrado(null);
		informeHipotecarioDTO.setNumeroDeEdificios(null);
		informeHipotecarioDTO.setUnidadesPorPiso(null);
		informeHipotecarioDTO.setUbicacionDelInmueble(null);
		informeHipotecarioDTO.setTotalUnidades(null);
		informeHipotecarioDTO.setCoeficiente(null);
		informeHipotecarioDTO.setPorteria(null);
		informeHipotecarioDTO.setCitofono(null);
		informeHipotecarioDTO.setBicicletero(null);
		informeHipotecarioDTO.setPiscina(null);
		informeHipotecarioDTO.setTanqueDeAgua(null);
		informeHipotecarioDTO.setClubHouse(null);
		informeHipotecarioDTO.setGarajeVisitantes(null);
		informeHipotecarioDTO.setJuegosNinos(null);
		informeHipotecarioDTO.setCanchaMultiple(null);
		informeHipotecarioDTO.setBombaEyectora(null);
		informeHipotecarioDTO.setAireAcondicionadoCentral(null);
		informeHipotecarioDTO.setCanchaSquash(null);
		informeHipotecarioDTO.setZonasVerdesComunales(null);
		informeHipotecarioDTO.setGimnasio(null);
		informeHipotecarioDTO.setGolfito(null);
		informeHipotecarioDTO.setSalonComunal(null);
		informeHipotecarioDTO.setShutBasuras(null);
		informeHipotecarioDTO.setEquipoDePresionConstante(null);
		informeHipotecarioDTO.setPlantaElectrica(null);
		informeHipotecarioDTO.setAscensor(null);
		informeHipotecarioDTO.setOtros(null);
	}

	private void removerAreasDeDeposito() {
		areasDepositos.clear();
	}

	private void removerAreasDeGaraje() {
		areasGarajes.clear();
	}

	private void separarDireccion(String direccion) {
		Pattern patronTipoVia = Pattern.compile("[A-Z]{2}");

		if (!StringUtils.isBlank(direccion)) {
			Matcher matcher = patronTipoVia.matcher(direccion);
			if (matcher.find()) {
				tipoViaSolicitante = matcher.group(0);
				direccion = direccion.replaceFirst(tipoViaSolicitante, "").trim();
			} else {
				log.error("La dirección: {} del cliente: {} es invalida", direccion, avaluoHipotecarioDTO.getCliente());
			}
			Pattern patronNumeroVia = Pattern.compile("[0-9]+");
			matcher = patronNumeroVia.matcher(direccion);
			if (matcher.find()) {
				numeroViaSolicitante = matcher.group(0);
				direccion = direccion.replaceFirst(numeroViaSolicitante, "").trim();
			} else {
				log.error("La dirección: {} del cliente: {} es invalida", direccion, avaluoHipotecarioDTO.getCliente());
			}
			Pattern patronComplementoVia = Pattern.compile("[a-zA-Z ]+");
			matcher = patronComplementoVia.matcher(direccion);
			if (matcher.find()) {
				String stringComplementoViaSolicitante = matcher.group(0).trim().length() > 0 ? matcher.group(0).trim() : "";
				complementoViaSolicitante = stringComplementoViaSolicitante.isEmpty() ? new ArrayList<String>()
						: convertirALista(stringComplementoViaSolicitante);
				direccion = !complementoViaSolicitante.isEmpty()
						? direccion.replaceFirst(stringComplementoViaSolicitante, "").trim()
						: direccion;
			}
			Pattern patronNumeral = Pattern.compile("#");
			matcher = patronNumeral.matcher(direccion);
			if (matcher.find()) {
				direccion = direccion.replaceFirst("#", "").trim();
			}
			Pattern patronNumeroViaGeneradora = Pattern.compile("[0-9]+");
			matcher = patronNumeroViaGeneradora.matcher(direccion);
			if (matcher.find()) {
				numeroViaGeneradoraSolicitante = matcher.group(0);
				direccion = direccion.replaceFirst(numeroViaGeneradoraSolicitante, "").trim();
			}
			Pattern patronComplementoViaGeneradora = Pattern.compile("[a-zA-Z ]+");
			matcher = patronComplementoViaGeneradora.matcher(direccion);
			if (matcher.find()) {
				String stringComplementoViaGeneradoraSolicitante = matcher.group(0).trim().length() > 0
						? matcher.group(0).trim()
						: "";
				complementoViaGeneradoraSolicitante = stringComplementoViaGeneradoraSolicitante.isEmpty()
						? new ArrayList<String>()
						: convertirALista(stringComplementoViaGeneradoraSolicitante);
				direccion = !complementoViaGeneradoraSolicitante.isEmpty()
						? direccion.replace(stringComplementoViaGeneradoraSolicitante, "").trim()
						: direccion;
			}
			Pattern patronGuion = Pattern.compile("-");
			matcher = patronGuion.matcher(direccion);
			if (matcher.find()) {
				direccion = direccion.replaceFirst("-", "").trim();
			}
			Pattern patronPlaca = Pattern.compile("[0-9]+");
			matcher = patronPlaca.matcher(direccion);
			if (matcher.find()) {
				placaSolicitante = matcher.group(0);
				direccion = direccion.replaceFirst(placaSolicitante, "").trim();
			}
			Pattern patronComplementoPlaca = Pattern.compile("[A-Za-z]{2,11} [0-9]{1,6}( [A-Za-z]{2,11} [0-9]{1,6})*");
			matcher = patronComplementoPlaca.matcher(direccion);
			if (matcher.find()) {
				complementoPlacaSolicitante = matcher.group(0).trim();
				// direccion =
				// direccion.replaceFirst(complementoPlacaSolicitante,
				// "").trim();
				direccion = complementoPlacaSolicitante;
			}
			adicionalDireccionSolicitante = direccion;
		}
	}

	public void concatenarDireccionInmueble() {
		String direccionInmueble = "";
		String stringComplementoVia = convertirAString(complementoVia);
		String stringComplementoViaGeneradora = convertirAString(complementoViaGeneradora);
		if (avaluoHipotecarioDTO.getAdicionalDireccionInforme() != null
				&& !avaluoHipotecarioDTO.getAdicionalDireccionInforme().isEmpty()) {
			direccionInmueble = avaluoHipotecarioDTO.getAdicionalDireccionInforme();
		}
		if (avaluoHipotecarioDTO.getNumeroViaInforme() != null && !avaluoHipotecarioDTO.getNumeroViaInforme().isEmpty()
				&& avaluoHipotecarioDTO.getTipoViaInforme() != null && !avaluoHipotecarioDTO.getTipoViaInforme().isEmpty()
				&& avaluoHipotecarioDTO.getAdicionalDireccionInforme() != null
				&& !avaluoHipotecarioDTO.getAdicionalDireccionInforme().isEmpty()) {
			direccionInmueble = avaluoHipotecarioDTO.getTipoViaInforme()
					.concat(" " + avaluoHipotecarioDTO.getNumeroViaInforme())
					.concat(stringComplementoVia == "" ? "" : " " + stringComplementoVia)
					.concat(", " + avaluoHipotecarioDTO.getAdicionalDireccionInforme());
		}
		if (avaluoHipotecarioDTO.getNumeroViaInforme() != null && !avaluoHipotecarioDTO.getNumeroViaInforme().isEmpty()
				&& avaluoHipotecarioDTO.getTipoViaInforme() != null && !avaluoHipotecarioDTO.getTipoViaInforme().isEmpty()
				&& avaluoHipotecarioDTO.getNumeroViaGeneradoraInforme() != null
				&& !avaluoHipotecarioDTO.getNumeroViaGeneradoraInforme().isEmpty()
				&& avaluoHipotecarioDTO.getAdicionalDireccionInforme() != null
				&& !avaluoHipotecarioDTO.getAdicionalDireccionInforme().isEmpty()) {
			direccionInmueble = avaluoHipotecarioDTO.getTipoViaInforme()
					.concat(" " + avaluoHipotecarioDTO.getNumeroViaInforme())
					.concat(stringComplementoVia == "" ? "" : " " + stringComplementoVia)
					.concat(avaluoHipotecarioDTO.getNumeroViaGeneradoraInforme() == null ? ""
							: " # " + avaluoHipotecarioDTO.getNumeroViaGeneradoraInforme())
					.concat(stringComplementoViaGeneradora == "" ? "" : " " + stringComplementoViaGeneradora)
					.concat(", " + avaluoHipotecarioDTO.getAdicionalDireccionInforme());
		}
		if (avaluoHipotecarioDTO.getNumeroViaInforme() != null && !avaluoHipotecarioDTO.getNumeroViaInforme().isEmpty()
				&& avaluoHipotecarioDTO.getTipoViaInforme() != null && !avaluoHipotecarioDTO.getTipoViaInforme().isEmpty()
				&& avaluoHipotecarioDTO.getNumeroViaGeneradoraInforme() != null
				&& !avaluoHipotecarioDTO.getNumeroViaGeneradoraInforme().isEmpty()
				&& avaluoHipotecarioDTO.getPlacaInforme() != null && !avaluoHipotecarioDTO.getPlacaInforme().isEmpty()) {
			direccionInmueble = avaluoHipotecarioDTO.getTipoViaInforme()
					.concat(" " + avaluoHipotecarioDTO.getNumeroViaInforme())
					.concat(stringComplementoVia == "" ? "" : " " + stringComplementoVia)
					.concat(avaluoHipotecarioDTO.getNumeroViaGeneradoraInforme() == null ? ""
							: " # " + avaluoHipotecarioDTO.getNumeroViaGeneradoraInforme())
					.concat(stringComplementoViaGeneradora == "" ? "" : " " + stringComplementoViaGeneradora)
					.concat(avaluoHipotecarioDTO.getPlacaInforme() == null ? "" : " - " + avaluoHipotecarioDTO.getPlacaInforme())
					.concat(avaluoHipotecarioDTO.getAdicionalDireccionInforme() == null ? ""
							: " " + avaluoHipotecarioDTO.getAdicionalDireccionInforme());
		}
		direccionInmueble = direccionInmueble.replace("  #  -  ", "");
		avaluoHipotecarioDTO.setDireccionInmuebleInforme(direccionInmueble);
		avaluoHipotecarioDTO.setComplementoViaInforme(stringComplementoVia);
		avaluoHipotecarioDTO.setComplementoViaGeneradoraInforme(stringComplementoViaGeneradora);
	}

	public void concatenarDireccionSolicitante() {
		String direccionSolicitante = "";

		if (numeroViaSolicitante != null && tipoViaSolicitante != null) {
			String stringComplementoViaSolicitante = convertirAString(complementoViaSolicitante);
			String stringComplementoViaGeneradoraSolicitante = convertirAString(complementoViaGeneradoraSolicitante);
			direccionSolicitante = tipoViaSolicitante.concat(" " + numeroViaSolicitante)
					.concat(stringComplementoViaSolicitante == "" ? "" : " " + stringComplementoViaSolicitante)
					.concat(numeroViaGeneradoraSolicitante == null ? "" : " # " + numeroViaGeneradoraSolicitante)
					.concat(
							stringComplementoViaGeneradoraSolicitante == "" ? "" : " " + stringComplementoViaGeneradoraSolicitante)
					.concat(placaSolicitante == null ? "" : " - " + placaSolicitante)
					.concat(complementoPlacaSolicitante == null ? "" : " " + complementoPlacaSolicitante)
					.concat(adicionalDireccionSolicitante == null ? "" : " - " + adicionalDireccionSolicitante);
		}
		avaluoHipotecarioDTO.getCliente().setDireccionDeContactoSolicitante(direccionSolicitante);
	}

	public boolean valorTotalExcedeSubsidio() {
		return avaluoHipotecarioDTO.getTipoSubsidio() != null
				&& (avaluoHipotecarioDTO.getValorTotalAvaluo().compareTo(avaluoHipotecarioDTO.getTipoSubsidio().getMaximo()) > 0
						|| avaluoHipotecarioDTO.getValorTotalAvaluo()
								.compareTo(avaluoHipotecarioDTO.getTipoSubsidio().getMinimo()) < 0);
	}

	private List<String> convertirALista(String string) {
		if (string.isEmpty()) {
			return new ArrayList<String>();
		}
		return Arrays.asList(string.split(" "));
	}

	private String convertirAString(List<String> list) {
		if (list == null || list.isEmpty()) {
			return "";
		}
		return list.toString().replace("[", "").replace("]", "").replace(",", "");
	}

	public List<String> completarComplemento(String valor) {
		List<String> resultado = new ArrayList<String>();
		for (String letra : letrasComplemento) {
			if (letra.startsWith(valor.toUpperCase())) {
				resultado.add(letra);
			}
		}
		for (String punto : puntosCardinales) {
			if (punto.startsWith(valor.toUpperCase())) {
				resultado.add(punto);
			}
		}
		return resultado;
	}

	public List<String> completarComplementoPlaca(String valor) {
		List<String> resultado = new ArrayList<String>();
		for (String letra : valoresUnidadAlterno) {
			if (letra.startsWith(valor)) {
				resultado.add(letra);
			}
		}
		return resultado;
	}

	public void cargarGarajes() {
		if (this.avaluoHipotecarioDTO != null && this.avaluoHipotecarioDTO.getGarajes() != null
				&& !this.avaluoHipotecarioDTO.getGarajes().isEmpty()) {
			List<GarajeDTO> garajesDTO = new ArrayList<GarajeDTO>();
			garajesDTO.addAll(this.avaluoHipotecarioDTO.getGarajes());
			if (garajesDTO.get(0).getTipo() != null) {
				this.tipoGaraje = ListaTipoGaraje.fromKey(garajesDTO.get(0).getTipo());
			}
			this.informeHipotecarioDTO.setNumeroTotalDeGarajes(garajesDTO.size());
			this.calcularGarajes();
		}
	}

	public void editarFC(RowEditEvent event) {
		AreaDTO areaDTO = null;
		Object object = event.getObject();
		if (object.getClass().equals(AreaDTO.class)) {
			areaDTO = (AreaDTO) object;
			this.calcularFitoCorvini(areaDTO);
			if (this.informeHipotecarioDTO.getSometidoAPropiedadHorizontal() != null) {
				List<AreaDTO> areasASumar = (this.informeHipotecarioDTO.getSometidoAPropiedadHorizontal()) ? this.areasPrivadas
						: this.areasConstruidasNoPH;
				if (areasASumar != null && !areasASumar.isEmpty()) {
					this.sumatoria = this.fitoCorviniController.calcularSumatoriaValorReposicionFinal(areasASumar);
				}

			}
		}
	}

	public void cancelFC(RowEditEvent event) {

	}

	/**
	 * Método que se encarga de calcular: Depreciación, costo de reposición, valor
	 * depreciación, valor final, valor adoptado, valor construcción. si es php :
	 * areasPrivadas Si es No ph: areasConstruccionNoPH.
	 *
	 */
	public void onCellEditFC(CellEditEvent event) {
		Object newValue = event.getNewValue();
		AreaDTO areaDTO = (this.informeHipotecarioDTO.getSometidoAPropiedadHorizontal()
				? this.areasPrivadas.get(event.getRowIndex())
				: this.areasConstruidasNoPH.get(event.getRowIndex()));
		String nombreColumna = event.getColumn().getClientId();
		if (nombreColumna.contains("valorReposicion")) {
			BigDecimal valorReposicion = (BigDecimal) newValue;
			areaDTO.setValorReposicion(valorReposicion);
		}
		if (nombreColumna.contains("calificacion")) {
			BigDecimal calificacion = (BigDecimal) newValue;
			areaDTO.setCalificacion(calificacion);
		}
		this.calcularFitoCorvini(areaDTO);
		if (this.informeHipotecarioDTO.getSometidoAPropiedadHorizontal() != null) {
			List<AreaDTO> areasASumar = (this.informeHipotecarioDTO.getSometidoAPropiedadHorizontal()) ? this.areasPrivadas
					: this.areasConstruidasNoPH;
			if (areasASumar != null && !areasASumar.isEmpty()) {
				this.sumatoria = this.fitoCorviniController.calcularSumatoriaValorReposicionFinal(areasASumar);
			}

		}

	}

	public void calcularFitoCorvini(AreaDTO area) {
		BigDecimal edad = this.informeHipotecarioDTO.getVetustez() == null ? BigDecimal.ZERO
				: new BigDecimal(this.informeHipotecarioDTO.getVetustez());
		edad.setScale(2);
		area.setEdad(edad);
		if (this.informeHipotecarioDTO.getEstructura() != null) {
			Integer vidaUtil = this.fitoCorviniController
					.calcularVidaUtil(this.informeHipotecarioDTO.getEstructura().getKey());
			area.setVidaUtil(new BigDecimal(vidaUtil));
		}
		areaDTO = this.fitoCorviniController.calcularFitoCorvini(area);

		/*
		 * @TODO: Por ahora se trabaja solo con formato hipotecario, quitar esta
		 * verificacion cuando ya se trabaje con formato comercial
		 */
		if (this.informeHipotecarioDTO.getClass().equals(FormatoInformeHipotecarioDTO.class)) {
			areaDTO = this.fitoCorviniController.calcularValorReposicion(area,
					this.informeHipotecarioDTO.getSometidoAPropiedadHorizontal(), this.informeHipotecarioDTO, this.areaPrivada);
		} else {
			System.err.println("NO SE ESTA CALCULANDO FITO Y CORVINI PORQUE NO ES FORMATO HIPOTECARIO");
		}

	}

	/**
	 * Método que se encarga de recalcular todos los valores de fito y corvini
	 * cuando se modifica : Ano de construcción,
	 *
	 */
	public void recalcularFitoCorvini() {
		// this.separarAreas();
		if (this.informeHipotecarioDTO.getSometidoAPropiedadHorizontal() != null) {
			List<AreaDTO> areas = (this.informeHipotecarioDTO.getSometidoAPropiedadHorizontal() ? this.areasPrivadas
					: this.areasConstruidasNoPH);
			if (areas != null) {
				for (AreaDTO area : areas) {
					this.calcularFitoCorvini(area);
				}
			}
		}
		if (this.informeHipotecarioDTO.getSometidoAPropiedadHorizontal() != null) {
			List<AreaDTO> areasASumar = (this.informeHipotecarioDTO.getSometidoAPropiedadHorizontal()) ? this.areasPrivadas
					: this.areasConstruidasNoPH;
			if (areasASumar != null && !areasASumar.isEmpty()) {
				this.sumatoria = this.fitoCorviniController.calcularSumatoriaValorReposicionFinal(areasASumar);
			}
		}

	}

	/**
	 * Cuando se elimina la metodologia fito y corvini se deben poner en cero los
	 * valores que fueron calculados previamente.
	 *
	 */
	public void inicializarFitoCorvini() {
		if (this.informeHipotecarioDTO.getSometidoAPropiedadHorizontal() != null) {
			List<AreaDTO> areas = (this.informeHipotecarioDTO.getSometidoAPropiedadHorizontal() ? this.areasPrivadas
					: this.areasConstruidasNoPH);
			for (AreaDTO area : areas) {
				this.fitoCorviniController.inicializarFitoCorvini(area);
			}
		}

	}

	/**
	 * Función que verifica si se marco el método de valuación especificado.
	 *
	 * @param nombreMetodo del método a verificar
	 * @return estado de la verificación
	 */
	public boolean verificarMetodo(String nombreMetodo) {
		List<MetodoValuacionDTO> metodosValuacion = this.avaluoHipotecarioDTO.getMetodosValuacion();
		if (metodosValuacion != null) {
			for (MetodoValuacionDTO metodo : metodosValuacion) {
				String metodoString = metodo.getMetodoUsado().getValue();
				if (metodoString.equals(nombreMetodo)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Método que verifica si el metodo de valuacion agregado es : reposicion o
	 * comparacion de mercado.
	 *
	 */
	public void verificarMetodosDeValuacion() {
		this.mostrarMetodoReposicion = this.verificarMetodo(MetodoValuacionEnum.MetodoReposicion.getValue());

		if (this.mostrarMetodoReposicion) {
			this.recalcularFitoCorvini();
		}
		Boolean comparacionMercado = this.verificarMetodo(MetodoValuacionEnum.ComparaciondeMercado.getValue());

		this.mostrarMetodoComparacionMercadoPHVenta = this
				.verificarMetodo(MetodoValuacionEnum.ComparaciondeMercadoPHVenta.getValue());

		this.mostrarMetodoComparacionMercadoPHRenta = this
				.verificarMetodo(MetodoValuacionEnum.ComparaciondeMercadoPHRenta.getValue());

		this.mostrarMetodoComparacionMercadoLoteRentaConContruccion = this
				.verificarMetodo(MetodoValuacionEnum.ComparaciondeMercadoLoteRentaContruccion.getValue());

		this.mostrarMetodoComparacionMercadoLoteVentaConContruccion = this
				.verificarMetodo(MetodoValuacionEnum.ComparaciondeMercadoLoteVentaConstruccion.getValue());

		this.mostrarMetodoComparacionMercadoLoteVentaSinContruccion = this
				.verificarMetodo(MetodoValuacionEnum.ComparaciondeMercadoLoteVentaSinContruccion.getValue());

		// Se verifica que el metodo que se cargo es comparacion de mercado
		if (this.metodoValuacion != null && comparacionMercado != null && comparacionMercado
				&& this.comparacionMercadoBean.getMetodoValuacion() == null) {
			this.comparacionMercado = this.metodoValuacion;
			this.comparacionMercadoBean.setMetodoValuacion(this.comparacionMercado);
			this.comparacionMercadoBean
					.setSometidoPropiedadHorizontal(this.informeHipotecarioDTO.getSometidoAPropiedadHorizontal() != null
							? this.informeHipotecarioDTO.getSometidoAPropiedadHorizontal()
							: false);
		}
		this.comparacionMercadoBean.setMostrar(comparacionMercado);
		// this.comparacionMercadoBean.setMostrarResultado(comparacionMercado);
	}

	public boolean verificarMetodoAgregado(MetodoValuacionEnum metodo) {
		if (this.metodoValuacion != null && this.metodoValuacion.getMetodoUsado() != null
				&& this.metodoValuacion.getMetodoUsado().getValue() != null) {
			return this.metodoValuacion.getMetodoUsado().getValue().equals(metodo.getValue());
		}
		return false;
	}

	public void cargarMetodosValuacion() {
		actualizarMetodosValuacionDisponibles();
		this.mostrarMetodoReposicion = this.verificarMetodo(MetodoValuacionEnum.MetodoReposicion.getValue());
		if (this.mostrarMetodoReposicion) {
			this.recalcularFitoCorvini();
		}

		// valida los campos de "sujeto" en la tabla de metodologias contra los
		// campos del avaluo
		if (this.avaluoHipotecarioDTO.getMetodosValuacion() != null) {
			for (MetodoValuacionDTO metodoValuacion : this.avaluoHipotecarioDTO.getMetodosValuacion()) {
				llenarCamposSujetoAvaluo(metodoValuacion);
			}
		}

		this.mostrarMetodoComparacionMercadoPHVenta = this
				.verificarMetodo(MetodoValuacionEnum.ComparaciondeMercadoPHVenta.getValue());

		this.mostrarMetodoComparacionMercadoPHRenta = this
				.verificarMetodo(MetodoValuacionEnum.ComparaciondeMercadoPHRenta.getValue());

		this.mostrarMetodoComparacionMercadoLoteRentaConContruccion = this
				.verificarMetodo(MetodoValuacionEnum.ComparaciondeMercadoLoteRentaContruccion.getValue());

		this.mostrarMetodoComparacionMercadoLoteVentaConContruccion = this
				.verificarMetodo(MetodoValuacionEnum.ComparaciondeMercadoLoteVentaConstruccion.getValue());

		this.mostrarMetodoComparacionMercadoLoteVentaSinContruccion = this
				.verificarMetodo(MetodoValuacionEnum.ComparaciondeMercadoLoteVentaSinContruccion.getValue());

		// si existe comparacion de mercado la carga.
		if (this.avaluoHipotecarioDTO.getMetodosValuacion() != null) {
			for (MetodoValuacionDTO metodo : this.avaluoHipotecarioDTO.getMetodosValuacion()) {
				String metodoString = metodo.getMetodoUsado().getValue();
				if (metodoString.equals(MetodoValuacionEnum.ComparaciondeMercado.getValue())) {
					this.comparacionMercadoBean
							.setSometidoPropiedadHorizontal(this.informeHipotecarioDTO.getSometidoAPropiedadHorizontal() != null
									? this.informeHipotecarioDTO.getSometidoAPropiedadHorizontal()
									: false);
					this.comparacionMercadoBean.setMetodoValuacion(metodo);
					this.comparacionMercadoBean.setMostrar(Boolean.TRUE);
					// this.comparacionMercadoBean.setMostrarResultado(Boolean.TRUE);
				}

				if (metodoString.equals(MetodoValuacionEnum.ComparaciondeMercadoPHVenta.getValue())) {
					comparacionMercadoPHVentaBean.setMetodoValuacion((ComparacionMercadoPHVentaDTO) metodo);
					comparacionMercadoPHVentaBean.setAvaluoHipotecarioDTO(avaluoHipotecarioDTO);
					comparacionMercadoPHVentaBean.setInformeHipotecarioDTO(informeHipotecarioDTO);
					comparacionMercadoPHVentaBean.setAreas(listaAreas);
					comparacionMercadoPHVentaBean.setTipoGaraje(tipoGaraje);
				}

				if (metodoString.equals(MetodoValuacionEnum.ComparaciondeMercadoPHRenta.getValue())) {
					comparacionMercadoPHRentaBean.setMetodoValuacion((ComparacionMercadoPHRentaDTO) metodo);
					comparacionMercadoPHRentaBean.setAvaluoHipotecarioDTO(avaluoHipotecarioDTO);
					comparacionMercadoPHRentaBean.setInformeHipotecarioDTO(informeHipotecarioDTO);
					comparacionMercadoPHRentaBean.setAreas(listaAreas);
					comparacionMercadoPHRentaBean.setTipoGaraje(tipoGaraje);
				}

				if (metodoString.equals(MetodoValuacionEnum.ComparaciondeMercadoLoteVentaSinContruccion.getValue())) {
					comparacionMercadoLoteVentaBean.setMetodoValuacion((ComparacionMercadoLoteVentaDTO) metodo);
					comparacionMercadoLoteVentaBean.setInformeHipotecarioDTO(informeHipotecarioDTO);
					comparacionMercadoLoteVentaBean.setAreas(listaAreas);
				}

				if (metodoString.equals(MetodoValuacionEnum.ComparaciondeMercadoLoteVentaConstruccion.getValue())) {
					comparacionMercadoLoteConstruccionVentaBean
							.setMetodoValuacion((ComparacionMercadoLoteConstruccionVentaDTO) metodo);
					comparacionMercadoLoteConstruccionVentaBean.setInformeHipotecarioDTO(informeHipotecarioDTO);
					comparacionMercadoLoteConstruccionVentaBean.setAreas(listaAreas);
				}

				if (metodoString.equals(MetodoValuacionEnum.ComparaciondeMercadoLoteRentaContruccion.getValue())) {
					comparacionMercadoLoteConstruccionRentaBean
							.setMetodoValuacion((ComparacionMercadoLoteConstruccionRentaDTO) metodo);
					comparacionMercadoLoteConstruccionRentaBean.setInformeHipotecarioDTO(informeHipotecarioDTO);
					comparacionMercadoLoteConstruccionRentaBean.setAreas(listaAreas);
				}
			}
		}

	}

	public void actualizarMetodosValuacionDisponibles() {

		setMetodosValuacionDisponibles(Arrays.asList(MetodoValuacionDTO.MetodoValuacionEnum.values()));

		Predicate<MetodoValuacionDTO.MetodoValuacionEnum> metodosValuacionSinUsar = new Predicate<MetodoValuacionDTO.MetodoValuacionEnum>() {
			@Override
			public boolean apply(MetodoValuacionEnum input) {
				if (input.equals(MetodoValuacionEnum.ComparaciondeMercado)) {
					return false;
				}

				if (avaluoHipotecarioDTO.getMetodosValuacion() != null
						&& !avaluoHipotecarioDTO.getMetodosValuacion().isEmpty()) {
					for (MetodoValuacionDTO metodoValuacionDTO : avaluoHipotecarioDTO.getMetodosValuacion()) {
						if (metodoValuacionDTO.getMetodoUsado().equals(input)) {
							return false;
						}
					}
				}
				return true;
			}
		};

		setMetodosValuacionDisponibles(new ArrayList<MetodoValuacionDTO.MetodoValuacionEnum>(
				Collections2.filter(getMetodosValuacionDisponibles(), metodosValuacionSinUsar)));

	}

	public void confirmarPago() {
		if (!informeHipotecarioController.confirmarPago(avaluoHipotecarioDTO, usuarioActivo)) {
			FacesContext.getCurrentInstance().addMessage("growl",
					new FacesMessage("Error", "No es posible confirmar el pago."));
		}
	}

	public void confirmarDocumentosCompletos() {
		guardarSinEnviar();
		if (!informeHipotecarioController.confirmarDocumentosCompletos(avaluoHipotecarioDTO, usuarioActivo)) {
			FacesContext.getCurrentInstance().addMessage("growl",
					new FacesMessage("Documentos No Confirmados", "Ocurrio un error los documentos no se confirmaron."));
		} else {
			FacesContext.getCurrentInstance().addMessage("growl",
					new FacesMessage("Documentos Confirmados", "Los documentos de la solicitud Nª "
							+ avaluoHipotecarioDTO.getCodigoExterno() + " fueron confirmados exitosamente."));
		}
	}

	/**
	 * Método que permite obtener la lista de areas que se puede agregar en valor
	 * comercial, de acuerdo al tipo de contrucción.
	 * 
	 * @return List<ListaDesplegable> Lista con los nombres de las areas que se
	 *         pueden agregar.
	 */
	public List<ListaDesplegable> getDescripcionNuevasAreas() {
		List<ListaDesplegable> areas;
		if (informeHipotecarioDTO.getSometidoAPropiedadHorizontal() != null
				&& informeHipotecarioDTO.getSometidoAPropiedadHorizontal()) {

			areas = Arrays.asList(AreaDTO.DescripcionAreaPH.values());
		} else {
			areas = Arrays.asList(AreaDTO.DescripcionAreaNoPH.values());
		}
		return areas;
	}

	public void cambiarTipoGaraje() {
		if (this.avaluoHipotecarioDTO.getGarajes() == null) {
			this.avaluoHipotecarioDTO.setGarajes(new ArrayList<GarajeDTO>());
			this.informeHipotecarioDTO.setNumeroTotalDeGarajes(1);
		} else {
			this.avaluoHipotecarioDTO.getGarajes().clear();
			this.informeHipotecarioDTO
					.setNumeroTotalDeGarajes(this.tipoGaraje == null ? 0 : this.tipoGaraje.getKey() == 0 ? 0 : 1);
		}
		this.actualizarGarajes();
	}

	public void actualizarGarajes() {
		if (this.avaluoHipotecarioDTO.getGarajes() != null
				&& this.avaluoHipotecarioDTO.getGarajes().size() > this.informeHipotecarioDTO.getNumeroTotalDeGarajes()) {
			List<GarajeDTO> garajes = new ArrayList<GarajeDTO>(this.avaluoHipotecarioDTO.getGarajes().size());
			garajes.addAll(this.avaluoHipotecarioDTO.getGarajes());

			List<Integer> indices = new ArrayList<Integer>(this.avaluoHipotecarioDTO.getGarajes().size());
			for (int i = this.informeHipotecarioDTO.getNumeroTotalDeGarajes(); i < this.avaluoHipotecarioDTO.getGarajes()
					.size(); i++) {
				indices.add(i);
			}
			for (Integer indice : indices) {
				GarajeDTO garaje = this.avaluoHipotecarioDTO.getGarajes().get(indice);
				garajes.remove(garaje);
			}
			// TODO: verificar al generar modificaicones si al setear los
			// garajes
			// son completamente difirentes
			this.avaluoHipotecarioDTO.setGarajes(garajes);
		} else {

			for (int i = this.avaluoHipotecarioDTO.getGarajes().size(); i < this.informeHipotecarioDTO
					.getNumeroTotalDeGarajes(); i++) {
				GarajeDTO garaje = new GarajeDTO();
				if (this.tipoGaraje != null) {
					garaje.setTipo(this.tipoGaraje.getKey());
				}

				garaje.setAvaluoId(this.avaluoHipotecarioDTO.getId());
				this.avaluoHipotecarioDTO.getGarajes().add(garaje);

			}

		}
		this.calcularGarajes();
		if (this.informeHipotecarioDTO.getSometidoAPropiedadHorizontal() != null
				&& this.informeHipotecarioDTO.getSometidoAPropiedadHorizontal()) {
			this.actualizarAreasPorGaraje();
		}

		this.actualizarMetodologiasCamposSujeto();
	}

	/**
	 * 
	 * Funcion que se llama cuando se cambia el numero de garaje y se debe
	 * actualizar el nombre en la lista de areas de valor comercial.
	 */
	public void actualizarNumeroGarajeAreas() {
		if (this.informeHipotecarioDTO.getSometidoAPropiedadHorizontal() != null
				&& this.informeHipotecarioDTO.getSometidoAPropiedadHorizontal()) {
			this.listaAreas.removeAll(this.areasGarajes);
			this.areasGarajes.clear();
			this.actualizarAreasPorGaraje();
		}

	}

	/**
	 * Funcion que se encarga de calcular el total de garajes y el numero de cupos.
	 */
	public void calcularGarajes() {
		if (this.avaluoHipotecarioDTO.getGarajes() != null) {
			int totalGarajes = this.avaluoHipotecarioDTO.getGarajes().size();
			int totalCupos = this.avaluoHipotecarioDTO.getGarajes().size();
			for (GarajeDTO garaje : this.avaluoHipotecarioDTO.getGarajes()) {
				if (garaje.isDoble()) {
					totalCupos += 1;
				}
			}
			this.informeHipotecarioDTO.setTotalCuposParquedaro(totalCupos);
			this.informeHipotecarioDTO.setNumeroTotalDeGarajes(totalGarajes);
		}
	}

	/**
	 * Se encarga de verificar las matricula del garaje.
	 */
	public void verificarMatriculasGarajes(GarajeDTO garaje) {
		String matricula = garaje.getMatriculaInmobiliaria();
		if (this.tipoGaraje != null && this.tipoGaraje.getKey() == 1) {
			// Verificacion con la matricula inmboiliaria principal.
			if (matricula != null && matricula.equals(avaluoHipotecarioDTO.getMatriculaInmobiliariaPrincipal1())) {
				FacesContext.getCurrentInstance().addMessage("growl",
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Matricula inmobiliaria duplicada",
								"Matricula inmobiliaria del garaje duplicada con la matricula inmobiliaria principal 1"));
			}
			// MATRICULAS INUMBULIARIAS GARAJES
			if (this.avaluoHipotecarioDTO.getGarajes() != null) {
				// se quita el garaje a ser comparado para no compararse con el
				// mismo.
				List<GarajeDTO> remove = new ArrayList<GarajeDTO>();
				remove.add(garaje);
				List<GarajeDTO> diferencia = new ArrayList<GarajeDTO>();
				diferencia.addAll(this.avaluoHipotecarioDTO.getGarajes());
				diferencia.removeAll(remove);

				for (GarajeDTO garajeDTO : diferencia) {
					if (matricula != null && matricula.equals(garajeDTO.getMatriculaInmobiliaria())) {
						FacesContext.getCurrentInstance().addMessage("growl",
								new FacesMessage(FacesMessage.SEVERITY_ERROR, "Matricula inmobiliaria duplicada",
										"Matricula inmobiliaria duplicada con la matricula inmobiliaria del garaje con matricula: "
												+ garajeDTO.getMatriculaInmobiliaria()));
					}
				}
			}
			// DEPOSITOS PRIVADOS
			if (this.informeHipotecarioDTO.getDepositosPrivados() != null
					&& this.informeHipotecarioDTO.getDepositosPrivados() > 0) {
				String[] depositosPrivados = { informeHipotecarioDTO.getMatriculaInmobiliariaDeposito1(),
						informeHipotecarioDTO.getMatriculaInmobiliariaDeposito2(),
						informeHipotecarioDTO.getMatriculaInmobiliariaDeposito3(),
						informeHipotecarioDTO.getMatriculaInmobiliariaDeposito4(),
						informeHipotecarioDTO.getMatriculaInmobiliariaDeposito5() };
				List<String> depositos = new ArrayList<String>(Arrays.asList(depositosPrivados));
				for (String matriculaDeposito : depositos) {
					if (matricula != null && matricula.equals(matriculaDeposito)) {
						FacesContext.getCurrentInstance().addMessage("growl",
								new FacesMessage(FacesMessage.SEVERITY_ERROR, "Matricula inmobiliaria duplicada",
										"Matricula inmobiliaria duplicada con la matricula inmobiliaria del deposito con matricula: "
												+ matriculaDeposito));
					}
				}
			}
		}
	}

	public void onClienteSeleccionado() {
		// 1. verificar si el cliente con documento modificado existe si es asi
		// asignarlo al avaluo
		// 2. si el cliente con documento existe,y es el mismo del informe
		// actualizar
		// 3. si no existe crear
		ClienteDTO clienteSeleccionado = this.avaluoHipotecarioDTO.getCliente();

		/** El informe tiene un cliente asiganado */
		if (clienteActual != null) {
			this.clienteNuevo = Boolean.FALSE;
			ClienteDTO cliente = this.clienteController.buscarCliente(clienteSeleccionado.getTipoDocumentoIdentificacion(),
					clienteSeleccionado.getNumeroDocumento());
			/** Si el cliente existe */
			if (cliente != null) {
				if (cliente.getNumeroDocumento() == clienteActual.getNumeroDocumento()
						&& cliente.getTipoDocumentoIdentificacion() == clienteActual.getTipoDocumentoIdentificacion()) {
					/**
					 * se actualiza el cliente actual ya que lo que se modifico fue informacion
					 * diferente a numero y tipo
					 */
					this.clienteController.actualizar(clienteSeleccionado);
					try {
						this.clienteActual = (ClienteDTO) clienteSeleccionado.clone();
					} catch (CloneNotSupportedException e) {
						e.printStackTrace();
					}
				} else {
					/**
					 * Se cambio el numero de cliente o tipo pero existe en el sistema lo que
					 * significa que se esta haciendo un cambio de cliente.
					 */
					this.avaluoHipotecarioDTO.setCliente(cliente);
					try {
						this.clienteActual = (ClienteDTO) cliente.clone();
					} catch (CloneNotSupportedException e) {
						e.printStackTrace();
					}
				}
			}
			/** El cliente no existe en el sistema, se crea */
			else {
				this.clienteNuevo = Boolean.TRUE;
				ClienteDTO clienteNuevo = new ClienteDTO();
				clienteNuevo.setNumeroDocumento(clienteSeleccionado.getNumeroDocumento());
				clienteNuevo.setTipoDocumentoIdentificacion(clienteSeleccionado.getTipoDocumentoIdentificacion());
				// this.clienteController.crear(clienteNuevo);
				this.avaluoHipotecarioDTO.setCliente(clienteNuevo);
				try {
					this.clienteActual = (ClienteDTO) clienteNuevo.clone();
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public void actualizarMetodologiasCamposSujeto() {

		if (this.avaluoHipotecarioDTO.getMetodosValuacion() != null) {
			for (int i = 0; i < this.avaluoHipotecarioDTO.getMetodosValuacion().size(); i++) {
				MetodoValuacionDTO metodo = this.avaluoHipotecarioDTO.getMetodosValuacion().get(i);
				String metodoString = metodo.getMetodoUsado().getValue();

				if (metodoString.equals(MetodoValuacionEnum.ComparaciondeMercadoPHVenta.getValue())) {
					llenarCamposSujetoAvaluo(comparacionMercadoPHVentaBean.getMetodoValuacion());
				} else if (metodoString.equals(MetodoValuacionEnum.ComparaciondeMercadoPHRenta.getValue())) {
					llenarCamposSujetoAvaluo(comparacionMercadoPHRentaBean.getMetodoValuacion());
				} else if (metodoString.equals(MetodoValuacionEnum.ComparaciondeMercadoLoteVentaSinContruccion.getValue())) {
					llenarCamposSujetoAvaluo(comparacionMercadoLoteVentaBean.getMetodoValuacion());
				} else if (metodoString.equals(MetodoValuacionEnum.ComparaciondeMercadoLoteVentaConstruccion.getValue())) {
					llenarCamposSujetoAvaluo(comparacionMercadoLoteConstruccionVentaBean.getMetodoValuacion());
				} else if (metodoString.equals(MetodoValuacionEnum.ComparaciondeMercadoLoteVentaConstruccion.getValue())) {
					llenarCamposSujetoAvaluo(comparacionMercadoLoteConstruccionRentaBean.getMetodoValuacion());
				}
			}
		}
	}

	public void validarCambiosCamposSujeto(MetodoValuacionDTO metodoValuacion) {

		boolean diferente = false;

		if (metodoValuacion instanceof ComparacionMercadoPHVentaDTO) {

			ComparacionMercadoPHVentaDTO metodoPHVenta = (ComparacionMercadoPHVentaDTO) metodoValuacion;

			if (metodoPHVenta.getPiso() != null && !metodoPHVenta.getPiso().equals(informeHipotecarioDTO.getNumeroPiso())) {
				diferente = true;
			}

			if (Boolean.TRUE.equals(informeHipotecarioDTO.getSometidoAPropiedadHorizontal())
					&& metodoPHVenta.getArea() != null && avaluoHipotecarioDTO.getAreaConstruccionAvaluoDTO() != null
					&& !metodoPHVenta.getArea().equals(avaluoHipotecarioDTO.getAreaConstruccionAvaluoDTO().getAreaConstruida())) {
				diferente = true;
			}

			if (metodoPHVenta.getGarajes() != null
					&& !metodoPHVenta.getGarajes().equals(informeHipotecarioDTO.getNumeroTotalDeGarajes())) {
				diferente = true;
			}

			if (metodoPHVenta.getEdad() != null && !metodoPHVenta.getEdad().equals(informeHipotecarioDTO.getVetustez())) {
				diferente = true;
			}

			if ((metodoPHVenta.getValorAdministracion() == null && informeHipotecarioDTO.getValorAdministracion() != null)
					|| (metodoPHVenta.getValorAdministracion() != null && informeHipotecarioDTO.getValorAdministracion() == null)
					|| (metodoPHVenta.getValorAdministracion() != null && informeHipotecarioDTO.getValorAdministracion() != null
							&& metodoPHVenta.getValorAdministracion().intValue() != informeHipotecarioDTO.getValorAdministracion())) {
				diferente = true;
			}

			if (diferente) {
				mostrarMensajeValidacionSujetoPHVenta = true;
			} else {
				mostrarMensajeValidacionSujetoPHVenta = false;
			}

			RequestContext.getCurrentInstance().update(
					"informeHipotecario:accordionPanel:accordionPanelMetodologias:ofertasPHV:panelSujetoPHV informeHipotecario:accordionPanel:accordionPanelMetodologias:ofertasPHV:alertaPHV");

		} else if (metodoValuacion instanceof ComparacionMercadoPHRentaDTO) {

			ComparacionMercadoPHRentaDTO metodoPHRenta = (ComparacionMercadoPHRentaDTO) metodoValuacion;

			if (metodoPHRenta.getPiso() != null && !metodoPHRenta.getPiso().equals(informeHipotecarioDTO.getNumeroPiso())) {
				diferente = true;
			}

			if (Boolean.TRUE.equals(informeHipotecarioDTO.getSometidoAPropiedadHorizontal())
					&& avaluoHipotecarioDTO.getAreaConstruccionAvaluoDTO() != null && metodoPHRenta.getArea() != null
					&& !metodoPHRenta.getArea().equals(avaluoHipotecarioDTO.getAreaConstruccionAvaluoDTO().getAreaConstruida())) {
				diferente = true;
			}

			if (metodoPHRenta.getGarajes() != null
					&& !metodoPHRenta.getGarajes().equals(informeHipotecarioDTO.getNumeroTotalDeGarajes())) {
				diferente = true;
			}

			if (metodoPHRenta.getEdad() != null && !metodoPHRenta.getEdad().equals(informeHipotecarioDTO.getVetustez())) {
				diferente = true;
			}

			if ((metodoPHRenta.getValorAdministracion() == null && informeHipotecarioDTO.getValorAdministracion() != null)
					|| (metodoPHRenta.getValorAdministracion() != null && informeHipotecarioDTO.getValorAdministracion() == null)
					|| (metodoPHRenta.getValorAdministracion() != null && informeHipotecarioDTO.getValorAdministracion() != null
							&& metodoPHRenta.getValorAdministracion().intValue() != informeHipotecarioDTO.getValorAdministracion())) {
				diferente = true;
			}

			if (diferente) {
				mostrarMensajeValidacionSujetoPHRenta = true;
			} else {
				mostrarMensajeValidacionSujetoPHRenta = false;
			}

			RequestContext.getCurrentInstance().update(
					"informeHipotecario:accordionPanel:accordionPanelMetodologias:ofertasPHR:panelSujetoPHR informeHipotecario:accordionPanel:accordionPanelMetodologias:ofertasPHV:alertaPHR");

		} else if (metodoValuacion instanceof ComparacionMercadoLoteVentaDTO) {

			ComparacionMercadoLoteVentaDTO metodoLoteVenta = (ComparacionMercadoLoteVentaDTO) metodoValuacion;

			if (avaluoHipotecarioDTO.getAreaLoteAvaluoDTO() != null && metodoLoteVenta.getAreaLote() != null
					&& !metodoLoteVenta.getAreaLote().equals(avaluoHipotecarioDTO.getAreaLoteAvaluoDTO().getArea())) {
				diferente = true;
			}

			if (diferente) {
				mostrarMensajeValidacionSujetoLoteVenta = true;
			} else {
				mostrarMensajeValidacionSujetoLoteVenta = false;
			}

			RequestContext.getCurrentInstance().update(
					"informeHipotecario:accordionPanel:accordionPanelMetodologias:ofertasLSCV:panelSujetoLSCV informeHipotecario:accordionPanel:accordionPanelMetodologias:ofertasLSCV:alertaLSCV");

		} else if (metodoValuacion instanceof ComparacionMercadoLoteConstruccionVentaDTO) {

			ComparacionMercadoLoteConstruccionVentaDTO metodoLoteConstruccionVenta = (ComparacionMercadoLoteConstruccionVentaDTO) metodoValuacion;

			if (metodoLoteConstruccionVenta.getEdad() != null
					&& !metodoLoteConstruccionVenta.getEdad().equals(informeHipotecarioDTO.getVetustez())) {
				diferente = true;
			}

			if (avaluoHipotecarioDTO.getAreaLoteAvaluoDTO() != null && metodoLoteConstruccionVenta.getAreaLote() != null
					&& !metodoLoteConstruccionVenta.getAreaLote().equals(avaluoHipotecarioDTO.getAreaLoteAvaluoDTO().getArea())) {
				diferente = true;
			}

			if (Boolean.TRUE.equals(informeHipotecarioDTO.getSometidoAPropiedadHorizontal())
					&& avaluoHipotecarioDTO.getAreaConstruccionAvaluoDTO() != null
					&& metodoLoteConstruccionVenta.getAreaConsttruida() != null && !metodoLoteConstruccionVenta
							.getAreaConsttruida().equals(avaluoHipotecarioDTO.getAreaConstruccionAvaluoDTO().getAreaConstruida())) {
				diferente = true;
			}

			if (diferente) {
				mostrarMensajeValidacionSujetoLoteconstruccionVenta = true;
			} else {
				mostrarMensajeValidacionSujetoLoteconstruccionVenta = false;
			}

			RequestContext.getCurrentInstance().update(
					"informeHipotecario:accordionPanel:accordionPanelMetodologias:ofertasLCV:panelSujetoLCV informeHipotecario:accordionPanel:accordionPanelMetodologias:ofertasPHV:alertaLCV");
			RequestContext.getCurrentInstance().update("");

		} else if (metodoValuacion instanceof ComparacionMercadoLoteConstruccionRentaDTO) {

			ComparacionMercadoLoteConstruccionRentaDTO metodoLoteConstruccionRenta = (ComparacionMercadoLoteConstruccionRentaDTO) metodoValuacion;

			if (metodoLoteConstruccionRenta.getEdad() != null
					&& !metodoLoteConstruccionRenta.getEdad().equals(informeHipotecarioDTO.getVetustez())) {
				diferente = true;
			}

			if (avaluoHipotecarioDTO.getAreaLoteAvaluoDTO().getArea() != null
					&& metodoLoteConstruccionRenta.getAreaLote() != null
					&& !metodoLoteConstruccionRenta.getAreaLote().equals(avaluoHipotecarioDTO.getAreaLoteAvaluoDTO().getArea())) {
				diferente = true;
			}

			if (Boolean.TRUE.equals(informeHipotecarioDTO.getSometidoAPropiedadHorizontal())
					&& avaluoHipotecarioDTO.getAreaConstruccionAvaluoDTO() != null
					&& metodoLoteConstruccionRenta.getAreaConsttruida() != null && !metodoLoteConstruccionRenta
							.getAreaConsttruida().equals(avaluoHipotecarioDTO.getAreaConstruccionAvaluoDTO().getAreaConstruida())) {
				diferente = true;
			}

			if (diferente) {
				mostrarMensajeValidacionSujetoLoteconstruccionRenta = true;
			} else {
				mostrarMensajeValidacionSujetoLoteconstruccionRenta = false;
			}

			RequestContext.getCurrentInstance().update(
					"informeHipotecario:accordionPanel:accordionPanelMetodologias:ofertasLCR:panelSujetoLCR informeHipotecario:accordionPanel:accordionPanelMetodologias:ofertasPHV:alertaLCR");

		}

	}

	/**
	 * 
	 * Metodo que valida los campos de "Sujeto" de la calculadora si los campos del
	 * metodo estan nulos, se llenan los campos con los valores traidos de
	 * FormatoInformeHipotecario y AvaluoDTO
	 * 
	 * 
	 * @param metodoValuacion
	 */
	public void llenarCamposSujetoAvaluo(MetodoValuacionDTO metodoValuacion) {

		if (metodoValuacion != null) {

			if (metodoValuacion instanceof ComparacionMercadoPHVentaDTO) {

				ComparacionMercadoPHVentaDTO metodoPHVenta = (ComparacionMercadoPHVentaDTO) metodoValuacion;

				if (metodoPHVenta.getPiso() == null) {
					metodoPHVenta.setPiso(informeHipotecarioDTO.getNumeroPiso());
				}

				if (metodoPHVenta.getArea() == null) {
					if (avaluoHipotecarioDTO.getAreaConstruccionAvaluoDTO() != null) {
						metodoPHVenta.setArea(avaluoHipotecarioDTO.getAreaConstruccionAvaluoDTO().getAreaConstruida());
					}
				}

				if (metodoPHVenta.getGarajes() == null) {
					metodoPHVenta.setGarajes(informeHipotecarioDTO.getNumeroTotalDeGarajes());
				}

				if (metodoPHVenta.getEdad() == null) {
					((ComparacionMercadoPHVentaDTO) metodoValuacion).setEdad(informeHipotecarioDTO.getVetustez());
				}

				if (metodoPHVenta.getValorAdministracion() == null) {
					if (informeHipotecarioDTO.getValorAdministracion() != null) {
						((ComparacionMercadoPHDTO) metodoPHVenta)
								.setValorAdministracion(BigDecimal.valueOf(informeHipotecarioDTO.getValorAdministracion()));
					}
				}

				if (metodoPHVenta.getAreaLibre() == null) {
					for (AreaDTO areaDTO : this.listaAreas) {
						if (areaDTO.getDescripcionNumerica() != null
								&& areaDTO.getDescripcionNumerica().getKey() == AreaDTO.DescripcionAreaPH.AreaLibre.getKey()
								&& areaDTO.getArea() != null) {
							if (metodoPHVenta.getAreaLibre() == null) {
								metodoPHVenta.setAreaLibre(BigDecimal.valueOf(0));
							}
							metodoPHVenta.setAreaLibre(metodoPHVenta.getAreaLibre().add(areaDTO.getArea()));
						}
					}
				}

				if (metodoPHVenta.getPrecioUnitarioAdministracionM2() == null) {
					if (metodoPHVenta.getValorAdministracion() != null && metodoPHVenta.getArea() != null
							&& !metodoPHVenta.getArea().equals(BigDecimal.valueOf(0))) {
						metodoPHVenta.setPrecioUnitarioAdministracionM2(
								metodoPHVenta.getValorAdministracion().divide(metodoPHVenta.getArea(), 8, RoundingMode.HALF_EVEN));
					}
				}

				comparacionMercadoPHVentaBean.calcular();

			} else if (metodoValuacion instanceof ComparacionMercadoPHRentaDTO) {

				ComparacionMercadoPHRentaDTO metodoPHRenta = (ComparacionMercadoPHRentaDTO) metodoValuacion;

				if (metodoPHRenta.getPiso() == null) {
					metodoPHRenta.setPiso(informeHipotecarioDTO.getNumeroPiso());
				}

				if (metodoPHRenta.getArea() == null) {
					if (avaluoHipotecarioDTO.getAreaConstruccionAvaluoDTO() != null) {
						metodoPHRenta.setArea(avaluoHipotecarioDTO.getAreaConstruccionAvaluoDTO().getAreaConstruida());
					}
				}

				if (metodoPHRenta.getGarajes() == null) {
					metodoPHRenta.setGarajes(informeHipotecarioDTO.getNumeroTotalDeGarajes());
				}

				if (metodoPHRenta.getEdad() == null) {
					metodoPHRenta.setEdad(informeHipotecarioDTO.getVetustez());
				}

				if (metodoPHRenta.getValorAdministracion() == null) {
					if (informeHipotecarioDTO.getValorAdministracion() != null) {
						((ComparacionMercadoPHRentaDTO) metodoValuacion)
								.setValorAdministracion(new BigDecimal(informeHipotecarioDTO.getValorAdministracion()));
					}
				}

				if (metodoPHRenta.getAreaLibre() == null) {
					for (AreaDTO areaDTO : this.listaAreas) {
						if (areaDTO.getDescripcionNumerica() != null
								&& areaDTO.getDescripcionNumerica().getKey() == AreaDTO.DescripcionAreaPH.AreaLibre.getKey()
								&& areaDTO.getArea() != null) {
							if (metodoPHRenta.getAreaLibre() == null) {
								metodoPHRenta.setAreaLibre(BigDecimal.valueOf(0));
							}
							metodoPHRenta.setAreaLibre(metodoPHRenta.getAreaLibre().add(areaDTO.getArea()));
						}
					}
				}

				if (metodoPHRenta.getPrecioUnitarioAdministracionM2() == null) {
					if (metodoPHRenta.getValorAdministracion() != null && metodoPHRenta.getArea() != null
							&& !metodoPHRenta.getArea().equals(BigDecimal.valueOf(0))) {
						metodoPHRenta.setPrecioUnitarioAdministracionM2(
								metodoPHRenta.getValorAdministracion().divide(metodoPHRenta.getArea(), 8, RoundingMode.HALF_EVEN));
					}
				}

				comparacionMercadoPHRentaBean.calcular();

			} else if (metodoValuacion instanceof ComparacionMercadoLoteVentaDTO) {

				ComparacionMercadoLoteVentaDTO metodoLoteVenta = (ComparacionMercadoLoteVentaDTO) metodoValuacion;

				if (metodoLoteVenta.getAreaLote() == null) {
					if (avaluoHipotecarioDTO.getAreaLoteAvaluoDTO() != null
							&& avaluoHipotecarioDTO.getAreaLoteAvaluoDTO().getArea() != null) {
						metodoLoteVenta.setAreaLote(avaluoHipotecarioDTO.getAreaLoteAvaluoDTO().getArea());
					}
				}

				RequestContext.getCurrentInstance()
						.update("informeHipotecario:accordionPanel:accordionPanelMetodologias:ofertasLSCV");

			} else if (metodoValuacion instanceof ComparacionMercadoLoteConstruccionVentaDTO) {

				ComparacionMercadoLoteConstruccionVentaDTO metodoLoteConstruccionVenta = (ComparacionMercadoLoteConstruccionVentaDTO) metodoValuacion;

				if (metodoLoteConstruccionVenta.getEdad() == null) {
					((ComparacionMercadoLoteConstruccionVentaDTO) metodoValuacion).setEdad(informeHipotecarioDTO.getVetustez());
				}

				if (metodoLoteConstruccionVenta.getAreaLote() == null) {
					if (metodoLoteConstruccionVenta.getAreaLote() == null) {
						if (avaluoHipotecarioDTO.getAreaLoteAvaluoDTO() != null
								&& avaluoHipotecarioDTO.getAreaLoteAvaluoDTO().getArea() != null) {
							metodoLoteConstruccionVenta.setAreaLote(avaluoHipotecarioDTO.getAreaLoteAvaluoDTO().getArea());
						}
					}
				}

				if (metodoLoteConstruccionVenta.getAreaConsttruida() == null) {
					metodoLoteConstruccionVenta
							.setAreaConsttruida(avaluoHipotecarioDTO.getAreaConstruccionAvaluoDTO().getAreaConstruida());
				}

				RequestContext.getCurrentInstance()
						.update("informeHipotecario:accordionPanel:accordionPanelMetodologias:ofertasLCV");

			} else if (metodoValuacion instanceof ComparacionMercadoLoteConstruccionRentaDTO) {

				ComparacionMercadoLoteConstruccionRentaDTO metodoLoteConstruccionRenta = (ComparacionMercadoLoteConstruccionRentaDTO) metodoValuacion;

				if (metodoLoteConstruccionRenta.getEdad() == null) {
					((ComparacionMercadoLoteConstruccionRentaDTO) metodoValuacion).setEdad(informeHipotecarioDTO.getVetustez());
				}

				if (metodoLoteConstruccionRenta.getAreaLote() == null) {
					if (metodoLoteConstruccionRenta.getAreaLote() == null) {
						if (avaluoHipotecarioDTO.getAreaLoteAvaluoDTO() != null
								&& avaluoHipotecarioDTO.getAreaLoteAvaluoDTO().getArea() != null) {
							metodoLoteConstruccionRenta.setAreaLote(avaluoHipotecarioDTO.getAreaLoteAvaluoDTO().getArea());
						}
					}
				}

				if (metodoLoteConstruccionRenta.getAreaConsttruida() == null) {
					if (avaluoHipotecarioDTO.getAreaConstruccionAvaluoDTO() != null) {
						metodoLoteConstruccionRenta
								.setAreaConsttruida(avaluoHipotecarioDTO.getAreaConstruccionAvaluoDTO().getAreaConstruida());
					}
				}

				RequestContext.getCurrentInstance()
						.update("informeHipotecario:accordionPanel:accordionPanelMetodologias:ofertasLCR");

			}
		}
	}

	public void actualizarMatriculasAdicionales() {

		Set<String> tiposInmuebles = new TreeSet<>();
		for (MatriculaDTO matricula : avaluoHipotecarioDTO.getMatriculas()) {
			if (matricula.getTipoInmueble() != null) {
				tiposInmuebles.add(matricula.getTipoInmueble());
			}
		}

		for (String tipoInmueble : tiposInmuebles) {
			int i = 1;

			for (MatriculaDTO matricula : avaluoHipotecarioDTO.getMatriculas()) {
				if (matricula.getTipoInmueble() != null && matricula.getTipoInmueble().equals(tipoInmueble)) {
					matricula.setNumero(i++);
				}
			}
		}

		RequestContext.getCurrentInstance().update("informeHipotecario:accordionPanel:matriculasAdicionalesGrid");
	}

	/**
	 * Metodo que obtiene la lista de Sectores
	 * 
	 * @return Lista con los nombres de los sectores
	 */
	public List<ListaSector> getSectores() {
		return Arrays.asList(AvaluoDTO.ListaSector.values());
	}

	/**
	 * Metodo que obtiene la lista de los estados de las vias.
	 * 
	 * @return Lista con los nombres de los sectores
	 */
	public List<ListaEstado> getEstados() {
		return Arrays.asList(FormatoInformeHipotecarioDTO.ListaEstado.values());
	}

	/**
	 * Metodo que obtiene las opciones de seleccion de topografia de sector.
	 * 
	 * @return Lista con los nombres de las topografias de sector
	 */
	public List<ListaTopografiaSector> getTopografiasSector() {
		return Arrays.asList(FormatoInformeHipotecarioDTO.ListaTopografiaSector.values());
	}

	/**
	 * Metodo que obtiene las opciones de usos predominantes en inmueble.
	 * 
	 * @return Lista con los nombres de los usos predominantes.
	 */
	public List<ListaUsoPredominanteInmueble> getUsosPredominantesInmueble() {
		return Arrays.asList(FormatoInformeHipotecarioDTO.ListaUsoPredominanteInmueble.values());
	}

	/**
	 * Metodo que obtiene las opciones de clases de inmuebles.
	 * 
	 * @return Lista con los nombres de clases de inmuebles.
	 */
	public List<ListaClaseInmueble> getClasesInmuebles() {
		return Arrays.asList(FormatoInformeHipotecarioDTO.ListaClaseInmueble.values());
	}

	/**
	 * Metodo que obtiene las opciones de las listas de tipo de vivienda
	 * 
	 * @return Lista con los nombres de tipos de vivienda.
	 */
	public List<ListaTipoVivienda> getTiposVivienda() {
		return Arrays.asList(FormatoInformeHipotecarioDTO.ListaTipoVivienda.values());
	}

	/**
	 * Metodo que obtiene las opciones de las listas de ubicacion del inmueble
	 * 
	 * @return Lista con los nombres de ubicacion del inmueble
	 */
	public List<ListaUbicacionInmueble> getUbicacionesInmueble() {
		return Arrays.asList(FormatoInformeHipotecarioDTO.ListaUbicacionInmueble.values());
	}

	/**
	 * Metodo que obtiene las opciones de las listas de ubicacion del inmueble
	 * 
	 * @return Lista con los nombres de ubicacion del inmueble
	 */
	public List<ListaEstadoConstruccion> getEstadosConstruccion() {
		return Arrays.asList(FormatoInformeHipotecarioDTO.ListaEstadoConstruccion.values());
	}

	/**
	 * Metodo que obtiene las opciones de las listas de estado de conservacion
	 * 
	 * @return Lista con los nombres de estado de conservacion
	 */
	public List<ListaEstadoConservacion> getEstadosConservacion() {
		return Arrays.asList(FormatoInformeHipotecarioDTO.ListaEstadoConservacion.values());
	}

	/**
	 * Metodo que obtiene las opciones de las listas de tipos de licencia
	 * 
	 * @return Lista con los nombres de tipos de licencia
	 */
	public List<ListaTipoLicencia> getTiposLicencia() {
		return Arrays.asList(FormatoInformeHipotecarioDTO.ListaTipoLicencia.values());
	}

	/**
	 * Metodo que obtiene las opciones de las listas de tipos de licencia
	 * 
	 * @return Lista con los nombres de tipos de licencia
	 */
	public List<ListaPisoBodega> getPisosBodega() {
		return Arrays.asList(FormatoInformeHipotecarioDTO.ListaPisoBodega.values());
	}

	/**
	 * Metodo que obtiene las opciones de las listas de estructuras
	 * 
	 * @return Lista con los nombres de estructuras.
	 */
	public List<ListaEstructura> getEstructuras() {
		return Arrays.asList(FormatoInformeHipotecarioDTO.ListaEstructura.values());
	}

	/**
	 * Metodo que obtiene las opciones de las listas de reparados
	 * 
	 * @return Lista con los nombres de reparados
	 */
	public List<ListaReparado> getReparados() {
		return Arrays.asList(FormatoInformeHipotecarioDTO.ListaReparado.values());
	}

	/**
	 * Metodo que obtiene las opciones de las listas de cubiertas
	 * 
	 * @return Lista con los nombres de cubiertas
	 */
	public List<ListaCubierta> getCubiertas() {
		return Arrays.asList(FormatoInformeHipotecarioDTO.ListaCubierta.values());
	}

	/**
	 * Metodo que obtiene las opciones de las listas de cubiertas
	 * 
	 * @return Lista con los nombres de cubiertas
	 */
	public List<ListaFachada> getFachadas() {
		return Arrays.asList(FormatoInformeHipotecarioDTO.ListaFachada.values());
	}

	/**
	 * Metodo que obtiene las opciones de las listas de cubiertas
	 * 
	 * @return Lista con los nombres de cubiertas
	 */
	public List<ListaTipoFachada> getTiposFachadas() {
		return Arrays.asList(FormatoInformeHipotecarioDTO.ListaTipoFachada.values());
	}

	/**
	 * Metodo que obtiene las opciones de las lista de estructura reforzada
	 * 
	 * @return Lista con los nombres de estructuras reforzadas
	 */
	public List<ListaEstructuraReforzada> getEstructurasReforzadas() {
		return Arrays.asList(FormatoInformeHipotecarioDTO.ListaEstructuraReforzada.values());
	}

	/**
	 * Metodo que obtiene las opciones de las lista de Danos previos
	 * 
	 * @return Lista con los nombres de danos previos.
	 */
	public List<ListaDanoPrevio> getDanosPrevios() {
		return Arrays.asList(FormatoInformeHipotecarioDTO.ListaDanoPrevio.values());
	}

	/**
	 * Metodo que obtiene las opciones de las lista de opciones para material
	 * constructor
	 * 
	 * @return Lista con los nombres de material constructor.
	 */
	public List<ListaMaterialConstructor> getMaterialesConstructor() {
		return Arrays.asList(FormatoInformeHipotecarioDTO.ListaMaterialConstructor.values());
	}

	/**
	 * Metodo que obtiene las opciones de las lista de opciones para detalles
	 * material constructor
	 * 
	 * @return Lista con los nombres de ddetalles material constructor.
	 */
	public List<ListaDetalleMaterial> getDetallesMaterial() {
		return Arrays.asList(FormatoInformeHipotecarioDTO.ListaDetalleMaterial.values());
	}

	/**
	 * Metodo que obtiene las opciones de las lista de opciones para material
	 * constructor
	 * 
	 * @return Lista con los nombres de material constructor.
	 */
	public List<ListaIrregularidad> getIrregularidades() {
		return Arrays.asList(FormatoInformeHipotecarioDTO.ListaIrregularidad.values());
	}

	/**
	 * Metodo que obtiene las opciones de las lista de opciones para estados de
	 * acabados
	 * 
	 * @return Lista con los nombres de estados de acabados.
	 */
	public List<ListaEstadosAcabados> getEstadosAcabados() {
		return Arrays.asList(FormatoInformeHipotecarioDTO.ListaEstadosAcabados.values());
	}

	/**
	 * Metodo que obtiene las opciones de las lista de opciones para calidad de
	 * acabados
	 * 
	 * @return Lista con los nombres de calidad de acabados.
	 */
	public List<ListaCalidadAcabados> getCalidadAcabados() {
		return Arrays.asList(FormatoInformeHipotecarioDTO.ListaCalidadAcabados.values());
	}

	/**
	 * Metodo que obtiene las opciones de las lista de opciones para calidad de
	 * acabados de cocina
	 * 
	 * @return Lista con los nombres de calidad de acabados concina.
	 */
	public List<ListaCalidadAcabadosCocina> getCalidadAcabadosCocina() {
		return Arrays.asList(FormatoInformeHipotecarioDTO.ListaCalidadAcabadosCocina.values());
	}

	/**
	 * Metodo que obtiene las opciones de las lista de opciones para tipos de garaje
	 * 
	 * @return Lista con los nombres de tipo garaje
	 */
	public List<ListaTipoGaraje> getTiposGarajes() {
		return Arrays.asList(GarajeDTO.ListaTipoGaraje.values());
	}

	/**
	 * Metodo que obtiene las opciones de las lista de opciones para uso Actual
	 * 
	 * @return Lista con los nombres de uso actual.
	 */
	public List<ListaUsoActual> getUsoActual() {
		return Arrays.asList(FormatoInformeHipotecarioDTO.ListaUsoActual.values());
	}

	/**
	 * Metodo que obtiene las opciones de las lista de opciones para calificacion de
	 * garantia
	 * 
	 * @return Lista con los nombres de calificacion garantia.
	 */
	public List<ListaCalificacionGarantia> getCalificacionesGarantia() {
		return Arrays.asList(AvaluoDTO.ListaCalificacionGarantia.values());
	}

	public void cambiarImpactoAmbientalAire() {
		if (this.informeHipotecarioDTO.getImpactoAmbientalAire() != null
				&& !this.informeHipotecarioDTO.getImpactoAmbientalAire()) {
			this.informeHipotecarioDTO.setExplicacionImpactoAmbientalAire("");
		}
	}

	public void cambiarImpactoAmbientalBasura() {
		if (this.informeHipotecarioDTO.getImpactoAmbientalBasura() != null
				&& !this.informeHipotecarioDTO.getImpactoAmbientalBasura()) {
			this.informeHipotecarioDTO.setExplicacionImpactoAmbientalBasura("");
		}
	}

	public void cambiarImpactoAmbientalInseguridad() {
		if (this.informeHipotecarioDTO.getImpactoAmbientalInseguridad() != null
				&& !this.informeHipotecarioDTO.getImpactoAmbientalInseguridad()) {
			this.informeHipotecarioDTO.setExplicacionImpactoAmbientalInseguridad("");
		}
	}

	public void cambiarImpactoAmbientalRuido() {
		if (this.informeHipotecarioDTO.getImpactoAmbientalRuido() != null
				&& !this.informeHipotecarioDTO.getImpactoAmbientalRuido()) {
			this.informeHipotecarioDTO.setExplicacionImpactoAmbientalRuido("");
		}
	}

	public void cambiarImpactoAmbientalAguasServidas() {
		if (this.informeHipotecarioDTO.getImpactoAmbientalAguasServidas() != null
				&& !this.informeHipotecarioDTO.getImpactoAmbientalAguasServidas()) {
			this.informeHipotecarioDTO.setExplicacionImpactoAmbientalAguasServidas("");
		}
	}

	public void cambiarImpactoAmbientalOtro() {
		if (this.informeHipotecarioDTO.getImpactoAmbientalOtro() != null
				&& !this.informeHipotecarioDTO.getImpactoAmbientalOtro()) {
			this.informeHipotecarioDTO.setExplicacionOtro("");
		}
	}

	public void actualizarTiempoComercializacion() {
		final Integer estrato = informeHipotecarioDTO.getEstrato();
		final DivipolaDTO divipola = avaluoHipotecarioDTO.getDivipolaInforme();
		final TipoInmuebleDTO tipoInmueble = avaluoHipotecarioDTO.getTipoDeInmueble();
		if (divipola != null && tipoInmueble != null && (estrato != null && estrato > 0)) {

			if (tipoInmueble.getId() <= 15 & estrato <= 6) {
				try {
					informeHipotecarioDTO.setTiempoEsperadoDeComercializacion(
							avaluoController.encontrarTiempoComercializacion(divipola.getId(), tipoInmueble.getId(), estrato));
				} catch (TiempoComercializacionNotFoundException e) {
					log.error(e.getMessage());
					FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage(
							"Error al encontrar tiempo de comercialización",
							"Contacte con el administrador del sistema para corregir los valores de tiempo de comercialización."));
					RequestContext.getCurrentInstance().update("informeHipotecario:growl");
					informeHipotecarioDTO.setTiempoEsperadoDeComercializacion(null);
				}
			} else {
				informeHipotecarioDTO.setTiempoEsperadoDeComercializacion(null);
			}
		}
	}
	// ---------------------- GETTERS AND
	// SETTERS-----------------------------------

	public AvaluoDTO getAvaluoHipotecarioDTO() {
		return avaluoHipotecarioDTO;
	}

	public void setAvaluoHipotecarioDTO(AvaluoDTO avaluoHipotecarioDTO) {
		this.avaluoHipotecarioDTO = avaluoHipotecarioDTO;
	}

	public FormatoInformeHipotecarioDTO getInformeHipotecarioDTO() {
		return informeHipotecarioDTO;
	}

	public void setInformeHipotecarioDTO(FormatoInformeHipotecarioDTO informeHipotecarioDTO) {
		this.informeHipotecarioDTO = informeHipotecarioDTO;
	}

	public String getDepartamentoInmueble() {
		return departamentoInmueble;
	}

	public void setDepartamentoInmueble(String departamentoInmueble) {
		this.departamentoInmueble = departamentoInmueble;
	}

	public String getDepartamentoCliente() {
		return departamentoCliente;
	}

	public void setDepartamentoCliente(String departamentoCliente) {
		this.departamentoCliente = departamentoCliente;
	}

	public SortedMap<String, String> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(SortedMap<String, String> departamentos) {
		this.departamentos = departamentos;
	}

	public List<DivipolaDTO> getCiudadesSolicitante() {
		return ciudadesSolicitante;
	}

	public void setCiudadesSolicitante(List<DivipolaDTO> ciudadesSolicitante) {
		this.ciudadesSolicitante = ciudadesSolicitante;
	}

	public List<DivipolaDTO> getCiudadesNotaria() {
		return ciudadesNotaria;
	}

	public void setCiudadesNotaria(List<DivipolaDTO> ciudadesNotaria) {
		this.ciudadesNotaria = ciudadesNotaria;
	}

	public List<DivipolaDTO> getCiudadesInmueble() {
		return ciudadesInmueble;
	}

	public void setCiudadesInmueble(List<DivipolaDTO> ciudadesInmueble) {
		this.ciudadesInmueble = ciudadesInmueble;
	}

	public SortedMap<String, String> getPaises() {
		return paises;
	}

	public void setPaises(SortedMap<String, String> paises) {
		this.paises = paises;
	}

	public String getCentroMapa() {
		return this.centroMapa;
	}

	public void setCentroMapa(String centroMapa) {
		this.centroMapa = centroMapa;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public List<EntidadDTO> getEntidades() {
		return entidades;
	}

	public void setEntidades(List<EntidadDTO> entidades) {
		this.entidades = entidades;
	}

	public List<String> getSucursales() {
		return sucursales;
	}

	public void setSucursales(List<String> sucursales) {
		this.sucursales = sucursales;
	}

	public List<SegmentoDTO> getSegmentos() {
		return segmentos;
	}

	public void setSegmentos(List<SegmentoDTO> segmentos) {
		this.segmentos = segmentos;
	}

	public String getTipoViaSolicitante() {
		return this.tipoViaSolicitante;
	}

	public String getNumeroViaSolicitante() {
		return this.numeroViaSolicitante;
	}

	public List<String> getComplementoViaSolicitante() {
		return this.complementoViaSolicitante;
	}

	public String getNumeroViaGeneradoraSolicitante() {
		return this.numeroViaGeneradoraSolicitante;
	}

	public List<String> getComplementoViaGeneradoraSolicitante() {
		return this.complementoViaGeneradoraSolicitante;
	}

	public String getPlacaSolicitante() {
		return this.placaSolicitante;
	}

	public String getComplementoPlacaSolicitante() {
		return this.complementoPlacaSolicitante;
	}

	public String getAdicionalDireccionSolicitante() {
		return this.adicionalDireccionSolicitante;
	}

	public void setTipoViaSolicitante(String tipoViaSolicitante) {
		this.tipoViaSolicitante = tipoViaSolicitante;
	}

	public void setNumeroViaSolicitante(String numeroViaSolicitante) {
		this.numeroViaSolicitante = numeroViaSolicitante;
	}

	public void setComplementoViaSolicitante(List<String> complementoViaSolicitante) {
		this.complementoViaSolicitante = complementoViaSolicitante;
	}

	public void setNumeroViaGeneradoraSolicitante(String numeroViaGeneradoraSolicitante) {
		this.numeroViaGeneradoraSolicitante = numeroViaGeneradoraSolicitante;
	}

	public void setComplementoViaGeneradoraSolicitante(List<String> complementoViaGeneradoraSolicitante) {
		this.complementoViaGeneradoraSolicitante = complementoViaGeneradoraSolicitante;
	}

	public void setPlacaSolicitante(String placaSolicitante) {
		this.placaSolicitante = placaSolicitante;
	}

	public void setComplementoPlacaSolicitante(String complementoPlacaSolicitante) {
		this.complementoPlacaSolicitante = complementoPlacaSolicitante;
	}

	public void setAdicionalDireccionSolicitante(String adicionalDireccionSolicitante) {
		this.adicionalDireccionSolicitante = adicionalDireccionSolicitante;
	}

	public List<String> getComplementoVia() {
		return complementoVia;
	}

	public void setComplementoVia(List<String> complementoVia) {
		this.complementoVia = complementoVia;
	}

	public List<String> getComplementoViaGeneradora() {
		return complementoViaGeneradora;
	}

	public void setComplementoViaGeneradora(List<String> complementoViaGeneradora) {
		this.complementoViaGeneradora = complementoViaGeneradora;
	}

	public boolean isNuevaArea() {
		return nuevaArea;
	}

	public void setNuevaArea(boolean nuevaArea) {
		this.nuevaArea = nuevaArea;
	}

	public AreaDTO getAreaDTO() {
		return areaDTO;
	}

	public void setAreaDTO(AreaDTO areaDTO) {
		this.areaDTO = areaDTO;
	}

	public List<AreaDTO> getListaAreas() {
		return listaAreas;
	}

	public void setListaAreas(List<AreaDTO> listaAreas) {
		this.listaAreas = listaAreas;
	}

	public boolean isEntidadBancolombia() {
		return entidadBancolombia;
	}

	public void setEntidadBancolombia(boolean entidadBancolombia) {
		this.entidadBancolombia = entidadBancolombia;
	}

	public boolean isAvaluoEnRevision() {
		return avaluoEnRevision;
	}

	public void setAvaluoEnRevision(boolean avaluoEnRevision) {
		this.avaluoEnRevision = avaluoEnRevision;
	}

	public boolean isAvaluoEnCorrecion() {
		return avaluoEnCorrecion;
	}

	public void setAvaluoEnCorrecion(boolean avaluoEnCorrecion) {
		this.avaluoEnCorrecion = avaluoEnCorrecion;
	}

	public String getCorreciones() {
		return correciones;
	}

	public void setCorreciones(String correciones) {
		this.correciones = correciones;
	}

	public Date getFechaElaboracionInforme() {
		return fechaElaboracionInforme;
	}

	public void setFechaElaboracionInforme(Date fechaElaboracionInforme) {
		this.fechaElaboracionInforme = fechaElaboracionInforme;
	}

	public MapModel getMapModel() {
		return mapModel;
	}

	public void setMapModel(MapModel mapModel) {
		this.mapModel = mapModel;
	}

	public List<AvaluoDTO> getAvaluosAnteriores() {
		return this.avaluosAnteriores;
	}

	public void setAvaluosAnteriores(List<AvaluoDTO> avaluosAnteriores) {
		this.avaluosAnteriores = avaluosAnteriores;
	}

	public BigDecimal getSubTotalHonorarios() {
		return subTotalHonorarios;
	}

	public void setSubTotalHonorarios(BigDecimal subTotalHonorarios) {
		this.subTotalHonorarios = subTotalHonorarios;
	}

	public BigDecimal getIvaHonorarios() {
		return ivaHonorarios;
	}

	public void setIvaHonorarios(BigDecimal ivaHonorarios) {
		this.ivaHonorarios = ivaHonorarios;
	}

	public BigDecimal getTotalHonorarios() {
		return totalHonorarios;
	}

	public void setTotalHonorarios(BigDecimal totalHonorarios) {
		this.totalHonorarios = totalHonorarios;
	}

	public UvrDTO getUvr() {
		return uvr;
	}

	public void setUvr(UvrDTO uvr) {
		this.uvr = uvr;
	}

	public List<TipoInmuebleDTO> getTiposInmueble() {
		return tiposInmueble;
	}

	public List<TipoAvaluoDTO> getTiposAvaluo() {
		return tiposAvaluo;
	}

	public void setTiposAvaluo(List<TipoAvaluoDTO> tiposAvaluo) {
		this.tiposAvaluo = tiposAvaluo;
	}

	public boolean isModificarInforme() {
		return modificarInforme;
	}

	public void setModificarInforme(boolean modificarInforme) {
		this.modificarInforme = modificarInforme;
	}

	public boolean isEnviarInforme() {
		return enviarInforme;
	}

	public void setEnviarInforme(boolean enviarInforme) {
		this.enviarInforme = enviarInforme;
	}

	public AreaDTO.UnidadDeMedida[] getUnidadesDeMedida() {
		return AreaDTO.UnidadDeMedida.values();
	}

	public ListaDesplegable[] getDescripcionAreas() {
		return descripcionAreas;
	}

	public Integer[] getNumerosGarajes() {
		return numerosGarajes;
	}

	public List<AreaDTO> getAreasPisos() {
		return areasPisos;
	}

	public void setAreasPisos(List<AreaDTO> areasPisos) {
		this.areasPisos = areasPisos;
	}

	public List<AreaDTO> getAreasConstruidasNoPH() {
		return areasConstruidasNoPH;
	}

	public void setAreasConstruidasNoPH(List<AreaDTO> areasConstruidasNoPH) {
		this.areasConstruidasNoPH = areasConstruidasNoPH;
	}

	public List<AreaDTO> getAreasPrivadas() {
		return areasPrivadas;
	}

	public void setAreasPrivadas(List<AreaDTO> areasPrivadas) {
		this.areasPrivadas = areasPrivadas;
	}

	public ArrayList<BigDecimal> getCalificaciones() {
		return calificaciones;
	}

	public void setCalificaciones(ArrayList<BigDecimal> calificaciones) {
		this.calificaciones = calificaciones;
	}

	public boolean isMostrarMetodoReposicion() {
		return mostrarMetodoReposicion;
	}

	public void setMostrarMetodoReposicion(boolean mostrarMetodoReposicion) {
		this.mostrarMetodoReposicion = mostrarMetodoReposicion;
	}

	public boolean isMostrarMetodoComparacionMercadoPHVenta() {
		return mostrarMetodoComparacionMercadoPHVenta;
	}

	public void setMostrarMetodoComparacionMercadoPHVenta(boolean mostrarMetodoComparacionMercadoPHVenta) {
		this.mostrarMetodoComparacionMercadoPHVenta = mostrarMetodoComparacionMercadoPHVenta;
	}

	public boolean isNuevoMetodoValuacion() {
		return nuevoMetodoValuacion;
	}

	public void setNuevoMetodoValuacion(boolean nuevoMetodoValuacion) {
		this.nuevoMetodoValuacion = nuevoMetodoValuacion;
	}

	public boolean isCobradoPorBancol() {
		return cobradoPorBancol;
	}

	public void setCobradoPorBancol(boolean cobradoPorBancol) {
		this.cobradoPorBancol = cobradoPorBancol;
	}

	public boolean isAvaluoRemate() {
		return avaluoRemate;
	}

	public void setAvaluoRemate(boolean avaluoRemate) {
		this.avaluoRemate = avaluoRemate;
	}

	public MetodoValuacionDTO getMetodoValuacion() {
		return metodoValuacion;
	}

	public void setMetodoValuacion(MetodoValuacionDTO metodoValuacion) {
		this.metodoValuacion = metodoValuacion;
	}

	public List<MetodoValuacionDTO.MetodoValuacionEnum> getMetodoValuacionDisponibles() {
		return getMetodosValuacionDisponibles();
	}

	public MetodoValuacionDTO.MetodoValuacionEnum[] getMetodosValuacion() {
		return MetodoValuacionDTO.MetodoValuacionEnum.values();
	}

	public boolean isEsMixtoUsoPredominante() {
		return esMixtoUsoPredominante;
	}

	public void setEsMixtoUsoPredominante(boolean esMixtoUsoPredominante) {
		this.esMixtoUsoPredominante = esMixtoUsoPredominante;
	}

	public MetodoValuacionDTO getComparacionMercado() {
		return comparacionMercado;
	}

	public void setComparacionMercado(MetodoValuacionDTO comparacionMercado) {
		this.comparacionMercado = comparacionMercado;
	}

	public String getDireccionCompleta() {
		onDireccionUsuarioChanged();
		return direccionCompleta;
	}

	public void setDireccionCompleta(String direccionCompleta) {
		this.direccionCompleta = direccionCompleta;
	}

	public List<MetodoValuacionDTO.MetodoValuacionEnum> getMetodosValuacionDisponibles() {
		return metodosValuacionDisponibles;
	}

	public void setMetodosValuacionDisponibles(List<MetodoValuacionDTO.MetodoValuacionEnum> metodosValuacionDisponibles) {
		this.metodosValuacionDisponibles = metodosValuacionDisponibles;
	}

	public BigDecimal getSumatoria() {
		return sumatoria;
	}

	public void setSumatoria(BigDecimal sumatoria) {
		this.sumatoria = sumatoria;
	}

	public Boolean getErrorDireccionInmueble() {
		return errorDireccionInmueble;
	}

	public void setErrorDireccionInmueble(Boolean errorDireccionInmueble) {
		this.errorDireccionInmueble = errorDireccionInmueble;
	}

	public boolean isCoordenadasValidas() {
		return coordenadasValidas;
	}

	public void setCoordenadasValidas(boolean coordenadasValidas) {
		this.coordenadasValidas = coordenadasValidas;
	}

	public boolean isMostrarCorrecciones() {
		return mostrarCorrecciones;
	}

	public void setMostrarCorrecciones(boolean mostrarCorrecciones) {
		this.mostrarCorrecciones = mostrarCorrecciones;
	}

	/**
	 * @return the tipoGaraje
	 */
	public ListaTipoGaraje getTipoGaraje() {
		return tipoGaraje;
	}

	/**
	 * @param tipoGaraje the tipoGaraje to set
	 */
	public void setTipoGaraje(ListaTipoGaraje tipoGaraje) {
		this.tipoGaraje = tipoGaraje;
	}

	public Integer[] getNumerosMatriculas() {
		return numerosMatriculas;
	}

	public boolean isMostrarMetodoComparacionMercadoPHRenta() {
		return mostrarMetodoComparacionMercadoPHRenta;
	}

	public void setMostrarMetodoComparacionMercadoPHRenta(boolean mostrarMetodoComparacionMercadoPHRenta) {
		this.mostrarMetodoComparacionMercadoPHRenta = mostrarMetodoComparacionMercadoPHRenta;
	}

	public boolean isMostrarMetodoComparacionMercadoLoteVentaSinContruccion() {
		return mostrarMetodoComparacionMercadoLoteVentaSinContruccion;
	}

	public void setMostrarMetodoComparacionMercadoLoteVentaSinContruccion(
			boolean mostrarMetodoComparacionMercadoLoteVentaSinContruccion) {
		this.mostrarMetodoComparacionMercadoLoteVentaSinContruccion = mostrarMetodoComparacionMercadoLoteVentaSinContruccion;
	}

	public boolean isMostrarMetodoComparacionMercadoLoteVentaConContruccion() {
		return mostrarMetodoComparacionMercadoLoteVentaConContruccion;
	}

	public void setMostrarMetodoComparacionMercadoLoteVentaConContruccion(
			boolean mostrarMetodoComparacionMercadoLoteVentaConContruccion) {
		this.mostrarMetodoComparacionMercadoLoteVentaConContruccion = mostrarMetodoComparacionMercadoLoteVentaConContruccion;
	}

	public boolean isMostrarMetodoComparacionMercadoLoteRentaConContruccion() {
		return mostrarMetodoComparacionMercadoLoteRentaConContruccion;
	}

	public void setMostrarMetodoComparacionMercadoLoteRentaConContruccion(
			boolean mostrarMetodoComparacionMercadoLoteRentaConContruccion) {
		this.mostrarMetodoComparacionMercadoLoteRentaConContruccion = mostrarMetodoComparacionMercadoLoteRentaConContruccion;
	}

	public boolean isAccionAgregarMetodologia() {
		return accionAgregarMetodologia;
	}

	public void setAccionAgregarMetodologia(boolean accionAgregarMetodologia) {
		this.accionAgregarMetodologia = accionAgregarMetodologia;
	}

	public String getMensajeValorTotalFueraRango() {
		if (avaluoHipotecarioDTO.getTipoSubsidio() != null) {
			String maximo = numberFormat.format(avaluoHipotecarioDTO.getTipoSubsidio().getMaximo().doubleValue());
			String maximoSalarios = String.valueOf(avaluoHipotecarioDTO.getTipoSubsidio().getMaximoSalarios());
			String minimo = numberFormat.format(avaluoHipotecarioDTO.getTipoSubsidio().getMinimo().doubleValue());
			String minimoSalarios = String.valueOf(avaluoHipotecarioDTO.getTipoSubsidio().getMinimoSalarios());
			String nombre = avaluoHipotecarioDTO.getTipoSubsidio().getNombre();

			String message = "El valor total comercial no esta entre el valor " + minimo + " (" + minimoSalarios
					+ " SMLMV) y " + maximo + " (" + maximoSalarios + " SMLMV) de subsidio " + nombre
					+ " por favor modifique el tipo de subsidio o ajuste el valor total comercial.";

			return message;
		} else {
			String message = "El valor total comercial supera el valor de subsidio por favor modifique el tipo de subsidio o ajuste el valor total comercial.";
			return message;
		}
	}

	public boolean isCoordenadasAjustadas() {
		return coordenadasAjustadas;
	}

	public void setCoordenadasAjustadas(boolean coordenadasAjustadas) {
		this.coordenadasAjustadas = coordenadasAjustadas;
	}

	public boolean isMostrarAreasLotePH() {
		return mostrarAreasLotePH;
	}

	public void setMostrarAreasLotePH(boolean mostrarAreasLotePH) {
		this.mostrarAreasLotePH = mostrarAreasLotePH;
	}

	public void calcularRelacionFrenteFondo() {

		if (avaluoHipotecarioDTO != null && avaluoHipotecarioDTO.getAreaLoteAvaluoDTO() != null
				&& avaluoHipotecarioDTO.getAreaLoteAvaluoDTO().getFondo() != null
				&& avaluoHipotecarioDTO.getAreaLoteAvaluoDTO().getFrente() != null
				&& avaluoHipotecarioDTO.getAreaLoteAvaluoDTO().getFondo().compareTo(BigDecimal.ZERO) != 0) {
			avaluoHipotecarioDTO.getAreaLoteAvaluoDTO().setRelacion(avaluoHipotecarioDTO.getAreaLoteAvaluoDTO().getFrente()
					.divide(avaluoHipotecarioDTO.getAreaLoteAvaluoDTO().getFondo(), 3, RoundingMode.HALF_UP));
		}
	}

	public void validarCamposNormasSuelo(boolean origen) { // false: carga
		// inicial true:
		// evento de cada
		// componente

		validarCampoAlturaPermitida(origen);
		validarAislamientoPosterior(origen);
		validarAislamientoLateral(origen);
		validarAnteJardin(origen);
		validarIndiceOcupacion(origen);
		validarIndiceConstruccion(origen);
	}

	public void validarCampoAlturaPermitida(boolean origen) {

		if ((informeHipotecarioDTO.getAlturaPermitida() != null
				&& informeHipotecarioDTO.getAlturaPermitida().equals("Aplica"))
				|| (!origen && informeHipotecarioDTO.getAlturaPermitida() == null)) {
			mostrarObservacionAlturaPermitida = true;
		} else {
			mostrarObservacionAlturaPermitida = false;
		}
	}

	public void validarAislamientoPosterior(boolean origen) {

		if ((informeHipotecarioDTO.getAislamientoPosterior() != null
				&& informeHipotecarioDTO.getAislamientoPosterior().equals("Aplica"))
				|| (!origen && informeHipotecarioDTO.getAislamientoPosterior() == null)) {
			mostrarObservacionAislamientoPosterior = true;
		} else {
			mostrarObservacionAislamientoPosterior = false;
		}
	}

	public void validarAislamientoLateral(boolean origen) {

		if ((informeHipotecarioDTO.getAislamientoLateral() != null
				&& informeHipotecarioDTO.getAislamientoLateral().equals("Aplica"))
				|| (!origen && informeHipotecarioDTO.getAislamientoLateral() == null)) {
			mostrarObservacionAislamientoLateral = true;
		} else {
			mostrarObservacionAislamientoLateral = false;
		}
	}

	public void validarAnteJardin(boolean origen) {

		if ((informeHipotecarioDTO.getAnteJardin() != null && informeHipotecarioDTO.getAnteJardin().equals("Aplica"))
				|| (!origen && informeHipotecarioDTO.getAnteJardin() == null)) {
			mostrarObservacionAnteJardin = true;
		} else {
			mostrarObservacionAnteJardin = false;
		}
	}

	public void validarIndiceOcupacion(boolean origen) {

		if ((informeHipotecarioDTO.getIndiceOcupacion() != null
				&& informeHipotecarioDTO.getIndiceOcupacion().equals("Aplica"))
				|| (!origen && informeHipotecarioDTO.getIndiceOcupacion() == null)) {
			mostrarObservacionIndiceOcupacion = true;
		} else {
			mostrarObservacionIndiceOcupacion = false;
		}
	}

	public void validarIndiceConstruccion(boolean origen) {

		if ((informeHipotecarioDTO.getIndiceConstruccion() != null
				&& informeHipotecarioDTO.getIndiceConstruccion().equals("Aplica"))
				|| (!origen && informeHipotecarioDTO.getIndiceConstruccion() == null)) {
			mostrarObservacionIndiceConstruccion = true;
		} else {
			mostrarObservacionIndiceConstruccion = false;
		}
	}

	public boolean isMostrarMensajeValidacionSujetoPHVenta() {
		return mostrarMensajeValidacionSujetoPHVenta;
	}

	public void setMostrarMensajeValidacionSujetoPHVenta(boolean mostrarMensajeValidacionSujetoPHVenta) {
		this.mostrarMensajeValidacionSujetoPHVenta = mostrarMensajeValidacionSujetoPHVenta;
	}

	public boolean isMostrarMensajeValidacionSujetoPHRenta() {
		return mostrarMensajeValidacionSujetoPHRenta;
	}

	public void setMostrarMensajeValidacionSujetoPHRenta(boolean mostrarMensajeValidacionSujetoPHRenta) {
		this.mostrarMensajeValidacionSujetoPHRenta = mostrarMensajeValidacionSujetoPHRenta;
	}

	public boolean isMostrarMensajeValidacionSujetoLoteVenta() {
		return mostrarMensajeValidacionSujetoLoteVenta;
	}

	public void setMostrarMensajeValidacionSujetoLoteVenta(boolean mostrarMensajeValidacionSujetoLoteVenta) {
		this.mostrarMensajeValidacionSujetoLoteVenta = mostrarMensajeValidacionSujetoLoteVenta;
	}

	public boolean isMostrarMensajeValidacionSujetoLoteconstruccionVenta() {
		return mostrarMensajeValidacionSujetoLoteconstruccionVenta;
	}

	public void setMostrarMensajeValidacionSujetoLoteconstruccionVenta(
			boolean mostrarMensajeValidacionSujetoLoteconstruccionVenta) {
		this.mostrarMensajeValidacionSujetoLoteconstruccionVenta = mostrarMensajeValidacionSujetoLoteconstruccionVenta;
	}

	public boolean isMostrarMensajeValidacionSujetoLoteconstruccionRenta() {
		return mostrarMensajeValidacionSujetoLoteconstruccionRenta;
	}

	public void setMostrarMensajeValidacionSujetoLoteconstruccionRenta(
			boolean mostrarMensajeValidacionSujetoLoteconstruccionRenta) {
		this.mostrarMensajeValidacionSujetoLoteconstruccionRenta = mostrarMensajeValidacionSujetoLoteconstruccionRenta;
	}

	public MetodoValuacionDTO getMetodoValuacionEliminar() {
		return metodoValuacionEliminar;
	}

	public void setMetodoValuacionEliminar(MetodoValuacionDTO metodoValuacionEliminar) {
		this.metodoValuacionEliminar = metodoValuacionEliminar;
	}

	public boolean isMostrarObservacionAlturaPermitida() {
		return mostrarObservacionAlturaPermitida;
	}

	public void setMostrarObservacionAlturaPermitida(boolean mostrarObservacionAlturaPermitida) {
		this.mostrarObservacionAlturaPermitida = mostrarObservacionAlturaPermitida;
	}

	public boolean isMostrarObservacionAislamientoPosterior() {
		return mostrarObservacionAislamientoPosterior;
	}

	public void setMostrarObservacionAislamientoPosterior(boolean mostrarObservacionAislamientoPosterior) {
		this.mostrarObservacionAislamientoPosterior = mostrarObservacionAislamientoPosterior;
	}

	public boolean isMostrarObservacionAislamientoLateral() {
		return mostrarObservacionAislamientoLateral;
	}

	public void setMostrarObservacionAislamientoLateral(boolean mostrarObservacionAislamientoLateral) {
		this.mostrarObservacionAislamientoLateral = mostrarObservacionAislamientoLateral;
	}

	public boolean isMostrarObservacionAnteJardin() {
		return mostrarObservacionAnteJardin;
	}

	public void setMostrarObservacionAnteJardin(boolean mostrarObservacionAnteJardin) {
		this.mostrarObservacionAnteJardin = mostrarObservacionAnteJardin;
	}

	public boolean isMostrarObservacionIndiceOcupacion() {
		return mostrarObservacionIndiceOcupacion;
	}

	public void setMostrarObservacionIndiceOcupacion(boolean mostrarObservacionIndiceOcupacion) {
		this.mostrarObservacionIndiceOcupacion = mostrarObservacionIndiceOcupacion;
	}

	public boolean isMostrarObservacionIndiceConstruccion() {
		return mostrarObservacionIndiceConstruccion;
	}

	public void setMostrarObservacionIndiceConstruccion(boolean mostrarObservacionIndiceConstruccion) {
		this.mostrarObservacionIndiceConstruccion = mostrarObservacionIndiceConstruccion;
	}

	public void subirFotos() {
		log.debug("subirFotos");
		String uri = FacesContext.getCurrentInstance().getExternalContext()
				.encodeActionURL(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()
						+ "/pages/informes/subirFotos.xhtml?source=sisgem");
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(uri);
		} catch (IOException e) {
			// TODO Reireccionar a página no encontrada
			e.printStackTrace();
		}
	}

	public void subirAnexos() {
		log.debug("subirAnexos");
		String uri = FacesContext.getCurrentInstance().getExternalContext()
				.encodeActionURL("/bancol_avaluos/pages/informes/anexos.xhtml?source=sisgem");
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(uri);
		} catch (IOException e) {
			// TODO Reireccionar a página no encontrada
			e.printStackTrace();
		}
	}

	public void subirCroquis() {
		log.debug("subirCroquis");
		String uri = FacesContext.getCurrentInstance().getExternalContext()
				.encodeActionURL(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()
						+ "/pages/informes/croquis.xhtml?source=sisgem");
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(uri);
		} catch (IOException e) {
			// TODO Reireccionar a página no encontrada
			e.printStackTrace();
		}
	}

	public void eliminarMatricula(MatriculaDTO matricula) {

		// elimina matricula
		if (matricula.getId() != null) {
			matriculaService.eliminarMatricula(matricula);
		}

		// limpia campos en tabla
		this.avaluoHipotecarioDTO.getMatriculas().remove(matricula);
		this.avaluoHipotecarioDTO.getMatriculas().add(new MatriculaDTO(this.avaluoHipotecarioDTO.getId()));

		RequestContext.getCurrentInstance().update("informeHipotecario:accordionPanel:matriculasAdicionalesGrid");

	}

	public void recalcularMetodologias() {

		comparacionMercadoPHVentaBean.calcular();
		comparacionMercadoLoteConstruccionRentaBean.calcular();
		comparacionMercadoLoteConstruccionVentaBean.calcular();
		comparacionMercadoLoteVentaBean.calcular();
		comparacionMercadoPHRentaBean.calcular();
		comparacionMercadoPHVentaBean.calcular();

	}

	public Motivo getMotivo() {
		return motivo;
	}

	public void setMotivo(Motivo motivo) {
		this.motivo = motivo;
	}

}
