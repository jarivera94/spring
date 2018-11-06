package com.helio4.bancol.avaluos.dto;

import java.math.BigDecimal;

import com.helio4.bancol.avaluos.controlador.ListaDesplegable;
import com.helio4.bancol.avaluos.servicio.util.Constantes;

public class AreaDTO implements Comparable<AreaDTO>,Cloneable{

	private Long id;
	private String nombre;
	private String descripcion;
	private Integer descripcionNumerica;
	private BigDecimal area = BigDecimal.ZERO;
	private Integer unidadDeMedida;
	private BigDecimal valorUnitario = BigDecimal.ZERO;
	private BigDecimal valorTotal = BigDecimal.ZERO;
	private BigDecimal coeficienteCopropiedad;
	private BigDecimal areaEnMetros;
	private BigDecimal porcentaje;
	private BigDecimal porcentajeValorProporcionalTerreno;
	private BigDecimal porcentajeValorProporcionalConstruccion;
	private BigDecimal valorReposicion = BigDecimal.ZERO;
	private BigDecimal costoTotalReposicion;
	private BigDecimal valorComercial;
	private BigDecimal valorResidualConstruccion;
	private Integer tipoArea;
	private Long avaluoId;
	
	
	/**variables usadas en el calculo de fito y corvini*/
	private BigDecimal depreciacion 	= BigDecimal.ZERO;
	private BigDecimal costoReposicion	= BigDecimal.ZERO;
	private BigDecimal calificacion 	= BigDecimal.ZERO;
	private BigDecimal valorDepreciacion= BigDecimal.ZERO;
	private BigDecimal valorFinal		= BigDecimal.ZERO;
	private BigDecimal valorAdoptado	= BigDecimal.ZERO;
	private BigDecimal valorConstruccion= BigDecimal.ZERO;
	private BigDecimal edad				= BigDecimal.ZERO;
	private BigDecimal vidaUtil			= BigDecimal.ZERO;
	//variable en la que se aloja area*vidaReposicion
	private BigDecimal valorReposicionFinal = BigDecimal.ZERO;

	
	public AreaDTO() {}
	
	public AreaDTO( Long id,
			 String nombre,
			 String descripcion,
			 Integer descripcionNumerica,
			 BigDecimal area,
			 Integer unidadDeMedida,
			 BigDecimal valorUnitario,
			 BigDecimal valorTotal,
			 BigDecimal coeficienteCopropiedad,
			 BigDecimal areaEnMetros,
			 BigDecimal porcentaje,
			 BigDecimal porcentajeValorProporcionalTerreno,
			 BigDecimal porcentajeValorProporcionalConstruccion,
			 BigDecimal valorReposicion ,
			 BigDecimal costoTotalReposicion,
			 BigDecimal valorComercial,
			 BigDecimal valorResidualConstruccion,
			 Integer tipoArea,
			 Long avaluoId,
			 BigDecimal depreciacion 	,
			 BigDecimal costoReposicion	,
			 BigDecimal calificacion 	,
			 BigDecimal valorDepreciacion,
			 BigDecimal valorFinal		,
			 BigDecimal valorAdoptado	,
			 BigDecimal valorConstruccion,
			 BigDecimal edad				,
			 BigDecimal vidaUtil			,
			 BigDecimal valorReposicionFinal){
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.descripcionNumerica = descripcionNumerica;
		this.area = area;
		this.unidadDeMedida = unidadDeMedida;
		this.valorUnitario = valorUnitario;
		this.valorTotal = valorTotal;
		this.coeficienteCopropiedad = coeficienteCopropiedad;
		this.areaEnMetros = areaEnMetros;
		this.porcentaje = porcentaje;
		this.porcentajeValorProporcionalTerreno = porcentajeValorProporcionalTerreno;
		this.porcentajeValorProporcionalConstruccion = porcentajeValorProporcionalConstruccion;
		this.valorReposicion = valorReposicion;
		this.costoTotalReposicion = costoTotalReposicion;
		this.valorComercial = valorComercial;
		this.valorResidualConstruccion =valorResidualConstruccion;
		this.tipoArea = tipoArea;
		this.avaluoId = avaluoId;
		this.depreciacion  =depreciacion;
		this.costoReposicion =costoReposicion ;
		this.calificacion 	=calificacion;
		this.valorDepreciacion = valorDepreciacion;
		this.valorFinal	 = valorFinal;
		this.valorAdoptado	=valorAdoptado;
		this.valorConstruccion = valorConstruccion;
		this.edad = edad;
		this.vidaUtil = vidaUtil;
		this.valorReposicionFinal =valorReposicionFinal;
	}
	
