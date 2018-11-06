package com.helio4.bancol.avaluos.persistencia;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.helio4.bancol.avaluos.dto.OfertaLoteSinConstruccionDTO;
import com.helio4.bancol.avaluos.dto.OfertaPHDTO;
import com.helio4.bancol.avaluos.modelo.OfertaLoteSinConstruccion;
public interface OfertaLoteSinConstruccionRepository extends JpaRepository<OfertaLoteSinConstruccion, Long>{

	@Query("SELECT  NEW com.helio4.bancol.avaluos.dto.OfertaLoteSinConstruccionDTO(m.id) FROM OfertaLoteSinConstruccion m WHERE m.metodoValuacion.id =:metodoValuacionId ORDER BY  m.id")
	public List<OfertaLoteSinConstruccionDTO> encontrarPorMetodoValuacion(@Param("metodoValuacionId") Long metodoValuacionId );
	
	
}
