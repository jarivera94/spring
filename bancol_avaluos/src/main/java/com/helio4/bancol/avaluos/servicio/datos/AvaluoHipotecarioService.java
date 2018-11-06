package com.helio4.bancol.avaluos.servicio.datos;

import java.util.Date;
import java.util.List;

import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;
import org.springframework.stereotype.Service;

import com.helio4.bancol.avaluos.dto.AvaluoHipotecarioDTO;
import com.helio4.bancol.avaluos.dto.EntidadDTO;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;
import com.helio4.bancol.avaluos.modelo.AvaluoHipotecario;

@Service
public interface AvaluoHipotecarioService {

    /**
     * Crea un nuevo avaluo hipotecario.
     * @param avaluoHipotecarioDTO La información del nuevo avaluo.
     * @param usuarioDTO Usuario que crea el avaluo.
     * @return El valuo creado
     */
    public AvaluoHipotecarioDTO crear(AvaluoHipotecarioDTO avaluoHipotecarioDTO, UsuarioDTO usuarioDTO);

    /**
     * Elimina el avaluo
     * @param avaluoId El identificador del avaluo que se va a eliminar.
     * @throws AvaluoNotFoundException Si el avaluo no existe.
     * @return El avaluo que se eliminó.
     */
    public AvaluoHipotecarioDTO eliminar(Long avaluoId) throws AvaluoNotFoundException;

    /**
     * Encuentra todos los avaluos.
     * @return Una lista con todos los avaluos.
     */
    public List<AvaluoHipotecarioDTO> encontrarTodos();

    /**
     * Encuentra el avaluo por el identificador.
     * @param id El identificador del avaluo que se quiere encontrar.
     * @return
     */
    public AvaluoHipotecarioDTO encontrarPorId(Long id);

    /**
     * Encuentra el avaluo cargando las areas en una consulta (Lazy Initialization Workaround)
     * @param avaluoId
     * @return
     */
    AvaluoHipotecarioDTO encontrarAvaluoConAreas(Long avaluoId);

    /**
     * Actualiza la información de un avaluo.
     * @param actualizado La información del avaluo actualizado
     * @return El avaluuo actualizado
     * @throws AvaluoNotFoundException Si no hay un avaluo con el id dado.
     */
    AvaluoHipotecarioDTO actualizar(AvaluoHipotecarioDTO actualizado) throws AvaluoNotFoundException;

    /**
     * Actualiza un avaluo hipotecario.
     * @param avaluoHipotecarioDTO La información del nuevo avaluo.
     * @param usuarioDTO Usuario que crea el avaluo.
     * @param avaluoHipotecario El avaluo que se va a actualizar.
     * @return El valuo creado
     * @throws AvaluoNotFoundException 
     */
    AvaluoHipotecarioDTO actualizar(AvaluoHipotecario avaluoHipotecario, AvaluoHipotecarioDTO avaluoHipotecarioDTO) throws AvaluoNotFoundException;

    /**
     * Encuentra los avaluos en el rango de fechas que ya esten terminados
     * @param fechaInicial
     * @param fechaFinal
     * @return
     */
    List<AvaluoHipotecarioDTO> encontrarPorFechaTerminacion(Date fechaInicial, Date fechaFinal);
    /**
     * Encuentra los avaluos Hipotecarios en el rango de fechas que ya esten terminados
     * @param fechaInicial
     * @param fechaFinal
     * @return
     */
    List<AvaluoHipotecarioDTO> encontrarPorFechaTerminacionHipotecarios(EntidadDTO entidadSeleccionada, Date fechaInicial, Date fechaFinal);

}
