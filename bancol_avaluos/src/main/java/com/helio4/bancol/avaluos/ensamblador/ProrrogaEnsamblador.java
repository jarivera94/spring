package com.helio4.bancol.avaluos.ensamblador;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.ProrrogaDTO;
import com.helio4.bancol.avaluos.modelo.AvaluoConstructor;
import com.helio4.bancol.avaluos.modelo.Prorroga;
import com.helio4.bancol.avaluos.persistencia.AvaluoConstructorRepository;
import com.helio4.bancol.avaluos.persistencia.ProrrogaRepository;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;

@Component
public class ProrrogaEnsamblador {

	@Autowired
	private AvaluoConstructorRepository avaluoConstructorRepository;
	
	@Autowired
	private ProrrogaRepository prorrogaRepository;

	public Prorroga crearProrroga(ProrrogaDTO prorrogaDTO) throws AvaluoNotFoundException {
		Prorroga prorroga = new Prorroga();
		AvaluoConstructor avaluoConstructor = avaluoConstructorRepository.findOne(prorrogaDTO.getAvaluoId());
		if (avaluoConstructor == null) {
			throw new AvaluoNotFoundException();
		}
		prorroga.setAvaluo(avaluoConstructor);
		prorroga.setFechaCreacion(prorrogaDTO.getFechaCreacion());
		prorroga.setFechaAprovacion(prorrogaDTO.getFechaAprovacion());
		prorroga.setFechaProrroga(prorrogaDTO.getFechaProrroga());
		prorroga.setJustificacion(prorrogaDTO.getJustificacion());
		return prorroga;
	}

	public ProrrogaDTO escribirDTO(Prorroga prorroga) {
		ProrrogaDTO prorrogaDTO = new ProrrogaDTO();
		prorrogaDTO.setAvaluoId(prorroga.getAvaluo().getId());
		prorrogaDTO.setId(prorroga.getId());
		prorrogaDTO.setFechaCreacion(prorroga.getFechaCreacion());
		prorrogaDTO.setFechaAprovacion(prorroga.getFechaAprovacion());
		prorrogaDTO.setFechaProrroga(prorroga.getFechaProrroga());
		prorrogaDTO.setJustificacion(prorroga.getJustificacion());
		return prorrogaDTO;
	}

	public List<ProrrogaDTO> escribirListaDTO(List<Prorroga> prorrogas) {
		List<ProrrogaDTO> prorrogasDTO = new ArrayList<ProrrogaDTO>();
		for (Prorroga prorroga:prorrogas) {
			prorrogasDTO.add(escribirDTO(prorroga));
		}
		return prorrogasDTO;
	}

	public Set<ProrrogaDTO> escribirListaDTO(Set<Prorroga> prorrogas) {
		Set<ProrrogaDTO> prorrogasDTO = new HashSet<ProrrogaDTO>();
		for (Prorroga prorroga:prorrogas) {
			prorrogasDTO.add(escribirDTO(prorroga));
		}
		return prorrogasDTO;
	}

	public void actualizarProrroga(Long id, ProrrogaDTO actualizado) throws AvaluoNotFoundException {
		Prorroga prorroga = prorrogaRepository.findOne(id);
		AvaluoConstructor avaluoConstructor = avaluoConstructorRepository.findOne(actualizado.getAvaluoId());
		if (avaluoConstructor == null) {
			throw new AvaluoNotFoundException();
		}
		prorroga.setAvaluo(avaluoConstructor);
		prorroga.setFechaCreacion(actualizado.getFechaCreacion());
		prorroga.setFechaAprovacion(actualizado.getFechaAprovacion());
		prorroga.setFechaProrroga(actualizado.getFechaProrroga());
		prorroga.setJustificacion(actualizado.getJustificacion());
	}

}
