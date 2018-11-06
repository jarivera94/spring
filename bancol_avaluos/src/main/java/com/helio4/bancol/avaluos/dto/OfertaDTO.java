package com.helio4.bancol.avaluos.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;

import com.helio4.bancol.avaluos.controlador.ListaDesplegable;
import com.helio4.bancol.avaluos.modelo.Oferta;
import com.helio4.bancol.avaluos.servicio.util.Constantes;

public class OfertaDTO implements Serializable, Cloneable {

	private static final long serialVersionUID = 6725349136978862513L;
	protected Long id;
	protected String barrio;
	protected String conjunto;
	protected String ciudad;

	protected String telefono;
	protected String celular;
	protected String observaciones;

	protected BigDecimal valorDepurado;
	protected BigDecimal garajesDepositos = BigDecimal.ZERO;
	protected BigDecimal valorOferta = BigDecimal.ZERO;
	protected BigDecimal porcentajeDepurado = BigDecimal.ZERO;
	protected BigDecimal valorM2Construccion;
	protected BigDecimal construccion;
	protected BigDecimal estadoConservacion = BigDecimal.ZERO;
	protected BigDecimal valorReposicion = BigDecimal.ZERO;
	protected BigDecimal areaTerreno = BigDecimal.ZERO;
	protected BigDecimal totalTerreno = BigDecimal.ZERO;
	protected BigDecimal valorM2Terreno = BigDecimal.ZERO;
	protected BigDecimal valorFinalDepurado = BigDecimal.ZERO;
	protected Integer edadInmueble = 0;
	protected Integer vidaUtil = 0;
	protected Integer areaConstruida = 0;

	// Nuevas metodologías
	protected String localizacion;
	protected String nombre;
	protected String fuente;
	protected Integer edad;
	protected Integer ubicacion;
	protected Integer acabados;
	protected BigDecimal negociacion;
	protected BigDecimal edadHomogenizada;
	protected BigDecimal factorHomogenizacion;

	protected Long metodoValuacionId;
	// empleado para el calculo de fito y corvini cuando es comparacion de
	// mercado no ph.
	protected AreaDTO areaTemporal;

	protected BigDecimal valorIntegralConstruccion;

	// variables auxiliares calculos
	private BigDecimal cuadroCalificacion;

	public enum TipoArea implements ListaDesplegable {
		Seleccione(Constantes.SELECCCIONE, 0), Privada("Privada", 1), Construida("Construida", 2);

		private final String value;
		private final int key;

