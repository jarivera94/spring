package com.helio4.bancol.avaluos.controlador;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.primefaces.model.DualListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.helio4.bancol.avaluos.dto.TipoAvaluoDTO;
import com.helio4.bancol.avaluos.servicio.datos.TipoAvaluoService;

@Controller
@Scope("view")
@Qualifier("tavaluosBean")
public class TAvaluosBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	@Qualifier("repositoryTipoAvaluoService")
	private TipoAvaluoService tipoAvaluoService;

	private TipoAvaluoDTO newtipoAvaluoDTO;
	private ArrayList<TipoAvaluoDTO> listTipoAvaluos;
	private ArrayList<TipoAvaluoDTO> listTipoAvaluosTarget;
	private DualListModel<TipoAvaluoDTO> dualListTipoAvaluo;

	@PostConstruct
	public void init() {
            
            try {
                listarTipoAvaluos();
		setNewtipoAvaluoDTO(new TipoAvaluoDTO());
		listTipoAvaluosTarget = new ArrayList<TipoAvaluoDTO>();
		setDualListTipoAvaluo(new DualListModel<TipoAvaluoDTO>(listTipoAvaluos, listTipoAvaluosTarget));
            } catch (Exception e) {
                
            }
	}
	
	public void listarTipoAvaluos(){
		listTipoAvaluos = new ArrayList<TipoAvaluoDTO>();
		listTipoAvaluos.addAll(tipoAvaluoService.encontrarTodos());
	}

	public TipoAvaluoDTO getNewtipoAvaluoDTO() {
		return newtipoAvaluoDTO;
	}

	public void setNewtipoAvaluoDTO(TipoAvaluoDTO newtipoAvaluoDTO) {
		this.newtipoAvaluoDTO = newtipoAvaluoDTO;
	}

	public DualListModel<TipoAvaluoDTO> getDualListTipoAvaluo() {
		return dualListTipoAvaluo;
	}

	public void setDualListTipoAvaluo(DualListModel<TipoAvaluoDTO> dualListTipoAvaluo) {
		this.dualListTipoAvaluo = dualListTipoAvaluo;
	}

}
