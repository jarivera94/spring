package com.helio4.bancol.avaluos.ensamblador;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.InmuebleDTO;
import com.helio4.bancol.avaluos.dto.PropietarioDTO;
import com.helio4.bancol.avaluos.modelo.AvaluoComercial;
import com.helio4.bancol.avaluos.modelo.Inmueble;
import com.helio4.bancol.avaluos.modelo.Propietario;
import com.helio4.bancol.avaluos.modelo.Usuario.DocumentoIdentificacion;
import com.helio4.bancol.avaluos.persistencia.AvaluoComercialRepository;
import com.helio4.bancol.avaluos.persistencia.InmuebleRepository;
import com.helio4.bancol.avaluos.persistencia.PropietarioRepository;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.InmuebleNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.PropietarioNotFoundException;

@Component
public class InmuebleEnsamblador {

	@Autowired
	private InmuebleRepository inmuebleRepository;
	
	@Autowired
	private PropietarioRepository propietarioRepository;
	
	@Autowired
	private AvaluoComercialRepository avaluoComercialRepository;
	
	@Autowired
	private PropietarioEnsamblador propietarioEnsamblador;

	public Inmueble crearInmueble(InmuebleDTO inmuebleDTO) throws AvaluoNotFoundException, PropietarioNotFoundException {
		Inmueble inmueble = new Inmueble();
		inmueble.setMatriculaInmobiliaria(inmuebleDTO.getMatriculaInmobiliaria());
		inmueble.setCedulaCatastral(inmuebleDTO.getCedulaCatastral());
		inmueble.setNumeroDeEscritura(inmuebleDTO.getNumeroDeEscritura());
		inmueble.setChip(inmuebleDTO.getChip());
		inmueble.setNumeroDeNotariaEscritura(inmuebleDTO.getNumeroDeNotariaEscritura());
		inmueble.setDepartamentoEscritura(inmuebleDTO.getDepartamentoEscritura());
		inmueble.setMunicipioEscritura(inmuebleDTO.getMunicipioEscritura());
		inmueble.setFechaEscritura(inmuebleDTO.getFechaEscritura());
		inmueble.setLatitud(inmuebleDTO.getLatitud());
		inmueble.setLongitud(inmuebleDTO.getLongitud());
		AvaluoComercial avaluo = avaluoComercialRepository.findOne(inmuebleDTO.getAvaluoId());
		if (avaluo == null) {
			throw new AvaluoNotFoundException();
		}
		inmueble.setPropietarios(new HashSet<Propietario>());
		for (PropietarioDTO propietarioDTO:inmuebleDTO.getPropietarios()) {
			Propietario propietario = propietarioRepository.findOne(new DocumentoIdentificacion(propietarioDTO.getTipoDocumentoIdentificacion(), propietarioDTO.getNumeroDocumento()));
			if (propietario == null) {
				throw new PropietarioNotFoundException();
			}
			inmueble.getPropietarios().add(propietario);
		}
		inmueble.setAvaluo(avaluo);
		return inmueble;
	}

	public void actualizarInmueble(Long inmuebleId, InmuebleDTO inmuebleDTO)
			throws InmuebleNotFoundException, AvaluoNotFoundException, PropietarioNotFoundException {
		Inmueble inmueble = inmuebleRepository.findOne(inmuebleId);
		if (inmueble == null) {
			throw new InmuebleNotFoundException();
		}
		inmueble.setMatriculaInmobiliaria(inmuebleDTO.getMatriculaInmobiliaria());
		inmueble.setCedulaCatastral(inmuebleDTO.getCedulaCatastral());
		inmueble.setNumeroDeEscritura(inmuebleDTO.getNumeroDeEscritura());
		inmueble.setChip(inmuebleDTO.getChip());
		inmueble.setNumeroDeNotariaEscritura(inmuebleDTO.getNumeroDeNotariaEscritura());
		inmueble.setDepartamentoEscritura(inmuebleDTO.getDepartamentoEscritura());
		inmueble.setMunicipioEscritura(inmuebleDTO.getMunicipioEscritura());
		inmueble.setFechaEscritura(inmuebleDTO.getFechaEscritura());
		inmueble.setLatitud(inmuebleDTO.getLatitud());
		inmueble.setLongitud(inmuebleDTO.getLongitud());
		AvaluoComercial avaluo = avaluoComercialRepository.findOne(inmuebleDTO.getAvaluoId());
		if (avaluo == null) {
			throw new AvaluoNotFoundException();
		}
		inmueble.setPropietarios(new HashSet<Propietario>());
		for (PropietarioDTO propietarioDTO:inmuebleDTO.getPropietarios()) {
			Propietario propietario = propietarioRepository.findOne(new DocumentoIdentificacion(propietarioDTO.getTipoDocumentoIdentificacion(), propietarioDTO.getNumeroDocumento()));
			if (propietario == null) {
				throw new PropietarioNotFoundException();
			}
			inmueble.getPropietarios().add(propietario);
		}
		inmueble.setAvaluo(avaluo);
	}

	public InmuebleDTO escribirDTO(Inmueble inmueble) {
		InmuebleDTO inmuebleDTO = new InmuebleDTO();
		inmuebleDTO.setId(inmueble.getId());
		inmuebleDTO.setMatriculaInmobiliaria(inmueble.getMatriculaInmobiliaria());
		inmuebleDTO.setCedulaCatastral(inmueble.getCedulaCatastral());
		inmuebleDTO.setNumeroDeEscritura(inmueble.getNumeroDeEscritura());
		inmuebleDTO.setChip(inmueble.getChip());
		inmuebleDTO.setNumeroDeNotariaEscritura(inmueble.getNumeroDeNotariaEscritura());
		inmuebleDTO.setDepartamentoEscritura(inmueble.getDepartamentoEscritura());
		inmuebleDTO.setMunicipioEscritura(inmueble.getMunicipioEscritura());
		inmuebleDTO.setFechaEscritura(inmueble.getFechaEscritura());
		inmuebleDTO.setLatitud(inmueble.getLatitud());
		inmuebleDTO.setLongitud(inmueble.getLongitud());
		inmuebleDTO.setAvaluoId(inmueble.getAvaluo().getId());
		escribirPropietarios(inmuebleDTO, inmueble.getPropietarios());
		return inmuebleDTO;
	}
	
	private void escribirPropietarios(InmuebleDTO inmuebleDTO,
			Set<Propietario> propietarios) {
		Set<PropietarioDTO> propietariosDTO = new HashSet<PropietarioDTO>();
		for (Propietario propietario:propietarios) {
			propietariosDTO.add(propietarioEnsamblador.escribirDTO(propietario));
		}
		inmuebleDTO.setPropietarios(propietariosDTO);
	}

	public List<InmuebleDTO> escribirListaDTO(List<Inmueble> inmuebles) {
		List<InmuebleDTO> inmuebleDTOs = new ArrayList<InmuebleDTO>();
		for (Inmueble inmueble:inmuebles) {
			inmuebleDTOs.add(escribirDTO(inmueble));
		}
		return inmuebleDTOs;
	}
	
	public Set<InmuebleDTO> escribirListaDTO(Set<Inmueble> inmuebles) {
		Set<InmuebleDTO> inmuebleDTOs = new HashSet<InmuebleDTO>();
		for (Inmueble inmueble:inmuebles) {
			inmuebleDTOs.add(escribirDTO(inmueble));
		}
		return inmuebleDTOs;
	}

}
