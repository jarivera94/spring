package com.helio4.bancol.avaluos.servicio.datos;

import java.util.List;

import org.springframework.stereotype.Service;

import com.helio4.bancol.avaluos.dto.RegionalDTO;

@Service
public interface RegionalService {

	/**
	 * Encuentra todos las regionales.
	 * @return Una lista con todos las regionales.
	 */
	public List<RegionalDTO> encontrarTodos();

	public RegionalDTO encontrarPorNombre(String value);

    public List<RegionalDTO> encontrarRegionales();
}
