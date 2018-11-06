package com.helio4.bancol.avaluos.servicio.datos;

import java.util.List;

import com.helio4.bancol.avaluos.dto.MetodoValuacionDTO;
import com.helio4.bancol.avaluos.dto.OfertaLoteSinConstruccionDTO;
import com.helio4.bancol.avaluos.exception.OfertaNotFoundException;

public interface OfertaLoteSinConstruccionService {
	
	public OfertaLoteSinConstruccionDTO encontrarPorId(Long id);
	
	public OfertaLoteSinConstruccionDTO crear(OfertaLoteSinConstruccionDTO ofertaDTO, MetodoValuacionDTO metodoValuacionDTO);
	
	public OfertaLoteSinConstruccionDTO actualizar(OfertaLoteSinConstruccionDTO ofertaActualizada) throws OfertaNotFoundException;
	
	public OfertaLoteSinConstruccionDTO eliminar(Long ofertaId ) throws OfertaNotFoundException;
	

}
