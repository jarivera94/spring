package com.helio4.bancol.avaluos.dominio;

import com.helio4.bancol.avaluos.dto.AvaluoDTO;
import com.helio4.bancol.avaluos.modelo.Avaluo;
import com.helio4.bancol.avaluos.modelo.AvaluoHipotecario;


/**
 * Class FabricaAvaluoHipotecario
 */
public class FabricaAvaluoHipotecario extends FabricaAvaluos {
	
	
	
	public FabricaAvaluoHipotecario () { }

	@Override
	public Avaluo getAvaluo(AvaluoDTO avaluoDTO) {
		return new AvaluoHipotecario();
	}
}
