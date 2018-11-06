package com.helio4.bancol.avaluos.servicio.datos;

import java.util.List;

import org.springframework.stereotype.Service;

import com.helio4.bancol.avaluos.dto.MotivoDTO;
import com.helio4.bancol.avaluos.modelo.Motivo;

@Service
public interface MotivoService {

	public List<MotivoDTO> getMotivosByEntidad(Long entidadId);
	
	public Motivo getMotivosById(Long id);

}
