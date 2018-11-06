package com.helio4.bancol.avaluos.servicio;

import com.helio4.bancol.avaluos.servicio.datos.ComparacionMercadoPHVentaService;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.MetodoValuacionNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.ComparacionMercadoPHVentaDTO;
import com.helio4.bancol.avaluos.ensamblador.ComparacionMercadoPHVentaEnsamblador;
import com.helio4.bancol.avaluos.exception.ComparacionMercadoPHVentaNotFoundException;
import com.helio4.bancol.avaluos.modelo.ComparacionMercadoPhVenta;
import com.helio4.bancol.avaluos.persistencia.ComparacionMercadoPHVentaRepository;

@Component(value="comparacionMercadoPHVentaService")
@Transactional(readOnly = true)
public class RepositoryComparacionMercadoPHVentaService implements ComparacionMercadoPHVentaService {

	private static Logger log = LoggerFactory.getLogger(  RepositoryComparacionMercadoPHVentaService.class );
	
	@Autowired
	private ComparacionMercadoPHVentaRepository comparacionMercadoPHVentaRepository;
	
	@Autowired
	private ComparacionMercadoPHVentaEnsamblador comparacionMercadoPHVentaEnsamblador;
	
	@Transactional
	@Override
	public ComparacionMercadoPHVentaDTO crear( ComparacionMercadoPHVentaDTO comparacionMercadoDTO ){
		ComparacionMercadoPhVenta comparacion;
		try{
			comparacion = this.comparacionMercadoPHVentaEnsamblador.crearComparacionMercadoPHVenta(comparacionMercadoDTO);
			this.comparacionMercadoPHVentaRepository.save(comparacion);
			comparacionMercadoDTO.setId( comparacion.getId() ); 
		} catch (MetodoValuacionNotFoundException e) {
			log.error( "El resultado de Comparacion Mercado PH Venta a crear no tiene asociado un Metodo de Valuacion" );
			e.printStackTrace();
		} catch (AvaluoNotFoundException e) {
			log.error( "El resultado de Comparacion Mercado PH Venta a crear no tiene asociado un Avaluo" );
			e.printStackTrace();
		}
		return comparacionMercadoDTO;
	}
	@Transactional(rollbackFor = ComparacionMercadoPHVentaNotFoundException.class)
	@Override
	public ComparacionMercadoPHVentaDTO actualizar(ComparacionMercadoPHVentaDTO comparacionActualizada) throws ComparacionMercadoPHVentaNotFoundException{
		try {
			this.comparacionMercadoPHVentaEnsamblador.actualizar( comparacionActualizada );
		} catch (AvaluoNotFoundException e) {
			log.error( "Comparacion Mercado PH  al actualizar  no se encontro un avaluo asociado" );
			e.printStackTrace();
		}
		return comparacionActualizada;
	}
	
	@Transactional(rollbackFor = ComparacionMercadoPHVentaNotFoundException.class)
	@Override
	public ComparacionMercadoPHVentaDTO eliminar(Long compracionMercadoPhId) throws ComparacionMercadoPHVentaNotFoundException{
		//System.err.println("Eliminando la Comparacion de Mercado PH con id: " +compracionMercadoPhId);
		ComparacionMercadoPhVenta comparacion = this.comparacionMercadoPHVentaRepository.findOne(compracionMercadoPhId);
		if( comparacion == null){ throw new ComparacionMercadoPHVentaNotFoundException(); }
		this.comparacionMercadoPHVentaRepository.delete(comparacion); 
		return this.comparacionMercadoPHVentaEnsamblador.escribirDTO(comparacion);
	}

}
