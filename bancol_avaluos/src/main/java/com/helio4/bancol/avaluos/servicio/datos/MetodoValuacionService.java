package com.helio4.bancol.avaluos.servicio.datos;

import com.helio4.bancol.avaluos.dto.MetodoValuacionDTO;
import com.helio4.bancol.avaluos.servicio.excepciones.MetodoValuacionNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MetodoValuacionService {

    /**
     * Crea un nuevo metodoValuacion.
     * @param metodoValuacionDTO La información del nuevo metodoValuacion.
     * @return El valuo creado
     */
    MetodoValuacionDTO crear(MetodoValuacionDTO metodoValuacionDTO);

    /**
     * Elimina el metodoValuacion
     * @param metodoValuacionDTO
     * @return
     * @throws MetodoValuacionNotFoundException
     */
    MetodoValuacionDTO eliminar(MetodoValuacionDTO metodoValuacionDTO) throws MetodoValuacionNotFoundException;

    /**
     * Encuentra todos los metodoValuacions.
     * @return Una lista con todos los metodoValuacion.
     */
    List<MetodoValuacionDTO> encontrarTodos();

    /**
     * Encuentra el metodoValuacion por el identificador.
     * @param id El identificador del metodoValuacion que se quiere encontrar.
     * @return
     */
    MetodoValuacionDTO encontrarPorId(Long id);

    /**
     * Actualiza la información de un metodoValuacion.
     * @param actualizado La información del metodoValuacion actualizado
     * @return El avaluuo actualizado
     * @throws MetodoValuacionNotFoundException Si no hay un metodoValuacion con el id dado.
     */
    MetodoValuacionDTO actualizar(MetodoValuacionDTO actualizado) throws MetodoValuacionNotFoundException;

    /**
     * Busca todos los métodos de valuación de un avalúo.
     * @param id
     * @return
     */
    List<MetodoValuacionDTO> encontrarPorAvaluoId(Long id);

    /**
     * Elimina todos los métodos de valuación por el identificador del avaluo
     * @param avaluoId
     */
    void eliminarPor(Long avaluoId);
}
