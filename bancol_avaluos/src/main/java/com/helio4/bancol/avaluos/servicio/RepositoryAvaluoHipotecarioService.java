package com.helio4.bancol.avaluos.servicio;

import com.helio4.bancol.avaluos.dto.AvaluoHipotecarioDTO;
import com.helio4.bancol.avaluos.dto.EntidadDTO;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;
import com.helio4.bancol.avaluos.ensamblador.AvaluoEnsamblador;
import com.helio4.bancol.avaluos.modelo.AvaluoHipotecario;
import com.helio4.bancol.avaluos.modelo.EstadoPorAsignar;
import com.helio4.bancol.avaluos.modelo.Usuario;
import com.helio4.bancol.avaluos.modelo.Usuario.DocumentoIdentificacion;
import com.helio4.bancol.avaluos.persistencia.AvaluoHipotecarioRepository;
import com.helio4.bancol.avaluos.persistencia.EstadoAvaluoRepository;
import com.helio4.bancol.avaluos.persistencia.UsuarioRepository;
import com.helio4.bancol.avaluos.servicio.datos.AvaluoHipotecarioService;
import com.helio4.bancol.avaluos.servicio.excepciones.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component(value="repositoryAvaluoHipotecarioService")
@Transactional(readOnly = true)
public class RepositoryAvaluoHipotecarioService implements AvaluoHipotecarioService {

	private static Logger log = LoggerFactory.getLogger( RepositoryAvaluoHipotecarioService.class );

	@Autowired
	private AvaluoHipotecarioRepository avaluoHipotecarioRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private AvaluoEnsamblador avaluoEnsamblador;

	@Autowired
	private EstadoAvaluoRepository estadoAvaluoRepository;

	@Transactional
	@Override
	public AvaluoHipotecarioDTO crear(AvaluoHipotecarioDTO avaluoHipotecarioDTO, UsuarioDTO usuarioDTO) {
        log.debug("Creando la solicitud del avaluo hipotecario:  {}",
                avaluoHipotecarioDTO);
		AvaluoHipotecario avaluoHipotecario;
		try {
			AvaluoHipotecario avaluo = avaluoEnsamblador.crearAvaluoHipotecario(avaluoHipotecarioDTO);
            Usuario creador = usuarioRepository.findOne(
					new DocumentoIdentificacion(usuarioDTO.getTipoDocumentoIdentificacion(),
							usuarioDTO.getNumeroDocumento()));
            avaluo.setCreador(creador);
            avaluo.setFechaCreacion(avaluo.getEstado().getFechaEstado());
			avaluoHipotecario = avaluoHipotecarioRepository.save(avaluo);
			EstadoPorAsignar estadoPorAsignar = (EstadoPorAsignar) avaluoHipotecario.getEstado();
			estadoPorAsignar.setUsuario(creador);
			estadoAvaluoRepository.save(estadoPorAsignar);
		} catch (ClienteNotFoundException e) {
            log.error("ClienteNotFoundException: No se encontro el cliente en el avaluo:  {}",
                    avaluoHipotecarioDTO);
			return null;
		} catch (EntidadNotFoundException e) {
            log.error("EntidadNotFoundException: No se encontro la entidad en el avaluo:  {}",
                    avaluoHipotecarioDTO);
			return null;
		} catch (UsuarioNotFoundException e) {
            log.error("UsuarioNotFoundException: No se encontro el Usuario en el avaluo:  {}",
                    avaluoHipotecarioDTO);
			return null;
		} catch (TipoAvaluoNotFoundException e) {
            log.error("TipoAvaluoNotFoundException: No se encontro el tipo de avaluo:  {}",
                    avaluoHipotecarioDTO);
			return null;
		}
		avaluoHipotecarioDTO.setId(avaluoHipotecario.getId());
		return avaluoHipotecarioDTO;
	}

	@Transactional(rollbackFor = AvaluoNotFoundException.class)
	@Override
	public AvaluoHipotecarioDTO eliminar(Long avaluoId) throws AvaluoNotFoundException {
		AvaluoHipotecario deleted = avaluoHipotecarioRepository.findOne(avaluoId);
		if (deleted == null){
			throw new AvaluoNotFoundException();
		}
		avaluoHipotecarioRepository.delete(deleted);
		return avaluoEnsamblador.escribirDTO(deleted);
	}

