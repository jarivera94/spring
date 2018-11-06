
package com.helio4.bancol.avaluos.controlador;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;

import com.helio4.bancol.avaluos.dominio.UsuarioController;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;

@Controller
@Scope("view")
@Qualifier("recuperarContrasenaBean")
public class RecuperarContrasenaBean implements Serializable {

	private static final long serialVersionUID = 5669113144073779692L;

	private String contrasenaAnterior;
	private String contrasenaNueva;
	private String contrasenaNueva2;

	private UsuarioDTO usuarioActivo;

	@Autowired
	private UsuarioController usuarioController;

	@PostConstruct
	public void init() {
		try {
			usuarioActivo = (UsuarioDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			contrasenaAnterior = "";
			contrasenaNueva = "";
			contrasenaNueva2 = "";
		} catch (Exception e) {

		}
	}

	public void reestablecer() {
		String contrasenaActual = usuarioController.buscarContrasena(usuarioActivo);
		Boolean contrasenaAnterioresIguales = BCrypt.checkpw(contrasenaAnterior, contrasenaActual);
		if (!contrasenaAnterioresIguales) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Error", "La contraseña anterior no coincide"));
		} else {
			if (!contrasenaNueva.equals(contrasenaNueva2)) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Error", "Las nuevas contraseñas no coinciden"));
			} else {
				usuarioActivo.setContrasenaNoEncriptada(contrasenaNueva);
				usuarioActivo
						.setContrasenaEncriptada(usuarioController.encriptarContrasena(usuarioActivo.getContrasenaNoEncriptada()));
				usuarioController.actualizarContrasena(usuarioActivo);
				String uri = FacesContext.getCurrentInstance().getExternalContext()
						.encodeActionURL(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()
								+ "/pages/recuperar/cambioContrasena.xhtml");
				try {
					FacesContext.getCurrentInstance().getExternalContext().redirect(uri);
				} catch (IOException e) {
					// TODO Reireccionar a página no encontrada
					e.printStackTrace();
				}
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

	// --------getter and setter----------------

	public String getContrasenaAnterior() {
		return contrasenaAnterior;
	}

	public void setContrasenaAnterior(String contrasenaAnterior) {
		this.contrasenaAnterior = contrasenaAnterior;
	}

	public String getContrasenaNueva() {
		return contrasenaNueva;
	}

	public void setContrasenaNueva(String contrasenaNueva) {
		this.contrasenaNueva = contrasenaNueva;
	}

	public String getContrasenaNueva2() {
		return contrasenaNueva2;
	}

	public void setContrasenaNueva2(String contrasenaNueva2) {
		this.contrasenaNueva2 = contrasenaNueva2;
	}

	public UsuarioDTO getUsuarioActivo() {
		return usuarioActivo;
	}

	public void setUsuarioActivo(UsuarioDTO usuarioActivo) {
		this.usuarioActivo = usuarioActivo;
	}

}
