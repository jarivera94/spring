package com.helio4.bancol.avaluos.servicio;

import java.util.List;

import com.helio4.bancol.avaluos.servicio.datos.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.modelo.Pais;
import com.helio4.bancol.avaluos.persistencia.PaisRepository;

@Component(value="repositoryPaisService")
@Transactional(readOnly = true)
public class RepositoryPaisService implements PaisService {

	@Autowired
	private PaisRepository paisRepository;

	@Transactional(readOnly = true)
	@Override
	public List<Pais> encontrarTodos() {
		return paisRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Pais encontrarPorId(String id) {
		return paisRepository.findOne(id);
	}

}
