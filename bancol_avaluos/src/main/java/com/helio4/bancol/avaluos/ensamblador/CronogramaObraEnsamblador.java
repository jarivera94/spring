package com.helio4.bancol.avaluos.ensamblador;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.CronogramaObraDTO;
import com.helio4.bancol.avaluos.modelo.AvaluoConstructor;
import com.helio4.bancol.avaluos.modelo.CronogramaObra;
import com.helio4.bancol.avaluos.persistencia.AvaluoConstructorRepository;
import com.helio4.bancol.avaluos.persistencia.CronogramaObraRepository;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;

@Component
public class CronogramaObraEnsamblador {

	@Autowired
	private AvaluoConstructorRepository avaluoConstructorRepository;
	
	@Autowired
	private CronogramaObraRepository cronogramaObraRepository;

	public CronogramaObra crearCronogramaObra(CronogramaObraDTO cronogramaObraDTO) throws AvaluoNotFoundException {
		CronogramaObra cronogramaObra = new CronogramaObra();
		AvaluoConstructor avaluoConstructor = avaluoConstructorRepository.findOne(cronogramaObraDTO.getAvaluoId());
		if (avaluoConstructor == null) {
			throw new AvaluoNotFoundException();
		}
		cronogramaObra.setAvaluo(avaluoConstructor);
		cronogramaObra.setNoDeCapitulo(cronogramaObraDTO.getNoDeCapitulo());
		cronogramaObra.setNombreCapitulo(cronogramaObraDTO.getNombreCapitulo());
		cronogramaObra.setValorPresupuesto(cronogramaObraDTO.getValorPresupuesto());
		cronogramaObra.setCostoTotal(cronogramaObraDTO.getCostoTotal());
		cronogramaObra.setPorcentajeDeAvance(cronogramaObraDTO.getPorcentajeDeAvance());
		cronogramaObra.setInvesionEjecutada(cronogramaObraDTO.getInvesionEjecutada());
		cronogramaObra.setInversionPoEjecutar(cronogramaObraDTO.getInversionPoEjecutar());
		cronogramaObra.setFechaInicio(cronogramaObraDTO.getFechaInicio());
		cronogramaObra.setFechaFin(cronogramaObraDTO.getFechaFin());
		cronogramaObra.setPorcentajeDeAvance1(cronogramaObraDTO.getPorcentajeDeAvance1());
		cronogramaObra.setPorcentajeDeAvance2(cronogramaObraDTO.getPorcentajeDeAvance2());
		cronogramaObra.setPorcentajeDeAvance3(cronogramaObraDTO.getPorcentajeDeAvance3());
		cronogramaObra.setPorcentajeDeAvance4(cronogramaObraDTO.getPorcentajeDeAvance4());
		cronogramaObra.setValorPresupuestoAumento(cronogramaObraDTO.getValorPresupuestoAumento());
		cronogramaObra.setFechaFinProrroga(cronogramaObraDTO.getFechaFinProrroga());
		cronogramaObra.setTipoCosto(cronogramaObraDTO.getTipoCosto());
		return cronogramaObra;
	}

