
package com.helio4.bancol.avaluos.dto;

import java.io.Serializable;
/**
 * DTO Matricula
 * 
 * @author <a href="mailto:j.j.o.c007@gmail.com">Juan Jose Orjuela Castillo</a>
 * 
 */
public class MatriculaDTO implements Serializable, Cloneable {

	
	private static final long serialVersionUID = 8455772356084408257L;
	private Long id;
	private String codigo;
	private String tipoInmueble;
	private Integer numero;
	private Long avaluoId;

	public MatriculaDTO() {
	}

	public MatriculaDTO(Long avaluoId) {
		super();
		this.avaluoId = avaluoId;
	}



	public MatriculaDTO(Long id, String codigo, String tipoInmueble, Integer numero, Long avaluoId) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.tipoInmueble = tipoInmueble;
		this.numero = numero;
		this.avaluoId = avaluoId;
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

	public String getTipoInmueble() {
		return tipoInmueble;
	}

	public void setTipoInmueble(String tipoInmueble) {
		this.tipoInmueble = tipoInmueble;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Long getAvaluoId() {
		return avaluoId;
	}

	public void setAvaluoId(Long avaluoId) {
		this.avaluoId = avaluoId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((avaluoId == null) ? 0 : avaluoId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		MatriculaDTO other = (MatriculaDTO) obj;
		if (avaluoId == null) {
			if (other.avaluoId != null)
				return false;
		} else if (!avaluoId.equals(other.avaluoId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public Object clone() {
		MatriculaDTO clone = new MatriculaDTO(this.id, this.codigo, this.tipoInmueble, this.numero, this.avaluoId);
		return clone;
	}

}
