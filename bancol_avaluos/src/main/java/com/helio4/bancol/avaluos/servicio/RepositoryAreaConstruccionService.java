package com.helio4.bancol.avaluos.servicio;

import java.util.ArrayList;
import java.util.List;

import com.helio4.bancol.avaluos.servicio.datos.AreaConstruccionService;
import com.helio4.bancol.avaluos.servicio.excepciones.AreaConstruccionNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.AreaConstruccionDTO;
import com.helio4.bancol.avaluos.ensamblador.AreaEnsamblador;
import com.helio4.bancol.avaluos.modelo.AreaConstruccion;
import com.helio4.bancol.avaluos.persistencia.AreaConstruccionRepository;

@Component(value = "repositoryAreaConstruccionService")
@Transactional(readOnly = true)
public class RepositoryAreaConstruccionService implements
		AreaConstruccionService {

	private static Logger log = LoggerFactory.getLogger( RepositoryAreaConstruccionService.class );

	@Autowired
	private AreaConstruccionRepository areaConstruccionRepository;
	
	@Autowired
	private AreaEnsamblador areaEnsamblador;

	@Transactional
	@Override
	public AreaConstruccionDTO crear(AreaConstruccionDTO areaConstruccionDTO) {
        log.debug("Creando un nuevo detalle de areaConstruccion {}",
                areaConstruccionDTO);
		AreaConstruccion areaConstruccion;
		try {
			areaConstruccion = areaEnsamblador.crearAreaConstruccion(areaConstruccionDTO);
		} catch (AvaluoNotFoundException e) {
            log.debug("AvaluoNotFoundException: no se encontró el avaluo asociado al areaConstruccion {}",
                    areaConstruccionDTO);
			return null;
		}
		areaConstruccionRepository.save(areaConstruccion);
		areaConstruccionDTO.setId(areaConstruccion.getId());
		return areaConstruccionDTO;
	}

	@Transactional(rollbackFor = AreaConstruccionNotFoundException.class)
	@Override
	public AreaConstruccionDTO eliminar(Long areaConstruccionId) throws AreaConstruccionNotFoundException {
        log.debug("Eliminando el areaConstruccion con id:  {}",
                areaConstruccionId);
		AreaConstruccion areaConstruccion = areaConstruccionRepository.findOne(areaConstruccionId);
		if (areaConstruccion == null) {
			throw new AreaConstruccionNotFoundException();
		}
		areaConstruccionRepository.delete(areaConstruccion);
		return areaEnsamblador.escribirDTO(areaConstruccion);
	}

	@Transactional(readOnly = true)
	@Override
	public List<AreaConstruccionDTO> encontrarTodos() {
		log.debug("Encontrando todas las areaConstruccions");
		List<AreaConstruccionDTO> resultado = new ArrayList<AreaConstruccionDTO>();
		for (AreaConstruccion areaConstruccion:areaConstruccionRepository.findAll()) {
			resultado.add(areaEnsamblador.escribirDTO(areaConstruccion));
		}
		return resultado;
	}

	@Transactional(readOnly = true)
	@Override
	public AreaConstruccionDTO encontrarPorId(Long id) {
		return areaEnsamblador.escribirDTO(areaConstruccionRepository.findOne(id));
	}

	@Transactional(rollbackFor = AreaConstruccionNotFoundException.class)
	@Override
	public AreaConstruccionDTO actualizar(AreaConstruccionDTO actualizado)
			throws AreaConstruccionNotFoundException {
		try {
			areaEnsamblador.actualizarAreaConstruccion(actualizado.getId(), actualizado);
		} catch (AvaluoNotFoundException e) {
            log.debug("AvaluoNotFoundException: no se encontró el avaluo asociado al areaConstruccion {}",
                    actualizado);
			return null;
		}
		return actualizado;
	}

}
