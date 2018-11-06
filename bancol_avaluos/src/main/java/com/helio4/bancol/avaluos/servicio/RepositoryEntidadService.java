package com.helio4.bancol.avaluos.servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.helio4.bancol.avaluos.servicio.datos.EntidadService;
import com.helio4.bancol.avaluos.servicio.excepciones.EntidadNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.EntidadDTO;
import com.helio4.bancol.avaluos.dto.SegmentoDTO;
import com.helio4.bancol.avaluos.ensamblador.EntidadEnsamblador;
import com.helio4.bancol.avaluos.modelo.Avaluo;
import com.helio4.bancol.avaluos.modelo.Entidad;
import com.helio4.bancol.avaluos.modelo.Sucursal;
import com.helio4.bancol.avaluos.persistencia.AvaluoRepository;
import com.helio4.bancol.avaluos.persistencia.EntidadRepository;
import com.helio4.bancol.avaluos.persistencia.SegmentoRepository;
import com.helio4.bancol.avaluos.persistencia.SucursalRepository;

@Component(value="repositoryEntidadService")
@Transactional(readOnly = true)
public class RepositoryEntidadService implements EntidadService {
	
	private static Logger log = LoggerFactory.getLogger( RepositoryEntidadService.class );

	@Autowired
	private EntidadRepository entidadRepository;
	
	@Autowired
	private SucursalRepository sucursalRepository;
	
	@Autowired
	private AvaluoRepository avaluoRepository;
	
	@Autowired
	private SegmentoRepository segmentoRepository;
	
	@Autowired
	private EntidadEnsamblador entidadEnsamblador;
	

