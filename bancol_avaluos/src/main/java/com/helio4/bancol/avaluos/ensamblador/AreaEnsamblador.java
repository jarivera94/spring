package com.helio4.bancol.avaluos.ensamblador;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.AreaConstruccionDTO;
import com.helio4.bancol.avaluos.dto.AreaDTO;
import com.helio4.bancol.avaluos.dto.AreaDTO.DescripcionAreaNoPH;
import com.helio4.bancol.avaluos.dto.AreaDTO.DescripcionAreaPH;
import com.helio4.bancol.avaluos.dto.AreaDTO.TipoArea;
import com.helio4.bancol.avaluos.dto.AreaDTO.UnidadDeMedida;
import com.helio4.bancol.avaluos.dto.AvaluoDTO.MBR;
import com.helio4.bancol.avaluos.modelo.Area;
import com.helio4.bancol.avaluos.modelo.AreaConstruccion;
import com.helio4.bancol.avaluos.modelo.Avaluo;
import com.helio4.bancol.avaluos.modelo.AvaluoComercial;
import com.helio4.bancol.avaluos.persistencia.AreaConstruccionRepository;
import com.helio4.bancol.avaluos.persistencia.AreaRepository;
import com.helio4.bancol.avaluos.persistencia.AvaluoComercialRepository;
import com.helio4.bancol.avaluos.persistencia.AvaluoRepository;
import com.helio4.bancol.avaluos.servicio.excepciones.AreaNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;

@Component
public class AreaEnsamblador {

	@Autowired
	private AvaluoRepository avaluoRepository;

	@Autowired
	private AreaRepository areaRepository;
	
	@Autowired
	private AvaluoComercialRepository avaluoComercialRepository;
	
	@Autowired
	private AreaConstruccionRepository areaConstruccionRepository;
	
	public Area crearArea(AreaDTO areaDTO) throws AvaluoNotFoundException {
		if (areaDTO.getClass().equals(AreaDTO.class)) {
			return crearArea(areaDTO, new Area());
		}
		return null;
	}
	
	private Area crearArea(AreaDTO areaDTO, Area area) throws AvaluoNotFoundException {
		area.setNombre(areaDTO.getNombre());
		area.setDescripcion(areaDTO.getDescripcion());
		if (areaDTO.getDescripcionNumerica() != null) {
			area.setDescripcionNumerica(areaDTO.getDescripcionNumerica()
					.getKey());
		}
		area.setArea(areaDTO.getArea());
		if (areaDTO.getUnidadDeMedida() != null) {
			area.setUnidadDeMedida(areaDTO.getUnidadDeMedida().getKey());
		}
		area.setValorUnitario(areaDTO.getValorUnitario());
		area.setValorTotal(areaDTO.getValorTotal());
		area.setCoeficienteCopropiedad(areaDTO.getCoeficienteCopropiedad());
		area.setAreaEnMetros(areaDTO.getAreaEnMetros());
		area.setPorcentaje(areaDTO.getPorcentaje());
		area.setPorcentajeValorProporcionalTerreno(areaDTO.getPorcentajeValorProporcionalTerreno());
		area.setPorcentajeValorProporcionalConstruccion(areaDTO.getPorcentajeValorProporcionalConstruccion());
		area.setValorReposicion(areaDTO.getValorReposicion());
		area.setCostoTotalReposicion(areaDTO.getCostoTotalReposicion());
		area.setValorRazonable(areaDTO.getValorComercial());
		area.setValorResidualConstruccion(areaDTO.getValorResidualConstruccion());
		if (areaDTO.getTipoArea() != null) {
			area.setTipoArea(areaDTO.getTipoArea().getKey());
		}
		Avaluo avaluo = avaluoRepository.findOne(areaDTO.getAvaluoId());
		if (avaluo == null) {
			throw new AvaluoNotFoundException();
		}
		area.setAvaluo(avaluo);
		
		area.setDepreciacion( areaDTO.getDepreciacion() );
		area.setCalificacion( areaDTO.getCalificacion() );
		area.setCostoReposicion( areaDTO.getCostoReposicion() );
		area.setValorAdoptado( areaDTO.getValorAdoptado() );
		area.setValorDepreciacion( areaDTO.getValorDepreciacion() );
		area.setValorFinal( areaDTO.getValorFinal() );
		area.setValorConstruccion( areaDTO.getValorConstruccion() );
		area.setEdad( areaDTO.getEdad() );
		area.setVidaUtil( areaDTO.getVidaUtil() );
		area.setValorReposicionFinal( areaDTO.getValorReposicionFinal() ); 
		return area;
	}

