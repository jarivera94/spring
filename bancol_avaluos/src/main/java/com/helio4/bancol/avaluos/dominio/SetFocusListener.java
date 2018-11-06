/*
 * net/balusc/webapp/SetFocusListener.java
 * 
 * Copyright (C) 2007 BalusC
 * 
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with this library.
 * If not, see <http://www.gnu.org/licenses/>.
 */

package com.helio4.bancol.avaluos.dominio;

import java.util.Iterator;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 * This phase listener checks if there is a client ID with message and will set the client ID as 
 * ${focus} in the request map. It will also gather all client IDs with message and will set it as
 * ${highlight} in the request map.
 * <p>
 * This phase listener should be configured in the faces-config.xml as follows:
 * <pre>
 * &lt;lifecycle&gt;
 *     &lt;phase-listener&gt;net.balusc.webapp.SetFocusListener&lt;/phase-listener&gt;
 * &lt;/lifecycle&gt;
 * </pre>
 * 
 * @author BalusC
 * @link http://balusc.blogspot.com/2007/12/set-focus-in-jsf.html
 */
public class SetFocusListener implements PhaseListener {

	// Actions ------------------------------------------------------------------------------------

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see javax.faces.event.PhaseListener#getPhaseId()
	 */
	public PhaseId getPhaseId() {

		// Listen on render response phase.
		return PhaseId.RENDER_RESPONSE;
	}

	/**
	 * @see javax.faces.event.PhaseListener#beforePhase(javax.faces.event.PhaseEvent)
	 */
	public void beforePhase(PhaseEvent event) {

		// Init.
		FacesContext facesContext = event.getFacesContext();
		String focus = null;
		StringBuilder highlight = new StringBuilder();

		// Iterate over all client ID's with messages.
		Iterator<String> clientIdsWithMessages = facesContext.getClientIdsWithMessages();
		while (clientIdsWithMessages.hasNext()) {
			String clientIdWithMessages = clientIdsWithMessages.next();
			if (focus == null) {
				focus = clientIdWithMessages;
			}
			highlight.append(clientIdWithMessages);
			if (clientIdsWithMessages.hasNext()) {
				highlight.append(",");
			}
		}

		// Set ${focus} and ${highlight} in JSP.
		facesContext.getExternalContext().getRequestMap().put("focus", focus);
		facesContext.getExternalContext().getRequestMap().put("highlight", highlight.toString());
	}

	/**
	 * @see javax.faces.event.PhaseListener#afterPhase(javax.faces.event.PhaseEvent)
	 */
	public void afterPhase(PhaseEvent event) {
		// Do nothing.
	}

}