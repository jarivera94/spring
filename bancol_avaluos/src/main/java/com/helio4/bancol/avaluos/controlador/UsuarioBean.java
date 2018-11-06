package com.helio4.bancol.avaluos.controlador;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SortedMap;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.model.DualListModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import com.helio4.bancol.avaluos.dominio.ListadoUsuariosController;
import com.helio4.bancol.avaluos.dominio.ListasController;
import com.helio4.bancol.avaluos.dominio.ListasGeograficasController;
import com.helio4.bancol.avaluos.dominio.RolController;
import com.helio4.bancol.avaluos.dominio.UsuarioController;
import com.helio4.bancol.avaluos.dto.DivipolaDTO;
import com.helio4.bancol.avaluos.dto.EntidadDTO;
import com.helio4.bancol.avaluos.dto.PermisoDTO;
import com.helio4.bancol.avaluos.dto.RegionalDTO;
import com.helio4.bancol.avaluos.dto.RolDTO;
import com.helio4.bancol.avaluos.dto.TipoAvaluoDTO;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;
import com.helio4.bancol.avaluos.servicio.util.Constantes;

/**
 * Bean controlador de la vista de creación y edición de un usuario del
 * aplicativo GIA.
 *
 * Hay dos modos en que funciona este Bean:
 * <ul>
 * <li>Modo de creación: se ingresa desde el botón de crear nuevo usuario en la
 * lista de usuarios {@link ListadoUsuariosBean} cuando se invoca el método de
 * entrada {@link #init} se recupera el
 * {@link ListadoUsuariosBean#usuarioSeleccionado} dentro de la variable
 * {@link #usuario} si es nulo se ingresa en modo de creación.
 *
 * <li>Cuando el {@link #usuario} no es nulo el Bean entra en modo de edición.
 * </ul>
 */
@Controller
@Scope("view")
@Qualifier("usuarioBean")
public class UsuarioBean implements Serializable {

    /** Logger and serial id */
    private static Logger log = LoggerFactory.getLogger(UsuarioBean.class);
    private static final long serialVersionUID = 1L;

    /** Usuario de la lista de usuarios o Usuario nuevo en caso de creación */
    private UsuarioDTO usuario;

    /** Lista de departamentos para la página de creación del usuario */
    private SortedMap<String, String> departamentos;
    /** Lista de ciudades para la página de creación del usuario */
    private List<DivipolaDTO> ciudades;
    /** Lista de roles para la página de creación del usuario */
    private List<RolDTO> roles;
    /** Modelo del elemento pickList de la página para seleccionar entidades */
    private DualListModel<EntidadDTO> entidades;
    private List<EntidadDTO> todasEntidades;
    /** Modelo del elemento pickList de la página para seleccionar regionales */
    private DualListModel<RegionalDTO> regionales;
    private List<RegionalDTO> todasRegionales;
    /** Modelo del elemento pickList de la página para seleccionar tipoAvaluo */
    private DualListModel<TipoAvaluoDTO> tiposAvaluo;
    private List<TipoAvaluoDTO> todosTiposAvaluos;
    /** Lista de usuarios coordinadores para los usuarios con rol abogados */
    private List<UsuarioDTO> usuariosCoordinadores;
    /** Propiedad que almacena temporalmente el usuario coordinador elegido */
    private UsuarioDTO coordinadorAsociado;

    /** Permisos para validaciones en la página */
    private PermisoDTO permisoAceptarCasos = new PermisoDTO(
            Constantes.PERMISO_ACEPTAR_CASO);
    private PermisoDTO permisoAbogado = new PermisoDTO(
            Constantes.PERMISO_ABOGADO);
    private PermisoDTO permisoCrearSolicitudes = new PermisoDTO(
            Constantes.PERMISO_CREAR_NUEVA_SOLICITUD);
    private PermisoDTO permisoEditarSolicitudes = new PermisoDTO(
            Constantes.PERMISO_EDITAR_SOLICITUD);
    private PermisoDTO permisoCrearUsuarios = new PermisoDTO(
            Constantes.PERMISO_CREAR_USUARIO);
    private final RolDTO rolAdministrador  = new RolDTO(1L,"ADMIN",1L,"Listado Avaluos");

