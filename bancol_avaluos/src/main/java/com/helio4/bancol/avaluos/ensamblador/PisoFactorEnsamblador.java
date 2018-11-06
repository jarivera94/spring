package com.helio4.bancol.avaluos.ensamblador;

import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.PisoFactorDTO;
import com.helio4.bancol.avaluos.modelo.PisoFactor;

@Component
public class PisoFactorEnsamblador {

	public PisoFactorDTO escribirDTO(PisoFactor pisoFactor) {
		
		PisoFactorDTO pisoFactorDTO = null;
		
		if(pisoFactor!=null){
			pisoFactorDTO = new PisoFactorDTO();
			pisoFactorDTO.setId(pisoFactor.getId());
			pisoFactorDTO.setDesde(pisoFactor.getDesde());
			pisoFactorDTO.setHasta(pisoFactor.getHasta());
			pisoFactorDTO.setPorcentaje(pisoFactor.getPorcentaje());
		}
		
		return pisoFactorDTO;
	}
	
}
