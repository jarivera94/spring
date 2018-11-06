package com.helio4.bancol.avaluos.servicio;

import java.util.List;

import com.helio4.bancol.avaluos.modelo.Area_;
import com.helio4.bancol.avaluos.modelo.Avaluo;
import com.helio4.bancol.avaluos.modelo.Avaluo_;
import com.helio4.bancol.avaluos.servicio.datos.AreaConstruccionService;
import com.helio4.bancol.avaluos.servicio.datos.AreaService;
import com.helio4.bancol.avaluos.servicio.excepciones.AreaNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.AreaConstruccionDTO;
import com.helio4.bancol.avaluos.dto.AreaDTO;
import com.helio4.bancol.avaluos.ensamblador.AreaEnsamblador;
import com.helio4.bancol.avaluos.modelo.Area;
import com.helio4.bancol.avaluos.persistencia.AreaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

@Component(value="repositoryAreaService")
@Transactional(readOnly = true)
public class RepositoryAreaService implements AreaService {

	private static Logger log = LoggerFactory.getLogger( RepositoryAreaService.class );
	@PersistenceContext
	private EntityManager em;

	@Autowired
	private AreaRepository areaRepository;
	
	@Autowired
	private AreaEnsamblador areaEnsamblador;
	
	@Autowired
	@Qualifier("repositoryAreaConstruccionService")
	private AreaConstruccionService areaConstruccionService;

	@Transactional
	@Override
	public AreaDTO crear(AreaDTO areaDTO) {
		log.debug("Creando un nuevo detalle de area {}",
                areaDTO);
		Area area;
		if (areaDTO.getClass().equals(AreaConstruccionDTO.class)) {
			return areaConstruccionService.crear((AreaConstruccionDTO) areaDTO);
		}else{
			try {
				area = areaEnsamblador.crearArea(areaDTO);
			} catch (AvaluoNotFoundException e) {
				log.debug(
                        "AvaluoNotFoundException: no se encontró el avaluo asociado al area {}",
                        areaDTO);
				return null;
			}
			areaRepository.save(area);
			areaDTO.setId(area.getId());
		}
		return areaDTO;
	}

	@Transactional(rollbackFor = AreaNotFoundException.class)
	@Override
	public AreaDTO eliminar(Long areaId) throws AreaNotFoundException {
		log.debug("Eliminando el area con id: {}",
                areaId);
		Area area = areaRepository.findOne(areaId);
		if (area == null) {
			throw new AreaNotFoundException();
		}
		areaRepository.delete(area);
		return areaEnsamblador.escribirDTO(area);
	}

	@Transactional(readOnly = true)
	@Override
	public List<AreaDTO> encontrarTodos() {
		log.debug("Encontrando todas las areas");
		return areaEnsamblador.escribirListaDTO(areaRepository.findAll());
	}

	@Transactional(readOnly = true)
	@Override
	public AreaDTO encontrarPorId(Long id) {
		return areaEnsamblador.escribirDTO(areaRepository.findOne(id));
	}

	@Transactional(rollbackFor = AreaNotFoundException.class)
	@Override
	public AreaDTO actualizar(AreaDTO actualizado)
			throws AreaNotFoundException {
		try {
			areaEnsamblador.actualizarArea(actualizado.getId(), actualizado);
		} catch (AvaluoNotFoundException e) {
			log.debug(
                    "AvaluoNotFoundException: no se encontró el avaluo asociado al area {}",
                    actualizado);
			return null;
		}
		return actualizado;
	}

	@Override
	public void eliminarPor(Long avaluoId) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();

        // create delete
        CriteriaDelete<Area> delete = cb.
                createCriteriaDelete(Area.class);

        // set the root class
        Root<Area> e = delete.from(Area.class);

        // set where clause
        delete.where(cb.equal(e.get(Area_.avaluo).get(Avaluo_.id), avaluoId));

        // perform update
        this.em.createQuery(delete).executeUpdate();
	}

}
