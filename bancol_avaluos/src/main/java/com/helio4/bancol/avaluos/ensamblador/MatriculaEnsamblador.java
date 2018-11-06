package com.helio4.bancol.avaluos.ensamblador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.MatriculaDTO;
import com.helio4.bancol.avaluos.modelo.Avaluo;
import com.helio4.bancol.avaluos.modelo.Matricula;
import com.helio4.bancol.avaluos.persistencia.AvaluoRepository;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;


/**
 * Ensamblador Matricula Transfer Object de DTO a modelo
 * 
 * @author <a href="mailto:j.j.o.c007@gmail.com">Juan Jose Orjuela Castillo</a>
 * 
 */
@Component
public class MatriculaEnsamblador {
	
	@Autowired
	private AvaluoRepository avaluoRepository;
	
	public Matricula crearMatricula(MatriculaDTO matriculaDTO) throws AvaluoNotFoundException {
		Matricula matricula =  new Matricula();
		
		matricula.setCodigo(matriculaDTO.getCodigo());
		matricula.setId(matriculaDTO.getId());
		matricula.setNumero(matriculaDTO.getNumero());
		matricula.setTipoInmueble(matriculaDTO.getTipoInmueble());

		if ( matriculaDTO.getAvaluoId() != null) {
			Avaluo avaluo = this.avaluoRepository.findOne(matriculaDTO.getAvaluoId());
			if( avaluo == null ){
				throw new AvaluoNotFoundException();
			}
			matricula.setAvaluo(avaluo);
		}
		
		return matricula;
	}
	
	public List<Matricula> obtenerMatriculas(List<MatriculaDTO> matriculasDTO) throws AvaluoNotFoundException {
		
		List<Matricula> matriculas = new ArrayList<Matricula>();
		
		for(MatriculaDTO matriculaDTO : matriculasDTO ){
			
			if( matriculaDTO.getCodigo() != null  &&  !matriculaDTO.getCodigo().equals("")){
				matriculas.add(this.crearMatricula(matriculaDTO));
			}
		}
		return matriculas;
	}
	
}
