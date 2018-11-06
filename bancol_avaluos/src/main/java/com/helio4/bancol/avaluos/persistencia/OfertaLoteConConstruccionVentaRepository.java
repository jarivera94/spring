package com.helio4.bancol.avaluos.persistencia;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.helio4.bancol.avaluos.dto.OfertaLoteConConstruccionVentaDTO;
import com.helio4.bancol.avaluos.modelo.OfertaLoteConConstruccionVenta;

public interface OfertaLoteConConstruccionVentaRepository extends JpaRepository<OfertaLoteConConstruccionVenta, Long>{

	@Query("SELECT  NEW com.helio4.bancol.avaluos.dto.OfertaLoteConConstruccionVentaDTO(m.id) FROM OfertaLoteConConstruccionVenta m WHERE m.metodoValuacion.id =:metodoValuacionId ORDER BY  m.id")
	public List<OfertaLoteConConstruccionVentaDTO> encontrarPorMetodoValuacion(@Param("metodoValuacionId") Long metodoValuacionId );
	

}
