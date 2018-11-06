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
@Table(name="documento")
public class Documento {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="documento_id")
	private Long id;
	
	@Column(name="ruta_ubicacion")
	private String rutaUbicacion;
	
	@Column(name="tipo_documento")
	private String tipoDocumento;
	
	@ManyToOne
	@JoinColumn(name = "avaluo_id", nullable=false)
	private Avaluo avaluo;
	
	public Documento(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Avaluo getAvaluo() {
		return avaluo;
	}

	public void setAvaluo(Avaluo avaluo) {
		this.avaluo = avaluo;
	}

	public String getRutaUbicacion() {
		return rutaUbicacion;
	}

	public void setRutaUbicacion(String rutaUbicacion) {
		this.rutaUbicacion = rutaUbicacion;
	}
	
}
