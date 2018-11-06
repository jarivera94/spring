package com.helio4.bancol.avaluos.dto;

import java.io.Serializable;

import javax.persistence.Column;

public class DivipolaDTO implements Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9023399109477399068L;
	private Long id;
	private String municipio;
	private String centroPoblado;
	private String departamento;
	private int codigoDepartamento;
	private int codigoMunicipio;
	private int codigoCentroPoblado;
	private int departamentoBUA;
	private int municipioBUA;
	private RegionalDTO regional;
	private Boolean capital;
	private Integer departamentoIdTinsa;
	private Integer municipioIdTinsa;

	public DivipolaDTO() {
		this.departamento = "";
		this.centroPoblado = "";
	}

	public DivipolaDTO(Long id, String departamento, String centroPoblado, String municipio) {
		this.id = id;
		this.departamento = departamento;
		this.centroPoblado = centroPoblado;
		this.municipio = municipio;

	}

	public DivipolaDTO(Long id, String municipio, String centroPoblado, String departamento, int codigoDepartamento,
			int codigoMunicipio, int codigoCentroPoblado, int departamentoBUA, int municipioBUA, RegionalDTO regional,
			Boolean capital) {
		super();
		this.id = id;
		this.municipio = municipio;
		this.centroPoblado = centroPoblado;
		this.departamento = departamento;
		this.codigoDepartamento = codigoDepartamento;
		this.codigoMunicipio = codigoMunicipio;
		this.codigoCentroPoblado = codigoCentroPoblado;
		this.departamentoBUA = departamentoBUA;
		this.municipioBUA = municipioBUA;
		this.regional = regional;
		this.capital = capital;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getCentroPoblado() {
		return centroPoblado;
	}

	public void setCentroPoblado(String centroPoblado) {
		this.centroPoblado = centroPoblado;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public int getCodigoDepartamento() {
		return codigoDepartamento;
	}

	public void setCodigoDepartamento(int codigoDepartamento) {
		this.codigoDepartamento = codigoDepartamento;
	}

	public int getCodigoMunicipio() {
		return codigoMunicipio;
	}

	public void setCodigoMunicipio(int codigoMunicipio) {
		this.codigoMunicipio = codigoMunicipio;
	}

	public int getCodigoCentroPoblado() {
		return codigoCentroPoblado;
	}

	public void setCodigoCentroPoblado(int codigoCentroPoblado) {
		this.codigoCentroPoblado = codigoCentroPoblado;
	}

	public int getDepartamentoBUA() {
		return departamentoBUA;
	}

	public void setDepartamentoBUA(int departamentoBUA) {
		this.departamentoBUA = departamentoBUA;
	}

	public int getMunicipioBUA() {
		return municipioBUA;
	}

	public void setMunicipioBUA(int municipioBUA) {
		this.municipioBUA = municipioBUA;
	}

	public RegionalDTO getRegional() {
		return regional;
	}

	public void setRegional(RegionalDTO regional) {
		this.regional = regional;
	}

	public Boolean getCapital() {
		return capital;
	}

	public void setCapital(Boolean capital) {
		this.capital = capital;
	}

	public Integer getDepartamentoIdTinsa() {
		return departamentoIdTinsa;
	}

	public void setDepartamentoIdTinsa(Integer departamentoIdTinsa) {
		this.departamentoIdTinsa = departamentoIdTinsa;
	}

	public Integer getMunicipioIdTinsa() {
		return municipioIdTinsa;
	}

	public void setMunicipioIdTinsa(Integer municipioIdTinsa) {
		this.municipioIdTinsa = municipioIdTinsa;
	}

	public String toString() {
		return String.format("%s[id=%d]", getClass().getSimpleName(), getId());
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new DivipolaDTO(id, municipio, centroPoblado, departamento, codigoDepartamento, codigoMunicipio,
				codigoCentroPoblado, departamentoBUA, municipioBUA, regional, capital);
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DivipolaDTO other = (DivipolaDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
