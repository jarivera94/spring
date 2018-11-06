package com.helio4.bancol.avaluos.dominio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.ScheduleEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.AvaluoDTO;
import com.helio4.bancol.avaluos.dto.EventoDTO;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;
import com.helio4.bancol.avaluos.servicio.datos.AvaluoService;
import com.helio4.bancol.avaluos.servicio.datos.EstadoAvaluoService;
import com.helio4.bancol.avaluos.servicio.datos.EventoService;

@Component
public class ProgramarCitaController {

	@Autowired
	@Qualifier("repositoryEventoService")
	private EventoService eventoService;

	@Autowired
	@Qualifier("repositoryEstadoAvaluoService")
	private EstadoAvaluoService estadoAvaluoService;

	@Autowired
	@Qualifier("repositoryAvaluoService")
	private AvaluoService avaluoService;

	public boolean programarCita
			(AvaluoDTO avaluo, Date horaInicio, Date horaFin,
			 UsuarioDTO perito, UsuarioDTO usuario) {
		return avaluoService
				.programarCita(avaluo, horaInicio, horaFin, "", usuario);
	}

    public boolean reProgramarCita
			(AvaluoDTO avaluo, EventoDTO evento, UsuarioDTO usuario) {
		return avaluoService.reProgramarCita(avaluo,"", evento, usuario);
	}

    public List<ScheduleEvent> eventosPerito(UsuarioDTO perito) {
		List<ScheduleEvent> events = new ArrayList<ScheduleEvent>();
		if (perito.getEventos() != null) {
			for (EventoDTO evento : perito.getEventos()) {
				String descripcion = "Codigo Externo: " + evento.getCodigoExterno()
						+ "Perito: " + evento.getNombresPerito()
						+ " " + evento.getApellidosPerito() + " "
						+ " Descripción: " + evento.getDescripcion();
				events.add(new DefaultScheduleEvent(descripcion, evento
						.getFechaHoraInicio(), evento.getFechaHoraFin()));
			}
		}
		return events;
	}

    
    
	public List<ScheduleEvent> encontrarEventosUsuario(UsuarioDTO perito, AvaluoDTO avaluo) {
		List<ScheduleEvent> events = new ArrayList<ScheduleEvent>();
		List<EventoDTO> eventos = eventoService.encontrarEventosUsuario(perito, avaluo);

		if (eventos != null) {
			for (EventoDTO evento : eventos) {
				String descripcion = " Codigo Externo: " + evento.getCodigoExterno()
						+ " Perito: " + evento.getNombresPerito()
						+ " " + evento.getApellidosPerito() + " "
						+ " Descripción: " + evento.getDescripcion();

				ScheduleEvent event = new DefaultScheduleEvent(descripcion, evento.getFechaHoraInicio(), evento.getFechaHoraFin());
				event.setId(evento.getId().toString());
				events.add(event);
			}
		}
		return events;
	}

	public int contarEventosPorAvaluo(AvaluoDTO avaluoDTO) {
	    return eventoService.contarEventosPorAvaluo(avaluoDTO.getId(),
                avaluoDTO.getPerito().getTipoDocumentoIdentificacion(),
                avaluoDTO.getPerito().getNumeroDocumento());
	}

    public EventoDTO encontrarCitaReprogramar(AvaluoDTO avaluoDTO) {
        return eventoService.encontrarCitaReprogramar(avaluoDTO.getId(),
                avaluoDTO.getPerito().getTipoDocumentoIdentificacion(),
                avaluoDTO.getPerito().getNumeroDocumento());
    }

    public EventoDTO encontrarUltimoEventoAvaluo(AvaluoDTO avaluoDTO) {
        return eventoService.encontrarUltimoEventoAvaluo(avaluoDTO.getId());
    }

    public List<ScheduleEvent> encontrarEventosUsuario(UsuarioDTO usuarioActual) {
        List<ScheduleEvent> events = new ArrayList<ScheduleEvent>();
        List<EventoDTO> eventos = eventoService.encontrarEventosUsuario(usuarioActual);

        if (eventos != null) {
            for (EventoDTO evento : eventos) {
                String descripcion = "Codigo Externo: " + evento.getCodigoExterno()
                		+ "Perito: " + evento.getNombresPerito()
                        + " " + evento.getApellidosPerito() + " "
                        + " Descripción: " + evento.getDescripcion();

                ScheduleEvent event = new DefaultScheduleEvent(descripcion, evento.getFechaHoraInicio(), evento.getFechaHoraFin());
                event.setId(evento.getId().toString());
                events.add(event);
            }
        }
        return events;

    }

}
