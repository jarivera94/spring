package com.helio4.bancol.avaluos.controlador;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.model.DualListModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.helio4.bancol.avaluos.dominio.ListadoRolesController;
import com.helio4.bancol.avaluos.dominio.ListasGeograficasController;
import com.helio4.bancol.avaluos.dominio.RolController;
import com.helio4.bancol.avaluos.dto.PaginaInicioDTO;
import com.helio4.bancol.avaluos.dto.PermisoDTO;
import com.helio4.bancol.avaluos.dto.RolDTO;
import com.helio4.bancol.avaluos.servicio.excepciones.PaginaInicioNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.PermisoNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.RolNotFoundException;

@Controller
@Scope("view")
@Qualifier("rolBean")
public class RolBean implements Serializable {

	private static Logger log = LoggerFactory.getLogger(RolBean.class);
	private static final long serialVersionUID = 1L;
	private List<PaginaInicioDTO> paginas;
	private DualListModel<PermisoDTO> permiso;

	private RolDTO rolDTO;
	
	@Autowired
	private ListadoRolesBean listadoRolesBean;

	@Autowired
	private ListasGeograficasController listasGeograficasController;

	@Autowired
	private ListadoRolesController listadoRolesController;

	@Autowired
	private RolController rolController;
	private List<PermisoDTO> permisosSource;
	private List<PermisoDTO> permisosTarget;
	
	private List<PermisoDTO> todosLosPermisos;

	@PostConstruct
	public void init() {
		iniciarRol();
		log.debug("Inicializando listas de departamentos desde la base de datos");
		setPaginas(listadoRolesController.paginas());
		iniciarPermisosDisponibles();
		permiso = new DualListModel<PermisoDTO>(permisosSource, permisosTarget);
		
		
	}

	public void iniciarRol() {
		if(listadoRolesBean.getSelectedRol() != null){
			rolDTO = listadoRolesBean.getSelectedRol();
		}else{
			rolDTO = new RolDTO();
		}
	}
	
	private void iniciarPermisosDisponibles() {
		permisosSource = listadoRolesController.permisos();
		permisosTarget = new ArrayList<PermisoDTO>();
		if(listadoRolesBean.getSelectedRol() != null){
			if(listadoRolesBean.getSelectedRol().getPermisos() != null){
				permisosSource.removeAll(listadoRolesBean.getSelectedRol().getPermisos());
				permisosTarget.addAll(listadoRolesBean.getSelectedRol().getPermisos());
			}
		}
		this.todosLosPermisos = new ArrayList<>();
		this.todosLosPermisos.addAll(this.permisosSource);
		this.todosLosPermisos.addAll(this.permisosTarget);
	}

	public void guardarRol() {
		if(validarPermisosAgregados()){
			if(listadoRolesBean.getSelectedRol() != null){
				actualizar();
			}else{
				guardar();
			}
            log.debug("Redireccionando con {} a listado de roles.",
                    rolDTO.getNombre());
			ExternalContext context = FacesContext.getCurrentInstance()
					.getExternalContext();
			String uri = context
					.encodeActionURL(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/pages/configuracion/rol/listadoRoles.xhtml");
			try {
				context.redirect(uri);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private boolean validarPermisosAgregados(){
		boolean permisosNoVacio = true;
		if(!(permiso.getTarget().size() > 0)){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Este rol necesita permisos para ser creado"));
			permisosNoVacio = false;
		}
		return permisosNoVacio;
	}

	private boolean guardar() {
		rolDTO.setPermisos(permiso.getTarget());
		rolController.guardarRol(rolDTO);
		//System.out.println("Nuevo Rol Creado: " + rolDTO.getNombre());
		listadoRolesBean.setNombreRolCreado(rolDTO.getNombre());
		listadoRolesBean.setNombreRolActualizado(null); 
		return true;

	}
	
	private void actualizar(){
		rolDTO.setPermisos(permiso.getTarget());
		try {
			rolController.actualizarRol(rolDTO);
			//System.out.println("Rol: " + rolDTO.getNombre() + " fue actualizado");
			listadoRolesBean.setNombreRolActualizado(rolDTO.getNombre());
			listadoRolesBean.setNombreRolCreado(null);
		} catch (RolNotFoundException e) {
			e.printStackTrace();
		} catch (PaginaInicioNotFoundException e) {
			e.printStackTrace();
		} catch (PermisoNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void cancelarRol() {
		ExternalContext context = FacesContext.getCurrentInstance()
				.getExternalContext();
		String uri = context
				.encodeActionURL(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/pages/configuracion/rol/listadoRoles.xhtml");
		try {
			context.redirect(uri);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	//----------getters and setters------------

	public List<PaginaInicioDTO> getPaginas() {
		return paginas;
	}

	public void setPaginas(List<PaginaInicioDTO> paginas) {
		this.paginas = paginas;
	}

	public static Logger getLog() {
		return log;
	}

	public static void setLog(Logger log) {
		RolBean.log = log;
	}

	public DualListModel<PermisoDTO> getPermiso() {
		return permiso;
	}

	public void setPermiso(DualListModel<PermisoDTO> permiso) {
		this.permiso = permiso;
	}

	public RolDTO getRolDTO() {
		return rolDTO;
	}

	public void setRolDTO(RolDTO rolDTO) {
		this.rolDTO = rolDTO;
	}

	public ListasGeograficasController getListasGeograficasController() {
		return listasGeograficasController;
	}

	public void setListasGeograficasController(
			ListasGeograficasController listasGeograficasController) {
		this.listasGeograficasController = listasGeograficasController;
	}

	public ListadoRolesController getListadoRolesController() {
		return listadoRolesController;
	}

	public void setListadoRolesController(
			ListadoRolesController listadoRolesController) {
		this.listadoRolesController = listadoRolesController;
	}

	public RolController getRolController() {
		return rolController;
	}

	public void setRolController(RolController rolController) {
		this.rolController = rolController;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<PermisoDTO> getTodosLosPermisos() {
		return todosLosPermisos;
	}

	public void setTodosLosPermisos(List<PermisoDTO> todosLosPermisos) {
		this.todosLosPermisos = todosLosPermisos;
	}

}
