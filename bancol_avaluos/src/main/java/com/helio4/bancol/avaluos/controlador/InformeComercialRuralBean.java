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
import com.helio4.bancol.avaluos.dominio.DateUtils;
import com.helio4.bancol.avaluos.dominio.InformeComercialController;
import com.helio4.bancol.avaluos.dominio.InformeHipotecarioController;
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
import com.helio4.bancol.avaluos.dto.ExplotacionEconomicaDTO;
import com.helio4.bancol.avaluos.dto.FormatoInformeComercialDTO;
import com.helio4.bancol.avaluos.dto.InmuebleDTO;
import com.helio4.bancol.avaluos.dto.MetodoValuacionDTO;
import com.helio4.bancol.avaluos.dto.PropietarioDTO;
import com.helio4.bancol.avaluos.dto.ServidumbreDTO;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;
import com.helio4.bancol.avaluos.dto.ViaAccesoDTO;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;
import com.helio4.bancol.avaluos.servicio.util.Constantes;

@Controller
@Scope("view")
@Qualifier("informeComercialRuralBean")
public class InformeComercialRuralBean implements Serializable{
	
	private static Logger log = LoggerFactory.getLogger( InformeComercialRuralBean.class );
	
	private static final long serialVersionUID = 1L;
	private AvaluoComercialDTO avaluoComercialDTO;
	private FormatoInformeComercialDTO informeComercialDTO;
	private ImmutableSet<InmuebleDTO> lotesOriginales;
	private ImmutableSet<ViaAccesoDTO> viasAccesoOriginales;
	private ImmutableSet<ServidumbreDTO> servidumbresOriginales;
	private ImmutableSet<ExplotacionEconomicaDTO> explotacionesEconomicasOriginales;
	private ImmutableSet<AreaDTO> areasOriginales;
	private ImmutableSet<AreaConstruccionDTO> areasConstruccionOriginales;
	private ImmutableSet<MetodoValuacionDTO> metodosValuacionOriginales;
	
	private SortedMap<String,String> departamentos;
	private List<DivipolaDTO> ciudadesSolicitante;
	private List<DivipolaDTO> ciudadesPredio;
	private List<DivipolaDTO> ciudadesLote;
	private SortedMap<String, String> paises;
	private String centroMapa;
	private List<EntidadDTO> entidades;
	private List<String> sucursales;
	private SortedMap<String, String> segmentos;

	private String departamentoInmueble;
	
	private boolean avaluoEnRevision;
	private boolean avaluoEnCorrecion;
	private String correcciones;
	
	private String nombresApellidosCliente;
	private String departamentoCliente;
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
	
	private boolean nuevoLote;
	private boolean editarLote;
	private InmuebleDTO lote;
	private boolean nuevoPropietario;
	private PropietarioDTO propietario;
	private boolean propietariosCienPorciento;
	
	private Set<ViaAccesoDTO> viasAccesoSector;
	private Set<ViaAccesoDTO> viasAccesoInmueble;
	
	private boolean nuevaViaAcceso;
	private ViaAccesoDTO viaAcceso;
	private boolean nuevaServidumbre;
	private ServidumbreDTO servidumbre;
	private boolean nuevaExplotacionEconomica;
	private ExplotacionEconomicaDTO explotacionEconomica;
	private Set<AreaDTO> areasTerreno;
	private Set<AreaDTO> areasInfraestructura;
	private Set<AreaDTO> cultivosAvaluables;
	private Set<AreaDTO> areasValorProporcionalTerreno;
	private List<AreaDTO> areasValorComercial;
	private Set<AreaConstruccionDTO> areasHabitacionales;
	
	private boolean nuevaArea;
	private AreaDTO area;
	private boolean nuevaAreaConstruccion;
	private boolean editarAreaConstruccion;
	private AreaConstruccionDTO areaConstruccion;
	private boolean nuevoMetodoValuacion;
	private MetodoValuacionDTO metodoValuacion;
	private BigDecimal subtotalAreasTerreno;
	private BigDecimal subtotalAreasConstruidas;
	private BigDecimal subtotalAreasInfraestructura;
	private BigDecimal subtotalCultivosAvaluables;
	private BigDecimal valorResidualParaLaConstruccion;
	private BigDecimal subtotalPorcentajeAreasTerreno;
	private BigDecimal subtotalPorcentajeAreasConstruidas;
	private BigDecimal subtotalPorcentajeAreasInfraestructura;
	private BigDecimal subtotalPorcentajeCultivosAvaluables;
	
	private BigDecimal subtotalArea;
	private BigDecimal subtotalPorcentajeArea;
	
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
	
