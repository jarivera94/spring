package com.helio4.bancol.avaluos.modelo;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

/**
 * Represents a subsidy type which limits the maximum and minimum amount of the appraisal
 * @author <a href="mailto:gersonsosa@gmail.com">Gerson Sosa</a>
 */
@Entity
@Table(name = "subsidio")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "bancolEntityCache")
public class Subsidio {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private long id;
    @Column(name="nombre")
    private String nombre;
    @Column(name="minimo")
    private long minimo;
    @Column(name="maximo")
    private long maximo;

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

    public long getMinimo() {
        return minimo;
    }

    public void setMinimo(long minimo) {
        this.minimo = minimo;
    }

    public long getMaximo() {
        return maximo;
    }

    public void setMaximo(long maximo) {
        this.maximo = maximo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subsidio subsidio = (Subsidio) o;

        if (id != subsidio.id) return false;
        if (minimo != subsidio.minimo) return false;
        if (maximo != subsidio.maximo) return false;
        return nombre != null ? nombre.equals(subsidio.nombre) : subsidio.nombre == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (int) (minimo ^ (minimo >>> 32));
        result = 31 * result + (int) (maximo ^ (maximo >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Subsidio{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", minimo=" + minimo +
                ", maximo=" + maximo +
                '}';
    }
}
