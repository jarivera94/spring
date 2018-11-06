package com.helio4.bancol.avaluos.persistencia;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.helio4.bancol.avaluos.modelo.Sucursal;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Long> {

	@Query("SELECT s FROM Sucursal s WHERE LOWER(s.codigo) LIKE LOWER(CONCAT(:codigo,'%')) AND s.entidad.id = :idEntidad AND s.activo = true")
	public List<Sucursal> encontrarPorCodigo(@Param("codigo") String codigo, @Param("idEntidad") Long idEntidad);
	
	@Query("SELECT s FROM Sucursal s WHERE LOWER(s.nombre) LIKE LOWER(CONCAT(:nombre,'%')) AND s.entidad.id = :idEntidad AND s.activo = true")
	public List<Sucursal> encontrarPorNombre(@Param("nombre") String nombre, @Param("idEntidad") Long idEntidad);
	
	/**
	 * 
	 */
	@Query("SELECT s FROM Sucursal s WHERE s.entidad.id = :idEntidad")
	public List<Sucursal> encontrarSucursalesPorEntidad(@Param("idEntidad") Long idEntidad);
	
	@Query("SELECT s FROM Sucursal s WHERE s.entidad.id = :idEntidad AND s.codigo = :codigo")
	public Sucursal encontrarSucursal(@Param("idEntidad") Long idEntidad, @Param("codigo") String codigo);
}
