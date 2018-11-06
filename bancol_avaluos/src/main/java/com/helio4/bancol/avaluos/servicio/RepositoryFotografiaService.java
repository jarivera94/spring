package com.helio4.bancol.avaluos.servicio;

import java.util.List;

import com.helio4.bancol.avaluos.servicio.datos.FotografiaService;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.FotografiaNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.FotografiaDTO;
import com.helio4.bancol.avaluos.ensamblador.FotografiaEnsamblador;
import com.helio4.bancol.avaluos.modelo.Anexo;
import com.helio4.bancol.avaluos.modelo.Croquis;
import com.helio4.bancol.avaluos.modelo.Fotografia;
import com.helio4.bancol.avaluos.persistencia.AnexoRepository;
import com.helio4.bancol.avaluos.persistencia.CroquisRepository;
import com.helio4.bancol.avaluos.persistencia.FotografiaRepository;

@Component(value="repositoryFotografiaService")
@Transactional(readOnly = true)
public class RepositoryFotografiaService implements FotografiaService {

	private static Logger log = LoggerFactory.getLogger( RepositoryFotografiaService.class );

	@Autowired
	private FotografiaRepository fotografiaRepository;
	
	@Autowired
	private AnexoRepository anexoRepository;
	
	@Autowired
	private CroquisRepository croquisRepository;
	
	@Autowired
	private FotografiaEnsamblador fotografiaEnsamblador;

	@Transactional
	@Override
	public FotografiaDTO crear(FotografiaDTO fotografiaDTO) {
        log.debug("Creando un nuevo detalle de fotografia {}",
                fotografiaDTO);
		Fotografia fotografia;
		try {
			fotografia = fotografiaEnsamblador.crearFotografia(fotografiaDTO);
		} catch (AvaluoNotFoundException e) {
            log.debug("AvaluoNotFoundException: no se encontro el avaluo de la fotografia:  {}",
                    fotografiaDTO);
			return null;
		}
		fotografia = fotografiaRepository.save(fotografia);
		fotografiaDTO.setId(fotografia.getId());
		return fotografiaDTO;
	}
	
	@Transactional
	@Override
	public FotografiaDTO crearAnexo(FotografiaDTO fotografiaDTO) {
        log.debug("Creando un nuevo detalle de anexo {}",
                fotografiaDTO);
		Anexo fotografia;
		try {
			fotografia = fotografiaEnsamblador.crearAnexo(fotografiaDTO);
		} catch (AvaluoNotFoundException e) {
            log.debug("AvaluoNotFoundException: no se encontro el avaluo del anexo:  {}",
                    fotografiaDTO);
			return null;
		}
		fotografia = anexoRepository.save(fotografia);
		fotografiaDTO.setId(fotografia.getId());
		return fotografiaDTO;
	}
	
	@Transactional
	@Override
	public FotografiaDTO crearCroquis(FotografiaDTO fotografiaDTO) {
        log.debug("Creando un nuevo detalle de croquis {}",
                fotografiaDTO);
		Croquis fotografia;
		try {
			fotografia = fotografiaEnsamblador.crearCroquis(fotografiaDTO);
		} catch (AvaluoNotFoundException e) {
            log.debug("AvaluoNotFoundException: no se encontro el avaluo del anexo:  {}",
                    fotografiaDTO);
			return null;
		}
		fotografia = croquisRepository.save(fotografia);
		fotografiaDTO.setId(fotografia.getId());
		return fotografiaDTO;
	}

	@Transactional(rollbackFor = FotografiaNotFoundException.class)
	@Override
	public FotografiaDTO eliminar(Long fotografiaId) throws FotografiaNotFoundException {
        log.debug("Eliminando el fotografia con id: {}",
                fotografiaId);
		Fotografia fotografia = fotografiaRepository.findOne(fotografiaId);
		if (fotografia == null) {
			throw new FotografiaNotFoundException();
		}
		fotografiaRepository.delete(fotografia);
		return fotografiaEnsamblador.escribirDTO(fotografia);
	}
	
	@Transactional(rollbackFor = FotografiaNotFoundException.class)
	@Override
	public FotografiaDTO eliminarAnexo(Long anexoId) throws FotografiaNotFoundException {
        log.debug("Eliminando el anexo con id: {}",
                anexoId);
		Anexo fotografia = anexoRepository.findOne(anexoId);
		if (fotografia == null) {
			throw new FotografiaNotFoundException();
		}
		anexoRepository.delete(fotografia);
		return fotografiaEnsamblador.escribirDTO(fotografia);
	}
	
