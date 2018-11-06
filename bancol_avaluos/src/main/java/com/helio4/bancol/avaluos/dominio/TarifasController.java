package com.helio4.bancol.avaluos.dominio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.TarifaDTO;
import com.helio4.bancol.avaluos.servicio.excepciones.TarifaNotFoundException;
import com.helio4.bancol.avaluos.servicio.datos.TarifaService;

@Component
@Scope("session")
public class TarifasController {

	/**
	 * Instancia Services Interface.
	 */
	@Autowired
	@Qualifier("repositoryTarifaService")
	private TarifaService tarifaService;
	
	public List<TarifaDTO> encontrarPorIdEntidad(Long entidadId) {
		return tarifaService.encontrarPorIdEntidad(entidadId);
	}
	
	public Boolean crearTarifas(List<TarifaDTO> listTarifaDTOs, Long idEntidad) throws TarifaNotFoundException{
		return tarifaService.crearTarifas(listTarifaDTOs, idEntidad);
	}
	
	public Boolean actualizarTarifas(List<TarifaDTO> listTarifaDTOs) throws TarifaNotFoundException{
		return tarifaService.actualizarTarifas(listTarifaDTOs);
	}
}
