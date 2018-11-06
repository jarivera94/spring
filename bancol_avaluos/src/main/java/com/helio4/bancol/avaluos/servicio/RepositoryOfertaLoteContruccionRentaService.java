package com.helio4.bancol.avaluos.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.MetodoValuacionDTO;
import com.helio4.bancol.avaluos.dto.OfertaLoteConConstruccionRentaDTO;
import com.helio4.bancol.avaluos.ensamblador.MetodoValuacionEnsamblador;
import com.helio4.bancol.avaluos.ensamblador.OfertaLoteConstruccionEnsamblador;
import com.helio4.bancol.avaluos.ensamblador.OfertaLoteConstruccionRentaEnsamblador;
import com.helio4.bancol.avaluos.exception.OfertaNotFoundException;
import com.helio4.bancol.avaluos.modelo.MetodoValuacion;
import com.helio4.bancol.avaluos.modelo.OfertaLoteConConstruccionRenta;
import com.helio4.bancol.avaluos.persistencia.MetodoValuacionRepository;
import com.helio4.bancol.avaluos.persistencia.OfertaLoteConConstruccionRentaRepository;
import com.helio4.bancol.avaluos.servicio.datos.OfertaLoteConConstruccionRentaService;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;

@Component(value="repositoryOfertaLoteContruccionRentaService")
@Transactional(readOnly = true)
public class RepositoryOfertaLoteContruccionRentaService implements OfertaLoteConConstruccionRentaService{

	@Autowired
	private OfertaLoteConstruccionEnsamblador ofertaLoteConstruccionEnsamblador;
	
	@Autowired
	private OfertaLoteConstruccionRentaEnsamblador ofertaLoteConstruccionRentaEnsamblador;
	
	@Autowired
	private OfertaLoteConConstruccionRentaRepository ofertaLoteConConstruccionRentaRepository;
	
	@Autowired
	private MetodoValuacionEnsamblador metodoValuacionEnsamblador;
	
	@Autowired
	private MetodoValuacionRepository metodoValuacionRepository;
	
	@Override
	public OfertaLoteConConstruccionRentaDTO encontrarPorId(Long id) {
		return this.ofertaLoteConstruccionRentaEnsamblador.escribirDTO( this.ofertaLoteConConstruccionRentaRepository.findOne(id));
	}

	@Override
	public OfertaLoteConConstruccionRentaDTO crear(OfertaLoteConConstruccionRentaDTO ofertaDTO, MetodoValuacionDTO metodoValuacion) {
		OfertaLoteConConstruccionRenta ofertaLoteConConstruccionRenta = new OfertaLoteConConstruccionRenta();
		this.ofertaLoteConstruccionEnsamblador.crearOfertaLoteConstruccion(ofertaLoteConConstruccionRenta, ofertaDTO);
		
		ofertaLoteConConstruccionRenta.setValorUnitarioM2(ofertaDTO.getValorUnitarioM2());
		ofertaLoteConConstruccionRenta.setValorUnitarioResultanteM2(ofertaDTO.getValorUnitarioResultanteM2());
		
		if(metodoValuacion!=null){
			MetodoValuacion metodo  = this.metodoValuacionRepository.findOne( metodoValuacion.getId());
			if( metodo==null){
				try {
					metodo = metodoValuacionEnsamblador.crearMetodoValuacion(metodoValuacion);
				} catch (AvaluoNotFoundException e) {
					e.printStackTrace();
				}
			}
			ofertaLoteConConstruccionRenta.setMetodoValuacion(metodo);
		}
		
		this.ofertaLoteConConstruccionRentaRepository.save(ofertaLoteConConstruccionRenta);
		ofertaDTO.setId( ofertaLoteConConstruccionRenta.getId());
		
		return ofertaDTO;
	}

	@Override
	public OfertaLoteConConstruccionRentaDTO actualizar(OfertaLoteConConstruccionRentaDTO ofertaActualizada)
			throws OfertaNotFoundException {
		OfertaLoteConConstruccionRenta ofertaLoteConConstruccionRenta = this.ofertaLoteConConstruccionRentaRepository.findOne( ofertaActualizada.getId() );
		if( ofertaLoteConConstruccionRenta == null ) throw new OfertaNotFoundException();
		this.ofertaLoteConConstruccionRentaRepository.save(ofertaLoteConConstruccionRenta);
		return ofertaActualizada;
	}

	@Override
	public OfertaLoteConConstruccionRentaDTO eliminar(Long ofertaId) throws OfertaNotFoundException {
		OfertaLoteConConstruccionRenta ofertaLoteConConstruccionRenta = this.ofertaLoteConConstruccionRentaRepository.findOne(ofertaId);
		if (ofertaLoteConConstruccionRenta == null) {
			throw new  OfertaNotFoundException();
		}
		this.ofertaLoteConConstruccionRentaRepository.delete(ofertaLoteConConstruccionRenta);
		return this.ofertaLoteConstruccionRentaEnsamblador.escribirDTO(ofertaLoteConConstruccionRenta);
	}

}