	@Transactional(rollbackFor = FotografiaNotFoundException.class)
	@Override
	public FotografiaDTO eliminarCroquis(Long anexoId) throws FotografiaNotFoundException {
        log.debug("Eliminando el croquis con id: {}",
                anexoId);
		Croquis fotografia = croquisRepository.findOne(anexoId);
		if (fotografia == null) {
			throw new FotografiaNotFoundException();
		}
		croquisRepository.delete(fotografia);
		return fotografiaEnsamblador.escribirDTO(fotografia);
	}

	@Transactional(readOnly = true)
	@Override
	public List<FotografiaDTO> encontrarTodos() {
		log.debug("Encontrando todas las fotografias");
		return fotografiaEnsamblador.escribirListaDTO(fotografiaRepository.findAll());
	}

	@Transactional(readOnly = true)
	@Override
	public FotografiaDTO encontrarPorId(Long id) {
		return fotografiaEnsamblador.escribirDTO(fotografiaRepository.findOne(id));
	}

	@Transactional(rollbackFor = FotografiaNotFoundException.class)
	@Override
	public FotografiaDTO actualizar(FotografiaDTO actualizado)
			throws FotografiaNotFoundException {
		try {
			fotografiaEnsamblador.actualizarFotografia(actualizado.getId(), actualizado);
		} catch (AvaluoNotFoundException e) {
            log.debug("AvaluoNotFoundException: no se encontro el avaluo de la fotografia:  {}",
                    actualizado);
			return null;
		}
		return actualizado;
	}
	
	@Transactional(rollbackFor = FotografiaNotFoundException.class)
	@Override
	public FotografiaDTO actualizarAnexo(FotografiaDTO actualizado)
			throws FotografiaNotFoundException {
		try {
			fotografiaEnsamblador.actualizarAnexo(actualizado.getId(), actualizado);
		} catch (AvaluoNotFoundException e) {
            log.debug("AvaluoNotFoundException: no se encontro el avaluo de la fotografia:  {}",
                    actualizado);
			return null;
		}
		return actualizado;
	}
	
	@Transactional(rollbackFor = FotografiaNotFoundException.class)
	@Override
	public FotografiaDTO actualizarCroquis(FotografiaDTO actualizado)
			throws FotografiaNotFoundException {
		try {
			fotografiaEnsamblador.actualizarCroquis(actualizado.getId(), actualizado);
		} catch (AvaluoNotFoundException e) {
            log.debug("AvaluoNotFoundException: no se encontro el avaluo del croquis:  {}",
                    actualizado);
			return null;
		}
		return actualizado;
	}

	@Override
	public List<FotografiaDTO> buscarFotografiasAvaluo(Long avaluoId) {
		return fotografiaEnsamblador.escribirListaDTO(fotografiaRepository.buscarFotografiasAvaluo(avaluoId));
	}
	
	@Override
	public List<FotografiaDTO> buscarAnexosAvaluo(Long avaluoId) {
		return fotografiaEnsamblador.escribirListaAnexosDTO(anexoRepository.buscarAnexosAvaluo(avaluoId));
	}
	
	@Override
	public List<FotografiaDTO> buscarCroquisAvaluo(Long avaluoId) {
		return fotografiaEnsamblador.escribirListaCroquisDTO(croquisRepository.buscarCroquisAvaluo(avaluoId));
	}

    @Transactional(rollbackFor = FotografiaNotFoundException.class)
    @Override
    public FotografiaDTO guardarRutaFotografia(FotografiaDTO fotografiaDTO)
            throws FotografiaNotFoundException {
        Fotografia fotografia = fotografiaRepository.findOne(fotografiaDTO
                .getId());
        if (fotografia == null) {
            throw new FotografiaNotFoundException();
        }
        fotografia.setRutaUbicacion(fotografiaDTO.getRutaUbicacion());
        return fotografiaDTO;
    }
    
    @Transactional(rollbackFor = FotografiaNotFoundException.class)
    @Override
    public FotografiaDTO guardarRutaAnexo(FotografiaDTO fotografiaDTO)
            throws FotografiaNotFoundException {
        Anexo fotografia = anexoRepository.findOne(fotografiaDTO
                .getId());
        if (fotografia == null) {
            throw new FotografiaNotFoundException();
        }
        fotografia.setRutaUbicacion(fotografiaDTO.getRutaUbicacion());
        return fotografiaDTO;
    }
    
    @Transactional(rollbackFor = FotografiaNotFoundException.class)
    @Override
    public FotografiaDTO guardarRutaCroquis(FotografiaDTO fotografiaDTO)
            throws FotografiaNotFoundException {
        Croquis fotografia = croquisRepository.findOne(fotografiaDTO
                .getId());
        if (fotografia == null) {
            throw new FotografiaNotFoundException();
        }
        fotografia.setRutaUbicacion(fotografiaDTO.getRutaUbicacion());
        return fotografiaDTO;
    }

}
