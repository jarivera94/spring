package com.helio4.bancol.avaluos.controlador;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import com.helio4.bancol.avaluos.dto.UsuarioDTO;

@Controller
@Scope("session")
@Qualifier("menuBean")
public class MenuBean implements Serializable {

	private static final long serialVersionUID = 9076632279744143935L;

	private MenuModel model;

	private UsuarioDTO usuario;

	@Autowired
	private ListadoAvaluosBean listadoAvaluosBean;

	@PostConstruct
	public void init() {

		try {
			usuario = (UsuarioDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			boolean entidades = false;
			boolean segmentos = false;
			boolean sucursales = false;
			boolean tarifas = false;
			boolean roles = false;
			boolean usuarios = false;
			boolean archivos = false;
			boolean reportes = false;
			boolean agenda = false;
			boolean estudio_mercado = false;
			boolean admin = false;
			boolean editarCliente = Boolean.FALSE;

			// consultar permisos desde ListadoAvaluosBean
			if (listadoAvaluosBean.isCrearEntidades()) {
				entidades = true;
			}
			if (listadoAvaluosBean.isCrearSegmentos()) {
				segmentos = true;
			}
			if (listadoAvaluosBean.isCrearSucursales()) {
				sucursales = true;
			}
			if (listadoAvaluosBean.isCrearRol()) {
				roles = true;
				admin = true;
			}
			if (listadoAvaluosBean.isCrearUsuario()) {
				usuarios = true;
			}
			if (listadoAvaluosBean.isExportarBUA()) {
				archivos = true;
			}
			if (listadoAvaluosBean.isExportarReportes()) {
				reportes = true;
			}
			if (listadoAvaluosBean.isVerAgenda()) {
				agenda = true;
			}
			if (listadoAvaluosBean.isEstudioMercado()) {
				estudio_mercado = true;
			}
			if (listadoAvaluosBean.isEditarCliente()) {
				editarCliente = Boolean.TRUE;
			}

			model = new DefaultMenuModel();

			DefaultSubMenu submenu = null;
			DefaultMenuItem item = null;

			submenu = new DefaultSubMenu("Principal:");
			submenu.setId("principal");
			submenu.setStyle("color: white;font-weight: normal;font-size: 14px;");
			model.addElement(submenu);

			item = new DefaultMenuItem("Lista Avalúos");
			item.setIcon("ui-icon-home");
			item.setCommand("#{menuBean.inicio()}");

			model.addElement(item);

			if (entidades || segmentos || sucursales || tarifas || roles || usuarios || editarCliente) {
				submenu = new DefaultSubMenu("Paginas:");
				submenu.setStyle("color: white;font-weight: normal;font-size: 14px;");

				if (entidades) {
					item = new DefaultMenuItem("Entidades");
					item.setIcon("ui-icon-home");
					item.setCommand("#{menuBean.entidades()}");
					submenu.addElement(item);
				}

				if (segmentos) {
					item = new DefaultMenuItem("Segmentos");
					item.setIcon("ui-icon-bookmark");
					item.setCommand("#{menuBean.segmentos()}");
					submenu.addElement(item);
				}

				if (sucursales) {
					item = new DefaultMenuItem("Sucursales");
					item.setIcon("ui-icon-home");
					item.setCommand("#{menuBean.sucursales()}");
					submenu.addElement(item);
				}

				if (tarifas) {
					item = new DefaultMenuItem("Tarifas");
					item.setIcon("ui-icon-tag");
					item.setCommand("#{menuBean.tarifas()}");
					submenu.addElement(item);
				}

				if (admin) {
					item = new DefaultMenuItem("Cargar UVR");
					item.setIcon("ui-icon-arrowthick-1-ne");
					item.setCommand("#{menuBean.tarifasUVR()}");
					submenu.addElement(item);
				}

				if (roles) {
					item = new DefaultMenuItem("Roles");
					item.setIcon("ui-icon-suitcase");
					item.setCommand("#{menuBean.roles()}");
					submenu.addElement(item);
				}

				if (usuarios) {
					item = new DefaultMenuItem("Usuarios");
					item.setIcon("ui-icon-person");
					item.setCommand("#{menuBean.usuarios()}");
					submenu.addElement(item);
				}

				if (listadoAvaluosBean.isCambiarFechaAprobacion()) {
					item = new DefaultMenuItem("Cambiar fecha de aprobación");
					item.setIcon("ui-icon-arrowthick-1-ne");
					item.setCommand("#{menuBean.cambiarFechaAprobacion()}");
					submenu.addElement(item);
				}

				if (editarCliente) {
					item = new DefaultMenuItem("Clientes");
					item.setIcon("ui-icon-arrowthick-1-ne");
					item.setCommand("#{menuBean.clientes()}");
					submenu.addElement(item);
				}

				model.addElement(submenu);
			}

			if (archivos) {
				submenu = new DefaultSubMenu("Archivos");
				submenu.setStyle("color: white;");

				item = new DefaultMenuItem("Exportar BUA");
				item.setIcon("ui-icon-document");
				item.setCommand("#{menuBean.exportarBUA()}");
				submenu.addElement(item);

				model.addElement(submenu);
			}
			if (reportes) {
				submenu = new DefaultSubMenu("Reportes");
				submenu.setStyle("color: white;");

				item = new DefaultMenuItem("Exportar reportes");
				item.setIcon("ui-icon-document");
				item.setCommand("#{menuBean.exportarReportes()}");
				submenu.addElement(item);

				model.addElement(submenu);
			}
			if (agenda) {
				submenu = new DefaultSubMenu("Agenda");
				submenu.setStyle("color: white;");

				item = new DefaultMenuItem("Ver Agenda");
				item.setIcon("ui-icon-calendar");
				item.setCommand("#{menuBean.verAgenda()}");
				submenu.addElement(item);

				model.addElement(submenu);
			}
			if (estudio_mercado) {
				submenu = new DefaultSubMenu("Consulta");
				submenu.setStyle("color: white;");

				item = new DefaultMenuItem("Estudio Mercado");
				item.setId("final");
				item.setIcon("ui-icon-search");
				item.setCommand("#{menuBean.estudioMercado()}");
				submenu.addElement(item);

				model.addElement(submenu);
			}

		} catch (Exception e) {

		}

	}

	public void inicio() {
		String uri = FacesContext.getCurrentInstance().getExternalContext()
				.encodeActionURL(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()
						+ "/pages/avaluos/listadoAvaluos.xhtml");
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(uri);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void entidades() {
		String uri = FacesContext.getCurrentInstance().getExternalContext()
				.encodeActionURL(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()
						+ "/pages/configuracion/entidades.xhtml");
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(uri);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void segmentos() {
		String uri = FacesContext.getCurrentInstance().getExternalContext()
				.encodeActionURL(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()
						+ "/pages/configuracion/segmentos.xhtml");
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(uri);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sucursales() {
		String uri = FacesContext.getCurrentInstance().getExternalContext()
				.encodeActionURL(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()
						+ "/pages/configuracion/sucursales.xhtml");
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(uri);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void tarifas() {
		String uri = FacesContext.getCurrentInstance().getExternalContext()
				.encodeActionURL(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()
						+ "/pages/configuracion/tarifas.xhtml");
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(uri);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void tarifasUVR() {
		String uri = FacesContext.getCurrentInstance().getExternalContext()
				.encodeActionURL(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()
						+ "/pages/configuracion/tarifaUVR.xhtml");
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(uri);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void exportarBUA() {
		String uri = FacesContext.getCurrentInstance().getExternalContext()
				.encodeActionURL(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()
						+ "/pages/avaluos/exportarArchivoBua.xhtml");
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(uri);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void exportarReportes() {
		String uri = FacesContext.getCurrentInstance().getExternalContext()
				.encodeActionURL(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()
						+ "/pages/avaluos/reportes.xhtml");
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(uri);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void verAgenda() {
		String uri = FacesContext.getCurrentInstance().getExternalContext().encodeActionURL(
				FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/pages/avaluos/agenda.xhtml");
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(uri);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void estudioMercado() {
		String uri = FacesContext.getCurrentInstance().getExternalContext()
				.encodeActionURL(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()
						+ "/pages/estudiosmercado.xhtml");
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(uri);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void roles() {
		String uri = FacesContext.getCurrentInstance().getExternalContext()
				.encodeActionURL(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()
						+ "/pages/configuracion/rol/listadoRoles.xhtml");
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(uri);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void usuarios() {
		String uri = FacesContext.getCurrentInstance().getExternalContext()
				.encodeActionURL(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()
						+ "/pages/configuracion/usuarios/listadoUsuarios.xhtml");
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(uri);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void cambiarContrasena() {
		// System.out.println("entre a recuperar en menuBean");
		String uri = FacesContext.getCurrentInstance().getExternalContext()
				.encodeActionURL(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()
						+ "/pages/recuperar/recuperarContrasena.xhtml");
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(uri);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void clientes() {
		// System.out.println("entre a recuperar en menuBean");
		String uri = FacesContext.getCurrentInstance().getExternalContext()
				.encodeActionURL(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()
						+ "/pages/configuracion/clientes/editarCliente.xhtml");
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(uri);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void cambiarFechaAprobacion() {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		try {
			context.redirect(context.getRequestContextPath() + "/pages/avaluos/actualizarFechaAprobacion.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public MenuModel getModel() {
		return model;
	}

	public void setModel(MenuModel model) {
		this.model = model;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

}
