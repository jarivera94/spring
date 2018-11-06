package com.helio4.bancol.avaluos.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.helio4.bancol.avaluos.controlador.ListaDesplegable;
import com.helio4.bancol.avaluos.dto.OfertaLoteDTO.TipoComparacion;
import com.helio4.bancol.avaluos.dto.OfertaLoteSinConstruccionDTO.TipoForma;
import com.helio4.bancol.avaluos.dto.OfertaLoteSinConstruccionDTO.TipoUbicacion;
import com.helio4.bancol.avaluos.modelo.MetodoValuacion;
import com.helio4.bancol.avaluos.servicio.util.Constantes;

public class MetodoValuacionDTO implements Serializable, Cloneable {

	private static final long serialVersionUID = -3756802096870742646L;
	protected Long id;
	protected Integer metodoUsado;
	protected String conceptoDelMetodo;
	protected Long avaluoId;

	protected List<OfertaDTO> ofertas;

	private List<OfertaDTO> ofertasOriginales;
	
	public MetodoValuacionDTO() {
	}

	public MetodoValuacionDTO(MetodoValuacion metodo) {

		this.id = metodo.getId();
		this.metodoUsado = metodo.getMetodoUsado();
		this.conceptoDelMetodo = metodo.getConceptoDelMetodo();
		this.avaluoId = metodo.getAvaluo().getId();

	}

	public MetodoValuacionDTO(Integer metodoUsado, Long avaluoId) {
		this.metodoUsado = metodoUsado;
		this.avaluoId = avaluoId;
	}

	public MetodoValuacionDTO(Long id, Integer metodoUsado, String conceptoDelMetodo, Long avaluoId) {
		super();
		this.id = id;
		this.metodoUsado = metodoUsado;
		this.conceptoDelMetodo = conceptoDelMetodo;
		this.avaluoId = avaluoId;
		this.ofertas = new ArrayList<OfertaDTO>();
	}

	public MetodoValuacionDTO(Long id, Integer metodoUsado, String conceptoDelMetodo, Long avaluoId,
			List<OfertaDTO> ofertas) {
		super();
		this.id = id;
		this.metodoUsado = metodoUsado;
		this.conceptoDelMetodo = conceptoDelMetodo;
		this.avaluoId = avaluoId;
		this.ofertas = ofertas;
	}

	public enum MetodoValuacionEnum {
		ComparaciondeMercado("Comparación de  Mercado", 21), Capitalizaciondeingresos("Capitalización de ingresos",
				22), MetodoReposicion("Método Reposición", 23), MetodoResidual("Metodo Residual",
						24), ComparaciondeMercadoPHVenta("Comparación de Mercado PH en Venta",
								25), ComparaciondeMercadoPHRenta("Comparación de Mercado PH en Renta",
										26), ComparaciondeMercadoLoteVentaSinContruccion(
												"Comparación de Mercado Lote en Venta",
												27), ComparaciondeMercadoLoteVentaConstruccion(
														"Comparación de Mercado Lote en Venta (Terreno + Construcción)",
														28), ComparaciondeMercadoLoteRentaContruccion(
																"Comparación de Mercado Lote en Renta (Terreno + Construcción)",
																29),

		/*
		 * , ComparacionCapitalizacion("Comparación - Capitalización", 25),
		 * ComparacionReposicion("Comparación  - Reposición", 26),
		 * ComparacionResidual("Comparación - Residual", 41),
		 * CapitalizacionReposicion("Capitalización - Reposición", 42),
		 * CapitalizacionResidual("Capitalización - Residual", 43),
		 * ReposicionResidual("Reposición - Residual", 44),
		 * CompCapitRepos("Comp.-Capit-Repos", 45),
		 * CompReposResidual("Comp.-Repos.-Residual", 61),
		 * CompCapitResidual("Comp.-Capit. - Residual", 62),
		 * CompCapitReposResidual("Comp.-Capit.-Repos.-Residual", 63)
		 */;

		private final String value;
		private final int key;

		MetodoValuacionEnum(String value, int key) {
			this.value = value;
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public int getKey() {
			return key;
		}

		public static MetodoValuacionEnum fromKey(int key) {

			MetodoValuacionEnum[] values = MetodoValuacionEnum.values();

			for (MetodoValuacionEnum value : values) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((avaluoId == null) ? 0 : avaluoId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		MetodoValuacionDTO other = (MetodoValuacionDTO) obj;
		if (avaluoId == null) {
			if (other.avaluoId != null)
				return false;
		} else if (!avaluoId.equals(other.avaluoId))
			return false;
		if (metodoUsado == null) {
			if (other.metodoUsado != null)
				return false;
		} else if (!metodoUsado.equals(other.metodoUsado))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public Object clone() {
		List<OfertaDTO> ofertasClonadas = new ArrayList<OfertaDTO>();
		if (this.getOfertas() != null) {
			for (OfertaDTO oferta : this.ofertas) {
				OfertaDTO clone = (OfertaDTO) oferta.clone();
				ofertasClonadas.add(clone);
			}
		}
		MetodoValuacionDTO clone = new MetodoValuacionDTO(this.id, this.metodoUsado, this.conceptoDelMetodo,
				this.avaluoId, ofertasClonadas);
		return clone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MetodoValuacionEnum getMetodoUsado() {
		if (metodoUsado != null) {
			return MetodoValuacionEnum.fromKey(metodoUsado);
		}
		return null;
	}

	public void setMetodoUsado(MetodoValuacionEnum metodoUsado) {
		this.metodoUsado = metodoUsado==null ? null:metodoUsado.getKey();
	}

	public String getConceptoDelMetodo() {
		return conceptoDelMetodo;
	}

	public void setConceptoDelMetodo(String conceptoDelMetodo) {
		this.conceptoDelMetodo = conceptoDelMetodo;
	}

	public Long getAvaluoId() {
		return avaluoId;
	}

	public void setAvaluoId(Long avaluoId) {
		this.avaluoId = avaluoId;
	}

	public List<OfertaDTO> getOfertas() {
		return ofertas;
	}

	public void setOfertas(List<OfertaDTO> ofertas) {
		this.ofertas = ofertas;
	}

	public List<OfertaDTO> getOfertasOriginales() {
		return ofertasOriginales;
	}

	public void setOfertasOriginales(List<OfertaDTO> ofertasOriginales) {
		this.ofertasOriginales = ofertasOriginales;
	}

	public void setMetodoUsado(Integer metodoUsado) {
		this.metodoUsado = metodoUsado;
	}
	
	public OfertaDTO.FactorHomologacion[] getFactoresHomologacion() {
		return OfertaDTO.FactorHomologacion.values();
	}
	
	public String toString() {
		String value = "Metodo Valuacion[" + this.id + "] \n";
		value += "	Avaluo id: " + this.avaluoId + "\n";

		if (this.ofertas != null) {
			for (OfertaDTO oferta : this.ofertas) {
				value += oferta.toString();
			}
		}
		return value;
	}
	
	public TipoUbicacion[] getTiposUbicacion() {
		return TipoUbicacion.values();
	} 
	
	public TipoComparacion[] getTiposComparacion() {
		return TipoComparacion.values();
	}
	
}
