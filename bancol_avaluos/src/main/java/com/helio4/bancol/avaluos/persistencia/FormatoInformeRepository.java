package com.helio4.bancol.avaluos.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.helio4.bancol.avaluos.modelo.FormatoInforme;

@Repository
public interface FormatoInformeRepository extends JpaRepository<FormatoInforme, Long> {

	@Query(value="SELECT fi.formato_informe_id FROM avaluos.formato_informe fi WHERE fi.avaluo_id = :idAvaluo", nativeQuery = true)
	Long encontrarIdFormatoPorIdAvaluo(@Param("idAvaluo") Long idAvaluo);

	@Query("SELECT f.sometidoAPropiedadHorizontal FROM FormatoInformeHipotecario f WHERE f.avaluo.id = :avaluoId")
    Boolean esSometidoPropiedadHorizontal(@Param("avaluoId") Long id);

}
