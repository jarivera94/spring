package com.helio4.bancol.avaluos.persistencia;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.helio4.bancol.avaluos.modelo.AvaluoHipotecario;

@Repository
public interface AvaluoHipotecarioRepository extends JpaRepository<AvaluoHipotecario, Long> {
	
	@Query("SELECT a FROM Avaluo a left join fetch a.areas left join fetch a.perito left join fetch a.estadosAvaluo left join fetch a.entidad left join fetch a.cliente cliente left join fetch cliente.avaluos WHERE a.id = :avaluoId")
	public AvaluoHipotecario encontrarAvaluoConAreas(@Param("avaluoId") Long avaluoId);
	
	@Query("SELECT a FROM Avaluo a left join fetch a.fotografias left join fetch a.perito left join fetch a.estadosAvaluo left join fetch a.entidad left join fetch a.cliente cliente left join fetch cliente.avaluos WHERE a.id = :avaluoId")
	public AvaluoHipotecario encontrarAvaluoConFotografias(@Param("avaluoId") Long avaluoId);
	
	@Query("SELECT e.avaluo FROM EstadoAvaluo e WHERE to_timestamp(to_char(e.fechaEstado, 'YYYY-MM-DD'), 'YYYY-MM-DD') BETWEEN to_timestamp(:fechaInicial, 'YYYY-MM-DD') AND to_timestamp(:fechaFinal, 'YYYY-MM-DD') AND e.estadoActual = true AND e.class = '11'")
	public List<AvaluoHipotecario> encontrarPorFechaTerminacion(@Param("fechaInicial") Date fechaInicial,@Param("fechaFinal") Date fechaFinal);
	
	@Query("SELECT e.avaluo FROM EstadoAvaluo e WHERE to_timestamp(to_char(e.fechaEstado, 'YYYY-MM-DD'), 'YYYY-MM-DD') BETWEEN to_timestamp(:fechaInicial, 'YYYY-MM-DD') AND to_timestamp(:fechaFinal, 'YYYY-MM-DD') AND e.estadoActual = true AND e.class = '11' AND e.avaluo.tipoAvaluo='1' AND e.avaluo.entidad.nombre = :nombreEntidad")
	public List<AvaluoHipotecario> encontrarPorFechaTerminacionHipotecarios(@Param("nombreEntidad") String nombreEntidad ,@Param("fechaInicial") Date fechaInicial,@Param("fechaFinal") Date fechaFinal);
}
