package com.helio4.bancol.avaluos.dominio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.PisoFactorDTO;
import com.helio4.bancol.avaluos.servicio.datos.PisoFactorService;

@Component
public class PisoFactorController {

	@Autowired
	@Qualifier("repositoryPisoFactorService")
	private PisoFactorService pisoFactorService;

    public List<PisoFactorDTO> encontrarPisosFactor(){
		return pisoFactorService.encontrarTodos();
	}

}
