package com.helio4.bancol.avaluos.modelo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.google.common.collect.Iterables;

@Entity
@DiscriminatorValue("13")
public class EstadoDevuelto extends EstadoAvaluo {

	public EstadoDevuelto() {
		super();
	}

	public EstadoDevuelto(Avaluo avaluo, Usuario usuario) {
		super(avaluo, usuario);
	}

	public EstadoDevuelto(EstadoAvaluo estadoAvaluo, Usuario usuario) {
		super(estadoAvaluo, usuario);
	}

	@Override
	public void reactivar(Usuario usuario) {
		// Calcular tiempo que se demoro inactivo desde
		// el principio de este estado y sumarlo al tiempo inactivo en el avaluo.
		Long tiempoInactivo = System.currentTimeMillis() - getFechaEstado().getTime();
		getAvaluo().setDuracionPausaSemaforo(getAvaluo().getDuracionPausaSemaforo() == null ?
		        tiempoInactivo : getAvaluo().getDuracionPausaSemaforo() + tiempoInactivo);
		getAvaluo().setEstado(pasarEstado(usuario));
	}

	@Override
	public void devolver(String justificacion, Usuario usuario) throws EstadoIlegalException {
        throw new EstadoIlegalException(
                "No se puede realizar esta operación en este estado: "
                + this.getClass().getName() + " avaluoId:  "
                + getAvaluo().getId());
    }

	@Override
	public EstadoAvaluo pasarEstado(Usuario usuario) {
		EstadoAvaluo estadoAnterior = null;
		EstadoAvaluo ultimoEstado = obtenerUltimoEstado();
		if(ultimoEstado.getClass().equals(EstadoPorAsignar.class)){
			estadoAnterior = new EstadoPorAsignar(this, usuario);
		}else if(ultimoEstado.getClass().equals(EstadoAsignado.class)){
			estadoAnterior = new EstadoAsignado(this, usuario);
		}else if (ultimoEstado.getClass().equals(EstadoPorProgramarCita.class)) {
			estadoAnterior = new EstadoPorProgramarCita(this, usuario);
		}else if (ultimoEstado.getClass().equals(EstadoCitaProgramada.class)){
			estadoAnterior = new EstadoCitaProgramada(this, usuario);
		}else if (ultimoEstado.getClass().equals(EstadoPendienteDocumentos.class)){
			estadoAnterior = new EstadoPendienteDocumentos(this, usuario);
		}else if (ultimoEstado.getClass().equals(EstadoEnProceso.class)){
			estadoAnterior = new EstadoEnProceso(this, usuario);
		}else if (ultimoEstado.getClass().equals(EstadoPendientePago.class)){
			estadoAnterior = new EstadoPendientePago(this, usuario);
		}else if(ultimoEstado.getClass().equals(EstadoEnviado.class)){
			estadoAnterior = new EstadoEnviado(this, usuario);
		}else if(ultimoEstado.getClass().equals(EstadoCorreciones.class)){
			estadoAnterior = new EstadoCorreciones(this, usuario);
		}else if(ultimoEstado.getClass().equals(EstadoPorRevisarComite.class)){
			estadoAnterior = new EstadoPorRevisarComite(this, usuario);
		}
		return estadoAnterior;
	}

	private EstadoAvaluo obtenerUltimoEstado() {
        if (Iterables
                .get(getAvaluo().getEstadosAvaluo(),
                        getAvaluo().getEstadosAvaluo().size() - 2).getClass()
                .equals(EstadoPorAprobarDevolucion.class)) {
			return Iterables.get(getAvaluo().getEstadosAvaluo(), getAvaluo().getEstadosAvaluo().size()-3);
		}else{
			return Iterables.get(getAvaluo().getEstadosAvaluo(), getAvaluo().getEstadosAvaluo().size()-2);
		}
		
	}
}
