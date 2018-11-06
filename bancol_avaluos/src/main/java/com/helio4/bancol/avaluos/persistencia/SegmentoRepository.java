package com.helio4.bancol.avaluos.persistencia;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.helio4.bancol.avaluos.dto.SegmentoDTO;
import com.helio4.bancol.avaluos.modelo.Segmento;

@Repository
public interface SegmentoRepository extends JpaRepository<Segmento, Long> {

	@Query("SELECT s FROM Segmento s WHERE LOWER(s.nombre) LIKE LOWER(CONCAT('%',:nombre,'%'))")
	public List<Segmento> encontrarPorNombre(@Param("nombre") String nombre);

	@Query("SELECT NEW com.helio4.bancol.avaluos.dto.SegmentoDTO(s.id, s.nombre)"
           + " FROM Segmento s WHERE s.entidad.id = :idEntidad AND s.activo = true")
	public List<SegmentoDTO> encontrarSegmentosPorEntidad(@Param("idEntidad") Long idEntidad);

	@Query("SELECT s.cobradoPorBancol FROM Segmento s WHERE s.id = :segmentoId")
    public Boolean cargarSegmentoCobradoAlBanco(@Param("segmentoId") Long segmentoId);
}
