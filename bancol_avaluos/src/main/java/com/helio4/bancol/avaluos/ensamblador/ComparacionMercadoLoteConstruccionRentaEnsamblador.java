package com.helio4.bancol.avaluos.ensamblador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.ComparacionMercadoLoteConstruccionRentaDTO;
import com.helio4.bancol.avaluos.dto.OfertaLoteConConstruccionRentaDTO;
import com.helio4.bancol.avaluos.modelo.Avaluo;
import com.helio4.bancol.avaluos.modelo.ComparacionMercadoLoteConstruccionRenta;
import com.helio4.bancol.avaluos.modelo.Oferta;
import com.helio4.bancol.avaluos.modelo.OfertaLoteConConstruccionRenta;
import com.helio4.bancol.avaluos.persistencia.AvaluoRepository;
import com.helio4.bancol.avaluos.persistencia.ComparacionMercadoLoteConstruccionRentaRepository;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.MetodoValuacionNotFoundException;

@Component
public class ComparacionMercadoLoteConstruccionRentaEnsamblador {
	
	@Autowired
	private OfertaLoteConstruccionRentaEnsamblador ofertaLoteConstruccionRentaEnsamblador;
	
	@Autowired
	private ComparacionMercadoLoteConstruccionRentaRepository comparacionMercadoLoteConstruccionRentaRepository;

	@Autowired
	private AvaluoRepository avaluoRepository;
	
	
	/**
	 * Función que se encarga de construir un DTO a partir del modelo
	 * 
	 * @param comparacionMercado modelo a partir del cual se va a construir el DTO
	 * @return  ComparacionMercadoPHVentaDTO  construido.
	 * */
	public ComparacionMercadoLoteConstruccionRentaDTO escribirDTO( ComparacionMercadoLoteConstruccionRenta comparacionMercado ){
		ComparacionMercadoLoteConstruccionRentaDTO comparacion = new ComparacionMercadoLoteConstruccionRentaDTO(comparacionMercado);
		
		if( comparacionMercado.getOfertas()!=null ){
			List<Oferta> ofertas =  comparacionMercado.getOfertas();
			List<OfertaLoteConConstruccionRenta> ofertasLoteConConstruccionRenta = new ArrayList<>();
			
			for(Oferta oferta:ofertas){
				ofertasLoteConConstruccionRenta.add((OfertaLoteConConstruccionRenta)oferta);
			}
			
			 List<OfertaLoteConConstruccionRentaDTO> ofertasLoteCRDTO = this.ofertaLoteConstruccionRentaEnsamblador.escribirListaDTO(ofertasLoteConConstruccionRenta);
			 comparacion.setOfertasConstruccionRenta(ofertasLoteCRDTO);
		}		
		return comparacion;
	}
	
	public void actualizar(ComparacionMercadoLoteConstruccionRentaDTO comparacionActualizada) throws AvaluoNotFoundException {
		ComparacionMercadoLoteConstruccionRenta comparacion = this.comparacionMercadoLoteConstruccionRentaRepository.findOne(comparacionActualizada.getId() );
		// TODO: Controlar si la comparación no esta en BD
		this.actuliazar(comparacionActualizada, comparacion); 		
	}
	
	
	public void actuliazar(ComparacionMercadoLoteConstruccionRentaDTO actualizada, ComparacionMercadoLoteConstruccionRenta comparacion) throws AvaluoNotFoundException{
		Avaluo avaluo = this.avaluoRepository.findOne(actualizada.getAvaluoId());
		if (avaluo == null) {
			throw new AvaluoNotFoundException();
		}

		comparacion.cargarLoteConstruccionRentaDTO(actualizada);
		comparacion.setAvaluo( avaluo );
		
	}
	
	/**
	 * Función que se encarga de crear un modelo a partir del DTO.
	 * 
	 * @param comparacionMercado DTO con el que se crea el modelo.
	 * @return ComparacionMercadoPh modelo creado.
	 * @throws AvaluoNotFoundException 
	 * */
	public ComparacionMercadoLoteConstruccionRenta crearComparacionMercadoLoteConstruccionRenta( ComparacionMercadoLoteConstruccionRentaDTO comparacionMercado ) 
			throws MetodoValuacionNotFoundException, AvaluoNotFoundException{
		
		ComparacionMercadoLoteConstruccionRenta comparacion = new ComparacionMercadoLoteConstruccionRenta();
		
		Avaluo avaluo = avaluoRepository.findOne(comparacionMercado.getAvaluoId());
		if (avaluo == null) {
			throw new AvaluoNotFoundException();
		}
		
		comparacion.setAvaluo( avaluo );
		comparacion.cargarLoteConstruccionRentaDTO(comparacionMercado);
		
		return comparacion;
	}
	
	
}
