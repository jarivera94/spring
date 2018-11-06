package com.helio4.bancol.avaluos.modelo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("2")
public class EstadoAsignado extends EstadoAvaluo {

    @Transient
    private String justificacion;

    public EstadoAsignado() {
        super();
    }

    public EstadoAsignado(Avaluo avaluo, Usuario usuario) {
        super(avaluo, usuario);
    }

    public EstadoAsignado(EstadoAvaluo estadoAvaluo, Usuario usuario) {
        super(estadoAvaluo, usuario);
    }

    @Override
    public void aceptarCaso(Usuario usuario) {
        getAvaluo().setEstado(pasarEstado(usuario));
    }

    @Override
    public void rechazarCaso(String justificacion, Usuario usuario) {
        this.justificacion = justificacion;
        getAvaluo().setEstado(pasarEstado(usuario));
    }

    @Override
    public EstadoAvaluo pasarEstado(Usuario usuario) {
        EstadoAvaluo estadoAvaluo = null;
        if (justificacion == null) {
            estadoAvaluo = new EstadoPorProgramarCita(this, usuario);
        } else {
            estadoAvaluo = new EstadoPorAsignar(this, usuario);
            estadoAvaluo.setJustificacionRechazo(justificacion);
        }
        return estadoAvaluo;
    }

}
