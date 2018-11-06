package com.helio4.bancol.avaluos.servicio.util;

import java.math.BigDecimal;

public class MedidasUtils {
	
	public static BigDecimal convertirHectareasAMetros(BigDecimal hectareas) {
		return hectareas.multiply(new BigDecimal(10000));
	}
	
	public static BigDecimal convertirFanegadasAMetros(BigDecimal fanegadas) {
		return fanegadas.multiply(new BigDecimal(6400));
	}

}
