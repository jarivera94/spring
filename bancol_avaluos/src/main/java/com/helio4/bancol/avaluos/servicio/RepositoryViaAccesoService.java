package com.helio4.bancol.avaluos.servicio;

import java.util.List;

import com.helio4.bancol.avaluos.servicio.datos.ViaAccesoService;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.ViaAccesoNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.ViaAccesoDTO;
import com.helio4.bancol.avaluos.ensamblador.ViaAccesoEnsamblador;
import com.helio4.bancol.avaluos.modelo.ViaAcceso;
import com.helio4.bancol.avaluos.persistencia.ViaAccesoRepository;

@Component(value="repositoryViaAccesoService")
@Transactional(readOnly = true)
public class RepositoryViaAccesoService implements ViaAccesoService {

	private static Logger log = LoggerFactory.getLogger( RepositoryViaAccesoService.class );

	@Autowired
	private ViaAccesoRepository viaAccesoRepository;
	
	@Autowired
	private ViaAccesoEnsamblador viaAccesoEnsamblador;

	@Transactional
	@Override
	public ViaAccesoDTO crear(ViaAccesoDTO viaAccesoDTO) {
        log.debug("Creando un nuevo detalle de viaAcceso {}",
                viaAccesoDTO);
		ViaAcceso viaAcceso;
		try {
			viaAcceso = viaAccesoEnsamblador.crearViaAcceso(viaAccesoDTO);
		} catch (AvaluoNotFoundException e) {
            log.debug("AvaluoNotFoundException: no se encontró el avaluo asociado a la viaAcceso {}",
                    viaAccesoDTO);
			return null;
		}
		viaAccesoRepository.save(viaAcceso);
		viaAccesoDTO.setId(viaAcceso.getId());
		return viaAccesoDTO;
	}

	@Transactional(rollbackFor = ViaAccesoNotFoundException.class)
	@Override
	public ViaAccesoDTO eliminar(Long viaAccesoId) throws ViaAccesoNotFoundException {
        log.debug("Eliminando la viaAcceso con id: {}",
                viaAccesoId);
		ViaAcceso viaAcceso = viaAccesoRepository.findOne(viaAccesoId);
		if (viaAcceso == null) {
			throw new ViaAccesoNotFoundException();
		}
		viaAccesoRepository.delete(viaAcceso);
		return viaAccesoEnsamblador.escribirDTO(viaAcceso);
	}

	@Transactional(readOnly = true)
	@Override
	public List<ViaAccesoDTO> encontrarTodos() {
		log.debug("Encontrando todas las viaAccesos");
		return viaAccesoEnsamblador.escribirListaDTO(viaAccesoRepository.findAll());
	}

	@Transactional(readOnly = true)
	@Override
	public ViaAccesoDTO encontrarPorId(Long id) {
		return viaAccesoEnsamblador.escribirDTO(viaAccesoRepository.findOne(id));
	}

	@Transactional(rollbackFor = ViaAccesoNotFoundException.class)
	@Override
	public ViaAccesoDTO actualizar(ViaAccesoDTO actualizado)
			throws ViaAccesoNotFoundException {
		try {
			viaAccesoEnsamblador.actualizarViaAcceso(actualizado.getId(), actualizado);
		} catch (AvaluoNotFoundException e) {
            log.debug("AvaluoNotFoundException: no se encontró el avaluo asociado a la viaAcceso {}",
                    actualizado);
			return null;
		}
		return actualizado;
	}

}
