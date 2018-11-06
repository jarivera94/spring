package com.helio4.bancol.avaluos.persistencia;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.helio4.bancol.avaluos.dto.EstadoAvaluoDTO;
import com.helio4.bancol.avaluos.modelo.EstadoAvaluo;

@Repository
public interface EstadoAvaluoRepository extends JpaRepository<EstadoAvaluo, Long> {
	
	@Query("SELECT  NEW  com.helio4.bancol.avaluos.dto.EstadoAvaluoDTO(" +
			"e.avaluo.id, e.estado, e.fechaEstado, e.usuario.nombres, e.usuario.apellidos, " +
			"e.usuario.rol.nombre, e.justificacionRechazo ) "
			+ "FROM EstadoAvaluo e WHERE e.avaluo.id = :id ORDER BY e.fechaEstado")
	List<EstadoAvaluoDTO> buscarEstadosAvaluo(@Param("id") Long id);
	
	@Query("SELECT ea.justificacionRechazo FROM EstadoAvaluo ea WHERE ea.fechaEstado = "
			+ "(SELECT MAX(ea.fechaEstado) FROM EstadoAvaluo ea WHERE (ea.avaluo.id = :id) AND (ea.estado = 9)) "
			+ "AND ea.avaluo.id = :id")
	String buscarUltimasCorreccionesSolicitadas(@Param("id") Long id);

	@Query("SELECT e FROM EstadoAvaluo e JOIN e.avaluo a WHERE a.codigoExterno = :codigoExterno AND e.estadoActual = true AND e.estado = 11")
	EstadoAvaluo buscarPorCodigoExterno(@Param("codigoExterno") String codigoExterno);
	
	@Query("SELECT e FROM EstadoAvaluo e JOIN e.avaluo a WHERE a.codigoExterno = :codigoExterno AND a.entidad.codigoTinsa = :codigoEntidad AND e.estadoActual = true ")
	EstadoAvaluo buscarEstadoActualPorCodigoExterno(@Param("codigoExterno") String codigoExterno, @Param("codigoEntidad") Integer codigoEntidad);
}
