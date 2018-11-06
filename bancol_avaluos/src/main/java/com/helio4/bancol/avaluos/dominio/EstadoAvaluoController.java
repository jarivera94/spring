package com.helio4.bancol.avaluos.dominio;

import java.util.List;

import com.helio4.bancol.avaluos.servicio.excepciones.EstadoAvaluoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.EstadoAvaluoDTO;
import com.helio4.bancol.avaluos.servicio.datos.EstadoAvaluoService;

@Component
public class EstadoAvaluoController {
	
	@Autowired
	@Qualifier("repositoryEstadoAvaluoService")
	private EstadoAvaluoService estadoAvaluoService;
	
	public List<EstadoAvaluoDTO> buscarEstadosAvaluo(Long id){
		return estadoAvaluoService.encontrarPorAvaluo(id);
	}
	
	public String buscarUltimasCorreccionesSolicitadas(Long id){
		return estadoAvaluoService.buscarUltimasCorreccionesSolicitadas(id); 
	}

	public EstadoAvaluoDTO buscarPorCodigoExterno(String codigoExterno) {
		return estadoAvaluoService.buscarPor(codigoExterno);
	}
	
	public EstadoAvaluoDTO buscarEstadoActualPorCodigoExterno(String codigoExterno, Integer codigoEntidad) {
		return estadoAvaluoService.buscarEstadoActualPorCodigoExterno(codigoExterno,  codigoEntidad);
	}

    public void actualizar(EstadoAvaluoDTO estadoAvaluoDTO)
            throws EstadoAvaluoNotFoundException {
        estadoAvaluoService.actualizar(estadoAvaluoDTO);
    }
}
