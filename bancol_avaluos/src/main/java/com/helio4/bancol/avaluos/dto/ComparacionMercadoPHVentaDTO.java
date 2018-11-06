package com.helio4.bancol.avaluos.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.helio4.bancol.avaluos.dto.GarajeDTO.ListaTipoGaraje;
import com.helio4.bancol.avaluos.modelo.ComparacionMercadoPhVenta;
import com.helio4.bancol.avaluos.modelo.OfertaPH;
import com.jidesoft.utils.BigDecimalMathUtils;


public class ComparacionMercadoPHVentaDTO extends ComparacionMercadoPHDTO implements Serializable,Cloneable{

	private static final long serialVersionUID = 7044367860879413204L;

	protected BigDecimal promedioM2Homogenizado;
	protected BigDecimal mediaAritmetica;
	protected BigDecimal desviacionEstandar;
	
	public ComparacionMercadoPHVentaDTO(){}

	public ComparacionMercadoPHVentaDTO(Integer metodoUsado, Long avaluoId){
        super(metodoUsado, avaluoId);
    }
	
	public ComparacionMercadoPHVentaDTO(ComparacionMercadoPhVenta comparacion){
		super(comparacion);
		
		this.promedioM2Homogenizado =  comparacion.getPromedioM2Homogenizado();
		this.mediaAritmetica = comparacion.getMediaAritmetica();
		this.desviacionEstandar = comparacion.getDesviacionEstandar();
		
	}

	public ComparacionMercadoPHVentaDTO(MetodoValuacionDTO metodo){
		this.setId(metodo.getId());
		this.setAvaluoId( metodo.getAvaluoId() );
		this.setConceptoDelMetodo( metodo.getConceptoDelMetodo()  );
		this.setMetodoUsado( metodo.getMetodoUsado() );
		this.setOfertasPH(new ArrayList<>());
	}
	
	public ComparacionMercadoPHVentaDTO(
			Long id, Integer metodoUsado,
			String conceptoDelMetodo, Long avaluoId,
			BigDecimal factorSuperficie,
			BigDecimal factorEdad,
			BigDecimal promedioValorM2HomogenizadoSinGaraje,
			BigDecimal promedioValorM2SinGarajeNoHomogenizado,
			BigDecimal desviacionEstandar,
			BigDecimal coeficienteVariacion,
			List<OfertaPHDTO> ofertasPH ) {
		this.setId(id);
		this.avaluoId = avaluoId;
		this.metodoUsado = metodoUsado;
		this.conceptoDelMetodo = conceptoDelMetodo;
		this.avaluoId = avaluoId;
		this.factorSuperficie = factorSuperficie;
		this.factorEdad = factorEdad;
		this.promedioValorM2HomogenizadoSinGaraje = promedioValorM2HomogenizadoSinGaraje;
		this.promedioValorM2SinGarajeNoHomogenizado = promedioValorM2SinGarajeNoHomogenizado;
		this.desviacionEstandar = desviacionEstandar;
		this.coeficienteVariacion = coeficienteVariacion;
		this.ofertasPH = ofertasPH;
	}

	public BigDecimal getDesviacionEstandar() {
		return desviacionEstandar;
	}

	public void setDesviacionEstandar(BigDecimal desviacionEstandar) {
		this.desviacionEstandar = desviacionEstandar;
	}

	public BigDecimal getPromedioM2Homogenizado() {
		return promedioM2Homogenizado;
	}

	public void setPromedioM2Homogenizado(BigDecimal promedioM2Homogenizado) {
		this.promedioM2Homogenizado = promedioM2Homogenizado;
	}

	public BigDecimal getMediaAritmetica() {
		return mediaAritmetica;
	}

	public void setMediaAritmetica(BigDecimal mediaAritmetica) {
		this.mediaAritmetica = mediaAritmetica;
	}

	public Object clone() {
		List<OfertaPHDTO> ofertasClonadas = new  ArrayList<OfertaPHDTO>();
    	if(this.getOfertasPH()!=null) {
    		for(OfertaPHDTO oferta: this.ofertasPH) {
    			OfertaPHDTO clone = (OfertaPHDTO) oferta.clone();
    			ofertasClonadas.add(clone);
    		}
    	}
		ComparacionMercadoPHVentaDTO clone = new ComparacionMercadoPHVentaDTO(
				this.getId(), this.metodoUsado,
				this.conceptoDelMetodo, this.avaluoId, this.factorSuperficie,
				this.factorEdad, this.promedioValorM2HomogenizadoSinGaraje, 
				this.promedioValorM2SinGarajeNoHomogenizado,
				this.desviacionEstandar, this.coeficienteVariacion,
				ofertasClonadas);
		return clone;
	}
	public String toString(){
		String value = "Comparacion de Mercado PH Venta["+this.getId()+"] \n";
		value += "	Avaluo id: "	+ this.avaluoId+"\n";
		if (this.ofertasPH != null) {
			for( OfertaDTO oferta: this.ofertasPH){
				value += oferta.toString();
			}
		}
		value += " 		factorSuperficie: " 			+ this.factorSuperficie+"\n";
		value += " 		factorEdad: " 			+ this.factorEdad +"\n";
		value += " 		Coeficiente Variacion: "	+ this.coeficienteVariacion +"\n";
		value += " 		promedioValorM2HomogenizadoSinGaraje: "		+ this.promedioValorM2HomogenizadoSinGaraje +"\n";
		value += " 		promedioValorM2SinGarajeNoHomogenizado: "					+ this.promedioValorM2SinGarajeNoHomogenizado + "\n";
		value += " 		desviacionEstandar: "				+ this.desviacionEstandar +"\n";
		value += " 		coeficienteVariacion: "		+ this.coeficienteVariacion+"\n";
		return value;
	}

