package com.helio4.bancol.avaluos.servicio.datos;

import java.util.List;

import org.springframework.stereotype.Service;

import com.helio4.bancol.avaluos.dto.PisoFactorDTO;

@Service
public interface PisoFactorService {

	/**
	 *
	 * @return
	 */
	public List<PisoFactorDTO> encontrarTodos();

}
