package com.helio4.bancol.avaluos.servicio.datos;

import com.helio4.bancol.avaluos.dto.AreaConstruccionAvaluoDTO;
import com.helio4.bancol.avaluos.servicio.excepciones.AreaConstruccionAvaluoNotFoundException;

public interface AreaConstruccionAvaluoService {

	public AreaConstruccionAvaluoDTO encontrarPorId(Long id);

	public AreaConstruccionAvaluoDTO crear(AreaConstruccionAvaluoDTO areaConstruccionAvaluoDTO);

	public AreaConstruccionAvaluoDTO actualizar(AreaConstruccionAvaluoDTO areaConstruccionAvaluoActualizada)
			throws AreaConstruccionAvaluoNotFoundException;

	public AreaConstruccionAvaluoDTO eliminar(Long areaConstruccionAvaluoId)
			throws AreaConstruccionAvaluoNotFoundException;
}
