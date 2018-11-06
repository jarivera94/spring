package com.helio4.bancol.avaluos.servicio;

import java.util.ArrayList;
import java.util.List;

import com.helio4.bancol.avaluos.servicio.datos.TipoAvaluoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.TipoAvaluoDTO;
import com.helio4.bancol.avaluos.ensamblador.AvaluoEnsamblador;
import com.helio4.bancol.avaluos.modelo.TipoAvaluo;
import com.helio4.bancol.avaluos.persistencia.TipoAvaluoRepository;

@Component(value = "repositoryTipoAvaluoService")
@Transactional(readOnly = true)
public class RepositoryTipoAvaluoService implements TipoAvaluoService {

	private static Logger log = LoggerFactory.getLogger(RepositoryTipoAvaluoService.class);

	@Autowired
	private TipoAvaluoRepository tipoAvaluoRepository;

	@Autowired
	private AvaluoEnsamblador avaluoEnsamblador;

	@Transactional(readOnly = true)
	@Override
	public List<TipoAvaluoDTO> encontrarTodos() {
		log.debug("Encontrar todos los Tipos Avaluo");
		List<TipoAvaluoDTO> tipoAvaluoDTOs = new ArrayList<TipoAvaluoDTO>();
		for (TipoAvaluo tipoAvaluo : tipoAvaluoRepository.findAll()) {
			tipoAvaluoDTOs.add(avaluoEnsamblador.escribirDTO(tipoAvaluo));
		}
		return tipoAvaluoDTOs;
	}

	@Transactional(readOnly=true)
	@Override
	public TipoAvaluoDTO encontrarPorId(Long id) {
		TipoAvaluo tipoAvaluo = tipoAvaluoRepository.getOne(id);
		return avaluoEnsamblador.escribirDTO(tipoAvaluo);
	}

	@Transactional(readOnly=true)
	@Override
	public TipoAvaluoDTO encontrarPorNombre(String value) {
		return tipoAvaluoRepository.encontrarPorNombre(value);
	}

    @Transactional(readOnly=true)
	@Override
	public List<TipoAvaluoDTO> encontrarTiposAvaluo() {
		return tipoAvaluoRepository.encontrarTiposAvaluo();
	}
}
