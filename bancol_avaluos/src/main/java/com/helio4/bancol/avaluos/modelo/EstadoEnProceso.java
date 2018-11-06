package com.helio4.bancol.avaluos.modelo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.helio4.bancol.avaluos.servicio.util.Constantes;

@Entity
@DiscriminatorValue("6")
public class EstadoEnProceso extends EstadoAvaluo {
	
	@Transient
	private String justificacion;
	
	@Transient
	private boolean cobradoPorBancol;

	public EstadoEnProceso() {
		super();
		cobradoPorBancol = false;
	}
	
	public EstadoEnProceso(Avaluo avaluo, Usuario usuario) {
		super(avaluo, usuario);
	}
	
	public EstadoEnProceso(EstadoAvaluo estadoAvaluo, Usuario usuario) {
		super(estadoAvaluo, usuario);
	}
	
	@Override
	public void solicitarDevolucion(String justificacion, Usuario usuario) {
		this.justificacion = justificacion;
		getAvaluo().setEstado(pasarEstado(usuario));
	}
	
	@Override
	public void notificarHonorarios(Usuario usuario) {
		getAvaluo().setEstado(pasarEstado(usuario));
	}
	
	@Override
	public void enviar(Usuario usuario) {
		// El perito cobra el avaluo? o no.
		cobradoPorBancol = comprobarCobradoPorBancol()
		        || getAvaluo().getTipoAvaluo().getNombre()
		        .equals(Constantes.TIPO_AVALUO_REMATE);
		getAvaluo().setEstado(pasarEstado(usuario));
	}

    public boolean comprobarCobradoPorBancol() {
		return (getAvaluo().getTipoAvaluo().equals(Constantes.TIPO_AVALUO_REMATE) ? true :
				(getAvaluo().getCompraCartera() != null
				&& getAvaluo().getCompraCartera()
				&& getAvaluo().getEntidad().getNombre().equals("Bancolombia")
				&& getAvaluo().getCliente().getSegmento() != null
				&& getAvaluo().getCliente().getSegmento().getCobradoPorBancol() != null
				&& getAvaluo().getCliente().getSegmento().getCobradoPorBancol())
				|| (getAvaluo().getEntidad().getCobradoPorBancol()));
	}
	
	@Override
	public EstadoAvaluo pasarEstado(Usuario usuario) {
		EstadoAvaluo estadoSiguiente;
		if (justificacion == null) {
			if (cobradoPorBancol) {
                /*
                 * Se envia uno de cada diez avaluos al comité
                 */
				estadoSiguiente = (getAvaluo().getId() % 10L) == 0 ? new EstadoPorRevisarComite(this, usuario) : new EstadoEnviado(this, usuario);
			}else{
				estadoSiguiente = getAvaluo().getSoloAvaluo() ? new EstadoEnviado(this, usuario) : new EstadoPendientePago(this, usuario);
			}
		}else{
			estadoSiguiente = new EstadoPorAprobarDevolucion(this, usuario);
			estadoSiguiente.setJustificacionRechazo(justificacion);
		}
		return estadoSiguiente;
	}

}