	public void actualizarArea(Long areaId, AreaDTO areaDTO) throws AreaNotFoundException, AvaluoNotFoundException {
		Area area = areaRepository.findOne(areaId);
		if (area == null) {
			throw new AreaNotFoundException();
		}
		area.setNombre(areaDTO.getNombre());
		area.setDescripcion(areaDTO.getDescripcion());
		if (areaDTO.getDescripcionNumerica() != null) {
			area.setDescripcionNumerica(areaDTO.getDescripcionNumerica()
					.getKey());
		}
		area.setArea(areaDTO.getArea());
		if (areaDTO.getUnidadDeMedida() != null) {
			area.setUnidadDeMedida(areaDTO.getUnidadDeMedida().getKey());
		}
		area.setValorUnitario(areaDTO.getValorUnitario());
		area.setValorTotal(areaDTO.getValorTotal());
		area.setCoeficienteCopropiedad(areaDTO.getCoeficienteCopropiedad());
		area.setAreaEnMetros(areaDTO.getAreaEnMetros());
		area.setPorcentaje(areaDTO.getPorcentaje());
		area.setPorcentajeValorProporcionalTerreno(areaDTO.getPorcentajeValorProporcionalTerreno());
		area.setPorcentajeValorProporcionalConstruccion(areaDTO.getPorcentajeValorProporcionalConstruccion());
		area.setValorReposicion(areaDTO.getValorReposicion());
		area.setCostoTotalReposicion(areaDTO.getCostoTotalReposicion());
		area.setValorRazonable(areaDTO.getValorComercial());
		area.setValorResidualConstruccion(areaDTO.getValorResidualConstruccion());
		if (areaDTO.getTipoArea() != null) {
			area.setTipoArea(areaDTO.getTipoArea().getKey());
		}
		Avaluo avaluo = avaluoRepository.findOne(areaDTO.getAvaluoId());
		if (avaluo == null) {
			throw new AvaluoNotFoundException();
		}
		area.setAvaluo(avaluo);
		
		area.setDepreciacion( areaDTO.getDepreciacion() );
		area.setCalificacion( areaDTO.getCalificacion() );
		area.setCostoReposicion( areaDTO.getCostoReposicion() );
		area.setValorAdoptado( areaDTO.getValorAdoptado() );
		area.setValorDepreciacion( areaDTO.getValorDepreciacion() );
		area.setValorFinal( areaDTO.getValorFinal() );
		area.setValorConstruccion( areaDTO.getValorConstruccion() );
		area.setEdad( areaDTO.getEdad() );
		area.setVidaUtil( areaDTO.getVidaUtil() );
		area.setValorReposicionFinal( areaDTO.getValorReposicionFinal() ); 

	}
	
	public AreaDTO escribirDTO(Area area) {
		if (area.getClass().equals(Area.class)) {
			return escribirDTO(area, new AreaDTO());
		}else if (area.getClass().equals(AreaConstruccion.class)) {
			return escribirDTO((AreaConstruccion)area);
		}
		return null;
	}

	private AreaDTO escribirDTO(Area area, AreaDTO areaDTO) {
		areaDTO.setId(area.getId());
		areaDTO.setNombre(area.getNombre());
		areaDTO.setDescripcion(area.getDescripcion());
		if (area.getDescripcionNumerica() != null && area.getDescripcionNumerica() > 0) {
			if (area.getDescripcionNumerica() < 5 || area.getDescripcionNumerica() == 7) {
				areaDTO.setDescripcionNumerica(DescripcionAreaPH.fromKey(area
						.getDescripcionNumerica()));
			}else{
				areaDTO.setDescripcionNumerica(DescripcionAreaNoPH.fromKey(area
						.getDescripcionNumerica()));
			}
		}
		areaDTO.setArea(area.getArea());
		if (area.getUnidadDeMedida() != null && area.getUnidadDeMedida() > 0) {
			areaDTO.setUnidadDeMedida(UnidadDeMedida.fromKey(area
					.getUnidadDeMedida()));
		}
		areaDTO.setValorUnitario(area.getValorUnitario());
		areaDTO.setValorTotal(area.getValorTotal());
		areaDTO.setCoeficienteCopropiedad(area.getCoeficienteCopropiedad());
		areaDTO.setAreaEnMetros(area.getAreaEnMetros());
		areaDTO.setPorcentaje(area.getPorcentaje());
		areaDTO.setPorcentajeValorProporcionalTerreno(area.getPorcentajeValorProporcionalTerreno());
		areaDTO.setPorcentajeValorProporcionalConstruccion(area.getPorcentajeValorProporcionalConstruccion());
		areaDTO.setValorReposicion(area.getValorReposicion());
		areaDTO.setCostoTotalReposicion(area.getCostoTotalReposicion());
		areaDTO.setValorComercial(area.getValorRazonable());
		areaDTO.setValorResidualConstruccion(area.getValorResidualConstruccion());
		if (area.getTipoArea() != null) {
			areaDTO.setTipoArea(TipoArea.fromKey(area.getTipoArea()));
		}
		areaDTO.setAvaluoId(area.getAvaluo().getId());
		
		areaDTO.setDepreciacion( area.getDepreciacion() );
		areaDTO.setCalificacion( area.getCalificacion() );
		areaDTO.setCostoReposicion( area.getCostoReposicion() );
		areaDTO.setValorAdoptado( area.getValorAdoptado() );
		areaDTO.setValorDepreciacion( area.getValorDepreciacion() );
		areaDTO.setValorFinal( area.getValorFinal() );
		areaDTO.setValorConstruccion( area.getValorConstruccion() );
		areaDTO.setEdad( area.getEdad() );
		areaDTO.setVidaUtil( area.getVidaUtil() );
		areaDTO.setValorReposicionFinal( area.getValorReposicionFinal() );

		return areaDTO;
	}
	
