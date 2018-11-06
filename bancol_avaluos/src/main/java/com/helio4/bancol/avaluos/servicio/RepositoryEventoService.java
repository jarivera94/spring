package com.helio4.bancol.avaluos.servicio;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.helio4.bancol.avaluos.servicio.datos.EventoService;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.EventoNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.UsuarioNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.AvaluoDTO;
import com.helio4.bancol.avaluos.dto.EventoDTO;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;
import com.helio4.bancol.avaluos.ensamblador.EventoEnsamblador;
import com.helio4.bancol.avaluos.ensamblador.UsuarioEnsamblador;
import com.helio4.bancol.avaluos.modelo.Evento;
import com.helio4.bancol.avaluos.persistencia.EventoRepository;

@Component(value="repositoryEventoService")
@Transactional(readOnly = true)
public class RepositoryEventoService implements EventoService {

	private static Logger log = LoggerFactory.getLogger( RepositoryEventoService.class );

	@Autowired
	private EventoRepository eventoRepository;

	@Autowired
	private EventoEnsamblador eventoEnsamblador;

	@Autowired
	private UsuarioEnsamblador usuarioEnsamblador;


	@Transactional
	@Override
	public EventoDTO crear(EventoDTO eventoDTO) {
        log.debug("Creando un nuevo detalle de evento {}",
                eventoDTO);
		Evento evento;
		try {
			evento = eventoEnsamblador.crearEvento(eventoDTO);
		} catch (AvaluoNotFoundException e) {
            log.debug("AvaluoNotFoundException: no se encontro el avaluo del evento:  {}",
                    eventoDTO);
			return null;
		} catch (UsuarioNotFoundException e) {
            log.debug("UsuarioNotFoundException: no se encontro el perito del evento:  {}",
                    eventoDTO);
			return null;
		}
		evento = eventoRepository.save(evento);
		eventoDTO.setId(evento.getId());
		return eventoDTO;
	}

	@Transactional(rollbackFor = EventoNotFoundException.class)
	@Override
	public EventoDTO eliminar(Long eventoId) throws EventoNotFoundException {
        log.debug("Eliminando el evento con id:  {}",
                eventoId);
		Evento evento = eventoRepository.findOne(eventoId);
		if (evento == null) {
			throw new EventoNotFoundException();
		}
		eventoRepository.delete(evento);
		return eventoEnsamblador.escribirDTO(evento);
	}

	@Transactional(readOnly = true)
	@Override
	public List<EventoDTO> encontrarTodos() {
		log.debug("Encontrando todas las eventos");
		return eventoEnsamblador.escribirListaDTO(eventoRepository.findAll());
	}

	@Transactional(readOnly = true)
	@Override
	public EventoDTO encontrarPorId(Long id) {
		return eventoEnsamblador.escribirDTO(eventoRepository.findOne(id));
	}

	@Transactional(rollbackFor = EventoNotFoundException.class)
	@Override
	public EventoDTO actualizar(EventoDTO actualizado)
			throws EventoNotFoundException {
		try {
			eventoEnsamblador.actualizarEvento(actualizado);
		} catch (AvaluoNotFoundException e) {
            log.debug("AvaluoNotFoundException: no se encontro el avaluo del evento:  {}",
                    actualizado);
			return null;
		} catch (UsuarioNotFoundException e) {
            log.debug("UsuarioNotFoundException: no se encontro el perito del evento:  {}",
                    actualizado);
			return null;
		}
		return actualizado;
	}

	@Transactional(readOnly = true)
	@Override
	public List<UsuarioDTO> buscarUsuariosEventosCercanos(Date fechaInicio,
			Date fechaFinal) {
		return usuarioEnsamblador.escribirListaDTO(eventoRepository.buscarUsuariosEventosCercanos(fechaInicio, fechaFinal));
	}

