package com.helio4.bancol.avaluos.servicio.datos;

import java.util.List;

import com.helio4.bancol.avaluos.servicio.excepciones.InmuebleNotFoundException;
import org.springframework.stereotype.Service;

import com.helio4.bancol.avaluos.dto.InmuebleDTO;

@Service
public interface InmuebleService {

	/**
	 * Crea un nuevo inmueble.
	 * @param inmuebleDTO La información del nuevo inmueble.
	 * @return El valuo creado
	 */
	public InmuebleDTO crear(InmuebleDTO inmuebleDTO);
	
	/**
	 * Elimina el inmueble
	 * @param inmuebleId El identificador del inmueble que se va a eliminar.
	 * @throws InmuebleNotFoundException Si el inmueble no existe.
	 * @return El inmueble que se eliminó.
	 */
	public InmuebleDTO eliminar(Long inmuebleId) throws InmuebleNotFoundException;
	
	/**
	 * Encuentra todos los inmuebles.
	 * @return Una lista con todos los inmuebles.
	 */
	public List<InmuebleDTO> encontrarTodos();
	
	/**
	 * Encuentra el inmueble por el identificador.
	 * @param id El identificador del inmueble que se quiere encontrar.
	 * @return
	 */
	public InmuebleDTO encontrarPorId(Long id);
	
	/**
	 * Actualiza la información de un inmueble.
	 * @param actualizado La información del inmueble actualizado
	 * @return El avaluuo actualizado
	 * @throws InmuebleNotFoundException Si no hay un inmueble con el id dado.
	 */
	public InmuebleDTO actualizar(InmuebleDTO actualizado) throws InmuebleNotFoundException;

}
