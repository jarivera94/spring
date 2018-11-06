package com.helio4.bancol.avaluos.dominio;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

import com.helio4.bancol.avaluos.dto.AreaDTO;
import com.helio4.bancol.avaluos.dto.FormatoInformeHipotecarioDTO;


public class FitoCorviniController {
	/**
	 * Calcula Fito y corvini para un area.
	 * 
	 * @param area
	 *            Area sobre la cual calcular F&C.
	 * @return AreaDTO, area con el calculo realizado
	 * 
	
	 * */
	public AreaDTO calcularFitoCorvini( AreaDTO area) {
		
		BigDecimal depreciacion = this.calcularDepreciacion(area);
		area.setDepreciacion(depreciacion);
		
		BigDecimal valorReposicion = area.getValorReposicion() ==null ? BigDecimal.ZERO :area.getValorReposicion()  ;
		valorReposicion.setScale(3,BigDecimal.ROUND_HALF_UP);
		
		BigDecimal valorDepreciacion = ( valorReposicion == null) ? BigDecimal.ZERO : valorReposicion.multiply(depreciacion);
		//valorDepreciacion.round(new MathContext(0, RoundingMode.HALF_UP) );
		valorDepreciacion = new BigDecimal(Math.round(valorDepreciacion.doubleValue()));
		
		BigDecimal valorFinal =  ( valorReposicion == null) ? BigDecimal.ZERO :  valorReposicion.subtract(valorDepreciacion);
		valorDepreciacion.setScale(3,BigDecimal.ROUND_HALF_UP);
		
		BigDecimal valorAdoptado = valorFinal;
		valorAdoptado.round( new MathContext(3, RoundingMode.HALF_UP) );
		//valorAdoptado = new BigDecimal(Math.round(valorAdoptado.doubleValue()));
		
		/*AFVV 2015-08-12 Se redondea a multiplos de 10000 como en el Excel de ejemplo*/
		double va = valorAdoptado.doubleValue()/10000;
		valorAdoptado = new BigDecimal(Math.round(va)*10000);
		
		area.setValorAdoptado(valorAdoptado);
		
		area.setValorDepreciacion(valorDepreciacion);
		area.setValorFinal(valorFinal);

		BigDecimal areaConstruida = area.getArea();
		BigDecimal costoTotalReposicion = (areaConstruida == null || valorReposicion == null ) ? BigDecimal.ZERO : valorReposicion.multiply(areaConstruida);
		costoTotalReposicion.setScale(3,BigDecimal.ROUND_HALF_UP);
		
		area.setCostoReposicion(costoTotalReposicion);
		
		BigDecimal valorConstruccion = (areaConstruida ==null) ? BigDecimal.ZERO : areaConstruida.multiply(valorAdoptado);
		valorConstruccion.setScale(3,BigDecimal.ROUND_HALF_UP);
		area.setValorConstruccion(valorConstruccion);
		return area;
	
	}
	public AreaDTO calcularValorReposicion(AreaDTO area){
		BigDecimal valorReposicionFinal = ( area.getArea()==null) ? BigDecimal.ZERO: area.getArea().multiply( area.getValorReposicion() ==null ? BigDecimal.ZERO : area.getValorReposicion() );	
		area.setValorReposicionFinal(valorReposicionFinal);
		valorReposicionFinal.setScale(3,BigDecimal.ROUND_HALF_UP);
		return area;
	}
	/**
	 *  @TODO: cuando se desarrolle en comercial se debe cambiar FormatoInformeHipotecario
	 * 		 por FormatoInforme, para que funcione con comercial
	 * */
	public AreaDTO calcularValorReposicion(
			AreaDTO area, Boolean esPropiedadHorizontal,
			FormatoInformeHipotecarioDTO formato, 
			AreaDTO areaPrivada){
//		BigDecimal valorReposicion = area.getValorReposicion() ==null ? BigDecimal.ZERO :area.getValorReposicion()  ;
		//se valcula el valor de reposicion final de acuerdo al tipo de propiedad. 
//		BigDecimal valorReposicionFinal = BigDecimal.ZERO;
//		if(esPropiedadHorizontal){
//			BigDecimal cien = new BigDecimal(100);
//			BigDecimal porcentajeTerreno = formato.getPorcentajeTerreno() == null ? BigDecimal.ZERO :formato.getPorcentajeTerreno() ;
//			BigDecimal valorProporcional = cien.subtract( porcentajeTerreno );
//			valorReposicion = area.getValorReposicion() ==null ? BigDecimal.ZERO : area.getValorReposicion();
//			valorReposicionFinal = (areaPrivada.getArea() == null ) ? BigDecimal.ZERO : areaPrivada.getArea().multiply( valorReposicion ).multiply(valorProporcional);
//			area.setValorReposicionFinal(valorReposicionFinal);
//			valorReposicionFinal.setScale(3);
//		}
//		else{
//			area = this.calcularValorReposicion(area);
//		}
		return this.calcularValorReposicion(area);
	}
	

