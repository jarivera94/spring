package com.helio4.bancol.avaluos.controlador;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.helio4.bancol.avaluos.dominio.AvaluoController;
import com.helio4.bancol.avaluos.dominio.ListadoAvaluosController;
import com.helio4.bancol.avaluos.dominio.ListasController;
import com.helio4.bancol.avaluos.dominio.ListasGeograficasController;
import com.helio4.bancol.avaluos.dominio.gmap.GLatLng;
import com.helio4.bancol.avaluos.dto.AreaDTO;
import com.helio4.bancol.avaluos.dto.AreaDTO.DescripcionAreaPH;
import com.helio4.bancol.avaluos.dto.AvaluoConstructorDTO;
import com.helio4.bancol.avaluos.dto.AvaluoDTO;
import com.helio4.bancol.avaluos.dto.CaracteristicasFisicasDTO;
import com.helio4.bancol.avaluos.dto.CronogramaObraDTO;
import com.helio4.bancol.avaluos.dto.DivipolaDTO;
import com.helio4.bancol.avaluos.dto.EntidadDTO;
import com.helio4.bancol.avaluos.dto.InmuebleDTO;
import com.helio4.bancol.avaluos.dto.PropietarioDTO;
import com.helio4.bancol.avaluos.dto.SegmentoDTO;
import com.helio4.bancol.avaluos.dto.TipoAvaluoDTO;
import com.helio4.bancol.avaluos.servicio.datos.AreaService;
import com.helio4.bancol.avaluos.servicio.datos.AvaluoConstructorService;
import com.helio4.bancol.avaluos.servicio.datos.AvaluoService;
import com.helio4.bancol.avaluos.servicio.datos.CronogramaObraService;
import com.helio4.bancol.avaluos.servicio.datos.InmuebleService;
import com.helio4.bancol.avaluos.servicio.datos.PropietarioService;
import com.helio4.bancol.avaluos.servicio.datos.TipoAvaluoService;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;

@Controller
@Scope("view")
@Qualifier("avaluoConstructorBean")
public class AvaluoConstructorBean implements Serializable {

	private static Logger log = LoggerFactory.getLogger(AvaluoConstructorBean.class);

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1580792994349122949L;

	/**
	 * Instanciar Servicies Interface ListadoCasosController
	 */
	@Autowired
	private AvaluoController listadoAvaluoController;

	/**
	 * Instanciar Servicies Interface ListadoCasosController
	 */
	@Autowired
	private ListadoAvaluosController listCasosController;

	/**
	 * Instanciar Servicies Interface cronogramaObraService.
	 */
	@Autowired
	@Qualifier("repositoryAvaluoConstructorService")
	private AvaluoConstructorService avaluoConstructorService;

	/**
	 * Instanciar Servicies Interface cronogramaObraService.
	 */
	@Autowired
	@Qualifier("repositoryInmuebleService")
	private InmuebleService inmuebleService;

	/**
	 * Instanciar Servicies Interface cronogramaObraService.
	 */
	@Autowired
	@Qualifier("repositoryPropietarioService")
	private PropietarioService propietarioService;

	/**
	 * Instanciar Servicies Interface cronogramaObraService.
	 */
	@Autowired
	@Qualifier("repositoryCronogramaObraService")
	private CronogramaObraService cronogramaObraService;

	/**
	 * Instanciar Servicies Interface AreaService.
	 */
	@Autowired
	@Qualifier("repositoryAreaService")
	private AreaService areaService;

	/**
	 * Instanciar Servicies Interface tipoAvaluoService.
	 */
	@Autowired
	@Qualifier("repositoryTipoAvaluoService")
	private TipoAvaluoService tipoAvaluoService;

	/**
	 * Instanciar Servicies Interface avaluoService.
	 */
	@Autowired
	@Qualifier("repositoryAvaluoService")
	private AvaluoService avaluoService;

	/**
	 * Traer la Informaciï¿½n del Bean Anterior Listado de Avaluo.
	 */
	@Autowired
	private ListadoAvaluosBean listadoAvaluosBean;

	/**
	 * Trae la información de Listas.
	 */
	@Autowired
	private ListasController listasController;

	/**
	 * Variable General
	 */
	private AvaluoConstructorDTO avaluoConstructorDTO;
	private InmuebleDTO inmuebleDTO;

	/**
	 * 1.1 Tipo Avalï¿½o
	 */
	private List<TipoAvaluoDTO> listTipoAvaluo;
	private Long idTipoAvaluo;

	/**
	 * 1.2 Informaciï¿½n Basica
	 */
	@Autowired
	private ListasGeograficasController listasGeograficasController;
	@Autowired
	private AvaluoController avaluoController;
	private String departamentoInmueble;
	private String departamentoCliente;
	private SortedMap<String, String> listPaisesOrg;
	private SortedMap<String, String> listPaisesPre;
	private SortedMap<String, String> listDepartamentosOrg;
	private SortedMap<String, String> listDepartamentosPre;
	private SortedMap<String, String> listDepartamentosPro;
	private List<DivipolaDTO> listCiudadesOrg;
	private List<DivipolaDTO> listCiudadesPre;
	private List<DivipolaDTO> listCiudadesPro;
	private List<AvaluoDTO> avaluosAnteriores;
	private List<String> listComplementoViaSolicitante;
	private List<String> listComplementoViaGeneradoraSolicitante;
	private final String[] letrasComplemento = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
			"O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
	private final String[] puntosCardinales = { "Norte", "Sur", "Este", "Oeste", "Bis", "Noreste", "Sureste", "Noroeste",
			"Suroeste", "Par", "Impar" };
	private final String[] valoresUnidadAlterno = { "Manzana", "Bloque", "Interior", "Edificio", "Torre", "Apartamento",
			"Casa", "Oficina", "Bodega", "Suite", "Local", "Garaje", "Consultorio", "Deposito", "Etapa", "Piso", "Nivel",
			"Agrupacion", "Unidad", "Tipo", "Sector", "Lote", "Superlote", "Conjunto", "Vivienda", "MÃ³dulo" };
	private List<EntidadDTO> listEntidades;
	private List<SegmentoDTO> listSegmentos;
	private Date fechaActual;
	private String centroMapa;
	private String tipoViaSol;
	private String numeroViaSol;
	private String complementoViaSol;
	private String numeroViaGeneradoraSol;
	private String complementoViaGeneradoraSol;
	private String placaSol;
	private String complementoPlacaSol;
	private String adicionalDireccionSol;

	/**
	 * 1.3 Titulaciï¿½n
	 */
	private List<PropietarioDTO> listPropietarios;
	private PropietarioDTO propietario;
	private Set<Integer> documentosConsultados;
	private boolean otrosDocumentos;
	private MapModel mapModel;

	/**
	 * 1.4 Informaciï¿½n del Sector
	 */
	private Boolean usuRevisor;
	private Boolean amenzaRiesgo;

	/**
	 * 1.5 InformaciÃ³n General del Inmueble
	 */
	private String opcionRegIrreg;
	private CaracteristicasFisicasDTO caracteristicasFisicasDTO;
	private List<CaracteristicasFisicasDTO> listCaracteristicasFisicas;
	private Boolean addTipoInmueble;

	/**
	 * 1.7 InformaciÃ³n de la ConstrucciÃ³n
	 */
	private static final Integer[] numerosGarajes = { 1, 2, 3, 4, 5 };
	private Set<AreaDTO> areasGarajes;
	private List<AreaDTO> listaAreas;
	private Set<AreaDTO> areasDepositos;

	/**
	 * 1.8 Cronograma Obra
	 */
	private List<CronogramaObraDTO> listCronogramaObraCostosDirectos;
	private List<CronogramaObraDTO> listCronogramaObraCostosIndirectos;
	private List<CronogramaObraDTO> listCostosDirectos;
	private List<CronogramaObraDTO> listCostosIndirectos;
	private CronogramaObraDTO newCronObraCD;
	private CronogramaObraDTO newCronObraCI;
	private Boolean addCostoD;
	private Boolean addCostoI;

	/**
	 * Informe Tecnico
	 */
	private List<AreaDTO> listDistribucionCostos;

	/**
	 * 1.10 Conceptos Finales
	 */
	private AreaDTO areas;
	private List<AreaDTO> listAreas;
	private BigDecimal valorTotalAvaluo;
	private BigDecimal valorUvrDia;
	private BigDecimal valorAvaluoUvr;
	private BigDecimal valorInmuebleAntesReforma;
	private Integer valorTotalGarantia;
	private Boolean addConFin;

	/**
	 * PostConstruct - Cargue Inicial
	 * 
	 * @throws Exception
	 */
	@PostConstruct
	public void init() {

		try {
			// 1.1 Listar Tipo Avaluo
			listarTipoAvaluos();
			// 1.2 Informaciï¿½n Bï¿½sica
			prepararAvaluoConstructor();

			// calcularCoordenadas();
			// 1.3 Titulaciï¿½n
			setPropietario(new PropietarioDTO());
			getPropietario().setPorcentajeDePropiedad(BigDecimal.valueOf(100));
			setListPropietarios(new ArrayList<PropietarioDTO>());
			getListPropietarios().add(getPropietario());

			// 1.4 Informaciï¿½n del Sector

		} catch (Exception e) {
			log.error(e.getMessage());
		}

	}

