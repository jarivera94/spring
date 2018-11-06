package com.helio4.bancol.avaluos.persistencia;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.helio4.bancol.avaluos.modelo.Propietario;
import com.helio4.bancol.avaluos.modelo.Usuario.DocumentoIdentificacion;

@Repository
public interface PropietarioRepository extends JpaRepository<Propietario, DocumentoIdentificacion> {
	
	@Query("SELECT p FROM Propietario p JOIN p.inmuebles i WHERE i.id = :inmuebleId")
	public List<Propietario> encontrarPropietariosPorImueble(@Param("inmuebleId") Long inmuebleId);
	
}
