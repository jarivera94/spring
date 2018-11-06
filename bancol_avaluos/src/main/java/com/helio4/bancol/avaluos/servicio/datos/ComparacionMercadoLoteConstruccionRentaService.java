package com.helio4.bancol.avaluos.servicio.datos;

import com.helio4.bancol.avaluos.dto.ComparacionMercadoLoteConstruccionRentaDTO;
import com.helio4.bancol.avaluos.exception.ComparacionMercadoLoteConstruccionRentaNotFoundException;

public interface ComparacionMercadoLoteConstruccionRentaService {

	ComparacionMercadoLoteConstruccionRentaDTO crear(ComparacionMercadoLoteConstruccionRentaDTO comparacionMercadoLoteConstruccionRentaDTO);

	ComparacionMercadoLoteConstruccionRentaDTO actualizar(ComparacionMercadoLoteConstruccionRentaDTO comparacionActualizada) throws ComparacionMercadoLoteConstruccionRentaNotFoundException;

	ComparacionMercadoLoteConstruccionRentaDTO eliminar(Long compracionMercadoPhId) throws ComparacionMercadoLoteConstruccionRentaNotFoundException;

}