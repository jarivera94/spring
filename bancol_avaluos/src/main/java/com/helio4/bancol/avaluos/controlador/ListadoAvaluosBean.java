package com.helio4.bancol.avaluos.controlador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.MapModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;

import com.helio4.bancol.avaluos.dominio.AvaluoController;
import com.helio4.bancol.avaluos.dominio.AvaluosLazyDataModel;
import com.helio4.bancol.avaluos.dominio.InformeHipotecarioController;
import com.helio4.bancol.avaluos.dominio.ListadoAvaluosController;
import com.helio4.bancol.avaluos.dominio.ParametrosController;
import com.helio4.bancol.avaluos.dominio.ProgramarCitaController;
import com.helio4.bancol.avaluos.dominio.TarifaUVRController;
import com.helio4.bancol.avaluos.dominio.UsuarioController;
import com.helio4.bancol.avaluos.dominio.gmap.GLatLng;
import com.helio4.bancol.avaluos.dto.AvaluoDTO;
import com.helio4.bancol.avaluos.dto.ClienteDTO;
import com.helio4.bancol.avaluos.dto.EventoDTO;
import com.helio4.bancol.avaluos.dto.FormatoInformeComercialDTO;
import com.helio4.bancol.avaluos.dto.FormatoInformeHipotecarioDTO;
import com.helio4.bancol.avaluos.dto.SucursalDTO;
import com.helio4.bancol.avaluos.dto.TipoAvaluoDTO;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;
import com.helio4.bancol.avaluos.modelo.Motivo;
import com.helio4.bancol.avaluos.servicio.datos.MotivoService;
import com.helio4.bancol.avaluos.servicio.util.Constantes;
import com.helio4.bancol.avaluos.servicio.util.Constantes.FormatoInforme;
import com.helio4.bancol.avaluos.servicio.util.DateUtils;
import com.helio4.bancol.avaluos.servicio.util.MathUtils;
import com.helio4.bancol.avaluos.servicio.util.StringUtils;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Controller
@Scope("session")
@Qualifier("listadoAvaluosBean")
public class ListadoAvaluosBean implements Serializable {

    private static Logger log = LoggerFactory
            .getLogger(ListadoAvaluosBean.class);
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private final AvaluosLazyDataModel avaluosLazyModel;
    private List<String> filteredData;
    private Map<String, Object> filtros =  new HashMap<>();
    private AvaluoDTO avaluo;
    /** Evento para mostrar la fecha de la cita en el resumen */
    private EventoDTO eventoDTO;
    /** Punto espacial de ubicacion del inmueble en el mapa */
    private GLatLng gLatLng;
    /** Valor total de los honorarios del avaluo */
    private String valorHonorarios;
    /** Modelo del mapa */
    private MapModel mapModel;
    /** Strings para almacenar el codigo externo del resultado de operaciones para dar noificaciones al usuario */
    private String solicitudCreada;
    private String solicitudActualizada;
    private String solicitudAsignada;
    private String solicitudAceptada;
    private String citaProgramada;
    private String citaReProgramada;
    private String informeGuardado;
    private String informeEnviado;
    private String informeEnviadoAComite;
    private String correcionesSolicitadas;
    private String informeLiquidado;
    private String informeHonorariosEnviado;
    private String mensajeError;
    private String urlReturn;

    private String justificacionRechazo;
    private String justificacion;

    private UsuarioDTO usuarioActivo;
    /** Variable para almacenar una copia del tipo de avaluo original cuando se cambia el tipo de avaluo */
    private TipoAvaluoDTO tipoAvaluo;
    private FormatoInforme formatoInforme;

    private String path;

    private boolean rowSeleccionado;

    private boolean renderizar;

    private boolean mostrarCitaReprogramada;

    private final ListadoAvaluosController listadoAvaluosController;
    private final InformeHipotecarioController informeHipotecarioController;
    private final AvaluoController avaluoController;
    private final ProgramarCitaController programarCitaController;
    private final TarifaUVRController tarifaUvrController;
    private final ParametrosController parametrosController;
    private final UsuarioController usuarioController;
    private final MotivoService motivoService;

    private final DataSource dataSource;

    private StreamedContent file;

    /**
     * Ruta en la que se genera el pdf
     *
     */
    private String rutaPDF = "";

    /**
     * Cadena de texto por la cual el usuario puede filtrar un
     * avalúo
     */
    private String filtro;
    private boolean esFito;
    private boolean esComparacion;
    private boolean esPH;

    private boolean crearRol;
    private boolean crearUsuario;
    private boolean crearNuevaSolicitud;
    private boolean editarSolicitud;
    private boolean asignarSolicitud;
    private boolean reasignarSolicitud;
    private boolean aceptarCaso;
    private boolean rechazarCaso;
    private boolean programaCita;
    private boolean reprogramarCita;
    private boolean confirmarVisita;
    private boolean confirmarDocumentos;
    private boolean confirmarPago;
    private boolean ingresarInforme;
    private boolean revisarEditarAvaluo;
    private boolean cargarFotos;
    private boolean enviarNotificacionesHonorarios;
    private boolean enviar;
    private boolean verCorreccionesSolicitadas;
    private boolean solicitarDevolucion;
    private boolean devolverCaso;
    private boolean reactivarCaso;
    private boolean solicitarCorrecciones;
    private boolean comite;
    private boolean enviarAComite;
    private boolean descargarPDFSinFirmas;
    private boolean verInformacionDePerito;
    private boolean editarAvaluoDespuesDeAprobado;
    private boolean aprobarAvaluo;
    private boolean verInformesSinEditar;
    private boolean descargarPDFconFirmas;
    private boolean descargarPDFAnexoMetodologias;
    private boolean cancelarAvaluo;
    private boolean exportarBUA;
    private boolean exportarReportes;
    private boolean verAgenda;
    private boolean estudioMercado;
    private boolean rechazarDevolucion;
    private boolean verFotos;
    private boolean corregirInforme;
    private boolean crearEntidades;
    private boolean crearSegmentos;
    private boolean crearSucursales;
    private boolean cambiarCamposClaves;
    private boolean permisoAbogado;
    private boolean permisoEditarCliente;
    private boolean cambiarFechaAprobacion;
    private boolean modificarIdSisgen;

    private String filtroEstado         = "";
    private String filtroCodigoExterno  = "";
    private String filtroFechaEstado    = "";
    private String filtroTipoAvaluo     = "";
    private String filtroEntidad        = "";
    private String filtroMotivo  = "";
    private String filtroAsignado       = "";
    private String filtroDireccion      = "";
    private String filtroBarrio         = "";
    private String filtroCiudad         = "";

    private Date time = null;

    @Autowired
    public ListadoAvaluosBean(AvaluosLazyDataModel avaluosLazyModel, AvaluoController avaluoController, InformeHipotecarioController informeHipotecarioController, ProgramarCitaController programarCitaController, UsuarioController usuarioController, ParametrosController parametrosController, ListadoAvaluosController listadoAvaluosController, DataSource dataSource, TarifaUVRController tarifaUvrController, MotivoService motivoService) {
        this.avaluosLazyModel = avaluosLazyModel;
        this.avaluoController = avaluoController;
        this.informeHipotecarioController = informeHipotecarioController;
        this.programarCitaController = programarCitaController;
        this.usuarioController = usuarioController;
        this.parametrosController = parametrosController;
        this.listadoAvaluosController = listadoAvaluosController;
        this.dataSource = dataSource;
        this.tarifaUvrController = tarifaUvrController;
        this.motivoService = motivoService;
    }

