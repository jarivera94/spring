package com.helio4.bancol.avaluos.modelo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Inmueble {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "inmueble_id")
	private Long id;
	@Column(name = "matricula_inmobiliaria")
	private String matriculaInmobiliaria;
	@Column(name = "cedula_catastral")
	private String cedulaCatastral;
	@Column(name = "numero_de_escritura")
	private String numeroDeEscritura;
	@Column(name = "chip")
	private String chip;
	@Column(name = "no_de_notaria_escritura")
	private Integer numeroDeNotariaEscritura;
	@Column(name = "departamento_escritura")
	private String departamentoEscritura;
	@Column(name = "municipio_escritura")
	private String municipioEscritura;
	@Column(name = "fecha_escritura")
	private Date fechaEscritura;
	@Column(name = "latitud")
	private BigDecimal latitud;
	@Column(name = "longitud")
	private BigDecimal longitud;
	@ManyToOne
	@JoinColumn(name="avaluo_id", nullable=false)
	private AvaluoComercial avaluo;
	@ManyToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
	@JoinTable(name="propietario_inmueble",
				joinColumns= {@JoinColumn(name="inmueble_id", referencedColumnName="inmueble_id")},
				inverseJoinColumns= {@JoinColumn(name="tipo_documento_identificacion", referencedColumnName="tipo_documento_identificacion"),
				@JoinColumn(name="numero_documento", referencedColumnName="numero_documento")})
	private Set<Propietario> propietarios;
	
	public Inmueble() {
	}

	public Inmueble(String matriculaInmobiliaria, String cedulaCatastral,
			String numeroDeEscritura, String chip,
			Integer numeroDeNotariaEscritura, String departamentoEscritura,
			String municipioEscritura, Date fechaEscritura, BigDecimal latitud,
			BigDecimal longitud, AvaluoComercial avaluo) {
		super();
		this.matriculaInmobiliaria = matriculaInmobiliaria;
		this.cedulaCatastral = cedulaCatastral;
		this.numeroDeEscritura = numeroDeEscritura;
		this.chip = chip;
		this.numeroDeNotariaEscritura = numeroDeNotariaEscritura;
		this.departamentoEscritura = departamentoEscritura;
		this.municipioEscritura = municipioEscritura;
		this.fechaEscritura = fechaEscritura;
		this.latitud = latitud;
		this.longitud = longitud;
		this.avaluo = avaluo;
	}

	public void update(String matriculaInmobiliaria, String cedulaCatastral,
			String numeroDeEscritura, String chip,
			Integer numeroDeNotariaEscritura, String departamentoEscritura,
			String municipioEscritura, Date fechaEscritura, BigDecimal latitud,
			BigDecimal longitud, AvaluoComercial avaluo) {
		this.matriculaInmobiliaria = matriculaInmobiliaria;
		this.cedulaCatastral = cedulaCatastral;
		this.numeroDeEscritura = numeroDeEscritura;
		this.chip = chip;
		this.numeroDeNotariaEscritura = numeroDeNotariaEscritura;
		this.departamentoEscritura = departamentoEscritura;
		this.municipioEscritura = municipioEscritura;
		this.fechaEscritura = fechaEscritura;
		this.latitud = latitud;
		this.longitud = longitud;
		this.avaluo = avaluo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMatriculaInmobiliaria() {
		return matriculaInmobiliaria;
	}

	public void setMatriculaInmobiliaria(String matriculaInmobiliaria) {
		this.matriculaInmobiliaria = matriculaInmobiliaria;
	}

	public String getCedulaCatastral() {
		return cedulaCatastral;
	}

	public void setCedulaCatastral(String cedulaCatastral) {
		this.cedulaCatastral = cedulaCatastral;
	}

	public String getNumeroDeEscritura() {
		return numeroDeEscritura;
	}

	public void setNumeroDeEscritura(String numeroDeEscritura) {
		this.numeroDeEscritura = numeroDeEscritura;
	}

	public String getChip() {
		return chip;
	}

	public void setChip(String chip) {
		this.chip = chip;
	}

	public Integer getNumeroDeNotariaEscritura() {
		return numeroDeNotariaEscritura;
	}

	public void setNumeroDeNotariaEscritura(Integer numeroDeNotariaEscritura) {
		this.numeroDeNotariaEscritura = numeroDeNotariaEscritura;
	}

	public String getDepartamentoEscritura() {
		return departamentoEscritura;
	}

	public void setDepartamentoEscritura(String departamentoEscritura) {
		this.departamentoEscritura = departamentoEscritura;
	}

	public String getMunicipioEscritura() {
		return municipioEscritura;
	}

	public void setMunicipioEscritura(String municipioEscritura) {
		this.municipioEscritura = municipioEscritura;
	}

	public Date getFechaEscritura() {
		return fechaEscritura;
	}

	public void setFechaEscritura(Date fechaEscritura) {
		this.fechaEscritura = fechaEscritura;
	}

	public BigDecimal getLatitud() {
		return latitud;
	}

	public void setLatitud(BigDecimal latitud) {
		this.latitud = latitud;
	}

	public BigDecimal getLongitud() {
		return longitud;
	}

	public void setLongitud(BigDecimal longitud) {
		this.longitud = longitud;
	}

	public AvaluoComercial getAvaluo() {
		return avaluo;
	}

	public void setAvaluo(AvaluoComercial avaluo) {
		this.avaluo = avaluo;
	}

	public Set<Propietario> getPropietarios() {
		return propietarios;
	}

	public void setPropietarios(Set<Propietario> propietarios) {
		this.propietarios = propietarios;
	}

}
