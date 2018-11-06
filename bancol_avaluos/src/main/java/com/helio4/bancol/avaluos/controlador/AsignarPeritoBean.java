package com.helio4.bancol.avaluos.controlador;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SortedMap;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.helio4.bancol.avaluos.dominio.AsignarPeritoController;
import com.helio4.bancol.avaluos.dominio.ListasGeograficasController;
import com.helio4.bancol.avaluos.dto.AvaluoDTO;
import com.helio4.bancol.avaluos.dto.DivipolaDTO;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;
import com.helio4.bancol.avaluos.servicio.excepciones.DivipolaNotFoundException;

@Controller
@Scope("view")
@Qualifier("asignarPeritoBean")
public class AsignarPeritoBean implements Serializable {

	private static Logger log = LoggerFactory.getLogger(AsignarPeritoBean.class);
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private AvaluoDTO avaluo;
	private String nombresApellidos;

	private List<UsuarioDTO> peritos;
	private UsuarioDTO perito;

	private SortedMap<String, String> departamentos;
	private List<DivipolaDTO> ciudades;
	private String departamentoBusqueda;
	private DivipolaDTO divipolaBusqueda;

	private int diasProximaCita;
	private int cercaniaCuadrasRedonda;

	private ScheduleModel eventModel;

	private boolean editable;

	/**
	 * Sección de injección de controlador
	 */
	@Autowired
	private AsignarPeritoController asignarPeritoController;
	@Autowired
	private ListasGeograficasController listasGeograficasController;

	@Autowired
	private ListadoAvaluosBean listadoAvaluosBean;

	/** Opciones por las que se puede buscar un perito */
	private List<String> opcionesBusquedaPerito;

	/** Opcion seleccionada por el usuario para buscar un perito */
	private String opcionBusquedaPerito;

	/** Nombre del perito a buscar */
	private String peritoABuscar;

	public final static String OPCION_BUSQUEDA_MUNICIPIO = "Municipio";
	public final static String OPCION_BUSQUEDA_DEPARTAMENTO = "Departamento";
	public final static String OPCION_BUSQUEDA_PAIS = "Pais";
	public final static String OPCION_BUSQUEDA_PERITO = "Perito";

	@PostConstruct
	public void init() {
		try {
			log.debug("Inicializando AsignarPeritoBean: ");
			avaluo = listadoAvaluosBean.getAvaluo();
			if (avaluo == null || avaluo.getId() == null) {
				throw new Exception("No hay ningun avaluo seleccionado");
			}
			departamentos = listasGeograficasController.departamentos();
			departamentoBusqueda = avaluo.getDivipola().getDepartamento();
			ciudades = new ArrayList<DivipolaDTO>();
			ciudades.addAll(listasGeograficasController.ciudadesEnDepartamento(departamentoBusqueda));
			divipolaBusqueda = avaluo.getDivipola();

			cercaniaCuadrasRedonda = new Integer(0);
			peritos = asignarPeritoController.buscarPeritos(null, avaluo);
			// perito = peritos.isEmpty() ? new UsuarioDTO() : peritos.iterator().next();
			inicializarEventos();

			this.setOpcionesBusquedaPerito(new ArrayList<String>());
			String opcionesBuesqueda[] = { "Seleccione", OPCION_BUSQUEDA_MUNICIPIO, OPCION_BUSQUEDA_DEPARTAMENTO,
					OPCION_BUSQUEDA_PAIS, OPCION_BUSQUEDA_PERITO };
			this.getOpcionesBusquedaPerito().addAll(Arrays.asList(opcionesBuesqueda));
		} catch (Exception e) {
			log.error(e.getMessage());
		}

	}

	public void inicializarEventos() {
		// Inicializar eventos de los peritos
		if (peritos != null && !peritos.isEmpty()) {
			eventModel = new DefaultScheduleModel(asignarPeritoController.eventosPeritos(peritos));
		} else {
			eventModel = new DefaultScheduleModel();
		}
	}

	public void buscarPeritos() {
		if (this.opcionBusquedaPerito == null || this.opcionBusquedaPerito.equals("Seleccione")) {
			if (diasProximaCita > 0 && cercaniaCuadrasRedonda > 0) {
				peritos = asignarPeritoController.buscarPeritosDireccionCita(divipolaBusqueda, cercaniaCuadrasRedonda,
						diasProximaCita, avaluo);
			} else if (diasProximaCita > 0) {
				this.peritos = this.asignarPeritoController.buscarPeritosDias(divipolaBusqueda, cercaniaCuadrasRedonda,
						diasProximaCita, avaluo);
			} else if (cercaniaCuadrasRedonda > 0) {
				peritos = asignarPeritoController.buscarPeritosDireccion(divipolaBusqueda, cercaniaCuadrasRedonda, avaluo);
			} else {
				peritos = asignarPeritoController.buscarPeritos(divipolaBusqueda, avaluo);
			}

			if (peritos != null && !peritos.isEmpty()) {
				eventModel = new DefaultScheduleModel(asignarPeritoController.eventosPeritos(peritos));
			} else {
				eventModel = new DefaultScheduleModel();
			}
		} else {
			if (this.opcionBusquedaPerito.equals(OPCION_BUSQUEDA_MUNICIPIO)) {
				try {
					this.peritos = this.asignarPeritoController.buscarPeritosDisponibles(this.divipolaBusqueda);
				} catch (DivipolaNotFoundException e) {
					e.printStackTrace();
				}
			} else if (this.opcionBusquedaPerito.equals(OPCION_BUSQUEDA_DEPARTAMENTO)) {
				this.peritos = this.asignarPeritoController.buscarPeritosDisponibles(this.departamentoBusqueda);
			} else if (this.opcionBusquedaPerito.equals(OPCION_BUSQUEDA_PAIS)) {
				this.peritos = this.asignarPeritoController.buscarPeritosDisponibles();
			} else if (this.opcionBusquedaPerito.equals(OPCION_BUSQUEDA_PERITO)) {
				this.peritos = this.asignarPeritoController.buscarPeritosDisponiblesNombre(this.peritoABuscar);
			}
			inicializarEventos();
		}
	}

