package com.helio4.bancol.avaluos.modelo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.google.common.collect.Iterables;

@Entity
@DiscriminatorValue("12")
public class EstadoPorAprobarDevolucion extends EstadoAvaluo {

	@Transient
	private String justificacion;

	public EstadoPorAprobarDevolucion() {
		super();
	}

	public EstadoPorAprobarDevolucion(Avaluo avaluo, Usuario usuario) {
		super(avaluo, usuario);
	}

	public EstadoPorAprobarDevolucion(EstadoAvaluo estadoAvaluo, Usuario usuario) {
		super(estadoAvaluo, usuario);
	}

	@Override
	public void reactivar(Usuario usuario) {
		getAvaluo().setEstado(pasarEstado(usuario));
	}

	@Override
	public EstadoAvaluo pasarEstado(Usuario usuario) {
		EstadoAvaluo estadoAnterior = null;
		EstadoAvaluo ultimoEstado = obtenerUltimoEstado();
		if (ultimoEstado.getClass().equals(EstadoPorProgramarCita.class)) {
			estadoAnterior = new EstadoPorProgramarCita(this, usuario);
		}else if (ultimoEstado.getClass().equals(EstadoCitaProgramada.class)){
			estadoAnterior = new EstadoCitaProgramada(this, usuario);
		}else if (ultimoEstado.getClass().equals(EstadoPendienteDocumentos.class)){
			estadoAnterior = new EstadoPendienteDocumentos(this, usuario);
		}else if (ultimoEstado.getClass().equals(EstadoEnProceso.class)){
			estadoAnterior = new EstadoEnProceso(this, usuario);
		}else if (ultimoEstado.getClass().equals(EstadoPendientePago.class)){
			estadoAnterior = new EstadoPendientePago(this, usuario);
		}
		return estadoAnterior;
	}

	private EstadoAvaluo obtenerUltimoEstado() {
		return Iterables.get(getAvaluo().getEstadosAvaluo(), getAvaluo().getEstadosAvaluo().size()-2);
	}

}
