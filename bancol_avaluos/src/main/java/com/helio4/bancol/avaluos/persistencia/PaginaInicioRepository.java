package com.helio4.bancol.avaluos.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.helio4.bancol.avaluos.modelo.PaginaInicio;

@Repository
public interface PaginaInicioRepository extends JpaRepository<PaginaInicio, Long> {
	
	@Query("SELECT pi FROM PaginaInicio pi left join fetch pi.roles WHERE pi.id = :paginaInicioId")
	public PaginaInicio encontrarPaginaInicioConRoles(@Param("paginaInicioId") Long paginaInicioId);
}
