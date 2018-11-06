package com.helio4.bancol.avaluos.dto;

import com.helio4.bancol.avaluos.servicio.util.Constantes;
import com.helio4.bancol.avaluos.servicio.util.Constantes.Estado;

import java.util.Date;

public class EstadoAvaluoDTO {

	private Long id;
	private Date fechaEstado;
	private Constantes.Estado estado;
	private Integer tipoDocumentoPerito;
    private Long numeroDocumentoPerito;
	private String justificacionRechazo;
	private Long avaluoId;
	private String usuarioNombres;
    private String usuarioApellidos;
    private String usuarioRol;
	
	public EstadoAvaluoDTO() {}

	public EstadoAvaluoDTO(Long avaluoId, Integer estado, Date fechaEstado,
                           String usuarioNombres, String usuarioApellidos,
                           String usuarioRol,
                           String justificacionRechazo ){
        this.avaluoId = avaluoId;
        this.estado = Estado.fromKey( estado );
        this.fechaEstado = fechaEstado;
        this.usuarioNombres = usuarioNombres;
        this.usuarioApellidos = usuarioApellidos;
        this.usuarioRol = usuarioRol;
        this.justificacionRechazo = justificacionRechazo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaEstado() {
		return fechaEstado;
	}

	public void setFechaEstado(Date fechaEstado) {
		this.fechaEstado = fechaEstado;
	}

	public Constantes.Estado getEstado() {
		return estado;
	}

	public void setEstado(Constantes.Estado estado) {
		this.estado = estado;
	}

    public Integer getTipoDocumentoPerito() {
        return tipoDocumentoPerito;
    }

    public void setTipoDocumentoPerito(Integer tipoDocumentoPerito) {
        this.tipoDocumentoPerito = tipoDocumentoPerito;
    }

    public Long getNumeroDocumentoPerito() {
        return numeroDocumentoPerito;
    }

    public void setNumeroDocumentoPerito(Long numeroDocumentoPerito) {
        this.numeroDocumentoPerito = numeroDocumentoPerito;
    }

    public Long getAvaluoId() {
		return avaluoId;
	}

	public void setAvaluoId(Long avaluoId) {
		this.avaluoId = avaluoId;
	}

	public String getJustificacionRechazo() {
		return justificacionRechazo;
	}

	public void setJustificacionRechazo(String justificacionRechazo) {
		this.justificacionRechazo = justificacionRechazo;
	}

    public String getUsuarioNombres() {
        return usuarioNombres;
    }

    public void setUsuarioNombres(String usuarioNombres) {
        this.usuarioNombres = usuarioNombres;
    }

    public String getUsuarioApellidos() {
        return usuarioApellidos;
    }

    public void setUsuarioApellidos(String usuarioApellidos) {
        this.usuarioApellidos = usuarioApellidos;
    }

    public String getUsuarioRol() {
        return usuarioRol;
    }

    public void setUsuarioRol(String usuarioRol) {
        this.usuarioRol = usuarioRol;
    }

    @Override
    public String toString() {
        return "EstadoAvaluoDTO{" +
                "id=" + id +
                ", fechaEstado=" + fechaEstado +
                ", estado=" + estado +
                ", tipoDocumentoPerito=" + tipoDocumentoPerito +
                ", numeroDocumentoPerito=" + numeroDocumentoPerito +
                ", justificacionRechazo='" + justificacionRechazo + '\'' +
                ", avaluoId=" + avaluoId +
                ", usuarioNombres='" + usuarioNombres + '\'' +
                ", usuarioApellidos='" + usuarioApellidos + '\'' +
                ", usuarioRol='" + usuarioRol + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EstadoAvaluoDTO that = (EstadoAvaluoDTO) o;

        if (!id.equals(that.id)) return false;
        if (estado != that.estado) return false;
        if (!avaluoId.equals(that.avaluoId)) return false;
        if (usuarioNombres != null ? !usuarioNombres.equals(that.usuarioNombres) : that.usuarioNombres != null)
            return false;
        return usuarioApellidos != null ? usuarioApellidos.equals(that.usuarioApellidos) : that.usuarioApellidos == null;

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (estado != null ? estado.hashCode() : 0);
        result = 31 * result + avaluoId.hashCode();
        result = 31 * result + (usuarioNombres != null ? usuarioNombres.hashCode() : 0);
        result = 31 * result + (usuarioApellidos != null ? usuarioApellidos.hashCode() : 0);
        return result;
    }
}
