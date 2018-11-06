package com.helio4.bancol.avaluos.ensamblador;

import com.helio4.bancol.avaluos.dto.EstadoAvaluoDTO;
import com.helio4.bancol.avaluos.modelo.EstadoAvaluo;
import com.helio4.bancol.avaluos.persistencia.AvaluoRepository;
import com.helio4.bancol.avaluos.persistencia.EstadoAvaluoRepository;
import com.helio4.bancol.avaluos.persistencia.UsuarioRepository;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.EstadoAvaluoNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.UsuarioNotFoundException;
import com.helio4.bancol.avaluos.servicio.util.Constantes.Estado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EstadoAvaluoEnsamblador {

	@Autowired
	private EstadoAvaluoRepository estadoAvaluoRepository;

	@Autowired
	private AvaluoRepository avaluoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public void actualizarEstadoAvaluo(Long estadoAvaluoId, EstadoAvaluoDTO estadoAvaluoDTO)
		throws EstadoAvaluoNotFoundException, AvaluoNotFoundException, UsuarioNotFoundException {
		EstadoAvaluo estadoAvaluo = estadoAvaluoRepository.findOne(estadoAvaluoId);
		if (estadoAvaluo == null) {
			throw new EstadoAvaluoNotFoundException();
		}
        /**
         * El unico dato que puede ser actualizado por el usuario es la fecha
         */
		estadoAvaluo.setFechaEstado(estadoAvaluoDTO.getFechaEstado());
		estadoAvaluo.setEstado(estadoAvaluoDTO.getEstado().key());
	}
	
	public EstadoAvaluoDTO escribirDTO(EstadoAvaluo estadoAvaluo) {
		if (estadoAvaluo == null) {
			return null;
		}
		EstadoAvaluoDTO estadoAvaluoDTO = new EstadoAvaluoDTO();
		estadoAvaluoDTO.setId(estadoAvaluo.getId());
		estadoAvaluoDTO.setFechaEstado(estadoAvaluo.getFechaEstado());
		estadoAvaluoDTO.setEstado(Estado.fromClass(estadoAvaluo.getClass()));
		if( estadoAvaluo.getPerito() != null ){
			estadoAvaluoDTO.setTipoDocumentoPerito(estadoAvaluo.getPerito().getTipoDocumentoIdentificacion());
            estadoAvaluoDTO.setNumeroDocumentoPerito(estadoAvaluo.getPerito().getNumeroDocumento());
		}
		
		estadoAvaluoDTO.setAvaluoId(estadoAvaluo.getAvaluo().getId());
		estadoAvaluoDTO.setJustificacionRechazo(estadoAvaluo.getJustificacionRechazo());
		estadoAvaluoDTO.setUsuarioNombres(estadoAvaluo.getUsuario().getNombres());
		estadoAvaluoDTO.setUsuarioApellidos(estadoAvaluo.getUsuario().getApellidos());
        estadoAvaluoDTO.setUsuarioRol(estadoAvaluo.getUsuario().getRol().getNombre());
		return estadoAvaluoDTO;
	}
	
	public List<EstadoAvaluoDTO> escribirListaDTO(List<EstadoAvaluo> estadosAvaluo) {
		List<EstadoAvaluoDTO> estadoAvaluoDTOs = new ArrayList<EstadoAvaluoDTO>();
		for (EstadoAvaluo estadoAvaluo:estadosAvaluo) {
			estadoAvaluoDTOs.add(escribirDTO(estadoAvaluo));
		}
		return estadoAvaluoDTOs;
	}

}
