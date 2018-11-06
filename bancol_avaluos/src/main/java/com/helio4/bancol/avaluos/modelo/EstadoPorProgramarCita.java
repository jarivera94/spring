package com.helio4.bancol.avaluos.modelo;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.helio4.bancol.avaluos.dto.EventoDTO;

@Entity
@DiscriminatorValue("3")
public class EstadoPorProgramarCita extends EstadoAvaluo {
	
	@Transient
	private String justificacion;
	
	public EstadoPorProgramarCita() {
		super();
	}
	
	public EstadoPorProgramarCita(Avaluo avaluo, Usuario usuario) {
		super(avaluo, usuario);
	}
	
	public EstadoPorProgramarCita(EstadoAvaluo estadoAvaluo, Usuario usuario) {
		super(estadoAvaluo, usuario);
	}
	
	@Override
	public void solicitarDevolucion(String justificacion, Usuario usuario) {
		this.justificacion = justificacion;
		getAvaluo().setEstado(pasarEstado(usuario));
	}
	
	@Override
	public EventoDTO programarCita(Date horaInicio, Date horaFin,
			String nombreRecibeVisita, Usuario usuario) {
		EventoDTO eventoDTO = new EventoDTO();
		eventoDTO.setAvaluoId(getAvaluo().getId());
		eventoDTO.setDescripcion("Cita programada en: "
				+ getAvaluo().getDireccionInmueble()
				+ ", Código de caso: " + getAvaluo().getCodigoExterno()
				+ ", Con: " + nombreRecibeVisita == null ?
						getPerito().getNombres() +
						" " + getPerito().getApellidos(): nombreRecibeVisita);
		eventoDTO.setFechaHoraInicio(horaInicio);
		eventoDTO.setFechaHoraFin(horaFin);
		eventoDTO.setTipoDocumentoIdentificacionPerito(getPerito()
				.getTipoDocumentoIdentificacion());
		eventoDTO.setNumeroDocumentoPerito(getPerito()
				.getNumeroDocumento());
		getAvaluo().setEstado(pasarEstado(usuario));                                                          
		return eventoDTO;
	}
	
	@Override
	public EstadoAvaluo pasarEstado(Usuario usuario) {
		EstadoAvaluo estadoAvaluo;
		if (justificacion == null) {
			estadoAvaluo = new EstadoCitaProgramada(this, usuario);
		}else{
			estadoAvaluo = new EstadoPorAprobarDevolucion(this, usuario);
			estadoAvaluo.setJustificacionRechazo(justificacion);
		}
		return estadoAvaluo;
	}

}
