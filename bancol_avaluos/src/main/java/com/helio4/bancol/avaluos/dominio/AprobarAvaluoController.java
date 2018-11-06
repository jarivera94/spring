package com.helio4.bancol.avaluos.dominio;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.AvaluoDTO;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;
import com.helio4.bancol.avaluos.servicio.datos.AvaluoService;
import com.helio4.bancol.avaluos.servicio.datos.EstadoAvaluoService;
import com.helio4.bancol.avaluos.servicio.datos.ParametroService;
import com.helio4.bancol.avaluos.servicio.excepciones.TarifaNotFoundException;

@Component
public class AprobarAvaluoController {

	@Autowired
	@Qualifier("repositoryAvaluoService")
	private AvaluoService avaluoService;

	@Autowired
	@Qualifier("repositoryEstadoAvaluoService")
	private EstadoAvaluoService estadoAvaluoService;

	@Autowired
	@Qualifier("repositoryParametroService")
	private ParametroService parametroService;

	public AvaluoDTO aprobarAvaluo(AvaluoDTO avaluoDTO, UsuarioDTO usuario)
			throws AvaluoNotFoundException, TarifaNotFoundException {
		if (avaluoDTO == null) {
			return null;
		}
		avaluoDTO.setValorLiquidacion(calcularValorLiquidacion(avaluoDTO));
		if (avaluoDTO.getValorLiquidacion() == null) {
			return null;
		}
		return avaluoService.aprobar(avaluoDTO, usuario) ? avaluoDTO : null;
	}

	public BigDecimal calcularValorLiquidacion(AvaluoDTO avaluoDTO) throws TarifaNotFoundException {
		if (avaluoDTO.getValorTotalAvaluo() == null
				|| avaluoDTO.getValorTotalAvaluo().compareTo(BigDecimal.ZERO) == 0) {
			return BigDecimal.ZERO;
		}

		BigDecimal tarifaValorMinimo = new BigDecimal(
				parametroService.encontrarPorNombre("tarifa_general_valor_minimo").getValor());
		BigDecimal tarifaValorMaximo = new BigDecimal(
				parametroService.encontrarPorNombre("tarifa_general_valor_maximo").getValor());
		BigDecimal tarifaValorTarifa = new BigDecimal(
				parametroService.encontrarPorNombre("tarifa_general_tarifa").getValor());

		BigDecimal valorLiquidacionBruto = tarifaValorTarifa
				.multiply(avaluoDTO.getValorTotalAvaluo().divide(new BigDecimal(1000), 2, RoundingMode.HALF_EVEN));
		return (valorLiquidacionBruto.compareTo(tarifaValorMaximo)) > 0 ? tarifaValorMaximo
				: valorLiquidacionBruto.compareTo(tarifaValorMinimo) < 0 ? tarifaValorMinimo : valorLiquidacionBruto;
	}

	public BigDecimal calcularValorHonorarios(BigDecimal valorLiquidacionBruto) {

		BigDecimal tarifaValorPorcentaje = new BigDecimal(
				parametroService.encontrarPorNombre("tarifa_general_porcentaje_perito").getValor());

		return valorLiquidacionBruto.multiply(tarifaValorPorcentaje.divide(new BigDecimal(100)));

	}

	public void notificarAvaluoAprobado(AvaluoDTO avaluo, UsuarioDTO usuario, String archivo) {
		avaluoService.notificarAvaluoAprobado(avaluo, usuario, archivo);
	}

}
