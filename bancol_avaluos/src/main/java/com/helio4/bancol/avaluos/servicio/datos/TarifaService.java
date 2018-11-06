package com.helio4.bancol.avaluos.servicio.datos;

import java.util.List;

import com.helio4.bancol.avaluos.servicio.excepciones.TarifaNotFoundException;
import org.springframework.stereotype.Service;

import com.helio4.bancol.avaluos.dto.TarifaDTO;

@Service
public interface TarifaService {
	
	/**
	 * Encuentra todos las tarifas.
	 * @return Una lista con todos los permisos.
	 */
	public List<TarifaDTO> encontrarTodos();

	/**
	 * Encuentra la tarifa por el identificador.
	 * @param id El identificador de la tarifa que se quiere encontrar.
	 * @return
	 * @throws TarifaNotFoundException
	 */
	public TarifaDTO encontrarPorId(Long id) throws TarifaNotFoundException;
	
	/**
	 * 
	 * @param entidadId
	 * @return
	 * @throws TarifaNotFoundException 
	 */
	public List<TarifaDTO> encontrarPorIdEntidad(Long entidadId) ;
	
	/**
	 * Crear Tarifas
	 * @param listTarifaDTOs
	 * @param idEntidad
	 * @return Confirmaci�n de la transacci�n
	 * @throws TarifaNotFoundException
	 */
	public Boolean crearTarifas(List<TarifaDTO> listTarifaDTOs, Long idEntidad) throws TarifaNotFoundException;
	
	/**
	 * 
	 * @param listTarifaDTOs
	 * @return
	 * @throws TarifaNotFoundException
	 */
	public Boolean actualizarTarifas(List<TarifaDTO> listTarifaDTOs) throws TarifaNotFoundException;

	/**
	 * 
	 * @param idTipoAvaluo
	 * @param entidadId
	 * @return
	 */
	public TarifaDTO encontrarPorIdTipoAvaluoIdEntidad(Long idTipoAvaluo, Long entidadId) throws TarifaNotFoundException;

}
