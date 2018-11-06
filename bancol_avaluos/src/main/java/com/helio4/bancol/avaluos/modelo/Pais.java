package com.helio4.bancol.avaluos.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.helio4.bancol.avaluos.dto.PaisDTO;

@Entity
@Table(name="country")
public class Pais {
	
	@Id
	@Column(name="id")
	private String id;
	@Column(nullable = false)
	private String name;
	
	public Pais() {
	}
	
	public Pais(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Pais(PaisDTO paisDTO) {
		super();
		this.id = paisDTO.getId();
		this.name = paisDTO.getName();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void update(String id, String name) {
		this.id = id;
		this.name = name;
	}

}
