package com.helio4.bancol.avaluos.ensamblador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.ComparacionMercadoPHDTO;
import com.helio4.bancol.avaluos.dto.MetodoValuacionDTO.MetodoValuacionEnum;
import com.helio4.bancol.avaluos.dto.OfertaDTO;
import com.helio4.bancol.avaluos.modelo.Avaluo;
import com.helio4.bancol.avaluos.modelo.ComparacionMercadoPh;
import com.helio4.bancol.avaluos.modelo.Oferta;
import com.helio4.bancol.avaluos.persistencia.AvaluoRepository;
import com.helio4.bancol.avaluos.persistencia.ComparacionMercadoPHRepository;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.MetodoValuacionNotFoundException;

@Component
public class ComparacionMercadoPHEnsamblador {
	
	@Autowired
	private OfertaEnsamblador ofertaEnsamblador;
	
	@Autowired
	private ComparacionMercadoPHRepository comparacionPHRespository;

	@Autowired
	private AvaluoRepository avaluoRepository;
	
	
	/**
	 * Función que se encarga de construir un DTO a partir del modelo
	 * 
	 * @param comparacionMercado modelo a partir del cual se va a construir el DTO
	 * @return  ComparacionMercadoPHDTO  construido.
	 * */
	public ComparacionMercadoPHDTO escribirDTO( ComparacionMercadoPh comparacionMercado ){
		ComparacionMercadoPHDTO comparacion = new ComparacionMercadoPHDTO();
		comparacion.setAvaluoId( comparacionMercado.getAvaluo().getId() ) ;
		comparacion.setCoeficienteVariacion( comparacionMercado.getCoeficienteVariacion() );
		comparacion.setConceptoDelMetodo( comparacionMercado.getConceptoDelMetodo() );
		comparacion.setDesviacion( comparacionMercado.getDesviacion() );
		comparacion.setId( comparacionMercado.getId() );
		comparacion.setLimiteInferior( comparacionMercado.getLimiteInferior() );
		comparacion.setLimiteSuperior( comparacionMercado.getLimiteSuperior() );
		
		if (comparacionMercado.getMetodoUsado() != null && comparacionMercado.getMetodoUsado() > 0) 
		{
			comparacion.setMetodoUsado(MetodoValuacionEnum.fromKey(comparacionMercado.getMetodoUsado()));
		}
		comparacion.setNumeroDatos( comparacionMercado.getNumeroDatos() );
		
		if( comparacionMercado.getOfertas()!=null ){
			List<Oferta> ofertas =  comparacionMercado.getOfertas();
			List<OfertaDTO> ofertasDTO = this.ofertaEnsamblador.escribirListaDTO(ofertas);
			comparacion.setOfertas( ofertasDTO );
		}
		comparacion.setPromedioM2( comparacionMercado.getPromedioM2() );
		comparacion.setRaiz( comparacionMercado.getRaiz() );
		comparacion.setTstudent( comparacionMercado.getTstudent() );		
		return comparacion;
	}
	/**
	 * Función que se encarga de crear un modelo a partir del DTO.
	 * 
	 * @param comparacionMercado DTO con el que se crea el modelo.
	 * @return ComparacionMercadoPh modelo creado.
	 * @throws AvaluoNotFoundException 
	 * */
	public ComparacionMercadoPh crearComparacionMercadoPH( ComparacionMercadoPHDTO comparacionMercado ) 
			throws MetodoValuacionNotFoundException, AvaluoNotFoundException{
		
		ComparacionMercadoPh comparacion = new ComparacionMercadoPh();
		Avaluo avaluo = avaluoRepository.findOne(comparacionMercado.getAvaluoId());
		if (avaluo == null) {
			throw new AvaluoNotFoundException();
		}
		comparacion.setAvaluo( avaluo );
		comparacion.setCoeficienteVariacion( comparacionMercado.getCoeficienteVariacion() );
		comparacion.setConceptoDelMetodo( comparacionMercado.getConceptoDelMetodo() );
		comparacion.setDesviacion( comparacionMercado.getDesviacion() );
		comparacion.setLimiteInferior( comparacionMercado.getLimiteInferior() );
		comparacion.setLimiteSuperior( comparacionMercado.getLimiteSuperior() );
		if( comparacionMercado.getMetodoUsado()!=null ){
			comparacion.setMetodoUsado(comparacionMercado.getMetodoUsado().getKey() );
		}
	
		comparacion.setFactorSuperficie(comparacionMercado.getFactorSuperficie());
		comparacion.setFactorEdad(comparacionMercado.getFactorEdad());
		comparacion.setNumeroDatos( comparacionMercado.getNumeroDatos() );
		comparacion.setPromedioM2( comparacionMercado.getPromedioM2() );
		comparacion.setRaiz( comparacionMercado.getRaiz() );
		comparacion.setTstudent( comparacionMercado.getTstudent() );
		comparacion.setTipoProyecto(comparacionMercado.getTipoProyecto().getKey());
		comparacion.setValorM2AreaLibre(comparacionMercado.getValorM2AreaLibre());
		comparacion.setValorUnitarioGaraje(comparacionMercado.getValorUnitarioGaraje());
		return comparacion;
	}
	public void actualizar(ComparacionMercadoPHDTO comparacionActualizada) throws AvaluoNotFoundException {
		ComparacionMercadoPh comparacion = this.comparacionPHRespository.findOne( comparacionActualizada.getId() );
		// TODO: Controlar si la comparación no esta en BD
		this.actuliazar(comparacionActualizada, comparacion); 		
	}
	
	
	public void actuliazar(ComparacionMercadoPHDTO actualizada, ComparacionMercadoPh comparacion) throws AvaluoNotFoundException{
		Avaluo avaluo = this.avaluoRepository.findOne(actualizada.getAvaluoId());
		if (avaluo == null) {
			throw new AvaluoNotFoundException();
		}
		comparacion.setAvaluo( avaluo );
		comparacion.setId( actualizada.getId() );
		comparacion.setCoeficienteVariacion( actualizada.getCoeficienteVariacion() );
		comparacion.setConceptoDelMetodo( actualizada.getConceptoDelMetodo() );
		comparacion.setDesviacion( actualizada.getDesviacion() );
		comparacion.setLimiteInferior( actualizada.getLimiteInferior() );
		comparacion.setLimiteSuperior( actualizada.getLimiteSuperior() );
		comparacion.setMetodoUsado( actualizada.getMetodoUsado().getKey() );
		comparacion.setNumeroDatos( actualizada.getNumeroDatos() );
		comparacion.setPromedioM2( actualizada.getPromedioM2() );
		comparacion.setRaiz( actualizada.getRaiz() );
		comparacion.setTstudent( actualizada.getTstudent() );
		
	}
	
	
}