	public Object clone() throws CloneNotSupportedException {
		AreaDTO clone = new AreaDTO(this.id,
				this.nombre ,
				this.descripcion,
				this.descripcionNumerica,
				this.area,
				this.unidadDeMedida,
				this.valorUnitario ,
				this.valorTotal,
				this.coeficienteCopropiedad ,
				this.areaEnMetros,
				this.porcentaje ,
				this.porcentajeValorProporcionalTerreno ,
				this.porcentajeValorProporcionalConstruccion ,
				this.valorReposicion,
				this.costoTotalReposicion ,
				this.valorComercial,
				this.valorResidualConstruccion ,
				this.tipoArea ,
				this.avaluoId,
				this.depreciacion ,
				this.costoReposicion  ,
				this.calificacion ,
				this.valorDepreciacion ,
				this.valorFinal,
				this.valorAdoptado,
				this.valorConstruccion ,
				this.edad,
				this.vidaUtil,
				this.valorReposicionFinal);
		
		return clone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public ListaDesplegable getDescripcionNumerica() {
		if (descripcionNumerica != null) {
			if (DescripcionAreaPH.fromKey(descripcionNumerica) != null) {
				return DescripcionAreaPH.fromKey(descripcionNumerica);
			}else{
				return DescripcionAreaNoPH.fromKey(descripcionNumerica);
			}
		}
		return null;
	}

	public void setDescripcionNumerica(ListaDesplegable descripcionNumerica) {
			this.descripcionNumerica = descripcionNumerica.getKey();
	}

	public BigDecimal getArea() {
		return area;
	}

	public void setArea(BigDecimal area) {
		this.area = area == null ?  BigDecimal.ZERO : area;
	}

	public UnidadDeMedida getUnidadDeMedida() {
		return UnidadDeMedida.fromKey(unidadDeMedida == null ? 0 : unidadDeMedida);
	}

	public void setUnidadDeMedida(UnidadDeMedida unidadDeMedida) {
		this.unidadDeMedida = unidadDeMedida.getKey();
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal == null ? BigDecimal.ZERO : valorTotal;
	}

	public BigDecimal getCoeficienteCopropiedad() {
		return coeficienteCopropiedad;
	}

	public void setCoeficienteCopropiedad(BigDecimal coeficienteCopropiedad) {
		this.coeficienteCopropiedad = coeficienteCopropiedad;
	}

	public BigDecimal getAreaEnMetros() {
		return areaEnMetros;
	}

	public void setAreaEnMetros(BigDecimal areaEnMetros) {
		this.areaEnMetros = areaEnMetros;
	}

	public BigDecimal getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(BigDecimal porcentaje) {
		this.porcentaje = porcentaje;
	}

	public BigDecimal getPorcentajeValorProporcionalTerreno() {
		return porcentajeValorProporcionalTerreno;
	}

	public void setPorcentajeValorProporcionalTerreno(
			BigDecimal porcentajeValorProporcionalTerreno) {
		this.porcentajeValorProporcionalTerreno = porcentajeValorProporcionalTerreno;
	}

	public BigDecimal getPorcentajeValorProporcionalConstruccion() {
		return porcentajeValorProporcionalConstruccion;
	}

	public void setPorcentajeValorProporcionalConstruccion(
			BigDecimal porcentajeValorProporcionalConstruccion) {
		this.porcentajeValorProporcionalConstruccion = porcentajeValorProporcionalConstruccion;
	}

	public BigDecimal getValorReposicion() {
		return valorReposicion;
	}

	public void setValorReposicion(BigDecimal valorReposicion) {
		this.valorReposicion = valorReposicion;
	}

	public BigDecimal getCostoTotalReposicion() {
		return costoTotalReposicion;
	}

	public void setCostoTotalReposicion(BigDecimal costoTotalReposicion) {
		this.costoTotalReposicion = costoTotalReposicion;
	}

	public BigDecimal getValorComercial() {
		return valorComercial;
	}

	public void setValorComercial(BigDecimal valorComercial) {
		this.valorComercial = valorComercial;
	}

	public BigDecimal getValorResidualConstruccion() {
		return valorResidualConstruccion;
	}

	public void setValorResidualConstruccion(BigDecimal valorResidualConstruccion) {
		this.valorResidualConstruccion = valorResidualConstruccion;
	}

	public TipoArea getTipoArea() {
		return TipoArea.fromKey(tipoArea == null ? 0 : tipoArea);
	}

	public void setTipoArea(TipoArea tipoArea) {
		this.tipoArea = tipoArea.getKey();
	}

	public Long getAvaluoId() {
		return avaluoId;
	}

	public void setAvaluoId(Long avaluoId) {
		this.avaluoId = avaluoId;
	}
	
	public enum UnidadDeMedida  implements ListaDesplegable {
		Seleccione(Constantes.SELECCCIONE,0),
		M2("M2", 1),
		Hectareas("Hectareas", 2),
		Fanegadas("Fanegadas", 3),
		Global("Global", 4);
	
		private final String value;
		private final int key;
		
		UnidadDeMedida(String value, int key) {
			this.value = value;
			this.key = key;
		}
		
		public String getValue() {
			return value;
		}
		
		public int getKey() {
			return key;
		}
		
		public static UnidadDeMedida fromKey(int key) {
			switch (key) {
			case 0: return UnidadDeMedida.Seleccione;
			case 1: return M2;
			case 2: return Hectareas;
			case 3: return Fanegadas;
			case 4: return Global;
			default:
				return null;
			}
		}
	
		@Override
		public String toString() {
			switch (this) {
			case Seleccione: return Constantes.SELECCCIONE;
			case M2: return "M2";
			case Hectareas: return "Hectareas";
			case Fanegadas: return "Fanegadas";
			case Global: return "Global";
			default:
				return "";
			}
		}		
	}
	
	public enum TipoArea {
		Terreno("Terreno", 1),
		Construccion("Construcción", 2),
		Infraestructura("Infraestructura", 3),
		CultivoAvaluable("Cultivo avaluable", 4);
	
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
			switch (key) {
			case 1: return Terreno;
			case 2: return Construccion;
			case 3: return Infraestructura;
			case 4: return CultivoAvaluable;
			default:
				return null;
			}
		}
	
		@Override
		public String toString() {
			switch (this) {
			case Terreno: return "Terreno";
			case Construccion: return "Construccion";
			case Infraestructura: return "Infraestructura";
			case CultivoAvaluable: return "Cultivo avaluable";
			default:
				return "";
			}
		}
	}
	
