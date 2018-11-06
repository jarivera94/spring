package com.helio4.bancol.avaluos.servicio;

import java.util.ArrayList;
import java.util.List;

import com.helio4.bancol.avaluos.servicio.datos.ListaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.TipoInmuebleDTO;
import com.helio4.bancol.avaluos.dto.ObjetoAvaluoDTO;
import com.helio4.bancol.avaluos.ensamblador.AvaluoEnsamblador;
import com.helio4.bancol.avaluos.modelo.TipoInmueble;
import com.helio4.bancol.avaluos.persistencia.ListaRepository;

@Component(value = "repositoryListaService")
@Transactional(readOnly = true)
public class RepositoryListaService implements ListaService {
	
	@Autowired
	private ListaRepository listaRepository;
	
	@Autowired
	private AvaluoEnsamblador avaluoEnsamblador;

    @Transactional(readOnly = true)
	@Override
	public List<TipoInmuebleDTO> encontrarTiposInmueble() {
		List<TipoInmuebleDTO> tiposInmueble = new ArrayList<TipoInmuebleDTO>();
		
		for(TipoInmueble tipoInmueble : listaRepository.encontrarTiposInmueble()){
			tiposInmueble.add(avaluoEnsamblador.escribirDTO(tipoInmueble));
		}
		
		return tiposInmueble;
	}

    @Transactional(readOnly = true)
	@Override
	public TipoInmuebleDTO encontrarTipoInmueblePorId(Long id) {
		TipoInmueble tipoInmueble = listaRepository.encontrarTipoInmueblePorId(id);
		return avaluoEnsamblador.escribirDTO(tipoInmueble);
	}

    @Transactional(readOnly = true)
	@Override
    public List<ObjetoAvaluoDTO> encontrarTodosObjetosAvaluo() {
        return listaRepository.encontrarTodosObjetosAvaluo();
    }

    @Transactional(readOnly = true)
	@Override
    public ObjetoAvaluoDTO encontrarObjetoAvaluoPorNombre(String nombre) {
        return listaRepository.encontrarObjetoAvaluoPorNombre(nombre);
    }
    
    @Transactional(readOnly = true)
	@Override
    public ObjetoAvaluoDTO encontrarObjetoAvaluoPorId(Long id) {
        return listaRepository.encontrarObjetoAvaluoPorId(id);
    }
}
