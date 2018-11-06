package com.helio4.bancol.avaluos.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.helio4.bancol.avaluos.modelo.Prorroga;

@Repository
public interface ProrrogaRepository extends JpaRepository<Prorroga, Long> {
}
