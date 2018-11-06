package com.helio4.bancol.avaluos.modelo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.helio4.bancol.avaluos.dto.ComparacionMercadoLoteConstruccionDTO;

@Entity
@Table(name = "comparacion_mercado_lote_construccion")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "metodo_valuacion_id")
public class ComparacionMercadoLoteConstruccion extends MetodoValuacion implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="edad")
	private Integer edad;
	@Column(name="area_consttruida")
	private BigDecimal areaConsttruida;
	@Column(name="area_lote")
	private BigDecimal areaLote;
	@Column(name="factor_homogenizacion_lote")
	private BigDecimal factorHomogenizacionLote;
	@Column(name="factor_homogenizacion_construccion_terreno")
	private BigDecimal factorHomogenizacionConstruccionTerreno;
	@Column(name="factor_homogenizacion_edad")
	private BigDecimal factorHomogenizacionEdad;

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
	
	public void cargarLoteConstruccionDTO(ComparacionMercadoLoteConstruccionDTO comparacion){
		super.cargarMetodoDTO(comparacion);
		
		this.edad = comparacion.getEdad();
		this.areaConsttruida = comparacion.getAreaConsttruida();
		this.areaLote = comparacion.getAreaLote();
		this.factorHomogenizacionLote = comparacion.getFactorHomogenizacionLote();
		this.factorHomogenizacionConstruccionTerreno = comparacion.getFactorHomogenizacionConstruccionTerreno();
		this.factorHomogenizacionEdad = comparacion.getFactorHomogenizacionEdad();
		
	}

	
}