package com.helio4.bancol.avaluos.controlador;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import com.helio4.bancol.avaluos.dominio.ListadoUsuariosController;
import com.helio4.bancol.avaluos.dominio.UsuarioController;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;

/**
 * Bean controlador de la página del listado de los usuarios
 * del aplicativo GIA.
 *
 * Este listado muestra los usuarios que puede administrar
 * el {@link usuarioActivo} según los {@link com.helio4.bancol.avaluos.modelo.Rol#permisos}
 * definidos:
 * <ul>
 *     <li> Administrador: puede ver todos los usuarios del sistema y modificarlos.
 *     <li> Coordinador de abogados: puede ver todos los abogados que tiene a su
 *     cargo y modificarlos.
 * </ul>
 *
 * La página del listado de usuarios tiene un botón que permite crear
 * nuevos usuarios, este botón redirecciona a la página de creación de usuarios.
 *
 * Tambien existe los botones de editar usuario que redirecciona a la página de
 * creación de usuarios en modo de edición. Y el botón de borrar usuario que
 * elimina el usuario seleccionado de aplicativo.
 */
@Controller
@Scope("view")
@Qualifier("listadoUsuariosBean")
public class ListadoUsuariosBean implements Serializable {

    private static Logger log = LoggerFactory
        .getLogger(ListadoUsuariosBean.class);
    private static final long serialVersionUID = 1L;
    /** Listado de usuarios mostrado */
    private List<UsuarioDTO> usuarios;
    /** Usuario seleccionado de la lista */
    private UsuarioDTO usuarioSeleccionado;
    /** {@link usuarioActivo} en el sistema */
    private UsuarioDTO usuarioActivo;
    /** Indica si el {@link usuarioActivo} es un coordinador de abogados */
    private boolean usuarioCoordinador;

    @Autowired
    private UsuarioController usuarioController;

    @Autowired
    private ListadoUsuariosController listadoUsuariosController;

    @Autowired
    private ListadoAvaluosBean listadoAvaluosController;

    @PostConstruct
    public void init() {
        log.debug("Iniciando listado de usuarios");
        usuarioActivo = (UsuarioDTO) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        usuarioCoordinador = listadoAvaluosController.isCrearUsuario()
            && !listadoAvaluosController.isCrearRol();
        cargarListaUsuarios();
    }

    public void actualizarListado(ComponentSystemEvent event) {
        /*if(userNameCreado != null){
            generarListaUsuarios();
            FacesContext.getCurrentInstance().addMessage(
                    "msgs",
                    new FacesMessage("Usuario creada",
                        "El usuario con alias: " + userNameCreado
                        + " fue creado exitosamente"));
            userNameCreado = null;
        }
        if(userNameModificado != null){
            generarListaUsuarios();
            FacesContext.getCurrentInstance().addMessage(
                    "msgs",
                    new FacesMessage("Usuario Modificado",
                        "El usuario con alias: " + userNameModificado
                        + " fue modificado exitosamente"));
            userNameModificado = null;
        }
        */
    }

    /**
     * Carga los usuarios de acuerdo al permiso del usuario
     * desde la base de datos.
     */
    public void cargarListaUsuarios(){
        if(usuarioCoordinador){
            usuarios = listadoUsuariosController
                .usuariosPorCoordDeAbogados(
                        usuarioActivo.getTipoDocumentoIdentificacion(),
                        usuarioActivo.getNumeroDocumento());
        }else{
            usuarios = listadoUsuariosController.usuarios();
        }
    }

    /**
     * Redirecciona a la pagina de creacion de
     * usuarios si hay un usuario seleccionado se envia el
     * parametro por medio de la URL.
     */
    public void crearUsuario(){
        String url = FacesContext.getCurrentInstance()
                .getExternalContext()
                .encodeActionURL(
                        FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/pages/configuracion/usuarios/usuario.xhtml");
        try {
            FacesContext.getCurrentInstance().getExternalContext()
                .redirect(url);
        } catch (IOException e) {
            // TODO Reireccionar a página no encontrada
            e.printStackTrace();
        }
    }

    public void modificarUsuario(){
        String url = FacesContext.getCurrentInstance()
                .getExternalContext()
                .encodeActionURL(
                        FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/pages/configuracion/usuarios/usuario.xhtml?tipoDocumento="
                        + usuarioSeleccionado.getTipoDocumentoIdentificacion() + "&numeroDocumento="
                        + usuarioSeleccionado.getNumeroDocumento());
        try {
            FacesContext.getCurrentInstance().getExternalContext()
                .redirect(url);
        } catch (IOException e) {
            // TODO Reireccionar a página no encontrada
            e.printStackTrace();
        }
    }

    public void eliminarUsuario(){
        if(usuarioSeleccionado != null){
            UsuarioDTO usuarioEliminado = usuarioController.eliminarUsuario(usuarioSeleccionado);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario Eliminado", "El usuario " + usuarioEliminado.getNombres()
                    + " " + usuarioEliminado.getApellidos() + " fue eliminado del sistema.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            cargarListaUsuarios();
        }
    }

    public List<UsuarioDTO> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UsuarioDTO> usuarios) {
        this.usuarios = usuarios;
    }

    public UsuarioDTO getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(UsuarioDTO usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }

}
