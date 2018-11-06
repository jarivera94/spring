package com.helio4.bancol.avaluos.dominio.comparador;

import java.util.Date;

import com.helio4.bancol.avaluos.dto.ModificacionDTO;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;

/**
 * Clase que se encarga de generar las modificaicones para los campos
 * que no requiren personalizacion.
 * */
public class DefaultGeneradorModificaciones extends AbstractGeneradorModificaciones {
	
	/**
	 * Constructor que recibe la información basica para generar
	 * la modificación.
	 * */
	public DefaultGeneradorModificaciones(String campo, Object anterior, Object nuevo,
			Long avaluoId, UsuarioDTO usuario) {
		super(campo, anterior, nuevo, avaluoId, usuario);
	}
	/**
	 * Genera la modificación personalizada.
	 * */
	@Override
	public ModificacionDTO getModificacion() {
		ModificacionDTO modificacionDTO = new ModificacionDTO();
	    modificacionDTO.setCampo(campo);
	    modificacionDTO.setAvaluo(this.avaluoId);
	    modificacionDTO.setFecha(new Date());
	    modificacionDTO.setUsuario(this.usuario);
        modificacionDTO.setAnterior( anterior != null ? String.valueOf(anterior) : "");
        modificacionDTO.setNuevo( nuevo != null ? String.valueOf(nuevo) : ""); 
        return  modificacionDTO;
	}

}
