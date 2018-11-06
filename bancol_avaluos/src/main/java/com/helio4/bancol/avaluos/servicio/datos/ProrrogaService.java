package com.helio4.bancol.avaluos.servicio.datos;

import java.util.List;

import com.helio4.bancol.avaluos.servicio.excepciones.ProrrogaNotFoundException;
import org.springframework.stereotype.Service;

import com.helio4.bancol.avaluos.dto.ProrrogaDTO;

@Service
public interface ProrrogaService {

	/**
	 * Crea un nueva prorroga.
	 * @param prorrogaDTO La información del nueva prorroga.
	 * @return El valuo creado
	 */
	public ProrrogaDTO crear(ProrrogaDTO prorrogaDTO);
	
	/**
	 * Elimina la prorroga
	 * @param prorrogaId El identificador del prorroga que se va a eliminar.
	 * @throws ProrrogaNotFoundException Si la prorroga no existe.
	 * @return El prorroga que se eliminó.
	 */
	public ProrrogaDTO eliminar(Long prorrogaId) throws ProrrogaNotFoundException;
	
	/**
	 * Encuentra todos los prorrogas.
	 * @return Una lista con todos los prorrogas.
	 */
	public List<ProrrogaDTO> encontrarTodos();
	
	/**
	 * Encuentra la prorroga por la identificador.
	 * @param id El identificador del prorroga que se quiere encontrar.
	 * @return
	 */
	public ProrrogaDTO encontrarPorId(Long id);
	
	/**
	 * Actualiza la información de un prorroga.
	 * @param actualizado La información del prorroga actualizado
	 * @return El avaluuo actualizado
	 * @throws ProrrogaNotFoundException Si no hay un prorroga con la id dado.
	 */
	public ProrrogaDTO actualizar(ProrrogaDTO actualizado) throws ProrrogaNotFoundException;

}
