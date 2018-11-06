package com.helio4.bancol.avaluos.servicio.datos;

import java.util.List;

import com.helio4.bancol.avaluos.servicio.excepciones.PermisoNotFoundException;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.helio4.bancol.avaluos.dto.PermisoDTO;

@Service
public interface PermisoService {
	
	/**
	 * Crea un nuevo permiso.
	 * @param permisoDTO La información del nuevo permiso.
	 * @return El valuo creado
	 */
	public PermisoDTO crear(PermisoDTO permisoDTO);
	
	/**
	 * Elimina el permiso
	 * @param permisoId El identificador del permiso que se va a eliminar.
	 * @throws PermisoNotFoundException Si el permiso no existe.
	 * @return El permiso que se eliminó.
	 */
	public PermisoDTO eliminar(Long permisoId) throws PermisoNotFoundException;
	
	/**
	 * Encuentra todos los permisos.
	 * @return Una lista con todos los permisos.
	 */
	public List<PermisoDTO> encontrarTodos();
	
	/**
	 * Encuentra el permiso por el identificador.
	 * @param id El identificador del permiso que se quiere encontrar.
	 * @return
	 */
	public PermisoDTO encontrarPorId(Long id);
	
	/**
	 * Retorna el Permiso con los roles a los que esta asociado (Cargue Perzoso)
	 * @param permisoId
	 * @return
	 */
	public PermisoDTO encontrarPermisoConRoles(@Param("permisoId") Long permisoId);
	
	/**
	 * Actualiza la información de un permiso.
	 * @param actualizado La información del permiso actualizado
	 * @return El avaluuo actualizado
	 * @throws PermisoNotFoundException Si no hay un permiso con el id dado.
	 */
	public PermisoDTO actualizar(PermisoDTO actualizado) throws PermisoNotFoundException;
	
	PermisoDTO encontrarPor(String nombre);
    List<PermisoDTO> encontrarPorRol(String nombreRol);

}
