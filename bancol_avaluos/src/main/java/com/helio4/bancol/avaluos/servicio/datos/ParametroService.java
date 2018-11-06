package com.helio4.bancol.avaluos.servicio.datos;

import java.util.List;

import com.helio4.bancol.avaluos.servicio.excepciones.ParametroNotFoundException;
import org.springframework.stereotype.Service;

import com.helio4.bancol.avaluos.dto.ParametroDTO;

@Service
public interface ParametroService {

	/**
	 * Crea un nuevo parametros.
	 * @param parametrosDTO La información del nuevo parametros.
	 * @return El parametro creado
	 */
	public ParametroDTO crear(ParametroDTO parametrosDTO);
	
	/**
	 * Elimina el parametro
	 * @param parametrosId El identificador del parametro que se va a eliminar.
	 * @throws ParametroNotFoundException Si el parametro no existe.
	 * @return El parametros que se eliminó.
	 */
	public ParametroDTO eliminar(Long parametrosId) throws ParametroNotFoundException;
	
	/**
	 * Encuentra todos los parametros.
	 * @return Una lista con todos los parametros.
	 */
	public List<ParametroDTO> encontrarTodos();
	
	/**
	 * Encuentra el parametro por el identificador.
	 * @param id El identificador del parametro que se quiere encontrar.
	 * @return
	 */
	public ParametroDTO encontrarPorId(Long id);
	
	/**
	 * Encuentra el parametro con nombre
	 * @param nombre
	 * @return
	 */
	public ParametroDTO encontrarPorNombre(String nombre);
	
	/**
	 * Actualiza la información de un parametro.
	 * @param actualizado La información del parametro actualizado
	 * @return El avaluuo actualizado
	 * @throws ParametroNotFoundException Si no hay un parametro con el id dado.
	 */
	public ParametroDTO actualizar(ParametroDTO actualizado) throws ParametroNotFoundException;

}
