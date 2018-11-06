package com.helio4.bancol.avaluos.persistencia;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.helio4.bancol.avaluos.dto.TarifaDTO;
import com.helio4.bancol.avaluos.modelo.Tarifa;

@Repository
public interface TarifaRepository extends JpaRepository<Tarifa, Long> {
//
//	@Query("SELECT t FROM Tarifa t WHERE t.entidad.id = :entidad")
//	public List<Tarifa> encontrarPorIdEntidad(@Param("entidad") Long entidad);
	
	@Query("SELECT NEW com.helio4.bancol.avaluos.dto.TarifaDTO( t.id, t.valorMinimo,t.valorMaximo,t.tarifa,t.entidad.id, t.tipoAvaluo.id,t.tipoAvaluo.nombre)  FROM Tarifa t WHERE t.entidad.id = :entidad")
	public List<TarifaDTO> encontrarPorIdEntidad(@Param("entidad") Long entidad);
	
	

	@Query("SELECT t FROM Tarifa t WHERE t.tipoAvaluo.id = :tipoAvaluo AND t.entidad.id = :entidad")
	public Tarifa encontrarPorIdTipoAvaluoIdEntidad(@Param("tipoAvaluo") Long idTipoAvaluo, @Param("entidad") Long idEntidad);

}
