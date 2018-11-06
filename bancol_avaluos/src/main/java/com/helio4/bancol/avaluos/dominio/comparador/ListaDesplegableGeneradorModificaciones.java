package com.helio4.bancol.avaluos.dominio.comparador;

import java.util.Date;

import com.helio4.bancol.avaluos.controlador.ListaDesplegable;
import com.helio4.bancol.avaluos.dto.ModificacionDTO;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;

/**
 * Clase que se encarga de generar las modificaicones personalizadas
 * para campos de tipo ListaDesplegable
 * */
public class ListaDesplegableGeneradorModificaciones extends AbstractGeneradorModificaciones{
	
	/**
	 * Constructor que recibe la información basica para generar
	 * la modificación.
	 * */
	public ListaDesplegableGeneradorModificaciones(String campo, Object anterior,
			Object nuevo, Long avaluoId, UsuarioDTO usuario) {
		super(campo, anterior, nuevo, avaluoId, usuario);
	}
	/**
	 * Genera la modificación personalizada.
	 * */
	@Override
	public ModificacionDTO getModificacion() {
		if ((anterior instanceof ListaDesplegable) || (nuevo instanceof ListaDesplegable)) {
			ModificacionDTO modificacionDTO = new ModificacionDTO();
		    modificacionDTO.setCampo(campo);
		    modificacionDTO.setAvaluo(this.avaluoId);
		    modificacionDTO.setFecha(new Date());
		    modificacionDTO.setUsuario(this.usuario);
			ListaDesplegable listaAnterior = (ListaDesplegable) this.anterior;
	        ListaDesplegable listaNuevo= (ListaDesplegable) this.nuevo;
	        modificacionDTO.setAnterior( listaAnterior !=null  && listaAnterior.getValue() !=null ? listaAnterior.getValue() : "");
	        modificacionDTO.setNuevo(listaNuevo !=null && listaNuevo.getValue()!=null ? listaNuevo.getValue(): ""); 
	        return  modificacionDTO;
		} 
		else{
			return this.getNext().getModificacion();
		}
	}

}
