package com.helio4.bancol.avaluos.servicio;

import java.util.List;

import com.helio4.bancol.avaluos.servicio.datos.FormatoInformeComercialService;
import com.helio4.bancol.avaluos.servicio.datos.FormatoInformeHipotecarioService;
import com.helio4.bancol.avaluos.servicio.datos.FormatoInformeService;
import com.helio4.bancol.avaluos.servicio.excepciones.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.FormatoInformeComercialDTO;
import com.helio4.bancol.avaluos.dto.FormatoInformeDTO;
import com.helio4.bancol.avaluos.dto.FormatoInformeHipotecarioDTO;
import com.helio4.bancol.avaluos.ensamblador.AvaluoEnsamblador;
import com.helio4.bancol.avaluos.ensamblador.FormatoInformeEnsamblador;
import com.helio4.bancol.avaluos.modelo.Avaluo;
import com.helio4.bancol.avaluos.modelo.FormatoInforme;
import com.helio4.bancol.avaluos.persistencia.AvaluoRepository;
import com.helio4.bancol.avaluos.persistencia.FormatoInformeRepository;

@Component(value="repositoryFormatoInformeService")
@Transactional(readOnly = true)
public class RepositoryFormatoInformeService implements FormatoInformeService {

	private static Logger log = LoggerFactory.getLogger( RepositoryFormatoInformeService.class );

    @Autowired
    private AvaluoEnsamblador avaluoEnsamblador;

    @Autowired
    private FormatoInformeEnsamblador formatoInformeEnsamblador;

    @Autowired
    private AvaluoRepository avaluoRepository;

    @Autowired
    @Qualifier("repositoryFormatoHipotecarioService")
    private FormatoInformeHipotecarioService
    formatoInformeHipotecarioService;
    
    @Autowired
    @Qualifier("repositoryFormatoComercialService")
    private FormatoInformeComercialService
    formatoInformeComercialService;
    
    @Autowired
    private FormatoInformeRepository formatoInformeRepository;

    /**
     * Crea un nuevo formato.
     * @param formatoInformeDTO La información del nuevo avaluo.
     * @return El valuo creado
     */
    @Override
    @Transactional
    public FormatoInformeDTO crear(FormatoInformeDTO formatoInformeDTO) {
        Avaluo avaluo = avaluoRepository.findOne(formatoInformeDTO.getAvaluoId());
        return crear(avaluo, formatoInformeDTO);
    }

    /**
     * Crea un nuevo avaluo.
     * @param avaluo el avaluo al que se agrega el formato.
     * @param formatoInformeDTO La información del nuevo avaluo.
     * @return El valuo creado
     */
    @Override
    @Transactional
    public FormatoInformeDTO crear(Avaluo avaluo, FormatoInformeDTO formatoInformeDTO) {
        if (formatoInformeDTO.getClass().equals(FormatoInformeHipotecarioDTO.class)) {
        	return formatoInformeHipotecarioService.crear(avaluo, (FormatoInformeHipotecarioDTO) formatoInformeDTO);
        }
        if (formatoInformeDTO.getClass().equals(FormatoInformeComercialDTO.class)) {
        	return formatoInformeComercialService.crear(avaluo, (FormatoInformeComercialDTO) formatoInformeDTO);
        }
        return null;
    }

    /**
     * Elimina el formato
     * @param formatoId El identificador del avaluo que se va a eliminar.
     * @return El avaluo que se eliminó.
     */
    public FormatoInformeDTO eliminar(Long formatoId)
        throws FormatoInformeNotFoundException {
    	log.debug("Eliminando el formato de avaluo con id: {}",
                formatoId);
		FormatoInforme formatoInforme = formatoInformeRepository.findOne(formatoId);
		if (formatoInforme == null) {
			throw new FormatoInformeNotFoundException();
		}
		formatoInformeRepository.delete(formatoInforme);
		return formatoInformeEnsamblador.escribirDTO(formatoInforme);
    }

    /**
     * Encuentra el avaluo por el identificador.
     * @param id El identificador del avaluo que se quiere encontrar.
     * @return
     */
    public FormatoInformeDTO encontrarPorId(Long id) {
    	return formatoInformeEnsamblador.escribirDTO(formatoInformeRepository.findOne(id));
    }

    /**
     * Actualiza la información de un avaluo.
     * @param actualizado La información del avaluo actualizado
     * @return El avaluuo actualizado
     * @throws AvaluoNotFoundException Si no hay un avaluo con el id dado.
     */
    @Override
    @Transactional(rollbackFor = FormatoInformeNotFoundException.class)
    public FormatoInformeDTO actualizar(FormatoInformeDTO actualizado)
        throws FormatoInformeNotFoundException {
    	try {
	    	if (actualizado.getClass().equals(FormatoInformeHipotecarioDTO.class)) {
				formatoInformeEnsamblador.actualizar(actualizado.getId(), (FormatoInformeHipotecarioDTO) actualizado);
	    	}else if (actualizado.getClass().equals(FormatoInformeComercialDTO.class)) {
	    		formatoInformeEnsamblador.actualizar(actualizado.getId(), (FormatoInformeComercialDTO) actualizado);
	    	}
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
		} catch (DivipolaNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return actualizado;
    }

    @Transactional(readOnly = true)
	@Override
	public List<FormatoInformeDTO> encontrarTodos() {
		return null;
	}

    @Transactional(readOnly = true)
	@Override
	public Long encontrarIdFormatoPorIdAvaluo(Long id) {
        return formatoInformeRepository.encontrarIdFormatoPorIdAvaluo(id);
	}

}

