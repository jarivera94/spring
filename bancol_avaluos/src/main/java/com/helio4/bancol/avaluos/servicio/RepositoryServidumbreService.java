package com.helio4.bancol.avaluos.servicio;

import java.util.List;

import com.helio4.bancol.avaluos.servicio.datos.ServidumbreService;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.ServidumbreNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.ServidumbreDTO;
import com.helio4.bancol.avaluos.ensamblador.ServidumbreEnsamblador;
import com.helio4.bancol.avaluos.modelo.Servidumbre;
import com.helio4.bancol.avaluos.persistencia.ServidumbreRepository;

@Component(value="repositoryServidumbreService")
@Transactional(readOnly = true)
public class RepositoryServidumbreService implements ServidumbreService {

	private static Logger log = LoggerFactory.getLogger( RepositoryServidumbreService.class );

	@Autowired
	private ServidumbreRepository servidumbreRepository;
	
	@Autowired
	private ServidumbreEnsamblador servidumbreEnsamblador;

	@Transactional
	@Override
	public ServidumbreDTO crear(ServidumbreDTO servidumbreDTO) {
		log.debug("Creando un nuevo detalle de servidumbre {}",
                servidumbreDTO);
		Servidumbre servidumbre;
		try {
			servidumbre = servidumbreEnsamblador.crearServidumbre(servidumbreDTO);
		} catch (AvaluoNotFoundException e) {
			log.debug("AvaluoNotFoundException: no se encontró el avaluo asociado al servidumbre {}",
                    servidumbreDTO);
			return null;
		}
		servidumbreRepository.save(servidumbre);
		servidumbreDTO.setId(servidumbre.getId());
		return servidumbreDTO;
	}

	@Transactional(rollbackFor = ServidumbreNotFoundException.class)
	@Override
	public ServidumbreDTO eliminar(Long servidumbreId) throws ServidumbreNotFoundException {
		log.debug("Eliminando el servidumbre con id: {}",
                servidumbreId);
		Servidumbre servidumbre = servidumbreRepository.findOne(servidumbreId);
		if (servidumbre == null) {
			throw new ServidumbreNotFoundException();
		}
		servidumbreRepository.delete(servidumbre);
		return servidumbreEnsamblador.escribirDTO(servidumbre);
	}

	@Transactional(readOnly = true)
	@Override
	public List<ServidumbreDTO> encontrarTodos() {
		log.debug("Encontrando todas las servidumbres");
		return servidumbreEnsamblador.escribirListaDTO(servidumbreRepository.findAll());
	}

	@Transactional(readOnly = true)
	@Override
	public ServidumbreDTO encontrarPorId(Long id) {
		return servidumbreEnsamblador.escribirDTO(servidumbreRepository.findOne(id));
	}

	@Transactional(rollbackFor = ServidumbreNotFoundException.class)
	@Override
	public ServidumbreDTO actualizar(ServidumbreDTO actualizado)
			throws ServidumbreNotFoundException {
		try {
			servidumbreEnsamblador.actualizarServidumbre(actualizado.getId(), actualizado);
		} catch (AvaluoNotFoundException e) {
			log.debug(
                    "AvaluoNotFoundException: no se encontró el avaluo asociado al servidumbre {}",
                    actualizado);
			return null;
		}
		return actualizado;
	}

}
