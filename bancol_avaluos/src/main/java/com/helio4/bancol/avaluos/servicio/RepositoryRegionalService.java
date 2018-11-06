package com.helio4.bancol.avaluos.servicio;

import java.util.ArrayList;
import java.util.List;

import com.helio4.bancol.avaluos.servicio.datos.RegionalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.RegionalDTO;
import com.helio4.bancol.avaluos.ensamblador.RegionalEnsamblador;
import com.helio4.bancol.avaluos.modelo.Regional;
import com.helio4.bancol.avaluos.persistencia.RegionalRepository;

@Component(value="repositoryRegionalService")
@Transactional(readOnly = true)
public class RepositoryRegionalService implements RegionalService {
	
	private static Logger log = LoggerFactory.getLogger( RepositoryEntidadService.class );
	
	@Autowired
	private RegionalRepository regionalRepository;
	
	@Autowired
	private RegionalEnsamblador regionalEnsamblador;

    @Transactional(readOnly = true)
	@Override
	public List<RegionalDTO> encontrarTodos() {
		log.debug("Consultado todas las regionales en la base de datos.");
		List<RegionalDTO> regionalDTOs = new ArrayList<RegionalDTO>();
		for (Regional regional : regionalRepository.findAll()) {
			regionalDTOs.add(regionalEnsamblador.escribirDTO(regional));
		}
		return regionalDTOs;
	}

    @Transactional(readOnly = true)
	@Override
	public RegionalDTO encontrarPorNombre(String value) {
		Regional regional = regionalRepository.encontrarPorNombre(value);
		return regionalEnsamblador.escribirDTO(regional);
	}

    @Transactional(readOnly = true)
    @Override
    public List<RegionalDTO> encontrarRegionales() {
        return regionalRepository.encontrarRegionales();
    }

}
