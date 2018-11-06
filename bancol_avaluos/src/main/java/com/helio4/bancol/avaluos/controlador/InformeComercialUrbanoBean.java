package com.helio4.bancol.avaluos.controlador;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
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

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
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
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.helio4.bancol.avaluos.dominio.AvaluoController;
import com.helio4.bancol.avaluos.dominio.InformeComercialController;
import com.helio4.bancol.avaluos.dominio.InformeHipotecarioController;
import com.helio4.bancol.avaluos.dominio.ListasController;
import com.helio4.bancol.avaluos.dominio.ListasGeograficasController;
import com.helio4.bancol.avaluos.dominio.gmap.GLatLng;
import com.helio4.bancol.avaluos.dto.AreaConstruccionDTO;
import com.helio4.bancol.avaluos.dto.AreaDTO;
import com.helio4.bancol.avaluos.dto.AreaDTO.TipoArea;
import com.helio4.bancol.avaluos.dto.AreaDTO.UnidadDeMedida;
import com.helio4.bancol.avaluos.dto.AvaluoComercialDTO;
import com.helio4.bancol.avaluos.dto.AvaluoDTO;
import com.helio4.bancol.avaluos.dto.DivipolaDTO;
import com.helio4.bancol.avaluos.dto.EntidadDTO;
import com.helio4.bancol.avaluos.dto.FormatoInformeComercialDTO;
import com.helio4.bancol.avaluos.dto.InmuebleDTO;
import com.helio4.bancol.avaluos.dto.MetodoValuacionDTO;
import com.helio4.bancol.avaluos.dto.PropietarioDTO;
import com.helio4.bancol.avaluos.dto.TipoInmuebleDTO;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;
import com.helio4.bancol.avaluos.dto.ViaAccesoDTO;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;
import com.helio4.bancol.avaluos.servicio.util.Constantes;
import com.helio4.bancol.avaluos.servicio.util.MedidasUtils;

@Controller
@Scope("view")
@Qualifier("informeComercialUrbanoBean")
public class InformeComercialUrbanoBean implements Serializable{

	private static Logger log = LoggerFactory.getLogger( InformeComercialUrbanoBean.class );
	
	private static final long serialVersionUID = 1L;
	private AvaluoComercialDTO avaluoComercialDTO;
	private FormatoInformeComercialDTO informeComercialDTO;
	private ImmutableSet<InmuebleDTO> inmueblesOriginales;
	private ImmutableSet<ViaAccesoDTO> viasAccesoOriginales;
	private ImmutableSet<AreaDTO> areasOriginales;
	private ImmutableSet<MetodoValuacionDTO> metodosValuacionOriginales;
	
	private SortedMap<String,String> departamentos;
	private List<DivipolaDTO> ciudadesSolicitante;
	private List<DivipolaDTO> ciudadesPredio;
	private List<DivipolaDTO> ciudadesInmueble;
	private SortedMap<String, String> paises;
	private String centroMapa;
	private List<TipoInmuebleDTO> tiposInmueble;
	private List<EntidadDTO> entidades;
	private List<String> sucursales;
	private SortedMap<String, String> segmentos;
	
	private boolean avaluoEnRevision;
	private boolean avaluoEnCorrecion;
	private String correcciones;
	
	private String nombresApellidosCliente;
	private String tipoViaCliente;
	private String numeroViaCliente;
	private List<String> complementoViaCliente;
	private String numeroViaGeneradoraCliente;
	private List<String> complementoViaGeneradoraCliente;
	private String placaCliente;
	private String complementoPlacaCliente;
	private String adicionalDireccionCliente;
	private MapModel mapModel;
	
	private List<String> complementoVia;
	private List<String> complementoViaGeneradora;

	private Set<Integer> documentosConsultados;
	private boolean otrosDocumentos;
	private boolean matriculaInmobiliariaTieneavaluos;
	private boolean cedulaCatastralTieneavaluos;
	
	private boolean nuevoInmueble;
	private boolean editarInmueble;
	private InmuebleDTO inmueble;
	private boolean nuevoPropietario;
	private PropietarioDTO propietario;
	private boolean propietariosCienPorciento;
	
	private Set<ViaAccesoDTO> viasAccesoPrincipal;
	private Set<ViaAccesoDTO> viasAccesoSecundario;
	private Set<ViaAccesoDTO> viasAccesoInmueble;
	
	private boolean nuevaViaAcceso;
	private boolean nuevaViaAccesoPrincipal;
	private boolean nuevaViaAccesoSecundaria;
	private ViaAccesoDTO viaAccesoPrincipal;
	private ViaAccesoDTO viaAccesoSecundaria;
	private ViaAccesoDTO viaAcceso;
	private Set<AreaDTO> areasInmueble;
	private Set<AreaDTO> areasNoConstruidas;
	private Set<AreaDTO> areasConstruidas;
	private Set<AreaDTO> areasValorProporcionalTerreno;
	private List<AreaDTO> areasValorComercial;
	private Set<AreaConstruccionDTO> areasHabitacionales;
	private AreaDTO areaTerreno;
	
	private boolean nuevaArea;
	private AreaDTO area;
	private AreaConstruccionDTO areaConstruccion;
	private boolean nuevoMetodoValuacion;
	private MetodoValuacionDTO metodoValuacion;
	private BigDecimal subtotalAreasTerreno;
	private BigDecimal subtotalAreasConstruidas;
	private BigDecimal valorResidualParaLaConstruccion;
	private BigDecimal subtotalPorcentajeAreasTerreno;
	private BigDecimal subtotalPorcentajeAreasConstruidas;
	
	private BigDecimal subtotalArea;
	private BigDecimal subtotalPorcentajeArea;
	
	private BigDecimal porcentajeFinal;
	
	@Autowired
	private AvaluoController avaluoController;
	
	@Autowired
	private InformeComercialController informeComercialController;

	@Autowired
	private InformeHipotecarioController informeHipotecarioController;
	
	@Autowired
	private ListasGeograficasController listasGeograficasController;
	
	@Autowired
	private ListadoAvaluosBean listadoAvaluosBean;
	
	@Autowired
	private ListasController listasController;
	
