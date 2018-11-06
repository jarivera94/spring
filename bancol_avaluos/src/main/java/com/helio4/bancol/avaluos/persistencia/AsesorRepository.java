package com.helio4.bancol.avaluos.persistencia;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.helio4.bancol.avaluos.modelo.Asesor;

public interface AsesorRepository extends JpaRepository<Asesor, Long> {

	@Query("SELECT a FROM Asesor a WHERE LOWER(a.nombre) LIKE LOWER(CONCAT('%',:texto,'%')) AND a.sucursal.id = :idEntidad")
	public List<Asesor> encontrarPorNombre(@Param("texto") String texto, @Param("idEntidad") Long idEntidad);

	@Query("SELECT a FROM Asesor a WHERE a.id = :id")
	public Asesor encontrarPorId(@Param("id") Long id);

	@Query
	public Asesor encontrarAsesor(@Param("nombre") String nombre, @Param("idEntidad") Long idEntidad, @Param("codigo") String codigoSucursal, @Param("celular") String celular, @Param("correo") String correo, @Param("telefono") String telefono);

	@Query("SELECT a FROM Asesor a WHERE a.correo=:correo")
	public Asesor encontrarPorCorreo(@Param("correo") String correo);
}
