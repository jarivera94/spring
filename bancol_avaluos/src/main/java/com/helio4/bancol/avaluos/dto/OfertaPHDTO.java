package com.helio4.bancol.avaluos.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.EnumSet;
import java.util.List;

import com.helio4.bancol.avaluos.dto.ComparacionMercadoPHDTO.TipoProyecto;
import com.helio4.bancol.avaluos.modelo.OfertaPH;

public class OfertaPHDTO extends OfertaDTO implements Serializable, Cloneable {

	private static final long serialVersionUID = 6725349136978862513L;

	protected Long idOfertaPH;

	protected Integer piso;
	protected BigDecimal area;
	protected Integer tipoArea;
	protected Integer garajes;
	protected Integer edificioConjunto;
	protected BigDecimal superficie;
	protected BigDecimal precioUnitarioGaraje;
	protected BigDecimal valorAdministracion;
	protected BigDecimal areaLibre;

	protected BigDecimal ubicacionPiso;
	protected BigDecimal valorMetroHomogenizadoSinGaraje;
	protected BigDecimal precioUnitarioAdministracionM2;
	protected BigDecimal valorM2SinGarajeNoHomogenizado;
	protected BigDecimal valorM2HomogenizadoSinGarajeYAreaLibre;

	// variables auxiliares calculos
	protected Integer diferencia;
	protected BigDecimal totalAreaLibreSujeto;
	protected BigDecimal totalAreaPrivadaSujeto;

	public OfertaPHDTO() {
		// TODO Auto-generated constructor stub
		this.ubicacion = FactorHomologacion.S.getKey();
		this.acabados = FactorHomologacion.S.getKey();
		this.edificioConjunto = FactorHomologacion.S.getKey();
	}

	public OfertaPHDTO(Long idOfertaPH, Integer piso, BigDecimal area, Integer tipoArea, Integer garajes,
			Integer edificioConjunto, BigDecimal superficie, BigDecimal precioUnitarioGaraje,
			BigDecimal valorAdministracion, BigDecimal areaLibre, BigDecimal ubicacionPiso,
			BigDecimal valorMetroHomogenizadoSinGaraje, BigDecimal precioUnitarioAdministracionM2,
			BigDecimal valorM2SinGarajeNoHomogenizado, BigDecimal valorM2HomogenizadoSinGarajeYAreaLibre) {
		super();
		this.idOfertaPH = idOfertaPH;
		this.piso = piso;
		this.area = area;
		this.tipoArea = tipoArea;
		this.garajes = garajes;
		this.edificioConjunto = edificioConjunto;
		this.superficie = superficie;
		this.precioUnitarioGaraje = precioUnitarioGaraje;
		this.valorAdministracion = valorAdministracion;
		this.areaLibre = areaLibre;
		this.ubicacionPiso = ubicacionPiso;
		this.valorMetroHomogenizadoSinGaraje = valorMetroHomogenizadoSinGaraje;
		this.precioUnitarioAdministracionM2 = precioUnitarioAdministracionM2;
		this.valorM2SinGarajeNoHomogenizado = valorM2SinGarajeNoHomogenizado;
		this.valorM2HomogenizadoSinGarajeYAreaLibre = valorM2HomogenizadoSinGarajeYAreaLibre;
	}

	public OfertaPHDTO(OfertaPH oferta) {

		super(oferta);

		this.setIdOfertaPH(oferta.getId());
		this.setPiso(oferta.getPiso());
		this.setArea(oferta.getArea());
		this.setTipoArea(oferta.getTipoArea());
		if (oferta.getTipoArea() != null) {
			this.setTipoArea(TipoArea.fromKey(oferta.getTipoArea()));
		}
		this.setGarajes(oferta.getGarajes());

		this.setEdificioConjunto(oferta.getEdificioConjunto());
		if (oferta.getEdificioConjunto() != null) {
			this.setEdificioConjunto(FactorHomologacion.fromKey(oferta.getEdificioConjunto()));
		}

		this.setSuperficie(oferta.getSuperficie());
		this.setPrecioUnitarioGaraje(oferta.getPrecioUnitarioGaraje());
		this.setValorAdministracion(oferta.getValorAdministracion());
		this.setAreaLibre(oferta.getAreaLibre());
		this.setUbicacionPiso(oferta.getUbicacionPiso());
		this.setValorMetroHomogenizadoSinGaraje(oferta.getValorMetroHomogenizadoSinGaraje());
		this.setPrecioUnitarioAdministracionM2(oferta.getPrecioUnitarioAdministracionM2());
		this.setValorM2SinGarajeNoHomogenizado(oferta.getValorM2SinGarajeNoHomogenizado());
		this.setValorM2HomogenizadoSinGarajeYAreaLibre(oferta.getValorM2HomogenizadoSinGarajeYAreaLibre());

	}

