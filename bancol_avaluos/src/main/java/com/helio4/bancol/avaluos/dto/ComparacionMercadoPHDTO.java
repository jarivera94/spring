package com.helio4.bancol.avaluos.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.helio4.bancol.avaluos.controlador.ListaDesplegable;
import com.helio4.bancol.avaluos.dto.GarajeDTO.ListaTipoGaraje;
import com.helio4.bancol.avaluos.modelo.ComparacionMercadoPh;
import com.helio4.bancol.avaluos.servicio.util.Constantes;

public class ComparacionMercadoPHDTO extends MetodoValuacionDTO implements Serializable,Cloneable{

	private static final long serialVersionUID = -8986702273545459414L;
	protected BigDecimal desviacion;
	protected BigDecimal promedioM2;
	protected BigDecimal coeficienteVariacion;
	protected Integer numeroDatos;
	protected BigDecimal raiz;
	protected BigDecimal tstudent;
	protected BigDecimal limiteInferior;
	protected BigDecimal limiteSuperior;
	
	// sujeto
	protected Integer piso;
	protected BigDecimal area;
	protected Integer garajes;
	protected Integer edad;
	protected BigDecimal valorAdministracion;
	protected BigDecimal areaLibre;
	protected BigDecimal precioUnitarioAdministracionM2;
	//promedios
  	protected BigDecimal promedioValorUnitarioGaraje;
    protected BigDecimal promedioValorM2HomogenizadoSinGaraje;
    protected BigDecimal promedioValorComercial;
    protected BigDecimal promedioValorM2SinGarajeNoHomogenizado;
    protected BigDecimal promedioValorM2HomogenizadoSinGarajeAreaLibre;
    //totales
    protected BigDecimal factorSuperficie;
    protected BigDecimal factorEdad;
    protected BigDecimal valorM2AreaLibre;
    protected BigDecimal valorUnitarioGaraje;
    
    protected BigDecimal valorm2homogenizadoGJ;
    
  //variables auxiliares
  	protected Integer tipoProyecto;

    protected List<OfertaPHDTO> ofertasPH;
    
    public ComparacionMercadoPHDTO(){}

    public ComparacionMercadoPHDTO(ComparacionMercadoPh comparacion){
    	
    	super(comparacion);
    	
    	this.desviacion = comparacion.getDesviacion();
    	this.promedioM2 = comparacion.getPromedioM2();
    	this.coeficienteVariacion = comparacion.getCoeficienteVariacion();
    	this.numeroDatos = comparacion.getNumeroDatos();
    	this.raiz = comparacion.getRaiz();
    	this.tstudent = comparacion.getTstudent();
    	this.limiteInferior = comparacion.getLimiteInferior();
    	this.limiteSuperior =  comparacion.getLimiteSuperior();
    	this.piso = comparacion.getPiso();
    	this.area =  comparacion.getArea();
    	this.garajes = comparacion.getGarajes();
    	this.edad = comparacion.getEdad();
    	this.valorAdministracion = comparacion.getValorAdministracion();
    	this.areaLibre = comparacion.getAreaLibre();
    	this.precioUnitarioAdministracionM2 = comparacion.getPrecioUnitarioAdministracionM2();
      	this.promedioValorUnitarioGaraje = comparacion.getPromedioValorUnitarioGaraje();
        this.promedioValorM2HomogenizadoSinGaraje = comparacion.getPromedioValorM2HomogenizadoSinGaraje();
        this.promedioValorComercial = comparacion.getPromedioValorComercial();
        this.promedioValorM2SinGarajeNoHomogenizado = comparacion.getPromedioValorM2SinGarajeNoHomogenizado();
        this.promedioValorM2HomogenizadoSinGarajeAreaLibre = comparacion.getPromedioValorM2HomogenizadoSinGarajeAreaLibre();
        this.factorSuperficie = comparacion.getFactorSuperficie();
        this.factorEdad = comparacion.getFactorEdad();
        this.valorM2AreaLibre = comparacion.getValorM2AreaLibre();
        this.valorUnitarioGaraje = comparacion.getValorUnitarioGaraje();
        this.tipoProyecto= comparacion.getTipoProyecto();
        this.valorm2homogenizadoGJ = comparacion.getValorm2homogenizadoGJ();
    	
    }
    
	public ComparacionMercadoPHDTO(Integer metodoUsado, Long avaluoId){
        super(metodoUsado, avaluoId);
    }

	public ComparacionMercadoPHDTO(MetodoValuacionDTO metodo){
		this.setId(metodo.getId());
		this.setAvaluoId( metodo.getAvaluoId() );
		this.setConceptoDelMetodo( metodo.getConceptoDelMetodo()  );
		this.setMetodoUsado( metodo.getMetodoUsado() );
		this.setOfertas((metodo.getOfertas()!=null)? metodo.getOfertas(): new ArrayList<OfertaDTO>()  );
	}
	
