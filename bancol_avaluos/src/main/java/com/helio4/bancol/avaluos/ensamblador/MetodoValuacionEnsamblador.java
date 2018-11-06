package com.helio4.bancol.avaluos.ensamblador;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.MetodoValuacionDTO;
import com.helio4.bancol.avaluos.dto.MetodoValuacionDTO.MetodoValuacionEnum;
import com.helio4.bancol.avaluos.dto.OfertaDTO;
import com.helio4.bancol.avaluos.modelo.Avaluo;
import com.helio4.bancol.avaluos.modelo.ComparacionMercadoLoteConstruccionRenta;
import com.helio4.bancol.avaluos.modelo.ComparacionMercadoLoteConstruccionVenta;
import com.helio4.bancol.avaluos.modelo.ComparacionMercadoLoteSinConstruccion;
import com.helio4.bancol.avaluos.modelo.ComparacionMercadoNoPh;
import com.helio4.bancol.avaluos.modelo.ComparacionMercadoPh;
import com.helio4.bancol.avaluos.modelo.ComparacionMercadoPhRenta;
import com.helio4.bancol.avaluos.modelo.ComparacionMercadoPhVenta;
import com.helio4.bancol.avaluos.modelo.MetodoValuacion;
import com.helio4.bancol.avaluos.modelo.Oferta;
import com.helio4.bancol.avaluos.persistencia.AvaluoRepository;
import com.helio4.bancol.avaluos.persistencia.MetodoValuacionRepository;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.MetodoValuacionNotFoundException;

@Component
public class MetodoValuacionEnsamblador {

	@Autowired
	private AvaluoRepository avaluoRepository;

	@Autowired
	private MetodoValuacionRepository metodoValuacionRepository;
	
	@Autowired
	private OfertaEnsamblador ofertaEnsamblador;
	
	@Autowired
	private ComparacionMercadoPHEnsamblador comparacionPHEnsamblador;
	
	@Autowired
	private ComparacionMercadoNOPHEnsamblador comparacionNOPHEnsamblador;
	
	@Autowired
	private ComparacionMercadoPHVentaEnsamblador comparacionPHVentaEnsamblador;
	
	@Autowired
	private ComparacionMercadoPHRentaEnsamblador comparacionPHRentaEnsamblador;
	
	@Autowired
	private ComparacionMercadoLoteVentaEnsamblador comparacionMercadoLoteVentaEnsamblador;
	
	@Autowired
	private ComparacionMercadoLoteConstruccionVentaEnsamblador comparacionMercadoLoteConstruccionVentaEnsamblador;
	
	@Autowired
	private ComparacionMercadoLoteConstruccionRentaEnsamblador comparacionMercadoLoteConstruccionRentaEnsamblador;
	
	public MetodoValuacion crearMetodoValuacion(MetodoValuacionDTO metodoValuacionDTO) throws AvaluoNotFoundException {
		MetodoValuacion metodoValuacion = new MetodoValuacion();
		metodoValuacion.setId(metodoValuacionDTO.getId());
		if (metodoValuacionDTO.getMetodoUsado() != null) {
			metodoValuacion.setMetodoUsado(metodoValuacionDTO.getMetodoUsado()
					.getKey());
		}
		metodoValuacion.setConceptoDelMetodo(metodoValuacionDTO.getConceptoDelMetodo());
		Avaluo avaluo = avaluoRepository.findOne(metodoValuacionDTO.getAvaluoId());
		if (avaluo == null) {
			throw new AvaluoNotFoundException();
		}
		metodoValuacion.setAvaluo(avaluo);
		
		return metodoValuacion;
	}

	public void actualizarMetodoValuacion(Long metodoValuacionId, MetodoValuacionDTO metodoValuacionDTO) throws MetodoValuacionNotFoundException, AvaluoNotFoundException {
		MetodoValuacion metodoValuacion = metodoValuacionRepository.findOne(metodoValuacionId);
		if (metodoValuacion == null) {
			throw new MetodoValuacionNotFoundException();
		}
		if (metodoValuacionDTO.getMetodoUsado() != null) {
			metodoValuacion.setMetodoUsado(metodoValuacionDTO.getMetodoUsado()
					.getKey());
		}
		metodoValuacion.setConceptoDelMetodo(metodoValuacionDTO.getConceptoDelMetodo());
		Avaluo avaluo = avaluoRepository.findOne(metodoValuacionDTO.getAvaluoId());
		if (avaluo == null) {
			throw new AvaluoNotFoundException();
		}
		
		metodoValuacion.setAvaluo(avaluo);
	}