	public OfertaPHDTO(Long id) {
		super();
		this.id = id;
	}

	public Long getIdOfertaPH() {
		return idOfertaPH;
	}

	public void setIdOfertaPH(Long idOfertaPH) {
		this.idOfertaPH = idOfertaPH;
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

	public TipoArea getTipoArea() {
		return TipoArea.fromKey(tipoArea != null ? tipoArea : 0);
	}

	public void setTipoArea(TipoArea tipoArea) {
		this.tipoArea = tipoArea == null ? 0 : tipoArea.getKey();
	}

	public void setTipoArea(Integer tipoArea) {
		this.tipoArea = tipoArea;
	}

	public Integer getGarajes() {
		return garajes;
	}

	public void setGarajes(Integer garajes) {
		this.garajes = garajes;
	}

	public FactorHomologacion getEdificioConjunto() {
		return FactorHomologacion.fromKey(edificioConjunto == null ? 0 : edificioConjunto);
	}

	public void setEdificioConjunto(FactorHomologacion edificioConjunto) {
		this.edificioConjunto = edificioConjunto == null ? 0 : edificioConjunto.getKey();
	}

	public void setEdificioConjunto(Integer edificioConjunto) {
		this.edificioConjunto = edificioConjunto;
	}

	public BigDecimal getSuperficie() {
		return superficie;
	}

	public void setSuperficie(BigDecimal superficie) {
		this.superficie = superficie;
	}

	public BigDecimal getPrecioUnitarioGaraje() {
		return precioUnitarioGaraje;
	}

	public void setPrecioUnitarioGaraje(BigDecimal precioUnitarioGaraje) {
		this.precioUnitarioGaraje = precioUnitarioGaraje;
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

	public BigDecimal getUbicacionPiso() {
		return ubicacionPiso;
	}

	public void setUbicacionPiso(BigDecimal ubicacionPiso) {
		this.ubicacionPiso = ubicacionPiso;
	}

	public BigDecimal getValorMetroHomogenizadoSinGaraje() {
		return valorMetroHomogenizadoSinGaraje;
	}

	public void setValorMetroHomogenizadoSinGaraje(BigDecimal valorMetroHomogenizadoSinGaraje) {
		this.valorMetroHomogenizadoSinGaraje = valorMetroHomogenizadoSinGaraje;
	}

	public BigDecimal getPrecioUnitarioAdministracionM2() {
		return precioUnitarioAdministracionM2;
	}

	public void setPrecioUnitarioAdministracionM2(BigDecimal precioUnitarioAdministracionM2) {
		this.precioUnitarioAdministracionM2 = precioUnitarioAdministracionM2;
	}

	public BigDecimal getValorM2SinGarajeNoHomogenizado() {
		return valorM2SinGarajeNoHomogenizado;
	}

	public void setValorM2SinGarajeNoHomogenizado(BigDecimal valorM2SinGarajeNoHomogenizado) {
		this.valorM2SinGarajeNoHomogenizado = valorM2SinGarajeNoHomogenizado;
	}

	public BigDecimal getValorM2HomogenizadoSinGarajeYAreaLibre() {
		return valorM2HomogenizadoSinGarajeYAreaLibre;
	}

	public void setValorM2HomogenizadoSinGarajeYAreaLibre(BigDecimal valorM2HomogenizadoSinGarajeYAreaLibre) {
		this.valorM2HomogenizadoSinGarajeYAreaLibre = valorM2HomogenizadoSinGarajeYAreaLibre;
	}

	public Integer getDiferencia() {
		return diferencia;
	}

	public void setDiferencia(Integer diferencia) {
		this.diferencia = diferencia;
	}

	public BigDecimal getTotalAreaLibreSujeto() {
		return totalAreaLibreSujeto;
	}

	public void setTotalAreaLibreSujeto(BigDecimal totalAreaLibreSujeto) {
		this.totalAreaLibreSujeto = totalAreaLibreSujeto;
	}

	public BigDecimal getTotalAreaPrivadaSujeto() {
		return totalAreaPrivadaSujeto;
	}

	public void setTotalAreaPrivadaSujeto(BigDecimal totalAreaPrivadaSujeto) {
		this.totalAreaPrivadaSujeto = totalAreaPrivadaSujeto;
	}

	public void calcularDiferencia(TipoProyecto tipo, Integer pisoSujeto) {

		EnumSet<TipoProyecto> sinEstrato = EnumSet.of(TipoProyecto.VIS_SIN_ASCENSOR,
				TipoProyecto.ESTRATO_MEDIO_SIN_ASCENSOR);
		EnumSet<TipoProyecto> estratoAlto = EnumSet.of(TipoProyecto.ESTRATO_ALTO);
		EnumSet<TipoProyecto> estratoMedio = EnumSet.of(TipoProyecto.VIS_CON_ASCENSOR,
				TipoProyecto.ESTRATO_MEDIO_CON_ASCENSOR);

		if (pisoSujeto != null && this.piso != null) {
			this.diferencia = this.piso - pisoSujeto;

			if (estratoMedio.contains(tipo)) { // 7
				if (this.diferencia > 7) {
					this.diferencia = 7;
				} else if (this.diferencia < -7) {
					this.diferencia = -7;
				}
			} else if (sinEstrato.contains(tipo)) { // 5 con signo invertido
				if (this.diferencia > 5) {
					this.diferencia = -5;
				} else if (this.diferencia < -5) {
					this.diferencia = 5;
				} else {
					this.diferencia = this.diferencia * (-1);
				}
			} else if (estratoAlto.contains(tipo)) { // 10
				if (this.diferencia > 10) {
					this.diferencia = 10;
				} else if (this.diferencia < -10) {
					this.diferencia = -10;
				}
			}

			this.diferencia = new BigDecimal(this.diferencia).intValue();
		}

	}

	public void calcularFactorHomologacionSuperficie(BigDecimal areaSujeto, BigDecimal factorSuperficie) {

		if (areaSujeto != null && !areaSujeto.equals(BigDecimal.ZERO) && factorSuperficie != null
				&& this.area != null) {
			this.superficie = BigDecimal
					.valueOf(Math.pow(this.area.divide(areaSujeto, 8, RoundingMode.HALF_UP).doubleValue(),
							factorSuperficie.doubleValue()));
		} else {
			this.superficie = BigDecimal.ZERO;
		}

	}

	public void calcularFactorHomologacionUbicacion(TipoProyecto tipo, BigDecimal parametroDiferenciaFormulaSencilla,
			BigDecimal parametroDiferenciaFormulaTabla, BigDecimal parametroPorcentajeFormulaSencilla,
			BigDecimal porcentajePisoFactor) {

		if (tipo != null && parametroDiferenciaFormulaSencilla != null && parametroDiferenciaFormulaTabla != null
				&& parametroPorcentajeFormulaSencilla != null && porcentajePisoFactor != null
				&& this.diferencia != null) {

			EnumSet<TipoProyecto> tiposProyectoFormulaSencillaSinAscensor = EnumSet.of(TipoProyecto.VIS_SIN_ASCENSOR,
					TipoProyecto.ESTRATO_MEDIO_SIN_ASCENSOR);
			EnumSet<TipoProyecto> tiposProyectoFormulaSencillaEstratoAlto = EnumSet.of(TipoProyecto.ESTRATO_ALTO);
			EnumSet<TipoProyecto> tiposProyectoFormulaTabla = EnumSet.of(TipoProyecto.VIS_CON_ASCENSOR,
					TipoProyecto.ESTRATO_MEDIO_CON_ASCENSOR);

			if (tiposProyectoFormulaSencillaSinAscensor.contains(tipo)) {
				this.ubicacionPiso = parametroDiferenciaFormulaSencilla.subtract(
						parametroPorcentajeFormulaSencilla.divide(BigDecimal.valueOf(100), 8, RoundingMode.HALF_UP)
								.multiply(new BigDecimal(this.diferencia)/*.negate()*/));
			} else if (tiposProyectoFormulaSencillaEstratoAlto.contains(tipo)) {
				this.ubicacionPiso = parametroDiferenciaFormulaSencilla.subtract(
						parametroPorcentajeFormulaSencilla.divide(BigDecimal.valueOf(100), 8, RoundingMode.HALF_UP)
								.multiply(new BigDecimal(this.diferencia)));
			} else if (tiposProyectoFormulaTabla.contains(tipo)) {
				this.ubicacionPiso = parametroDiferenciaFormulaTabla.subtract(
						new BigDecimal(this.diferencia).divide(BigDecimal.valueOf(100), 8, RoundingMode.HALF_UP));
			} else {
				this.ubicacionPiso = BigDecimal.ONE;
			}

		}

	}

	public void calcularValorM2HomogenizadoSinGaraje(BigDecimal areaConstruidaSujeto, BigDecimal areaPrivadaSujeto) {

		BigDecimal paraAreaPrivada = BigDecimal.ZERO;

		if (this.getTipoArea() != null && this.valorOferta != null && this.factorHomogenizacion != null
				&& this.precioUnitarioGaraje != null && this.garajes != null && this.area != null
				&& this.area.compareTo(BigDecimal.ZERO) != 0 && this.areaConstruida != null) {

			paraAreaPrivada = this.valorOferta.multiply(factorHomogenizacion)
					.subtract(this.getPrecioUnitarioGaraje().multiply(new BigDecimal(this.garajes)))
					.divide(this.area, 8, RoundingMode.HALF_UP);

			if (tipoArea == TipoArea.Privada.getKey()) {
				valorMetroHomogenizadoSinGaraje = paraAreaPrivada;
			} else if (areaConstruidaSujeto != null && tipoArea == TipoArea.Construida.getKey()
					&& areaPrivadaSujeto != null && areaPrivadaSujeto.compareTo(BigDecimal.ZERO) != 0) {
				valorMetroHomogenizadoSinGaraje = paraAreaPrivada
						.multiply(areaConstruidaSujeto.divide(areaPrivadaSujeto, 8, RoundingMode.HALF_UP));
			} else {
				valorMetroHomogenizadoSinGaraje = BigDecimal.ZERO;
			}
		}
	}

	public void calcularPrecioUnitarioAdministracionM2() {

		if (this.valorOferta != null && this.area != null && this.valorAdministracion != null
				&& this.area.compareTo(BigDecimal.ZERO) != 0) {
			this.precioUnitarioAdministracionM2 = this.valorAdministracion.divide(this.area, 8, RoundingMode.HALF_UP);
		} else {
			this.precioUnitarioAdministracionM2 = BigDecimal.ZERO;
		}
	}

	public void calcularValorM2SinGarajeNoHomogenizado(BigDecimal areaConstruidaSujeto) {

		BigDecimal paraAreaPrivada = BigDecimal.ZERO;

		if (this.getTipoArea() != null && this.valorOferta != null && this.precioUnitarioGaraje != null
				&& this.garajes != null && this.area != null && this.area.compareTo(BigDecimal.ZERO) != 0) {

			paraAreaPrivada = this.valorOferta
					.subtract(this.precioUnitarioGaraje.multiply(new BigDecimal(this.garajes)))
					.divide(this.area, 8, RoundingMode.HALF_UP);

			if (tipoArea == TipoArea.Privada.getKey()) {
				this.valorM2SinGarajeNoHomogenizado = paraAreaPrivada;
			} else if (tipoArea == TipoArea.Construida.getKey() && this.totalAreaPrivadaSujeto != null
					&& this.totalAreaPrivadaSujeto.compareTo(BigDecimal.ZERO) != 0 && areaConstruidaSujeto != null) {
				this.valorM2SinGarajeNoHomogenizado = paraAreaPrivada
						.multiply(areaConstruidaSujeto.divide(this.totalAreaPrivadaSujeto, 8, RoundingMode.HALF_UP));
			} else {
				this.valorM2SinGarajeNoHomogenizado = BigDecimal.ZERO;
			}
		} else {
			this.valorM2SinGarajeNoHomogenizado = BigDecimal.ZERO;
		}
	}

	public void calcularTotalAreas(List<AreaDTO> listaAreas) {

		this.totalAreaLibreSujeto = BigDecimal.ZERO;
		this.totalAreaPrivadaSujeto = BigDecimal.ZERO;

		for (AreaDTO areaDTO : listaAreas) {

			if (areaDTO.getDescripcionNumerica() != null
					&& areaDTO.getDescripcionNumerica().getKey() == AreaDTO.DescripcionAreaPH.AreaLibre.getKey()
					&& areaDTO.getArea() != null) {
				this.totalAreaLibreSujeto = this.totalAreaLibreSujeto.add(areaDTO.getArea());
			} else if (areaDTO.getDescripcionNumerica() != null
					&& areaDTO.getDescripcionNumerica().getKey() == AreaDTO.DescripcionAreaPH.AreaPrivada.getKey()
					&& areaDTO.getArea() != null) {
				this.totalAreaPrivadaSujeto = this.totalAreaPrivadaSujeto.add(areaDTO.getArea());
			}

		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OfertaPHDTO other = (OfertaPHDTO) obj;
		if (idOfertaPH == null) {
			if (other.idOfertaPH != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
