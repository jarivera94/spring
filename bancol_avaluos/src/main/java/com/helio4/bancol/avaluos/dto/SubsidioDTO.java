package com.helio4.bancol.avaluos.dto;

import java.math.BigDecimal;

/**
 * {@link com.helio4.bancol.avaluos.modelo.Subsidio} data transfer object
 * @author <a href="mailto:gersonsosa@gmail.com">Gerson Sosa</a>
 */
public class SubsidioDTO {
    private long id;
    private String nombre;
    private BigDecimal minimo;
    private long minimoSalarios;
    private BigDecimal maximo;
    private long maximoSalarios;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getMinimo() {
        return minimo;
    }

    public void setMinimo(BigDecimal minimo) {
        this.minimo = minimo;
    }

    public long getMinimoSalarios() {
        return minimoSalarios;
    }

    public void setMinimoSalarios(long minimoSalarios) {
        this.minimoSalarios = minimoSalarios;
    }

    public BigDecimal getMaximo() {
        return maximo;
    }

    public void setMaximo(BigDecimal maximo) {
        this.maximo = maximo;
    }

    public long getMaximoSalarios() {
        return maximoSalarios;
    }

    public void setMaximoSalarios(long maximoSalarios) {
        this.maximoSalarios = maximoSalarios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubsidioDTO that = (SubsidioDTO) o;

        if (id != that.id) return false;
        if (minimoSalarios != that.minimoSalarios) return false;
        if (maximoSalarios != that.maximoSalarios) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (minimo != null ? !minimo.equals(that.minimo) : that.minimo != null) return false;
        return maximo != null ? maximo.equals(that.maximo) : that.maximo == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (minimo != null ? minimo.hashCode() : 0);
        result = 31 * result + (int) (minimoSalarios ^ (minimoSalarios >>> 32));
        result = 31 * result + (maximo != null ? maximo.hashCode() : 0);
        result = 31 * result + (int) (maximoSalarios ^ (maximoSalarios >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "SubsidioDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", minimo=" + minimo +
                ", minimoSalarios=" + minimoSalarios +
                ", maximo=" + maximo +
                ", maximoSalarios=" + maximoSalarios +
                '}';
    }
}
