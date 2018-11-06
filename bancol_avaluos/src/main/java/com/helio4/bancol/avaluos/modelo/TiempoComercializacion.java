package com.helio4.bancol.avaluos.modelo;

import javax.persistence.*;

/**
 * Represents the relationship between divipola, tipo_inmueble and estrato
 * @author <a href="mailto:gersonsosa@gmail.com">Gerson Sosa</a>
 */
@Entity
@Table(name = "tiempo_comercializacion")
public class TiempoComercializacion {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name="divipola_id", nullable=false)
    private Divipola divipola;
    @Column(name="categoria")
    private int categoria;
    @ManyToOne
    @JoinColumn(name="tipo_inmueble_id", nullable=false)
    private TipoInmueble tipoInmueble;
    @Column(name="estrato", nullable = false)
    private int estrato;
    @Column(name="tiempo_comercializacion")
    private Double tiempoComercializacion;

    public TiempoComercializacion() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Divipola getDivipola() {
        return divipola;
    }

    public void setDivipola(Divipola divipola) {
        this.divipola = divipola;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public TipoInmueble getTipoInmueble() {
        return tipoInmueble;
    }

    public void setTipoInmueble(TipoInmueble tipoInmueble) {
        this.tipoInmueble = tipoInmueble;
    }

    public int getEstrato() {
        return estrato;
    }

    public void setEstrato(int estrato) {
        this.estrato = estrato;
    }

    public Double getTiempoComercializacion() {
        return tiempoComercializacion;
    }

    public void setTiempoComercializacion(Double tiempoComercializacion) {
        this.tiempoComercializacion = tiempoComercializacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TiempoComercializacion that = (TiempoComercializacion) o;

        if (id != that.id) return false;
        if (categoria != that.categoria) return false;
        if (estrato != that.estrato) return false;
        if (divipola != null ? !divipola.equals(that.divipola) : that.divipola != null) return false;
        if (tipoInmueble != null ? !tipoInmueble.equals(that.tipoInmueble) : that.tipoInmueble != null) return false;
        return tiempoComercializacion != null ? tiempoComercializacion.equals(that.tiempoComercializacion) : that.tiempoComercializacion == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (divipola != null ? divipola.hashCode() : 0);
        result = 31 * result + categoria;
        result = 31 * result + (tipoInmueble != null ? tipoInmueble.hashCode() : 0);
        result = 31 * result + estrato;
        result = 31 * result + (tiempoComercializacion != null ? tiempoComercializacion.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TiempoComercializacion{" +
                "id=" + id +
                ", divipola=" + divipola +
                ", categoria=" + categoria +
                ", tipoInmueble=" + tipoInmueble +
                ", estrato=" + estrato +
                ", tiempoComercializacion=" + tiempoComercializacion +
                '}';
    }
}
