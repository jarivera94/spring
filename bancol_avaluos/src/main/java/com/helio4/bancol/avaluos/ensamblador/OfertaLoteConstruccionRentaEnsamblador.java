package com.helio4.bancol.avaluos.ensamblador;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.MetodoValuacionDTO;
import com.helio4.bancol.avaluos.dto.OfertaLoteConConstruccionRentaDTO;
import com.helio4.bancol.avaluos.dto.OfertaLoteConConstruccionVentaDTO;
import com.helio4.bancol.avaluos.exception.OfertaNotFoundException;
import com.helio4.bancol.avaluos.modelo.OfertaLoteConConstruccionRenta;
import com.helio4.bancol.avaluos.persistencia.OfertaLoteConConstruccionRentaRepository;
import com.helio4.bancol.avaluos.servicio.datos.OfertaLoteConConstruccionRentaService;
import com.helio4.bancol.avaluos.servicio.excepciones.MetodoValuacionNotFoundException;

@Component
public class OfertaLoteConstruccionRentaEnsamblador {
	
	private static Logger log = LoggerFactory
			.getLogger(OfertaLoteConstruccionRentaEnsamblador.class);

	@Autowired
	private OfertaLoteConstruccionEnsamblador ofertaLoteConstruccionEnsamblador;
		
	@Autowired
	OfertaLoteConConstruccionRentaService ofertaLoteConConstruccionRentaService;
	
	@Autowired
	OfertaLoteConConstruccionRentaRepository ofertaLoteConConstruccionRentaRepository;
	
	/**
	 * Crea una OfertaDTO a partir del modelo:Oferta
	 * 
	 * @param Oferta a convertir
	 * @return OfertaDTO convertida.
	 * */
	public OfertaLoteConConstruccionRentaDTO escribirDTO(OfertaLoteConConstruccionRenta ofertaLoteConstruccionRenta) {
		
		OfertaLoteConConstruccionRentaDTO  ofertaConConstruccionRentaDTO = new OfertaLoteConConstruccionRentaDTO(ofertaLoteConstruccionRenta);
		
		return ofertaConConstruccionRentaDTO;
	}
	
	/**
	 * Crear una lista de OfertaDTO a partir de una lista de modelo: Oferta
	 * 
	 * @param
	 * @return
	 * */
	public List<OfertaLoteConConstruccionRentaDTO> escribirListaDTO( List<OfertaLoteConConstruccionRenta> ofertas){
		List<OfertaLoteConConstruccionRentaDTO> ofertasDTO = new ArrayList<>();
		for( OfertaLoteConConstruccionRenta oferta: ofertas){
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
	public OfertaLoteConConstruccionRenta crearOfertaLoteConstruccionRenta(OfertaLoteConConstruccionRentaDTO ofertaDTO, MetodoValuacionDTO metodoValuacionDTO) throws MetodoValuacionNotFoundException {
		
		OfertaLoteConConstruccionRenta ofertaConstruccionRenta = (OfertaLoteConConstruccionRenta) ofertaLoteConstruccionEnsamblador.crearOfertaLoteConstruccion(ofertaDTO, metodoValuacionDTO);
		
		ofertaConstruccionRenta.setId(ofertaDTO.getOfertaLoteConConstruccionRentaId());
		ofertaConstruccionRenta.setValorUnitarioM2(ofertaDTO.getValorUnitarioM2());
		ofertaConstruccionRenta.setValorUnitarioResultanteM2(ofertaDTO.getValorUnitarioResultanteM2());
		
		return ofertaConstruccionRenta;
	}
	
	/**
	 * Setea los campos de un modelo con base al DTO.
	 * */
	public OfertaLoteConConstruccionRenta crearOfertaLoteConstruccionRenta(OfertaLoteConConstruccionRenta ofertaLoteConstruccionRenta,OfertaLoteConConstruccionRentaDTO ofertaDTO) {

		ofertaLoteConstruccionEnsamblador.crearOfertaLoteConstruccion(ofertaLoteConstruccionRenta,ofertaDTO);
		
		ofertaLoteConstruccionRenta.setValorUnitarioM2(ofertaDTO.getValorUnitarioM2());
		ofertaLoteConstruccionRenta.setValorUnitarioResultanteM2(ofertaDTO.getValorUnitarioResultanteM2());
		
		return ofertaLoteConstruccionRenta;
	}
	
	/**
	 * Realiza una conversion de List<OfertaDTO> a List<Oferta>
	 * @param List<OfertaDTO> lista de ofertas DTO a convertir.
	 * @return List<Oferta> lista de ofertas
	 * @throws MetodoValuacionNotFoundException 
	 * */
	public List<OfertaLoteConConstruccionRenta> crearLista( List<OfertaLoteConConstruccionRentaDTO> ofertasLoteConstruccionRentaDTO, MetodoValuacionDTO metodoValuacionDTO ) throws MetodoValuacionNotFoundException{
		List<OfertaLoteConConstruccionRenta> ofertas = new ArrayList<>();
		for( OfertaLoteConConstruccionRentaDTO ofertaLoteConstruccionRentaDTO : ofertasLoteConstruccionRentaDTO ){
			ofertas.add(this.crearOfertaLoteConstruccionRenta(ofertaLoteConstruccionRentaDTO,metodoValuacionDTO));
		}
		return ofertas;
	}
	
	@Transactional(rollbackFor = OfertaNotFoundException.class)
	public void actualizarOfertas(List<OfertaLoteConConstruccionRentaDTO> ofertasLoteConstruccionRentaDTO, MetodoValuacionDTO metodoValuacionDTO) {
		log.info( " ACTULIZANDO OFERTAS LOTE Construccion Venta" );
		
		List<OfertaLoteConConstruccionRentaDTO> ofertasActuales = ofertaLoteConConstruccionRentaRepository.encontrarPorMetodoValuacion(metodoValuacionDTO.getId());

		if (ofertasLoteConstruccionRentaDTO != null && !ofertasLoteConstruccionRentaDTO.isEmpty()) {

			for (OfertaLoteConConstruccionRentaDTO ofertaLoteConstruccionRentaDTO : ofertasLoteConstruccionRentaDTO) {
				this.ofertaLoteConConstruccionRentaService.crear(ofertaLoteConstruccionRentaDTO, metodoValuacionDTO);
				ofertasActuales.remove(ofertaLoteConstruccionRentaDTO);
			}
			for (OfertaLoteConConstruccionRentaDTO ofertaLoteConstruccionRentaDTO : ofertasActuales) {
				try {
					this.ofertaLoteConConstruccionRentaService.eliminar(ofertaLoteConstruccionRentaDTO.getId());
				} catch (OfertaNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