    @PostConstruct
    public void init() {
        log.debug("Inicializando ListadoAvaluosBean: ");
        restartTime();
        // Cargar lista de avaluos segun permisos del usuario
        usuarioActivo = (UsuarioDTO) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        path = parametrosController.obtenerValor("rutaReportes");
        eventoDTO = new EventoDTO();
        rowSeleccionado = false;
        obtenerPermisosUsuario(usuarioActivo);
        
        if(urlReturn == null){
        	urlReturn =  "/pages/avaluos/listadoAvaluos.xhtml";
        }
    }
    
    public void onLoad(){
    	
    	FacesContext context = FacesContext.getCurrentInstance();
    	Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
    	
    	String avaluoId = paramMap.get("avaluoIdSisgen");
    	
    	if(avaluoId!= null){
    		
    		AvaluoDTO avaluoAuxiliar = informeHipotecarioController.encontrarAvaluoPorIdTinsa(Long.parseLong(avaluoId));
    		if(avaluoAuxiliar!= null){
    			this.avaluo = avaluoAuxiliar;
    			ingresarInforme();
    		}
    	}
    }

    private void obtenerPermisosUsuario(UsuarioDTO usuario) {
        crearRol = usuario.tienePermisoCrearRol();
        crearUsuario = usuario.tienePermisoCrearUsuario();
        crearNuevaSolicitud = usuario.tienePermisoCrearNuevaSolicitudes();
        editarSolicitud = usuario.tienePermisoEditarSolicitud();
        asignarSolicitud = usuario.tienePermisoAsignarSolicitud();
        reasignarSolicitud = usuario.tienePermisoReAsignarSolicitud();
        aceptarCaso = usuario.tienePermisoAceptarCaso();
        rechazarCaso = usuario.tienePermisoRechazarCaso();
        programaCita = usuario.tienePermisoProgramarCita();
        reprogramarCita = usuario.tienePermisoReProgramarCita();
        confirmarVisita = usuario.tienePermisoConfirmarVisita();
        confirmarDocumentos = usuario.tienePermisoConfirmarDocumentos();
        confirmarPago = usuario.tienePermisoConfirmarPago();
        ingresarInforme = usuario.tienePermisoIngresarInforme();
        revisarEditarAvaluo = usuario.tienePermisoRevisarGuardarAvaluo();
        cargarFotos = usuario.tienePermisoCargarFotos();
        enviarNotificacionesHonorarios = usuario.tienePermisoEnviarNotificacionesHonorarios();
        enviar = usuario.tienePermisoEnviar();
        verCorreccionesSolicitadas = usuario.tienePermisoVerCorreccionesSolicitadas();
        solicitarDevolucion = usuario.tienePermisoSolicitarDevolucion();
        devolverCaso = usuario.tienePermisoDevolverCaso();
        reactivarCaso = usuario.tienePermisoReactivarCaso();
        solicitarCorrecciones = usuario.tienePermisoSolicitarCorrecciones();
        comite = usuario.tienePermisoComite();
        enviarAComite = usuario.tienePermisoEnviarAComite();
        descargarPDFSinFirmas = usuario.tienePermisoDescargarPDFSinFirmas();
        verInformacionDePerito = usuario.tienePermisoVerInformacionDePerito();
        editarAvaluoDespuesDeAprobado = usuario.tienePermisoEditarAvaluoDespuesDeAprobado();
        aprobarAvaluo = usuario.tienePermisoAprobarAvaluo();
        verInformesSinEditar = usuario.tienePermisoVerInformesSinEditar();
        descargarPDFconFirmas = usuario.tienePermisoDescargarPDFConFirmas();
        descargarPDFAnexoMetodologias = usuario.tienePermisoDescargarPDFAnexoMetodologias();
        cancelarAvaluo = usuario.tienePermisoCancelarAvaluo();
        exportarBUA = usuario.tienePermisoExportarBUA();
        exportarReportes = usuario.tienePermisoExportarReportes();
        verAgenda = usuario.tienePermisoVerAgenda();
        estudioMercado = usuario.tienePermisoEstudioMercado();
        rechazarDevolucion = usuario.tienePermisoRechazarDevolucion();
        verFotos = usuario.tienePermisoVerFotos();
        corregirInforme = usuario.tienePermisoCorregirInforme();
        crearEntidades = usuario.tienePermisoCrearEntidades();
        crearSegmentos = usuario.tienePermisoCrearSegmentos();
        crearSucursales = usuario.tienePermisoCrearSucursales();
        cambiarCamposClaves= usuario.tienePermisoCambiarCamposClaves();
        permisoAbogado = usuario.tienePermisoAbogado();
        this.permisoEditarCliente = usuario.tienePermisoEditarCliente();
        this.cambiarFechaAprobacion = usuario.tienePermisoCambiarFechaAprobacion();
        this.modificarIdSisgen = usuario.tienePermisoModificarIdSisgen();
    }

