package com.helio4.bancol.avaluos.ensamblador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.ComparacionMercadoLoteVentaDTO;
import com.helio4.bancol.avaluos.dto.ComparacionMercadoPHRentaDTO;
import com.helio4.bancol.avaluos.dto.ComparacionMercadoPHVentaDTO;
import com.helio4.bancol.avaluos.dto.MetodoValuacionDTO.MetodoValuacionEnum;
import com.helio4.bancol.avaluos.dto.OfertaDTO;
import com.helio4.bancol.avaluos.dto.OfertaLoteSinConstruccionDTO;
import com.helio4.bancol.avaluos.dto.OfertaPHDTO;
import com.helio4.bancol.avaluos.modelo.Avaluo;
import com.helio4.bancol.avaluos.modelo.ComparacionMercadoLoteSinConstruccion;
import com.helio4.bancol.avaluos.modelo.ComparacionMercadoPhRenta;
import com.helio4.bancol.avaluos.modelo.ComparacionMercadoPhVenta;
import com.helio4.bancol.avaluos.modelo.Oferta;
import com.helio4.bancol.avaluos.modelo.OfertaLote;
import com.helio4.bancol.avaluos.modelo.OfertaLoteSinConstruccion;
import com.helio4.bancol.avaluos.modelo.OfertaPH;
import com.helio4.bancol.avaluos.persistencia.AvaluoRepository;
import com.helio4.bancol.avaluos.persistencia.ComparacionMercadoLoteVentaRepository;
import com.helio4.bancol.avaluos.persistencia.ComparacionMercadoPHRentaRepository;
import com.helio4.bancol.avaluos.persistencia.ComparacionMercadoPHVentaRepository;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.MetodoValuacionNotFoundException;

@Component
public class ComparacionMercadoLoteVentaEnsamblador {
	
	@Autowired
	private OfertaLoteSinConstruccionEnsamblador ofertaLoteSinConstruccionEnsamblador;
	
	@Autowired
	private ComparacionMercadoLoteVentaRepository comparacionLoteVentaRespository;

	@Autowired
	private AvaluoRepository avaluoRepository;
	
	
	/**
	 * Función que se encarga de construir un DTO a partir del modelo
	 * 
	 * @param comparacionMercado modelo a partir del cual se va a construir el DTO
	 * @return  ComparacionMercadoPHVentaDTO  construido.
	 * */
	public ComparacionMercadoLoteVentaDTO escribirDTO( ComparacionMercadoLoteSinConstruccion comparacionMercado ){
		ComparacionMercadoLoteVentaDTO comparacion = new ComparacionMercadoLoteVentaDTO(comparacionMercado);
		
		if( comparacionMercado.getOfertas()!=null ){
			List<Oferta> ofertas =  comparacionMercado.getOfertas();
			List<OfertaLoteSinConstruccion> ofertasloteSinConstruccion = new ArrayList<>();
			
			for(Oferta oferta:ofertas){
				ofertasloteSinConstruccion.add((OfertaLoteSinConstruccion)oferta);
			}
			
			 List<OfertaLoteSinConstruccionDTO> ofertasLoteSCDTO = this.ofertaLoteSinConstruccionEnsamblador.escribirListaDTO(ofertasloteSinConstruccion);
			comparacion.setOfertasLoteSinConstruccion(ofertasLoteSCDTO);
		}		
		return comparacion;
	}
	
	public void actualizar(ComparacionMercadoLoteVentaDTO comparacionActualizada) throws AvaluoNotFoundException {
		ComparacionMercadoLoteSinConstruccion comparacion = this.comparacionLoteVentaRespository.findOne( comparacionActualizada.getId() );
		// TODO: Controlar si la comparación no esta en BD
		this.actuliazar(comparacionActualizada, comparacion); 		
	}
	
	
	public void actuliazar(ComparacionMercadoLoteVentaDTO actualizada, ComparacionMercadoLoteSinConstruccion comparacion) throws AvaluoNotFoundException{
		Avaluo avaluo = this.avaluoRepository.findOne(actualizada.getAvaluoId());
		if (avaluo == null) {
			throw new AvaluoNotFoundException();
		}
		
		comparacion.setAvaluo( avaluo );
		comparacion.cargarLoteDTO(actualizada);		
	}
	
	/**
	 * Función que se encarga de crear un modelo a partir del DTO.
	 * 
	 * @param comparacionMercado DTO con el que se crea el modelo.
	 * @return ComparacionMercadoPh modelo creado.
	 * @throws AvaluoNotFoundException 
	 * */
	public ComparacionMercadoLoteSinConstruccion crearComparacionMercadoLoteVenta( ComparacionMercadoLoteVentaDTO comparacionMercado ) 
			throws MetodoValuacionNotFoundException, AvaluoNotFoundException{
		
		ComparacionMercadoLoteSinConstruccion comparacion = new ComparacionMercadoLoteSinConstruccion();
		Avaluo avaluo = avaluoRepository.findOne(comparacionMercado.getAvaluoId());
		if (avaluo == null) {
			throw new AvaluoNotFoundException();
		}
		
		comparacion.setAvaluo( avaluo );
		comparacion.cargarLoteDTO(comparacionMercado);

		return comparacion;
	}
	
	
}
