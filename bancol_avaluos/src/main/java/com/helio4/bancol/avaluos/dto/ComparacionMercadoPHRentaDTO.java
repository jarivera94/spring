package com.helio4.bancol.avaluos.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import com.helio4.bancol.avaluos.dto.GarajeDTO.ListaTipoGaraje;
import com.helio4.bancol.avaluos.modelo.ComparacionMercadoPhRenta;

public class ComparacionMercadoPHRentaDTO extends ComparacionMercadoPHDTO implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	protected BigDecimal tasaAplicada;
	protected BigDecimal rentaBrutaMensual;
	protected BigDecimal deducciones;
	protected BigDecimal rentaNetaMensual;
	protected BigDecimal rentaNetaAnual;
	protected BigDecimal valorCapitalizacion;
	protected BigDecimal valorM2HomogenizadoSinGaraje;

	public ComparacionMercadoPHRentaDTO(Long idComparacionMercadoRenta, BigDecimal tasaAplicada,
			BigDecimal rentaBrutaMensual, BigDecimal deducciones, BigDecimal rentaNetaMensual,
			BigDecimal rentaNetaAnual, BigDecimal valorCapitalizacion, BigDecimal valorM2HomogenizadoSinGaraje,
			Long comparacionMercadoPhId) {
		super();
		this.id = idComparacionMercadoRenta;
		this.tasaAplicada = tasaAplicada;
		this.rentaBrutaMensual = rentaBrutaMensual;
		this.deducciones = deducciones;
		this.rentaNetaMensual = rentaNetaMensual;
		this.rentaNetaAnual = rentaNetaAnual;
		this.valorCapitalizacion = valorCapitalizacion;
		this.valorM2HomogenizadoSinGaraje = valorM2HomogenizadoSinGaraje;
		this.id = comparacionMercadoPhId;
	}
	
	public ComparacionMercadoPHRentaDTO(MetodoValuacionDTO metodo){
		this.setId(metodo.getId());
		this.setAvaluoId( metodo.getAvaluoId() );
		this.setConceptoDelMetodo( metodo.getConceptoDelMetodo()  );
		this.setMetodoUsado( metodo.getMetodoUsado() );
		this.setOfertasPH(new ArrayList<>());
	}

	public ComparacionMercadoPHRentaDTO() {
		super();
	}
	
	public ComparacionMercadoPHRentaDTO(Integer metodoUsado, Long avaluoId){
        super(metodoUsado, avaluoId);
    }

	public ComparacionMercadoPHRentaDTO(ComparacionMercadoPhRenta comparacion) {
		super(comparacion);

		this.tasaAplicada = comparacion.getTasaAplicada();
		this.rentaBrutaMensual = comparacion.getRentaBrutaMensual();
		this.deducciones = comparacion.getDeducciones();
		this.rentaNetaMensual = comparacion.getRentaNetaMensual();
		this.rentaNetaAnual = comparacion.getRentaNetaAnual();
		this.valorCapitalizacion = comparacion.getValorCapitalizacion();
		this.valorM2HomogenizadoSinGaraje = comparacion.getValorM2HomogenizadoSinGaraje();

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

	public BigDecimal getValorM2HomogenizadoSinGaraje() {
		return valorM2HomogenizadoSinGaraje;
	}

	public void setValorM2HomogenizadoSinGaraje(BigDecimal valorM2HomogenizadoSinGaraje) {
		this.valorM2HomogenizadoSinGaraje = valorM2HomogenizadoSinGaraje;
	}

	@Override
	public String toString() {
		return "ComparacionMercadoPhRentaDTO [idComparacionMercadoRenta=" + id + ", tasaAplicada=" + tasaAplicada
				+ ", rentaBrutaMensual=" + rentaBrutaMensual + ", deducciones=" + deducciones + ", rentaNetaMensual="
				+ rentaNetaMensual + ", rentaNetaAnual=" + rentaNetaAnual + ", valorCapitalizacion="
				+ valorCapitalizacion + ", valorM2HomogenizadoSinGaraje=" + valorM2HomogenizadoSinGaraje + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((deducciones == null) ? 0 : deducciones.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComparacionMercadoPHRentaDTO other = (ComparacionMercadoPHRentaDTO) obj;
		
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}*/

	@Override
	public Object clone() {
		ComparacionMercadoPHRentaDTO clone = new ComparacionMercadoPHRentaDTO(this.id, this.tasaAplicada,
				this.rentaBrutaMensual, this.deducciones, this.rentaNetaMensual, this.rentaNetaAnual,
				this.valorCapitalizacion, this.valorM2HomogenizadoSinGaraje, this.id);
		return clone;
	}

	public void calcularRentaBrutaMensual() {
		if (this.promedioValorM2HomogenizadoSinGaraje != null && area != null) {
			this.rentaBrutaMensual = this.promedioValorM2HomogenizadoSinGaraje.multiply(area);
		} else {
			this.rentaBrutaMensual = BigDecimal.ZERO;
		}
	}

	public void calcularRentaNetaMensual() {
		if (this.rentaBrutaMensual != null) {
			this.rentaNetaMensual = rentaBrutaMensual;
			if(deducciones != null){
				this.rentaNetaMensual = this.rentaNetaMensual.subtract(deducciones);
			}			
		} else {
			this.rentaNetaMensual = BigDecimal.ZERO;
		}
	}

	public void calcularRentaNetaAnual() {
		if (this.rentaNetaMensual != null) {
			this.rentaNetaAnual = this.rentaNetaMensual.multiply(BigDecimal.valueOf(12));
		} else {
			this.rentaNetaAnual = BigDecimal.ZERO;
		}
	}

	public void calcularValorPorCapitalizacion() {

		if (this.rentaNetaAnual != null && this.tasaAplicada != null && !BigDecimal.ZERO.equals(this.tasaAplicada)) {
			this.valorCapitalizacion = this.rentaNetaAnual.divide(this.tasaAplicada.divide(BigDecimal.valueOf(100)),  8 , RoundingMode.HALF_UP);
		} else {
			this.valorCapitalizacion = BigDecimal.ZERO;
		}
	}

	public void calcularValorM2HomogenizadoSinGarage() {

		if (this.valorCapitalizacion != null && area != null && !BigDecimal.ZERO.equals(this.area)) {
			this.valorM2HomogenizadoSinGaraje = this.valorCapitalizacion.divide(area,  8 , RoundingMode.HALF_UP);
		} else {
			this.valorM2HomogenizadoSinGaraje = BigDecimal.ZERO;
		}
	}

	@Override
	public void calcularPromedioValorComercial() {
		if (this.valorAdministracion != null && area != null && area.compareTo(BigDecimal.ZERO)!=0) {
			this.promedioValorComercial = this.valorAdministracion.divide(area, 8 , RoundingMode.HALF_UP );
		} else {
			this.promedioValorComercial = BigDecimal.ZERO;
		}

	}
	
	public void calcularValorM2HomogenizadoGJ(ListaTipoGaraje tipoGaraje) {

		if (tipoGaraje != null) {

			if (tipoGaraje.equals(ListaTipoGaraje.Privado) && this.valorM2HomogenizadoSinGaraje != null) {
				this.valorm2homogenizadoGJ = this.valorM2HomogenizadoSinGaraje;

			} else if (tipoGaraje.equals(ListaTipoGaraje.Exclusivo) && this.valorM2HomogenizadoSinGaraje != null) {

				this.garajes = this.garajes == null ? 0 : this.garajes;

				if (this.area != null && this.area.compareTo(BigDecimal.ZERO) != 0 && this.tasaAplicada != null && this.tasaAplicada.compareTo(BigDecimal.ZERO) != 0) {
					this.valorm2homogenizadoGJ = this.valorM2HomogenizadoSinGaraje.multiply(area)
							.add(new BigDecimal(this.garajes).multiply(new BigDecimal(this.garajes))
									.multiply(BigDecimal.valueOf(12)).divide(tasaAplicada, 8, BigDecimal.ROUND_HALF_UP))
							.divide(area, 8, BigDecimal.ROUND_HALF_UP);
					
				}
			} else {
				this.valorm2homogenizadoGJ = BigDecimal.ZERO;
			}

		}

	}


}