	@Transactional(readOnly = true)
	@Override
	public List<AvaluoHipotecarioDTO> encontrarTodos() {
		List<AvaluoHipotecarioDTO> avaluoHipotecarioDTOs = new ArrayList<AvaluoHipotecarioDTO>();
		for (AvaluoHipotecario avaluoHipotecario:avaluoHipotecarioRepository.findAll()) {
			avaluoHipotecarioDTOs.add(avaluoEnsamblador.escribirDTO(avaluoHipotecario));
		}
		return avaluoHipotecarioDTOs;
	}

	@Transactional(readOnly = true)
	@Override
	public AvaluoHipotecarioDTO encontrarPorId(Long id) {
		return avaluoEnsamblador.escribirDTO(avaluoHipotecarioRepository.findOne(id));
	}

	@Transactional(rollbackFor = AvaluoNotFoundException.class)
	@Override
	public AvaluoHipotecarioDTO actualizar(AvaluoHipotecarioDTO actualizado)
			throws AvaluoNotFoundException {
		log.debug("Actualizando avaluo  {}",
                actualizado);
        AvaluoHipotecario avaluoHipotecario = avaluoHipotecarioRepository.findOne(actualizado.getId());
        if (avaluoHipotecario == null) {
            throw new AvaluoNotFoundException();
        }
		return actualizar(avaluoHipotecario, actualizado);
	}

    @Override
    @Transactional(rollbackFor = AvaluoNotFoundException.class)
    public AvaluoHipotecarioDTO actualizar(AvaluoHipotecario avaluoHipotecario, AvaluoHipotecarioDTO actualizado)
            throws AvaluoNotFoundException {
        log.debug("Actualizando avaluo  {}",
                actualizado);
        try {
			avaluoEnsamblador.actualizarAvaluoHipotecario(avaluoHipotecario, actualizado);
		} catch (FormatoInformeNotFoundException e) {
            log.error("FormatoInformeNotFoundException: No se encontro el formato:  {}",
                    actualizado);
			return null;
		} catch (EntidadNotFoundException e) {
            log.error("EntidadNotFoundException: No se encontro la entidad en el avaluo:  {}",
                    actualizado);
        } catch (ClienteNotFoundException e) {
            log.error("ClienteNotFoundException: No se encontro el cliente en el avaluo:  {}",
                    actualizado);
        } catch (UsuarioNotFoundException e) {
            log.error("UsuarioNotFoundException: No se encontro el Usuario en el avaluo:  {}",
                    actualizado);
        } catch (TipoAvaluoNotFoundException e) {
            log.error("TipoAvaluoNotFoundException: No se encontro el tipo de avaluo:  {}",
                    actualizado);
        } catch (DivipolaNotFoundException e) {
            log.error("DivipolaNotFoundException: No se encontro el divipola de:  {}",
                    actualizado);
        }
		return actualizado;
    }

    @Transactional(readOnly = true)
	@Override
	public AvaluoHipotecarioDTO encontrarAvaluoConAreas(Long avaluoId) {
		return avaluoEnsamblador.escribirDTO(avaluoHipotecarioRepository.encontrarAvaluoConAreas(avaluoId));
	}

    @Transactional(readOnly = true)
	@Override
	public List<AvaluoHipotecarioDTO> encontrarPorFechaTerminacion(
			Date fechaInicial, Date fechaFinal) {
		List<AvaluoHipotecarioDTO> avaluoHipotecarioDTOs = new ArrayList<AvaluoHipotecarioDTO>();
		for (AvaluoHipotecario avaluoHipotecario:avaluoHipotecarioRepository.encontrarPorFechaTerminacion(fechaInicial, fechaFinal)) {
			avaluoHipotecarioDTOs.add(avaluoEnsamblador.escribirDTO(avaluoHipotecario));
		}
		return avaluoHipotecarioDTOs;
	}
	/**
	 * Búsca solo avalúos hipotecarios en las fechas establecidas.
	 * 
	 * */
    @Transactional(readOnly = true)
    @Override
	public List<AvaluoHipotecarioDTO> encontrarPorFechaTerminacionHipotecarios(EntidadDTO entidadSeleccionada, Date fechaInicial, Date fechaFinal) {
		List<AvaluoHipotecarioDTO> avaluoHipotecarioDTOs = new ArrayList<>();
		List<AvaluoHipotecario> avaluos = avaluoHipotecarioRepository.encontrarPorFechaTerminacionHipotecarios(entidadSeleccionada.getNombre(), fechaInicial, fechaFinal);
		if (avaluos != null) {
			for (AvaluoHipotecario avaluoHipotecario: avaluos) {
				avaluoHipotecarioDTOs.add(avaluoEnsamblador.escribirDTO(avaluoHipotecario));
			}
		}
		return avaluoHipotecarioDTOs;
	}

}
