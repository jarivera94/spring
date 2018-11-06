package com.helio4.bancol.avaluos.dominio;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.helio4.bancol.avaluos.modelo.Cliente;

@Controller
@Scope("session")
@RequestMapping("/report")
public class ReportController {
	
	@RequestMapping(value="/simplereport", method=RequestMethod.GET)
    public String generarSimpleReport(ModelMap modelMap, HttpServletResponse response) {
        return("simpleReport");
    }
	
	@RequestMapping(value="/datareport", method=RequestMethod.GET)
    public String generarDataSourceReport(ModelMap modelMap, HttpServletResponse response) {		
		modelMap.put("clienteData", getClienteData());
        return("dataReport");
    }
	
	private Collection<Cliente> getClienteData() {		
		Collection<Cliente> datos = new ArrayList<Cliente>();		
		Cliente c = new Cliente();
		c.setPrimerNombre("Jose");
		c.setDireccionDeContactoSolicitante("Calle Colon 1");
		datos.add(c);		
		return datos;
	}
	
}