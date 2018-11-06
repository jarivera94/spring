package com.helio4.bancol.avaluos.dominio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.SucursalDTO;
import com.helio4.bancol.avaluos.servicio.excepciones.EntidadNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.SucursalNotFoundException;
import com.helio4.bancol.avaluos.servicio.datos.SucursalService;
import java.io.Serializable;

@Component
@Scope("session")
public class SucursalesController  implements Serializable {
	
	/**
	 * Instancia Services Interface.
	 */
	@Autowired
	@Qualifier("repositorySucursalService")
	private SucursalService sucursalService;
	
	public Boolean crearSucursal(SucursalDTO sucursalDTO) throws SucursalNotFoundException{
		return sucursalService.crearSucursal(sucursalDTO);
	}

	public Boolean actualizarTodo(SucursalDTO sucursalDTO) throws SucursalNotFoundException,  EntidadNotFoundException{
		return sucursalService.actualizarTodo(sucursalDTO);
	}
	
	public SucursalDTO eliminarSucursal(SucursalDTO sucursalDTO) throws SucursalNotFoundException{
		return sucursalService.eliminarSucursal(sucursalDTO);
	}
	
	public List<SucursalDTO> encontrarTodos(){
		return sucursalService.encontrarTodos();
	}
}
