package com.helio4.bancol.avaluos.servicio.datos;

import com.helio4.bancol.avaluos.dto.ComparacionMercadoPHDTO;
import com.helio4.bancol.avaluos.exception.ComparacionMercadoPHNotFoundException;

public interface ComparacionMercadoPHService {

	ComparacionMercadoPHDTO crear(ComparacionMercadoPHDTO comparacionMercadoDTO);

	ComparacionMercadoPHDTO actualizar(ComparacionMercadoPHDTO comparacionActualizada) throws ComparacionMercadoPHNotFoundException;

	ComparacionMercadoPHDTO eliminar(Long compracionMercadoPhId) throws ComparacionMercadoPHNotFoundException;

}
