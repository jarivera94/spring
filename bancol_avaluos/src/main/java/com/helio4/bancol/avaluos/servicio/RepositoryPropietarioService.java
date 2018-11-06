package com.helio4.bancol.avaluos.servicio;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.helio4.bancol.avaluos.servicio.datos.PropietarioService;
import com.helio4.bancol.avaluos.servicio.excepciones.InmuebleNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.PropietarioNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.PropietarioDTO;
import com.helio4.bancol.avaluos.ensamblador.PropietarioEnsamblador;
import com.helio4.bancol.avaluos.modelo.Propietario;
import com.helio4.bancol.avaluos.modelo.Usuario.DocumentoIdentificacion;
import com.helio4.bancol.avaluos.persistencia.PropietarioRepository;

@Component(value="repositoryPropietarioService")
@Transactional(readOnly = true)
public class RepositoryPropietarioService implements PropietarioService {

	private static Logger log = LoggerFactory.getLogger( RepositoryPropietarioService.class );

	@Autowired
	private PropietarioRepository propietarioRepository;
	
	@Autowired
	private PropietarioEnsamblador propietarioEnsamblador;

	@Transactional
	@Override
	public PropietarioDTO crear(PropietarioDTO propietarioDTO) {
		log.debug("Creando un nuevo propietario {}",
                propietarioDTO);
		Propietario propietario;
		propietario = propietarioEnsamblador.crearPropietario(propietarioDTO);
		propietarioRepository.save(propietario);
		return propietarioDTO;
	}

	@Transactional(rollbackFor = PropietarioNotFoundException.class)
	@Override
	public PropietarioDTO eliminar(
            Integer tipoDocumentoIdentificacion, Long numeroDocumento)
    throws PropietarioNotFoundException {
		log.debug("Eliminando el propietario con id: {} {} ",
                tipoDocumentoIdentificacion, numeroDocumento);
		Propietario propietario = propietarioRepository.findOne(new DocumentoIdentificacion(tipoDocumentoIdentificacion, numeroDocumento));
		if (propietario == null) {
			throw new PropietarioNotFoundException();
		}
		propietarioRepository.delete(propietario);
		return propietarioEnsamblador.escribirDTO(propietario);
	}

	@Transactional(readOnly = true)
	@Override
	public List<PropietarioDTO> encontrarTodos() {
		log.debug("Encontrando todas las propietarios");
		return propietarioEnsamblador.escribirListaDTO(propietarioRepository.findAll());
	}

	@Transactional(readOnly = true)
	@Override
	public PropietarioDTO encontrarPorId(Integer tipoDocumentoIdentificacion, Long numeroDocumento) {
		return propietarioEnsamblador.escribirDTO(propietarioRepository.findOne(new DocumentoIdentificacion(tipoDocumentoIdentificacion, numeroDocumento)));
	}

	@Transactional(rollbackFor = PropietarioNotFoundException.class)
	@Override
	public PropietarioDTO actualizar(PropietarioDTO actualizado)
			throws PropietarioNotFoundException {
		try {
			propietarioEnsamblador.actualizarPropietario(actualizado.getTipoDocumentoIdentificacion(), actualizado.getNumeroDocumento(), actualizado);
		} catch (InmuebleNotFoundException e) {
			log.debug(
                    "InmuebleNotFoundException: no se encontró el inmueble asociado al propietario {}",
                    actualizado);
			return null;
		}
		return actualizado;
	}

	@Transactional(readOnly = true)
	@Override
	public Set<PropietarioDTO> encontrarPorInmueble(Long inmuebleId) {
		return new HashSet<PropietarioDTO>(propietarioEnsamblador.escribirListaDTO(propietarioRepository.encontrarPropietariosPorImueble(inmuebleId)));
	}

}
