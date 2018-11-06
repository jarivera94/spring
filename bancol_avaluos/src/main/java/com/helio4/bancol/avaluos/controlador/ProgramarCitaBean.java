package com.helio4.bancol.avaluos.controlador;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import com.helio4.bancol.avaluos.dominio.AvaluoController;
import com.helio4.bancol.avaluos.dominio.ProgramarCitaController;
import com.helio4.bancol.avaluos.dto.AvaluoDTO;
import com.helio4.bancol.avaluos.dto.EventoDTO;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;

@Controller
@Scope("view")
@Qualifier("programarCitaBean")
public class ProgramarCitaBean {
	
	private static Logger log = LoggerFactory.getLogger( ProgramarCitaBean.class );
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AvaluoDTO avaluo;
    private UsuarioDTO usuarioActual;
	private String nombresApellidos;
	private Date fechaCita;
	private Date horaInicio;
	private Date horaFin;
	private EventoDTO eventoDTO;
	
	private ScheduleModel eventModel;
	private ScheduleEvent scheduleEvent;
    private boolean modoReprogramarCita = Boolean.FALSE;
	
	@Autowired
	private ProgramarCitaController programarCitaController;
	
	@Autowired
	private AvaluoController avaluoController;
	
	@Autowired
	private ListadoAvaluosBean listadoAvaluosBean;
	
	@PostConstruct
	public void init() {
		avaluo = listadoAvaluosBean.getAvaluo();
		avaluo =  listadoAvaluosBean.getAvaluo() == null ?  null : avaluoController.encontrarPorId(avaluo.getId());
		usuarioActual = (UsuarioDTO) SecurityContextHolder.getContext()
                            .getAuthentication().getPrincipal();
		
		/**
		 * Si se ingresa en un estado no permitido se muestra
		 * el calendario con la cita programada o vacio en otro caso.
		 * VER programarCita.xhtml
		 */
	    /**
         * Consultar si el avaluo ya tiene un evento programado
         * para el perito asignado actualmente
         * si es asi se debe poner este evento para editarlo
         * y poner el Bean en estado de reprogramar cita.
         */
        eventModel = new DefaultScheduleModel();
        List<ScheduleEvent> eventosUsuario = null;
        if (avaluo != null && programarCitaController.contarEventosPorAvaluo(avaluo) > 0)  {
            EventoDTO eventoActual = programarCitaController
                    .encontrarCitaReprogramar(avaluo);
            scheduleEvent = new DefaultScheduleEvent(" Cita para avaluo en dirección: "
                    + avaluo.getDireccionInmueble() + " Código de solicitud: " 
                    + avaluo.getCodigoExterno(),
                    eventoActual.getFechaHoraInicio(),
                    eventoActual.getFechaHoraFin(), eventoActual);
            ((DefaultScheduleEvent) scheduleEvent).setStyleClass("eventoActual");
            scheduleEvent.setId(eventoActual.getId().toString());
            eventModel.addEvent(scheduleEvent);
            eventosUsuario = programarCitaController.encontrarEventosUsuario(usuarioActual, avaluo);
            eventosUsuario.remove(scheduleEvent);
        }else{
        	eventosUsuario = programarCitaController.encontrarEventosUsuario(usuarioActual);
        }
        for (ScheduleEvent evento : eventosUsuario) {
        	eventModel.addEvent(evento);
        }
		fechaCita = new Date(System.currentTimeMillis());
		Calendar horaInicioCalendar = (Calendar) hoy().clone();
		horaInicio = horaInicioCalendar.getTime();
		horaInicioCalendar.set(Calendar.HOUR_OF_DAY, horaInicioCalendar.get(Calendar.HOUR_OF_DAY) + 2);
		horaFin = horaInicioCalendar.getTime();
        if(listadoAvaluosBean.isMostrarCitaReprogramada()) {
        	this.modoReprogramarCita = Boolean.TRUE;
        }

	}
	
	public Calendar hoy() {
		Calendar hoy = Calendar.getInstance();
		hoy.set(hoy.get(Calendar.YEAR),
				hoy.get(Calendar.MONTH),
				hoy.get(Calendar.DATE),
				hoy.get(Calendar.HOUR_OF_DAY) + 1,
				0, 0);
		return hoy;
	}
    