	public ComparacionMercadoPHDTO(
			Long id, Integer metodoUsado,
			String conceptoDelMetodo, Long avaluoId, BigDecimal desviacion,
			BigDecimal promedioM2,
			BigDecimal coeficienteVariacion,
			Integer numeroDatos,
			BigDecimal raiz,
			BigDecimal tstudent,
			BigDecimal limiteInferior,
			BigDecimal limiteSuperior, List<OfertaDTO> ofertas, BigDecimal valorM2areaLibre, BigDecimal valorUnitarioGaraje ) {
		this.setId(id);
		this.avaluoId = avaluoId;
		this.metodoUsado = metodoUsado;
		this.conceptoDelMetodo = conceptoDelMetodo;
		this.avaluoId = avaluoId;
		this.desviacion = desviacion;
		this.promedioM2 = promedioM2;
		this.coeficienteVariacion = coeficienteVariacion;
		this.numeroDatos = numeroDatos;
		this.raiz = raiz;
		this.tstudent = tstudent;
		this.limiteInferior = limiteInferior;
		this.limiteSuperior = limiteSuperior;	
		this.ofertas = ofertas;
		this.valorM2AreaLibre = valorM2areaLibre;
		this.valorUnitarioGaraje = valorUnitarioGaraje;
	}
	public BigDecimal getPromedioM2() {
		return promedioM2;
	}

	public void setPromedioM2(BigDecimal promedioM2) {
		this.promedioM2 = promedioM2;
	}

	public BigDecimal getCoeficienteVariacion() {
		return coeficienteVariacion;
	}

	public void setCoeficienteVariacion(BigDecimal coeficienteVariacion) {
		this.coeficienteVariacion = coeficienteVariacion;
	}

	public Integer getNumeroDatos() {
		return numeroDatos;
	}

	public void setNumeroDatos(Integer numeroDatos) {
		this.numeroDatos = numeroDatos;
	}

	public BigDecimal getRaiz() {
		return raiz;
	}

	public void setRaiz(BigDecimal raiz) {
		this.raiz = raiz;
	}

	public BigDecimal getTstudent() {
		return tstudent;
	}

	public void setTstudent(BigDecimal tstudent) {
		this.tstudent = tstudent;
	}

	public BigDecimal getLimiteInferior() {
		return limiteInferior;
	}

	public void setLimiteInferior(BigDecimal limiteInferior) {
		this.limiteInferior = limiteInferior;
	}

	public BigDecimal getLimiteSuperior() {
		return limiteSuperior;
	}

	public void setLimiteSuperior(BigDecimal limiteSuperior) {
		this.limiteSuperior = limiteSuperior;
	}

	public BigDecimal getDesviacion() {
		return desviacion;
	}

	public void setDesviacion(BigDecimal desviacion) {
		this.desviacion = desviacion;
	}
	
	public Integer getPiso() {
		return piso;
	}

	public void setPiso(Integer piso) {
		this.piso = piso;
	}

	public BigDecimal getArea() {
		return area;
	}

	public void setArea(BigDecimal area) {
		this.area = area;
	}

	public Integer getGarajes() {
		return garajes;
	}