	public List<AreaDTO> escribirListaDTO(List<? extends Area> areas) {
		List<AreaDTO> areaDTOs = new ArrayList<AreaDTO>();
		for (Area area:areas) {
			if (area.getClass().equals(Area.class)) {
				areaDTOs.add(escribirDTO(area, new AreaDTO()));
			}else if (area.getClass().equals(AreaConstruccion.class)) {
				areaDTOs.add(escribirDTO(area, new AreaConstruccionDTO()));
			}
		}
		return areaDTOs;
	}
	
	public AreaConstruccion crearAreaConstruccion(AreaConstruccionDTO areaConstruccionDTO) throws AvaluoNotFoundException {
		AreaConstruccion areaConstruccion = new AreaConstruccion();
		crearArea(areaConstruccionDTO, areaConstruccion);
		areaConstruccion.setTipoDeConstruccion(areaConstruccionDTO.getTipoDeConstruccion());
		areaConstruccion.setClaseDeInmueble(areaConstruccionDTO.getClaseDeInmueble());
		areaConstruccion.setNumeroDeSotanos(areaConstruccionDTO.getNumeroDeSotanos());
		areaConstruccion.setObservacionesNumeroDeSotanos(areaConstruccionDTO.getObservacionesNumeroDeSotanos());
		areaConstruccion.setUsoDelInmueble(areaConstruccionDTO.getUsoDelInmueble());
		areaConstruccion.setEstadoDeLaConstruccion(areaConstruccionDTO.getEstadoDeLaConstruccion());
		areaConstruccion.setObservacionesEstadoDeLaConstruccion(areaConstruccionDTO.getObservacionesEstadoDeLaConstruccion());
		areaConstruccion.setEstadoDeConservacion(areaConstruccionDTO.getEstadoDeConservacion());
		areaConstruccion.setEstadoConservacionConstruccion(areaConstruccionDTO.getEstadoConservacionConstruccion());
		areaConstruccion.setObservacionesEstadoDeLaConservacion(areaConstruccionDTO.getObservacionesEstadoDeLaConservacion());
		areaConstruccion.setPorcentajeAvance(areaConstruccionDTO.getPorcentajeAvance());
		areaConstruccion.setNumeroDePisos(areaConstruccionDTO.getNumeroDePisos());
		areaConstruccion.setDescripcionNumeroDePisos(areaConstruccionDTO.getDescripcionNumeroDePisos());
		areaConstruccion.setEstadoDeObra(areaConstruccionDTO.getEstadoDeObra().getKey());
		areaConstruccion.setDescripcionEstadoDeObra(areaConstruccionDTO.getDescripcionEstadoDeObra());
		areaConstruccion.setAnoDeConstruccion(areaConstruccionDTO.getAnoDeConstruccion());
		areaConstruccion.setObseracionesAnoDeConstruccion(areaConstruccionDTO.getObseracionesAnoDeConstruccion());
		areaConstruccion.setVetustez(areaConstruccionDTO.getVetustez());
		areaConstruccion.setDescripcionVetustez(areaConstruccionDTO.getDescripcionVetustez());
		areaConstruccion.setPisos(areaConstruccionDTO.getPisos());
		areaConstruccion.setDescripcionPisos(areaConstruccionDTO.getDescripcionPisos());
		areaConstruccion.setEstructura(areaConstruccionDTO.getEstructura());
		areaConstruccion.setDescripcionEstructura(areaConstruccionDTO.getDescripcionEstructura());
		areaConstruccion.setReparados(areaConstruccionDTO.getReparados());
		areaConstruccion.setDescripcionReparados(areaConstruccionDTO.getDescripcionReparados());
		areaConstruccion.setCubierta(areaConstruccionDTO.getCubierta());
		areaConstruccion.setDescripcionCubierta(areaConstruccionDTO.getDescripcionCubierta());
		areaConstruccion.setFachada(areaConstruccionDTO.getFachada());
		areaConstruccion.setDescripcionFachada(areaConstruccionDTO.getDescripcionFachada());
		areaConstruccion.setTipoDeFachada(areaConstruccionDTO.getTipoDeFachada());
		areaConstruccion.setDescripcionTipoDeFachada(areaConstruccionDTO.getDescripcionTipoDeFachada());
		areaConstruccion.setEstructuraReforzada(areaConstruccionDTO.getEstructuraReforzada());
		areaConstruccion.setDescripcionEstructuraReforzada(areaConstruccionDTO.getDescripcionEstructuraReforzada());
		areaConstruccion.setDanosPrevios(areaConstruccionDTO.getDanosPrevios());
		areaConstruccion.setDescripcionDanosPrevios(areaConstruccionDTO.getDescripcionDanosPrevios());
		areaConstruccion.setMaterialDeConstruccion(areaConstruccionDTO.getMaterialDeConstruccion());
		areaConstruccion.setDescripcionMaterialDeConstruccion(areaConstruccionDTO.getDescripcionMaterialDeConstruccion());
		areaConstruccion.setVentaneria(areaConstruccionDTO.getVentaneria());
		areaConstruccion.setDescripcionVentaneria(areaConstruccionDTO.getDescripcionVentaneria());
		areaConstruccion.setIluminacion(areaConstruccionDTO.getIluminacion().getKey());
		areaConstruccion.setDescripcionIluminacion(areaConstruccionDTO.getDescripcionIluminacion());
		areaConstruccion.setVentilacion(areaConstruccionDTO.getVentilacion().getKey());
		areaConstruccion.setDescripcionVentilacion(areaConstruccionDTO.getDescripcionVentilacion());
		areaConstruccion.setIrregularidadPlanta(areaConstruccionDTO.getIrregularidadPlanta());
		areaConstruccion.setDescripcionIrregularidadDePlanta(areaConstruccionDTO.getDescripcionIrregularidadDePlanta());
		areaConstruccion.setIrregularidadAltura(areaConstruccionDTO.getIrregularidadAltura());
		areaConstruccion.setDescripcionIrregularidadAltura(areaConstruccionDTO.getDescripcionIrregularidadAltura());
		areaConstruccion.setSalas(areaConstruccionDTO.getSalas());
		areaConstruccion.setComedores(areaConstruccionDTO.getComedores());
		areaConstruccion.setEstudios(areaConstruccionDTO.getEstudios());
		areaConstruccion.setJardines(areaConstruccionDTO.getJardines());
		areaConstruccion.setBanoSocial(areaConstruccionDTO.getBanoSocial());
		areaConstruccion.setEstarHabitacion(areaConstruccionDTO.getEstarHabitacion());
		areaConstruccion.setHabitaciones(areaConstruccionDTO.getHabitaciones());
		areaConstruccion.setDepositos(areaConstruccionDTO.getDepositos());
		areaConstruccion.setBanosPrivados(areaConstruccionDTO.getBanosPrivados());
		areaConstruccion.setCocinas(areaConstruccionDTO.getCocinas());
		areaConstruccion.setCuartoDeServicio(areaConstruccionDTO.getCuartoDeServicio());
		areaConstruccion.setOficinas(areaConstruccionDTO.getOficinas());
		areaConstruccion.setBanoServicio(areaConstruccionDTO.getBanoServicio());
		areaConstruccion.setPatioInterno(areaConstruccionDTO.getPatioInterno());
		areaConstruccion.setTerrazas(areaConstruccionDTO.getTerrazas());
		areaConstruccion.setBodegas(areaConstruccionDTO.getBodegas());
		areaConstruccion.setZonaDeRopas(areaConstruccionDTO.getZonaDeRopas());
		areaConstruccion.setBalcones(areaConstruccionDTO.getBalcones());
		areaConstruccion.setCloseths(areaConstruccionDTO.getCloseths());
		areaConstruccion.setLocales(areaConstruccionDTO.getLocales());
		areaConstruccion.setZonasVerdesPrivadas(areaConstruccionDTO.getZonasVerdesPrivadas());
		areaConstruccion.setObservacionesDependencias(areaConstruccionDTO.getObservacionesDependencias());
		areaConstruccion.setEstadoAcabadosPisos(areaConstruccionDTO.getEstadoAcabadosPisos().getKey());
		areaConstruccion.setEstadoAcabadosMuros(areaConstruccionDTO.getEstadoAcabadosMuros().getKey());
		areaConstruccion.setEstadoAcabadosTechos(areaConstruccionDTO.getEstadoAcabadosTechos().getKey());
		areaConstruccion.setEstadoAcabadosCarpinteriaMetal(areaConstruccionDTO.getEstadoAcabadosCarpinteriaMetal().getKey());
		areaConstruccion.setEstadoAcabadosCarpinteriaMadera(areaConstruccionDTO.getEstadoAcabadosCarpinteriaMadera().getKey());
		areaConstruccion.setEstadoAcabadosBanos(areaConstruccionDTO.getEstadoAcabadosBanos().getKey());
		areaConstruccion.setEstadoAcabadosCocina(areaConstruccionDTO.getEstadoAcabadosCocina().getKey());
		areaConstruccion.setCalidadAcabadosPisos(areaConstruccionDTO.getCalidadAcabadosPisos());
		areaConstruccion.setCalidadAcabadosMuros(areaConstruccionDTO.getCalidadAcabadosMuros());
		areaConstruccion.setCalidadAcabadosTechos(areaConstruccionDTO.getCalidadAcabadosTechos());
		areaConstruccion.setCalidadAcabadosCarpinteriaMetal(areaConstruccionDTO.getCalidadAcabadosCarpinteriaMetal());
		areaConstruccion.setCalidadAcabadosCarpinteriaMadera(areaConstruccionDTO.getCalidadAcabadosCarpinteriaMadera());
		areaConstruccion.setCalidadAcabadosBanos(areaConstruccionDTO.getCalidadAcabadosBanos());
		areaConstruccion.setCalidadAcabadosCocina(areaConstruccionDTO.getCalidadAcabadosCocina());
		areaConstruccion.setObservacionesAcabados(areaConstruccionDTO.getObservacionesAcabados());
		return areaConstruccion;
	}