    public void actualizarListado(ComponentSystemEvent event) {
        DataTable tablaAvaluos = (DataTable) FacesContext.getCurrentInstance()
                .getViewRoot().findComponent("form:tabla_Avaluos");
        tablaAvaluos.setFilters(filtros);
        if (mensajeError != null) {
            FacesContext.getCurrentInstance()
                    .addMessage("growl", new FacesMessage(
                            "Error",
                            mensajeError));
            mensajeError = null;
        }
        if (solicitudCreada != null) {
//          actualizarTabla();
            FacesContext.getCurrentInstance().addMessage(
                    "growl",
                    new FacesMessage("Solicitud creada",
                            "La solicitud con número: " + solicitudCreada
                                    + " fue creada exitosamente"));
            solicitudCreada = null;
        }
        if (solicitudActualizada != null) {
//          actualizarTabla();
            FacesContext.getCurrentInstance().addMessage(
                    "growl",
                    new FacesMessage("Solicitud actualizada",
                            "La solicitud con número: " + solicitudActualizada
                                    + " fue actualizada exitosamente"));
            solicitudActualizada = null;
        }
        if (solicitudAsignada != null) {
//          actualizarTabla();
            FacesContext.getCurrentInstance().addMessage(
                    "growl",
                    new FacesMessage("Solicitud asignada",
                            "La solicitud con número: " + solicitudAsignada
                                    + " fue asignada exitosamente"));
            solicitudAsignada = null;
        }
        if (solicitudAceptada != null && citaProgramada == null) {
//          actualizarTabla();
            FacesContext.getCurrentInstance().addMessage(
                    "growl",
                    new FacesMessage("Solicitud aceptada",
                            "La solicitud con número: " + solicitudAceptada
                                    + " fue aceptada exitosamente"));
            solicitudAceptada = null;
        }
        if (citaProgramada != null) {
//          actualizarTabla();
            FacesContext.getCurrentInstance().addMessage(
                    "growl",
                    new FacesMessage("Cita programada",
                            "La solicitud con número: " + citaProgramada
                                    + " fue programada exitosamente"));
            solicitudAceptada = null;
            citaProgramada = null;
        }
        if (citaReProgramada!= null) {
//          actualizarTabla();
            FacesContext.getCurrentInstance().addMessage(
                    "growl",
                    new FacesMessage("Cita reprogramada",
                            "La solicitud con número: " + citaReProgramada
                                    + " fue reprogramada exitosamente"));
            citaReProgramada = null;
        }

        if (informeGuardado != null) {
//          actualizarTabla();
            FacesContext.getCurrentInstance().addMessage(
                    "growl",
                    new FacesMessage("Informe guardado",
                            "El informe de la solicitud con número: "
                                    + informeGuardado + " fue guardado."));
            informeGuardado = null;
        }
        if (informeEnviado != null) {
//          actualizarTabla();
            FacesContext.getCurrentInstance().addMessage(
                    "growl",
                    new FacesMessage("Informe enviado",
                            "El informe de la solicitud con número: "
                                    + informeEnviado + " fue enviado."));
            informeEnviado = null;
        }

        if (informeEnviadoAComite != null) {
//          actualizarTabla();
            FacesContext.getCurrentInstance().addMessage(
                    "growl",
                    new FacesMessage("Informe enviado",
                            "El informe de la solicitud con número: "
                                    + informeEnviadoAComite
                                    + " fue enviado a comité."));
            informeEnviadoAComite = null;
        }
        if (correcionesSolicitadas != null) {
//          actualizarTabla();
            FacesContext.getCurrentInstance().addMessage(
                    "growl",
                    new FacesMessage("Correcciones Solicitadas",
                            "Las correcciones del informe de la solicitud con número: "
                                    + correcionesSolicitadas
                                    + " fueron enviadas."));
            correcionesSolicitadas = null;
        }
        if (informeLiquidado != null) {
//          actualizarTabla();
            FacesContext.getCurrentInstance().addMessage(
                    "growl",
                    new FacesMessage("Informe liquidado",
                            "El informe de la solicitud con número: "
                                    + informeLiquidado + " fue liquidado."));
            informeLiquidado = null;
        }


        if(this.informeHonorariosEnviado != null ){
//          actualizarTabla();
            FacesContext.getCurrentInstance().addMessage(
                    "growl",new FacesMessage("Informe enviado","El informe de honorarios de la solicitud número "+this.informeHonorariosEnviado+"fue enviado"));
            this.informeHonorariosEnviado  = null;

        }
        
        if(!FacesContext.getCurrentInstance().getPartialViewContext().isAjaxRequest()){
        	restartTime();
        }
    }

