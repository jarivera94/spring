package com.helio4.bancol.avaluos.persistencia;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.helio4.bancol.avaluos.modelo.PisoFactor;

@Repository
public interface PisoFactorRepository extends JpaRepository<PisoFactor, Long> {

    @Query("SELECT p FROM PisoFactor p")
    public List<PisoFactor> encontrarPisoFactores();
    
}
