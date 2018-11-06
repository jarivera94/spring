package com.helio4.bancol.avaluos.servicio.datos;

import java.util.List;

import com.helio4.bancol.avaluos.servicio.excepciones.ExplotacionEconomicaNotFoundException;
import org.springframework.stereotype.Service;

import com.helio4.bancol.avaluos.dto.ExplotacionEconomicaDTO;

@Service
public interface ExplotacionEconomicaService {

	/**
	 * Crea un nuevo explotacionEconomica.
	 * @param explotacionEconomicaDTO La información del nuevo explotacionEconomica.
	 * @return El valuo creado
	 */
	public ExplotacionEconomicaDTO crear(ExplotacionEconomicaDTO explotacionEconomicaDTO);
	
	/**
	 * Elimina el explotacionEconomica
	 * @param explotacionEconomicaId El identificador del explotacionEconomica que se va a eliminar.
	 * @throws ExplotacionEconomicaNotFoundException Si el explotacionEconomica no existe.
	 * @return El explotacionEconomica que se eliminó.
	 */
	public ExplotacionEconomicaDTO eliminar(Long explotacionEconomicaId) throws ExplotacionEconomicaNotFoundException;
	
	/**
	 * Encuentra todos los explotacionEconomicas.
	 * @return Una lista con todos los explotacionEconomicas.
	 */
	public List<ExplotacionEconomicaDTO> encontrarTodos();
	
	/**
	 * Encuentra el explotacionEconomica por el identificador.
	 * @param id El identificador del explotacionEconomica que se quiere encontrar.
	 * @return
	 */
	public ExplotacionEconomicaDTO encontrarPorId(Long id);
	
	/**
	 * Actualiza la información de un explotacionEconomica.
	 * @param actualizado La información del explotacionEconomica actualizado
	 * @return El avaluuo actualizado
	 * @throws ExplotacionEconomicaNotFoundException Si no hay un explotacionEconomica con el id dado.
	 */
	public ExplotacionEconomicaDTO actualizar(ExplotacionEconomicaDTO actualizado) throws ExplotacionEconomicaNotFoundException;

}
