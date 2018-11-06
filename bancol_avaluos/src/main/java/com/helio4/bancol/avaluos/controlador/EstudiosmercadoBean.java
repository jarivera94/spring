package com.helio4.bancol.avaluos.controlador;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

import javax.annotation.PostConstruct;

import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.Circle;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.helio4.bancol.avaluos.dominio.AvaluoController;
import com.helio4.bancol.avaluos.dominio.ListasController;
import com.helio4.bancol.avaluos.dominio.ListasGeograficasController;
import com.helio4.bancol.avaluos.dominio.ParametrosController;
import com.helio4.bancol.avaluos.dto.AvaluoConsultaDTO;
import com.helio4.bancol.avaluos.dto.DivipolaDTO;
import com.helio4.bancol.avaluos.dto.TipoInmuebleDTO;

@Controller
@Scope("view")
@Qualifier("estudiosmercadoBean")
public class EstudiosmercadoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_BUFER_SIZE = 10240;

	private AvaluoConsultaDTO avaluoConsultaDTO;

	@Autowired
	private AvaluoController avaluoController;
	@Autowired
	private ListasController listasController;
	@Autowired
	private ListasGeograficasController listasGeograficasController;
	@Autowired
	ParametrosController parametrosController;

	private List<TipoInmuebleDTO> tiposInmuebles;
	private SortedMap<String, String> departamentos;
	private String departamento;
	private List<DivipolaDTO> ciudades;
	private int sector;

	private boolean busqDireccion;
	private boolean busqMapa;

	private String direccion;

	private String centroMapa;
	private MapModel mapModel;
	private BigDecimal latitudInit;
	private BigDecimal longitudInit;
	private BigDecimal latitud;
	private BigDecimal longitud;
	private Integer kilometrosRedonda;

	private String ph;
	private boolean panelPH;
	private boolean inmuebleUrbNoPH;
	private boolean inmuebleUrbPH;
	private boolean inmuebleRur;

	private String estadoInmNPH = "";
	private String estadoInmPH = "";

	private boolean edadInm;

	private List<AvaluoConsultaDTO> avaluosEncontrados;
	private boolean mostrarMapaResultado;
	private boolean mostrarTablaResultadoUrbPH;
	private boolean mostrarTablaResultadoUrbNPH;
	private boolean mostrarTablaResultadoRural;
	private boolean mostrarSinResultados;

	// ---------------VARIABLES RESPUESTAS----------------------------
	private String resCentroMapa;
	private MapModel resMapModel;
	private Marker marcadorGMapSeleccionado;
	private String path;
	// ---------------------------------------------------------------

	private final String[] letrasComplemento = { "A", "B", "C", "D", "E", "F",
			"G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S",
			"T", "U", "V", "W", "X", "Y", "Z" };

	private final String[] puntosCardinales = { "Norte", "Sur", "Este",
			"Oeste", "Bis", "Noreste", "Sureste", "Noroeste", "Suroeste",
			"Par", "Impar" };

	@PostConstruct
	public void init() {
		avaluoConsultaDTO = new AvaluoConsultaDTO();
		tiposInmuebles = listasController.tiposInmueble();
		departamentos = listasGeograficasController.departamentos();

		mapModel = new DefaultMapModel();
		LatLng coordenadas = new LatLng(3.523719, -73.305448);
		Marker marker = new Marker(coordenadas, "Marcador");
		marker.setDraggable(true);
		mapModel.addOverlay(marker);
		centroMapa = Double.toString(coordenadas.getLat()) + ","
				+ Double.toString(coordenadas.getLng());
		resCentroMapa = centroMapa;
		// latitudInit = BigDecimal.valueOf(coordenadas.getLat());
		// longitudInit = BigDecimal.valueOf(coordenadas.getLng());

		resMapModel = new DefaultMapModel();
		path = parametrosController.obtenerValor("rutaImagenes");
	}

	public void onDepartamentoChanged() {
		setCiudades(listasGeograficasController
				.ciudadesPorDepartamento(departamento));
	}

	public void onDireccionChanged() {
		avaluoConsultaDTO.setComplementoVia(avaluoConsultaDTO
				.getComplementoVia());
		avaluoConsultaDTO.setComplementoViaGeneradora(avaluoConsultaDTO
				.getComplementoViaGeneradora());
		direccion = avaluoConsultaDTO
				.getTipoVia()
				.concat(" " + (avaluoConsultaDTO.getNumeroVia() == 0 ? "" : " "
						+ avaluoConsultaDTO.getNumeroVia()))
				.concat(avaluoConsultaDTO.getComplementoVia() == null ? "" : " "
						+ avaluoConsultaDTO.getComplementoVia().replace("[","").replace("]","").replace(",", ""))
				.concat(avaluoConsultaDTO.getNumeroViaGeneradora() == 0 ? ""
						: " # " + avaluoConsultaDTO.getNumeroViaGeneradora())
				.concat(avaluoConsultaDTO.getComplementoViaGeneradora() == null ? ""
						: " " + avaluoConsultaDTO.getComplementoViaGeneradora().replace("[","").replace("]","").replace(",", ""))
				.concat(avaluoConsultaDTO.getPlaca() == 0 ? "" : " - "
						+ avaluoConsultaDTO.getPlaca())
				.concat(avaluoConsultaDTO.getAdicional() == null ? "" : " "
						+ avaluoConsultaDTO.getAdicional());
		if (!"".equals(direccion)) {
			if (direccion.contains("null")) {
				direccion.split("null")[0].trim();
			}
		}
		avaluoConsultaDTO.setDireccion(direccion);
		//System.out.println("Dirección: " + avaluoConsultaDTO.getDireccion());
	}

	// private String convertirAString(List<String> list) {
	// if(list != null){
	// if(list.isEmpty()){
	// return "";
	// }else{
	// return list.toString().replace("[", "").replace("]", "")
	// .replace(",", "");
	// }
	// }
	// return "";
	// }

	public List<String> completarComplemento(String valor) {
		List<String> resultado = new ArrayList<String>();
		for (String letra : letrasComplemento) {
			if (letra.startsWith(valor.toUpperCase())) {
				resultado.add(letra);
			}
		}
		for (String punto : puntosCardinales) {
			if (punto.startsWith(valor.toUpperCase())) {
				resultado.add(punto);
			}
		}
		return resultado;
	}

	public void onMarkerDrag(MarkerDragEvent event) {
		Marker marker = event.getMarker();
		latitud = BigDecimal.valueOf(marker.getLatlng().getLat());
		longitud = BigDecimal.valueOf(marker.getLatlng().getLng());
	}

	public void sectorSeleccionado() {
		if (sector == 1) {
			busqDireccion = true;
			panelPH = true;
			inmuebleRur = false;
		} else if (sector == 2) {
			busqDireccion = false;
			panelPH = false;
			inmuebleUrbNoPH = false;
			inmuebleUrbPH = false;
			inmuebleRur = true;
		} else {
			busqDireccion = false;
			panelPH = false;
			inmuebleUrbNoPH = false;
			inmuebleUrbPH = false;
			inmuebleRur = false;
		}
	}

	public void propiedadHorizontal() {
		if (ph.equals("si")) {
			inmuebleUrbNoPH = false;
			inmuebleUrbPH = true;
		} else {
			inmuebleUrbNoPH = true;
			inmuebleUrbPH = false;
		}
	}

	public void mostrarEdadInmb() {
		if ((estadoInmNPH != null) && (estadoInmPH != null)) {
			if (estadoInmNPH.equals("usado") || estadoInmPH.equals("usado")) {
				edadInm = true;
			} else {
				edadInm = false;
			}
		}
	}

	public void consultar() {
		avaluoConsultaDTO.setDepartamento(departamento);
		avaluoConsultaDTO.setSector(sector);
		avaluoConsultaDTO.setLatitud(latitud);
		avaluoConsultaDTO.setLongitud(longitud);
		avaluoConsultaDTO.setKilometrosRedonda(kilometrosRedonda);
		avaluoConsultaDTO.setPh(ph);
		avaluoConsultaDTO.setEstadoInmNPH(estadoInmNPH);
		avaluoConsultaDTO.setEstadoInmPH(estadoInmPH);

		avaluosEncontrados = avaluoController
				.consultarAvaluosParaEstudios(avaluoConsultaDTO);

		if (avaluosEncontrados != null) {
			if (avaluosEncontrados.size() != 0) {
				mostrarSinResultados = false;
				mostrarMapaResultado = true;
				if (avaluosEncontrados.get(0).isEsUrbanoPH()) {
					mostrarTablaResultadoUrbPH = true;
				} else if (avaluosEncontrados.get(0).isEsUrbanoNPH()) {
					mostrarTablaResultadoUrbNPH = true;
				} else if (avaluosEncontrados.get(0).isEsRural()) {
					mostrarTablaResultadoRural = true;
				}
				generarMarkersMapaResultado(avaluosEncontrados);
				interpretarValores(avaluosEncontrados);
			} else {
				mostrarSinResultados = true;
				mostrarMapaResultado = false;
				mostrarTablaResultadoUrbPH = false;
				mostrarTablaResultadoUrbNPH = false;
				mostrarTablaResultadoRural = false;
			}
		}
	}

	public void generarMarkersMapaResultado(
			List<AvaluoConsultaDTO> avaluosEncontrados) {
		for (AvaluoConsultaDTO avaluoConsultaDTO : avaluosEncontrados) {
			LatLng coordMarcador = new LatLng(avaluoConsultaDTO
					.getResUrbPHlatitud().doubleValue(), avaluoConsultaDTO
					.getResUrbPHlongitud().doubleValue());
			resMapModel.addOverlay(new Marker(coordMarcador, "Avaluo: "
					+ avaluoConsultaDTO.getResIdAvaluo()));
			Circle circle = new Circle(coordMarcador,
					(kilometrosRedonda != null) ? kilometrosRedonda : 0);
			circle.setFillColor("#D18F8F");
			circle.setFillOpacity(0.5);
			resMapModel.addOverlay(circle);
			resCentroMapa = Double.toString(coordMarcador.getLat()) + ","
					+ Double.toString(coordMarcador.getLng());
		}
	}

	public void interpretarValores(List<AvaluoConsultaDTO> avaluoConsultaDTOs) {
		for (AvaluoConsultaDTO avaluoConsultaDTO : avaluoConsultaDTOs) {
			switch (avaluoConsultaDTO.getResTipoInmueble()) {
			case 2:
				avaluoConsultaDTO.setResNombreTipoInmueble("Apartamento");
				break;
			case 4:
				avaluoConsultaDTO.setResNombreTipoInmueble("Casa");
				break;
			case 5:
				avaluoConsultaDTO.setResNombreTipoInmueble("Bodega");
				break;
			case 6:
				avaluoConsultaDTO.setResNombreTipoInmueble("Local");
				break;
			case 7:
				avaluoConsultaDTO.setResNombreTipoInmueble("Oficina");
				break;
			case 12:
				avaluoConsultaDTO.setResNombreTipoInmueble("Lote");
				break;
			case 13:
				avaluoConsultaDTO.setResNombreTipoInmueble("Casa Rural");
				break;
			case 15:
				avaluoConsultaDTO.setResNombreTipoInmueble("Lote Urbano");
				break;
			}
			switch (avaluoConsultaDTO.getResUrbPHestadoConstruccion()) {
			case 1:
				avaluoConsultaDTO.setResUrbPHnombreEstadoConstruccion("Nueva");
				break;
			case 2:
				avaluoConsultaDTO.setResUrbPHnombreEstadoConstruccion("Usada");
				break;
			}
		}
	}

	public void onMarkerSelect(OverlaySelectEvent event) {
		marcadorGMapSeleccionado = (Marker) event.getOverlay();
	}

	public void verInforme() throws IOException {
		String ruta = "";
		ruta = this.path;
		ruta += marcadorGMapSeleccionado.getTitle().split(":")[1].trim()
				+ ".pdf";

		File file = new File(ruta);

		boolean esWindows = false;
		boolean esLinux = false;
		boolean esMac = false;
		String os = System.getProperty("os.name").toLowerCase();
		//System.out.println("sistema operativo: " + os);
		esWindows = os.contains("win");
		esLinux = os.contains("nux") || os.contains("nix");
		esMac = os.contains("mac");

		if (esWindows) {
			Runtime.getRuntime().exec(
					new String[] { "rundll32", "url.dll,FileProtocolHandler",
							file.getAbsolutePath() });
		} else if (esLinux || esMac) {
			Runtime.getRuntime().exec(
					new String[] { "/usr/bin/open", file.getAbsolutePath() });
		} else {
			if (Desktop.isDesktopSupported()) {
				Desktop.getDesktop().open(file);
			} else {
				System.out.println("No se pudo abrir el archivo");
			}
		}
	}

	// ----------------getter and setter -----------------------------------

	public List<TipoInmuebleDTO> getTiposInmuebles() {
		return tiposInmuebles;
	}

	public void setTiposInmuebles(List<TipoInmuebleDTO> tiposInmuebles) {
		this.tiposInmuebles = tiposInmuebles;
	}

	public SortedMap<String, String> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(SortedMap<String, String> departamentos) {
		this.departamentos = departamentos;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public int getSector() {
		return sector;
	}

	public void setSector(int sector) {
		this.sector = sector;
	}

	public boolean isBusqDireccion() {
		return busqDireccion;
	}

	public void setBusqDireccion(boolean busqDireccion) {
		this.busqDireccion = busqDireccion;
	}

	public boolean isBusqMapa() {
		return busqMapa;
	}

	public void setBusqMapa(boolean busqMapa) {
		this.busqMapa = busqMapa;
	}

	public String getCentroMapa() {
		return centroMapa;
	}

	public void setCentroMapa(String centroMapa) {
		this.centroMapa = centroMapa;
	}

	public MapModel getMapModel() {
		return mapModel;
	}

	public void setMapModel(MapModel mapModel) {
		this.mapModel = mapModel;
	}

	public BigDecimal getLatitud() {
		return latitud;
	}

	public void setLatitud(BigDecimal latitud) {
		this.latitud = latitud;
	}

	public BigDecimal getLongitud() {
		return longitud;
	}

	public void setLongitud(BigDecimal longitud) {
		this.longitud = longitud;
	}

	public String getPh() {
		return ph;
	}

	public void setPh(String ph) {
		this.ph = ph;
	}

	public boolean isPanelPH() {
		return panelPH;
	}

	public void setPanelPH(boolean panelPH) {
		this.panelPH = panelPH;
	}

	public boolean isInmuebleUrbNoPH() {
		return inmuebleUrbNoPH;
	}

	public void setInmuebleUrbNoPH(boolean inmuebleUrbNoPH) {
		this.inmuebleUrbNoPH = inmuebleUrbNoPH;
	}

	public boolean isInmuebleUrbPH() {
		return inmuebleUrbPH;
	}

	public void setInmuebleUrbPH(boolean inmuebleUrbPH) {
		this.inmuebleUrbPH = inmuebleUrbPH;
	}

	public boolean isInmuebleRur() {
		return inmuebleRur;
	}

	public void setInmuebleRur(boolean inmuebleRur) {
		this.inmuebleRur = inmuebleRur;
	}

	public String getEstadoInmNPH() {
		return estadoInmNPH;
	}

	public void setEstadoInmNPH(String estadoInmNPH) {
		this.estadoInmNPH = estadoInmNPH;
	}

	public String getEstadoInmPH() {
		return estadoInmPH;
	}

	public void setEstadoInmPH(String estadoInmPH) {
		this.estadoInmPH = estadoInmPH;
	}

	public boolean isEdadInm() {
		return edadInm;
	}

	public void setEdadInm(boolean edadInm) {
		this.edadInm = edadInm;
	}

	public AvaluoConsultaDTO getAvaluoConsultaDTO() {
		return avaluoConsultaDTO;
	}

	public void setAvaluoConsultaDTO(AvaluoConsultaDTO avaluoConsultaDTO) {
		this.avaluoConsultaDTO = avaluoConsultaDTO;
	}

	public List<DivipolaDTO> getCiudades() {
		return ciudades;
	}

	public void setCiudades(List<DivipolaDTO> ciudades) {
		this.ciudades = ciudades;
	}

	public List<AvaluoConsultaDTO> getAvaluosEncontrados() {
		return avaluosEncontrados;
	}

	public void setAvaluosEncontrados(List<AvaluoConsultaDTO> avaluosEncontrados) {
		this.avaluosEncontrados = avaluosEncontrados;
	}

	public Integer getKilometrosRedonda() {
		return kilometrosRedonda;
	}

	public void setKilometrosRedonda(Integer kilometrosRedonda) {
		this.kilometrosRedonda = kilometrosRedonda;
	}

	public BigDecimal getLatitudInit() {
		return latitudInit;
	}

	public void setLatitudInit(BigDecimal latitudInit) {
		this.latitudInit = latitudInit;
	}

	public BigDecimal getLongitudInit() {
		return longitudInit;
	}

	public void setLongitudInit(BigDecimal longitudInit) {
		this.longitudInit = longitudInit;
	}

	public String getResCentroMapa() {
		return resCentroMapa;
	}

	public void setResCentroMapa(String resCentroMapa) {
		this.resCentroMapa = resCentroMapa;
	}

	public MapModel getResMapModel() {
		return resMapModel;
	}

	public void setResMapModel(MapModel resMapModel) {
		this.resMapModel = resMapModel;
	}

	public boolean isMostrarMapaResultado() {
		return mostrarMapaResultado;
	}

	public void setMostrarMapaResultado(boolean mostrarMapaResultado) {
		this.mostrarMapaResultado = mostrarMapaResultado;
	}

	public boolean isMostrarTablaResultadoUrbPH() {
		return mostrarTablaResultadoUrbPH;
	}

	public void setMostrarTablaResultadoUrbPH(boolean mostrarTablaResultadoUrbPH) {
		this.mostrarTablaResultadoUrbPH = mostrarTablaResultadoUrbPH;
	}

	public boolean isMostrarTablaResultadoUrbNPH() {
		return mostrarTablaResultadoUrbNPH;
	}

	public void setMostrarTablaResultadoUrbNPH(
			boolean mostrarTablaResultadoUrbNPH) {
		this.mostrarTablaResultadoUrbNPH = mostrarTablaResultadoUrbNPH;
	}

	public boolean isMostrarTablaResultadoRural() {
		return mostrarTablaResultadoRural;
	}

	public void setMostrarTablaResultadoRural(boolean mostrarTablaResultadoRural) {
		this.mostrarTablaResultadoRural = mostrarTablaResultadoRural;
	}

	public Marker getMarcadorGMapSeleccionado() {
		return marcadorGMapSeleccionado;
	}

	public void setMarcadorGMapSeleccionado(Marker marcadorGMapSeleccionado) {
		this.marcadorGMapSeleccionado = marcadorGMapSeleccionado;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isMostrarSinResultados() {
		return mostrarSinResultados;
	}

	public void setMostrarSinResultados(boolean mostrarSinResultados) {
		this.mostrarSinResultados = mostrarSinResultados;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

}
