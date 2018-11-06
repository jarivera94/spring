package com.helio4.bancol.avaluos.controlador;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import com.helio4.bancol.avaluos.dominio.AvaluoController;
import com.helio4.bancol.avaluos.dominio.InformeHipotecarioController;
import com.helio4.bancol.avaluos.dominio.TarifaUVRController;
import com.helio4.bancol.avaluos.dto.AvaluoDTO;
import com.helio4.bancol.avaluos.dto.FormatoInformeComercialDTO;
import com.helio4.bancol.avaluos.dto.FormatoInformeHipotecarioDTO;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;
import com.helio4.bancol.avaluos.servicio.util.Constantes;
import com.helio4.bancol.avaluos.servicio.util.DateUtils;

@Controller
@Scope("session")
@Qualifier("listadoAvaluosSisgemBean")
public class ListadoAvaluosSisgemBean implements Serializable {

    private static Logger log = LoggerFactory
            .getLogger(ListadoAvaluosSisgemBean.class);
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private AvaluoDTO avaluo;
    
    private String mensajeValidacion;

    private final InformeHipotecarioController informeHipotecarioController;
    private final AvaluoController avaluoController;
    private final TarifaUVRController tarifaUvrController;
    private final ListadoAvaluosBean listadoAvaluosBean;
    
    @Autowired
    public ListadoAvaluosSisgemBean(InformeHipotecarioController informeHipotecarioController,  TarifaUVRController tarifaUvrController, AvaluoController avaluoController,ListadoAvaluosBean listadoAvaluosBean) {
        this.informeHipotecarioController = informeHipotecarioController;
        this.tarifaUvrController = tarifaUvrController;
        this.avaluoController = avaluoController;
        this.listadoAvaluosBean= listadoAvaluosBean;
    }

    @PostConstruct
    public void init() {
        log.debug("Inicializando ListadoAvaluosSisgemBean: ");
    }
    
    public void onLoad(){
    	
    	FacesContext context = FacesContext.getCurrentInstance();
    	Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
    	String avaluoId = paramMap.get("avaluoIdSisgen");

        UsuarioDTO user = (UsuarioDTO) SecurityContextHolder.getContext()
        .getAuthentication().getPrincipal();
    	
    	if(avaluoId!= null){
    		
    		AvaluoDTO avaluoAuxiliar = informeHipotecarioController.encontrarAvaluoPorIdTinsa(Long.parseLong(avaluoId));
    		if(avaluoAuxiliar!= null){
    			this.avaluo = avaluoAuxiliar;
    			listadoAvaluosBean.setAvaluo(avaluo);
    			listadoAvaluosBean.setUrlReturn("/pages/informes/sisgem/hipotecario_sisgem.xhtml");
    			ingresarInforme();
    		}
    	}
    }

    public void ingresarInforme() {
        log.info("ingresarInforme {}", avaluo);

        String uri = "";
        Date fecha = new Date();
        if (tarifaUvrController
                .encontrarPorFecha
                        (DateUtils
                                .getFechaFormateada
                                        (DateUtils.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS, fecha)) != null ){
            avaluo.setFormatoInforme(avaluoController.cargarFormatoInforme(avaluo.getId()));
            if (avaluo.getFormatoInforme().getClass()
                    .equals(FormatoInformeHipotecarioDTO.class)) {
                uri = FacesContext
                        .getCurrentInstance()
                        .getExternalContext()
                        .encodeActionURL(
                                FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/pages/informes/sisgem/hipotecario_sisgem.xhtml");
            }
            try {
                FacesContext.getCurrentInstance().getExternalContext()
                        .redirect(uri);
            } catch (IOException e) {
                // TODO Reireccionar a página no encontrada
                e.printStackTrace();
            }
        }else{
        	mensajeValidacion = "No existe UVR para este día: Por favor contacte al administrador del sistema.";
        }
    }

	public String getMensajeValidacion() {
		return mensajeValidacion;
	}

	public void setMensajeValidacion(String mensajeValidacion) {
		this.mensajeValidacion = mensajeValidacion;
	}
}
