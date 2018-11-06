package com.helio4.bancol.avaluos.ensamblador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.ComparacionMercadoPHRentaDTO;
import com.helio4.bancol.avaluos.dto.ComparacionMercadoPHVentaDTO;
import com.helio4.bancol.avaluos.dto.MetodoValuacionDTO.MetodoValuacionEnum;
import com.helio4.bancol.avaluos.dto.OfertaDTO;
import com.helio4.bancol.avaluos.dto.OfertaPHDTO;
import com.helio4.bancol.avaluos.modelo.Avaluo;
import com.helio4.bancol.avaluos.modelo.ComparacionMercadoPhRenta;
import com.helio4.bancol.avaluos.modelo.ComparacionMercadoPhVenta;
import com.helio4.bancol.avaluos.modelo.Oferta;
import com.helio4.bancol.avaluos.modelo.OfertaPH;
import com.helio4.bancol.avaluos.persistencia.AvaluoRepository;
import com.helio4.bancol.avaluos.persistencia.ComparacionMercadoPHRentaRepository;
import com.helio4.bancol.avaluos.persistencia.ComparacionMercadoPHVentaRepository;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.MetodoValuacionNotFoundException;

@Component
public class ComparacionMercadoPHRentaEnsamblador {
	
	@Autowired
	private OfertaEnsamblador ofertaEnsamblador;
	
	@Autowired
	private OfertaPHEnsamblador ofertaPHEnsamblador;
	
	@Autowired
	private ComparacionMercadoPHRentaRepository comparacionPHRentaRespository;

	@Autowired
	private AvaluoRepository avaluoRepository;
	
	
	/**
	 * Función que se encarga de construir un DTO a partir del modelo
	 * 
	 * @param comparacionMercado modelo a partir del cual se va a construir el DTO
	 * @return  ComparacionMercadoPHVentaDTO  construido.
	 * */
	public ComparacionMercadoPHRentaDTO escribirDTO( ComparacionMercadoPhRenta comparacionMercado ){
		
		ComparacionMercadoPHRentaDTO comparacion = new ComparacionMercadoPHRentaDTO(comparacionMercado);
		
		if( comparacionMercado.getOfertas()!=null ){
			List<Oferta> ofertas =  comparacionMercado.getOfertas();
			List<OfertaPH> ofertasPH = new ArrayList<>();
			
			for(Oferta oferta:ofertas){
				ofertasPH.add((OfertaPH)oferta);
			}
			
			List<OfertaPHDTO> ofertasPHDTO = this.ofertaPHEnsamblador.escribirListaDTO(ofertasPH);
			comparacion.setOfertasPH(ofertasPHDTO);
		}		
		return comparacion;
	}
	
