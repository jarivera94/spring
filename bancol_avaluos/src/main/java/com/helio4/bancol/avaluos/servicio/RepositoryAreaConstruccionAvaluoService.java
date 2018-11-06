package com.helio4.bancol.avaluos.servicio;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.AreaConstruccionAvaluoDTO;
import com.helio4.bancol.avaluos.ensamblador.AreaConstruccionAvaluoEnsamblador;
import com.helio4.bancol.avaluos.modelo.AreaConstruccionAvaluo;
import com.helio4.bancol.avaluos.persistencia.AreaConstruccionAvaluoRepository;
import com.helio4.bancol.avaluos.servicio.datos.AreaConstruccionAvaluoService;
import com.helio4.bancol.avaluos.servicio.datos.AreaConstruccionService;
import com.helio4.bancol.avaluos.servicio.excepciones.AreaConstruccionAvaluoNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;

@Component(value="repositoryAreaConstruccionAvaluoService")
@Transactional(readOnly = true)
public class RepositoryAreaConstruccionAvaluoService implements AreaConstruccionAvaluoService {

	private static Logger log = LoggerFactory.getLogger( RepositoryAreaConstruccionAvaluoService.class );
	@PersistenceContext
	private EntityManager em;

	@Autowired
	private AreaConstruccionAvaluoRepository areaConstruccionAvaluoRepository;
	
	@Autowired
	private AreaConstruccionAvaluoEnsamblador areaConstruccionAvaluoEnsamblador;
	
	@Autowired
	@Qualifier("repositoryAreaConstruccionService")
	private AreaConstruccionService areaConstruccionService;

	@Override
	public AreaConstruccionAvaluoDTO encontrarPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public AreaConstruccionAvaluoDTO crear(AreaConstruccionAvaluoDTO areaConstruccionAvaluoDTO) {
		log.debug("Creando un nuevo detalle de area construccion de avaluo {}", areaConstruccionAvaluoDTO);

		try {
			AreaConstruccionAvaluo areaConstruccionAvaluo = areaConstruccionAvaluoEnsamblador.crear(areaConstruccionAvaluoDTO);

			areaConstruccionAvaluoRepository.save(areaConstruccionAvaluo);
			areaConstruccionAvaluoDTO.setId(areaConstruccionAvaluo.getId());

			return areaConstruccionAvaluoDTO;
		} catch (AvaluoNotFoundException e) {
			log.debug("AvaluoNotFoundException: no se encontró el avaluo asociado a la matricula {}", areaConstruccionAvaluoDTO);
			return null;
		}
	}

	@Override
	public AreaConstruccionAvaluoDTO actualizar(AreaConstruccionAvaluoDTO areaConstruccionAvaluoActualizada)
			throws AreaConstruccionAvaluoNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AreaConstruccionAvaluoDTO eliminar(Long areaConstruccionAvaluoId)
			throws AreaConstruccionAvaluoNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
