package com.helio4.bancol.avaluos.persistencia;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.helio4.bancol.avaluos.modelo.AvaluoComercial;

@Repository
public interface AvaluoComercialRepository extends JpaRepository<AvaluoComercial, Long> {

	@Query("SELECT a FROM AvaluoComercial a left join fetch"
			+ " a.formatoInforme f left join fetch a.areas"
			+ " left join fetch a.perito left join fetch"
			+ " a.estadosAvaluo left join fetch a.entidad"
			+ " left join fetch a.cliente cliente"
			+ " left join fetch cliente.avaluos"
			+ " left join fetch f.inmuebles"
			+ " left join fetch f.explotacionesEconomicas"
			+ " left join fetch f.servidumbrez"
			+ " left join fetch f.viasAcceso"
			+ " left join fetch f.metodosValuacion WHERE a.id = :avaluoId")
	public AvaluoComercial encontrarAvaluoConAreas(@Param("avaluoId") Long avaluoId);
	
	@Query("SELECT a FROM Avaluo a left join fetch a.fotografias left join fetch a.perito left join fetch a.estadosAvaluo left join fetch a.entidad left join fetch a.cliente cliente left join fetch cliente.avaluos WHERE a.id = :avaluoId")
	public AvaluoComercial encontrarAvaluoConFotografias(@Param("avaluoId") Long avaluoId);
	
	@Query("SELECT e.avaluo FROM EstadoAvaluo e WHERE to_timestamp(to_char(e.fechaEstado, 'YYYY-MM-DD'), 'YYYY-MM-DD') BETWEEN to_timestamp(:fechaInicial, 'YYYY-MM-DD') AND to_timestamp(:fechaFinal, 'YYYY-MM-DD') AND e.estadoActual = true AND e.class = '9'")
	public List<AvaluoComercial> encontrarPorFechaTerminacion(@Param("fechaInicial") Date fechaInicial,@Param("fechaFinal") Date fechaFinal);

}
