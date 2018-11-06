package com.helio4.bancol.avaluos.dto;

import org.springframework.security.core.GrantedAuthority;

import com.helio4.bancol.avaluos.modelo.Estado;
import com.helio4.bancol.avaluos.modelo.Permiso;

public class PermisoDTO implements GrantedAuthority {

    private static final long serialVersionUID = 131513232312l;

    private Long id;
    private String nombre;
    private EstadoDTO estado;

    public PermisoDTO() {
    }
    
    public PermisoDTO(Permiso permiso) {
    	this.id = permiso.getId();
    	this.nombre  = permiso.getNombre();
    	
    	Estado estado = permiso.getEstado();
    	this.estado = new EstadoDTO();
    	if(estado != null) {
    		this.estado.setId(estado.getId());
    		this.estado.setNombre(estado.getNombre());
    	}    	
    }

    public PermisoDTO(Long id, String nombre) {
        super();
        this.id = id;
        this.nombre = nombre;
    }

    public PermisoDTO(String nombre) {
        this.nombre = nombre;
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

    public EstadoDTO getEstado() {
        return estado;
    }

    public void setEstado(EstadoDTO estado) {
        this.estado = estado;
    }

    public String getAuthority() {
        return "ROLE_" + nombre;
    }

    public String toString() {
        return String.format("%s[id=%d]", getClass().getSimpleName(), getId());
    };

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
        PermisoDTO other = (PermisoDTO) obj;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        return true;
    }

}
