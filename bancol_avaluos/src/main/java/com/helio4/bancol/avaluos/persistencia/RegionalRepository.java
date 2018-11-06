package com.helio4.bancol.avaluos.persistencia;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.helio4.bancol.avaluos.dto.RegionalDTO;
import com.helio4.bancol.avaluos.modelo.Regional;

@Repository
public interface RegionalRepository extends JpaRepository<Regional, Long> {

	@Query("SELECT r FROM Regional r WHERE LOWER(r.nombre) = LOWER(:nombre)")
	public Regional encontrarPorNombre(@Param("nombre") String nombre);

	@Query("SELECT r FROM Regional r left join fetch r.usuarios WHERE r.id = :regionalId")
	public Regional encontrarRegionalConUsuarios(@Param("regionalId") Long regionalId);

    @Query("SELECT NEW com.helio4.bancol.avaluos.dto.RegionalDTO(r.id, r.nombre) FROM Regional r")
	public List<RegionalDTO> encontrarRegionales();
}
