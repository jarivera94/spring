package com.helio4.bancol.avaluos.modelo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("14")
public class EstadoCancelado extends EstadoAvaluo {
	
	public EstadoCancelado() {
		super();
	}

	public EstadoCancelado(Avaluo avaluo, Usuario usuario) {
		super(avaluo, usuario);
	}
	
	public EstadoCancelado(EstadoAvaluo estadoAvaluo, Usuario usuario) {
		super(estadoAvaluo, usuario);
	}

	@Override
	public void cancelarAvaluo(Usuario usuario) throws EstadoIlegalException {
        throw new EstadoIlegalException(
                "No se puede realizar esta operación en este estado: "
                + this.getClass().getName() + " avaluoId:  "
                + getAvaluo().getId());
    }
}
