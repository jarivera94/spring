package com.helio4.bancol.avaluos.dto;

import java.io.Serializable;

public class SegmentoDTO implements Serializable {

	/**
     * 
     */
    private static final long serialVersionUID = -2256084815059143503L;
    private Long id;
	private String nombre;
	private String fechaCreacion;
	private Boolean accesoCredito;
	private Boolean activo;
	private Boolean cobradoPorBancol;
	private Long entidadId;
	private String nombreEntidad;

	@Override
	public String toString() {
		return String.format("%s[id=%d]", getClass().getSimpleName(), getId());
	}

	public SegmentoDTO() {
	}

    public SegmentoDTO(Long id, String nombre) {
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

	public Long getEntidadId() {
		return entidadId;
	}

	public void setEntidadId(Long entidadId) {
		this.entidadId = entidadId;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Boolean getAccesoCredito() {
		return accesoCredito;
	}

	public void setAccesoCredito(Boolean accesoCredito) {
		this.accesoCredito = accesoCredito;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Boolean getCobradoPorBancol() {
		return cobradoPorBancol;
	}

	public void setCobradoPorBancol(Boolean cobradoAlBanco) {
		this.cobradoPorBancol = cobradoAlBanco;
	}

	public String getNombreEntidad() {
		return nombreEntidad;
	}

	public void setNombreEntidad(String nombreEntidad) {
		this.nombreEntidad = nombreEntidad;
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
		SegmentoDTO other = (SegmentoDTO) obj;
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
