package com.helio4.bancol.avaluos.dominio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.CalificacionHomologacionDTO;
import com.helio4.bancol.avaluos.servicio.datos.CalificacionHomologacionService;

@Component
public class CalificacionHomologacionController {

	@Autowired
	@Qualifier("repositoryCalificacionHomologacionService")
	private CalificacionHomologacionService calificacionHomologacionService;

    public List<CalificacionHomologacionDTO> encontrarCalificacionesHomologacion(){
		return calificacionHomologacionService.encontrarTodos();
	}

}
