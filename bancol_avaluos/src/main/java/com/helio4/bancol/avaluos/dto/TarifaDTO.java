package com.helio4.bancol.avaluos.dto;

import java.math.BigDecimal;

public class TarifaDTO {

	private Long id;
	private BigDecimal valorMinimo;
	private BigDecimal valorMaximo;
	private BigDecimal tarifa;
	private Long entidadId;	
	private Long tipoAvaluoId;
	private BigDecimal porcentajePerito;
	// Campo adicional
	private String nombreTipoAvaluo;
	private String tarifaConComa;

	public TarifaDTO() {}
	
	public TarifaDTO(Long id, BigDecimal valorMinimo,
			BigDecimal valorMaximo, BigDecimal tarifa,
			Long entidadId, Long tipoAvaluoId, String nomnbreTipoAvaluo) {
		super();
		this.id = id;
		this.valorMinimo = valorMinimo;
		this.valorMaximo = valorMaximo;
		this.tarifa = tarifa;
		this.entidadId = entidadId;		
		this.tipoAvaluoId = tipoAvaluoId;		
		this.nombreTipoAvaluo = nomnbreTipoAvaluo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValorMinimo() {
		return valorMinimo;
	}

	public void setValorMinimo(BigDecimal valorMinimo) {
		this.valorMinimo = valorMinimo;
	}

	public BigDecimal getValorMaximo() {
		return valorMaximo;
	}

	public void setValorMaximo(BigDecimal valorMaximo) {
		this.valorMaximo = valorMaximo;
	}

	public BigDecimal getTarifa() {
		return tarifa;
	}

	public void setTarifa(BigDecimal tarifa) {
		this.tarifa = tarifa;
	}	

	public Long getEntidadId() {
		return entidadId;
	}

	public void setEntidadId(Long entidadId) {
		this.entidadId = entidadId;
	}

	public Long getTipoAvaluoId() {
		return tipoAvaluoId;
	}

	public void setTipoAvaluoId(Long tipoAvaluoId) {
		this.tipoAvaluoId = tipoAvaluoId;
	}

	public String getNombreTipoAvaluo() {
		return nombreTipoAvaluo;
	}

	public void setNombreTipoAvaluo(String nombreTipoAvaluo) {
		this.nombreTipoAvaluo = nombreTipoAvaluo;
	}

	public String getTarifaConComa() {
		return tarifaConComa;
	}

	public void setTarifaConComa(String tarifaConComa) {
		this.tarifaConComa = tarifaConComa;
	}

	public BigDecimal getPorcentajePerito() {
		return porcentajePerito;
	}

	public void setPorcentajePerito(BigDecimal porcentajePerito) {
		this.porcentajePerito = porcentajePerito;
	}

}
