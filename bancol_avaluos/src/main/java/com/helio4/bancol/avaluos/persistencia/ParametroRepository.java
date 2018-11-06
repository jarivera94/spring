package com.helio4.bancol.avaluos.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.helio4.bancol.avaluos.modelo.Parametro;

@Repository
public interface ParametroRepository extends JpaRepository<Parametro, Long> {
	
	@Query("SELECT p FROM Parametro p WHERE p.nombre = :nombre")
	public Parametro encontrarPorNombre(@Param("nombre") String nombre);

}
