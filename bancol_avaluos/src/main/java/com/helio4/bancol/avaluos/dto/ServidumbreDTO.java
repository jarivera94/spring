package com.helio4.bancol.avaluos.dto;

public class ServidumbreDTO {

	private Long id;
	private Integer tipoDeServidumbre;
	private String beneficiario;
	private Long avaluoId;
	
	public ServidumbreDTO() {
	}

	public ServidumbreDTO(Long id, Integer tipoDeServidumbre,
			String beneficiario, Long avaluoId) {
		super();
		this.id = id;
		this.tipoDeServidumbre = tipoDeServidumbre;
		this.beneficiario = beneficiario;
		this.avaluoId = avaluoId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTipoDeServidumbre() {
		return tipoDeServidumbre;
	}

	public void setTipoDeServidumbre(Integer tipoDeServidumbre) {
		if(new Integer(0).equals(tipoDeServidumbre)){
			tipoDeServidumbre = null;
		}
		this.tipoDeServidumbre = tipoDeServidumbre;
	}

	public String getBeneficiario() {
		return beneficiario;
	}

	public void setBeneficiario(String beneficiario) {
		this.beneficiario = beneficiario;
	}

	public Long getAvaluoId() {
		return avaluoId;
	}

	public void setAvaluoId(Long avaluoId) {
		this.avaluoId = avaluoId;
	}

}