	@Transactional(readOnly=true)
	@Override
	public List<EntidadDTO> encontrarTodos() {
		log.debug("Consultado todas las entidades en la base de datos.");
		List<EntidadDTO> entidadDTOs = new ArrayList<EntidadDTO>();
		for (Entidad entidad:entidadRepository.findAll(sortById())) {
			entidadDTOs.add(entidadEnsamblador.escribirDTO(entidad));
		}
		return entidadDTOs;
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<EntidadDTO> encontrarTodosActivos() {
		log.debug("Consultado todas las entidades activas en la base de datos.");
		List<EntidadDTO> entidadDTOs = new ArrayList<EntidadDTO>();
		for (Entidad entidad:entidadRepository.encontrarTodoActivo()) {
			entidadDTOs.add(entidadEnsamblador.escribirDTO(entidad));
		}
		return entidadDTOs;
	}
	
	/**
	 * Ordenar por Id a la consulta de la lista todos.
	 * @return
	 */
	private Sort sortById() {
		return new Sort(Sort.Direction.ASC, "id");
	}

	@Transactional(readOnly=true)
	@Override
	public EntidadDTO encontrarPorId(Long id) {
		return entidadEnsamblador.escribirDTO(entidadRepository.findOne(id));
	}
	
	@Transactional(readOnly=true)
	@Override
	public EntidadDTO encontrarPorCodigoTinsa(Integer codigo) {
		return entidadRepository.encontrarEntidadPorCodigoTinsa(codigo);
	}

	@Override
	public EntidadDTO encontrarPorNombre(String nombre) {
		return entidadRepository.encontrarPorNombre(nombre);
	}

	@Transactional(readOnly = true)
    @Override
    public List<EntidadDTO> encontrarEntidadesPorUsuario(
            Integer tipoDocumentoIdentificacion, Long numeroDocumento) {
	    return entidadRepository.encontrarEntidadesPorUsuario(tipoDocumentoIdentificacion, numeroDocumento);
    }

    @Transactional(readOnly = true)
    @Override
    public List<EntidadDTO> encontrarEntidades() {
        return entidadRepository.encontrarEntidades();
    }

	@Transactional(rollbackFor = EntidadNotFoundException.class)
	@Override
	public Boolean crearEntidad(EntidadDTO entidadCrear) {
        log.debug("Creando una Entidad:  {}",
                entidadCrear);
		Entidad entidad;
		try {
			entidad = entidadEnsamblador.createEntidad(entidadCrear);
		} catch (EntidadNotFoundException e) {
            log.debug("EntidadNotFoundException: no se creo la entidad {}",
                    entidadCrear);
			return false;
		}
		entidadRepository.save(entidad);		
		
		return true;
	}
	
	@Transactional(rollbackFor = EntidadNotFoundException.class)
	@Override
	public Boolean actualizarTodo(EntidadDTO entidadActualizada) throws EntidadNotFoundException {
		log.debug("Actualizar Entidad RepositoryEntidadService");
		try {
			entidadEnsamblador.updateEntidad(entidadActualizada);
		} catch (EntidadNotFoundException e) {
            log.debug("EntidadNotFoundException: no se encontro la Entidad:  {}",
                    entidadActualizada);
			return false;
		}
		return true;		
	}

    @Transactional(readOnly = true)
    @Override
    public String cargarPrefijo(Long entidadId) {
        return entidadRepository.cargarPrefijo(entidadId);
    }

	@Transactional(rollbackFor = EntidadNotFoundException.class)
	@Override
	public EntidadDTO eliminarEntidad(EntidadDTO entidadEliminar) throws EntidadNotFoundException {
        log.debug("Eliminando el area con id:  {}",
                entidadEliminar.getId());
		Boolean eliminarTotal = false, eliminarAvaluo = false, eliminarSucursal = false, eliminarSegmento = false;		
		Set<Avaluo> listAvaluo = avaluoRepository.encontrarAvaluosPorEntidad(entidadEliminar.getId());
		if ((listAvaluo.isEmpty()) || (listAvaluo == null)) {
			// Si no existen Avaluos Elimina
			eliminarAvaluo = true;
		} else {
			// Si existen Avaluos
			String idsAvaluo = "";
			for (Avaluo avaluo : listAvaluo) {
				idsAvaluo += avaluo.getId().toString() + " - ";
			}
			entidadEliminar.setAvaluosRelacionados(idsAvaluo);
			eliminarAvaluo = false;
		}
		
		List<Sucursal> listSucursales = new ArrayList<Sucursal>();
		listSucursales = sucursalRepository.encontrarSucursalesPorEntidad(entidadEliminar.getId());
		if ((listSucursales.isEmpty()) || (listSucursales == null)) {
			// Si no existen Sucursal Elimina
			eliminarSucursal = true;
		} else {
			// Si existen Sucursal
			String idsSucursales = "";
			for (Sucursal sucursal : listSucursales) {
				idsSucursales += sucursal.getNombre() + " - ";
			}
			entidadEliminar.setSucursalesRelacionadas(idsSucursales);
			eliminarSucursal = false;
		}

		List<SegmentoDTO> listSegmento = segmentoRepository.encontrarSegmentosPorEntidad(entidadEliminar.getId());
		if ((listSegmento.isEmpty()) || (listSegmento == null)) {
			// Si no existen Segmentos Elimina
			eliminarSegmento = true;
		} else {
			// Si existen Segmentos
			String idsSegmentos = "";
			for (SegmentoDTO segmento : listSegmento) {
				idsSegmentos += segmento.getNombre() + " - ";
			}
			entidadEliminar.setSegmentosRelacionados(idsSegmentos);
			eliminarSegmento = false;
		} 
		
		// Verifica si existe alguna relacion con una de estas tablas 
		if (!eliminarAvaluo || !eliminarSucursal || !eliminarSegmento) {			
			eliminarTotal = false;
		} else {
			eliminarTotal = true;
		}

		if (eliminarTotal) {
			entidadRepository.delete(entidadRepository.findOne(entidadEliminar.getId()));
		}
		return entidadEliminar;
	}

	@Transactional(readOnly = true)
	@Override
	public List<EntidadDTO> encontrarEntidadesConCodigoBUA() {
		return entidadRepository.encontrarEntidadesConCodigoBUA();
	}
}
