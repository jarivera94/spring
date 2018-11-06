package com.helio4.bancol.avaluos.persistencia;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.helio4.bancol.avaluos.modelo.CalificacionHomologacion;

@Repository
public interface CalificacionHomologacionRepository extends JpaRepository<CalificacionHomologacion, Long> {

    @Query("SELECT c FROM CalificacionHomologacion c")
    public List<CalificacionHomologacion> encontrarCalificacionesHomologacion();
    
}
