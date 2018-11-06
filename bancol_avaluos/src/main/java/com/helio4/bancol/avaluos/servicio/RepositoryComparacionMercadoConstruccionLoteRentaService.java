package com.helio4.bancol.avaluos.servicio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.ComparacionMercadoLoteConstruccionRentaDTO;
import com.helio4.bancol.avaluos.ensamblador.ComparacionMercadoLoteConstruccionRentaEnsamblador;
import com.helio4.bancol.avaluos.exception.ComparacionMercadoLoteConstruccionRentaNotFoundException;
import com.helio4.bancol.avaluos.exception.ComparacionMercadoLoteConstruccionVentaNotFoundException;
import com.helio4.bancol.avaluos.modelo.ComparacionMercadoLoteConstruccionRenta;
import com.helio4.bancol.avaluos.modelo.ComparacionMercadoLoteConstruccionVenta;
import com.helio4.bancol.avaluos.persistencia.ComparacionMercadoLoteConstruccionRentaRepository;
import com.helio4.bancol.avaluos.servicio.datos.ComparacionMercadoLoteConstruccionRentaService;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.MetodoValuacionNotFoundException;

@Component(value="comparacionMercadoLoteConstruccionRentaService")
@Transactional(readOnly = true)
public class RepositoryComparacionMercadoConstruccionLoteRentaService implements ComparacionMercadoLoteConstruccionRentaService{

	private static Logger log = LoggerFactory.getLogger(  RepositoryComparacionMercadoConstruccionLoteRentaService.class );

	
	@Autowired
	private ComparacionMercadoLoteConstruccionRentaRepository comparacionMercadoLoteConstruccionRentaRepository;
	
	@Autowired
	private ComparacionMercadoLoteConstruccionRentaEnsamblador comparacionMercadoLoteConstruccionRentaEnsamblador;

	@Transactional
	@Override
	public ComparacionMercadoLoteConstruccionRentaDTO crear(ComparacionMercadoLoteConstruccionRentaDTO comparacionMercadoDTO ){
		ComparacionMercadoLoteConstruccionRenta comparacion;
		try{
			comparacion = this.comparacionMercadoLoteConstruccionRentaEnsamblador.crearComparacionMercadoLoteConstruccionRenta(comparacionMercadoDTO);
			this.comparacionMercadoLoteConstruccionRentaRepository.save(comparacion);
			comparacionMercadoDTO.setId( comparacion.getId() ); 
		} catch (MetodoValuacionNotFoundException e) {
			log.error( "El resultado de Comparacion Mercado Lote construccion Renta a crear no tiene asociado un Metodo de Valuacion" );
			e.printStackTrace();
		} catch (AvaluoNotFoundException e) {
			log.error( "El resultado de Comparacion MercadoLote construccion Rentaa crear no tiene asociado un Avaluo" );
			e.printStackTrace();
		}
		return comparacionMercadoDTO;
	}
	
	@Transactional(rollbackFor = ComparacionMercadoLoteConstruccionRentaNotFoundException.class)
	@Override
	public ComparacionMercadoLoteConstruccionRentaDTO actualizar(ComparacionMercadoLoteConstruccionRentaDTO comparacionActualizada) throws ComparacionMercadoLoteConstruccionRentaNotFoundException{
		try {
			this.comparacionMercadoLoteConstruccionRentaEnsamblador.actualizar( comparacionActualizada );
		} catch (AvaluoNotFoundException e) {
			log.error( "Comparacion Mercado Lote construccion venta  al actualizar  no se encontro un avaluo asociado" );
			e.printStackTrace();
		}
		return comparacionActualizada;
	}

	@Override
	public ComparacionMercadoLoteConstruccionRentaDTO eliminar(Long compracionMercadoPhId)
			throws ComparacionMercadoLoteConstruccionRentaNotFoundException {
		ComparacionMercadoLoteConstruccionRenta comparacion = this.comparacionMercadoLoteConstruccionRentaRepository.findOne(compracionMercadoPhId);
		if( comparacion == null){ throw new ComparacionMercadoLoteConstruccionRentaNotFoundException(); }
		this.comparacionMercadoLoteConstruccionRentaRepository.delete(comparacion); 
		return null;
	}
	
	
}
