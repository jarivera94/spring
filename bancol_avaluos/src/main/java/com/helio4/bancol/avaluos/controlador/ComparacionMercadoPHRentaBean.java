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
import com.helio4.bancol.avaluos.dto.AvaluoDTO;
import com.helio4.bancol.avaluos.dto.CalificacionHomologacionDTO;
import com.helio4.bancol.avaluos.dto.ComparacionMercadoPHRentaDTO;
import com.helio4.bancol.avaluos.dto.FormatoInformeHipotecarioDTO;
import com.helio4.bancol.avaluos.dto.MetodoValuacionDTO;
import com.helio4.bancol.avaluos.dto.MetodoValuacionDTO.MetodoValuacionEnum;
import com.helio4.bancol.avaluos.dto.OfertaPHDTO;
import com.helio4.bancol.avaluos.dto.PisoFactorDTO;
import com.helio4.bancol.avaluos.dto.GarajeDTO.ListaTipoGaraje;

@Controller
@Scope("view")
@Qualifier("comparacionMercadoPHRentaBean")
public class ComparacionMercadoPHRentaBean {

	private FormatoInformeHipotecarioDTO informeHipotecarioDTO;
	private AvaluoDTO avaluoHipotecarioDTO;
	private ComparacionMercadoPHRentaDTO metodoValuacion;

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
	
	/**
	 * Tipo de garaje seeleccionado.
	 */
	private ListaTipoGaraje tipoGaraje;

	@Autowired
	private CalificacionHomologacionController calificacionHomologacionController;

	@Autowired
	ParametrosController parametrosController;

	@Autowired
	private PisoFactorController pisoFactorController;

	@PostConstruct
	public void inicializar() {

		metodoValuacion = new ComparacionMercadoPHRentaDTO();
		metodoValuacion.setMetodoUsado(MetodoValuacionEnum.ComparaciondeMercadoPHRenta);

		this.factores = new ArrayList<>();
		for (double i = 0.14; i >= 0.05; i -= 0.01) {
			BigDecimal value = new BigDecimal(i);
			value = value.setScale(2, RoundingMode.HALF_EVEN);
			this.factores.add(value);
		}

		factoresNegociacion = new ArrayList<>();
		for (double i = 0.80; i < 1.01; i += 0.01) {
			BigDecimal value = new BigDecimal(i);
			value = value.setScale(2, RoundingMode.HALF_EVEN);
			this.factoresNegociacion.add(value);
		}

		factoresEdad = new ArrayList<>();
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
			metodoValuacion = new ComparacionMercadoPHRentaDTO();
		}

		if (metodoValuacion.getOfertasPH() == null) {
			metodoValuacion.setOfertasPH(new ArrayList<>());
		}

		metodoValuacion.getOfertasPH().add(new OfertaPHDTO());

