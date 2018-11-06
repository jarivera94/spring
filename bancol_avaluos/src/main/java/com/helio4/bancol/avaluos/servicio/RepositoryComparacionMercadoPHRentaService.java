package com.helio4.bancol.avaluos.servicio;

import com.helio4.bancol.avaluos.servicio.datos.ComparacionMercadoPHRentaService;
import com.helio4.bancol.avaluos.servicio.datos.ComparacionMercadoPHVentaService;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.MetodoValuacionNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.ComparacionMercadoPHRentaDTO;
import com.helio4.bancol.avaluos.dto.ComparacionMercadoPHVentaDTO;
import com.helio4.bancol.avaluos.ensamblador.ComparacionMercadoPHRentaEnsamblador;
import com.helio4.bancol.avaluos.ensamblador.ComparacionMercadoPHVentaEnsamblador;
import com.helio4.bancol.avaluos.exception.ComparacionMercadoPHRentaNotFoundException;
import com.helio4.bancol.avaluos.exception.ComparacionMercadoPHVentaNotFoundException;
import com.helio4.bancol.avaluos.modelo.ComparacionMercadoPhRenta;
import com.helio4.bancol.avaluos.modelo.ComparacionMercadoPhVenta;
import com.helio4.bancol.avaluos.persistencia.ComparacionMercadoPHRentaRepository;
import com.helio4.bancol.avaluos.persistencia.ComparacionMercadoPHVentaRepository;

@Component(value="comparacionMercadoPHRentaService")
@Transactional(readOnly = true)
public class RepositoryComparacionMercadoPHRentaService implements ComparacionMercadoPHRentaService {

	private static Logger log = LoggerFactory.getLogger(  RepositoryComparacionMercadoPHVentaService.class );
	
	@Autowired
	private ComparacionMercadoPHRentaRepository comparacionMercadoPHRentaRepository;
	
	@Autowired
	private ComparacionMercadoPHRentaEnsamblador comparacionMercadoPHRentaEnsamblador;
	
	@Transactional
	@Override
	public ComparacionMercadoPHRentaDTO crear( ComparacionMercadoPHRentaDTO comparacionMercadoDTO ){
		ComparacionMercadoPhRenta comparacion;
		try{
			comparacion = this.comparacionMercadoPHRentaEnsamblador.crearComparacionMercadoPHRenta(comparacionMercadoDTO);
			this.comparacionMercadoPHRentaRepository.save(comparacion);
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

	@Transactional(rollbackFor = ComparacionMercadoPHRentaNotFoundException.class)
	@Override
	public ComparacionMercadoPHRentaDTO actualizar(ComparacionMercadoPHRentaDTO comparacionActualizada) throws ComparacionMercadoPHRentaNotFoundException{
		try {
			this.comparacionMercadoPHRentaEnsamblador.actualizar( comparacionActualizada );
		} catch (AvaluoNotFoundException e) {
			log.error( "Comparacion Mercado PH  al actualizar  no se encontro un avaluo asociado" );
			e.printStackTrace();
		}
		return comparacionActualizada;
	}

	@Override
	public ComparacionMercadoPHRentaDTO eliminar(Long compracionMercadoPhId)
			throws ComparacionMercadoPHRentaNotFoundException {
		ComparacionMercadoPhRenta comparacion = this.comparacionMercadoPHRentaRepository.findOne(compracionMercadoPhId);
		if( comparacion == null){ throw new ComparacionMercadoPHRentaNotFoundException(); }
		this.comparacionMercadoPHRentaRepository.delete(comparacion); 
		return null;
	}

}
