package com.helio4.bancol.avaluos.servicio;

import java.util.List;

import com.helio4.bancol.avaluos.servicio.datos.PermisoService;
import com.helio4.bancol.avaluos.servicio.excepciones.PermisoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.PermisoDTO;
import com.helio4.bancol.avaluos.ensamblador.UsuarioEnsamblador;
import com.helio4.bancol.avaluos.modelo.Permiso;
import com.helio4.bancol.avaluos.persistencia.PermisoRepository;

@Component(value="repositoryPermisoService")
@Transactional(readOnly = true)
public class RepositoryPermisoService implements PermisoService {
	
	@Autowired
	private PermisoRepository permisoRepository;
	
	@Autowired
	private UsuarioEnsamblador usuarioEnsamblador;

	@Transactional
	@Override
	public PermisoDTO crear(PermisoDTO permisoDTO) {
		Permiso permiso = new Permiso();
		permiso.setNombre(permisoDTO.getNombre());
		permiso = permisoRepository.save(permiso);
		permisoDTO.setId(permiso.getId());
		return permisoDTO;
	}

	@Transactional(rollbackFor = PermisoNotFoundException.class)
	@Override
	public PermisoDTO eliminar(Long permisoId) throws PermisoNotFoundException {
		Permiso deleted = permisoRepository.findOne(permisoId);
		if (deleted == null){
			throw new PermisoNotFoundException();
		}
		permisoRepository.delete(deleted);
		return usuarioEnsamblador.escribirDTO(deleted);
	}

	@Transactional(readOnly = true)
	@Override
	public List<PermisoDTO> encontrarTodos() {
		return usuarioEnsamblador.escribirListaPermisos(permisoRepository.findAll());
	}

	@Transactional(readOnly = true)
	@Override
	public PermisoDTO encontrarPorId(Long id) {
		return usuarioEnsamblador.escribirDTO(permisoRepository.findOne(id));
	}

	@Transactional(rollbackFor = PermisoNotFoundException.class)
	@Override
	public PermisoDTO actualizar(PermisoDTO actualizado)
			throws PermisoNotFoundException {
		Permiso permiso = permisoRepository.findOne(actualizado.getId());
		if (permiso == null){
			throw new PermisoNotFoundException();
		}
		permiso.update(permiso.getNombre());
		return actualizado;
	}

	@Transactional(readOnly = true)
	@Override
	public PermisoDTO encontrarPermisoConRoles(Long permisoId) {
		return usuarioEnsamblador.escribirDTO(permisoRepository.encontrarPermisoConRoles(permisoId));
	}

	@Transactional(readOnly = true)
	@Override
	public PermisoDTO encontrarPor(String nombre) {
		return permisoRepository.encontrarPor(nombre);
	}

	@Transactional(readOnly = true)
    @Override
    public List<PermisoDTO> encontrarPorRol(String nombreRol) {
        return permisoRepository.encontrarPorRol(nombreRol);
    }

}
