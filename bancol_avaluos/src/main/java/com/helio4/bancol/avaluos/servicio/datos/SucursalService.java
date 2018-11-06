package com.helio4.bancol.avaluos.servicio.datos;

import java.util.List;

import com.helio4.bancol.avaluos.servicio.excepciones.EntidadNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.SucursalNotFoundException;
import org.springframework.stereotype.Service;

import com.helio4.bancol.avaluos.dto.SucursalDTO;

@Service
public interface SucursalService {
	
	/**
	 * Encuentra todos los sucursales.
	 * @return Una lista con todos los sucursales.
	 */
	public List<SucursalDTO> encontrarTodos();
	
	/**
	 * Encuentra el sucursal por el identificador.
	 * @param id El identificador del sucursal que se quiere encontrar.
	 * @return
	 */
	public SucursalDTO encontrarPorId(Long id);

	/**
	 * Encuentra las sucursales que contienen el codigo
	 * @return Una lista con todos los sucursals.
	 */
	public List<SucursalDTO> encontrarPorCodigo(String texto, Long idEntidad);

	/**
	 * Encuentra las sucursales que contienen el codigo
	 * @return Una lista con todos los sucursals.
	 */
	public List<SucursalDTO> encontrarPorNombre(String nombre, Long idEntidad);

	/**
	 * Actualizar la sucursal por codigo
	 * @param codigo de la Sucursal
	 * @return Boolean de confirmación
	 * @throws SucursalNotFoundException
	 */
	public Boolean actualizarTodo(SucursalDTO sucursalDTO) throws SucursalNotFoundException, EntidadNotFoundException;
	
	/**
	 * Crear Sucursal
	 * @param sucursalDTO Objeto
	 * @return Confirmaci�n de la transacci�n
	 * @throws SucursalNotFoundException
	 */
	public Boolean crearSucursal(SucursalDTO sucursalDTO) throws SucursalNotFoundException;
	
	/**
	 * Eliminar la Sucursal
	 * @param sucursalDTO Objeto
	 * @return Confirmaci�n de la transacci�n
	 * @throws SucursalNotFoundException
	 */
	public SucursalDTO eliminarSucursal(SucursalDTO sucursalDTO) throws SucursalNotFoundException;
	
	public SucursalDTO encontrarSucursal(Long entidadId, String codigo);
}
