package com.helio4.bancol.avaluos.controlador;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.google.common.collect.ImmutableList;
import com.helio4.bancol.avaluos.dominio.ComparacionMercadoController;
import com.helio4.bancol.avaluos.dominio.ComparacionMercadoNoPHStrategy;
import com.helio4.bancol.avaluos.dominio.ComparacionMercadoPHStrategy;
import com.helio4.bancol.avaluos.dominio.ComparacionMercadoStrategy;
import com.helio4.bancol.avaluos.dto.ComparacionMercadoNOPHDTO;
import com.helio4.bancol.avaluos.dto.ComparacionMercadoPHDTO;
import com.helio4.bancol.avaluos.dto.MetodoValuacionDTO;
import com.helio4.bancol.avaluos.dto.MetodoValuacionDTO.MetodoValuacionEnum;
import com.helio4.bancol.avaluos.dto.OfertaDTO;

@Controller
@Scope("view")
@Qualifier("comparacionMercadoBean")
public class ComparacionMercadoBean {
	
	private static final int OFERTAS_MINIMAS = 3;
	private static final int OFERTAS_MAXIMAS = 7;
	
	private boolean mostrarResultado;
	/**
	 * Variable que permite establecer al sistema si se debe activar el
	 * Botón para calcular el estudio de mercado.
	 * */
	private boolean activarEstudio;
	
	/**
	 * Variable que permite mostrar la comparación de mercado si
	 * el usuario selecciona este método de valuación
	 * */
	private boolean mostrar;
	
	
	/**
	 * Método de valuación al que se encuentra relacionado las ofertas.
	 * */
	private MetodoValuacionDTO metodoValuacion;
	
	/**
	 * 
	 * */
	private ComparacionMercadoPHDTO resultado;

	@Autowired
	private ComparacionMercadoController comparacionMercadoController;	
	
	private boolean sometidoPropiedadHorizontal;
	
	/**
	 * Lista de selección de los estados de conservación.
	 * 
	 * */
	private List<BigDecimal> estadosConservacion;
	
	@PostConstruct
	public void inicializar(){
		//System.err.println( "inicializando comparacion de mercado" );
		this.mostrarResultado = Boolean.FALSE;
		this.setActivarEstudio(Boolean.FALSE);
		this.setMostrar(Boolean.FALSE);
		this.setEstadosConservacion(new ArrayList<BigDecimal>());
		for( double i=0; i<=5; i+=0.5){
			BigDecimal value = new BigDecimal(i);
			value = value.setScale(1);
			this.getEstadosConservacion().add(  value  );
		}
	
	}

	/**
	 * Función que se encarga de calcular el estudio de mercado.
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * */

