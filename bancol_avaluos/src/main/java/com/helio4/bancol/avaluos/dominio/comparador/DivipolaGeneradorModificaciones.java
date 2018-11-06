package com.helio4.bancol.avaluos.dominio.comparador;

import java.util.Date;

import com.helio4.bancol.avaluos.dto.DivipolaDTO;
import com.helio4.bancol.avaluos.dto.ModificacionDTO;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;
/**
 * Clase que se encarga de generar las modificaicones personalizadas
 * para campos de tipo DivipolaDTO.
 * */
public class DivipolaGeneradorModificaciones  extends AbstractGeneradorModificaciones{
	
	/**
	 * Constructor que recibe la información basica para generar
	 * la modificación.
	 * */
	public DivipolaGeneradorModificaciones(String campo, Object anterior, Object nuevo,
			Long avaluoId, UsuarioDTO usuario) {
		super(campo, anterior, nuevo, avaluoId, usuario);
	}
	/**
	 * Genera la modificación personalizada.
	 * */
	@Override
	public ModificacionDTO getModificacion() {
		if (anterior instanceof DivipolaDTO  ||  nuevo instanceof DivipolaDTO) {
			ModificacionDTO modificacionDTO = new ModificacionDTO();
		    modificacionDTO.setCampo(campo);
		    modificacionDTO.setAvaluo(this.avaluoId);
		    modificacionDTO.setFecha(new Date());
		    modificacionDTO.setUsuario(this.usuario);
		    DivipolaDTO divipolaAnterior = (DivipolaDTO)anterior;
      	  	DivipolaDTO divipolaNuevo = (DivipolaDTO)nuevo;
      	  	String stringAnterior =  divipolaAnterior  !=null ?  (divipolaAnterior.getDepartamento() + "-" +divipolaAnterior.getCentroPoblado()):"";
      	  	String stringNuevo =  divipolaNuevo != null ? (divipolaNuevo.getDepartamento()+"-"+divipolaNuevo.getCentroPoblado()) :"";
      	  	modificacionDTO.setAnterior(stringAnterior);
      	  	modificacionDTO.setNuevo(stringNuevo);
      	  	return  modificacionDTO;
		} 
		else{
			return this.getNext().getModificacion();
		}
	}

}
