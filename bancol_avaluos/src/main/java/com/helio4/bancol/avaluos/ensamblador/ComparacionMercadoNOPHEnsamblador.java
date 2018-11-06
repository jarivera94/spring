package com.helio4.bancol.avaluos.ensamblador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.ComparacionMercadoNOPHDTO;
import com.helio4.bancol.avaluos.dto.MetodoValuacionDTO.MetodoValuacionEnum;
import com.helio4.bancol.avaluos.dto.OfertaDTO;
import com.helio4.bancol.avaluos.modelo.Avaluo;
import com.helio4.bancol.avaluos.modelo.ComparacionMercadoNoPh;
import com.helio4.bancol.avaluos.modelo.Oferta;
import com.helio4.bancol.avaluos.persistencia.AvaluoRepository;
import com.helio4.bancol.avaluos.persistencia.ComparacionMercadoNOPHRepository;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.MetodoValuacionNotFoundException;


@Component
public class ComparacionMercadoNOPHEnsamblador {

	
	@Autowired
	private ComparacionMercadoNOPHRepository comparacionNOPHRepository;
	
	@Autowired
	private OfertaEnsamblador ofertaEnsamblador;
	
	
	@Autowired
	private AvaluoRepository avaluoRepository;
	
	public ComparacionMercadoNOPHDTO escribirDTO( ComparacionMercadoNoPh comparacionMercado ){
		ComparacionMercadoNOPHDTO comparacion = new ComparacionMercadoNOPHDTO();
		comparacion.setAvaluoId( comparacionMercado.getAvaluo().getId() );
		comparacion.setCoeficienteVariacion( comparacionMercado.getCoeficienteVariacion() );
		comparacion.setCoeficienteVariacionTerreno( comparacionMercado.getCoeficienteVariacionTerreno() );
		comparacion.setConceptoDelMetodo( comparacionMercado.getConceptoDelMetodo() );
		comparacion.setDesviacion( comparacionMercado.getDesviacion() );
		comparacion.setDesviacionTerreno(comparacionMercado.getDesviacionTerreno() );
		comparacion.setId( comparacionMercado.getId() );
		comparacion.setLimiteInferior( comparacionMercado.getLimiteInferior() );
		comparacion.setLimiteSuperior( comparacionMercado.getLimiteSuperior() );
		comparacion.setLimiteInferiorTerreno(comparacionMercado.getLimieteInferiorTerreno());
		comparacion.setLimiteSuperiorTerreno( comparacionMercado.getLimiteSuperiorTerreno() );
		
		comparacion.setLimiteSuperiorIntegral( comparacionMercado.getLimiteSuperiorIntegral() );
		comparacion.setLimiteInferiorIntegral( comparacionMercado.getLimiteInferiorIntegral()   );
		comparacion.setPromedioM2Integral( comparacionMercado.getPromedioM2Integral() );
		comparacion.setDesviacionIntegral( comparacionMercado.getDesviacionIntegral() );
		comparacion.setCoeficienteVariacionIntegral( comparacionMercado.getCoeficienteVariacionIntegral() ); 
		
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
		comparacion.setPromedioM2Terreno( comparacionMercado.getPromedioM2Terreno() );
		comparacion.setRaiz( comparacionMercado.getRaiz() );
		comparacion.setTstudent( comparacionMercado.getTstudent() );		
		return comparacion;
	}

