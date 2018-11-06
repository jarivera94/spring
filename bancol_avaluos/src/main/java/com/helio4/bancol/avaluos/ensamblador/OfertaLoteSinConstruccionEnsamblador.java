package com.helio4.bancol.avaluos.ensamblador;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.MetodoValuacionDTO;
import com.helio4.bancol.avaluos.dto.OfertaLoteSinConstruccionDTO;
import com.helio4.bancol.avaluos.dto.OfertaPHDTO;
import com.helio4.bancol.avaluos.exception.OfertaNotFoundException;
import com.helio4.bancol.avaluos.modelo.OfertaLoteSinConstruccion;
import com.helio4.bancol.avaluos.persistencia.OfertaLoteSinConstruccionRepository;
import com.helio4.bancol.avaluos.servicio.datos.OfertaLoteSinConstruccionService;
import com.helio4.bancol.avaluos.servicio.excepciones.MetodoValuacionNotFoundException;

@Component
public class OfertaLoteSinConstruccionEnsamblador {
	
	private static Logger log = LoggerFactory
			.getLogger(OfertaLoteSinConstruccionEnsamblador.class);

	@Autowired
	private OfertaLoteEnsamblador ofertaLoteEnsamblador;
		
	@Autowired
	OfertaLoteSinConstruccionService ofertaLoteSinConstruccionService;
	
	@Autowired
	OfertaLoteSinConstruccionRepository ofertaLoteSinConstruccionRepository;
	
	/**
	 * Crea una OfertaDTO a partir del modelo:Oferta
	 * 
	 * @param Oferta a convertir
	 * @return OfertaDTO convertida.
	 * */
	public OfertaLoteSinConstruccionDTO escribirDTO(OfertaLoteSinConstruccion ofertaLoteSinConstruccion) {
		
		OfertaLoteSinConstruccionDTO  ofertaSinConstruccionDTO = new OfertaLoteSinConstruccionDTO(ofertaLoteSinConstruccion);
		
		return ofertaSinConstruccionDTO;
	}
	
	/**
	 * Crear una lista de OfertaDTO a partir de una lista de modelo: Oferta
	 * 
	 * @param
	 * @return
	 * */
	public List<OfertaLoteSinConstruccionDTO> escribirListaDTO( List<OfertaLoteSinConstruccion> ofertas){
		List<OfertaLoteSinConstruccionDTO> ofertasDTO = new ArrayList<>();
		for( OfertaLoteSinConstruccion oferta: ofertas){
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
	public OfertaLoteSinConstruccion crearOfertaLoteSinConstruccion(OfertaLoteSinConstruccionDTO ofertaDTO, MetodoValuacionDTO metodoValuacionDTO) throws MetodoValuacionNotFoundException {
		
		OfertaLoteSinConstruccion ofertaSinConstruccion = (OfertaLoteSinConstruccion)ofertaLoteEnsamblador.crearOfertaLote(ofertaDTO, metodoValuacionDTO);
		
		ofertaSinConstruccion.setId(ofertaDTO.getId());
		ofertaSinConstruccion.setFactorHomologacionForma(ofertaDTO.getFactorHomologacionForma().getKey());
		ofertaSinConstruccion.setFactorHomologacionSuperficie(ofertaDTO.getFactorHomologacionSuperficie());
		ofertaSinConstruccion.setFactorHomologacionTipografia(ofertaDTO.getFactorHomologacionTipografia().getKey());
		ofertaSinConstruccion.setFactorHomologacionUbicacion(ofertaDTO.getFactorHomologacionUbicacion().getKey());
		ofertaSinConstruccion.setForma(ofertaDTO.getForma().getKey());
		ofertaSinConstruccion.setUbicacionLote(ofertaDTO.getUbicacionLote().getKey());
		
		return ofertaSinConstruccion;
	}
	
	/**
	 * Setea los campos de un modelo con base al DTO.
	 * */
	public OfertaLoteSinConstruccion crearOfertaLoteSinConstruccion(OfertaLoteSinConstruccion ofertaLoteSinConstruccion,OfertaLoteSinConstruccionDTO ofertaDTO) {

		ofertaLoteEnsamblador.crearOfertaLote(ofertaLoteSinConstruccion,ofertaDTO);
		
		ofertaLoteSinConstruccion.setFactorHomologacionForma(ofertaDTO.getFactorHomologacionForma().getKey());
		ofertaLoteSinConstruccion.setFactorHomologacionSuperficie(ofertaDTO.getFactorHomologacionSuperficie());
		ofertaLoteSinConstruccion.setFactorHomologacionTipografia(ofertaDTO.getFactorHomologacionTipografia().getKey());
		ofertaLoteSinConstruccion.setFactorHomologacionUbicacion(ofertaDTO.getFactorHomologacionUbicacion().getKey());
		ofertaLoteSinConstruccion.setForma(ofertaDTO.getForma().getKey());
		ofertaLoteSinConstruccion.setUbicacionLote(ofertaDTO.getUbicacionLote().getKey());
		
		return ofertaLoteSinConstruccion;
	}
	
	/**
	 * Realiza una conversion de List<OfertaDTO> a List<Oferta>
	 * @param List<OfertaDTO> lista de ofertas DTO a convertir.
	 * @return List<Oferta> lista de ofertas
	 * @throws MetodoValuacionNotFoundException 
	 * */
	public List<OfertaLoteSinConstruccion> crearLista( List<OfertaLoteSinConstruccionDTO> ofertasLoteSinConstruccionDTO, MetodoValuacionDTO metodoValuacionDTO ) throws MetodoValuacionNotFoundException{
		List<OfertaLoteSinConstruccion> ofertas = new ArrayList<>();
		for( OfertaLoteSinConstruccionDTO ofertaLoteSinConstruccionDTO : ofertasLoteSinConstruccionDTO ){
			ofertas.add(this.crearOfertaLoteSinConstruccion(ofertaLoteSinConstruccionDTO, metodoValuacionDTO));
		}
		return ofertas;
	}
	
	@Transactional(rollbackFor = OfertaNotFoundException.class)
	public void actualizarOfertas(List<OfertaLoteSinConstruccionDTO> ofertasLoteSinConstruccionDTO,
			MetodoValuacionDTO metodoValuacionDTO) {
		log.info(" ACTULIZANDO OFERTAS LOTE Sin Construccion");

		List<OfertaLoteSinConstruccionDTO> ofertasActuales = ofertaLoteSinConstruccionRepository.encontrarPorMetodoValuacion(metodoValuacionDTO.getId());

		if (ofertasLoteSinConstruccionDTO != null && !ofertasLoteSinConstruccionDTO.isEmpty()) {

			for (OfertaLoteSinConstruccionDTO ofertaLoteSinConstruccionDTO : ofertasLoteSinConstruccionDTO) {
				this.ofertaLoteSinConstruccionService.crear(ofertaLoteSinConstruccionDTO, metodoValuacionDTO);
				ofertasActuales.remove(ofertaLoteSinConstruccionDTO);
			}

			for (OfertaLoteSinConstruccionDTO ofertaLoteSinConstruccionDTO : ofertasActuales) {
				try {
					this.ofertaLoteSinConstruccionService.eliminar(ofertaLoteSinConstruccionDTO.getId());
				} catch (OfertaNotFoundException e) {
					e.printStackTrace();
				}
			}

		}

	}

}
