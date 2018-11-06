package com.helio4.bancol.avaluos.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.helio4.bancol.avaluos.modelo.ComparacionMercadoLoteConstruccion;

public class ComparacionMercadoLoteConstruccionDTO extends MetodoValuacionDTO implements Serializable, Cloneable{
	
	private static final long serialVersionUID = 1L;
	
	protected Integer    edad;
	protected BigDecimal areaConsttruida;
	protected BigDecimal areaLote;
	protected BigDecimal factorHomogenizacionLote;
	protected BigDecimal factorHomogenizacionConstruccionTerreno;
	protected BigDecimal factorHomogenizacionEdad;
	
	public ComparacionMercadoLoteConstruccionDTO(Integer edad,
			BigDecimal areaConsttruida, BigDecimal areaLote, BigDecimal factorHomogenizacionLote,
			BigDecimal factorHomogenizacionConstruccionTerreno, BigDecimal factorHomogenizacionEdad) {
		super();
		this.edad = edad;
		this.areaConsttruida = areaConsttruida;
		this.areaLote = areaLote;
		this.factorHomogenizacionLote = factorHomogenizacionLote;
		this.factorHomogenizacionConstruccionTerreno = factorHomogenizacionConstruccionTerreno;
		this.factorHomogenizacionEdad = factorHomogenizacionEdad;
	}

	public ComparacionMercadoLoteConstruccionDTO() {
		super();
	}
	
	public ComparacionMercadoLoteConstruccionDTO(Integer metodoUsado, Long avaluoId){
        super(metodoUsado, avaluoId);
    }
	
	public ComparacionMercadoLoteConstruccionDTO(ComparacionMercadoLoteConstruccion comparacion) {
		super(comparacion);
		
		this.edad = comparacion.getEdad();
		this.areaConsttruida = comparacion.getAreaConsttruida();
		this.areaLote = comparacion.getAreaLote();
		this.factorHomogenizacionLote = comparacion.getFactorHomogenizacionLote();
		this.factorHomogenizacionConstruccionTerreno = comparacion.getFactorHomogenizacionConstruccionTerreno();
		this.factorHomogenizacionEdad = comparacion.getFactorHomogenizacionEdad();
		
	}
	
	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public BigDecimal getAreaConsttruida() {
		return areaConsttruida;
	}

	public void setAreaConsttruida(BigDecimal areaConsttruida) {
		this.areaConsttruida = areaConsttruida;
	}

	public BigDecimal getAreaLote() {
		return areaLote;
	}

	public void setAreaLote(BigDecimal areaLote) {
		this.areaLote = areaLote;
	}

	public BigDecimal getFactorHomogenizacionLote() {
		return factorHomogenizacionLote;
	}

	public void setFactorHomogenizacionLote(BigDecimal factorHomogenizacionLote) {
		this.factorHomogenizacionLote = factorHomogenizacionLote;
	}

	public BigDecimal getFactorHomogenizacionConstruccionTerreno() {
		return factorHomogenizacionConstruccionTerreno;
	}

	public void setFactorHomogenizacionConstruccionTerreno(BigDecimal factorHomogenizacionConstruccionTerreno) {
		this.factorHomogenizacionConstruccionTerreno = factorHomogenizacionConstruccionTerreno;
	}

	public BigDecimal getFactorHomogenizacionEdad() {
		return factorHomogenizacionEdad;
	}

	public void setFactorHomogenizacionEdad(BigDecimal factorHomogenizacionEdad) {
		this.factorHomogenizacionEdad = factorHomogenizacionEdad;
	}

	@Override
	public String toString() {
		return "ComparacionMercadoLoteConstruccionDTO [id="
				+ id + ", edad=" + edad + ", areaConsttruida=" + areaConsttruida
				+ ", areaLote=" + areaLote + ", factorHomogenizacionLote=" + factorHomogenizacionLote
				+ ", factorHomogenizacionConstruccionTerreno=" + factorHomogenizacionConstruccionTerreno
				+ ", factorHomogenizacionEdad=" + factorHomogenizacionEdad + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((areaConsttruida == null) ? 0 : areaConsttruida.hashCode());
		result = prime * result + ((areaLote == null) ? 0 : areaLote.hashCode());
		result = prime * result + ((edad == null) ? 0 : edad.hashCode());
		result = prime * result + ((factorHomogenizacionConstruccionTerreno == null) ? 0
				: factorHomogenizacionConstruccionTerreno.hashCode());
		result = prime * result + ((factorHomogenizacionEdad == null) ? 0 : factorHomogenizacionEdad.hashCode());
		result = prime * result + ((factorHomogenizacionLote == null) ? 0 : factorHomogenizacionLote.hashCode());
		return result;
	}

	/*@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComparacionMercadoLoteConstruccionDTO other = (ComparacionMercadoLoteConstruccionDTO) obj;
		if (areaConsttruida == null) {
			if (other.areaConsttruida != null)
				return false;
		} else if (!areaConsttruida.equals(other.areaConsttruida))
			return false;
		if (areaLote == null) {
			if (other.areaLote != null)
				return false;
		} else if (!areaLote.equals(other.areaLote))
			return false;
		if (edad == null) {
			if (other.edad != null)
				return false;
		} else if (!edad.equals(other.edad))
			return false;
		if (factorHomogenizacionConstruccionTerreno == null) {
			if (other.factorHomogenizacionConstruccionTerreno != null)
				return false;
		} else if (!factorHomogenizacionConstruccionTerreno.equals(other.factorHomogenizacionConstruccionTerreno))
			return false;
		if (factorHomogenizacionEdad == null) {
			if (other.factorHomogenizacionEdad != null)
				return false;
		} else if (!factorHomogenizacionEdad.equals(other.factorHomogenizacionEdad))
			return false;
		if (factorHomogenizacionLote == null) {
			if (other.factorHomogenizacionLote != null)
				return false;
		} else if (!factorHomogenizacionLote.equals(other.factorHomogenizacionLote))
			return false;
		return true;
	}*/
	
	
	@Override
	public Object clone(){
		
		ComparacionMercadoLoteConstruccionDTO clone = new ComparacionMercadoLoteConstruccionDTO(this.edad,
				this.areaConsttruida, this.areaLote, this.factorHomogenizacionLote,
				this.factorHomogenizacionConstruccionTerreno, this.factorHomogenizacionEdad);
		return clone;
	}

}