	public  BigDecimal calcularDepreciacion(AreaDTO area) {
		BigDecimal depreciacion = new BigDecimal(0, new MathContext(5, RoundingMode.HALF_UP) );
		
		BigDecimal factorUno = BigDecimal.ZERO;
		;
		BigDecimal factorDos = BigDecimal.ZERO;
		BigDecimal factorTres = BigDecimal.ZERO;

		if (area.getCalificacion() == null) {
			area.setCalificacion(new BigDecimal(0.0));
		}

		BigDecimal califiacacion = area.getCalificacion();
		if (califiacacion.compareTo((BigDecimal.ZERO))==0
				|| area.getCalificacion().compareTo(new BigDecimal(0.5))==0
				|| area.getCalificacion().compareTo(BigDecimal.ONE)==0 ) {
			factorUno = new BigDecimal(0.005);
			factorDos = new BigDecimal(0.5001);
			factorTres = new BigDecimal(0.0071);
		} else if (califiacacion.compareTo(new BigDecimal(1.5))==0  ) {
			factorUno = new BigDecimal(0.005);
			factorDos = new BigDecimal(0.4998);
			factorTres = new BigDecimal(0.0262);
		} else if (califiacacion.compareTo(new BigDecimal(2.0))==0 ) {
			factorUno = new BigDecimal(0.0049);
			factorDos = new BigDecimal(0.4861);
			factorTres = new BigDecimal(2.5407);
		} else if (califiacacion.compareTo(new BigDecimal(2.5))==0 ) {
			factorUno = new BigDecimal(0.0046);
			factorDos = new BigDecimal(0.4581);
			factorTres = new BigDecimal(8.1068);
		} else if (califiacacion.compareTo(new BigDecimal(3.0))==0 ) {
			factorUno = new BigDecimal(0.0041);
			factorDos = new BigDecimal(0.4092);
			factorTres = new BigDecimal(18.1041);
		} else if (califiacacion.compareTo(new BigDecimal(3.5))==0 ) {
			factorUno = new BigDecimal(0.0033);
			factorDos = new BigDecimal(0.3341);
			factorTres = new BigDecimal(33.199);
		} else if (califiacacion.compareTo(new BigDecimal(4.0))==0 ) {
			factorUno = new BigDecimal(0.0023);
			factorDos = new BigDecimal(0.24);
			factorTres = new BigDecimal(52.5274);

		} else if (califiacacion.compareTo(new BigDecimal(4.5))==0 ) {
			factorUno = new BigDecimal(0.0012);
			factorDos = new BigDecimal(0.1275);
			factorTres = new BigDecimal(75.153);

		} else if (califiacacion.compareTo(new BigDecimal(5.0))==0 ) {
			factorUno = new BigDecimal(0.0012);
			factorDos = new BigDecimal(0.1275);
			factorTres = new BigDecimal(75.153);
		}
		
		factorUno = factorUno.setScale( 4, RoundingMode.HALF_UP);
		factorDos = factorDos.setScale( 4, RoundingMode.HALF_UP);
		factorTres = factorTres.setScale( 4, RoundingMode.HALF_UP);
			
		if (area.getVidaUtil() != null && area.getVidaUtil().compareTo(BigDecimal.ZERO)!=0 ) {

			BigDecimal primera = (area.getEdad().divide(area.getVidaUtil(), 4, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).pow(2)).multiply(factorUno);
			BigDecimal segunda = area.getEdad().divide(area.getVidaUtil(), 4, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).multiply(factorDos);
			if (califiacacion.equals(BigDecimal.ZERO)
					|| califiacacion.equals(new BigDecimal("0.5"))
					|| califiacacion.equals(BigDecimal.ONE)) {

				depreciacion = primera.add(segunda).subtract(factorTres).divide(new BigDecimal(100), 4, RoundingMode.HALF_UP);
				
			} else {
				depreciacion = primera.add(segunda).add(factorTres).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP);
			
			}
		}
		return depreciacion;
	}
	
	public Integer calcularVidaUtil(Integer estructura) {
		return ( estructura !=null) ? (estructura == 1 ? 70 : 100) : 0;
	}
	
	/**
	 * Método que calcula la edad en porcentaje de vida.
	 * 
	 * @param area sobre la cual realizar el calculo
	 * @return  calculo realizado.
	 * */
	public BigDecimal edadPorcentajeVida(AreaDTO area){
		BigDecimal edad = area.getEdad();
		BigDecimal vidaUtil = area.getVidaUtil();
		return ( vidaUtil !=null && vidaUtil.compareTo( BigDecimal.ZERO)!=0 ) 
				? edad.divide( vidaUtil , 4,RoundingMode.HALF_UP) : BigDecimal.ZERO;
	}

	
	public AreaDTO inicializarFitoCorvini(AreaDTO area) {
		area.setDepreciacion( BigDecimal.ZERO );
		area.setCostoReposicion(BigDecimal.ZERO);
		area.setCalificacion( BigDecimal.ZERO);
		area.setValorDepreciacion(BigDecimal.ZERO);
		area.setValorFinal(BigDecimal.ZERO );
		area.setValorAdoptado(BigDecimal.ZERO);
		area.setValorConstruccion( BigDecimal.ZERO);
		area.setEdad(BigDecimal.ZERO);
		area.setVidaUtil(BigDecimal.ZERO);
		area.setValorReposicionFinal(BigDecimal.ZERO);
		return area;
	}
	/**
	 * Calculo para no ph
	 * */
	public BigDecimal calcularSumatoriaValorReposicionFinal(List<AreaDTO> areas){
		BigDecimal sumatoria = BigDecimal.ZERO;
		for( AreaDTO area:areas ){
			if( area!=null && area.getValorReposicionFinal()!=null ){
				sumatoria  = sumatoria.add( area.getValorReposicionFinal() );
			}
		}
		return sumatoria;
	}
	
}
