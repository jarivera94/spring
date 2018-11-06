package com.helio4.bancol.avaluos.ensamblador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.PropietarioDTO;
import com.helio4.bancol.avaluos.modelo.Propietario;
import com.helio4.bancol.avaluos.modelo.Usuario.DocumentoIdentificacion;
import com.helio4.bancol.avaluos.persistencia.PropietarioRepository;
import com.helio4.bancol.avaluos.servicio.excepciones.InmuebleNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.PropietarioNotFoundException;

@Component
public class PropietarioEnsamblador {
	
	@Autowired
	private PropietarioRepository propietarioRepository;

	public Propietario crearPropietario(PropietarioDTO propietarioDTO) {
		Propietario propietario = new Propietario();
		propietario.setTipoDocumentoIdentificacion(propietarioDTO.getTipoDocumentoIdentificacion());
		propietario.setNumeroDocumento(propietarioDTO.getNumeroDocumento());
		propietario.setNombresApellidosPropietario(propietarioDTO.getNombresApellidosPropietario());
		propietario.setPorcentajeDePropiedad(propietarioDTO.getPorcentajeDePropiedad());
		return propietario;
	}

	public void actualizarPropietario(Integer tipoDocumentoIdentificacion, Long numeroDocumento, PropietarioDTO propietarioDTO)
			throws PropietarioNotFoundException, InmuebleNotFoundException {
		Propietario propietario = propietarioRepository.findOne(new DocumentoIdentificacion(tipoDocumentoIdentificacion, numeroDocumento));
		if (propietario == null) {
			throw new PropietarioNotFoundException();
		}
		propietario.setTipoDocumentoIdentificacion(propietarioDTO.getTipoDocumentoIdentificacion());
		propietario.setNumeroDocumento(propietarioDTO.getNumeroDocumento());
		propietario.setNombresApellidosPropietario(propietarioDTO.getNombresApellidosPropietario());
		propietario.setPorcentajeDePropiedad(propietarioDTO.getPorcentajeDePropiedad());
	}

	public PropietarioDTO escribirDTO(Propietario propietario) {
		if (propietario == null) {
			return null;
		}
		PropietarioDTO propietarioDTO = new PropietarioDTO();
		propietarioDTO.setTipoDocumentoIdentificacion(propietario.getId().getTipoDocumentoIdentificacion());
		propietarioDTO.setNumeroDocumento(propietario.getId().getNumeroDocumento());
		propietarioDTO.setNombresApellidosPropietario(propietario.getNombresApellidosPropietario());
		propietarioDTO.setPorcentajeDePropiedad(propietario.getPorcentajeDePropiedad());
		return propietarioDTO;
	}
	
	public List<PropietarioDTO> escribirListaDTO(List<Propietario> propietarios) {
		List<PropietarioDTO> propietarioDTOs = new ArrayList<PropietarioDTO>();
		for (Propietario propietario:propietarios) {
			propietarioDTOs.add(escribirDTO(propietario));
		}
		return propietarioDTOs;
	}

}
