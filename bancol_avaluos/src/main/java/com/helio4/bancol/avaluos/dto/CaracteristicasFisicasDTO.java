package com.helio4.bancol.avaluos.dto;

public class CaracteristicasFisicasDTO {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6754331434644728062L;
	private Long id;
	private Integer avaluoId;
	private String tipoInmueble;
	private Integer cantidad;

	public CaracteristicasFisicasDTO() {
		super();
	}

	public CaracteristicasFisicasDTO(Integer avaluoId, String tipoInmueble, Integer cantidad) {
		super();
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
