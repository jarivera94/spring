package com.helio4.bancol.avaluos.persistencia;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.helio4.bancol.avaluos.dto.PermisoDTO;
import com.helio4.bancol.avaluos.dto.RolDTO;
import com.helio4.bancol.avaluos.modelo.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

    @Query("SELECT NEW com.helio4.bancol.avaluos.dto.RolDTO( "
            + "r.id, r.nombre, r.paginaInicio.id, r.paginaInicio.nombre) "
            + "FROM Rol r")
    public List<RolDTO> encontrarRoles();

    @Query("SELECT r FROM Rol r WHERE r.nombre = :nombre")
    public Rol encontrarPorNombre(@Param("nombre") String nombre);

    @Query("SELECT NEW com.helio4.bancol.avaluos.dto.RolDTO( "
            + "r.id, r.nombre, r.paginaInicio.id, r.paginaInicio.nombre) "
            + "FROM Rol r WHERE r.nombre = :nombre")
    public RolDTO encontrarRolPorNombre(@Param("nombre") String nombre);
    
    @Query("SELECT NEW com.helio4.bancol.avaluos.dto.PermisoDTO(p) FROM Rol r JOIN r.permisos p WHERE r.id=:idRol")
    public List<PermisoDTO> encontrarPermisosPorRol(@Param("idRol") Long rolId);

}
