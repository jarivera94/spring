package com.helio4.bancol.avaluos.modelo;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import com.helio4.bancol.avaluos.modelo.Usuario.DocumentoIdentificacion;

@Entity
public class Propietario {
	
	@EmbeddedId DocumentoIdentificacion id;
	@Column(name = "nombres_apellidos_propietario")
	private String nombresApellidosPropietario;
	@Column(name = "porcentaje_de_propiedad")
	private BigDecimal porcentajeDePropiedad;
	@ManyToMany(mappedBy="propietarios", fetch=FetchType.LAZY)
	private Set<Inmueble> inmuebles;
	
	public Propietario() {
		this.id = new DocumentoIdentificacion();
	}

	public Propietario(DocumentoIdentificacion id,
			String nombresApellidosPropietario,
			BigDecimal porcentajeDePropiedad, Set<Inmueble> inmuebles) {
		super();
		this.id = id;
		this.nombresApellidosPropietario = nombresApellidosPropietario;
		this.porcentajeDePropiedad = porcentajeDePropiedad;
		this.inmuebles = inmuebles;
	}

	public void update(DocumentoIdentificacion id,
			String nombresApellidosPropietario,
			BigDecimal porcentajeDePropiedad, Set<Inmueble> inmuebles) {
		this.id = id;
		this.nombresApellidosPropietario = nombresApellidosPropietario;
		this.porcentajeDePropiedad = porcentajeDePropiedad;
		this.inmuebles = inmuebles;
	}

	public DocumentoIdentificacion getId() {
		return id;
	}

	public void setId(DocumentoIdentificacion id) {
		this.id = id;
	}
	
	public Integer getTipoDocumentoIdentificacion() {
		return id.tipoDocumentoIdentificacion;
	}

	public void setTipoDocumentoIdentificacion(Integer tipoDocumentoIdentificacion) {
		this.id.tipoDocumentoIdentificacion = tipoDocumentoIdentificacion;
	}

	public Long getNumeroDocumento() {
		return id.numeroDocumento;
	}

	public void setNumeroDocumento(Long numeroDocumento) {
		this.id.numeroDocumento = numeroDocumento;
	}

	public String getNombresApellidosPropietario() {
		return nombresApellidosPropietario;
	}

	public void setNombresApellidosPropietario(String nombresApellidosPropietario) {
		this.nombresApellidosPropietario = nombresApellidosPropietario;
	}

	public BigDecimal getPorcentajeDePropiedad() {
		return porcentajeDePropiedad;
	}

	public void setPorcentajeDePropiedad(BigDecimal porcentajeDePropiedad) {
		this.porcentajeDePropiedad = porcentajeDePropiedad;
	}

	public Set<Inmueble> getInmuebles() {
		return inmuebles;
	}

	public void setInmuebles(Set<Inmueble> inmuebles) {
		this.inmuebles = inmuebles;
	}

}
