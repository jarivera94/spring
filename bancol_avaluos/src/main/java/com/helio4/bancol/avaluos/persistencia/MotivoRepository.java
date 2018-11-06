package com.helio4.bancol.avaluos.persistencia;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.helio4.bancol.avaluos.dto.MotivoDTO;
import com.helio4.bancol.avaluos.dto.OfertaLoteConConstruccionRentaDTO;
import com.helio4.bancol.avaluos.modelo.Motivo;

/**
 * Repository Motivo
 * 
 * @author <a href="mailto:j.j.o.c007@gmail.com">Juan Jose Orjuela Castillo</a>
 * 
 */
@Repository
public interface MotivoRepository extends JpaRepository<Motivo, Long> {
	
	@Query("SELECT  NEW com.helio4.bancol.avaluos.dto.MotivoDTO(m.codigo, m.nombre, m.abreviatura, m.prefijo, m.estado) FROM Motivo m WHERE m.estado = true and m.entidad.id = :entidadId  ORDER BY  m.codigo ")
	public List<MotivoDTO> getMotivosByEntidad(@Param("entidadId") Long entidadId );
	

}