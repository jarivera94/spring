package com.helio4.bancol.avaluos.servicio.datos;

import java.util.List;

import com.helio4.bancol.avaluos.servicio.excepciones.EntidadNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.SegmentoNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.SucursalNotFoundException;
import org.springframework.stereotype.Service;

import com.helio4.bancol.avaluos.dto.SegmentoDTO;

@Service
public interface SegmentoService {

	/**
	 * Encuentra todos los sucursales.
	 * @return Una lista con todos los sucursales.
	 */
	public List<SegmentoDTO> encontrarTodos();

    /**
     * Encuentra los segmentos de la entidad
     * @return Una lista con los segmentos de la entidad
     */
	public List<SegmentoDTO> encontrarPorEntidad(Long idEntidad);

	/**
	 * Encuentra el sucursal por el identificador.
	 * @param id El identificador del sucursal que se quiere encontrar.
	 * @return Segmento
	 */
	public SegmentoDTO encontrarPorId(Long id);

	/**
	 * Encuentra las sucursales que contienen el codigo
	 * @param nombre Segmento a buscar
	 * @return Lista de los segmentos
	 */
	public List<SegmentoDTO> encontrarPorNombre(String nombre);

	/**
	 * CRear Segmento
	 * @param segmentoDTO Objeto
	 * @return Confirmaci�n de la transacci�n
	 * @throws SegmentoNotFoundException
	 */
	public Boolean crearSegmento(SegmentoDTO segmentoDTO) throws EntidadNotFoundException;

	/**
	 * Actualizar la sucursal por codigo
	 * @param codigo de la Sucursal
	 * @return Boolean de confirmación
	 * @throws SucursalNotFoundException
	 */
	public Boolean actualizarTodo(SegmentoDTO segmentoDTO) throws SegmentoNotFoundException;

	/**
	 * Eliminar la Segmento
	 * @param SegmentoDTO Objeto
	 * @return Confirmaci�n de la transacci�n
	 * @throws SegmentoNotFoundException
	 */
	public SegmentoDTO eliminarSegmento(SegmentoDTO segmentoDTO) throws SegmentoNotFoundException;
}
