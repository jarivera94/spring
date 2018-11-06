package com.helio4.bancol.avaluos.servicio.datos;

import java.util.List;

import org.springframework.stereotype.Service;

import com.helio4.bancol.avaluos.dto.ModificacionDTO;

@Service
public interface ModificacionService {
	
	public List<ModificacionDTO> guardar(List<ModificacionDTO> modificaciones);

}
