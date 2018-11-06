package com.helio4.bancol.avaluos.servicio;

import com.helio4.bancol.avaluos.servicio.datos.FormatoInformeHipotecarioService;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.DivipolaNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.FormatoInformeNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.MetodoValuacionNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.FormatoInformeHipotecarioDTO;
import com.helio4.bancol.avaluos.ensamblador.FormatoInformeEnsamblador;
import com.helio4.bancol.avaluos.modelo.Avaluo;
import com.helio4.bancol.avaluos.modelo.FormatoInformeHipotecario;
import com.helio4.bancol.avaluos.persistencia.AvaluoRepository;
import com.helio4.bancol.avaluos.persistencia.FormatoInformeHipotecarioRepository;

@Component(value="repositoryFormatoHipotecarioService")
@Transactional(readOnly = true)
public class RepositoryFormatoInformeHipotecarioService implements FormatoInformeHipotecarioService {

	private static Logger log = LoggerFactory.getLogger( RepositoryFormatoInformeHipotecarioService.class );

	@Autowired
	private FormatoInformeHipotecarioRepository formatoInformeHipotecarioRepository;

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
    public FormatoInformeHipotecarioDTO crear(FormatoInformeHipotecarioDTO formatoInformeDTO) {
        Avaluo avaluo = avaluoRepository.findOne(formatoInformeDTO.getAvaluoId());
       	return crear(avaluo, formatoInformeDTO);
    }

    /**
     * Crea un nuevo avaluo.
     * @param avaluo Avaluo al que se agrega el formato.
     * @param formatoInformeDTO La información del nuevo avaluo.
     * @return El valuo creado
     */
    @Override
    @Transactional
    public FormatoInformeHipotecarioDTO crear(Avaluo avaluo, FormatoInformeHipotecarioDTO formatoInformeDTO) {
        FormatoInformeHipotecario formatoInforme = null;
        if (formatoInformeDTO.getClass().equals(FormatoInformeHipotecarioDTO.class)) {
        	try {
        		formatoInforme = formatoInformeEnsamblador.crear(avaluo, formatoInformeDTO);
			} catch (DivipolaNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        formatoInforme = formatoInformeHipotecarioRepository.save(formatoInforme);
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
    public FormatoInformeHipotecarioDTO eliminar(Long formatoId)
        throws FormatoInformeNotFoundException {
    	FormatoInformeHipotecario deleted = formatoInformeHipotecarioRepository.findOne(formatoId);
    	if (deleted == null) {
    		throw new FormatoInformeNotFoundException();
    	}
    	formatoInformeHipotecarioRepository.delete(deleted);
    	return formatoInformeEnsamblador.escribirDTO(deleted);
    }

    /**
     * Encuentra el avaluo por el identificador.
     * @param id El identificador del avaluo que se quiere encontrar.
     * @return
     */
    @Override
    @Transactional(readOnly=true)
    public FormatoInformeHipotecarioDTO encontrarPorId(Long id) {
    	return formatoInformeEnsamblador.escribirDTO(formatoInformeHipotecarioRepository.findOne(id));
    }

    /**
     * Actualiza la información de un avaluo.
     * @param actualizado La información del avaluo actualizado
     * @return El avaluuo actualizado
     * @throws AvaluoNotFoundException Si no hay un avaluo con el id dado.
     */
    @Override
    @Transactional(rollbackFor = FormatoInformeNotFoundException.class)
    public FormatoInformeHipotecarioDTO actualizar(FormatoInformeHipotecarioDTO actualizado)
        throws FormatoInformeNotFoundException {
    	try {
			formatoInformeEnsamblador.actualizar(actualizado.getId(), actualizado);
		} catch (FormatoInformeNotFoundException e) {
            log.debug("FormatoInformeNotFoundException: no se encontró el formato {}",
                    actualizado);
			return null;
		} catch (DivipolaNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return actualizado;
    }

}

