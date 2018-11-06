package com.helio4.bancol.avaluos.ensamblador;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.MetodoValuacionDTO;
import com.helio4.bancol.avaluos.dto.OfertaLoteDTO;
import com.helio4.bancol.avaluos.exception.OfertaNotFoundException;
import com.helio4.bancol.avaluos.modelo.Oferta;
import com.helio4.bancol.avaluos.modelo.OfertaLote;
import com.helio4.bancol.avaluos.servicio.datos.OfertaLoteService;
import com.helio4.bancol.avaluos.servicio.excepciones.MetodoValuacionNotFoundException;

@Component
public class OfertaLoteEnsamblador {
	
	private static Logger log = LoggerFactory
			.getLogger(OfertaLoteEnsamblador.class);

	@Autowired
	private OfertaEnsamblador ofertaEnsamblador;
		
	@Autowired
	OfertaLoteService ofertaLoteService;
	
	/**
	 * Crea una OfertaDTO a partir del modelo:Oferta
	 * 
	 * @param Oferta a convertir
	 * @return OfertaDTO convertida.
	 * */
	public OfertaLoteDTO escribirDTO(OfertaLote ofertaLote) {
		
		OfertaLoteDTO  ofertaLoteDTO = new OfertaLoteDTO(ofertaLote);
		
		return ofertaLoteDTO;
	}
	
	/**
	 * Crear una lista de OfertaDTO a partir de una lista de modelo: Oferta
	 * 
	 * @param
	 * @return
	 * */
	public List<OfertaLoteDTO> escribirListaDTO( List<OfertaLote> ofertas){
		List<OfertaLoteDTO> ofertasDTO = new ArrayList<>();
		for( OfertaLote oferta: ofertas){
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
	public OfertaLote crearOfertaLote(OfertaLoteDTO ofertaDTO, MetodoValuacionDTO metodoValuacionDTO) throws MetodoValuacionNotFoundException {
		
		OfertaLote ofertaLote = new OfertaLote();
		ofertaEnsamblador.crearOferta(ofertaLote, ofertaDTO, metodoValuacionDTO);
		
		ofertaLote.setId(ofertaDTO.getId());
		ofertaLote.setComparacion(ofertaDTO.getComparacion().getKey());
		ofertaLote.setAreaLoteM2(ofertaDTO.getAreaLoteM2());
		ofertaLote.setPrecioUnitarioM2(ofertaDTO.getPrecioUnitarioM2());
		ofertaLote.setValorM2Ajustado(ofertaDTO.getValorM2Ajustado());
		
		return ofertaLote;
	}
	
	/**
	 * Setea los campos de un modelo con base al DTO.
	 * */
	public OfertaLote crearOfertaLote(OfertaLote ofertaLote,OfertaLoteDTO ofertaDTO) {
		
		ofertaEnsamblador.crearOferta(ofertaLote,ofertaDTO);
		
		ofertaLote.setComparacion(ofertaDTO.getComparacion().getKey());
		ofertaLote.setAreaLoteM2(ofertaDTO.getAreaLoteM2());
		ofertaLote.setPrecioUnitarioM2(ofertaDTO.getPrecioUnitarioM2());
		ofertaLote.setValorM2Ajustado(ofertaDTO.getValorM2Ajustado());
		
		return ofertaLote;
	}
	
	/**
	 * Realiza una conversion de List<OfertaDTO> a List<Oferta>
	 * @param List<OfertaDTO> lista de ofertas DTO a convertir.
	 * @return List<Oferta> lista de ofertas
	 * @throws MetodoValuacionNotFoundException 
	 * */
	public List<OfertaLote> crearLista( List<OfertaLoteDTO> ofertasLoteDTO, MetodoValuacionDTO metodoValuacionDTO ) throws MetodoValuacionNotFoundException{
		List<OfertaLote> ofertas = new ArrayList<>();
		for( OfertaLoteDTO ofertaLoteDTO : ofertasLoteDTO ){
			ofertas.add(this.crearOfertaLote(ofertaLoteDTO, metodoValuacionDTO));
		}
		return ofertas;
	}
	
	@Transactional(rollbackFor = OfertaNotFoundException.class)
	public void actualizarOfertas(List<OfertaLoteDTO> ofertasLoteDTO, MetodoValuacionDTO metodoValuacionDTO) {
		log.info( " ACTULIZANDO OFERTAS LOTE" );
		
		if (ofertasLoteDTO != null && !ofertasLoteDTO.isEmpty()) {
			for (OfertaLoteDTO ofertaLoteDTO : ofertasLoteDTO) {
				this.ofertaLoteService.crear(ofertaLoteDTO, metodoValuacionDTO);
			}

		}
		
	}

}
