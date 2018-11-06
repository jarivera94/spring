package com.helio4.bancol.avaluos.controlador;

import static com.helio4.bancol.avaluos.servicio.util.Constantes.OBJETO_AVALUO_ORIGINACION;
import static com.helio4.bancol.avaluos.servicio.util.Constantes.TIPO_AVALUO_HIPOTECARIO;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.PersistenceException;

import org.primefaces.context.RequestContext;
import org.primefaces.event.TabChangeEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dominio.AvaluoController;
import com.helio4.bancol.avaluos.dominio.ListasController;
import com.helio4.bancol.avaluos.dominio.ListasGeograficasController;
import com.helio4.bancol.avaluos.dominio.SolicitudUtils;
import com.helio4.bancol.avaluos.dominio.TiposAvaluoController;
import com.helio4.bancol.avaluos.dominio.UsuarioController;
import com.helio4.bancol.avaluos.dto.AsesorDTO;
import com.helio4.bancol.avaluos.dto.AvaluoDTO;
import com.helio4.bancol.avaluos.dto.AvaluoRemateDTO;
import com.helio4.bancol.avaluos.dto.ClienteDTO;
import com.helio4.bancol.avaluos.dto.DivipolaDTO;
import com.helio4.bancol.avaluos.dto.EntidadDTO;
import com.helio4.bancol.avaluos.dto.MotivoDTO;
import com.helio4.bancol.avaluos.dto.ObjetoAvaluoDTO;
import com.helio4.bancol.avaluos.dto.SegmentoDTO;
import com.helio4.bancol.avaluos.dto.SucursalDTO;
import com.helio4.bancol.avaluos.dto.TipoAvaluoDTO;
import com.helio4.bancol.avaluos.dto.TipoInmuebleDTO;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;
import com.helio4.bancol.avaluos.exception.GeneralFacesMessageException;
import com.helio4.bancol.avaluos.servicio.datos.EntidadService;
import com.helio4.bancol.avaluos.servicio.datos.MotivoService;
import com.helio4.bancol.avaluos.servicio.util.Constantes;

@Controller
@Scope("view")
@Qualifier("solicitudBean")
public class SolicitudBean implements Serializable {

	private static Logger log = LoggerFactory.getLogger(SolicitudBean.class);

	/**
	 * Miembros de la clase
	 */
	private static final long serialVersionUID = 1L;
	private AvaluoDTO avaluoDTO;
	private AsesorDTO asesorDTO;
	private SucursalDTO sucursalDTO;
	private String departamentoInmueble;
	private String departamentoCliente;
	private SortedMap<String, String> departamentos;
	private List<DivipolaDTO> ciudadesSolicitante;
	private List<MotivoDTO> motivosEntidad;
	private List<DivipolaDTO> ciudadesInmueble;
	private SortedMap<String, String> paises;
	private List<EntidadDTO> entidades;
	private List<TipoAvaluoDTO> tiposAvaluo;
	private List<ObjetoAvaluoDTO> objetosAvaluo;
	private List<TipoInmuebleDTO> tiposInmueble;
	private List<SegmentoDTO> segmentos;
	private List<SucursalDTO> sucursales;

	@Autowired
	private AvaluoController avaluoController;
	@Autowired
	private TiposAvaluoController tiposAvaluoController;
	@Autowired
	private UsuarioController usuarioController;
	@Autowired
	private ListasController listasController;
	@Autowired
	private ListasGeograficasController listasGeograficasController;
	@Autowired
	private ListadoAvaluosBean listadoAvaluosBean;
	@Autowired
	private EntidadService entidadService;

	@Autowired
	private MotivoService motivoService;

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

	private boolean crearSolicitud;
	private boolean entidadBancolombia;
	private boolean mostrarRepetirDatosCliente;
	private boolean mostrarMotivoMenu;
	private boolean repetirDatosCliente;
	private boolean repetirDireccionCliente;
	private boolean tipoAvaluoRemate;

	private boolean ocultarBotonGuardar;
	private String codigoExternoOriginal;
	private Long entidadOriginalId;

	private UsuarioDTO usuario;

	@Autowired
	private SolicitudUtils solicitudUtils;

	private List<AvaluoDTO> avaluosAnteriores;
	private Set<AvaluoDTO> avaluosDireccion;
	private AvaluoDTO avaluoCambioGarantia;

	private final String[] letrasComplemento = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
			"O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

	private final String[] puntosCardinales = { "Norte", "Sur", "Este", "Oeste", "Bis", "Noreste", "Sureste",
			"Noroeste", "Suroeste", "Par", "Impar" };

	private final String[] valoresUnidadAlterno = { "Manzana", "Bloque", "Interior", "Edificio", "Torre", "Apartamento",
			"Casa", "Oficina", "Bodega", "Suite", "Local", "Garaje", "Consultorio", "Deposito", "Etapa", "Piso",
			"Nivel", "Agrupacion", "Unidad", "Tipo", "Sector", "Lote", "Superlote", "Conjunto", "Vivienda", "Módulo" };

	private Boolean errorDireccionInmueble;

