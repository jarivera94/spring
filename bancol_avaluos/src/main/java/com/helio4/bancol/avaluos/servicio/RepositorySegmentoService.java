package com.helio4.bancol.avaluos.servicio;

import java.util.ArrayList;
import java.util.List;

import com.helio4.bancol.avaluos.servicio.datos.SegmentoService;
import com.helio4.bancol.avaluos.servicio.excepciones.EntidadNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.SegmentoNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.SegmentoDTO;
import com.helio4.bancol.avaluos.ensamblador.SegmentoEnsamblador;
import com.helio4.bancol.avaluos.modelo.Segmento;
import com.helio4.bancol.avaluos.persistencia.SegmentoRepository;

@Component(value = "repositorySegmentoService")
@Transactional(readOnly = true)
public class RepositorySegmentoService implements SegmentoService {

	private static Logger log = LoggerFactory.getLogger(RepositorySegmentoService.class);

	@Autowired
	private SegmentoRepository segmentoRepository;

	@Autowired
	private SegmentoEnsamblador segmentoEnsamblador;

	@Transactional(readOnly = true)
	@Override
	public List<SegmentoDTO> encontrarTodos() {
		log.debug("Encontrar todos los segmentos");
		List<SegmentoDTO> segmentoDTO = new ArrayList<SegmentoDTO>();
		for (Segmento segmento : segmentoRepository.findAll(sortById())) {
			segmentoDTO.add(segmentoEnsamblador.escribirDTO(segmento));
		}
		return segmentoDTO;
	}

	@Transactional(readOnly = true)
    @Override
	public List<SegmentoDTO> encontrarPorEntidad(Long idEntidad){
		return segmentoRepository.encontrarSegmentosPorEntidad(idEntidad);
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
	public SegmentoDTO encontrarPorId(Long id) {
		Segmento segmento = segmentoRepository.findOne(id);
		return segmentoEnsamblador.escribirDTO(segmento);
	}

	@Transactional(readOnly = true)
	@Override
	public List<SegmentoDTO> encontrarPorNombre(String nombre) {
		List<SegmentoDTO> segmentoDTOs = new ArrayList<SegmentoDTO>();
		for (Segmento segmento : segmentoRepository.encontrarPorNombre(nombre)) {
			segmentoDTOs.add(segmentoEnsamblador.escribirDTO(segmento));
		}
		return segmentoDTOs;
	}

	@Transactional(rollbackFor = EntidadNotFoundException.class)
	@Override
	public Boolean crearSegmento(SegmentoDTO segmentoCrear) {
		log.debug("Crear Segmento RepositorySegmentoService");
		Segmento segmento;
		try {
			segmento = segmentoEnsamblador.createSegmento(segmentoCrear);
		} catch (EntidadNotFoundException e) {
			log.debug(
                    "EntidadNotFoundException: no se la Entidad asociada a Segmento: {}",
                    segmentoCrear);
			return false;
		}
		segmentoRepository.save(segmento);
		return true;
	}

	@Transactional(rollbackFor = EntidadNotFoundException.class)
	@Override
	public Boolean actualizarTodo(SegmentoDTO segmentoActualizada) throws SegmentoNotFoundException {
		log.debug("Actualizar Segmento RepositorySegmentoService");
		try {
			segmentoEnsamblador.updateSegmento(segmentoActualizada);
		} catch (EntidadNotFoundException e) {
			log.debug(
                    "EntidadNotFoundException: no se la Entidad asociada a Segmento: {}",
                    segmentoActualizada);
			return false;
		}
		return true;
	}

	@Transactional(rollbackFor = SegmentoNotFoundException.class)
	@Override
	public SegmentoDTO eliminarSegmento(SegmentoDTO segmentoEliminar) throws SegmentoNotFoundException {
		log.debug("Eliminar Sucursal RepositorySucursalService");
		Segmento segmento;
		try {
			segmento = segmentoRepository.findOne(segmentoEliminar.getId());
			if (segmento == null) {
				throw new SegmentoNotFoundException();
			}
		} catch (SegmentoNotFoundException e) {
			log.error("SegmentoNotFoundException: no se creo el segmento {}",
                    segmentoEliminar);
			return null;
		}
		segmentoRepository.delete(segmento);

		return segmentoEliminar;
	}
}
