package com.helio4.bancol.avaluos.ensamblador;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.MetodoValuacionDTO;
import com.helio4.bancol.avaluos.dto.OfertaDTO;
import com.helio4.bancol.avaluos.dto.OfertaDTO.FactorHomologacion;
import com.helio4.bancol.avaluos.exception.OfertaNotFoundException;
import com.helio4.bancol.avaluos.modelo.MetodoValuacion;
import com.helio4.bancol.avaluos.modelo.Oferta;
import com.helio4.bancol.avaluos.modelo.OfertaPH;
import com.helio4.bancol.avaluos.persistencia.MetodoValuacionRepository;
import com.helio4.bancol.avaluos.persistencia.OfertaRepository;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.MetodoValuacionNotFoundException;
import com.helio4.bancol.avaluos.servicio.datos.OfertaService;

@Component
public class OfertaEnsamblador {
	
	private static Logger log = LoggerFactory
			.getLogger(OfertaEnsamblador.class);

	@Autowired
	private OfertaRepository ofertaRepository;
	
	@Autowired
	private OfertaService ofertaService;
	
	@Autowired
	private MetodoValuacionRepository metodoValuacionRepository;
	
	@Autowired
	private MetodoValuacionEnsamblador metodoValuacionEnsamblador;
	
	
	/**
	 * Crea una OfertaDTO a partir del modelo:Oferta
	 * 
	 * @param Oferta a convertir
	 * @return OfertaDTO convertida.
	 * */
	public OfertaDTO escribirDTO(Oferta oferta) {
		OfertaDTO  ofertaDTO = new OfertaDTO();
		ofertaDTO.setId( oferta.getId()  );
		ofertaDTO.setAreaConstruida( oferta.getAreaConstruida() );
		ofertaDTO.setAreaTerreno( oferta.getAreaTerreno() );
		ofertaDTO.setBarrio( oferta.getBarrio() );
		ofertaDTO.setCiudad( oferta.getCiudad() );
		ofertaDTO.setCelular( oferta.getCelular() );
		ofertaDTO.setConjunto( oferta.getConjunto() );
		ofertaDTO.setConstruccion( oferta.getConstruccion() );
		ofertaDTO.setEdadInmueble( oferta.getEdadInmueble() );
		ofertaDTO.setEstadoConservacion( oferta.getEstadoConservacion() );
		ofertaDTO.setGarajesDepositos( oferta.getGarajesDepositos() );
		ofertaDTO.setObservaciones( oferta.getObservaciones() );
		ofertaDTO.setPorcentajeDepurado( oferta.getPorcentajeDepurado() );
		ofertaDTO.setTelefono( oferta.getTelefono() );
		ofertaDTO.setTotalTerreno( oferta.getTotalTerreno() );
		ofertaDTO.setValorDepurado( oferta.getValorDepurado()  );
		ofertaDTO.setValorM2Construccion( oferta.getValorM2Construccion() );
		ofertaDTO.setValorM2Terreno( oferta.getValorM2Terreno() );
		ofertaDTO.setValorOferta( oferta.getValorOferta() );
		ofertaDTO.setValorReposicion( oferta.getValorReposicion() );
		ofertaDTO.setVidaUtil( oferta.getVidaUtil() );
		
		ofertaDTO.setValorIntegralConstruccion( oferta.getValorIntegralConstruccion() );
		
		if(oferta.getMetodoValuacion()!=null){
			ofertaDTO.setMetodoValuacionId( oferta.getMetodoValuacion().getId()  );
		}
		
		ofertaDTO.setLocalizacion(oferta.getLocalizacion());
		ofertaDTO.setNombre(oferta.getNombre());
		
		ofertaDTO.setEdad(oferta.getEdad());
		ofertaDTO.setUbicacion(oferta.getUbicacion());
		if(oferta.getUbicacion()!=null){
			ofertaDTO.setUbicacion(FactorHomologacion.fromKey(oferta.getUbicacion()));
		}
		ofertaDTO.setAcabados(oferta.getAcabados());
		if(oferta.getAcabados()!=null){
			ofertaDTO.setAcabados(FactorHomologacion.fromKey(oferta.getAcabados()));
		}
		
		ofertaDTO.setNegociacion(oferta.getNegociacion());
		ofertaDTO.setEdadHomogenizada(oferta.getEdadHomogenizada());
		ofertaDTO.setFactorHomogenizacion(oferta.getFactorHomogenizacion());
		
		return ofertaDTO;
	}
	
	
	/**
	 * Crear una lista de OfertaDTO a partir de una lista de modelo: Oferta
	 * 
	 * @param
	 * @return
	 * */
	public List<OfertaDTO> escribirListaDTO( List<Oferta> ofertas){
		List<OfertaDTO> ofertasDTO = new ArrayList<OfertaDTO>();
		for( Oferta oferta: ofertas){
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
	public Oferta crearOferta(Oferta oferta, OfertaDTO ofertaDTO, MetodoValuacionDTO metodoValuacionDTO) throws MetodoValuacionNotFoundException {
		
		oferta.setId(ofertaDTO.getId());
		ofertaDTO.setMetodoValuacionId(metodoValuacionDTO.getId());
		oferta.setAreaConstruida( ofertaDTO.getAreaConstruida() );
		oferta.setAreaTerreno( ofertaDTO.getAreaTerreno() );
		oferta.setCiudad( ofertaDTO.getCiudad() ); 
		oferta.setBarrio( ofertaDTO.getBarrio() );
		oferta.setCelular( ofertaDTO.getCelular() );
		oferta.setConjunto( ofertaDTO.getConjunto() );
		oferta.setConstruccion( ofertaDTO.getConstruccion() );
		oferta.setEdadInmueble( ofertaDTO.getEdadInmueble() );
		oferta.setEstadoConservacion( ofertaDTO.getEstadoConservacion() );
		oferta.setGarajesDepositos( ofertaDTO.getGarajesDepositos() );
		oferta.setValorIntegralConstruccion( ofertaDTO.getValorIntegralConstruccion() );

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
			oferta.setMetodoValuacion(metodo);
		}
		
		oferta.setObservaciones( ofertaDTO.getObservaciones() );
		oferta.setPorcentajeDepurado( ofertaDTO.getPorcentajeDepurado() );
		oferta.setTelefono( ofertaDTO.getTelefono() );
		oferta.setTotalTerreno( ofertaDTO.getTotalTerreno() );
		oferta.setValorDepurado( ofertaDTO.getValorDepurado() );
		oferta.setValorFinalDepurado( ofertaDTO.getValorFinalDepurado() );
		oferta.setValorM2Construccion( ofertaDTO.getValorM2Construccion() );
		oferta.setValorM2Terreno( ofertaDTO.getValorM2Terreno() );
		oferta.setValorOferta( ofertaDTO.getValorOferta() );
		oferta.setValorReposicion( ofertaDTO.getValorReposicion() );
		oferta.setVidaUtil( ofertaDTO.getVidaUtil() );
		
		
		///
		oferta.setLocalizacion(ofertaDTO.getLocalizacion());
		oferta.setNombre(ofertaDTO.getNombre());
		oferta.setFuente(ofertaDTO.getFuente());
		oferta.setEdad(ofertaDTO.getEdad());
		if(ofertaDTO.getUbicacion()!=null){
			oferta.setUbicacion(ofertaDTO.getUbicacion().getKey());
		}
		if(ofertaDTO.getAcabados()!=null){
			oferta.setAcabados(ofertaDTO.getAcabados().getKey());
		}
		
		oferta.setNegociacion(ofertaDTO.getNegociacion());
		oferta.setEdadHomogenizada(ofertaDTO.getEdadHomogenizada());
		oferta.setFactorHomogenizacion(ofertaDTO.getFactorHomogenizacion());
		
		return oferta;
	}
	
	/**
	 * Setea los campos de un modelo con base al DTO.
	 * */
	public Oferta crearOferta(Oferta oferta,OfertaDTO ofertaDTO) {
		oferta.setAreaConstruida( ofertaDTO.getAreaConstruida() );
		oferta.setAreaTerreno( ofertaDTO.getAreaTerreno() );
		oferta.setCiudad( ofertaDTO.getCiudad() ); 
		oferta.setBarrio( ofertaDTO.getBarrio() );
		oferta.setCelular( ofertaDTO.getCelular() );
		oferta.setConjunto( ofertaDTO.getConjunto() );
		oferta.setConstruccion( ofertaDTO.getConstruccion() );
		oferta.setEdadInmueble( ofertaDTO.getEdadInmueble() );
		oferta.setEstadoConservacion( ofertaDTO.getEstadoConservacion() );
		oferta.setGarajesDepositos( ofertaDTO.getGarajesDepositos() );
		oferta.setId( ofertaDTO.getId() );
		//oferta.setMetodoValuacion( ofertaDTO.getMetodoValuacion() ); 
		oferta.setObservaciones( ofertaDTO.getObservaciones() );
		oferta.setPorcentajeDepurado( ofertaDTO.getPorcentajeDepurado() );
		oferta.setTelefono( ofertaDTO.getTelefono() );
		oferta.setTotalTerreno( ofertaDTO.getTotalTerreno() );
		oferta.setValorDepurado( ofertaDTO.getValorDepurado() );
		oferta.setValorFinalDepurado( ofertaDTO.getValorFinalDepurado() );
		oferta.setValorM2Construccion( ofertaDTO.getValorM2Construccion() );
		oferta.setValorM2Terreno( ofertaDTO.getValorM2Terreno() );
		oferta.setValorOferta( ofertaDTO.getValorOferta() );
		oferta.setValorReposicion( ofertaDTO.getValorReposicion() );
		oferta.setVidaUtil( ofertaDTO.getVidaUtil() );
		oferta.setValorIntegralConstruccion( ofertaDTO.getValorIntegralConstruccion() );
		
		///
		oferta.setLocalizacion(ofertaDTO.getLocalizacion());
		oferta.setNombre(ofertaDTO.getNombre());
		oferta.setFuente(ofertaDTO.getFuente());
		oferta.setEdad(ofertaDTO.getEdad());
		if(ofertaDTO.getUbicacion()!=null){
			oferta.setUbicacion(ofertaDTO.getUbicacion().getKey());
		}
		if(ofertaDTO.getAcabados()!=null){
			oferta.setAcabados(ofertaDTO.getAcabados().getKey());
		}
		
		oferta.setNegociacion(ofertaDTO.getNegociacion());
		oferta.setEdadHomogenizada(ofertaDTO.getEdadHomogenizada());
		oferta.setFactorHomogenizacion(ofertaDTO.getFactorHomogenizacion());
		
		return oferta;
	}
	/**
	 * Realiza una conversion de List<OfertaDTO> a List<Oferta>
	 * @param List<OfertaDTO> lista de ofertas DTO a convertir.
	 * @return List<Oferta> lista de ofertas
	 * @throws MetodoValuacionNotFoundException 
	 * */
	public List<Oferta> crearLista( List<OfertaDTO> ofertasDTO, MetodoValuacionDTO metodoValuacionDTO ) throws MetodoValuacionNotFoundException{
		List<Oferta> ofertas = new ArrayList<Oferta>();
		for( OfertaDTO ofertaDTO : ofertasDTO ){
			Oferta ofertaAux =  new Oferta();
			this.crearOferta(ofertaAux,ofertaDTO, metodoValuacionDTO);
			ofertas.add( ofertaAux);
		}
		return ofertas;
	}

	@Transactional(rollbackFor = OfertaNotFoundException.class)
	public void actualizarOfertas(List<OfertaDTO> ofertasDTO, MetodoValuacionDTO metodoValuacionDTO) {
		log.info( " ACTULIZANDO OFERTAS" );
		// buscar por modelo find by id 
		// setearle los valores
		// el transactional guarda las modificaciones.
		
		for( OfertaDTO ofertaDTO: ofertasDTO){
			//no existe
			if( ofertaDTO.getId()==null){
				this.ofertaService.crear(ofertaDTO, metodoValuacionDTO);
			}
			//existe
			else{
				Oferta oferta = this.ofertaRepository.findOne( ofertaDTO.getId());
				if( oferta!=null){
					oferta = this.crearOferta(oferta, ofertaDTO);
				}
			}
		}
	}


	
}
