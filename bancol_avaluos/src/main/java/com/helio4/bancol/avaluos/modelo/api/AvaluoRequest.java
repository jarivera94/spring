package com.helio4.bancol.avaluos.modelo.api;

import java.math.BigDecimal;

public class AvaluoRequest {

	private Long idAvaluoSisgen;
	private EntidadRequest entidad;
	private String codigoExterno;
	private String accion;
	private Long tipoAvaluo;
	private BigDecimal valorSolicitado;
	private Long objetoAvaluo;
	private Boolean soloAvaluo;
	private ClienteRequest cliente;
	private Integer municipioId;
	private Integer departamentoId;
	private PeritoRequest perito;

	public AvaluoRequest() {
		// TODO Auto-generated constructor stub
	}

	public String getCodigoExterno() {
		return codigoExterno;
	}

	public void setCodigoExterno(String codigoExterno) {
		this.codigoExterno = codigoExterno;
	}

	public EntidadRequest getEntidad() {
		return entidad;
	}

	public void setEntidad(EntidadRequest entidad) {
		this.entidad = entidad;
	}

	public Long getTipoAvaluo() {
		return tipoAvaluo;
	}

	public void setTipoAvaluo(Long tipoAvaluo) {
		this.tipoAvaluo = tipoAvaluo;
	}

	public BigDecimal getValorSolicitado() {
		return valorSolicitado;
	}

	public void setValorSolicitado(BigDecimal valorSolicitado) {
		this.valorSolicitado = valorSolicitado;
	}

	public Long getObjetoAvaluo() {
		return objetoAvaluo;
	}

	public void setObjetoAvaluo(Long objetoAvaluo) {
		this.objetoAvaluo = objetoAvaluo;
	}

	public Boolean getSoloAvaluo() {
		return soloAvaluo;
	}

	public void setSoloAvaluo(Boolean soloAvaluo) {
		this.soloAvaluo = soloAvaluo;
	}

	public ClienteRequest getCliente() {
		return cliente;
	}

	public void setCliente(ClienteRequest cliente) {
		this.cliente = cliente;
	}

	public PeritoRequest getPerito() {
		return perito;
	}

	public void setPerito(PeritoRequest perito) {
		this.perito = perito;
	}

	public Integer getMunicipioId() {
		return municipioId;
	}

	public void setMunicipioId(Integer municipioId) {
		this.municipioId = municipioId;
	}

	public Integer getDepartamentoId() {
		return departamentoId;
	}

	public void setDepartamentoId(Integer departamentoId) {
		this.departamentoId = departamentoId;
	}

	public Long getIdAvaluoSisgen() {
		return idAvaluoSisgen;
	}

	public void setIdAvaluoSisgen(Long idAvaluoSisgen) {
		this.idAvaluoSisgen = idAvaluoSisgen;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	@Override
	public String toString() {
		return "AvaluoRequest [idAvaluoSisgen=" + idAvaluoSisgen + ", entidad=" + entidad.toString() + ", codigoExterno="
				+ codigoExterno + ", accion=" + accion + ", tipoAvaluo=" + tipoAvaluo + ", valorSolicitado="
				+ valorSolicitado + ", objetoAvaluo=" + objetoAvaluo + ", soloAvaluo=" + soloAvaluo + ", cliente="
				+ cliente.toString() + ", municipioId=" + municipioId + ", departamentoId=" + departamentoId + ", perito=" + perito.toString()
				+ "]";
	}
	
	

}
