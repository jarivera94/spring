package com.helio4.bancol.avaluos.modelo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("5")
public class EstadoPendienteDocumentos extends EstadoAvaluo {
	
	@Transient
	private String justificacion;
	
	public EstadoPendienteDocumentos() {
		super();
	}
	
	public EstadoPendienteDocumentos(Avaluo avaluo, Usuario usuario) {
		super(avaluo, usuario);
	}
	
	public EstadoPendienteDocumentos(EstadoAvaluo estadoAvaluo, Usuario usuario) {
		super(estadoAvaluo, usuario);
	}
	
	@Override
	public void solicitarDevolucion(String justificacion, Usuario usuario) {
		this.justificacion = justificacion;
		getAvaluo().setEstado(pasarEstado(usuario));
	}
	
	@Override
	public void confirmarDocumentosCompletos(Usuario usuario) {
		getAvaluo().setEstado(pasarEstado(usuario));
	}
	
	@Override
	public EstadoAvaluo pasarEstado(Usuario usuario) {
		EstadoAvaluo estadoPorRevisar;
		if (justificacion == null) {
			estadoPorRevisar = new EstadoEnProceso(this, usuario);
		}else{
			estadoPorRevisar = new EstadoPorAprobarDevolucion(this, usuario);
			estadoPorRevisar.setJustificacionRechazo(justificacion);
		}
		return estadoPorRevisar;
	}

}
