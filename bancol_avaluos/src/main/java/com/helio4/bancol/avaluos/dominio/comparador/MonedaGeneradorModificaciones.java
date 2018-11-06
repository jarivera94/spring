package com.helio4.bancol.avaluos.dominio.comparador;

import java.util.Arrays;
import java.util.Date;

import com.helio4.bancol.avaluos.dto.ModificacionDTO;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;

/**
 * Clase que se encarga de personalizar las modificaciones generadas
 * para los campos que requiren un valor de moneda. 
 * Se aplica este generador sobre los campos:
 * <ul>
 * 		<li> Valor Comercial (Valor total del avaluo)</li>
 *  </ul>
 * */
public class MonedaGeneradorModificaciones extends AbstractGeneradorModificaciones {
	
	/**
	 * Constructor que recibe la información basica para generar
	 * la modificación.
	 * */
	public MonedaGeneradorModificaciones(String campo, Object anterior,
			Object nuevo, Long avaluoId, UsuarioDTO usuario) {
		super(campo, anterior, nuevo, avaluoId, usuario);
	}
	/**
	 * Genera la modificación personalizada.
	 * */
	@Override
	public ModificacionDTO getModificacion() {
		String campoOriginal = campo.replaceAll("\\s+","");
		/** campos sobre los que aplica este generador. */
		String [] campos = {"ValorTotalAvaluo","ValorIntegralTerreno","ValorProporcionalTerreno","valorIntegralConstruccion","ValorProporcionalConstruccion"};
		if (this.contains(campos, campoOriginal)) {
			ModificacionDTO modificacionDTO = new ModificacionDTO();
		    modificacionDTO.setCampo(campo);
		    modificacionDTO.setAvaluo(this.avaluoId);
		    modificacionDTO.setFecha(new Date());
		    modificacionDTO.setUsuario(this.usuario);
		    String  valorAnterior = "$" + String.valueOf(anterior);
        	String  valorNuevo =  "$" +String.valueOf(nuevo);
        	modificacionDTO.setAnterior( valorAnterior );
        	modificacionDTO.setNuevo( valorNuevo );
	        return  modificacionDTO;
		} 
		else{
			return this.getNext().getModificacion();
		}
	}

	public boolean contains(final String[] array, final String key) {
	    return Arrays.asList(array).contains(key);
	}
	
}
