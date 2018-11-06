package com.helio4.bancol.avaluos.servicio.datos;

import java.util.List;

import org.springframework.stereotype.Service;

import com.helio4.bancol.avaluos.dto.TipoAvaluoDTO;

@Service
public interface TipoAvaluoService {

	/**
	 *
	 * @return
	 */
	public List<TipoAvaluoDTO> encontrarTodos();

	public TipoAvaluoDTO encontrarPorId(Long id);

	public TipoAvaluoDTO encontrarPorNombre(String value);

	public List<TipoAvaluoDTO> encontrarTiposAvaluo();

}
