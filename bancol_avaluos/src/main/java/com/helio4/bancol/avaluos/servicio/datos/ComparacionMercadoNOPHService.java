package com.helio4.bancol.avaluos.servicio.datos;

import com.helio4.bancol.avaluos.dto.ComparacionMercadoNOPHDTO;
import com.helio4.bancol.avaluos.exception.ComparacionMercadoNOPHNotFoundException;

public interface ComparacionMercadoNOPHService {


	ComparacionMercadoNOPHDTO actualizar(ComparacionMercadoNOPHDTO comparacionActualizada) throws  ComparacionMercadoNOPHNotFoundException;

	ComparacionMercadoNOPHDTO eliminar(Long compracionMercadoNOPhId) throws ComparacionMercadoNOPHNotFoundException;

	ComparacionMercadoNOPHDTO crear(ComparacionMercadoNOPHDTO comparacionMercadoDTO);

	


}
