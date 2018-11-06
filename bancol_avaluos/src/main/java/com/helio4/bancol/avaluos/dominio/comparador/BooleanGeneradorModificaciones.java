package com.helio4.bancol.avaluos.dominio.comparador;

import java.util.Date;

import com.helio4.bancol.avaluos.dto.ModificacionDTO;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;

/**
 * Clase que se encarga de generar las modificaicones personalizadas para campos
 * de tipo Boolean.
 */
public class BooleanGeneradorModificaciones extends AbstractGeneradorModificaciones {

	/**
	 * Constructor que recibe la información basica para generar la
	 * modificación.
	 */
	public BooleanGeneradorModificaciones(String campo, Object anterior, Object nuevo, Long avaluoId,
			UsuarioDTO usuario) {
		super(campo, anterior, nuevo, avaluoId, usuario);
	}

	/**
	 * Genera la modificación personalizada.
	 */
	@Override
	public ModificacionDTO getModificacion() {
		if ((anterior instanceof Boolean) || (nuevo instanceof Boolean)) {
			ModificacionDTO modificacionDTO = new ModificacionDTO();
			modificacionDTO.setCampo(campo);
			modificacionDTO.setAvaluo(this.avaluoId);
			modificacionDTO.setFecha(new Date());
			modificacionDTO.setUsuario(this.usuario);
			Boolean valorAnterior = (Boolean) anterior;
			Boolean valorNuevo = (Boolean) nuevo;

			if (valorAnterior == null) {
				modificacionDTO.setAnterior("");
			} else {
				modificacionDTO.setAnterior(valorAnterior != null && valorAnterior ? "Si" : "No");
			}

			if (valorNuevo == null) {
				modificacionDTO.setNuevo("");

			} else {
				modificacionDTO.setNuevo(valorAnterior != null && valorNuevo ? "Si" : "No");
			}

			return modificacionDTO;
		} else {
			return this.getNext().getModificacion();
		}
	}

}
