package com.helio4.bancol.avaluos.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.helio4.bancol.avaluos.dto.AvaluoHipotecarioDTO;

@Service
public interface BUAService {

	String exportartCsvBUA(List<AvaluoHipotecarioDTO> avaluosHipotecariosDTOs);
	
	String exportartCsvBUA(List<AvaluoHipotecarioDTO> avaluosHipotecariosDTOs, String consecutivo);

}