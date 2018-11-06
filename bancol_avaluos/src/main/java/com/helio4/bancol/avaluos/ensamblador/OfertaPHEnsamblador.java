package com.helio4.bancol.avaluos.ensamblador;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.MetodoValuacionDTO;
import com.helio4.bancol.avaluos.dto.OfertaPHDTO;
import com.helio4.bancol.avaluos.exception.OfertaNotFoundException;
import com.helio4.bancol.avaluos.modelo.MetodoValuacion;
import com.helio4.bancol.avaluos.modelo.OfertaPH;
import com.helio4.bancol.avaluos.persistencia.MetodoValuacionRepository;
import com.helio4.bancol.avaluos.persistencia.OfertaPHRepository;
import com.helio4.bancol.avaluos.servicio.datos.OfertaPHService;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.MetodoValuacionNotFoundException;

@Component
public class OfertaPHEnsamblador {
	
	private static Logger log = LoggerFactory
			.getLogger(OfertaPHEnsamblador.class);
		
	@Autowired
	OfertaPHService OfertaPHService;
	
	@Autowired
	private MetodoValuacionRepository metodoValuacionRepository;
	
	@Autowired
	private MetodoValuacionEnsamblador metodoValuacionEnsamblador;
	
	@Autowired
	private OfertaPHRepository ofertaPHRepository;
	
	@Autowired
	OfertaEnsamblador ofertaEnsamblador;
	
	/**
	 * Crea una OfertaDTO a partir del modelo:Oferta
	 * 
	 * @param Oferta a convertir
	 * @return OfertaDTO convertida.
	 * */
	public OfertaPHDTO escribirDTO(OfertaPH ofertaPH) {
		
		OfertaPHDTO  ofertaDTO = new OfertaPHDTO(ofertaPH);
		
		return ofertaDTO;
	}
	
	
	/**
	 * Crear una lista de OfertaDTO a partir de una lista de modelo: Oferta
	 * 
	 * @param
	 * @return
	 * */
	public List<OfertaPHDTO> escribirListaDTO( List<OfertaPH> ofertas){
		List<OfertaPHDTO> ofertasDTO = new ArrayList<>();
		for( OfertaPH oferta: ofertas){
			ofertasDTO.add( this.escribirDTO(oferta) );
		}
		return ofertasDTO;
	}
	/**
	 * Convierte una OfertaDTO a un modelo:Oferta
	 * @param OfertaDTO
	 * @return Oferta
	 * @throws MetodoValuacionNotFoundException 
	 * */
	public OfertaPH crearOfertaPH(OfertaPHDTO ofertaDTO, MetodoValuacionDTO metodoValuacionDTO) throws MetodoValuacionNotFoundException {
		
		OfertaPH ofertaPH = new OfertaPH();
		ofertaEnsamblador.crearOferta(ofertaPH,ofertaDTO, metodoValuacionDTO);
		
		llenarCamposOfertaDesdeDTOAEntidad(ofertaDTO, ofertaPH, metodoValuacionDTO);
		
		ofertaPH.setPiso(ofertaDTO.getPiso());
		ofertaPH.setArea(ofertaDTO.getArea());
		if(ofertaDTO.getTipoArea()!=null){
			ofertaPH.setTipoArea(ofertaDTO.getTipoArea().getKey());
		}
		ofertaPH.setGarajes(ofertaDTO.getGarajes());
		
		if(ofertaDTO.getEdificioConjunto()!=null){
			ofertaPH.setEdificioConjunto(ofertaDTO.getEdificioConjunto().getKey());
		}
		
		ofertaPH.setSuperficie(ofertaDTO.getSuperficie());
		ofertaPH.setPrecioUnitarioGaraje(ofertaDTO.getPrecioUnitarioGaraje());
		ofertaPH.setValorAdministracion(ofertaDTO.getValorAdministracion());
		ofertaPH.setAreaLibre(ofertaDTO.getAreaLibre());
		
		ofertaPH.setUbicacionPiso(ofertaDTO.getUbicacionPiso());
		
		ofertaPH.setValorMetroHomogenizadoSinGaraje(ofertaDTO.getValorMetroHomogenizadoSinGaraje());
		ofertaPH.setPrecioUnitarioAdministracionM2(ofertaDTO.getPrecioUnitarioAdministracionM2());
		ofertaPH.setValorM2SinGarajeNoHomogenizado(ofertaDTO.getValorM2SinGarajeNoHomogenizado());
		ofertaPH.setValorM2HomogenizadoSinGarajeYAreaLibre(ofertaDTO.getValorM2HomogenizadoSinGarajeYAreaLibre());
		
		return ofertaPH;
	}
	
