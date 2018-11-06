package com.helio4.bancol.avaluos.servicio.datos;

import java.util.List;

import com.helio4.bancol.avaluos.dto.MetodoValuacionDTO;
import com.helio4.bancol.avaluos.dto.OfertaLoteDTO;
import com.helio4.bancol.avaluos.exception.OfertaNotFoundException;

public interface OfertaLoteService {

	public OfertaLoteDTO encontrarPorId(Long id);
	
	public OfertaLoteDTO crear(OfertaLoteDTO ofertaDTO, MetodoValuacionDTO metodoValuacionDTO);
	
	public OfertaLoteDTO actualizar(OfertaLoteDTO ofertaActualizada) throws OfertaNotFoundException;
	
	public OfertaLoteDTO eliminar(Long ofertaId ) throws OfertaNotFoundException;
	
	public List<OfertaLoteDTO> crearList( List<OfertaLoteDTO> ofertasDTO, MetodoValuacionDTO metodoValuacionDTO );
}
