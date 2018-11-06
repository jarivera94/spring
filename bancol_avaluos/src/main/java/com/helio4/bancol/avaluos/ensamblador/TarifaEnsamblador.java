package com.helio4.bancol.avaluos.ensamblador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.TarifaDTO;
import com.helio4.bancol.avaluos.modelo.Entidad;
import com.helio4.bancol.avaluos.modelo.Tarifa;
import com.helio4.bancol.avaluos.modelo.TipoAvaluo;
import com.helio4.bancol.avaluos.persistencia.EntidadRepository;
import com.helio4.bancol.avaluos.persistencia.TarifaRepository;
import com.helio4.bancol.avaluos.persistencia.TipoAvaluoRepository;
import com.helio4.bancol.avaluos.servicio.excepciones.EntidadNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.TarifaNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.TipoAvaluoNotFoundException;

@Component
public class TarifaEnsamblador {

	@Autowired
	private TarifaRepository tarifaRepository;

	@Autowired
	private EntidadRepository entidadRepository;
	
	@Autowired
	private TipoAvaluoRepository tipoAvaluoRepository;
	
	public Tarifa createTarifa(TarifaDTO tarifaDTO, Long idEntidad) throws TipoAvaluoNotFoundException, EntidadNotFoundException {
		Tarifa tarifa = new Tarifa();		
		tarifa.setTarifa(tarifaDTO.getTarifa());
		tarifa.setValorMinimo(tarifaDTO.getValorMinimo());
		tarifa.setValorMaximo(tarifaDTO.getValorMaximo());	
		TipoAvaluo tipoAvaluo = tipoAvaluoRepository.findOne(tarifaDTO.getTipoAvaluoId());
		if (tipoAvaluo == null) {
			throw new TipoAvaluoNotFoundException();
		}
		tarifa.setTipoAvaluo(tipoAvaluo);		
		Entidad entidad = entidadRepository.findOne(idEntidad);
		
		if (entidad == null) {
			throw new EntidadNotFoundException();
		}
		tarifa.setEntidad(entidad);
		if (tarifaDTO.getPorcentajePerito() != null) {
			tarifa.setPorcentajePerito(tarifaDTO.getPorcentajePerito());
		}
		this.tarifaRepository.save(tarifa);
		return tarifa;
	}

    public TarifaDTO escribirDTO(Tarifa tarifa) {
        TarifaDTO tarifaDTO = new TarifaDTO();
        tarifaDTO.setId(tarifa.getId());
        tarifaDTO.setValorMinimo(tarifa.getValorMinimo());
        tarifaDTO.setValorMaximo(tarifa.getValorMaximo());
        tarifaDTO.setTarifa(tarifa.getTarifa());
        tarifaDTO.setPorcentajePerito(tarifa.getPorcentajePerito());
        if (tarifa.getEntidad() != null) {
            tarifaDTO.setEntidadId(tarifa.getEntidad().getId());
        }
        if (tarifa.getTipoAvaluo() != null) {
            tarifaDTO.setTipoAvaluoId(tarifa.getTipoAvaluo().getId());
            tarifaDTO.setNombreTipoAvaluo(tarifa.getTipoAvaluo().getNombre());
        }
        return tarifaDTO;
    }
	
	public Tarifa updateTarifa(TarifaDTO tarifaDTO) throws TarifaNotFoundException, TipoAvaluoNotFoundException, EntidadNotFoundException {		
		Tarifa tarifa = new Tarifa();
		tarifa = tarifaRepository.findOne(tarifaDTO.getId());
		if (tarifa == null) {
			throw new TarifaNotFoundException();
		}
		tarifa.setTarifa(tarifaDTO.getTarifa());
		tarifa.setValorMinimo(tarifaDTO.getValorMinimo());
		tarifa.setValorMaximo(tarifaDTO.getValorMaximo());
		tarifa.setPorcentajePerito(tarifaDTO.getPorcentajePerito());
		TipoAvaluo tipoAvaluo = tipoAvaluoRepository.findOne(tarifaDTO.getTipoAvaluoId());
		if (tipoAvaluo == null) {
			throw new TipoAvaluoNotFoundException();
		}
		tarifa.setTipoAvaluo(tipoAvaluo);		
		Entidad entidad = entidadRepository.findOne(tarifaDTO.getEntidadId());
		if (entidad == null) {
			throw new EntidadNotFoundException();
		}
		tarifa.setEntidad(entidad);

		return tarifa;
	}
}
