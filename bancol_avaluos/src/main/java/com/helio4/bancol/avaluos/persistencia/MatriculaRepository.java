package com.helio4.bancol.avaluos.persistencia;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.helio4.bancol.avaluos.dto.MatriculaDTO;
import com.helio4.bancol.avaluos.modelo.Matricula;

/**
 * Repository Matricula
 * 
 * @author <a href="mailto:j.j.o.c007@gmail.com">Juan Jose Orjuela Castillo</a>
 * 
 */
@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
	
	@Query("SELECT  NEW com.helio4.bancol.avaluos.dto.MatriculaDTO(m.id,m.codigo,m.tipoInmueble,m.numero,m.avaluo.id) FROM Matricula m WHERE m.avaluo.id =:avaluoId ORDER BY  m.id")
	public List<MatriculaDTO> encontrarPorAvaluoId(@Param("avaluoId") Long idAvaluo );
}
