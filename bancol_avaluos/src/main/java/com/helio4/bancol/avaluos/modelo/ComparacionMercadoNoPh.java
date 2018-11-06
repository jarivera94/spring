package com.helio4.bancol.avaluos.modelo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "comparacion_mercado_no_ph")
@PrimaryKeyJoinColumn(name="metodo_valuacion_id")
public class ComparacionMercadoNoPh extends ComparacionMercadoPh{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "desviacion_terreno")
    private BigDecimal desviacionTerreno;
   
	@Column(name = "promedio_mc_terreno")
    private BigDecimal promedioM2Terreno;
    
	@Column(name = "coeficiente_variacion_terreno")
    private BigDecimal coeficienteVariacionTerreno;
    
	@Column(name = "limite_inferior_terreno")
    private BigDecimal limieteInferiorTerreno;
    
	@Column(name = "limite_superior_terreno")
    private BigDecimal limiteSuperiorTerreno;
	
	@Column(name = "limite_superior_integral")
	private BigDecimal limiteSuperiorIntegral;
	
	@Column(name = "limite_inferior_integral")
	private BigDecimal limiteInferiorIntegral;
	
	@Column(name = "promedio_mc_integral")
	private BigDecimal promedioM2Integral;
	
	@Column(name = "desviacion_integral")
	private BigDecimal desviacionIntegral;
	
	@Column(name = "coeficiente_variacion_integral")
	private BigDecimal coeficienteVariacionIntegral;
	

	public BigDecimal getDesviacionTerreno() {
		return desviacionTerreno;
	}

	public void setDesviacionTerreno(BigDecimal desviacionTerreno) {
		this.desviacionTerreno = desviacionTerreno;
	}

	public BigDecimal getPromedioM2Terreno() {
		return promedioM2Terreno;
	}

	public void setPromedioM2Terreno(BigDecimal promedioM2Terreno) {
		this.promedioM2Terreno = promedioM2Terreno;
	}

	public BigDecimal getCoeficienteVariacionTerreno() {
		return coeficienteVariacionTerreno;
	}

	public void setCoeficienteVariacionTerreno(
			BigDecimal coeficienteVariacionTerreno) {
		this.coeficienteVariacionTerreno = coeficienteVariacionTerreno;
	}

	public BigDecimal getLimieteInferiorTerreno() {
		return limieteInferiorTerreno;
	}

	public void setLimieteInferiorTerreno(BigDecimal limieteInferiorTerreno) {
		this.limieteInferiorTerreno = limieteInferiorTerreno;
	}

	public BigDecimal getLimiteSuperiorTerreno() {
		return limiteSuperiorTerreno;
	}

	public void setLimiteSuperiorTerreno(BigDecimal limiteSuperiorTerreno) {
		this.limiteSuperiorTerreno = limiteSuperiorTerreno;
	}

	public BigDecimal getLimiteSuperiorIntegral() {
		return limiteSuperiorIntegral;
	}

	public void setLimiteSuperiorIntegral(BigDecimal limiteSuperiorIntegral) {
		this.limiteSuperiorIntegral = limiteSuperiorIntegral;
	}

	public BigDecimal getLimiteInferiorIntegral() {
		return limiteInferiorIntegral;
	}

	public void setLimiteInferiorIntegral(BigDecimal limiteInferiorIntegral) {
		this.limiteInferiorIntegral = limiteInferiorIntegral;
	}

	public BigDecimal getPromedioM2Integral() {
		return promedioM2Integral;
	}

	public void setPromedioM2Integral(BigDecimal promedioM2Integral) {
		this.promedioM2Integral = promedioM2Integral;
	}

	public BigDecimal getDesviacionIntegral() {
		return desviacionIntegral;
	}

	public void setDesviacionIntegral(BigDecimal desviacionIntegral) {
		this.desviacionIntegral = desviacionIntegral;
	}

	public BigDecimal getCoeficienteVariacionIntegral() {
		return coeficienteVariacionIntegral;
	}

	public void setCoeficienteVariacionIntegral(
			BigDecimal coeficienteVariacionIntegral) {
		this.coeficienteVariacionIntegral = coeficienteVariacionIntegral;
	}
   
}
