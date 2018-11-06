package com.helio4.bancol.avaluos.servicio;

import java.util.List;

import com.helio4.bancol.avaluos.servicio.datos.ParametroService;
import com.helio4.bancol.avaluos.servicio.excepciones.ParametroNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.ParametroDTO;
import com.helio4.bancol.avaluos.ensamblador.ParametroEnsamblador;
import com.helio4.bancol.avaluos.modelo.Parametro;
import com.helio4.bancol.avaluos.persistencia.ParametroRepository;

@Component(value="repositoryParametroService")
@Transactional(readOnly = true)
public class RepositoryParametroService implements ParametroService {

	private static Logger log = LoggerFactory.getLogger( RepositoryParametroService.class );

	@Autowired
	private ParametroRepository parametroRepository;
	
	@Autowired
	private ParametroEnsamblador parametroEnsamblador;

	@Transactional
	@Override
	public ParametroDTO crear(ParametroDTO parametroDTO) {
        log.debug("Creando un nuevo detalle de parametro {}",
                parametroDTO);
		Parametro parametro = parametroEnsamblador.crearParametro(parametroDTO);
		parametro = parametroRepository.save(parametro);
		parametroDTO.setId(parametro.getId());
		return parametroDTO;
	}

	@Transactional(rollbackFor = ParametroNotFoundException.class)
	@Override
	public ParametroDTO eliminar(Long parametroId) throws ParametroNotFoundException {
        log.debug("Eliminando el parametro con id: {}",
                parametroId);
		Parametro parametro = parametroRepository.findOne(parametroId);
		if (parametro == null) {
			throw new ParametroNotFoundException();
		}
		parametroRepository.delete(parametro);
		return parametroEnsamblador.escribirDTO(parametro);
	}

	@Transactional(readOnly = true)
	@Override
	public List<ParametroDTO> encontrarTodos() {
		log.debug("Encontrando todas las parametros");
		return parametroEnsamblador.escribirListaParametros(parametroRepository.findAll());
	}

	@Transactional(readOnly = true)
	@Override
	public ParametroDTO encontrarPorId(Long id) {
		return parametroEnsamblador.escribirDTO(parametroRepository.findOne(id));
	}

	@Transactional(rollbackFor = ParametroNotFoundException.class)
	@Override
	public ParametroDTO actualizar(ParametroDTO actualizado)
			throws ParametroNotFoundException {
		parametroEnsamblador.actualizarParametro(actualizado.getId(), actualizado);
		return actualizado;
	}

	@Transactional(readOnly = true)
	@Override
	public ParametroDTO encontrarPorNombre(String nombre) {
		return parametroEnsamblador.escribirDTO(parametroRepository.encontrarPorNombre(nombre));
	}

}
