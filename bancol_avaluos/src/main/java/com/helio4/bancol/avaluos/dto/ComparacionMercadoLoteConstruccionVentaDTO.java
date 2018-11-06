package com.helio4.bancol.avaluos.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.helio4.bancol.avaluos.modelo.ComparacionMercadoLoteConstruccionVenta;
import com.jidesoft.utils.BigDecimalMathUtils;

public class ComparacionMercadoLoteConstruccionVentaDTO extends ComparacionMercadoLoteConstruccionDTO implements Serializable, Cloneable {
	
	private static final long serialVersionUID = 1L;
	
	protected BigDecimal promedioValorComercial;
	protected BigDecimal promedioValorM2Ajustado;
	protected BigDecimal promedioAreaConstruccionAreaTerreno;
	protected BigDecimal promedioPrecioUnitario;
	protected BigDecimal promedioPrecioUnitarioM2Homogenizado;
	protected BigDecimal promedioM2ConstruccionTerrenoHomogenizado;
	protected BigDecimal promedioM2ConstruccionTerreno;
	protected BigDecimal mediaArtimetica;
	protected BigDecimal desviacionEstandar;
	protected BigDecimal coheficienteVariacion;
	
	protected List<OfertaLoteConConstruccionVentaDTO> ofertasConstruccionVenta;
	
	public ComparacionMercadoLoteConstruccionVentaDTO(Integer metodoUsado, Long avaluoId){
        super(metodoUsado, avaluoId);
    }
	
	public ComparacionMercadoLoteConstruccionVentaDTO(long id, BigDecimal promedioValorComercial,
			BigDecimal promedioValorM2Ajustado, BigDecimal promedioAreaConstruccionAreaTerreno,
			BigDecimal promedioPrecioUnitario, BigDecimal promedioPrecioUnitarioM2Homogenizado,
			BigDecimal promedioM2ConstruccionTerrenoHomogenizado, BigDecimal promedioM2ConstruccionTerreno,
			BigDecimal mediaArtimetica, BigDecimal desviacionEstandar, BigDecimal coheficienteVariacion
			) {
		super();
		this.id = id;
		this.promedioValorComercial = promedioValorComercial;
		this.promedioValorM2Ajustado = promedioValorM2Ajustado;
		this.promedioAreaConstruccionAreaTerreno = promedioAreaConstruccionAreaTerreno;
		this.promedioPrecioUnitario = promedioPrecioUnitario;
		this.promedioPrecioUnitarioM2Homogenizado = promedioPrecioUnitarioM2Homogenizado;
		this.promedioM2ConstruccionTerrenoHomogenizado = promedioM2ConstruccionTerrenoHomogenizado;
		this.promedioM2ConstruccionTerreno = promedioM2ConstruccionTerreno;
		this.mediaArtimetica = mediaArtimetica;
		this.desviacionEstandar = desviacionEstandar;
		this.coheficienteVariacion = coheficienteVariacion;
	}
	
	public ComparacionMercadoLoteConstruccionVentaDTO(ComparacionMercadoLoteConstruccionVenta comparacion) {
		super(comparacion);
		
		this.promedioAreaConstruccionAreaTerreno =  comparacion.getPromedioAreaConstruccionAreaTerreno();
		this.promedioPrecioUnitario = comparacion.getPromedioPrecioUnitario();
		this.promedioPrecioUnitarioM2Homogenizado = comparacion.getPromedioPrecioUnitarioM2Homogenizado();
		this.promedioM2ConstruccionTerrenoHomogenizado = comparacion.getPromedioM2ConstruccionTerrenoHomogenizado();
		this.promedioM2ConstruccionTerreno = comparacion.getPromedioM2ConstruccionTerreno();
		this.mediaArtimetica = comparacion.getMediaArtimetica();
		this.desviacionEstandar = comparacion.getDesviacionEstandar();
		this.coheficienteVariacion = comparacion.getCoheficienteVariacion();
		this.promedioValorM2Ajustado = comparacion.getPromedioValorM2Ajustado();
		this.promedioValorComercial =  comparacion.getPromedioValorComercial();
		
	}
	
