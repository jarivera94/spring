package com.helio4.bancol.avaluos.ensamblador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.AreaConstruccionAvaluoDTO;
import com.helio4.bancol.avaluos.modelo.AreaConstruccionAvaluo;
import com.helio4.bancol.avaluos.modelo.Avaluo;
import com.helio4.bancol.avaluos.persistencia.AreaConstruccionAvaluoRepository;
import com.helio4.bancol.avaluos.persistencia.AvaluoRepository;
import com.helio4.bancol.avaluos.servicio.excepciones.AreaConstruccionAvaluoNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;

@Component
public class AreaConstruccionAvaluoEnsamblador {
	private static Logger log = LoggerFactory
	        .getLogger(AreaConstruccionAvaluoEnsamblador.class);
	
	@Autowired
	private AreaConstruccionAvaluoRepository areaConstruccionAvaluoRepository;
	
	@Autowired
	private AvaluoRepository avaluoRepository;
		
	public AreaConstruccionAvaluo crear(AreaConstruccionAvaluoDTO areaConstruccionAvaluoDTO) throws AvaluoNotFoundException{
		AreaConstruccionAvaluo areaConstruccionAvaluo = new AreaConstruccionAvaluo(); 
		areaConstruccionAvaluo.setId(areaConstruccionAvaluoDTO.getId());
		areaConstruccionAvaluo.setAreaPrivada(areaConstruccionAvaluoDTO.getAreaPrivada());
		areaConstruccionAvaluo.setAreaConstruida(areaConstruccionAvaluoDTO.getAreaConstruida());
		areaConstruccionAvaluo.setAreaLibre(areaConstruccionAvaluoDTO.getAreaLibre());
		areaConstruccionAvaluo.setUsoAreaLibre(areaConstruccionAvaluoDTO.getUsoAreaLibre());
		areaConstruccionAvaluo.setAreaCatastral(areaConstruccionAvaluoDTO.getAreaCatastral());
		areaConstruccionAvaluo.setAreaMedidaInspeccion(areaConstruccionAvaluoDTO.getAreaMedidaInspeccion());
		areaConstruccionAvaluo.setAreaValorada(areaConstruccionAvaluoDTO.getAreaValorada());
		areaConstruccionAvaluo.setArealicenciaConstruccion(areaConstruccionAvaluoDTO.getArealicenciaConstruccion());
		areaConstruccionAvaluo.setAreaSuseptibleLegalizacion(areaConstruccionAvaluoDTO.getAreaSuseptibleLegalizacion());
		
		if ( areaConstruccionAvaluoDTO.getAvaluoId() != null) {
			Avaluo avaluo = this.avaluoRepository.findOne(areaConstruccionAvaluoDTO.getAvaluoId());
			if( avaluo == null ){
				throw new AvaluoNotFoundException();
			}
			areaConstruccionAvaluo.setAvaluo(avaluo);
		}
		
		return areaConstruccionAvaluo;
		
	}
	public AreaConstruccionAvaluoDTO escribirDTO(AreaConstruccionAvaluo areaConstruccionAvaluo) {
		AreaConstruccionAvaluoDTO areaConstruccionAvaluoDTO = new AreaConstruccionAvaluoDTO();
		areaConstruccionAvaluoDTO.setId(areaConstruccionAvaluo.getId());
		areaConstruccionAvaluoDTO.setAreaPrivada(areaConstruccionAvaluo.getAreaPrivada());
		areaConstruccionAvaluoDTO.setAreaConstruida(areaConstruccionAvaluo.getAreaConstruida());
		areaConstruccionAvaluoDTO.setAreaLibre(areaConstruccionAvaluo.getAreaLibre());
		areaConstruccionAvaluoDTO.setUsoAreaLibre(areaConstruccionAvaluo.getUsoAreaLibre());
		areaConstruccionAvaluoDTO.setAreaCatastral(areaConstruccionAvaluo.getAreaCatastral());
		areaConstruccionAvaluoDTO.setAreaMedidaInspeccion(areaConstruccionAvaluo.getAreaMedidaInspeccion());
		areaConstruccionAvaluoDTO.setAreaValorada(areaConstruccionAvaluo.getAreaValorada());
		areaConstruccionAvaluoDTO.setArealicenciaConstruccion(areaConstruccionAvaluo.getArealicenciaConstruccion());
		areaConstruccionAvaluoDTO.setAreaSuseptibleLegalizacion(areaConstruccionAvaluo.getAreaSuseptibleLegalizacion());
		if(areaConstruccionAvaluo.getAvaluo()!= null){
			areaConstruccionAvaluoDTO.setAvaluoId(areaConstruccionAvaluo.getAvaluo().getId());
		}
		return areaConstruccionAvaluoDTO; 
	}
	
	public AreaConstruccionAvaluo actualizar(Long id, AreaConstruccionAvaluoDTO actualizado) throws AreaConstruccionAvaluoNotFoundException{
		log.debug("Actualizando FormatoInformeHipotecarioDTO:  {}",actualizado);
		AreaConstruccionAvaluo areaConstruccionAvaluo = areaConstruccionAvaluoRepository.findOne(id);
	        if (areaConstruccionAvaluo == null) {
	            throw new AreaConstruccionAvaluoNotFoundException();
	        } 
		areaConstruccionAvaluo.setId(actualizado.getId());
		areaConstruccionAvaluo.setAreaPrivada(actualizado.getAreaPrivada());
		areaConstruccionAvaluo.setAreaConstruida(actualizado.getAreaConstruida());
		areaConstruccionAvaluo.setAreaLibre(actualizado.getAreaLibre());
		areaConstruccionAvaluo.setUsoAreaLibre(actualizado.getUsoAreaLibre());
		areaConstruccionAvaluo.setAreaCatastral(actualizado.getAreaCatastral());
		areaConstruccionAvaluo.setAreaMedidaInspeccion(actualizado.getAreaMedidaInspeccion());
		areaConstruccionAvaluo.setAreaValorada(actualizado.getAreaValorada());
		areaConstruccionAvaluo.setArealicenciaConstruccion(actualizado.getArealicenciaConstruccion());
		areaConstruccionAvaluo.setAreaSuseptibleLegalizacion(actualizado.getAreaSuseptibleLegalizacion());
		return areaConstruccionAvaluo;
		
	}
 
}