	@Transactional(readOnly = true)
	@Override
	public List<EventoDTO> encontrarEventosUsuario(UsuarioDTO usuarioDTO, AvaluoDTO avaluo) {
		return eventoRepository.encontrarEventosUsuario( usuarioDTO.getNumeroDocumento(),
		                    usuarioDTO.getTipoDocumentoIdentificacion(), avaluo.getId());
	}

	@Transactional(readOnly = true)
	@Override
    public List<EventoDTO> encontrarEventosUsuario(UsuarioDTO usuarioDTO) {
        return eventoRepository.encontrarEventosUsuario(
                            usuarioDTO.getTipoDocumentoIdentificacion(),
                            usuarioDTO.getNumeroDocumento());
    }

	@Transactional(readOnly = true)
	@Override
	public List<EventoDTO> encontrarEventosUsuarios(List<UsuarioDTO> usuarios) {
		List<String> documentos = new ArrayList<String>();
		for(UsuarioDTO usuarioDTO: usuarios){
			documentos.add(usuarioDTO.getTipoDocumentoIdentificacion().toString()+"-"+usuarioDTO.getNumeroDocumento().toString());
		}

		List<EventoDTO> eventos = null;
		List<Object> lista = eventoRepository.encontrarEventosUsuarios(documentos);

		eventos = extraerDTOs(lista);
		return eventos;
	}

	private List<EventoDTO> extraerDTOs(List<Object> lista){
		List<EventoDTO> eventos = new ArrayList<EventoDTO>();
		for(Object object: lista){
			Object[] objects = (Object[])object;

			Integer id = (Integer)objects[0];
			Timestamp fechaInicio = (Timestamp)objects[1];
			Timestamp fechaFin = (Timestamp)objects[2];
			String descripcion = (String)objects[3];
			Integer tipoDocumento = (Integer)objects[4];
			BigInteger numeroDocumentoBigInteger = (BigInteger)objects[5];
			Long numeroDocumento = numeroDocumentoBigInteger.longValue();
			String nombres = (String)objects[6];
			String apellidos = (String)objects[7];
			Integer idAvaluo = (Integer)objects[8];
			String codigoExterno = (String)objects[9];

			EventoDTO eventoDTO = new EventoDTO();
			eventoDTO.setId(new Long(id));
			eventoDTO.setFechaHoraFin(new Date(fechaInicio.getTime()));
			eventoDTO.setFechaHoraInicio(new Date(fechaFin.getTime()));
			eventoDTO.setDescripcion(descripcion);
			eventoDTO.setTipoDocumentoIdentificacionPerito(tipoDocumento);
			eventoDTO.setNumeroDocumentoPerito(numeroDocumento);
			eventoDTO.setNombresPerito(nombres);
			eventoDTO.setApellidosPerito(apellidos);
			eventoDTO.setAvaluoId(new Long(idAvaluo));
			eventoDTO.setCodigoExterno(codigoExterno);

			eventos.add(eventoDTO);
		}
		return eventos;
	}
	
	@Transactional(readOnly = true)
    @Override
    public int contarEventosPorAvaluo(Long avaluoId,
            Integer tipoDocumentoPerito, Long numeroDocumentoPerito) {
        return eventoRepository.contarEventosDelCicloActualEstadosPorAvaluo(avaluoId,
                tipoDocumentoPerito, numeroDocumentoPerito);
    }

    @Transactional(readOnly = true)
    @Override
    public EventoDTO encontrarCitaReprogramar(Long avaluoId,
            Integer tipoDocumentoPerito, Long numeroDocumentoPerito) {
 		List<EventoDTO> eventos = eventoRepository
            .encontrarCitasAReprogramar(avaluoId, tipoDocumentoPerito,
                    numeroDocumentoPerito);
        return eventos.isEmpty() ? null : eventos.get(0);
    }

    @Transactional(readOnly = true)
    @Override
    public EventoDTO encontrarUltimoEventoAvaluo(Long avaluoId) {
        List<EventoDTO> eventos = eventoRepository
            .encontrarUltimoEventoAvaluo(avaluoId);
        return eventos.isEmpty() ? null : eventos.get(0);
    }

}