	public MetodoValuacionDTO escribirDTO(MetodoValuacion metodoValuacion) {
		MetodoValuacionDTO metodoValuacionDTO = null;
		
		if(metodoValuacion!=null){
			metodoValuacionDTO = new MetodoValuacionDTO();
			if( metodoValuacion.getClass().equals( ComparacionMercadoPh.class)){
				return this.comparacionPHEnsamblador.escribirDTO( (ComparacionMercadoPh) metodoValuacion );
			}
			else if(metodoValuacion.getClass().equals( ComparacionMercadoNoPh.class) ){
				return this.comparacionNOPHEnsamblador.escribirDTO( (ComparacionMercadoNoPh)metodoValuacion );
			}else if(metodoValuacion.getClass().equals( ComparacionMercadoPhVenta.class) || metodoValuacion.getMetodoUsado().intValue() == MetodoValuacionEnum.ComparaciondeMercadoPHVenta.getKey() ){
				return this.comparacionPHVentaEnsamblador.escribirDTO( (ComparacionMercadoPhVenta)metodoValuacion );
			}else if(metodoValuacion.getClass().equals( ComparacionMercadoPhRenta.class) || metodoValuacion.getMetodoUsado().intValue() == MetodoValuacionEnum.ComparaciondeMercadoPHRenta.getKey() ){
				return this.comparacionPHRentaEnsamblador.escribirDTO( (ComparacionMercadoPhRenta)metodoValuacion );
			}else if(metodoValuacion.getClass().equals( ComparacionMercadoLoteSinConstruccion.class) || metodoValuacion.getMetodoUsado().intValue() == MetodoValuacionEnum.ComparaciondeMercadoLoteVentaSinContruccion.getKey() ){
				return this.comparacionMercadoLoteVentaEnsamblador.escribirDTO( (ComparacionMercadoLoteSinConstruccion)metodoValuacion );
			}else if(metodoValuacion.getClass().equals( ComparacionMercadoLoteConstruccionVenta.class) || metodoValuacion.getMetodoUsado().intValue() == MetodoValuacionEnum.ComparaciondeMercadoLoteVentaConstruccion.getKey() ){
				return this.comparacionMercadoLoteConstruccionVentaEnsamblador.escribirDTO( (ComparacionMercadoLoteConstruccionVenta)metodoValuacion );
			}else if(metodoValuacion.getClass().equals( ComparacionMercadoLoteConstruccionRenta.class) || metodoValuacion.getMetodoUsado().intValue() == MetodoValuacionEnum.ComparaciondeMercadoLoteRentaContruccion.getKey() ){
				return this.comparacionMercadoLoteConstruccionRentaEnsamblador.escribirDTO( (ComparacionMercadoLoteConstruccionRenta)metodoValuacion );
			}else{			
				metodoValuacionDTO.setId(metodoValuacion.getId());
				if (metodoValuacion.getMetodoUsado() != null
						&& metodoValuacion.getMetodoUsado() > 0) {
					metodoValuacionDTO.setMetodoUsado(MetodoValuacionEnum
							.fromKey(metodoValuacion.getMetodoUsado()));
				}
				if( metodoValuacion.getOfertas()!=null){
					List<Oferta> ofertas = metodoValuacion.getOfertas();
					List<OfertaDTO> ofertasDTO = this.ofertaEnsamblador.escribirListaDTO( ofertas );
					if( ofertasDTO !=null ){
						metodoValuacionDTO.setOfertas( ofertasDTO );
					}
				
				}
				metodoValuacionDTO.setConceptoDelMetodo(metodoValuacion.getConceptoDelMetodo());
				metodoValuacionDTO.setAvaluoId(metodoValuacion.getAvaluo().getId());
			}
		}
		
		return metodoValuacionDTO;
	}
	
	public List<MetodoValuacionDTO> escribirListaDTO(List<MetodoValuacion> metodoValuacions) {
		List<MetodoValuacionDTO> metodoValuacionDTOs = new ArrayList<MetodoValuacionDTO>();
		for (MetodoValuacion metodoValuacion:metodoValuacions) {
			metodoValuacionDTOs.add(escribirDTO(metodoValuacion));
		}
		return metodoValuacionDTOs;
	}

	public Set<MetodoValuacionDTO> escribirListaDTO(Set<MetodoValuacion> metodoValuacions) {
		Set<MetodoValuacionDTO> metodoValuacionDTOs = new HashSet<MetodoValuacionDTO>();
		for (MetodoValuacion metodoValuacion:metodoValuacions) {
			metodoValuacionDTOs.add(escribirDTO(metodoValuacion));
		}
		return metodoValuacionDTOs;
	}

}
