package com.helio4.bancol.avaluos.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.helio4.bancol.avaluos.modelo.Modificacion;

@Repository
public interface ModificacionRepository extends JpaRepository<Modificacion, Long> {

}
