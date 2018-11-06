package com.helio4.bancol.avaluos.servicio.datos;

import org.springframework.stereotype.Service;

import com.helio4.bancol.avaluos.dto.MatriculaDTO;

@Service
public interface MatriculaService {

    /**
     * Actualiza o crea una matricula
     * @param matriculaDTO La informacion de la matricula.
     * @return La matricula actualizada.
     */
	MatriculaDTO crear(MatriculaDTO matriculaDTO);
	
    /**
     * Elimina una matricula
     * @param matriculaDTO La informacion de la matricula.
     */
	void eliminarMatricula(MatriculaDTO matriculaDTO);

}
