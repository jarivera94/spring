package com.helio4.bancol.avaluos.dominio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.TipoAvaluoDTO;
import com.helio4.bancol.avaluos.servicio.datos.TipoAvaluoService;

@Component
public class TiposAvaluoController {

	/**
	 * Instancia Services Interface.
	 */
	@Autowired
	@Qualifier("repositoryTipoAvaluoService")
	private TipoAvaluoService tipoAvaluoService;

	public List<TipoAvaluoDTO> tiposAvaluo(){
		return tipoAvaluoService.encontrarTodos();
	}

}
