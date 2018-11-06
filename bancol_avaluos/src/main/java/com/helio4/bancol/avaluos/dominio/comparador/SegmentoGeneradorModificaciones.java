package com.helio4.bancol.avaluos.dominio.comparador;

import java.util.Date;
import com.helio4.bancol.avaluos.dto.ModificacionDTO;
import com.helio4.bancol.avaluos.dto.SegmentoDTO;
import com.helio4.bancol.avaluos.dto.SemaforoDTO;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;

/**
 * Clase que se encarga de generar las modificaicones personalizadas
 * para campos de tipo SegmentoDTO.
 * */
public class SegmentoGeneradorModificaciones extends AbstractGeneradorModificaciones {

	/**
	 * Constructor que recibe la información basica para generar
	 * la modificación.
	 * */
	public SegmentoGeneradorModificaciones(String campo, Object anterior, Object nuevo,
			Long avaluoId, UsuarioDTO usuario) {
		super(campo, anterior, nuevo, avaluoId, usuario);
	}
	/**
	 * Genera la modificación personalizada.
	 * */
	@Override
	public ModificacionDTO getModificacion() {
		if (anterior instanceof SegmentoDTO || nuevo instanceof SemaforoDTO) {
			ModificacionDTO modificacionDTO = new ModificacionDTO();
		    modificacionDTO.setCampo(campo);
		    modificacionDTO.setAvaluo(this.avaluoId);
		    modificacionDTO.setFecha(new Date());
		    modificacionDTO.setUsuario(this.usuario);
		    SegmentoDTO segmentoAnterior = (SegmentoDTO)anterior;
        	SegmentoDTO segmentoNuevo = (SegmentoDTO)nuevo;
        	modificacionDTO.setAnterior( segmentoAnterior != null ?  segmentoAnterior.getNombre(): "");
        	modificacionDTO.setNuevo( segmentoNuevo != null ? segmentoNuevo.getNombre():"");
	        return  modificacionDTO;
		} 
		else{
			return this.getNext().getModificacion();
		}
	}

}
