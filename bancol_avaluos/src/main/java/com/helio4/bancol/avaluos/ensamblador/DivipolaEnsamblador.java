package com.helio4.bancol.avaluos.ensamblador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.DivipolaDTO;
import com.helio4.bancol.avaluos.modelo.Divipola;

@Component
public class DivipolaEnsamblador {
	
	@Autowired
	private RegionalEnsamblador regionalEnsamblador;
	
	public DivipolaDTO escribirDTO(Divipola divipola) {
		DivipolaDTO divipolaDTO = new DivipolaDTO();
		divipolaDTO.setId(divipola.getId());
		divipolaDTO.setMunicipio(divipola.getMunicipio());
		divipolaDTO.setCentroPoblado(divipola.getCentroPoblado());
		divipolaDTO.setDepartamento(divipola.getDepartamento());
		divipolaDTO.setCodigoDepartamento(divipola.getCodigoDepartamento());
		divipolaDTO.setCodigoMunicipio(divipola.getCodigoMunicipio());
		divipolaDTO.setCodigoCentroPoblado(divipola.getCodigoCentroPoblado());
		divipolaDTO.setDepartamentoBUA(divipola.getDepartamentoBUA());
		divipolaDTO.setMunicipioBUA(divipola.getMunicipioBUA());
		divipolaDTO.setRegional(regionalEnsamblador.escribirDTO(divipola.getRegional()));
		divipolaDTO.setCapital(divipola.getCapital());
		return divipolaDTO;
	}

	public List<DivipolaDTO> escribirListaDTO(List<Divipola> divipolas) {
		int size = divipolas !=null ?  divipolas.size() : 0;
		List<DivipolaDTO> divipolasDTO = new ArrayList<DivipolaDTO>(size);
		for (Divipola divipola:divipolas) {
			divipolasDTO.add(escribirDTO(divipola));
		}
		return divipolasDTO;
	}

}