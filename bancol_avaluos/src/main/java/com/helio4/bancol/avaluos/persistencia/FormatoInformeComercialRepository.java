package com.helio4.bancol.avaluos.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.helio4.bancol.avaluos.modelo.FormatoInformeComercial;

@Repository
public interface FormatoInformeComercialRepository
	extends JpaRepository<FormatoInformeComercial, Long> {

}
