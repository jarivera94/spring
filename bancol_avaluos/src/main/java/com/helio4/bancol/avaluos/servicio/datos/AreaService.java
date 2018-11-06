package com.helio4.bancol.avaluos.servicio.datos;

import java.util.List;

import com.helio4.bancol.avaluos.servicio.excepciones.AreaNotFoundException;
import org.springframework.stereotype.Service;

import com.helio4.bancol.avaluos.dto.AreaDTO;

@Service
public interface AreaService {

    /**
     * Crea un nuevo area.
     * @param areaDTO La información del nuevo area.
     * @return El valuo creado
     */
    AreaDTO crear(AreaDTO areaDTO);

    /**
     * Elimina el area
     * @param areaId El identificador del area que se va a eliminar.
     * @throws AreaNotFoundException Si el area no existe.
     * @return El area que se eliminó.
     */
    AreaDTO eliminar(Long areaId) throws AreaNotFoundException;

    /**
     * Encuentra todos los areas.
     * @return Una lista con todos los areas.
     */
    List<AreaDTO> encontrarTodos();

    /**
     * Encuentra el area por el identificador.
     * @param id El identificador del area que se quiere encontrar.
     * @return
     */
    AreaDTO encontrarPorId(Long id);

    /**
     * Actualiza la información de un area.
     * @param actualizado La información del area actualizado
     * @return El avaluuo actualizado
     * @throws AreaNotFoundException Si no hay un area con el id dado.
     */
    AreaDTO actualizar(AreaDTO actualizado) throws AreaNotFoundException;

    void eliminarPor(Long avaluoId);
}