    /**
     * Propiedades que almacena los elemetos de los complementos de la direccion
     */
    private List<String> complementoVia;
    private List<String> complementoViaGeneradora;

    /** Propiedad que almacena si el Bean esta en modo crear o editar usuario */
    private boolean modoCrear;

    /** Propiedades auxiliares para mostrar elementos graficos */
    private boolean ocultarBotonGuardar;

    private final String[] letrasComplemento = { "A", "B", "C", "D", "E", "F",
            "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S",
            "T", "U", "V", "W", "X", "Y", "Z" };

    private final String[] puntosCardinales = { "Norte", "Sur", "Este", "Oeste",
            "Bis", "Noreste", "Sureste", "Noroeste", "Suroeste", "Par",
            "Impar" };

    @Autowired
    private ListasGeograficasController listasGeograficasController;

    @Autowired
    private ListasController listasController;

    @Autowired
    private RolController rolController;

    @Autowired
    private UsuarioController usuarioController;

    @Autowired
    private ListadoAvaluosBean listadoAvaluosController;

    /** Lista de Municipios para los peritos */
    private List<DivipolaDTO> divipolasUsuarios;

    /** Ciudad del usuario */
    private DivipolaDTO ciudad;

    private List<DivipolaDTO> divipolasSeleccionadas;
    
    private Integer tipoDocumentoIdentificacion;
	private Long numeroDocumento;

    @PostConstruct
    public void init() {
        usuario = new UsuarioDTO();
        this.usuario.setRol( rolAdministrador );
        this.usuario.setDivipolas(new ArrayList<DivipolaDTO>());
    }

    /**
     * Carga el usuario si se inicia en modo de edición
     */
    public void cargarUsuario() {
        if (usuario.getTipoDocumentoIdentificacion() != null
                && usuario.getNumeroDocumento() != null) {
            log.debug("Editar usuario: {}",
                    usuario);
            
            tipoDocumentoIdentificacion = usuario.getTipoDocumentoIdentificacion();
        	numeroDocumento = usuario.getNumeroDocumento();
            
            // Editar usuario
            usuario = usuarioController.cargarUsuario(
                    usuario.getTipoDocumentoIdentificacion(),
                    usuario.getNumeroDocumento());
            this.establecerCiudad();
            if (usuario.getTipoDocumentoCoordinador() != null
                    && usuario.getNumeroDocumentoCoordinador() != null) {
                coordinadorAsociado = usuarioController.cargarUsuario(
                        usuario.getTipoDocumentoCoordinador(),
                        usuario.getNumeroDocumentoCoordinador());
            }
            this.divipolasSeleccionadas = this.usuario.getDivipolas();
        } else {
            log.debug("Nuevo usuario");
            usuario.setEntidades(new ArrayList<EntidadDTO>());
            usuario.setRegionales(new ArrayList<RegionalDTO>());
            usuario.setTipoAvaluos(new ArrayList<TipoAvaluoDTO>());
            modoCrear = true;
            this.divipolasSeleccionadas = new ArrayList<DivipolaDTO>();

        }
        inicializarListas();
    }

    public void generarListaDivipolasPerito() {
        this.divipolasUsuarios = listasGeograficasController
                .obtenerMunicipios();
    }

