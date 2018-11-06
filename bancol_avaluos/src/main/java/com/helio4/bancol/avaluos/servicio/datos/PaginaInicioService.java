package com.helio4.bancol.avaluos.servicio.datos;

import java.util.List;

import com.helio4.bancol.avaluos.servicio.excepciones.PaginaInicioNotFoundException;
import org.springframework.stereotype.Service;

import com.helio4.bancol.avaluos.dto.PaginaInicioDTO;

@Service
public interface PaginaInicioService {
	
	/**
	 * Crea un nuevo paginaInicio.
	 * @param paginaInicioDTO La información del nuevo paginaInicio.
	 * @return El valuo creado
	 */
	public PaginaInicioDTO crear(PaginaInicioDTO paginaInicioDTO);
	
	/**
	 * Elimina el paginaInicio
	 * @param paginaInicioId El identificador del paginaInicio que se va a eliminar.
	 * @throws PaginaInicioNotFoundException Si el paginaInicio no existe.
	 * @return El paginaInicio que se eliminó.
	 */
	public PaginaInicioDTO eliminar(Long paginaInicioId) throws PaginaInicioNotFoundException;
	
	/**
	 * Encuentra todos las paginaInicio.
	 * @return Una lista con todos las paginaInicio.
	 */
	public List<PaginaInicioDTO> encontrarTodos();
	
	/**
	 * Encuentra el paginaInicio por el identificador.
	 * @param id El identificador del paginaInicio que se quiere encontrar.
	 * @return
	 */
	public PaginaInicioDTO encontrarPorId(Long id);
	
	/**
	 * Devuelve la página de inicio con los roles a los que pertenece (Cargue perezoso)
	 * @param paginaInicioId
	 * @return
	 */
	public PaginaInicioDTO encontrarPaginaInicioConRoles(Long paginaInicioId);
	
	/**
	 * Actualiza la información de un paginaInicio.
	 * @param actualizado La información del paginaInicio actualizado
	 * @return 	El avaluuo actualizado
	 * @throws PaginaInicioNotFoundException Si no hay un paginaInicio con el id dado.
	 */
	public PaginaInicioDTO actualizar(PaginaInicioDTO actualizado) throws PaginaInicioNotFoundException;

}
