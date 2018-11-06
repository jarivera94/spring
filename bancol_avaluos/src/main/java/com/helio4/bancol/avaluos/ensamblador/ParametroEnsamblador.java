package com.helio4.bancol.avaluos.ensamblador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.ParametroDTO;
import com.helio4.bancol.avaluos.modelo.Parametro;
import com.helio4.bancol.avaluos.persistencia.ParametroRepository;
import com.helio4.bancol.avaluos.servicio.excepciones.ParametroNotFoundException;

@Component
public class ParametroEnsamblador {
	
	@Autowired
	private ParametroRepository parametroRepository;
	
	public Parametro crearParametro(ParametroDTO parametroDTO) {
		Parametro parametro = new Parametro();
		parametro.setNombre(parametroDTO.getNombre());
		parametro.setValor(parametroDTO.getValor());
		return parametro;
	}
	
	public ParametroDTO escribirDTO(Parametro parametro) {
		ParametroDTO parametroDTO = new ParametroDTO();
		parametroDTO.setId(parametro.getId());
		parametroDTO.setNombre(parametro.getNombre());
		parametroDTO.setValor(parametro.getValor());
		return parametroDTO;
	}
	
	public List<ParametroDTO> escribirListaParametros(List<Parametro> parametros){
		List<ParametroDTO> parametroDTOs = new ArrayList<ParametroDTO>();
		for (Parametro parametro:parametros) {
			parametroDTOs.add(escribirDTO(parametro));
		}
		return parametroDTOs;
	}
	
	public void actualizarParametro(Long parametroId, ParametroDTO parametroDTO) throws ParametroNotFoundException {
		Parametro parametro = parametroRepository.findOne(parametroId);
		if (parametro == null) {
			throw new ParametroNotFoundException();
		}
		parametro.setNombre(parametroDTO.getNombre());
		parametro.setValor(parametroDTO.getValor());
	}

}
