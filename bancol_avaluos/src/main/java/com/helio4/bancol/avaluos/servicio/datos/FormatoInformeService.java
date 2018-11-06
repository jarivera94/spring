package com.helio4.bancol.avaluos.servicio.datos;

import java.util.List;

import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.FormatoInformeNotFoundException;
import org.springframework.stereotype.Service;

import com.helio4.bancol.avaluos.modelo.Avaluo;
import com.helio4.bancol.avaluos.dto.FormatoInformeDTO;

@Service
public interface FormatoInformeService {

    /**
     * Crea un nuevo avaluo.
     * @param formatoInformeDTO La información del nuevo avaluo.
     * @return El valuo creado
     */
    public FormatoInformeDTO crear(FormatoInformeDTO formatoInformeDTO);

    /**
     * Crea un nuevo avaluo.
     * @param avaluo el avaluo al que se agrega el formato.
     * @param formatoInformeDTO La información del nuevo avaluo.
     * @return El valuo creado
     */
    public FormatoInformeDTO crear(Avaluo avaluo, FormatoInformeDTO formatoInformeDTO);

    /**
     * Elimina el avaluo
     * @param avaluoId El identificador del avaluo que se va a eliminar.
     * @throws AvaluoNotFoundException Si el avaluo no existe.
     * @return El avaluo que se eliminó.
     */
    public FormatoInformeDTO eliminar(Long avaluoId) throws FormatoInformeNotFoundException;

    /**
     * Encuentra todos los avaluos.
     * @return Una lista con todos los avaluos.
     */
    public List<FormatoInformeDTO> encontrarTodos();

    /**
     * Encuentra el avaluo por el identificador.
     * @param id El identificador del avaluo que se quiere encontrar.
     * @return
     */
    FormatoInformeDTO encontrarPorId(Long id);
    
    Long encontrarIdFormatoPorIdAvaluo(Long id);

    /**
     * Actualiza la información de un avaluo.
     * @param actualizado La información del avaluo actualizado
     * @return El avaluuo actualizado
     * @throws AvaluoNotFoundException Si no hay un avaluo con el id dado.
     */
    FormatoInformeDTO actualizar(FormatoInformeDTO actualizado) throws FormatoInformeNotFoundException;

}

