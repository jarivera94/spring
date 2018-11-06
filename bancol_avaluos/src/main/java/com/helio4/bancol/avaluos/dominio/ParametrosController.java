package com.helio4.bancol.avaluos.dominio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.helio4.bancol.avaluos.servicio.datos.ParametroService;

@Controller
public class ParametrosController {

	@Autowired
	private ParametroService parametroService;
	
	public String obtenerValor(String nombreParametro) {
		return parametroService.encontrarPorNombre(nombreParametro).getValor();
	}
	
}
