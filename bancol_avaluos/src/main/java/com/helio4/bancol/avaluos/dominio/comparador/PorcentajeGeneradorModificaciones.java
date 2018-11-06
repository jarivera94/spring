package com.helio4.bancol.avaluos.dominio.comparador;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import com.helio4.bancol.avaluos.dto.ModificacionDTO;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;
import com.helio4.bancol.avaluos.servicio.util.MathUtils;

/**
 * Clase que se encarga de personalizar las modificaciones generadas
 * para los campos que requiren un valor de porcentaje. 
 * */
public class PorcentajeGeneradorModificaciones extends AbstractGeneradorModificaciones {
	
	/**
	 * Constructor que recibe la información basica para generar
	 * la modificación.
	 * */
	public PorcentajeGeneradorModificaciones(String campo, Object anterior,
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
		String [] campos = {"PorcentajeValorProporcionalTerreno","PorcentajeValorProporcionalConstrucciono", "Porcentaje"};
		if (this.contains(campos, campoOriginal)) {
			ModificacionDTO modificacionDTO = new ModificacionDTO();
		    modificacionDTO.setCampo(campo);
		    modificacionDTO.setAvaluo(this.avaluoId);
		    modificacionDTO.setFecha(new Date());
		    modificacionDTO.setUsuario(this.usuario);
		    String  valorAnterior = "%" + this.getPorcentaje(anterior);
        	String  valorNuevo =  "%" + this.getPorcentaje(nuevo);
        	modificacionDTO.setAnterior( valorAnterior );
        	modificacionDTO.setNuevo( valorNuevo );
	        return  modificacionDTO;
		} 
		else{
			return this.getNext().getModificacion();
		}
	}
	

	private String getPorcentaje(Object value) {
		try {
			if(value instanceof Integer){
				return String.valueOf(Integer.parseInt(String.valueOf(value)) * 100);
			}
			if(value instanceof BigDecimal) {
				BigDecimal valor = MathUtils.getBigDecimal(value);
				if(valor!=null) {
					return String.valueOf(valor.multiply( new BigDecimal("100")));
				}
			}
		}
		catch (NumberFormatException e) {
		     return "";
		}
		return "";
	}
	
	public boolean contains(final String[] array, final String key) {
	    return Arrays.asList(array).contains(key);
	}
	
}