    /**
     * Inicializa las listas usadas por la pagina JSF
     */
    public void inicializarListas() {
        departamentos = listasGeograficasController.departamentos();
        ciudades = usuario.getDepartamento() != null
                ? listasGeograficasController
                        .ciudadesEnDepartamento(usuario.getDepartamento())
                : new ArrayList<DivipolaDTO>();
        this.generarListaDivipolasPerito();
        /** Carga los roles que puede crear el usuario activo */
        roles = rolController.encontrarRoles(
                listadoAvaluosController.isCrearRol(),
                listadoAvaluosController.isPermisoAbogado());
        if (usuario != null && usuario.getNombreUsuario() != null) {
            cambioRol();
        }

        List<EntidadDTO> entidadesOrigen = listasController
                .encontrarEntidades();
        if (entidadesOrigen.size() == 1) {
            usuario.getEntidades().add(entidadesOrigen.get(0));
        }
        if (!usuario.getEntidades().isEmpty()) {
            entidadesOrigen.removeAll(usuario.getEntidades());
        }
        entidades = new DualListModel<EntidadDTO>(entidadesOrigen,
                usuario.getEntidades());
        todasEntidades = new ArrayList<>();
        todasEntidades.addAll(entidadesOrigen);
        todasEntidades.addAll(usuario.getEntidades());

        List<RegionalDTO> regionalesOrigen = listasController
                .encontrarRegionales();
        if (!usuario.getRegionales().isEmpty()) {
            regionalesOrigen.removeAll(usuario.getRegionales());
        }
        regionales = new DualListModel<RegionalDTO>(regionalesOrigen,
                usuario.getRegionales());
        
        setTodasRegionales(new ArrayList<>());
        getTodasRegionales().addAll(regionalesOrigen);
        getTodasRegionales().addAll(usuario.getRegionales());

        List<TipoAvaluoDTO> tiposAvaluoOrigen = listasController
                .encontrarTiposAvaluo();
        if (tiposAvaluoOrigen.size() == 1) {
            usuario.getTipoAvaluos().add(tiposAvaluoOrigen.get(0));
        }
        if (!usuario.getTipoAvaluos().isEmpty()) {
            tiposAvaluoOrigen.removeAll(usuario.getTipoAvaluos());
        }
        tiposAvaluo = new DualListModel<TipoAvaluoDTO>(tiposAvaluoOrigen,
                usuario.getTipoAvaluos());
        
        setTodosTiposAvaluos(new ArrayList<>());
        getTodosTiposAvaluos().addAll(tiposAvaluoOrigen);
        getTodosTiposAvaluos().addAll(usuario.getTipoAvaluos());
        
        complementoVia = usuario.getComplementoVia() != null
                ? convertirALista(usuario.getComplementoVia())
                : new ArrayList<String>();
        complementoViaGeneradora = usuario.getComplementoViaGeneradora() != null
                ? convertirALista(usuario.getComplementoViaGeneradora())
                : new ArrayList<String>();
        /**
         * Si el usuario creador es un coordinador se asigna al nuevo usuario y
         * se limita la lista de coordinadores
         */
        if (!listadoAvaluosController.isCrearRol()) {
            UsuarioDTO usuarioActivo = (UsuarioDTO) SecurityContextHolder
                    .getContext().getAuthentication().getPrincipal();
            usuario.setNumeroDocumentoCoordinador(
                    usuarioActivo.getNumeroDocumento());
            usuario.setTipoDocumentoCoordinador(
                    usuarioActivo.getTipoDocumentoIdentificacion());
            usuariosCoordinadores = new ArrayList<UsuarioDTO>();
            usuariosCoordinadores.add(usuarioActivo);
        }
    }

    public void cambioRol() {
        if (usuario.getRol().getPermisos().contains(permisoAbogado)) {
            usuariosCoordinadores = usuarioController
                    .buscarCoordinadoresAbogados();
        } else if (usuario.getRol().getPermisos()
                .contains(permisoCrearSolicitudes)
                && !usuario.getRol().getPermisos()
                        .contains(permisoEditarSolicitudes)
                && !usuario.getRol().getPermisos()
                        .contains(permisoCrearUsuarios)) {
            usuariosCoordinadores = usuarioController
                    .buscarCoordinadoresAsesores();
        }
    }

    /**
     * Cancela la creacion o edicion del usuario
     */
    public void cancelarUsuario() {
        String uri = FacesContext.getCurrentInstance().getExternalContext()
                .encodeActionURL(
                        FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/pages/configuracion/usuarios/listadoUsuarios.xhtml");
        try {
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect(uri);
        } catch (IOException e) {
            // TODO Reireccionar a página no encontrada
            e.printStackTrace();
        }
    }

    /**
     * Verifica el alias existente al digitarlo.
     */
    public void verificarAliasExistente() {
        // Verificar en que el alias no exista en la BD
        ocultarBotonGuardar = usuarioController
                .verificarAliasExistente(usuario.getNombreUsuario());
        if (ocultarBotonGuardar) {
            FacesContext.getCurrentInstance()
                    .addMessage("mensajes", new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "Error",
                            "Ya existe en la base de datos un usuario con este alias"));
        }
    }

