package com.helio4.bancol.avaluos.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.helio4.bancol.avaluos.modelo.AvaluoRemate;

@Repository
public interface AvaluoRemateRepository extends JpaRepository<AvaluoRemate, Long> {

	
}
