package com.helio4.bancol.avaluos.modelo;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("1")
public class EstadoPorAsignar extends EstadoAvaluo {

    public EstadoPorAsignar() {
        super();
    }

    public EstadoPorAsignar(Avaluo avaluo) {
        setAvaluo(avaluo);
        setFechaEstado(new Date(System.currentTimeMillis()));
        setEstadoActual(true);
    }

    public EstadoPorAsignar(EstadoAvaluo estadoAvaluo, Usuario usuario) {
        super(estadoAvaluo, usuario);
    }

}
