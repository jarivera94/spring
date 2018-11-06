package com.helio4.bancol.avaluos.servicio.datos;

import java.util.List;
import com.helio4.bancol.avaluos.dto.AreaLoteAvaluoDTO;
import com.helio4.bancol.avaluos.servicio.excepciones.AreaLoteAvaluoNotFoundException;

public interface AreaLoteAvaluoService {

	public AreaLoteAvaluoDTO encontrarPorId(Long id);

	public AreaLoteAvaluoDTO crear(AreaLoteAvaluoDTO areaLoteAvaluoDTO);

	public AreaLoteAvaluoDTO actualizar(AreaLoteAvaluoDTO areaLoteAvaluoActualizada)
			throws AreaLoteAvaluoNotFoundException;

	public AreaLoteAvaluoDTO eliminar(Long areaLoteAvaluoId) throws AreaLoteAvaluoNotFoundException;

	public List<AreaLoteAvaluoDTO> crearList(List<AreaLoteAvaluoDTO> areaLoteAvaluoDTO);

}
