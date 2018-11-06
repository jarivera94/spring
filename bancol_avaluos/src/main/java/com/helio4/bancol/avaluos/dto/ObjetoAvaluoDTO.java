package com.helio4.bancol.avaluos.dto;

import java.io.Serializable;

public class ObjetoAvaluoDTO implements Cloneable, Serializable {

	/**
     * 
     */
    private static final long serialVersionUID = 5404103491916166130L;

    private Long id;

	private String nombre;

	public ObjetoAvaluoDTO() {
    }

    public ObjetoAvaluoDTO(Long id, String nombre) {
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
    protected Object clone() throws CloneNotSupportedException {
        return new ObjetoAvaluoDTO(id, nombre);
    }

    @Override
	public String toString() {
		return String.format("%s[id=%d]", getClass().getSimpleName(), getId());
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
        ObjetoAvaluoDTO other = (ObjetoAvaluoDTO) obj;
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