	@PostConstruct
	public void init() {
		avaluoComercialDTO = (AvaluoComercialDTO) listadoAvaluosBean.getAvaluo();
		informeComercialDTO = (FormatoInformeComercialDTO) avaluoComercialDTO.getFormatoInforme();
		if (informeComercialDTO.getInmuebles() != null) {
			inmueblesOriginales = ImmutableSet.copyOf(informeComercialDTO
					.getInmuebles());
		}
		if (informeComercialDTO.getViasAcceso() != null) {
			viasAccesoOriginales = ImmutableSet.copyOf(informeComercialDTO.getViasAcceso());
		}
		if (avaluoComercialDTO.getAreas() != null) {
			areasOriginales = ImmutableSet.copyOf(avaluoComercialDTO.getAreas());
		}
		if (informeComercialDTO.getMetodosValuacion() != null) {
			metodosValuacionOriginales = ImmutableSet.copyOf(informeComercialDTO.getMetodosValuacion());
		}
		
		avaluoEnRevision = avaluoComercialDTO.getEstado().getEstado().equals(Constantes.Estado.Enviado.value());
		avaluoEnCorrecion = avaluoComercialDTO.getEstado().getEstado().equals(Constantes.Estado.Correciones.value());
		
		String[] strings = {avaluoComercialDTO.getCliente().getPrimerNombre(), avaluoComercialDTO.getCliente().getSegundoNombre(), avaluoComercialDTO.getCliente().getPrimerApellido(), avaluoComercialDTO.getCliente().getSegundoApellido()};
		nombresApellidosCliente = StringUtils.join(strings, " ");
		if (StringUtils.isBlank(nombresApellidosCliente) || nombresApellidosCliente == null || nombresApellidosCliente == "") {
			nombresApellidosCliente = avaluoComercialDTO.getCliente().getRazonSocial();
		}
		separarDireccion(avaluoComercialDTO.getCliente().getDireccionDeContactoSolicitante());
		documentosConsultados = new HashSet<Integer>();
		if (informeComercialDTO.getDocumentosConsultados() != null && !informeComercialDTO.getDocumentosConsultados().isEmpty()) {
			List<String> documentosConsultadosList = Arrays
					.asList(informeComercialDTO.getDocumentosConsultados()
							.split(", "));
			for (String string : documentosConsultadosList) {
				documentosConsultados.add(Integer.parseInt(string));
			}
		}
		mapModel = new DefaultMapModel();
		LatLng coordenadas = informeComercialController.obtenerCoordenadasInmueble(avaluoComercialDTO);
		GLatLng gLatLng = avaluoController.calcularCoordenadasAvaluo(avaluoComercialDTO, avaluoComercialDTO.getDivipola().getDepartamento(), true);
		if (gLatLng != null) {
			avaluoComercialDTO.setLatitud(new BigDecimal(gLatLng.getLat()));
			avaluoComercialDTO.setLongitud(new BigDecimal(gLatLng.getLng()));
			coordenadas = new LatLng(gLatLng.getLat(), gLatLng.getLng());
		}

		Marker marker = new Marker(coordenadas, "Codigo solicitud: "+avaluoComercialDTO.getCodigoExterno().toString());
		marker.setDraggable(true);
		mapModel.addOverlay(marker);
		centroMapa = Double.toString(coordenadas.getLat())+", "+Double.toString(coordenadas.getLng());
		
		cargarInformacionInmuebles();
		separarViasAcceso();
		separarAreas();
		if (avaluoEnCorrecion) {
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('correcionesDialog').show();");
		}
		
		// inicializar listas de parametros desde la base de datos
		tiposInmueble = listasController.tiposInmueble();
		entidades = avaluoController.entidades();
		sucursales = avaluoController.sucursales();
		log.debug("Inicializando listas de departamentos desde la base de datos");
		departamentos = listasGeograficasController.departamentos();
		ciudadesSolicitante = new ArrayList<DivipolaDTO>();
		ciudadesPredio = new ArrayList<DivipolaDTO>();
		ciudadesInmueble = new ArrayList<DivipolaDTO>();
		// Inicializar lista de paises
		log.debug("Inicializando lista de paises.");
		paises = listasGeograficasController.paises();
	}
	
	private void cargarInformacionInmuebles() {
		if (informeComercialDTO.getInmuebles() == null) {
			informeComercialDTO.setInmuebles(new HashSet<InmuebleDTO>());
		}else{
			for (InmuebleDTO inmueble:informeComercialDTO.getInmuebles()) {
				inmueble.setMapModel(new DefaultMapModel());
				if (inmueble.getLatitud() != null && inmueble.getLongitud() != null) {
					LatLng coordenadas = new LatLng(inmueble.getLatitud().doubleValue(), inmueble.getLongitud().doubleValue());
					Marker marker = new Marker(coordenadas, "Inmueble: "+inmueble.getMatriculaInmobiliaria());
					marker.setDraggable(false);
					inmueble.getMapModel().addOverlay(marker);
				}
				if (inmueble.getPropietarios() != null && !inmueble.getPropietarios().isEmpty()) {
					inmueble.setPropietariosOriginales(ImmutableSet.copyOf(inmueble.getPropietarios()));
				}
			}
		}
	}

	private void separarAreas() {
		informeComercialDTO.setAreasConstruccion(new HashSet<AreaConstruccionDTO>());
		areasNoConstruidas = new HashSet<AreaDTO>();
		areasConstruidas = new HashSet<AreaDTO>();
		areasValorProporcionalTerreno = new HashSet<AreaDTO>();
		areasHabitacionales = new HashSet<AreaConstruccionDTO>();
		areasValorComercial = new ArrayList<AreaDTO>();
		areasInmueble = new HashSet<AreaDTO>();
		BigDecimal sumaAreasTerreno = new BigDecimal(0);
		
		if (avaluoComercialDTO.getAreas() == null) {
			avaluoComercialDTO.setAreas(new HashSet<AreaDTO>());
			areaConstruccion = new AreaConstruccionDTO();
			areaConstruccion.setAvaluoId(avaluoComercialDTO.getId());
			areaConstruccion.setTipoArea(TipoArea.Construccion);
			avaluoComercialDTO.getAreas().add(areaConstruccion);
		}else{
			for (AreaDTO areaDTO:avaluoComercialDTO.getAreas()) {
				TipoArea tipo = areaDTO.getTipoArea();
				if (tipo.equals(TipoArea.Terreno)) {
					areasNoConstruidas.add(areaDTO);
					areasInmueble.add(areaDTO);
					if (areaDTO.getUnidadDeMedida().equals(UnidadDeMedida.M2)) {
						sumaAreasTerreno = sumaAreasTerreno.add(areaDTO
								.getArea());
					}else if (areaDTO.getUnidadDeMedida().equals(UnidadDeMedida.Hectareas)) {
						sumaAreasTerreno = sumaAreasTerreno.add(MedidasUtils.
								convertirHectareasAMetros(areaDTO.getArea()));
					}else if (areaDTO.getUnidadDeMedida().equals(UnidadDeMedida.Fanegadas)) {
						sumaAreasTerreno = sumaAreasTerreno.add(MedidasUtils.
								convertirFanegadasAMetros(areaDTO.getArea()));
					}
				}else if (tipo.equals(TipoArea.Construccion)) {
					if (areaDTO.getClass().equals(AreaConstruccionDTO.class)) {
						areaConstruccion = (AreaConstruccionDTO) areaDTO;
					}else {
						areasConstruidas.add(areaDTO);
						areasInmueble.add(areaDTO);
					}
				}
			}
			if (!areasNoConstruidas.isEmpty() || !informeComercialDTO.getAreasConstruccion().isEmpty()) {
				areasValorProporcionalTerreno.addAll(areasNoConstruidas);
				areasValorProporcionalTerreno.addAll(informeComercialDTO.getAreasConstruccion());
				for (AreaConstruccionDTO areaConstruccionDTO : informeComercialDTO.getAreasConstruccion()) {
					if (areaConstruccionDTO.getTipoDeConstruccion().equals(1)) {
						areasHabitacionales.add(areaConstruccionDTO);
					}
				}
			}
			if (areaConstruccion == null) {
				areaConstruccion = new AreaConstruccionDTO();
				areaConstruccion.setAvaluoId(avaluoComercialDTO.getId());
				areaConstruccion.setTipoArea(TipoArea.Construccion);
				avaluoComercialDTO.getAreas().add(areaConstruccion);
			}
			areasValorComercial.addAll(areasConstruidas);
			areaTerreno = new AreaDTO();
			areaTerreno.setNombre("Areas de terreno");
			areaTerreno.setDescripcion("Suma areas de terreno");
			areaTerreno.setArea(sumaAreasTerreno);
			areaTerreno.setUnidadDeMedida(UnidadDeMedida.M2);
			areaTerreno.setTipoArea(TipoArea.Terreno);
			areasValorComercial.add(areaTerreno);
			calcularValores();
		}
	}

	private void separarViasAcceso() {
		viasAccesoInmueble = new HashSet<ViaAccesoDTO>();
		viasAccesoPrincipal = new HashSet<ViaAccesoDTO>();
		viasAccesoSecundario = new HashSet<ViaAccesoDTO>();
		if (informeComercialDTO.getViasAcceso() == null) {
			informeComercialDTO.setViasAcceso(new HashSet<ViaAccesoDTO>());
		}else{
			for (ViaAccesoDTO viaAccesoDTO:informeComercialDTO.getViasAcceso()) {
				Integer tipo = viaAccesoDTO.getTipo();
				if (tipo == 2){
					viasAccesoPrincipal.add(viaAccesoDTO);
				}else if (tipo == 1) {
					viasAccesoInmueble.add(viaAccesoDTO);
				}else if (tipo == 3) {
					viasAccesoSecundario.add(viaAccesoDTO);
				}
			}
		}
	}

