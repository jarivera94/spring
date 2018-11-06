package com.helio4.bancol.avaluos.servicio.datos;

import java.util.List;

import com.helio4.bancol.avaluos.servicio.excepciones.FotografiaNotFoundException;
import org.springframework.stereotype.Service;

import com.helio4.bancol.avaluos.dto.FotografiaDTO;

@Service
public interface FotografiaService {

    /**
     * Crea un nuevo fotografia.
     * 
     * @param fotografiaDTO
     *            La información del nuevo fotografia.
     * @return El valuo creado
     */
    public FotografiaDTO crear(FotografiaDTO fotografiaDTO);
    
    /**
     * Crea un nuevo anexo.
     * 
     * @param fotografiaDTO
     *            La información del nuevo anexo.
     * @return El valuo creado
     */
    public FotografiaDTO crearAnexo(FotografiaDTO fotografiaDTO);
    
    /**
     * Crea un nuevo croquis.
     * 
     * @param fotografiaDTO
     *            La información del nuevo croquis.
     * @return El valuo creado
     */
    public FotografiaDTO crearCroquis(FotografiaDTO fotografiaDTO);

    /**
     * Elimina el fotografia
     * 
     * @param fotografiaId
     *            El identificador del fotografia que se va a eliminar.
     * @throws FotografiaNotFoundException
     *             Si el fotografia no existe.
     * @return El fotografia que se eliminó.
     */
    public FotografiaDTO eliminar(Long fotografiaId)
            throws FotografiaNotFoundException;
    
    /**
     * Elimina el anexo
     * 
     * @param anexoId
     *            El identificador del anexo que se va a eliminar.
     * @throws FotografiaNotFoundException
     *             Si el anexo no existe.
     * @return El anexo que se eliminó.
     */
    public FotografiaDTO eliminarAnexo(Long anexoId)
            throws FotografiaNotFoundException;
    
    /**
     * Elimina el croquis
     * 
     * @param croquisId
     *            El identificador del croquis que se va a eliminar.
     * @throws FotografiaNotFoundException
     *             Si el croquis no existe.
     * @return El croquis que se eliminó.
     */
    public FotografiaDTO eliminarCroquis(Long anexoId)
            throws FotografiaNotFoundException;

    /**
     * Encuentra todos los fotografias.
     * 
     * @return Una lista con todos los fotografias.
     */
    public List<FotografiaDTO> encontrarTodos();

    /**
     * Encuentra el fotografia por el identificador.
     * 
     * @param id
     *            El identificador del fotografia que se quiere encontrar.
     * @return
     */
    public FotografiaDTO encontrarPorId(Long id);

    /**
     * Retorna las fotografias del avaluo (Inicialización perezosa manual)
     * 
     * @param avaluo
     * @return
     */
    public List<FotografiaDTO> buscarFotografiasAvaluo(Long avaluoId);
    
    /**
     * Retorna los anexos del avaluo (Inicialización perezosa manual)
     * 
     * @param avaluo
     * @return
     */
    public List<FotografiaDTO> buscarAnexosAvaluo(Long avaluoId);
    
    /**
     * Retorna los croquis del avaluo (Inicialización perezosa manual)
     * 
     * @param avaluo
     * @return
     */
    public List<FotografiaDTO> buscarCroquisAvaluo(Long avaluoId);

    /**
     * Actualiza la información de un fotografia.
     * 
     * @param actualizado
     *            La información del fotografia actualizado
     * @return El avaluuo actualizado
     * @throws FotografiaNotFoundException
     *             Si no hay un fotografia con el id dado.
     */
    public FotografiaDTO actualizar(FotografiaDTO actualizado)
            throws FotografiaNotFoundException;
    
    /**
     * Actualiza la información de un anexo.
     * 
     * @param actualizado
     *            La información del anexo actualizado
     * @return El avaluo actualizado
     * @throws FotografiaNotFoundException
     *             Si no hay un fotografia con el id dado.
     */
    public FotografiaDTO actualizarAnexo(FotografiaDTO actualizado)
            throws FotografiaNotFoundException;
    
    /**
     * Actualiza la información de un croquis.
     * 
     * @param actualizado
     *            La información del croquis actualizado
     * @return El avaluo actualizado
     * @throws FotografiaNotFoundException
     *             Si no hay un croquis con el id dado.
     */
    public FotografiaDTO actualizarCroquis(FotografiaDTO actualizado)
            throws FotografiaNotFoundException;

    /**
     * Guarda la ruta de la fotografia que se pasa como parametro
     * 
     * @param fotografiaDTO
     * @return
     */
    public FotografiaDTO guardarRutaFotografia(FotografiaDTO fotografiaDTO)
            throws FotografiaNotFoundException;
    
    /**
     * Guarda la ruta del anexo que se pasa como parametro
     * 
     * @param fotografiaDTO
     * @return
     */
    public FotografiaDTO guardarRutaAnexo(FotografiaDTO fotografiaDTO)
            throws FotografiaNotFoundException;
    
    /**
     * Guarda la ruta del croquis que se pasa como parametro
     * 
     * @param fotografiaDTO
     * @return
     */
    public FotografiaDTO guardarRutaCroquis(FotografiaDTO fotografiaDTO)
            throws FotografiaNotFoundException;

}
