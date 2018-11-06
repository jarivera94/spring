package com.helio4.bancol.avaluos.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.helio4.bancol.avaluos.modelo.ComparacionMercadoLoteConstruccionRenta;

public class ComparacionMercadoLoteConstruccionRentaDTO extends ComparacionMercadoLoteConstruccionDTO implements Cloneable, Serializable {

	private static final long serialVersionUID = 1L;
	
	protected BigDecimal promedioValorUnitarioResultanteM2Mes;
	protected BigDecimal promedioRentaMensual;
	protected BigDecimal promedioAreaConstruccionAreaTerreno;
	protected BigDecimal tasaAplicada;
	protected BigDecimal rentaBrutaMensual;
	protected BigDecimal deducciones;
	protected BigDecimal rentaNetaMensual;
	protected BigDecimal rentaNetaAnual;
	protected BigDecimal valorCapitalizacion;
	protected BigDecimal valorM2HomogenizadoSinGaraje;
	
	protected List<OfertaLoteConConstruccionRentaDTO> ofertasConstruccionRenta;
	
	public ComparacionMercadoLoteConstruccionRentaDTO(Long id, BigDecimal promedioValorUnitarioResultanteM2Mes,
			BigDecimal promedioRentaMensual, BigDecimal promedioAreaConstruccionAreaTerreno, BigDecimal tasaAplicada,
			BigDecimal rentaBrutaMensual, BigDecimal deducciones, BigDecimal rentaNetaMensual,
			BigDecimal rentaNetaAnual, BigDecimal valorCapitalizacion, BigDecimal valorM2HomogenizadoSinGaraje) {
		super();
		this.id = id;
		this.promedioValorUnitarioResultanteM2Mes = promedioValorUnitarioResultanteM2Mes;
		this.promedioRentaMensual = promedioRentaMensual;
		this.promedioAreaConstruccionAreaTerreno = promedioAreaConstruccionAreaTerreno;
		this.tasaAplicada = tasaAplicada;
		this.rentaBrutaMensual = rentaBrutaMensual;
		this.deducciones = deducciones;
		this.rentaNetaMensual = rentaNetaMensual;
		this.rentaNetaAnual = rentaNetaAnual;
		this.valorCapitalizacion = valorCapitalizacion;
		this.valorM2HomogenizadoSinGaraje = valorM2HomogenizadoSinGaraje;
	}

	public ComparacionMercadoLoteConstruccionRentaDTO(Integer metodoUsado, Long avaluoId){
        super(metodoUsado, avaluoId);
    }
	
	public ComparacionMercadoLoteConstruccionRentaDTO(ComparacionMercadoLoteConstruccionRenta comparacion) {
		super(comparacion);
		
		this.promedioValorUnitarioResultanteM2Mes = comparacion.getPromedioValorUnitarioResultanteM2Mes();
		this.promedioRentaMensual = comparacion.getPromedioRentaMensual();
		this.promedioAreaConstruccionAreaTerreno = comparacion.getPromedioAreaConstruccionAreaTerreno();
		this.tasaAplicada = comparacion.getTasaAplicada();
		this.rentaBrutaMensual = comparacion.getRentaBrutaMensual();
		this.deducciones = comparacion.getDeducciones();
		this.rentaNetaMensual = comparacion.getRentaNetaMensual();
		this.rentaNetaAnual = comparacion.getRentaNetaAnual();
		this.valorCapitalizacion = comparacion.getValorCapitalizacion();
		this.valorM2HomogenizadoSinGaraje = comparacion.getValorM2HomogenizadoSinGaraje();
		
	}
	
	public ComparacionMercadoLoteConstruccionRentaDTO() {
		super();
	}
	
	public ComparacionMercadoLoteConstruccionRentaDTO(MetodoValuacionDTO metodo){
		this.setId(metodo.getId());
		this.setAvaluoId( metodo.getAvaluoId() );
		this.setConceptoDelMetodo( metodo.getConceptoDelMetodo()  );
		this.setMetodoUsado( metodo.getMetodoUsado() );
		this.setOfertasConstruccionRenta(new ArrayList<>());
	}
	
	public BigDecimal getValorM2HomogenizadoSinGaraje() {
		return valorM2HomogenizadoSinGaraje;
	}

	public void setValorM2HomogenizadoSinGaraje(BigDecimal valorM2HomogenizadoSinGaraje) {
		this.valorM2HomogenizadoSinGaraje = valorM2HomogenizadoSinGaraje;
	}

	public BigDecimal getPromedioValorUnitarioResultanteM2Mes() {
		return promedioValorUnitarioResultanteM2Mes;
	}

