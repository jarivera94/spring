package com.helio4.bancol.avaluos.servicio.datos;

import java.util.List;

import com.helio4.bancol.avaluos.servicio.excepciones.AreaConstruccionNotFoundException;
import org.springframework.stereotype.Service;

import com.helio4.bancol.avaluos.dto.AreaConstruccionDTO;

@Service
public interface AreaConstruccionService {

	/**
	 * Crea un nuevo areaConstruccion.
	 * @param areaConstruccionDTO La información del nuevo areaConstruccion.
	 * @return El valuo creado
	 */
	public AreaConstruccionDTO crear(AreaConstruccionDTO areaConstruccionDTO);
	
	/**
	 * Elimina el areaConstruccion
	 * @param areaConstruccionId El identificador del areaConstruccion que se va a eliminar.
	 * @throws AreaConstruccionNotFoundException Si el areaConstruccion no existe.
	 * @return El areaConstruccion que se eliminó.
	 */
	public AreaConstruccionDTO eliminar(Long areaConstruccionId) throws AreaConstruccionNotFoundException;
	
	/**
	 * Encuentra todos los areaConstruccions.
	 * @return Una lista con todos los areaConstruccions.
	 */
	public List<AreaConstruccionDTO> encontrarTodos();
	
	/**
	 * Encuentra el areaConstruccion por el identificador.
	 * @param id El identificador del areaConstruccion que se quiere encontrar.
	 * @return
	 */
	public AreaConstruccionDTO encontrarPorId(Long id);
	
	/**
	 * Actualiza la información de un areaConstruccion.
	 * @param actualizado La información del areaConstruccion actualizado
	 * @return El avaluuo actualizado
	 * @throws AreaConstruccionNotFoundException Si no hay un areaConstruccion con el id dado.
	 */
	public AreaConstruccionDTO actualizar(AreaConstruccionDTO actualizado) throws AreaConstruccionNotFoundException;

}