	public ComparacionMercadoLoteConstruccionVentaDTO(MetodoValuacionDTO metodo){
		this.setId(metodo.getId());
		this.setAvaluoId( metodo.getAvaluoId() );
		this.setConceptoDelMetodo( metodo.getConceptoDelMetodo()  );
		this.setMetodoUsado( metodo.getMetodoUsado() );
		this.setOfertasConstruccionVenta(new ArrayList<>());
	}
	
	public ComparacionMercadoLoteConstruccionVentaDTO() {
		super();
	}
	
	public BigDecimal getPromedioAreaConstruccionAreaTerreno() {
		return promedioAreaConstruccionAreaTerreno;
	}

	public void setPromedioAreaConstruccionAreaTerreno(BigDecimal promedioAreaConstruccionAreaTerreno) {
		this.promedioAreaConstruccionAreaTerreno = promedioAreaConstruccionAreaTerreno;
	}

	public BigDecimal getPromedioValorComercial() {
		return promedioValorComercial;
	}

	public void setPromedioValorComercial(BigDecimal promedioValorComercial) {
		this.promedioValorComercial = promedioValorComercial;
	}

	public BigDecimal getPromedioValorM2Ajustado() {
		return promedioValorM2Ajustado;
	}

	public void setPromedioValorM2Ajustado(BigDecimal promedioValorM2Ajustado) {
		this.promedioValorM2Ajustado = promedioValorM2Ajustado;
	}

	public BigDecimal getPromedioPrecioUnitario() {
		return promedioPrecioUnitario;
	}

	public void setPromedioPrecioUnitario(BigDecimal promedioPrecioUnitario) {
		this.promedioPrecioUnitario = promedioPrecioUnitario;
	}

	public BigDecimal getPromedioPrecioUnitarioM2Homogenizado() {
		return promedioPrecioUnitarioM2Homogenizado;
	}

	public void setPromedioPrecioUnitarioM2Homogenizado(BigDecimal promedioPrecioUnitarioM2Homogenizado) {
		this.promedioPrecioUnitarioM2Homogenizado = promedioPrecioUnitarioM2Homogenizado;
	}

	public BigDecimal getPromedioM2ConstruccionTerrenoHomogenizado() {
		return promedioM2ConstruccionTerrenoHomogenizado;
	}

	public void setPromedioM2ConstruccionTerrenoHomogenizado(BigDecimal promedioM2ConstruccionTerrenoHomogenizado) {
		this.promedioM2ConstruccionTerrenoHomogenizado = promedioM2ConstruccionTerrenoHomogenizado;
	}

	public BigDecimal getPromedioM2ConstruccionTerreno() {
		return promedioM2ConstruccionTerreno;
	}