	/**
	 * Guardar el Avaluo Constructor.
	 */
	public void guardarAvaluoConstructor() {
		// AvaluoConstructorDTO avaluoConstrutorExistente =
		// avaluoConstructorService.encontrarPorId(avaluoConstructorDTO.getId());
		Boolean actualizar = true;
		if (actualizar) {
			try {
				// Guardar AvaluoConstructor
				avaluoConstructorService.actualizar(avaluoConstructorDTO);

				// Guardar Inmueble
				inmuebleService.crear(getInmuebleDTO());

				// Guardar Propietario
				/**
				 * if (listPropietarios != null || !listPropietarios.isEmpty()) { for
				 * (PropietarioDTO propietario : listPropietarios) { if
				 * (propietario.getNombresApellidosPropietario() != null ||
				 * !propietario.getNombresApellidosPropietario().isEmpty()) {
				 * propietarioService.crear(propietario); } } }
				 **/
				// Guardar CronogramaObra CostosDirectos e Indirectos
				if (listCronogramaObraCostosDirectos != null || listCronogramaObraCostosDirectos.isEmpty()) {
					for (CronogramaObraDTO cronogramaObraCD : listCronogramaObraCostosDirectos) {
						cronogramaObraService.crear(cronogramaObraCD);
					}
				}

				if (listCronogramaObraCostosIndirectos != null || !listCronogramaObraCostosIndirectos.isEmpty()) {
					for (CronogramaObraDTO cronogramaObraCD : listCronogramaObraCostosIndirectos) {
						cronogramaObraService.crear(cronogramaObraCD);
					}
				}

				// Guardar Areas
				if (listAreas != null || !listAreas.isEmpty()) {
					for (AreaDTO areaDTO : listAreas) {
						areaService.crear(areaDTO);
					}
				}
			} catch (AvaluoNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Actualiza los paneles en la vista desde el Bean.
	 * 
	 * @param idVistaPanel Id Panel
	 */
	private void actualizarVistaPanel(String idVistaPanel) {
		RequestContext context = RequestContext.getCurrentInstance();
		context.update(idVistaPanel);
	}

	/**
	 * Mensaje que se muestra en el Grow
	 * 
	 * @param mensaje              a Mostrar
	 * @param avaluoConstructorDTO Objeto
	 */
	private void mensajeGrow(String mensaje, AvaluoConstructorDTO avaluoConstructorDTO) {

		if (avaluoConstructorDTO == null) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} else {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, mensaje, "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	/**
	 * Prepara el cargue inicial para el Avaluo Constructor.
	 */
	private void prepararAvaluoConstructor() {
		fechaActual();

		// InformaciÃ³n Basica.
		avaluoConstructorDTO = (AvaluoConstructorDTO) listadoAvaluosBean.getAvaluo();

		// Cuando el Usuario es Revisor.
		setUsuRevisor(listadoAvaluosBean.isIngresarInforme());

		// Cliente
		separarDireccion(avaluoConstructorDTO.getCliente().getDireccionDeContactoSolicitante());

		listEntidades = avaluoController.entidades();
		listSegmentos = listasController.segmentos();
		avaluosAnteriores = obtenerAvaluosAnteriores();
		if (avaluoConstructorDTO.getCliente().getDivipola() != null) {
			departamentoCliente = avaluoConstructorDTO.getCliente().getDivipola().getDepartamento();
		}
		if (avaluoConstructorDTO.getDivipola() != null) {
			departamentoInmueble = avaluoConstructorDTO.getDivipola().getDepartamento();
		}
		listarPaises();
		listarDepartamentos();
		listarCiudadesPorDepartamentoOrg();
		listarCiudadesPorDepartamentoPre();
		// El pais de origen del cliente no necesariamente es colombia
		avaluoConstructorDTO.getCliente().setPaisOrigen("Colombia");
		avaluoConstructorDTO.setLocalizacion(null);
		avaluoConstructorDTO.setBarrio(null);

		// TitulaciÃ³n
		inmuebleDTO = new InmuebleDTO();
		inmuebleDTO.setLatitud(avaluoConstructorDTO.getLatitud());
		inmuebleDTO.setLongitud(avaluoConstructorDTO.getLongitud());
		inmuebleDTO.setAvaluoId(avaluoConstructorDTO.getId());
		documentosConsultados = new HashSet<Integer>();
		if (avaluoConstructorDTO.getDocumentosConsultados() != null
				&& !avaluoConstructorDTO.getDocumentosConsultados().isEmpty()) {
			List<String> documentosConsultadosList = Arrays
					.asList(avaluoConstructorDTO.getDocumentosConsultados().split(", "));
			for (String string : documentosConsultadosList) {
				documentosConsultados.add(Integer.parseInt(string));
			}
		}

		try {
			mapModel = new DefaultMapModel();
			LatLng coordenadas = coordenadasDeUbicacion();
			Marker marker = new Marker(coordenadas,
					"Codigo solicitud: " + avaluoConstructorDTO.getCodigoExterno().toString());
			marker.setDraggable(true);
			mapModel.addOverlay(marker);
			BigDecimal lat = BigDecimal.valueOf(coordenadas.getLat());
			BigDecimal lon = BigDecimal.valueOf(coordenadas.getLng());
			centroMapa = coordenadas.getLat() + ", " + coordenadas.getLng();
			inmuebleDTO.setLatitud(lat);
			inmuebleDTO.setLongitud(lon);
			avaluoConstructorDTO.setLatitud(lat);
			avaluoConstructorDTO.setLongitud(lon);
		} catch (Exception ex) {
			try {
				String uri = FacesContext.getCurrentInstance().getExternalContext()
						.encodeActionURL(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()
								+ "/pages/avaluos/listadoAvaluos.xhtml");
				FacesContext.getCurrentInstance().getExternalContext().redirect(uri);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				log.debug("Error en Coordenadas {}", avaluoConstructorDTO);
				e.printStackTrace();
			}
		}

		// Informacion del Sector.
		avaluoConstructorDTO.setAlcantarillado(Boolean.FALSE);
		avaluoConstructorDTO.setAgua(Boolean.FALSE);
		avaluoConstructorDTO.setEnergia(Boolean.FALSE);
		avaluoConstructorDTO.setGas(Boolean.FALSE);
		avaluoConstructorDTO.setTelefono(Boolean.FALSE);
		avaluoConstructorDTO.setZonaObjetivo(Boolean.FALSE);

		// Informacion General del Inmueble
		avaluoConstructorDTO.setRequiereReparaciones(Boolean.FALSE);
		avaluoConstructorDTO.setAlcantarilladoSector(Boolean.FALSE);
		avaluoConstructorDTO.setAguaSector(Boolean.FALSE);
		avaluoConstructorDTO.setEnergiaSector(Boolean.FALSE);
		avaluoConstructorDTO.setGasSector(Boolean.FALSE);
		avaluoConstructorDTO.setTelefonoSector(Boolean.FALSE);
		avaluoConstructorDTO.setSometidoAPropiedadHorizontal(Boolean.FALSE);
		avaluoConstructorDTO.setZonasVerdes(Boolean.FALSE);
		avaluoConstructorDTO.setPiscina(Boolean.FALSE);
		avaluoConstructorDTO.setSalonSocial(Boolean.FALSE);
		avaluoConstructorDTO.setJuegosInfantiles(Boolean.FALSE);
		avaluoConstructorDTO.setAscensor(Boolean.FALSE);
		listCaracteristicasFisicas = new ArrayList<CaracteristicasFisicasDTO>();
		caracteristicasFisicasDTO = new CaracteristicasFisicasDTO();
		addTipoInmueble = Boolean.FALSE;

		// Informe Tecnico
		AreaDTO distri = new AreaDTO();
		listDistribucionCostos = new ArrayList<AreaDTO>();
		distri.setNombre("Costos Directos");
		distri.setValorTotal(BigDecimal.valueOf(0));
		distri.setPorcentaje(BigDecimal.valueOf(0));
		listDistribucionCostos.add(distri);
		distri = new AreaDTO();
		distri.setNombre("Costos Indirectos");
		distri.setValorTotal(BigDecimal.valueOf(0));
		distri.setPorcentaje(BigDecimal.valueOf(0));
		listDistribucionCostos.add(distri);
		distri = new AreaDTO();
		distri.setNombre("Costos Financieros");
		distri.setValorTotal(BigDecimal.valueOf(0));
		distri.setPorcentaje(BigDecimal.valueOf(0));
		listDistribucionCostos.add(distri);
		distri = new AreaDTO();
		distri.setNombre("Valor Total del Proyecto");
		distri.setValorTotal(BigDecimal.valueOf(0));
		distri.setPorcentaje(BigDecimal.valueOf(0));
		listDistribucionCostos.add(distri);
		distri = new AreaDTO();
		distri.setNombre("Valor Max. a Financiar");
		distri.setValorTotal(BigDecimal.valueOf(0));
		distri.setPorcentaje(BigDecimal.valueOf(0));
		listDistribucionCostos.add(distri);

		// Conceptos Finales
		addConFin = Boolean.FALSE;
		areas = new AreaDTO();
		listAreas = new ArrayList<AreaDTO>();
		valorUvrDia = BigDecimal.valueOf(276.65);

		// Cronograma Obra
		addCostoD = false;
		addCostoI = false;
		newCronObraCD = new CronogramaObraDTO();
		newCronObraCD.setFechaInicio(fechaActual());
		newCronObraCI = new CronogramaObraDTO();
		newCronObraCI.setFechaInicio(fechaActual());
		cronogramaObra(avaluoConstructorDTO.getId());
	}

	/**
	 * CoordenadasDeUbicacion
	 * 
	 * @return LatLng
	 */
	private LatLng coordenadasDeUbicacion() {
		LatLng coordenadas = (avaluoConstructorDTO.getLatitud() == null || avaluoConstructorDTO.getLongitud() == null)
				? null
				: new LatLng(avaluoConstructorDTO.getLatitud().doubleValue(), avaluoConstructorDTO.getLongitud().doubleValue());
		if (coordenadas == null) {
			GLatLng gLatLng = avaluoController.calcularCoordenadasAvaluo(avaluoConstructorDTO,
					avaluoConstructorDTO.getDivipola().getDepartamento(), true);
			if (gLatLng != null) {
				avaluoConstructorDTO.setLatitud(new BigDecimal(gLatLng.getLat()));
				avaluoConstructorDTO.setLongitud(new BigDecimal(gLatLng.getLng()));
				coordenadas = new LatLng(gLatLng.getLat(), gLatLng.getLng());
			}
		}
		return coordenadas;
	}

	/**
	 * Metodo onMarkerDrag.
	 * 
	 * @param event onMarkerDrag
	 */
	public void onMarkerDrag(MarkerDragEvent event) {
		log.debug("Arrastrando marcador");
		Marker marker = event.getMarker();
		avaluoConstructorDTO.setLatitud(BigDecimal.valueOf(marker.getLatlng().getLat()));
		avaluoConstructorDTO.setLongitud(BigDecimal.valueOf(marker.getLatlng().getLng()));
		log.debug("El marcador quedo en: {}, {}", marker.getLatlng().getLat(), marker.getLatlng().getLng());
	}

	/**
	 * --------------------------------------- TIPO DE AVALUO
	 * ------------------------------------------------------------
	 */

	/**
	 * Se listan los Tipos de Avaluo.
	 */
	private void listarTipoAvaluos() {
		setListTipoAvaluo(new ArrayList<TipoAvaluoDTO>());
		listTipoAvaluo.addAll(tipoAvaluoService.encontrarTodos());
	}

	/**
	 * --------------------------------------- INFORMACIÃ–N BASICA
	 * --------------------------------------------------------
	 */

	/**
	 * Fecha Actual del sistema.
	 * 
	 * @return Date
	 */
	private Date fechaActual() {
		fechaActual = new Date();
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
		// Formateo de Fecha para mostrar en la vista - (String)
		String fechaCreacion = sm.format(fechaActual.getTime());
		// Formateo de Fecha campo db - (Date)
		try {
			fechaActual = new Date();
			setFechaActual(sm.parse(fechaCreacion));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;
		return getFechaActual();
	}

	/**
	 * Listar Paises.
	 */
	private void listarPaises() {
		log.debug("Inicializando lista de paises.");
		setListPaisesOrg(listasGeograficasController.paises());
		setListPaisesPre(listasGeograficasController.paises());
	}

	/**
	 * Listar Departamentos.
	 */
	private void listarDepartamentos() {
		log.debug("Inicializando lista de los departamentos.");
		setListDepartamentosOrg(listasGeograficasController.departamentos());
		setListDepartamentosPre(listasGeograficasController.departamentos());
		setListDepartamentosPro(listasGeograficasController.departamentos());
	}

	/**
	 * Listar Ciudades por Departamento Origen.
	 */
	public void listarCiudadesPorDepartamentoOrg() {
		log.debug("Inicializando lista de las ciudades por Departamento Origen.");
		setListCiudadesOrg(listasGeograficasController.ciudadesPorDepartamento(departamentoCliente));
	}

	/**
	 * Listar Ciudades por Departamento Predio.
	 */
	public void listarCiudadesPorDepartamentoPre() {
		log.debug("Inicializando lista de las ciudades por Departamento Predio.");
		setListCiudadesPre(listasGeograficasController.ciudadesPorDepartamento(departamentoInmueble));
	}

	/**
	 * Listar Ciudades por Departamento Propietario.
	 */
	public void listarCiudadesPorDepartamentoPro() {
		log.debug("Inicializando lista de las ciudades por Departamento Propietario.");
		setListCiudadesPro(listasGeograficasController.ciudadesEnDepartamento(inmuebleDTO.getDepartamentoEscritura()));
	}

	/**
	 * Lista los Avaluos anteriores del Cliente.
	 * 
	 * @param tipoDocumentoIdentificacion Tipo Documento
	 * @param numeroDocumento             Identificaciï¿½n
	 * @return Lista de Avaluos Anteriores.
	 */
	public List<AvaluoDTO> obtenerAvaluosAnteriores() {
		return avaluoService.encontrarAvaluosAnteriores(
				getAvaluoConstructorDTO().getCliente().getTipoDocumentoIdentificacion(),
				getAvaluoConstructorDTO().getCliente().getNumeroDocumento(), avaluoConstructorDTO.getId());
	}

	/**
	 * Separa la direcciï¿½n String para mostrarse en la vista.
	 * 
	 * @param direccion String Direcciï¿½n.
	 */
	private void separarDireccion(String direccion) {
		Pattern patronTipoVia = Pattern.compile("[A-Z]{2}");
		if (!direccion.isEmpty()) {
			Matcher matcher = patronTipoVia.matcher(direccion);
			if (matcher.find()) {
				setTipoViaSol(matcher.group(0));
				direccion = direccion.replaceFirst(getTipoViaSol(), "").trim();
			} else {
				throw new IllegalArgumentException("La direcciÃ³n del cliente " + direccion + " es invalida");
			}
			Pattern patronNumeroVia = Pattern.compile("[0-9]+");
			matcher = patronNumeroVia.matcher(direccion);
			if (matcher.find()) {
				setNumeroViaSol(matcher.group(0));
				direccion = direccion.replaceFirst(getNumeroViaSol(), "").trim();
			} else {
				throw new IllegalArgumentException("La direcciÃ³n del cliente " + direccion + " es invalida");
			}
			Pattern patronComplementoVia = Pattern.compile("[a-zA-Z ]+");
			matcher = patronComplementoVia.matcher(direccion);
			if (matcher.find()) {
				setComplementoViaSol(matcher.group(0).trim().length() > 0 ? matcher.group(0).trim() : "");
				setListComplementoViaSolicitante(
						getComplementoViaSol().isEmpty() ? new ArrayList<String>() : convertirALista(getComplementoViaSol()));
				direccion = !listComplementoViaSolicitante.isEmpty() ? direccion.replaceFirst(getComplementoViaSol(), "").trim()
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
				setNumeroViaGeneradoraSol(matcher.group(0));
				direccion = direccion.replaceFirst(getNumeroViaGeneradoraSol(), "").trim();
			}
			Pattern patronComplementoViaGeneradora = Pattern.compile("[a-zA-Z ]+");
			matcher = patronComplementoViaGeneradora.matcher(direccion);
			if (matcher.find()) {
				setComplementoViaGeneradoraSol(matcher.group(0).trim().length() > 0 ? matcher.group(0).trim() : "");
				setListComplementoViaGeneradoraSolicitante(getComplementoViaGeneradoraSol().isEmpty() ? new ArrayList<String>()
						: convertirALista(getComplementoViaGeneradoraSol()));
				direccion = !getComplementoViaGeneradoraSol().isEmpty()
						? direccion.replace(getComplementoViaGeneradoraSol(), "").trim()
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
				setPlacaSol(matcher.group(0));
				direccion = direccion.replaceFirst(getPlacaSol(), "").trim();
			}
			Pattern patronComplementoPlaca = Pattern.compile("[A-Za-z]{2,11} [0-9]{1,6}( [A-Za-z]{2,11} [0-9]{1,6})*");
			matcher = patronComplementoPlaca.matcher(direccion);
			if (matcher.find()) {
				setComplementoPlacaSol(matcher.group(0).trim());
				direccion = direccion.replaceFirst(getComplementoPlacaSol(), "").trim();
			}
			avaluoConstructorDTO.setAdicionalDireccion(direccion);
		}
	}

	/**
	 * Lista de autocompletar Complemento.
	 * 
	 * @param valor
	 * @return Lista de complementos.
	 */
	public List<String> completarComplemento(String valor) {
		List<String> resultado = new ArrayList<String>();
		for (String letra : letrasComplemento) {
			if (letra.startsWith(valor)) {
				resultado.add(letra);
			}
		}
		for (String punto : puntosCardinales) {
			if (punto.startsWith(valor)) {
				resultado.add(punto);
			}
		}
		return resultado;
	}

	/**
	 * Lista de autocompletar Complemento Placa.
	 * 
	 * @param valor
	 * @return Lista de complementos Placa.
	 */
	public List<String> completarComplementoPlaca(String valor) {
		List<String> resultado = new ArrayList<String>();
		for (String letra : valoresUnidadAlterno) {
			if (letra.startsWith(valor)) {
				resultado.add(letra);
			}
		}
		return resultado;
	}

	/**
	 * Convertir a Lista.
	 * 
	 * @param string Parametro String
	 * @return Lista de String.
	 */
	private List<String> convertirALista(String string) {
		if (string.isEmpty()) {
			return new ArrayList<String>();
		}
		return Arrays.asList(string.split(" "));
	}

	/**
	 * ---------------------------------------- TITULACIï¿½N
	 * ----------------------------------------------------------------
	 */

	/**
	 * Calcula el porcentaje de propiedad de todos.
	 */
	public Boolean calcularPorcentajePropiedad() {
		List<PropietarioDTO> listaPropietarios = getListPropietarios();
		BigDecimal porcentajeTotal = BigDecimal.valueOf(0.0);
		Boolean pocentajeMayor100 = false;
		for (PropietarioDTO propietario : listaPropietarios) {
			if (propietario.getPorcentajeDePropiedad() != null) {
				porcentajeTotal = porcentajeTotal.add(propietario.getPorcentajeDePropiedad());

				if (porcentajeTotal.compareTo(BigDecimal.valueOf(100.0)) == 1) {
					mensajeGrow("El Porcentaje supera el 100%", avaluoConstructorDTO);
					propietario.setPorcentajeDePropiedad(BigDecimal.valueOf(0.0));
					return pocentajeMayor100 = true;
				} else if (porcentajeTotal.compareTo(BigDecimal.valueOf(100.0)) == 0) {
					mensajeGrow("El Porcentaje es igual 100% y no se Agregara otro Propietario", avaluoConstructorDTO);
					return pocentajeMayor100 = true;
				}
			}
		}
		return pocentajeMayor100;
	}

	/**
	 * Agregar Propietarios a la lista.
	 */
	public void agregarPropietario() {
		if (!calcularPorcentajePropiedad()) {
			getListPropietarios().add(new PropietarioDTO());
		}
	}

	/**
	 * Eliminar Propietario de la Lista.
	 */
	public void eliminarPropietario(PropietarioDTO propietarioEliminar) {
		getListPropietarios().remove(propietarioEliminar);
	}

	/**
	 * --------------------------------- INFORMACION GENERAL DEL INMUEBLE
	 * ---------------------------------------------------------------
	 */

	public Boolean validarTipoInmuebleRepetido() {
		Boolean validacionOk = Boolean.TRUE;
		String mensaje = "Campos Vacios: ";
		for (CaracteristicasFisicasDTO caraDto : listCaracteristicasFisicas) {
			if (caracteristicasFisicasDTO.getTipoInmueble() != null) {
				if (caracteristicasFisicasDTO.getTipoInmueble().equals(caraDto.getTipoInmueble())) {
					mensaje = "Tipo de Inmuble Existente ";
					caracteristicasFisicasDTO = new CaracteristicasFisicasDTO();
					validacionOk = Boolean.FALSE;
					break;
				}
			} else {
				mensaje += " [Tipo Inmueble]";
				validacionOk = Boolean.FALSE;
			}

			if (caracteristicasFisicasDTO.getCantidad() == null) {
				mensaje += " [Cantidad]";
				validacionOk = Boolean.FALSE;
			}
		}

		if (!validacionOk) {
			mensajeGrow(mensaje, null);
		}

		return validacionOk;
	}

	/**
	 * Agrega tipos de inmueble en Caracteristicas fisicas.
	 */
	public void agregarTipoInmuebleCF() {
		if (validarTipoInmuebleRepetido()) {
			caracteristicasFisicasDTO.setAvaluoId(avaluoConstructorDTO.getId().intValue());
			listCaracteristicasFisicas.add(caracteristicasFisicasDTO);
		}

		caracteristicasFisicasDTO = new CaracteristicasFisicasDTO();
	}

	/**
	 * Eliminar Tipo Inmueble.
	 */
	public void eliminarTipoInmueble(CaracteristicasFisicasDTO caracteristicasFisicasDTO) {
		listCaracteristicasFisicas.remove(caracteristicasFisicasDTO);
	}

	/**
	 * Asigna el valor seleccionado en la opcion.
	 */
	public void opcionRegIrreg() {
		if (opcionRegIrreg.equals("Regular")) {
			avaluoConstructorDTO.setRegular(Boolean.TRUE);
			avaluoConstructorDTO.setIrregular(Boolean.FALSE);
		} else if (opcionRegIrreg.equals("Irregular")) {
			avaluoConstructorDTO.setIrregular(Boolean.TRUE);
			avaluoConstructorDTO.setRegular(Boolean.FALSE);

		}
	}

	/**
	 * --------------------------------- INFORMACION DE LA CONSTRUCCION
	 * ---------------------------------------------------------------
	 */

	/**
	 * onGarajeDobleClicked.
	 */
	public void onGarajeDobleClicked() {
		if (avaluoConstructorDTO.getGarajePrivado() != null && avaluoConstructorDTO.getGarajePrivado() > 0) {
			avaluoConstructorDTO.setTotalCuposParquedaro(avaluoConstructorDTO.getGarajePrivado()
					* (avaluoConstructorDTO.getGarajeDoble() == null ? 1 : avaluoConstructorDTO.getGarajeDoble() ? 2 : 1));
		}
		if (avaluoConstructorDTO.getGarajeExlusivo() != null && avaluoConstructorDTO.getGarajeExlusivo() > 0) {
			avaluoConstructorDTO.setTotalCuposParquedaro(avaluoConstructorDTO.getGarajeExlusivo()
					* (avaluoConstructorDTO.getGarajeDoble() == null ? 1 : avaluoConstructorDTO.getGarajeDoble() ? 2 : 1));
		}
		if (avaluoConstructorDTO.getBahiaComunal() != null && avaluoConstructorDTO.getBahiaComunal() > 0) {
			avaluoConstructorDTO.setTotalCuposParquedaro(avaluoConstructorDTO.getBahiaComunal()
					* (avaluoConstructorDTO.getGarajeDoble() == null ? 1 : avaluoConstructorDTO.getGarajeDoble() ? 2 : 1));
		}
	}

	/**
	 * onParaleloClicked.
	 */
	public void onParaleloClicked() {
		if (avaluoConstructorDTO.getGarajeParalelo1()) {
			avaluoConstructorDTO.setGarajeServidumbre1(false);
		}
		if (avaluoConstructorDTO.getGarajeParalelo2()) {
			avaluoConstructorDTO.setGarajeServidumbre2(false);
		}
		if (avaluoConstructorDTO.getGarajeParalelo3()) {
			avaluoConstructorDTO.setGarajeServidumbre3(false);
		}
		if (avaluoConstructorDTO.getGarajeParalelo4()) {
			avaluoConstructorDTO.setGarajeServidumbre4(false);
		}
		if (avaluoConstructorDTO.getGarajeParalelo5()) {
			avaluoConstructorDTO.setGarajeServidumbre5(false);
		}
	}

	/**
	 * verificarMatriculaDuplicada
	 * 
	 * @param clientId              Id del Cliente
	 * @param matriculaInmobiliaria
	 */
	public void verificarMatriculaDuplicada(String clientId, String matriculaInmobiliaria) {
		FacesContext context = FacesContext.getCurrentInstance();
		if (!clientId.contains("Principal1")
				&& matriculaInmobiliaria.equals(avaluoConstructorDTO.getMatriculaInmobiliariaPrincipal1())) {
			context.addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Matricula inmobiliaria duplicada",
					"Matricula inmobiliaria duplicada con la matricula inmobiliaria principal"));
		}
		if (!clientId.contains("Garaje1")
				&& matriculaInmobiliaria.equals(avaluoConstructorDTO.getMatriculaInmobiliariaGaraje1())) {
			context.addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Matricula inmobiliaria duplicada",
					"Matricula inmobiliaria duplicada con la matricula inmobiliaria garaje 1"));
		}
		if (!clientId.contains("Garaje2")
				&& matriculaInmobiliaria.equals(avaluoConstructorDTO.getMatriculaInmobiliariaGaraje2())) {
			context.addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Matricula inmobiliaria duplicada",
					"Matricula inmobiliaria duplicada con la matricula inmobiliaria garaje 2"));
		}
		if (!clientId.contains("Garaje3")
				&& matriculaInmobiliaria.equals(avaluoConstructorDTO.getMatriculaInmobiliariaGaraje3())) {
			context.addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Matricula inmobiliaria duplicada",
					"Matricula inmobiliaria duplicada con la matricula inmobiliaria garaje 3"));
		}
		if (!clientId.contains("Garaje4")
				&& matriculaInmobiliaria.equals(avaluoConstructorDTO.getMatriculaInmobiliariaGaraje4())) {
			context.addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Matricula inmobiliaria duplicada",
					"Matricula inmobiliaria duplicada con la matricula inmobiliaria garaje 4"));
		}
		if (!clientId.contains("Garaje5")
				&& matriculaInmobiliaria.equals(avaluoConstructorDTO.getMatriculaInmobiliariaGaraje5())) {
			context.addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Matricula inmobiliaria duplicada",
					"Matricula inmobiliaria duplicada con la matricula inmobiliaria garaje 5"));
		}
		if (!clientId.contains("Deposito1")
				&& matriculaInmobiliaria.equals(avaluoConstructorDTO.getMatriculaInmobiliariaDeposito1())) {
			context.addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Matricula inmobiliaria duplicada",
					"Matricula inmobiliaria duplicada con la matricula inmobiliaria deposito 1"));
		}
		if (!clientId.contains("Deposito2")
				&& matriculaInmobiliaria.equals(avaluoConstructorDTO.getMatriculaInmobiliariaDeposito2())) {
			context.addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Matricula inmobiliaria duplicada",
					"Matricula inmobiliaria duplicada con la matricula inmobiliaria deposito 2"));
		}
		if (!clientId.contains("Deposito3")
				&& matriculaInmobiliaria.equals(avaluoConstructorDTO.getMatriculaInmobiliariaDeposito3())) {
			context.addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Matricula inmobiliaria duplicada",
					"Matricula inmobiliaria duplicada con la matricula inmobiliaria deposito 3"));
		}
		if (!clientId.contains("Deposito4")
				&& matriculaInmobiliaria.equals(avaluoConstructorDTO.getMatriculaInmobiliariaDeposito4())) {
			context.addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Matricula inmobiliaria duplicada",
					"Matricula inmobiliaria duplicada con la matricula inmobiliaria deposito 4"));
		}
		if (!clientId.contains("Deposito5")
				&& matriculaInmobiliaria.equals(avaluoConstructorDTO.getMatriculaInmobiliariaDeposito5())) {
			context.addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Matricula inmobiliaria duplicada",
					"Matricula inmobiliaria duplicada con la matricula inmobiliaria deposito 5"));
		}
	}

	/**
	 * Actualiza los Garajes.
	 * 
	 * @param actualizado Objeto.
	 */
	public void actualizarGarajes(String actualizado) {
		if ("Privado".equals(actualizado) && avaluoConstructorDTO.getGarajePrivado() != null
				&& avaluoConstructorDTO.getGarajePrivado() > 0) {
			avaluoConstructorDTO.setGarajeExlusivo(null);
			avaluoConstructorDTO.setBahiaComunal(null);
			avaluoConstructorDTO.setTotalCuposDeParqueo(avaluoConstructorDTO.getGarajePrivado()
					* (avaluoConstructorDTO.getGarajeDoble() == null ? 1 : avaluoConstructorDTO.getGarajeDoble() ? 2 : 1));
			avaluoConstructorDTO.setNumeroTotalDeGarajes(avaluoConstructorDTO.getGarajePrivado());
		} else if ("Exclusivo".equals(actualizado) && avaluoConstructorDTO.getGarajeExlusivo() != null
				&& avaluoConstructorDTO.getGarajeExlusivo() > 0) {
			avaluoConstructorDTO.setGarajePrivado(null);
			avaluoConstructorDTO.setBahiaComunal(null);
			avaluoConstructorDTO.setNumeroTotalDeGarajes(avaluoConstructorDTO.getGarajeExlusivo());
			avaluoConstructorDTO.setTotalCuposDeParqueo(avaluoConstructorDTO.getGarajeExlusivo()
					* (avaluoConstructorDTO.getGarajeDoble() == null ? 1 : avaluoConstructorDTO.getGarajeDoble() ? 2 : 1));
		} else if ("BahiaComunal".equals(actualizado) && avaluoConstructorDTO.getBahiaComunal() != null
				&& avaluoConstructorDTO.getBahiaComunal() > 0) {
			avaluoConstructorDTO.setGarajeExlusivo(null);
			avaluoConstructorDTO.setGarajePrivado(null);
			avaluoConstructorDTO.setNumeroTotalDeGarajes(avaluoConstructorDTO.getBahiaComunal());
			avaluoConstructorDTO.setTotalCuposDeParqueo(avaluoConstructorDTO.getBahiaComunal()
					* (avaluoConstructorDTO.getGarajeDoble() == null ? 1 : avaluoConstructorDTO.getGarajeDoble() ? 2 : 1));
		} else {
			avaluoConstructorDTO.setGarajePrivado(null);
			avaluoConstructorDTO.setGarajeExlusivo(null);
			avaluoConstructorDTO.setBahiaComunal(null);
			avaluoConstructorDTO.setNumeroTotalDeGarajes(null);
			avaluoConstructorDTO.setTotalCuposDeParqueo(null);
		}
		// actualizarAreasPorGaraje();
	}

	/**
	 * Comprueba la existencia de Parqueaderos.
	 * 
	 * @param descripcionNumerica Des
	 * @param nombre              Nom
	 * @param areas               Areas
	 * @return AreaDTO
	 */
	private AreaDTO comprobarExistenciaArea(ListaDesplegable descripcionNumerica, String nombre, Set<AreaDTO> areas) {
		for (AreaDTO area : areas) {
			if (area.getNombre().equals(nombre) && area.getDescripcionNumerica().equals(descripcionNumerica)) {
				return area;
			}
		}
		return null;
	}

	/**
	 * Actualiza el numeor de Parqueadero 1
	 * 
	 * @param event Evento
	 */
	public void actualizarNumeroParqueadero1(ValueChangeEvent event) {
		if (event.getPhaseId() != PhaseId.INVOKE_APPLICATION) {
			event.setPhaseId(PhaseId.INVOKE_APPLICATION);
			event.queue();
			return;
		}
		String oldValue = event.getOldValue() != null ? Integer.toString((Integer) event.getOldValue()) : null;
		String newValue = Integer.toString((Integer) event.getNewValue());
		AreaDTO area = comprobarExistenciaArea(DescripcionAreaPH.Garaje, oldValue == null ? Integer.toString(1) : oldValue,
				areasGarajes);
		if (area != null) {
			area.setNombre(newValue);
		}
	}

	/**
	 * Actualiza el numeor de Parqueadero 2
	 * 
	 * @param event Evento
	 */
	public void actualizarNumeroParqueadero2(ValueChangeEvent event) {
		if (event.getPhaseId() != PhaseId.INVOKE_APPLICATION) {
			event.setPhaseId(PhaseId.INVOKE_APPLICATION);
			event.queue();
			return;
		}
		String oldValue = event.getOldValue() != null ? Integer.toString((Integer) event.getOldValue()) : null;
		String newValue = Integer.toString((Integer) event.getNewValue());
		AreaDTO area = comprobarExistenciaArea(DescripcionAreaPH.Garaje, oldValue == null ? Integer.toString(2) : oldValue,
				areasGarajes);
		if (area != null) {
			area.setNombre(newValue);
		}
	}

	/**
	 * Actualiza el numeor de Parqueadero 3
	 * 
	 * @param event Evento
	 */
	public void actualizarNumeroParqueadero3(ValueChangeEvent event) {
		if (event.getPhaseId() != PhaseId.INVOKE_APPLICATION) {
			event.setPhaseId(PhaseId.INVOKE_APPLICATION);
			event.queue();
			return;
		}
		String oldValue = event.getOldValue() != null ? Integer.toString((Integer) event.getOldValue()) : null;
		String newValue = Integer.toString((Integer) event.getNewValue());
		AreaDTO area = comprobarExistenciaArea(DescripcionAreaPH.Garaje, oldValue == null ? Integer.toString(3) : oldValue,
				areasGarajes);
		if (area != null) {
			area.setNombre(newValue);
		}
	}

	/**
	 * Actualiza el numeor de Parqueadero 4
	 * 
	 * @param event Evento
	 */
	public void actualizarNumeroParqueadero4(ValueChangeEvent event) {
		if (event.getPhaseId() != PhaseId.INVOKE_APPLICATION) {
			event.setPhaseId(PhaseId.INVOKE_APPLICATION);
			event.queue();
			return;
		}
		String oldValue = event.getOldValue() != null ? Integer.toString((Integer) event.getOldValue()) : null;
		String newValue = Integer.toString((Integer) event.getNewValue());
		AreaDTO area = comprobarExistenciaArea(DescripcionAreaPH.Garaje, oldValue == null ? Integer.toString(4) : oldValue,
				areasGarajes);
		if (area != null) {
			area.setNombre(newValue);
		}
	}

	/**
	 * Actualiza el numeor de Parqueadero 5
	 * 
	 * @param event Evento
	 */
	public void actualizarNumeroParqueadero5(ValueChangeEvent event) {
		if (event.getPhaseId() != PhaseId.INVOKE_APPLICATION) {
			event.setPhaseId(PhaseId.INVOKE_APPLICATION);
			event.queue();
			return;
		}
		String oldValue = event.getOldValue() != null ? Integer.toString((Integer) event.getOldValue()) : null;
		String newValue = Integer.toString((Integer) event.getNewValue());
		AreaDTO area = comprobarExistenciaArea(DescripcionAreaPH.Garaje, oldValue == null ? Integer.toString(5) : oldValue,
				areasGarajes);
		if (area != null) {
			area.setNombre(newValue);
		}
	}

	/**
	 * Actualiza los Depositos.
	 * 
	 * @param actualizado String
	 */
	public void actualizarDepositos(String actualizado) {
		if ("Privado".equals(actualizado) && avaluoConstructorDTO.getDepositosPrivados() != null
				&& avaluoConstructorDTO.getDepositosPrivados().intValue() > 0) {
			avaluoConstructorDTO.setDepositosExclusivos(null);
			avaluoConstructorDTO.setNumeroTotalDepositos(avaluoConstructorDTO.getDepositosPrivados());
		} else if ("Exclusivo".equals(actualizado) && avaluoConstructorDTO.getDepositosExclusivos() != null
				&& avaluoConstructorDTO.getDepositosExclusivos().intValue() > 0) {
			avaluoConstructorDTO.setDepositosPrivados(null);
			avaluoConstructorDTO.setNumeroTotalDepositos(avaluoConstructorDTO.getDepositosExclusivos());
		} else {
			avaluoConstructorDTO.setDepositosPrivados(null);
			avaluoConstructorDTO.setDepositosExclusivos(null);
			avaluoConstructorDTO.setNumeroTotalDepositos(null);
		}
		// actualizarAreasPorDeposito();
	}

	/**
	 * Actualizar Ares por Deposito.
	 */
	public void actualizarAreasPorDeposito() {
		if (avaluoConstructorDTO.getNumeroTotalDepositos() != null && avaluoConstructorDTO.getNumeroTotalDepositos() > 0) {
			if (areasDepositos != null && !areasDepositos.isEmpty()) {
				listaAreas.removeAll(areasDepositos);
				if (areasDepositos.size() > avaluoConstructorDTO.getNumeroTotalDepositos()) {
					Predicate<AreaDTO> depositosRetenidos = new Predicate<AreaDTO>() {

						@Override
						public boolean apply(AreaDTO input) {
							return Integer.parseInt(input.getNombre()) <= avaluoConstructorDTO.getNumeroTotalDepositos();
						}
					};
					Set<AreaDTO> areasResultado = ImmutableSet.copyOf(Sets.filter(areasDepositos, depositosRetenidos));
					areasDepositos.clear();
					areasDepositos.addAll(areasResultado);
				} else if (areasDepositos.size() < avaluoConstructorDTO.getNumeroTotalDepositos()) {
					for (int i = 1; i <= avaluoConstructorDTO.getNumeroTotalDepositos(); i++) {
						if (comprobarExistenciaArea(DescripcionAreaPH.Deposito, Integer.toString(i), areasDepositos) == null) {
							AreaDTO area = new AreaDTO();
							area.setDescripcionNumerica(DescripcionAreaPH.Deposito);
							area.setNombre(Integer.toString(i));
							area.setAvaluoId(avaluoConstructorDTO.getId());
							areasDepositos.add(area);
						}
					}
				}
				listaAreas.addAll(areasDepositos);
			} else {
				areasDepositos = new HashSet<AreaDTO>();
				for (int i = 1; i <= avaluoConstructorDTO.getNumeroTotalDepositos(); i++) {
					AreaDTO area = new AreaDTO();
					area.setDescripcionNumerica(DescripcionAreaPH.Deposito);
					area.setNombre(Integer.toString(i));
					area.setAvaluoId(avaluoConstructorDTO.getId());
					areasDepositos.add(area);
				}
				listaAreas.addAll(areasDepositos);
			}
		}
	}

	/**
	 * Actualizar Nombre Deposito1
	 * 
	 * @param event
	 */
	public void actualizarNombreDeposito1(ValueChangeEvent event) {
		if (event.getPhaseId() != PhaseId.INVOKE_APPLICATION) {
			event.setPhaseId(PhaseId.INVOKE_APPLICATION);
			event.queue();
			return;
		}
		String oldValue = event.getOldValue() != null ? Integer.toString((Integer) event.getOldValue()) : null;
		String newValue = Integer.toString((Integer) event.getNewValue());
		AreaDTO area = comprobarExistenciaArea(DescripcionAreaPH.Deposito,
				oldValue == null ? Integer.toString(1) : oldValue, areasDepositos);
		if (area != null) {
			area.setNombre(newValue);
		}
	}

	/**
	 * Actualizar Nombre Deposito2
	 * 
	 * @param event
	 */
	public void actualizarNombreDeposito2(ValueChangeEvent event) {
		if (event.getPhaseId() != PhaseId.INVOKE_APPLICATION) {
			event.setPhaseId(PhaseId.INVOKE_APPLICATION);
			event.queue();
			return;
		}
		String oldValue = event.getOldValue() != null ? Integer.toString((Integer) event.getOldValue()) : null;
		String newValue = Integer.toString((Integer) event.getNewValue());
		AreaDTO area = comprobarExistenciaArea(DescripcionAreaPH.Deposito,
				oldValue == null ? Integer.toString(2) : oldValue, areasDepositos);
		if (area != null) {
			area.setNombre(newValue);
		}
	}

	/**
	 * Actualizar Nombre Deposito3
	 * 
	 * @param event
	 */
	public void actualizarNombreDeposito3(ValueChangeEvent event) {
		if (event.getPhaseId() != PhaseId.INVOKE_APPLICATION) {
			event.setPhaseId(PhaseId.INVOKE_APPLICATION);
			event.queue();
			return;
		}
		String oldValue = event.getOldValue() != null ? Integer.toString((Integer) event.getOldValue()) : null;
		String newValue = Integer.toString((Integer) event.getNewValue());
		AreaDTO area = comprobarExistenciaArea(DescripcionAreaPH.Deposito,
				oldValue == null ? Integer.toString(3) : oldValue, areasDepositos);
		if (area != null) {
			area.setNombre(newValue);
		}
	}

	/**
	 * Actualizar Nombre Deposito4
	 * 
	 * @param event
	 */
	public void actualizarNombreDeposito4(ValueChangeEvent event) {
		if (event.getPhaseId() != PhaseId.INVOKE_APPLICATION) {
			event.setPhaseId(PhaseId.INVOKE_APPLICATION);
			event.queue();
			return;
		}
		String oldValue = event.getOldValue() != null ? Integer.toString((Integer) event.getOldValue()) : null;
		String newValue = Integer.toString((Integer) event.getNewValue());
		AreaDTO area = comprobarExistenciaArea(DescripcionAreaPH.Deposito,
				oldValue == null ? Integer.toString(4) : oldValue, areasDepositos);
		if (area != null) {
			area.setNombre(newValue);
		}
	}

	/**
	 * Actualizar Nombre Deposito5
	 * 
	 * @param event
	 */
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

	/**
	 * --------------------------------- CRONOGRAMA OBRA
	 * ---------------------------------------------------------------
	 */

	/**
	 * Valida las fechas Actual, Inicio y Fin.
	 * 
	 * @param fechaIni Fecha Inicio
	 * @param fechaFin Fecha Fin
	 */
	public void validarFechas(CronogramaObraDTO cronObraDTO, String opcion) {
		try {
			/**
			 * if (opcion.equals("ini")) { if
			 * (fechaActual.compareTo(cronObraDTO.getFechaInicio()) > 0) { mensajeGrow("La
			 * Fecha Inicio es Menor a la Fecha Actual", null); } }
			 * 
			 * if (opcion.equals("fin")) { if
			 * (fechaActual.compareTo(cronObraDTO.getFechaFin()) > 0) { mensajeGrow("La
			 * Fecha Fin es Menor a la Fecha Actual", null); } }
			 */

			// Validacion de las dos Fecha Ini y Fin
			if (cronObraDTO.getFechaInicio() != null && cronObraDTO.getFechaFin() != null) {
				if (cronObraDTO.getFechaInicio().compareTo(cronObraDTO.getFechaFin()) > 0) {
					mensajeGrow("La Fecha Fin es Menor a la Fecha Inicio", null);
					cronObraDTO.setFechaFin(null);
					actualizarVistaPanel("form:accordionPanel:idTableCronObraCD");
				}
			}
		} catch (Exception e) {
			System.out.print("Null");
		}
	}

	/**
	 * Calcula en la tabla Cronograma Obra - Constos Directos.
	 */
	public void calcularTotalCostosDirectos(String opcion) {
		BigDecimal totalValorPresupuestoCD = new BigDecimal(0.0);
		BigDecimal totalCostoTotalCD = new BigDecimal(0.0);
		BigDecimal totalPorcentajeAvanceCD = new BigDecimal(0.0);
		BigDecimal totalInversionEjecutadaCD = new BigDecimal(0.0);
		BigDecimal totalInversionPorEjecutarCD = new BigDecimal(0.0);
		for (CronogramaObraDTO cronogramaCD : listCostosDirectos) {
			if (cronogramaCD.getNoDeCapitulo() != null) {
				if (cronogramaCD.getValorPresupuesto() != null) {
					totalValorPresupuestoCD = totalValorPresupuestoCD.add(cronogramaCD.getValorPresupuesto());
				}

				if (cronogramaCD.getCostoTotal() != null) {
					totalCostoTotalCD = totalCostoTotalCD.add(cronogramaCD.getCostoTotal());
				}

				if (cronogramaCD.getPorcentajeDeAvance() != null) {
					totalPorcentajeAvanceCD = totalPorcentajeAvanceCD.add(cronogramaCD.getPorcentajeDeAvance());
					if (opcion.equals("porAvance")) {
						if (Double.parseDouble(cronogramaCD.getPorcentajeDeAvance().toString()) > 100) {
							mensajeGrow("El Porcentaje No puede ser Mayor a 100", avaluoConstructorDTO);
							cronogramaCD.setPorcentajeDeAvance(null);
						} else {
							calcularInversion(cronogramaCD);
						}
					}
				}

				if (cronogramaCD.getInvesionEjecutada() != null) {
					totalInversionEjecutadaCD = totalInversionEjecutadaCD.add(cronogramaCD.getInvesionEjecutada());
				}

				if (cronogramaCD.getInversionPoEjecutar() != null) {
					totalInversionPorEjecutarCD = totalInversionPorEjecutarCD.add(cronogramaCD.getInversionPoEjecutar());
				}

				cronogramaCD.setTipoCosto(1);
			}

			if (cronogramaCD.getNombreCapitulo() != null) {
				if (cronogramaCD.getNombreCapitulo().equals("Total Costos Directos")) {
					cronogramaCD.setValorPresupuesto(totalValorPresupuestoCD);
					cronogramaCD.setCostoTotal(totalCostoTotalCD);
					if (Double.parseDouble(totalInversionEjecutadaCD.toString()) != 0
							&& Double.parseDouble(totalValorPresupuestoCD.toString()) != 0) {
						cronogramaCD.setPorcentajeDeAvance(
								convertirAEnteros(BigDecimal.valueOf((Double.parseDouble(totalInversionEjecutadaCD.toString())
										/ (Double.parseDouble(totalValorPresupuestoCD.toString()))))));
					}
					cronogramaCD.setInvesionEjecutada(totalInversionEjecutadaCD);
					cronogramaCD.setInversionPoEjecutar(totalInversionPorEjecutarCD);
					avaluoConstructorDTO.setCostosDirectos(cronogramaCD.getCostoTotal());
					avaluoConstructorDTO.setPorcentajeCostosDirectos(cronogramaCD.getPorcentajeDeAvance());
				}
			}

		}

		calcularTotal();
		actualizarVistaPanel("form:accordionPanel:pngTableCronObraCI");
		calcularCampoCostoTotal(listCostosDirectos);
		actualizarVistaPanel("form:accordionPanel:pngDistCostos");
	}

	/**
	 * Calcular Costos Tottales.
	 */
	public void calcularCampoCostoTotal(List<CronogramaObraDTO> listCostosGeneral) {
		for (CronogramaObraDTO cronObra : listCostosGeneral) {
			if (cronObra.getValorPresupuesto() != null) {
				if (cronObra.getNoDeCapitulo() != null) {
					cronObra.setCostoTotal(
							convertirAEnteros(BigDecimal.valueOf((Double.parseDouble(cronObra.getValorPresupuesto().toString())
									/ Double.parseDouble(avaluoConstructorDTO.getCostosDelProyecto().toString())))));
					if (cronObra.getPorcentajeDeAvance() != null) {
						cronObra.setPorcentajeDeAvance(convertirAEnteros(
								BigDecimal.valueOf((Double.parseDouble(cronObra.getPorcentajeDeAvance().toString()) / 100))));
					}
				}
			} else {
				cronObra.setCostoTotal(BigDecimal.valueOf(0));
			}
		}
	}

	/**
	 * Calcular las Inversiones segun el Presupuesto.
	 * 
	 * @param cronoDto Objeto
	 */
	public void calcularInversion(CronogramaObraDTO cronoDto) {
		if (cronoDto.getValorPresupuesto() != null) {
			cronoDto.setInvesionEjecutada(
					cronoDto.getValorPresupuesto().multiply(cronoDto.getPorcentajeDeAvance()).divide(BigDecimal.valueOf(100)));
			cronoDto.setInversionPoEjecutar(cronoDto.getValorPresupuesto().subtract(cronoDto.getInvesionEjecutada()));
		}
	}

	/**
	 * Calcula en la tabla Cronograma Obra - Constos Indirectos.
	 */
	public void calcularTotalCostosIndirectos(String opcion) {
		BigDecimal totalValorPresupuestoCI = new BigDecimal(0.0);
		BigDecimal totalCostoTotalCI = new BigDecimal(0.0);
		BigDecimal totalPorcentajeAvanceCI = new BigDecimal(0.0);
		BigDecimal totalInversionEjecutadaCI = new BigDecimal(0.0);
		BigDecimal totalInversionPorEjecutarCI = new BigDecimal(0.0);
		for (CronogramaObraDTO cronogramaCI : listCostosIndirectos) {
			if (cronogramaCI.getNoDeCapitulo() != null) {
				if (cronogramaCI.getValorPresupuesto() != null) {
					totalValorPresupuestoCI = totalValorPresupuestoCI.add(cronogramaCI.getValorPresupuesto());
				}

				if (cronogramaCI.getCostoTotal() != null) {
					totalCostoTotalCI = totalCostoTotalCI.add(cronogramaCI.getCostoTotal());
				}

				if (cronogramaCI.getPorcentajeDeAvance() != null) {
					totalPorcentajeAvanceCI = totalPorcentajeAvanceCI.add(cronogramaCI.getPorcentajeDeAvance());
					if (opcion.equals("porAvance")) {
						calcularInversion(cronogramaCI);
					}
				}

				if (cronogramaCI.getInvesionEjecutada() != null) {
					totalInversionEjecutadaCI = totalInversionEjecutadaCI.add(cronogramaCI.getInvesionEjecutada());
				}

				if (cronogramaCI.getInversionPoEjecutar() != null) {
					totalInversionPorEjecutarCI = totalInversionPorEjecutarCI.add(cronogramaCI.getInversionPoEjecutar());
				}
				cronogramaCI.setTipoCosto(2);
			}

			if (cronogramaCI.getNombreCapitulo().equals("Total Costos Indirectos")) {
				cronogramaCI.setValorPresupuesto(totalValorPresupuestoCI);
				cronogramaCI.setCostoTotal(totalCostoTotalCI);
				cronogramaCI.setPorcentajeDeAvance(
						convertirAEnteros(BigDecimal.valueOf(Double.parseDouble(totalInversionEjecutadaCI.toString())
								/ Double.parseDouble(totalValorPresupuestoCI.toString()))));
				cronogramaCI.setInvesionEjecutada(totalInversionEjecutadaCI);
				cronogramaCI.setInversionPoEjecutar(totalInversionPorEjecutarCI);
				avaluoConstructorDTO.setCostosIndirectos(cronogramaCI.getCostoTotal());
				avaluoConstructorDTO.setPorcentajeCostosIndirectos(cronogramaCI.getPorcentajeDeAvance());
			}
		}

		calcularTotal();
		calcularCampoCostoTotal(listCostosIndirectos);
		calcularTotalCostosDirectos("valorPresupuesto");
		actualizarVistaPanel("form:accordionPanel:pngTableCronObraCD");
		actualizarVistaPanel("form:accordionPanel:pngDistCostos");
	}

	/**
	 * Calculo total de los costos Directos e Indirectos.
	 * 
	 * @param totalCD - Costos Directos
	 * @param totalCI - Costos Indirectos.
	 */
	private void calcularTotal() {
		BigDecimal totalValorPresCD = BigDecimal.valueOf(0);
		BigDecimal totalCostoCD = BigDecimal.valueOf(0);
		BigDecimal totalPorcentajeAvanceCD = BigDecimal.valueOf(0);
		BigDecimal totalInversionEjecutadaCD = BigDecimal.valueOf(0);
		BigDecimal totalInversionPorEjecutarCD = BigDecimal.valueOf(0);
		for (CronogramaObraDTO cronDto : listCostosDirectos) {
			if (cronDto.getNombreCapitulo() != null) {
				if (cronDto.getNombreCapitulo().equals("Total Costos Directos")) {
					if (cronDto.getValorPresupuesto() != null) {
						totalValorPresCD = cronDto.getValorPresupuesto();
						totalCostoCD = cronDto.getCostoTotal();
						totalPorcentajeAvanceCD = cronDto.getPorcentajeDeAvance();
						totalInversionEjecutadaCD = cronDto.getInvesionEjecutada();
						totalInversionPorEjecutarCD = cronDto.getInversionPoEjecutar();
						// Asignar valores para guardado
						avaluoConstructorDTO.setCostosDirectos(cronDto.getValorPresupuesto());
						avaluoConstructorDTO.setPorcentajeCostosDirectos(cronDto.getPorcentajeDeAvance());
						actualizarVistaPanel("form:accordionPanel:pngInfTecnicoConstReform");
					} else {
						totalValorPresCD = BigDecimal.valueOf(0);
						totalCostoCD = BigDecimal.valueOf(0);
						totalPorcentajeAvanceCD = BigDecimal.valueOf(0);
					}
				}
			}
		}

		BigDecimal totalValorPresCI = BigDecimal.valueOf(0);
		BigDecimal totalCostoCI = BigDecimal.valueOf(0);
		BigDecimal totalPorcentajeAvanceCI = BigDecimal.valueOf(0);
		BigDecimal totalInversionEjecutadaCI = BigDecimal.valueOf(0);
		BigDecimal totalInversionPorEjecutarCI = BigDecimal.valueOf(0);
		for (CronogramaObraDTO cronDto : listCostosIndirectos) {
			if (cronDto.getNombreCapitulo() != null) {
				if (cronDto.getNombreCapitulo().equals("Total Costos Indirectos")) {
					if (cronDto.getValorPresupuesto() != null) {
						totalValorPresCI = cronDto.getValorPresupuesto();
						totalCostoCI = cronDto.getCostoTotal();
						totalPorcentajeAvanceCI = cronDto.getPorcentajeDeAvance();
						totalInversionEjecutadaCI = cronDto.getInvesionEjecutada();
						totalInversionPorEjecutarCI = cronDto.getInversionPoEjecutar();
						// Asignar valor para Guardado
						avaluoConstructorDTO.setCostosIndirectos(cronDto.getValorPresupuesto());
						avaluoConstructorDTO.setPorcentajeCostosIndirectos(cronDto.getPorcentajeDeAvance());
						actualizarVistaPanel("form:accordionPanel:pngInfTecnicoConstReform");
					} else {
						totalValorPresCI = BigDecimal.valueOf(0);
						totalCostoCI = BigDecimal.valueOf(0);
						totalPorcentajeAvanceCI = BigDecimal.valueOf(0);
					}
				}

				if (cronDto.getNombreCapitulo().equals("TOTAL INVERSION EN COSTOS")) {
					cronDto.setValorPresupuesto(totalValorPresCD.add(totalValorPresCI));
					cronDto.setCostoTotal(totalCostoCD.add(totalCostoCI));
					cronDto.setInvesionEjecutada(totalInversionEjecutadaCD.add(totalInversionEjecutadaCI));
					cronDto.setInversionPoEjecutar(totalInversionPorEjecutarCD.add(totalInversionPorEjecutarCI));
					cronDto.setPorcentajeDeAvance(
							convertirAEnteros(BigDecimal.valueOf(Double.parseDouble(cronDto.getInvesionEjecutada().toString())
									/ Double.parseDouble(cronDto.getValorPresupuesto().toString()))));
					// Asignar valor para Guardado
					avaluoConstructorDTO.setCostosDelProyecto(cronDto.getValorPresupuesto());
					avaluoConstructorDTO.setPorcentajeDeCostosDelProyecto(cronDto.getPorcentajeDeAvance());
				}
			}
		}
	}

	/**
	 * Lista todos los Capitulos de la Tabla Cronograma Obra
	 */
	private void cronogramaObra(Long idAvaluo) {
		setListCronogramaObraCostosDirectos(new ArrayList<CronogramaObraDTO>());
		setListCronogramaObraCostosIndirectos(new ArrayList<CronogramaObraDTO>());
		setListCostosDirectos(new ArrayList<CronogramaObraDTO>());
		CronogramaObraDTO cronoExistente = cronogramaObraService.encontrarPorIdAvaluo(idAvaluo);
		if (cronoExistente == null) {
			// Si No existe ya un cronograma para este Avaluo
			getListCronogramaObraCostosDirectos().addAll(armarCronogramaObraAvaluoNewCD());
			getListCronogramaObraCostosIndirectos().addAll(armarCronogramaObraAvaluoNewCI());
		} else {
			// Si existe un cronograma para este Avaluo
			getListCronogramaObraCostosDirectos().addAll(cronogramaObraService.encontrarPorTipoCosto(1));
			getListCronogramaObraCostosIndirectos().addAll(cronogramaObraService.encontrarPorTipoCosto(2));
		}

		listCostosDirectos.addAll(getListCronogramaObraCostosDirectos());
		setListCostosIndirectos(new ArrayList<CronogramaObraDTO>());
		listCostosIndirectos.addAll(getListCronogramaObraCostosIndirectos());

		calcularTotalCostosDirectos("valorPresupuesto");
		calcularTotalCostosIndirectos("valorPresupuesto");

		agregarTotalesCronograma();
	}

	/**
	 * Lista el Cronograma Obra a dos List de Costos Directos e Indirectos.
	 */
	private void agregarTotalesCronograma() {
		CronogramaObraDTO cronObraCD = new CronogramaObraDTO();
		cronObraCD.setNombreCapitulo("Total Costos Directos");
		listCostosDirectos.add(cronObraCD);
		CronogramaObraDTO cronObraCI = new CronogramaObraDTO();
		cronObraCI.setNombreCapitulo("Total Costos Indirectos");
		listCostosIndirectos.add(cronObraCI);
		CronogramaObraDTO cronObraCT = new CronogramaObraDTO();
		cronObraCT.setNombreCapitulo("TOTAL INVERSION EN COSTOS");
		listCostosIndirectos.add(cronObraCT);

		convertirAEnterosLista(getListCostosDirectos());
		convertirAEnterosLista(getListCostosIndirectos());
	}

	/**
	 * Arma la lista de Cronograma de Obra Costos Directos para un Avaluo Nuevo.
	 * 
	 * @return Lista de Cronograma Obra Costos Directos.
	 */
	private List<CronogramaObraDTO> armarCronogramaObraAvaluoNewCD() {
		Integer tipoCostoDIRECTO = 1;
		List<CronogramaObraDTO> listaCronogramaBaseCD = new ArrayList<CronogramaObraDTO>();
		listaCronogramaBaseCD.add(new CronogramaObraDTO(1, "Preliminares", BigDecimal.valueOf(0), BigDecimal.valueOf(0),
				BigDecimal.valueOf(0), getFechaActual(), null, tipoCostoDIRECTO, avaluoConstructorDTO.getId()));
		listaCronogramaBaseCD.add(new CronogramaObraDTO(2, "CimentaciÃ³n", BigDecimal.valueOf(0), BigDecimal.valueOf(0),
				BigDecimal.valueOf(0), getFechaActual(), null, tipoCostoDIRECTO, avaluoConstructorDTO.getId()));
		listaCronogramaBaseCD.add(new CronogramaObraDTO(3, "Estructura", BigDecimal.valueOf(0), BigDecimal.valueOf(0),
				BigDecimal.valueOf(0), getFechaActual(), null, tipoCostoDIRECTO, avaluoConstructorDTO.getId()));
		listaCronogramaBaseCD.add(new CronogramaObraDTO(4, "Mamposteria", BigDecimal.valueOf(0), BigDecimal.valueOf(0),
				BigDecimal.valueOf(0), getFechaActual(), null, tipoCostoDIRECTO, avaluoConstructorDTO.getId()));
		listaCronogramaBaseCD
				.add(new CronogramaObraDTO(5, "InstalaciÃ³n HidrÃ¡ulica", BigDecimal.valueOf(0), BigDecimal.valueOf(0),
						BigDecimal.valueOf(0), getFechaActual(), null, tipoCostoDIRECTO, avaluoConstructorDTO.getId()));
		listaCronogramaBaseCD
				.add(new CronogramaObraDTO(6, "InstalaciÃ³n Sanitaria", BigDecimal.valueOf(0), BigDecimal.valueOf(0),
						BigDecimal.valueOf(0), getFechaActual(), null, tipoCostoDIRECTO, avaluoConstructorDTO.getId()));
		listaCronogramaBaseCD.add(new CronogramaObraDTO(7, "InstalaciÃ³n Gas", BigDecimal.valueOf(0), BigDecimal.valueOf(0),
				BigDecimal.valueOf(0), getFechaActual(), null, tipoCostoDIRECTO, avaluoConstructorDTO.getId()));
		listaCronogramaBaseCD
				.add(new CronogramaObraDTO(8, "InstalaciÃ³n ElÃ©ctrica", BigDecimal.valueOf(0), BigDecimal.valueOf(0),
						BigDecimal.valueOf(0), getFechaActual(), null, tipoCostoDIRECTO, avaluoConstructorDTO.getId()));
		listaCronogramaBaseCD.add(new CronogramaObraDTO(9, "PaÃ±ete", BigDecimal.valueOf(0), BigDecimal.valueOf(0),
				BigDecimal.valueOf(0), getFechaActual(), null, tipoCostoDIRECTO, avaluoConstructorDTO.getId()));
		listaCronogramaBaseCD.add(new CronogramaObraDTO(10, "Cubierta", BigDecimal.valueOf(0), BigDecimal.valueOf(0),
				BigDecimal.valueOf(0), getFechaActual(), null, tipoCostoDIRECTO, avaluoConstructorDTO.getId()));
		listaCronogramaBaseCD.add(new CronogramaObraDTO(11, "Ventaneria", BigDecimal.valueOf(0), BigDecimal.valueOf(0),
				BigDecimal.valueOf(0), getFechaActual(), null, tipoCostoDIRECTO, avaluoConstructorDTO.getId()));
		listaCronogramaBaseCD
				.add(new CronogramaObraDTO(12, "Carpinteria Madera", BigDecimal.valueOf(0), BigDecimal.valueOf(0),
						BigDecimal.valueOf(0), getFechaActual(), null, tipoCostoDIRECTO, avaluoConstructorDTO.getId()));
		listaCronogramaBaseCD.add(new CronogramaObraDTO(13, "Pisos", BigDecimal.valueOf(0), BigDecimal.valueOf(0),
				BigDecimal.valueOf(0), getFechaActual(), null, tipoCostoDIRECTO, avaluoConstructorDTO.getId()));
		listaCronogramaBaseCD.add(new CronogramaObraDTO(14, "Enchapes", BigDecimal.valueOf(0), BigDecimal.valueOf(0),
				BigDecimal.valueOf(0), getFechaActual(), null, tipoCostoDIRECTO, avaluoConstructorDTO.getId()));
		listaCronogramaBaseCD
				.add(new CronogramaObraDTO(15, "Aparatos Sanitarios", BigDecimal.valueOf(0), BigDecimal.valueOf(0),
						BigDecimal.valueOf(0), getFechaActual(), null, tipoCostoDIRECTO, avaluoConstructorDTO.getId()));
		listaCronogramaBaseCD.add(new CronogramaObraDTO(16, "Pintura", BigDecimal.valueOf(0), BigDecimal.valueOf(0),
				BigDecimal.valueOf(0), getFechaActual(), null, tipoCostoDIRECTO, avaluoConstructorDTO.getId()));

		return listaCronogramaBaseCD;
	}

	/**
	 * Arma la lista de Cronograma de Obra Costos Indirectos para un Avaluo Nuevo.
	 * 
	 * @return Lista de Cronograma Obra Costos Indirectos.
	 */
	private List<CronogramaObraDTO> armarCronogramaObraAvaluoNewCI() {
		Integer tipoCostoINDIRECTO = 2;
		List<CronogramaObraDTO> listaCronogramaBaseCI = new ArrayList<CronogramaObraDTO>();
		listaCronogramaBaseCI
				.add(new CronogramaObraDTO(1, "Honorarios de DiseÃ±o", BigDecimal.valueOf(0), BigDecimal.valueOf(0),
						BigDecimal.valueOf(0), getFechaActual(), null, tipoCostoINDIRECTO, avaluoConstructorDTO.getId()));
		listaCronogramaBaseCI
				.add(new CronogramaObraDTO(2, "Honorarios de Calculos", BigDecimal.valueOf(0), BigDecimal.valueOf(0),
						BigDecimal.valueOf(0), getFechaActual(), null, tipoCostoINDIRECTO, avaluoConstructorDTO.getId()));
		listaCronogramaBaseCI
				.add(new CronogramaObraDTO(3, "Calculos Estructurales", BigDecimal.valueOf(0), BigDecimal.valueOf(0),
						BigDecimal.valueOf(0), getFechaActual(), null, tipoCostoINDIRECTO, avaluoConstructorDTO.getId()));
		listaCronogramaBaseCI.add(new CronogramaObraDTO(4, "Licencias", BigDecimal.valueOf(0), BigDecimal.valueOf(0),
				BigDecimal.valueOf(0), getFechaActual(), null, tipoCostoINDIRECTO, avaluoConstructorDTO.getId()));

		return listaCronogramaBaseCI;
	}

	/**
	 * Agrega un costo Directo Nuevo.
	 */
	public void agregarCostosDirectos() {
		if (validarCamposCronograma(newCronObraCD)) {
			List<CronogramaObraDTO> listCostosDirectosAdd = new ArrayList<CronogramaObraDTO>();
			// newCronObraCD.setCostoTotal(newCronObraCD.getCostoTotal().divide(BigDecimal.valueOf(100)));
			listCostosDirectosAdd.add(newCronObraCD);
			listCronogramaObraCostosDirectos.addAll(listCostosDirectosAdd);
			setListCostosDirectos(new ArrayList<CronogramaObraDTO>());
			getListCostosDirectos().addAll(listCronogramaObraCostosDirectos);
			setListCostosIndirectos(new ArrayList<CronogramaObraDTO>());
			getListCostosIndirectos().addAll(listCronogramaObraCostosIndirectos);
			agregarTotalesCronograma();
			calcularTotalCostosDirectos("valorPresupuesto");
			newCronObraCD = new CronogramaObraDTO();
			traerId(listCronogramaObraCostosDirectos, "directo");
		}
	}

	/**
	 * Obtener el Id Final de la Lista
	 * 
	 * @return Id
	 */
	public void traerId(List<CronogramaObraDTO> listCronObra, String opt) {
		Integer id = 0;
		for (CronogramaObraDTO cron : listCronObra) {
			id = cron.getNoDeCapitulo();
		}
		id = id + 1;

		if (opt.equals("directo")) {
			newCronObraCD.setNoDeCapitulo(id);
		} else if (opt.equals("indirecto")) {
			newCronObraCI.setNoDeCapitulo(id);
		}
	}

	/**
	 * ValidaciÃ³n de Campos.
	 * 
	 * @param obraDTO Objeto
	 * @return ConfirmaciÃ³n de validaciÃ³n.
	 */
	private Boolean validarCamposCronograma(CronogramaObraDTO obraDTO) {
		Boolean validacionOk = true;
		String mensaje = "Campos Vacios: ";
		if (obraDTO.getNombreCapitulo() == null) {
			mensaje += " [Nombre de Capitulo]";
			validacionOk = false;
		}

		/*
		 * if (obraDTO.getCostoTotal() == null) { mensaje += " [Costo Total]";
		 * validacionOk = false; }
		 */

		if (!validacionOk) {
			mensajeGrow(mensaje, null);
		}

		return validacionOk;
	}

	/**
	 * Eliminar Costo Directo de la Lista.
	 */
	public void eliminarCostoDirecto(CronogramaObraDTO costoDirectoEliminar) {
		getListCostosDirectos().remove(costoDirectoEliminar);
		calcularTotalCostosDirectos("valorPresupuesto");
		traerId(listCronogramaObraCostosDirectos, "directo");
	}

	/**
	 * Agrega un costo Indirecto Nuevo.
	 */
	public void agregarCostosIndirectos() {
		if (validarCamposCronograma(newCronObraCI)) {
			List<CronogramaObraDTO> listCostosIndirectosAdd = new ArrayList<CronogramaObraDTO>();
			// newCronObraCI.setCostoTotal(newCronObraCI.getCostoTotal().divide(BigDecimal.valueOf(100)));
			listCostosIndirectosAdd.add(newCronObraCI);
			listCronogramaObraCostosIndirectos.addAll(listCostosIndirectosAdd);
			setListCostosIndirectos(new ArrayList<CronogramaObraDTO>());
			getListCostosIndirectos().addAll(listCronogramaObraCostosIndirectos);
			setListCostosDirectos(new ArrayList<CronogramaObraDTO>());
			getListCostosDirectos().addAll(listCronogramaObraCostosDirectos);
			agregarTotalesCronograma();
			calcularTotalCostosIndirectos("valorPresupuesto");
			newCronObraCI = new CronogramaObraDTO();
			traerId(listCronogramaObraCostosIndirectos, "indirecto");
		}
	}

	/**
	 * Eliminar Costo Indirecto de la Lista.
	 */
	public void eliminarCostoIndirecto(CronogramaObraDTO costoIndirectoEliminar) {
		getListCostosIndirectos().remove(costoIndirectoEliminar);
		calcularTotalCostosIndirectos("valorPresupuesto");
		traerId(listCronogramaObraCostosIndirectos, "indirecto");
	}

	/**
	 * Convierte a Enteros la Lista los decimales para mostrar como porcentaje Ejm:
	 * 0.3 = 30%.
	 * 
	 * @param listCronogramaObra Lista
	 * @return Lista modificada.
	 */
	private void convertirAEnterosLista(List<CronogramaObraDTO> listCronogramaObra) {
		for (CronogramaObraDTO cronogramaObraDTO : listCronogramaObra) {
			if (cronogramaObraDTO.getCostoTotal() != null
					&& (Double.parseDouble(cronogramaObraDTO.getCostoTotal().toString()) <= 1)) {
				cronogramaObraDTO.setCostoTotal(convertirAEnteros(cronogramaObraDTO.getCostoTotal()));
			}
		}
	}

	/**
	 * Convertir a Entero un Ojeto.
	 * 
	 * @param cron Objeto
	 */
	private BigDecimal convertirAEnteros(BigDecimal decimal) {
		if (decimal != null && (Double.parseDouble(decimal.toString()) <= 1)) {
			// ConversiÃ³n de decimales.
			Double porcentaje = decimal.doubleValue() * 100;
			double por = Math.round(porcentaje * Math.pow(10, 2)) / Math.pow(10, 2);
			decimal = BigDecimal.valueOf(por);
		}
		return decimal;
	}

	/**
	 * ValidaciÃ³n si ya existe un Capitulo.
	 * 
	 * @param listaCronogramaVal Lista a Validar
	 */
	public void validarCapituloRepetidos(String opcion) {
		List<CronogramaObraDTO> listaAValidar = new ArrayList<CronogramaObraDTO>();
		CronogramaObraDTO cronObra = new CronogramaObraDTO();
		if (opcion.equals("directo")) {
			listaAValidar = listCostosDirectos;
			cronObra = newCronObraCD;
		} else if (opcion.equals("indirecto")) {
			listaAValidar = listCostosIndirectos;
			cronObra = newCronObraCI;
		}

		for (CronogramaObraDTO cronDto : listaAValidar) {
			if (cronDto.getNombreCapitulo().trim().equals(cronObra.getNombreCapitulo().trim())) {
				mensajeGrow("El Nombre Capitulo: " + cronDto.getNombreCapitulo() + " Ya Existe", null);
				cronObra.setNombreCapitulo(null);
				break;
			}
		}
	}

	/**
	 * --------------------------------- CONCEPTOS FINALES
	 * ---------------------------------------------------------------
	 */

	/**
	 * Calcula el valor Total del Area.
	 * 
	 * @param conceptosFinalesDTO Parametros.
	 */
	public void calcularvalorAreaTotal() {
		if (areas.getArea() != null && areas.getValorUnitario() != null) {
			areas.setValorTotal(areas.getArea().multiply(areas.getValorUnitario()));
			valorTotalAvaluo();
		}
	}

	private void valorTotalAvaluo() {
		valorInmuebleAntesReforma = BigDecimal.valueOf(0);
		for (AreaDTO areaDTO : listAreas) {
			if (areaDTO.getValorTotal() != null) {
				valorInmuebleAntesReforma = valorInmuebleAntesReforma.add(areaDTO.getValorTotal());
			}
		}
		valorTotalAvaluo = valorInmuebleAntesReforma;
		valorTotalGarantia = valorInmuebleAntesReforma.intValue();
		valorAvaluoUvr = valorTotalAvaluo.divideToIntegralValue(valorUvrDia);
	}

	/**
	 * Calculo de los Costos Financieros.
	 */
	public void costosFinancieros() {
		Double valorSolicitado, factor;
		Integer programacionMes;
		if (avaluoConstructorDTO.getValorSolicitado() != null) {
			valorSolicitado = Double.parseDouble(avaluoConstructorDTO.getValorSolicitado().toString());
			try {
				avaluoConstructorDTO.setValorMaxAFinanciar(BigDecimal
						.valueOf(valorSolicitado / Double.parseDouble(avaluoConstructorDTO.getPorcentajeAFinanciar().toString())));
			} catch (Exception ex) {
				avaluoConstructorDTO.setValorMaxAFinanciar(BigDecimal.valueOf(0.0));
			}
		} else {
			valorSolicitado = 0.0;
		}

		if (avaluoConstructorDTO.getPorcentajeAFinanciar() != null) {
			try {
				avaluoConstructorDTO.setValorMaxAFinanciar(BigDecimal
						.valueOf(valorSolicitado / Double.parseDouble(avaluoConstructorDTO.getPorcentajeAFinanciar().toString())));
			} catch (Exception ex) {
				avaluoConstructorDTO.setValorMaxAFinanciar(BigDecimal.valueOf(0.0));
			}
		} else {
			avaluoConstructorDTO.setValorMaxAFinanciar(BigDecimal.valueOf(0.0));
		}

		if (avaluoConstructorDTO.getFactor() != null) {
			factor = Double.parseDouble(avaluoConstructorDTO.getFactor().toString());
		} else {
			factor = 0.0;
		}

		if (avaluoConstructorDTO.getProgramacionEnMeses() != null) {
			programacionMes = avaluoConstructorDTO.getProgramacionEnMeses();
		} else {
			programacionMes = 0;
		}

		avaluoConstructorDTO
				.setCostosFinancieros(BigDecimal.valueOf(valorSolicitado * (factor / 100) * (programacionMes / 2)));

		calculoValorTotalProyecto();
	}

	private void calculoValorTotalProyecto() {
		if (avaluoConstructorDTO.getCostosDelProyecto() == null) {
			avaluoConstructorDTO.setCostosDelProyecto(BigDecimal.valueOf(0));
		}
		if (avaluoConstructorDTO.getCostosFinancieros() == null) {
			avaluoConstructorDTO.setCostosFinancieros(BigDecimal.valueOf(0));
		}

		try {
			avaluoConstructorDTO.setCostosTotalDelProyecto(
					avaluoConstructorDTO.getCostosDelProyecto().add(avaluoConstructorDTO.getCostosFinancieros()));

			avaluoConstructorDTO.setPorcentajeFinanciado(convertirAEnteros(
					BigDecimal.valueOf(Double.parseDouble(avaluoConstructorDTO.getCostosTotalDelProyecto().toString())
							/ Double.parseDouble(avaluoConstructorDTO.getValorSolicitado().toString()))));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Adicionar Area en el momento de diligenciar Informe Técnico Para
	 * Construcciones y Reformas.
	 */
	public void adicionarArea() {
		areas = new AreaDTO();
		areas.setNombre("Lote");
		areas.setArea(avaluoConstructorDTO.getLoteProyectoM2());
		areas.setValorUnitario(avaluoConstructorDTO.getValorLote());
		areas.setValorTotal(avaluoConstructorDTO.getLoteProyectoM2().multiply(avaluoConstructorDTO.getValorLote()));
		agregarArea();
		actualizarVistaPanel("form:accordionPanel:dtbConceptosFinales");
		actualizarVistaPanel("form:accordionPanel:pngValorTotalAvaluoUvr");
	}

	/**
	 * Agregar un Area.
	 */
	public void agregarArea() {
		if (validarCamposArea(areas)) {
			listAreas.add(areas);
			// Limpiamos el Objeto Agregado
			areas = new AreaDTO();
			valorTotalAvaluo();
		}
	}

	/**
	 * Valida los campos del Area.
	 * 
	 * @param areaDTO Objeto
	 * @return ConfirmaciÃ³n de validaciÃ³n.
	 */
	private Boolean validarCamposArea(AreaDTO areaDTO) {
		Boolean validacionOk = true;
		String mensaje = "Campos Vacios: ";
		if (areas.getNombre() == null) {
			mensaje += " [Nombre]";
			validacionOk = false;
		}

		if (areas.getArea() == null) {
			mensaje += " [Ã�rea]";
			validacionOk = false;
		}

		if (areas.getValorUnitario() == null) {
			mensaje += " [Valor Unitario]";
			validacionOk = false;
		}

		if (areas.getValorTotal() == null) {
			mensaje += " [Valor Total]";
			validacionOk = false;
		}

		if (!validacionOk) {
			mensajeGrow(mensaje, null);
		}

		return validacionOk;
	}

	/**
	 * Eliminar un Area.
	 */
	public void eliminarArea(AreaDTO area) {
		listAreas.remove(area);
		valorTotalAvaluo();
		actualizarVistaPanel("form:accordionPanel:pngValorTotalAvaluoUvr");
	}

	/**
	 * ------------------------------------------------------------------------
	 * ---------------------------------------------
	 * ------------------------------------------ Getter and Setter
	 * -------------------------------------------------------
	 */
	public List<CronogramaObraDTO> getListCronogramaObraCostosDirectos() {
		return listCronogramaObraCostosDirectos;
	}

	public void setListCronogramaObraCostosDirectos(List<CronogramaObraDTO> listCronogramaObraCostosDirectos) {
		this.listCronogramaObraCostosDirectos = listCronogramaObraCostosDirectos;
	}

	public List<CronogramaObraDTO> getListCronogramaObraCostosIndirectos() {
		return listCronogramaObraCostosIndirectos;
	}

	public void setListCronogramaObraCostosIndirectos(List<CronogramaObraDTO> listCronogramaObraCostosIndirectos) {
		this.listCronogramaObraCostosIndirectos = listCronogramaObraCostosIndirectos;
	}

	public List<TipoAvaluoDTO> getListTipoAvaluo() {
		return listTipoAvaluo;
	}

	public void setListTipoAvaluo(List<TipoAvaluoDTO> listTipoAvaluo) {
		this.listTipoAvaluo = listTipoAvaluo;
	}

	public AvaluoConstructorDTO getAvaluoConstructorDTO() {
		return avaluoConstructorDTO;
	}

	public void setAvaluoConstructorDTO(AvaluoConstructorDTO avaluoConstructorDTO) {
		this.avaluoConstructorDTO = avaluoConstructorDTO;
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

	public Long getIdTipoAvaluo() {
		return idTipoAvaluo;
	}

	public void setIdTipoAvaluo(Long idTipoAvaluo) {
		this.idTipoAvaluo = idTipoAvaluo;
	}

	public SortedMap<String, String> getListPaisesOrg() {
		return listPaisesOrg;
	}

	public void setListPaisesOrg(SortedMap<String, String> listPaises) {
		this.listPaisesOrg = listPaises;
	}

	public SortedMap<String, String> getListDepartamentosOrg() {
		return listDepartamentosOrg;
	}

	public void setListDepartamentosOrg(SortedMap<String, String> listDepartamentos) {
		this.listDepartamentosOrg = listDepartamentos;
	}

	public List<DivipolaDTO> getListCiudadesOrg() {
		return listCiudadesOrg;
	}

	public void setListCiudadesOrg(List<DivipolaDTO> listCiudades) {
		this.listCiudadesOrg = listCiudades;
	}

	public List<PropietarioDTO> getListPropietarios() {
		return listPropietarios;
	}

	public void setListPropietarios(List<PropietarioDTO> listPropietarios) {
		this.listPropietarios = listPropietarios;
	}

	public PropietarioDTO getPropietario() {
		return propietario;
	}

	public void setPropietario(PropietarioDTO propietario) {
		this.propietario = propietario;
	}

	public List<AvaluoDTO> getAvaluosAnteriores() {
		return avaluosAnteriores;
	}

	public void setAvaluosAnteriores(List<AvaluoDTO> avaluosAnteriores) {
		this.avaluosAnteriores = avaluosAnteriores;
	}

	public List<String> getListComplementoViaSolicitante() {
		return listComplementoViaSolicitante;
	}

	public void setListComplementoViaSolicitante(List<String> complementoViaSolicitante) {
		this.listComplementoViaSolicitante = complementoViaSolicitante;
	}

	public List<String> getListComplementoViaGeneradoraSolicitante() {
		return listComplementoViaGeneradoraSolicitante;
	}

	public void setListComplementoViaGeneradoraSolicitante(List<String> complementoViaGeneradoraSolicitante) {
		this.listComplementoViaGeneradoraSolicitante = complementoViaGeneradoraSolicitante;
	}

	public List<EntidadDTO> getListEntidades() {
		return listEntidades;
	}

	public void setListEntidades(List<EntidadDTO> listEntidades) {
		this.listEntidades = listEntidades;
	}

	public List<DivipolaDTO> getListCiudadesPre() {
		return listCiudadesPre;
	}

	public void setListCiudadesPre(List<DivipolaDTO> listCiudadesPre) {
		this.listCiudadesPre = listCiudadesPre;
	}

	public List<DivipolaDTO> getListCiudadesPro() {
		return listCiudadesPro;
	}

	public void setListCiudadesPro(List<DivipolaDTO> listCiudadesPro) {
		this.listCiudadesPro = listCiudadesPro;
	}

	public SortedMap<String, String> getListDepartamentosPre() {
		return listDepartamentosPre;
	}

	public void setListDepartamentosPre(SortedMap<String, String> listDepartamentosPre) {
		this.listDepartamentosPre = listDepartamentosPre;
	}

	public SortedMap<String, String> getListDepartamentosPro() {
		return listDepartamentosPro;
	}

	public void setListDepartamentosPro(SortedMap<String, String> listDepartamentosPro) {
		this.listDepartamentosPro = listDepartamentosPro;
	}

	public SortedMap<String, String> getListPaisesPre() {
		return listPaisesPre;
	}

	public void setListPaisesPre(SortedMap<String, String> listPaisesPre) {
		this.listPaisesPre = listPaisesPre;
	}

	public List<CronogramaObraDTO> getListCostosDirectos() {
		return listCostosDirectos;
	}

	public void setListCostosDirectos(List<CronogramaObraDTO> listCostosDirectos) {
		this.listCostosDirectos = listCostosDirectos;
	}

	public List<CronogramaObraDTO> getListCostosIndirectos() {
		return listCostosIndirectos;
	}

	public void setListCostosIndirectos(List<CronogramaObraDTO> listCostosIndirectos) {
		this.listCostosIndirectos = listCostosIndirectos;
	}

	public Date getFechaActual() {
		return fechaActual;
	}

	public void setFechaActual(Date fechaActual) {
		this.fechaActual = fechaActual;
	}

	public String getCentroMapa() {
		return centroMapa;
	}

	public void setCentroMapa(String centroMapa) {
		this.centroMapa = centroMapa;
	}

	public InmuebleDTO getInmuebleDTO() {
		return inmuebleDTO;
	}

	public void setInmuebleDTO(InmuebleDTO inmuebleDTO) {
		this.inmuebleDTO = inmuebleDTO;
	}

	public BigDecimal getValorAvaluoUvr() {
		return valorAvaluoUvr;
	}

	public void setValorAvaluoUvr(BigDecimal valorAvaluoUvr) {
		this.valorAvaluoUvr = valorAvaluoUvr;
	}

	public BigDecimal getValorUvrDia() {
		return valorUvrDia;
	}

	public void setValorUvrDia(BigDecimal valorUvrDia) {
		this.valorUvrDia = valorUvrDia;
	}

	public BigDecimal getValorTotalAvaluo() {
		return valorTotalAvaluo;
	}

	public void setValorTotalAvaluo(BigDecimal valorTotalAvaluo) {
		this.valorTotalAvaluo = valorTotalAvaluo;
	}

	public BigDecimal getValorInmuebleAntesReforma() {
		return valorInmuebleAntesReforma;
	}

	public void setValorInmuebleAntesReforma(BigDecimal valorInmuebleAntesReforma) {
		this.valorInmuebleAntesReforma = valorInmuebleAntesReforma;
	}

	public Integer getValorTotalGarantia() {
		return valorTotalGarantia;
	}

	public void setValorTotalGarantia(Integer valorTotalGarantia) {
		this.valorTotalGarantia = valorTotalGarantia;
	}

	public Set<AreaDTO> getAreasGarajes() {
		return areasGarajes;
	}

	public void setAreasGarajes(Set<AreaDTO> areasGarajes) {
		this.areasGarajes = areasGarajes;
	}

	public List<AreaDTO> getListaAreas() {
		return listaAreas;
	}

	public void setListaAreas(List<AreaDTO> listaAreas) {
		this.listaAreas = listaAreas;
	}

	public List<AreaDTO> getListAreas() {
		return listAreas;
	}

	public void setListAreas(List<AreaDTO> listAreas) {
		this.listAreas = listAreas;
	}

	public AreaDTO getAreas() {
		return areas;
	}

	public void setAreas(AreaDTO areas) {
		this.areas = areas;
	}

	public CronogramaObraDTO getNewCronObraCD() {
		return newCronObraCD;
	}

	public void setNewCronObraCD(CronogramaObraDTO newCronObraCD) {
		this.newCronObraCD = newCronObraCD;
	}

	public Boolean getAddCostoD() {
		return addCostoD;
	}

	public void setAddCostoD(Boolean addCostoD) {
		this.addCostoD = addCostoD;
	}

	public Boolean getAddCostoI() {
		return addCostoI;
	}

	public void setAddCostoI(Boolean addCostoI) {
		this.addCostoI = addCostoI;
	}

	public CronogramaObraDTO getNewCronObraCI() {
		return newCronObraCI;
	}

	public void setNewCronObraCI(CronogramaObraDTO newCronObraCI) {
		this.newCronObraCI = newCronObraCI;
	}

	public Integer[] getNumerosGarajes() {
		return numerosGarajes;
	}

	public Boolean getAddConFin() {
		return addConFin;
	}

	public void setAddConFin(Boolean addConFin) {
		this.addConFin = addConFin;
	}

	public String getTipoViaSol() {
		return tipoViaSol;
	}

	public void setTipoViaSol(String tipoViaSol) {
		this.tipoViaSol = tipoViaSol;
	}

	public String getNumeroViaSol() {
		return numeroViaSol;
	}

	public void setNumeroViaSol(String numeroViaSol) {
		this.numeroViaSol = numeroViaSol;
	}

	public String getComplementoViaSol() {
		return complementoViaSol;
	}

	public void setComplementoViaSol(String complementoViaSol) {
		this.complementoViaSol = complementoViaSol;
	}

	public String getNumeroViaGeneradoraSol() {
		return numeroViaGeneradoraSol;
	}

	public void setNumeroViaGeneradoraSol(String numeroViaGeneradoraSol) {
		this.numeroViaGeneradoraSol = numeroViaGeneradoraSol;
	}

	public String getComplementoViaGeneradoraSol() {
		return complementoViaGeneradoraSol;
	}

	public void setComplementoViaGeneradoraSol(String complementoViaGeneradoraSol) {
		this.complementoViaGeneradoraSol = complementoViaGeneradoraSol;
	}

	public String getPlacaSol() {
		return placaSol;
	}

	public void setPlacaSol(String placaSol) {
		this.placaSol = placaSol;
	}

	public String getComplementoPlacaSol() {
		return complementoPlacaSol;
	}

	public void setComplementoPlacaSol(String complementoPlacaSol) {
		this.complementoPlacaSol = complementoPlacaSol;
	}

	public String getAdicionalDireccionSol() {
		return adicionalDireccionSol;
	}

	public void setAdicionalDireccionSol(String adicionalDireccionSol) {
		this.adicionalDireccionSol = adicionalDireccionSol;
	}

	public Set<Integer> getDocumentosConsultados() {
		return documentosConsultados;
	}

	public void setDocumentosConsultados(Set<Integer> documentosConsultados) {
		this.documentosConsultados = documentosConsultados;
		avaluoConstructorDTO.setDocumentosConsultados(documentosConsultados.toString().replace("[", "").replace("]", ""));
	}

	public boolean isOtrosDocumentos() {
		return otrosDocumentos;
	}

	public void setOtrosDocumentos(boolean otrosDocumentos) {
		this.otrosDocumentos = otrosDocumentos;
	}

	public Boolean getUsuRevisor() {
		return usuRevisor;
	}

	public void setUsuRevisor(Boolean usuRevisor) {
		this.usuRevisor = usuRevisor;
	}

	public String getOpcionRegIrreg() {
		return opcionRegIrreg;
	}

	public void setOpcionRegIrreg(String opcionRegIrreg) {
		this.opcionRegIrreg = opcionRegIrreg;
	}

	public MapModel getMapModel() {
		return mapModel;
	}

	public void setMapModel(MapModel mapModel) {
		this.mapModel = mapModel;
	}

	public List<SegmentoDTO> getListSegmentos() {
		return listSegmentos;
	}

	public void setListSegmentos(List<SegmentoDTO> listSegmentos) {
		this.listSegmentos = listSegmentos;
	}

	public List<CaracteristicasFisicasDTO> getListCaracteristicasFisicas() {
		return listCaracteristicasFisicas;
	}

	public void setListCaracteristicasFisicas(List<CaracteristicasFisicasDTO> listCaracteristicasFisicas) {
		this.listCaracteristicasFisicas = listCaracteristicasFisicas;
	}

	public Boolean getAddTipoInmueble() {
		return addTipoInmueble;
	}

	public void setAddTipoInmueble(Boolean addTipoInmueble) {
		this.addTipoInmueble = addTipoInmueble;
	}

	public CaracteristicasFisicasDTO getCaracteristicasFisicasDTO() {
		return caracteristicasFisicasDTO;
	}

	public void setCaracteristicasFisicasDTO(CaracteristicasFisicasDTO caracteristicasFisicasDTO) {
		this.caracteristicasFisicasDTO = caracteristicasFisicasDTO;
	}

	public Boolean getAmenzaRiesgo() {
		return amenzaRiesgo;
	}

	public void setAmenzaRiesgo(Boolean amenzaRiesgo) {
		this.amenzaRiesgo = amenzaRiesgo;
	}

	public List<AreaDTO> getListDistribucionCostos() {
		return listDistribucionCostos;
	}

	public void setListDistribucionCostos(List<AreaDTO> listDistribucionCostos) {
		this.listDistribucionCostos = listDistribucionCostos;
	}

}
