package com.helio4.bancol.avaluos.dto;

import java.math.BigDecimal;

import com.helio4.bancol.avaluos.dto.AvaluoDTO.MBR;

public class ViaAccesoDTO {
	
	private Long id;
	private BigDecimal trayecto;
	private String via;
	private String descripcion;
	private Integer estado;
	private Integer tipo;
	private Long avaluoId;
	
	public ViaAccesoDTO() {
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

	public MBR getEstado() {
		return MBR.fromKey(estado == null ? 0 : estado);
	}

	public void setEstado(MBR estado) {
		this.estado = estado.getKey();
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public Long getAvaluoId() {
		return avaluoId;
	}

	public void setAvaluoId(Long avaluoId) {
		this.avaluoId = avaluoId;
	}

}
