package com.helio4.bancol.avaluos.modelo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.helio4.bancol.avaluos.dto.UvrDTO;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "uvr")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "bancolEntityCache")
public class Uvr {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="uvr_id")
	private Long id;
	@Column(name="fecha")
	@Temporal(TemporalType.DATE)
	private Date fechaUvr;
	@Column
	private BigDecimal valor;
	
	public Uvr() {}
	
	public Uvr(Long id, Date fechaUvr, BigDecimal valor) {
		super();
		this.id = id;
		this.fechaUvr = fechaUvr;
		this.valor = valor;
	}
	
	public Uvr(UvrDTO dto) {
		super();
		this.id = dto.getId();
		this.fechaUvr = dto.getFechaUvr();
		this.valor = dto.getValor();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaUvr() {
		return fechaUvr;
	}

	public void setFechaUvr(Date fechaUvr) {
		this.fechaUvr = fechaUvr;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
