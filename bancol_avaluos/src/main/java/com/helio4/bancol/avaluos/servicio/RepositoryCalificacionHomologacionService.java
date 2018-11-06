package com.helio4.bancol.avaluos.servicio;

import java.util.ArrayList;
import java.util.List;

import com.helio4.bancol.avaluos.servicio.datos.CalificacionHomologacionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.CalificacionHomologacionDTO;
import com.helio4.bancol.avaluos.ensamblador.CalificacionHomologacionEnsamblador;
import com.helio4.bancol.avaluos.modelo.CalificacionHomologacion;
import com.helio4.bancol.avaluos.persistencia.CalificacionHomologacionRepository;

@Component(value = "repositoryCalificacionHomologacionService")
@Transactional(readOnly = true)
public class RepositoryCalificacionHomologacionService implements CalificacionHomologacionService {

	private static Logger log = LoggerFactory.getLogger(RepositoryCalificacionHomologacionService.class);

	@Autowired
	private CalificacionHomologacionRepository calificacionHomologacionRepository;

	@Autowired
	private CalificacionHomologacionEnsamblador calificacionHomologacionEnsamblador;

	@Transactional(readOnly = true)
	@Override
	public List<CalificacionHomologacionDTO> encontrarTodos() {
		log.debug("Encontrar todas las Calificaciones de Homologación");
		List<CalificacionHomologacionDTO> calificacionHomologacionDTOs = new ArrayList<CalificacionHomologacionDTO>();
		for (CalificacionHomologacion calificacionHomologacion : calificacionHomologacionRepository.findAll()) {
			calificacionHomologacionDTOs.add(calificacionHomologacionEnsamblador.escribirDTO(calificacionHomologacion));
		}
		return calificacionHomologacionDTOs;
	}
	
}
