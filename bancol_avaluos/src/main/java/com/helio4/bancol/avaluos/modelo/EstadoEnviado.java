package com.helio4.bancol.avaluos.modelo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("8")
public class EstadoEnviado extends EstadoAvaluo {
	
	public EstadoEnviado() {
		super();
	}
	
	@Transient
	private boolean enviarAComite;
	
	@Transient
	private String correcciones;
	
	public EstadoEnviado(Avaluo avaluo, Usuario usuario) {
		super(avaluo, usuario);
	}
	
	public EstadoEnviado(EstadoAvaluo estadoAvaluo, Usuario usuario) {
		super(estadoAvaluo, usuario);
	}
	
	@Override
	public void solicitarCorreciones(String correcciones, Usuario usuario) {
		this.correcciones = correcciones;
		getAvaluo().setEstado(pasarEstado(usuario));
	}
	
	@Override
	public void enviarAComite(Usuario usuario) {
		enviarAComite = true;
		getAvaluo().setEstado(pasarEstado(usuario));
	}
	
	@Override
	public void aprobar(Usuario usuario) {
		getAvaluo().setEstado(pasarEstado(usuario));
	}
	
	@Override
	public EstadoAvaluo pasarEstado(Usuario usuario) {
		EstadoAvaluo estadoAvaluo;
		if (enviarAComite){
			estadoAvaluo = new EstadoPorRevisarComite(this, usuario);
		}else if (correcciones != null) {
			estadoAvaluo = new EstadoCorreciones(this, usuario);
			estadoAvaluo.setJustificacionRechazo(correcciones);
		}else{
			estadoAvaluo = new EstadoAprobado(this, usuario);
		}
		return estadoAvaluo;
	}

}
