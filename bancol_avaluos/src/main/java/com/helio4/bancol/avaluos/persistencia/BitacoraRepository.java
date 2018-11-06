package com.helio4.bancol.avaluos.persistencia;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.helio4.bancol.avaluos.dto.BitacoraDTO;
import com.helio4.bancol.avaluos.modelo.Bitacora;

@Repository
public interface BitacoraRepository extends JpaRepository<Bitacora, Long> {
	
	@Query("SELECT NEW com.helio4.bancol.avaluos.dto.BitacoraDTO( b.estado,b.fecha,b.usuario,b.observaciones,"
			+ "b.notificacionCreador, b.notificacionPerito, b.notificacionRevisor, b.notificacionCliente, b.notificacionPersonaRecibePerito, "
			+ "b.notificacionAsesor,b.notificacionSeguidor  ) "
			+ "FROM Bitacora b WHERE b.avaluo = :id" )
	public List<BitacoraDTO> encontrarBitacoraPorAvaluo(@Param("id") Long id);

}
