package com.helio4.bancol.avaluos.servicio;

import com.helio4.bancol.avaluos.servicio.datos.FormatoInformeComercialService;
import com.helio4.bancol.avaluos.servicio.excepciones.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.FormatoInformeComercialDTO;
import com.helio4.bancol.avaluos.ensamblador.FormatoInformeEnsamblador;
import com.helio4.bancol.avaluos.modelo.Avaluo;
import com.helio4.bancol.avaluos.modelo.FormatoInformeComercial;
import com.helio4.bancol.avaluos.persistencia.AvaluoRepository;
import com.helio4.bancol.avaluos.persistencia.FormatoInformeComercialRepository;

@Component(value="repositoryFormatoComercialService")
@Transactional(readOnly = true)
public class RepositoryFormatoInformeComercialService implements FormatoInformeComercialService {
    private static Logger log = LoggerFactory.getLogger( RepositoryFormatoInformeComercialService.class);

    @Autowired
    private FormatoInformeComercialRepository formatoInformeComercialRepository;

    @Autowired
    private AvaluoRepository avaluoRepository;
    
    @Autowired
    private FormatoInformeEnsamblador formatoInformeEnsamblador;

    /**
     * Crea un nuevo formato.
     * @param formatoInformeDTO La información del nuevo avaluo.
     * @return El valuo creado
     */
    @Override
    @Transactional
    public FormatoInformeComercialDTO crear(FormatoInformeComercialDTO formatoInformeDTO) {
        Avaluo avaluo = avaluoRepository.findOne(formatoInformeDTO.getAvaluoId());
        return crear(avaluo, formatoInformeDTO);
    }

    /**
     * Crea un nuevo avaluo.
     * @param avaluo al que se agrega el formato.
     * @param formatoInformeDTO La información del nuevo avaluo.
     * @return El valuo creado
     */
    @Override
    @Transactional
    public FormatoInformeComercialDTO crear(Avaluo avaluo, FormatoInformeComercialDTO formatoInformeDTO) {
        FormatoInformeComercial formatoInforme = null;
        if (formatoInformeDTO.getClass().equals(FormatoInformeComercialDTO.class)) {
            try {
                formatoInforme = formatoInformeEnsamblador.crear(avaluo, formatoInformeDTO);
            } catch (MetodoValuacionNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InmuebleNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ServidumbreNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ViaAccesoNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        formatoInforme = formatoInformeComercialRepository.save(formatoInforme);
        formatoInformeDTO.setId(formatoInforme.getId());
        return formatoInformeDTO;
    }

    /**
     * Elimina el formato
     * @param formatoId El identificador del avaluo que se va a eliminar.
     * @throws AvaluoNotFoundException Si el avaluo no existe.
     * @return El avaluo que se eliminó.
     */
    @Override
    @Transactional(rollbackFor=FormatoInformeNotFoundException.class)
    public FormatoInformeComercialDTO eliminar(Long formatoId)
        throws FormatoInformeNotFoundException {
        FormatoInformeComercial deleted = formatoInformeComercialRepository.findOne(formatoId);
        if (deleted == null) {
            throw new FormatoInformeNotFoundException();
        }
        formatoInformeComercialRepository.delete(deleted);
        return formatoInformeEnsamblador.escribirDTO(deleted);
    }

    /**
     * Encuentra el avaluo por el identificador.
     * @param id El identificador del avaluo que se quiere encontrar.
     * @return
     */
    @Override
    @Transactional(readOnly=true)
    public FormatoInformeComercialDTO encontrarPorId(Long id) {
        return formatoInformeEnsamblador.escribirDTO(formatoInformeComercialRepository.findOne(id));
    }

    /**
     * Actualiza la información de un avaluo.
     * @param actualizado La información del avaluo actualizado
     * @return El avaluuo actualizado
     * @throws AvaluoNotFoundException Si no hay un avaluo con el id dado.
     */
    @Override
    @Transactional(rollbackFor = FormatoInformeNotFoundException.class)
    public FormatoInformeComercialDTO actualizar(FormatoInformeComercialDTO actualizado)
        throws FormatoInformeNotFoundException {
        try {
            formatoInformeEnsamblador.actualizar(actualizado.getId(), actualizado);
        } catch (FormatoInformeNotFoundException e) {
            log.debug("FormatoInformeNotFoundException: no se encontró el formato {}",
                    actualizado);
            return null;
        } catch (MetodoValuacionNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InmuebleNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ServidumbreNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ViaAccesoNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return actualizado;
    }
}
