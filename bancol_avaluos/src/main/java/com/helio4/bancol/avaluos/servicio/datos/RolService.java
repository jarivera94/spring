package com.helio4.bancol.avaluos.servicio.datos;

import java.util.List;

import com.helio4.bancol.avaluos.servicio.excepciones.PaginaInicioNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.PermisoNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.RolNotFoundException;
import org.springframework.stereotype.Service;

import com.helio4.bancol.avaluos.dto.PermisoDTO;
import com.helio4.bancol.avaluos.dto.RolDTO;

@Service
public interface RolService {

    /**
     * Crea un nuevo rol.
     * 
     * @param rolDTO
     *            La información del nuevo rol.
     * @return El rol creado
     */
    public RolDTO crear(RolDTO rolDTO);

    /**
     * Elimina el rol
     * 
     * @param rolId
     *            El identificador del rol que se va a eliminar.
     * @throws RolNotFoundException
     *             Si el rol no existe.
     * @return El rol que se eliminó.
     */
    public RolDTO eliminar(Long rolId) throws RolNotFoundException;

    /**
     * Encuentra todos los roles.
     * 
     * @return Una lista con todos los roles.
     */
    public List<RolDTO> encontrarTodos();

    /**
     * Encuentra el rol por el identificador.
     * 
     * @param id
     *            El identificador del rol que se quiere encontrar.
     * @return
     */
    public RolDTO encontrarPorId(Long id);

    /**
     * Actualiza la información de un rol.
     * 
     * @param actualizado
     *            La información del rol actualizado
     * @return El avaluuo actualizado
     * @throws RolNotFoundException
     *             Si no hay un rol con el id dado.
     */
    public RolDTO actualizar(RolDTO actualizado) throws RolNotFoundException,
            PaginaInicioNotFoundException, PermisoNotFoundException;

    List<RolDTO> encontrarRoles();

    RolDTO encontrarPorNombre(String value);

    /**
     * Encuentra el rol sin permisos
     */
    RolDTO encontrarRolPorNombre(String value);
    
    /**
     * Busca los permisos asociados a un rol
     * */
    List<PermisoDTO> encontrarPermisosPorRol(Long idRol);

}
