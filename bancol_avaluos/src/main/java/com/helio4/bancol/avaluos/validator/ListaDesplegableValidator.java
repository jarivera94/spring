package com.helio4.bancol.avaluos.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.helio4.bancol.avaluos.controlador.ListaDesplegable;
import com.helio4.bancol.avaluos.servicio.util.Constantes;

@FacesValidator("listaDesplegableValidator")
public class ListaDesplegableValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		ListaDesplegable lista = (ListaDesplegable)value;
		String nombreLista = component.getId();
		String opcionLista = lista.getValue();
		if(opcionLista!=null && opcionLista.equals(Constantes.SELECCCIONE)) {
			FacesMessage msg =  new FacesMessage(nombreLista, "Debe seleccionar un valor");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
	}
}
