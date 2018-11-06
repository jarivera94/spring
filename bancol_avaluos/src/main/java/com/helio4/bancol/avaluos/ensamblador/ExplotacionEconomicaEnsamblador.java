package com.helio4.bancol.avaluos.ensamblador;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.ExplotacionEconomicaDTO;
import com.helio4.bancol.avaluos.dto.AreaDTO.UnidadDeMedida;
import com.helio4.bancol.avaluos.dto.AvaluoDTO.MBR;
import com.helio4.bancol.avaluos.modelo.AvaluoComercial;
import com.helio4.bancol.avaluos.modelo.ExplotacionEconomica;
import com.helio4.bancol.avaluos.persistencia.AvaluoComercialRepository;
import com.helio4.bancol.avaluos.persistencia.ExplotacionEconomicaRepository;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.ExplotacionEconomicaNotFoundException;

@Component
public class ExplotacionEconomicaEnsamblador {

	@Autowired
	private AvaluoComercialRepository avaluoComercialRepository;

	@Autowired
	private ExplotacionEconomicaRepository explotacionEconomicaRepository;
	
	public ExplotacionEconomica crearExplotacionEconomica(ExplotacionEconomicaDTO explotacionEconomicaDTO) throws AvaluoNotFoundException {
		ExplotacionEconomica explotacionEconomica = new ExplotacionEconomica();
		explotacionEconomica.setId(explotacionEconomicaDTO.getId());
		explotacionEconomica.setEtapa(explotacionEconomicaDTO.getEtapa());
		explotacionEconomica.setDescripcion(explotacionEconomicaDTO.getDescripcion());
		if (explotacionEconomicaDTO.getUnidadDeMedida() != null) {
			explotacionEconomica.setUnidadDeMedida(explotacionEconomicaDTO
					.getUnidadDeMedida().getKey());
		}
		explotacionEconomica.setArea(explotacionEconomicaDTO.getArea());
		explotacionEconomica.setPorcentajeDeParticipacion(explotacionEconomicaDTO.getPorcentajeDeParticipacion());
		explotacionEconomica.setFechaDeSiembra(explotacionEconomicaDTO.getFechaDeSiembra());
		explotacionEconomica.setAntiguedad(explotacionEconomicaDTO.getAntiguedad());
		explotacionEconomica.setEstadoFitosanitario(explotacionEconomicaDTO.getEstadoFitosanitario().getKey());
		AvaluoComercial avaluoComercial = avaluoComercialRepository.findOne(explotacionEconomicaDTO.getAvaluoId());
		if (avaluoComercial == null) {
			throw new AvaluoNotFoundException();
		}
		explotacionEconomica.setAvaluo(avaluoComercial);
		return explotacionEconomica;
	}

	public void actualizarExplotacionEconomica(Long explotacionEconomicaId, ExplotacionEconomicaDTO explotacionEconomicaDTO) throws ExplotacionEconomicaNotFoundException, AvaluoNotFoundException {
		ExplotacionEconomica explotacionEconomica = explotacionEconomicaRepository.findOne(explotacionEconomicaId);
		if (explotacionEconomica == null) {
			throw new ExplotacionEconomicaNotFoundException();
		}
		explotacionEconomica.setEtapa(explotacionEconomicaDTO.getEtapa());
		explotacionEconomica.setDescripcion(explotacionEconomicaDTO.getDescripcion());
		if (explotacionEconomicaDTO.getUnidadDeMedida() != null) {
			explotacionEconomica.setUnidadDeMedida(explotacionEconomicaDTO
					.getUnidadDeMedida().getKey());
		}
		explotacionEconomica.setArea(explotacionEconomicaDTO.getArea());
		explotacionEconomica.setPorcentajeDeParticipacion(explotacionEconomicaDTO.getPorcentajeDeParticipacion());
		explotacionEconomica.setFechaDeSiembra(explotacionEconomicaDTO.getFechaDeSiembra());
		explotacionEconomica.setAntiguedad(explotacionEconomicaDTO.getAntiguedad());
		explotacionEconomica.setEstadoFitosanitario(explotacionEconomicaDTO.getEstadoFitosanitario().getKey());
		AvaluoComercial avaluoComercial = avaluoComercialRepository.findOne(explotacionEconomicaDTO.getAvaluoId());
		if (avaluoComercial == null) {
			throw new AvaluoNotFoundException();
		}
		explotacionEconomica.setAvaluo(avaluoComercial);
	}

	public ExplotacionEconomicaDTO escribirDTO(ExplotacionEconomica explotacionEconomica) {
		ExplotacionEconomicaDTO explotacionEconomicaDTO = new ExplotacionEconomicaDTO();
		explotacionEconomicaDTO.setId(explotacionEconomica.getId());
		explotacionEconomicaDTO.setEtapa(explotacionEconomica.getEtapa());
		explotacionEconomicaDTO.setDescripcion(explotacionEconomica.getDescripcion());
		if (explotacionEconomica.getUnidadDeMedida() != null && explotacionEconomica.getUnidadDeMedida() > 0) {
			explotacionEconomicaDTO.setUnidadDeMedida(UnidadDeMedida
					.fromKey(explotacionEconomica.getUnidadDeMedida()));
		}
		explotacionEconomicaDTO.setArea(explotacionEconomica.getArea());
		explotacionEconomicaDTO.setPorcentajeDeParticipacion(explotacionEconomica.getPorcentajeDeParticipacion());
		explotacionEconomicaDTO.setFechaDeSiembra(explotacionEconomica.getFechaDeSiembra());
		explotacionEconomicaDTO.setAntiguedad(explotacionEconomica.getAntiguedad());
		if (explotacionEconomica.getEstadoFitosanitario() != null) {
			explotacionEconomicaDTO.setEstadoFitosanitario(MBR
					.fromKey(explotacionEconomica.getEstadoFitosanitario()));
		}
		explotacionEconomicaDTO.setAvaluoId(explotacionEconomica.getAvaluo().getId());
		return explotacionEconomicaDTO;
	}
	
	public List<ExplotacionEconomicaDTO> escribirListaDTO(List<ExplotacionEconomica> explotacionEconomicas) {
		List<ExplotacionEconomicaDTO> explotacionEconomicaDTOs = new ArrayList<ExplotacionEconomicaDTO>();
		for (ExplotacionEconomica explotacionEconomica:explotacionEconomicas) {
			explotacionEconomicaDTOs.add(escribirDTO(explotacionEconomica));
		}
		return explotacionEconomicaDTOs;
	}
	
	public Set<ExplotacionEconomicaDTO> escribirListaDTO(Set<ExplotacionEconomica> explotacionEconomicas) {
		Set<ExplotacionEconomicaDTO> explotacionEconomicaDTOs = new HashSet<ExplotacionEconomicaDTO>();
		for (ExplotacionEconomica explotacionEconomica:explotacionEconomicas) {
			explotacionEconomicaDTOs.add(escribirDTO(explotacionEconomica));
		}
		return explotacionEconomicaDTOs;
	}

}
