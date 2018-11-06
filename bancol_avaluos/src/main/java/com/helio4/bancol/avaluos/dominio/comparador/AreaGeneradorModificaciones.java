package com.helio4.bancol.avaluos.dominio.comparador;

import java.util.ArrayList;
import java.util.Date;

import com.google.common.collect.ImmutableSet;
import com.helio4.bancol.avaluos.controlador.ListaDesplegable;
import com.helio4.bancol.avaluos.dto.AreaDTO;
import com.helio4.bancol.avaluos.dto.AreaDTO.UnidadDeMedida;
import com.helio4.bancol.avaluos.dto.ModificacionDTO;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase que se encarga de generar las modificaicones personalizadas
 * para campos de tipo DivipolaDTO.
 * */
public class AreaGeneradorModificaciones  extends AbstractGeneradorModificaciones{
	
    private static Logger log = LoggerFactory
            .getLogger(AreaGeneradorModificaciones.class);

	/**
	 * Constructor que recibe la información basica para generar
	 * la modificación.
	 * */
	public AreaGeneradorModificaciones(String campo, Object anterior, Object nuevo,
			Long avaluoId, UsuarioDTO usuario) {
		super(campo, anterior, nuevo, avaluoId, usuario);
		log.debug("Anterior: {}", String.valueOf(anterior));
		log.debug("Nuevo: {}", String.valueOf(nuevo));
		log.debug("Campo: {}", String.valueOf(campo));
		
	}
	/**
	 * Genera la modificación personalizada.
	 * */
	@Override
	public ModificacionDTO getModificacion() {
		if (anterior instanceof AreaDTO  ||  nuevo instanceof AreaDTO) {
			ModificacionDTO modificacionDTO = new ModificacionDTO();
			AreaDTO areaAnterior = (AreaDTO)anterior;	      	  	
			String campo = areaAnterior.getDescripcionNumerica().getKey() == AreaDTO.DescripcionAreaPH.Garaje.getKey() || 
					areaAnterior.getDescripcionNumerica().getKey() == AreaDTO.DescripcionAreaPH.Deposito.getKey()
					? areaAnterior.getDescripcion() + " " + areaAnterior.getNombre() + " " + this.campo
					: areaAnterior.getNombre() + " " + this.campo;
					
		    modificacionDTO.setCampo(campo);
		    modificacionDTO.setAvaluo(this.avaluoId);
		    modificacionDTO.setFecha(new Date());
		    modificacionDTO.setUsuario(this.usuario);
      	  	modificacionDTO.setAnterior(String.valueOf(anterior));
      	  	modificacionDTO.setNuevo(String.valueOf(nuevo));
      	  	return  modificacionDTO;
		} 
		else{
			return this.getNext().getModificacion();
		}
	}

}
