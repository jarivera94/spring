package com.helio4.bancol.avaluos.dominio;

import com.helio4.bancol.avaluos.dto.AvaluoDTO;
import com.helio4.bancol.avaluos.modelo.Avaluo;


public abstract class FabricaAvaluos {
	
	public FabricaAvaluos () { };
	
	
	/**
	 * 
	 * @return Avaluo creado
	 */
	abstract public Avaluo getAvaluo(AvaluoDTO avaluoDTO);


	/**
	 * 
	 * @param tipoAvaluo tipo de avaluo de la fabrica
	 * @return fabrica de avaluo segun el tipo provisto
	 */
	/*public static FabricaAvaluos getFabricaAvaluo(AvaluoDTO.TipoAvaluo tipoAvaluo) {
		if (tipoAvaluo.equals(AvaluoDTO.TipoAvaluo.Hipotecario)) {
			return new FabricaAvaluoHipotecario();
		}
		if (tipoAvaluo.equals(AvaluoDTO.TipoAvaluo.Comercial)) {
			return new FabricaAvaluoComercial();
		}
		if (tipoAvaluo.equals(AvaluoDTO.TipoAvaluo.Constructor)) {
			return new FabricaAvaluoConstructor();
		}
		if (tipoAvaluo.equals(AvaluoDTO.TipoAvaluo.Actualizacion)) {
			return new FabricaAvaluoActualizacion();
		}
		if (tipoAvaluo.equals(AvaluoDTO.TipoAvaluo.Proyecto)) {
			return new FabricaAvaluoProyecto();
		}
		if (tipoAvaluo.equals(AvaluoDTO.TipoAvaluo.Remate)) {
			return new FabricaAvaluoRemate();
		}
		return null;
	}*/

}
