package com.helio4.bancol.avaluos.controlador.soporte;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.helio4.bancol.avaluos.dominio.UsuarioController;
import com.helio4.bancol.avaluos.dto.EntidadDTO;

public class GeneralSoporte implements Serializable {

	private static Logger log = LoggerFactory.getLogger(GeneralSoporte.class);

	private static final long serialVersionUID = 9076632279744143935L;

	protected List<EntidadDTO> entidades;
	
	protected EntidadDTO entidadSeleccionada;
	protected String codigoExternoSeleccionado;
	
	@Autowired
	private UsuarioController usuarioController;
	
	public void inicializar() {
		
		entidades = usuarioController.encontrarEntidades();
		
	}

}