	public enum DescripcionAreaPH implements ListaDesplegable {
		AreaPrivada("Area privada", 1),
		AreaLibre("Area libre", 2),
		Garaje("Garaje", 3),
		Deposito("Deposito", 4),
		Otro("Otro", 7);
	
		private final String value;
		private final int key;
		
		DescripcionAreaPH(String value, int key) {
			this.value = value;
			this.key = key;
		}
		
		public String getValue() {
			return value;
		}
		
		public int getKey() {
			return key;
		}
		
		public static DescripcionAreaPH fromKey(int key) {
			switch (key) {
			case 1: return AreaPrivada;
			case 2: return AreaLibre;
			case 3: return Garaje;
			case 4: return Deposito;
			case 7: return Otro;
			default:
				return null;
			}
		}
	
		@Override
		public String toString() {
			return String.format("%s[id=%d]", getClass().getSimpleName(), getKey());
		}		
	}

	public enum DescripcionAreaNoPH implements ListaDesplegable {
		AreaTerreno("Area de terreno", 21),
		AreaConstruccion("Area de construcción", 22),
		Otros("Otros",5);
	
		private final String value;
		private final int key;
		
		DescripcionAreaNoPH(String value, int key) {
			this.value = value;
			this.key = key;
		}
		
		public String getValue() {
			return value;
		}
		
