package com.helio4.bancol.avaluos.servicio;

import java.util.List;

import com.helio4.bancol.avaluos.modelo.Avaluo_;
import com.helio4.bancol.avaluos.modelo.Garaje_;
import com.helio4.bancol.avaluos.servicio.datos.GarajeService;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.GarajeDTO;
import com.helio4.bancol.avaluos.ensamblador.GarajeEnsamblador;
import com.helio4.bancol.avaluos.exception.GarajeNotFoundException;
import com.helio4.bancol.avaluos.modelo.Garaje;
import com.helio4.bancol.avaluos.persistencia.GarajeRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;

@Component(value="repositoryGarajeService")
@Transactional(readOnly = true)
public class RepositoryGarajeService implements GarajeService {
	
	private static Logger log = LoggerFactory.getLogger( RepositoryAreaService.class );
	@PersistenceContext
	private EntityManager em;

	@Autowired
	private GarajeRepository garajeRepository;
	
	@Autowired
	private GarajeEnsamblador garajeEnsamblador;

	@Transactional
	@Override
	public GarajeDTO crear(GarajeDTO garajeDTO) throws AvaluoNotFoundException {
		Garaje garaje = this.garajeEnsamblador.crearGaraje(garajeDTO);
		this.garajeRepository.save(garaje);
		garajeDTO.setId( garaje.getId() ); 
		return garajeDTO;
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<GarajeDTO> encontrarTodos(Long avaluoId) { 
		return this.garajeRepository.encontrarGarajes(avaluoId);
	}

	@Transactional(readOnly = true)
	@Override
	public GarajeDTO encontrarPorId(Long id) {
		return this.garajeEnsamblador.escribirGaraje( this.garajeRepository.findOne(id) ); 
	}
	
	@Transactional(rollbackFor = GarajeNotFoundException.class)
	@Override
	public GarajeDTO eliminar(Long garajeId) throws GarajeNotFoundException {
        log.debug("Eliminando el garaje con id:  {}",
                garajeId);
		Garaje  garaje = this.garajeRepository.findOne(garajeId);
		if( garaje == null ) {
			throw new GarajeNotFoundException();
		}
		this.garajeRepository.delete(garaje);
		return null;
	}

	@Transactional(rollbackFor = GarajeNotFoundException.class)
	@Override
	public GarajeDTO actualizar(GarajeDTO actualizado)
			throws GarajeNotFoundException {
		this.garajeEnsamblador.actualizarGaraje( actualizado.getId(), actualizado );
		return actualizado;
	}

	@Override
	public void eliminarPor(Long avaluoId) {
		CriteriaBuilder cb = this.em.getCriteriaBuilder();

		// create delete
		CriteriaDelete<Garaje> delete = cb.
				createCriteriaDelete(Garaje.class);

		// set the root class
		Root<Garaje> e = delete.from(Garaje.class);

		// set where clause
		delete.where(cb.equal(e.get(Garaje_.avaluo).get(Avaluo_.id), avaluoId));

		// perform update
		this.em.createQuery(delete).executeUpdate();
	}

}
