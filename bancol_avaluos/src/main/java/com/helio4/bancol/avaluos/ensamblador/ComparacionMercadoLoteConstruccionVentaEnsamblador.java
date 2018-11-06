package com.helio4.bancol.avaluos.ensamblador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.ComparacionMercadoLoteConstruccionVentaDTO;
import com.helio4.bancol.avaluos.dto.OfertaLoteConConstruccionVentaDTO;
import com.helio4.bancol.avaluos.modelo.Avaluo;
import com.helio4.bancol.avaluos.modelo.ComparacionMercadoLoteConstruccionVenta;
import com.helio4.bancol.avaluos.modelo.Oferta;
import com.helio4.bancol.avaluos.modelo.OfertaLoteConConstruccionVenta;
import com.helio4.bancol.avaluos.persistencia.AvaluoRepository;
import com.helio4.bancol.avaluos.persistencia.ComparacionMercadoLoteConstruccionVentaRepository;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.MetodoValuacionNotFoundException;

@Component
public class ComparacionMercadoLoteConstruccionVentaEnsamblador {
	
	@Autowired
	private OfertaLoteConstruccionVentaEnsamblador ofertaLoteConstruccionVentaEnsamblador;
	
	@Autowired
	private ComparacionMercadoLoteConstruccionVentaRepository comparacionMercadoLoteConstruccionVentaRepository;

	@Autowired
	private AvaluoRepository avaluoRepository;
	
	
	/**
	 * Función que se encarga de construir un DTO a partir del modelo
	 * 
	 * @param comparacionMercado modelo a partir del cual se va a construir el DTO
	 * @return  ComparacionMercadoPHVentaDTO  construido.
	 * */
	public ComparacionMercadoLoteConstruccionVentaDTO escribirDTO( ComparacionMercadoLoteConstruccionVenta comparacionMercado ){
		ComparacionMercadoLoteConstruccionVentaDTO comparacion = new ComparacionMercadoLoteConstruccionVentaDTO(comparacionMercado);
		
		if( comparacionMercado.getOfertas()!=null ){
			List<Oferta> ofertas =  comparacionMercado.getOfertas();
			List<OfertaLoteConConstruccionVenta> ofertasLoteConConstruccionVenta = new ArrayList<>();
			
			for(Oferta oferta:ofertas){
				ofertasLoteConConstruccionVenta.add((OfertaLoteConConstruccionVenta)oferta);
			}
			
			 List<OfertaLoteConConstruccionVentaDTO> ofertasLoteCVDTO = this.ofertaLoteConstruccionVentaEnsamblador.escribirListaDTO(ofertasLoteConConstruccionVenta);
			 comparacion.setOfertasConstruccionVenta(ofertasLoteCVDTO);
		}		
		return comparacion;
	}
	
	public void actualizar(ComparacionMercadoLoteConstruccionVentaDTO comparacionActualizada) throws AvaluoNotFoundException {
		ComparacionMercadoLoteConstruccionVenta comparacion = this.comparacionMercadoLoteConstruccionVentaRepository.findOne( comparacionActualizada.getId() );
		// TODO: Controlar si la comparación no esta en BD
		this.actuliazar(comparacionActualizada, comparacion); 		
	}
	
	
	public void actuliazar(ComparacionMercadoLoteConstruccionVentaDTO actualizada, ComparacionMercadoLoteConstruccionVenta comparacion) throws AvaluoNotFoundException{
		Avaluo avaluo = this.avaluoRepository.findOne(actualizada.getAvaluoId());
		if (avaluo == null) {
			throw new AvaluoNotFoundException();
		}

		comparacion.cargarLoteConstruccionVentaDTO(actualizada);
		comparacion.setAvaluo( avaluo );
		
	}
	
	/**
	 * Función que se encarga de crear un modelo a partir del DTO.
	 * 
	 * @param comparacionMercado DTO con el que se crea el modelo.
	 * @return ComparacionMercadoPh modelo creado.
	 * @throws AvaluoNotFoundException 
	 * */
	public ComparacionMercadoLoteConstruccionVenta crearComparacionMercadoLoteconstruccionVenta( ComparacionMercadoLoteConstruccionVentaDTO comparacionMercado ) 
			throws MetodoValuacionNotFoundException, AvaluoNotFoundException{
		
		ComparacionMercadoLoteConstruccionVenta comparacion = new ComparacionMercadoLoteConstruccionVenta();
		
		Avaluo avaluo = avaluoRepository.findOne(comparacionMercado.getAvaluoId());
		if (avaluo == null) {
			throw new AvaluoNotFoundException();
		}
		
		comparacion.setAvaluo( avaluo );
		comparacion.cargarLoteConstruccionVentaDTO(comparacionMercado);
		
		return comparacion;
	}
	
	
}