	/**
	 * Setea los campos de un modelo con base al DTO.
	 * */
	public OfertaPH crearOfertaPH(OfertaPH ofertaPH,OfertaPHDTO ofertaDTO) {
		
		
		ofertaEnsamblador.crearOferta(ofertaPH, ofertaDTO);
		
		ofertaPH.setPiso(ofertaDTO.getPiso());
		ofertaPH.setArea(ofertaDTO.getArea());
		if(ofertaDTO.getTipoArea()!=null){
			ofertaPH.setTipoArea(ofertaDTO.getTipoArea().getKey());
		}
		ofertaPH.setGarajes(ofertaDTO.getGarajes());
		
		if(ofertaDTO.getEdificioConjunto()!=null){
			ofertaPH.setEdificioConjunto(ofertaDTO.getEdificioConjunto().getKey());
		}
		
		ofertaPH.setSuperficie(ofertaDTO.getSuperficie());
		ofertaPH.setPrecioUnitarioGaraje(ofertaDTO.getPrecioUnitarioGaraje());
		ofertaPH.setValorAdministracion(ofertaDTO.getValorAdministracion());
		ofertaPH.setAreaLibre(ofertaDTO.getAreaLibre());
		
		ofertaPH.setUbicacionPiso(ofertaDTO.getUbicacionPiso());
		
		ofertaPH.setValorMetroHomogenizadoSinGaraje(ofertaDTO.getValorMetroHomogenizadoSinGaraje());
		ofertaPH.setPrecioUnitarioAdministracionM2(ofertaDTO.getPrecioUnitarioAdministracionM2());
		ofertaPH.setValorM2SinGarajeNoHomogenizado(ofertaDTO.getValorM2SinGarajeNoHomogenizado());
		ofertaPH.setValorM2HomogenizadoSinGarajeYAreaLibre(ofertaDTO.getValorM2HomogenizadoSinGarajeYAreaLibre());
		
		return ofertaPH;
	}
	
	/**
	 * Realiza una conversion de List<OfertaDTO> a List<Oferta>
	 * @param List<OfertaDTO> lista de ofertas DTO a convertir.
	 * @return List<Oferta> lista de ofertas
	 * @throws MetodoValuacionNotFoundException 
	 * */
	public List<OfertaPH> crearLista( List<OfertaPHDTO> ofertasPHDTO, MetodoValuacionDTO metodoValuacionDTO ) throws MetodoValuacionNotFoundException{
		List<OfertaPH> ofertas = new ArrayList<>();
		for( OfertaPHDTO ofertaPHDTO : ofertasPHDTO ){
			ofertas.add(this.crearOfertaPH(ofertaPHDTO, metodoValuacionDTO));
		}
		return ofertas;
	}
	
