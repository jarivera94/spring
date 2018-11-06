package com.helio4.bancol.avaluos.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import com.helio4.bancol.avaluos.controlador.ListaDesplegable;
import com.helio4.bancol.avaluos.modelo.OfertaLoteSinConstruccion;
import com.helio4.bancol.avaluos.servicio.util.Constantes;

public class OfertaLoteSinConstruccionDTO extends OfertaLoteDTO implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	protected Integer ubicacionLote;
	protected Integer forma;
	protected Integer factorHomologacionForma;
	protected Integer factorHomologacionTipografia;
	protected Integer factorHomologacionUbicacion;
	protected BigDecimal factorHomologacionSuperficie;

	public OfertaLoteSinConstruccionDTO() {
		
		this.factorHomologacionUbicacion = FactorHomologacion.S.getKey();
		this.factorHomologacionForma = FactorHomologacion.S.getKey();
		this.factorHomologacionTipografia = FactorHomologacion.S.getKey();

	}

	public OfertaLoteSinConstruccionDTO(Long id) {
		super();
		this.id=id;
	}

	public OfertaLoteSinConstruccionDTO(OfertaLoteSinConstruccion oferta) {
		super(oferta);

		this.setUbicacionLote(oferta.getUbicacionLote());
		this.setForma(oferta.getForma());
		this.setFactorHomologacionForma(oferta.getFactorHomologacionForma());
		this.setFactorHomologacionTipografia(oferta.getFactorHomologacionTipografia());
		this.setFactorHomologacionSuperficie(oferta.getFactorHomologacionSuperficie());
		this.setFactorHomologacionUbicacion(oferta.getFactorHomologacionUbicacion());

	}

	public OfertaLoteSinConstruccionDTO(Integer ubicacionLote, Integer forma, Integer factorHomologacionForma,
			Integer factorHomologacionTipografia, Integer factorHomologacionUbicacion,
			BigDecimal factorHomologacionSuperficie) {
		super();
		this.ubicacionLote = ubicacionLote;
		this.forma = forma;
		this.factorHomologacionForma = factorHomologacionForma;
		this.factorHomologacionTipografia = factorHomologacionTipografia;
		this.factorHomologacionUbicacion = factorHomologacionUbicacion;
		this.factorHomologacionSuperficie = factorHomologacionSuperficie;
	}

	public void setUbicacionLote(Integer ubicacionLote) {
		this.ubicacionLote = ubicacionLote;
	}
	
	public TipoUbicacion getUbicacionLote() {
		return TipoUbicacion.fromKey(ubicacionLote == null ? 0 : ubicacionLote);
	}

	public void setUbicacionLote(TipoUbicacion ubicacionLote) {
		this.ubicacionLote = ubicacionLote == null ? 0 : ubicacionLote.getKey();
	}
	
	public TipoForma getForma() {
		return TipoForma.fromKey(forma == null ? 0 : forma);
	}

	public void setForma(TipoForma forma) {
		this.forma = forma == null ? 0 : forma.getKey();
	}
	
	public void setForma(Integer forma) {
		this.forma = forma;
	}

	public FactorHomologacion getFactorHomologacionForma() {
		return FactorHomologacion.fromKey(factorHomologacionForma == null ? 0 : factorHomologacionForma);
	}

	public void setFactorHomologacionForma(FactorHomologacion factorHomologacionForma) {
		this.factorHomologacionForma = factorHomologacionForma == null ? 0 : factorHomologacionForma.getKey();
	}

	public void setFactorHomologacionForma(Integer factorHomologacionForma) {
		this.factorHomologacionForma = factorHomologacionForma;
	}

	public FactorHomologacion getFactorHomologacionTipografia() {
		return FactorHomologacion.fromKey(factorHomologacionTipografia == null ? 0 : factorHomologacionTipografia);
	}

	public void setFactorHomologacionTipografia(FactorHomologacion factorHomologacionTipografia) {
		this.factorHomologacionTipografia = factorHomologacionTipografia == null ? 0
				: factorHomologacionTipografia.getKey();
	}

	public void setFactorHomologacionTipografia(Integer factorHomologacionTipografia) {
		this.factorHomologacionTipografia = factorHomologacionTipografia;
	}

	public FactorHomologacion getFactorHomologacionUbicacion() {
		return FactorHomologacion.fromKey(factorHomologacionUbicacion == null ? 0 : factorHomologacionUbicacion);
	}

	public void setFactorHomologacionUbicacion(FactorHomologacion factorHomologacionUbicacion) {
		this.factorHomologacionUbicacion = factorHomologacionUbicacion == null ? 0
				: factorHomologacionUbicacion.getKey();
	}

	public void setFactorHomologacionUbicacion(Integer factorHomologacionUbicacion) {
		this.factorHomologacionUbicacion = factorHomologacionUbicacion;
	}

	public BigDecimal getFactorHomologacionSuperficie() {
		return factorHomologacionSuperficie;
	}

	public void setFactorHomologacionSuperficie(BigDecimal factorHomologacionSuperficie) {
		this.factorHomologacionSuperficie = factorHomologacionSuperficie;
	}
	

	@Override
	public String toString() {
		return "OfertaLoteSinConstruccionDTO [id=" + id + ", ubicacion=" + ubicacionLote + ", forma=" + forma
				+ ", factorHomologacionForma=" + factorHomologacionForma + ", factorHomologacionTipografia="
				+ factorHomologacionTipografia + ", factorHomologacionSuperficie=" + factorHomologacionSuperficie + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((factorHomologacionForma == null) ? 0 : factorHomologacionForma.hashCode());
		result = prime * result
				+ ((factorHomologacionSuperficie == null) ? 0 : factorHomologacionSuperficie.hashCode());
		result = prime * result
				+ ((factorHomologacionTipografia == null) ? 0 : factorHomologacionTipografia.hashCode());
		result = prime * result + ((forma == null) ? 0 : forma.hashCode());
		result = prime * result + ((ubicacionLote == null) ? 0 : ubicacionLote.hashCode());
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
		OfertaLoteSinConstruccionDTO other = (OfertaLoteSinConstruccionDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public Object clone() {
		// TODO Auto-generated method stub
		OfertaLoteSinConstruccionDTO clone = new OfertaLoteSinConstruccionDTO(this.ubicacionLote, this.forma,
				this.factorHomologacionForma, this.factorHomologacionTipografia, this.factorHomologacionUbicacion,
				this.factorHomologacionSuperficie);
		return clone;
	}

	public enum TipoForma implements ListaDesplegable {
		Seleccione(Constantes.SELECCCIONE, 0), REGULAR("Regular", 1), IRREGULAR("Irregular", 2);

		private final String value;
		private final int key;

		TipoForma(String value, int key) {
			this.value = value;
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public int getKey() {
			return key;
		}

		public static TipoForma fromKey(int key) {

			TipoForma[] values = TipoForma.values();

			for (TipoForma value : values) {
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
	
	public enum TipoUbicacion implements ListaDesplegable {
		Seleccione(Constantes.SELECCCIONE, 0), MEDIANERO("Medianero", 1), ESQUINERO("Esquinero",
				2), DOS_FRENTES("Dos frentes", 3), TRES_FRENTES("Tres frentes", 4);

		private final String value;
		private final int key;

		TipoUbicacion(String value, int key) {
			this.value = value;
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public int getKey() {
			return key;
		}

		public static TipoUbicacion fromKey(int key) {

			TipoUbicacion[] values = TipoUbicacion.values();

			for (TipoUbicacion value : values) {
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
	
	public void calcularFactorHomologacionSuperficie(BigDecimal areaConstruidoSujeto, BigDecimal factorSuperficie) {

		if (factorSuperficie != null && this.areaLoteM2 != null && areaConstruidoSujeto != null && areaConstruidoSujeto.compareTo(BigDecimal.ZERO)!=0) {

			this.factorHomologacionSuperficie = BigDecimal.valueOf(Math.pow(this.areaLoteM2.divide(areaConstruidoSujeto, 8, RoundingMode.HALF_UP).doubleValue(), factorSuperficie.doubleValue()));
		} else {
			this.factorHomologacionSuperficie = BigDecimal.ZERO;
		}

	}

}
