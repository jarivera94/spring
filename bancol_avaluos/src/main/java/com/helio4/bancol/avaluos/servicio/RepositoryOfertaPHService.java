package com.helio4.bancol.avaluos.servicio;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.MetodoValuacionDTO;
import com.helio4.bancol.avaluos.dto.OfertaDTO;
import com.helio4.bancol.avaluos.dto.OfertaPHDTO;
import com.helio4.bancol.avaluos.ensamblador.OfertaPHEnsamblador;
import com.helio4.bancol.avaluos.exception.OfertaNotFoundException;
import com.helio4.bancol.avaluos.modelo.OfertaPH;
import com.helio4.bancol.avaluos.persistencia.OfertaPHRepository;
import com.helio4.bancol.avaluos.servicio.datos.OfertaPHService;
import com.helio4.bancol.avaluos.servicio.excepciones.MetodoValuacionNotFoundException;

@Component(value="repositoryOfertaPHService")
@Transactional(readOnly = true)
public class RepositoryOfertaPHService implements OfertaPHService {
	
	private static Logger log = LoggerFactory
			.getLogger(RepositoryOfertaPHService.class);

	@Autowired
	private OfertaPHRepository ofertaPHRepository;
	
	@Autowired
	private OfertaPHEnsamblador ofertaPHEnsamblador;
	
    @Transactional(readOnly=true)
	@Override
	public OfertaPHDTO encontrarPorId(Long id) {
		return this.ofertaPHEnsamblador.escribirDTO( this.ofertaPHRepository.findOne(id));
	}
    
    @Transactional
	@Override
	public OfertaPHDTO crear(OfertaPHDTO ofertaPHDTO, MetodoValuacionDTO metodoValuacionDTO){
		OfertaPH ofertaPH;
		try {
			ofertaPH = this.ofertaPHEnsamblador.crearOfertaPH(ofertaPHDTO, metodoValuacionDTO);
			
			this.ofertaPHRepository.save( ofertaPH );
			ofertaPHDTO.setId( ofertaPH.getId());
		} catch (MetodoValuacionNotFoundException e) {
			log.error( "La oferta a crear no tiene asociado un Metodo de Valuacion", e );
		}
		
		return ofertaPHDTO;
	}

	public List<OfertaPHDTO> crearList( List<OfertaPHDTO> ofertasPHDTO, MetodoValuacionDTO metodoValuacionDTO ){
		List<OfertaPHDTO> ofertasCreadas = new ArrayList<>();
		for( OfertaPHDTO ofertaPHDTO: ofertasPHDTO ){
			ofertasCreadas.add(  this.crear(ofertaPHDTO, metodoValuacionDTO) );
		}
		return ofertasCreadas;
	}

	@Transactional(rollbackFor = OfertaNotFoundException.class)
	@Override
	public OfertaPHDTO actualizar(OfertaPHDTO ofertaActualizada) throws OfertaNotFoundException{
		OfertaPH ofertaPH = this.ofertaPHRepository.findOne( ofertaActualizada.getId() );
		if( ofertaPH == null ) throw new OfertaNotFoundException();
		this.ofertaPHRepository.save(ofertaPH);
		return ofertaActualizada;
	}

	@Transactional(rollbackFor = OfertaNotFoundException.class)
	@Override
	public OfertaPHDTO eliminar(Long ofertaId ) throws OfertaNotFoundException{
		OfertaPH ofertaPH = this.ofertaPHRepository.findOne(ofertaId);
		if (ofertaPH == null) {
			throw new  OfertaNotFoundException();
		}
		this.ofertaPHRepository.delete(ofertaPH);
		return this.ofertaPHEnsamblador.escribirDTO(ofertaPH);
	}
}
