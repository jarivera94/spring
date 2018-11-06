package com.helio4.bancol.avaluos.servicio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.MotivoDTO;
import com.helio4.bancol.avaluos.modelo.Motivo;
import com.helio4.bancol.avaluos.persistencia.MotivoRepository;
import com.helio4.bancol.avaluos.servicio.datos.MotivoService;

@Component(value="repositoryMotivoService")
@Transactional(readOnly = true)
public class RepositoryMotivoService implements MotivoService {

	private static Logger log = LoggerFactory.getLogger( RepositoryMotivoService.class );
	@PersistenceContext
	private EntityManager em;

	@Autowired
	private MotivoRepository motivoRepository;

	public List<MotivoDTO> getMotivosByEntidad(Long entidadId){
		
		List<MotivoDTO> motivos = motivoRepository.getMotivosByEntidad(entidadId);
			
		return motivos;
		
	}
	
	public Motivo getMotivosById(Long id){
		
		Motivo motivo = motivoRepository.findOne(id);
			
		return motivo;
		
	}
	
}
