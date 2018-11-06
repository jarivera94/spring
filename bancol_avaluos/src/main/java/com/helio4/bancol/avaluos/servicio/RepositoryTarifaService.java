package com.helio4.bancol.avaluos.servicio;

import java.util.ArrayList;
import java.util.List;

import com.helio4.bancol.avaluos.servicio.datos.TarifaService;
import com.helio4.bancol.avaluos.servicio.excepciones.EntidadNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.TarifaNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.TipoAvaluoNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.TarifaDTO;
import com.helio4.bancol.avaluos.ensamblador.TarifaEnsamblador;
import com.helio4.bancol.avaluos.modelo.Tarifa;
import com.helio4.bancol.avaluos.persistencia.TarifaRepository;

@Component(value="repositoryTarifaService")
@Transactional(readOnly = true)
public class RepositoryTarifaService implements TarifaService {

	private static Logger log = LoggerFactory.getLogger( RepositoryTarifaService.class );

	@Autowired
	private TarifaRepository tarifaRepository;
	
	@Autowired
	private TarifaEnsamblador tarifaEnsamblador;
	

	@Transactional(readOnly=true)
	@Override
	public List<TarifaDTO> encontrarTodos() {
		log.debug("Encontrar todas las tarifas");
		List<TarifaDTO> tarifaDTOs = new ArrayList<TarifaDTO>();
		for (Tarifa tarifa:tarifaRepository.findAll(sortById())) {
            tarifaDTOs.add(tarifaEnsamblador.escribirDTO(tarifa));
		}
		return tarifaDTOs;
	}
	
	/**
	 * Ordenar por Id a la consulta de la lista todos.
	 * @return
	 */
	private Sort sortById() {
		return new Sort(Sort.Direction.ASC, "id");
	}

    @Transactional(readOnly = true)
    @Override
    public TarifaDTO encontrarPorId(Long id) throws TarifaNotFoundException {
        Tarifa tarifa = tarifaRepository.findOne(id);
        if (tarifa == null) {
            throw new TarifaNotFoundException();
        }
        return tarifaEnsamblador.escribirDTO(tarifaRepository.findOne(id));
    }

    @Transactional(readOnly = true)
    @Override
    public List<TarifaDTO> encontrarPorIdEntidad(Long entidadId) {
    	List<TarifaDTO> tarifas = tarifaRepository.encontrarPorIdEntidad(entidadId);
    	return tarifas;
    }

    @Transactional(readOnly = true)
    @Override
    public TarifaDTO encontrarPorIdTipoAvaluoIdEntidad(Long idTipoAvaluo,
            Long entidadId) throws TarifaNotFoundException {
        Tarifa tarifa = tarifaRepository.encontrarPorIdTipoAvaluoIdEntidad(
                idTipoAvaluo, entidadId);
        if (tarifa == null) {
        	return null;
        }
        return tarifaEnsamblador.escribirDTO(tarifa);
    }

	@Transactional(rollbackFor = TarifaNotFoundException.class)
	@Override
	public Boolean crearTarifas(List<TarifaDTO> listTarifasCrear, Long idEntidad) {
		log.debug("Crear Tarifas RepositoryTarifaService");
		List<Tarifa> listTarifas = new ArrayList<Tarifa>();
		for (TarifaDTO tarifaCrear : listTarifasCrear) {
			try {
				listTarifas.add(tarifaEnsamblador.createTarifa(tarifaCrear, idEntidad));
			} catch (EntidadNotFoundException e) {
                log.debug("EntidadNotFoundException: no se encontro Entidad relacionada a esta Tarifa:  {}",
                        tarifaCrear);
				return false;
			} catch (TipoAvaluoNotFoundException e) {
                log.debug("TipoAvaluoNotFoundException: no se encontro Tivo Avaluo relacionado a esta Tarifa:  {}",
                        tarifaCrear);
				return false;
			}
		}
		tarifaRepository.save(listTarifas);
		return true;
	}

	@Transactional(rollbackFor = TarifaNotFoundException.class)
	@Override
	public Boolean actualizarTarifas(List<TarifaDTO> listTarifaActualizar) throws TarifaNotFoundException {
		log.debug("Actualizar Tarifas RepositoryTarifaService");
		for (TarifaDTO tarifa : listTarifaActualizar) {
			try {
				if( tarifa.getId()==null){
					tarifaEnsamblador.createTarifa(tarifa, tarifa.getEntidadId());
				}
				else{
					tarifaEnsamblador.updateTarifa(tarifa);
				}
				
			} catch (EntidadNotFoundException e) {
                log.debug("EntidadNotFoundException: no se encontro Entidad relacionada a la Tarifa:  {}",
                        tarifa);
				return false;
			} catch (TipoAvaluoNotFoundException e) {
                log.debug("TipoAvaluoNotFoundException: no se encontro Tipo Avaluo relacionado a la Tarifa:  {}",
                        tarifa);
				return false;
			}
		}
		return true;
	}
}
