package com.helio4.bancol.avaluos.dominio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.ScheduleEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.AvaluoDTO;
import com.helio4.bancol.avaluos.dto.DivipolaDTO;
import com.helio4.bancol.avaluos.dto.EventoDTO;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;
import com.helio4.bancol.avaluos.servicio.datos.AvaluoService;
import com.helio4.bancol.avaluos.servicio.excepciones.DivipolaNotFoundException;
import com.helio4.bancol.avaluos.servicio.datos.DivipolaService;
import com.helio4.bancol.avaluos.servicio.datos.EstadoAvaluoService;
import com.helio4.bancol.avaluos.servicio.datos.EventoService;
import com.helio4.bancol.avaluos.servicio.datos.UsuarioService;

@Component
public class AsignarPeritoController {

	@Autowired
	@Qualifier("repositoryUsuarioService")
	private UsuarioService usuarioService;

	@Autowired
	@Qualifier("repositoryAvaluoService")
	private AvaluoService avaluoService;

	@Autowired
	@Qualifier("repositoryEstadoAvaluoService")
	private EstadoAvaluoService estadoAvaluoService;

	@Autowired
	@Qualifier("repositoryEventoService")
	private EventoService eventoService;

    @Autowired
    @Qualifier("repositoryDivipolaService")
    private DivipolaService divipolaService;
    
    
	public List<UsuarioDTO> buscarPeritos(DivipolaDTO divipolaBusqueda, AvaluoDTO avaluo) {
		if (divipolaBusqueda == null) {
			divipolaBusqueda = avaluo.getDivipola();
		}
		List<UsuarioDTO> resultado = usuarioService
				.buscarPeritosDisponibles(divipolaBusqueda.getId());
		if (resultado.isEmpty()) {
            Long regionalId = divipolaService.cargarRegionalId(divipolaBusqueda.getId());
			resultado = usuarioService.buscarPeritosDisponibles(regionalId);
		}
		return resultado;
	}
	/**
	 * Busca todos los peritos que esten activos y disponibles por asignacion
	 * */
	public List<UsuarioDTO> buscarPeritosDisponibles() { 
		List<UsuarioDTO> usuarios = new ArrayList<UsuarioDTO>();
		usuarios.addAll(this.usuarioService.buscarPeritosDisponibles());
		return usuarios;
	}
	public List<UsuarioDTO> buscarPeritosDireccion(DivipolaDTO divipolaBusqueda, int cercaniaCuadrasRedonda, AvaluoDTO avaluo) {
		if (divipolaBusqueda == null) {
			divipolaBusqueda = avaluo.getDivipola();
		}
		if (cercaniaCuadrasRedonda > 0) {
			int cuadrasViaAdelante = Integer.parseInt(avaluo.getNumeroVia()) + cercaniaCuadrasRedonda;
			int cuadrasViaAtras = Integer.parseInt(avaluo.getNumeroVia()) - cercaniaCuadrasRedonda;
			int cuadrasViaGenAdelante = Integer.parseInt(avaluo.getNumeroViaGeneradora()) + cercaniaCuadrasRedonda;
			int cuadrasViaGenAtras = Integer.parseInt(avaluo.getNumeroViaGeneradora()) - cercaniaCuadrasRedonda;
			return usuarioService.buscarPeritosDisponibles(divipolaBusqueda.getId(), avaluo.getTipoVia(), cuadrasViaAdelante, cuadrasViaAtras, cuadrasViaGenAdelante, cuadrasViaGenAtras);
		}
		return new ArrayList<UsuarioDTO>();
	}

	public List<UsuarioDTO> buscarPeritosDireccionCita(DivipolaDTO divipolaBusqueda, int cercaniaCuadrasRedonda, int diasProximaCita, AvaluoDTO avaluo) {
		if (divipolaBusqueda == null) {
			divipolaBusqueda = avaluo.getDivipola();
		}
		if (diasProximaCita > 0 && cercaniaCuadrasRedonda > 0) {
			int cuadrasViaAdelante = Integer.parseInt(avaluo.getNumeroVia()) + cercaniaCuadrasRedonda;
			int cuadrasViaAtras = Integer.parseInt(avaluo.getNumeroVia()) - cercaniaCuadrasRedonda;
			int cuadrasViaGenAdelante = Integer.parseInt(avaluo.getNumeroViaGeneradora()) + cercaniaCuadrasRedonda;
			int cuadrasViaGenAtras = Integer.parseInt(avaluo.getNumeroViaGeneradora()) - cercaniaCuadrasRedonda;
			return usuarioService.buscarPeritosDisponibles(divipolaBusqueda.getId(), avaluo.getTipoVia(), cuadrasViaAdelante, cuadrasViaAtras, cuadrasViaGenAdelante, cuadrasViaGenAtras, diasProximaCita);
		}
		return new ArrayList<UsuarioDTO>();
	}

