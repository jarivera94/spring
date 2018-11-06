package com.helio4.bancol.avaluos.controlador;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dominio.ParametrosController;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;
import com.helio4.bancol.avaluos.servicio.datos.UsuarioService;
import com.helio4.bancol.avaluos.servicio.excepciones.UsuarioNotFoundException;

@Component("authenticationProvider")
public class AvaluosAuthenticationProvider implements AuthenticationProvider {

	private static Logger log = LoggerFactory.getLogger(AvaluosAuthenticationProvider.class);

	@Autowired
	@Qualifier("repositoryUsuarioService")
	private UsuarioService usuarioService;

	@Autowired
	private ParametrosController parametrosController;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private SessionRegistry sessionRegistry;

	public void register(UsuarioDTO usuarioDTO) throws UsuarioNotFoundException {
		// TODO verificar datos
		UsuarioDTO usuarioExistente = usuarioService.encontrarPorNombreUsuario(usuarioDTO.getNombreUsuario());
		if (usuarioExistente != null) {
			usuarioService.crear(usuarioDTO);
		} else {
			throw new UsuarioNotFoundException();
		}
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UsernamePasswordAuthenticationToken passwordAuthenticationToken = (UsernamePasswordAuthenticationToken) authentication;
		String username = String.valueOf(passwordAuthenticationToken.getPrincipal());
		String password = String.valueOf(passwordAuthenticationToken.getCredentials());

		log.debug("Autenticando con usuario:  {}", username);

		// load the user with permissions
		UsuarioDTO usuarioDTO = usuarioService.encontrarPorNombreUsuario(username);

		// valida por numero de identificacion
		if (usuarioDTO == null && StringUtils.isNumeric(username)) {
			usuarioDTO = usuarioService.buscarPorNumeroDocumento(Long.parseLong(username));
		}

		if (usuarioDTO != null) {
			if (!usuarioDTO.getUsuarioVigente()) {
				log.info("Lanzando excepción usuario {} no vigente en GIA", username);
				throw new LockedException("El usuario ingresado no esta vigente en GIA");
			}

			if (!passwordEncoder.matches(password, usuarioDTO.getContrasenaEncriptada())) {
				log.info("Lanzando excepción usuario o contraseña invalidos:  {}", username);
				throw new BadCredentialsException("El usuario o la contraseña son incorrectas");

			}

		} else {
			log.debug("Lanzando excepción usuario o contraseña invalidos:  {}", username);

			throw new BadCredentialsException("El usuario o la contraseña son incorrectas");
		}

		// mostrar el numero de usuarios conectados antes de loggeo
		mostrarUsuariosConectados();

		usuarioDTO.setContrasenaEncriptada("");

		Collection<? extends GrantedAuthority> authorities = usuarioDTO.getRol().getPermisos();

		return new UsernamePasswordAuthenticationToken(usuarioDTO, null, authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	private void mostrarUsuariosConectados() {

		List<Object> principals = sessionRegistry.getAllPrincipals();

		List<String> usuariosLogueados = new ArrayList<>();

		for (Object principal : principals) {
			if (principal instanceof UsuarioDTO) {
				usuariosLogueados.add(((UsuarioDTO) principal).getNombreUsuario());
			}
		}
		log.error("Pre autenticacion Usuarios logueados:  {}", usuariosLogueados.size());

	}

}
