package com.helio4.bancol.avaluos.persistencia;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.helio4.bancol.avaluos.modelo.TipoAvaluo;
import com.helio4.bancol.avaluos.dto.TipoAvaluoDTO;

@Repository
public interface TipoAvaluoRepository extends JpaRepository<TipoAvaluo, Long> {

	@Query("SELECT NEW com.helio4.bancol.avaluos.dto.TipoAvaluoDTO(t.id, t.nombre) FROM TipoAvaluo t WHERE LOWER(t.nombre) = LOWER(:nombre)")
	public TipoAvaluoDTO encontrarPorNombre(@Param("nombre") String nombre);

    @Query("SELECT NEW com.helio4.bancol.avaluos.dto.TipoAvaluoDTO(t.id, t.nombre) FROM TipoAvaluo t")
	public List<TipoAvaluoDTO> encontrarTiposAvaluo();

}
