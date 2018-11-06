package com.helio4.bancol.avaluos.servicio.datos;

import java.util.List;

import com.helio4.bancol.avaluos.dto.MetodoValuacionDTO;
import com.helio4.bancol.avaluos.dto.OfertaLoteConConstruccionRentaDTO;
import com.helio4.bancol.avaluos.exception.OfertaNotFoundException;

public interface OfertaLoteConConstruccionRentaService {

	public OfertaLoteConConstruccionRentaDTO encontrarPorId(Long id);
	
	public OfertaLoteConConstruccionRentaDTO crear(OfertaLoteConConstruccionRentaDTO ofertaDTO, MetodoValuacionDTO metodoValuacion);
	
	public OfertaLoteConConstruccionRentaDTO actualizar(OfertaLoteConConstruccionRentaDTO ofertaActualizada) throws OfertaNotFoundException;
	
	public OfertaLoteConConstruccionRentaDTO eliminar(Long ofertaId ) throws OfertaNotFoundException;
	
}
