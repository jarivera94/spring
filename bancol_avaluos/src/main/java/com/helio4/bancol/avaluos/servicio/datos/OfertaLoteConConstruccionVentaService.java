package com.helio4.bancol.avaluos.servicio.datos;

import java.util.List;

import com.helio4.bancol.avaluos.dto.MetodoValuacionDTO;
import com.helio4.bancol.avaluos.dto.OfertaLoteConConstruccionVentaDTO;
import com.helio4.bancol.avaluos.exception.OfertaNotFoundException;

public interface OfertaLoteConConstruccionVentaService {

	public OfertaLoteConConstruccionVentaDTO encontrarPorId(Long id);
	
	public OfertaLoteConConstruccionVentaDTO crear(OfertaLoteConConstruccionVentaDTO ofertaDTO, MetodoValuacionDTO metodoValuacion);
	
	public OfertaLoteConConstruccionVentaDTO actualizar(OfertaLoteConConstruccionVentaDTO ofertaActualizada) throws OfertaNotFoundException;
	
	public OfertaLoteConConstruccionVentaDTO eliminar(Long ofertaId ) throws OfertaNotFoundException;
	
}
