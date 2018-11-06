package com.helio4.bancol.avaluos.servicio.datos;

import java.util.List;

import org.springframework.stereotype.Service;

import com.helio4.bancol.avaluos.dto.BitacoraDTO;

@Service
public interface BitacoraService {
	
	public List<BitacoraDTO> encontrarBitacoraPorAvaluo(Long id);
	
	public BitacoraDTO guardar(BitacoraDTO bitacoraDTO);

}
