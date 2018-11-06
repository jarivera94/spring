package com.helio4.bancol.avaluos.servicio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.ComparacionMercadoLoteConstruccionVentaDTO;
import com.helio4.bancol.avaluos.ensamblador.ComparacionMercadoLoteConstruccionVentaEnsamblador;
import com.helio4.bancol.avaluos.exception.ComparacionMercadoLoteConstruccionVentaNotFoundException;
import com.helio4.bancol.avaluos.exception.ComparacionMercadoLoteVentaNotFoundException;
import com.helio4.bancol.avaluos.modelo.ComparacionMercadoLoteConstruccionVenta;
import com.helio4.bancol.avaluos.modelo.ComparacionMercadoLoteSinConstruccion;
import com.helio4.bancol.avaluos.persistencia.ComparacionMercadoLoteConstruccionVentaRepository;
import com.helio4.bancol.avaluos.servicio.datos.ComparacionMercadoLoteConstruccionVentaService;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.MetodoValuacionNotFoundException;

@Component(value="comparacionMercadoLoteConstruccionVentaService")
@Transactional(readOnly = true)
public class RepositoryComparacionMercadoLoteConstruccionVentaService implements ComparacionMercadoLoteConstruccionVentaService{

	private static Logger log = LoggerFactory.getLogger(  RepositoryComparacionMercadoLoteConstruccionVentaService.class );

	@Autowired
	private ComparacionMercadoLoteConstruccionVentaRepository comparacionMercadoLoteConstruccionVentaRepository;
	
	@Autowired
	private ComparacionMercadoLoteConstruccionVentaEnsamblador comparacionMercadoLoteConstruccionVentaEnsamblador;

	@Transactional
	@Override
	public ComparacionMercadoLoteConstruccionVentaDTO crear(ComparacionMercadoLoteConstruccionVentaDTO comparacionMercadoDTO ){
		ComparacionMercadoLoteConstruccionVenta comparacion;
		try{
			comparacion = this.comparacionMercadoLoteConstruccionVentaEnsamblador.crearComparacionMercadoLoteconstruccionVenta(comparacionMercadoDTO);
			this.comparacionMercadoLoteConstruccionVentaRepository.save(comparacion);
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
	
	@Transactional(rollbackFor = ComparacionMercadoLoteConstruccionVentaNotFoundException.class)
	@Override
	public ComparacionMercadoLoteConstruccionVentaDTO actualizar(ComparacionMercadoLoteConstruccionVentaDTO comparacionActualizada) throws ComparacionMercadoLoteConstruccionVentaNotFoundException{
		try {
			this.comparacionMercadoLoteConstruccionVentaEnsamblador.actualizar( comparacionActualizada );
		} catch (AvaluoNotFoundException e) {
			log.error( "Comparacion Mercado Lote construccion venta  al actualizar  no se encontro un avaluo asociado" );
			e.printStackTrace();
		}
		return comparacionActualizada;
	}


	@Override
	public ComparacionMercadoLoteConstruccionVentaDTO eliminar(Long compracionMercadoPhId)
			throws ComparacionMercadoLoteConstruccionVentaNotFoundException {
		ComparacionMercadoLoteConstruccionVenta comparacion = this.comparacionMercadoLoteConstruccionVentaRepository.findOne(compracionMercadoPhId);
		if( comparacion == null){ throw new ComparacionMercadoLoteConstruccionVentaNotFoundException(); }
		this.comparacionMercadoLoteConstruccionVentaRepository.delete(comparacion); 
		return null;
	}

	
}
