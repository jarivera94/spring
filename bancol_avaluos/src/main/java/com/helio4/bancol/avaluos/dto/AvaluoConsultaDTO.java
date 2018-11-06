package com.helio4.bancol.avaluos.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import org.primefaces.model.map.MapModel;

public class AvaluoConsultaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	// -------------------DATOS PARA CONSULTA----------------------------
	private TipoInmuebleDTO tipoInmuebleDTO;

	private String departamento;
	private DivipolaDTO divipolaCiudad;
	private Integer sector;

	private String tipoVia;
	private Integer numeroVia;
	private String complementoVia;
	private Integer numeroViaGeneradora;
	private String complementoViaGeneradora;
	private Integer placa;
	private String complementoPlaca;
	private String adicional;
	private String direccion;
	private String barrio;
	private Integer estrato;
	private Integer cuadrasRedonda;

	private String centroMapa;
	private MapModel mapModel;
	private BigDecimal latitud;
	private BigDecimal longitud;
	private Integer kilometrosRedonda;

	private boolean panelPH;
	private String ph;
	private boolean inmuebleUrbNoPH;
	private boolean inmuebleUrbPH;
	private boolean inmuebleRur;

	private boolean edadInm;

	private Integer urbAreaTotalMinNPH;
	private Integer urbAreaTotalMaxNPH;
	private Integer urbAreaConstMinNPH;
	private Integer urbAreaConstMaxNPH;
	private Integer habitacionesNPH;
	private Integer banosNPH;
	private String tipoParqNPH;
	private Integer numParqNPH;

	private String estadoInmNPH;
	private String edadInmuebleNPH;

	private Integer urbAreaPrivMinPH;
	private Integer urbAreaPrivMaxPH;
	private Integer habitacionesPH;
	private Integer banosPH;
	private String tipoParqPH;
	private Integer numParqPH;

	private String estadoInmPH;
	private String edadInmueblePH;

	private Integer rurAreaTotalMin;
	private Integer rurAreaTotalMax;
	private Integer rurAreaConstMin;
	private Integer rurAreaConstMax;
	private Integer rurAreaInfrMin;
	private Integer rurAreaInfrMax;
	private Integer rurAreaCultMin;
	private Integer rurAreaCultMax;
	private Integer rurDistCascoUrb;
	private Integer rurCondCultivos;
	private Integer rurCondAgrolog;

	private String valorMax;
	private String valorMin;

	// -------------------DATOS PARA RESULTADO----------------------------
	private Integer resIdAvaluo;
	private Integer resTipoInmueble;
	private String resNombreTipoInmueble;
	// RESULTADO URBANO PH
	private boolean esUrbanoPH;
	private BigDecimal resUrbPHlatitud, resUrbPHlongitud;
	private String resUrbPHbarrio, resUrbPHdireccion, resUrbPHciudad,
			resUrbPHdepartamento, resUrbPHnombreEstadoConstruccion;
	private Integer resUrbPHestadoConstruccion, resUrbPHhabitaciones,
			resUrbPHestrato;
	private Long resUrbPHvalorUnidad;
	private BigDecimal resUrbPHedad, resUrbPHvalorMtrCuadrado,
			resUrbPHvalorTotal, resUrbPHareaPrivada;
	// RESULTADO URBANO NPH
	private boolean esUrbanoNPH;
	private BigDecimal resUrbNPHlatitud, resUrbNPHlongitud;
	private String resUrbNPHbarrio, resUrbNPHdireccion, resUrbNPHciudad,
			resUrbNPHdepartamento, resUrbNPHnombreEstadoConstruccion;
	private Integer resUrbNPHestadoConstruccion, resUrbNPHhabitaciones,
			resUrbNPHestrato;
	private Long resUrbNPHvalorUnidad;
	private BigDecimal resUrbNPHedad, resUrbNPHvalorMtrCuadrado,
			resUrbNPHvalorTotal, resUrbNPHareaTotal;
	// RESULTADO RURAL
	private boolean esRural;
	private BigDecimal resRurallatitud, resRurallongitud;
	private String resRuralVereda, resRuralDireccion, resRuralCiudad,
			resRuralDepartamento;
	private Integer resRuralDistCascoUrbano;
	private Long resRuralvalorUnidad;
	private BigDecimal resRuralEdad, resRuralAreaConst,
			resRuralvalorMtrCuadradoAreaConst,
			resRuralAreaCultivosAvaluables, resRuralvalorHtaCultivosAvaluables,
			resRuralAreaTerreno, resRuralValorHtaTerreno, resRuralvalorTotal;

	// ------------getter and setter------------
	public TipoInmuebleDTO getTipoInmuebleDTO() {
		return tipoInmuebleDTO;
	}

	public void setTipoInmuebleDTO(TipoInmuebleDTO tipoInmuebleDTO) {
		this.tipoInmuebleDTO = tipoInmuebleDTO;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public Integer getSector() {
		return sector;
	}

	public void setSector(Integer sector) {
		this.sector = sector;
	}

	public String getTipoVia() {
		return tipoVia;
	}

	public void setTipoVia(String tipoVia) {
		this.tipoVia = tipoVia;
	}

	public Integer getNumeroVia() {
		if (numeroVia == null) {
			numeroVia = 0;
		}
		return numeroVia;
	}

	public void setNumeroVia(Integer numeroVia) {
		this.numeroVia = numeroVia;
	}

	public String getComplementoVia() {
		return complementoVia;
	}

	public void setComplementoVia(String complementoVia) {
		this.complementoVia = complementoVia;
	}

	public Integer getNumeroViaGeneradora() {
		if (numeroViaGeneradora == null) {
			numeroViaGeneradora = 0;
		}
		return numeroViaGeneradora;
	}

	public void setNumeroViaGeneradora(Integer numeroViaGeneradora) {
		this.numeroViaGeneradora = numeroViaGeneradora;
	}

	public String getComplementoViaGeneradora() {
		return complementoViaGeneradora;
	}

	public void setComplementoViaGeneradora(String complementoViaGeneradora) {
		this.complementoViaGeneradora = complementoViaGeneradora;
	}

	public Integer getPlaca() {
		if (placa == null) {
			placa = 0;
		}
		return placa;
	}

	public void setPlaca(Integer placa) {
		this.placa = placa;
	}

	public String getComplementoPlaca() {
		return complementoPlaca;
	}

	public void setComplementoPlaca(String complementoPlaca) {
		this.complementoPlaca = complementoPlaca;
	}

	public String getAdicional() {
		return adicional;
	}

	public void setAdicional(String adicional) {
		this.adicional = adicional;
	}

	public Integer getEstrato() {
		return estrato;
	}

	public void setEstrato(Integer estrato) {
		this.estrato = estrato;
	}

	public Integer getCuadrasRedonda() {
		return cuadrasRedonda;
	}

	public void setCuadrasRedonda(Integer cuadrasRedonda) {
		this.cuadrasRedonda = cuadrasRedonda;
	}

	public String getCentroMapa() {
		return centroMapa;
	}

	public void setCentroMapa(String centroMapa) {
		this.centroMapa = centroMapa;
	}

	public MapModel getMapModel() {
		return mapModel;
	}

	public void setMapModel(MapModel mapModel) {
		this.mapModel = mapModel;
	}

	public BigDecimal getLatitud() {
		return latitud;
	}

	public void setLatitud(BigDecimal latitud) {
		this.latitud = latitud;
	}

	public BigDecimal getLongitud() {
		return longitud;
	}

	public void setLongitud(BigDecimal longitud) {
		this.longitud = longitud;
	}

	public boolean isPanelPH() {
		return panelPH;
	}

	public void setPanelPH(boolean panelPH) {
		this.panelPH = panelPH;
	}

	public String getPh() {
		return ph;
	}

	public void setPh(String ph) {
		this.ph = ph;
	}

	public boolean isInmuebleUrbNoPH() {
		return inmuebleUrbNoPH;
	}

	public void setInmuebleUrbNoPH(boolean inmuebleUrbNoPH) {
		this.inmuebleUrbNoPH = inmuebleUrbNoPH;
	}

	public boolean isInmuebleUrbPH() {
		return inmuebleUrbPH;
	}

	public void setInmuebleUrbPH(boolean inmuebleUrbPH) {
		this.inmuebleUrbPH = inmuebleUrbPH;
	}

	public boolean isInmuebleRur() {
		return inmuebleRur;
	}

	public void setInmuebleRur(boolean inmuebleRur) {
		this.inmuebleRur = inmuebleRur;
	}

	public boolean isEdadInm() {
		return edadInm;
	}

	public void setEdadInm(boolean edadInm) {
		this.edadInm = edadInm;
	}

	public Integer getUrbAreaTotalMinNPH() {
		return urbAreaTotalMinNPH;
	}

	public void setUrbAreaTotalMinNPH(Integer urbAreaTotalMinNPH) {
		this.urbAreaTotalMinNPH = urbAreaTotalMinNPH;
	}

	public Integer getUrbAreaTotalMaxNPH() {
		return urbAreaTotalMaxNPH;
	}

	public void setUrbAreaTotalMaxNPH(Integer urbAreaTotalMaxNPH) {
		this.urbAreaTotalMaxNPH = urbAreaTotalMaxNPH;
	}

	public Integer getUrbAreaConstMinNPH() {
		return urbAreaConstMinNPH;
	}

	public void setUrbAreaConstMinNPH(Integer urbAreaConstMinNPH) {
		this.urbAreaConstMinNPH = urbAreaConstMinNPH;
	}

	public Integer getUrbAreaConstMaxNPH() {
		return urbAreaConstMaxNPH;
	}

	public void setUrbAreaConstMaxNPH(Integer urbAreaConstMaxNPH) {
		this.urbAreaConstMaxNPH = urbAreaConstMaxNPH;
	}

	public Integer getHabitacionesNPH() {
		return habitacionesNPH;
	}

	public void setHabitacionesNPH(Integer habitacionesNPH) {
		this.habitacionesNPH = habitacionesNPH;
	}

	public Integer getBanosNPH() {
		return banosNPH;
	}

	public void setBanosNPH(Integer banosNPH) {
		this.banosNPH = banosNPH;
	}

	public String getTipoParqNPH() {
		return tipoParqNPH;
	}

	public void setTipoParqNPH(String tipoParqNPH) {
		this.tipoParqNPH = tipoParqNPH;
	}

	public Integer getNumParqNPH() {
		return numParqNPH;
	}

	public void setNumParqNPH(Integer numParqNPH) {
		this.numParqNPH = numParqNPH;
	}

	public String getEstadoInmNPH() {
		return estadoInmNPH;
	}

	public void setEstadoInmNPH(String estadoInmNPH) {
		this.estadoInmNPH = estadoInmNPH;
	}

	public String getEdadInmuebleNPH() {
		return edadInmuebleNPH;
	}

	public void setEdadInmuebleNPH(String edadInmuebleNPH) {
		this.edadInmuebleNPH = edadInmuebleNPH;
	}

	public Integer getHabitacionesPH() {
		return habitacionesPH;
	}

	public void setHabitacionesPH(Integer habitacionesPH) {
		this.habitacionesPH = habitacionesPH;
	}

	public Integer getBanosPH() {
		return banosPH;
	}

	public void setBanosPH(Integer banosPH) {
		this.banosPH = banosPH;
	}

	public String getTipoParqPH() {
		return tipoParqPH;
	}

	public void setTipoParqPH(String tipoParqPH) {
		this.tipoParqPH = tipoParqPH;
	}

	public Integer getNumParqPH() {
		return numParqPH;
	}

	public void setNumParqPH(Integer numParqPH) {
		this.numParqPH = numParqPH;
	}

	public String getEstadoInmPH() {
		return estadoInmPH;
	}

	public void setEstadoInmPH(String estadoInmPH) {
		this.estadoInmPH = estadoInmPH;
	}

	public String getEdadInmueblePH() {
		return edadInmueblePH;
	}

	public void setEdadInmueblePH(String edadInmueblePH) {
		this.edadInmueblePH = edadInmueblePH;
	}

	public Integer getRurAreaTotalMin() {
		return rurAreaTotalMin;
	}

	public void setRurAreaTotalMin(Integer rurAreaTotalMin) {
		this.rurAreaTotalMin = rurAreaTotalMin;
	}

	public Integer getRurAreaTotalMax() {
		return rurAreaTotalMax;
	}

	public void setRurAreaTotalMax(Integer rurAreaTotalMax) {
		this.rurAreaTotalMax = rurAreaTotalMax;
	}

	public Integer getRurAreaConstMin() {
		return rurAreaConstMin;
	}

	public void setRurAreaConstMin(Integer rurAreaConstMin) {
		this.rurAreaConstMin = rurAreaConstMin;
	}

	public Integer getRurAreaConstMax() {
		return rurAreaConstMax;
	}

	public void setRurAreaConstMax(Integer rurAreaConstMax) {
		this.rurAreaConstMax = rurAreaConstMax;
	}

	public Integer getRurAreaInfrMin() {
		return rurAreaInfrMin;
	}

	public void setRurAreaInfrMin(Integer rurAreaInfrMin) {
		this.rurAreaInfrMin = rurAreaInfrMin;
	}

	public Integer getRurAreaInfrMax() {
		return rurAreaInfrMax;
	}

	public void setRurAreaInfrMax(Integer rurAreaInfrMax) {
		this.rurAreaInfrMax = rurAreaInfrMax;
	}

	public Integer getRurAreaCultMin() {
		return rurAreaCultMin;
	}

	public void setRurAreaCultMin(Integer rurAreaCultMin) {
		this.rurAreaCultMin = rurAreaCultMin;
	}

	public Integer getRurAreaCultMax() {
		return rurAreaCultMax;
	}

	public void setRurAreaCultMax(Integer rurAreaCultMax) {
		this.rurAreaCultMax = rurAreaCultMax;
	}

	public Integer getRurDistCascoUrb() {
		return rurDistCascoUrb;
	}

	public void setRurDistCascoUrb(Integer rurDistCascoUrb) {
		this.rurDistCascoUrb = rurDistCascoUrb;
	}

	public Integer getRurCondCultivos() {
		return rurCondCultivos;
	}

	public void setRurCondCultivos(Integer rurCondCultivos) {
		this.rurCondCultivos = rurCondCultivos;
	}

	public Integer getRurCondAgrolog() {
		return rurCondAgrolog;
	}

	public void setRurCondAgrolog(Integer rurCondAgrolog) {
		this.rurCondAgrolog = rurCondAgrolog;
	}

	public String getValorMax() {
		if (valorMax != null) {
			if ("".equals(valorMax)) {
				valorMax = "100000000";
			}
		}
		return valorMax;
	}

	public void setValorMax(String valorMax) {
		this.valorMax = valorMax;
	}

	public String getValorMin() {
		if (valorMin != null) {
			if ("".equals(valorMin)) {
				valorMin = "0";
			}
		}
		return valorMin;
	}

	public void setValorMin(String valorMin) {
		this.valorMin = valorMin;
	}

	public DivipolaDTO getDivipolaCiudad() {
		return divipolaCiudad;
	}

	public void setDivipolaCiudad(DivipolaDTO divipolaCiudad) {
		this.divipolaCiudad = divipolaCiudad;
	}

	public Integer getUrbAreaPrivMinPH() {
		if(urbAreaPrivMinPH == null){
			urbAreaPrivMinPH = 0;
		}
		return urbAreaPrivMinPH;
	}

	public void setUrbAreaPrivMinPH(Integer urbAreaPrivMinPH) {
		this.urbAreaPrivMinPH = urbAreaPrivMinPH;
	}

	public Integer getUrbAreaPrivMaxPH() {
		if(urbAreaPrivMaxPH == null){
			urbAreaPrivMaxPH = 1000;
		}
		return urbAreaPrivMaxPH;
	}

	public void setUrbAreaPrivMaxPH(Integer urbAreaPrivMaxPH) {
		this.urbAreaPrivMaxPH = urbAreaPrivMaxPH;
	}

	public String getBarrio() {
		return barrio;
	}

	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}

	public Integer getResIdAvaluo() {
		return resIdAvaluo;
	}

	public void setResIdAvaluo(Integer resIdAvaluo) {
		this.resIdAvaluo = resIdAvaluo;
	}

	public Integer getResTipoInmueble() {
		return resTipoInmueble;
	}

	public void setResTipoInmueble(Integer resTipoInmueble) {
		this.resTipoInmueble = resTipoInmueble;
	}

	public BigDecimal getResUrbPHlatitud() {
		return resUrbPHlatitud;
	}

	public void setResUrbPHlatitud(BigDecimal resUrbPHlatitud) {
		this.resUrbPHlatitud = resUrbPHlatitud;
	}

	public BigDecimal getResUrbPHlongitud() {
		return resUrbPHlongitud;
	}

	public void setResUrbPHlongitud(BigDecimal resUrbPHlongitud) {
		this.resUrbPHlongitud = resUrbPHlongitud;
	}

	public String getResUrbPHbarrio() {
		return resUrbPHbarrio;
	}

	public void setResUrbPHbarrio(String resUrbPHbarrio) {
		this.resUrbPHbarrio = resUrbPHbarrio;
	}

	public String getResUrbPHdireccion() {
		return resUrbPHdireccion;
	}

	public void setResUrbPHdireccion(String resUrbPHdireccion) {
		this.resUrbPHdireccion = resUrbPHdireccion;
	}

	public String getResUrbPHciudad() {
		return resUrbPHciudad;
	}

	public void setResUrbPHciudad(String resUrbPHciudad) {
		this.resUrbPHciudad = resUrbPHciudad;
	}

	public String getResUrbPHdepartamento() {
		return resUrbPHdepartamento;
	}

	public void setResUrbPHdepartamento(String resUrbPHdepartamento) {
		this.resUrbPHdepartamento = resUrbPHdepartamento;
	}

	public Integer getResUrbPHestadoConstruccion() {
		return resUrbPHestadoConstruccion;
	}

	public void setResUrbPHestadoConstruccion(Integer resUrbPHestadoConstruccion) {
		this.resUrbPHestadoConstruccion = resUrbPHestadoConstruccion;
	}

	public BigDecimal getResUrbPHedad() {
		return resUrbPHedad;
	}

	public void setResUrbPHedad(BigDecimal resUrbPHedad) {
		this.resUrbPHedad = resUrbPHedad;
	}

	public Integer getResUrbPHhabitaciones() {
		return resUrbPHhabitaciones;
	}

	public void setResUrbPHhabitaciones(Integer resUrbPHhabitaciones) {
		this.resUrbPHhabitaciones = resUrbPHhabitaciones;
	}

	public Long getResUrbPHvalorUnidad() {
		return resUrbPHvalorUnidad;
	}

	public void setResUrbPHvalorUnidad(Long resUrbPHvalorUnidad) {
		this.resUrbPHvalorUnidad = resUrbPHvalorUnidad;
	}

	public BigDecimal getResUrbPHvalorTotal() {
		return resUrbPHvalorTotal;
	}

	public void setResUrbPHvalorTotal(BigDecimal resUrbPHvalorTotal) {
		this.resUrbPHvalorTotal = resUrbPHvalorTotal;
	}

	public BigDecimal getResUrbNPHlatitud() {
		return resUrbNPHlatitud;
	}

	public void setResUrbNPHlatitud(BigDecimal resUrbNPHlatitud) {
		this.resUrbNPHlatitud = resUrbNPHlatitud;
	}

	public BigDecimal getResUrbNPHlongitud() {
		return resUrbNPHlongitud;
	}

	public void setResUrbNPHlongitud(BigDecimal resUrbNPHlongitud) {
		this.resUrbNPHlongitud = resUrbNPHlongitud;
	}

	public String getResUrbNPHbarrio() {
		return resUrbNPHbarrio;
	}

	public void setResUrbNPHbarrio(String resUrbNPHbarrio) {
		this.resUrbNPHbarrio = resUrbNPHbarrio;
	}

	public String getResUrbNPHdireccion() {
		return resUrbNPHdireccion;
	}

	public void setResUrbNPHdireccion(String resUrbNPHdireccion) {
		this.resUrbNPHdireccion = resUrbNPHdireccion;
	}

	public String getResUrbNPHciudad() {
		return resUrbNPHciudad;
	}

	public void setResUrbNPHciudad(String resUrbNPHciudad) {
		this.resUrbNPHciudad = resUrbNPHciudad;
	}

	public String getResUrbNPHdepartamento() {
		return resUrbNPHdepartamento;
	}

	public void setResUrbNPHdepartamento(String resUrbNPHdepartamento) {
		this.resUrbNPHdepartamento = resUrbNPHdepartamento;
	}

	public Integer getResUrbNPHestadoConstruccion() {
		return resUrbNPHestadoConstruccion;
	}

	public void setResUrbNPHestadoConstruccion(
			Integer resUrbNPHestadoConstruccion) {
		this.resUrbNPHestadoConstruccion = resUrbNPHestadoConstruccion;
	}

	public BigDecimal getResUrbNPHedad() {
		return resUrbNPHedad;
	}

	public void setResUrbNPHedad(BigDecimal resUrbNPHedad) {
		this.resUrbNPHedad = resUrbNPHedad;
	}

	public Integer getResUrbNPHhabitaciones() {
		return resUrbNPHhabitaciones;
	}

	public void setResUrbNPHhabitaciones(Integer resUrbNPHhabitaciones) {
		this.resUrbNPHhabitaciones = resUrbNPHhabitaciones;
	}

	public Long getResUrbNPHvalorUnidad() {
		return resUrbNPHvalorUnidad;
	}

	public void setResUrbNPHvalorUnidad(Long resUrbNPHvalorUnidad) {
		this.resUrbNPHvalorUnidad = resUrbNPHvalorUnidad;
	}

	public BigDecimal getResUrbNPHvalorTotal() {
		return resUrbNPHvalorTotal;
	}

	public void setResUrbNPHvalorTotal(BigDecimal resUrbNPHvalorTotal) {
		this.resUrbNPHvalorTotal = resUrbNPHvalorTotal;
	}

	public BigDecimal getResRurallatitud() {
		return resRurallatitud;
	}

	public void setResRurallatitud(BigDecimal resRurallatitud) {
		this.resRurallatitud = resRurallatitud;
	}

	public BigDecimal getResRurallongitud() {
		return resRurallongitud;
	}

	public void setResRurallongitud(BigDecimal resRurallongitud) {
		this.resRurallongitud = resRurallongitud;
	}

	public String getResRuralVereda() {
		return resRuralVereda;
	}

	public void setResRuralVereda(String resRuralVereda) {
		this.resRuralVereda = resRuralVereda;
	}

	public String getResRuralDireccion() {
		return resRuralDireccion;
	}

	public void setResRuralDireccion(String resRuralDireccion) {
		this.resRuralDireccion = resRuralDireccion;
	}

	public String getResRuralCiudad() {
		return resRuralCiudad;
	}

	public void setResRuralCiudad(String resRuralCiudad) {
		this.resRuralCiudad = resRuralCiudad;
	}

	public String getResRuralDepartamento() {
		return resRuralDepartamento;
	}

	public void setResRuralDepartamento(String resRuralDepartamento) {
		this.resRuralDepartamento = resRuralDepartamento;
	}

	public Integer getResRuralDistCascoUrbano() {
		return resRuralDistCascoUrbano;
	}

	public void setResRuralDistCascoUrbano(Integer resRuralDistCascoUrbano) {
		this.resRuralDistCascoUrbano = resRuralDistCascoUrbano;
	}

	public Long getResRuralvalorUnidad() {
		return resRuralvalorUnidad;
	}

	public void setResRuralvalorUnidad(Long resRuralvalorUnidad) {
		this.resRuralvalorUnidad = resRuralvalorUnidad;
	}

	public BigDecimal getResRuralvalorTotal() {
		return resRuralvalorTotal;
	}

	public void setResRuralvalorTotal(BigDecimal resRuralvalorTotal) {
		this.resRuralvalorTotal = resRuralvalorTotal;
	}

	public Integer getKilometrosRedonda() {
		return kilometrosRedonda;
	}

	public void setKilometrosRedonda(Integer kilometrosRedonda) {
		this.kilometrosRedonda = kilometrosRedonda;
	}

	public Integer getResUrbPHestrato() {
		return resUrbPHestrato;
	}

	public void setResUrbPHestrato(Integer resUrbPHestrato) {
		this.resUrbPHestrato = resUrbPHestrato;
	}

	public Integer getResUrbNPHestrato() {
		return resUrbNPHestrato;
	}

	public void setResUrbNPHestrato(Integer resUrbNPHestrato) {
		this.resUrbNPHestrato = resUrbNPHestrato;
	}

	public BigDecimal getResUrbPHareaPrivada() {
		return resUrbPHareaPrivada;
	}

	public void setResUrbPHareaPrivada(BigDecimal resUrbPHareaPrivada) {
		this.resUrbPHareaPrivada = resUrbPHareaPrivada;
	}

	public BigDecimal getResUrbPHvalorMtrCuadrado() {
		return resUrbPHvalorMtrCuadrado;
	}

	public void setResUrbPHvalorMtrCuadrado(BigDecimal resUrbPHvalorMtrCuadrado) {
		this.resUrbPHvalorMtrCuadrado = resUrbPHvalorMtrCuadrado;
	}

	public String getResNombreTipoInmueble() {
		return resNombreTipoInmueble;
	}

	public void setResNombreTipoInmueble(String resNombreTipoInmueble) {
		this.resNombreTipoInmueble = resNombreTipoInmueble;
	}

	public String getResUrbPHnombreEstadoConstruccion() {
		return resUrbPHnombreEstadoConstruccion;
	}

	public void setResUrbPHnombreEstadoConstruccion(
			String resUrbPHnombreEstadoConstruccion) {
		this.resUrbPHnombreEstadoConstruccion = resUrbPHnombreEstadoConstruccion;
	}

	public boolean isEsUrbanoPH() {
		return esUrbanoPH;
	}

	public void setEsUrbanoPH(boolean esUrbanoPH) {
		this.esUrbanoPH = esUrbanoPH;
	}

	public boolean isEsUrbanoNPH() {
		return esUrbanoNPH;
	}

	public void setEsUrbanoNPH(boolean esUrbanoNPH) {
		this.esUrbanoNPH = esUrbanoNPH;
	}

	public boolean isEsRural() {
		return esRural;
	}

	public void setEsRural(boolean esRural) {
		this.esRural = esRural;
	}

	public BigDecimal getResUrbNPHareaTotal() {
		return resUrbNPHareaTotal;
	}

	public void setResUrbNPHareaTotal(BigDecimal resUrbNPHareaTotal) {
		this.resUrbNPHareaTotal = resUrbNPHareaTotal;
	}

	public BigDecimal getResUrbNPHvalorMtrCuadrado() {
		return resUrbNPHvalorMtrCuadrado;
	}

	public void setResUrbNPHvalorMtrCuadrado(
			BigDecimal resUrbNPHvalorMtrCuadrado) {
		this.resUrbNPHvalorMtrCuadrado = resUrbNPHvalorMtrCuadrado;
	}

	public String getResUrbNPHnombreEstadoConstruccion() {
		return resUrbNPHnombreEstadoConstruccion;
	}

	public void setResUrbNPHnombreEstadoConstruccion(
			String resUrbNPHnombreEstadoConstruccion) {
		this.resUrbNPHnombreEstadoConstruccion = resUrbNPHnombreEstadoConstruccion;
	}

	public BigDecimal getResRuralEdad() {
		return resRuralEdad;
	}

	public void setResRuralEdad(BigDecimal resRuralEdad) {
		this.resRuralEdad = resRuralEdad;
	}

	public BigDecimal getResRuralAreaConst() {
		return resRuralAreaConst;
	}

	public void setResRuralAreaConst(BigDecimal resRuralAreaConst) {
		this.resRuralAreaConst = resRuralAreaConst;
	}

	public BigDecimal getResRuralvalorMtrCuadradoAreaConst() {
		return resRuralvalorMtrCuadradoAreaConst;
	}

	public void setResRuralvalorMtrCuadradoAreaConst(
			BigDecimal resRuralvalorMtrCuadradoAreaConst) {
		this.resRuralvalorMtrCuadradoAreaConst = resRuralvalorMtrCuadradoAreaConst;
	}

	public BigDecimal getResRuralAreaCultivosAvaluables() {
		return resRuralAreaCultivosAvaluables;
	}

	public void setResRuralAreaCultivosAvaluables(
			BigDecimal resRuralAreaCultivosAvaluables) {
		this.resRuralAreaCultivosAvaluables = resRuralAreaCultivosAvaluables;
	}

	public BigDecimal getResRuralvalorHtaCultivosAvaluables() {
		return resRuralvalorHtaCultivosAvaluables;
	}

	public void setResRuralvalorHtaCultivosAvaluables(
			BigDecimal resRuralvalorHtaCultivosAvaluables) {
		this.resRuralvalorHtaCultivosAvaluables = resRuralvalorHtaCultivosAvaluables;
	}

	public BigDecimal getResRuralAreaTerreno() {
		return resRuralAreaTerreno;
	}

	public void setResRuralAreaTerreno(BigDecimal resRuralAreaTerreno) {
		this.resRuralAreaTerreno = resRuralAreaTerreno;
	}

	public BigDecimal getResRuralValorHtaTerreno() {
		return resRuralValorHtaTerreno;
	}

	public void setResRuralValorHtaTerreno(BigDecimal resRuralValorHtaTerreno) {
		this.resRuralValorHtaTerreno = resRuralValorHtaTerreno;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
}
