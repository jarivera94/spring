package com.helio4.bancol.avaluos.dominio;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.helio4.bancol.avaluos.dto.ComparacionMercadoPHDTO;
import com.helio4.bancol.avaluos.dto.OfertaDTO;


public abstract class ComparacionMercadoStrategy {
	
	protected ComparacionMercadoPHDTO metodo;
	
	
	public ComparacionMercadoStrategy(ComparacionMercadoPHDTO metodo){
		this.metodo = metodo;
	}
	/**
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * 
	 * */
	public ComparacionMercadoPHDTO calcularEstudio(ComparacionMercadoController comparacionMercadoController) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		List<OfertaDTO> ofertas = this.metodo.getOfertas();
		BigDecimal desviacion = comparacionMercadoController.calcularDesviacionEstandar(ofertas, "getValorM2Construccion" );
		BigDecimal promedioM2 = comparacionMercadoController.calcularPromedio(this.metodo.getOfertas(), "getValorM2Construccion");
		BigDecimal coeficienteVariacion = (promedioM2.compareTo(BigDecimal.ZERO)!=0 ) ?  desviacion.divide( promedioM2,2, RoundingMode.HALF_UP ):BigDecimal.ZERO;
		Integer numeroDatos = ofertas.size();
		BigDecimal raiz =  BigDecimal.valueOf(Math.sqrt( numeroDatos ));
		BigDecimal tstudent = comparacionMercadoController.calcularTN(numeroDatos);
		BigDecimal limiteInferior =  comparacionMercadoController.calcularLimiteInferior(promedioM2, tstudent, desviacion, raiz);
		BigDecimal limiteSuperior =  comparacionMercadoController.calcularLimiteSuperior(promedioM2, tstudent, desviacion, raiz);
		this.metodo.setDesviacion(desviacion);
		this.metodo.setPromedioM2(promedioM2);
		this.metodo.setCoeficienteVariacion(coeficienteVariacion);
		this.metodo.setNumeroDatos(numeroDatos);
		this.metodo.setRaiz(raiz);
		this.metodo.setTstudent(tstudent);
		this.metodo.setLimiteInferior(limiteInferior);
		this.metodo.setLimiteSuperior(limiteSuperior);
		return this.metodo;
	}
	public OfertaDTO calcularValorConstruccion(OfertaDTO oferta) {
		BigDecimal valorFinalDepurado = (oferta.getValorDepurado()!=null ) ? oferta.getValorDepurado().subtract( (oferta.getGarajesDepositos()== null ? BigDecimal.ZERO : oferta.getGarajesDepositos())  ) : BigDecimal.ZERO;
		oferta.setValorFinalDepurado(valorFinalDepurado);
		oferta.setConstruccion( valorFinalDepurado );
		BigDecimal valorConstruido = (oferta.getAreaConstruida()!=null && oferta.getAreaConstruida()!=0) ? valorFinalDepurado.divide( new BigDecimal(oferta.getAreaConstruida()),2, RoundingMode.HALF_UP ) : BigDecimal.ZERO;
		oferta.setValorM2Construccion(valorConstruido);
		return oferta;
	}
	
	
}
