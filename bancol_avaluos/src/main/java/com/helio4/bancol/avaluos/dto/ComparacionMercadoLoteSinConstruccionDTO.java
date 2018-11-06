package com.helio4.bancol.avaluos.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.helio4.bancol.avaluos.modelo.ComparacionMercadoLoteSinConstruccion;

public class ComparacionMercadoLoteSinConstruccionDTO extends MetodoValuacionDTO implements Serializable, Cloneable {
	
	private static final long serialVersionUID = 1L;
	
	protected BigDecimal areaLote;
	protected BigDecimal promedioPrecioUnitarioM2;
	protected BigDecimal promedioValorM2Ajustado;
	protected BigDecimal promedioValorComercial;
	protected BigDecimal factorSuperficie;
	protected BigDecimal mediaArtimetica;
	protected BigDecimal desviacionEstandar;
	protected BigDecimal variacion;
	
	protected List<OfertaLoteSinConstruccionDTO> ofertasLoteSinConstruccion;
	
	
	
	public ComparacionMercadoLoteSinConstruccionDTO(BigDecimal areaLote, BigDecimal promedioPrecioUnitarioM2,
			BigDecimal promedioValorM2Ajustado, BigDecimal promedioValorComercial, BigDecimal factorSuperficie,
			BigDecimal mediaArtimetica, BigDecimal desviacionEstandar, BigDecimal variacion) {
		super();
		this.areaLote = areaLote;
		this.promedioPrecioUnitarioM2 = promedioPrecioUnitarioM2;
		this.promedioValorM2Ajustado = promedioValorM2Ajustado;
		this.promedioValorComercial = promedioValorComercial;
		this.factorSuperficie = factorSuperficie;
		this.mediaArtimetica = mediaArtimetica;
		this.desviacionEstandar = desviacionEstandar;
		this.variacion = variacion;
	}

	public ComparacionMercadoLoteSinConstruccionDTO(Integer metodoUsado, Long avaluoId){
        super(metodoUsado, avaluoId);
    }

	public ComparacionMercadoLoteSinConstruccionDTO(ComparacionMercadoLoteSinConstruccion comparacion) {
		super(comparacion);
		
		this.areaLote = comparacion.getAreaLote();
		this.promedioPrecioUnitarioM2 =  comparacion.getPromedioPrecioUnitarioM2();
		this.promedioValorM2Ajustado = comparacion.getPromedioValorM2Ajustado();
		this.promedioValorComercial =comparacion.getPromedioValorComercial();
		this.factorSuperficie = comparacion.getFactorSuperficie();
		this.mediaArtimetica = comparacion.getMediaArtimetica();
		this.desviacionEstandar = comparacion.getDesviacionEstandar();
		this.variacion = comparacion.getVariacion();
		
	}
	
	public ComparacionMercadoLoteSinConstruccionDTO() {
		super();
	}

	public BigDecimal getAreaLote() {
		return areaLote;
	}

	public void setAreaLote(BigDecimal areaLote) {
		this.areaLote = areaLote;
	}

	public BigDecimal getPromedioPrecioUnitarioM2() {
		return promedioPrecioUnitarioM2;
	}

	public void setPromedioPrecioUnitarioM2(BigDecimal promedioPrecioUnitarioM2) {
		this.promedioPrecioUnitarioM2 = promedioPrecioUnitarioM2;
	}

	public BigDecimal getPromedioValorM2Ajustado() {
		return promedioValorM2Ajustado;
	}

	public void setPromedioValorM2Ajustado(BigDecimal promedioValorM2Ajustado) {
		this.promedioValorM2Ajustado = promedioValorM2Ajustado;
	}

	public BigDecimal getPromedioValorComercial() {
		return promedioValorComercial;
	}

	public void setPromedioValorComercial(BigDecimal promedioValorComercial) {
		this.promedioValorComercial = promedioValorComercial;
	}

	public BigDecimal getFactorSuperficie() {
		return factorSuperficie;
	}

	public void setFactorSuperficie(BigDecimal factorSuperficie) {
		this.factorSuperficie = factorSuperficie;
	}

	public BigDecimal getMediaArtimetica() {
		return mediaArtimetica;
	}

	public void setMediaArtimetica(BigDecimal mediaArtimetica) {
		this.mediaArtimetica = mediaArtimetica;
	}

	public BigDecimal getDesviacionEstandar() {
		return desviacionEstandar;
	}

	public void setDesviacionEstandar(BigDecimal desviacionEstandar) {
		this.desviacionEstandar = desviacionEstandar;
	}

	public BigDecimal getVariacion() {
		return variacion;
	}

	public void setVariacion(BigDecimal variacion) {
		this.variacion = variacion;
	}

	public List<OfertaLoteSinConstruccionDTO> getOfertasLoteSinConstruccion() {
		return ofertasLoteSinConstruccion;
	}

	public void setOfertasLoteSinConstruccion(List<OfertaLoteSinConstruccionDTO> ofertasLoteSinConstruccion) {
		this.ofertasLoteSinConstruccion = ofertasLoteSinConstruccion;
	}

