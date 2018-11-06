package com.helio4.bancol.avaluos.persistencia;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.helio4.bancol.avaluos.modelo.Documento;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long>{

	@Query("SELECT d  FROM Documento d WHERE d.avaluo.id=:avaluoId")
	public List<Documento> encontrarDocumentos(@Param("avaluoId") Long avaluoId);
	
}
