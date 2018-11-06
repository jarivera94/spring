package com.helio4.bancol.avaluos.servicio.datos;

import java.util.List;

import com.helio4.bancol.avaluos.dto.MetodoValuacionDTO;
import com.helio4.bancol.avaluos.dto.OfertaPHDTO;
import com.helio4.bancol.avaluos.exception.OfertaNotFoundException;

public interface OfertaPHService {

	public OfertaPHDTO encontrarPorId(Long id);
	
	public OfertaPHDTO crear(OfertaPHDTO ofertaPHDTO, MetodoValuacionDTO metodoValuacionDTO);
	
	public OfertaPHDTO actualizar(OfertaPHDTO ofertaActualizada) throws OfertaNotFoundException;
	
	public OfertaPHDTO eliminar(Long ofertaId ) throws OfertaNotFoundException;
	
	public List<OfertaPHDTO> crearList( List<OfertaPHDTO> ofertasDTO, MetodoValuacionDTO metodoValuacionDTO );
	
		
}