	public void actualizar(ComparacionMercadoPHRentaDTO comparacionActualizada) throws AvaluoNotFoundException {
		ComparacionMercadoPhRenta comparacion = this.comparacionPHRentaRespository.findOne( comparacionActualizada.getId() );
		// TODO: Controlar si la comparación no esta en BD
		this.actuliazar(comparacionActualizada, comparacion); 		
	}
	
	
	public void actuliazar(ComparacionMercadoPHRentaDTO actualizada, ComparacionMercadoPhRenta comparacion) throws AvaluoNotFoundException{
		Avaluo avaluo = this.avaluoRepository.findOne(actualizada.getAvaluoId());
		if (avaluo == null) {
			throw new AvaluoNotFoundException();
		}
		
		comparacion.setAvaluo( avaluo );
		comparacion.setCoeficienteVariacion( actualizada.getCoeficienteVariacion() );
		comparacion.setConceptoDelMetodo( actualizada.getConceptoDelMetodo() );
		comparacion.setId(actualizada.getId());
		
		comparacion.setTasaAplicada(actualizada.getTasaAplicada());
		comparacion.setRentaBrutaMensual(actualizada.getRentaBrutaMensual());
		comparacion.setDeducciones(actualizada.getDeducciones());
		comparacion.setRentaNetaMensual(actualizada.getRentaNetaMensual());
		comparacion.setRentaNetaAnual(actualizada.getRentaNetaAnual());
		comparacion.setValorCapitalizacion(actualizada.getValorCapitalizacion());
		comparacion.setValorM2HomogenizadoSinGaraje(actualizada.getValorM2HomogenizadoSinGaraje());
		
		comparacion.setCoeficienteVariacion(actualizada.getCoeficienteVariacion());
		comparacion.setPiso(actualizada.getPiso());
		comparacion.setArea(actualizada.getArea());
		comparacion.setGarajes(actualizada.getGarajes());
		comparacion.setEdad(actualizada.getEdad());
		comparacion.setValorAdministracion(actualizada.getValorAdministracion());
		comparacion.setAreaLibre(actualizada.getAreaLibre());
		comparacion.setPrecioUnitarioAdministracionM2(actualizada.getPrecioUnitarioAdministracionM2());
		
		comparacion.setPromedioValorUnitarioGaraje(actualizada.getPromedioValorUnitarioGaraje());
		comparacion.setPromedioValorM2HomogenizadoSinGaraje(actualizada.getPromedioValorM2HomogenizadoSinGaraje());
		comparacion.setPromedioValorComercial(actualizada.getPromedioValorComercial());
		comparacion.setPromedioValorM2SinGarajeNoHomogenizado(actualizada.getPromedioValorM2SinGarajeNoHomogenizado());
		comparacion.setPromedioValorM2HomogenizadoSinGarajeAreaLibre(actualizada.getPromedioValorM2HomogenizadoSinGarajeAreaLibre());
		
		comparacion.setFactorSuperficie(actualizada.getFactorSuperficie());
		comparacion.setFactorEdad(actualizada.getFactorEdad());
		comparacion.setTipoProyecto(actualizada.getTipoProyecto().getKey());
		comparacion.setValorM2AreaLibre(actualizada.getValorM2AreaLibre());
		comparacion.setValorUnitarioGaraje(actualizada.getValorUnitarioGaraje());
		comparacion.setValorm2homogenizadoGJ(actualizada.getValorm2homogenizadoGJ());
	}
	
	/**
	 * Función que se encarga de crear un modelo a partir del DTO.
	 * 
	 * @param comparacionMercado DTO con el que se crea el modelo.
	 * @return ComparacionMercadoPh modelo creado.
	 * @throws AvaluoNotFoundException 
	 * */
	public ComparacionMercadoPhRenta crearComparacionMercadoPHRenta( ComparacionMercadoPHRentaDTO comparacionMercado ) 
			throws MetodoValuacionNotFoundException, AvaluoNotFoundException{
		
		ComparacionMercadoPhRenta comparacion = new ComparacionMercadoPhRenta();
		Avaluo avaluo = avaluoRepository.findOne(comparacionMercado.getAvaluoId());
		if (avaluo == null) {
			throw new AvaluoNotFoundException();
		}
		comparacion.setAvaluo( avaluo );
		comparacion.setCoeficienteVariacion( comparacionMercado.getCoeficienteVariacion() );
		comparacion.setConceptoDelMetodo( comparacionMercado.getConceptoDelMetodo() );		
		comparacion.setId(comparacionMercado.getId());
		
		comparacion.setTasaAplicada(comparacionMercado.getTasaAplicada());
		comparacion.setRentaBrutaMensual(comparacionMercado.getRentaBrutaMensual());
		comparacion.setDeducciones(comparacionMercado.getDeducciones());
		comparacion.setRentaNetaMensual(comparacionMercado.getRentaNetaMensual());
		comparacion.setRentaNetaAnual(comparacionMercado.getRentaNetaAnual());
		comparacion.setValorCapitalizacion(comparacionMercado.getValorCapitalizacion());
		comparacion.setValorM2HomogenizadoSinGaraje(comparacionMercado.getValorM2HomogenizadoSinGaraje());
		
		comparacion.setFactorSuperficie(comparacionMercado.getFactorSuperficie());
		comparacion.setFactorEdad(comparacionMercado.getFactorEdad());
		comparacion.setTipoProyecto(comparacionMercado.getTipoProyecto().getKey());
		comparacion.setValorM2AreaLibre(comparacionMercado.getValorM2AreaLibre());
		comparacion.setValorUnitarioGaraje(comparacionMercado.getValorUnitarioGaraje());
		
		comparacion.setCoeficienteVariacion(comparacionMercado.getCoeficienteVariacion());
		
		if( comparacionMercado.getMetodoUsado()!=null ){
			comparacion.setMetodoUsado(comparacionMercado.getMetodoUsado().getKey() );
		}
		
		return comparacion;
	}
	
	
}