	@PostConstruct
	public void init() {
		avaluoComercialDTO = (AvaluoComercialDTO) listadoAvaluosBean.getAvaluo();
		informeComercialDTO = (FormatoInformeComercialDTO) avaluoComercialDTO.getFormatoInforme();
		if (informeComercialDTO.getInmuebles() != null) {
			lotesOriginales = ImmutableSet.copyOf(informeComercialDTO
					.getInmuebles());
		}
		if (informeComercialDTO.getViasAcceso() != null) {
			viasAccesoOriginales = ImmutableSet.copyOf(informeComercialDTO.getViasAcceso());
		}
		if (informeComercialDTO.getServidumbrez() != null) {
			servidumbresOriginales = ImmutableSet.copyOf(informeComercialDTO.getServidumbrez());
		}
		if (informeComercialDTO.getExplotacionesEconomicas() != null) {
			explotacionesEconomicasOriginales = ImmutableSet.copyOf(informeComercialDTO.getExplotacionesEconomicas());
		}
		if (avaluoComercialDTO.getAreas() != null) {
			areasOriginales = ImmutableSet.copyOf(avaluoComercialDTO.getAreas());
		}
		if (informeComercialDTO.getAreasConstruccion() != null) {
			areasConstruccionOriginales = ImmutableSet.copyOf(informeComercialDTO.getAreasConstruccion());
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

		if (avaluoComercialDTO.getCliente().getDivipola() != null) {
			departamentoCliente = avaluoComercialDTO.getCliente().getDivipola().getDepartamento();
		}
		separarDireccion(avaluoComercialDTO.getCliente().getDireccionDeContactoSolicitante());
		if (avaluoComercialDTO.getDivipola() != null) {
			departamentoInmueble = avaluoComercialDTO.getDivipola().getDepartamento();
		}
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
		
		cargarInformacionLotes();
		separarViasAcceso();
		separarAreas();
		if (avaluoEnCorrecion) {
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('correcionesDialog').show();");
		}
		
		// inicializar listas de parametros desde la base de datos
		entidades = avaluoController.entidades();
		sucursales = avaluoController.sucursales();
		log.debug("Inicializando listas de departamentos desde la base de datos");
		departamentos = listasGeograficasController.departamentos();
		ciudadesSolicitante = listasGeograficasController.ciudadesPorDepartamento(departamentoCliente);
		ciudadesPredio = listasGeograficasController.ciudadesPorDepartamento(departamentoInmueble);
		ciudadesLote = listasGeograficasController.ciudadesPorDepartamento(departamentoInmueble);
		// Inicializar lista de paises
		log.debug("Inicializando lista de paises.");
		paises = listasGeograficasController.paises();
	}
	
	private void cargarInformacionLotes() {
		if (informeComercialDTO.getInmuebles() == null) {
			informeComercialDTO.setInmuebles(new HashSet<InmuebleDTO>());
		}else{
			for (InmuebleDTO inmueble:informeComercialDTO.getInmuebles()) {
				inmueble.setMapModel(new DefaultMapModel());
				if (inmueble.getLatitud() != null && inmueble.getLongitud() != null) {
					LatLng coordenadas = new LatLng(inmueble.getLatitud().doubleValue(), inmueble.getLongitud().doubleValue());
					Marker marker = new Marker(coordenadas, "Lote: "+inmueble.getMatriculaInmobiliaria());
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
		areasTerreno = new HashSet<AreaDTO>();
		areasInfraestructura = new HashSet<AreaDTO>();
		cultivosAvaluables = new HashSet<AreaDTO>();
		areasValorProporcionalTerreno = new HashSet<AreaDTO>();
		areasHabitacionales = new HashSet<AreaConstruccionDTO>();
		areasValorComercial = new ArrayList<AreaDTO>();
		
		if (avaluoComercialDTO.getAreas() == null) {
			avaluoComercialDTO.setAreas(new HashSet<AreaDTO>());
		}else{
			for (AreaDTO areaDTO:avaluoComercialDTO.getAreas()) {
				TipoArea tipo = areaDTO.getTipoArea();
				if (tipo.equals(TipoArea.Terreno)) {
					areasTerreno.add(areaDTO);
				}else if (tipo.equals(TipoArea.Construccion)) {
					informeComercialDTO.getAreasConstruccion().add((AreaConstruccionDTO) areaDTO);
				}else if (tipo.equals(TipoArea.Infraestructura)) {
					areasInfraestructura.add(areaDTO);
				}else if (tipo.equals(TipoArea.CultivoAvaluable)) {
					cultivosAvaluables.add(areaDTO);
				}
			}
			if (!areasTerreno.isEmpty() || !informeComercialDTO.getAreasConstruccion().isEmpty()) {
				areasValorProporcionalTerreno.addAll(areasTerreno);
				areasValorProporcionalTerreno.addAll(informeComercialDTO.getAreasConstruccion());
				for (AreaConstruccionDTO areaConstruccionDTO : informeComercialDTO.getAreasConstruccion()) {
					if (areaConstruccionDTO.getTipoDeConstruccion().equals(1)) {
						areasHabitacionales.add(areaConstruccionDTO);
					}
				}
			}
			areasValorComercial.addAll(avaluoComercialDTO.getAreas());
			calcularValores();
		}
	}

	private void separarViasAcceso() {
		viasAccesoInmueble = new HashSet<ViaAccesoDTO>();
		viasAccesoSector = new HashSet<ViaAccesoDTO>();
		if (informeComercialDTO.getViasAcceso() == null) {
			informeComercialDTO.setViasAcceso(new HashSet<ViaAccesoDTO>());
		}else{
			for (ViaAccesoDTO viaAccesoDTO:informeComercialDTO.getViasAcceso()) {
				Integer tipo = viaAccesoDTO.getTipo();
				if (tipo == 0){
					viasAccesoSector.add(viaAccesoDTO);
				}else if (tipo == 1) {
					viasAccesoInmueble.add(viaAccesoDTO);
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
		if (event.getTab().getClientId().equals("informeComercialRural:tabHost:titulacion")) {
			RequestContext.getCurrentInstance().update("informeComercialRural:tabHost:lotesCarousel");
		}
		if (nuevoLote || nuevoPropietario) {
			cancelarNuevoLote();
			RequestContext.getCurrentInstance().update("informeComercialRural:tabHost:nuevoLote");
		}
		if (nuevaViaAcceso) {
			cancelarNuevaViaAcceso();
			RequestContext.getCurrentInstance().update("informeComercialRural:tabHost:viasAccesoSector");
			RequestContext.getCurrentInstance().update("informeComercialRural:tabHost:viasAcceso");
		}
		if (nuevaServidumbre) {
			cancelarNuevaServidumbre();
			RequestContext.getCurrentInstance().update("informeComercialRural:tabHost:cercasPerimetrales");
		}
		if (nuevaExplotacionEconomica) {
			cancelarNuevoExplotacionEconomica();
			RequestContext.getCurrentInstance().update("informeComercialRural:tabHost:explotacionEconomica");
		}
		if (nuevaArea) {
			cancelarNuevaArea();
			RequestContext.getCurrentInstance().update("informeComercialRural:tabHost:areas");
			RequestContext.getCurrentInstance().update("informeComercialRural:tabHost:infraestructura");
		}
		if (nuevaAreaConstruccion) {
			cancelarNuevaAreaConstruccion();
			RequestContext.getCurrentInstance().update("informeComercialRural:tabHost:nuevaAreaConstruccion");
		}
		RequestContext.getCurrentInstance().scrollTo(event.getTab().getParent().getClientId());
	}

	public void onMarkerDrag(MarkerDragEvent event) {
		log.debug("Arrastrando marcador");
		Marker marker = event.getMarker();
		lote.setLatitud(BigDecimal.valueOf(marker.getLatlng().getLat()));
		lote.setLongitud(BigDecimal.valueOf(marker.getLatlng().getLng()));
		log.debug("El marcador quedo en: {}, {}",
                marker.getLatlng().getLat(),
                marker.getLatlng().getLng());
	}
	
	public void agregarLote(){
		nuevoLote = true;
		if (lote == null) {
			lote = new InmuebleDTO();
		}
		propietariosCienPorciento = false;
	}
	
	public void cancelarNuevoLote() {
		if (nuevoLote) {
			if (nuevoPropietario) {
				cancelarNuevoPropietario();
			}
			nuevoLote = false;
			String numeroEscritura = lote.getNumeroDeEscritura();
			Integer numeroNotaria = lote.getNumeroDeNotariaEscritura();
			String departamento = lote.getDepartamentoEscritura();
			String municipio = lote.getMunicipioEscritura();
			Date fechaEscritura = lote.getFechaEscritura();
			lote = new InmuebleDTO();
			lote.setNumeroDeEscritura(numeroEscritura);
			lote.setNumeroDeNotariaEscritura(numeroNotaria);
			lote.setDepartamentoEscritura(departamento);
			lote.setMunicipioEscritura(municipio);
			lote.setFechaEscritura(fechaEscritura);
		}else if (editarLote) {
			if (nuevoPropietario) {
				cancelarNuevoPropietario();
			}
			editarLote = false;
		}
	}
	
	public void editarLote(InmuebleDTO inmueble) {
		editarLote = true;
		lote = inmueble;
		RequestContext.getCurrentInstance().scrollTo("informeComercialRural:tabHost:nuevoLote");
	}

	public void guardarLote() {
		if (nuevoLote) {
			if (nuevoPropietario) {
				agregarPropietario();
			}
			// Oculta al panel de agregar nuevo lote
			nuevoLote = false;
			lote.setAvaluoId(avaluoComercialDTO.getId());
			String numeroEscritura = lote.getNumeroDeEscritura();
			Integer numeroNotaria = lote.getNumeroDeNotariaEscritura();
			String departamento = lote.getDepartamentoEscritura();
			String municipio = lote.getMunicipioEscritura();
			Date fechaEscritura = lote.getFechaEscritura();
			informeComercialDTO.getInmuebles().add(lote);
			lote = new InmuebleDTO();
			lote.setNumeroDeEscritura(numeroEscritura);
			lote.setNumeroDeNotariaEscritura(numeroNotaria);
			lote.setDepartamentoEscritura(departamento);
			lote.setMunicipioEscritura(municipio);
			lote.setFechaEscritura(fechaEscritura);
		}else if (editarLote) {
			if (nuevoPropietario) {
				agregarPropietario();
			}
			// Oculta al panel de agregar nuevo lote
			editarLote = false;
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Lotes", "El lote fue guardado.");
			FacesContext.getCurrentInstance().addMessage("growl", msg);
		}
	}
	
	public void borrarLote(InmuebleDTO inmueble) {
		informeComercialDTO.getInmuebles().remove(inmueble);
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Lotes", "El lote fue borrado.");
		FacesContext.getCurrentInstance().addMessage("growl", msg);
	}

	public void agregarPropietario() {
		if (nuevoPropietario) {
			nuevoPropietario = false;
			verificarCienPorcientoPropietarios();
			BigDecimal numeroPorcentaje = propietario.getPorcentajeDePropiedad();
			propietario.setPorcentajeDePropiedad(numeroPorcentaje.divide(new BigDecimal(100)));
			lote.getPropietarios().add(propietario);
			propietario = new PropietarioDTO();
		}else{
			this.nuevoPropietario = true;
			if (lote.getPropietarios() == null) {
				lote.setPropietarios(new HashSet<PropietarioDTO>());
			}
			if (propietario == null) {
				propietario = new PropietarioDTO();
			}
		}
	}

	private void verificarCienPorcientoPropietarios() {
		BigDecimal porcentajeTotal = new BigDecimal(0);
		for (PropietarioDTO propietarioDTO:lote.getPropietarios()) {
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
		for (PropietarioDTO propietarioDTO:lote.getPropietarios()) {
			porcentajeTotal = porcentajeTotal.add(propietarioDTO.getPorcentajeDePropiedad());
		}
		porcentajeTotal = porcentajeTotal.add(porcentajeActual);
		if (porcentajeTotal.compareTo(new BigDecimal(100)) > 0) {
			propietariosCienPorciento = true;
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Propietarios del lote", "Los propietarios del lote exceden el 100 %.");
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
		lote.getPropietarios().remove(propietario);
		verificarCienPorcientoPropietarios();
	}

	public void agregarViaAcceso(Set<ViaAccesoDTO> viasAcceso, ViaAccesoDTO viaAcceso, String tipoVia) {
		if (nuevaViaAcceso) {
			nuevaViaAcceso = false;
			viaAcceso.setAvaluoId(avaluoComercialDTO.getId());
			viaAcceso.setTipo(tipoVia == "1" ? 1 : 0);
			viasAcceso.add(viaAcceso);
		}else{
			nuevaViaAcceso = true;
			this.viaAcceso = new ViaAccesoDTO();
		}
	}
	
	public void cancelarNuevaViaAcceso() {
		nuevaViaAcceso = false;
		viaAcceso = new ViaAccesoDTO();
	}
	
	public void editarViaAcceso(RowEditEvent event) {
	}
	
	public void cancelarViaAcceso(RowEditEvent event) {
	}
	
	public void borrarViaAcceso(Set<ViaAccesoDTO> viasAcceso, ViaAccesoDTO viaAccesoDTO) {
		viasAcceso.remove(viaAccesoDTO);
	}

	public void agregarServidumbre() {
		if (nuevaServidumbre) {
			nuevaServidumbre = false;
			servidumbre.setAvaluoId(avaluoComercialDTO.getId());
			informeComercialDTO.getServidumbrez().add(servidumbre);
			this.servidumbre = new ServidumbreDTO();
		}else{
			nuevaServidumbre = true;
			if (informeComercialDTO.getServidumbrez() == null) {
				informeComercialDTO.setServidumbrez(new HashSet<ServidumbreDTO>());
			}
			this.servidumbre = new ServidumbreDTO();
		}
	}
	
	public void cancelarNuevaServidumbre(){
		nuevaServidumbre = false;
		servidumbre = new ServidumbreDTO();
	}

	public void editarServidumbre(RowEditEvent event) {
	}

	public void cancelarServidumbre(RowEditEvent event) {
	}

	public void borrarServidumbre(ServidumbreDTO servidumbre) {
		informeComercialDTO.getServidumbrez().remove(servidumbre);
	}
	
	public void agregarExplotacionEconomica() {
		if (nuevaExplotacionEconomica) {
			nuevaExplotacionEconomica = false;
			explotacionEconomica.setAvaluoId(avaluoComercialDTO.getId());
			informeComercialDTO.getExplotacionesEconomicas().add(explotacionEconomica);
			calcularAntiguedad(explotacionEconomica);
			calcularPorcentajeExplotacionesEconomicas();
			this.explotacionEconomica = new ExplotacionEconomicaDTO();
		}else{
			nuevaExplotacionEconomica = true;
			if (informeComercialDTO.getExplotacionesEconomicas() == null) {
				informeComercialDTO.setExplotacionesEconomicas(new HashSet<ExplotacionEconomicaDTO>());
			}
			this.explotacionEconomica = new ExplotacionEconomicaDTO();
		}
	}
	
	public void cancelarNuevoExplotacionEconomica(){
		nuevaExplotacionEconomica = false;
		explotacionEconomica = new ExplotacionEconomicaDTO();
	}

	private void calcularPorcentajeExplotacionesEconomicas(){
		BigDecimal sumaAreasExplotacionesEconomicas = new BigDecimal(0);
		for (ExplotacionEconomicaDTO explotacionEconomica:informeComercialDTO.getExplotacionesEconomicas()) {
			sumaAreasExplotacionesEconomicas = sumaAreasExplotacionesEconomicas.add(explotacionEconomica.getArea());
		}
		if (sumaAreasExplotacionesEconomicas.compareTo(BigDecimal.ZERO) > 0) {
			for (ExplotacionEconomicaDTO explotacionEconomica:informeComercialDTO.getExplotacionesEconomicas()) {
				explotacionEconomica.setPorcentajeDeParticipacion(explotacionEconomica.getArea().divide(sumaAreasExplotacionesEconomicas, 2, RoundingMode.HALF_UP));
			}
		}else{
			if (explotacionEconomica != null && nuevaExplotacionEconomica) {
				explotacionEconomica.setPorcentajeDeParticipacion(new BigDecimal(1));
			}
		}
	}
	
	private void calcularAntiguedad(ExplotacionEconomicaDTO objetoEditado) {
		int meses = DateUtils.getDiferenciaEnMeses(objetoEditado.getFechaDeSiembra(), new Date(System.currentTimeMillis()));
		objetoEditado.setAntiguedad(new BigDecimal(meses));
	}

	public void editarExplotacionEconomica(RowEditEvent event) {
		Object object = event.getObject();
		if (object.getClass().equals(ExplotacionEconomicaDTO.class)) {
			ExplotacionEconomicaDTO objetoEditado = (ExplotacionEconomicaDTO)object;
			calcularAntiguedad(objetoEditado);
			calcularPorcentajeExplotacionesEconomicas();
		}
	}

	public void cancelarExplotacionEconomica(RowEditEvent event) {
		calcularPorcentajeExplotacionesEconomicas();
	}

	public void borrarExplotacionEconomica(ExplotacionEconomicaDTO explotacionEconomica) {
		informeComercialDTO.getExplotacionesEconomicas().remove(explotacionEconomica);
	}
	
	public int ordenarTablaValorComercial(Object tipoArea1, Object tipoArea2) {
		if (tipoArea1.equals(tipoArea2)) {
			return 0;
		}
		if ((tipoArea1.equals(TipoArea.Terreno) && !tipoArea2.equals(TipoArea.Terreno)) 
				|| (tipoArea1.equals(TipoArea.Construccion) && tipoArea2.equals(TipoArea.Infraestructura))
				|| (tipoArea1.equals(TipoArea.Infraestructura) && tipoArea2.equals(TipoArea.CultivoAvaluable))
				|| (tipoArea1.equals(TipoArea.Construccion) && tipoArea2.equals(TipoArea.CultivoAvaluable))) {
			return -1;
		}
		if (!tipoArea1.equals(TipoArea.Terreno) && tipoArea2.equals(TipoArea.Terreno)
				|| (tipoArea1.equals(TipoArea.Infraestructura) && tipoArea2.equals(TipoArea.Construccion))
				|| (tipoArea1.equals(TipoArea.CultivoAvaluable) && tipoArea2.equals(TipoArea.Infraestructura))
				|| (tipoArea1.equals(TipoArea.CultivoAvaluable) && tipoArea2.equals(TipoArea.Construccion))) {
			return 1;
		}
		return 0;
	}
	
	public void calcularRelacionFrenteFondo() {
		if (informeComercialDTO.getFrente() != null
				&& informeComercialDTO.getFrente().compareTo(BigDecimal.ZERO) > 0
				&& informeComercialDTO.getFondo() != null
				&& informeComercialDTO.getFondo().compareTo(BigDecimal.ZERO) > 0) {
			informeComercialDTO.setRelacionFrenteFondo(informeComercialDTO.getFrente()
					.divide(informeComercialDTO.getFondo(), 2, RoundingMode.HALF_UP));
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
			if (tipoArea2.equals(TipoArea.Terreno)) {
				areasValorProporcionalTerreno.add(area);
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
			calcularAreaEnMetros((AreaDTO) event.getObject());
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
		if (!areasTerreno.isEmpty()) {
			subtotalAreasTerreno = new BigDecimal(0);
			subtotalPorcentajeAreasTerreno = new BigDecimal(0);
		}
		if (informeComercialDTO.getAreasConstruccion() != null && !informeComercialDTO.getAreasConstruccion().isEmpty()) {
			subtotalAreasConstruidas = new BigDecimal(0);
			subtotalPorcentajeAreasConstruidas = new BigDecimal(0);
		}
		if (!areasInfraestructura.isEmpty()) {
			subtotalAreasInfraestructura = new BigDecimal(0);
			subtotalPorcentajeAreasInfraestructura = new BigDecimal(0);
		}
		if (!cultivosAvaluables.isEmpty()) {
			subtotalCultivosAvaluables = new BigDecimal(0);
			subtotalPorcentajeCultivosAvaluables = new BigDecimal(0);
		}
		// Calcular subtotales y sumas de areas
		BigDecimal sumaAreasTerreno = new BigDecimal(0);
		BigDecimal sumaTotalesTerreno = new BigDecimal(0);
		BigDecimal sumaAreasConstruccion = new BigDecimal(0);
		BigDecimal sumaTotalesHabitacionales = new BigDecimal(0);
		for (AreaDTO area:avaluoComercialDTO.getAreas()) {
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
					// Verificar area habitacional y agregar el area total a la suma 
					if (area.getClass().equals(AreaConstruccionDTO.class) && ((AreaConstruccionDTO) area).getTipoDeConstruccion().equals(1)) {
						sumaTotalesHabitacionales = sumaTotalesHabitacionales.add(area.getValorTotal());
						sumaTotalesTerreno = sumaTotalesTerreno.add(area.getValorTotal());
						calcularValorResidualConstruccion((AreaConstruccionDTO) area);
					}
				} else if (area.getTipoArea().equals(TipoArea.Infraestructura)) {
					subtotalAreasInfraestructura = subtotalAreasInfraestructura
							.add(area.getValorTotal());
				} else if (area.getTipoArea().equals(TipoArea.CultivoAvaluable)) {
					subtotalCultivosAvaluables = subtotalCultivosAvaluables
							.add(area.getValorTotal());
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
		if (subtotalAreasInfraestructura != null && subtotalAreasInfraestructura.compareTo(BigDecimal.ZERO) > 0) {
			valorComercialInmueble = valorComercialInmueble.add(subtotalAreasInfraestructura);
		}
		if (subtotalCultivosAvaluables != null && subtotalCultivosAvaluables.compareTo(BigDecimal.ZERO) > 0) {
			valorComercialInmueble = valorComercialInmueble.add(subtotalCultivosAvaluables);
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
		for (AreaDTO area:avaluoComercialDTO.getAreas()) {
			area.setPorcentaje(avaluoController.calcularPorcentajeArea(area,
					informeComercialDTO.getValorComercialDelInmueble()));
			if (area.getTipoArea().equals(TipoArea.Terreno)) {
				subtotalPorcentajeAreasTerreno = subtotalPorcentajeAreasTerreno
						.add(area.getPorcentaje());
				calcularPorcentajePorpocionalTerreno(area, sumaTotalesTerreno);
			} else if (area.getTipoArea().equals(TipoArea.Construccion)) {
				subtotalPorcentajeAreasConstruidas = subtotalPorcentajeAreasConstruidas
						.add(area.getPorcentaje());
				calcularPorcentajePorpocionalHabitacional(area, sumaTotalesHabitacionales);
				calcularPorcentajePorpocionalTerreno(area, sumaTotalesTerreno);
			} else if (area.getTipoArea().equals(TipoArea.Infraestructura)) {
				subtotalPorcentajeAreasInfraestructura = subtotalPorcentajeAreasInfraestructura
						.add(area.getPorcentaje());
			} else if (area.getTipoArea().equals(TipoArea.CultivoAvaluable)) {
				subtotalPorcentajeCultivosAvaluables = subtotalPorcentajeCultivosAvaluables
						.add(area.getPorcentaje());
			}
		}
	}
	
	private void calcularPorcentajePorpocionalHabitacional(AreaDTO areaEditada, BigDecimal sumaTotalesHabitacionales) {
		BigDecimal porcentajeProporcionalHabitacional = new BigDecimal(0);
		if (sumaTotalesHabitacionales.compareTo(BigDecimal.ZERO) > 0 && areaEditada.getValorTotal() != null) {
			porcentajeProporcionalHabitacional = areaEditada.getValorTotal().divide(
					sumaTotalesHabitacionales, 2, RoundingMode.HALF_UP);
		}
		areaEditada.setPorcentajeValorProporcionalConstruccion(porcentajeProporcionalHabitacional);
	}
	
	private void calcularPorcentajePorpocionalTerreno(AreaDTO areaEditada, BigDecimal sumaTotalesTerreno) {
		BigDecimal porcentajeProporcionalTerreno = new BigDecimal(0);
		if (sumaTotalesTerreno.compareTo(BigDecimal.ZERO) > 0 && areaEditada.getValorTotal() != null) {
			porcentajeProporcionalTerreno = areaEditada.getValorTotal().divide(
					sumaTotalesTerreno, 2, RoundingMode.HALF_UP);
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
		}else if (tipoArea.equals(TipoArea.Infraestructura)) {
			subtotalArea = subtotalAreasInfraestructura;
			subtotalPorcentajeArea =  subtotalPorcentajeAreasInfraestructura;
		}else if (tipoArea.equals(TipoArea.CultivoAvaluable)) {
			subtotalArea = subtotalCultivosAvaluables;
			subtotalPorcentajeArea =  subtotalPorcentajeCultivosAvaluables;
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
		BigDecimal porcentajeFinal = informeComercialDTO.getPorcentajeFinal();
		valorResidualParaLaConstruccion = new BigDecimal(0);
		if (porcentajeFinal != null && porcentajeFinal.compareTo(BigDecimal.ZERO) > 0) {
			for (AreaConstruccionDTO areaConstruccionDTO : areasHabitacionales) {
				calcularValorResidualConstruccion(areaConstruccionDTO);
				valorResidualParaLaConstruccion = valorResidualParaLaConstruccion.add(areaConstruccionDTO.getValorResidualConstruccion());
			}
		}
	}
	
	public void calcularValorResidualConstruccion(AreaConstruccionDTO areaEditada) {
		BigDecimal porcentajeFinal = informeComercialDTO.getPorcentajeFinal();
		if (areaEditada.getValorTotal() != null && porcentajeFinal != null) {
			areaEditada.setValorResidualConstruccion(areaEditada.getValorTotal().multiply(porcentajeFinal));
		}
	}
	
	public void agregarAreaConstruccion() {
		if (!nuevaAreaConstruccion){
			nuevaAreaConstruccion = true;
			if (informeComercialDTO.getAreasConstruccion() == null) {
				informeComercialDTO.setAreasConstruccion(new HashSet<AreaConstruccionDTO>());
			}
			this.areaConstruccion = new AreaConstruccionDTO();
		}
	}
	
	public void cancelarNuevaAreaConstruccion() {
		if (nuevaAreaConstruccion) {
			nuevaAreaConstruccion = false;
			this.areaConstruccion = new AreaConstruccionDTO();
		}else if (editarAreaConstruccion) {
			editarAreaConstruccion = false;
			this.areaConstruccion = new AreaConstruccionDTO();
		}
	}
	
	public void editarAreaConstruccion(AreaConstruccionDTO areaConstruccion) {
		editarAreaConstruccion = true;
		this.areaConstruccion = areaConstruccion;
	}
	
	public void guardarAreaConstruccion() {
		if (nuevaAreaConstruccion) {
			nuevaAreaConstruccion = false;
			areaConstruccion.setTipoArea(TipoArea.Construccion);
			areaConstruccion.setAvaluoId(avaluoComercialDTO.getId());
			
			// Agregar Area a las listas según corresponda
			avaluoComercialDTO.getAreas().add(areaConstruccion);
			areasValorComercial.add(areaConstruccion);
			areasValorProporcionalTerreno.add(areaConstruccion);
			if (areaConstruccion.getTipoDeConstruccion().equals(1)) {
				areasHabitacionales.add(areaConstruccion);
			}
			informeComercialDTO.getAreasConstruccion().add(areaConstruccion);
		}else if (editarAreaConstruccion) {
			editarAreaConstruccion = false;
			
			if (areaConstruccion.getTipoDeConstruccion().equals(1) && !areasHabitacionales.contains(areaConstruccion)) {
				areasHabitacionales.add(areaConstruccion);
			}
			calcularAreaEnMetros(areaConstruccion);
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Areas de construcción", "El área de construcción fue guardado.");
			FacesContext.getCurrentInstance().addMessage("growl", msg);
		}
	}
	
	public void borrarAreaConstruccion(AreaConstruccionDTO areaConstruccion) {
		informeComercialDTO.getAreasConstruccion().remove(areaConstruccion);
		avaluoComercialDTO.getAreas().remove(areaConstruccion);
		if (areasHabitacionales.contains(areaConstruccion)) {
			areasHabitacionales.remove(areaConstruccion);
		}
		if (areasValorProporcionalTerreno.contains(areaConstruccion)) {
			areasValorProporcionalTerreno.remove(areaConstruccion);
		}
		if (areasValorComercial.contains(areaConstruccion)) {
			areasValorComercial.remove(areaConstruccion);
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
		if (origen.equals("lote")) {
			ciudadesLote = listasGeograficasController.ciudadesEnDepartamento(departamento);
		}else if (origen.equals("solicitante")) {
			ciudadesSolicitante = listasGeograficasController.ciudadesPorDepartamento(departamento);
		}else if (origen.equals("predio")) {
			ciudadesPredio = listasGeograficasController.ciudadesPorDepartamento(departamento);
		}
	}

	public void calcularVetustez() {
		int nuevaVetusdez = informeHipotecarioController.actualizarVetusdez(new Date(System.currentTimeMillis()), areaConstruccion.getAnoDeConstruccion());
		areaConstruccion.setVetustez(new BigDecimal(nuevaVetusdez));
	}

	public void enviarAvaluo() {
        log.debug("Enviando informe del avaluo  {}",
                avaluoComercialDTO);
		String direccionCliente = avaluoController.onDireccionChanged(
					tipoViaCliente, numeroViaCliente,
					convertirAString(complementoViaCliente),
					convertirAString(complementoViaGeneradoraCliente),
					numeroViaGeneradoraCliente, placaCliente,
					complementoPlacaCliente);
		avaluoComercialDTO.getCliente().setDireccionDeContactoSolicitante(direccionCliente);
		String informeEnviado = informeComercialController.enviarAvaluo(
				avaluoComercialDTO,	lotesOriginales, viasAccesoOriginales,
				servidumbresOriginales,	explotacionesEconomicasOriginales,
				areasOriginales, areasConstruccionOriginales,
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
		String informeGuardado = null;
		informeComercialDTO.setViasAcceso(Sets.union(viasAccesoInmueble, viasAccesoSector));
		try {
			informeGuardado = informeComercialController.guardarSinEnviar(avaluoComercialDTO,
					lotesOriginales, viasAccesoOriginales, servidumbresOriginales,
					explotacionesEconomicasOriginales, areasOriginales, areasConstruccionOriginales, metodosValuacionOriginales)
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

	public List<DivipolaDTO> getCiudadesLote() {
		return ciudadesLote;
	}

	public void setCiudadesLote(List<DivipolaDTO> ciudadesLote) {
		this.ciudadesLote = ciudadesLote;
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
	
	public String getDepartamentoInmueble() {
		return departamentoInmueble;
	}
	
	public void setDepartamentoInmueble(String departamentoInmueble) {
		this.departamentoInmueble = departamentoInmueble;
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

	public String getDepartamentoCliente() {
		return departamentoCliente;
	}

	public void setDepartamentoCliente(String departamentoCliente) {
		this.departamentoCliente = departamentoCliente;
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

	public boolean isNuevoLote() {
		return nuevoLote;
	}

	public void setNuevoLote(boolean nuevoLote) {
		this.nuevoLote = nuevoLote;
	}

	public boolean isEditarLote() {
		return editarLote;
	}

	public void setEditarLote(boolean editarLote) {
		this.editarLote = editarLote;
	}

	public InmuebleDTO getLote() {
		return lote;
	}

	public void setLote(InmuebleDTO lote) {
		this.lote = lote;
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

	public Set<ViaAccesoDTO> getViasAccesoSector() {
		return viasAccesoSector;
	}

	public void setViasAccesoSector(Set<ViaAccesoDTO> viasAccesoSector) {
		this.viasAccesoSector = viasAccesoSector;
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

	public ViaAccesoDTO getViaAcceso() {
		return viaAcceso;
	}

	public void setViaAcceso(ViaAccesoDTO viaAcceso) {
		this.viaAcceso = viaAcceso;
	}

	public boolean isNuevaServidumbre() {
		return nuevaServidumbre;
	}

	public void setNuevaServidumbre(boolean nuevaServidumbre) {
		this.nuevaServidumbre = nuevaServidumbre;
	}

	public ServidumbreDTO getServidumbre() {
		return servidumbre;
	}

	public void setServidumbre(ServidumbreDTO servidumbre) {
		this.servidumbre = servidumbre;
	}

	public boolean isNuevaExplotacionEconomica() {
		return nuevaExplotacionEconomica;
	}

	public void setNuevaExplotacionEconomica(boolean nuevaExplotacionEconomica) {
		this.nuevaExplotacionEconomica = nuevaExplotacionEconomica;
	}

	public ExplotacionEconomicaDTO getExplotacionEconomica() {
		return explotacionEconomica;
	}

	public void setExplotacionEconomica(ExplotacionEconomicaDTO explotacionEconomica) {
		this.explotacionEconomica = explotacionEconomica;
	}

	public Set<AreaDTO> getAreasTerreno() {
		return areasTerreno;
	}

	public void setAreasTerreno(Set<AreaDTO> areasTerreno) {
		this.areasTerreno = areasTerreno;
	}

	public Set<AreaDTO> getAreasInfraestructura() {
		return areasInfraestructura;
	}

	public void setAreasInfraestructura(Set<AreaDTO> areasInfraestructura) {
		this.areasInfraestructura = areasInfraestructura;
	}

	public Set<AreaDTO> getCultivosAvaluables() {
		return cultivosAvaluables;
	}

	public void setCultivosAvaluables(Set<AreaDTO> cultivosAvaluables) {
		this.cultivosAvaluables = cultivosAvaluables;
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

	public boolean isNuevaAreaConstruccion() {
		return nuevaAreaConstruccion;
	}

	public void setNuevaAreaConstruccion(boolean nuevaAreaConstruccion) {
		this.nuevaAreaConstruccion = nuevaAreaConstruccion;
	}

	public boolean isEditarAreaConstruccion() {
		return editarAreaConstruccion;
	}

	public void setEditarAreaConstruccion(boolean editarAreaConstruccion) {
		this.editarAreaConstruccion = editarAreaConstruccion;
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
	
	public AreaDTO.UnidadDeMedida[] getUnidadesDeMedida(){
		return AreaDTO.UnidadDeMedida.values();
	}
	
	public AvaluoDTO.MBR[] getMbr(){
		return AvaluoDTO.MBR.values();
	}

}
