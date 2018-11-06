package com.helio4.bancol.avaluos.modelo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("10")
public class EstadoPorRevisarComite extends EstadoAvaluo {
	
	@Transient
	private String correcciones;
	
	public EstadoPorRevisarComite() {
		super();
	}
	
	public EstadoPorRevisarComite(Avaluo avaluo, Usuario usuario) {
		super(avaluo, usuario);
	}
	
	public EstadoPorRevisarComite(EstadoAvaluo estadoAvaluo, Usuario usuario) {
		super(estadoAvaluo, usuario);
	}
	
	@Override
	public void solicitarCorreciones(String correciones, Usuario usuario) {
		this.correcciones = correciones;
		getAvaluo().setEstado(pasarEstado(usuario));
	}
	
	@Override
	public void aprobar(Usuario usuario) {
		getAvaluo().setEstado(pasarEstado(usuario));
	}
	
	@Override
	public EstadoAvaluo pasarEstado(Usuario usuario) {
		EstadoAvaluo estadoAvaluo;
		if (correcciones != null) {
			estadoAvaluo = new EstadoCorreciones(this, usuario);
			estadoAvaluo.setJustificacionRechazo(correcciones);
		}else{
			estadoAvaluo = new EstadoAprobado(this, usuario);
		}
		return estadoAvaluo;
	}

}
