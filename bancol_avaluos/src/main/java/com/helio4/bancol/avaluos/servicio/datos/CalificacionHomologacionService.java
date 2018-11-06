package com.helio4.bancol.avaluos.servicio.datos;

import java.util.List;

import org.springframework.stereotype.Service;

import com.helio4.bancol.avaluos.dto.CalificacionHomologacionDTO;

@Service
public interface CalificacionHomologacionService {

	/**
	 *
	 * @return
	 */
	public List<CalificacionHomologacionDTO> encontrarTodos();

}