	public void setGarajes(Integer garajes) {
		this.garajes = garajes;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public BigDecimal getValorAdministracion() {
		return valorAdministracion;
	}

	public void setValorAdministracion(BigDecimal valorAdministracion) {
		this.valorAdministracion = valorAdministracion;
	}

	public BigDecimal getAreaLibre() {
		return areaLibre;
	}

	public void setAreaLibre(BigDecimal areaLibre) {
		this.areaLibre = areaLibre;
	}

	public BigDecimal getPrecioUnitarioAdministracionM2() {
		return precioUnitarioAdministracionM2;
	}

	public void setPrecioUnitarioAdministracionM2(BigDecimal precioUnitarioAdministracionM2) {
		this.precioUnitarioAdministracionM2 = precioUnitarioAdministracionM2;
	}

	public BigDecimal getPromedioValorUnitarioGaraje() {
		return promedioValorUnitarioGaraje;
	}

	public void setPromedioValorUnitarioGaraje(BigDecimal promedioValorUnitarioGaraje) {
		this.promedioValorUnitarioGaraje = promedioValorUnitarioGaraje;
	}

	public BigDecimal getPromedioValorM2HomogenizadoSinGaraje() {
		return promedioValorM2HomogenizadoSinGaraje;
	}

	public void setPromedioValorM2HomogenizadoSinGaraje(BigDecimal promedioValorM2HomogenizadoSinGaraje) {
		this.promedioValorM2HomogenizadoSinGaraje = promedioValorM2HomogenizadoSinGaraje;
	}

	public BigDecimal getPromedioValorComercial() {
		return promedioValorComercial;
	}

	public void setPromedioValorComercial(BigDecimal promedioValorComercial) {
		this.promedioValorComercial = promedioValorComercial;
	}

	public BigDecimal getPromedioValorM2SinGarajeNoHomogenizado() {
		return promedioValorM2SinGarajeNoHomogenizado;
	}

	public void setPromedioValorM2SinGarajeNoHomogenizado(BigDecimal promedioValorM2SinGarajeNoHomogenizado) {
		this.promedioValorM2SinGarajeNoHomogenizado = promedioValorM2SinGarajeNoHomogenizado;
	}

	public BigDecimal getPromedioValorM2HomogenizadoSinGarajeAreaLibre() {
		return promedioValorM2HomogenizadoSinGarajeAreaLibre;
	}

	public void setPromedioValorM2HomogenizadoSinGarajeAreaLibre(
			BigDecimal promedioValorM2HomogenizadoSinGarajeAreaLibre) {
		this.promedioValorM2HomogenizadoSinGarajeAreaLibre = promedioValorM2HomogenizadoSinGarajeAreaLibre;
	}

	public BigDecimal getFactorSuperficie() {
		return factorSuperficie;
	}

	public void setFactorSuperficie(BigDecimal factorSuperficie) {
		this.factorSuperficie = factorSuperficie;
	}

	public BigDecimal getFactorEdad() {
		return factorEdad;
	}

	public void setFactorEdad(BigDecimal factorEdad) {
		this.factorEdad = factorEdad;
	}

	public List<OfertaPHDTO> getOfertasPH() {
		return ofertasPH;
	}

	public void setOfertasPH(List<OfertaPHDTO> ofertasPH) {
		this.ofertasPH = ofertasPH;
	}
	
	public BigDecimal getValorM2AreaLibre() {
		return valorM2AreaLibre;
	}

	public void setValorM2AreaLibre(BigDecimal valorM2AreaLibre) {
		this.valorM2AreaLibre = valorM2AreaLibre;
	}
	
	public BigDecimal getValorUnitarioGaraje() {
		return valorUnitarioGaraje;
	}

	public void setValorUnitarioGaraje(BigDecimal valorUnitarioGaraje) {
		this.valorUnitarioGaraje = valorUnitarioGaraje;
	}

	public TipoProyecto getTipoProyecto() {
		return TipoProyecto.fromKey(tipoProyecto == null ? 0 : tipoProyecto);
	}

	public void setTipoProyecto(TipoProyecto tipoProyecto) {
		this.tipoProyecto = tipoProyecto == null ? 0 : tipoProyecto.getKey();
	}
	
	public void setTipoProyecto(Integer tipoProyecto) {
		this.tipoProyecto = tipoProyecto;
	}

	public BigDecimal getValorm2homogenizadoGJ() {
		return valorm2homogenizadoGJ;
	}

	public void setValorm2homogenizadoGJ(BigDecimal valorm2homogenizadoGJ) {
		this.valorm2homogenizadoGJ = valorm2homogenizadoGJ;
	}

	public Object clone() {
		List<OfertaDTO> ofertasClonadas = new  ArrayList<OfertaDTO>();
    	if(this.getOfertas()!=null) {
    		for(OfertaDTO oferta: this.ofertas) {
    			OfertaDTO clone = (OfertaDTO) oferta.clone();
    			ofertasClonadas.add(clone);
    		}
    	}
		ComparacionMercadoPHDTO clone = new ComparacionMercadoPHDTO(
				this.getId(), this.metodoUsado,
				this.conceptoDelMetodo, this.avaluoId, this. desviacion,
				this.promedioM2,
				this.coeficienteVariacion,
				this.numeroDatos,
				this.raiz,
				this.tstudent,
				this.limiteInferior,
				this.limiteSuperior,ofertasClonadas, this.valorM2AreaLibre, this.valorUnitarioGaraje);
		return clone;
	}
	public String toString(){
		String value = "Comparacion de Mercado PH["+this.getId()+"] \n";
		value += "	Avaluo id: "	+ this.avaluoId+"\n";
		if (this.ofertas != null) {
			for( OfertaDTO oferta: this.ofertas){
				value += oferta.toString();
			}
		}
		value += " 		Desviacion: " 			+ this.desviacion+"\n";
		value += " 		Promedio M2: " 			+ this.promedioM2 +"\n";
		value += " 		Coeficiente Variacion: "	+ this.coeficienteVariacion +"\n";
		value += " 		Numero de datos: "		+ this.numeroDatos +"\n";
		value += " 		Raiz: "					+ this.raiz + "\n";
		value += " 		TStudent: "				+ this.tstudent +"\n";
		value += " 		Limite inferior: "		+ this.limiteInferior+"\n";
		value += " 		Limite superior: "		+ this.limiteSuperior +" \n";
		return value;
	}
	
	/*@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComparacionMercadoPHDTO other = (ComparacionMercadoPHDTO) obj;
		
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}*/
	
	public void calcularPromedioValorUnitarioGaraje() {
 
 		
 		if (ofertasPH != null && !ofertasPH.isEmpty()){
			
			if(ofertasPH.stream()
					.filter(x -> x.getPrecioUnitarioGaraje() != null && x.getPrecioUnitarioGaraje().doubleValue() > 0).
					mapToDouble((x) -> x.getPrecioUnitarioGaraje() == null ? 0 : x.getPrecioUnitarioGaraje().doubleValue())
					.average().isPresent()){
				
				this.promedioValorUnitarioGaraje = BigDecimal.valueOf(ofertasPH.stream()
						.filter(x -> x.getPrecioUnitarioGaraje() != null && x.getPrecioUnitarioGaraje().doubleValue() > 0).
						mapToDouble((x) -> x.getPrecioUnitarioGaraje() == null ? 0 : x.getPrecioUnitarioGaraje().doubleValue())
						.average().getAsDouble());
			}else{
				this.promedioValorUnitarioGaraje = BigDecimal.ZERO;
			}
 		
 		}else{
 			this.promedioValorUnitarioGaraje = BigDecimal.ZERO;
 		}
 
 	}

	public void calcularPromedioValorM2HomogenizadoSinGaraje() {

		if(ofertasPH != null  && !ofertasPH.isEmpty()){
		
		this.promedioValorM2HomogenizadoSinGaraje = BigDecimal
				.valueOf(
						ofertasPH.stream()
								.mapToDouble((x) -> x.getValorMetroHomogenizadoSinGaraje() == null ? 0
										: x.getValorMetroHomogenizadoSinGaraje().doubleValue())
								.average().getAsDouble());
		}else{
			this.promedioValorM2HomogenizadoSinGaraje = BigDecimal.ZERO;
		}
		
	}

	public void calcularPromedioValorComercial() {

		if(ofertasPH != null  && !ofertasPH.isEmpty()){
		this.promedioValorComercial = BigDecimal.valueOf(
				ofertasPH.stream().mapToDouble((x) -> x.getValorOferta() == null ? 0 : x.getValorOferta().doubleValue())
						.average().getAsDouble());
		}else{
			this.promedioValorComercial = BigDecimal.ZERO;
					
		}

	}

	public void calcularPromedioValorM2SinGarajeNoHomogenizado() {
		if(ofertasPH != null  && !ofertasPH.isEmpty()){
		this.promedioValorM2SinGarajeNoHomogenizado = BigDecimal
				.valueOf(ofertasPH.stream().mapToDouble((x) -> x.getValorM2SinGarajeNoHomogenizado() == null ? 0
						: x.getValorM2SinGarajeNoHomogenizado().doubleValue()).average().getAsDouble());
		}else{
			this.promedioValorM2SinGarajeNoHomogenizado = BigDecimal.ZERO;
		}

	}

	public void calcularPromedioValorM2HomogenizadoSinGarajeAreaLibre() {

		if(ofertasPH != null  && !ofertasPH.isEmpty()){
		this.promedioValorM2HomogenizadoSinGarajeAreaLibre = BigDecimal
				.valueOf(
						ofertasPH.stream()
								.mapToDouble((x) -> x.getValorM2HomogenizadoSinGarajeYAreaLibre() == null ? 0
										: x.getValorM2HomogenizadoSinGarajeYAreaLibre().doubleValue())
								.average().getAsDouble());
		}else{
			this.promedioValorM2HomogenizadoSinGarajeAreaLibre =  BigDecimal.ZERO;
		}

	}
	
	
	
	public OfertaDTO.TipoArea[] getTiposArea() {
		return OfertaDTO.TipoArea.values();
	}
	
	public enum TipoProyecto implements ListaDesplegable {
		Seleccione("No aplica", 0), VIS_SIN_ASCENSOR("Vis sin ascensor", 1), VIS_CON_ASCENSOR("Vis con ascensor",
				2), ESTRATO_MEDIO_CON_ASCENSOR("Estrato medio con ascensor", 3), ESTRATO_MEDIO_SIN_ASCENSOR("Estrato medio sin ascensor", 4), ESTRATO_ALTO("Estrato alto", 5);

		private final String value;
		private final int key;

		TipoProyecto(String value, int key) {
			this.value = value;
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public int getKey() {
			return key;
		}

		public static TipoProyecto fromKey(int key) {

			TipoProyecto[] values = TipoProyecto.values();

			for (TipoProyecto value : values) {
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
	
	public TipoProyecto[] getTiposProyecto() {
		return TipoProyecto.values();
	}
	
	
	
}
