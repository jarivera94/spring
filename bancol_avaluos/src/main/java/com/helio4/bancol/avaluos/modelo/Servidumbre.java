package com.helio4.bancol.avaluos.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Servidumbre {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "servidumbre_id")
	private Long id;
	@Column(name = "tipo_de_servidumbre")
	private Integer tipoDeServidumbre;
	@Column(name = "beneficiario")
	private String beneficiario;
	@ManyToOne
	@JoinColumn(name = "avaluo_id")
	private AvaluoComercial avaluo;
	
	public Servidumbre() {
	}

	public Servidumbre(Integer tipoDeServidumbre, String beneficiario,
			AvaluoComercial avaluo) {
		super();
		this.tipoDeServidumbre = tipoDeServidumbre;
		this.beneficiario = beneficiario;
		this.avaluo = avaluo;
	}
	
	public void update(Integer tipoDeServidumbre, String beneficiario,
			AvaluoComercial avaluo) {
		this.tipoDeServidumbre = tipoDeServidumbre;
		this.beneficiario = beneficiario;
		this.avaluo = avaluo;
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
		this.tipoDeServidumbre = tipoDeServidumbre;
	}

	public String getBeneficiario() {
		return beneficiario;
	}

	public void setBeneficiario(String beneficiario) {
		this.beneficiario = beneficiario;
	}

	public AvaluoComercial getAvaluo() {
		return avaluo;
	}

	public void setAvaluo(AvaluoComercial avaluo) {
		this.avaluo = avaluo;
	}

}
