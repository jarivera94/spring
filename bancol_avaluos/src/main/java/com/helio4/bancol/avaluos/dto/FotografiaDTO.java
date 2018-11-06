package com.helio4.bancol.avaluos.dto;

import java.io.Serializable;


public class FotografiaDTO  implements Serializable{

    private static final long serialVersionUID = 5613637973994864728L;
    private Long id;
    private String rutaUbicacion;
    private String descripcion;
    private Long avaluoId;
    private Long orden;
    private boolean dirty;

    public FotografiaDTO() {}

    public FotografiaDTO(String rutaUbicacion, String descripcion,
            Long avaluoId, Long orden) {
        super();
        this.rutaUbicacion = rutaUbicacion;
        this.descripcion = descripcion;
        this.avaluoId = avaluoId;
        this.orden = orden;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAvaluoId() {
        return avaluoId;
    }

    public void setAvaluoId(Long avaluoId) {
        this.avaluoId = avaluoId;
    }

    public String getRutaUbicacion() {
        return rutaUbicacion;
    }

    public void setRutaUbicacion(String rutaUbicacion) {
        this.rutaUbicacion = rutaUbicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getOrden() {
        return orden;
    }

    public void setOrden(Long orden) {
        this.orden = orden;
    }

    public boolean isDirty() {
        return dirty;
    }

    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    @Override
    public String toString() {
		return String.format("%s[id=%d], [descripcion=%s]",
                getClass().getSimpleName(), getId(), getDescripcion());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
            + ((avaluoId == null) ? 0 : avaluoId.hashCode());
        result = prime * result
            + ((descripcion == null) ? 0 : descripcion.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result
            + ((rutaUbicacion == null) ? 0 : rutaUbicacion.hashCode());
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
        FotografiaDTO other = (FotografiaDTO) obj;
        if (avaluoId == null) {
            if (other.avaluoId != null)
                return false;
        } else if (!avaluoId.equals(other.avaluoId))
            return false;
        if (descripcion == null) {
            if (other.descripcion != null)
                return false;
        } else if (!descripcion.equals(other.descripcion))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (rutaUbicacion == null) {
            if (other.rutaUbicacion != null)
                return false;
        } else if (!rutaUbicacion.equals(other.rutaUbicacion))
            return false;
        return true;
    }
}
