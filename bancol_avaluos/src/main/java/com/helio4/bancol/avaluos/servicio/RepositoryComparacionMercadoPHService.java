package com.helio4.bancol.avaluos.servicio;

import com.helio4.bancol.avaluos.servicio.datos.ComparacionMercadoPHService;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.MetodoValuacionNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.ComparacionMercadoPHDTO;
import com.helio4.bancol.avaluos.ensamblador.ComparacionMercadoPHEnsamblador;
import com.helio4.bancol.avaluos.exception.ComparacionMercadoPHNotFoundException;
import com.helio4.bancol.avaluos.modelo.ComparacionMercadoPh;
import com.helio4.bancol.avaluos.persistencia.ComparacionMercadoPHRepository;

@Component(value="comparacionMercadoPHService")
@Transactional(readOnly = true)
public class RepositoryComparacionMercadoPHService implements ComparacionMercadoPHService {

	private static Logger log = LoggerFactory.getLogger(  RepositoryComparacionMercadoPHService.class );
	
	@Autowired
	private ComparacionMercadoPHRepository comparacionMercadoPHRepository;
	
	@Autowired
	private ComparacionMercadoPHEnsamblador comparacionMercadoPHEnsamblador;
	
	@Transactional
	@Override
	public ComparacionMercadoPHDTO crear( ComparacionMercadoPHDTO comparacionMercadoDTO ){
		ComparacionMercadoPh comparacion;
		try{
			comparacion = this.comparacionMercadoPHEnsamblador.crearComparacionMercadoPH(comparacionMercadoDTO);
			this.comparacionMercadoPHRepository.save(comparacion);
			comparacionMercadoDTO.setId( comparacion.getId() ); 
		} catch (MetodoValuacionNotFoundException e) {
			log.error( "El resultado de Comparacion Mercado PH  a crear no tiene asociado un Metodo de Valuacion" );
			e.printStackTrace();
		} catch (AvaluoNotFoundException e) {
			log.error( "El resultado de Comparacion Mercado PH  a crear no tiene asociado un Avaluo" );
			e.printStackTrace();
		}
		return comparacionMercadoDTO;
	}
	@Transactional(rollbackFor = ComparacionMercadoPHNotFoundException.class)
	@Override
	public ComparacionMercadoPHDTO actualizar(ComparacionMercadoPHDTO comparacionActualizada) throws ComparacionMercadoPHNotFoundException{
		try {
			this.comparacionMercadoPHEnsamblador.actualizar( comparacionActualizada );
		} catch (AvaluoNotFoundException e) {
			log.error( "Comparacion Mercado PH  al actualizar  no se encontro un avaluo asociado" );
			e.printStackTrace();
		}
		return comparacionActualizada;
	}
	
	@Transactional(rollbackFor = ComparacionMercadoPHNotFoundException.class)
	@Override
	public ComparacionMercadoPHDTO eliminar(Long compracionMercadoPhId) throws ComparacionMercadoPHNotFoundException{
		//System.err.println("Eliminando la Comparacion de Mercado PH con id: " +compracionMercadoPhId);
		ComparacionMercadoPh comparacion = this.comparacionMercadoPHRepository.findOne(compracionMercadoPhId);
		if( comparacion == null){ throw new ComparacionMercadoPHNotFoundException(); }
		this.comparacionMercadoPHRepository.delete(comparacion); 
		return this.comparacionMercadoPHEnsamblador.escribirDTO(comparacion);
	}

}