	public void setPromedioValorUnitarioResultanteM2Mes(BigDecimal promedioValorUnitarioResultanteM2Mes) {
		this.promedioValorUnitarioResultanteM2Mes = promedioValorUnitarioResultanteM2Mes;
	}

	public BigDecimal getPromedioRentaMensual() {
		return promedioRentaMensual;
	}

	public void setPromedioRentaMensual(BigDecimal promedioRentaMensual) {
		this.promedioRentaMensual = promedioRentaMensual;
	}

	public BigDecimal getPromedioAreaConstruccionAreaTerreno() {
		return promedioAreaConstruccionAreaTerreno;
	}

	public void setPromedioAreaConstruccionAreaTerreno(BigDecimal promedioAreaConstruccionAreaTerreno) {
		this.promedioAreaConstruccionAreaTerreno = promedioAreaConstruccionAreaTerreno;
	}

	public BigDecimal getTasaAplicada() {
		return tasaAplicada;
	}

	public void setTasaAplicada(BigDecimal tasaAplicada) {
		this.tasaAplicada = tasaAplicada;
	}

	public BigDecimal getRentaBrutaMensual() {
		return rentaBrutaMensual;
	}

	public void setRentaBrutaMensual(BigDecimal rentaBrutaMensual) {
		this.rentaBrutaMensual = rentaBrutaMensual;
	}

	public BigDecimal getDeducciones() {
		return deducciones;
	}

	public void setDeducciones(BigDecimal deducciones) {
		this.deducciones = deducciones;
	}

	public BigDecimal getRentaNetaMensual() {
		return rentaNetaMensual;
	}

	public void setRentaNetaMensual(BigDecimal rentaNetaMensual) {
		this.rentaNetaMensual = rentaNetaMensual;
	}

	public BigDecimal getRentaNetaAnual() {
		return rentaNetaAnual;
	}

	public void setRentaNetaAnual(BigDecimal rentaNetaAnual) {
		this.rentaNetaAnual = rentaNetaAnual;
	}

	public BigDecimal getValorCapitalizacion() {
		return valorCapitalizacion;
	}

	public void setValorCapitalizacion(BigDecimal valorCapitalizacion) {
		this.valorCapitalizacion = valorCapitalizacion;
	}

	public List<OfertaLoteConConstruccionRentaDTO> getOfertasConstruccionRenta() {
		return ofertasConstruccionRenta;
	}

	public void setOfertasConstruccionRenta(List<OfertaLoteConConstruccionRentaDTO> ofertasConstruccionRenta) {
		this.ofertasConstruccionRenta = ofertasConstruccionRenta;
	}

