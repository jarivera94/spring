package com.helio4.bancol.avaluos.servicio;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.MetodoValuacionDTO;
import com.helio4.bancol.avaluos.dto.OfertaLoteDTO;
import com.helio4.bancol.avaluos.ensamblador.OfertaLoteEnsamblador;
import com.helio4.bancol.avaluos.exception.OfertaNotFoundException;
import com.helio4.bancol.avaluos.modelo.OfertaLote;
import com.helio4.bancol.avaluos.persistencia.OfertaLoteRepository;
import com.helio4.bancol.avaluos.servicio.datos.OfertaLoteService;
import com.helio4.bancol.avaluos.servicio.excepciones.MetodoValuacionNotFoundException;

@Component(value="repositoryOfertaLoteService")
@Transactional(readOnly = true)
public class RepositoryOfertaLoteService implements OfertaLoteService {
	
	private static Logger log = LoggerFactory
			.getLogger(RepositoryOfertaLoteService.class);

	@Autowired
	private OfertaLoteRepository ofertaLoteRepository;
	
	@Autowired
	private OfertaLoteEnsamblador ofertaLoteEnsamblador;
	
    @Transactional(readOnly=true)
	@Override
	public OfertaLoteDTO encontrarPorId(Long id) {
		return this.ofertaLoteEnsamblador.escribirDTO( this.ofertaLoteRepository.findOne(id));
	}

    @Transactional
	@Override
	public OfertaLoteDTO crear(OfertaLoteDTO ofertaLoteDTO, MetodoValuacionDTO metodoValuacionDTO){
		OfertaLote  ofertaLote;
		try {
			ofertaLote = this.ofertaLoteEnsamblador.crearOfertaLote(ofertaLoteDTO, metodoValuacionDTO);
			
			this.ofertaLoteRepository.save(ofertaLote);
			ofertaLoteDTO.setId( ofertaLote.getId());
			
		} catch (MetodoValuacionNotFoundException e) {
			log.error( "La oferta a crear no tiene asociado un Metodo de Valuacion", e );
		}
		
		return ofertaLoteDTO;
	}

	public List<OfertaLoteDTO> crearList( List<OfertaLoteDTO> ofertasLoteDTO, MetodoValuacionDTO metodoValuacionDTO ){
		List<OfertaLoteDTO> ofertasCreadas = new ArrayList<>();
		for( OfertaLoteDTO ofertaLoteDTO: ofertasLoteDTO ){
			ofertasCreadas.add(  this.crear(ofertaLoteDTO, metodoValuacionDTO) );
		}
		return ofertasCreadas;
	}

	@Transactional(rollbackFor = OfertaNotFoundException.class)
	@Override
	public OfertaLoteDTO actualizar(OfertaLoteDTO ofertaActualizada) throws OfertaNotFoundException{
		OfertaLote ofertaLote = this.ofertaLoteRepository.findOne( ofertaActualizada.getId() );
		if( ofertaLote == null ) throw new OfertaNotFoundException();
		this.ofertaLoteRepository.save(ofertaLote);
		return ofertaActualizada;
	}

	@Transactional(rollbackFor = OfertaNotFoundException.class)
	@Override
	public OfertaLoteDTO eliminar(Long ofertaId ) throws OfertaNotFoundException{
		OfertaLote ofertaLote = this.ofertaLoteRepository.findOne(ofertaId);
		if (ofertaLote == null) {
			throw new  OfertaNotFoundException();
		}
		this.ofertaLoteRepository.delete(ofertaLote);
		return this.ofertaLoteEnsamblador.escribirDTO(ofertaLote);
	}
}
