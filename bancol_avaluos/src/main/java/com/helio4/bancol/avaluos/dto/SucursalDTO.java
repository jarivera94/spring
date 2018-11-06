package com.helio4.bancol.avaluos.dto;

import java.io.Serializable;

public class SucursalDTO implements Serializable {

	/**
     * 
     */
    private static final long serialVersionUID = -1290489578244223561L;
    private Long id;
	private String codigo;
	private String nombre;
	private String nombreCompuesto;
	private String fechaCreacion;
	private Boolean activo;
	private Long entidadId;
	private String nombreEntidad;

	public SucursalDTO() {
	}

    public SucursalDTO(Long id, String nombreCompuesto) {
        this.id = id;
        this.nombreCompuesto = nombreCompuesto;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreCompuesto() {
		return nombreCompuesto;
	}

	public void setNombreCompuesto(String nombreCompuesto) {
		this.nombreCompuesto = nombreCompuesto;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Long getEntidadId() {
		return entidadId;
	}

	public void setEntidadId(Long entidadId) {
		this.entidadId = entidadId;
	}

	/**
	 * Nombre de la Entidad.
	 */
	public String getNombreEntidad() {
		return nombreEntidad;
	}

	public void setNombreEntidad(String nombreEntidad) {
		this.nombreEntidad = nombreEntidad;
	}

	public String toString() {
		return String.format("%s[id=%d]", getClass().getSimpleName(), getId());
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SucursalDTO other = (SucursalDTO) obj;
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