    public void refresh(){
        Date now = new Date();

        long minutos = 120;

        if (now.getTime() - time.getTime() > (1000*60*minutos)) {
            HttpServletRequest request = (HttpServletRequest) FacesContext
                    .getCurrentInstance().getExternalContext().getRequest();
            Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
            if (auth != null){    
            	String uri = FacesContext
                        .getCurrentInstance()
                        .getExternalContext()
                        .encodeActionURL(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/login.xhtml");
            	
            	try {
                    FacesContext.getCurrentInstance().getExternalContext()
                            .redirect(uri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            	
            	new SecurityContextLogoutHandler()
                .logout(request, null, auth);
            }
        }
    }

    public void actualizarModeloMapa(AvaluoDTO avaluo) {
        gLatLng = avaluoController.calcularCoordenadasAvaluo(avaluo, avaluo
                .getDivipola().getDepartamento(), false);
        if (gLatLng != null) {
            mapModel = listadoAvaluosController.actualizarModeloMapa(gLatLng,
                    avaluo.getDireccionInmueble());
        }else{
            mapModel = new DefaultMapModel();
        }
    }

    public String redireccionarListado() {
        return FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .encodeActionURL(
                        FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/pages/avaluos/listadoAvaluos.xhtml");
    }

    public void cargarResumen() {
    this.avaluo.setDireccionInmueble(avaluo.getDireccionInmueble() != null && avaluo.getAdicionalDireccion() != null ?
            avaluo.getDireccionInmueble().contains(avaluo.getAdicionalDireccion()) ? avaluo.getDireccionInmueble() : avaluo.getDireccionInmueble() + " " + avaluo.getAdicionalDireccion() :
            avaluo.getDireccionInmueble() != null ? avaluo.getDireccionInmueble() : avaluo.getAdicionalDireccion());
        if (this.avaluo.getTipoAvaluo() != null &&  this.avaluo.getTipoAvaluo().getNombre() != null &&
            this.avaluo.getTipoAvaluo().getNombre().equals(Constantes.TIPO_AVALUO_REMATE)) {
            UsuarioDTO abogado =  this.avaluoController.buscarAbogado( this.avaluo.getId() );
            this.avaluo.setNombreAsesor( abogado.getNombres() );
            this.avaluo.setCelularAsesor( abogado.getCelular() );
            this.avaluo.setTelefonoAsesor( abogado.getTelefono() );
            this.avaluo.setCorreoElectronicoAsesor( abogado.getEmail() );
        } else{
            UsuarioDTO asesor =  this.avaluoController.buscarAsesor( this.avaluo.getId() );
            this.avaluo.setNombreAsesor( asesor.getNombres() );
            this.avaluo.setCelularAsesor( asesor.getCelular() );
            this.avaluo.setTelefonoAsesor( asesor.getTelefono() );
            this.avaluo.setCorreoElectronicoAsesor( asesor.getEmail() );
        }
        UsuarioDTO perito =   this.avaluoController.buscarPerito( this.avaluo.getId() );
        this.avaluo.setPerito(perito);
        eventoDTO = programarCitaController.encontrarUltimoEventoAvaluo(avaluo);

        if(eventoDTO==null){
            eventoDTO = new EventoDTO();
        }

        ClienteDTO cliente  = this.avaluoController.buscarCliente( this.avaluo.getId() );
        this.avaluo.setCliente(cliente);

        UsuarioDTO personaRecibe = this.avaluoController.buscarPersonaRecibe( this.avaluo.getId() );
        this.avaluo.setNombreRecibe( personaRecibe.getNombres() );
        this.avaluo.setTelefonoRecibe( personaRecibe.getTelefono() );
        this.avaluo.setCorreoElectronicoRecibe( personaRecibe.getEmail() );
    }

    public void filtrar() {
        DataTable tablaAvaluos = (DataTable) FacesContext
            .getCurrentInstance().getViewRoot()
            .findComponent("form:tabla_Avaluos");
        filtros = tablaAvaluos.getFilters();
    	/*
    	  Para el código externo y la cédula se eliminan los caracteres
    	  especieales dejando solo valores numericos.
    	  */
    	if(filtros.containsKey("codigoExterno")) {
    		filtros.put( "codigoExterno", StringUtils.obtenerNumeros(filtros.get("codigoExterno") )); 
    	}
    	
    	if (filtros.containsKey("cliente.numeroDocumento")) {
    		filtros.put( "cliente.numeroDocumento", StringUtils.obtenerNumeros(filtros.get("cliente.numeroDocumento") )); 
    	}
        log.info("Filtros del listado: {}", filtros);
    }

    public LazyDataModel<AvaluoDTO> getAvaluosLazyModel() {
        return avaluosLazyModel;
    }

    public List<String> getFilteredData() {
        return filteredData;
    }

    public void setFilteredData(List<String> filteredData) {
        this.filteredData = filteredData;
    }

    public Map<String, Object> getFiltros() {
        return filtros;
    }

    public void setFiltros(Map<String, Object> filtros) {
        this.filtros = filtros;
    }

    public AvaluoDTO getAvaluo() {
        return avaluo;
    }

    public void setAvaluo(AvaluoDTO avaluo) {
        this.avaluo = avaluo;
    }

    public EventoDTO getEventoDTO() {
        return eventoDTO;
    }

    public void setEventoDTO(EventoDTO eventoDTO) {
        this.eventoDTO = eventoDTO;
    }

    public GLatLng getgLatLng() {
        return gLatLng;
    }

    public void setgLatLng(GLatLng gLatLng) {
        this.gLatLng = gLatLng;
    }

    public String getValorHonorarios() {
        return valorHonorarios;
    }

    public void setValorHonorarios(String valorHonorarios) {
        this.valorHonorarios = valorHonorarios;
    }

    public MapModel getMapModel() {
        return mapModel;
    }

    public void setMapModel(MapModel mapModel) {
        this.mapModel = mapModel;
    }

    public String getSolicitudCreada() {
        return solicitudCreada;
    }

    public void setSolicitudCreada(String solicitudCreada) {
        this.solicitudCreada = solicitudCreada;
    }

    public String getSolicitudActualizada() {
        return solicitudActualizada;
    }

    public void setSolicitudActualizada(String solicitudActualizada) {
        this.solicitudActualizada = solicitudActualizada;
    }

    public String getSolicitudAsignada() {
        return solicitudAsignada;
    }

    public void setSolicitudAsignada(String solicitudAsignada) {
        this.solicitudAsignada = solicitudAsignada;
    }

    public String getSolicitudAceptada() {
        return solicitudAceptada;
    }

    public void setSolicitudAceptada(String solicitudAceptada) {
        this.solicitudAceptada = solicitudAceptada;
    }

    public String getInformeGuardado() {
        return informeGuardado;
    }

    public void setInformeGuardado(String informeGuardado) {
        this.informeGuardado = informeGuardado;
    }

    public String getCitaProgramada() {
        return citaProgramada;
    }

    public void setCitaProgramada(String citaProgramada) {
        this.citaProgramada = citaProgramada;
    }

    public String getCitaReProgramada() {
        return citaReProgramada;
    }

    public void setCitaReProgramada(String citaReProgramada) {
        this.citaReProgramada = citaReProgramada;
    }

    public String getInformeEnviado() {
        return informeEnviado;
    }

    public void setInformeEnviado(String informeEnviado) {
        this.informeEnviado = informeEnviado;
    }

    public String getInformeEnviadoAComite() {
        return informeEnviadoAComite;
    }

    public void setInformeEnviadoAComite(String informeEnviadoAComite) {
        this.informeEnviadoAComite = informeEnviadoAComite;
    }

    public String getCorrecionesSolicitadas() {
        return correcionesSolicitadas;
    }

    public void setCorrecionesSolicitadas(String correcionesSolicitadas) {
        this.correcionesSolicitadas = correcionesSolicitadas;
    }

    public String getInformeLiquidado() {
        return informeLiquidado;
    }

    public void setInformeLiquidado(String informeLiquidado) {
        this.informeLiquidado = informeLiquidado;
    }

    public String getJustificacionRechazo() {
        return justificacionRechazo;
    }

    public void setJustificacionRechazo(String justificacionRechazo) {
        this.justificacionRechazo = justificacionRechazo;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public UsuarioDTO getUsuarioActivo() {
        return usuarioActivo;
    }

    public void setUsuarioActivo(UsuarioDTO usuarioActivo) {
        this.usuarioActivo = usuarioActivo;
    }

    public TipoAvaluoDTO getTipoAvaluo() {
        return tipoAvaluo;
    }

    public void setTipoAvaluo(TipoAvaluoDTO tipoAvaluo) {
        this.tipoAvaluo = tipoAvaluo;
    }

    public FormatoInforme getFormatoInforme() {
        return formatoInforme;
    }

    public void setFormatoInforme(FormatoInforme formatoInforme) {
        this.formatoInforme = formatoInforme;
    }

    public List<SucursalDTO> autocompleteSucursal(String query) {
        return avaluoController.sucursalesEnEntidad(query, avaluo.getEntidad()
                .getId());
    }

    public void nuevaSolicitud() {
        avaluo = null;
        String uri = FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .encodeActionURL(
                        FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/pages/solicitudes/avaluo.xhtml");
        try {
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect(uri);
        } catch (IOException e) {
            // TODO Reireccionar a página no encontrada
            e.printStackTrace();
        }
    }

    public void editarSolicitud() {
        log.debug("editarSolicitud");
        String uri = FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .encodeActionURL(
                        FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/pages/solicitudes/avaluo.xhtml?id=" + avaluo.getId());
        try {
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect(uri);
        } catch (IOException e) {
            // TODO Reireccionar a página no encontrada
            e.printStackTrace();
        }
    }

    /**
     * Se elige el avaluo seleccionado en la tabla para asignar el perito
     */
    public void asignarPerito() {
        String uri = FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .encodeActionURL(
                        FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/pages/avaluos/asignarPerito.xhtml");
        try {
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect(uri);
        } catch (IOException e) {
            // TODO Reireccionar a página no encontrada
            e.printStackTrace();
        }
    }
    public void reprogramarCita(){
        this.mostrarCitaReprogramada = Boolean.TRUE;
        this.redireccionarCita();
    }
    public void programarCita() {
        this.mostrarCitaReprogramada = Boolean.FALSE;
        this.redireccionarCita();
    }
    private void redireccionarCita(){
        String uri = FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .encodeActionURL(
                        FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/pages/avaluos/programarCita.xhtml");
        try {
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect(uri);
        } catch (IOException e) {
            // TODO Reireccionar a página no encontrada
            e.printStackTrace();
        }
    }

    public void verEstados() {
        String uri = FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .encodeActionURL(
                        FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/pages/avaluos/estadosAvaluo.xhtml");
        try {
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect(uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void verBitacora() {
        String uri = FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .encodeActionURL(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/pages/avaluos/bitacora.xhtml");
        try {
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect(uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void aceptarCaso() {
        log.info("Acción aceptarCaso");
        if (!listadoAvaluosController.aceptarCaso(avaluo, usuarioActivo)) {
            FacesContext.getCurrentInstance().addMessage(
                    "growl",
                    new FacesMessage("Error",
                            "No es posible aceptar. El caso ya ha sido aceptado."));
            actualizarMenu();
        } else {
            //setSolicitudAceptada(avaluo.getCodigoExterno());
            programarCita();
        }
    }

    private void actualizarMenu() {
        RequestContext.getCurrentInstance().update(":formBtnAccion");
    }

    public void rechazarCaso() {
        log.debug("Acción rechazarCaso");
        if (!listadoAvaluosController.rechazarCaso(avaluo,
                justificacionRechazo, usuarioActivo)) {
            FacesContext.getCurrentInstance().addMessage(
                    "growl",
                    new FacesMessage("Error",
                            "No se pudo rechazar el caso " +
                            "el caso ya fue rechazado."));
            actualizarMenu();
        }else{
            justificacionRechazo = "";
        }
    }

    public void solicitarDevolucion() {
        log.debug("Solicitar devolución de avaluo {}", avaluo);
        if (!listadoAvaluosController.solicitarDevolucion(avaluo, justificacion,usuarioActivo)){
            mostrarNotificacion("Error",
                    "No es posible solicitar devolución. La devolución ya ha sido solicitada.");
            actualizarMenu();
        }
        this.justificacion = "";
    }

    public void devolver() {
        log.debug("Devolver avaluo {}",
                avaluo);
        if (!listadoAvaluosController.devolver(avaluo, usuarioActivo)) {
            mostrarNotificacion("Error", "No se puede devolver el avalúo ya fue devuelto.");
            actualizarMenu();
        }
    }

    public void reactivar() {
        log.debug("Reactivar avaluo {}",
                avaluo);
        if (!listadoAvaluosController.reactivar(avaluo, usuarioActivo)) {
            FacesContext.getCurrentInstance().addMessage(
                    "growl",
                    new FacesMessage("Error",
                            "No se pudo reactivar el caso, " +
                                    "ya fue reactivado."));
            actualizarMenu();
        }
    }

    public void confirmarDocumentos() {
        if (!informeHipotecarioController
                .confirmarDocumentosCompletos(avaluo, usuarioActivo)) {
            FacesContext.getCurrentInstance().addMessage(
                    "growl",
                    new FacesMessage("Error",
                            "No se pudo confirmar documentos del caso, " +
                                    "los documentos ya fueron confirmados."));
        }else{
            FacesContext.getCurrentInstance().addMessage("growl",
                    new FacesMessage("Documentos Confirmados",
                            "Los documentos de la solicitud Nª " +
                                    avaluo.getCodigoExterno() +
                                    " fueron confirmados exitosamente."));
        }
        actualizarMenu();
    }

    public void confirmarPago() {
        if (!informeHipotecarioController.confirmarPago(avaluo, usuarioActivo)) {
            FacesContext.getCurrentInstance().addMessage(
                    "growl",
                    new FacesMessage("Error",
                            "No se pudo cofirmar pago del caso, " +
                                    "el pago ya fue confirmado."));
            actualizarMenu();
        }
    }

    public void ingresarInforme() {
        log.info("ingresarInforme {}", avaluo);
        if (this.avaluo.getEstado().getEstado() == Constantes.Estado.CitaProgramada) {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('tipoAvaluoDialog').hide(); PF('confirmarVisitaDialog').show();");
            return;
        }
        if (avaluoController.encontrarFormatoInformeId(avaluo.getId()) == null) {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('tipoAvaluoDialog').show(); PF('avaluoDialog').hide();");
            return;
        }
        String uri = "";
        Date fecha = new Date();
        if (tarifaUvrController
                .encontrarPorFecha
                        (DateUtils
                                .getFechaFormateada
                                        (DateUtils.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS, fecha)) != null ){
            avaluo.setFormatoInforme(avaluoController.cargarFormatoInforme(avaluo.getId()));
            if (avaluo.getFormatoInforme().getClass()
                    .equals(FormatoInformeHipotecarioDTO.class)) {
                uri = FacesContext
                        .getCurrentInstance()
                        .getExternalContext()
                        .encodeActionURL(
                                FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/pages/informes/hipotecario.xhtml");
            } else if (avaluo.getFormatoInforme().getClass()
                    .equals(FormatoInformeComercialDTO.class)) {
                if (avaluo.getSector().equals("Rural")) {
                    uri = FacesContext
                            .getCurrentInstance()
                            .getExternalContext()
                            .encodeActionURL(
                                    FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/pages/informes/comercial-rural.xhtml");
                } else {
                    uri = FacesContext
                            .getCurrentInstance()
                            .getExternalContext()
                            .encodeActionURL(
                                    FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/pages/informes/comercial-urbano.xhtml");
                }
            } else {
                uri = FacesContext
                        .getCurrentInstance()
                        .getExternalContext()
                        .encodeActionURL(
                                FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/pages/avaluos/listadoAvaluos.xhtml");
            }
            try {
                FacesContext.getCurrentInstance().getExternalContext()
                        .redirect(uri);
            } catch (IOException e) {
                // TODO Reireccionar a página no encontrada
                e.printStackTrace();
            }
        }else{
            FacesContext.getCurrentInstance().addMessage(
                    "growl",
                    new FacesMessage(FacesMessage.SEVERITY_WARN,"No existe UVR para este día", "Por favor contacte al administrador del sistema."));
        }
    }

    public void subirFotos() {
        log.debug("subirFotos");
        String uri = FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .encodeActionURL(
                        FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/pages/informes/subirFotos.xhtml");
        try {
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect(uri);
        } catch (IOException e) {
            // TODO Reireccionar a página no encontrada
            e.printStackTrace();
        }
    }
    
    public void subirAnexos() {
        log.debug("subirAnexos");
        String uri = FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .encodeActionURL(
                		FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/pages/informes/anexos.xhtml");
        try {
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect(uri);
        } catch (IOException e) {
            // TODO Reireccionar a página no encontrada
            e.printStackTrace();
        }
    }
    
    public void subirCroquis() {
        log.debug("subirCroquis");
        String uri = FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .encodeActionURL(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+
                        "/pages/informes/croquis.xhtml");
        try {
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect(uri);
        } catch (IOException e) {
            // TODO Reireccionar a página no encontrada
            e.printStackTrace();
        }
    }

    public void revisarAvaluo() {
        log.debug("revisarAvaluo");
        java.util.Date fecha = new Date();
        if(tarifaUvrController.encontrarPorFecha(DateUtils.getFechaFormateada(DateUtils.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS, fecha)) != null){
            String uri = FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .encodeActionURL(
                            FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/pages/informes/hipotecario.xhtml");
            try {
                FacesContext.getCurrentInstance().getExternalContext()
                        .redirect(uri);
            } catch (IOException e) {
                // TODO Reireccionar a página no encontrada
                e.printStackTrace();
            }
        }else{
            FacesContext.getCurrentInstance().addMessage(
                    "growl",
                    new FacesMessage(FacesMessage.SEVERITY_WARN,"No existe UVR para este día", "Por favor contacte al administrador del sistema."));
        }
    }

    public void corregirInforme() {
        log.debug("corregirAvaluo");
        String uri = FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .encodeActionURL(
                        FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/pages/informes/hipotecario.xhtml");
        try {
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect(uri);
        } catch (IOException e) {
            // TODO Reireccionar a página no encontrada
            e.printStackTrace();
        }
    }

    public void exportarArchivoBUA() {
        log.debug("exportarArchivoBUA");
        String uri = FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .encodeActionURL(
                        FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/pages/avaluos/exportarArchivoBua.xhtml");
        try {
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect(uri);
        } catch (IOException e) {
            // TODO Reireccionar a página no encontrada
            e.printStackTrace();
        }
    }

    public void subirDocumentos() {
        log.debug("subir Archivo");
        String uri = FacesContext.getCurrentInstance().getExternalContext().encodeActionURL(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/pages/avaluos/subirDocumentos.xhtml");
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(uri);
        } catch (IOException e) {
            // TODO Reireccionar a página no encontrada
            e.printStackTrace();
        }
    }

    public void confirmarTipoAvaluo() {
        log.debug("Guardando tipo de avaluo y continuando según se elija");
        /*if (!avaluo.getTipoAvaluo().equals(this.tipoAvaluo)) {
            listadoAvaluosController
                    .actualizarTipoAvaluo(avaluo, usuarioActivo,
                            this.tipoAvaluo);
        } else {*/
            if (formatoInforme != null && avaluo.getFormatoInforme() == null) {
                avaluo.setFormatoInforme(
                        formatoInforme.equals(
                                FormatoInforme.FormatoInformeHipotecario)
                                ? new FormatoInformeHipotecarioDTO(avaluo.getId())
                                : new FormatoInformeComercialDTO(avaluo.getId()));
                avaluoController.crearFormatoInforme(avaluo);
            }
        //}
            //La siguiente validacion tambien se hace dentro del método ingresarInforme()
        /*if (this.avaluo.getEstado().getEstado() == Constantes.Estado.CitaProgramada) {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('tipoAvaluoDialog').hide(); PF('confirmarVisitaDialog').show();");
        }else{*/
            ingresarInforme();
        //}
    }

    public void confirmarVisita() {
        if (!listadoAvaluosController.confirmarVisita(avaluo, usuarioActivo)) {
            FacesContext.getCurrentInstance().addMessage(
                    "growl",
                    new FacesMessage("Error",
                            "No se pudo cofirmar la visita del caso, " +
                                    "la visita ya fue confirmada."));
            actualizarMenu();
            return;
        }
        
        avaluo = avaluoController.encontrarPorId(avaluo.getId());
        
        ingresarInforme();
        
        actualizarMenu();
    }

    public void cancelarAvaluo() {
        if (!listadoAvaluosController.cancelarAvaluo(avaluo, usuarioActivo)) {
            mostrarNotificacion("Error", "No se puede cancelar el avalúo ya fue cancelado.");
            actualizarMenu();
        }
    }

    public void guardar() {
        log.debug("guardarAvaluo");
        avaluoController.actualizar(avaluo);
        String uri = FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .encodeActionURL(
                        FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/pages/avaluos/listadoAvaluos.xhtml");
        try {
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect(uri);
        } catch (IOException e) {
            // TODO Reireccionar a página no encontrada
            e.printStackTrace();
        }
    }

    public void calcularValorHonorarios(){
        if( this.avaluo != null ){
            List<Object> honorarios = this.avaluoController.getHonorariosPerito( this.avaluo.getId() );
            if( honorarios!=null && !honorarios.isEmpty() ){
                NumberFormat formateadorDinero = NumberFormat
                    .getCurrencyInstance(Locale.forLanguageTag("es-CO"));
                    formateadorDinero.setMaximumFractionDigits(2);
                Object[] objects = (Object[]) honorarios.get(0);
                avaluo.setGastosTranslado( MathUtils.getBigDecimal( objects[0]) );
                avaluo.setIva( MathUtils.getBigDecimal(objects[1]) );
                avaluo.setValorLiquidacion( MathUtils.getBigDecimal(objects[2]));
                avaluo.setPerito(usuarioController.cargarUsuario(avaluo.getPerito().getTipoDocumentoIdentificacion(), avaluo.getPerito().getNumeroDocumento()));
                BigDecimal gastosTranslado = (avaluo.getGastosTranslado() == null)?
                    BigDecimal.ZERO : avaluo.getGastosTranslado()
                    .setScale(2, RoundingMode.HALF_EVEN);
                BigDecimal ivaBigDecimal = avaluo.getIva() == null ?
                        BigDecimal.ZERO : avaluo.getIva() ;
                BigDecimal totalHonorarios = (avaluo.getValorLiquidacion() == null )?
                        BigDecimal.ZERO
                        :avaluo.getValorLiquidacion().add(gastosTranslado)
                            .add(ivaBigDecimal).setScale(2, RoundingMode.HALF_EVEN) ;
                String totalHonorariosStr = formateadorDinero.format( totalHonorarios.doubleValue() );
                this.valorHonorarios = totalHonorariosStr;
            }

        }
    }

    private void mostrarNotificacion(String titulo, String mensaje) {
        FacesContext.getCurrentInstance().addMessage(
                "growl",
                new FacesMessage(titulo, mensaje));
    }

    public boolean esCobradoPorBancol(AvaluoDTO avaluoDTO) {
        return avaluoController.esCobradoPorBancol(avaluoDTO);
    }

    //---------------PERMISOS----------------

    public boolean isCrearRol() {
        return crearRol;
    }

    public boolean isCrearUsuario() {
        return crearUsuario;
    }

    public boolean isCrearSolicitudes() {
        return crearNuevaSolicitud;
    }

    public boolean isEditarSolicitud() {
        return editarSolicitud;
    }

    public boolean isAsignarPerito() {
        return asignarSolicitud;
    }

    public boolean isReasignarSolicitud() {
        return reasignarSolicitud;
    }

    public boolean isAceptarSolicitudes() {
        return aceptarCaso;
    }

    public boolean isRechazarCaso() {
        return rechazarCaso;
    }

    public boolean isProgramarCita() {
        return programaCita;
    }

    public boolean isReProgramarCita() {
        return reprogramarCita;
    }

    public boolean isConfirmarVisita() {
        return confirmarVisita;
    }

    public boolean isConfirmarDocumentos() {
        return confirmarDocumentos;
    }

    public boolean isConfirmarPago() {
        return confirmarPago;
    }

    public boolean isIngresarInforme() {
        return ingresarInforme;
    }

    public boolean isRevisarAvaluos() {
        return revisarEditarAvaluo;
    }

    public boolean isCargarFotos() {
        return cargarFotos;
    }

    public boolean isEnviarNotificacionesHonorarios() {
        return enviarNotificacionesHonorarios;
    }

    public boolean isEnviar() {
        return enviar;
    }

    public boolean isVerCorreccionesSolicitadas() {
        return verCorreccionesSolicitadas;
    }

    public boolean isSolicitarDevolucion() {
        return solicitarDevolucion;
    }

    public boolean isDevolverCaso() {
        return devolverCaso;
    }

    public boolean isReactivarCaso() {
        return reactivarCaso;
    }

    public boolean isSolicitarCorrecciones() {
        return solicitarCorrecciones;
    }

    public boolean isComite() {
        return comite;
    }

    public boolean isEnviarAComite() {
        return enviarAComite;
    }

    public boolean isDescargarPDFSinFirmas() {
        return descargarPDFSinFirmas;
    }

    public boolean isVerInformacionDePerito() {
        return verInformacionDePerito;
    }

    public boolean isEditarAvaluoDespuesDeAprobado() {
        return editarAvaluoDespuesDeAprobado;
    }

    public boolean isLiquidarAvaluos() {
        return aprobarAvaluo;
    }

    public boolean isSeguirAvaluos() {
        return verInformesSinEditar;
    }

    public boolean isDescargarPDFConFirmas() {
        return descargarPDFconFirmas;
    }

    public boolean isDescargarPDFAnexoMetodologias(){
        return descargarPDFAnexoMetodologias;
    }

    public boolean isCancelarAvaluo() {
        return cancelarAvaluo;
    }

    public boolean isExportarBUA() {
        return exportarBUA;
    }

    public boolean isExportarReportes() {
        return exportarReportes;
    }

    public boolean isVerAgenda() {
        return verAgenda;
    }

    public boolean isEstudioMercado() {
        return estudioMercado;
    }

    public boolean isRechazarDevolucion() {
        return rechazarDevolucion;
    }

    public boolean isVerFotos() {
        return verFotos;
    }

    public boolean isCorregirInforme() {
        return corregirInforme;
    }

    public boolean isCrearEntidades() {
        return crearEntidades;
    }

    public boolean isCrearSegmentos() {
        return crearSegmentos;
    }

    public boolean isCrearSucursales() {
        return crearSucursales;
    }

    public boolean isPermisoAbogado() {
        return permisoAbogado;
    }

    public boolean isEditarCliente() {
        return permisoEditarCliente;
    }

    public boolean isCambiarFechaAprobacion() {
        return cambiarFechaAprobacion;
    }
    
    public boolean isCambiarCodigoExternoClienteID() {
		return cambiarCamposClaves;
	}

	public void setCambiarFechaAprobacion(boolean cambiarFechaAprobacion) {
        this.cambiarFechaAprobacion = cambiarFechaAprobacion;
    }
	
	public boolean isModificarIdSisgen() {
		return modificarIdSisgen;
	}
    //---------------FIN PERMISOS--------------------

    public StreamedContent getFile() {
        String url = path + "imagenes" + File.separator + avaluo.getId() + ".pdf";

        generarPDF();
        /*AFVV 2017-02-22: Se quita la firma digital por vencimiento de certificado*/
        /*PDFDigitalSign digitalSign = new PDFDigitalSign(path);
        digitalSign.firmar(url);*/

        avaluo.getEntidad().setPrefijo(avaluoController.cargarPrefijoEntidad(avaluo.getEntidad().getId()));
        
        Motivo motivoAvaluo =  motivoService.getMotivosById(avaluo.getMotivo().longValue());
        
        File file1 = new File(url);

        InputStream stream;
        try {
            stream = new FileInputStream(file1);
            file = new DefaultStreamedContent(stream, "application/pdf", avaluo
                    .getEntidad().getPrefijo()
                    + "-"
                    + avaluo.getCodigoExterno()
                    + (motivoAvaluo.getPrefijo() != null ? "-" :"")
                    + (motivoAvaluo.getPrefijo() != null ? motivoAvaluo.getPrefijo() : "")
                    + ".pdf");
        } catch (FileNotFoundException e) {
            log.error("No se encontró el archivo de PDF generado", e);
        }

        return file;
    }

    public void generarPDF() {
        HashMap<String, Object> hm = new HashMap<String, Object>();

        String reporte = this.avaluo.getTipoAvaluo().getNombre()
                .equals(Constantes.TIPO_AVALUO_REMATE) ? "reporte_remate.jasper"
                : "reporte_Portada.jasper";

        String contexto = path + reporte;

        Connection connection = null;

        this.setRutaPDF(path + "imagenes" + File.separator + avaluo.getId()
                + ".pdf");

        hm.put("IDAVALUO", avaluo.getId());
        hm.put("SUBREPORT_DIR", path);

        JasperPrint jasperPrint = null;
        try {
            connection = dataSource.getConnection();
            jasperPrint = JasperFillManager
                    .fillReport(contexto, hm, connection);
            JasperExportManager
                    .exportReportToPdfFile(jasperPrint, this.rutaPDF);

            File file1 = new File(this.rutaPDF);
            
            Motivo motivoAvaluo =  motivoService.getMotivosById(avaluo.getMotivo().longValue());
            
            InputStream stream = new FileInputStream(file1);
            file = new DefaultStreamedContent(stream, "application/pdf", avaluo
                    .getEntidad().getPrefijo()
                    + "-"
                    + avaluo.getCodigoExterno()
                    + (motivoAvaluo.getPrefijo() != null ? "-" :"")
                    + (motivoAvaluo.getPrefijo() != null ? motivoAvaluo.getPrefijo() : "")
            		+".pdf");

            stream.close();
        } catch (JRException e) {
            log.error("Generando PDF de avaluo: {}",
                    avaluo.getId(), e);
        } catch (SQLException e) {
            log.error("Generando PDF de avaluo: {}",
                    avaluo.getId(), e);
        } catch (FileNotFoundException e) {
            log.error("Generando PDF de avaluo: {}",
                    avaluo.getId(), e);
        } catch (IOException e) {
            log.error("Generando PDF de avaluo: {}",
                    avaluo.getId(), e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                log.error("Generando PDF de avaluo, Cerrando conexión: {}",
                        avaluo.getId(), e);
            }
        }
    }

    public StreamedContent getFileAnexoMetodologias() {
        if (generarPDFAnexoMetodologias()) {
            File fileAnexoMetodologias = new File(path + "imagenes/" + avaluo.getId() + "_AnexoMetodologias.pdf");
            InputStream stream;
            try{
                stream = new FileInputStream(fileAnexoMetodologias);
                file = new DefaultStreamedContent(stream, "application/pdf", "Cod-"
                        + avaluo.getCodigoExterno()
                        + "_AnexoMetodologias.pdf");
            }catch(FileNotFoundException e){
                e.getMessage();
            }
            return file;
        }else{
            FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Anexo Metodologias", "El avaluo no contiene calculadoras  para exportart a PDF"));
            return new DefaultStreamedContent();
        }
    }

    public boolean generarPDFAnexoMetodologias() {
        HashMap<String, Object> hm = new HashMap<String, Object>();
        String reporte = "";
        esFito = this.avaluoController.tieneMetodologiaFito(avaluo.getId());
        esComparacion = this.avaluoController
                .tieneMetodologiaComparacion(avaluo.getId());
        esPH = this.avaluoController.avaluoEsPH(avaluo.getId());
        if (esFito && !esComparacion) {
            reporte = "fito.jasper";
        } else if (!esFito && esComparacion && esPH) {
            reporte = "comparacionPH.jasper";
        } else if (!esFito && esComparacion && !esPH) {
            reporte = "comparacionNPH.jasper";
        } else if (esFito && esComparacion && esPH) {
            reporte = "fito_comparacionPH.jasper";
        } else if (esFito && esComparacion && !esPH) {
            reporte = "fito_comparacionNPH.jasper";
        } else {
            return false;
        }
        if (!"".equals(reporte)) {
            String contexto = path + reporte;
            Connection connection = null;
            this.setRutaPDF(path + "imagenes" + File.separator + avaluo.getId()
                    + "_AnexoMetodologias.pdf");
            hm.put("IDAVALUO", avaluo.getId());
            hm.put("SUBREPORT_DIR", path);
            JasperPrint jasperPrint;
            try {
                connection = dataSource.getConnection();
                jasperPrint = JasperFillManager.fillReport(contexto, hm,
                        connection);
                JasperExportManager.exportReportToPdfFile(jasperPrint,
                        getRutaPDF());

                File file1 = new File(getRutaPDF());

                InputStream stream = new FileInputStream(file1);
                file = new DefaultStreamedContent(stream, "application/pdf",
                        "Cod-" + avaluo.getCodigoExterno()
                                + "_AnexoMetodologias.pdf");
                stream.close();
            } catch (JRException e) {
                log.error("Generando PDF metodologias de avaluo: {}",
                        avaluo.getId(), e);
                log.error(e.getMessage());
            } catch (SQLException e) {
                log.error("Generando PDF metodologias de avaluo: {}",
                        avaluo.getId(), e);
                log.error(e.getMessage());
            } catch (FileNotFoundException e) {
                log.error("Generando PDF metodologias de avaluo: {}",
                        avaluo.getId(), e);
                log.error(e.getMessage());
            } catch (IOException e) {
                log.error("Generando PDF metodologias de avaluo: {}",
                        avaluo.getId(), e);
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("Generando PDF metodologias de avaluo, Cerrando conexión: {}",
                            avaluo.getId(), e);
                }
            }
            return true;
        }
        return false;
    }

    public StreamedContent getFileResumenSolicitud(){
        if(generarPDFResumenSolicitud()){
            File fileResumenSolicitud = new File(path + "imagenes/" + avaluo.getId() + "_ResumenSolicitud.pdf");
            InputStream stream;
            try{
                stream = new FileInputStream(fileResumenSolicitud);
                file = new DefaultStreamedContent(stream, "application/pdf", "Cod-"
                        + avaluo.getCodigoExterno()
                        + "_ResumenSolicitud.pdf");
            }catch(FileNotFoundException e){
                e.getMessage();
            }
            return file;
        }else{
            FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Resumen Solicitud .pdf", "no pudo ser creado"));
            return new DefaultStreamedContent();
        }
    }

    public boolean generarPDFResumenSolicitud() {
        HashMap<String, Object> hm = new HashMap<String, Object>();
        if (avaluo != null) {
            String contexto = path + "reporte_solicitud.jasper";
            Connection connection = null;
            this.setRutaPDF(path + "imagenes" + File.separator + avaluo.getId()
                    + "_ResumenSolicitud.pdf");
            hm.put("ID_AVALUO", avaluo.getId());
            JasperPrint jasperPrint;
            try {
                connection = dataSource.getConnection();
                jasperPrint = JasperFillManager.fillReport(contexto, hm,
                        connection);
                JasperExportManager.exportReportToPdfFile(jasperPrint,
                        getRutaPDF());
                File file1 = new File(getRutaPDF());

                InputStream stream = new FileInputStream(file1);
                file = new DefaultStreamedContent(stream, "application/pdf",
                        "Cod-" + avaluo.getCodigoExterno()
                                + "_ResumenSolicitud.pdf");
                stream.close();
            } catch (JRException e) {
                log.error("Generando PDF resumen de avaluo:  {}",
                        avaluo.getId(), e);
            } catch (SQLException e) {
                log.error("Generando PDF resumen de avaluo:  {}",
                        avaluo.getId(), e);
            } catch (FileNotFoundException e) {
                log.error("Generando PDF resumen de avaluo:  {}",
                        avaluo.getId(), e);
            } catch (IOException e) {
                log.error("Generando PDF resumen de avaluo:  {}",
                        avaluo.getId(), e);
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("Generando PDF resumen de avaluo, cerrando conexión: {}",
                            avaluo.getId(), e);
                }
            }
            return true;
        }
        return false;
    }
    
    private void restartTime() {
		this.time = new Date();
	}

    public boolean isRowSeleccionado() {
        return rowSeleccionado;
    }

    public void setRowSeleccionado(boolean rowSeleccionado) {
        this.rowSeleccionado = rowSeleccionado;
    }

    public boolean isRenderizar() {
        return renderizar;
    }

    public void setRenderizar(boolean renderizar) {
        this.renderizar = renderizar;
    }

    public String getRutaPDF() {
        return rutaPDF;
    }

    public void setRutaPDF(String rutaPDF) {
        this.rutaPDF = rutaPDF;
    }

    public String getInformeHonorariosEnviado() {
        return informeHonorariosEnviado;
    }

    public void setInformeHonorariosEnviado(String informeHonorariosEnviado) {
        this.informeHonorariosEnviado = informeHonorariosEnviado;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public FormatoInforme[] getFormatosInforme(){
        return FormatoInforme.values();
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public boolean isEsFito() {
        return esFito;
    }

    public void setEsFito(boolean esFito) {
        this.esFito = esFito;
    }

    public boolean isEsComparacion() {
        return esComparacion;
    }

    public void setEsComparacion(boolean esComparacion) {
        this.esComparacion = esComparacion;
    }

    public boolean isEsPH() {
        return esPH;
    }

    public void setEsPH(boolean esPH) {
        this.esPH = esPH;
    }


    public String getFiltroEstado() {
        return filtroEstado;
    }

    public void setFiltroEstado(String filtroEstado) {
        this.filtroEstado = filtroEstado;
    }

    public String getFiltroCodigoExterno() {
        return filtroCodigoExterno;
    }

    public void setFiltroCodigoExterno(String filtroCodigoExterno) {
        this.filtroCodigoExterno = filtroCodigoExterno;
    }

    public String getFiltroMotivo() {
		return filtroMotivo;
	}

	public void setFiltroMotivo(String filtroMotivo) {
		this.filtroMotivo = filtroMotivo;
	}

	public String getFiltroFechaEstado() {
        return filtroFechaEstado;
    }

    public void setFiltroFechaEstado(String filtroFechaEstado) {
        this.filtroFechaEstado = filtroFechaEstado;
    }

    public String getFiltroTipoAvaluo() {
        return filtroTipoAvaluo;
    }

    public void setFiltroTipoAvaluo(String filtroTipoAvaluo) {
        this.filtroTipoAvaluo = filtroTipoAvaluo;
    }

    public String getFiltroEntidad() {
        return filtroEntidad;
    }

    public void setFiltroEntidad(String filtroEntidad) {
        this.filtroEntidad = filtroEntidad;
    }

    public String getFiltroAsignado() {
        return filtroAsignado;
    }

    public void setFiltroAsignado(String filtroAsignado) {
        this.filtroAsignado = filtroAsignado;
    }

    public String getFiltroDireccion() {
        return filtroDireccion;
    }

    public void setFiltroDireccion(String filtroDireccion) {
        this.filtroDireccion = filtroDireccion;
    }

    public String getFiltroBarrio() {
        return filtroBarrio;
    }

    public void setFiltroBarrio(String filtroBarrio) {
        this.filtroBarrio = filtroBarrio;
    }

    public String getFiltroCiudad() {
        return filtroCiudad;
    }

    public void setFiltroCiudad(String filtroCiudad) {
        this.filtroCiudad = filtroCiudad;
    }

    public boolean isMostrarCitaReprogramada() {
        return mostrarCitaReprogramada;
    }

    public void setMostrarCitaReprogramada(boolean mostrarCitaReprogramada) {
        this.mostrarCitaReprogramada = mostrarCitaReprogramada;
    }

	public String getUrlReturn() {
		return urlReturn;
	}

	public void setUrlReturn(String urlReturn) {
		this.urlReturn = urlReturn;
	}

	public boolean isCambiarCamposClaves() {
		return cambiarCamposClaves;
	}

	public void setCambiarCamposClaves(boolean cambiarCamposClaves) {
		this.cambiarCamposClaves = cambiarCamposClaves;
	}
	
}
