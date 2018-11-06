package com.helio4.bancol.avaluos.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.RolDTO;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;
import com.helio4.bancol.avaluos.servicio.datos.PermisoService;
import com.helio4.bancol.avaluos.servicio.datos.RolService;
import com.helio4.bancol.avaluos.servicio.datos.UsuarioService;

@Component
@Scope("view")
public class ListadoUsuariosController {

	public RolDTO rolDTO;

	@Autowired
	@Qualifier("repositoryRolService")
	private RolService rolService;
	
	@Autowired
	@Qualifier("repositoryPermisoService")
	private PermisoService permisoService;

	@Autowired
	@Qualifier("repositoryUsuarioService")
	private UsuarioService usuarioService;

	
	public List<RolDTO> rolesUsuario(UsuarioDTO usuario) {
		List<RolDTO> roles = new ArrayList<RolDTO>();
		usuarioService.encontrarEstadosActuales(usuario.getTipoDocumentoIdentificacion()
				, usuario.getNumeroDocumento()
				);
		if (usuario.tienePermisoCrearRol()) {
			roles.add(rolService.crear(rolDTO));
		}

		return roles;
	}
	

	public boolean tienePermisoCrearUsuario(UsuarioDTO usuarioDTO) {
		return usuarioDTO.getRol().getPermisos().contains(permisoService.encontrarPor("Crear Usuario"));
	}

	public List<UsuarioDTO> usuarios() {
		List<UsuarioDTO> usuarios = usuarioService.encontrarUsuarios();
        Collections.reverse(usuarios);
		return usuarios;
	}
	
	public List<UsuarioDTO> usuariosPorPermisos(String permiso) {
		List<UsuarioDTO> usuarios = new ArrayList<UsuarioDTO>();
		for (UsuarioDTO usuario :usuarioService.encontrarUsuariosPorPermiso(permiso)) {
			usuarios.add(usuario);
		}
		return usuarios;
	}
	
	public List<UsuarioDTO> usuariosPorCoordDeAbogados(Integer tipoDocCoordAsignado, Long numDocCoordAsignado) {
		List<UsuarioDTO> usuarios = new ArrayList<UsuarioDTO>();
		for (UsuarioDTO usuario :usuarioService.encontrarUsuariosPorCoordAbogados(tipoDocCoordAsignado, numDocCoordAsignado)) {
			usuarios.add(usuario);
		}
		return usuarios;
	}
}
