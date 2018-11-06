package com.helio4.bancol.avaluos.modelo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("7")
public class EstadoPendientePago extends EstadoAvaluo {
	
	@Transient
	private String justificacion;
	
	public EstadoPendientePago() {
		super();
	}
	
	public EstadoPendientePago(Avaluo avaluo, Usuario usuario) {
		super(avaluo, usuario);
	}
	
	public EstadoPendientePago(EstadoAvaluo estadoAvaluo, Usuario usuario) {
		super(estadoAvaluo, usuario);
	}

	@Override
	public void solicitarDevolucion(String justificacion, Usuario usuario) {
		this.justificacion = justificacion;
		getAvaluo().setEstado(pasarEstado(usuario));
	}
	
	@Override
	public void notificarHonorarios(Usuario usuario) {
	}
	
	@Override
	public void confirmarPago(Usuario usuario) {
		getAvaluo().setEstado(pasarEstado(usuario));
	}
	
	@Override
	public EstadoAvaluo pasarEstado(Usuario usuario) {
		EstadoAvaluo estadoPorRevisar;
		if (justificacion == null) {
            /*
             * Se envia uno de cada diez avaluos al comité
             */
			estadoPorRevisar = (getAvaluo().getId() % 10l) == 0 ? new EstadoPorRevisarComite(this, usuario) : new EstadoEnviado(this, usuario);
		}else{
			estadoPorRevisar = new EstadoPorAprobarDevolucion(this, usuario);
			estadoPorRevisar.setJustificacionRechazo(justificacion);
		}
		return estadoPorRevisar;
	}

}
