package com.helio4.bancol.avaluos.servicio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.NonUniqueResultException;

import com.helio4.bancol.avaluos.servicio.datos.UvrService;
import com.helio4.bancol.avaluos.servicio.excepciones.UvrNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.UvrDTO;
import com.helio4.bancol.avaluos.ensamblador.UvrEnsamblador;
import com.helio4.bancol.avaluos.modelo.Uvr;
import com.helio4.bancol.avaluos.persistencia.UvrRepository;

@Component(value="repositoryUvrService")
@Transactional(readOnly = true)
public class RepositoryUvrService implements UvrService {

	private static Logger log = LoggerFactory
			.getLogger(RepositoryUvrService.class);

	@Autowired
	private UvrRepository uvrRepository;

	@Autowired
	private UvrEnsamblador uvrEnsamblador;

	@Transactional(readOnly = true)
	@Override
	public UvrDTO encontrarPorId(Long id) {
		return uvrEnsamblador.escribirDTO(uvrRepository.findOne(id));
	}

	@Transactional(readOnly = true)
	@Override
	public UvrDTO encontrarPor(Date fecha) {
		if ( fecha !=null ) {
			Uvr uvr = uvrRepository.encontrarPor(fecha);
			if( uvr !=null){
				return uvrEnsamblador.escribirDTO(uvr);
			}
		}
		
		return null;
	}

	@Transactional(readOnly = true)
	@Override
	public UvrDTO encontrarUvrActual() {
        UvrDTO resultado = null;
        try {
            resultado = uvrEnsamblador.escribirDTO(uvrRepository.encontrarUvrActual());
        } catch (NonUniqueResultException e) {
            log.error("UVR duplicada ", e);
        }
		return resultado;
	}

	@Transactional
	@Override
	public UvrDTO crear(UvrDTO uvrDTO){
		Uvr uvr = this.uvrEnsamblador.crearUvr(uvrDTO);
		this.uvrRepository.save(uvr);
		uvrDTO.setId( uvr.getId() );
		return uvrDTO;
	}

	@Transactional(readOnly = true)
	@Override
	public List<UvrDTO> crear(List<UvrDTO> uvrs) {
		List<UvrDTO> lista = new ArrayList<UvrDTO>();
		for( UvrDTO uvr: uvrs){
			lista.add(  this.crear(uvr) );
		}
		return lista;
	}

	@Transactional(readOnly = true)
	@Override
	public List<UvrDTO> encontrarTodos() {
		return  this.uvrEnsamblador.escribirDTOs( uvrRepository.findAll() );
	}

	@Transactional(rollbackFor = UvrNotFoundException.class)
	@Override
	public UvrDTO actualizar( UvrDTO uvrActualizada ) {
		//se asume que se realizó la validación de la existencia de la UVR antes de llamar a este método
		Uvr uvr = this.uvrRepository.findOne( uvrActualizada.getId() );
		uvr.setValor( uvrActualizada.getValor() );
		this.uvrRepository.save(uvr);
		return this.uvrEnsamblador.escribirDTO(uvr);
	}
}
