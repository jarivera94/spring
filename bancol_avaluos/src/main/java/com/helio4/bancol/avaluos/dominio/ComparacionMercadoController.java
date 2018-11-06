package com.helio4.bancol.avaluos.dominio;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.helio4.bancol.avaluos.dto.ComparacionMercadoNOPHDTO;
import com.helio4.bancol.avaluos.dto.ComparacionMercadoPHDTO;
import com.helio4.bancol.avaluos.dto.MetodoValuacionDTO;
import com.helio4.bancol.avaluos.dto.OfertaDTO;
import com.helio4.bancol.avaluos.exception.ComparacionMercadoNOPHNotFoundException;
import com.helio4.bancol.avaluos.exception.ComparacionMercadoPHNotFoundException;
import com.helio4.bancol.avaluos.exception.OfertaNotFoundException;
import com.helio4.bancol.avaluos.servicio.datos.ComparacionMercadoNOPHService;
import com.helio4.bancol.avaluos.servicio.datos.ComparacionMercadoPHService;
import com.helio4.bancol.avaluos.servicio.datos.OfertaService;
import com.jidesoft.utils.BigDecimalMathUtils;

@Component
public class ComparacionMercadoController {
	
	private static Logger log = LoggerFactory.getLogger(ComparacionMercadoController.class);
	@Autowired
	private OfertaService ofertaService;
	
	@Autowired
	private ComparacionMercadoPHService comparacionMercadoPHService;
	
	@Autowired
	private ComparacionMercadoNOPHService comparacionMercadoNOPHService;
	
	
	/**
	 * Obtiene el numero de t-student basado en el número de ofertas.
	 * 
	 * */
	public BigDecimal calcularTN(int numeroDatos){
		switch (numeroDatos) {
		case 1:return new BigDecimal(4.696);
		case 2:return new BigDecimal(2.403);
		case 3:return new BigDecimal(1.9955);
		case 4:return new BigDecimal(1.85765);
		case 5:return new BigDecimal(1.7455);
		case 6:return new BigDecimal(1.6915);
		case 7:case 8:case 9:case 10:return new BigDecimal(1.592);
		case 11:case 12:case 13:return new BigDecimal(1.561);
		case 14:case 15:case 16:return new BigDecimal(1.542);
		case 17:return new BigDecimal(1.537);
		case 18:return new BigDecimal(1.533);
		case 19:return new BigDecimal(1.529);
		case 20:return new BigDecimal(1.526);
		case 21:case 22:return new BigDecimal(1.523);
		case 23:return new BigDecimal(1.518);
		case 24:return new BigDecimal(1.516);
		case 25:return new BigDecimal(1.513);
		default:
			break;
		}
		return BigDecimal.ZERO;
	}
	
	public OfertaDTO calcularValorDepurado(OfertaDTO oferta){
		BigDecimal porcentajeDepurado = oferta.getPorcentajeDepurado();
		
		if(porcentajeDepurado==null){
			porcentajeDepurado = BigDecimal.ZERO;
		}
		
		BigDecimal divisor = porcentajeDepurado .divide( new BigDecimal(100) ,5, RoundingMode.HALF_UP) ;
		BigDecimal valorDepurado = (oferta.getValorOferta() == null ) ? BigDecimal.ZERO : oferta.getValorOferta().subtract(
					oferta.getValorOferta() == null ? BigDecimal.ZERO :
					oferta.getValorOferta()
							.multiply( (divisor==null ? BigDecimal.ONE :divisor)  )
					);
		oferta.setValorDepurado(valorDepurado);
		return oferta;
	}
	/**
	 * Función que calcula el valor de la construcción de una oferta.
	 * 
	 * @param OfertaDTO  sobre la cual se calcula el valor de construcción
	 * @return OfertaDTO después de realizar el calculo.
	 * */
	public OfertaDTO calcularValorConstruccion(OfertaDTO oferta){
		BigDecimal valorFinalDepurado = ( oferta.getGarajesDepositos()!=null && oferta.getValorDepurado()!=null ) ? oferta.getValorDepurado().subtract( oferta.getGarajesDepositos() ) : BigDecimal.ZERO;
		oferta.setValorFinalDepurado(valorFinalDepurado);
		oferta.setConstruccion( valorFinalDepurado );
		BigDecimal valorConstruido = (oferta.getAreaConstruida()!=null && oferta.getAreaConstruida()!=0) ? valorFinalDepurado.divide( new BigDecimal(oferta.getAreaConstruida()),2, RoundingMode.HALF_UP ) : BigDecimal.ZERO;
		oferta.setValorM2Construccion(valorConstruido);
		return oferta;
	}
	/**
	 * Función que se encarga de calcular la desviación estándar sobre el 
	 * valor m2 de la construcción para construcciones con propiedad horizontal.
	 * 
	 * @param Lista de ofertas
	 * @param metodo : nombre del método get sobre el cual se aplica la desviación
	 * @return desviación calculada.
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * */
	public BigDecimal calcularDesviacionEstandar(List<OfertaDTO> ofertas, String metodo) 
			throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {	
		Class<OfertaDTO> ofertaClass = OfertaDTO.class;
		Method metodoGet =  ofertaClass.getDeclaredMethod(metodo);
		
		BigDecimal desviacion = BigDecimal.ZERO;
		List<BigDecimal> valores = new ArrayList<BigDecimal>();
		for( OfertaDTO oferta:ofertas){
			BigDecimal value = (BigDecimal) metodoGet.invoke(oferta);
			//value.setScale(2);
			if( value!=null){
				valores.add (value);
			}
			
		}
		if( valores.size()>0 ){
			desviacion = BigDecimalMathUtils.stddev(valores, true, new MathContext(4,RoundingMode.HALF_UP));	
		}
		return desviacion;
	}


