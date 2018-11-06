package com.helio4.bancol.avaluos.servicio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.MetodoValuacionDTO;
import com.helio4.bancol.avaluos.dto.OfertaLoteSinConstruccionDTO;
import com.helio4.bancol.avaluos.ensamblador.MetodoValuacionEnsamblador;
import com.helio4.bancol.avaluos.ensamblador.OfertaLoteSinConstruccionEnsamblador;
import com.helio4.bancol.avaluos.exception.OfertaNotFoundException;
import com.helio4.bancol.avaluos.modelo.MetodoValuacion;
import com.helio4.bancol.avaluos.modelo.OfertaLoteSinConstruccion;
import com.helio4.bancol.avaluos.persistencia.MetodoValuacionRepository;
import com.helio4.bancol.avaluos.persistencia.OfertaLoteSinConstruccionRepository;
import com.helio4.bancol.avaluos.servicio.datos.OfertaLoteSinConstruccionService;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;

@Component(value="repositoryOfertaLoteSinContruccionService")
@Transactional(readOnly = true)
public class RepositoryOfertaLoteSinContruccionService implements OfertaLoteSinConstruccionService {

	private static Logger log = LoggerFactory
			.getLogger(RepositoryOfertaLoteSinContruccionService.class);
	
	@Autowired
	private OfertaLoteSinConstruccionRepository ofertaLoteSinConstruccionRepository;
	
	@Autowired
	private MetodoValuacionRepository metodoValuacionRepository;
	
	@Autowired
	private OfertaLoteSinConstruccionEnsamblador ofertaLoteSinConstruccionEnsamblador;
	
	@Autowired
	private MetodoValuacionEnsamblador metodoValuacionEnsamblador;
	
	
	@Override
	public OfertaLoteSinConstruccionDTO encontrarPorId(Long id) {
		return this.ofertaLoteSinConstruccionEnsamblador.escribirDTO( this.ofertaLoteSinConstruccionRepository.findOne(id));
	}

	@Override
	public OfertaLoteSinConstruccionDTO crear(OfertaLoteSinConstruccionDTO ofertaDTO, MetodoValuacionDTO metodoValuacionDTO) {
		OfertaLoteSinConstruccion ofertaLoteSinConstruccion = new OfertaLoteSinConstruccion();
		this.ofertaLoteSinConstruccionEnsamblador.crearOfertaLoteSinConstruccion(ofertaLoteSinConstruccion, ofertaDTO);
		
		if(metodoValuacionDTO!=null){
			MetodoValuacion metodo  = this.metodoValuacionRepository.findOne( metodoValuacionDTO.getId());
			if( metodo==null){
				try {
					metodo = metodoValuacionEnsamblador.crearMetodoValuacion(metodoValuacionDTO);
				} catch (AvaluoNotFoundException e) {
					e.printStackTrace();
				}
				//throw new MetodoValuacionNotFoundException();
			}
			ofertaLoteSinConstruccion.setMetodoValuacion(metodo);
		}
		
		this.ofertaLoteSinConstruccionRepository.save(ofertaLoteSinConstruccion);
		ofertaDTO.setId( ofertaLoteSinConstruccion.getId());
		
		return ofertaDTO;
	}

	@Override
	public OfertaLoteSinConstruccionDTO actualizar(OfertaLoteSinConstruccionDTO ofertaActualizada)
			throws OfertaNotFoundException {
		OfertaLoteSinConstruccion ofertaLoteSinConstruccion = this.ofertaLoteSinConstruccionRepository.findOne( ofertaActualizada.getId() );
		if( ofertaLoteSinConstruccion == null ) throw new OfertaNotFoundException();
		this.ofertaLoteSinConstruccionRepository.save(ofertaLoteSinConstruccion);
		return ofertaActualizada;
	}

	@Override
	public OfertaLoteSinConstruccionDTO eliminar(Long ofertaId) throws OfertaNotFoundException {
		OfertaLoteSinConstruccion ofertaLoteSinConstruccion = this.ofertaLoteSinConstruccionRepository.findOne(ofertaId);
		if (ofertaLoteSinConstruccion == null) {
			throw new  OfertaNotFoundException();
		}
		this.ofertaLoteSinConstruccionRepository.delete(ofertaLoteSinConstruccion);
		return this.ofertaLoteSinConstruccionEnsamblador.escribirDTO(ofertaLoteSinConstruccion);
	}

}