	private void separarDireccion(String direccion) {
		Pattern patronTipoVia = Pattern.compile("[A-Z]{2}");
		if(!direccion.isEmpty()) {
			Matcher matcher = patronTipoVia.matcher(direccion);
			if(matcher.find()) {
				tipoViaCliente = matcher.group(0);
				direccion = direccion.replace(tipoViaCliente, "").trim();
			}else{
				throw new IllegalArgumentException("La dirección del cliente "+direccion+" es invalida");
			}
			Pattern patronNumeroVia =  Pattern.compile("[0-9]+");
			matcher = patronNumeroVia.matcher(direccion);
			if (matcher.find()) {
				numeroViaCliente = matcher.group(0);
				direccion = direccion.replace(numeroViaCliente, "").trim();
			}else {
				throw new IllegalArgumentException("La dirección del cliente "+direccion+" es invalida");
			}
			Pattern patronComplementoVia =  Pattern.compile("[a-zA-Z ]+");
			matcher = patronComplementoVia.matcher(direccion);
			if (matcher.find()) {
				String stringComplementoViaCliente = matcher.group(0).trim().length() > 0 ? matcher.group(0).trim() : "";
				complementoViaCliente = stringComplementoViaCliente.isEmpty() ? new ArrayList<String>() : convertirALista(stringComplementoViaCliente);
				direccion = ! complementoViaCliente.isEmpty() ? direccion.replace(stringComplementoViaCliente, "").trim() : direccion;
			}
			Pattern patronNumeral =  Pattern.compile("#");
			matcher = patronNumeral.matcher(direccion);
			if (matcher.find()) {
				direccion = direccion.replace("#", "").trim();
			}
			Pattern patronNumeroViaGeneradora =  Pattern.compile("[0-9]+");
			matcher = patronNumeroViaGeneradora.matcher(direccion);
			if (matcher.find()) {
				numeroViaGeneradoraCliente = matcher.group(0);
				direccion = direccion.replace(numeroViaGeneradoraCliente, "").trim();
			}
			Pattern patronComplementoViaGeneradora =  Pattern.compile("[a-zA-Z ]+");
			matcher = patronComplementoViaGeneradora.matcher(direccion);
			if (matcher.find()) {
				String stringComplementoViaGeneradoraCliente = matcher.group(0).trim().length() > 0 ? matcher.group(0).trim() : ""; 
				complementoViaGeneradoraCliente = stringComplementoViaGeneradoraCliente.isEmpty() ? new ArrayList<String>() : convertirALista(stringComplementoViaGeneradoraCliente);
				direccion = ! complementoViaGeneradoraCliente.isEmpty() ?  direccion.replace(stringComplementoViaGeneradoraCliente, "").trim() : direccion;
			}
			Pattern patronGuion =  Pattern.compile("-");
			matcher = patronGuion.matcher(direccion);
			if (matcher.find()) {
				direccion = direccion.replace("-", "").trim();
			}
			Pattern patronPlaca =  Pattern.compile("[0-9]+");
			matcher = patronPlaca.matcher(direccion);
			if (matcher.find()) {
				placaCliente = matcher.group(0);
				direccion = direccion.replace(placaCliente, "").trim();
			}
			Pattern patronComplementoPlaca =  Pattern.compile("[A-Za-z]{2,11} [0-9]{1,6}( [A-Za-z]{2,11} [0-9]{1,6})*");
			matcher = patronComplementoPlaca.matcher(direccion);
			if (matcher.find()) {
				complementoPlacaCliente = matcher.group(0).trim();
				direccion = direccion.replace(complementoPlacaCliente, "").trim();
			}
			adicionalDireccionCliente = direccion;
		}
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
		return complementoVia;
	}
	
	public void cambioDeTab(TabChangeEvent event){
		if (event.getTab().getClientId().equals("informeComercialUrbano:tabHost:titulacion")) {
			RequestContext.getCurrentInstance().update("informeComercialUrbano:tabHost:inmueblesCarousel");
		}
		if (nuevoInmueble || nuevoPropietario) {
			cancelarNuevoInmueble();
			RequestContext.getCurrentInstance().update("informeComercialUrbano:tabHost:nuevoInmueble");
		}
		if (nuevaViaAcceso || nuevaViaAccesoPrincipal || nuevaViaAccesoSecundaria) {
			cancelarNuevaViaAcceso();
			RequestContext.getCurrentInstance().update("informeComercialUrbano:tabHost:viasAccesoPrincipal");
			RequestContext.getCurrentInstance().update("informeComercialUrbano:tabHost:viasAccesoSecundario");
			RequestContext.getCurrentInstance().update("informeComercialUrbano:tabHost:viasAcceso");
		}
		if (nuevaArea) {
			cancelarNuevaArea();
			RequestContext.getCurrentInstance().update("informeComercialUrbano:tabHost:areas");
			RequestContext.getCurrentInstance().update("informeComercialUrbano:tabHost:areasConstruidasPanel");
		}
		RequestContext.getCurrentInstance().scrollTo(event.getTab().getParent().getClientId());
	}

	public void onMarkerDrag(MarkerDragEvent event) {
		Marker marker = event.getMarker();
		inmueble.setLatitud(BigDecimal.valueOf(marker.getLatlng().getLat()));
		inmueble.setLongitud(BigDecimal.valueOf(marker.getLatlng().getLng()));
		log.debug("El marcador quedo en: {}, {}",
                marker.getLatlng().getLat(),
                marker.getLatlng().getLng());
	}
	
	public void agregarInmueble(){
		nuevoInmueble = true;
		if (inmueble == null) {
			inmueble = new InmuebleDTO();
		}
		propietariosCienPorciento = false;
	}
	
	public void cancelarNuevoInmueble() {
		if (nuevoInmueble) {
			if (nuevoPropietario) {
				cancelarNuevoPropietario();
			}
			nuevoInmueble = false;
			String numeroEscritura = inmueble.getNumeroDeEscritura();
			Integer numeroNotaria = inmueble.getNumeroDeNotariaEscritura();
			String departamento = inmueble.getDepartamentoEscritura();
			String municipio = inmueble.getMunicipioEscritura();
			Date fechaEscritura = inmueble.getFechaEscritura();
			inmueble = new InmuebleDTO();
			inmueble.setNumeroDeEscritura(numeroEscritura);
			inmueble.setNumeroDeNotariaEscritura(numeroNotaria);
			inmueble.setDepartamentoEscritura(departamento);
			inmueble.setMunicipioEscritura(municipio);
			inmueble.setFechaEscritura(fechaEscritura);
		}else if (editarInmueble) {
			if (nuevoPropietario) {
				cancelarNuevoPropietario();
			}
			editarInmueble = false;
		}
	}
	
	public void editarInmueble(InmuebleDTO inmueble) {
		editarInmueble = true;
		this.inmueble = inmueble;
		RequestContext.getCurrentInstance().scrollTo("informeComercialUrbano:tabHost:nuevoInmueble");
	}

	public void guardarInmueble() {
		if (nuevoInmueble) {
			if (nuevoPropietario) {
				agregarPropietario();
			}
			// Oculta al panel de agregar nuevo inmueble
			nuevoInmueble = false;
			inmueble.setAvaluoId(avaluoComercialDTO.getId());
			String numeroEscritura = inmueble.getNumeroDeEscritura();
			Integer numeroNotaria = inmueble.getNumeroDeNotariaEscritura();
			String departamento = inmueble.getDepartamentoEscritura();
			String municipio = inmueble.getMunicipioEscritura();
			Date fechaEscritura = inmueble.getFechaEscritura();
			informeComercialDTO.getInmuebles().add(inmueble);
			inmueble = new InmuebleDTO();
			inmueble.setNumeroDeEscritura(numeroEscritura);
			inmueble.setNumeroDeNotariaEscritura(numeroNotaria);
			inmueble.setDepartamentoEscritura(departamento);
			inmueble.setMunicipioEscritura(municipio);
			inmueble.setFechaEscritura(fechaEscritura);
		}else if (editarInmueble) {
			if (nuevoPropietario) {
				agregarPropietario();
			}
			// Oculta al panel de agregar nuevo inmueble
			editarInmueble = false;
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Inmuebles", "El inmueble fue guardado.");
			FacesContext.getCurrentInstance().addMessage("growl", msg);
		}
	}
	
