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
import com.helio4.bancol.avaluos.dto.ComparacionMercadoLoteConstruccionRentaDTO;
import com.helio4.bancol.avaluos.dto.FormatoInformeHipotecarioDTO;
import com.helio4.bancol.avaluos.dto.MetodoValuacionDTO.MetodoValuacionEnum;
import com.helio4.bancol.avaluos.dto.OfertaDTO;
import com.helio4.bancol.avaluos.dto.OfertaLoteConConstruccionRentaDTO;
import com.helio4.bancol.avaluos.dto.PisoFactorDTO;

@Controller
@Scope("view")
@Qualifier("comparacionMercadoLoteConstruccionRentaBean")
public class ComparacionMercadoLoteConstruccionRentaBean {

	private FormatoInformeHipotecarioDTO informeHipotecarioDTO;

	private ComparacionMercadoLoteConstruccionRentaDTO metodoValuacion;

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

		metodoValuacion = new ComparacionMercadoLoteConstruccionRentaDTO();
		metodoValuacion.setMetodoUsado(MetodoValuacionEnum.ComparaciondeMercadoLoteRentaContruccion);

		this.factores = new ArrayList<>();
		for (double i = 0.14; i >= 0.05; i -= 0.01) {
			BigDecimal value = new BigDecimal(i);
			value = value.setScale(2, RoundingMode.HALF_EVEN);
			this.factores.add(value);
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
		
		List<CalificacionHomologacionDTO> ch = calificacionHomologacionController.encontrarCalificacionesHomologacion();
		calificacionesHomologacion = new HashMap<Long, CalificacionHomologacionDTO>();
		for (int i = 0; i < ch.size(); i++) {
			calificacionesHomologacion.put(ch.get(i).getId(), ch.get(i));
		}

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
			metodoValuacion = new ComparacionMercadoLoteConstruccionRentaDTO();
		}

		if (metodoValuacion.getOfertasConstruccionRenta() == null) {
			metodoValuacion.setOfertasConstruccionRenta(new ArrayList<>());
		}

		metodoValuacion.getOfertasConstruccionRenta().add(new OfertaLoteConConstruccionRentaDTO());

		nuevaOferta = true;
	}

	public void calcular() {

		if (metodoValuacion != null && metodoValuacion.getOfertasConstruccionRenta() != null
				&& !metodoValuacion.getOfertasConstruccionRenta().isEmpty()) {

			for (OfertaLoteConConstruccionRentaDTO ofertaConstruccionRenta : metodoValuacion
					.getOfertasConstruccionRenta()) {
				ofertaConstruccionRenta.calcularFactorHomologacionEdad(metodoValuacion.getEdad(),
						metodoValuacion.getFactorHomogenizacionEdad());

				ofertaConstruccionRenta.calcularFactorHomologacionConstruccion(metodoValuacion.getAreaConsttruida(),
						metodoValuacion.getAreaLote(), metodoValuacion.getFactorHomogenizacionConstruccionTerreno());
				ofertaConstruccionRenta.calcularFactorHomologacionLote(metodoValuacion.getFactorHomogenizacionLote(), metodoValuacion.getAreaLote());
				ofertaConstruccionRenta.calcularPrecioUnitarioM2();

				if (ofertaConstruccionRenta.getUbicacion() != null
						&& ofertaConstruccionRenta.getFactorHomologacionConservacion() != null
						&& ofertaConstruccionRenta.getAcabados() != null) {

					CalificacionHomologacionDTO ubi = calificacionesHomologacion
							.get(ofertaConstruccionRenta.getUbicacion().getLongKey());
					CalificacionHomologacionDTO con = calificacionesHomologacion
							.get(ofertaConstruccionRenta.getFactorHomologacionConservacion().getLongKey());
					CalificacionHomologacionDTO aca = calificacionesHomologacion
							.get(ofertaConstruccionRenta.getAcabados().getLongKey());

					if (ubi != null && con != null && aca != null) {
						ofertaConstruccionRenta.calcularCuadroCalificacion(ubi.getFactor(), con.getFactor(),
								aca.getFactor());
					}
				}

				ofertaConstruccionRenta.calcularFactorHomogenizacion(ofertaConstruccionRenta.getCuadroCalificacion(),
						ofertaConstruccionRenta.getNegociacion(), ofertaConstruccionRenta.getEdadHomogenizada(),
						ofertaConstruccionRenta.getFactorHomologacionConstruccion(),
						ofertaConstruccionRenta.getFactorHomologacionLote());
				ofertaConstruccionRenta.calcularValorUnitarioResultanteM2();
				ofertaConstruccionRenta.calcularAreaconstruccionAreaTerreno();

			}

		}
		metodoValuacion.calcularAreaConstruccionAreaTerrenoSujeto();
		metodoValuacion.calcularPromedioRentaMensual();
		metodoValuacion.calcularPromedioValorUnitarioResultanteM2Mes();
		metodoValuacion.calcularRentaBrutaMensual();
		metodoValuacion.calcularRentaNetaMensual();
		;
		metodoValuacion.calcularRentaNetaAnual();
		metodoValuacion.calcularValorCapitalizacion();
		metodoValuacion.calcularValorIntegralCTHomogenizado();

		RequestContext.getCurrentInstance()
				.update("informeHipotecario:accordionPanel:accordionPanelMetodologias:ofertasLCR");
	}

	public void borrarOferta(OfertaLoteConConstruccionRentaDTO ofertaLoteConConstruccionRentaDTO) {
		this.metodoValuacion.getOfertasConstruccionRenta().remove(ofertaLoteConConstruccionRentaDTO);
		calcular();
	}
	
	public FormatoInformeHipotecarioDTO getInformeHipotecarioDTO() {
		return informeHipotecarioDTO;
	}

	public void setInformeHipotecarioDTO(FormatoInformeHipotecarioDTO informeHipotecarioDTO) {
		this.informeHipotecarioDTO = informeHipotecarioDTO;
	}

	public ComparacionMercadoLoteConstruccionRentaDTO getMetodoValuacion() {
		return metodoValuacion;
	}

	public void setMetodoValuacion(ComparacionMercadoLoteConstruccionRentaDTO metodoValuacion) {
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
