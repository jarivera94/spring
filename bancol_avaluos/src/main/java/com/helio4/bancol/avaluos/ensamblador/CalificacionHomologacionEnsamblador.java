package com.helio4.bancol.avaluos.ensamblador;

import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.CalificacionHomologacionDTO;
import com.helio4.bancol.avaluos.modelo.CalificacionHomologacion;

@Component
public class CalificacionHomologacionEnsamblador {

	public CalificacionHomologacionDTO escribirDTO(CalificacionHomologacion calificacionHomologacion) {
		CalificacionHomologacionDTO calificacionHomologacionDTO = null;
		if(calificacionHomologacion!=null){
			calificacionHomologacionDTO = new CalificacionHomologacionDTO();
			calificacionHomologacionDTO.setId(calificacionHomologacion.getId());
			calificacionHomologacionDTO.setCodigo(calificacionHomologacion.getCodigo());
			calificacionHomologacionDTO.setCalificacion(calificacionHomologacion.getCalificacion());
			calificacionHomologacionDTO.setFactor(calificacionHomologacion.getFactor());
		}
		
		return calificacionHomologacionDTO;
	}
	
}
