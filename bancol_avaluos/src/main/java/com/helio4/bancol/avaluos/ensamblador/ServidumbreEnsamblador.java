package com.helio4.bancol.avaluos.ensamblador;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.ServidumbreDTO;
import com.helio4.bancol.avaluos.modelo.AvaluoComercial;
import com.helio4.bancol.avaluos.modelo.Servidumbre;
import com.helio4.bancol.avaluos.persistencia.AvaluoComercialRepository;
import com.helio4.bancol.avaluos.persistencia.ServidumbreRepository;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.ServidumbreNotFoundException;

@Component
public class ServidumbreEnsamblador {

	@Autowired
	private AvaluoComercialRepository avaluoComercialRepository;

	@Autowired
	private ServidumbreRepository servidumbreRepository;
	
	public Servidumbre crearServidumbre(ServidumbreDTO servidumbreDTO) throws AvaluoNotFoundException {
		Servidumbre servidumbre = new Servidumbre();
		servidumbre.setId(servidumbreDTO.getId());
		servidumbre.setTipoDeServidumbre(servidumbreDTO.getTipoDeServidumbre());
		servidumbre.setBeneficiario(servidumbreDTO.getBeneficiario());
		AvaluoComercial avaluoComercial = avaluoComercialRepository.findOne(servidumbreDTO.getAvaluoId());
		if (avaluoComercial == null) {
			throw new AvaluoNotFoundException();
		}
		servidumbre.setAvaluo(avaluoComercial);
		return servidumbre;
	}

	public void actualizarServidumbre(Long servidumbreId, ServidumbreDTO servidumbreDTO) throws ServidumbreNotFoundException, AvaluoNotFoundException {
		Servidumbre servidumbre = servidumbreRepository.findOne(servidumbreId);
		if (servidumbre == null) {
			throw new ServidumbreNotFoundException();
		}
		servidumbre.setTipoDeServidumbre(servidumbreDTO.getTipoDeServidumbre());
		servidumbre.setBeneficiario(servidumbreDTO.getBeneficiario());
		AvaluoComercial avaluoComercial = avaluoComercialRepository.findOne(servidumbreDTO.getAvaluoId());
		if (avaluoComercial == null) {
			throw new AvaluoNotFoundException();
		}
		servidumbre.setAvaluo(avaluoComercial);
	}

	public ServidumbreDTO escribirDTO(Servidumbre servidumbre) {
		ServidumbreDTO servidumbreDTO = new ServidumbreDTO();
		servidumbreDTO.setId(servidumbre.getId());
		servidumbreDTO.setTipoDeServidumbre(servidumbre.getTipoDeServidumbre());
		servidumbreDTO.setBeneficiario(servidumbre.getBeneficiario());
		servidumbreDTO.setAvaluoId(servidumbre.getAvaluo().getId());
		return servidumbreDTO;
	}
	
	public List<ServidumbreDTO> escribirListaDTO(List<Servidumbre> servidumbres) {
		List<ServidumbreDTO> servidumbreDTOs = new ArrayList<ServidumbreDTO>();
		for (Servidumbre servidumbre:servidumbres) {
			servidumbreDTOs.add(escribirDTO(servidumbre));
		}
		return servidumbreDTOs;
	}

	public Set<ServidumbreDTO> escribirListaDTO(Set<Servidumbre> servidumbres) {
		Set<ServidumbreDTO> servidumbreDTOs = new HashSet<ServidumbreDTO>();
		for (Servidumbre servidumbre:servidumbres) {
			servidumbreDTOs.add(escribirDTO(servidumbre));
		}
		return servidumbreDTOs;
	}

}
