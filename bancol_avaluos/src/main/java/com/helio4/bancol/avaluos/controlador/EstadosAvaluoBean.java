package com.helio4.bancol.avaluos.controlador;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.helio4.bancol.avaluos.dominio.EstadoAvaluoController;
import com.helio4.bancol.avaluos.dto.AvaluoDTO;
import com.helio4.bancol.avaluos.dto.EstadoAvaluoDTO;

@Controller
@Scope("view")
@Qualifier("estadosAvaluoBean")
public class EstadosAvaluoBean implements Serializable{
	
	private static Logger log = LoggerFactory.getLogger(EstadosAvaluoBean.class);

	private static final long serialVersionUID = -1739840596716307219L;
	
	@Autowired
	private ListadoAvaluosBean listadoAvaluosBean;
	
	@Autowired
	private EstadoAvaluoController estadoAvaluoController;
	
	private AvaluoDTO avaluo;
	
	private EstadoAvaluoDTO estadoAvaluo;
	
	private List<EstadoAvaluoDTO> estadosAvaluo; 
	
	@PostConstruct
	public void init() {
		avaluo = listadoAvaluosBean.getAvaluo();
		estadosAvaluo = estadoAvaluoController.buscarEstadosAvaluo(avaluo.getId());
	}
	
	public void cancelar(){
		String uri = FacesContext.getCurrentInstance().getExternalContext().encodeActionURL(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/pages/avaluos/listadoAvaluos.xhtml");
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(uri);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public AvaluoDTO getAvaluo() {
		return avaluo;
	}

	public void setAvaluo(AvaluoDTO avaluo) {
		this.avaluo = avaluo;
	}

	public EstadoAvaluoDTO getEstadoAvaluo() {
		return estadoAvaluo;
	}

	public void setEstadoAvaluo(EstadoAvaluoDTO estadoAvaluo) {
		this.estadoAvaluo = estadoAvaluo;
	}

	public List<EstadoAvaluoDTO> getEstadosAvaluo() {
		return estadosAvaluo;
	}

	public void setEstadosAvaluo(List<EstadoAvaluoDTO> estadosAvaluo) {
		this.estadosAvaluo = estadosAvaluo;
	}

}