	/**
	 * Inicializa el bean de solicitud
	 */
	@PostConstruct
	public void init() {
		log.debug("Inicializando SolicitudBean: ");
		// avaluoDTO = listadoAvaluosBean.getAvaluo();
		this.avaluoDTO = new AvaluoDTO();
		this.asesorDTO = new AsesorDTO();
		ocultarBotonGuardar = false;
		entidadBancolombia = false;
		usuario = (UsuarioDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	private void redireccionarAvisoPermisos() {
		String uri = FacesContext.getCurrentInstance().getExternalContext()
				.encodeActionURL(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()
						+ "/pages/solicitudes/error_permiso.html");
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(uri);
		} catch (IOException e) {
			// TODO Reireccionar a página no encontrada
			e.printStackTrace();
		}
		
		
	}

	/**
	 * Carga la solicitud si se inicia la pagina en modo de edicion de otra
	 * forma carga la pagina en modo de nueva solicitud
	 */
	public void cargarSolicitud() {
		/** Ingresa a editar una solicitud */
		if (avaluoDTO.getId() != null) {
			crearSolicitud = false;
			avaluoDTO = avaluoController.encontrarPorId(avaluoDTO.getId());
			/**
			 * Verificar que si el usuario tiene rol creados haya creado este
			 * caso
			 */
			if (listadoAvaluosBean.isCrearSolicitudes() && !listadoAvaluosBean.isEditarSolicitud()) {
				// Redirigir a un aviso de falta de permisos
				redireccionarAvisoPermisos();
			}
			codigoExternoOriginal = avaluoDTO.getCodigoExterno();
			entidadOriginalId = new Long(avaluoDTO.getEntidad().getId());
			entidadBancolombia = solicitudUtils.comprobarEntidadBancolombia(avaluoDTO.getEntidad());
			cargarSegmentosEntidad();
			cargarMotivos();
			cargarDepartamentoInmueble();
			if (!this.avaluoDTO.isSoloAvaluo()) {
				if (!avaluoDTO.getClass().equals(AvaluoRemateDTO.class)) {
					this.loadDepartamentoSolicitante();
					separarDireccionCliente(avaluoDTO.getCliente());
					asesorDTO = this.avaluoController.encontrarAsesor(this.avaluoDTO.getCorreoElectronicoAsesor());
					if (this.avaluoDTO.getCorreoElectronicoAsesor() == null
							|| this.avaluoDTO.getCorreoElectronicoAsesor().isEmpty()) {
						cargarAsesorSinCorreo();
					}
					tipoAvaluoRemate = false;
					if (asesorDTO != null) {
						this.autocompletarSucursal(asesorDTO.getSucursal().getNombre());
					}
				} else {
					tipoAvaluoRemate = true;
				}
			}
			/** Nueva solicitud */
		} else {
			crearSolicitud = true;
			iniciarSolicitud(); // inicializar listas de parametros desde la
								// base de datos
			ciudadesSolicitante = new ArrayList<DivipolaDTO>(); // Inicializar
																// lista de
																// paises
			motivosEntidad = new ArrayList<>();
			mostrarMotivoMenu = true;
			ciudadesInmueble = new ArrayList<DivipolaDTO>();
		}
		inicializarListas();
	}

	/**
	 * Inicializa las listas usadas en la pagina {@link avaluo.xhtml}
	 * <ul>
	 * <li>{@link #avaluosAnteriores}: Que contiene los avaluos anteriores del
	 * cliente se inicializa una lista vacia.
	 * <li>{@link #entidades}: Contiene las entidades a las que tiene acceso el
	 * {@link #usuario} activo, si el {@link #usuario} no tiene ninguna entidad
	 * se inicializa con la lista de todas las entidades.
	 * <li>{@link #tiposInmueble}: Contiene los tipos de inmueble estos se
	 * cargan desde la base de datos.
	 * <li>{@link #tiposAvaluo}: Contiene los tipos de avaluo a los que tiene
	 * acceso el {@link #usuario} activo. Si el {@link #usuario} no tiene
	 * ninguno se cargan todos los tipos de avaluo desde la base de datos.
	 * <li>{@link #objetosAvaluo}: Contiene todos los objetos del avaluo a los
	 * que tiene acceso el {@link #usuario} dependiendo de su lista de permisos.
	 * <li>{@link #departamentos}: contiene todos los departamentos según
	 * Divipola.
	 * <li>{@link #paises}: contiene todos los paises segun <a href=
	 * "https://github.com/umpirsky/country-list">https://github.com/umpirsky/country-list</a>
	 * </ul>
	 */
	private void inicializarListas() {
		avaluosAnteriores = new ArrayList<AvaluoDTO>();
		entidades = usuario.getEntidades();
		if (entidades == null || entidades.isEmpty()) {
			entidades = usuarioController.encontrarEntidades();
		}

		// Quita las entidades inactivas
		Boolean pid = false;
		Predicate<EntidadDTO> entidadPredicate = p -> p.getActivo() == pid;
		entidades.removeIf(entidadPredicate);

		tiposInmueble = listasController.tiposInmueble();
		// Se condiciona a los permisos del usuario
		tiposAvaluo = usuario.getTipoAvaluos();
		if (tiposAvaluo == null || tiposAvaluo.isEmpty()) {
			tiposAvaluo = tiposAvaluoController.tiposAvaluo();
		}
		objetosAvaluo = new ArrayList<ObjetoAvaluoDTO>();
		/**
		 * Se construye la lista de objetosAvaluo: Abogados, creadores externos
		 * si no creadores internos
		 */
		if (listadoAvaluosBean.isPermisoAbogado()) {

			// deja la entidad bancolombia en el menu
			this.avaluoDTO.setEntidad(entidadService.encontrarPorId(1L));
			// deja el motivo de remates
			this.avaluoDTO.setMotivo(11358);

			ObjetoAvaluoDTO objetoAvaluo = new ObjetoAvaluoDTO();
			objetoAvaluo.setId(5L);
			objetoAvaluo.setNombre(Constantes.OBJETO_AVALUO_REMATE);
			this.avaluoDTO.setObjetoDelAvaluo(objetoAvaluo);
		} else if (listadoAvaluosBean.isCrearSolicitudes() && !listadoAvaluosBean.isEditarSolicitud()
				&& !listadoAvaluosBean.isPermisoAbogado()) {
			// Es un creador externo
			objetosAvaluo.add(listasController.encontrarObjetoAvaluoPorNombre(OBJETO_AVALUO_ORIGINACION));
			objetosAvaluo.add(listasController.encontrarObjetoAvaluoPorNombre(Constantes.OBJETO_AVALUO_ACTUALIZACION));
		}
		if (listadoAvaluosBean.isCrearSolicitudes() && listadoAvaluosBean.isEditarSolicitud()) {
			objetosAvaluo = listasController.encontrarTodosObjetosAvaluo();
		}
		if (tiposAvaluo.size() == 1) {
			avaluoDTO.setTipoAvaluo(tiposAvaluo.get(0));
			cambioTipoAvaluo();
		}
		log.debug("Inicializando listas de departamentos desde la base de datos");
		departamentos = listasGeograficasController.departamentos();
		log.debug("Inicializando lista de paises.");
		paises = listasGeograficasController.paises();

		solicitudUtils.cambioTipoDocumento(avaluoDTO);
		this.mostrarRepetirDatosCliente = solicitudUtils.isMostrarRepetirDatosCliente();
	}

	private void cargarAsesorSinCorreo() {
		Pattern p = Pattern.compile("([0-9]*)");
		Matcher m = p.matcher(avaluoDTO.getSucursalAsesor());
		String codigoSucursal = "";
		if (m.find()) {
			codigoSucursal = m.group(0);
		}
		this.asesorDTO = new AsesorDTO(null, avaluoDTO.getNombreAsesor(),
				avaluoController.encontrarSucursal(avaluoDTO.getEntidad().getId(), codigoSucursal),
				avaluoDTO.getCelularAsesor(), avaluoDTO.getCorreoElectronicoAsesor(), avaluoDTO.getTelefonoAsesor());
	}

	public void iniciarSolicitud() {
		avaluoDTO = new AvaluoDTO();
		avaluoDTO.setCliente(new ClienteDTO());
		avaluoDTO.setTipoAvaluo(new TipoAvaluoDTO());
		avaluoDTO.setObjetoDelAvaluo(new ObjetoAvaluoDTO());
	}

	public void cambioDeTab(TabChangeEvent event) {
		RequestContext.getCurrentInstance().scrollTo(event.getTab().getClientId());
	}

	public void cambioTipoAvaluo() {
		log.debug("AvaluoDTO: {}, TipoAvaluoDTO: {}", avaluoDTO, avaluoDTO.getTipoAvaluo());
		if (avaluoDTO.getTipoAvaluo() != null && avaluoDTO.getTipoAvaluo().getNombre().equals("Remates")) {
			AvaluoRemateDTO avaluoRemate = new AvaluoRemateDTO(avaluoDTO);
			avaluoDTO = avaluoRemate;
			tipoAvaluoRemate = true;
		} else {
			tipoAvaluoRemate = false;
		}
	}


	public void cambioTipoDocumento() {
		if (this.avaluoDTO != null) {
			if (this.avaluoDTO.getCliente() != null
					&& this.avaluoDTO.getCliente().getTipoDocumentoIdentificacion() != null) {
				if (this.avaluoDTO.getCliente().getTipoDocumentoIdentificacion() == 23) { // si
																							// se
																							// selecciono
																							// NIT
					this.mostrarRepetirDatosCliente = false;
					// this.avaluoDTO.getCliente().setNumeroDocumento(null);
				} else {
					mostrarRepetirDatosCliente = true;
				}
			}
		}
	}

	public void cargarDepartamentoInmueble() {
		this.departamentoInmueble = (this.avaluoDTO.getDivipola() != null
				&& !this.avaluoDTO.getDivipola().getDepartamento().isEmpty()
						? this.avaluoDTO.getDivipola().getDepartamento() : this.departamentoInmueble);
		ciudadesInmueble = listasGeograficasController.ciudadesPorDepartamento(departamentoInmueble);
	}

	public void onDepartamentoInmuebleChanged() {
		ciudadesInmueble = listasGeograficasController.ciudadesPorDepartamento(departamentoInmueble);
	}

	private void loadDepartamentoSolicitante() {
		this.departamentoCliente = ((avaluoDTO.getCliente().getDivipola() != null
				&& !avaluoDTO.getCliente().getDivipola().getDepartamento().isEmpty())
						? avaluoDTO.getCliente().getDivipola().getDepartamento() : this.departamentoCliente);
		ciudadesSolicitante = listasGeograficasController.ciudadesPorDepartamento(departamentoCliente);
	}

	public void onDepartamentoSolicitanteChanged() {
		ciudadesSolicitante = listasGeograficasController.ciudadesPorDepartamento(this.departamentoCliente);
	}

	public void onRepetirDatosCliente() {

		if (repetirDatosCliente) {
			String nombreCompleto = "";

			if (avaluoDTO != null && avaluoDTO.getCliente() != null
					&& avaluoDTO.getCliente().getTipoDocumentoIdentificacion() != null) {
				if (avaluoDTO.getCliente().getTipoDocumentoIdentificacion().intValue() == 23) {
					if (avaluoDTO.getCliente().getRazonSocial() != null) {
						nombreCompleto += avaluoDTO.getCliente().getRazonSocial();
					}
				} else {
					if (avaluoDTO.getCliente().getPrimerNombre() != null) {
						nombreCompleto += " " + avaluoDTO.getCliente().getPrimerNombre();
					}
					if (avaluoDTO.getCliente().getSegundoNombre() != null) {
						nombreCompleto += " " + avaluoDTO.getCliente().getSegundoNombre();
					}
					if (avaluoDTO.getCliente().getPrimerApellido() != null) {
						nombreCompleto += " " + avaluoDTO.getCliente().getPrimerApellido();
					}
					if (avaluoDTO.getCliente().getSegundoApellido() != null) {
						nombreCompleto += " " + avaluoDTO.getCliente().getSegundoApellido();
					}

					avaluoDTO.setNombreRecibe(nombreCompleto.trim());
					avaluoDTO.setTelefonoRecibe(avaluoDTO.getCliente().getTelefonoSolicitante());
					avaluoDTO.setCorreoElectronicoRecibe(avaluoDTO.getCliente().getCorreoElectronicoSolicitante());
				}
			}
		} else {
			avaluoDTO.setNombreRecibe("");
			avaluoDTO.setTelefonoRecibe(null);
			avaluoDTO.setCorreoElectronicoRecibe(null);
		}
	}

	public void onRepetirDireccion() {
		if (repetirDireccionCliente) {
			if (avaluoDTO != null && avaluoDTO.getCliente() != null) {
				if (tipoViaSolicitante != null) {
					avaluoDTO.setTipoVia(tipoViaSolicitante);
				}
				if (numeroViaSolicitante != null) {
					avaluoDTO.setNumeroVia(numeroViaSolicitante);
				}
				if (complementoViaSolicitante != null) {
					complementoVia = complementoViaSolicitante;
				}
				if (numeroViaGeneradoraSolicitante != null) {
					avaluoDTO.setNumeroViaGeneradora(numeroViaGeneradoraSolicitante);
				}
				if (complementoViaGeneradoraSolicitante != null) {
					complementoViaGeneradora = complementoViaGeneradoraSolicitante;
				}
				if (placaSolicitante != null) {
					avaluoDTO.setPlaca(placaSolicitante);
				}
				if (adicionalDireccionSolicitante != null) {
					avaluoDTO.setAdicionalDireccion(adicionalDireccionSolicitante);
				}
				if (departamentoCliente != null) {
					departamentoInmueble = departamentoCliente;
					onDepartamentoInmuebleChanged();
				}
				if (avaluoDTO.getCliente().getDivipola() != null) {
					avaluoDTO.setDivipola(avaluoDTO.getCliente().getDivipola());
				}
			}
		} else {
			avaluoDTO.setTipoVia(null);
			avaluoDTO.setNumeroVia(null);
			complementoVia = null;
			avaluoDTO.setNumeroViaGeneradora(null);
			complementoViaGeneradora = null;
			avaluoDTO.setPlaca(null);
			avaluoDTO.setAdicionalDireccion(null);
			avaluoDTO.setDivipola(null);
			departamentoInmueble = null;

			ciudadesInmueble = new ArrayList<DivipolaDTO>();
		}
	}

	public void validarCodigoExterno() {
		if (avaluoDTO.getEntidad() != null) {
			try {
				solicitudUtils.validarCodigoExterno(avaluoDTO);
				this.mostrarRepetirDatosCliente = solicitudUtils.isMostrarRepetirDatosCliente();
				this.avaluoCambioGarantia = solicitudUtils.getAvaluoCambioGarantia();
				ocultarBotonGuardar = false;
			} catch (GeneralFacesMessageException e) {
				ocultarBotonGuardar = true;
				e.printStackTrace();
				FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage(e.getTitle(), e.getMessage()));
			}
		}
	}
	
