package com.helio4.bancol.avaluos.persistencia;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.helio4.bancol.avaluos.dto.PermisoDTO;
import com.helio4.bancol.avaluos.modelo.Permiso;

@Repository
public interface PermisoRepository extends JpaRepository<Permiso, Long> {

    @Query("SELECT p FROM Permiso p left join fetch p.roles WHERE p.id = :permisoId")
    public Permiso encontrarPermisoConRoles(@Param("permisoId") Long permisoId);

    @Query("SELECT p FROM Permiso p WHERE p.nombre = :nombre")
    public Permiso encontrarPermisoPor(@Param("nombre") String nombre);

    @Query("SELECT NEW com.helio4.bancol.avaluos.dto.PermisoDTO(p.id, p.nombre) FROM Permiso p JOIN p.roles r WHERE r.nombre = :nombreRol")
    public List<PermisoDTO> encontrarPorRol(@Param("nombreRol") String nombreRol);

    @Query("SELECT NEW com.helio4.bancol.avaluos.dto.PermisoDTO(p.id, p.nombre) FROM Permiso p WHERE p.nombre = :nombre")
    public PermisoDTO encontrarPor(@Param("nombre") String nombre);
}
