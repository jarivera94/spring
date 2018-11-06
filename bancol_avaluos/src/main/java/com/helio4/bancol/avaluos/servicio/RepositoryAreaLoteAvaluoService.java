package com.helio4.bancol.avaluos.servicio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.AreaLoteAvaluoDTO;
import com.helio4.bancol.avaluos.ensamblador.AreaLoteAvaluoEnsamblador;
import com.helio4.bancol.avaluos.modelo.AreaLoteAvaluo;
import com.helio4.bancol.avaluos.persistencia.AreaLoteAvaluoRepository;
import com.helio4.bancol.avaluos.servicio.datos.AreaLoteAvaluoService;
import com.helio4.bancol.avaluos.servicio.excepciones.AreaLoteAvaluoNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;

@Component(value="repositoryAreaLoteAvaluoService")
@Transactional(readOnly = true)
public class RepositoryAreaLoteAvaluoService implements AreaLoteAvaluoService {

	private static Logger log = LoggerFactory.getLogger( RepositoryAreaLoteAvaluoService.class );
	@PersistenceContext
	private EntityManager em;

	@Autowired
	private AreaLoteAvaluoRepository areaLoteAvaluoRepository;
	
	@Autowired
	private AreaLoteAvaluoEnsamblador areaLoteAvaluoEnsamblador;
	
	@Override
	public AreaLoteAvaluoDTO encontrarPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public AreaLoteAvaluoDTO crear(AreaLoteAvaluoDTO areaLoteAvaluoDTO) {
		log.debug("Creando un nuevo detalle de area lote de avaluo {}", areaLoteAvaluoDTO);

		try {
			AreaLoteAvaluo areaLoteAvaluo = areaLoteAvaluoEnsamblador.crear(areaLoteAvaluoDTO);

			areaLoteAvaluoRepository.save(areaLoteAvaluo);
			areaLoteAvaluoDTO.setId(areaLoteAvaluo.getId());

			return areaLoteAvaluoDTO;
		} catch (AvaluoNotFoundException e) {
			log.debug("AvaluoNotFoundException: no se encontró el avaluo asociado al area lote {}", areaLoteAvaluoDTO);
			return null;
		}
	}

	@Override
	public AreaLoteAvaluoDTO actualizar(AreaLoteAvaluoDTO areaLoteAvaluoActualizada)
			throws AreaLoteAvaluoNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AreaLoteAvaluoDTO eliminar(Long areaLoteAvaluoId) throws AreaLoteAvaluoNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AreaLoteAvaluoDTO> crearList(List<AreaLoteAvaluoDTO> areaLoteAvaluoDTO) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
