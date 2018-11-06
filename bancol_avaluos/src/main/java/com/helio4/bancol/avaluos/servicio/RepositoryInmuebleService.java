package com.helio4.bancol.avaluos.servicio;

import java.util.List;

import com.helio4.bancol.avaluos.servicio.datos.InmuebleService;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.InmuebleNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.PropietarioNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.InmuebleDTO;
import com.helio4.bancol.avaluos.ensamblador.InmuebleEnsamblador;
import com.helio4.bancol.avaluos.modelo.Inmueble;
import com.helio4.bancol.avaluos.persistencia.InmuebleRepository;

@Component(value="repositoryInmuebleService")
@Transactional(readOnly = true)
public class RepositoryInmuebleService implements InmuebleService {

	private static Logger log = LoggerFactory.getLogger( RepositoryInmuebleService.class );

	@Autowired
	private InmuebleRepository inmuebleRepository;
	
	@Autowired
	private InmuebleEnsamblador inmuebleEnsamblador;

	@Transactional
	@Override
	public InmuebleDTO crear(InmuebleDTO inmuebleDTO) {
		log.debug("Creando un nuevo detalle de inmueble {}",
                inmuebleDTO);
		Inmueble inmueble;
		try {
			inmueble = inmuebleEnsamblador.crearInmueble(inmuebleDTO);
		} catch (AvaluoNotFoundException e) {
			log.debug(
                    "AvaluoNotFoundException: no se encontró el avaluo asociado al inmueble {}",
                    inmuebleDTO);
			return null;
		} catch (PropietarioNotFoundException e) {
			log.debug(
                    "PropietarioNotFoundException: no se encontró uno de los propetarios del inmueble {}",
                    inmuebleDTO);
			return null;
		}
		inmuebleRepository.save(inmueble);
		inmuebleDTO.setId(inmueble.getId());
		return inmuebleDTO;
	}

	@Transactional(rollbackFor = InmuebleNotFoundException.class)
	@Override
	public InmuebleDTO eliminar(Long inmuebleId) throws InmuebleNotFoundException {
		log.debug("Eliminando el inmueble con id: {}",
                inmuebleId);
		Inmueble inmueble = inmuebleRepository.findOne(inmuebleId);
		if (inmueble == null) {
			throw new InmuebleNotFoundException();
		}
		inmuebleRepository.delete(inmueble);
		return inmuebleEnsamblador.escribirDTO(inmueble);
	}

	@Transactional(readOnly = true)
	@Override
	public List<InmuebleDTO> encontrarTodos() {
		log.debug("Encontrando todas las inmuebles");
		return inmuebleEnsamblador.escribirListaDTO(inmuebleRepository.findAll());
	}

	@Transactional(readOnly = true)
	@Override
	public InmuebleDTO encontrarPorId(Long id) {
		return inmuebleEnsamblador.escribirDTO(inmuebleRepository.findOne(id));
	}

	@Transactional(rollbackFor = InmuebleNotFoundException.class)
	@Override
	public InmuebleDTO actualizar(InmuebleDTO actualizado)
			throws InmuebleNotFoundException {
		try {
			inmuebleEnsamblador.actualizarInmueble(actualizado.getId(), actualizado);
		} catch (AvaluoNotFoundException e) {
            log.debug("AvaluoNotFoundException: no se encontró el avaluo asociado al inmueble {}",
                    actualizado);
			return null;
		} catch (PropietarioNotFoundException e) {
            log.debug("PropietarioNotFoundException: no se encontró uno de los propetarios del inmueble {}",
                    actualizado);
			return null;
		}
		return actualizado;
	}

}
