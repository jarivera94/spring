package com.helio4.bancol.avaluos.dominio;

import java.util.ArrayList;
import java.util.Collection;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.helio4.bancol.avaluos.modelo.Cliente;

@RequestMapping("/customer/**")
@Controller
@Scope("session")
public class CustomerController {

 @RequestMapping(value ="/customer/report/pdf", method = RequestMethod.GET)
 public String fireReport(ModelMap modelMap) {
	 Collection<Cliente> datos = new ArrayList<Cliente>();		
		Cliente c = new Cliente();
		c.setPrimerNombre("Jose");
		c.setDireccionDeContactoSolicitante("Calle Colon 1");
		datos.add(c);
  JRBeanCollectionDataSource jrDataSource = new JRBeanCollectionDataSource(datos,false);
  modelMap.put("customerReportList", jrDataSource);
  return "customerReportList";
 }
}