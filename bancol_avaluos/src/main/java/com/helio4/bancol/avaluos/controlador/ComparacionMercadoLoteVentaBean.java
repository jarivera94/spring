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
import com.helio4.bancol.avaluos.dto.ComparacionMercadoLoteVentaDTO;
import com.helio4.bancol.avaluos.dto.FormatoInformeHipotecarioDTO;
import com.helio4.bancol.avaluos.dto.MetodoValuacionDTO;
import com.helio4.bancol.avaluos.dto.MetodoValuacionDTO.MetodoValuacionEnum;
import com.helio4.bancol.avaluos.dto.OfertaDTO;
import com.helio4.bancol.avaluos.dto.OfertaLoteSinConstruccionDTO;
import com.helio4.bancol.avaluos.dto.PisoFactorDTO;

@Controller
@Scope("view")
@Qualifier("comparacionMercadoLoteVentaBean")
public class ComparacionMercadoLoteVentaBean {

	private FormatoInformeHipotecarioDTO informeHipotecarioDTO;
	private ComparacionMercadoLoteVentaDTO metodoValuacion;

	private boolean nuevaOferta;

	private BigDecimal diferenciaFormulaSencilla;
	private BigDecimal porcentajeFormulaSencilla;
	private BigDecimal diferenciaFormulaTabla;
	private BigDecimal porcentajeFormulaTabla;

	private HashMap<Long, CalificacionHomologacionDTO> calificacionesHomologacion;

	private List<PisoFactorDTO> pisosFactor;

	private ArrayList<BigDecimal> factores;
	private ArrayList<BigDecimal> factoresNegociacion;

	private List<AreaDTO> areas;

	@Autowired
	private CalificacionHomologacionController calificacionHomologacionController;

	@Autowired
	ParametrosController parametrosController;

	@Autowired
	private PisoFactorController pisoFactorController;

	@PostConstruct
	public void inicializar() {

		metodoValuacion = new ComparacionMercadoLoteVentaDTO();
		metodoValuacion.setMetodoUsado(MetodoValuacionEnum.ComparaciondeMercadoLoteVentaSinContruccion);

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
			metodoValuacion = new ComparacionMercadoLoteVentaDTO();
		}

		if (metodoValuacion.getOfertasLoteSinConstruccion() == null) {
			metodoValuacion.setOfertasLoteSinConstruccion(new ArrayList<>());
		}

		metodoValuacion.getOfertasLoteSinConstruccion().add(new OfertaLoteSinConstruccionDTO());

		nuevaOferta = true;
	}

	public void calcular() {

		if (metodoValuacion != null && metodoValuacion.getOfertasLoteSinConstruccion() != null
				&& !metodoValuacion.getOfertasLoteSinConstruccion().isEmpty()) {

			for (OfertaLoteSinConstruccionDTO oferta : metodoValuacion.getOfertasLoteSinConstruccion()) {

				oferta.calcularFactorHomologacionSuperficie(metodoValuacion.getAreaLote(),
						metodoValuacion.getFactorSuperficie());

				if (oferta.getFactorHomologacionUbicacion() != null && oferta.getFactorHomologacionForma() != null
						&& oferta.getFactorHomologacionTipografia() != null) {

					CalificacionHomologacionDTO ubi = calificacionesHomologacion
							.get(oferta.getFactorHomologacionUbicacion().getLongKey());
					CalificacionHomologacionDTO form = calificacionesHomologacion
							.get(oferta.getFactorHomologacionForma().getLongKey());
					CalificacionHomologacionDTO tip = calificacionesHomologacion
							.get(oferta.getFactorHomologacionTipografia().getLongKey());

					if (ubi != null && form != null && tip != null) {
						oferta.calcularCuadroCalificacion(ubi.getFactor(), form.getFactor(), tip.getFactor());
					}
				}

				oferta.calcularFactorHomogenizacion(oferta.getCuadroCalificacion(), oferta.getNegociacion(),
						oferta.getFactorHomologacionSuperficie());

				oferta.calcularPrecioUnitarioM2();
				oferta.calcularValorM2Ajustado();

			}

		}
		metodoValuacion.calcularPromedioPrecioUnitarioM2();
		metodoValuacion.calcularPromedioValorComercial();
		metodoValuacion.calcularPromedioValorM2Ajustado();
		RequestContext.getCurrentInstance()
				.update("informeHipotecario:accordionPanel:accordionPanelMetodologias:ofertasLSCV");
	}

	public void borrarOferta(OfertaLoteSinConstruccionDTO ofertaLoteSinConstruccionDTO) {
		this.metodoValuacion.getOfertasLoteSinConstruccion().remove(ofertaLoteSinConstruccionDTO);
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

	public void setMetodoValuacion(ComparacionMercadoLoteVentaDTO metodoValuacion) {
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

}
