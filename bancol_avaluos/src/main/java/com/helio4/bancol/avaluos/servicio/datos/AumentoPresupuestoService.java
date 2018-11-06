package com.helio4.bancol.avaluos.servicio.datos;

import java.util.List;

import com.helio4.bancol.avaluos.servicio.excepciones.AumentoPresupuestoNotFoundException;
import org.springframework.stereotype.Service;

import com.helio4.bancol.avaluos.dto.AumentoPresupuestoDTO;

@Service
public interface AumentoPresupuestoService {

	/**
	 * Crea un nuevo aumentoPresupuesto.
	 * @param aumentoPresupuestoDTO La información del nuevo aumentoPresupuesto.
	 * @return El valuo creado
	 */
	public AumentoPresupuestoDTO crear(AumentoPresupuestoDTO aumentoPresupuestoDTO);
	
	/**
	 * Elimina el aumentoPresupuesto
	 * @param aumentoPresupuestoId El identificador del aumentoPresupuesto que se va a eliminar.
	 * @throws AumentoPresupuestoNotFoundException Si el aumentoPresupuesto no existe.
	 * @return El aumentoPresupuesto que se eliminó.
	 */
	public AumentoPresupuestoDTO eliminar(Long aumentoPresupuestoId) throws AumentoPresupuestoNotFoundException;
	
	/**
	 * Encuentra todos los aumentoPresupuestos.
	 * @return Una lista con todos los aumentoPresupuestos.
	 */
	public List<AumentoPresupuestoDTO> encontrarTodos();
	
	/**
	 * Encuentra el aumentoPresupuesto por el identificador.
	 * @param id El identificador del aumentoPresupuesto que se quiere encontrar.
	 * @return
	 */
	public AumentoPresupuestoDTO encontrarPorId(Long id);
	
	/**
	 * Actualiza la información de un aumentoPresupuesto.
	 * @param actualizado La información del aumentoPresupuesto actualizado
	 * @return El avaluuo actualizado
	 * @throws AumentoPresupuestoNotFoundException Si no hay un aumentoPresupuesto con el id dado.
	 */
	public AumentoPresupuestoDTO actualizar(AumentoPresupuestoDTO actualizado) throws AumentoPresupuestoNotFoundException;

}
