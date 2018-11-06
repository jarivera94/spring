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
@Table(name = "tarifa")
public class Tarifa {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="tarifa_id")
	private Long id;
	/**
	@Column(name="tipo_avaluo")
	private Integer tipoAvaluo;
	*/
	@Column(name="valor_minimo")
	private BigDecimal valorMinimo;
	@Column(name="valor_maximo")
	private BigDecimal valorMaximo;
	@Column
	private BigDecimal tarifa;
	@ManyToOne
	@JoinColumn(name="entidad_id", nullable = false)
	private Entidad entidad;
	@ManyToOne
	@JoinColumn(name="tipo_avaluo_id", nullable = false)
	private TipoAvaluo tipoAvaluo;
	@Column(name="porcentaje_perito")
	private BigDecimal porcentajePerito;

	public Tarifa() {}
	
	/*public Tarifa(Long id, BigDecimal valorMinimo, BigDecimal valorMaximo, BigDecimal tarifa, Entidad entidad, TipoAvaluo tipoAvaluo) {
		super();
		this.id = id;
		//this.tipoAvaluo = tipoAvaluo.getKey();
		this.valorMinimo = valorMinimo;
		this.valorMaximo = valorMaximo;
		this.tarifa = tarifa;
		this.entidad = entidad;
		this.tipoAvaluo = tipoAvaluo;
	}*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	public AvaluoDTO.TipoAvaluo getTipoAvaluo() {
		return AvaluoDTO.TipoAvaluo.fromKey(tipoAvaluo == null ? 0 : tipoAvaluo);
	}

	public void setTipoAvaluo(AvaluoDTO.TipoAvaluo tipoAvaluo) {
		this.tipoAvaluo = tipoAvaluo.getKey();
	}
	*/

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

	public Entidad getEntidad() {
		return entidad;
	}

	public void setEntidad(Entidad entidad) {
		this.entidad = entidad;
	}
	
	public TipoAvaluo getTipoAvaluo() {
		return tipoAvaluo;
	}

	public void setTipoAvaluo(TipoAvaluo tipoAvaluo) {
		this.tipoAvaluo = tipoAvaluo;
	}

	public BigDecimal getPorcentajePerito() {
		return porcentajePerito;
	}

	public void setPorcentajePerito(BigDecimal porcentajePerito) {
		this.porcentajePerito = porcentajePerito;
	}

}