	public void validarIdSisgen() {
		if (avaluoDTO.getEntidad() != null) {
			try {
				solicitudUtils.validarIdSisgem(avaluoDTO.getAvaluoSisgenId());
				ocultarBotonGuardar = false;
			} catch (GeneralFacesMessageException e) {
				ocultarBotonGuardar = true;
				e.printStackTrace();
				FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage(e.getTitle(), e.getMessage()));
			}
		}
	}

	public void cambioEntidad() {
		if (avaluoDTO.getEntidad() != null) {
			entidadBancolombia = solicitudUtils.comprobarEntidadBancolombia(avaluoDTO.getEntidad());
			cargarSegmentosEntidad();
			try {
				solicitudUtils.validarCodigoExterno(avaluoDTO);
				this.mostrarRepetirDatosCliente = solicitudUtils.isMostrarRepetirDatosCliente();
				this.avaluoCambioGarantia = solicitudUtils.getAvaluoCambioGarantia();
				ocultarBotonGuardar = false;
			} catch (GeneralFacesMessageException e) {
				ocultarBotonGuardar = true;
				e.printStackTrace();
				FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage(e.getTitle(), e.getMessage()));
			}
			cargarMotivos();
		}
		if (listadoAvaluosBean.isPermisoAbogado() && listadoAvaluosBean.isCrearUsuario()) {
			if (this.avaluoDTO.getEntidad() != null) {
				ObjetoAvaluoDTO objetoAvaluo = new ObjetoAvaluoDTO();
				if (this.avaluoDTO.getEntidad().getNombre().contains(Constantes.ENTIDAD_DACIONES_EN_PAGO)) {
					objetoAvaluo.setId(6L);
					objetoAvaluo.setNombre(Constantes.OBJETO_AVALUO_DACION_EN_PAGO);
				} else if (this.avaluoDTO.getEntidad().getNombre().contains(Constantes.OBJETO_AVALUO_REMATE)) {
					objetoAvaluo.setId(5L);
					objetoAvaluo.setNombre(Constantes.OBJETO_AVALUO_REMATE);
				}
				this.avaluoDTO.setObjetoDelAvaluo(objetoAvaluo);
			} else {
				this.avaluoDTO.setObjetoDelAvaluo(null);
			}

		}
		// debido que el asesor es por entidad se anula el asesor.
		this.asesorDTO = new AsesorDTO();
	}

	public void validarCodigoExternoEdicion() {
		if (avaluoDTO.getEntidad() != null && avaluoDTO.getCodigoExterno() != null) {
			if (avaluoDTO.getEntidad().getId().equals(entidadOriginalId)
					&& avaluoDTO.getCodigoExterno().equals(codigoExternoOriginal)) {
				ocultarBotonGuardar = false;
			} else {
				try {
					solicitudUtils.validarCodigoExterno(avaluoDTO);
					ocultarBotonGuardar = false;
					this.mostrarRepetirDatosCliente = solicitudUtils.isMostrarRepetirDatosCliente();
					this.avaluoCambioGarantia = solicitudUtils.getAvaluoCambioGarantia();
				} catch (GeneralFacesMessageException e) {
					ocultarBotonGuardar = true;
					e.printStackTrace();
					FacesContext.getCurrentInstance().addMessage("growl",
							new FacesMessage(e.getTitle(), e.getMessage()));
				}
			}
		}
	}

	private void cargarMotivos() {

		if ((avaluoDTO.getMotivo() != null
				&& (avaluoDTO.getMotivo() == 0 || avaluoDTO.getMotivo() == 3 || avaluoDTO.getMotivo() == 4))) {
			mostrarMotivoMenu = false;
		} else {
			this.motivosEntidad = motivoService.getMotivosByEntidad(avaluoDTO.getEntidad().getId());
			mostrarMotivoMenu = true;
		}

	}

	private void cargarSegmentosEntidad() {
		segmentos = listasController.segmentosPorEntidad(avaluoDTO.getEntidad().getId());
	}

	private void comprobarEntidadBancolombia() {
		if ("Bancolombia".equals(avaluoDTO.getEntidad().getNombre())) {
			entidadBancolombia = true;
		} else {
			entidadBancolombia = false;
		}
	}

	public void validarCambioGarantia() {
		if (avaluoDTO.getMotivo() != null && avaluoDTO.getMotivo() == 3 && avaluoDTO.getEntidad() != null) {
			// comprobar avaluo aprobado en BD
			// comprobar mismo cliente
			List<AvaluoDTO> cambiosGarantia = avaluoController.comprobarCambioGarantia(avaluoDTO.getCodigoExterno(),
					avaluoDTO.getEntidad().getId());
			if (cambiosGarantia != null && !cambiosGarantia.isEmpty()) {
				this.avaluoCambioGarantia = cambiosGarantia.get(0);
				if (this.avaluoCambioGarantia != null
						&& this.avaluoCambioGarantia.getEstado().getEstado().equals(Constantes.Estado.Aprobado)) {
					if (this.avaluoCambioGarantia.getCambioGarantia() != null
							&& this.avaluoCambioGarantia.getCambioGarantia() == 4) {
						FacesContext.getCurrentInstance().addMessage("growl",
								new FacesMessage("No es posible hacer un cambio de garantía",
										"Ya existen 4 cambios de garantía: " + avaluoDTO.getCodigoExterno() + " en "
												+ avaluoDTO.getEntidad().getNombre()));
						ocultarBotonGuardar = true;
					} else {
						this.avaluoDTO.setCambioGarantia(this.avaluoCambioGarantia.getCambioGarantia() != null
								? this.avaluoCambioGarantia.getCambioGarantia() + 1 : 1);
						this.avaluoDTO.setCambioGarantiaAvaluo(this.avaluoCambioGarantia.getId());
						if (avaluoDTO.getCliente().getTipoDocumentoIdentificacion() != null
								&& avaluoDTO.getCliente().getNumeroDocumento() != null) {
							if (this.avaluoCambioGarantia.getCliente().getTipoDocumentoIdentificacion()
									.equals(avaluoDTO.getCliente().getTipoDocumentoIdentificacion())
									&& this.avaluoCambioGarantia.getCliente().getNumeroDocumento()
											.equals(avaluoDTO.getCliente().getNumeroDocumento())) {
								ocultarBotonGuardar = false;
							} else {
								ocultarBotonGuardar = true;
								FacesContext.getCurrentInstance().addMessage("growl",
										new FacesMessage("El cliente no puede ser diferente",
												"El cliente es diferente del avaluo aprobado: "
														+ avaluoDTO.getCodigoExterno() + " en "
														+ avaluoDTO.getEntidad().getNombre()));
							}
						} else {
							this.avaluoDTO.setCliente(this.avaluoController.buscarCliente(
									this.avaluoCambioGarantia.getCliente().getTipoDocumentoIdentificacion(),
									this.avaluoCambioGarantia.getCliente().getNumeroDocumento()));
							cambioTipoDocumento();
							ocultarBotonGuardar = false;
						}
					}
				} else {
					ocultarBotonGuardar = true;
					FacesContext.getCurrentInstance().addMessage("growl",
							new FacesMessage("No es posible hacer un cambio de garantía",
									"No existe un avaluo terminado " + avaluoDTO.getCodigoExterno() + " en "
											+ avaluoDTO.getEntidad().getNombre()));
				}
			} else {
				ocultarBotonGuardar = true;
				FacesContext.getCurrentInstance().addMessage("growl",
						new FacesMessage("No es posible hacer un cambio de garantía", "No existe un avaluo terminado "
								+ avaluoDTO.getCodigoExterno() + " en " + avaluoDTO.getEntidad().getNombre()));
			}
		} else {
			// validarCodigoExterno();
		}
	}

	public List<SucursalDTO> autocompletarSucursal(String query) {
		if (avaluoDTO.getEntidad() != null) {
			this.sucursales = avaluoController.sucursalesEnEntidad(query, avaluoDTO.getEntidad().getId());
			return this.sucursales;
		} else {
			this.sucursales.clear();
			return this.sucursales;
		}
	}

	public void onDireccionInmuebleChanged() {
		String direccionInmueble = "";
		String stringComplementoVia = convertirAString(complementoVia);
		String stringComplementoViaGeneradora = convertirAString(complementoViaGeneradora);
		if (avaluoDTO.getNumeroVia() != null && avaluoDTO.getTipoVia() != null) {
			direccionInmueble = avaluoDTO.getTipoVia().concat(" " + avaluoDTO.getNumeroVia())
					.concat(stringComplementoVia == "" ? "" : " " + stringComplementoVia)
					.concat(avaluoDTO.getNumeroViaGeneradora() == null ? ""
							: " # " + avaluoDTO.getNumeroViaGeneradora())
					.concat(stringComplementoViaGeneradora == "" ? "" : " " + stringComplementoViaGeneradora)
					.concat(avaluoDTO.getPlaca() == null ? "" : " - " + avaluoDTO.getPlaca())
					.concat(avaluoDTO.getComplementoPlaca() == null ? "" : " " + avaluoDTO.getComplementoPlaca())
					.concat(avaluoDTO.getAdicionalDireccion() == null ? "" : " " + avaluoDTO.getAdicionalDireccion());
		}
		avaluoDTO.setDireccionInmueble(direccionInmueble);
		avaluoDTO.setComplementoVia(stringComplementoViaGeneradora);
		avaluoDTO.setComplementoViaGeneradora(stringComplementoViaGeneradora);

		if (avaluoDTO.getDireccionInmueble() != null && !"".equals(avaluoDTO.getDireccionInmueble())) {
			avaluosDireccion = new HashSet<AvaluoDTO>(
					avaluoController.verificarDuplicadosDireccion(avaluoDTO.getDireccionInmueble()));
		}
	}

	public void onDireccionSolicitanteChanged() {
		String direccionSolicitante = "";
		if (numeroViaSolicitante != null && tipoViaSolicitante != null) {
			direccionSolicitante = tipoViaSolicitante.concat(" " + numeroViaSolicitante)
					.concat(convertirAString(complementoViaSolicitante) == "" ? ""
							: " " + convertirAString(complementoViaSolicitante))
					.concat(numeroViaGeneradoraSolicitante == null ? "" : " # " + numeroViaGeneradoraSolicitante)
					.concat(convertirAString(complementoViaGeneradoraSolicitante) == "" ? ""
							: " " + convertirAString(complementoViaGeneradoraSolicitante))
					.concat(placaSolicitante == null ? "" : " - " + placaSolicitante)
					.concat(complementoPlacaSolicitante == null ? "" : " " + complementoPlacaSolicitante);
		}
		avaluoDTO.getCliente().setDireccionDeContactoSolicitante(direccionSolicitante);
	}

	@Transactional
	public void onClienteSeleccionado() {
		Integer tipoDocumentoIdentificacionCliente = avaluoDTO.getCliente().getTipoDocumentoIdentificacion();
		Long numeroDocumentoCliente = avaluoDTO.getCliente().getNumeroDocumento();
		if (Constantes.AVALUO_CAMBIO_GARANTIA.equals(avaluoDTO.getMotivo()) && this.avaluoCambioGarantia != null
				&& this.avaluoCambioGarantia.getCliente() != null
				&& !(this.avaluoCambioGarantia.getCliente().getNumeroDocumento().equals(numeroDocumentoCliente)
						&& this.avaluoCambioGarantia.getCliente().getTipoDocumentoIdentificacion()
								.equals(tipoDocumentoIdentificacionCliente))) {
			// Dar mensaje error
			FacesContext.getCurrentInstance().addMessage("growl",
					new FacesMessage("El cliente no puede ser diferente",
							"El cliente es diferente del avaluo aprobado: " + avaluoDTO.getCodigoExterno() + " en "
									+ avaluoDTO.getEntidad().getNombre()));
			ocultarBotonGuardar = true;
		} else {
			this.ocultarBotonGuardar = false;
		}
		ClienteDTO clienteFormulario = avaluoDTO.getCliente();
		ClienteDTO cliente = avaluoController.buscarCliente(tipoDocumentoIdentificacionCliente, numeroDocumentoCliente);
		if (cliente != null) {
			if (cliente.getDivipola() != null) {
				departamentoCliente = cliente.getDivipola().getDepartamento();
				ciudadesSolicitante = listasGeograficasController.ciudadesPorDepartamento(departamentoCliente);
			}
			separarDireccionCliente(cliente);
			avaluoDTO.setCliente(cliente);
			avaluosAnteriores = avaluoController.obtenerAvaluosAnteriores(cliente.getTipoDocumentoIdentificacion(),
					cliente.getNumeroDocumento(), avaluoDTO.getId());
		} else {
			avaluoDTO.setCliente(clienteFormulario);
			avaluosAnteriores = avaluoController.obtenerAvaluosAnteriores(
					clienteFormulario.getTipoDocumentoIdentificacion(), clienteFormulario.getNumeroDocumento(),
					avaluoDTO.getId());
		}
	}

	private void separarDireccionCliente(ClienteDTO cliente) {
		String direccionOrignial = cliente.getDireccionDeContactoSolicitante() == null ? ""
				: cliente.getDireccionDeContactoSolicitante();
		Pattern patronTipoVia = Pattern.compile("[A-Z]{2}");
		String direccionCliente = cliente.getDireccionDeContactoSolicitante();
		if (direccionCliente != null && !direccionCliente.isEmpty() && !direccionCliente.equals("  #  - ")) {
			Matcher matcher = patronTipoVia.matcher(direccionCliente);
			if (matcher.find()) {
				tipoViaSolicitante = matcher.group(0);
				direccionCliente = direccionCliente.replace(tipoViaSolicitante, "").trim();
			} else {
				// throw new IllegalArgumentException("La dirección del cliente
				// "+cliente+" es invalida");
				log.error("La dirección: {} del cliente: {} es invalida", cliente.getDireccionDeContactoSolicitante(),
						cliente.getNumeroDocumento());
				adicionalDireccionSolicitante = adicionalDireccionSolicitante == null ? ""
						: adicionalDireccionSolicitante;
				adicionalDireccionSolicitante += direccionOrignial;
				return;

			}
			Pattern patronNumeroVia = Pattern.compile("[0-9]+");
			matcher = patronNumeroVia.matcher(direccionCliente);
			if (matcher.find()) {
				numeroViaSolicitante = matcher.group(0);
				direccionCliente = direccionCliente.replace(numeroViaSolicitante, "").trim();
			} else {

				adicionalDireccionSolicitante = adicionalDireccionSolicitante == null ? ""
						: adicionalDireccionSolicitante;
				adicionalDireccionSolicitante += direccionOrignial;
				return;
			}
			Pattern patronComplementoVia = Pattern.compile("[a-zA-Z ]+");
			matcher = patronComplementoVia.matcher(direccionCliente);
			if (matcher.find()) {
				String stringComplementoViaSolicitante = matcher.group(0).trim().length() > 0 ? matcher.group(0).trim()
						: "";
				complementoViaSolicitante = stringComplementoViaSolicitante.isEmpty() ? new ArrayList<String>()
						: convertirALista(stringComplementoViaSolicitante);
				direccionCliente = !complementoViaSolicitante.isEmpty()
						? direccionCliente.replace(stringComplementoViaSolicitante, "").trim() : direccionCliente;
			}
			Pattern patronNumeral = Pattern.compile("#");
			matcher = patronNumeral.matcher(direccionCliente);
			if (matcher.find()) {
				direccionCliente = direccionCliente.replace("#", "").trim();
			}
			Pattern patronNumeroViaGeneradora = Pattern.compile("[0-9]+");
			matcher = patronNumeroViaGeneradora.matcher(direccionCliente);
			if (matcher.find()) {
				numeroViaGeneradoraSolicitante = matcher.group(0);
				direccionCliente = direccionCliente.replace(numeroViaGeneradoraSolicitante, "").trim();
			}
			Pattern patronComplementoViaGeneradora = Pattern.compile("[a-zA-Z ]+");
			matcher = patronComplementoViaGeneradora.matcher(direccionCliente);
			if (matcher.find()) {
				String stringComplementoViaGeneradoraSolicitante = matcher.group(0).trim().length() > 0
						? matcher.group(0).trim() : "";
				complementoViaGeneradoraSolicitante = stringComplementoViaGeneradoraSolicitante.isEmpty()
						? new ArrayList<String>() : convertirALista(stringComplementoViaGeneradoraSolicitante);
				direccionCliente = !complementoViaGeneradoraSolicitante.isEmpty()
						? direccionCliente.replace(stringComplementoViaGeneradoraSolicitante, "").trim()
						: direccionCliente;
			}
			Pattern patronGuion = Pattern.compile("-");
			matcher = patronGuion.matcher(direccionCliente);
			if (matcher.find()) {
				direccionCliente = direccionCliente.replace("-", "").trim();
			}
			Pattern patronPlaca = Pattern.compile("[0-9]+");
			matcher = patronPlaca.matcher(direccionCliente);
			if (matcher.find()) {
				placaSolicitante = matcher.group(0);
				direccionCliente = direccionCliente.replace(placaSolicitante, "").trim();
			}
			Pattern patronComplementoPlaca = Pattern.compile("[A-Za-z]{2,11} [0-9]{1,6}( [A-Za-z]{2,11} [0-9]{1,6})*");
			matcher = patronComplementoPlaca.matcher(direccionCliente);
			if (matcher.find()) {
				complementoPlacaSolicitante = matcher.group(0).trim();
				direccionCliente = direccionCliente.replace(complementoPlacaSolicitante, "").trim();
			}
			adicionalDireccionSolicitante = direccionCliente;
		}
	}

	public void cambiarSoloAvaluo() {
		this.avaluoDTO.setTipoAvaluo(this.tiposAvaluo.stream()
				.filter(tipoAvaluoDTO -> tipoAvaluoDTO.getNombre().equals(TIPO_AVALUO_HIPOTECARIO))
				.collect(Collectors.toList()).get(0));
		this.avaluoDTO.setObjetoDelAvaluo(this.objetosAvaluo.stream()
				.filter(objetoAvaluoDTO -> objetoAvaluoDTO.getNombre().equals(OBJETO_AVALUO_ORIGINACION))
				.collect(Collectors.toList()).get(0));

		this.avaluoDTO.setTipoCredito("Vivienda");
	}

	private List<String> convertirALista(String string) {
		if (string.isEmpty()) {
			return new ArrayList<String>();
		}
		return Arrays.asList(string.split(" "));
	}

	private String convertirAString(List<String> list) {
		String cadena = "";
		if (list != null && !list.isEmpty()) {
			cadena = list.toString().replace("[", "").replace("]", "").replace(",", "");
		}
		return cadena;
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

	public Map<String, String> getDepartamentos() {
		return departamentos;
	}

	public List<DivipolaDTO> getCiudadesSolicitante() {
		return ciudadesSolicitante;
	}

	public void setDepartamentos(SortedMap<String, String> departamentos) {
		this.departamentos = departamentos;
	}

	public void setCiudadesSolicitante(List<DivipolaDTO> ciudadesSolicitante) {
		this.ciudadesSolicitante = ciudadesSolicitante;
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

	public List<EntidadDTO> getEntidades() {
		return entidades;
	}

	public void setEntidades(List<EntidadDTO> entidades) {
		this.entidades = entidades;
	}

	public AsesorDTO getAsesorDTO() {
		return asesorDTO;
	}

	public void setAsesorDTO(AsesorDTO asesorDTO) {
		this.asesorDTO = asesorDTO;
	}

	public SucursalDTO getSucursalDTO() {
		return sucursalDTO;
	}

	public void setSucursalDTO(SucursalDTO sucursalDTO) {
		this.sucursalDTO = sucursalDTO;
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

	public List<TipoAvaluoDTO> getTiposAvaluo() {
		return tiposAvaluo;
	}

	public void setTiposAvaluo(List<TipoAvaluoDTO> tiposAvaluo) {
		this.tiposAvaluo = tiposAvaluo;
	}

	public List<ObjetoAvaluoDTO> getObjetosAvaluo() {
		return objetosAvaluo;
	}

	public void setObjetosAvaluo(List<ObjetoAvaluoDTO> objetosAvaluo) {
		this.objetosAvaluo = objetosAvaluo;
	}

	public List<SegmentoDTO> getSegmentos() {
		return segmentos;
	}

	public void setSegmentos(List<SegmentoDTO> segmentos) {
		this.segmentos = segmentos;
	}

	public List<SucursalDTO> getSucursales() {
		return sucursales;
	}

	public void setSucursales(List<SucursalDTO> sucursales) {
		this.sucursales = sucursales;
	}

	public AvaluoDTO getAvaluoDTO() {
		return avaluoDTO;
	}

	public void setAvaluoDTO(AvaluoDTO avaluoDTO) {
		this.avaluoDTO = avaluoDTO;
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

	public boolean isEntidadBancolombia() {
		return entidadBancolombia;
	}

	public void setEntidadBancolombia(boolean entidadBancolombia) {
		this.entidadBancolombia = entidadBancolombia;
	}

	public boolean isRepetirDatosCliente() {
		return repetirDatosCliente;
	}

	public void setRepetirDatosCliente(boolean repetirDatosCliente) {
		this.repetirDatosCliente = repetirDatosCliente;
	}

	public boolean isRepetirDireccionCliente() {
		return repetirDireccionCliente;
	}

	public void setRepetirDireccionCliente(boolean repetirDireccionCliente) {
		this.repetirDireccionCliente = repetirDireccionCliente;
	}

	public List<AvaluoDTO> getAvaluosAnteriores() {
		return this.avaluosAnteriores;
	}

	public void setAvaluosAnteriores(List<AvaluoDTO> avaluosAnteriores) {
		this.avaluosAnteriores = avaluosAnteriores;
	}

	public Set<AvaluoDTO> getAvaluosDireccion() {
		return avaluosDireccion;
	}

	public void setAvaluosDireccion(Set<AvaluoDTO> avaluosDireccion) {
		this.avaluosDireccion = avaluosDireccion;
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

	/**
	 * @return True si la direccion es incorrecta, False si se diligencio alguna
	 *         de las dos direcciones.
	 */
	public boolean dirreccionInmuebleIncorrecta() {
		Boolean direccionPartes = Boolean.FALSE;
		Boolean direccionAdicional = Boolean.FALSE;
		String tipoVia = this.avaluoDTO.getTipoVia() == null ? "" : this.avaluoDTO.getTipoVia();
		String numeroViaGeneradora = this.avaluoDTO.getNumeroViaGeneradora() == null ? ""
				: this.avaluoDTO.getNumeroViaGeneradora();
		String via = this.avaluoDTO.getNumeroVia() == null ? "" : this.avaluoDTO.getNumeroVia();
		String placa = this.avaluoDTO.getPlaca() == null ? "" : this.avaluoDTO.getPlaca();

		if (tipoVia.isEmpty())
			direccionPartes = Boolean.TRUE;
		if (numeroViaGeneradora.isEmpty())
			direccionPartes = Boolean.TRUE;
		if (placa.isEmpty())
			direccionPartes = Boolean.TRUE;
		if (via.isEmpty())
			direccionPartes = Boolean.TRUE;
		String adicional = this.avaluoDTO.getAdicionalDireccion() == null ? "" : this.avaluoDTO.getAdicionalDireccion();
		if (adicional.isEmpty()) {
			direccionAdicional = Boolean.TRUE;
		} else {
			direccionAdicional = Boolean.FALSE;
		}
		return direccionPartes && direccionAdicional;
	}

	public void guardarSolicitud() {
		if (!this.avaluoDTO.isSoloAvaluo() && this.dirreccionInmuebleIncorrecta()) {
			this.errorDireccionInmueble = Boolean.TRUE;
			FacesContext.getCurrentInstance().addMessage("errorMessageInmueble",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Diligencie la direccion del inmueble"));
			return;
		}

		guardar();
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		String uri = context
				.encodeActionURL(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()
						+ "/pages/avaluos/listadoAvaluos.xhtml");
		try {
			context.redirect(uri);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void cancelarSolicitud() {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		String uri = context
				.encodeActionURL(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()
						+ "/pages/avaluos/listadoAvaluos.xhtml");
		try {
			context.redirect(uri);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void asignarPerito() {
		log.info("Redireccionando con {} a asignar perito.", avaluoDTO);
		if (!this.avaluoDTO.isSoloAvaluo() && this.dirreccionInmuebleIncorrecta()) {
			this.errorDireccionInmueble = Boolean.TRUE;
			FacesContext.getCurrentInstance().addMessage("errorMessageInmueble",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Diligencie la direccion del inmueble"));
			return;
		}
		// redireccionar a asignar perito
		AvaluoDTO guardado = guardar();
		if (guardado != null) {
			listadoAvaluosBean.setAvaluo(guardado);
			String uri = FacesContext.getCurrentInstance().getExternalContext()
					.encodeActionURL(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()
							+ "/pages/avaluos/asignarPerito.xhtml");
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect(uri);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private AvaluoDTO guardar() {
		onDireccionInmuebleChanged();
		onDireccionSolicitanteChanged();
		if (!avaluoDTO.getTipoAvaluo().getNombre().equals(Constantes.TIPO_AVALUO_REMATE)
				&& !this.avaluoDTO.isSoloAvaluo()) {
			this.avaluoDTO.setCelularAsesor(this.asesorDTO.getCelular());
			this.avaluoDTO.setCorreoElectronicoAsesor(this.asesorDTO.getCorreo());
			this.avaluoDTO.setNombreAsesor(this.asesorDTO.getNombre());
			if (this.asesorDTO.getSucursal() != null) {
				this.avaluoDTO.setSucursalAsesor(
						this.asesorDTO.getSucursal().getCodigo() + " " + this.asesorDTO.getSucursal().getNombre());
			}
			this.avaluoDTO.setTelefonoAsesor(this.asesorDTO.getTelefono());
			if (this.asesorDTO.getId() == null) {
				this.asesorDTO = this.avaluoController.guardarAsesor(this.asesorDTO);
			} else {
				avaluoController.actualizarAsesor(asesorDTO);
			}
		}
		if (avaluoDTO.getId() != null && avaluoDTO.getId() > 0) {
			listadoAvaluosBean.setSolicitudActualizada(avaluoDTO.getCodigoExterno());
			return avaluoController.actualizar(avaluoDTO);
		} else {
			try {
				avaluoDTO = avaluoController.crearSolicitud(avaluoDTO, usuario);
			} catch (PersistenceException e) {
				FacesContext.getCurrentInstance().addMessage("growl",
						new FacesMessage("No es posible guardar la solicitud", "El código externo ya existe: "
								+ avaluoDTO.getCodigoExterno() + " en " + avaluoDTO.getEntidad().getNombre()));
				e.printStackTrace();
				return null;
			}
			if (this.avaluoDTO != null) {
				if (this.avaluoCambioGarantia != null) {
					avaluoController.actualizarCambioGarantia(avaluoCambioGarantia);
				}
				listadoAvaluosBean.setSolicitudCreada(avaluoDTO.getCodigoExterno());
			}
			return avaluoDTO;
		}
	}

	public List<TipoInmuebleDTO> getTiposInmueble() {
		return tiposInmueble;
	}

	/**
	 * Auto completa los campos del asesor basado en el correo
	 *
	 */
	public void autocompletarAsesor() {
		log.debug("automcomepletando los datos del asesor");
		AsesorDTO asesor = null;
		if (this.avaluoDTO.getEntidad() != null) {
			if (this.asesorDTO.getCorreo() != null && !this.asesorDTO.getCorreo().isEmpty()) {
				asesor = this.avaluoController.encontrarAsesor(this.asesorDTO.getCorreo());
				if (asesor != null && asesor.getSucursal() != null) {
					boolean pertenceAEntidad = (this.avaluoDTO.getEntidad().getId() == asesor.getSucursal()
							.getEntidadId()) ? true : false;
					if (!pertenceAEntidad) {
						FacesContext.getCurrentInstance().addMessage("growl",
								new FacesMessage("Asesor", "El asesor con correo electrónico: " + asesor.getCorreo()
										+ " corresponde a otra entidad "));
						ocultarBotonGuardar = true;
						asesor = null;
					} else {
						ocultarBotonGuardar = false;
					}

				} else {
					ocultarBotonGuardar = false;
					String coreoAsesorTemp = this.asesorDTO.getCorreo();
					this.asesorDTO = new AsesorDTO();
					this.asesorDTO.setCorreo(coreoAsesorTemp);
				}
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Entidad", "Debes seleccionar una entidad para continuar"));
		}
		if (asesor != null) {
			this.asesorDTO = asesor;
			this.autocompletarSucursal(asesor.getSucursal().getNombre());
		} else {
			this.asesorDTO.setId(null);
		}
	}

	public boolean isTipoAvaluoRemate() {
		return tipoAvaluoRemate;
	}

	public void setTipoAvaluoRemate(boolean panelSecuestre) {
		this.tipoAvaluoRemate = panelSecuestre;
	}

	public boolean isOcultarBotonGuardar() {
		return ocultarBotonGuardar;
	}

	public void setOcultarBotonGuardar(boolean mostrarBotonGuardar) {
		this.ocultarBotonGuardar = mostrarBotonGuardar;
	}

	public boolean isMostrarRepetirDatosCliente() {
		return mostrarRepetirDatosCliente;
	}

	public void setMostrarRepetirDatosCliente(boolean mostrarRepetirDatosCliente) {
		this.mostrarRepetirDatosCliente = mostrarRepetirDatosCliente;
	}

	public Boolean getErrorDireccionInmueble() {
		return errorDireccionInmueble;
	}

	public void setErrorDireccionInmueble(Boolean errorDireccionInmueble) {
		this.errorDireccionInmueble = errorDireccionInmueble;
	}

	public List<MotivoDTO> getMotivosEntidad() {
		return motivosEntidad;
	}

	public void setMotivosEntidad(List<MotivoDTO> motivosEntidad) {
		this.motivosEntidad = motivosEntidad;
	}

	public boolean isMostrarMotivoMenu() {
		return mostrarMotivoMenu;
	}

	public void setMostrarMotivoMenu(boolean mostrarMotivoMenu) {
		this.mostrarMotivoMenu = mostrarMotivoMenu;
	}

	public boolean isCrearSolicitud() {
		return crearSolicitud;
	}

	public void setCrearSolicitud(boolean crearSolicitud) {
		this.crearSolicitud = crearSolicitud;
	}

}
