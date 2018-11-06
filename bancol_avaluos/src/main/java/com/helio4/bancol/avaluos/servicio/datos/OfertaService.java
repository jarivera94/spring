package com.helio4.bancol.avaluos.servicio.datos;

import java.util.List;

import com.helio4.bancol.avaluos.dto.MetodoValuacionDTO;
import com.helio4.bancol.avaluos.dto.OfertaDTO;
import com.helio4.bancol.avaluos.exception.OfertaNotFoundException;

public interface OfertaService {

	public OfertaDTO encontrarPorId(Long id);
	
	public OfertaDTO crear(OfertaDTO ofertaDTO, MetodoValuacionDTO metodoValuacionDTO);
	
	public OfertaDTO actualizar(OfertaDTO ofertaActualizada) throws OfertaNotFoundException;
	
	public OfertaDTO eliminar(Long ofertaId ) throws OfertaNotFoundException;
	
	public List<OfertaDTO> crearList( List<OfertaDTO> ofertasDTO, MetodoValuacionDTO metodoValuacionDTO );
	
		
}
