package com.helio4.bancol.avaluos.servicio;

import com.helio4.bancol.avaluos.dto.EstadoAvaluoDTO;
import com.helio4.bancol.avaluos.ensamblador.EstadoAvaluoEnsamblador;
import com.helio4.bancol.avaluos.modelo.EstadoAvaluo;
import com.helio4.bancol.avaluos.persistencia.AvaluoRepository;
import com.helio4.bancol.avaluos.persistencia.EstadoAvaluoRepository;
import com.helio4.bancol.avaluos.servicio.datos.EstadoAvaluoService;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.EstadoAvaluoNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.UsuarioNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component(value="repositoryEstadoAvaluoService")
@Transactional(readOnly = true)
public class RepositoryEstadoAvaluoService implements EstadoAvaluoService {

	private static Logger log = LoggerFactory.getLogger( RepositoryEstadoAvaluoService.class );

	@Autowired
	private EstadoAvaluoRepository estadoAvaluoRepository;
	
	@Autowired
	private AvaluoRepository avaluoRepository;
	
	@Autowired
	private EstadoAvaluoEnsamblador estadoAvaluoEnsamblador;

	@Transactional(rollbackFor = EstadoAvaluoNotFoundException.class)
	@Override
	public EstadoAvaluoDTO eliminar(Long estadoAvaluoId) throws EstadoAvaluoNotFoundException {
		log.debug("Eliminando el estadoAvaluo con id: {}", estadoAvaluoId);
		EstadoAvaluo estadoAvaluo = estadoAvaluoRepository.findOne(estadoAvaluoId);
		if (estadoAvaluo == null) {
			throw new EstadoAvaluoNotFoundException();
		}
		estadoAvaluoRepository.delete(estadoAvaluo);
		return estadoAvaluoEnsamblador.escribirDTO(estadoAvaluo);
	}

	@Transactional(readOnly = true)
	@Override
	public List<EstadoAvaluoDTO> encontrarTodos() {
		log.debug("Encontrando todas las estadoAvaluos");
		return estadoAvaluoEnsamblador.escribirListaDTO(estadoAvaluoRepository.findAll());
	}

	@Transactional(readOnly = true)
	@Override
	public EstadoAvaluoDTO encontrarPorId(Long id) {
		return estadoAvaluoEnsamblador.escribirDTO(estadoAvaluoRepository.findOne(id));
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<EstadoAvaluoDTO> encontrarPorAvaluo(Long id) {
		return estadoAvaluoRepository.buscarEstadosAvaluo(id);
	}

	@Transactional(rollbackFor = EstadoAvaluoNotFoundException.class)
	@Override
	public EstadoAvaluoDTO actualizar(EstadoAvaluoDTO actualizado)
			throws EstadoAvaluoNotFoundException {
		log.debug("Actualizando estado {}", actualizado);
		try {
			estadoAvaluoEnsamblador.actualizarEstadoAvaluo(actualizado.getId(), actualizado);
		} catch (AvaluoNotFoundException e) {
			log.debug(
                    "AvaluoNotFoundException: No se encontró el avaluo asociado al EstadoAvaluo: {}",
                    actualizado);
			return null;
		} catch (UsuarioNotFoundException e) {
			log.debug(
                    "UsuarioNotFoundException: No se encontró el perito asociado al EstadoAvaluo: {}",
                    actualizado);
			return null;
		}
		return actualizado;
	}

	@Transactional(readOnly = true)
	@Override
	public String buscarUltimasCorreccionesSolicitadas(Long id) {
		return estadoAvaluoRepository.buscarUltimasCorreccionesSolicitadas(id);
	}

	@Override
	public EstadoAvaluoDTO buscarPor(String codigoExterno) {
		return estadoAvaluoEnsamblador
				.escribirDTO(estadoAvaluoRepository
						.buscarPorCodigoExterno(codigoExterno));
	}
	
	@Override
	public EstadoAvaluoDTO buscarEstadoActualPorCodigoExterno(String codigoExterno, Integer codigoEntidad) {
		return estadoAvaluoEnsamblador
				.escribirDTO(estadoAvaluoRepository
						.buscarEstadoActualPorCodigoExterno(codigoExterno, codigoEntidad));
	}
}
