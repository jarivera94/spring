package com.helio4.bancol.avaluos.servicio;

import java.util.List;

import com.helio4.bancol.avaluos.servicio.datos.ProrrogaService;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.ProrrogaNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.ProrrogaDTO;
import com.helio4.bancol.avaluos.ensamblador.ProrrogaEnsamblador;
import com.helio4.bancol.avaluos.modelo.Prorroga;
import com.helio4.bancol.avaluos.persistencia.ProrrogaRepository;

@Component(value="repositoryProrrogaService")
@Transactional(readOnly = true)
public class RepositoryProrrogaService implements ProrrogaService {

	private static Logger log = LoggerFactory.getLogger( RepositoryProrrogaService.class );

	@Autowired
	private ProrrogaRepository prorrogaRepository;
	
	@Autowired
	private ProrrogaEnsamblador prorrogaEnsamblador;

	@Transactional
	@Override
	public ProrrogaDTO crear(ProrrogaDTO prorrogaDTO) {
		log.debug("Creando un nuevo detalle de prorroga {}",
                prorrogaDTO);
		Prorroga prorroga;
		try {
			prorroga = prorrogaEnsamblador.crearProrroga(prorrogaDTO);
		} catch (AvaluoNotFoundException e) {
			log.debug("AvaluoNotFoundException: no se encontró el avaluo asociado al prorroga {}",
                    prorrogaDTO);
			return null;
		}
		prorrogaRepository.save(prorroga);
		prorrogaDTO.setId(prorroga.getId());
		return prorrogaDTO;
	}

	@Transactional(rollbackFor = ProrrogaNotFoundException.class)
	@Override
	public ProrrogaDTO eliminar(Long prorrogaId) throws ProrrogaNotFoundException {
		log.debug("Eliminando el prorroga con id: {}",
                prorrogaId);
		Prorroga prorroga = prorrogaRepository.findOne(prorrogaId);
		if (prorroga == null) {
			throw new ProrrogaNotFoundException();
		}
		prorrogaRepository.delete(prorroga);
		return prorrogaEnsamblador.escribirDTO(prorroga);
	}

	@Transactional(readOnly = true)
	@Override
	public List<ProrrogaDTO> encontrarTodos() {
		log.debug("Encontrando todas las prorrogas");
		return prorrogaEnsamblador.escribirListaDTO(prorrogaRepository.findAll());
	}

	@Transactional(readOnly = true)
	@Override
	public ProrrogaDTO encontrarPorId(Long id) {
		return prorrogaEnsamblador.escribirDTO(prorrogaRepository.findOne(id));
	}

	@Transactional(rollbackFor = ProrrogaNotFoundException.class)
	@Override
	public ProrrogaDTO actualizar(ProrrogaDTO actualizado)
			throws ProrrogaNotFoundException {
		try {
			prorrogaEnsamblador.actualizarProrroga(actualizado.getId(), actualizado);
		} catch (AvaluoNotFoundException e) {
			log.debug("AvaluoNotFoundException: no se encontró el avaluo asociado al prorroga {}",
                    actualizado);
			return null;
		}
		return actualizado;
	}

}
