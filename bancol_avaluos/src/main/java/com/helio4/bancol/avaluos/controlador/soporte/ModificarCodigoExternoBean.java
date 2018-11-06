package com.helio4.bancol.avaluos.controlador.soporte;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.helio4.bancol.avaluos.dto.EntidadDTO;

@Controller
@Scope("session")
@Qualifier("modificarCodigoExternoBean")
public class ModificarCodigoExternoBean extends GeneralSoporte  implements Serializable {

	private static Logger log = LoggerFactory.getLogger(ModificarCodigoExternoBean.class);

	private static final long serialVersionUID = 9076632279744143935L;

	@PostConstruct
	public void init() {
            try {
            super.inicializar();
            } catch (Exception e) {
                log.error(e.getMessage());
            }

	}
	
	public List<EntidadDTO> getEntidades() {
		return entidades;
	}

	public EntidadDTO getEntidadSeleccionada() {
		return entidadSeleccionada;
	}
	
	public void consultarSolicitud() {
		System.out.println("ejecucion de consultar solicitud!!");
	}
	
	public String getCodigoExternoSeleccionado() {
		return codigoExternoSeleccionado;
	}
	
	public void setEntidadSeleccionada(EntidadDTO entidadSeleccionada) {
		this.entidadSeleccionada = entidadSeleccionada;
	}

	public void setCodigoExternoSeleccionado(String codigoExternoSeleccionado) {
		this.codigoExternoSeleccionado = codigoExternoSeleccionado;
	}
	
}
