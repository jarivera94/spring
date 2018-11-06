package com.helio4.bancol.avaluos.servicio;

import java.util.List;

import com.helio4.bancol.avaluos.servicio.datos.RolService;
import com.helio4.bancol.avaluos.servicio.excepciones.PaginaInicioNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.PermisoNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.RolNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.PermisoDTO;
import com.helio4.bancol.avaluos.dto.RolDTO;
import com.helio4.bancol.avaluos.ensamblador.UsuarioEnsamblador;
import com.helio4.bancol.avaluos.modelo.Rol;
import com.helio4.bancol.avaluos.persistencia.RolRepository;

@Component(value="repositoryRolService")
@Transactional(readOnly = true)
public class RepositoryRolService implements RolService {

	private static Logger log = LoggerFactory.getLogger( RepositoryRolService.class );

	@Autowired
	private RolRepository rolRepository;

	@Autowired
	private UsuarioEnsamblador usuarioEnsamblador;

	@Transactional
	@Override
	public RolDTO crear(RolDTO rolDTO) {
		Rol rol = null;
		try {
			rol = usuarioEnsamblador.crearRol(rolDTO);
		} catch (PaginaInicioNotFoundException e) {
			log.debug("No se encontró la página de inicio {}",
                    rolDTO.getPaginaInicio());
			return null;
		} catch (PermisoNotFoundException e) {
            log.debug("No se encontró el permiso {}",
                    rolDTO);
		}
		rol = rolRepository.save(rol);
        rolDTO.setId(rol.getId());
		return rolDTO;
	}

	@Transactional(rollbackFor = RolNotFoundException.class)
	@Override
	public RolDTO eliminar(Long rolId) throws RolNotFoundException {
		Rol deleted = rolRepository.findOne(rolId);
		if (deleted == null){
			throw new RolNotFoundException();
		}
		rolRepository.delete(deleted);
		return usuarioEnsamblador.escribirDTO(deleted);
	}

	@Transactional(readOnly = true)
	@Override
	public List<RolDTO> encontrarTodos() {
		return usuarioEnsamblador.escribirListaRoles(rolRepository.findAll());
	}

	@Transactional(readOnly = true)
    @Override
    public List<RolDTO> encontrarRoles() {
        return rolRepository.encontrarRoles();
    }

	@Transactional(readOnly = true)
	@Override
	public RolDTO encontrarPorId(Long id) {
		return usuarioEnsamblador.escribirDTO(rolRepository.findOne(id));
	}

	@Transactional(rollbackFor = RolNotFoundException.class)
	@Override
	public RolDTO actualizar(RolDTO actualizado)
			throws RolNotFoundException, PaginaInicioNotFoundException, PermisoNotFoundException {
		usuarioEnsamblador.actualizarRol(actualizado.getId(), actualizado);
		return actualizado;
	}

	@Transactional(readOnly = true)
    @Override
    public RolDTO encontrarPorNombre(String value) {
        return usuarioEnsamblador.escribirDTO(rolRepository.encontrarPorNombre(value));
    }

	@Transactional(readOnly = true)
    @Override
    public RolDTO encontrarRolPorNombre(String value) {
        return rolRepository.encontrarRolPorNombre(value);
    }

	@Override
	public List<PermisoDTO> encontrarPermisosPorRol(Long idRol) {
		return rolRepository.encontrarPermisosPorRol(idRol);
	}

}
