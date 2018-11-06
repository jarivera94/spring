package com.helio4.bancol.avaluos.persistencia;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.helio4.bancol.avaluos.modelo.MetodoValuacion;

@Repository
public interface MetodoValuacionRepository extends JpaRepository<MetodoValuacion, Long> {
	
	@Query("SELECT m FROM MetodoValuacion m WHERE m.avaluo.id =:avaluoId")
	public List<MetodoValuacion> encontrarPorAvaluoId(@Param("avaluoId") Long idAvaluo );
}