    private Date actualizarHora(Date nuevaHora) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaCita);
		Calendar hora = Calendar.getInstance();
		hora.setTime(nuevaHora);
		calendar.set(Calendar.HOUR_OF_DAY, hora.get(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, hora.get(Calendar.MINUTE));
		return calendar.getTime();
	}
	
	public void onFechaChanged() {
        EventoDTO data = null;
		if (scheduleEvent != null) {
			eventModel.deleteEvent(scheduleEvent);
            data = (EventoDTO) scheduleEvent.getData();
		}
		scheduleEvent = new DefaultScheduleEvent("Cita para avaluo en dirección: "
                + avaluo.getDireccionInmueble() + " Código de solicitud: " 
                + avaluo.getCodigoExterno(), getHoraInicio(), getHoraFin(),
                data);
		eventModel.addEvent(scheduleEvent);
	}
	
	public void onDateSelect(SelectEvent selectEvent) {
		horaInicio = (Date) selectEvent.getObject();
		
		Calendar calendario = Calendar.getInstance(); 
	    calendario.setTime(horaInicio); 
	    calendario.add(Calendar.HOUR_OF_DAY, 1);
		horaFin = calendario.getTime(); 
		fechaCita = (Date) selectEvent.getObject();
	}
	
	public void onEventMove(ScheduleEntryMoveEvent entryMoveEvent) {
        ScheduleEvent scheduleEvent = entryMoveEvent.getScheduleEvent();
		if ((modoReprogramarCita && scheduleEvent == this.scheduleEvent) || !modoReprogramarCita) {
		    horaInicio = scheduleEvent.getStartDate();
	    	horaFin = scheduleEvent.getEndDate();
		    fechaCita = scheduleEvent.getStartDate();
        }
	}
	
	public void onEventResize(ScheduleEntryResizeEvent entryResizeEvent) {
		ScheduleEvent scheduleEvent = entryResizeEvent.getScheduleEvent();
 		if ((modoReprogramarCita && scheduleEvent == this.scheduleEvent) || !modoReprogramarCita) {
    		horaInicio = scheduleEvent.getStartDate();
	    	horaFin = scheduleEvent.getEndDate();
		    fechaCita = scheduleEvent.getStartDate();
        }
	}
	
	public void onEventSelect(SelectEvent selectEvent){
        ScheduleEvent event = (ScheduleEvent) selectEvent.getObject();
	    if (modoReprogramarCita) {
	    	this.fechaCita =  event.getStartDate();
	    	this.horaInicio = event.getStartDate();
	    	this.horaFin =  event.getEndDate();
	    }
	}
	
	public void onHoraInicioSeleccionada(){
		horaFin.setTime(horaInicio.getTime()+3600000);
	}

    public void programarCita() {
        log.debug(
                "Programando cita el día: {} con fecha de finalización {}",
                horaInicio.toString(), horaFin.toString());
        if (scheduleEvent != null) {
			if (! programarCitaController.programarCita(avaluo, horaInicio, horaFin,
					usuarioActual, usuarioActual)) {
				listadoAvaluosBean.setMensajeError("No es posible " +
						"programar la visita porque la cita ya ha " +
						"sido programada");
			}else {
				avaluoController.actualizar(avaluo);
				listadoAvaluosBean.setCitaProgramada(avaluo.getCodigoExterno());
			}
        }
        redireccionarListado();
    }

    private void redireccionarListado() {
        String uri = FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .encodeActionURL(
                        FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/pages/avaluos/listadoAvaluos.xhtml");
        try {
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect(uri);
        } catch (IOException e) {
            // TODO Reireccionar a página no encontrada
            e.printStackTrace();
        }
    }

    public void reProgramarCita() {
        EventoDTO eventoDTO = null;
        if (scheduleEvent != null) {
            eventoDTO = ((EventoDTO) scheduleEvent.getData());
            if (eventoDTO!=null &&  eventoDTO.getAvaluoId().equals(avaluo.getId())) {
                eventoDTO.setFechaHoraInicio(scheduleEvent.getStartDate());
                eventoDTO.setFechaHoraFin(scheduleEvent.getEndDate());
                if (!programarCitaController.reProgramarCita(avaluo, eventoDTO,
                        (UsuarioDTO) SecurityContextHolder.getContext()
                                .getAuthentication().getPrincipal())) {
					listadoAvaluosBean.setMensajeError("No es posible reprogramar la visita" +
							"ocurrio un error.");
				}else{
					avaluoController.actualizar(avaluo);
					listadoAvaluosBean.setCitaReProgramada(avaluo
							.getCodigoExterno());
				}
                redireccionarListado();
            }
        }
       
    }

	public void cancelar(){
		redireccionarListado();
	}

	public AvaluoDTO getAvaluo() {
		return avaluo;
	}

	public void setAvaluo(AvaluoDTO avaluo) {
		this.avaluo = avaluo;
	}

	public String getNombresApellidos() {
		return nombresApellidos;
	}

	public void setNombresApellidos(String nombresApellidos) {
		this.nombresApellidos = nombresApellidos;
	}

	public Date getFechaCita() {
		return fechaCita;
	}

	public void setFechaCita(Date fechaCita) {
		this.fechaCita = fechaCita;
		this.horaInicio = actualizarHora(this.horaInicio);
		this.horaFin = actualizarHora(this.horaFin);
	}

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = actualizarHora(horaInicio);
	}

	public Date getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(Date horaFin) {
		this.horaFin = actualizarHora(horaFin);
	}

	public ScheduleModel getEventModel() {
		return eventModel;
	}

	public void setEventModel(ScheduleModel eventModel) {
		this.eventModel = eventModel;
	}
	
	public EventoDTO getEventoDTO() {
		return eventoDTO;
	}

	public void setEventoDTO(EventoDTO eventoDTO) {
		this.eventoDTO = eventoDTO;
	}
	
	
	public boolean isModoReprogramarCita() {
		return modoReprogramarCita;
	}

	public void setModoReprogramarCita(boolean modoReprogramarCita) {
		this.modoReprogramarCita = modoReprogramarCita;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
