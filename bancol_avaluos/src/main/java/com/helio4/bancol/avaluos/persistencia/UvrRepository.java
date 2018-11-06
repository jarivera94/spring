package com.helio4.bancol.avaluos.persistencia;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.helio4.bancol.avaluos.modelo.Uvr;

@Repository
public interface UvrRepository extends JpaRepository<Uvr, Long> {
	
	@Query("SELECT u FROM Uvr u WHERE to_timestamp(to_char(u.fechaUvr, 'YYYY-MM-DD'), 'YYYY-MM-DD') = to_timestamp(:fecha, 'YYYY-MM-DD')")
	public Uvr encontrarPor(@Param("fecha") Date fecha);
	
	@Query("SELECT u FROM Uvr u WHERE to_char(u.fechaUvr, 'YYYY-MM-DD') = to_char(CURRENT_DATE, 'YYYY-MM-DD')")
	public Uvr encontrarUvrActual();

}
