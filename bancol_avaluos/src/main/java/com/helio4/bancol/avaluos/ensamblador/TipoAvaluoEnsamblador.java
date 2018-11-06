package com.helio4.bancol.avaluos.ensamblador;

import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.TipoAvaluoDTO;
import com.helio4.bancol.avaluos.modelo.TipoAvaluo;

@Component
public class TipoAvaluoEnsamblador {

	public TipoAvaluoDTO escribirDTO(TipoAvaluo tipoAvaluo) {
		TipoAvaluoDTO tipoAvaluoDTO = null;
		if(tipoAvaluo!=null){
			tipoAvaluoDTO = new TipoAvaluoDTO();
			tipoAvaluoDTO.setId(tipoAvaluo.getId());
			tipoAvaluoDTO.setNombre(tipoAvaluo.getNombre());
		}
		
		return tipoAvaluoDTO;
	}
	
}
