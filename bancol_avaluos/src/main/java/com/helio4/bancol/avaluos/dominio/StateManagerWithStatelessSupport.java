package com.helio4.bancol.avaluos.dominio;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import org.apache.myfaces.application.StateManagerImpl;

/**
 * User: Nkey
 * Date: 27.11.12
 * Time: 20:54
 */
public class StateManagerWithStatelessSupport extends StateManagerImpl {
	// TODO: do such trick only if view is really stateless
	@Override
	public UIViewRoot restoreView(FacesContext context, String viewId, String renderKitId) {
		UIViewRoot result = super.restoreView(context, viewId, renderKitId);
		if (result == null) {
			return context.getViewRoot();
		} else {
			return result;
		}
	}
}