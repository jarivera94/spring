package com.helio4.bancol.avaluos.servicio;

import java.util.List;

import com.helio4.bancol.avaluos.servicio.datos.AumentoPresupuestoService;
import com.helio4.bancol.avaluos.servicio.excepciones.AumentoPresupuestoNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.AumentoPresupuestoDTO;
import com.helio4.bancol.avaluos.ensamblador.AumentoPresupuestoEnsamblador;
import com.helio4.bancol.avaluos.modelo.AumentoPresupuesto;
import com.helio4.bancol.avaluos.persistencia.AumentoPresupuestoRepository;

@Component(value="repositoryAumentoPresupuestoService")
@Transactional(readOnly = true)
public class RepositoryAumentoPresupuestoService implements
		AumentoPresupuestoService {

	private static Logger log = LoggerFactory.getLogger( RepositoryAumentoPresupuestoService.class );

	@Autowired
	private AumentoPresupuestoRepository aumentoPresupuestoRepository;
	
	@Autowired
	private AumentoPresupuestoEnsamblador aumentoPresupuestoEnsamblador;

	@Transactional
	@Override
	public AumentoPresupuestoDTO crear(AumentoPresupuestoDTO aumentoPresupuestoDTO) {
		log.debug(
                "Creando un nuevo detalle de aumentoPresupuesto {}",
                aumentoPresupuestoDTO);
		AumentoPresupuesto aumentoPresupuesto;
		try {
			aumentoPresupuesto = aumentoPresupuestoEnsamblador.crearAumentoPresupuesto(aumentoPresupuestoDTO);
		} catch (AvaluoNotFoundException e) {
			log.debug(
                    "AvaluoNotFoundException: no se encontró el avaluo asociado al aumentoPresupuesto {}",
                    aumentoPresupuestoDTO);
			return null;
		}
		aumentoPresupuestoRepository.save(aumentoPresupuesto);
		aumentoPresupuestoDTO.setId(aumentoPresupuesto.getId());
		return aumentoPresupuestoDTO;
	}

	@Transactional(rollbackFor = AumentoPresupuestoNotFoundException.class)
	@Override
	public AumentoPresupuestoDTO eliminar(Long aumentoPresupuestoId) throws AumentoPresupuestoNotFoundException {
		log.debug(
                "Eliminando el aumentoPresupuesto con id: {}",
                aumentoPresupuestoId);
		AumentoPresupuesto aumentoPresupuesto = aumentoPresupuestoRepository.findOne(aumentoPresupuestoId);
		if (aumentoPresupuesto == null) {
			throw new AumentoPresupuestoNotFoundException();
		}
		aumentoPresupuestoRepository.delete(aumentoPresupuesto);
		return aumentoPresupuestoEnsamblador.escribirDTO(aumentoPresupuesto);
	}

	@Transactional(readOnly = true)
	@Override
	public List<AumentoPresupuestoDTO> encontrarTodos() {
		log.debug("Encontrando todas las aumentoPresupuestos");
		return aumentoPresupuestoEnsamblador.escribirListaDTO(aumentoPresupuestoRepository.findAll());
	}

	@Transactional(readOnly = true)
	@Override
	public AumentoPresupuestoDTO encontrarPorId(Long id) {
		return aumentoPresupuestoEnsamblador.escribirDTO(aumentoPresupuestoRepository.findOne(id));
	}

	@Transactional(rollbackFor = AumentoPresupuestoNotFoundException.class)
	@Override
	public AumentoPresupuestoDTO actualizar(AumentoPresupuestoDTO actualizado)
			throws AumentoPresupuestoNotFoundException {
		try {
			aumentoPresupuestoEnsamblador.actualizarAumentoPresupuesto(actualizado.getId(), actualizado);
		} catch (AvaluoNotFoundException e) {
			log.debug(
                    "AvaluoNotFoundException: no se encontró el avaluo asociado al aumentoPresupuesto {}",
                    actualizado);
			return null;
		}
		return actualizado;
	}

}
