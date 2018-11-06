package com.helio4.bancol.avaluos.servicio;

import java.util.ArrayList;
import java.util.List;

import com.helio4.bancol.avaluos.servicio.datos.ModificacionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.ModificacionDTO;
import com.helio4.bancol.avaluos.ensamblador.AvaluoEnsamblador;
import com.helio4.bancol.avaluos.modelo.Modificacion;
import com.helio4.bancol.avaluos.persistencia.ModificacionRepository;

@Transactional(readOnly = true)
@Component(value="repositoryModificacionService")
public class RepositoryModificacionService implements ModificacionService {
	
	private static Logger log = LoggerFactory.getLogger(RepositoryModificacionService.class );
	
	@Autowired
	private ModificacionRepository modificacionRepository;
	
	@Autowired
	private AvaluoEnsamblador avaluoEnsamblador;

	@Transactional
    @Override
	public List<ModificacionDTO> guardar(List<ModificacionDTO> modificaciones) {
		
		List<Modificacion> lista = new ArrayList<Modificacion>();
		List<ModificacionDTO> lista2 = new ArrayList<ModificacionDTO>();
		
		for(ModificacionDTO modificacionDTO: modificaciones){
			Modificacion modificacion = avaluoEnsamblador.crearModificacion(modificacionDTO);
			lista.add(modificacion);
		}
		
		List<Modificacion> modificaciones2 = modificacionRepository.save(lista);
		
		for(Modificacion modificacion: modificaciones2){
			ModificacionDTO modificacionDTO = avaluoEnsamblador.escribirDTO(modificacion);
			lista2.add(modificacionDTO);
		}
		
		return lista2;
	}

}
