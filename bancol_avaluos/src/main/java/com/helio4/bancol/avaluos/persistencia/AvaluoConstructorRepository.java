package com.helio4.bancol.avaluos.persistencia;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.helio4.bancol.avaluos.modelo.AvaluoConstructor;

@Repository
public interface AvaluoConstructorRepository extends JpaRepository<AvaluoConstructor, Long> {
	
	@Query("SELECT a FROM AvaluoConstructor a left join fetch a.areas left join fetch a.perito left join fetch a.estadosAvaluo left join fetch a.entidad left join fetch a.cliente cliente left join fetch cliente.avaluos WHERE a.id = :avaluoId")
	public AvaluoConstructor encontrarAvaluoConAreas(@Param("avaluoId") Long avaluoId);
	
	@Query("SELECT a FROM AvaluoConstructor a left join fetch a.fotografias left join fetch a.perito left join fetch a.estadosAvaluo left join fetch a.entidad left join fetch a.cliente cliente left join fetch cliente.avaluos WHERE a.id = :avaluoId")
	public AvaluoConstructor encontrarAvaluoConFotografias(@Param("avaluoId") Long avaluoId);
	
	@Query("SELECT e.avaluo FROM EstadoAvaluo e WHERE to_timestamp(to_char(e.fechaEstado, 'YYYY-MM-DD'), 'YYYY-MM-DD') BETWEEN to_timestamp(:fechaInicial, 'YYYY-MM-DD') AND to_timestamp(:fechaFinal, 'YYYY-MM-DD') AND e.estadoActual = true AND e.class = '9'")
	public List<AvaluoConstructor> encontrarPorFechaTerminacion(@Param("fechaInicial") Date fechaInicial,@Param("fechaFinal") Date fechaFinal);
	
}