	public void borrarInmueble(InmuebleDTO inmueble) {
		informeComercialDTO.getInmuebles().remove(inmueble);
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Inmuebles", "El inmueble fue borrado.");
		FacesContext.getCurrentInstance().addMessage("growl", msg);
	}

	public void agregarPropietario() {
		if (nuevoPropietario) {
			nuevoPropietario = false;
			verificarCienPorcientoPropietarios();
			BigDecimal numeroPorcentaje = propietario.getPorcentajeDePropiedad();
			propietario.setPorcentajeDePropiedad(numeroPorcentaje.divide(new BigDecimal(100)));
			inmueble.getPropietarios().add(propietario);
			propietario = new PropietarioDTO();
		}else{
			this.nuevoPropietario = true;
			if (inmueble.getPropietarios() == null) {
				inmueble.setPropietarios(new HashSet<PropietarioDTO>());
			}
			if (propietario == null) {
				propietario = new PropietarioDTO();
			}
		}
	}

	private void verificarCienPorcientoPropietarios() {
		BigDecimal porcentajeTotal = new BigDecimal(0);
		for (PropietarioDTO propietarioDTO:inmueble.getPropietarios()) {
			porcentajeTotal = porcentajeTotal.add(propietarioDTO.getPorcentajeDePropiedad());
		}
		if (porcentajeTotal.compareTo(new BigDecimal(100)) == 0) {
			propietariosCienPorciento = true;
		}else {
			propietariosCienPorciento = false;
		}
	}
	
	private void cancelarNuevoPropietario() {
		nuevoPropietario = false;
		propietario = new PropietarioDTO();
	}
	
	public void verificarPorcentajePropiedad(BigDecimal porcentajeActual) {
		BigDecimal porcentajeTotal = new BigDecimal(0);
		for (PropietarioDTO propietarioDTO:inmueble.getPropietarios()) {
			porcentajeTotal = porcentajeTotal.add(propietarioDTO.getPorcentajeDePropiedad());
		}
		porcentajeTotal = porcentajeTotal.add(porcentajeActual);
		if (porcentajeTotal.compareTo(new BigDecimal(100)) > 0) {
			propietariosCienPorciento = true;
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Propietarios del inmueble", "Los propietarios del inmueble exceden el 100 %.");
			FacesContext.getCurrentInstance().addMessage("growl", msg);
		}else{
			propietariosCienPorciento = false;
		}
	}
	
	public void editarPropietario(RowEditEvent event){
		verificarCienPorcientoPropietarios();
	}

	public void cancelarPropietario(RowEditEvent event){
	}
	
	public void borrarPropietario(PropietarioDTO propietario){
		inmueble.getPropietarios().remove(propietario);
		verificarCienPorcientoPropietarios();
	}

	public void agregarViaAccesoPrincipal(Set<ViaAccesoDTO> viasAcceso, ViaAccesoDTO viaAcceso) {
		if (nuevaViaAccesoPrincipal) {
			nuevaViaAccesoPrincipal = false;
			viaAcceso.setAvaluoId(avaluoComercialDTO.getId());
			viaAcceso.setTipo(2);
			viasAcceso.add(viaAcceso);
		}else{
			nuevaViaAccesoPrincipal = true;
			this.viaAccesoPrincipal = new ViaAccesoDTO();
		}
	}
	
	public void agregarViaAccesoSecundaria(Set<ViaAccesoDTO> viasAcceso, ViaAccesoDTO viaAcceso) {
		if (nuevaViaAccesoSecundaria) {
			nuevaViaAccesoSecundaria = false;
			viaAcceso.setAvaluoId(avaluoComercialDTO.getId());
			viaAcceso.setTipo(3);
			viasAcceso.add(viaAcceso);
		}else{
			nuevaViaAccesoSecundaria = true;
			this.viaAccesoSecundaria = new ViaAccesoDTO();
		}
	}
	
	public void agregarViaAcceso(Set<ViaAccesoDTO> viasAcceso, ViaAccesoDTO viaAcceso) {
		if (nuevaViaAcceso) {
			nuevaViaAcceso = false;
			viaAcceso.setAvaluoId(avaluoComercialDTO.getId());
			viaAcceso.setTipo(1);
			viasAcceso.add(viaAcceso);
		}else{
			nuevaViaAcceso = true;
			this.viaAcceso = new ViaAccesoDTO();
		}
	}
	
	public void cancelarNuevaViaAcceso() {
		nuevaViaAcceso = false;
		nuevaViaAccesoPrincipal = false;
		nuevaViaAccesoSecundaria = false;
		viaAcceso = new ViaAccesoDTO();
		viaAccesoPrincipal = new ViaAccesoDTO();
		viaAccesoSecundaria = new ViaAccesoDTO();
	}
	
	public void editarViaAcceso(RowEditEvent event) {
	}
	
	public void cancelarViaAcceso(RowEditEvent event) {
	}
	
	public void borrarViaAcceso(Set<ViaAccesoDTO> viasAcceso, ViaAccesoDTO viaAccesoDTO) {
		viasAcceso.remove(viaAccesoDTO);
	}

	public int ordenarTablaValorComercial(Object tipoArea1, Object tipoArea2) {
		if (tipoArea1.equals(tipoArea2)) {
			return 0;
		}
		if ((tipoArea1.equals(TipoArea.Terreno) && tipoArea2.equals(TipoArea.Construccion))) {
			return -1;
		}
		if (tipoArea1.equals(TipoArea.Construccion) && tipoArea2.equals(TipoArea.Terreno)) {
			return 1;
		}
		return 0;
	}
	
	public void calcularRelacionFrenteFondo() {
		if (informeComercialDTO.getFrente() != null
				&& informeComercialDTO.getFrente().compareTo(BigDecimal.ZERO) > 0
				&& informeComercialDTO.getFondo() != null
				&& informeComercialDTO.getFondo().compareTo(BigDecimal.ZERO) > 0) {
			informeComercialDTO.setRelacionFrenteFondo(informeComercialDTO.getFrente().divide(informeComercialDTO.getFondo(), 2, RoundingMode.HALF_UP));
		}
	}
	
	public void agregarArea(Set<AreaDTO> areas, AreaDTO area, String tipoArea) {
		if (nuevaArea) {
			nuevaArea = false;
			AreaDTO.TipoArea tipoArea2 = TipoArea.fromKey(Integer.parseInt(tipoArea));
			area.setTipoArea(tipoArea2);
			area.setAvaluoId(avaluoComercialDTO.getId());
			areas.add(area);
			avaluoComercialDTO.getAreas().add(area);
			areasValorComercial.add(area);
			areasInmueble.add(area);
			if (tipoArea2.equals(TipoArea.Terreno)) {
				areasValorProporcionalTerreno.add(area);
				if (area.getUnidadDeMedida().equals(UnidadDeMedida.M2)) {
					areaTerreno.getArea().add(area.getArea());
				}else if (area.getUnidadDeMedida().equals(UnidadDeMedida.Hectareas)) {
					areaTerreno.getArea().add(MedidasUtils.
							convertirHectareasAMetros(area.getArea()));
				}else if (area.getUnidadDeMedida().equals(UnidadDeMedida.Fanegadas)) {
					areaTerreno.getArea().add(MedidasUtils.
							convertirFanegadasAMetros(area.getArea()));
				}
			}
			calcularAreaEnMetros(area);
			this.area = new AreaDTO();
		}else{
			nuevaArea = true;
			this.area = new AreaDTO();
		}
	}
	
	public void cancelarNuevaArea(){
		nuevaArea = false;
		area = new AreaDTO();
	}