		TipoArea(String value, int key) {
			this.value = value;
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public int getKey() {
			return key;
		}

		public static TipoArea fromKey(int key) {

			TipoArea[] values = TipoArea.values();

			for (TipoArea value : values) {
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

	public enum FactorHomologacion implements ListaDesplegable {
		 S("S", 5),NM("NM", 1), MM("MM", 2), M("M", 3), SM("SM", 4), SP("SP", 6), P("P", 7), MP("MP", 8), NP("NP", 9);

		private final String value;
		private final int key;

		FactorHomologacion(String value, int key) {
			this.value = value;
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public int getKey() {
			return key;
		}
		
		public long getLongKey() {
			return key;
		}

		public static FactorHomologacion fromKey(int key) {

			FactorHomologacion[] values = FactorHomologacion.values();

			for (FactorHomologacion value : values) {
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

	public OfertaDTO() {
		this.areaTemporal = new AreaDTO();
	}

	public OfertaDTO(Long id, String barrio, String conjunto, String ciudad,

			String telefono, String celular, String observaciones, BigDecimal valorDepurado,
			BigDecimal garajesDepositos, BigDecimal valorOferta, BigDecimal porcentajeDepurado,
			BigDecimal valorM2Construccion, BigDecimal construccion, BigDecimal estadoConservacion,
			BigDecimal valorReposicion, BigDecimal areaTerreno, BigDecimal totalTerreno, BigDecimal valorM2Terreno,
			BigDecimal valorFinalDepurado, Integer edadInmueble, Integer vidaUtil, Integer areaConstruida) {
		this.id = id;
		this.barrio = barrio;
		this.conjunto = conjunto;
		this.ciudad = ciudad;
		this.telefono = telefono;
		this.celular = celular;
		this.observaciones = observaciones;
		this.valorDepurado = valorDepurado;
		this.garajesDepositos = garajesDepositos;
		this.valorOferta = valorOferta;
		this.porcentajeDepurado = porcentajeDepurado;
		this.valorM2Construccion = valorM2Construccion;
		this.construccion = construccion;
		this.estadoConservacion = estadoConservacion;
		this.valorReposicion = valorReposicion;
		this.areaTerreno = areaTerreno;
		this.totalTerreno = totalTerreno;
		this.valorM2Terreno = valorM2Terreno;
		this.valorFinalDepurado = valorFinalDepurado;
		this.edadInmueble = edadInmueble;
		this.vidaUtil = vidaUtil;
		this.areaConstruida = areaConstruida;
	}

	public OfertaDTO(Oferta oferta) {
		this.areaTemporal = new AreaDTO();

		this.id = oferta.getId();
		this.barrio = oferta.getBarrio();
		this.conjunto = oferta.getConjunto();
		this.ciudad = oferta.getCiudad();
		this.telefono = oferta.getTelefono();
		this.celular = oferta.getCelular();
		this.observaciones = oferta.getObservaciones();
		this.valorDepurado = oferta.getValorDepurado();
		this.garajesDepositos = oferta.getGarajesDepositos();
		this.valorOferta = oferta.getValorOferta();
		this.porcentajeDepurado = oferta.getPorcentajeDepurado();
		this.valorM2Construccion = oferta.getValorM2Construccion();
		this.construccion = oferta.getConstruccion();
		this.estadoConservacion = oferta.getEstadoConservacion();
		this.valorReposicion = oferta.getValorReposicion();
		this.areaTerreno = oferta.getAreaTerreno();
		this.totalTerreno = oferta.getTotalTerreno();
		this.valorM2Terreno = oferta.getValorM2Terreno();
		this.valorFinalDepurado = oferta.getValorFinalDepurado();
		this.edadInmueble = oferta.getEdadInmueble();
		this.vidaUtil = oferta.getVidaUtil();
		this.areaConstruida = oferta.getAreaConstruida();
		this.localizacion = oferta.getLocalizacion();
		this.nombre = oferta.getNombre();
		this.fuente = oferta.getFuente();
		this.edad = oferta.getEdad();
		this.ubicacion = oferta.getUbicacion();
		this.acabados = oferta.getAcabados();
		this.negociacion = oferta.getNegociacion();
		this.edadHomogenizada = oferta.getEdadHomogenizada();
		this.factorHomogenizacion = oferta.getFactorHomogenizacion();

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBarrio() {
		return barrio;
	}

	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}

	public String getConjunto() {
		return conjunto;
	}

	public void setConjunto(String conjunto) {
		this.conjunto = conjunto;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public BigDecimal getValorDepurado() {
		return valorDepurado;
	}

	public void setValorDepurado(BigDecimal valorDepurado) {
		this.valorDepurado = valorDepurado;
	}

	public BigDecimal getGarajesDepositos() {
		return garajesDepositos;
	}

	public void setGarajesDepositos(BigDecimal garajesDepositos) {
		this.garajesDepositos = garajesDepositos;
	}

	public BigDecimal getValorOferta() {
		return valorOferta;
	}

	public void setValorOferta(BigDecimal valorOferta) {
		this.valorOferta = valorOferta;
	}

	public BigDecimal getPorcentajeDepurado() {
		return porcentajeDepurado;
	}

	public void setPorcentajeDepurado(BigDecimal porcentajeDepurado) {
		this.porcentajeDepurado = porcentajeDepurado;
	}

	public Integer getAreaConstruida() {
		return areaConstruida;
	}

	public void setAreaConstruida(Integer areaConstruida) {
		this.areaConstruida = areaConstruida;
	}

	public BigDecimal getValorM2Construccion() {
		return valorM2Construccion;
	}

	public void setValorM2Construccion(BigDecimal valorM2Construccion) {
		this.valorM2Construccion = valorM2Construccion;
	}

	public BigDecimal getConstruccion() {
		return construccion;
	}

	public void setConstruccion(BigDecimal construccion) {
		this.construccion = construccion;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public BigDecimal getEstadoConservacion() {
		return estadoConservacion;
	}

	public void setEstadoConservacion(BigDecimal estadoConservacion) {
		this.estadoConservacion = estadoConservacion;
	}

	public Integer getEdadInmueble() {
		return edadInmueble;
	}

	public void setEdadInmueble(Integer edadInmueble) {
		this.edadInmueble = edadInmueble;
	}

	public Integer getVidaUtil() {
		return vidaUtil;
	}

	public void setVidaUtil(Integer vidaUtil) {
		this.vidaUtil = vidaUtil;
	}

	public BigDecimal getValorReposicion() {
		return valorReposicion;
	}

	public void setValorReposicion(BigDecimal valorReposicion) {
		this.valorReposicion = valorReposicion;
	}

	public BigDecimal getAreaTerreno() {
		return areaTerreno;
	}

	public void setAreaTerreno(BigDecimal areaTerreno) {
		this.areaTerreno = areaTerreno;
	}

	public BigDecimal getTotalTerreno() {
		return totalTerreno;
	}

	public void setTotalTerreno(BigDecimal totalTerreno) {
		this.totalTerreno = totalTerreno;
	}

	public BigDecimal getValorM2Terreno() {
		return valorM2Terreno;
	}

	public void setValorM2Terreno(BigDecimal valorM2Terreno) {
		this.valorM2Terreno = valorM2Terreno;
	}

	public BigDecimal getValorFinalDepurado() {
		return valorFinalDepurado;
	}

	public void setValorFinalDepurado(BigDecimal valorFinalDepurado) {
		this.valorFinalDepurado = valorFinalDepurado;
	}

	public Long getMetodoValuacionId() {
		return metodoValuacionId;
	}

	public void setMetodoValuacionId(Long metodoValuacionId) {
		this.metodoValuacionId = metodoValuacionId;
	}

	public AreaDTO getAreaTemporal() {
		return areaTemporal;
	}

	public void setAreaTemporal(AreaDTO areaTemporal) {
		this.areaTemporal = areaTemporal;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public BigDecimal getValorIntegralConstruccion() {
		return valorIntegralConstruccion;
	}

	public void setValorIntegralConstruccion(BigDecimal valorIntegralConstruccion) {
		this.valorIntegralConstruccion = valorIntegralConstruccion;
	}

	public String getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public FactorHomologacion getUbicacion() {
		return FactorHomologacion.fromKey(ubicacion == null ? 0 : ubicacion);
	}

	public void setUbicacion(FactorHomologacion ubicacion) {
		this.ubicacion = ubicacion == null ? 0 : ubicacion.getKey();
	}

	public FactorHomologacion getAcabados() {
		return FactorHomologacion.fromKey(acabados == null ? 0 : acabados);
	}

	public void setAcabados(FactorHomologacion acabados) {
		this.acabados = acabados == null ? 0 : acabados.getKey();
	}

	public BigDecimal getNegociacion() {
		return negociacion;
	}

	public void setNegociacion(BigDecimal negociacion) {
		this.negociacion = negociacion;
	}

	public BigDecimal getEdadHomogenizada() {
		return edadHomogenizada;
	}

	public void setEdadHomogenizada(BigDecimal edadHomogenizada) {
		this.edadHomogenizada = edadHomogenizada;
	}

	public BigDecimal getFactorHomogenizacion() {
		return factorHomogenizacion;
	}

	public void setFactorHomogenizacion(BigDecimal factorHomogenizacion) {
		this.factorHomogenizacion = factorHomogenizacion;
	}

	public void setUbicacion(Integer ubicacion) {
		this.ubicacion = ubicacion;
	}

	public void setAcabados(Integer acabados) {
		this.acabados = acabados;
	}
	
	public BigDecimal getCuadroCalificacion() {
		return cuadroCalificacion;
	}

	public void setCuadroCalificacion(BigDecimal cuadroCalificacion) {
		this.cuadroCalificacion = cuadroCalificacion;
	}
	
	public String getFuente() {
		return fuente;
	}

	public void setFuente(String fuente) {
		this.fuente = fuente;
	}

	@Override
	public Object clone() {
		OfertaDTO clone = new OfertaDTO(this.id, this.barrio, this.conjunto, this.ciudad, this.telefono, this.celular,
				this.observaciones, this.valorDepurado, this.garajesDepositos, this.valorOferta,
				this.porcentajeDepurado, this.valorM2Construccion, this.construccion, this.estadoConservacion,
				this.valorReposicion, this.areaTerreno, this.totalTerreno, this.valorM2Terreno, this.valorFinalDepurado,
				this.edadInmueble, this.vidaUtil, this.areaConstruida);
		return clone;
	}

	public String toString() {
		String value = "Oferta [" + this.id + "] \n";
		value += " 		Valor Depurado: " + this.valorDepurado + "\n";
		value += " 		Garajes y depositos: " + this.garajesDepositos + "\n";
		value += " 		Valor de la ferta: " + this.valorOferta + "\n";
		value += " 		Porcentaje depurado: " + this.porcentajeDepurado + "\n";
		value += " 		Valor M2 Construccion: " + this.valorM2Construccion + "\n";
		value += " 		Construccion: " + this.construccion + "\n";
		value += " 		Area construida: " + this.areaConstruida + "\n";
		value += " 		Estado de la conservacion: " + this.estadoConservacion + "\n";
		value += " 		Valor de la reposicion: " + this.valorReposicion + "\n";
		value += " 		Area de terreno: " + this.areaTerreno + "\n";
		value += " 		Total de terreno: " + this.totalTerreno + "\n";
		value += " 		Valor final depurado: " + this.valorFinalDepurado + "\n";
		value += " 		Edad del inmueble: " + this.edadInmueble + "\n";
		value += " 		Vida util: " + this.vidaUtil + "\n";
		return value;
	}

	public void calcularFactorHomologacionEdad(Integer edadSujeto, BigDecimal factorHomogenizacionEdad) {

		if (this.edad != null && edadSujeto != null && factorHomogenizacionEdad != null) {
			this.edadHomogenizada = factorHomogenizacionEdad.multiply(new BigDecimal(this.edad - edadSujeto))
					.add(BigDecimal.ONE);
		} else {
			this.edadHomogenizada = BigDecimal.ZERO;
		}

	}

	public void calcularCuadroCalificacion(BigDecimal... factores) {
		this.cuadroCalificacion = BigDecimal.ONE;

		for (BigDecimal factor : factores) {
			if (factor != null) {
				
				this.cuadroCalificacion = this.cuadroCalificacion.multiply(factor.setScale(8, BigDecimal.ROUND_HALF_UP));
			}
		}

	}

	public void calcularFactorHomogenizacion(BigDecimal... factores){
		
		this.factorHomogenizacion = BigDecimal.ONE;
		
		for(BigDecimal factor: factores){
			if(factor!=  null){
				this.factorHomogenizacion =this.factorHomogenizacion.multiply(factor);
			}else{
				this.factorHomogenizacion = BigDecimal.ZERO;
				break;
			}
		}
		this.factorHomogenizacion =this.factorHomogenizacion.setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}
}
