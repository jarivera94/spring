package com.helio4.bancol.avaluos.servicio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.MetodoValuacionDTO;
import com.helio4.bancol.avaluos.dto.OfertaLoteConConstruccionVentaDTO;
import com.helio4.bancol.avaluos.ensamblador.MetodoValuacionEnsamblador;
import com.helio4.bancol.avaluos.ensamblador.OfertaLoteConstruccionEnsamblador;
import com.helio4.bancol.avaluos.ensamblador.OfertaLoteConstruccionVentaEnsamblador;
import com.helio4.bancol.avaluos.exception.OfertaNotFoundException;
import com.helio4.bancol.avaluos.modelo.MetodoValuacion;
import com.helio4.bancol.avaluos.modelo.OfertaLoteConConstruccionVenta;
import com.helio4.bancol.avaluos.persistencia.MetodoValuacionRepository;
import com.helio4.bancol.avaluos.persistencia.OfertaLoteConConstruccionVentaRepository;
import com.helio4.bancol.avaluos.servicio.datos.OfertaLoteConConstruccionVentaService;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;

@Component(value="repositoryOfertaLoteContruccionVentaService")
@Transactional(readOnly = true)
public class RepositoryOfertaLoteContruccionVentaService implements OfertaLoteConConstruccionVentaService {

	private static Logger log = LoggerFactory
			.getLogger(RepositoryOfertaLoteContruccionVentaService.class);

	@Autowired
	private OfertaLoteConstruccionEnsamblador ofertaLoteConstruccionEnsamblador;
	
	@Autowired
	private OfertaLoteConstruccionVentaEnsamblador ofertaLoteConstruccionVentaEnsamblador;
	
	@Autowired
	private OfertaLoteConConstruccionVentaRepository ofertaLoteConConstruccionVentaRepository;
	
	@Autowired
	private MetodoValuacionEnsamblador metodoValuacionEnsamblador;
	
	@Autowired
	private MetodoValuacionRepository metodoValuacionRepository;
	
	@Override
	public OfertaLoteConConstruccionVentaDTO encontrarPorId(Long id) {
		return this.ofertaLoteConstruccionVentaEnsamblador.escribirDTO( this.ofertaLoteConConstruccionVentaRepository.findOne(id));
	}

	@Override
	public OfertaLoteConConstruccionVentaDTO crear(OfertaLoteConConstruccionVentaDTO ofertaDTO, MetodoValuacionDTO metodoValuacion) {
		OfertaLoteConConstruccionVenta ofertaLoteConConstruccionVenta = new OfertaLoteConConstruccionVenta();
		this.ofertaLoteConstruccionEnsamblador.crearOfertaLoteConstruccion(ofertaLoteConConstruccionVenta, ofertaDTO);
		
		ofertaLoteConConstruccionVenta.setValorConstruccionM2(ofertaDTO.getValorConstruccionM2());
		ofertaLoteConConstruccionVenta.setValorUnitarioM2Homogenizado(ofertaDTO.getValorUnitarioM2Homogenizado());
		
		if(metodoValuacion!=null){
			MetodoValuacion metodo  = this.metodoValuacionRepository.findOne( metodoValuacion.getId());
			if( metodo==null){
				try {
					metodo = metodoValuacionEnsamblador.crearMetodoValuacion(metodoValuacion);
				} catch (AvaluoNotFoundException e) {
					e.printStackTrace();
				}
			}
			ofertaLoteConConstruccionVenta.setMetodoValuacion(metodo);
		}
		
		this.ofertaLoteConConstruccionVentaRepository.save(ofertaLoteConConstruccionVenta);
		ofertaDTO.setId( ofertaLoteConConstruccionVenta.getId());
		
		return ofertaDTO;
	}

	@Override
	public OfertaLoteConConstruccionVentaDTO actualizar(OfertaLoteConConstruccionVentaDTO ofertaActualizada)
			throws OfertaNotFoundException {
		OfertaLoteConConstruccionVenta ofertaLoteConConstruccionVenta = this.ofertaLoteConConstruccionVentaRepository.findOne( ofertaActualizada.getId() );
		if( ofertaLoteConConstruccionVenta == null ) throw new OfertaNotFoundException();
		this.ofertaLoteConConstruccionVentaRepository.save(ofertaLoteConConstruccionVenta);
		return ofertaActualizada;
	}

	@Override
	public OfertaLoteConConstruccionVentaDTO eliminar(Long ofertaId) throws OfertaNotFoundException {
		OfertaLoteConConstruccionVenta ofertaLoteConConstruccionVenta = this.ofertaLoteConConstruccionVentaRepository.findOne(ofertaId);
		if (ofertaLoteConConstruccionVenta == null) {
			throw new  OfertaNotFoundException();
		}
		this.ofertaLoteConConstruccionVentaRepository.delete(ofertaLoteConConstruccionVenta);
		return this.ofertaLoteConstruccionVentaEnsamblador.escribirDTO(ofertaLoteConConstruccionVenta);
	}

}
