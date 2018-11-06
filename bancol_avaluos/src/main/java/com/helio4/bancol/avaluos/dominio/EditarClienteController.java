package com.helio4.bancol.avaluos.dominio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.ClienteDTO;
import com.helio4.bancol.avaluos.servicio.excepciones.ClienteNotFoundException;
import com.helio4.bancol.avaluos.servicio.datos.ClienteService;
import com.helio4.bancol.avaluos.servicio.excepciones.EntidadNotFoundException;

@Component
public class EditarClienteController {
	
	@Autowired
	private ClienteService clienteService;
		
	/**
	 * Metodo que obtiene el cliente que se desea
	 * editar.
	 * 
	 * */
	public ClienteDTO buscarCliente(int tipoDocumentoIdentificacion, Long numeroDocumento) {
		return this.clienteService.encontrarPorId(tipoDocumentoIdentificacion, numeroDocumento);
	}
	/**
	 * 1. Guarda la informacion del cliente
	 * 2. Si el cliente aparece en registros de avaluos aprobados 
	 *    se generan las respectivas modificaciones.
	 * 3. Una vez generadas las modificaciones se genera el pdf 
	 * 	  con las respectivas modificaicones
	 * 
	 * @param usuario en el sistema que realiza la modificacion del cliente
	 * @param original cliente original
	 * @param actualizado cliente con la nueva informacion
	 * @param cliente actualizado 
	 * */
	public ClienteDTO guardar(ClienteDTO original,ClienteDTO actualizado) {
		try {
			if(!original.getNumeroDocumento().equals(actualizado.getNumeroDocumento()) ) {
				this.clienteService.actualizar( 
						actualizado.getTipoDocumentoIdentificacion(),
						actualizado.getNumeroDocumento(), 
						original.getTipoDocumentoIdentificacion(),
						original.getNumeroDocumento());
				 this.clienteService.encontrarPorNumeroDocumento(actualizado.getTipoDocumentoIdentificacion(),actualizado.getNumeroDocumento());
			}
			this.clienteService.actualizar(actualizado);
		} catch (ClienteNotFoundException | EntidadNotFoundException e) {
			e.printStackTrace();
		}
		return actualizado;
	}
	
	public ClienteDTO actualizar(ClienteDTO actualizado) { 
		try {
			this.clienteService.actualizar(actualizado);
		} catch (ClienteNotFoundException | EntidadNotFoundException e) {
			e.printStackTrace();
		}
		return actualizado;
	}
	
	public ClienteDTO crear(ClienteDTO clienteDTO) {
		try {
			return this.clienteService.crear(clienteDTO);
		} catch (EntidadNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
