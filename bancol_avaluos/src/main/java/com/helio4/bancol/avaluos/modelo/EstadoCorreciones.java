package com.helio4.bancol.avaluos.modelo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("9")
public class EstadoCorreciones extends EstadoAvaluo {
	
	public EstadoCorreciones() {
		super();
	}
	
	public EstadoCorreciones(Avaluo avaluo, Usuario usuario) {
		super(avaluo, usuario);
	}
	
	public EstadoCorreciones(EstadoAvaluo estadoAvaluo, Usuario usuario) {
		super(estadoAvaluo, usuario);
	}
	
	@Override
	public void enviar(Usuario usuario) {
		getAvaluo().setEstado(pasarEstado(usuario));
	}
	
	@Override
	public EstadoAvaluo pasarEstado(Usuario usuario) {
		EstadoAvaluo EstadoEnviado;
		if (obtenerEstadoAnterior().getClass().equals(EstadoPorRevisarComite.class)) {
			EstadoEnviado = new EstadoPorRevisarComite(this, usuario);
		}else{
			EstadoEnviado = new EstadoEnviado(this, usuario);
		}
		return EstadoEnviado;
	}

}
