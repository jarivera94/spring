package com.helio4.bancol.avaluos.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.helio4.bancol.avaluos.modelo.Inmueble;

@Repository
public interface InmuebleRepository extends JpaRepository<Inmueble, Long> {
}