	public int casosAbiertos(UsuarioDTO usuarioDTO) {
		return asignarPeritoController.casosAbiertos(usuarioDTO);
	}

	public int casosAsignadosSemana(UsuarioDTO usuarioDTO) {
		return asignarPeritoController.casosAsignadosSemana(usuarioDTO);
	}

	public int casosAsignadosMes(UsuarioDTO usuarioDTO) {
		return asignarPeritoController.casosAsignadosMes(usuarioDTO);
	}

	public void cancelar() {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		String uri = context.encodeActionURL(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()
				+ "/pages/avaluos/listadoAvaluos.xhtml");
		try {
			context.redirect(uri);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public AvaluoDTO getAvaluo() {
		return avaluo;
	}

	public void setAvaluo(AvaluoDTO avaluo) {
		this.avaluo = avaluo;
	}

	public String getNombresApellidos() {
		return nombresApellidos;
	}

	public void setNombresApellidos(String nombresApellidos) {
		this.nombresApellidos = nombresApellidos;
	}

	public List<UsuarioDTO> getPeritos() {
		return peritos;
	}

	public void setPeritos(List<UsuarioDTO> peritos) {
		this.peritos = peritos;
	}

	public UsuarioDTO getPerito() {
		return perito;
	}

	public void setPeritos(UsuarioDTO perito) {
		this.perito = perito;
	}

	public SortedMap<String, String> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(SortedMap<String, String> departamentos) {
		this.departamentos = departamentos;
	}

	public List<DivipolaDTO> getCiudades() {
		return ciudades;
	}

	public void setCiudades(List<DivipolaDTO> ciudades) {
		this.ciudades = ciudades;
	}

	public String getDepartamentoBusqueda() {
		return departamentoBusqueda;
	}

	public void setDepartamentoBusqueda(String departamentoBusqueda) {
		this.departamentoBusqueda = departamentoBusqueda;
	}

	public DivipolaDTO getDivipolaBusqueda() {
		return divipolaBusqueda;
	}

	public void setDivipolaBusqueda(DivipolaDTO divipolaBusqueda) {
		this.divipolaBusqueda = divipolaBusqueda;
	}

	public int getDiasProximaCita() {
		return this.diasProximaCita;
	}

	public int getCercaniaCuadrasRedonda() {
		return this.cercaniaCuadrasRedonda;
	}

	public void setDiasProximaCita(int diasProximaCita) {
		this.diasProximaCita = diasProximaCita;
	}

	public void setCercaniaCuadrasRedonda(int cercaniaCuadrasRedonda) {
		this.cercaniaCuadrasRedonda = cercaniaCuadrasRedonda;
	}

	public ScheduleModel getEventModel() {
		return eventModel;
	}

	public void setEventModel(ScheduleModel eventModel) {
		this.eventModel = eventModel;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public void onDepartamentoChanged() {
		ciudades = listasGeograficasController.ciudadesPorDepartamento(departamentoBusqueda);
	}

	/**
	 * Se elige el avaluo seleccionado en la tabla para asignar el perito
	 */
	public void asignarPerito() {
		if (avaluo.getId() != null && perito != null) {
			if (!asignarPeritoController.asignarPerito(avaluo, perito, listadoAvaluosBean.getUsuarioActivo())) {
				listadoAvaluosBean.setMensajeError("No se pudo asignar el perito " + "ocurrio un error.");
			}

			listadoAvaluosBean.setSolicitudAsignada(avaluo.getCodigoExterno());

			String uri = FacesContext.getCurrentInstance().getExternalContext()
					.encodeActionURL(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()
							+ "/pages/avaluos/listadoAvaluos.xhtml");
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect(uri);
			} catch (IOException e) {
				// TODO Reireccionar a página no encontrada
				e.printStackTrace();
			}
		} else {
			FacesContext.getCurrentInstance().addMessage("growl",
					new FacesMessage("Error", "No se pudo asignar el perito debe " + "seleccionar un perito."));
		}
	}

	public void setPerito(UsuarioDTO perito) {
		this.perito = perito;
	}

	public String getOpcionBusquedaPerito() {
		return opcionBusquedaPerito;
	}

	public void setOpcionBusquedaPerito(String opcionBuesquedaPerito) {
		this.opcionBusquedaPerito = opcionBuesquedaPerito;
	}

	public List<String> getOpcionesBusquedaPerito() {
		return opcionesBusquedaPerito;
	}

	public void setOpcionesBusquedaPerito(List<String> opcionesBusquedaPerito) {
		this.opcionesBusquedaPerito = opcionesBusquedaPerito;
	}

	public String getPeritoABuscar() {
		return peritoABuscar;
	}

	public void setPeritoABuscar(String peritoABuscar) {
		this.peritoABuscar = peritoABuscar;
	}

}
