package com.helio4.bancol.avaluos.dominio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.PaginaInicioDTO;
import com.helio4.bancol.avaluos.dto.PermisoDTO;
import com.helio4.bancol.avaluos.dto.RolDTO;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;
import com.helio4.bancol.avaluos.servicio.datos.PaginaInicioService;
import com.helio4.bancol.avaluos.servicio.excepciones.RolNotFoundException;
import com.helio4.bancol.avaluos.servicio.util.Constantes;
import com.helio4.bancol.avaluos.servicio.datos.RolService;
import com.helio4.bancol.avaluos.servicio.datos.UsuarioService;
import com.helio4.bancol.avaluos.servicio.datos.PermisoService;
import java.io.Serializable;

@Component
@Scope("session")
public class ListadoRolesController  implements Serializable {

	private static final long serialVersionUID = 1L;

	public RolDTO rolDTO;

	@Autowired
	@Qualifier("repositoryRolService")
	private RolService rolService;

	@Autowired
	@Qualifier("repositoryUsuarioService")
	private UsuarioService usuarioService;

	@Autowired
	@Qualifier("repositoryPermisoService")
	private PermisoService permisoService;
	
	@Autowired
	@Qualifier("repositoryPaginaInicioService")
	private PaginaInicioService paginaInicioService;

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

	public boolean tienePermisoCrearRol(UsuarioDTO usuarioDTO) {
		return usuarioDTO.getRol().getPermisos().contains(permisoService.encontrarPor(Constantes.PERMISO_CREAR_ROL));
	}

	public List<PermisoDTO> permisos() {
		List<PermisoDTO> permisos = new ArrayList<PermisoDTO>();
		for (PermisoDTO permiso : permisoService.encontrarTodos()) {
			permisos.add(permiso);
		}
		return permisos;
	}
	
	public List<RolDTO> roles() {
		List<RolDTO> roles = new ArrayList<RolDTO>();
		for (RolDTO rol : rolService.encontrarTodos()) {
			roles.add(rol);
		}
		return roles;
	}
	
	
	public List<PaginaInicioDTO> paginas(){
		List<PaginaInicioDTO> paginas = new ArrayList<PaginaInicioDTO>();
		for (PaginaInicioDTO pagina : paginaInicioService.encontrarTodos()){
			paginas.add(pagina);
		}
		return paginas;
		
	}
	
	public void eliminarRol(RolDTO rolDTO) throws RolNotFoundException{
		rolService.eliminar(rolDTO.getId()); 
	}

}