	public void editarArea(RowEditEvent event) {
		if (event.getObject().getClass().equals(AreaDTO.class)) {
			AreaDTO areaDTO = (AreaDTO) event.getObject();
			calcularAreaEnMetros(areaDTO);
			if (area.getTipoArea().equals(TipoArea.Terreno)) {
				if (area.getUnidadDeMedida().equals(UnidadDeMedida.M2)) {
					areaTerreno.getArea().add(area.getArea());
				}else if (area.getUnidadDeMedida().equals(UnidadDeMedida.Hectareas)) {
					areaTerreno.getArea().add(MedidasUtils.
							convertirHectareasAMetros(area.getArea()));
				}else if (area.getUnidadDeMedida().equals(UnidadDeMedida.Fanegadas)) {
					areaTerreno.getArea().add(MedidasUtils.
							convertirFanegadasAMetros(area.getArea()));
				}
			}
		}
	}

	public void editarArea(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		if (newValue != null && !newValue.equals(oldValue)) {
			FacesContext context = FacesContext.getCurrentInstance();
			AreaConstruccionDTO areaConstruccion = context.getApplication().evaluateExpressionGet(context, "#{areaConstruccion}", AreaConstruccionDTO.class);
			calcularAreaEnMetros(areaConstruccion);
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "La celda cambio", "Nuevo valor: " + newValue);
			FacesContext.getCurrentInstance().addMessage("growl", msg);
		}
	}
	
	private void calcularAreaEnMetros(AreaDTO area) {
		if (area.getArea() != null && area.getUnidadDeMedida() != null && !area.getUnidadDeMedida().equals(UnidadDeMedida.M2)) {
			if (area.getUnidadDeMedida().equals(UnidadDeMedida.Hectareas)) {
				area.setAreaEnMetros(area.getArea().multiply(new BigDecimal(10000)));
			}else if (area.getUnidadDeMedida().equals(UnidadDeMedida.Fanegadas)) {
				area.setAreaEnMetros(area.getArea().multiply(new BigDecimal(6400)));
			}
		}else if (area.getUnidadDeMedida().equals(UnidadDeMedida.M2)) {
			area.setAreaEnMetros(area.getArea());
		}
	}
	
	public void cancelarArea(RowEditEvent event) {
	}

	public void borrarArea(Set<AreaDTO> areas, AreaDTO area) {
		areas.remove(area);
		avaluoComercialDTO.getAreas().remove(area);
		if (areasHabitacionales.contains(area)) {
			areasHabitacionales.remove(area);
		}
		if (areasValorProporcionalTerreno.contains(area)) {
			areasValorProporcionalTerreno.remove(area);
		}
		if (areasValorComercial.contains(area)) {
			areasValorComercial.remove(area);
		}
		if (areasInmueble.contains(area)) {
			areasInmueble.remove(area);
		}
		if (area.getTipoArea().equals(TipoArea.Terreno)) {
			if (area.getUnidadDeMedida().equals(UnidadDeMedida.M2)) {
				areaTerreno.getArea().subtract(area.getArea());
			}else if (area.getUnidadDeMedida().equals(UnidadDeMedida.Hectareas)) {
				areaTerreno.getArea().subtract(MedidasUtils.
						convertirHectareasAMetros(area.getArea()));
			}else if (area.getUnidadDeMedida().equals(UnidadDeMedida.Fanegadas)) {
				areaTerreno.getArea().subtract(MedidasUtils.
						convertirFanegadasAMetros(area.getArea()));
			}
		}
	}
	
	public void editarAreaRazonable(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		if (newValue != null && !newValue.equals(oldValue)) {
			AreaDTO areaEditada = areasValorComercial.get(event.getRowIndex());
			areaEditada.setValorTotal(calcularAreaTotal(areaEditada));
			calcularAreaEnMetros(areaEditada);
			calcularValores();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "La celda cambio", "Nuevo valor: " + newValue);
			FacesContext.getCurrentInstance().addMessage("growl", msg);
		}
	}
	
	public BigDecimal calcularAreaTotal(AreaDTO area) {
		return area.getValorUnitario().multiply(area.getArea());
	}

	public void calcularValores() {
		// Inicializar subtotales
		if (!areasNoConstruidas.isEmpty() || areaTerreno != null) {
			subtotalAreasTerreno = new BigDecimal(0);
			subtotalPorcentajeAreasTerreno = new BigDecimal(0);
		}
		if (!areasConstruidas.isEmpty()) {
			subtotalAreasConstruidas = new BigDecimal(0);
			subtotalPorcentajeAreasConstruidas = new BigDecimal(0);
		}
		// Calcular subtotales y sumas de areas
		BigDecimal sumaAreasTerreno = new BigDecimal(0);
		BigDecimal sumaTotalesTerreno = new BigDecimal(0);
		BigDecimal sumaAreasConstruccion = new BigDecimal(0);
		BigDecimal sumaTotalesHabitacionales = new BigDecimal(0);
		for (AreaDTO area: areasValorComercial) {
			if (area.getValorTotal() != null) {
				if (area.getTipoArea().equals(TipoArea.Terreno)) {
					subtotalAreasTerreno = subtotalAreasTerreno.add(area
							.getValorTotal());
					sumaAreasTerreno = sumaAreasTerreno.add(area
							.getArea());
					sumaTotalesTerreno = sumaTotalesTerreno.add(area.getValorTotal());
				} else if (area.getTipoArea().equals(TipoArea.Construccion)) {
					subtotalAreasConstruidas = subtotalAreasConstruidas
							.add(area.getValorTotal());
					sumaAreasConstruccion = sumaAreasConstruccion.add(area
							.getArea());
					sumaTotalesTerreno = sumaTotalesTerreno.add(area.getValorTotal());
					calcularValorResidualConstruccion(area);
				}
			}
		}
		// Calcular valor comercial
		BigDecimal valorComercialInmueble = new BigDecimal(0);
		if (subtotalAreasTerreno != null && subtotalAreasTerreno.compareTo(BigDecimal.ZERO) > 0) {
			valorComercialInmueble = valorComercialInmueble.add(subtotalAreasTerreno);
		}
		if (subtotalAreasConstruidas != null && subtotalAreasConstruidas.compareTo(BigDecimal.ZERO) > 0) {
			valorComercialInmueble = valorComercialInmueble.add(subtotalAreasConstruidas);
		}
		informeComercialDTO.setValorComercialDelInmueble(valorComercialInmueble);
		if (sumaAreasTerreno.compareTo(BigDecimal.ZERO) > 0) {
			informeComercialDTO
					.setValorIntegralSobreElTerreno(valorComercialInmueble
							.divide(sumaAreasTerreno, 4, RoundingMode.HALF_UP));
		}
		if (sumaAreasConstruccion.compareTo(BigDecimal.ZERO) > 0) {
			informeComercialDTO.setValorIntegralSobreElConstruccion(valorComercialInmueble.divide(sumaAreasConstruccion, 4, RoundingMode.HALF_UP));
		}
		// Calcular porcentajes de las areas
		for (AreaDTO area: areasValorComercial) {
			calcularPorcentajesArea(area);
			if (area.getPorcentaje() != null) {
				if (area.getTipoArea().equals(TipoArea.Terreno)) {
					subtotalPorcentajeAreasTerreno = subtotalPorcentajeAreasTerreno
							.add(area.getPorcentaje());
					calcularPorcentajePorpocionalTerreno(area, sumaTotalesTerreno);
				} else if (area.getTipoArea().equals(TipoArea.Construccion)) {
					subtotalPorcentajeAreasConstruidas = subtotalPorcentajeAreasConstruidas
							.add(area.getPorcentaje());
					calcularPorcentajePorpocionalHabitacional(area, sumaTotalesHabitacionales);
					calcularPorcentajePorpocionalTerreno(area, sumaTotalesTerreno);
				}
			}
		}
	}
	
	private void calcularPorcentajesArea(AreaDTO areaEditada) {
		BigDecimal porcentaje = new BigDecimal(0);
		BigDecimal valorComercialInmueble = informeComercialDTO.getValorComercialDelInmueble();
		if (valorComercialInmueble.compareTo(BigDecimal.ZERO) > 0 && areaEditada.getValorTotal() != null) {
			porcentaje = areaEditada.getValorTotal().divide(
					valorComercialInmueble, 15, RoundingMode.HALF_UP);
		}
		areaEditada.setPorcentaje(porcentaje);
	}
	
	private void calcularPorcentajePorpocionalHabitacional(AreaDTO areaEditada, BigDecimal sumaTotalesHabitacionales) {
		BigDecimal porcentajeProporcionalHabitacional = new BigDecimal(0);
		if (sumaTotalesHabitacionales.compareTo(BigDecimal.ZERO) > 0 && areaEditada.getValorTotal() != null) {
			porcentajeProporcionalHabitacional = areaEditada.getValorTotal().divide(
					sumaTotalesHabitacionales, 15, RoundingMode.HALF_UP);
		}
		areaEditada.setPorcentajeValorProporcionalConstruccion(porcentajeProporcionalHabitacional);
	}
	
	private void calcularPorcentajePorpocionalTerreno(AreaDTO areaEditada, BigDecimal sumaTotalesTerreno) {
		BigDecimal porcentajeProporcionalTerreno = new BigDecimal(0);
		if (sumaTotalesTerreno.compareTo(BigDecimal.ZERO) > 0 && areaEditada.getValorTotal() != null) {
			porcentajeProporcionalTerreno = areaEditada.getValorTotal().divide(
					sumaTotalesTerreno, 15, RoundingMode.HALF_UP);
		}
		areaEditada.setPorcentajeValorProporcionalTerreno(porcentajeProporcionalTerreno);
	}

	public void calcularSubtotales(Object object) {
		TipoArea tipoArea = (TipoArea) object;
		if (tipoArea.equals(TipoArea.Terreno)) {
			subtotalArea = subtotalAreasTerreno;
			subtotalPorcentajeArea =  subtotalPorcentajeAreasTerreno;
		}else if (tipoArea.equals(TipoArea.Construccion)) {
			subtotalArea = subtotalAreasConstruidas;
			subtotalPorcentajeArea =  subtotalPorcentajeAreasConstruidas;
		}
	}
	
	public void editarAreaReposicion(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		if (newValue != null && !newValue.equals(oldValue)) {
			AreaDTO areaEditada = (AreaDTO) areasHabitacionales.toArray()[event.getRowIndex()];
			calcularCostoTotalReposicion(areaEditada);
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "La celda cambio", "Nuevo valor: " + newValue);
			FacesContext.getCurrentInstance().addMessage("growl", msg);
		}
	}
	
	public void calcularCostoTotalReposicion(AreaDTO areaEditada) {
		if (areaEditada.getArea() != null) {
			if (areaEditada.getTipoArea().equals(TipoArea.Terreno)
					&& areaEditada.getPorcentajeValorProporcionalTerreno() != null) {
				areaEditada
						.setCostoTotalReposicion(areaEditada
								.getValorReposicion()
								.multiply(areaEditada.getArea())
								.multiply(
										areaEditada
												.getPorcentajeValorProporcionalTerreno()));
			}
			if (areaEditada.getTipoArea().equals(TipoArea.Construccion)
					&& areaEditada.getPorcentajeValorProporcionalConstruccion() != null) {
				areaEditada
						.setCostoTotalReposicion(areaEditada
								.getValorReposicion()
								.multiply(areaEditada.getArea())
								.multiply(
										areaEditada
												.getPorcentajeValorProporcionalConstruccion()));
			}
		}
	}
	
	public void calcularValoresResiduales() {
		valorResidualParaLaConstruccion = new BigDecimal(0);
		if (porcentajeFinal != null && porcentajeFinal.compareTo(BigDecimal.ZERO) > 0) {
			for (AreaConstruccionDTO areaConstruccionDTO : areasHabitacionales) {
				calcularValorResidualConstruccion(areaConstruccionDTO);
				valorResidualParaLaConstruccion = valorResidualParaLaConstruccion.add(areaConstruccionDTO.getValorResidualConstruccion());
			}
		}
	}
	
	public void calcularValorResidualConstruccion(AreaDTO areaEditada) {
		if (areaEditada.getValorTotal() != null && porcentajeFinal != null) {
			areaEditada.setValorResidualConstruccion(areaEditada.getValorTotal().multiply(porcentajeFinal));
		}
	}
	
	public void agregarMetodoValuacion(){
		if (nuevoMetodoValuacion) {
			nuevoMetodoValuacion = false;
			metodoValuacion.setAvaluoId(avaluoComercialDTO.getId());
			informeComercialDTO.getMetodosValuacion().add(metodoValuacion);
		}else{
			nuevoMetodoValuacion = true;
			if (informeComercialDTO.getMetodosValuacion() == null) {
				informeComercialDTO.setMetodosValuacion(new HashSet<MetodoValuacionDTO>());
			}
			metodoValuacion = new MetodoValuacionDTO();
		}
	}
	
	public void cancelarNuevoMetodoValuacion() {
		nuevoMetodoValuacion = false;
	}
	
	public void editarMetodoDeValuacion(RowEditEvent event){
	}
	
	public void cancelarMetodoDeValuacion(RowEditEvent event){
	}
	
	public void borrarMetodoValuacion(MetodoValuacionDTO metodoValuacion){
		informeComercialDTO.getMetodosValuacion().remove(metodoValuacion);
	}	

	public void cambioRegular() {
		informeComercialDTO.setIrregular(!informeComercialDTO.getRegular());
	}
	
	public void onDepartamentoChanged(String origen, String departamento) {
		if (origen.equals("inmueble")) {
			ciudadesInmueble = listasGeograficasController.ciudadesEnDepartamento(departamento);
		}else if (origen.equals("solicitante")) {
			ciudadesSolicitante = listasGeograficasController.ciudadesEnDepartamento(departamento);
		}else if (origen.equals("predio")) {
			ciudadesPredio = listasGeograficasController.ciudadesEnDepartamento(departamento);
		}
	}

	public void calcularVetustez() {
		int nuevaVetusdez = informeHipotecarioController.actualizarVetusdez(new Date(System.currentTimeMillis()), areaConstruccion.getAnoDeConstruccion());
		areaConstruccion.setVetustez(new BigDecimal(nuevaVetusdez));
		informeComercialDTO.setVidaDelInmuebleConstruccion(nuevaVetusdez);
	}
	
	public void calcularVidaRemanente() {
		if (informeComercialDTO.getVidaDelInmuebleConstruccion() != null) {
			informeComercialDTO.setVidaRemanenteConstruccion(informeComercialDTO
					.getVidaUtilConstruccion()
					- informeComercialDTO.getVidaDelInmuebleConstruccion());
		}
	}

	public void enviarAvaluo() {
        log.debug("Enviando informe del avaluo  {}",
                avaluoComercialDTO);
		//onDireccionInmuebleChanged();
		//onDireccionSolicitanteChanged();
		String informeEnviado = informeComercialController.enviarAvaluo(
				avaluoComercialDTO,	inmueblesOriginales, viasAccesoOriginales,
				null,	null,
				areasOriginales, null,
				metodosValuacionOriginales)
				.getCodigoExterno();
		if (informeEnviado == null) {
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Error", "No se encontró el avaluo"));
		}else {
			listadoAvaluosBean.setInformeEnviado(informeEnviado);
		}
		String uri = FacesContext.getCurrentInstance().getExternalContext().encodeActionURL(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/pages/avaluos/listadoAvaluos.xhtml");
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(uri);
		} catch (IOException e) {
			// TODO Reireccionar a página no encontrada
			e.printStackTrace();
		}
	}

	public void guardarSinEnviar() {
        log.debug("Guardando sin enviar informe comercial {}",
                avaluoComercialDTO);
		String direccionCliente = avaluoController.onDireccionChanged(
				tipoViaCliente, numeroViaCliente,
				convertirAString(complementoViaCliente),
				convertirAString(complementoViaGeneradoraCliente),
				numeroViaGeneradoraCliente, placaCliente,
				complementoPlacaCliente);
		avaluoComercialDTO.getCliente().setDireccionDeContactoSolicitante(direccionCliente);
		String stringComplementoVia = convertirAString(complementoVia);
		String stringComplementoViaGeneradora = convertirAString(complementoViaGeneradora);
		String direccionInmueble = avaluoController.onDireccionChanged(avaluoComercialDTO.getTipoVia(),
				avaluoComercialDTO.getNumeroVia(), stringComplementoVia,
				stringComplementoViaGeneradora, avaluoComercialDTO.getNumeroViaGeneradora(),
				avaluoComercialDTO.getPlaca(), avaluoComercialDTO.getComplementoPlaca());
		avaluoComercialDTO.setComplementoVia(stringComplementoVia);
		avaluoComercialDTO.setComplementoViaGeneradora(stringComplementoViaGeneradora);
		avaluoComercialDTO.setDireccionInmueble(direccionInmueble);
		String informeGuardado = null;
		informeComercialDTO.setViasAcceso(Sets.union(viasAccesoInmueble, viasAccesoPrincipal));
		informeComercialDTO.setViasAcceso(Sets.union(informeComercialDTO.getViasAcceso(), viasAccesoSecundario));
		try {
			informeGuardado = informeComercialController.guardarSinEnviar(avaluoComercialDTO,
					inmueblesOriginales, viasAccesoOriginales, null,
					null, areasOriginales, null, metodosValuacionOriginales)
					.getCodigoExterno();
		} catch (AvaluoNotFoundException e) {
            log.debug("No se encontró el avaluo {}",
                    avaluoComercialDTO);
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Error", "No se encontró el avaluo"));
		}
		if ( informeGuardado == null) {
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Error", "No se encontró el avaluo"));
		}else {
			listadoAvaluosBean.setInformeGuardado(informeGuardado);
		}
		String uri = FacesContext.getCurrentInstance().getExternalContext().encodeActionURL(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/pages/avaluos/listadoAvaluos.xhtml");
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(uri);
		} catch (IOException e) {
			// TODO Reireccionar a página no encontrada
			e.printStackTrace();
		}
	}

	public void liquidarAvaluo() {
		log.debug("Liquidando avaluo  {}",
avaluoComercialDTO);
		String uri = FacesContext.getCurrentInstance().getExternalContext().encodeActionURL(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/pages/avaluos/liquidarAvaluo.xhtml");
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(uri);
		} catch (IOException e) {
			// TODO Reireccionar a página no encontrada
			e.printStackTrace();
		}
	}

	public void solcitarCorreciones() {
        log.debug("Solicitando correciones del avaluo  {}",
                avaluoComercialDTO);
		//onDireccionInmuebleChanged();
		//onDireccionSolicitanteChanged();
		avaluoController.solicitarCorreciones(avaluoComercialDTO, (UsuarioDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal(), correcciones);
		String uri = FacesContext.getCurrentInstance().getExternalContext().encodeActionURL(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/pages/avaluos/listadoAvaluos.xhtml");
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(uri);
		} catch (IOException e) {
			// TODO Reireccionar a página no encontrada
			e.printStackTrace();
		}
	}

	public AvaluoComercialDTO getAvaluoComercialDTO() {
		return avaluoComercialDTO;
	}

	public void setAvaluoComercialDTO(AvaluoComercialDTO avaluoComercialDTO) {
		this.avaluoComercialDTO = avaluoComercialDTO;
	}

	public FormatoInformeComercialDTO getInformeComercialDTO() {
		return informeComercialDTO;
	}

	public void setInformeComercialDTO(FormatoInformeComercialDTO informeComercialDTO) {
		this.informeComercialDTO = informeComercialDTO;
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

	public List<DivipolaDTO> getCiudadesPredio() {
		return ciudadesPredio;
	}

	public void setCiudadesPredio(List<DivipolaDTO> ciudadesPredio) {
		this.ciudadesPredio = ciudadesPredio;
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
		return centroMapa;
	}

	public void setCentroMapa(String centroMapa) {
		this.centroMapa = centroMapa;
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

	public SortedMap<String, String> getSegmentos() {
		return segmentos;
	}

	public void setSegmentos(SortedMap<String, String> segmentos) {
		this.segmentos = segmentos;
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
	
	public String getCorrecciones() {
		return correcciones;
	}
	
	public void setCorrecciones(String correcciones) {
		this.correcciones = correcciones;
	}

	public String getNombresApellidosCliente() {
		return nombresApellidosCliente;
	}

	public void setNombresApellidosCliente(String nombresApellidosCliente) {
		this.nombresApellidosCliente = nombresApellidosCliente;
	}

	public String getTipoViaCliente() {
		return tipoViaCliente;
	}

	public void setTipoViaCliente(String tipoViaCliente) {
		this.tipoViaCliente = tipoViaCliente;
	}

	public String getNumeroViaCliente() {
		return numeroViaCliente;
	}

	public void setNumeroViaCliente(String numeroViaCliente) {
		this.numeroViaCliente = numeroViaCliente;
	}

	public List<String> getComplementoViaCliente() {
		return complementoViaCliente;
	}

	public void setComplementoViaCliente(List<String> complementoViaCliente) {
		this.complementoViaCliente = complementoViaCliente;
	}

	public String getNumeroViaGeneradoraCliente() {
		return numeroViaGeneradoraCliente;
	}

	public void setNumeroViaGeneradoraCliente(String numeroViaGeneradoraCliente) {
		this.numeroViaGeneradoraCliente = numeroViaGeneradoraCliente;
	}

	public List<String> getComplementoViaGeneradoraCliente() {
		return complementoViaGeneradoraCliente;
	}

	public void setComplementoViaGeneradoraCliente(
			List<String> complementoViaGeneradoraCliente) {
		this.complementoViaGeneradoraCliente = complementoViaGeneradoraCliente;
	}

	public String getPlacaCliente() {
		return placaCliente;
	}

	public void setPlacaCliente(String placaCliente) {
		this.placaCliente = placaCliente;
	}

	public String getComplementoPlacaCliente() {
		return complementoPlacaCliente;
	}

	public void setComplementoPlacaCliente(String complementoPlacaCliente) {
		this.complementoPlacaCliente = complementoPlacaCliente;
	}

	public String getAdicionalDireccionCliente() {
		return adicionalDireccionCliente;
	}

	public void setAdicionalDireccionCliente(String adicionalDireccionCliente) {
		this.adicionalDireccionCliente = adicionalDireccionCliente;
	}

	public MapModel getMapModel() {
		return mapModel;
	}

	public void setMapModel(MapModel mapModel) {
		this.mapModel = mapModel;
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

	public Set<Integer> getDocumentosConsultados() {
		return documentosConsultados;
	}

	public void setDocumentosConsultados(Set<Integer> documentosConsultados) {
		this.documentosConsultados = documentosConsultados;
		informeComercialDTO.setDocumentosConsultados(documentosConsultados.toString().replace("[", "").replace("]", ""));
	}

	public boolean isOtrosDocumentos() {
		return otrosDocumentos;
	}

	public void setOtrosDocumentos(boolean otrosDocumentos) {
		this.otrosDocumentos = otrosDocumentos;
	}

	public boolean isMatriculaInmobiliariaTieneavaluos() {
		return matriculaInmobiliariaTieneavaluos;
	}

	public void setMatriculaInmobiliariaTieneavaluos(
			boolean matriculaInmobiliariaTieneavaluos) {
		this.matriculaInmobiliariaTieneavaluos = matriculaInmobiliariaTieneavaluos;
	}

	public boolean isCedulaCatastralTieneavaluos() {
		return cedulaCatastralTieneavaluos;
	}

	public void setCedulaCatastralTieneavaluos(boolean cedulaCatastralTieneavaluos) {
		this.cedulaCatastralTieneavaluos = cedulaCatastralTieneavaluos;
	}

	public boolean isNuevoInmueble() {
		return nuevoInmueble;
	}

	public void setNuevoInmueble(boolean nuevoInmueble) {
		this.nuevoInmueble = nuevoInmueble;
	}

	public boolean isEditarInmueble() {
		return editarInmueble;
	}

	public void setEditarInmueble(boolean editarInmueble) {
		this.editarInmueble = editarInmueble;
	}

	public InmuebleDTO getInmueble() {
		return inmueble;
	}

	public void setInmueble(InmuebleDTO inmueble) {
		this.inmueble = inmueble;
	}

	public boolean isNuevoPropietario() {
		return nuevoPropietario;
	}

	public void setNuevoPropietario(boolean nuevoPropietario) {
		this.nuevoPropietario = nuevoPropietario;
	}

	public PropietarioDTO getPropietario() {
		return propietario;
	}

	public void setPropietario(PropietarioDTO propietario) {
		this.propietario = propietario;
	}

	public boolean isPropietariosCienPorciento() {
		return propietariosCienPorciento;
	}

	public void setPropietariosCienPorciento(boolean propietariosCienPorciento) {
		this.propietariosCienPorciento = propietariosCienPorciento;
	}

	public Set<ViaAccesoDTO> getViasAccesoPrincipal() {
		return viasAccesoPrincipal;
	}

	public void setViasAccesoPrincipal(Set<ViaAccesoDTO> viasAccesoPrincipal) {
		this.viasAccesoPrincipal = viasAccesoPrincipal;
	}

	public Set<ViaAccesoDTO> getViasAccesoSecundario() {
		return viasAccesoSecundario;
	}

	public void setViasAccesoSecundario(Set<ViaAccesoDTO> viasAccesoSecundario) {
		this.viasAccesoSecundario = viasAccesoSecundario;
	}	

	public Set<ViaAccesoDTO> getViasAccesoInmueble() {
		return viasAccesoInmueble;
	}

	public void setViasAccesoInmueble(Set<ViaAccesoDTO> viasAccesoInmueble) {
		this.viasAccesoInmueble = viasAccesoInmueble;
	}

	public boolean isNuevaViaAcceso() {
		return nuevaViaAcceso;
	}

	public void setNuevaViaAcceso(boolean nuevaViaAcceso) {
		this.nuevaViaAcceso = nuevaViaAcceso;
	}

	public boolean isNuevaViaAccesoPrincipal() {
		return nuevaViaAccesoPrincipal;
	}

	public void setNuevaViaAccesoPrincipal(boolean nuevaViaAccesoPrincipal) {
		this.nuevaViaAccesoPrincipal = nuevaViaAccesoPrincipal;
	}

	public boolean isNuevaViaAccesoSecundaria() {
		return nuevaViaAccesoSecundaria;
	}

	public void setNuevaViaAccesoSecundaria(boolean nuevaViaAccesoSecundaria) {
		this.nuevaViaAccesoSecundaria = nuevaViaAccesoSecundaria;
	}

	public ViaAccesoDTO getViaAcceso() {
		return viaAcceso;
	}

	public void setViaAcceso(ViaAccesoDTO viaAcceso) {
		this.viaAcceso = viaAcceso;
	}

	public Set<AreaDTO> getAreasInmueble() {
		return areasInmueble;
	}

	public void setAreasInmueble(Set<AreaDTO> areasInmueble) {
		this.areasInmueble = areasInmueble;
	}

	public ViaAccesoDTO getViaAccesoPrincipal() {
		return viaAccesoPrincipal;
	}

	public void setViaAccesoPrincipal(ViaAccesoDTO viaAccesoPrincipal) {
		this.viaAccesoPrincipal = viaAccesoPrincipal;
	}

	public ViaAccesoDTO getViaAccesoSecundaria() {
		return viaAccesoSecundaria;
	}

	public void setViaAccesoSecundaria(ViaAccesoDTO viaAccesoSecundaria) {
		this.viaAccesoSecundaria = viaAccesoSecundaria;
	}

	public Set<AreaDTO> getAreasNoConstruidas() {
		return areasNoConstruidas;
	}

	public void setAreasNoConstruidas(Set<AreaDTO> areasNoConstruidas) {
		this.areasNoConstruidas = areasNoConstruidas;
	}

	public Set<AreaDTO> getAreasConstruidas() {
		return areasConstruidas;
	}

	public void setAreasConstruidas(Set<AreaDTO> areasConstruidas) {
		this.areasConstruidas = areasConstruidas;
	}

	public Set<AreaDTO> getAreasValorProporcionalTerreno() {
		return areasValorProporcionalTerreno;
	}

	public void setAreasValorProporcionalTerreno(
			Set<AreaDTO> areasValorProporcionalTerreno) {
		this.areasValorProporcionalTerreno = areasValorProporcionalTerreno;
	}

	public List<AreaDTO> getAreasValorComercial() {
		return areasValorComercial;
	}

	public void setAreasValorComercial(List<AreaDTO> areasValorComercial) {
		this.areasValorComercial = areasValorComercial;
	}

	public Set<AreaConstruccionDTO> getAreasHabitacionales() {
		return areasHabitacionales;
	}

	public void setAreasHabitacionales(Set<AreaConstruccionDTO> areasHabitacionales) {
		this.areasHabitacionales = areasHabitacionales;
	}

	public AreaDTO getAreaTerreno() {
		return areaTerreno;
	}

	public void setAreaTerreno(AreaDTO areaTerreno) {
		this.areaTerreno = areaTerreno;
	}

	public boolean isNuevaArea() {
		return nuevaArea;
	}

	public void setNuevaArea(boolean nuevaArea) {
		this.nuevaArea = nuevaArea;
	}

	public AreaDTO getArea() {
		return area;
	}

	public void setArea(AreaDTO area) {
		this.area = area;
	}

	public AreaConstruccionDTO getAreaConstruccion() {
		return areaConstruccion;
	}

	public void setAreaConstruccion(AreaConstruccionDTO areaConstruccion) {
		this.areaConstruccion = areaConstruccion;
	}

	public boolean isNuevoMetodoValuacion() {
		return nuevoMetodoValuacion;
	}

	public void setNuevoMetodoValuacion(boolean nuevoMetodoValuacion) {
		this.nuevoMetodoValuacion = nuevoMetodoValuacion;
	}

	public MetodoValuacionDTO getMetodoValuacion() {
		return metodoValuacion;
	}

	public void setMetodoValuacion(MetodoValuacionDTO metodoValuacion) {
		this.metodoValuacion = metodoValuacion;
	}

	public AvaluoController getAvaluoController() {
		return avaluoController;
	}

	public void setAvaluoController(AvaluoController avaluoController) {
		this.avaluoController = avaluoController;
	}

	public InformeComercialController getInformeComercialController() {
		return informeComercialController;
	}

	public void setInformeComercialController(
			InformeComercialController informeComercialController) {
		this.informeComercialController = informeComercialController;
	}

	public ListasGeograficasController getListasGeograficasController() {
		return listasGeograficasController;
	}

	public void setListasGeograficasController(
			ListasGeograficasController listasGeograficasController) {
		this.listasGeograficasController = listasGeograficasController;
	}

	public ListadoAvaluosBean getListadoAvaluosBean() {
		return listadoAvaluosBean;
	}

	public void setListadoAvaluosBean(ListadoAvaluosBean listadoAvaluosBean) {
		this.listadoAvaluosBean = listadoAvaluosBean;
	}

	public BigDecimal getValorResidualParaLaConstruccion() {
		return valorResidualParaLaConstruccion;
	}

	public void setValorResidualParaLaConstruccion(
			BigDecimal valorResidualParaLaConstruccion) {
		this.valorResidualParaLaConstruccion = valorResidualParaLaConstruccion;
	}

	public BigDecimal getSubtotalArea() {
		return subtotalArea;
	}

	public BigDecimal getSubtotalPorcentajeArea() {
		return subtotalPorcentajeArea;
	}

	public BigDecimal getPorcentajeFinal() {
		return porcentajeFinal;
	}

	public void setPorcentajeFinal(BigDecimal porcentajeFinal) {
		this.porcentajeFinal = porcentajeFinal;
	}
	
	public List<TipoInmuebleDTO> getTiposInmueble() {
		return tiposInmueble;
	}
	
	public AreaDTO.UnidadDeMedida[] getUnidadesDeMedida(){
		return AreaDTO.UnidadDeMedida.values();
	}
	
	public AvaluoDTO.MBR[] getMbr(){
		return AvaluoDTO.MBR.values();
	}
}