	@Override
	public String toString() {
		return "ComparacionMercadoLoteSinConstruccionDTO [idmetodo="
				+ id + ", areaLote=" + areaLote + ", promedioPrecioUnitarioM2="
				+ promedioPrecioUnitarioM2 + ", promedioValorM2Ajustado=" + promedioValorM2Ajustado
				+ ", promedioValorComercial=" + promedioValorComercial + ", factorSuperficie=" + factorSuperficie
				+ ", mediaArtimetica=" + mediaArtimetica + ", desviacionEstandar=" + desviacionEstandar + ", variacion="
				+ variacion + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((areaLote == null) ? 0 : areaLote.hashCode());
		result = prime * result + ((desviacionEstandar == null) ? 0 : desviacionEstandar.hashCode());
		result = prime * result + ((factorSuperficie == null) ? 0 : factorSuperficie.hashCode());
		result = prime * result + ((mediaArtimetica == null) ? 0 : mediaArtimetica.hashCode());
		result = prime * result + ((ofertasLoteSinConstruccion == null) ? 0 : ofertasLoteSinConstruccion.hashCode());
		result = prime * result + ((promedioPrecioUnitarioM2 == null) ? 0 : promedioPrecioUnitarioM2.hashCode());
		result = prime * result + ((promedioValorComercial == null) ? 0 : promedioValorComercial.hashCode());
		result = prime * result + ((promedioValorM2Ajustado == null) ? 0 : promedioValorM2Ajustado.hashCode());
		result = prime * result + ((variacion == null) ? 0 : variacion.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComparacionMercadoLoteSinConstruccionDTO other = (ComparacionMercadoLoteSinConstruccionDTO) obj;
		if (areaLote == null) {
			if (other.areaLote != null)
				return false;
		} else if (!areaLote.equals(other.areaLote))
			return false;
		if (desviacionEstandar == null) {
			if (other.desviacionEstandar != null)
				return false;
		} else if (!desviacionEstandar.equals(other.desviacionEstandar))
			return false;
		if (factorSuperficie == null) {
			if (other.factorSuperficie != null)
				return false;
		} else if (!factorSuperficie.equals(other.factorSuperficie))
			return false;
		if (mediaArtimetica == null) {
			if (other.mediaArtimetica != null)
				return false;
		} else if (!mediaArtimetica.equals(other.mediaArtimetica))
			return false;
		if (ofertasLoteSinConstruccion == null) {
			if (other.ofertasLoteSinConstruccion != null)
				return false;
		} else if (!ofertasLoteSinConstruccion.equals(other.ofertasLoteSinConstruccion))
			return false;
		if (promedioPrecioUnitarioM2 == null) {
			if (other.promedioPrecioUnitarioM2 != null)
				return false;
		} else if (!promedioPrecioUnitarioM2.equals(other.promedioPrecioUnitarioM2))
			return false;
		if (promedioValorComercial == null) {
			if (other.promedioValorComercial != null)
				return false;
		} else if (!promedioValorComercial.equals(other.promedioValorComercial))
			return false;
		if (promedioValorM2Ajustado == null) {
			if (other.promedioValorM2Ajustado != null)
				return false;
		} else if (!promedioValorM2Ajustado.equals(other.promedioValorM2Ajustado))
			return false;
		if (variacion == null) {
			if (other.variacion != null)
				return false;
		} else if (!variacion.equals(other.variacion))
			return false;
		return true;
	}

	@Override
	public Object clone(){
		ComparacionMercadoLoteSinConstruccionDTO clone =  new ComparacionMercadoLoteSinConstruccionDTO(this.areaLote,
				this.promedioPrecioUnitarioM2, this.promedioValorM2Ajustado, this.promedioValorComercial,
				this.factorSuperficie, this.mediaArtimetica, this.desviacionEstandar,
				this.variacion);
		return clone;
	}
	
	public void calcularPromedioPrecioUnitarioM2() {

		if(ofertasLoteSinConstruccion != null  && !ofertasLoteSinConstruccion.isEmpty()){
		
		this.promedioPrecioUnitarioM2 = BigDecimal
				.valueOf(
						ofertasLoteSinConstruccion.stream()
								.mapToDouble((x) -> x.getPrecioUnitarioM2() == null ? 0
										: x.getPrecioUnitarioM2().doubleValue())
								.average().getAsDouble());
		}else{
			this.promedioPrecioUnitarioM2 = BigDecimal.ZERO;
		}
		
	}
	
	public void calcularPromedioValorM2Ajustado() {

		if(ofertasLoteSinConstruccion != null  && !ofertasLoteSinConstruccion.isEmpty()){
		
		this.promedioValorM2Ajustado = BigDecimal
				.valueOf(
						ofertasLoteSinConstruccion.stream()
								.mapToDouble((x) -> x.getValorM2Ajustado() == null ? 0
										: x.getValorM2Ajustado().doubleValue())
								.average().getAsDouble());
		}else{
			this.promedioValorM2Ajustado = BigDecimal.ZERO;
		}
		
	}
	
	public void calcularPromedioValorComercial() {

		if(ofertasLoteSinConstruccion != null  && !ofertasLoteSinConstruccion.isEmpty()){
		
		this.promedioValorComercial = BigDecimal
				.valueOf(
						ofertasLoteSinConstruccion.stream()
								.mapToDouble((x) -> x.getValorOferta() == null ? 0
										: x.getValorOferta().doubleValue())
								.average().getAsDouble());
		}else{
			this.promedioValorComercial = BigDecimal.ZERO;
		}
	}
	
}
