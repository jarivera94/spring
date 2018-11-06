package com.helio4.bancol.avaluos.dominio;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.MetodoValuacionDTO;
import com.helio4.bancol.avaluos.dto.OfertaDTO;
import com.jidesoft.utils.BigDecimalMathUtils;

@Component
public class ComparacionMercadoNOPHController {

	/**
	 * Función que calcula el valor de la construcción de una oferta.
	 * 
	 * @param OfertaDTO
	 *            sobre la cual se calcula el valor de construcción
	 * @return OfertaDTO después de realizar el calculo.
	 */
	public OfertaDTO calcularValorConstruccion(OfertaDTO oferta) {
		BigDecimal valorConstruccion = BigDecimal.ZERO;
		BigDecimal area = new BigDecimal(oferta.getAreaConstruida() != null ? oferta.getAreaConstruida() : 0);
		BigDecimal valorM2Construccion = oferta.getValorM2Construccion();
		valorConstruccion = area.multiply(valorM2Construccion != null ? valorM2Construccion : BigDecimal.ZERO);
		oferta.setConstruccion(valorConstruccion != null ? valorConstruccion : BigDecimal.ZERO);
		return oferta;
	}

	public OfertaDTO calcularTerreno(OfertaDTO oferta) {
		BigDecimal valorDepurado = oferta.getValorDepurado() != null ? oferta.getValorDepurado() : BigDecimal.ZERO;
		BigDecimal valorConstruccion = oferta.getConstruccion() != null ? oferta.getConstruccion() : BigDecimal.ZERO;
		BigDecimal totalTerreno = valorDepurado.subtract(valorConstruccion);
		oferta.setTotalTerreno(totalTerreno);
		BigDecimal area = new BigDecimal(oferta.getAreaConstruida() != null ? oferta.getAreaConstruida() : 0);
		BigDecimal valorM2Terreno = (area.compareTo(BigDecimal.ZERO) != 0 && totalTerreno != null)
				? totalTerreno.divide(area, 4, RoundingMode.HALF_UP)
				: BigDecimal.ZERO;
		oferta.setValorM2Terreno(valorM2Terreno);
		return oferta;
	}

	/**
	 * Función que se encarga de calcular la desviación estándar sobre el valor m2
	 * de la construcción para construcciones con propiedad horizontal.
	 * 
	 * @param Lista
	 *            de ofertas
	 * @return desviación calculada.
	 */
	public BigDecimal calcularDesviacionEstandar(List<OfertaDTO> ofertas) {

		BigDecimal desviacion = BigDecimal.ZERO;
		List<BigDecimal> valores = new ArrayList<BigDecimal>();
		for (OfertaDTO oferta : ofertas) {
			// System.err.println(oferta.getValorM2Terreno() );
			valores.add(oferta.getValorM2Terreno());
		}
		desviacion = BigDecimalMathUtils.stddev(valores, true, new MathContext(4, RoundingMode.HALF_UP));
		// System.err.println( " desviacion: "+ desviacion);
		return desviacion;
	}

	/**
	 * Función que calcula el promedio del valor de las construcciones con propiedad
	 * horizontal de las diferentes ofertas.
	 * 
	 * @param Lista
	 *            de ofertas
	 * @return promedio calculado.
	 */
	public BigDecimal calcularPromedioTerreno(List<OfertaDTO> ofertas) {
		BigDecimal promedio = BigDecimal.ZERO;
		for (OfertaDTO oferta : ofertas) {
			promedio = promedio.add(oferta.getValorM2Terreno());
		}
		if (ofertas != null && ofertas.size() > 0) {
			return promedio.divide(new BigDecimal(ofertas.size()), 2, RoundingMode.HALF_UP);
		} else {
			return BigDecimal.ZERO;
		}
	}

	public void guardar(Set<MetodoValuacionDTO> metodosValuacionActuales, Set<MetodoValuacionDTO> metodosValuacion,
			Set<OfertaDTO> ofertasActuales, Set<OfertaDTO> ofertas) {
		// TODO Auto-generated method stub

	}

}