	/**
	 * Función que calcula el promedio del valor de las construcciones 
	 * con propiedad horizontal de las  diferentes ofertas.
	 * 
	 * @param Lista de ofertas
	 * @param campo Campo sobre el cual se va a realizar el calculo del promeidio getValorM2Construccion()
	 * @return promedio calculado.
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * */
	public BigDecimal calcularPromedio(List<OfertaDTO> ofertas, String campo) 
			throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		BigDecimal promedio = BigDecimal.ZERO;
		Class<OfertaDTO> ofertaClass = OfertaDTO.class;
		Method metodoGet = ofertaClass.getDeclaredMethod(campo);
		for(OfertaDTO oferta: ofertas){
			BigDecimal value = (BigDecimal) metodoGet.invoke(oferta);
			if(value != null){
				promedio = promedio.add( value );
			}
		}
		return ( ofertas!=null && ofertas.size()>0 ) ?  promedio.divide( new BigDecimal(ofertas.size()),2, RoundingMode.HALF_UP ) : BigDecimal.ZERO;
	}
	/**
	 * Función que calcula el limite inferior
	 * 
	 * @param promedio
	 * @param tn
	 * @param desviacion
	 * @param raiz
	 * 
	 * @return Límite inferior calculado
	 * */
	public BigDecimal calcularLimiteInferior(BigDecimal promedio, BigDecimal tn, BigDecimal desviacion, BigDecimal raiz){
		BigDecimal limite = BigDecimal.ZERO;
		limite = ( raiz!=null && desviacion !=null && desviacion.compareTo(BigDecimal.ZERO)!=0 )  ?  promedio.subtract( desviacion.divide( raiz ,2, RoundingMode.HALF_UP).multiply( tn ) ) : BigDecimal.ZERO;	
		return limite;
	}
	
	/**
	 * Función que calcula el limite superior
	 * 
	 * @param promedio
	 * @param tn
	 * @param desviacion	
	 * @param raiz
	 * 
	 * @return Limite superior calculado
	 * */
	public BigDecimal calcularLimiteSuperior(BigDecimal promedio, BigDecimal tn, BigDecimal desviacion, BigDecimal raiz){
		BigDecimal limite = BigDecimal.ZERO;
		limite =( raiz!=null &&  desviacion!=null && desviacion.compareTo(BigDecimal.ZERO)!=0  ) ? desviacion.divide( raiz,2, RoundingMode.HALF_UP ).multiply( tn ).add( promedio ) : BigDecimal.ZERO;
		return limite;
	}
	
	/**
	 * Método que se encarga de actualizar las ofertas de un método de valuación,
	 * Compara si se eliminaron
	 * */
	public void actualizarOfertas(MetodoValuacionDTO metodoValuacion) {
		List<OfertaDTO> ofertas = metodoValuacion.getOfertas();
		List<OfertaDTO> ofertasOriginales = metodoValuacion.getOfertasOriginales();
		
		//verificando si se borro alguna oferta del método de valuación.
		if( ofertasOriginales!=null && !ofertasOriginales.isEmpty() ){
			Collection<OfertaDTO> ofertasBorradas = Collections2.filter(ofertasOriginales, Predicates.not(Predicates.in(ofertas)));
			
			if( ofertasBorradas!=null && !ofertasBorradas.isEmpty() ){
				for( OfertaDTO ofertaDTO : ofertasBorradas ){
					try{
						this.ofertaService.eliminar( ofertaDTO.getId() );
					}
					catch( OfertaNotFoundException e){
                        log.error("No se encontró la oferta:  {}",
                                ofertaDTO);
					}
				}
			}
		}
		for( OfertaDTO  ofertaDTO: ofertas ){
			if( ofertaDTO.getId() == null ){
				this.ofertaService.crear(ofertaDTO, metodoValuacion);
			}
			else{
				try{
					this.ofertaService.actualizar( ofertaDTO );
				}
				catch(OfertaNotFoundException e){
                    log.error("No se encontró  la oferta  a actualizar {}",
                            ofertaDTO );
					this.ofertaService.crear(ofertaDTO, metodoValuacion);
				}
			}
		}
	}
	
	
	////
	/// Comparacion mercado no ph controller 
	///
	
	
	public OfertaDTO  calcularTerreno(OfertaDTO oferta){
		try{
			BigDecimal 	valorDepurado = oferta.getValorDepurado()   !=null ? oferta.getValorDepurado() : BigDecimal.ZERO;
			BigDecimal valorConstruccion = oferta.getConstruccion() !=null ? oferta.getConstruccion()  : BigDecimal.ZERO;
			BigDecimal totalTerreno = valorDepurado.subtract( valorConstruccion );
			oferta.setTotalTerreno(totalTerreno);
			BigDecimal area = oferta.getAreaTerreno()!=null ?   oferta.getAreaTerreno()  : BigDecimal.ZERO;
			BigDecimal valorM2Terreno = (area.compareTo(BigDecimal.ZERO)!=0 && totalTerreno!=null ) ?  totalTerreno.divide(area ,4, RoundingMode.HALF_UP): BigDecimal.ZERO;
			valorM2Terreno = new BigDecimal(Math.round(valorM2Terreno.doubleValue()));
			oferta.setValorM2Terreno(valorM2Terreno);
		}
		catch(ArithmeticException e) {
			System.err.println("ERROR AL CALCULAR EL TERRENO");
			System.err.println( "El valor depurado: "+ oferta.getValorDepurado() );
			System.err.println( "Valor construccion: "+ oferta.getConstruccion() );
			System.err.println(  "Area construida: "+ oferta.getAreaConstruida());
		}
		return oferta;
	}
	
	/**
	 * Se encarga de actualizar los datos  de los resultados calculados
	 * en comparacion de mercado.
	 * 
	 * @retrun resultado CompracionMercadoPHDTO, ComparacionMercadoNOPH
	 * */
	public void actualizarResultados( ComparacionMercadoPHDTO resultado){
		if( resultado.getClass().equals(ComparacionMercadoPHDTO.class )){
			try {
				if( resultado!=null && resultado.getId()!=null ){
					this.comparacionMercadoPHService.actualizar(resultado);
				}	
				if( resultado!=null && resultado.getId()==null){
					this.comparacionMercadoPHService.crear(resultado);
				}
			
			} catch (ComparacionMercadoPHNotFoundException e) {
				e.printStackTrace();
			}
		}
		if( resultado.getClass().equals(  ComparacionMercadoNOPHDTO.class) ){
			try {
				if( resultado!=null && resultado.getId()!=null ){
					this.comparacionMercadoNOPHService.actualizar((ComparacionMercadoNOPHDTO) resultado);
				}
				if( resultado!=null && resultado.getId()==null){
					this.comparacionMercadoNOPHService.crear((ComparacionMercadoNOPHDTO) resultado);
				}
				
			} catch (ComparacionMercadoNOPHNotFoundException e) {
				e.printStackTrace();
			}
		}	
	}
	
	public void eliminarOfertas( List<OfertaDTO> ofertas){
		for(OfertaDTO oferta: ofertas){
			try {
				if( oferta.getId()!=null){
					this.ofertaService.eliminar(oferta.getId());
				}
			} catch (OfertaNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void eliminarOferta( OfertaDTO oferta){
		try {
			this.ofertaService.eliminar(oferta.getId() );
		} catch (OfertaNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
}
