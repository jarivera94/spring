package com.helio4.bancol.avaluos.servicio;

import com.helio4.bancol.avaluos.servicio.datos.ComparacionMercadoNOPHService;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.MetodoValuacionNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.ComparacionMercadoNOPHDTO;
import com.helio4.bancol.avaluos.ensamblador.ComparacionMercadoNOPHEnsamblador;
import com.helio4.bancol.avaluos.exception.ComparacionMercadoNOPHNotFoundException;
import com.helio4.bancol.avaluos.modelo.ComparacionMercadoNoPh;
import com.helio4.bancol.avaluos.persistencia.ComparacionMercadoNOPHRepository;

@Component(value="comparacionMercadoNOPHService")
@Transactional(readOnly = true)
public class RepositoryComparacionMercadoNOPHService implements ComparacionMercadoNOPHService {

	private static Logger log = LoggerFactory.getLogger(  RepositoryComparacionMercadoNOPHService.class );
	

	@Autowired
	private ComparacionMercadoNOPHRepository comparacionMercadoNOPHRepository;
	
	@Autowired
	private ComparacionMercadoNOPHEnsamblador comparacionMercadoNOPHEnsamblador;
	
	@Transactional
	@Override
	public ComparacionMercadoNOPHDTO crear( ComparacionMercadoNOPHDTO comparacionMercadoDTO ){
		ComparacionMercadoNoPh comparacion = null;
		try {
			comparacion = this.comparacionMercadoNOPHEnsamblador.crearComparacionMercadoNOPH(comparacionMercadoDTO);
		} catch (MetodoValuacionNotFoundException e) {
			log.error( "La comparación de Mercado NO PH a crear no tiene asociado un Metodo de Valuacion" );
			e.printStackTrace();
		} catch (AvaluoNotFoundException e) {
			log.error( "La comparación de Mercado NO PH a crear no tiene asociado un Avaluo" );
			e.printStackTrace();
		}
		this.comparacionMercadoNOPHRepository.save(comparacion);
		comparacionMercadoDTO.setId( comparacion.getId() );
		return comparacionMercadoDTO;
	}

	@Transactional(rollbackFor = ComparacionMercadoNOPHNotFoundException.class)
	@Override
	public ComparacionMercadoNOPHDTO actualizar(ComparacionMercadoNOPHDTO comparacionActualizada) throws ComparacionMercadoNOPHNotFoundException{
		try {
			this.comparacionMercadoNOPHEnsamblador.actualizar(comparacionActualizada);
		} catch (AvaluoNotFoundException e) {
			log.error( "Comparacion Mercado NO PH  al actualizar  no se encontro un avaluo asociado" );
			e.printStackTrace();
		}
		
		return comparacionActualizada;
	}

	@Transactional(rollbackFor = ComparacionMercadoNOPHNotFoundException.class)
	@Override
	public ComparacionMercadoNOPHDTO eliminar(Long compracionMercadoNOPhId) throws ComparacionMercadoNOPHNotFoundException{
		//System.err.println("Eliminando la Comparacion de Mercado NO PH con id: " +compracionMercadoNOPhId);
		ComparacionMercadoNoPh comparacion = this.comparacionMercadoNOPHRepository.findOne(compracionMercadoNOPhId);
		if( comparacion == null){ throw new ComparacionMercadoNOPHNotFoundException(); }
		this.comparacionMercadoNOPHRepository.delete(comparacion); 
		return this.comparacionMercadoNOPHEnsamblador.escribirDTO(comparacion);
	}
	


}
