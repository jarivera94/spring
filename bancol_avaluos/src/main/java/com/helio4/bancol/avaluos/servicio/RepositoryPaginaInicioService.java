package com.helio4.bancol.avaluos.servicio;

import java.util.List;

import com.helio4.bancol.avaluos.servicio.datos.PaginaInicioService;
import com.helio4.bancol.avaluos.servicio.excepciones.PaginaInicioNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.PaginaInicioDTO;
import com.helio4.bancol.avaluos.ensamblador.UsuarioEnsamblador;
import com.helio4.bancol.avaluos.modelo.PaginaInicio;
import com.helio4.bancol.avaluos.persistencia.PaginaInicioRepository;

@Component(value="repositoryPaginaInicioService")
@Transactional(readOnly = true)
public class RepositoryPaginaInicioService implements PaginaInicioService {
	
	@Autowired
	private PaginaInicioRepository paginaInicioRepository;
	
	@Autowired
	private UsuarioEnsamblador usuarioEnsamblador;

	@Transactional
	@Override
	public PaginaInicioDTO crear(PaginaInicioDTO paginaInicioDTO) {
		PaginaInicio paginaInicio = new PaginaInicio();
		paginaInicio.setNombre(paginaInicioDTO.getNombre());
		paginaInicio.setUrl(paginaInicioDTO.getUrl());
		paginaInicio = paginaInicioRepository.save(paginaInicio);
		paginaInicioDTO.setId(paginaInicio.getId());
		return paginaInicioDTO;
	}

	@Transactional(rollbackFor = PaginaInicioNotFoundException.class)
	@Override
	public PaginaInicioDTO eliminar(Long paginaInicioId) throws PaginaInicioNotFoundException {
		PaginaInicio deleted = paginaInicioRepository.findOne(paginaInicioId);
		if (deleted == null){
			throw new PaginaInicioNotFoundException();
		}
		paginaInicioRepository.delete(deleted);
		return usuarioEnsamblador.escribirDTO(deleted);
	}

	@Transactional(readOnly = true)
	@Override
	public List<PaginaInicioDTO> encontrarTodos() {
		return usuarioEnsamblador.escribirListaPaginaInicio(paginaInicioRepository.findAll());
	}

	@Transactional(readOnly = true)
	@Override
	public PaginaInicioDTO encontrarPorId(Long id) {
		return usuarioEnsamblador.escribirDTO(paginaInicioRepository.findOne(id));
	}

	@Transactional(rollbackFor = PaginaInicioNotFoundException.class)
	@Override
	public PaginaInicioDTO actualizar(PaginaInicioDTO actualizado)
			throws PaginaInicioNotFoundException {
		usuarioEnsamblador.actualizarPaginaInicio(actualizado.getId(), actualizado);
		return actualizado;
	}

	@Transactional(readOnly = true)
	@Override
	public PaginaInicioDTO encontrarPaginaInicioConRoles(Long paginaInicioId) {
		return usuarioEnsamblador.escribirDTO(paginaInicioRepository.encontrarPaginaInicioConRoles(paginaInicioId));
	}

}