	public void setPromedioM2ConstruccionTerreno(BigDecimal promedioM2ConstruccionTerreno) {
		this.promedioM2ConstruccionTerreno = promedioM2ConstruccionTerreno;
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

	public BigDecimal getCoheficienteVariacion() {
		return coheficienteVariacion;
	}

	public void setCoheficienteVariacion(BigDecimal coheficienteVariacion) {
		this.coheficienteVariacion = coheficienteVariacion;
	}

	public List<OfertaLoteConConstruccionVentaDTO> getOfertasConstruccionVenta() {
		return ofertasConstruccionVenta;
	}

	public void setOfertasConstruccionVenta(List<OfertaLoteConConstruccionVentaDTO> ofertasConstruccionVenta) {
		this.ofertasConstruccionVenta = ofertasConstruccionVenta;
	}

	@Override
	public String toString() {
		return "ComparacionMercadoLoteConstruccionVentaDTO [id=" + id + ", promedioAreaConstruccionAreaTerreno="
				+ promedioAreaConstruccionAreaTerreno + ", promedioPrecioUnitario=" + promedioPrecioUnitario
				+ ", promedioPrecioUnitarioM2Homogenizado=" + promedioPrecioUnitarioM2Homogenizado
				+ ", promedioM2ConstruccionTerrenoHomogenizado=" + promedioM2ConstruccionTerrenoHomogenizado
				+ ", promedioM2ConstruccionTerreno=" + promedioM2ConstruccionTerreno + ", mediaArtimetica="
				+ mediaArtimetica + ", desviacionEstandar=" + desviacionEstandar + ", coheficienteVariacion="
				+ coheficienteVariacion + "]";
	}	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coheficienteVariacion == null) ? 0 : coheficienteVariacion.hashCode());
		result = prime * result + ((desviacionEstandar == null) ? 0 : desviacionEstandar.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((mediaArtimetica == null) ? 0 : mediaArtimetica.hashCode());
		result = prime * result
				+ ((promedioAreaConstruccionAreaTerreno == null) ? 0 : promedioAreaConstruccionAreaTerreno.hashCode());
		result = prime * result
				+ ((promedioM2ConstruccionTerreno == null) ? 0 : promedioM2ConstruccionTerreno.hashCode());
		result = prime * result + ((promedioM2ConstruccionTerrenoHomogenizado == null) ? 0
				: promedioM2ConstruccionTerrenoHomogenizado.hashCode());
		result = prime * result + ((promedioPrecioUnitario == null) ? 0 : promedioPrecioUnitario.hashCode());
		result = prime * result + ((promedioPrecioUnitarioM2Homogenizado == null) ? 0
				: promedioPrecioUnitarioM2Homogenizado.hashCode());
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
		ComparacionMercadoLoteConstruccionVentaDTO other = (ComparacionMercadoLoteConstruccionVentaDTO) obj;
		if (!id.equals(other.id))
			return false;
		return true;
	}*/

	@Override
	public Object clone(){
		ComparacionMercadoLoteConstruccionVentaDTO clone = new ComparacionMercadoLoteConstruccionVentaDTO(this.id, this.promedioValorComercial,
				this.promedioValorM2Ajustado, this.promedioAreaConstruccionAreaTerreno,
				this.promedioPrecioUnitario, this.promedioPrecioUnitarioM2Homogenizado,
				this.promedioM2ConstruccionTerrenoHomogenizado, this.promedioM2ConstruccionTerreno,
				this.mediaArtimetica, this.desviacionEstandar, this.coheficienteVariacion);
		return clone;
	}
	
	public void calcularPromedioValorComercial() {
		if (ofertasConstruccionVenta != null && !ofertasConstruccionVenta.isEmpty()) {
			this.promedioValorComercial = BigDecimal.valueOf(ofertasConstruccionVenta.stream()
					.mapToDouble((x) -> x.getValorOferta() == null ? 0 : x.getValorOferta().doubleValue())
					.average().getAsDouble());
		} else {
			this.promedioValorComercial = BigDecimal.ZERO;
		}
	}
	
	public void calcularPromedioValorM2AjustadoLoteSinConstruccion() {
		if (ofertasConstruccionVenta != null && !ofertasConstruccionVenta.isEmpty()) {
			this.promedioValorM2Ajustado = BigDecimal.valueOf(ofertasConstruccionVenta.stream()
					.mapToDouble((x) -> x.getValorM2Ajustado() == null ? 0 : x.getValorM2Ajustado().doubleValue())
					.average().getAsDouble());
		} else {
			this.promedioValorM2Ajustado = BigDecimal.ZERO;
		}
	}
	
	public void calcularAreaConstruccionAreaTerrenoSujeto() {
		if (this.areaConsttruida != null && this.areaLote != null && !this.areaLote.equals(BigDecimal.ZERO)) {
			this.promedioAreaConstruccionAreaTerreno = this.areaConsttruida.divide(this.areaLote, 8, RoundingMode.HALF_UP);
		} else {
			this.promedioAreaConstruccionAreaTerreno = BigDecimal.ZERO;
		}
	}

	public void calcularPromedioPrecioUnitario() {
		if (ofertasConstruccionVenta != null && !ofertasConstruccionVenta.isEmpty()) {
			this.promedioPrecioUnitario = BigDecimal.valueOf(ofertasConstruccionVenta.stream()
					.mapToDouble((x) -> x.getPrecioUnitarioM2() == null ? 0 : x.getPrecioUnitarioM2().doubleValue())
					.average().getAsDouble());
		}
	}

	
	public void calcularPromedioPrecioUnitarioM2Homogenizado() {
		if (ofertasConstruccionVenta != null && !ofertasConstruccionVenta.isEmpty()) {
			this.promedioPrecioUnitarioM2Homogenizado = BigDecimal
					.valueOf(
							ofertasConstruccionVenta.stream()
									.mapToDouble((x) -> x.getValorUnitarioM2Homogenizado() == null ? 0
											: x.getValorUnitarioM2Homogenizado().doubleValue())
									.average().getAsDouble());
		}
	}
	
	public void calcularPromedioM2ConstruccionTerrenoHomogenizado() {

		if (this.promedioPrecioUnitarioM2Homogenizado != null) {
			promedioM2ConstruccionTerrenoHomogenizado = promedioPrecioUnitarioM2Homogenizado;
		} else {
			promedioM2ConstruccionTerrenoHomogenizado = BigDecimal.ZERO;
		}

	}
	
	public void calcularPromedioM2ConstruccionTerreno() {

		if (this.promedioPrecioUnitario != null) {
			this.promedioM2ConstruccionTerreno = this.promedioPrecioUnitario;
		} else {
			promedioM2ConstruccionTerreno = BigDecimal.ZERO;
		}

	}
	
	public void calcularMediaAritmetica() {

		
		
		if (ofertasConstruccionVenta != null && !ofertasConstruccionVenta.isEmpty()) {
			this.mediaArtimetica = BigDecimal
					.valueOf(
							ofertasConstruccionVenta.stream()
									.mapToDouble((x) -> x.getValorM2Ajustado() == null ? 0
											: x.getValorM2Ajustado().doubleValue())
									.average().getAsDouble());
		}else{
			this.mediaArtimetica = BigDecimal.ZERO;
		}
		
	}
	
	public void calcularDesviacionEstandar() {

		List<BigDecimal> listaValorM2Ajustado;
		
		if(ofertasConstruccionVenta!=null){
			listaValorM2Ajustado = ofertasConstruccionVenta.stream()
					.map(sc -> sc.getValorM2Ajustado()).collect(Collectors.toList());
			
			if(listaValorM2Ajustado!= null && listaValorM2Ajustado.size()>1){
				BigDecimal promedio = BigDecimalMathUtils.sum(listaValorM2Ajustado).divide(BigDecimal.valueOf(listaValorM2Ajustado.size()), 8, RoundingMode.HALF_UP);
				
				BigDecimal sumatoria = BigDecimal.ZERO;
				
				for(BigDecimal x : listaValorM2Ajustado){
					BigDecimal diferencia = x.subtract(promedio);
					
					BigDecimal cuadrado = BigDecimal.valueOf(Math.pow(diferencia.doubleValue(), 2));
					sumatoria = sumatoria.add(cuadrado);
				}
				
				BigDecimal varianza = sumatoria.divide(BigDecimal.valueOf(listaValorM2Ajustado.size()-1), 8, RoundingMode.HALF_UP);
				
				desviacionEstandar = BigDecimalMathUtils.sqrt(varianza);
			}else{
				desviacionEstandar = BigDecimal.ZERO;
			}

			/*if (!listaValorM2Ajustado.isEmpty() && !listaValorM2Ajustado.contains(null)) {
				desviacionEstandar = BigDecimalMathUtils.stddev(listaValorM2Ajustado, true,
						new MathContext(4, RoundingMode.HALF_EVEN));
			}else{
				desviacionEstandar = BigDecimal.ZERO;
			}*/
		}else{
			desviacionEstandar = BigDecimal.ZERO;
		}
	}
	
	public void calcularCoheficienteVariacion() {

		if (this.desviacionEstandar != null && mediaArtimetica != null && mediaArtimetica.compareTo(BigDecimal.ZERO)  != 0) {

			this.coheficienteVariacion = this.desviacionEstandar.divide(mediaArtimetica,RoundingMode.HALF_UP).multiply(new BigDecimal(100));
		} else {
			this.coheficienteVariacion = BigDecimal.ZERO;
		}

	}
	
}
