package com.helio4.bancol.avaluos.ensamblador;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.ViaAccesoDTO;
import com.helio4.bancol.avaluos.dto.AvaluoDTO.MBR;
import com.helio4.bancol.avaluos.modelo.AvaluoComercial;
import com.helio4.bancol.avaluos.modelo.ViaAcceso;
import com.helio4.bancol.avaluos.persistencia.AvaluoComercialRepository;
import com.helio4.bancol.avaluos.persistencia.ViaAccesoRepository;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;

@Component
public class ViaAccesoEnsamblador {
	
	@Autowired
	private ViaAccesoRepository viaAccesoRepository;
	
	@Autowired
	private AvaluoComercialRepository avaluoComercialRepository;

	public ViaAcceso crearViaAcceso(ViaAccesoDTO viaAccesoDTO) throws AvaluoNotFoundException {
		ViaAcceso viaAcceso = new ViaAcceso();
		AvaluoComercial avaluoComercial = avaluoComercialRepository.findOne(viaAccesoDTO.getAvaluoId());
		if (avaluoComercial == null) {
			throw new AvaluoNotFoundException();
		}
		viaAcceso.setAvaluo(avaluoComercial);
		viaAcceso.setTrayecto(viaAccesoDTO.getTrayecto());
		viaAcceso.setVia(viaAccesoDTO.getVia());
		viaAcceso.setDescripcion(viaAccesoDTO.getDescripcion());
		if (viaAccesoDTO.getEstado() != null) {
			viaAcceso.setEstado(viaAccesoDTO.getEstado().getKey());
		}
		viaAcceso.setTipo(viaAccesoDTO.getTipo());
		return viaAcceso;
	}

	public ViaAccesoDTO escribirDTO(ViaAcceso viaAcceso) {
		ViaAccesoDTO viaAccesoDTO = new ViaAccesoDTO();
		viaAccesoDTO.setAvaluoId(viaAcceso.getAvaluo().getId());
		viaAccesoDTO.setId(viaAcceso.getId());
		viaAccesoDTO.setTrayecto(viaAcceso.getTrayecto());
		viaAccesoDTO.setVia(viaAcceso.getVia());
		viaAccesoDTO.setDescripcion(viaAcceso.getDescripcion());
		if (viaAcceso.getEstado() != null && viaAcceso.getEstado() > 0) {
			viaAccesoDTO.setEstado(MBR.fromKey(viaAcceso.getEstado()));
		}
		viaAccesoDTO.setTipo(viaAcceso.getTipo());
		return viaAccesoDTO;
	}

	public List<ViaAccesoDTO> escribirListaDTO(List<ViaAcceso> viasAcceso) {
		List<ViaAccesoDTO> viasAccesoDTO = new ArrayList<ViaAccesoDTO>();
		for (ViaAcceso viaAcceso:viasAcceso) {
			viasAccesoDTO.add(escribirDTO(viaAcceso));
		}
		return viasAccesoDTO;
	}

	public Set<ViaAccesoDTO> escribirListaDTO(Set<ViaAcceso> viasAcceso) {
		Set<ViaAccesoDTO> viasAccesoDTO = new HashSet<ViaAccesoDTO>();
		for (ViaAcceso viaAcceso:viasAcceso) {
			viasAccesoDTO.add(escribirDTO(viaAcceso));
		}
		return viasAccesoDTO;
	}

	public void actualizarViaAcceso(Long id, ViaAccesoDTO actualizado) throws AvaluoNotFoundException {
		ViaAcceso viaAcceso = viaAccesoRepository.findOne(id);
		AvaluoComercial avaluoComercial = avaluoComercialRepository.findOne(actualizado.getAvaluoId());
		if (avaluoComercial == null) {
			throw new AvaluoNotFoundException();
		}
		viaAcceso.setAvaluo(avaluoComercial);
		viaAcceso.setTrayecto(actualizado.getTrayecto());
		viaAcceso.setVia(actualizado.getVia());
		viaAcceso.setDescripcion(actualizado.getDescripcion());
		if (actualizado.getEstado() != null) {
			viaAcceso.setEstado(actualizado.getEstado().getKey());
		}
		viaAcceso.setTipo(actualizado.getTipo());
	}

}