		nuevaOferta = true;
	}

	public void calcular() {

		if (metodoValuacion != null && metodoValuacion.getOfertasPH() != null
				&& !metodoValuacion.getOfertasPH().isEmpty()) {

			BigDecimal areaContruida = null;
			BigDecimal areaPrivada = null;

			if (avaluoHipotecarioDTO.getAreaConstruccionAvaluoDTO() != null) {
				areaContruida = avaluoHipotecarioDTO.getAreaConstruccionAvaluoDTO().getAreaConstruida();
				areaPrivada = avaluoHipotecarioDTO.getAreaConstruccionAvaluoDTO().getAreaPrivada();
			}

			for (OfertaPHDTO ofertaPH : metodoValuacion.getOfertasPH()) {

				ofertaPH.calcularTotalAreas(areas);

				ofertaPH.calcularFactorHomologacionEdad(metodoValuacion.getEdad(), metodoValuacion.getFactorEdad());
				ofertaPH.calcularDiferencia(metodoValuacion.getTipoProyecto(),metodoValuacion.getPiso());

				BigDecimal porcentajePisoFactor = null;
				for (PisoFactorDTO pisoFactorDTO : pisosFactor) {
					if (ofertaPH.getPiso() != null && pisoFactorDTO.getDesde() != null
							&& pisoFactorDTO.getHasta() != null && ofertaPH.getPiso() >= pisoFactorDTO.getDesde()
							&& ofertaPH.getPiso() <= pisoFactorDTO.getHasta()) {
						porcentajePisoFactor = pisoFactorDTO.getPorcentaje();
					}
				}

				ofertaPH.calcularFactorHomologacionUbicacion(metodoValuacion.getTipoProyecto(),
						diferenciaFormulaSencilla, diferenciaFormulaTabla, porcentajeFormulaSencilla,
						porcentajePisoFactor);

				if (ofertaPH.getUbicacion() != null && ofertaPH.getAcabados() != null
						&& ofertaPH.getEdificioConjunto() != null) {

					CalificacionHomologacionDTO ubi = calificacionesHomologacion
							.get(ofertaPH.getUbicacion().getLongKey());
					CalificacionHomologacionDTO aca = calificacionesHomologacion
							.get(ofertaPH.getAcabados().getLongKey());
					CalificacionHomologacionDTO edi = calificacionesHomologacion
							.get(ofertaPH.getEdificioConjunto().getLongKey());

					if (ubi != null && aca != null && edi != null) {
						ofertaPH.calcularCuadroCalificacion(ubi.getFactor(), aca.getFactor(), edi.getFactor());
					}
				}

				ofertaPH.calcularFactorHomologacionSuperficie(metodoValuacion.getArea(), metodoValuacion.getFactorSuperficie());

				ofertaPH.calcularFactorHomogenizacion(ofertaPH.getCuadroCalificacion(), ofertaPH.getNegociacion(),
						ofertaPH.getEdadHomogenizada(), ofertaPH.getUbicacionPiso(), ofertaPH.getSuperficie());

				BigDecimal precioUnitarioGaraje = BigDecimal.ZERO;
				if (ofertaPH.getGarajes() != null && metodoValuacion.getValorUnitarioGaraje() != null
						&& metodoValuacion.getTasaAplicada() != null) {
					precioUnitarioGaraje = new BigDecimal(ofertaPH.getGarajes())
							.multiply(metodoValuacion.getValorUnitarioGaraje()
									.multiply(metodoValuacion.getTasaAplicada().divide(BigDecimal.valueOf(100)))
									.divide(BigDecimal.valueOf(12), 8, RoundingMode.HALF_UP));
				}
				ofertaPH.setPrecioUnitarioGaraje(precioUnitarioGaraje);

				ofertaPH.calcularValorM2HomogenizadoSinGaraje(areaContruida, areaPrivada);
				ofertaPH.calcularPrecioUnitarioAdministracionM2();

				ofertaPH.calcularValorM2SinGarajeNoHomogenizado(areaContruida);

				BigDecimal valorM2HomogenizadoSinGarajeYAreaLibre = BigDecimal.ZERO;
				if (ofertaPH.getValorMetroHomogenizadoSinGaraje() != null && ofertaPH.getAreaLibre() != null
						&& ofertaPH.getArea() != null && !BigDecimal.ZERO.equals(ofertaPH.getArea()) && metodoValuacion.getValorM2AreaLibre() != null
						&& ofertaPH.getTotalAreaLibreSujeto() != null && metodoValuacion.getTasaAplicada() != null) {

					valorM2HomogenizadoSinGarajeYAreaLibre = ofertaPH.getValorMetroHomogenizadoSinGaraje()
							.subtract(metodoValuacion.getValorM2AreaLibre()
									.multiply(ofertaPH.getTotalAreaLibreSujeto()
											.multiply(metodoValuacion.getTasaAplicada()).divide(BigDecimal.valueOf(12),
													8, RoundingMode.HALF_UP))
									.divide(ofertaPH.getArea(), 8, RoundingMode.HALF_UP));
				}

				ofertaPH.setValorM2HomogenizadoSinGarajeYAreaLibre(valorM2HomogenizadoSinGarajeYAreaLibre);

			}
		}
		metodoValuacion.calcularRentaBrutaMensual();
		metodoValuacion.calcularRentaNetaMensual();
		metodoValuacion.calcularRentaNetaAnual();
		metodoValuacion.calcularValorPorCapitalizacion();
		metodoValuacion.calcularValorM2HomogenizadoSinGarage();

		metodoValuacion.calcularPromedioValorComercial();
		metodoValuacion.calcularPromedioValorM2HomogenizadoSinGaraje();
		metodoValuacion.calcularPromedioValorM2HomogenizadoSinGarajeAreaLibre();
		metodoValuacion.calcularPromedioValorM2SinGarajeNoHomogenizado();
		metodoValuacion.calcularPromedioValorUnitarioGaraje();
		
		if(avaluoHipotecarioDTO!= null && avaluoHipotecarioDTO.getGarajes() != null && avaluoHipotecarioDTO.getGarajes().size() > 0 ){
			metodoValuacion.calcularValorM2HomogenizadoGJ(ListaTipoGaraje.fromKey(avaluoHipotecarioDTO.getGarajes().get(0).getTipo()));
		}

		RequestContext.getCurrentInstance()
				.update("informeHipotecario:accordionPanel:accordionPanelMetodologias:ofertasPHR");
	}

	public void borrarOferta(OfertaPHDTO ofertaPHDTO) {
		this.metodoValuacion.getOfertasPH().remove(ofertaPHDTO);
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

	public void setMetodoValuacion(ComparacionMercadoPHRentaDTO metodoValuacion) {
		this.metodoValuacion = metodoValuacion;
	}

	public List<AreaDTO> getAreas() {
		return areas;
	}

	public void setAreas(List<AreaDTO> areas) {
		this.areas = areas;
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

	public AvaluoDTO getAvaluoHipotecarioDTO() {
		return avaluoHipotecarioDTO;
	}

	public void setAvaluoHipotecarioDTO(AvaluoDTO avaluoHipotecarioDTO) {
		this.avaluoHipotecarioDTO = avaluoHipotecarioDTO;
	}

	public ListaTipoGaraje getTipoGaraje() {
		return tipoGaraje;
	}

	public void setTipoGaraje(ListaTipoGaraje tipoGaraje) {
		this.tipoGaraje = tipoGaraje;
	}
	
	public boolean getMostrarValorHomogenizacionGarajePrivado() {

		if(avaluoHipotecarioDTO.getGarajes() != null && avaluoHipotecarioDTO.getGarajes().size() > 0 && ListaTipoGaraje.Privado.equals(ListaTipoGaraje.fromKey(avaluoHipotecarioDTO.getGarajes().get(0).getTipo()))){
			return true;
		}

		return false;
		
	}

	public boolean getMostrarValorHomogenizacionGarajeExclusivo() {
		if(avaluoHipotecarioDTO.getGarajes() != null && avaluoHipotecarioDTO.getGarajes().size() > 0 && ListaTipoGaraje.Exclusivo.equals(ListaTipoGaraje.fromKey(avaluoHipotecarioDTO.getGarajes().get(0).getTipo()))){
			return true;
		}

		return false;

	}
	
}
