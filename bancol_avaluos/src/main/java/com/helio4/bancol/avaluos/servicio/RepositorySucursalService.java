package com.helio4.bancol.avaluos.servicio;

import java.util.ArrayList;
import java.util.List;

import com.helio4.bancol.avaluos.servicio.datos.SucursalService;
import com.helio4.bancol.avaluos.servicio.excepciones.EntidadNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.SucursalNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.SucursalDTO;
import com.helio4.bancol.avaluos.ensamblador.AvaluoEnsamblador;
import com.helio4.bancol.avaluos.ensamblador.SucursalEnsamblador;
import com.helio4.bancol.avaluos.modelo.Sucursal;
import com.helio4.bancol.avaluos.persistencia.SucursalRepository;

@Component(value = "repositorySucursalService")
@Transactional(readOnly = true)
public class RepositorySucursalService implements SucursalService {

	private static Logger log = LoggerFactory
			.getLogger(RepositorySucursalService.class);

	@Autowired
	private SucursalRepository sucursalRepository;

	@Autowired
	private SucursalEnsamblador sucursalEnsamblador;

	@Autowired
	private AvaluoEnsamblador avaluoEnsamblador;

	@Transactional(readOnly = true)
	@Override
	public List<SucursalDTO> encontrarTodos() {
		log.debug("Encontrar todos las sucursales");
		List<SucursalDTO> sucursalesDTO = new ArrayList<SucursalDTO>();
		for (Sucursal sucursal : sucursalRepository.findAll(sortById())) {
			sucursalesDTO.add(avaluoEnsamblador.escribirDTO(sucursal));
		}
		return sucursalesDTO;
	}
	
	/**
	 * Ordenar por Id a la consulta de la lista todos.
	 * @return
	 */
	private Sort sortById() {
		return new Sort(Sort.Direction.ASC, "id");
	}

	@Transactional(readOnly = true)
	@Override
	public SucursalDTO encontrarPorId(Long id) {
		Sucursal sucursal = sucursalRepository.findOne(id);
		return avaluoEnsamblador.escribirDTO(sucursal);
	}

	@Transactional(readOnly = true)
	@Override
	public List<SucursalDTO> encontrarPorCodigo(String codigo, Long idEntidad) {
		List<SucursalDTO> sucursalesDTO = new ArrayList<SucursalDTO>();
		for (Sucursal sucursal : sucursalRepository.encontrarPorCodigo(codigo, idEntidad)) {
			sucursalesDTO.add(avaluoEnsamblador.escribirDTO(sucursal));
		}
		return sucursalesDTO;
	}

	@Transactional(readOnly = true)
	@Override
	public List<SucursalDTO> encontrarPorNombre(String nombre, Long idEntidad) {
		List<SucursalDTO> sucursalesDTO = new ArrayList<SucursalDTO>();
		for (Sucursal sucursal : sucursalRepository.encontrarPorNombre(nombre, idEntidad)) {
			sucursalesDTO.add(avaluoEnsamblador.escribirDTO(sucursal));
		}
		return sucursalesDTO;
	}

	@Transactional(rollbackFor = EntidadNotFoundException.class)
	@Override
	public Boolean crearSucursal(SucursalDTO sucursalCrear) {
		log.debug("Crear Sucursal RepositorySucursalService");
		Sucursal sucursal;
		try {
			sucursal = sucursalEnsamblador.createSucursal(sucursalCrear);				
		} catch (EntidadNotFoundException e) {
            log.error(
                    "EntidadNotFoundException: no se encontro Entidad relacionada a la Sucursal:  {}",
                    sucursalCrear, e);
			return false;
		}		
		sucursalRepository.save(sucursal);
		return true;
	}
	
	@Transactional(rollbackFor = SucursalNotFoundException.class)
	@Override
	public Boolean actualizarTodo(SucursalDTO sucursalActualizada) throws SucursalNotFoundException {
		log.debug("Actualizar Sucursal RepositorySucursalService");
		try {
			sucursalEnsamblador.updateSucursal(sucursalActualizada);
		} catch (EntidadNotFoundException e) {
            log.error("EntidadNotFoundException: no se encontro Entidad relacionada a la Sucursal:  {}",
                    sucursalActualizada, e);
			return false;
		}
		return true;
	}

	@Transactional(rollbackFor = SucursalNotFoundException.class)
	@Override
	public SucursalDTO eliminarSucursal(SucursalDTO sucursalEliminar) throws SucursalNotFoundException {		
		log.debug("Eliminar Sucursal RepositorySucursalService");
		Sucursal sucursal;
		try {
			sucursal = sucursalRepository.findOne(sucursalEliminar.getId());
			if (sucursal == null) {
				throw new SucursalNotFoundException();
			}
		} catch (SucursalNotFoundException e) {
            log.error("EntidadNotFoundException: no se creo la entidad {}",
                    sucursalEliminar, e);
			return null;
		}
		sucursalRepository.delete(sucursal);
		
		return sucursalEliminar;
	}

	@Override
	@Transactional(readOnly = true)
	public SucursalDTO encontrarSucursal(Long idEntidad, String codigo) {
		return avaluoEnsamblador.escribirDTO(sucursalRepository.encontrarSucursal(idEntidad, codigo));
	}
}
