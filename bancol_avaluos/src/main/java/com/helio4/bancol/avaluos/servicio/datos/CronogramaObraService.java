package com.helio4.bancol.avaluos.servicio.datos;

import java.util.List;

import com.helio4.bancol.avaluos.servicio.excepciones.CronogramaObraNotFoundException;
import org.springframework.stereotype.Service;

import com.helio4.bancol.avaluos.dto.CronogramaObraDTO;

@Service
public interface CronogramaObraService {

	/**
	 * Crea un nuevo cronogramaObra.
	 * @param cronogramaObraDTO La información del nuevo cronogramaObra.
	 * @return El valuo creado
	 */
	public CronogramaObraDTO crear(CronogramaObraDTO cronogramaObraDTO);
	
	/**
	 * Elimina el cronogramaObra
	 * @param cronogramaObraId El identificador del cronogramaObra que se va a eliminar.
	 * @throws CronogramaObraNotFoundException Si el cronogramaObra no existe.
	 * @return El cronogramaObra que se eliminó.
	 */
	public CronogramaObraDTO eliminar(Long cronogramaObraId) throws CronogramaObraNotFoundException;
	
	/**
	 * Encuentra todos los cronogramaObras.
	 * @return Una lista con todos los cronogramaObras.
	 */
	public List<CronogramaObraDTO> encontrarTodos();
	
	/**
	 * Encuentra el cronogramaObra por el identificador.
	 * @param id El identificador del cronogramaObra que se quiere encontrar.
	 * @return List Conogramas
	 */
	public CronogramaObraDTO encontrarPorId(Long id);
	
	/**
	 * Encuentra el cronogramaObra por id de Avaluo.
	 * @param id El identificador del avaluo que se quiere encontrar el cronograma.
	 * @return Conograma
	 */
	public CronogramaObraDTO encontrarPorIdAvaluo(Long id);
	
	/**
	 * Actualiza la información de un cronogramaObra.
	 * @param actualizado La información del cronogramaObra actualizado
	 * @return El avaluuo actualizado
	 * @throws CronogramaObraNotFoundException Si no hay un cronogramaObra con el id dado.
	 */
	public CronogramaObraDTO actualizar(CronogramaObraDTO actualizado) throws CronogramaObraNotFoundException;

	/**
	 * 
	 * @param tipoCosto
	 * @return
	 */
	public List<CronogramaObraDTO> encontrarPorTipoCosto(Integer tipoCosto);
}
