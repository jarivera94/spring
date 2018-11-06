package com.helio4.bancol.avaluos.controlador;

import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
@Qualifier("jsfHelper")
public class JsfHelper {
    public boolean hasMessages(String clientId) {
        return FacesContext.getCurrentInstance().getMessages(clientId).hasNext();
    }
}
