package com.helio4.bancol.avaluos.dto;

import java.util.ArrayList;
import java.util.List;


public class RolDTO {
	
	private Long id;
	private String nombre;
	private PaginaInicioDTO paginaInicio;
	private List<PermisoDTO> permisos;

    public RolDTO(){
    	this.permisos = new ArrayList<PermisoDTO>();
    }

    public RolDTO(Long id, String nombre,
            Long paginaInicioId, String nombrePaginaInicio) {
        this.id = id;
        this.nombre = nombre;
        this.paginaInicio = new PaginaInicioDTO();
        this.paginaInicio.setId(paginaInicioId);
        this.paginaInicio.setNombre(nombrePaginaInicio);
    }

	public Long getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public PaginaInicioDTO getPaginaInicio() {
		return paginaInicio;
	}
	public void setPaginaInicio(PaginaInicioDTO paginaInicio) {
		this.paginaInicio = paginaInicio;
	}
	public List<PermisoDTO> getPermisos() {
		return permisos;
	}
	public void setPermisos(List<PermisoDTO> permisos) {
		this.permisos = permisos;
	}
	
	public String toString() {
		return String.format("%s[id=%d]", getClass().getSimpleName(), getId());
	}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
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
        RolDTO other = (RolDTO) obj;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        return true;
    }

}