    /**
     * Verifica numero y tipo de documento de usuario existente al digitar el
     * numero de identificacion.
     */
    public void verificarNumeroTipoDocumentoExistente() {
        // Verificar en que el numero no exista en la BD
        if (usuario.getTipoDocumentoIdentificacion() != null
                && usuario.getNumeroDocumento() != null) {
            ocultarBotonGuardar = usuarioController
                    .verificarNumeroTipoDocumentoExistente(
                            usuario.getTipoDocumentoIdentificacion(),
                            usuario.getNumeroDocumento());
            if (ocultarBotonGuardar) {
                FacesContext.getCurrentInstance()
                        .addMessage("mensajes", new FacesMessage(
                                FacesMessage.SEVERITY_ERROR, "Error",
                                "Ya existe en la base de datos un usuario con este tipo y número de documento"));
            }
        }
    }

    /**
     * Cambia la lista de ciudades al seleccionar un departamento en la pagina
     * JSF
     */
    public void cambiarDepartamento() {
        ciudades = listasGeograficasController
                .ciudadesEnDepartamento(usuario.getDepartamento());
    }

    /**
     * Metodo aplicado cuando el usuario que se esta creando no es un perito.
     */
    public void seleccionarCiudad() {
        List<DivipolaDTO> divipolas = new ArrayList<DivipolaDTO>(1);
        divipolas.add(this.ciudad);
        this.usuario.setDivipolas(divipolas);
    }
    /**
     * Metodo aplicado cuando el usuario que ese esta cargando no es un perito.
     * */
    public void establecerCiudad() {
    	this.ciudad = this.usuario.getDivipolas() !=null ?  
    			!this.usuario.getDivipolas().isEmpty() ? this.usuario.getDivipolas().get(0): null: null;
    	this.usuario.setDepartamento(this.ciudad == null ? "" : this.ciudad.getDepartamento());
    }

