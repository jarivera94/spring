package com.helio4.bancol.avaluos.persistencia;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.helio4.bancol.avaluos.dto.OfertaLoteConConstruccionRentaDTO;
import com.helio4.bancol.avaluos.dto.OfertaLoteConConstruccionVentaDTO;
import com.helio4.bancol.avaluos.modelo.OfertaLoteConConstruccionRenta;

public interface OfertaLoteConConstruccionRentaRepository extends JpaRepository<OfertaLoteConConstruccionRenta, Long> {

	@Query("SELECT  NEW com.helio4.bancol.avaluos.dto.OfertaLoteConConstruccionRentaDTO(m.id) FROM OfertaLoteConConstruccionRenta m WHERE m.metodoValuacion.id =:metodoValuacionId ORDER BY  m.id")
	public List<OfertaLoteConConstruccionRentaDTO> encontrarPorMetodoValuacion(@Param("metodoValuacionId") Long metodoValuacionId );
	
}
