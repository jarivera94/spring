package com.helio4.bancol.avaluos.dto;

import java.io.Serializable;
import java.util.ArrayList;

import com.helio4.bancol.avaluos.dto.OfertaLoteDTO.TipoComparacion;
import com.helio4.bancol.avaluos.dto.OfertaLoteSinConstruccionDTO.TipoForma;
import com.helio4.bancol.avaluos.dto.OfertaLoteSinConstruccionDTO.TipoUbicacion;
import com.helio4.bancol.avaluos.modelo.ComparacionMercadoLoteSinConstruccion;

public class ComparacionMercadoLoteVentaDTO extends ComparacionMercadoLoteSinConstruccionDTO
		implements Serializable, Cloneable {

	private static final long serialVersionUID = 7044367860879413204L;

	public ComparacionMercadoLoteVentaDTO() {

	}
	
	public ComparacionMercadoLoteVentaDTO(Integer metodoUsado, Long avaluoId){
        super(metodoUsado, avaluoId);
    }

	public ComparacionMercadoLoteVentaDTO(MetodoValuacionDTO metodo) {

		if (metodo != null) {

			this.setId(metodo.getId());
			this.setAvaluoId(metodo.getAvaluoId());
			this.setConceptoDelMetodo(metodo.getConceptoDelMetodo());
			this.setMetodoUsado(metodo.getMetodoUsado());
			this.setOfertasLoteSinConstruccion(new ArrayList<>());
		}
	}

	public ComparacionMercadoLoteVentaDTO(ComparacionMercadoLoteSinConstruccion comparacion) {
		super(comparacion);
	}

	public TipoForma[] getTiposForma() {
		return TipoForma.values();
	}
	
	

}
