package com.helio4.bancol.avaluos.ensamblador;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.AumentoPresupuestoDTO;
import com.helio4.bancol.avaluos.modelo.AumentoPresupuesto;
import com.helio4.bancol.avaluos.modelo.AvaluoConstructor;
import com.helio4.bancol.avaluos.persistencia.AumentoPresupuestoRepository;
import com.helio4.bancol.avaluos.persistencia.AvaluoConstructorRepository;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;

@Component
public class AumentoPresupuestoEnsamblador {

	@Autowired
	private AvaluoConstructorRepository avaluoConstructorRepository;
	
	@Autowired
	private AumentoPresupuestoRepository aumentoPresupuestoRepository;

	public AumentoPresupuesto crearAumentoPresupuesto(AumentoPresupuestoDTO aumentoPresupuestoDTO) throws AvaluoNotFoundException {
		AumentoPresupuesto aumentoPresupuesto = new AumentoPresupuesto();
		AvaluoConstructor avaluoConstructor = avaluoConstructorRepository.findOne(aumentoPresupuestoDTO.getAvaluoId());
		if (avaluoConstructor == null) {
			throw new AvaluoNotFoundException();
		}
		aumentoPresupuesto.setAvaluo(avaluoConstructor);
		aumentoPresupuesto.setFechaCreacion(aumentoPresupuestoDTO.getFechaCreacion());
		aumentoPresupuesto.setFechaAprovacion(aumentoPresupuestoDTO.getFechaAprovacion());
		aumentoPresupuesto.setValorAumento(aumentoPresupuestoDTO.getValorAumento());
		aumentoPresupuesto.setJustificacion(aumentoPresupuestoDTO.getJustificacion());
		return aumentoPresupuesto;
	}

	public AumentoPresupuestoDTO escribirDTO(AumentoPresupuesto aumentoPresupuesto) {
		AumentoPresupuestoDTO aumentoPresupuestoDTO = new AumentoPresupuestoDTO();
		aumentoPresupuestoDTO.setAvaluoId(aumentoPresupuesto.getAvaluo().getId());
		aumentoPresupuestoDTO.setId(aumentoPresupuesto.getId());
		aumentoPresupuestoDTO.setFechaCreacion(aumentoPresupuesto.getFechaCreacion());
		aumentoPresupuestoDTO.setFechaAprovacion(aumentoPresupuesto.getFechaAprovacion());
		aumentoPresupuestoDTO.setValorAumento(aumentoPresupuesto.getValorAumento());
		aumentoPresupuestoDTO.setJustificacion(aumentoPresupuesto.getJustificacion());
		return aumentoPresupuestoDTO;
	}

	public List<AumentoPresupuestoDTO> escribirListaDTO(List<AumentoPresupuesto> aumentosPresupuesto) {
		List<AumentoPresupuestoDTO> aumentosPresupuestoDTO = new ArrayList<AumentoPresupuestoDTO>();
		for (AumentoPresupuesto aumentoPresupuesto:aumentosPresupuesto) {
			aumentosPresupuestoDTO.add(escribirDTO(aumentoPresupuesto));
		}
		return aumentosPresupuestoDTO;
	}

	public Set<AumentoPresupuestoDTO> escribirListaDTO(Set<AumentoPresupuesto> aumentosPresupuesto) {
		Set<AumentoPresupuestoDTO> aumentosPresupuestoDTO = new HashSet<AumentoPresupuestoDTO>();
		for (AumentoPresupuesto aumentoPresupuesto:aumentosPresupuesto) {
			aumentosPresupuestoDTO.add(escribirDTO(aumentoPresupuesto));
		}
		return aumentosPresupuestoDTO;
	}

	public void actualizarAumentoPresupuesto(Long id, AumentoPresupuestoDTO actualizado) throws AvaluoNotFoundException {
		AumentoPresupuesto aumentoPresupuesto = aumentoPresupuestoRepository.findOne(id);
		AvaluoConstructor avaluoConstructor = avaluoConstructorRepository.findOne(actualizado.getAvaluoId());
		if (avaluoConstructor == null) {
			throw new AvaluoNotFoundException();
		}
		aumentoPresupuesto.setAvaluo(avaluoConstructor);
		aumentoPresupuesto.setFechaCreacion(actualizado.getFechaCreacion());
		aumentoPresupuesto.setFechaAprovacion(actualizado.getFechaAprovacion());
		aumentoPresupuesto.setValorAumento(actualizado.getValorAumento());
		aumentoPresupuesto.setJustificacion(actualizado.getJustificacion());
	}

}
