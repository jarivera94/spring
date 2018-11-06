package com.helio4.bancol.avaluos.dominio;

import java.util.List;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.EntidadDTO;
import com.helio4.bancol.avaluos.servicio.excepciones.EntidadNotFoundException;
import com.helio4.bancol.avaluos.servicio.datos.EntidadService;
import java.io.Serializable;

@Component
@Scope("session")
public class EntidadesController implements Serializable {
	
	/**
	 * Instancia Services Interface.
	 */
	@Autowired
	@Qualifier("repositoryEntidadService")
	private EntidadService entidadService;	
		
	public TreeMap<String, String> paises() {
		
		/**
		TreeMap<String, String> paises = new TreeMap<String, String>();
		for (Pais pais : paisService.encontrarTodos()) {
			paises.put(pais.getName(), pais.getId());
		}
		*/
		return null;
	}
	
	public Boolean crearEntidad(EntidadDTO entidadDTO) throws EntidadNotFoundException{
		return entidadService.crearEntidad(entidadDTO);
		
	}
	
	public Boolean actualizarTodo(EntidadDTO entidadDTO) throws EntidadNotFoundException{
		return entidadService.actualizarTodo(entidadDTO);
	}
	
	public EntidadDTO eliminarEntidad(EntidadDTO entidadDTO) throws EntidadNotFoundException{
		return entidadService.eliminarEntidad(entidadDTO);
	}
	
	public List<EntidadDTO> encontrarTodos(){
		return entidadService.encontrarTodos();
	}
}
