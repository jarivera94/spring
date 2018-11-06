package com.helio4.bancol.avaluos.servicio.datos;

import java.util.List;

import org.springframework.stereotype.Service;

import com.helio4.bancol.avaluos.dto.ObjetoAvaluoDTO;
import com.helio4.bancol.avaluos.dto.TipoInmuebleDTO;

@Service
public interface ListaService {
	
	public List<TipoInmuebleDTO> encontrarTiposInmueble();
	
	public TipoInmuebleDTO encontrarTipoInmueblePorId(Long id);

    public List<ObjetoAvaluoDTO> encontrarTodosObjetosAvaluo();

    public ObjetoAvaluoDTO encontrarObjetoAvaluoPorNombre(String nombre);
    
    public ObjetoAvaluoDTO encontrarObjetoAvaluoPorId(Long id);

}
