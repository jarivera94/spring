package com.helio4.bancol.avaluos.modelo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "via_acceso")
public class ViaAcceso {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "via_acceso_id")
	private Long id;
	@Column(name = "trayecto")
	private BigDecimal trayecto;
	@Column(name = "via")
	private String via;
	@Column(name = "descripcion")
	private String descripcion;
	@Column(name = "estado")
	private Integer estado;
	@Column(name = "tipo")
	private Integer tipo;
	@ManyToOne
	@JoinColumn(name="avaluo_id", nullable=false)
	private AvaluoComercial avaluo;
	
	public ViaAcceso() {
	}
	
	public ViaAcceso(BigDecimal trayecto, String via,
			String descripcion, Integer estado, Integer tipo,
			AvaluoComercial avaluo) {
		super();
		this.trayecto = trayecto;
		this.via = via;
		this.descripcion = descripcion;
		this.estado = estado;
		this.tipo = tipo;
		this.avaluo = avaluo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getTrayecto() {
		return trayecto;
	}

	public void setTrayecto(BigDecimal trayecto) {
		this.trayecto = trayecto;
	}

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public AvaluoComercial getAvaluo() {
		return avaluo;
	}

	public void setAvaluo(AvaluoComercial avaluo) {
		this.avaluo = avaluo;
	}

}
