package com.helio4.bancol.avaluos.modelo;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(name = "objeto")
@Cacheable(true)
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "bancolEntityCache")
public class ObjetoAvaluo {

    @Id
    @Column(name="id")
    private Long id;
    @Column(name="nombre")
    private String nombre;

    public ObjetoAvaluo() {}

    public ObjetoAvaluo(Long id, String nombre) {
        super();
        this.id = id;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
        public String toString() {
            return nombre;
        }

    @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((id == null) ? 0 : id.hashCode());
            result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
            return result;
        }

    @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            ObjetoAvaluo other = (ObjetoAvaluo) obj;
            if (id == null) {
                if (other.id != null)
                    return false;
            } else if (!id.equals(other.id))
                return false;
            if (nombre == null) {
                if (other.nombre != null)
                    return false;
            } else if (!nombre.equals(other.nombre))
                return false;
            return true;
        }

}
