package com.helio4.bancol.avaluos.servicio.datos;

import java.util.Date;
import java.util.List;

import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;
import org.springframework.stereotype.Service;

import com.helio4.bancol.avaluos.dto.AvaluoComercialDTO;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;
import com.helio4.bancol.avaluos.modelo.AvaluoComercial;

@Service
public interface AvaluoComercialService {

    /**
     * Crea un nuevo avaluo.
     * @param avaluoComercialDTO La información del nuevo avaluo.
     * @return El valuo creado
     */
    public AvaluoComercialDTO crear(AvaluoComercialDTO avaluoComercialDTO , UsuarioDTO usuarioDTO);

    /**
     * Elimina el avaluo
     * @param avaluoId El identificador del avaluo que se va a eliminar.
     * @throws AvaluoNotFoundException Si el avaluo no existe.
     * @return El avaluo que se eliminó.
     */
    public AvaluoComercialDTO eliminar(Long avaluoId) throws AvaluoNotFoundException;

    /**
     * Encuentra todos los avaluos.
     * @return Una lista con todos los avaluos.
     */
    public List<AvaluoComercialDTO> encontrarTodos();

    /**
     * Encuentra el avaluo por el identificador.
     * @param id El identificador del avaluo que se quiere encontrar.
     * @return
     */
    public AvaluoComercialDTO encontrarPorId(Long id);

    /**
     * Encuentra el avaluo cargando las areas en una consulta (Lazy Initialization Workaround)
     * @param avaluoId
     * @return
     */
    public AvaluoComercialDTO encontrarAvaluoConAreas(Long avaluoId);

    /**
     * Actualiza la información de un avaluo.
     * @param actualizado La información del avaluo actualizado
     * @return El avaluuo actualizado
     * @throws AvaluoNotFoundException Si no hay un avaluo con el id dado.
     */
    public AvaluoComercialDTO actualizar(AvaluoComercialDTO actualizado) throws AvaluoNotFoundException;

    /**
     * Actualiza la información de un avaluo.
     * @param avaluoComercial La información del avaluo actual
     * @param actualizado La información del avaluo actualizado
     * @return El avaluuo actualizado
     * @throws AvaluoNotFoundException Si no hay un avaluo con el id dado.
     */
    public AvaluoComercialDTO actualizar(AvaluoComercial avaluoComercial, AvaluoComercialDTO actualizado) throws AvaluoNotFoundException;

    /**
     * Encuentra los avaluos en el rango de fechas que ya esten terminados
     * @param fechaInicial
     * @param fechaFinal
     * @return
     */
    public List<AvaluoComercialDTO> encontrarPorFechaTerminacion(Date fechaInicial, Date fechaFinal);

}
