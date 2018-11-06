package com.helio4.bancol.avaluos.servicio.datos;

import java.util.List;

import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;
import org.springframework.stereotype.Service;

import com.helio4.bancol.avaluos.dto.AvaluoRemateDTO;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;
import com.helio4.bancol.avaluos.modelo.AvaluoRemate;

@Service
public interface AvaluoRemateService {

    /**
     * Crea un nuevo avaluo.
     * @param avaluoRemateDTO La información del nuevo avaluo.
     * @return El valuo creado
     */
    public AvaluoRemateDTO crear(AvaluoRemateDTO avaluoRemateDTO, UsuarioDTO usuarioDTO);

    /**
     * Elimina el avaluo
     * @param avaluoId El identificador del avaluo que se va a eliminar.
     * @throws AvaluoNotFoundException Si el avaluo no existe.
     * @return El avaluo que se eliminó.
     */
    public AvaluoRemateDTO eliminar(Long avaluoId) throws AvaluoNotFoundException;

    /**
     * Encuentra todos los avaluos.
     * @return Una lista con todos los avaluos.
     */
    public List<AvaluoRemateDTO> encontrarTodos();

    /**
     * Encuentra el avaluo por el identificador.
     * @param id El identificador del avaluo que se quiere encontrar.
     * @return
     */
    public AvaluoRemateDTO encontrarPorId(Long id);

    /**
     * Actualiza la información de un avaluo.
     * @param actualizado La información del avaluo actualizado
     * @return El avaluuo actualizado
     * @throws AvaluoNotFoundException Si no hay un avaluo con el id dado.
     */
    AvaluoRemateDTO actualizar(AvaluoRemateDTO actualizado) throws AvaluoNotFoundException;

    /**
     * Actualiza un avaluo hipotecario.
     * @param avaluoRemateDTO La información del nuevo avaluo.
     * @param usuarioDTO Usuario que crea el avaluo.
     * @param avaluoRemate El avaluo que se va a actualizar.
     * @return El valuo creado
     * @throws AvaluoNotFoundException
     */
    AvaluoRemateDTO actualizar(AvaluoRemate avaluoRemate, AvaluoRemateDTO avaluoRemateDTO) throws AvaluoNotFoundException;

}
