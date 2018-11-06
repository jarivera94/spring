package com.helio4.bancol.avaluos.controlador;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.helio4.bancol.avaluos.dominio.CalificacionHomologacionController;
import com.helio4.bancol.avaluos.dominio.ParametrosController;
import com.helio4.bancol.avaluos.dominio.PisoFactorController;
import com.helio4.bancol.avaluos.dto.AreaDTO;
import com.helio4.bancol.avaluos.dto.CalificacionHomologacionDTO;
import com.helio4.bancol.avaluos.dto.ComparacionMercadoLoteConstruccionVentaDTO;
import com.helio4.bancol.avaluos.dto.FormatoInformeHipotecarioDTO;
import com.helio4.bancol.avaluos.dto.MetodoValuacionDTO;
import com.helio4.bancol.avaluos.dto.MetodoValuacionDTO.MetodoValuacionEnum;
import com.helio4.bancol.avaluos.dto.OfertaDTO;
import com.helio4.bancol.avaluos.dto.OfertaLoteConConstruccionVentaDTO;
import com.helio4.bancol.avaluos.dto.PisoFactorDTO;

@Controller
@Scope("view")
@Qualifier("comparacionMercadoLoteConstruccionVentaBean")
public class ComparacionMercadoLoteConstruccionVentaBean {

	private FormatoInformeHipotecarioDTO informeHipotecarioDTO;

	private ComparacionMercadoLoteConstruccionVentaDTO metodoValuacion;

	private boolean nuevaOferta;

	private BigDecimal diferenciaFormulaSencilla;
	private BigDecimal porcentajeFormulaSencilla;
	private BigDecimal diferenciaFormulaTabla;
	private BigDecimal porcentajeFormulaTabla;

	private HashMap<Long, CalificacionHomologacionDTO> calificacionesHomologacion;

	private List<PisoFactorDTO> pisosFactor;

	private ArrayList<BigDecimal> factores;
	private ArrayList<BigDecimal> factoresNegociacion;
	private ArrayList<BigDecimal> factoresEdad;

	private List<AreaDTO> areas;

	@Autowired
	private CalificacionHomologacionController calificacionHomologacionController;

	@Autowired
	ParametrosController parametrosController;

	@Autowired
	private PisoFactorController pisoFactorController;

	@PostConstruct
	public void inicializar() {

		metodoValuacion = new ComparacionMercadoLoteConstruccionVentaDTO();
		metodoValuacion.setMetodoUsado(MetodoValuacionEnum.ComparaciondeMercadoLoteVentaConstruccion);

		this.factores = new ArrayList<>();
		for (double i = 0.14; i >= 0.05; i -= 0.01) {
			BigDecimal value = new BigDecimal(i);
			value = value.setScale(2, RoundingMode.HALF_EVEN);
			this.factores.add(value);
		}

		List<CalificacionHomologacionDTO> ch = calificacionHomologacionController.encontrarCalificacionesHomologacion();
		calificacionesHomologacion = new HashMap<Long, CalificacionHomologacionDTO>();
		for (int i = 0; i < ch.size(); i++) {
			calificacionesHomologacion.put(ch.get(i).getId(), ch.get(i));
		}
		
		factoresNegociacion = new ArrayList<>();
		for (double i = 0.80; i < 1.01; i +=0.01) {
			BigDecimal value = new BigDecimal(i);
			value = value.setScale(2, RoundingMode.HALF_EVEN);
			this.factoresNegociacion.add(value);
		}
		
		factoresEdad =  new ArrayList<>();
		factoresEdad.add(BigDecimal.valueOf(0.01));
		factoresEdad.add(BigDecimal.valueOf(0.02));

		cargarListasParametros();

		pisosFactor = pisoFactorController.encontrarPisosFactor();

	}

	private void cargarListasParametros() {
		diferenciaFormulaSencilla = new BigDecimal(
				Float.parseFloat(parametrosController.obtenerValor("Diferencia Fórmula Sencilla")));
		porcentajeFormulaSencilla = new BigDecimal(
				Float.parseFloat(parametrosController.obtenerValor("Porcentaje Fórmula Sencilla")));
		diferenciaFormulaTabla = new BigDecimal(
				Float.parseFloat(parametrosController.obtenerValor("Diferencia Fórmula Tabla")));
		porcentajeFormulaTabla = new BigDecimal(
				Float.parseFloat(parametrosController.obtenerValor("Porcentaje Fórmula Tabla")));

		diferenciaFormulaSencilla.setScale(2, RoundingMode.HALF_EVEN);
		porcentajeFormulaSencilla.setScale(2, RoundingMode.HALF_EVEN);
		diferenciaFormulaTabla.setScale(2, RoundingMode.HALF_EVEN);
		porcentajeFormulaTabla.setScale(2, RoundingMode.HALF_EVEN);
	}

	public void adicionarOferta() {

		if (metodoValuacion == null) {
			metodoValuacion = new ComparacionMercadoLoteConstruccionVentaDTO();
		}

		if (metodoValuacion.getOfertasConstruccionVenta() == null) {
			metodoValuacion.setOfertasConstruccionVenta(new ArrayList<>());
		}

		metodoValuacion.getOfertasConstruccionVenta().add(new OfertaLoteConConstruccionVentaDTO());

		nuevaOferta = true;
	}

