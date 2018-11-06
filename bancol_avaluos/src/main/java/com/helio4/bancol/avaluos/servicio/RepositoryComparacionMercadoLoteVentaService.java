package com.helio4.bancol.avaluos.servicio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.ComparacionMercadoLoteVentaDTO;
import com.helio4.bancol.avaluos.ensamblador.ComparacionMercadoLoteVentaEnsamblador;
import com.helio4.bancol.avaluos.exception.ComparacionMercadoLoteVentaNotFoundException;
import com.helio4.bancol.avaluos.modelo.ComparacionMercadoLoteSinConstruccion;
import com.helio4.bancol.avaluos.persistencia.ComparacionMercadoLoteVentaRepository;
import com.helio4.bancol.avaluos.servicio.datos.ComparacionMercadoLoteVentaService;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.MetodoValuacionNotFoundException;

@Component(value="comparacionMercadoLoteVentaService")
@Transactional(readOnly = true)
public class RepositoryComparacionMercadoLoteVentaService implements ComparacionMercadoLoteVentaService{

	private static Logger log = LoggerFactory.getLogger(  RepositoryComparacionMercadoLoteVentaService.class );

	@Autowired
	private ComparacionMercadoLoteVentaRepository comparacionMercadoLoteVentaRepository;
	
	@Autowired
	private ComparacionMercadoLoteVentaEnsamblador comparacionMercadoLoteVentaEnsamblador;
	
	@Transactional
	@Override
	public ComparacionMercadoLoteVentaDTO crear(ComparacionMercadoLoteVentaDTO comparacionMercadoDTO ){
		ComparacionMercadoLoteSinConstruccion comparacion;
		try{
			comparacion = this.comparacionMercadoLoteVentaEnsamblador.crearComparacionMercadoLoteVenta(comparacionMercadoDTO);
			this.comparacionMercadoLoteVentaRepository.save(comparacion);
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

	@Transactional(rollbackFor = ComparacionMercadoLoteVentaNotFoundException.class)
	@Override
	public ComparacionMercadoLoteVentaDTO actualizar(ComparacionMercadoLoteVentaDTO comparacionActualizada) throws ComparacionMercadoLoteVentaNotFoundException{
		try {
			this.comparacionMercadoLoteVentaEnsamblador.actualizar( comparacionActualizada );
		} catch (AvaluoNotFoundException e) {
			log.error( "Comparacion Mercado Lote venta  al actualizar  no se encontro un avaluo asociado" );
			e.printStackTrace();
		}
		return comparacionActualizada;
	}

	@Override
	public ComparacionMercadoLoteVentaDTO eliminar(Long compracionMercadoPhId)
			throws ComparacionMercadoLoteVentaNotFoundException {
		ComparacionMercadoLoteSinConstruccion comparacion = this.comparacionMercadoLoteVentaRepository.findOne(compracionMercadoPhId);
		if( comparacion == null){ throw new ComparacionMercadoLoteVentaNotFoundException(); }
		this.comparacionMercadoLoteVentaRepository.delete(comparacion); 
		return null;
	}

}
