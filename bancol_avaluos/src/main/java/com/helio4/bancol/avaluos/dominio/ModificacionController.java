package com.helio4.bancol.avaluos.dominio;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.ModificacionDTO;
import com.helio4.bancol.avaluos.servicio.datos.ModificacionService;

@Component
public class ModificacionController {

	private static Logger log = LoggerFactory
			.getLogger(ModificacionController.class);

	@Autowired
	@Qualifier("repositoryModificacionService")
	private ModificacionService modificacionService;


	public List<ModificacionDTO> guardar(List<ModificacionDTO> modificaciones) {
		
		modificaciones = modificacionService.guardar(modificaciones);
		
		return modificaciones;
	}
}
