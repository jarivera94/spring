package com.helio4.bancol.avaluos.servicio.datos;

import com.helio4.bancol.avaluos.dto.ComparacionMercadoLoteConstruccionVentaDTO;
import com.helio4.bancol.avaluos.exception.ComparacionMercadoLoteConstruccionVentaNotFoundException;

public interface ComparacionMercadoLoteConstruccionVentaService {

	ComparacionMercadoLoteConstruccionVentaDTO crear(ComparacionMercadoLoteConstruccionVentaDTO comparacionMercadoLoteConstruccionVentaDTO);

	ComparacionMercadoLoteConstruccionVentaDTO actualizar(ComparacionMercadoLoteConstruccionVentaDTO comparacionActualizada) throws ComparacionMercadoLoteConstruccionVentaNotFoundException;

	ComparacionMercadoLoteConstruccionVentaDTO eliminar(Long compracionMercadoPhId) throws ComparacionMercadoLoteConstruccionVentaNotFoundException;

}
