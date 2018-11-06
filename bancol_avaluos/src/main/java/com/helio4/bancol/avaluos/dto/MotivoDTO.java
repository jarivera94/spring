
package com.helio4.bancol.avaluos.dto;

import java.io.Serializable;
/**
 * DTO Motivo
 * 
 * @author <a href="mailto:j.j.o.c007@gmail.com">Juan Jose Orjuela Castillo</a>
 * 
 */
public class MotivoDTO implements Serializable, Cloneable {

	
	private static final long serialVersionUID = 8455772356084408257L;
	private Long codigo;
	private String nombre;
	private String abreviatura;
	private String prefijo;
	private Boolean estado;
	private Long entidadId;
	
	public MotivoDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public MotivoDTO(Long codigo, String nombre, String abreviatura, String prefijo, Boolean estado) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.abreviatura = abreviatura;
		this.prefijo = prefijo;
		this.estado = estado;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public String getPrefijo() {
		return prefijo;
	}

	public void setPrefijo(String prefijo) {
		this.prefijo = prefijo;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Long getEntidadId() {
		return entidadId;
	}

	public void setEntidadId(Long entidadId) {
		this.entidadId = entidadId;
	}

	@Override
	public String toString() {
		return "MotivoDTO [codigo=" + codigo + ", nombre=" + nombre + ", abreviatura=" + abreviatura + ", prefijo="
				+ prefijo + ", estado=" + estado + ", entidadId=" + entidadId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((abreviatura == null) ? 0 : abreviatura.hashCode());
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((entidadId == null) ? 0 : entidadId.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((prefijo == null) ? 0 : prefijo.hashCode());
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
		MotivoDTO other = (MotivoDTO) obj;
		if (abreviatura == null) {
			if (other.abreviatura != null)
				return false;
		} else if (!abreviatura.equals(other.abreviatura))
			return false;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (entidadId == null) {
			if (other.entidadId != null)
				return false;
		} else if (!entidadId.equals(other.entidadId))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (prefijo == null) {
			if (other.prefijo != null)
				return false;
		} else if (!prefijo.equals(other.prefijo))
			return false;
		return true;
	}
	
}