		public int getKey() {
			return key;
		}
		
		public static DescripcionAreaNoPH fromKey(int key) {
			switch (key) {
			case 21: return AreaTerreno;
			case 22: return AreaConstruccion;
			case 5: return Otros;
			default:
				return null;
			}
		}
	
		@Override
		public String toString() {
			return String.format("%s[id=%d]", getClass().getSimpleName(), getKey());
		}		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime
				* result
				+ ((descripcionNumerica == null) ? 0 : descripcionNumerica
						.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result
				+ ((tipoArea == null) ? 0 : tipoArea.hashCode());
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
		AreaDTO other = (AreaDTO) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (descripcionNumerica == null) {
			if (other.descripcionNumerica != null)
				return false;
		} else if (!descripcionNumerica.equals(other.descripcionNumerica))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (tipoArea == null) {
			if (other.tipoArea != null)
				return false;
		} else if (!tipoArea.equals(other.tipoArea))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AreaDTO [id=" + id + ", nombre=" + nombre + ", descripcion="
				+ descripcion + ", descripcionNumerica=" + descripcionNumerica
				+ ", area=" + area + ", unidadDeMedida=" + unidadDeMedida
				+ ", valorUnitario=" + valorUnitario + ", valorTotal="
				+ valorTotal + ", coeficienteCopropiedad="
				+ coeficienteCopropiedad + ", areaEnMetros=" + areaEnMetros
				+ ", porcentaje=" + porcentaje
				+ ", porcentajeValorProporcionalTerreno="
				+ porcentajeValorProporcionalTerreno
				+ ", porcentajeValorProporcionalConstruccion="
				+ porcentajeValorProporcionalConstruccion
				+ ", valorReposicion=" + valorReposicion
				+ ", costoTotalReposicion=" + costoTotalReposicion
				+ ", valorComercial=" + valorComercial
				+ ", valorResidualConstruccion=" + valorResidualConstruccion
				+ ", tipoArea=" + tipoArea + ", avaluoId=" + avaluoId + "]";
	}

	public BigDecimal getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(BigDecimal calificacion) {
		this.calificacion = calificacion;
	}

	public BigDecimal getValorConstruccion() {
		return valorConstruccion;
	}

	public void setValorConstruccion(BigDecimal valorConstruccion) {
		this.valorConstruccion = valorConstruccion;
	}

	public BigDecimal getValorAdoptado() {
		return valorAdoptado;
	}

	public void setValorAdoptado(BigDecimal valorAdoptado) {
		this.valorAdoptado = valorAdoptado;
	}

	public BigDecimal getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(BigDecimal valorFinal) {
		this.valorFinal = valorFinal;
	}

	public BigDecimal getValorDepreciacion() {
		return valorDepreciacion;
	}

	public void setValorDepreciacion(BigDecimal valorDepreciacion) {
		this.valorDepreciacion = valorDepreciacion;
	}

	public BigDecimal getCostoReposicion() {
		return costoReposicion;
	}

	public void setCostoReposicion(BigDecimal costoReposicion) {
		this.costoReposicion = costoReposicion;
	}

	public BigDecimal getDepreciacion() {
		return depreciacion;
	}

	public void setDepreciacion(BigDecimal depreciacion) {
		this.depreciacion = depreciacion;
	}

	public BigDecimal getEdad() {
		return edad;
	}

	public void setEdad(BigDecimal edad) {
		this.edad = edad;
	}

	public BigDecimal getVidaUtil() {
		return vidaUtil;
	}

	public void setVidaUtil(BigDecimal vidaUtil) {
		this.vidaUtil = vidaUtil;
	}

	public BigDecimal getValorReposicionFinal() {
		return valorReposicionFinal;
	}

	public void setValorReposicionFinal(BigDecimal valorReposicionFinal) {
		this.valorReposicionFinal = valorReposicionFinal;
	}

	public int compareTo(AreaDTO arg) {
		String a = this.descripcionNumerica.toString()+"-"+this.nombre;
		String b = arg.descripcionNumerica.toString()+"-"+arg.nombre;
		return a.compareTo(b);
	}
}
