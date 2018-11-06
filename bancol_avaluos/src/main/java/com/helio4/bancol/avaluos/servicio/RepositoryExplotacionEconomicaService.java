package com.helio4.bancol.avaluos.servicio;

import java.util.List;

import com.helio4.bancol.avaluos.servicio.datos.ExplotacionEconomicaService;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.ExplotacionEconomicaNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.ExplotacionEconomicaDTO;
import com.helio4.bancol.avaluos.ensamblador.ExplotacionEconomicaEnsamblador;
import com.helio4.bancol.avaluos.modelo.ExplotacionEconomica;
import com.helio4.bancol.avaluos.persistencia.ExplotacionEconomicaRepository;

@Component(value="repositoryExplotacionEconomicaService")
@Transactional(readOnly = true)
public class RepositoryExplotacionEconomicaService implements
		ExplotacionEconomicaService {

	private static Logger log = LoggerFactory.getLogger( RepositoryExplotacionEconomicaService.class );

	@Autowired
	private ExplotacionEconomicaRepository explotacionEconomicaRepository;
	
	@Autowired
	private ExplotacionEconomicaEnsamblador explotacionEconomicaEnsamblador;

	@Transactional
	@Override
	public ExplotacionEconomicaDTO crear(ExplotacionEconomicaDTO explotacionEconomicaDTO) {
        log.debug("Creando un nuevo detalle de explotacionEconomica {}",
                explotacionEconomicaDTO);
		ExplotacionEconomica explotacionEconomica;
		try {
			explotacionEconomica = explotacionEconomicaEnsamblador.crearExplotacionEconomica(explotacionEconomicaDTO);
		} catch (AvaluoNotFoundException e) {
			log.debug("AvaluoNotFoundException: no se encontró el avaluo asociado al explotacionEconomica {}",
 explotacionEconomicaDTO);
			return null;
		}
		explotacionEconomicaRepository.save(explotacionEconomica);
		explotacionEconomicaDTO.setId(explotacionEconomica.getId());
		return explotacionEconomicaDTO;
	}

	@Transactional(rollbackFor = ExplotacionEconomicaNotFoundException.class)
	@Override
	public ExplotacionEconomicaDTO eliminar(Long explotacionEconomicaId) throws ExplotacionEconomicaNotFoundException {
        log.debug("Eliminando el explotacionEconomica con id:  {}",
                explotacionEconomicaId);
		ExplotacionEconomica explotacionEconomica = explotacionEconomicaRepository.findOne(explotacionEconomicaId);
		if (explotacionEconomica == null) {
			throw new ExplotacionEconomicaNotFoundException();
		}
		explotacionEconomicaRepository.delete(explotacionEconomica);
		return explotacionEconomicaEnsamblador.escribirDTO(explotacionEconomica);
	}

	@Transactional(readOnly = true)
	@Override
	public List<ExplotacionEconomicaDTO> encontrarTodos() {
		log.debug("Encontrando todas las explotacionEconomicas");
		return explotacionEconomicaEnsamblador.escribirListaDTO(explotacionEconomicaRepository.findAll());
	}

	@Transactional(readOnly = true)
	@Override
	public ExplotacionEconomicaDTO encontrarPorId(Long id) {
		return explotacionEconomicaEnsamblador.escribirDTO(explotacionEconomicaRepository.findOne(id));
	}

	@Transactional(rollbackFor = ExplotacionEconomicaNotFoundException.class)
	@Override
	public ExplotacionEconomicaDTO actualizar(ExplotacionEconomicaDTO actualizado)
			throws ExplotacionEconomicaNotFoundException {
		try {
			explotacionEconomicaEnsamblador.actualizarExplotacionEconomica(actualizado.getId(), actualizado);
		} catch (AvaluoNotFoundException e) {
            log.debug("AvaluoNotFoundException: no se encontró el avaluo asociado al explotacionEconomica {}",
                    actualizado);
			return null;
		}
		return actualizado;
	}

}
