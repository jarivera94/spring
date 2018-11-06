package com.helio4.bancol.avaluos.modelo;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Represents a minimum monthly wage.
 * @author <a href="mailto:gersonsosa@gmail.com">Gerson Sosa</a>
 */
@Entity
@Table(name = "salario_minimo")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "bancolEntityCache")
public class SalarioMinimo {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @Column(name="mensual")
    private BigDecimal mensual;
    @Column(name="fecha_vigencia")
    private Date fechaVigencia;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getMensual() {
        return mensual;
    }

    public void setMensual(BigDecimal mensual) {
        this.mensual = mensual;
    }

    public Date getFechaVigencia() {
        return fechaVigencia;
    }

    public void setFechaVigencia(Date fechaVigencia) {
        this.fechaVigencia = fechaVigencia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SalarioMinimo that = (SalarioMinimo) o;

        if (id != that.id) return false;
        if (mensual != null ? !mensual.equals(that.mensual) : that.mensual != null) return false;
        return fechaVigencia != null ? fechaVigencia.equals(that.fechaVigencia) : that.fechaVigencia == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (mensual != null ? mensual.hashCode() : 0);
        result = 31 * result + (fechaVigencia != null ? fechaVigencia.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SalarioMinimo{" +
                "id=" + id +
                ", mensual=" + mensual +
                ", fechaVigencia=" + fechaVigencia +
                '}';
    }
}
