package com.helio4.bancol.avaluos.persistencia;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.helio4.bancol.avaluos.modelo.CronogramaObra;

@Repository
public interface CronogramaObraRepository extends JpaRepository<CronogramaObra, Long> {

	@Query("SELECT e FROM CronogramaObra e WHERE e.tipoCosto = :tipoCosto")
	public List<CronogramaObra> encontrarPorTipoCosto(@Param("tipoCosto") Integer tipoCosto);
	
	@Query("SELECT e FROM CronogramaObra e WHERE e.avaluo.id = :idAvaluo")
	public List<CronogramaObra> encontrarPorAvaluo(@Param("idAvaluo") Long idAvaluo);
}
