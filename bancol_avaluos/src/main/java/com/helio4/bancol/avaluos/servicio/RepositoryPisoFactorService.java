package com.helio4.bancol.avaluos.servicio;

import java.util.ArrayList;
import java.util.List;

import com.helio4.bancol.avaluos.servicio.datos.PisoFactorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.PisoFactorDTO;
import com.helio4.bancol.avaluos.ensamblador.PisoFactorEnsamblador;
import com.helio4.bancol.avaluos.modelo.PisoFactor;
import com.helio4.bancol.avaluos.persistencia.PisoFactorRepository;;

@Component(value = "repositoryPisoFactorService")
@Transactional(readOnly = true)
public class RepositoryPisoFactorService implements PisoFactorService {

	private static Logger log = LoggerFactory.getLogger(RepositoryPisoFactorService.class);

	@Autowired
	private PisoFactorRepository pisoFactorRepository;

	@Autowired
	private PisoFactorEnsamblador pisoFactorEnsamblador;

	@Transactional(readOnly = true)
	@Override
	public List<PisoFactorDTO> encontrarTodos() {
		log.debug("Encontrar todos los Pisos Factor");
		List<PisoFactorDTO> pisoFactorDTOs = new ArrayList<PisoFactorDTO>();
		for (PisoFactor pisoFactor : pisoFactorRepository.findAll()) {
			pisoFactorDTOs.add(pisoFactorEnsamblador.escribirDTO(pisoFactor));
		}
		return pisoFactorDTOs;
	}
	
}
