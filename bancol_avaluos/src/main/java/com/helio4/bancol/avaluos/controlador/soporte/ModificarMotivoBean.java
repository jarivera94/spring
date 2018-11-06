package com.helio4.bancol.avaluos.controlador.soporte;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("session")
@Qualifier("modificarMotivoBean")
public class ModificarMotivoBean extends GeneralSoporte  implements Serializable {

	private static Logger log = LoggerFactory.getLogger(ModificarMotivoBean.class);

	private static final long serialVersionUID = 9076632279744143935L;

	@PostConstruct
	public void init() {
            try {
                
            } catch (Exception e) {
            }
		
	}
	
}
