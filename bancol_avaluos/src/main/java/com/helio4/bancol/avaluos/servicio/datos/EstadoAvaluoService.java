package com.helio4.bancol.avaluos.servicio.datos;

import java.util.List;

import org.springframework.stereotype.Service;

import com.helio4.bancol.avaluos.dto.EstadoAvaluoDTO;
import com.helio4.bancol.avaluos.servicio.excepciones.EstadoAvaluoNotFoundException;

@Service
public interface EstadoAvaluoService {

	/**
	 * Elimina el estadoAvaluo
	 * @param estadoAvaluoId El identificador del estadoAvaluo que se va a eliminar.
	 * @throws EstadoAvaluoNotFoundException Si el estadoAvaluo no existe.
	 * @return El estadoAvaluo que se eliminó.
	 */
	EstadoAvaluoDTO eliminar(Long estadoAvaluoId) throws EstadoAvaluoNotFoundException;

	/**
	 * Encuentra todos los estadoAvaluos.
	 * @return Una lista con todos los estadoAvaluos.
	 */
	List<EstadoAvaluoDTO> encontrarTodos();

	/**
	 * Encuentra el EstadoAvaluo por el identificador.
	 * @param id El identificador del estadoAvaluo que se quiere encontrar.
	 * @return
	 */
	EstadoAvaluoDTO encontrarPorId(Long id);

	/**
	 * Encuentra los EstadoAvaluo por avaluo.
	 * @param id El identificador del avaluo que se quieren encontrar.
	 * @return
	 */
	List<EstadoAvaluoDTO> encontrarPorAvaluo(Long id);

	/**
	 * Actualiza la información de un estadoAvaluo.
	 * @param actualizado La información del estadoAvaluo actualizado
	 * @return El avaluuo actualizado
	 * @throws EstadoAvaluoNotFoundException Si no hay un estadoAvaluo con el id dado.
	 */
	EstadoAvaluoDTO actualizar(EstadoAvaluoDTO actualizado) throws EstadoAvaluoNotFoundException;

	String buscarUltimasCorreccionesSolicitadas(Long id);

	EstadoAvaluoDTO buscarPor(String codigoExterno);
	
	EstadoAvaluoDTO buscarEstadoActualPorCodigoExterno(String codigoExterno, Integer codigoEntidad);
}