	@Transactional(rollbackFor = OfertaNotFoundException.class)
	public void actualizarOfertas(List<OfertaPHDTO> ofertasPHDTO, MetodoValuacionDTO metodoValuacionDTO) {
		log.info( " ACTULIZANDO OFERTAS PH" );
		
		List<OfertaPHDTO> ofertasActuales = ofertaPHRepository.encontrarPorMetodoValuacion(metodoValuacionDTO.getId());
		
		if (ofertasPHDTO != null && !ofertasPHDTO.isEmpty()) {

			for (OfertaPHDTO ofertaPHDTO : ofertasPHDTO) {
				this.OfertaPHService.crear(ofertaPHDTO, metodoValuacionDTO);
				ofertasActuales.remove(ofertaPHDTO);
			}
			
			for (OfertaPHDTO ofertaPHDTO : ofertasActuales){
				try {
					this.OfertaPHService.eliminar(ofertaPHDTO.getId());
				} catch (OfertaNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	
	public void llenarCamposOfertaDesdeDTOAEntidad(OfertaPHDTO  ofertaDTO, OfertaPH ofertaPH, MetodoValuacionDTO metodoValuacionDTO){
		
		ofertaPH.setId( ofertaDTO.getId()  );
		ofertaPH.setAreaConstruida( ofertaDTO.getAreaConstruida() );
		ofertaPH.setAreaTerreno( ofertaDTO.getAreaTerreno() );
		ofertaPH.setBarrio( ofertaDTO.getBarrio() );
		ofertaPH.setCiudad( ofertaDTO.getCiudad() );
		ofertaPH.setCelular( ofertaDTO.getCelular() );
		ofertaPH.setConjunto( ofertaDTO.getConjunto() );
		ofertaPH.setConstruccion( ofertaDTO.getConstruccion() );
		ofertaPH.setEdadInmueble( ofertaDTO.getEdadInmueble() );
		ofertaPH.setEstadoConservacion( ofertaDTO.getEstadoConservacion() );
		ofertaPH.setGarajesDepositos( ofertaDTO.getGarajesDepositos() );
		ofertaPH.setObservaciones( ofertaDTO.getObservaciones() );
		ofertaPH.setPorcentajeDepurado( ofertaDTO.getPorcentajeDepurado() );
		ofertaPH.setTelefono( ofertaDTO.getTelefono() );
		ofertaPH.setTotalTerreno( ofertaDTO.getTotalTerreno() );
		ofertaPH.setValorDepurado( ofertaDTO.getValorDepurado()  );
		ofertaPH.setValorM2Construccion( ofertaDTO.getValorM2Construccion() );
		ofertaPH.setValorM2Terreno( ofertaDTO.getValorM2Terreno() );
		ofertaPH.setValorOferta( ofertaDTO.getValorOferta() );
		ofertaPH.setValorReposicion( ofertaDTO.getValorReposicion() );
		ofertaPH.setVidaUtil( ofertaDTO.getVidaUtil() );
		ofertaPH.setValorIntegralConstruccion( ofertaDTO.getValorIntegralConstruccion() );
		
		if( ofertaDTO.getMetodoValuacionId()!=null){
			MetodoValuacion metodo  = this.metodoValuacionRepository.findOne( ofertaDTO.getMetodoValuacionId() );
			if( metodo==null){
				try {
					metodo = metodoValuacionEnsamblador.crearMetodoValuacion(metodoValuacionDTO);
				} catch (AvaluoNotFoundException e) {
					e.printStackTrace();
				}
				//throw new MetodoValuacionNotFoundException();
			}
			ofertaPH.setMetodoValuacion(metodo);
		}
		
		ofertaPH.setLocalizacion(ofertaDTO.getLocalizacion());
		ofertaPH.setNombre(ofertaDTO.getNombre());
		ofertaPH.setFuente(ofertaDTO.getFuente());
		ofertaPH.setEdad(ofertaDTO.getEdad());
		
		if(ofertaDTO.getUbicacion()!=null){
			ofertaPH.setUbicacion(ofertaDTO.getUbicacion().getKey());
		}
		
		if(ofertaDTO.getAcabados()!=null){
			ofertaPH.setAcabados(ofertaDTO.getAcabados().getKey());
		}
		
		ofertaPH.setNegociacion(ofertaDTO.getNegociacion());
		ofertaPH.setEdadHomogenizada(ofertaDTO.getEdadHomogenizada());
		ofertaPH.setFactorHomogenizacion(ofertaDTO.getFactorHomogenizacion());
		
	}

}
