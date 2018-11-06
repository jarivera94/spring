package com.helio4.bancol.avaluos.ensamblador;

import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.RegionalDTO;
import com.helio4.bancol.avaluos.modelo.Regional;

@Component
public class RegionalEnsamblador {
	
	public RegionalDTO escribirDTO(Regional regional){
		RegionalDTO regionalDTO = null;
		
		if(regional!=null){
			regionalDTO = new RegionalDTO();
			regionalDTO.setId(regional.getId());
			regionalDTO.setNombre(regional.getNombre());
		}
		
		return regionalDTO;
	}
	
	public Regional crearRegional(RegionalDTO regionalDTO){
		Regional regional = null;
		
		if(regionalDTO!=null){
			regional = new Regional();
			regional.setId(regionalDTO.getId());
			regional.setNombre(regionalDTO.getNombre());
		}
		
		return regional;
	}

}
