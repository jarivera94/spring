package com.helio4.bancol.avaluos.persistencia;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.helio4.bancol.avaluos.modelo.Anexo;
import com.helio4.bancol.avaluos.modelo.Fotografia;

@Repository
public interface FotografiaRepository extends JpaRepository<Fotografia, Long> {
	
	@Query("SELECT f FROM Fotografia f WHERE f.avaluo.id = :avaluoId ORDER BY f.orden")
	public List<Fotografia> buscarFotografiasAvaluo(@Param("avaluoId") Long avaluoId);

}
