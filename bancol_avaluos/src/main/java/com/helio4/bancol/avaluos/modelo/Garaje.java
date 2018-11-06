package com.helio4.bancol.avaluos.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "garaje")
public class Garaje {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="tipo")
	private Integer tipo;

	@Column(name="numero_garaje")
	private String numeroGaraje;

	@Column(name="matricula_inmobiliaria")
	private String matriculaInmobiliaria;

	@Column(name="doble")
	private Boolean doble;

	@Column(name="cubierto")
	private Boolean cubierto;

	@Column(name="paralelo")
	private Boolean paralelo;

	@Column(name="servidumbre")
	private Boolean servidumbre;

	@ManyToOne
	@JoinColumn(name="avaluo_id", nullable=false)
	private Avaluo avaluo;

	public Garaje(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getNumeroGaraje() {
		return numeroGaraje;
	}

	public void setNumeroGaraje(String numeroGaraje) {
		this.numeroGaraje = numeroGaraje;
	}

	public String getMatriculaInmobiliaria() {
		return matriculaInmobiliaria;
	}

	public void setMatriculaInmobiliaria(String matriculaInmobiliaria) {
		this.matriculaInmobiliaria = matriculaInmobiliaria;
	}

	public Boolean getDoble() {
		return doble;
	}

	public void setDoble(Boolean doble) {
		this.doble = doble;
	}

	public Boolean getCubierto() {
		return cubierto;
	}

	public void setCubierto(Boolean cubierto) {
		this.cubierto = cubierto;
	}

	public Boolean getParalelo() {
		return paralelo;
	}

	public void setParalelo(Boolean paralelo) {
		this.paralelo = paralelo;
	}

	public Boolean getServidumbre() {
		return servidumbre;
	}

	public void setServidumbre(Boolean servidumbre) {
		this.servidumbre = servidumbre;
	}

	public Avaluo getAvaluo() {
		return avaluo;
	}

	public void setAvaluo(Avaluo avaluo) {
		this.avaluo = avaluo;
	}

}
