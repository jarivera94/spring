package com.helio4.bancol.avaluos.servicio.datos;

import java.util.List;

import com.helio4.bancol.avaluos.servicio.excepciones.ServidumbreNotFoundException;
import org.springframework.stereotype.Service;

import com.helio4.bancol.avaluos.dto.ServidumbreDTO;

@Service
public interface ServidumbreService {

	/**
	 * Crea un nuevo servidumbre.
	 * @param servidumbreDTO La información del nuevo servidumbre.
	 * @return El valuo creado
	 */
	public ServidumbreDTO crear(ServidumbreDTO servidumbreDTO);
	
	/**
	 * Elimina el servidumbre
	 * @param servidumbreId El identificador del servidumbre que se va a eliminar.
	 * @throws ServidumbreNotFoundException Si el servidumbre no existe.
	 * @return El servidumbre que se eliminó.
	 */
	public ServidumbreDTO eliminar(Long servidumbreId) throws ServidumbreNotFoundException;
	
	/**
	 * Encuentra todos los servidumbres.
	 * @return Una lista con todos los servidumbre.
	 */
	public List<ServidumbreDTO> encontrarTodos();
	
	/**
	 * Encuentra el servidumbre por el identificador.
	 * @param id El identificador del servidumbre que se quiere encontrar.
	 * @return
	 */
	public ServidumbreDTO encontrarPorId(Long id);
	
	/**
	 * Actualiza la información de un servidumbre.
	 * @param actualizado La información del servidumbre actualizado
	 * @return El avaluuo actualizado
	 * @throws ServidumbreNotFoundException Si no hay un servidumbre con el id dado.
	 */
	public ServidumbreDTO actualizar(ServidumbreDTO actualizado) throws ServidumbreNotFoundException;

}