	public void realizarCalculoEstudio() throws NoSuchMethodException, SecurityException, 
		IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		
		if( this.metodoValuacion!=null && this.metodoValuacion.getOfertas()!=null && this.metodoValuacion.getOfertas().size()>0){
			// TODO: Comprobar si la comparación es PH o NO y mostrar un error?
			ComparacionMercadoStrategy strategy = this.isSometidoPropiedadHorizontal() ?
					  new ComparacionMercadoPHStrategy(  (ComparacionMercadoPHDTO)this.metodoValuacion ) 
					: new ComparacionMercadoNoPHStrategy(  (ComparacionMercadoNOPHDTO)this.metodoValuacion );
			this.resultado = strategy.calcularEstudio( this.comparacionMercadoController );
			//System.out.println( this.resultado.toString() );
			if( this.resultado!=null) {
				this.metodoValuacion = this.resultado;
				this.mostrarResultado 	=  (this.getMetodoValuacion().getOfertas().size() >= OFERTAS_MINIMAS -1 )? Boolean.TRUE : Boolean.FALSE;
			}
		}
				
	}
	
	public void calcularEstudio() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		boolean ofertasNecesarias = this.ofertasNecesarias();
		this.realizarCalculoEstudio();
	}
	
	public boolean ofertasNecesarias(){
		if(this.getMetodoValuacion().getOfertas().size() <= OFERTAS_MINIMAS -1){
			FacesContext.getCurrentInstance().addMessage("growlcm", new FacesMessage("", "Ingrese mínimo 3 ofertas."));
			return false;
		}
		
		if( this.getMetodoValuacion().getOfertas().size()>OFERTAS_MAXIMAS ){
			FacesContext.getCurrentInstance().addMessage("growlcm", new FacesMessage("Error al calcular comparacion de mercado", "Ingrese máximo 7  ofertas."));
			return false;
		}
		
		if(this.getMetodoValuacion().getOfertas().size() >= OFERTAS_MINIMAS -1 && this.getMetodoValuacion().getOfertas().size()<=OFERTAS_MAXIMAS){
			return true;
		}
		return false;
	}
	/**
	 * Función que activa el botón de estudio de mercado
	 * @return Estado que indica si se activa o no el botón.
	 * */

	protected boolean isCalcularEstudio(){
		if( this.getMetodoValuacion().getOfertas().isEmpty() ){
			return false;
		}
		for( OfertaDTO oferta : this.getMetodoValuacion().getOfertas() ){
			BigDecimal valorDepurado = oferta.getValorDepurado();
			BigDecimal valorM2Construccion = oferta.getValorM2Construccion();
			BigDecimal construccion = oferta.getConstruccion();
			if( valorDepurado == null && valorM2Construccion == null && construccion ==null ){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Función que se encarga de eliminar una oferta ingresada por el usuario.
	 * */
	public void borrarOferta(int index){
		if(this.getMetodoValuacion().getOfertas()!=null && !this.getMetodoValuacion().getOfertas().isEmpty()  
					&& this.getMetodoValuacion().getOfertas().size()>index ){
			
			OfertaDTO oferta = this.getMetodoValuacion().getOfertas().get(index);
			if( oferta.getId()!=null){
				this.comparacionMercadoController.eliminarOferta(oferta); 
			}
			this.getMetodoValuacion().getOfertas().remove(index);
		}
		this.verificarCalcularEstudio();
		try {
			this.realizarCalculoEstudio();
		} catch(Exception e) {
			e.printStackTrace();
		} 
	}
	/**
	 * Función que se encara de actualizar las ofertas de un
	 * Método de valuación
	 * */
	public void actualizarOfertas(){
		if( this.metodoValuacion!=null &&  this.metodoValuacion.getOfertas()!=null && this.metodoValuacion.getId()!=null){
			this.comparacionMercadoController.actualizarOfertas(this.metodoValuacion);
		}
		
	}
	
	/**
	 * Función que se encarga de actualizar los resultados de la comparacion de mercado.
	 * */
	public void actualizarResultados() {
		this.comparacionMercadoController.actualizarResultados(this.resultado);
	}
	
	/**
	 * Función que se encarga de agregar oferta a la lista de ofertas ph.
	 * */
	public void agregarOferta(){
		this.metodoValuacion.getOfertas().add( new OfertaDTO() );
		try {
			this.calcularEstudio();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * Verifica si se debe activar el botón de estudio de mercado.
	 * */
	public void verificarCalcularEstudio(){
		this.activarEstudio  = this.isCalcularEstudio();
	}
	/**
	 * Función que se encarga de calcular los valores de la construcción.
	 * @param Indice de la oferta sobre la cual se esta editando la información. 
	 * */
	public void calcularConstruccion(int index){
		OfertaDTO ofertaDTO = this.metodoValuacion.getOfertas().get(index);
		ComparacionMercadoStrategy strategy = this.isSometidoPropiedadHorizontal() ?
				  new ComparacionMercadoPHStrategy( new ComparacionMercadoPHDTO( this.metodoValuacion )) 
				: new ComparacionMercadoNoPHStrategy( new ComparacionMercadoNOPHDTO( this.metodoValuacion));
				
		ofertaDTO = strategy.calcularValorConstruccion(ofertaDTO);
		this.comparacionMercadoController.calcularTerreno(ofertaDTO);
		this.metodoValuacion.getOfertas().set(index,ofertaDTO );
		try {
			this.calcularEstudio();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * Calcula el valor depurado sobre una oferta.
	 * 
	 * @param OfertaDTO  sobre la cual se calcula el valor depurado
	 * @return OfertaDTO después de realizar el calculo.
	 * */
	public void  calcularValorDepurado(int index) {
		if( this.metodoValuacion.getOfertas()!=null && !this.metodoValuacion.getOfertas().isEmpty() 
				&& index<this.metodoValuacion.getOfertas().size()){
			OfertaDTO oferta =this.metodoValuacion.getOfertas().get(index);
			oferta  = this.comparacionMercadoController.calcularValorDepurado(oferta);
			this.calcularConstruccion(index);
			this.comparacionMercadoController.calcularTerreno(oferta);
		}
	}

	/**
	 * Método que se ejecuta cuando se diligencian los campos, area de terreno.
	 * */
	public void calcularTerreno(int index){
		OfertaDTO ofertaDTO = this.metodoValuacion.getOfertas().get(index);
		ofertaDTO = this.comparacionMercadoController.calcularTerreno(ofertaDTO);
		this.metodoValuacion.getOfertas().set(index,ofertaDTO);
	}
	
	/**
	 * Método que se encarga de  modificar las ofertas cuando se cambia de 
	 * sometido a propiedad horizontal
	 * */
    public MetodoValuacionDTO cambiarTipoConstruccion(Boolean sometidoPropiedadHorizontal) {
        if (sometidoPropiedadHorizontal != null) {
            this.sometidoPropiedadHorizontal = sometidoPropiedadHorizontal;
        }
        /* Si hay un método de valuación de comparación de mercado
         * se cambia el tipo del método al correspondiente
         * Y se borran las ofertas.
         */
        if (this.metodoValuacion != null) {
            if (this.metodoValuacion.getOfertas() != null
                    && this.metodoValuacion.getOfertas().size() > 0) {
                this.comparacionMercadoController
                        .eliminarOfertas(this.metodoValuacion.getOfertas());
            }
            Long avaluoId = this.metodoValuacion.getAvaluoId();
            this.metodoValuacion = (this.sometidoPropiedadHorizontal ? new ComparacionMercadoPHDTO(
                    MetodoValuacionEnum.ComparaciondeMercado.getKey(), avaluoId)
                    : new ComparacionMercadoNOPHDTO(
                            MetodoValuacionEnum.ComparaciondeMercado.getKey(),
                            avaluoId));
            this.metodoValuacion.setOfertas(new ArrayList<OfertaDTO>());
            //se anulan los calculos realizados con el metodo anterior. 
            this.resultado = null;
            return this.metodoValuacion;
        }
        return null;
    }
	
    public void setMetodoValuacion(MetodoValuacionDTO metodoValuacion) {
		this.metodoValuacion = metodoValuacion;
		if( this.metodoValuacion.getOfertas()==null){
			this.metodoValuacion.setOfertas( new ArrayList<OfertaDTO>() ); 
		}
		List<OfertaDTO> ofertasOriginales = ImmutableList.copyOf(this.metodoValuacion.getOfertas());
		this.metodoValuacion.setOfertasOriginales(ofertasOriginales );
		try {
			if( this.metodoValuacion!=null && this.metodoValuacion.getOfertas()!=null && this.metodoValuacion.getOfertas().size()>0){
				this.realizarCalculoEstudio();
			}

		} catch (Exception e) {
			System.err.println("Error al calcular el estudio al iniciar el bean de Comparacion de Mercado");
			System.err.println("Sometido a propiedad horizontal: "+this.isSometidoPropiedadHorizontal() );
			System.err.println("Tipo de comparacion de mercado: "+this.metodoValuacion.getClass().getSimpleName() );
			e.printStackTrace();
		} 
	}
    
    public void setMostrar(boolean mostrar) {
		this.mostrar = mostrar;
		if( !this.mostrar ){
			this.mostrarResultado = Boolean.FALSE;
		}
	}
    
    public boolean isMostrar() {
		return mostrar;
	}
	public MetodoValuacionDTO getMetodoValuacion() {
		return metodoValuacion;
	}
	public void setSometidoPropiedadHorizontal(boolean sometidoPropiedadHorizontal) {
		this.sometidoPropiedadHorizontal = sometidoPropiedadHorizontal;
	}
	
	public boolean isSometidoPropiedadHorizontal() {
		return sometidoPropiedadHorizontal;
	}

	public boolean isMostrarResultado() {
		return mostrarResultado;
	}

	public void setMostrarResultado(boolean mostrarResultado) {
		this.mostrarResultado = mostrarResultado;
	}

	public boolean isActivarEstudio() {
		return activarEstudio;
	}

	public void setActivarEstudio(boolean activarEstudio) {
		this.activarEstudio = activarEstudio;
	}

	public void borrarMetodoValuacion(){
		this.metodoValuacion = null;
	}
	public List<BigDecimal> getEstadosConservacion() {
		return estadosConservacion;
	}

	public void setEstadosConservacion(List<BigDecimal> estadosConservacion) {
		this.estadosConservacion = estadosConservacion;
	}

	public ComparacionMercadoPHDTO getResultado() {
		return resultado;
	}

	public void setResultado(ComparacionMercadoPHDTO resultado) {
		this.resultado = resultado;
	}}
