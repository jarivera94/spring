package com.helio4.bancol.avaluos.servicio.datos;

import java.util.List;

import com.helio4.bancol.avaluos.dto.GarajeDTO;
import com.helio4.bancol.avaluos.exception.GarajeNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;

public interface GarajeService {
	/**
	 * Crea un nuevo area.
	 * @param garajeDTO La información del nuevo area.
	 * @return El garaje creado
	 */
	GarajeDTO crear(GarajeDTO garajeDTO) throws AvaluoNotFoundException;
	
	/**
	 * Elimina un garaje
	 * @param garajeId El identificador del area que se va a eliminar.
	 * @throws GarajeNotFoundException Si el garaje no existe.
	 * @return El garaje que se eliminó.
	 */
	GarajeDTO eliminar(Long garajeId) throws GarajeNotFoundException;
	
	/**
	 * Encuentra todos los garajes que pertenecen a un avaluo.
	 * @return Una lista con todos los areas.
	 */
	List<GarajeDTO> encontrarTodos(Long avaluoId);
	
	/**
	 * Encuentra un garaje por el identificador.
	 * @param id El identificador del garaje que se quiere encontrar.
	 * @return
	 */
	GarajeDTO encontrarPorId(Long id);
	
	/**
	 * Actualiza la información de un garaje.
	 * @param actualizado La información del garaje actualizado
	 * @return El garaje actualizado
	 * @throws GarajeNotFoundException Si no hay un area con el id dado.
	 */
	GarajeDTO actualizar(GarajeDTO actualizado) throws GarajeNotFoundException;

	void eliminarPor(Long avaluoId);
}