	public CronogramaObraDTO escribirDTO(CronogramaObra cronogramaObra) {
		CronogramaObraDTO cronogramaObraDTO = new CronogramaObraDTO();
		cronogramaObraDTO.setAvaluoId(cronogramaObra.getAvaluo().getId());
		cronogramaObraDTO.setId(cronogramaObra.getId());
		cronogramaObraDTO.setNoDeCapitulo(cronogramaObra.getNoDeCapitulo());
		cronogramaObraDTO.setNombreCapitulo(cronogramaObra.getNombreCapitulo());
		cronogramaObraDTO.setValorPresupuesto(cronogramaObra.getValorPresupuesto());
		cronogramaObraDTO.setCostoTotal(cronogramaObra.getCostoTotal());
		cronogramaObraDTO.setPorcentajeDeAvance(cronogramaObra.getPorcentajeDeAvance());
		cronogramaObraDTO.setInvesionEjecutada(cronogramaObra.getInvesionEjecutada());
		cronogramaObraDTO.setInversionPoEjecutar(cronogramaObra.getInversionPoEjecutar());
		cronogramaObraDTO.setFechaInicio(cronogramaObra.getFechaInicio());
		cronogramaObraDTO.setFechaFin(cronogramaObra.getFechaFin());
		cronogramaObraDTO.setPorcentajeDeAvance1(cronogramaObra.getPorcentajeDeAvance1());
		cronogramaObraDTO.setPorcentajeDeAvance2(cronogramaObra.getPorcentajeDeAvance2());
		cronogramaObraDTO.setPorcentajeDeAvance3(cronogramaObra.getPorcentajeDeAvance3());
		cronogramaObraDTO.setPorcentajeDeAvance4(cronogramaObra.getPorcentajeDeAvance4());
		cronogramaObraDTO.setValorPresupuestoAumento(cronogramaObra.getValorPresupuestoAumento());
		cronogramaObraDTO.setFechaFinProrroga(cronogramaObra.getFechaFinProrroga());
		cronogramaObraDTO.setTipoCosto(cronogramaObra.getTipoCosto());
		cronogramaObraDTO.setAvaluoId(cronogramaObra.getAvaluo().getId());
		return cronogramaObraDTO;
	}

	public List<CronogramaObraDTO> escribirListaDTO(List<CronogramaObra> cronogramasObra) {
		List<CronogramaObraDTO> cronogramasObraDTO = new ArrayList<CronogramaObraDTO>();
		for (CronogramaObra cronogramaObra:cronogramasObra) {
			cronogramasObraDTO.add(escribirDTO(cronogramaObra));
		}
		return cronogramasObraDTO;
	}

	public Set<CronogramaObraDTO> escribirListaDTO(Set<CronogramaObra> cronogramasObra) {
		Set<CronogramaObraDTO> cronogramasObraDTO = new HashSet<CronogramaObraDTO>();
		for (CronogramaObra cronogramaObra:cronogramasObra) {
			cronogramasObraDTO.add(escribirDTO(cronogramaObra));
		}
		return cronogramasObraDTO;
	}

	public void actualizarCronogramaObra(Long id, CronogramaObraDTO actualizado) throws AvaluoNotFoundException {
		CronogramaObra cronogramaObra = cronogramaObraRepository.findOne(id);
		AvaluoConstructor avaluoConstructor = avaluoConstructorRepository.findOne(actualizado.getAvaluoId());
		if (avaluoConstructor == null) {
			throw new AvaluoNotFoundException();
		}
		cronogramaObra.setAvaluo(avaluoConstructor);
		cronogramaObra.setNoDeCapitulo(actualizado.getNoDeCapitulo());
		cronogramaObra.setNombreCapitulo(actualizado.getNombreCapitulo());
		cronogramaObra.setValorPresupuesto(actualizado.getValorPresupuesto());
		cronogramaObra.setCostoTotal(actualizado.getCostoTotal());
		cronogramaObra.setPorcentajeDeAvance(actualizado.getPorcentajeDeAvance());
		cronogramaObra.setInvesionEjecutada(actualizado.getInvesionEjecutada());
		cronogramaObra.setInversionPoEjecutar(actualizado.getInversionPoEjecutar());
		cronogramaObra.setFechaInicio(actualizado.getFechaInicio());
		cronogramaObra.setFechaFin(actualizado.getFechaFin());
		cronogramaObra.setPorcentajeDeAvance1(actualizado.getPorcentajeDeAvance1());
		cronogramaObra.setPorcentajeDeAvance2(actualizado.getPorcentajeDeAvance2());
		cronogramaObra.setPorcentajeDeAvance3(actualizado.getPorcentajeDeAvance3());
		cronogramaObra.setPorcentajeDeAvance4(actualizado.getPorcentajeDeAvance4());
		cronogramaObra.setValorPresupuestoAumento(actualizado.getValorPresupuestoAumento());
		cronogramaObra.setFechaFinProrroga(actualizado.getFechaFinProrroga());
		cronogramaObra.setTipoCosto(actualizado.getTipoCosto());
	}

}
