package com.helio4.bancol.avaluos.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "segmento")
public class Segmento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "segmento_id")
	private Long id;	
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "fecha_creacion")
	private Date fechaCreacion;
	@Column(name = "acceso_credito")
	private Boolean accesoCredito;
	@Column(name = "activo")
	private Boolean activo;
	@Column(name = "cobrado_banco")
	private Boolean cobradoPorBancol;
	@ManyToOne
	@JoinColumn(name = "entidad_id", nullable = false)
	private Entidad entidad;	

	public Segmento() {
		super();
	}

	public Segmento(Long id, String nombre, Date fechaCreacion, Boolean accesoCredito,
			Boolean activo, Boolean cobradoAlBanco, Entidad entidad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.entidad = entidad;
		this.fechaCreacion = fechaCreacion;
		this.accesoCredito = accesoCredito;
		this.activo = activo;
		this.cobradoPorBancol = cobradoAlBanco;
	}

	public void update(String nombre, Date fechaCreacion, Boolean accesoCredito,
			Boolean activo, Boolean cobradoAlBanco, Entidad entidad) {		
		this.nombre = nombre;
		this.entidad = entidad;
		this.fechaCreacion = fechaCreacion;
		this.accesoCredito = accesoCredito;
		this.activo = activo;
		this.cobradoPorBancol = cobradoAlBanco;
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

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
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

	public Entidad getEntidad() {
		return entidad;
	}

	public void setEntidad(Entidad entidad) {
		this.entidad = entidad;
	}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((accesoCredito == null) ? 0 : accesoCredito.hashCode());
        result = prime * result + ((activo == null) ? 0 : activo.hashCode());
        result = prime * result
                + ((cobradoPorBancol == null) ? 0 : cobradoPorBancol.hashCode());
        result = prime * result + ((entidad == null) ? 0 : entidad.hashCode());
        result = prime * result
                + ((fechaCreacion == null) ? 0 : fechaCreacion.hashCode());
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
        Segmento other = (Segmento) obj;
        if (accesoCredito == null) {
            if (other.accesoCredito != null)
                return false;
        } else if (!accesoCredito.equals(other.accesoCredito))
            return false;
        if (activo == null) {
            if (other.activo != null)
                return false;
        } else if (!activo.equals(other.activo))
            return false;
        if (cobradoPorBancol == null) {
            if (other.cobradoPorBancol != null)
                return false;
        } else if (!cobradoPorBancol.equals(other.cobradoPorBancol))
            return false;
        if (entidad == null) {
            if (other.entidad != null)
                return false;
        } else if (!entidad.equals(other.entidad))
            return false;
        if (fechaCreacion == null) {
            if (other.fechaCreacion != null)
                return false;
        } else if (!fechaCreacion.equals(other.fechaCreacion))
            return false;
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
