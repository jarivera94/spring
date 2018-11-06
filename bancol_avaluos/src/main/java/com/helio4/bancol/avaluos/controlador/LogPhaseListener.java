package com.helio4.bancol.avaluos.controlador;

import java.util.Map;

import javax.faces.component.UIViewRoot;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogPhaseListener implements PhaseListener {

    private static final long serialVersionUID = -4130659991543028001L;

    public long startTime;

    private static final Logger log = LoggerFactory
            .getLogger(LogPhaseListener.class);

    public void afterPhase(PhaseEvent event) {
        event.getSource();
        if (event.getPhaseId() == PhaseId.RENDER_RESPONSE) {
            long endTime = System.nanoTime();
            long diffMs = (long) ((endTime - startTime) * 0.000001);
            log.warn("Execution Time = {}ms", diffMs);
            printLog(event, "after phase");
        }
        log.warn("Executed Phase {}", event.getPhaseId());
    }

    protected void printLog(PhaseEvent event, String msg) {
        UIViewRoot view = event.getFacesContext().getViewRoot();
        String viewID = "no view";
        if (view != null)
            viewID = view.getViewId();
        log.warn(msg + " {} {}", event.getPhaseId(), viewID);
        printRequestParameters(event);
        printRequestAttributes(event);
        printSessionAttributes(event);

    }

    private void printSessionAttributes(PhaseEvent event) {
        Map<String, Object> sessAttrs = event.getFacesContext()
                .getExternalContext().getSessionMap();
        StringBuilder sb = new StringBuilder();
        for (String key : sessAttrs.keySet()) {
            sb.append("(" + key + "=" + sessAttrs.get(key) + ") ");
        }
        log.warn("Session Attributes : {}", sb.toString());
    }

    private void printRequestAttributes(PhaseEvent event) {
        Map<String, Object> reqAttrs = event.getFacesContext()
                .getExternalContext().getRequestMap();
        StringBuilder sb = new StringBuilder();
        for (String key : reqAttrs.keySet()) {
            sb.append("(" + key + "=" + reqAttrs.get(key) + ") ");
        }
        log.warn("Request Attributes: {}", sb.toString());
    }

    private void printRequestParameters(PhaseEvent event) {
        Map<String, String> reqParams = event.getFacesContext()
                .getExternalContext().getRequestParameterMap();
        StringBuilder sb = new StringBuilder();
        for (String key : reqParams.keySet()) {
            sb.append("(" + key + "=" + reqParams.get(key) + ") ");
        }
        log.warn("Request Parameters: {}", sb.toString());
    }

    public void beforePhase(PhaseEvent event) {

        if (event.getPhaseId() == PhaseId.RESTORE_VIEW) {
            startTime = System.nanoTime();
        }
    }

    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }

}
