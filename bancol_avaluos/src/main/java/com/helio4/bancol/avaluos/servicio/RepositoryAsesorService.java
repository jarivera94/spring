package com.helio4.bancol.avaluos.servicio;

import java.util.ArrayList;
import java.util.List;

import com.helio4.bancol.avaluos.servicio.datos.AsesorService;
import com.helio4.bancol.avaluos.servicio.excepciones.AsesorNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.EntidadNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.ServidumbreNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.SucursalNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.AsesorDTO;
import com.helio4.bancol.avaluos.ensamblador.AvaluoEnsamblador;
import com.helio4.bancol.avaluos.modelo.Asesor;
import com.helio4.bancol.avaluos.persistencia.AsesorRepository;

@Component(value = "repositoryAsesorService")
@Transactional(readOnly = true)
public class RepositoryAsesorService implements AsesorService {
	
	private static Logger log = LoggerFactory.getLogger( RepositoryAsesorService.class );
	
	@Autowired
	private AsesorRepository asesorRepository;
	
	@Autowired
	private AvaluoEnsamblador avaluoEnsamblador;
	
	@Transactional(readOnly = true)
	@Override
	public List<AsesorDTO> encontrarPorNombre(String texto, Long idEntidad){
		List<AsesorDTO> asesores = new ArrayList<AsesorDTO>();
		for(Asesor asesor: asesorRepository.encontrarPorNombre(texto, idEntidad)){
			asesores.add(avaluoEnsamblador.escribirDTO(asesor));
		}
		return asesores;
	}

	@Transactional(readOnly = true)
	@Override
	public AsesorDTO encontrarPorId(Long id) {
		return avaluoEnsamblador.escribirDTO(asesorRepository.encontrarPorId(id));
	}

	@Transactional
	@Override
	public AsesorDTO guardar(AsesorDTO asesorDTO) throws EntidadNotFoundException {
		Asesor asesor;
		try {
			asesor = asesorRepository.save(avaluoEnsamblador.crearAsesor(asesorDTO));
			asesorDTO.setId(asesor.getId());
		} catch (SucursalNotFoundException e) {
			return null;
		}
		return asesorDTO;
	}

	@Transactional(rollbackFor = AsesorNotFoundException.class)
	@Override
	public AsesorDTO actualizar(AsesorDTO asesorDTO) throws EntidadNotFoundException {
		try {
			avaluoEnsamblador.actualizarAsesor(asesorDTO);
		} catch (ServidumbreNotFoundException e) {
			log.error("No se encontró la sucursal del asesor");
			return null;
		} catch (AsesorNotFoundException e) {
			log.error("No se encontró el asesor para actualizar");
			return guardar(asesorDTO);
		}
		return asesorDTO;
	}

	@Transactional(readOnly = true)
	@Override
	public AsesorDTO encontrarAsesor(String nombre, Long idEntidad,
			String codigoSucursal, String celular, String correo, String telefono) {
		AsesorDTO asesorDTO =  avaluoEnsamblador.escribirDTO(asesorRepository.encontrarAsesor(nombre, idEntidad, codigoSucursal, celular, correo, telefono));
		return asesorDTO;
	}
	
	@Transactional(readOnly = true)
	@Override
	public AsesorDTO encontrarPorCorreo(String correo){
		Asesor asesor = asesorRepository.encontrarPorCorreo(correo);
		if( asesor !=null ){
			AsesorDTO asesorDTO = avaluoEnsamblador.escribirDTO(asesor); 
			return asesorDTO;
		}
		return null;
	}

}
