package com.helio4.bancol.avaluos.ensamblador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.AreaLoteAvaluoDTO;
import com.helio4.bancol.avaluos.modelo.AreaLoteAvaluo;
import com.helio4.bancol.avaluos.modelo.Avaluo;
import com.helio4.bancol.avaluos.persistencia.AvaluoRepository;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;

@Component
public class AreaLoteAvaluoEnsamblador {

	@Autowired
	private AvaluoRepository avaluoRepository;
	
		
	public AreaLoteAvaluo crear(AreaLoteAvaluoDTO areaLoteAvaluoDTO) throws AvaluoNotFoundException{
		AreaLoteAvaluo areaLoteAvaluo = new AreaLoteAvaluo();
		areaLoteAvaluo.setId(areaLoteAvaluoDTO.getId());
		areaLoteAvaluo.setArea(areaLoteAvaluoDTO.getArea());
		areaLoteAvaluo.setFrente(areaLoteAvaluoDTO.getFrente());
		areaLoteAvaluo.setFondo(areaLoteAvaluoDTO.getFondo());
		areaLoteAvaluo.setRelacion(areaLoteAvaluoDTO.getRelacion());
		
		if ( areaLoteAvaluoDTO.getAvaluoId() != null) {
			Avaluo avaluo = this.avaluoRepository.findOne(areaLoteAvaluoDTO.getAvaluoId());
			if( avaluo == null ){
				throw new AvaluoNotFoundException();
			}
			areaLoteAvaluo.setAvaluo(avaluo);
		}
		
		return areaLoteAvaluo;
		
	}
	public AreaLoteAvaluoDTO escribirDTO(AreaLoteAvaluo areaLoteAvaluo) {
		AreaLoteAvaluoDTO areaLoteAvaluoDTO = new AreaLoteAvaluoDTO();
		areaLoteAvaluoDTO.setId(areaLoteAvaluo.getId());
		areaLoteAvaluoDTO.setArea(areaLoteAvaluo.getArea());
		areaLoteAvaluoDTO.setFrente(areaLoteAvaluo.getFrente());
		areaLoteAvaluoDTO.setFondo(areaLoteAvaluo.getFondo());
		areaLoteAvaluoDTO.setRelacion(areaLoteAvaluo.getRelacion());
		if(areaLoteAvaluo.getAvaluo()!= null){
			areaLoteAvaluoDTO.setAvaluoId(areaLoteAvaluo.getAvaluo().getId());
		}
		return areaLoteAvaluoDTO; 
	}
}
