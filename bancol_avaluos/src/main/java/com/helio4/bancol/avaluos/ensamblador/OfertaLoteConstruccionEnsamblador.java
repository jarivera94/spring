package com.helio4.bancol.avaluos.ensamblador;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.MetodoValuacionDTO;
import com.helio4.bancol.avaluos.dto.OfertaLoteConConstruccionDTO;
import com.helio4.bancol.avaluos.modelo.OfertaLoteConConstruccion;
import com.helio4.bancol.avaluos.servicio.excepciones.MetodoValuacionNotFoundException;

@Component
public class OfertaLoteConstruccionEnsamblador {
	
	private static Logger log = LoggerFactory
			.getLogger(OfertaLoteConstruccionEnsamblador.class);

	@Autowired
	private OfertaLoteEnsamblador ofertaLoteEnsamblador;
		
	/**
	 * Crea una OfertaDTO a partir del modelo:Oferta
	 * 
	 * @param Oferta a convertir
	 * @return OfertaDTO convertida.
	 * */
	public OfertaLoteConConstruccionDTO escribirDTO(OfertaLoteConConstruccion ofertaLoteConstruccion) {
		
		OfertaLoteConConstruccionDTO  ofertaConConstruccionDTO = new OfertaLoteConConstruccionDTO(ofertaLoteConstruccion);
		
		return ofertaConConstruccionDTO;
	}
	
	/**
	 * Crear una lista de OfertaDTO a partir de una lista de modelo: Oferta
	 * 
	 * @param
	 * @return
	 * */
	public List<OfertaLoteConConstruccionDTO> escribirListaDTO( List<OfertaLoteConConstruccion> ofertas){
		List<OfertaLoteConConstruccionDTO> ofertasDTO = new ArrayList<>();
		for( OfertaLoteConConstruccion oferta: ofertas){
			ofertasDTO.add( this.escribirDTO(oferta) );
		}
		return ofertasDTO;
	}
	/**
	 * Convierte una OfertaDTO a un modelo:Oferta
	 * @param OfertaDTO
	 * @return Oferta
	 * @throws MetodoValuacionNotFoundException 
	 * */
	public OfertaLoteConConstruccion crearOfertaLoteConstruccion(OfertaLoteConConstruccionDTO ofertaDTO, MetodoValuacionDTO metodoValuacionDTO) throws MetodoValuacionNotFoundException {
		
		OfertaLoteConConstruccion ofertaConstruccion =  (OfertaLoteConConstruccion) ofertaLoteEnsamblador.crearOfertaLote(ofertaDTO, metodoValuacionDTO);
		
		ofertaConstruccion.setId(ofertaDTO.getId());
		ofertaConstruccion.setAreaConstruidaM2(ofertaDTO.getAreaConstruidaM2());
		ofertaConstruccion.setFactorHomologacionConservacion(ofertaDTO.getFactorHomologacionConservacion().getKey());
		ofertaConstruccion.setFactorHomologacionConstruccion(ofertaDTO.getFactorHomologacionConstruccion());
		ofertaConstruccion.setFactorHomologacionLote(ofertaDTO.getFactorHomologacionLote());
		ofertaConstruccion.setAreaConstruccion(ofertaDTO.getAreaConstruccion());
		
		return ofertaConstruccion;
	}
	
	/**
	 * Setea los campos de un modelo con base al DTO.
	 * */
	public OfertaLoteConConstruccion crearOfertaLoteConstruccion(OfertaLoteConConstruccion ofertaLoteConstruccion,OfertaLoteConConstruccionDTO ofertaDTO) {

		ofertaLoteEnsamblador.crearOfertaLote(ofertaLoteConstruccion,ofertaDTO);
		
		ofertaLoteConstruccion.setAreaConstruidaM2(ofertaDTO.getAreaConstruidaM2());
		ofertaLoteConstruccion.setFactorHomologacionConservacion(ofertaDTO.getFactorHomologacionConservacion().getKey());
		ofertaLoteConstruccion.setFactorHomologacionConstruccion(ofertaDTO.getFactorHomologacionConstruccion());
		ofertaLoteConstruccion.setFactorHomologacionLote(ofertaDTO.getFactorHomologacionLote());
		ofertaLoteConstruccion.setAreaConstruccion(ofertaDTO.getAreaConstruccion());
		
		return ofertaLoteConstruccion;
	}
	
	/**
	 * Realiza una conversion de List<OfertaDTO> a List<Oferta>
	 * @param List<OfertaDTO> lista de ofertas DTO a convertir.
	 * @return List<Oferta> lista de ofertas
	 * @throws MetodoValuacionNotFoundException 
	 * */
	public List<OfertaLoteConConstruccion> crearLista( List<OfertaLoteConConstruccionDTO> ofertasLoteConstruccionDTO, MetodoValuacionDTO metodoValuacionDTO ) throws MetodoValuacionNotFoundException{
		List<OfertaLoteConConstruccion> ofertas = new ArrayList<>();
		
		if (ofertasLoteConstruccionDTO != null && !ofertasLoteConstruccionDTO.isEmpty()) {

			for (OfertaLoteConConstruccionDTO ofertaLoteConstruccionDTO : ofertasLoteConstruccionDTO) {
				ofertas.add(this.crearOfertaLoteConstruccion(ofertaLoteConstruccionDTO, metodoValuacionDTO));
			}
		}
		return ofertas;
	}
	
	
}
