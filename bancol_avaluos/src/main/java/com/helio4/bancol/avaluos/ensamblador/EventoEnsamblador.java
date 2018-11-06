package com.helio4.bancol.avaluos.ensamblador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.EventoDTO;
import com.helio4.bancol.avaluos.modelo.Avaluo;
import com.helio4.bancol.avaluos.modelo.Evento;
import com.helio4.bancol.avaluos.modelo.Usuario;
import com.helio4.bancol.avaluos.modelo.Usuario.DocumentoIdentificacion;
import com.helio4.bancol.avaluos.persistencia.AvaluoRepository;
import com.helio4.bancol.avaluos.persistencia.EventoRepository;
import com.helio4.bancol.avaluos.persistencia.UsuarioRepository;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.EventoNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.UsuarioNotFoundException;

@Component
public class EventoEnsamblador {

	@Autowired
	private EventoRepository eventoRepository;

	@Autowired
	private AvaluoRepository avaluoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioEnsamblador usuarioEnsamblador;

	public Evento crearEvento(EventoDTO eventoDTO) throws AvaluoNotFoundException, UsuarioNotFoundException {
		Evento evento = new Evento();
		evento.setId(eventoDTO.getId());
		evento.setFechaHoraInicio(eventoDTO.getFechaHoraInicio());
		evento.setFechaHoraFin(eventoDTO.getFechaHoraFin());
		evento.setDescripcion(eventoDTO.getDescripcion());
		Usuario usuario = usuarioRepository.findOne(new DocumentoIdentificacion(eventoDTO.getTipoDocumentoIdentificacionPerito(), eventoDTO.getNumeroDocumentoPerito()));
		if (usuario == null) {
			throw new UsuarioNotFoundException();
		}
		evento.setUsuario(usuario);
		Avaluo avaluo = avaluoRepository.findOne(eventoDTO.getAvaluoId());
		if (avaluo == null) {
			throw new AvaluoNotFoundException();
		}
		evento.setAvaluo(avaluo);
		return evento;
	}

	public void actualizarEvento(EventoDTO eventoDTO) throws EventoNotFoundException, AvaluoNotFoundException, UsuarioNotFoundException {
		Evento evento = eventoRepository.findOne(eventoDTO.getId());
		if (evento == null) {
			throw new EventoNotFoundException();
		}
		evento.setFechaHoraInicio(eventoDTO.getFechaHoraInicio());
		evento.setFechaHoraFin(eventoDTO.getFechaHoraFin());
		evento.setDescripcion(eventoDTO.getDescripcion());
		Usuario usuario = usuarioRepository.findOne(new DocumentoIdentificacion(eventoDTO.getTipoDocumentoIdentificacionPerito(), eventoDTO.getNumeroDocumentoPerito()));
		if (usuario == null) {
			throw new UsuarioNotFoundException();
		}
		evento.setUsuario(usuario);
		Avaluo avaluo = avaluoRepository.findOne(eventoDTO.getAvaluoId());
		if (avaluo == null) {
			throw new AvaluoNotFoundException();
		}
		evento.setAvaluo(avaluo);
	}

	public EventoDTO escribirDTO(Evento evento) {

		EventoDTO eventoDTO = new EventoDTO();
		eventoDTO.setId(evento.getId());
		eventoDTO.setFechaHoraInicio(evento.getFechaHoraInicio());
		eventoDTO.setFechaHoraFin(evento.getFechaHoraFin());
		eventoDTO.setDescripcion(evento.getDescripcion());
		if (evento.getUsuario() != null) {
            eventoDTO.setTipoDocumentoIdentificacionPerito(evento.getUsuario()
                    .getTipoDocumentoIdentificacion());
            eventoDTO.setNumeroDocumentoPerito(evento.getUsuario()
                    .getNumeroDocumento());
            eventoDTO.setNombresPerito(evento.getUsuario().getNombres());
            eventoDTO.setApellidosPerito(evento.getUsuario().getApellidos());
            eventoDTO.setEmailPerito(evento.getUsuario().getEmail());
        }
        if (evento.getAvaluo() != null) {
            eventoDTO.setAvaluoId(evento.getAvaluo().getId());
            eventoDTO.setCodigoExterno(evento.getAvaluo().getCodigoExterno());
        }
        return eventoDTO;
	}

	public List<EventoDTO> escribirListaDTO(List<Evento> eventos) {
		List<EventoDTO> eventoDTOs = new ArrayList<EventoDTO>();
		for (Evento evento:eventos) {
			eventoDTOs.add(escribirDTO(evento));
		}
		return eventoDTOs;
	}

}
