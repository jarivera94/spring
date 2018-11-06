package com.helio4.bancol.avaluos.servicio;

import java.util.ArrayList;
import java.util.List;

import com.helio4.bancol.avaluos.servicio.datos.OfertaService;
import com.helio4.bancol.avaluos.servicio.excepciones.MetodoValuacionNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.MetodoValuacionDTO;
import com.helio4.bancol.avaluos.dto.OfertaDTO;
import com.helio4.bancol.avaluos.ensamblador.OfertaEnsamblador;
import com.helio4.bancol.avaluos.exception.OfertaNotFoundException;
import com.helio4.bancol.avaluos.modelo.Oferta;
import com.helio4.bancol.avaluos.persistencia.OfertaRepository;

@Component(value="repositoryOfertaService")
@Transactional(readOnly = true)
public class RepositoryOfertaService implements OfertaService {
	
	private static Logger log = LoggerFactory
			.getLogger(RepositoryOfertaService.class);

	@Autowired
	private OfertaRepository ofertaRepository;

	@Autowired
	private OfertaEnsamblador ofertaEnsamblador;
	
    @Transactional(readOnly=true)
	@Override
	public OfertaDTO encontrarPorId(Long id) {
		return this.ofertaEnsamblador.escribirDTO( this.ofertaRepository.findOne(id));
	}

    @Transactional
	@Override
	public OfertaDTO crear(OfertaDTO ofertaDTO, MetodoValuacionDTO metodoValuacionDTO){
		Oferta oferta = new Oferta();
		try {
			this.ofertaEnsamblador.crearOferta(oferta,ofertaDTO, metodoValuacionDTO);
			this.ofertaRepository.save( oferta );
			ofertaDTO.setId( oferta.getId() );
		} catch (MetodoValuacionNotFoundException e) {
			log.error( "La oferta a crear no tiene asociado un Metodo de Valuacion", e );
		}
		
		return ofertaDTO;
	}

	public List<OfertaDTO> crearList( List<OfertaDTO> ofertasDTO, MetodoValuacionDTO metodoValuacionDTO ){
		List<OfertaDTO> ofertasCreadas = new ArrayList<OfertaDTO>();
		for( OfertaDTO ofertaDTO: ofertasDTO ){
			ofertasCreadas.add(  this.crear(ofertaDTO, metodoValuacionDTO) );
		}
		return ofertasCreadas;
	}

	@Transactional(rollbackFor = OfertaNotFoundException.class)
	@Override
	public OfertaDTO actualizar(OfertaDTO ofertaActualizada) throws OfertaNotFoundException{
		Oferta oferta = this.ofertaRepository.findOne( ofertaActualizada.getId() );
		if( oferta == null ) throw new OfertaNotFoundException();
		this.ofertaRepository.save(oferta);
		return ofertaActualizada;
	}

	@Transactional(rollbackFor = OfertaNotFoundException.class)
	@Override
	public OfertaDTO eliminar(Long ofertaId ) throws OfertaNotFoundException{
		//System.err.println("Eliminando la oferta con id: " +ofertaId);
		Oferta oferta = this.ofertaRepository.findOne(ofertaId);
		if (oferta == null) {
			throw new  OfertaNotFoundException();
		}
		this.ofertaRepository.delete(oferta);
		return this.ofertaEnsamblador.escribirDTO(oferta);
	}
}
