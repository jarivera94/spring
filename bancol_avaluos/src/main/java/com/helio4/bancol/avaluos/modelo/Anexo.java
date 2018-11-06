package com.helio4.bancol.avaluos.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "anexo")
public class Anexo{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="anexo_id")
	private Long id;
	@Column(name = "ruta_ubicacion")
	private String rutaUbicacion;
	@Column
	private String descripcion;
	@ManyToOne
	@JoinColumn(name="avaluo_id", nullable=false)
	private Avaluo avaluo;
	@Column(name="orden")
	private Long orden;
	
	public Anexo() {
	}

	public void update(String descripcion,
			String area, String unidadDeMedida, String valorUnitario,
			String valorTotal) {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Avaluo getAvaluo() {
		return avaluo;
	}

	public void setAvaluo(Avaluo avaluo) {
		this.avaluo = avaluo;
	}

	public Long getOrden() {
		return orden;
	}

	public void setOrden(Long orden) {
		this.orden = orden;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((avaluo == null) ? 0 : avaluo.hashCode());
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
		Anexo other = (Anexo) obj;
		if (avaluo == null) {
			if (other.avaluo != null)
				return false;
		} else if (!avaluo.equals(other.avaluo))
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