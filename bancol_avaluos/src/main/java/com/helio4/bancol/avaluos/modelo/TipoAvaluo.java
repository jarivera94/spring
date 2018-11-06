package com.helio4.bancol.avaluos.modelo;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipo_avaluo")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "bancolEntityCache")
public class TipoAvaluo {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="tipo_avaluo_id")
	private Long id;
	@Column(name="nombre")
	private String nombre;
	/*
	@OneToMany(mappedBy="tipo_avaluo", fetch=FetchType.LAZY)
	private Set<Tarifa> tarifas;
	*/

	public TipoAvaluo() {}

	public TipoAvaluo(Long id, String nombre/**, Set<Tarifa> tarifas*/) {
		super();
		this.id = id;
		this.nombre = nombre;
		//this.tarifas = tarifas;
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

	/**
	public Set<Tarifa> getTarifas() {
		return tarifas;
	}

	public void setTarifas(Set<Tarifa> tarifas) {
		this.tarifas = tarifas;
	}
	*/

	@Override
	public String toString() {
		return nombre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		TipoAvaluo other = (TipoAvaluo) obj;
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
