package com.helio4.bancol.avaluos.modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Rol implements GrantedAuthority{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="rol_id")
	private Long id;
	@Column
	private String nombre;
	@ManyToOne
	@JoinColumn(name = "pagina_inicio_id", nullable = false)
	private PaginaInicio paginaInicio;
	@ManyToMany(cascade={CascadeType.REFRESH})
	@JoinTable(name="rol_permiso",
				joinColumns= {@JoinColumn(name="rolId")},
				inverseJoinColumns= {@JoinColumn(name="permisoId")})
	private List<Permiso> permisos;
	
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
	public PaginaInicio getPaginaInicio() {
		return paginaInicio;
	}
	public void setPaginaInicio(PaginaInicio paginaInicio) {
		this.paginaInicio = paginaInicio;
	}
	public List<Permiso> getPermisos() {
		return permisos;
	}
	public void setPermisos(List<Permiso> permisos) {
		this.permisos = permisos;
	}
	@Override
	public String getAuthority() {
		return nombre;
	}
	public void update(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result
				+ ((paginaInicio == null) ? 0 : paginaInicio.hashCode());
		result = prime * result
				+ ((permisos == null) ? 0 : permisos.hashCode());
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
		Rol other = (Rol) obj;
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
		if (paginaInicio == null) {
			if (other.paginaInicio != null)
				return false;
		} else if (!paginaInicio.equals(other.paginaInicio))
			return false;
		if (permisos == null) {
			if (other.permisos != null)
				return false;
		} else if (!permisos.equals(other.permisos))
			return false;
		return true;
	}

}
