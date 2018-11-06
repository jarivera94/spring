package com.helio4.bancol.avaluos.servicio;

import java.util.List;

import com.helio4.bancol.avaluos.servicio.datos.CronogramaObraService;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.CronogramaObraNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.CronogramaObraDTO;
import com.helio4.bancol.avaluos.ensamblador.CronogramaObraEnsamblador;
import com.helio4.bancol.avaluos.modelo.CronogramaObra;
import com.helio4.bancol.avaluos.persistencia.CronogramaObraRepository;

@Component(value="repositoryCronogramaObraService")
@Transactional(readOnly = true)
public class RepositoryCronogramaObraService implements CronogramaObraService {

	private static Logger log = LoggerFactory.getLogger( RepositoryCronogramaObraService.class );

	@Autowired
	private CronogramaObraRepository cronogramaObraRepository;
	
	@Autowired
	private CronogramaObraEnsamblador cronogramaObraEnsamblador;

	@Transactional
	@Override
	public CronogramaObraDTO crear(CronogramaObraDTO cronogramaObraDTO) {
		log.debug(
                "Creando un nuevo detalle de cronogramaObra {}",
                cronogramaObraDTO);
		CronogramaObra cronogramaObra;
		try {
			cronogramaObra = cronogramaObraEnsamblador.crearCronogramaObra(cronogramaObraDTO);
		} catch (AvaluoNotFoundException e) {
			log.debug(
                    "AvaluoNotFoundException: no se encontró el avaluo asociado al cronogramaObra {}",
                    cronogramaObraDTO);
			return null;
		}
		cronogramaObraRepository.save(cronogramaObra);
		cronogramaObraDTO.setId(cronogramaObra.getId());
		return cronogramaObraDTO;
	}

	@Transactional(rollbackFor = CronogramaObraNotFoundException.class)
	@Override
	public CronogramaObraDTO eliminar(Long cronogramaObraId) throws CronogramaObraNotFoundException {
		log.debug("Eliminando el cronogramaObra con id: {}",
                cronogramaObraId);
		CronogramaObra cronogramaObra = cronogramaObraRepository.findOne(cronogramaObraId);
		if (cronogramaObra == null) {
			throw new CronogramaObraNotFoundException();
		}
		cronogramaObraRepository.delete(cronogramaObra);
		return cronogramaObraEnsamblador.escribirDTO(cronogramaObra);
	}

	@Transactional(readOnly = true)
	@Override
	public List<CronogramaObraDTO> encontrarTodos() {
		log.debug("Encontrando todas las cronogramaObras");		
		return cronogramaObraEnsamblador.escribirListaDTO(cronogramaObraRepository.findAll(sortById()));
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
	public CronogramaObraDTO encontrarPorId(Long id) {
		return cronogramaObraEnsamblador.escribirDTO(cronogramaObraRepository.findOne(id));
	}
	
	@Transactional(readOnly = true)
	@Override
	public CronogramaObraDTO encontrarPorIdAvaluo(Long id) {
		CronogramaObra cronogramaObra = cronogramaObraRepository.findOne(id);
		if (cronogramaObra != null) {
			return cronogramaObraEnsamblador.escribirDTO(cronogramaObra);
		}
		return null;
	}

	@Transactional(rollbackFor = CronogramaObraNotFoundException.class)
	@Override
	public CronogramaObraDTO actualizar(CronogramaObraDTO actualizado)
			throws CronogramaObraNotFoundException {
		try {
			cronogramaObraEnsamblador.actualizarCronogramaObra(actualizado.getId(), actualizado);
		} catch (AvaluoNotFoundException e) {
			log.debug(
                    "AvaluoNotFoundException: no se encontró el avaluo asociado al cronogramaObra {}",
                    actualizado);
			return null;
		}
		return actualizado;
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<CronogramaObraDTO> encontrarPorTipoCosto(Integer tipoCosto) {		
		return cronogramaObraEnsamblador.escribirListaDTO(cronogramaObraRepository.encontrarPorTipoCosto(tipoCosto));
	}	

}
