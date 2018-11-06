package com.helio4.bancol.avaluos.controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;

import com.helio4.bancol.avaluos.dto.UsuarioDTO;

@Controller
@Scope("view")
@Qualifier("adminBean")
public class AdminBean {

    private static Logger log = LoggerFactory.getLogger(AdminBean.class);

    @Autowired
    private ListadoAvaluosBean listadoAvaluosBean;

    @Autowired
    private SessionRegistry sessionRegistry;

    List<String> usuariosLogueados;

    @PostConstruct
    public void init() {
        try {
            log.debug("Inicializando AdminBean: ");
            actualizarInformacionUsuarios();
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }

    private void actualizarInformacionUsuarios() {
        List<Object> principals = sessionRegistry.getAllPrincipals();

        usuariosLogueados = new ArrayList<>();

        for (Object principal : principals) {
            if (principal instanceof UsuarioDTO) {
                usuariosLogueados.add(((UsuarioDTO) principal).getNombreUsuario());
            }
        }
        log.error("Usuarios logueados:  {}", usuariosLogueados.size());
    }

    public void cerrar() {
        redireccionarListado();
    }

    private void redireccionarListado() {
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

    public List<String> getUsuariosLogueados() {
        return usuariosLogueados;
    }
}
