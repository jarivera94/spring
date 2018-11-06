package com.helio4.bancol.avaluos.controlador;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import org.primefaces.event.ToggleEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import com.helio4.bancol.avaluos.dominio.ListadoRolesController;
import com.helio4.bancol.avaluos.dominio.ListasGeograficasController;
import com.helio4.bancol.avaluos.dto.PaginaInicioDTO;
import com.helio4.bancol.avaluos.dto.PermisoDTO;
import com.helio4.bancol.avaluos.dto.RolDTO;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;
import com.helio4.bancol.avaluos.modelo.Rol;
import com.helio4.bancol.avaluos.servicio.excepciones.RolNotFoundException;

@Controller
@Scope("session")
@Qualifier("listadoRolesBean")
public class ListadoRolesBean implements Serializable {

	private static Logger log = LoggerFactory.getLogger(ListadoRolesBean.class);
	private static final long serialVersionUID = 1L;
	private List<RolDTO> roles;
	private List<Rol> filteredRoles;
	private List<PaginaInicioDTO> paginas;
	private RolDTO rolDTO;
	private UsuarioDTO usuarioActivo;
	private List<PermisoDTO> rolPermisos;
	private RolDTO selectedRol;
	public String nombreRolCreado;
	public String nombreRolActualizado;
	public String nombreRolEliminado;

	@Autowired
	private ListasGeograficasController listasGeograficasController;

	@Autowired
	private ListadoRolesController listadoRolesController;

	@PostConstruct
	public void init() {
		iniciarRol();
		log.debug("Inicializando listas de departamentos desde la base de datos");
		paginas = listadoRolesController.paginas();
		usuarioActivo = (UsuarioDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		selectedRol = new RolDTO();
	}
	
	public void iniciarRol() {
		rolDTO = new RolDTO();
	}

	public void actualizarListado(ComponentSystemEvent event) {
		roles = new ArrayList<RolDTO>();
		roles.addAll(listadoRolesController.roles());
		if (!roles.isEmpty() && roles == null) {
			rolDTO = roles.iterator().next();
		}
		if(nombreRolCreado != null){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("El nuevo rol: " + nombreRolCreado, " fue creado con exito"));
			nombreRolCreado = null;
		}
		if(nombreRolActualizado != null){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("El rol: " + nombreRolActualizado, " fue actualizado con exito"));
			nombreRolActualizado = null;
		}
		if(nombreRolEliminado != null){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("El rol: " + nombreRolEliminado, " fue eliminado con exito"));
			nombreRolEliminado = null;
		}
	}
	
	public boolean tienePermisoCrearRol(UsuarioDTO usuarioDTO) {
		return listadoRolesController.tienePermisoCrearRol(usuarioDTO);
	}

	public void nuevoRol() {
		selectedRol = null;
		String uri = FacesContext.getCurrentInstance().getExternalContext()
				.encodeActionURL(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/pages/configuracion/rol/creacionRol.xhtml");
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(uri);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void onRowToggle (ToggleEvent event){
		rolPermisos = new ArrayList<PermisoDTO>();
		rolPermisos.addAll(((RolDTO)(event.getData())).getPermisos());
	}
	
	public void modificarRol(){
		if(selectedRol != null){
			String uri = FacesContext.getCurrentInstance().getExternalContext()
					.encodeActionURL(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/pages/configuracion/rol/creacionRol.xhtml");
			try {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect(uri);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void eliminarRol(){
		if(selectedRol != null){
			try {
				nombreRolEliminado = selectedRol.getNombre();
				listadoRolesController.eliminarRol(selectedRol);
			} catch (RolNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	//----------getters and setters------------

	public List<RolDTO> getRoles() {
		return roles;
	}

	public void setRoles(List<RolDTO> roles) {
		this.roles = roles;
	}

	public List<PaginaInicioDTO> getPaginas() {
		return paginas;
	}

	public void setPaginas(List<PaginaInicioDTO> paginas) {
		this.paginas = paginas;
	}

	public RolDTO getRolDTO() {
		return rolDTO;
	}

	public void setRolDTO(RolDTO rolDTO) {
		this.rolDTO = rolDTO;
	}

	public UsuarioDTO getUsuarioActivo() {
		return usuarioActivo;
	}

	public void setUsuarioActivo(UsuarioDTO usuarioActivo) {
		this.usuarioActivo = usuarioActivo;
	}

	public List<Rol> getFilteredRoles() {
		return filteredRoles;
	}

	public void setFilteredRoles(List<Rol> filteredRoles) {
		this.filteredRoles = filteredRoles;
	}

	public RolDTO getSelectedRol() {
		return selectedRol;
	}

	public void setSelectedRol(RolDTO selectedRol) {
		this.selectedRol = selectedRol;
	}

	public String getNombreRolCreado() {
		return nombreRolCreado;
	}

	public void setNombreRolCreado(String nombreRolCreado) {
		this.nombreRolCreado = nombreRolCreado;
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

	public List<PermisoDTO> getRolPermisos() {
		return rolPermisos;
	}

	public void setRolPermisos(List<PermisoDTO> rolPermisos) {
		this.rolPermisos = rolPermisos;
	}

	public String getNombreRolActualizado() {
		return nombreRolActualizado;
	}

	public void setNombreRolActualizado(String nombreRolActualizado) {
		this.nombreRolActualizado = nombreRolActualizado;
	}

	public String getNombreRolEliminado() {
		return nombreRolEliminado;
	}

	public void setNombreRolEliminado(String nombreRolEliminado) {
		this.nombreRolEliminado = nombreRolEliminado;
	}

	
}