    /**
     * Guardar nuevo usuario
     */
    public void crearUsuario() {
        usuarioController.crearUsuario(usuario);
        // enviar correo con el usuario y contraseña creados
        usuarioController.notificarUsuarioCreado(usuario);
        String url = FacesContext.getCurrentInstance().getExternalContext()
                .encodeActionURL(
                        FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/pages/configuracion/usuarios/usuarioCreado.xhtml");
        try {
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect(url);
        } catch (IOException e) {
            // TODO Reireccionar a página no encontrada
            e.printStackTrace();
        }
    }

    /**
     * Guarda el usuario dependiendo del {@link #modoCrear} si es un nuevo
     * usuario llama al controlador {@link ListadoUsuariosController} para crear
     * el nuevo usuario. Si el usuario ya existe y el bean esta en modo de
     * edicion se actualizan los datos del usuario.
     */
    public void guardar() {
        /**
         * Antes de actualizar o crear es necesario actualizar las listas de los
         * elementos pickList y autoComplete
         */
        usuario.setEntidades(entidades.getTarget());
        usuario.setRegionales(regionales.getTarget());
        usuario.setDivipolas(this.getDivipolasSeleccionadas());
        if (this.usuario.getDivipolas() != null
                && this.usuario.getDivipolas().isEmpty()) {
            this.seleccionarCiudad();
        }
        usuario.setTipoAvaluos(tiposAvaluo.getTarget());
        usuario.setComplementoVia(convertirAString(complementoVia));
        usuario.setComplementoViaGeneradora(
                convertirAString(complementoViaGeneradora));

        /** Si el usuario tiene coordinador */
        if (((usuario.getRol().getPermisos().contains(permisoAbogado))
                || (usuario.getRol().getPermisos()
                        .contains(permisoCrearSolicitudes)
                        && !usuario.getRol().getPermisos()
                                .contains(permisoEditarSolicitudes)))
                && !usuario.getRol().getPermisos()
                        .contains(permisoCrearUsuarios)) {
            if (coordinadorAsociado != null) {
                usuario.setNumeroDocumentoCoordinador(
                        coordinadorAsociado.getNumeroDocumento());
                usuario.setTipoDocumentoCoordinador(
                        coordinadorAsociado.getTipoDocumentoIdentificacion());
            }

        }

        if (modoCrear) {
            if (usuarioController.verificarNumeroTipoDocumentoExistente(
                    usuario.getTipoDocumentoIdentificacion(),
                    usuario.getNumeroDocumento())) {
                FacesContext.getCurrentInstance()
                        .addMessage("mensajes", new FacesMessage(
                                FacesMessage.SEVERITY_ERROR, "Error",
                                "Ya existe en la base de datos un usuario con este tipo y número de documento"));
            } else {
                crearUsuario();
            }
        } else {
            usuarioController.actualizarUsuario(tipoDocumentoIdentificacion, numeroDocumento, usuario);
            String uri = redireccionarListado();
            try {
                FacesContext.getCurrentInstance().getExternalContext()
                        .redirect(uri);
            } catch (IOException e) {
                // TODO Reireccionar a página no encontrada
                e.printStackTrace();
            }
        }
    }

    public void reestablecerContrasena() {
        usuarioController.actualizarContrasena(
                usuarioController.asignarContrasena(usuario));
        FacesContext.getCurrentInstance()
                .addMessage("mensajes", new FacesMessage(
                        FacesMessage.SEVERITY_INFO, "Contraseña Reestablecida",
                        "Se ha enviado la notificación a este usuario"));
    }

    public String redireccionarListado() {
        return FacesContext.getCurrentInstance().getExternalContext()
                .encodeActionURL(
                        FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/pages/configuracion/usuarios/listadoUsuarios.xhtml");
    }
    
    public boolean entidadRequerida() {
    	return this.entidades != null && this.entidades.getTarget().isEmpty();
    }
    
    public boolean regionalRequerida() {
    	return this.regionales != null && this.regionales.getTarget().isEmpty();
    }
    
    public boolean tipoAvaluoRequerido() {
    	return this.tiposAvaluo != null && this.tiposAvaluo.getTarget().isEmpty();
    }
  
    /** Getters y setters */
    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public SortedMap<String, String> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(SortedMap<String, String> departamentos) {
        this.departamentos = departamentos;
    }

    public List<DivipolaDTO> getCiudades() {
        return ciudades;
    }

    public void setCiudades(List<DivipolaDTO> ciudades) {
        this.ciudades = ciudades;
    }

    public List<RolDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RolDTO> roles) {
        this.roles = roles;
    }

    public DualListModel<EntidadDTO> getEntidades() {
        return entidades;
    }

    public void setEntidades(DualListModel<EntidadDTO> entidades) {
        this.entidades = entidades;
    }

    public List<EntidadDTO> getTodasEntidades() {
        return todasEntidades;
    }

    public void setTodasEntidades(List<EntidadDTO> todasEntidades) {
        this.todasEntidades = todasEntidades;
    }

    public DualListModel<RegionalDTO> getRegionales() {
        return regionales;
    }

    public void setRegionales(DualListModel<RegionalDTO> regionales) {
        this.regionales = regionales;
    }

    public DualListModel<TipoAvaluoDTO> getTiposAvaluo() {
        return tiposAvaluo;
    }

    public void setTiposAvaluo(DualListModel<TipoAvaluoDTO> tiposAvaluo) {
        this.tiposAvaluo = tiposAvaluo;
    }

    public List<UsuarioDTO> getUsuariosCoordinadores() {
        return usuariosCoordinadores;
    }

    public void setUsuariosCoordinadores(
            List<UsuarioDTO> usuariosCoordinadores) {
        this.usuariosCoordinadores = usuariosCoordinadores;
    }

    public UsuarioDTO getCoordinadorAsociado() {
        return coordinadorAsociado;
    }

    public void setCoordinadorAsociado(UsuarioDTO coordinadorAsociado) {
        this.coordinadorAsociado = coordinadorAsociado;
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

    public void setComplementoViaGeneradora(
            List<String> complementoViaGeneradora) {
        this.complementoViaGeneradora = complementoViaGeneradora;
    }

    public boolean isModoCrear() {
        return modoCrear;
    }

    public void setModoCrear(boolean modoCrear) {
        this.modoCrear = modoCrear;
    }

    public boolean getOcultarBotonGuardar() {
        return ocultarBotonGuardar;
    }

    public void setOcultarBotonGuardar(boolean ocultarBotonGuardar) {
        this.ocultarBotonGuardar = ocultarBotonGuardar;
    }

    public String[] getLetrasComplemento() {
        return letrasComplemento;
    }

    public PermisoDTO getPermisoAceptarCasos() {
        return permisoAceptarCasos;
    }

    public void setPermisoAceptarCasos(PermisoDTO permisoAceptarCasos) {
        this.permisoAceptarCasos = permisoAceptarCasos;
    }

    public PermisoDTO getPermisoAbogado() {
        return permisoAbogado;
    }

    public void setPermisoAbogado(PermisoDTO permisoAbogado) {
        this.permisoAbogado = permisoAbogado;
    }

    public PermisoDTO getPermisoCrearSolicitudes() {
        return permisoCrearSolicitudes;
    }

    public void setPermisoCrearSolicitudes(PermisoDTO permisoCrearSolicitudes) {
        this.permisoCrearSolicitudes = permisoCrearSolicitudes;
    }

    public PermisoDTO getPermisoEditarSolicitudes() {
        return permisoEditarSolicitudes;
    }

    public void setPermisoEditarSolicitudes(
            PermisoDTO permisoEditarSolicitudes) {
        this.permisoEditarSolicitudes = permisoEditarSolicitudes;
    }

    public PermisoDTO getPermisoCrearUsuarios() {
        return permisoCrearUsuarios;
    }

    public void setPermisoCrearUsuarios(PermisoDTO permisoCrearUsuarios) {
        this.permisoCrearUsuarios = permisoCrearUsuarios;
    }

    public String[] getPuntosCardinales() {
        return puntosCardinales;
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

    private List<String> convertirALista(String string) {
        if (string.isEmpty()) {
            return new ArrayList<String>();
        }
        return Arrays.asList(string.split(" "));
    }

    private String convertirAString(List<String> list) {
        String cadena = "";
        if (list != null && !list.isEmpty()) {
            cadena = list.toString().replace("[", "").replace("]", "")
                    .replace(",", "");
        }
        return cadena;
    }

    public List<DivipolaDTO> getDivipolasUsuarios() {
        return divipolasUsuarios;
    }

    public void setDivipolasUsuarios(List<DivipolaDTO> divipolasUsuarios) {
        this.divipolasUsuarios = divipolasUsuarios;
    }

    public List<DivipolaDTO> getDivipolasSeleccionadas() {
        return divipolasSeleccionadas;
    }

    public void setDivipolasSeleccionadas(
            List<DivipolaDTO> divipolasSeleccionadas) {
        this.divipolasSeleccionadas = divipolasSeleccionadas;
    }

    public DivipolaDTO getCiudad() {
        return ciudad;
    }

    public void setCiudad(DivipolaDTO ciudad) {
        this.ciudad = ciudad;
    }

	public List<RegionalDTO> getTodasRegionales() {
		return todasRegionales;
	}

	public void setTodasRegionales(List<RegionalDTO> todasRegionales) {
		this.todasRegionales = todasRegionales;
	}

	public List<TipoAvaluoDTO> getTodosTiposAvaluos() {
		return todosTiposAvaluos;
	}

	public void setTodosTiposAvaluos(List<TipoAvaluoDTO> todosTiposAvaluos) {
		this.todosTiposAvaluos = todosTiposAvaluos;
	}

}