	@Override
	public String toString() {
		return "ComparacionMercadoLoteConstruccionRentaDTO [id=" + id + ", promedioValorUnitarioResultanteM2Mes="
				+ promedioValorUnitarioResultanteM2Mes + ", promedioRentaMensual=" + promedioRentaMensual
				+ ", promedioAreaConstruccionAreaTerreno=" + promedioAreaConstruccionAreaTerreno + ", tasaAplicada="
				+ tasaAplicada + ", rentaBrutaMensual=" + rentaBrutaMensual + ", deducciones=" + deducciones
				+ ", rentaNetaMensual=" + rentaNetaMensual + ", rentaNetaAnual=" + rentaNetaAnual
				+ ", valorCapitalizacion=" + valorCapitalizacion + ", valorM2HomogenizadoSinGaraje="
				+ valorM2HomogenizadoSinGaraje + ", id=" + id + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deducciones == null) ? 0 : deducciones.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((promedioAreaConstruccionAreaTerreno == null) ? 0 : promedioAreaConstruccionAreaTerreno.hashCode());
		result = prime * result + ((promedioRentaMensual == null) ? 0 : promedioRentaMensual.hashCode());
		result = prime * result + ((promedioValorUnitarioResultanteM2Mes == null) ? 0
				: promedioValorUnitarioResultanteM2Mes.hashCode());
		result = prime * result + ((rentaBrutaMensual == null) ? 0 : rentaBrutaMensual.hashCode());
		result = prime * result + ((rentaNetaAnual == null) ? 0 : rentaNetaAnual.hashCode());
		result = prime * result + ((rentaNetaMensual == null) ? 0 : rentaNetaMensual.hashCode());
		result = prime * result + ((tasaAplicada == null) ? 0 : tasaAplicada.hashCode());
		result = prime * result + ((valorCapitalizacion == null) ? 0 : valorCapitalizacion.hashCode());
		result = prime * result
				+ ((valorM2HomogenizadoSinGaraje == null) ? 0 : valorM2HomogenizadoSinGaraje.hashCode());
		return result;
	}

	/*@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComparacionMercadoLoteConstruccionRentaDTO other = (ComparacionMercadoLoteConstruccionRentaDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}*/
	
	@Override
	public Object clone(){
		ComparacionMercadoLoteConstruccionRentaDTO clone =  new ComparacionMercadoLoteConstruccionRentaDTO(this.id, this.promedioValorUnitarioResultanteM2Mes,
				this.promedioRentaMensual, this.promedioAreaConstruccionAreaTerreno, this.tasaAplicada,
				this.rentaBrutaMensual, this.deducciones, this.rentaNetaMensual,
				this.rentaNetaAnual, this.valorCapitalizacion, this.valorM2HomogenizadoSinGaraje);
		return clone;
	}
	
	
	public void calcularPromedioValorUnitarioResultanteM2Mes() {
		if (ofertasConstruccionRenta != null && !ofertasConstruccionRenta.isEmpty()) {
			this.promedioValorUnitarioResultanteM2Mes = BigDecimal.valueOf(ofertasConstruccionRenta.stream()
					.mapToDouble((x) -> x.getValorUnitarioResultanteM2() == null ? 0 : x.getValorUnitarioResultanteM2().doubleValue())
					.average().getAsDouble());
		} else {
			this.promedioValorUnitarioResultanteM2Mes = BigDecimal.ZERO;
		}
	}
	
	public void calcularPromedioRentaMensual() {
		if (ofertasConstruccionRenta != null && !ofertasConstruccionRenta.isEmpty()) {
			this.promedioRentaMensual = BigDecimal.valueOf(ofertasConstruccionRenta.stream()
					.mapToDouble((x) -> x.getValorOferta() == null ? 0 : x.getValorOferta().doubleValue())
					.average().getAsDouble());
		} else {
			this.promedioRentaMensual = BigDecimal.ZERO;
		}
	}
	
	public void calcularAreaConstruccionAreaTerrenoSujeto() {
		if (this.areaConsttruida != null && this.areaLote != null && !this.areaLote.equals(BigDecimal.ZERO)) {
			this.promedioAreaConstruccionAreaTerreno = this.areaConsttruida.divide(this.areaLote, 8 , RoundingMode.HALF_UP);
		} else {
			this.promedioAreaConstruccionAreaTerreno = BigDecimal.ZERO;
		}
	}
	
	
	public void calcularRentaBrutaMensual() {
		if (promedioValorUnitarioResultanteM2Mes != null && areaConsttruida!= null) {
			this.rentaBrutaMensual = this.promedioValorUnitarioResultanteM2Mes.multiply(areaConsttruida);
		} else {
			this.rentaBrutaMensual = BigDecimal.ZERO;
		}
	}
	
	public void calcularRentaNetaMensual() {
		
		if (this.rentaBrutaMensual != null) {
			this.rentaNetaMensual = this.rentaBrutaMensual;
			if(deducciones != null){
				this.rentaNetaMensual = this.rentaNetaMensual.subtract(deducciones);
			}
		} else {
			this.rentaNetaMensual = BigDecimal.ZERO;
		}
	}
	
	public void calcularRentaNetaAnual() {
		if (promedioValorUnitarioResultanteM2Mes != null && areaConsttruida!= null) {
			if (this.rentaNetaMensual != null) {
				this.rentaNetaAnual = this.rentaNetaMensual.multiply(BigDecimal.valueOf(12));
			} else {
				this.rentaNetaAnual = BigDecimal.ZERO;
			}
		}
	}
	
	public void calcularValorCapitalizacion() {
		if (this.rentaNetaAnual != null && this.tasaAplicada != null) {
			this.valorCapitalizacion = this.rentaNetaAnual.divide(this.tasaAplicada.divide(BigDecimal.valueOf(100), 8 , RoundingMode.HALF_UP), 8 , RoundingMode.HALF_UP );
		} else {
			this.valorCapitalizacion = BigDecimal.ZERO;
		}
	}
	
	
	public void calcularValorIntegralCTHomogenizado() {
		if (promedioValorUnitarioResultanteM2Mes != null && areaConsttruida!= null && !BigDecimal.ZERO.equals(areaConsttruida)) {
			this.valorM2HomogenizadoSinGaraje = this.valorCapitalizacion.divide(areaConsttruida, 8 , RoundingMode.HALF_UP );
		} else {
			this.valorM2HomogenizadoSinGaraje = BigDecimal.ZERO;
		}
	}
	
}
