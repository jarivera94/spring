package com.helio4.bancol.avaluos.dominio;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import com.helio4.bancol.avaluos.dto.AreaDTO;
import com.helio4.bancol.avaluos.dto.ComparacionMercadoNOPHDTO;
import com.helio4.bancol.avaluos.dto.ComparacionMercadoPHDTO;
import com.helio4.bancol.avaluos.dto.OfertaDTO;


public class ComparacionMercadoNoPHStrategy extends ComparacionMercadoStrategy {

	
	private FitoCorviniController fitoCorviniController;

	public ComparacionMercadoNoPHStrategy(ComparacionMercadoNOPHDTO metodo) {
		super(metodo);
		this.fitoCorviniController = new FitoCorviniController();
	}

	/**
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * */
	public ComparacionMercadoPHDTO calcularEstudio(ComparacionMercadoController comparacionMercadoController) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		this.metodo = super.calcularEstudio(comparacionMercadoController);

		ComparacionMercadoNOPHDTO resultado = (ComparacionMercadoNOPHDTO) this.metodo;
		BigDecimal promedioM2Terreno  =  comparacionMercadoController.calcularPromedio(this.metodo.getOfertas(), "getValorM2Terreno");
		BigDecimal desviacionTerreno =  comparacionMercadoController.calcularDesviacionEstandar(this.metodo.getOfertas(), "getValorM2Terreno");
		BigDecimal coeficienteVariacionTerreno = (promedioM2Terreno.compareTo(BigDecimal.ZERO)==0 ) ? BigDecimal.ZERO : desviacionTerreno.divide( promedioM2Terreno,8,RoundingMode.HALF_UP );
		
		BigDecimal limiteSuperiorTerreno = comparacionMercadoController.calcularLimiteSuperior(promedioM2Terreno, this.metodo.getTstudent(), desviacionTerreno,this.metodo.getRaiz());
		BigDecimal limiteInferiorTerreno = comparacionMercadoController.calcularLimiteInferior(promedioM2Terreno, this.metodo.getTstudent(), desviacionTerreno,this.metodo.getRaiz());
		
		resultado.setPromedioM2Terreno(promedioM2Terreno);
		resultado.setDesviacionTerreno(desviacionTerreno);
		resultado.setCoeficienteVariacionTerreno(coeficienteVariacionTerreno);
		resultado.setLimiteInferiorTerreno(limiteInferiorTerreno);
		resultado.setLimiteSuperiorTerreno(limiteSuperiorTerreno);
		
		BigDecimal promedioIntegral  			=  comparacionMercadoController.calcularPromedio(this.metodo.getOfertas(), "getValorIntegralConstruccion");
		BigDecimal desviacionIntegral 			=  comparacionMercadoController.calcularDesviacionEstandar(this.metodo.getOfertas(), "getValorIntegralConstruccion");
		BigDecimal coeficienteVariacionIntegral = (promedioIntegral.compareTo(BigDecimal.ZERO)==0 ) ? BigDecimal.ZERO : desviacionTerreno.divide( promedioIntegral,8,RoundingMode.HALF_UP );
	

		BigDecimal limiteSuperiorIntegral = comparacionMercadoController.calcularLimiteSuperior(promedioIntegral, this.metodo.getTstudent(), desviacionIntegral,this.metodo.getRaiz());
		BigDecimal limiteInferiorIntegral = comparacionMercadoController.calcularLimiteInferior(promedioIntegral, this.metodo.getTstudent(), desviacionIntegral,this.metodo.getRaiz());
		
		resultado.setPromedioM2Integral(promedioIntegral);
		resultado.setDesviacionIntegral(desviacionIntegral);
		resultado.setCoeficienteVariacionIntegral(coeficienteVariacionIntegral);
		resultado.setLimiteInferiorIntegral(limiteInferiorIntegral);
		resultado.setLimiteSuperiorIntegral(limiteSuperiorIntegral);
		
		return resultado;
	}

	public OfertaDTO calcularValorConstruccion(OfertaDTO ofertaDTO){
		AreaDTO areaDTO = ofertaDTO.getAreaTemporal();
		areaDTO = this.escribirAreaOferta( ofertaDTO, areaDTO );
		areaDTO = this.fitoCorviniController.calcularFitoCorvini(areaDTO);	
		areaDTO = this.fitoCorviniController.calcularValorReposicion(areaDTO);	
		ofertaDTO.setValorM2Construccion( areaDTO.getValorAdoptado() );
		ofertaDTO = this.getValorConstruccion(ofertaDTO);
		BigDecimal 	valorDepurado = ofertaDTO.getValorDepurado()   !=null ? ofertaDTO.getValorDepurado() : BigDecimal.ZERO;
		BigDecimal valorConstruccion = ofertaDTO.getConstruccion() !=null ? ofertaDTO.getConstruccion()  : BigDecimal.ZERO;
		BigDecimal totalTerreno = valorDepurado.subtract( valorConstruccion );
		ofertaDTO.setTotalTerreno(totalTerreno);
		
		BigDecimal areaConstruccion  = ofertaDTO.getAreaConstruida() !=null ? new  BigDecimal( ofertaDTO.getAreaConstruida() ) : BigDecimal.ZERO;
		BigDecimal valorIntegralConstruccion  = ( areaConstruccion.compareTo( BigDecimal.ZERO) == 0 ) ?   BigDecimal.ZERO : valorDepurado.divide( areaConstruccion,4, RoundingMode.HALF_UP);
		
		ofertaDTO.setValorIntegralConstruccion(valorIntegralConstruccion);
		return ofertaDTO;
	}
	
	/**
	 * Función que calcula el valor de la construcción de una oferta.
	 * 
	 * @param OfertaDTO  sobre la cual se calcula el valor de construcción
	 * @return OfertaDTO después de realizar el calculo.
	 * */
	public OfertaDTO getValorConstruccion(OfertaDTO oferta){
		BigDecimal valorConstruccion = BigDecimal.ZERO;
		BigDecimal area = new BigDecimal( oferta.getAreaConstruida()!=null ? oferta.getAreaConstruida() : 0 );
		BigDecimal valorM2Construccion = oferta.getValorM2Construccion();
		valorConstruccion = area.multiply( valorM2Construccion!=null ? valorM2Construccion : BigDecimal.ZERO ); 
		oferta.setConstruccion( valorConstruccion!=null ? valorConstruccion : BigDecimal.ZERO ); 
		return oferta;
	}
	/**
	 * Método que se encarga de hacer la transacción de una OfertaDTO a 
	 * una AreaDTO, la cual se empleara para calcular fito y corvini. 
	 * 
	 * @param OfertaDTO
	 * @param AreaDTO
	 * 
	 * @return AreaDTO
	 * */
	private AreaDTO escribirAreaOferta(OfertaDTO o, AreaDTO a){
		a.setArea( ( o.getAreaConstruida() !=null ) ? new BigDecimal(o.getAreaConstruida() ) : BigDecimal.ZERO );
		a.setEdad( ( o.getEdadInmueble()!=null ) ?  new BigDecimal(o.getEdadInmueble() ) : BigDecimal.ZERO );
		a.setCalificacion( o.getEstadoConservacion() !=null ? o.getEstadoConservacion(): BigDecimal.ZERO ); 
		a.setValorReposicion( o.getValorReposicion() !=null ?  o.getValorReposicion() : BigDecimal.ZERO );
		a.setVidaUtil( (o.getVidaUtil()!=null ) ? new BigDecimal( o.getVidaUtil() ) : BigDecimal.ZERO );
		return a;
	}
	
}
