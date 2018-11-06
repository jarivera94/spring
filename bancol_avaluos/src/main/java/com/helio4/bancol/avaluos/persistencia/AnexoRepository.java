package com.helio4.bancol.avaluos.persistencia;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.helio4.bancol.avaluos.modelo.Anexo;

@Repository
public interface AnexoRepository extends JpaRepository<Anexo, Long> {
	
	@Query("SELECT f FROM Anexo f WHERE f.avaluo.id = :avaluoId ORDER BY f.orden")
	public List<Anexo> buscarAnexosAvaluo(@Param("avaluoId") Long avaluoId);

}
