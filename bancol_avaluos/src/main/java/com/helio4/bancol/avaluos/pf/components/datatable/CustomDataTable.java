package com.helio4.bancol.avaluos.pf.components.datatable;

import java.util.HashMap;
import java.util.Map;

import javax.el.ValueExpression;
import javax.faces.context.FacesContext;

import org.primefaces.component.datatable.DataTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helio4.bancol.avaluos.controlador.InformeHipotecarioBean;
/**
 * Extends the Primefaces DataTableRenderer to address issus with the
 * persistence of the filter values.
 */
public class CustomDataTable extends DataTable {
		

		private static Logger LOG = LoggerFactory.getLogger( InformeHipotecarioBean.class );
	   /**
	    * Locates the filterMap attribute on the datatable.
	    * <p>
	    * @return the ValueExpression provided in the filterMap attribute if
	    *         specified or null if not
	    */
	   protected ValueExpression getFilterFacetValueExpression() {
	      ValueExpression ve = getValueExpression("filterMap");
	      return ve;
	   }

	   /*
	    * (non-Javadoc)
	    * @see org.primefaces.component.datatable.DataTable#getFilters()
	    */
	   @Override
	   public Map<String,Object> getFilters(){
	      ValueExpression ve = getFilterFacetValueExpression();
	      if (ve == null)
	         return super.getFilters();

	      Map<String, Object> map = (Map<String, Object>) ve.getValue(FacesContext.getCurrentInstance().getELContext());
	      LOG.trace("Facet found and map is {}", map);
	      if (map == null)
	         return new HashMap<String, Object>();
	      else
	         return map;
	   };

	   /*
	    * (non-Javadoc)
	    * @see
	    * org.primefaces.component.datatable.DataTable#setFilters(java.util.Map)
	    */
	   @Override
	   public void setFilters(Map<String,Object> filters) {
	      ValueExpression ve = getFilterFacetValueExpression();
	      if (ve == null) {
	         super.setFilters(filters);
	         return;
	      }

	      ve.setValue(FacesContext.getCurrentInstance().getELContext(), filters);
	      LOG.trace("Facet updated to {}", filters);
	   };
	}