	public AreaConstruccionDTO escribirDTO(AreaConstruccion areaConstruccion) {
		AreaConstruccionDTO areaConstruccionDTO = new AreaConstruccionDTO();
		escribirDTO(areaConstruccion, areaConstruccionDTO);
		areaConstruccionDTO.setAvaluoId(areaConstruccion.getAvaluo().getId());
		areaConstruccionDTO.setId(areaConstruccion.getId());
		areaConstruccionDTO.setTipoDeConstruccion(areaConstruccion.getTipoDeConstruccion());
		areaConstruccionDTO.setClaseDeInmueble(areaConstruccion.getClaseDeInmueble());
		areaConstruccionDTO.setNumeroDeSotanos(areaConstruccion.getNumeroDeSotanos());
		areaConstruccionDTO.setObservacionesNumeroDeSotanos(areaConstruccion.getObservacionesNumeroDeSotanos());
		areaConstruccionDTO.setUsoDelInmueble(areaConstruccion.getUsoDelInmueble());
		areaConstruccionDTO.setEstadoDeLaConstruccion(areaConstruccion.getEstadoDeLaConstruccion());
		areaConstruccionDTO.setObservacionesEstadoDeLaConstruccion(areaConstruccion.getObservacionesEstadoDeLaConstruccion());
		areaConstruccionDTO.setEstadoDeConservacion(areaConstruccion.getEstadoDeConservacion());
		areaConstruccionDTO.setEstadoConservacionConstruccion(areaConstruccion.getEstadoConservacionConstruccion());
		areaConstruccionDTO.setObservacionesEstadoDeLaConservacion(areaConstruccion.getObservacionesEstadoDeLaConservacion());
		areaConstruccionDTO.setPorcentajeAvance(areaConstruccion.getPorcentajeAvance());
		areaConstruccionDTO.setNumeroDePisos(areaConstruccion.getNumeroDePisos());
		areaConstruccionDTO.setDescripcionNumeroDePisos(areaConstruccion.getDescripcionNumeroDePisos());
		if (areaConstruccion
					.getEstadoDeObra() != null) {
			areaConstruccionDTO.setEstadoDeObra(MBR.fromKey(areaConstruccion
					.getEstadoDeObra()));
		}
		areaConstruccionDTO.setDescripcionEstadoDeObra(areaConstruccion.getDescripcionEstadoDeObra());
		areaConstruccionDTO.setAnoDeConstruccion(areaConstruccion.getAnoDeConstruccion());
		areaConstruccionDTO.setObseracionesAnoDeConstruccion(areaConstruccion.getObseracionesAnoDeConstruccion());
		areaConstruccionDTO.setVetustez(areaConstruccion.getVetustez());
		areaConstruccionDTO.setDescripcionVetustez(areaConstruccion.getDescripcionVetustez());
		areaConstruccionDTO.setPisos(areaConstruccion.getPisos());
		areaConstruccionDTO.setDescripcionPisos(areaConstruccion.getDescripcionPisos());
		areaConstruccionDTO.setEstructura(areaConstruccion.getEstructura());
		areaConstruccionDTO.setDescripcionEstructura(areaConstruccion.getDescripcionEstructura());
		areaConstruccionDTO.setReparados(areaConstruccion.getReparados());
		areaConstruccionDTO.setDescripcionReparados(areaConstruccion.getDescripcionReparados());
		areaConstruccionDTO.setCubierta(areaConstruccion.getCubierta());
		areaConstruccionDTO.setDescripcionCubierta(areaConstruccion.getDescripcionCubierta());
		areaConstruccionDTO.setFachada(areaConstruccion.getFachada());
		areaConstruccionDTO.setDescripcionFachada(areaConstruccion.getDescripcionFachada());
		areaConstruccionDTO.setTipoDeFachada(areaConstruccion.getTipoDeFachada());
		areaConstruccionDTO.setDescripcionTipoDeFachada(areaConstruccion.getDescripcionTipoDeFachada());
		areaConstruccionDTO.setEstructuraReforzada(areaConstruccion.getEstructuraReforzada());
		areaConstruccionDTO.setDescripcionEstructuraReforzada(areaConstruccion.getDescripcionEstructuraReforzada());
		areaConstruccionDTO.setDanosPrevios(areaConstruccion.getDanosPrevios());
		areaConstruccionDTO.setDescripcionDanosPrevios(areaConstruccion.getDescripcionDanosPrevios());
		areaConstruccionDTO.setMaterialDeConstruccion(areaConstruccion.getMaterialDeConstruccion());
		areaConstruccionDTO.setDescripcionMaterialDeConstruccion(areaConstruccion.getDescripcionMaterialDeConstruccion());
		areaConstruccionDTO.setVentaneria(areaConstruccion.getVentaneria());
		areaConstruccionDTO.setDescripcionVentaneria(areaConstruccion.getDescripcionVentaneria());
		if (areaConstruccion.getIluminacion() != null) {
			areaConstruccionDTO.setIluminacion(MBR.fromKey(areaConstruccion
					.getIluminacion()));
		}
		areaConstruccionDTO.setDescripcionIluminacion(areaConstruccion.getDescripcionIluminacion());
		if (areaConstruccion.getVentilacion() != null) {
			areaConstruccionDTO.setVentilacion(MBR.fromKey(areaConstruccion
					.getVentilacion()));
		}
		areaConstruccionDTO.setDescripcionVentilacion(areaConstruccion.getDescripcionVentilacion());
		areaConstruccionDTO.setIrregularidadPlanta(areaConstruccion.getIrregularidadPlanta());
		areaConstruccionDTO.setDescripcionIrregularidadDePlanta(areaConstruccion.getDescripcionIrregularidadDePlanta());
		areaConstruccionDTO.setIrregularidadAltura(areaConstruccion.getIrregularidadAltura());
		areaConstruccionDTO.setDescripcionIrregularidadAltura(areaConstruccion.getDescripcionIrregularidadAltura());
		areaConstruccionDTO.setSalas(areaConstruccion.getSalas());
		areaConstruccionDTO.setComedores(areaConstruccion.getComedores());
		areaConstruccionDTO.setEstudios(areaConstruccion.getEstudios());
		areaConstruccionDTO.setJardines(areaConstruccion.getJardines());
		areaConstruccionDTO.setBanoSocial(areaConstruccion.getBanoSocial());
		areaConstruccionDTO.setEstarHabitacion(areaConstruccion.getEstarHabitacion());
		areaConstruccionDTO.setHabitaciones(areaConstruccion.getHabitaciones());
		areaConstruccionDTO.setDepositos(areaConstruccion.getDepositos());
		areaConstruccionDTO.setBanosPrivados(areaConstruccion.getBanosPrivados());
		areaConstruccionDTO.setCocinas(areaConstruccion.getCocinas());
		areaConstruccionDTO.setCuartoDeServicio(areaConstruccion.getCuartoDeServicio());
		areaConstruccionDTO.setOficinas(areaConstruccion.getOficinas());
		areaConstruccionDTO.setBanoServicio(areaConstruccion.getBanoServicio());
		areaConstruccionDTO.setPatioInterno(areaConstruccion.getPatioInterno());
		areaConstruccionDTO.setTerrazas(areaConstruccion.getTerrazas());
		areaConstruccionDTO.setBodegas(areaConstruccion.getBodegas());
		areaConstruccionDTO.setZonaDeRopas(areaConstruccion.getZonaDeRopas());
		areaConstruccionDTO.setBalcones(areaConstruccion.getBalcones());
		areaConstruccionDTO.setCloseths(areaConstruccion.getCloseths());
		areaConstruccionDTO.setLocales(areaConstruccion.getLocales());
		areaConstruccionDTO.setZonasVerdesPrivadas(areaConstruccion.getZonasVerdesPrivadas());
		areaConstruccionDTO.setObservacionesDependencias(areaConstruccion.getObservacionesDependencias());
		if (areaConstruccion.getEstadoAcabadosPisos() != null) {
			areaConstruccionDTO.setEstadoAcabadosPisos(MBR
					.fromKey(areaConstruccion.getEstadoAcabadosPisos()));
		}
		if (areaConstruccion.getEstadoAcabadosMuros() != null) {
			areaConstruccionDTO.setEstadoAcabadosMuros(MBR
					.fromKey(areaConstruccion.getEstadoAcabadosMuros()));
		}
		if (areaConstruccion.getEstadoAcabadosTechos() != null) {
			areaConstruccionDTO.setEstadoAcabadosTechos(MBR
					.fromKey(areaConstruccion.getEstadoAcabadosTechos()));
		}
		if (areaConstruccion.getEstadoAcabadosCarpinteriaMetal() != null) {
			areaConstruccionDTO.setEstadoAcabadosCarpinteriaMetal(MBR
					.fromKey(areaConstruccion.getEstadoAcabadosCarpinteriaMetal()));
		}
		if (areaConstruccion.getEstadoAcabadosCarpinteriaMadera() != null) {
			areaConstruccionDTO.setEstadoAcabadosCarpinteriaMadera(MBR
					.fromKey(areaConstruccion.getEstadoAcabadosCarpinteriaMadera()));
		}
		if (areaConstruccion.getEstadoAcabadosBanos() != null) {
			areaConstruccionDTO.setEstadoAcabadosBanos(MBR
					.fromKey(areaConstruccion.getEstadoAcabadosBanos()));
		}
		if (areaConstruccion.getEstadoAcabadosCocina() != null) {
			areaConstruccionDTO.setEstadoAcabadosCocina(MBR
					.fromKey(areaConstruccion.getEstadoAcabadosCocina()));
		}
		areaConstruccionDTO.setCalidadAcabadosPisos(areaConstruccion.getCalidadAcabadosPisos());
		areaConstruccionDTO.setCalidadAcabadosMuros(areaConstruccion.getCalidadAcabadosMuros());
		areaConstruccionDTO.setCalidadAcabadosTechos(areaConstruccion.getCalidadAcabadosTechos());
		areaConstruccionDTO.setCalidadAcabadosCarpinteriaMetal(areaConstruccion.getCalidadAcabadosCarpinteriaMetal());
		areaConstruccionDTO.setCalidadAcabadosCarpinteriaMadera(areaConstruccion.getCalidadAcabadosCarpinteriaMadera());
		areaConstruccionDTO.setCalidadAcabadosBanos(areaConstruccion.getCalidadAcabadosBanos());
		areaConstruccionDTO.setCalidadAcabadosCocina(areaConstruccion.getCalidadAcabadosCocina());
		areaConstruccionDTO.setObservacionesAcabados(areaConstruccion.getObservacionesAcabados());
		return areaConstruccionDTO;
	}	

