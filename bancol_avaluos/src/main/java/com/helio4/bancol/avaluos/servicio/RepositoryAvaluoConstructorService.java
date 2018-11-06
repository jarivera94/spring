package com.helio4.bancol.avaluos.servicio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.helio4.bancol.avaluos.servicio.datos.AvaluoConstructorService;
import com.helio4.bancol.avaluos.servicio.excepciones.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.AvaluoConstructorDTO;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;
import com.helio4.bancol.avaluos.ensamblador.AvaluoEnsamblador;
import com.helio4.bancol.avaluos.ensamblador.UsuarioEnsamblador;
import com.helio4.bancol.avaluos.exception.GarajeNotFoundException;
import com.helio4.bancol.avaluos.modelo.AvaluoConstructor;
import com.helio4.bancol.avaluos.modelo.EstadoPorAsignar;
import com.helio4.bancol.avaluos.modelo.Usuario;
import com.helio4.bancol.avaluos.modelo.Usuario.DocumentoIdentificacion;
import com.helio4.bancol.avaluos.persistencia.AvaluoConstructorRepository;
import com.helio4.bancol.avaluos.persistencia.EstadoAvaluoRepository;
import com.helio4.bancol.avaluos.persistencia.UsuarioRepository;

@Component("repositoryAvaluoConstructorService")
@Transactional(readOnly = true)
public class RepositoryAvaluoConstructorService implements
		AvaluoConstructorService {

	private static Logger log = LoggerFactory.getLogger( RepositoryAvaluoConstructorService.class );

	@Autowired
	private AvaluoConstructorRepository avaluoConstructorRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private AvaluoEnsamblador avaluoEnsamblador;
	
	@Autowired
	private UsuarioEnsamblador usuarioEnsamblador;
	
	@Autowired
	private EstadoAvaluoRepository estadoAvaluoRepository;

	@Transactional
	@Override
	public AvaluoConstructorDTO crear(AvaluoConstructorDTO avaluoConstructorDTO, UsuarioDTO usuarioDTO) {
        log.debug("Creando la solicitud del avaluo hipotecario:  {}",
                avaluoConstructorDTO);
		AvaluoConstructor avaluoConstructor;
		try {
            avaluoConstructor = avaluoEnsamblador.crearAvaluoConstructor(avaluoConstructorDTO);
            Usuario creador = usuarioRepository.findOne(
					new DocumentoIdentificacion(usuarioDTO.getTipoDocumentoIdentificacion(),
							usuarioDTO.getNumeroDocumento()));
            avaluoConstructor.setCreador(creador);
            avaluoConstructor.setFechaCreacion(avaluoConstructor.getEstado().getFechaEstado());
			avaluoConstructor = avaluoConstructorRepository.save(avaluoConstructor);
			EstadoPorAsignar estadoPorAsignar = (EstadoPorAsignar) avaluoConstructor.getEstado();
			estadoPorAsignar.setUsuario(creador);
			estadoAvaluoRepository.save(estadoPorAsignar);
		} catch (ClienteNotFoundException e) {
			log.debug(
                    "ClienteNotFoundException: No se encontro el cliente en el avaluo: {}",
                    avaluoConstructorDTO);
			return null;
		} catch (EntidadNotFoundException e) {
			log.debug(
                    "EntidadNotFoundException: No se encontro la entidad en el avaluo: {}",
                    avaluoConstructorDTO);
			return null;
		} catch (UsuarioNotFoundException e) {
			log.debug(
                    "UsuarioNotFoundException: No se encontro el Usuario en el avaluo: {}",
                    avaluoConstructorDTO);
			return null;
		} catch (FotografiaNotFoundException e) {
			log.debug(
                    "FotografiaNotFoundException: No se encontro la fotografia en el avaluo: {}",
                    avaluoConstructorDTO);
			return null;
		} catch (AreaNotFoundException e) {
			log.debug(
                    "AreaNotFoundException: No se encontro el area en el avaluo: {}",
                    avaluoConstructorDTO);
			return null;
		} catch (CronogramaObraNotFoundException e) {
			log.debug(
                    "CronogramaObraNotFoundException: No se encontro el cronograma de obra en el avaluo: {}",
                    avaluoConstructorDTO);
			return null;
		} catch (TipoAvaluoNotFoundException e) {
            log.debug(
                    "TipoAvaluoNotFoundException: No se encontro el tipo de avaluo:  {}",
                    avaluoConstructorDTO);
			return null;
		} catch (MetodoValuacionNotFoundException e) {
            log.debug(
                    "MetodoValuacionNotFoundException: No se encontro un método de valuación:  {}",
                    avaluoConstructorDTO);
            return null;
        }catch (GarajeNotFoundException e) {
            log.debug(
                    "GarajeNotFoundException: No se encontro un garajen en el avaluo:  {}",
                    avaluoConstructorDTO);
            return null;
        }
		avaluoConstructorDTO.setId(avaluoConstructor.getId());
		return avaluoConstructorDTO;
	}

	@Transactional(rollbackFor = AvaluoNotFoundException.class)
	@Override
	public AvaluoConstructorDTO eliminar(Long avaluoId) throws AvaluoNotFoundException {
		AvaluoConstructor deleted = avaluoConstructorRepository.findOne(avaluoId);
		if (deleted == null){
			throw new AvaluoNotFoundException();
		}
		avaluoConstructorRepository.delete(deleted);
		return avaluoEnsamblador.escribirDTO(deleted);
	}

	@Transactional(readOnly = true)
	@Override
	public List<AvaluoConstructorDTO> encontrarTodos() {
		List<AvaluoConstructorDTO> avaluoConstructorDTOs = new ArrayList<AvaluoConstructorDTO>();
		for (AvaluoConstructor avaluoConstructor:avaluoConstructorRepository.findAll()) {
			avaluoConstructorDTOs.add(avaluoEnsamblador.escribirDTO(avaluoConstructor));
		}
		return avaluoConstructorDTOs;
	}

	@Transactional(readOnly = true)
	@Override
	public AvaluoConstructorDTO encontrarPorId(Long id) {
		return avaluoEnsamblador.escribirDTO(avaluoConstructorRepository.findOne(id));
	}

	@Transactional(rollbackFor = AvaluoNotFoundException.class)
	@Override
	public AvaluoConstructorDTO actualizar(AvaluoConstructorDTO actualizado)
			throws AvaluoNotFoundException {
        log.debug("Actualizando avaluo  {}",
                actualizado);
        AvaluoConstructor avaluoConstructor = avaluoConstructorRepository.findOne(actualizado.getId());
        if (avaluoConstructor == null) {
            throw new AvaluoNotFoundException();
        }
        return actualizar(avaluoConstructor, actualizado);
	}

    @Override
    @Transactional(rollbackFor = AvaluoNotFoundException.class)
    public AvaluoConstructorDTO actualizar(AvaluoConstructor avaluoConstructor,
            AvaluoConstructorDTO actualizado) throws AvaluoNotFoundException {
		try {
			avaluoEnsamblador.actualizarAvaluoConstructor(avaluoConstructor, actualizado);
		} catch (CronogramaObraNotFoundException e) {
			log.error(
                    "CronogramaObraNotFoundException: No se encontro el cronograma de obra en el avaluo: {}",
                    actualizado);
			return null;
		} catch (EntidadNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClienteNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UsuarioNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FotografiaNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AreaNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TipoAvaluoNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MetodoValuacionNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (GarajeNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		return actualizado;
    }

    @Transactional(readOnly = true)
	@Override
	public AvaluoConstructorDTO encontrarAvaluoConAreas(Long avaluoId) {
		return avaluoEnsamblador.escribirDTO(avaluoConstructorRepository.encontrarAvaluoConAreas(avaluoId));
	}

    @Transactional(readOnly = true)
	@Override
	public List<AvaluoConstructorDTO> encontrarPorFechaTerminacion(
			Date fechaInicial, Date fechaFinal) {
		List<AvaluoConstructorDTO> avaluoConstructorDTOs = new ArrayList<AvaluoConstructorDTO>();
		for (AvaluoConstructor avaluoConstructor:avaluoConstructorRepository.encontrarPorFechaTerminacion(fechaInicial, fechaFinal)) {
			avaluoConstructorDTOs.add(avaluoEnsamblador.escribirDTO(avaluoConstructor));
		}
		return avaluoConstructorDTOs;
	}

}
