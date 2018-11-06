package com.helio4.bancol.avaluos.servicio.datos;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.helio4.bancol.avaluos.dto.UvrDTO;

@Service
public interface UvrService {
	
	/**
	 * Encuentra el uvr por el identificador.
	 * @param id El identificador del uvr que se quiere encontrar.
	 * @return
	 */
	public UvrDTO encontrarPorId(Long id);
	
	public UvrDTO encontrarPor(Date fecha);
	
	public UvrDTO encontrarUvrActual();
	
	/**
	 * Crea una nueva uvr.
	 * 
	 * @param uvr que se va a guardar
	 * @return UVR creada.
	 * */
	public UvrDTO crear(UvrDTO uvr);
	
	/**
	 * Crea uvrs
	 * @param uvrs Lista de uvrs DTO a crear
	 * @return Lista de Uvrs creadas
	 * 
	 * */
	public List<UvrDTO> crear(List<UvrDTO> uvrs); 
	
	/**
	 * Obtiene toda las UVRS
	 * 
	 * @return Lista de datos de UVR's. 
	 * */
	public List<UvrDTO> encontrarTodos();
	
	/**
	 * Actualiza la información de una uvr.
	 * */
	public UvrDTO actualizar( UvrDTO uvrActualizada );

}
