package com.helio4.bancol.avaluos.dominio.comparador;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.helio4.bancol.avaluos.dto.ModificacionDTO;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;
/**
 * Clase que se encarga de generar las modificaicones personalizadas
 * para campos de tipo Date.
 * */
public class DateGeneradorModificaciones extends AbstractGeneradorModificaciones {
	
	/**
	 * Constructor que recibe la información basica para generar
	 * la modificación.
	 * */
	public DateGeneradorModificaciones(String campo, Object anterior, Object nuevo,
			Long avaluoId, UsuarioDTO usuario) {
		super(campo, anterior, nuevo, avaluoId, usuario);
	}
	/**
	 * Genera la modificación personalizada.
	 * */
	@Override
	public ModificacionDTO getModificacion() {
		if ((anterior instanceof Date) || (nuevo instanceof Date)) {
			ModificacionDTO modificacionDTO = new ModificacionDTO();
		    modificacionDTO.setCampo(campo);
		    modificacionDTO.setAvaluo(this.avaluoId);
		    modificacionDTO.setFecha(new Date());
		    modificacionDTO.setUsuario(this.usuario);
		    anterior = (anterior instanceof Date) ? new SimpleDateFormat("yyyy/MM/dd").format(anterior) : "";
		    nuevo = (nuevo instanceof Date) ? new SimpleDateFormat("yyyy/MM/dd").format(nuevo) : "";
		    modificacionDTO.setAnterior( anterior != null ? anterior.toString(): "");
		    modificacionDTO.setNuevo(nuevo!=null?nuevo.toString():"");
	        return  modificacionDTO;
		} 
		else{
			return this.getNext().getModificacion();
		}
	}

}