	public Set<AreaConstruccionDTO> escribirListaDTO(Set<AreaConstruccion> areasConstruccion) {
		Set<AreaConstruccionDTO> areasConstruccionDTO = new HashSet<AreaConstruccionDTO>();
		for (AreaConstruccion areaConstruccion:areasConstruccion) {
			areasConstruccionDTO.add(escribirDTO(areaConstruccion));
		}
		return areasConstruccionDTO;
	}

	public void actualizarAreaConstruccion(Long id, AreaConstruccionDTO actualizado) throws AvaluoNotFoundException {
		AreaConstruccion areaConstruccion = areaConstruccionRepository.findOne(id);
		AvaluoComercial avaluoComercial = avaluoComercialRepository.findOne(actualizado.getAvaluoId());
		if (avaluoComercial == null) {
			throw new AvaluoNotFoundException();
		}
		areaConstruccion.setAvaluo(avaluoComercial);
		areaConstruccion.setTipoDeConstruccion(actualizado.getTipoDeConstruccion());
		areaConstruccion.setClaseDeInmueble(actualizado.getClaseDeInmueble());
		areaConstruccion.setNumeroDeSotanos(actualizado.getNumeroDeSotanos());
		areaConstruccion.setObservacionesNumeroDeSotanos(actualizado.getObservacionesNumeroDeSotanos());
		areaConstruccion.setUsoDelInmueble(actualizado.getUsoDelInmueble());
		areaConstruccion.setEstadoDeLaConstruccion(actualizado.getEstadoDeLaConstruccion());
		areaConstruccion.setObservacionesEstadoDeLaConstruccion(actualizado.getObservacionesEstadoDeLaConstruccion());
		areaConstruccion.setEstadoDeConservacion(actualizado.getEstadoDeConservacion());
		areaConstruccion.setEstadoConservacionConstruccion(actualizado.getEstadoConservacionConstruccion());
		areaConstruccion.setObservacionesEstadoDeLaConservacion(actualizado.getObservacionesEstadoDeLaConservacion());
		areaConstruccion.setPorcentajeAvance(actualizado.getPorcentajeAvance());
		areaConstruccion.setNumeroDePisos(actualizado.getNumeroDePisos());
		areaConstruccion.setDescripcionNumeroDePisos(actualizado.getDescripcionNumeroDePisos());
		areaConstruccion.setEstadoDeObra(actualizado.getEstadoDeObra().getKey());
		areaConstruccion.setDescripcionEstadoDeObra(actualizado.getDescripcionEstadoDeObra());
		areaConstruccion.setAnoDeConstruccion(actualizado.getAnoDeConstruccion());
		areaConstruccion.setObseracionesAnoDeConstruccion(actualizado.getObseracionesAnoDeConstruccion());
		areaConstruccion.setVetustez(actualizado.getVetustez());
		areaConstruccion.setDescripcionVetustez(actualizado.getDescripcionVetustez());
		areaConstruccion.setPisos(actualizado.getPisos());
		areaConstruccion.setDescripcionPisos(actualizado.getDescripcionPisos());
		areaConstruccion.setEstructura(actualizado.getEstructura());
		areaConstruccion.setDescripcionEstructura(actualizado.getDescripcionEstructura());
		areaConstruccion.setReparados(actualizado.getReparados());
		areaConstruccion.setDescripcionReparados(actualizado.getDescripcionReparados());
		areaConstruccion.setCubierta(actualizado.getCubierta());
		areaConstruccion.setDescripcionCubierta(actualizado.getDescripcionCubierta());
		areaConstruccion.setFachada(actualizado.getFachada());
		areaConstruccion.setDescripcionFachada(actualizado.getDescripcionFachada());
		areaConstruccion.setTipoDeFachada(actualizado.getTipoDeFachada());
		areaConstruccion.setDescripcionTipoDeFachada(actualizado.getDescripcionTipoDeFachada());
		areaConstruccion.setEstructuraReforzada(actualizado.getEstructuraReforzada());
		areaConstruccion.setDescripcionEstructuraReforzada(actualizado.getDescripcionEstructuraReforzada());
		areaConstruccion.setDanosPrevios(actualizado.getDanosPrevios());
		areaConstruccion.setDescripcionDanosPrevios(actualizado.getDescripcionDanosPrevios());
		areaConstruccion.setMaterialDeConstruccion(actualizado.getMaterialDeConstruccion());
		areaConstruccion.setDescripcionMaterialDeConstruccion(actualizado.getDescripcionMaterialDeConstruccion());
		areaConstruccion.setVentaneria(actualizado.getVentaneria());
		areaConstruccion.setDescripcionVentaneria(actualizado.getDescripcionVentaneria());
		areaConstruccion.setIluminacion(actualizado.getIluminacion().getKey());
		areaConstruccion.setDescripcionIluminacion(actualizado.getDescripcionIluminacion());
		areaConstruccion.setVentilacion(actualizado.getVentilacion().getKey());
		areaConstruccion.setDescripcionVentilacion(actualizado.getDescripcionVentilacion());
		areaConstruccion.setIrregularidadPlanta(actualizado.getIrregularidadPlanta());
		areaConstruccion.setDescripcionIrregularidadDePlanta(actualizado.getDescripcionIrregularidadDePlanta());
		areaConstruccion.setIrregularidadAltura(actualizado.getIrregularidadAltura());
		areaConstruccion.setDescripcionIrregularidadAltura(actualizado.getDescripcionIrregularidadAltura());
		areaConstruccion.setSalas(actualizado.getSalas());
		areaConstruccion.setComedores(actualizado.getComedores());
		areaConstruccion.setEstudios(actualizado.getEstudios());
		areaConstruccion.setJardines(actualizado.getJardines());
		areaConstruccion.setBanoSocial(actualizado.getBanoSocial());
		areaConstruccion.setEstarHabitacion(actualizado.getEstarHabitacion());
		areaConstruccion.setHabitaciones(actualizado.getHabitaciones());
		areaConstruccion.setDepositos(actualizado.getDepositos());
		areaConstruccion.setBanosPrivados(actualizado.getBanosPrivados());
		areaConstruccion.setCocinas(actualizado.getCocinas());
		areaConstruccion.setCuartoDeServicio(actualizado.getCuartoDeServicio());
		areaConstruccion.setOficinas(actualizado.getOficinas());
		areaConstruccion.setBanoServicio(actualizado.getBanoServicio());
		areaConstruccion.setPatioInterno(actualizado.getPatioInterno());
		areaConstruccion.setTerrazas(actualizado.getTerrazas());
		areaConstruccion.setBodegas(actualizado.getBodegas());
		areaConstruccion.setZonaDeRopas(actualizado.getZonaDeRopas());
		areaConstruccion.setBalcones(actualizado.getBalcones());
		areaConstruccion.setCloseths(actualizado.getCloseths());
		areaConstruccion.setLocales(actualizado.getLocales());
		areaConstruccion.setZonasVerdesPrivadas(actualizado.getZonasVerdesPrivadas());
		areaConstruccion.setObservacionesDependencias(actualizado.getObservacionesDependencias());
		areaConstruccion.setEstadoAcabadosPisos(actualizado.getEstadoAcabadosPisos().getKey());
		areaConstruccion.setEstadoAcabadosMuros(actualizado.getEstadoAcabadosMuros().getKey());
		areaConstruccion.setEstadoAcabadosTechos(actualizado.getEstadoAcabadosTechos().getKey());
		areaConstruccion.setEstadoAcabadosCarpinteriaMetal(actualizado.getEstadoAcabadosCarpinteriaMetal().getKey());
		areaConstruccion.setEstadoAcabadosCarpinteriaMadera(actualizado.getEstadoAcabadosCarpinteriaMadera().getKey());
		areaConstruccion.setEstadoAcabadosBanos(actualizado.getEstadoAcabadosBanos().getKey());
		areaConstruccion.setEstadoAcabadosCocina(actualizado.getEstadoAcabadosCocina().getKey());
		areaConstruccion.setCalidadAcabadosPisos(actualizado.getCalidadAcabadosPisos());
		areaConstruccion.setCalidadAcabadosMuros(actualizado.getCalidadAcabadosMuros());
		areaConstruccion.setCalidadAcabadosTechos(actualizado.getCalidadAcabadosTechos());
		areaConstruccion.setCalidadAcabadosCarpinteriaMetal(actualizado.getCalidadAcabadosCarpinteriaMetal());
		areaConstruccion.setCalidadAcabadosCarpinteriaMadera(actualizado.getCalidadAcabadosCarpinteriaMadera());
		areaConstruccion.setCalidadAcabadosBanos(actualizado.getCalidadAcabadosBanos());
		areaConstruccion.setCalidadAcabadosCocina(actualizado.getCalidadAcabadosCocina());
		areaConstruccion.setObservacionesAcabados(actualizado.getObservacionesAcabados());
	}

}