	public List<UsuarioDTO> buscarPeritosDias(DivipolaDTO divipolaBusqueda, int cercaniaCuadrasRedonda, int diasProximaCita, AvaluoDTO avaluo){
		divipolaBusqueda = ((divipolaBusqueda == null) ? avaluo.getDivipola() :divipolaBusqueda  );
		return this.usuarioService.buscarPeritosDisponiblesDias(divipolaBusqueda.getId(),  avaluo.getTipoVia(), diasProximaCita);
	}

	public List<ScheduleEvent> eventosPeritos(List<UsuarioDTO> peritos) {
		List<ScheduleEvent> events = new ArrayList<ScheduleEvent>();
		List<EventoDTO> eventos = new ArrayList<EventoDTO>();

		eventos = eventoService.encontrarEventosUsuarios(peritos);

		int color = 0;

		if (eventos != null && !eventos.isEmpty()) {
			Long documento = null;
			for (EventoDTO evento : eventos) {
				String descripcion = "Codigo Externo: " + evento.getCodigoExterno()
						+" Perito: " + evento.getNombresPerito()
						+ " " + evento.getApellidosPerito() + " "
						+ " Descripción: " + evento.getDescripcion();
				DefaultScheduleEvent event = new DefaultScheduleEvent(descripcion, evento
						.getFechaHoraInicio(), evento.getFechaHoraFin());

				if(documento!=null && !evento.getNumeroDocumentoPerito().equals(documento)){
					if(color>9){
						color = 0;
					}else{
						color++;
					}
				}

				event.setStyleClass("color_"+color);
				events.add(event);

				documento = evento.getNumeroDocumentoPerito();
			}
		}

		return events;
	}

	public boolean asignarPerito(AvaluoDTO avaluo, UsuarioDTO perito, UsuarioDTO usuario) {
		avaluoService.asignarPerito(avaluo, perito, usuario);
		return true;
	}
	
	public boolean actualizarPerito(AvaluoDTO avaluo, UsuarioDTO perito, UsuarioDTO usuario) {
		avaluoService.actualizarPerito(avaluo, perito, usuario);
		return true;
	}

    public int casosAbiertos(UsuarioDTO usuarioDTO) {
        return avaluoService.encontrarCasosAbiertos(
                usuarioDTO.getTipoDocumentoIdentificacion(),
                usuarioDTO.getNumeroDocumento());
    }

	public int casosAsignadosSemana(UsuarioDTO usuarioDTO) {
		return avaluoService.encontrarCasosAbiertosSemana(
                usuarioDTO.getTipoDocumentoIdentificacion(),
                usuarioDTO.getNumeroDocumento());
	}

	public int casosAsignadosMes(UsuarioDTO usuarioDTO) {
		return avaluoService.encontrarCasosAbiertosMes(
                usuarioDTO.getTipoDocumentoIdentificacion(),
                usuarioDTO.getNumeroDocumento());
	}

	public boolean estaEnSemana(Date fecha) {
		Calendar principioSemana = Calendar.getInstance();
		principioSemana.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		principioSemana.set(Calendar.HOUR_OF_DAY, 0);
		principioSemana.set(Calendar.MINUTE, 0);
		principioSemana.set(Calendar.SECOND, 0);
		Calendar finSemana = (Calendar) principioSemana.clone();
		finSemana.add(Calendar.DAY_OF_WEEK, 7);
		return fecha.after(principioSemana.getTime()) && fecha.before(finSemana.getTime());
	}

	public boolean estaEnMes(Date fecha) {
		Calendar principioMes = Calendar.getInstance();
		principioMes.set(Calendar.DAY_OF_MONTH, 1);
		principioMes.set(Calendar.HOUR_OF_DAY, 0);
		principioMes.set(Calendar.MINUTE, 0);
		principioMes.set(Calendar.SECOND, 0);
		Calendar finMes = (Calendar) principioMes.clone();
		finMes.set(Calendar.DATE, principioMes.getActualMaximum(Calendar.DAY_OF_MONTH));
		return fecha.after(principioMes.getTime()) && fecha.before(finMes.getTime());
	}
	public List<UsuarioDTO> buscarPeritosDisponibles(DivipolaDTO divipolaBusqueda) throws DivipolaNotFoundException {
		return this.usuarioService.buscarPeritosDisponibles(divipolaBusqueda);
		
	}
	public List<UsuarioDTO>  buscarPeritosDisponibles(String departamentoBusqueda) {
		return this.usuarioService.buscarPeritosDisponiblesDepartamento(departamentoBusqueda);
		
	}
	public List<UsuarioDTO> buscarPeritosDisponiblesNombre(String peritoABuscar) {
		return this.usuarioService.buscarDisponbilesNombre(peritoABuscar);
	}
}
