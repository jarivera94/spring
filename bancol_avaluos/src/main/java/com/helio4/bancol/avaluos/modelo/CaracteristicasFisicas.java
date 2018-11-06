package com.helio4.bancol.avaluos.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "caracteristicas_fisicas")
public class CaracteristicasFisicas {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name="avaluo_id")
	private Integer avaluoId;
	@Column(name="tipo_inmueble")
	private String tipoInmueble;
	@Column(name="cantidad")
	private Integer cantidad;
	
	public CaracteristicasFisicas() {
		super();
	}

	public CaracteristicasFisicas(Integer avaluoId, String tipoInmueble, Integer cantidad) {
		super();
		this.avaluoId = avaluoId;
		this.tipoInmueble = tipoInmueble;
		this.cantidad = cantidad;
	}

	public void update(Integer avaluoId, String tipoInmueble, Integer cantidad) {
		this.avaluoId = avaluoId;
		this.tipoInmueble = tipoInmueble;
		this.cantidad = cantidad;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAvaluoId() {
		return avaluoId;
	}

	public void setAvaluoId(Integer avaluoId) {
		this.avaluoId = avaluoId;
	}

	public String getTipoInmueble() {
		return tipoInmueble;
	}

	public void setTipoInmueble(String tipoInmueble) {
		this.tipoInmueble = tipoInmueble;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

}
