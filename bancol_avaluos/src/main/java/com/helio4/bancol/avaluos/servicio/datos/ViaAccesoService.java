package com.helio4.bancol.avaluos.servicio.datos;

import java.util.List;

import com.helio4.bancol.avaluos.servicio.excepciones.ViaAccesoNotFoundException;
import org.springframework.stereotype.Service;

import com.helio4.bancol.avaluos.dto.ViaAccesoDTO;

@Service
public interface ViaAccesoService {

	/**
	 * Crea una nueva viaAcceso.
	 * @param viaAccesoDTO La información del nuevo viaAcceso.
	 * @return El valuo creado
	 */
	public ViaAccesoDTO crear(ViaAccesoDTO viaAccesoDTO);
	
	/**
	 * Elimina la viaAcceso
	 * @param viaAccesoId El identificador de la viaAcceso que se va a eliminar.
	 * @throws ViaAccesoNotFoundException Si la viaAcceso no existe.
	 * @return La viaAcceso que se eliminó.
	 */
	public ViaAccesoDTO eliminar(Long viaAccesoId) throws ViaAccesoNotFoundException;
	
	/**
	 * Encuentra todas las viaAccesos.
	 * @return Una lista con todas las viaAccesos.
	 */
	public List<ViaAccesoDTO> encontrarTodos();
	
	/**
	 * Encuentra la viaAcceso por el identificador.
	 * @param id El identificador de la viaAcceso que se quiere encontrar.
	 * @return
	 */
	public ViaAccesoDTO encontrarPorId(Long id);
	
	/**
	 * Actualiza la información de una viaAcceso.
	 * @param actualizado La información de la viaAcceso actualizada
	 * @return La via acceso actualizada
	 * @throws ViaAccesoNotFoundException Si no hay una viaAcceso con el id dado.
	 */
	public ViaAccesoDTO actualizar(ViaAccesoDTO actualizado) throws ViaAccesoNotFoundException;

}
