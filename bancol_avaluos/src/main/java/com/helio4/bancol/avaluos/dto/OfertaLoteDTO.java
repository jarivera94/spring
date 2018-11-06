package com.helio4.bancol.avaluos.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import com.helio4.bancol.avaluos.controlador.ListaDesplegable;
import com.helio4.bancol.avaluos.dto.OfertaLoteSinConstruccionDTO.TipoUbicacion;
import com.helio4.bancol.avaluos.modelo.OfertaLote;
import com.helio4.bancol.avaluos.servicio.util.Constantes;

public class OfertaLoteDTO extends OfertaDTO implements Serializable, Cloneable {

	private static final long serialVersionUID = 8455772356084408257L;

	protected Integer idOfertaLote;
	protected Integer comparacion;
	protected BigDecimal areaLoteM2;
	protected BigDecimal precioUnitarioM2;
	protected BigDecimal valorM2Ajustado;

	public OfertaLoteDTO() {
		super();
	}

	public OfertaLoteDTO(OfertaLote oferta) {
			
		super(oferta);
		
		this.setComparacion(oferta.getComparacion());
		this.setAreaLoteM2(oferta.getAreaLoteM2());
		this.setPrecioUnitarioM2(oferta.getPrecioUnitarioM2());
		this.setValorM2Ajustado(oferta.getValorM2Ajustado());

	}

	public OfertaLoteDTO(Long id, String barrio, String conjunto, String ciudad, String telefono, String celular,
			String observaciones, BigDecimal valorDepurado, BigDecimal garajesDepositos, BigDecimal valorOferta,
			BigDecimal porcentajeDepurado, BigDecimal valorM2Construccion, BigDecimal construccion,
			BigDecimal estadoConservacion, BigDecimal valorReposicion, BigDecimal areaTerreno, BigDecimal totalTerreno,
			BigDecimal valorM2Terreno, BigDecimal valorFinalDepurado, Integer edadInmueble, Integer vidaUtil,
			Integer areaConstruida) {
		super(id, barrio, conjunto, ciudad, telefono, celular, observaciones, valorDepurado, garajesDepositos,
				valorOferta, porcentajeDepurado, valorM2Construccion, construccion, estadoConservacion, valorReposicion,
				areaTerreno, totalTerreno, valorM2Terreno, valorFinalDepurado, edadInmueble, vidaUtil, areaConstruida);
	}

	public OfertaLoteDTO(Integer idOfertaLote, Integer comparacion, BigDecimal areaLoteM2, BigDecimal precioUnitarioM2,
			BigDecimal valorM2Ajustado) {
		super();
		this.idOfertaLote = idOfertaLote;
		this.comparacion = comparacion;
		this.areaLoteM2 = areaLoteM2;
		this.precioUnitarioM2 = precioUnitarioM2;
		this.valorM2Ajustado = valorM2Ajustado;
	}

	public Integer getIdOfertaLote() {
		return idOfertaLote;
	}

	public void setIdOfertaLote(Integer idOfertaLote) {
		this.idOfertaLote = idOfertaLote;
	}

	public BigDecimal getAreaLoteM2() {
		return areaLoteM2;
	}

	public void setAreaLoteM2(BigDecimal areaLoteM2) {
		this.areaLoteM2 = areaLoteM2;
	}

	public BigDecimal getPrecioUnitarioM2() {
		return precioUnitarioM2;
	}

	public void setPrecioUnitarioM2(BigDecimal precioUnitarioM2) {
		this.precioUnitarioM2 = precioUnitarioM2;
	}

	public BigDecimal getValorM2Ajustado() {
		return valorM2Ajustado;
	}

	public void setValorM2Ajustado(BigDecimal valorM2Ajustado) {
		this.valorM2Ajustado = valorM2Ajustado;
	}

	public void setComparacion(Integer comparacion) {
		this.comparacion = comparacion;
	}

	public TipoComparacion getComparacion() {
		return TipoComparacion.fromKey(comparacion == null ? 0 : comparacion);
	}

	public void setComparacion(TipoComparacion comparacion) {
		this.comparacion = comparacion == null ? 0 : comparacion.getKey();
	}

	@Override
	public String toString() {
		return "OfertaLoteDTO [id=" + idOfertaLote + ", comparacion=" + comparacion + ", areaLoteM2=" + areaLoteM2
				+ ", precioUnitarioM2=" + precioUnitarioM2 + ", valorM2Ajustado=" + valorM2Ajustado + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((areaLoteM2 == null) ? 0 : areaLoteM2.hashCode());
		result = prime * result + ((comparacion == null) ? 0 : comparacion.hashCode());
		result = prime * result + ((idOfertaLote == null) ? 0 : idOfertaLote.hashCode());
		result = prime * result + ((precioUnitarioM2 == null) ? 0 : precioUnitarioM2.hashCode());
		result = prime * result + ((valorM2Ajustado == null) ? 0 : valorM2Ajustado.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OfertaLoteDTO other = (OfertaLoteDTO) obj;
		if (areaLoteM2 == null) {
			if (other.areaLoteM2 != null)
				return false;
		} else if (!areaLoteM2.equals(other.areaLoteM2))
			return false;
		if (comparacion == null) {
			if (other.comparacion != null)
				return false;
		} else if (!comparacion.equals(other.comparacion))
			return false;
		if (idOfertaLote == null) {
			if (other.idOfertaLote != null)
				return false;
		} else if (!idOfertaLote.equals(other.idOfertaLote))
			return false;

		if (precioUnitarioM2 == null) {
			if (other.precioUnitarioM2 != null)
				return false;
		} else if (!precioUnitarioM2.equals(other.precioUnitarioM2))
			return false;
		if (valorM2Ajustado == null) {
			if (other.valorM2Ajustado != null)
				return false;
		} else if (!valorM2Ajustado.equals(other.valorM2Ajustado))
			return false;
		return true;
	}

	@Override
	public Object clone() {
		OfertaLoteDTO clone = new OfertaLoteDTO(this.idOfertaLote, this.comparacion, this.areaLoteM2, this.precioUnitarioM2,
				this.valorM2Ajustado);
		return clone;
	}
	
	public enum TipoComparacion implements ListaDesplegable {
		Seleccione(Constantes.SELECCCIONE, 0), INFERIOR("Inferior", 1), SIMILAR("Similar",2), SUPERIOR("Superior", 3);

		private final String value;
		private final int key;

		TipoComparacion(String value, int key) {
			this.value = value;
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public int getKey() {
			return key;
		}

		public static TipoComparacion fromKey(int key) {

			TipoComparacion[] values = TipoComparacion.values();

			for (TipoComparacion value : values) {
				if (value.getKey() == key) {
					return value;
				}
			}

			return null;

		}

		@Override
		public String toString() {

			return value;

		}

	}
	
	public void calcularPrecioUnitarioM2() {

		if (this.valorOferta != null && areaLoteM2 != null && !BigDecimal.ZERO.equals(this.areaLoteM2)) {
			this.precioUnitarioM2 = this.valorOferta.divide(areaLoteM2, 8, RoundingMode.HALF_UP);
		} else {
			this.precioUnitarioM2 = BigDecimal.ZERO;
		}

	}
	
	public void calcularValorM2Ajustado(){
		if (this.precioUnitarioM2 != null && this.factorHomogenizacion != null) {
			this.valorM2Ajustado = this.precioUnitarioM2.multiply(this.factorHomogenizacion);
		} else {
			this.valorM2Ajustado = BigDecimal.ZERO;
		}
	}

}