	public void calcular() {

		if (metodoValuacion != null && metodoValuacion.getOfertasConstruccionVenta() != null
				&& !metodoValuacion.getOfertasConstruccionVenta().isEmpty()) {

			for (OfertaLoteConConstruccionVentaDTO ofertaConstruccionVenta : metodoValuacion
					.getOfertasConstruccionVenta()) {
				ofertaConstruccionVenta.calcularFactorHomologacionEdad(metodoValuacion.getEdad(),
						metodoValuacion.getFactorHomogenizacionEdad());

				ofertaConstruccionVenta.calcularCuadroCalificacion();
				ofertaConstruccionVenta.calcularFactorHomologacionConstruccion(metodoValuacion.getAreaConsttruida(),
						metodoValuacion.getAreaLote(), metodoValuacion.getFactorHomogenizacionConstruccionTerreno());
				ofertaConstruccionVenta.calcularFactorHomologacionLote(metodoValuacion.getFactorHomogenizacionLote(), metodoValuacion.getAreaLote());

				if (ofertaConstruccionVenta.getUbicacion() != null
						&& ofertaConstruccionVenta.getFactorHomologacionConservacion() != null
						&& ofertaConstruccionVenta.getAcabados() != null) {

					CalificacionHomologacionDTO ubi = calificacionesHomologacion
							.get(ofertaConstruccionVenta.getUbicacion().getLongKey());
					CalificacionHomologacionDTO con = calificacionesHomologacion
							.get(ofertaConstruccionVenta.getFactorHomologacionConservacion().getLongKey());
					CalificacionHomologacionDTO aca = calificacionesHomologacion
							.get(ofertaConstruccionVenta.getAcabados().getLongKey());

					if (ubi != null && con != null && aca != null) {
						ofertaConstruccionVenta.calcularCuadroCalificacion(ubi.getFactor(), con.getFactor(),
								aca.getFactor());
					}
				}

				ofertaConstruccionVenta.calcularFactorHomogenizacion(ofertaConstruccionVenta.getCuadroCalificacion(),
						ofertaConstruccionVenta.getNegociacion(), ofertaConstruccionVenta.getEdadHomogenizada(),
						ofertaConstruccionVenta.getFactorHomologacionConstruccion(),
						ofertaConstruccionVenta.getFactorHomologacionLote());

				ofertaConstruccionVenta.calcularAreaconstruccionAreaTerreno();
				ofertaConstruccionVenta.calcularValorM2Ajustado();
				ofertaConstruccionVenta.calcularPrecioUnitarioM2();
				ofertaConstruccionVenta.calcularPrecioUnitarioM2Homogenizado();

			}

		}
		metodoValuacion.calcularPromedioValorComercial();
		metodoValuacion.calcularPromedioValorM2AjustadoLoteSinConstruccion();
		metodoValuacion.calcularAreaConstruccionAreaTerrenoSujeto();
		metodoValuacion.calcularPromedioPrecioUnitario();
		metodoValuacion.calcularPromedioPrecioUnitarioM2Homogenizado();
		metodoValuacion.calcularPromedioM2ConstruccionTerrenoHomogenizado();
		metodoValuacion.calcularPromedioM2ConstruccionTerreno();
		metodoValuacion.calcularMediaAritmetica();
		metodoValuacion.calcularDesviacionEstandar();
		metodoValuacion.calcularCoheficienteVariacion();

		RequestContext.getCurrentInstance()
				.update("informeHipotecario:accordionPanel:accordionPanelMetodologias:ofertasLCV");

	}

	public void borrarOferta(OfertaLoteConConstruccionVentaDTO ofertaLoteConConstruccionVentaDTO) {
		this.metodoValuacion.getOfertasConstruccionVenta().remove(ofertaLoteConConstruccionVentaDTO);
		calcular();
	}
	
	public FormatoInformeHipotecarioDTO getInformeHipotecarioDTO() {
		return informeHipotecarioDTO;
	}

	public void setInformeHipotecarioDTO(FormatoInformeHipotecarioDTO informeHipotecarioDTO) {
		this.informeHipotecarioDTO = informeHipotecarioDTO;
	}

	public MetodoValuacionDTO getMetodoValuacion() {
		return metodoValuacion;
	}

	public void setMetodoValuacion(ComparacionMercadoLoteConstruccionVentaDTO metodoValuacion) {
		this.metodoValuacion = metodoValuacion;
	}

	public List<AreaDTO> getAreas() {
		return areas;
	}

	public void setAreas(List<AreaDTO> areas) {
		this.areas = areas;
	}

	public OfertaDTO.TipoArea[] getTiposArea() {
		return OfertaDTO.TipoArea.values();
	}

	public OfertaDTO.FactorHomologacion[] getFactoresHomologacion() {
		return OfertaDTO.FactorHomologacion.values();
	}

	public ArrayList<BigDecimal> getFactores() {
		return factores;
	}

	public void setFactores(ArrayList<BigDecimal> factores) {
		this.factores = factores;
	}

	public ArrayList<BigDecimal> getFactoresNegociacion() {
		return factoresNegociacion;
	}

	public ArrayList<BigDecimal> getFactoresEdad() {
		return factoresEdad;
	}
}
