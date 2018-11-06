package com.helio4.bancol.avaluos.servicio.datos;

import java.util.List;
import java.util.Set;

import com.helio4.bancol.avaluos.servicio.excepciones.PropietarioNotFoundException;
import org.springframework.stereotype.Service;

import com.helio4.bancol.avaluos.dto.PropietarioDTO;

@Service
public interface PropietarioService {

	/**
	 * Crea un nuevo propietario.
	 * @param propietarioDTO La información del nuevo propietario.
	 * @return El valuo creado
	 */
	public PropietarioDTO crear(PropietarioDTO propietarioDTO);
	
	/**
	 * Elimina el propietario
	 * @param propietarioId El identificador del propietario que se va a eliminar.
	 * @throws PropietarioNotFoundException Si el propietario no existe.
	 * @return El propietario que se eliminó.
	 */
	public PropietarioDTO eliminar(Integer tipoDocumentoIdentificacion, Long numeroDocumento) throws PropietarioNotFoundException;
	
	/**
	 * Encuentra todos los propietarios.
	 * @return Una lista con todos los propietarios.
	 */
	public List<PropietarioDTO> encontrarTodos();
	
	/**
	 * Encuentra el propietario por el identificador.
	 * @param id El identificador del propietario que se quiere encontrar.
	 * @return
	 */
	public PropietarioDTO encontrarPorId(Integer tipoDocumentoIdentificacion, Long numeroDocumento);
	
	/**
	 * Encuentra los propietarios del inmueble.
	 * @param id El identificador del inmueble.
	 * @return
	 */
	public Set<PropietarioDTO> encontrarPorInmueble(Long inmuebleId);
	
	/**
	 * Actualiza la información de un propietario.
	 * @param actualizado La información del propietario actualizado
	 * @return El avaluuo actualizado
	 * @throws PropietarioNotFoundException Si no hay un propietario con el id dado.
	 */
	public PropietarioDTO actualizar(PropietarioDTO actualizado) throws PropietarioNotFoundException;

}
