package com.helio4.bancol.avaluos.ensamblador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.GarajeDTO;
import com.helio4.bancol.avaluos.exception.GarajeNotFoundException;
import com.helio4.bancol.avaluos.modelo.Avaluo;
import com.helio4.bancol.avaluos.modelo.Garaje;
import com.helio4.bancol.avaluos.persistencia.AvaluoRepository;
import com.helio4.bancol.avaluos.persistencia.GarajeRepository;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;

@Component
public class GarajeEnsamblador {

	@Autowired
	private GarajeRepository garajeRepository;

	@Autowired
	private AvaluoRepository avaluoRepository;
	/**
	 * Contruye un modelo a partir a de DTO.
	 *
	 * @param GarajeDTO a partir del cual se contruye el modelo
	 * @return Garaje modelo generado
	 * @throws AvaluoNotFoundException
	 * */
	public Garaje crearGaraje(GarajeDTO garajeDTO) throws AvaluoNotFoundException {
		Garaje garaje = new Garaje();
		garaje.setMatriculaInmobiliaria( garajeDTO.getMatriculaInmobiliaria() );
		garaje.setNumeroGaraje( garajeDTO.getNumeroGaraje() );
		garaje.setParalelo( garajeDTO.isParalelo() );
		garaje.setDoble( garajeDTO.isDoble() );
		garaje.setServidumbre( garajeDTO.isServidumbre() );
		garaje.setCubierto( garajeDTO.isCubierto() );
		garaje.setTipo( garajeDTO.getTipo() );
		if ( garajeDTO.getAvaluoId() != null) {
			Avaluo avaluo = this.avaluoRepository.findOne(garajeDTO.getAvaluoId());
			if( avaluo == null ){
				throw new AvaluoNotFoundException();
			}
			garaje.setAvaluo(avaluo);
		}

		return garaje;
	}
	public List<Garaje> obtenerGarajes(List<GarajeDTO> garajesDTO) throws GarajeNotFoundException{
		List<Garaje> garajes = new ArrayList<Garaje>();
		for(GarajeDTO garajeDTO : garajesDTO ){
			Garaje garaje = this.garajeRepository.findOne( garajeDTO.getId() );
			if( garaje == null ){
				throw new GarajeNotFoundException();
			}
			garajes.add(garaje);
		}
		return garajes;
	}
	/**
	 * Contruye un DTO a partir de un modelo
	 * @param Garaje
	 * @return GarajeDTO
	 * */
	public GarajeDTO escribirGaraje(Garaje garaje){
		GarajeDTO garajeDTO = new GarajeDTO();
		garajeDTO.setMatriculaInmobiliaria( garaje.getMatriculaInmobiliaria() );
		garajeDTO.setNumeroGaraje( garaje.getNumeroGaraje() );
		garajeDTO.setParalelo( garaje.getParalelo() );
		garajeDTO.setDoble( garaje.getDoble() );
		garajeDTO.setServidumbre( garaje.getServidumbre() );
		garajeDTO.setCubierto( garaje.getCubierto() );
		garajeDTO.setTipo( garaje.getTipo() );
		garajeDTO.setAvaluoId( garaje.getAvaluo().getId() );
		return garajeDTO;
	}
	/**
	 * Genera una lista de GarajesDTO a partir de una lista de modelos de Garajes.
	 * @param Lista de modelos de garajes
	 * @return Lista de DTO's generados.
	 * */
	public List<GarajeDTO> escribirGarajes( List<Garaje> garajes ){
		List<GarajeDTO> garajesDTO = new ArrayList<GarajeDTO>();
		for( Garaje garaje: garajes){
			garajesDTO.add( this.escribirGaraje(garaje) );
		}
		return garajesDTO;
	}

	public void actualizarGaraje(Long garajeId, GarajeDTO garajeDTO) throws GarajeNotFoundException{
		Garaje garaje = this.garajeRepository.findOne(garajeId);
		if( garaje == null ) {
			throw new GarajeNotFoundException();
		}
		garaje.setMatriculaInmobiliaria( garajeDTO.getMatriculaInmobiliaria() );
		garaje.setNumeroGaraje( garajeDTO.getNumeroGaraje() );
		garaje.setParalelo( garajeDTO.isParalelo() );
		garaje.setDoble( garajeDTO.isDoble() );
		garaje.setServidumbre( garajeDTO.isServidumbre() );
		garaje.setCubierto( garajeDTO.isCubierto() );
		garaje.setTipo( garajeDTO.getTipo() );
	}

}
