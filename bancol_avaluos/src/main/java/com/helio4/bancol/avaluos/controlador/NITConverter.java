package com.helio4.bancol.avaluos.controlador;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.stereotype.Component;

@Component("NITConverter")
public class NITConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,String value) {
		//System.err.println( Long.valueOf(value.replace("-", "")) );
		
		if (value!= null){
			return value.contains("-") ?  Long.valueOf(value.replace("-", ""))  : Long.valueOf(value) ;
		}else{
			return null;
		}
		
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,Object value) {
		return  value!=null ? value.toString() : "";
	}

}
