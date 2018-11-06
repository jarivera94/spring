package com.helio4.bancol.avaluos.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class ComparacionMercadoNOPHDTO extends ComparacionMercadoPHDTO implements Serializable {

	private static final long serialVersionUID = 168703563484769561L;
	private BigDecimal desviacionTerreno;
	private BigDecimal promedioM2Terreno;
	private BigDecimal coeficienteVariacionTerreno;
	private BigDecimal limiteInferiorTerreno;
	private BigDecimal limiteSuperiorTerreno;
	private BigDecimal limiteSuperiorIntegral;
	private BigDecimal limiteInferiorIntegral;
	private BigDecimal promedioM2Integral;
	private BigDecimal desviacionIntegral;
	private BigDecimal coeficienteVariacionIntegral;

	public ComparacionMercadoNOPHDTO() {}

	public ComparacionMercadoNOPHDTO(Integer metodoUsado, Long avaluoId) {
        super(metodoUsado, avaluoId);
    }

	public ComparacionMercadoNOPHDTO(MetodoValuacionDTO metodo) {
		this.setId(metodo.getId());
		this.setAvaluoId( metodo.getAvaluoId() );
		this.setConceptoDelMetodo( metodo.getConceptoDelMetodo()  );
		this.setMetodoUsado( metodo.getMetodoUsado() );
		this.setOfertas((metodo.getOfertas()!=null)? metodo.getOfertas(): new ArrayList<OfertaDTO>()  );
	}
	public ComparacionMercadoNOPHDTO(
			Long id, Integer metodoUsado,
			String conceptoDelMetodo, Long avaluoId, BigDecimal desviacion,
			BigDecimal promedioM2,
			BigDecimal coeficienteVariacion,
			Integer numeroDatos,
			BigDecimal raiz,
			BigDecimal tstudent,
			BigDecimal limiteInferior,
			BigDecimal limiteSuperior,
			BigDecimal desviacionTerreno,
			BigDecimal promedioM2Terreno,
			BigDecimal coeficienteVariacionTerreno,
			BigDecimal limiteInferiorTerreno,
			BigDecimal limiteSuperiorTerreno,
			BigDecimal limiteSuperiorIntegral,
			BigDecimal limiteInferiorIntegral,
			BigDecimal promedioM2Integral,
			BigDecimal desviacionIntegral,
			BigDecimal coeficienteVariacionIntegral,List<OfertaDTO> ofertas) {
		this.setId(id); 
		this.avaluoId = avaluoId;
		this.metodoUsado = metodoUsado;
		this.conceptoDelMetodo = conceptoDelMetodo;
		this.avaluoId = avaluoId;
		this.desviacion = desviacion;
		this.promedioM2 = promedioM2;
		this.coeficienteVariacion = coeficienteVariacion;
		this.numeroDatos = numeroDatos;
		this.raiz = raiz;
		this.tstudent = tstudent;
		this.limiteInferior = limiteInferior;
		this.limiteSuperior = limiteSuperior;	
		this.desviacionTerreno = desviacionTerreno;
		this.promedioM2Terreno =  promedioM2Terreno;
		this.coeficienteVariacionTerreno = coeficienteVariacionTerreno;
		this.limiteInferiorTerreno = limiteSuperiorIntegral;
		this.limiteInferiorIntegral = limiteInferior;
		this.promedioM2Integral = promedioM2Integral;
		this.desviacionIntegral =desviacionIntegral;
		this.coeficienteVariacionIntegral = coeficienteVariacionIntegral;
		this.ofertas = ofertas;
	}
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

	public BigDecimal getLimiteInferiorTerreno() {
		return limiteInferiorTerreno;
	}

	public void setLimiteInferiorTerreno(BigDecimal limiteInteriorTerreno) {
		this.limiteInferiorTerreno = limiteInteriorTerreno;
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
	
	@Override
	public Object clone() {
		List<OfertaDTO> ofertasClonadas = new  ArrayList<OfertaDTO>();
    	if(this.getOfertas()!=null) {
    		for(OfertaDTO oferta: this.ofertas) {
    			OfertaDTO clone = (OfertaDTO) oferta.clone();
    			ofertasClonadas.add(clone);
    		}
    	}
		ComparacionMercadoNOPHDTO clone = new ComparacionMercadoNOPHDTO(this.getId(), this.metodoUsado,
				this.conceptoDelMetodo,this.avaluoId,this.desviacion,
				this.promedioM2,
				this.coeficienteVariacion,
				this.numeroDatos,
				this.raiz,
				this.tstudent,
				this.limiteInferior,
				this.limiteSuperior,
				this.desviacionTerreno,
				this.promedioM2Terreno,
				this.coeficienteVariacionTerreno,
				this.limiteInferiorTerreno,
				this.limiteSuperiorTerreno,
				this.limiteSuperiorIntegral,
				this.limiteInferiorIntegral,
				this.promedioM2Integral,
				this.desviacionIntegral,
				this.coeficienteVariacionIntegral,ofertas);
		return clone;
	}
	public String toString(){
		String value = "Comparacion de Mercado NO PH ["+this.getId()+"] \n";
		value += "	Avaluo id: "	+ this.avaluoId+"\n";
		if (this.ofertas != null) {
			for( OfertaDTO oferta: this.ofertas){
				value += oferta.toString();
			}
		}
		value += " 		Desviacion: " 						+ this.desviacion+"\n";
		value += " 		Promedio M2: " 						+ this.promedioM2 +"\n";
		value += " 		Coeficiente Variacion: "			+ this.coeficienteVariacion +"\n";
		value += " 		Limite inferior: "					+ this.limiteInferior+"\n";
		value += " 		Limite superior: "					+ this.limiteSuperior +" \n";
		
		value += " 		Desviacion Terreno: "				+ this.desviacionTerreno+"\n";
		value += " 		Promedio M2 terreno: "				+ this.promedioM2Terreno+"\n";
		value += " 		Coeficiente variacion Terreno: "	+ this.coeficienteVariacionTerreno+"\n";
		value += " 		Limite inferior terreno: "			+ this.limiteInferiorTerreno+"\n";
		value += " 		Limite superior terreno: "			+ this.limiteSuperiorTerreno+"\n";
		
		value += " 		Desviacion Integral: "				+ this.desviacionIntegral+"\n";
		value += " 		Promedio M2 Integral: "				+ this.promedioM2Integral+"\n";
		value += " 		Coeficiente variacion Integral: "	+ this.coeficienteVariacionIntegral+"\n";
		value += " 		Limite superior integral: "			+ this.limiteSuperiorIntegral+"\n";
		value += " 		Limite inferior integral: "			+ this.limiteInferiorIntegral+"\n";
		
		
		value += " 		Numero de datos: "					+ this.numeroDatos +"\n";
		value += " 		Raiz: "								+ this.raiz + "\n";
		value += " 		TStudent: "							+ this.tstudent +"\n";
		return value;
	}
}
