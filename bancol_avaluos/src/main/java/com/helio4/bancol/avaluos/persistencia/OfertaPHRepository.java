package com.helio4.bancol.avaluos.persistencia;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.helio4.bancol.avaluos.dto.MatriculaDTO;
import com.helio4.bancol.avaluos.dto.OfertaPHDTO;
import com.helio4.bancol.avaluos.modelo.OfertaPH;

public interface OfertaPHRepository extends JpaRepository<OfertaPH, Long> {

	@Query("SELECT  NEW com.helio4.bancol.avaluos.dto.OfertaPHDTO(m.id) FROM OfertaPH m WHERE m.metodoValuacion.id =:metodoValuacionId ORDER BY  m.id")
	public List<OfertaPHDTO> encontrarPorMetodoValuacion(@Param("metodoValuacionId") Long metodoValuacionId );
	
}


