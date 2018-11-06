package com.helio4.bancol.avaluos.servicio.datos;

import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.FormatoInformeNotFoundException;
import org.springframework.stereotype.Service;

import com.helio4.bancol.avaluos.dto.FormatoInformeComercialDTO;
import com.helio4.bancol.avaluos.modelo.Avaluo;

@Service
public interface FormatoInformeComercialService {

    /**
     * Crea un nuevo avaluo.
     * @param formatoInformeDTO La información del nuevo avaluo.
     * @return El valuo creado
     */
    public FormatoInformeComercialDTO crear(FormatoInformeComercialDTO formatoInformeDTO);

    /**
     * Crea un nuevo avaluo.
     * @param avaluo al que se agrega el formato.
     * @param formatoInformeDTO La información del nuevo avaluo.
     * @return El valuo creado
     */
    public FormatoInformeComercialDTO crear(Avaluo avaluo, FormatoInformeComercialDTO formatoInformeDTO);

    /**
     * Elimina el avaluo
     * @param avaluoId El identificador del avaluo que se va a eliminar.
     * @throws AvaluoNotFoundException Si el avaluo no existe.
     * @return El avaluo que se eliminó.
     */
    public FormatoInformeComercialDTO eliminar(Long avaluoId) throws FormatoInformeNotFoundException;

    /**
     * Encuentra el avaluo por el identificador.
     * @param id El identificador del avaluo que se quiere encontrar.
     * @return
     */
    public FormatoInformeComercialDTO encontrarPorId(Long id);

    /**
     * Actualiza la información de un avaluo.
     * @param actualizado La información del avaluo actualizado
     * @return El avaluuo actualizado
     * @throws AvaluoNotFoundException Si no hay un avaluo con el id dado.
     */
    public FormatoInformeComercialDTO actualizar(FormatoInformeComercialDTO actualizado) throws FormatoInformeNotFoundException;
}
