package com.helio4.bancol.avaluos.controlador;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.primefaces.model.DualListModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.helio4.bancol.avaluos.dto.RegionalDTO;
import com.helio4.bancol.avaluos.servicio.datos.RegionalService;

@Controller
@Scope("view")
@Qualifier("regionalBean")
public class RegionalBean implements Serializable {

	private static Logger log = LoggerFactory.getLogger(RegionalBean.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instanciar Services Interface.
	 */
	@Autowired
	@Qualifier("repositoryRegionalService")
	private RegionalService regionalService;

	// ------Objetos y Listas
	private RegionalDTO newRegional;
	private ArrayList<RegionalDTO> listRegional;
	private ArrayList<RegionalDTO> listRegionalTarget;
	private DualListModel<RegionalDTO> dualListRegionales;

	@PostConstruct
	public void init() {

		try {
			log.debug("Inicializando listas de regionales desde la base de datos");
			listarRegionales();
			newRegional = new RegionalDTO();
			listRegionalTarget = new ArrayList<RegionalDTO>();
			dualListRegionales = new DualListModel<RegionalDTO>(listRegional, listRegionalTarget);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	public void listarRegionales() {
		listRegional = new ArrayList<RegionalDTO>();
		listRegional.addAll(regionalService.encontrarTodos());
		/*
		 * if (!getListRegional().isEmpty()) { for (RegionalDTO regionalDTO :
		 * listRegional) {
		 * 
		 * } }
		 */
	}

	public RegionalDTO getNewRegional() {
		return newRegional;
	}

	public void setNewRegional(RegionalDTO newRegional) {
		this.newRegional = newRegional;
	}

	public ArrayList<RegionalDTO> getListRegional() {
		return listRegional;
	}

	public void setListRegional(ArrayList<RegionalDTO> listRegional) {
		this.listRegional = listRegional;
	}

	public ArrayList<RegionalDTO> getListRegionalTarget() {
		return listRegionalTarget;
	}

	public void setListRegionalTarget(ArrayList<RegionalDTO> listRegionalTarget) {
		this.listRegionalTarget = listRegionalTarget;
	}

	public DualListModel<RegionalDTO> getDualListRegionales() {
		return dualListRegionales;
	}

	public void setDualListRegionales(DualListModel<RegionalDTO> dualListRegionales) {
		this.dualListRegionales = dualListRegionales;
	}
}
