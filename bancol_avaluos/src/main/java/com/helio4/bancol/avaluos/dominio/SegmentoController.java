package com.helio4.bancol.avaluos.dominio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.SegmentoDTO;
import com.helio4.bancol.avaluos.servicio.excepciones.EntidadNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.SegmentoNotFoundException;
import com.helio4.bancol.avaluos.servicio.datos.SegmentoService;
import java.io.Serializable;

@Component
@Scope("session")
public class SegmentoController  implements Serializable {

	/**
	 * Instancia Services Interface.
	 */
	
	@Autowired
	@Qualifier("repositorySegmentoService")
	private SegmentoService segmentoService;
	
	public Boolean crearSegmento(SegmentoDTO segmentoDTO) throws EntidadNotFoundException{
		return segmentoService.crearSegmento(segmentoDTO);
	}
	
	public Boolean actualizarTodo(SegmentoDTO segmentoDTO) throws SegmentoNotFoundException{
		return segmentoService.actualizarTodo(segmentoDTO);
	}
	
	public List<SegmentoDTO> encontrarTodos(){
		return segmentoService.encontrarTodos();
	}
	
	public SegmentoDTO eliminarSegmento(SegmentoDTO segmentoDTO) throws SegmentoNotFoundException{
		return segmentoService.eliminarSegmento(segmentoDTO);
	}
}
