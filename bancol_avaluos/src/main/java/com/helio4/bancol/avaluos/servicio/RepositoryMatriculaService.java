package com.helio4.bancol.avaluos.servicio;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.MatriculaDTO;
import com.helio4.bancol.avaluos.ensamblador.MatriculaEnsamblador;
import com.helio4.bancol.avaluos.modelo.Matricula;
import com.helio4.bancol.avaluos.persistencia.MatriculaRepository;
import com.helio4.bancol.avaluos.servicio.datos.MatriculaService;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;

@Component(value="repositoryMatriculaService")
@Transactional(readOnly = true)
public class RepositoryMatriculaService implements MatriculaService {

	private static Logger log = LoggerFactory.getLogger( RepositoryMatriculaService.class );
	@PersistenceContext
	private EntityManager em;

	@Autowired
	private MatriculaRepository matriculaRepository;
	
	@Autowired
	private MatriculaEnsamblador matriculaEnsamblador;

	/* (non-Javadoc)
	 * @see com.helio4.bancol.avaluos.servicio.datos.MatriculaService#crear(com.helio4.bancol.avaluos.dto.MatriculaDTO)
	 */
	@Transactional
	@Override
	public MatriculaDTO crear(MatriculaDTO matriculaDTO) {
		log.debug("Creando un nuevo detalle de matricula de avaluo {}", matriculaDTO);

		try {
			Matricula matricula = matriculaEnsamblador.crearMatricula(matriculaDTO);

			matriculaRepository.save(matricula);
			matriculaDTO.setId(matricula.getId());

			return matriculaDTO;
		} catch (AvaluoNotFoundException e) {
			log.debug("AvaluoNotFoundException: no se encontró el avaluo asociado a la matricula {}", matriculaDTO);
			return null;
		}

	}
	
	
	public void eliminarMatricula(MatriculaDTO matriculaDTO){
		
		matriculaRepository.delete(matriculaDTO.getId());
		
	}
	

}
