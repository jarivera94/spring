package com.helio4.bancol.avaluos.ensamblador;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.MetodoValuacionDTO;
import com.helio4.bancol.avaluos.dto.OfertaLoteConConstruccionVentaDTO;
import com.helio4.bancol.avaluos.dto.OfertaLoteSinConstruccionDTO;
import com.helio4.bancol.avaluos.exception.OfertaNotFoundException;
import com.helio4.bancol.avaluos.modelo.OfertaLoteConConstruccionVenta;
import com.helio4.bancol.avaluos.persistencia.OfertaLoteConConstruccionVentaRepository;
import com.helio4.bancol.avaluos.servicio.datos.OfertaLoteConConstruccionVentaService;
import com.helio4.bancol.avaluos.servicio.excepciones.MetodoValuacionNotFoundException;

@Component
public class OfertaLoteConstruccionVentaEnsamblador {
	
	private static Logger log = LoggerFactory
			.getLogger(OfertaLoteConstruccionVentaEnsamblador.class);

	@Autowired
	private OfertaLoteConstruccionEnsamblador ofertaLoteConstruccionEnsamblador;
		
	@Autowired
	OfertaLoteConConstruccionVentaService ofertaLoteConConstruccionVentaService;
	
	@Autowired
	OfertaLoteConConstruccionVentaRepository ofertaLoteConConstruccionVentaRepository;
	
	/**
	 * Crea una OfertaDTO a partir del modelo:Oferta
	 * 
	 * @param Oferta a convertir
	 * @return OfertaDTO convertida.
	 * */
	public OfertaLoteConConstruccionVentaDTO escribirDTO(OfertaLoteConConstruccionVenta ofertaLoteConstruccionVenta) {
		
		OfertaLoteConConstruccionVentaDTO  ofertaConConstruccionVentaDTO = new OfertaLoteConConstruccionVentaDTO(ofertaLoteConstruccionVenta);
		
		return ofertaConConstruccionVentaDTO;
	}
	
	/**
	 * Crear una lista de OfertaDTO a partir de una lista de modelo: Oferta
	 * 
	 * @param
	 * @return
	 * */
	public List<OfertaLoteConConstruccionVentaDTO> escribirListaDTO( List<OfertaLoteConConstruccionVenta> ofertas){
		List<OfertaLoteConConstruccionVentaDTO> ofertasDTO = new ArrayList<>();
		for( OfertaLoteConConstruccionVenta oferta: ofertas){
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
	public OfertaLoteConConstruccionVenta crearOfertaLoteConstruccionVenta(OfertaLoteConConstruccionVentaDTO ofertaDTO, MetodoValuacionDTO metodoValuacionDTO) throws MetodoValuacionNotFoundException {
		
		OfertaLoteConConstruccionVenta ofertaConstruccionVenta = (OfertaLoteConConstruccionVenta) ofertaLoteConstruccionEnsamblador.crearOfertaLoteConstruccion(ofertaDTO, metodoValuacionDTO);
		
		ofertaConstruccionVenta.setId(ofertaDTO.getId());
		ofertaConstruccionVenta.setValorConstruccionM2(ofertaDTO.getValorConstruccionM2());
		ofertaConstruccionVenta.setValorUnitarioM2Homogenizado(ofertaDTO.getValorUnitarioM2Homogenizado());
		
		return ofertaConstruccionVenta;
	}
	
	/**
	 * Setea los campos de un modelo con base al DTO.
	 * */
	public OfertaLoteConConstruccionVenta crearOfertaLoteConstruccionVenta(OfertaLoteConConstruccionVenta ofertaLoteConstruccionVenta,OfertaLoteConConstruccionVentaDTO ofertaDTO) {

		ofertaLoteConstruccionEnsamblador.crearOfertaLoteConstruccion(ofertaLoteConstruccionVenta,ofertaDTO);
		
		ofertaLoteConstruccionVenta.setValorConstruccionM2(ofertaDTO.getValorConstruccionM2());
		ofertaLoteConstruccionVenta.setValorUnitarioM2Homogenizado(ofertaDTO.getValorUnitarioM2Homogenizado());
		
		return ofertaLoteConstruccionVenta;
	}
	
	/**
	 * Realiza una conversion de List<OfertaDTO> a List<Oferta>
	 * @param List<OfertaDTO> lista de ofertas DTO a convertir.
	 * @return List<Oferta> lista de ofertas
	 * @throws MetodoValuacionNotFoundException 
	 * */
	public List<OfertaLoteConConstruccionVenta> crearLista( List<OfertaLoteConConstruccionVentaDTO> ofertasLoteConstruccionVentaDTO, MetodoValuacionDTO metodoValuacionDTO ) throws MetodoValuacionNotFoundException{
		List<OfertaLoteConConstruccionVenta> ofertas = new ArrayList<>();
		for( OfertaLoteConConstruccionVentaDTO ofertaLoteConstruccionDTO : ofertasLoteConstruccionVentaDTO ){
			ofertas.add(this.crearOfertaLoteConstruccionVenta(ofertaLoteConstruccionDTO, metodoValuacionDTO));
		}
		return ofertas;
	}
	
	@Transactional(rollbackFor = OfertaNotFoundException.class)
	public void actualizarOfertas(List<OfertaLoteConConstruccionVentaDTO> ofertasLoteConstruccionVentaDTO,
			MetodoValuacionDTO metodoValuacionDTO) {
		log.info(" ACTULIZANDO OFERTAS LOTE Construccion Venta");

		List<OfertaLoteConConstruccionVentaDTO> ofertasActuales = ofertaLoteConConstruccionVentaRepository.encontrarPorMetodoValuacion(metodoValuacionDTO.getId());

		if (ofertasLoteConstruccionVentaDTO != null && !ofertasLoteConstruccionVentaDTO.isEmpty()) {

			for (OfertaLoteConConstruccionVentaDTO ofertaLoteConstruccionVentaDTO : ofertasLoteConstruccionVentaDTO) {
				this.ofertaLoteConConstruccionVentaService.crear(ofertaLoteConstruccionVentaDTO, metodoValuacionDTO);
				ofertasActuales.remove(ofertaLoteConstruccionVentaDTO);
			}

			for (OfertaLoteConConstruccionVentaDTO ofertaLoteConstruccionVentaDTO : ofertasActuales) {
				try {
					this.ofertaLoteConConstruccionVentaService.eliminar(ofertaLoteConstruccionVentaDTO.getId());
				} catch (OfertaNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
