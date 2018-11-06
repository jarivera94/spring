package com.helio4.bancol.avaluos.modelo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("4")
public class EstadoCitaProgramada extends EstadoAvaluo {
	
	@Transient
	private String justificacion;
	
	public EstadoCitaProgramada() {
		super();
	}
	
	public EstadoCitaProgramada(Avaluo avaluo, Usuario usuario) {
		super(avaluo, usuario);
	}
	
	public EstadoCitaProgramada(EstadoAvaluo estadoAvaluo, Usuario usuario) {
		super(estadoAvaluo, usuario);
	}
	
	@Override
	public void solicitarDevolucion(String justificacion, Usuario usuario) {
		this.justificacion = justificacion;
		getAvaluo().setEstado(pasarEstado(usuario));
	}
	
	@Override
	public void confirmarVisita(Usuario usuario) {
		getAvaluo().setEstado(pasarEstado(usuario));
	}
	
	@Override
	public EstadoAvaluo pasarEstado(Usuario usuario) {
		EstadoAvaluo estadoEnProceso;
		if (justificacion == null) {
			estadoEnProceso = new EstadoPendienteDocumentos(this, usuario);
		}else{
			estadoEnProceso =  new EstadoPorAprobarDevolucion(this, usuario);
			estadoEnProceso.setJustificacionRechazo(justificacion);
		}
		return estadoEnProceso;
	}

}