	public ComparacionMercadoNoPh crearComparacionMercadoNOPH( ComparacionMercadoNOPHDTO comparacionMercado) 
			throws MetodoValuacionNotFoundException, AvaluoNotFoundException {
		

		ComparacionMercadoNoPh comparacion = new ComparacionMercadoNoPh();
		
		Avaluo avaluo = avaluoRepository.findOne(comparacionMercado.getAvaluoId());
		if (avaluo == null) {
			throw new AvaluoNotFoundException();
		}
		comparacion.setAvaluo( avaluo );
		comparacion.setCoeficienteVariacionTerreno( comparacionMercado.getCoeficienteVariacionTerreno() );
		comparacion.setCoeficienteVariacion( comparacionMercado.getCoeficienteVariacion() );
		comparacion.setConceptoDelMetodo( comparacionMercado.getConceptoDelMetodo() );
		comparacion.setDesviacion( comparacionMercado.getDesviacion() );
		comparacion.setDesviacionTerreno(  comparacionMercado.getDesviacionTerreno() );
		
	
		comparacion.setLimiteInferior( comparacionMercado.getLimiteInferior() );
		comparacion.setLimieteInferiorTerreno( comparacionMercado.getLimiteInferiorTerreno() );
		comparacion.setLimiteSuperior( comparacionMercado.getLimiteSuperior() );
		comparacion.setLimiteSuperiorTerreno( comparacionMercado.getLimiteSuperiorTerreno() ); 
		
		comparacion.setLimiteSuperiorIntegral( comparacionMercado.getLimiteSuperiorIntegral() );
		comparacion.setLimiteInferiorIntegral( comparacionMercado.getLimiteInferiorIntegral()   );
		comparacion.setPromedioM2Integral( comparacionMercado.getPromedioM2Integral() );
		comparacion.setDesviacionIntegral( comparacionMercado.getDesviacionIntegral() );
		comparacion.setCoeficienteVariacionIntegral( comparacionMercado.getCoeficienteVariacionIntegral() ); 
		
		
		if( comparacionMercado.getMetodoUsado()!=null ){
			comparacion.setMetodoUsado(comparacionMercado.getMetodoUsado().getKey() );
		}
	
		comparacion.setNumeroDatos( comparacionMercado.getNumeroDatos() );
		comparacion.setPromedioM2( comparacionMercado.getPromedioM2() );
		comparacion.setPromedioM2Terreno(  comparacionMercado.getPromedioM2Terreno() );
		comparacion.setRaiz( comparacionMercado.getRaiz() );
		comparacion.setTstudent( comparacionMercado.getTstudent() );
		return comparacion;
	}

	
	public void actualizar( ComparacionMercadoNOPHDTO actualizada) throws AvaluoNotFoundException{
		ComparacionMercadoNoPh comparacion= this.comparacionNOPHRepository.findOne( actualizada.getId() );
		this.actualizar(actualizada, comparacion);
	}
	
	public void actualizar(ComparacionMercadoNOPHDTO actualizada, ComparacionMercadoNoPh comparacion) throws AvaluoNotFoundException{
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
		
		comparacion.setCoeficienteVariacionTerreno( actualizada.getCoeficienteVariacionTerreno() );
		comparacion.setPromedioM2Terreno( actualizada.getPromedioM2Terreno() );
		comparacion.setDesviacionTerreno( actualizada.getDesviacionTerreno() );
		comparacion.setCoeficienteVariacionTerreno( actualizada.getCoeficienteVariacionTerreno() );
		comparacion.setLimieteInferiorTerreno( actualizada.getLimiteInferiorTerreno() );
		comparacion.setLimiteSuperiorTerreno( actualizada.getLimiteSuperiorTerreno() ); 
		
		comparacion.setLimiteSuperiorIntegral( actualizada.getLimiteSuperiorIntegral() );
		comparacion.setLimiteInferiorIntegral( actualizada.getLimiteInferiorIntegral()   );
		comparacion.setPromedioM2Integral( actualizada.getPromedioM2Integral() );
		comparacion.setDesviacionIntegral( actualizada.getDesviacionIntegral() );
		comparacion.setCoeficienteVariacionIntegral( actualizada.getCoeficienteVariacionIntegral() ); 
		

		if( comparacion.getMetodoUsado()!=null ){
			comparacion.setMetodoUsado(actualizada.getMetodoUsado().getKey() );
		}
	}
	
}