	public void calcularCoheficienteVariacion() {

		if (desviacionEstandar != null && promedioValorM2HomogenizadoSinGaraje != null
				&& promedioValorM2HomogenizadoSinGaraje.doubleValue() > 0) {
			coeficienteVariacion = new BigDecimal(
					desviacionEstandar.doubleValue() / promedioValorM2HomogenizadoSinGaraje.doubleValue()).multiply(new BigDecimal(100));
		} else {
			coeficienteVariacion = BigDecimal.ZERO;
		}

	}

	public void calcularDesviacionEstandar() {

		List<BigDecimal> listaValorM2HomogenizadoSinGaraje = new ArrayList<>();
		
		for(OfertaPHDTO oferta: ofertasPH){
			
			if(oferta.getValorMetroHomogenizadoSinGaraje() != null){
				listaValorM2HomogenizadoSinGaraje.add(oferta.getValorMetroHomogenizadoSinGaraje());
			}
		}
		
		if(listaValorM2HomogenizadoSinGaraje!= null && listaValorM2HomogenizadoSinGaraje.size()>1){
			BigDecimal promedio = BigDecimalMathUtils.sum(listaValorM2HomogenizadoSinGaraje).divide(BigDecimal.valueOf(listaValorM2HomogenizadoSinGaraje.size()), 8, RoundingMode.HALF_UP);
			
			BigDecimal sumatoria = BigDecimal.ZERO;
			
			for(BigDecimal x : listaValorM2HomogenizadoSinGaraje){
				BigDecimal diferencia = x.subtract(promedio);
				
				BigDecimal cuadrado = BigDecimal.valueOf(Math.pow(diferencia.doubleValue(), 2));
				sumatoria = sumatoria.add(cuadrado);
			}
			
			BigDecimal varianza = sumatoria.divide(BigDecimal.valueOf(listaValorM2HomogenizadoSinGaraje.size()-1), 8, RoundingMode.HALF_UP);
			
			desviacionEstandar = BigDecimalMathUtils.sqrt(varianza);
		}else{
			desviacionEstandar = BigDecimal.ZERO;
		}

		/*if (!listaValorM2HomogenizadoSinGaraje.isEmpty() && !listaValorM2HomogenizadoSinGaraje.contains(null)) {
			desviacionEstandar = BigDecimalMathUtils.stddev(listaValorM2HomogenizadoSinGaraje, true,
					new MathContext(4, RoundingMode.HALF_EVEN));
		}else{
			desviacionEstandar = BigDecimal.ZERO;
		}*/

	}
	
	public void calcularPromedioM2Homogenizado(){
		this.promedioM2Homogenizado =  this.promedioValorM2HomogenizadoSinGaraje;
	}
	
	public void calcularPromedioM2(){
		this.promedioM2 =  this.promedioValorM2SinGarajeNoHomogenizado;
	}
	
	public void calcularMediaAritmetica(){
		if(promedioValorM2HomogenizadoSinGaraje!= null){
		this.mediaAritmetica =  this.promedioValorM2HomogenizadoSinGaraje;
		}else{
			this.mediaAritmetica = BigDecimal.ZERO;	
		}
	}

	public void calcularValorM2HomogenizadoGJ(ListaTipoGaraje tipoGaraje) {

		if (tipoGaraje != null) {

			if (tipoGaraje.equals(ListaTipoGaraje.Privado) && this.promedioM2Homogenizado != null) {
				this.valorm2homogenizadoGJ = this.promedioM2Homogenizado;

			} else if (tipoGaraje.equals(ListaTipoGaraje.Exclusivo) && this.promedioM2Homogenizado != null
					&& this.promedioValorUnitarioGaraje != null && this.area != null
					&& this.area.compareTo(BigDecimal.ZERO) != 0) {

				this.garajes = this.garajes == null ? 0 : this.garajes;

				this.valorm2homogenizadoGJ = this.promedioM2Homogenizado
						.add(new BigDecimal(this.garajes).multiply(this.promedioValorUnitarioGaraje)
						.divide(this.area, 8, BigDecimal.ROUND_HALF_UP));

			} else {
				this.valorm2homogenizadoGJ = BigDecimal.ZERO;
			}

		}

	}

}
