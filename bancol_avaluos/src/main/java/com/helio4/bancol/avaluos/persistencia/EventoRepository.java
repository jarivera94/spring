package com.helio4.bancol.avaluos.persistencia;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.helio4.bancol.avaluos.dto.EventoDTO;
import com.helio4.bancol.avaluos.modelo.Evento;
import com.helio4.bancol.avaluos.modelo.Usuario;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

	@Query("SELECT e.usuario FROM Evento e WHERE e.fechaHoraInicio between :fechaInicial AND :fechaFinal AND e.fechaHoraFin between :fechaInicial AND :fechaFinal")
	public List<Usuario> buscarUsuariosEventosCercanos(@Param("fechaInicial") Date fechaInicial, @Param("fechaFinal") Date fechaFinal);

    @Query("SELECT NEW com.helio4.bancol.avaluos.dto.EventoDTO"
            + "(e.id, e.descripcion, e.fechaHoraInicio, e.fechaHoraFin,"
            + "u.nombres, u.apellidos, a.codigoExterno)"
            + " FROM Evento e "
            + " JOIN e.usuario u "
            + "	JOIN e.avaluo a  "
            + "	JOIN a.estadosAvaluo ea"
            + " WHERE u.id.tipoDocumentoIdentificacion = :tipoIdentificacion "
            + " AND u.id.numeroDocumento = :numeroDocumento  "
            + " AND ea.estado = 4  AND a.id != :avaluoId AND e.avaluo.id != :avaluoId")
	public List<EventoDTO> encontrarEventosUsuario(  
			@Param("numeroDocumento") Long numeroDocumento,
	        @Param("tipoIdentificacion") int tipoIdentificacion,
	        @Param("avaluoId") Long avaluoId
	      );

    @Query("SELECT NEW com.helio4.bancol.avaluos.dto.EventoDTO"
            + "(e.id, e.descripcion, e.fechaHoraInicio, e.fechaHoraFin,"
            + "u.nombres, u.apellidos)"
            + " FROM Evento e JOIN e.usuario u "
            + " WHERE u.id.tipoDocumentoIdentificacion = :tipoIdentificacion "
            + " AND u.id.numeroDocumento = :numeroDocumento "
            + " AND e.fechaHoraInicio > CURRENT_DATE ")
    public List<EventoDTO> encontrarEventosUsuario(
            @Param("tipoIdentificacion") int tipoIdentificacion,
            @Param("numeroDocumento") Long numeroDocumento);

	@Query(value="SELECT e.evento_id, e.fecha_hora_inicio, e.fecha_hora_fin, e.descripcion, "
			+ "e.tipo_documento_usuario, e.numero_documento_usuario, u.nombres, u.apellidos, e.avaluo_id, a.codigo_externo "
			+ "FROM avaluos.evento e, avaluos.avaluo a, avaluos.usuario u "
			+ "WHERE e.avaluo_id = a.avaluo_id "
			+ "AND e.tipo_documento_usuario = u.tipo_documento_identificacion "
			+ "AND e.numero_documento_usuario = u.numero_documento "
			+ "AND e.fecha_hora_inicio > now() - interval '30 days' "
			+ "AND CAST( e.tipo_documento_usuario AS text )||'-'||CAST( e.numero_documento_usuario AS text ) IN (:documentos) "
			+ "ORDER BY e.tipo_documento_usuario, e.numero_documento_usuario ", nativeQuery=true)
	public List<Object> encontrarEventosUsuarios(@Param("documentos") List<String> documentos);

    @Query("SELECT COUNT(*) "
            + "FROM Evento e, EstadoAvaluo ea "
            + "WHERE e.avaluo.id = ea.avaluo.id "
            + "AND e.avaluo.id = :avaluoId "
            + "AND ea.estadoActual = true "
            + "AND e.usuario.id.numeroDocumento = :numeroDocumentoPerito "
            + "AND e.usuario.id.tipoDocumentoIdentificacion = :tipoDocumentoPerito "
            + " ")
    public int contarEventosDelCicloActualEstadosPorAvaluo(@Param("avaluoId") Long avaluoId,
            @Param("tipoDocumentoPerito") Integer tipoIdentificacionPerito,
            @Param("numeroDocumentoPerito") Long numeroDocumentoPerito);

    @Query("SELECT NEW com.helio4.bancol.avaluos.dto.EventoDTO( "
            + "e.id, e.descripcion, e.fechaHoraInicio, e.fechaHoraFin, "
            + "e.avaluo.id, e.usuario.id.tipoDocumentoIdentificacion, "
            + "e.usuario.id.numeroDocumento) "
            + "FROM Evento e, EstadoAvaluo ea "
            + "WHERE e.avaluo.id = ea.avaluo.id "
            + "AND e.avaluo.id = :avaluoId "
            + "AND ea.estadoActual = true "
            + "AND e.usuario.id.numeroDocumento = :numeroDocumentoPerito "
            + "AND e.usuario.id.tipoDocumentoIdentificacion = :tipoDocumentoPerito "
            + " "
            + "ORDER BY e.fechaHoraInicio DESC")
    public List<EventoDTO> encontrarCitasAReprogramar(@Param("avaluoId") Long avaluoId,
            @Param("tipoDocumentoPerito") Integer tipoIdentificacionPerito,
            @Param("numeroDocumentoPerito") Long numeroDocumentoPerito);

    @Query("SELECT NEW com.helio4.bancol.avaluos.dto.EventoDTO( "
            + "e.id, e.descripcion, e.fechaHoraInicio, e.fechaHoraFin, "
            + "e.avaluo.id, e.usuario.id.tipoDocumentoIdentificacion, "
            + "e.usuario.id.numeroDocumento) "
            + "FROM Evento e "
            + "WHERE e.avaluo.id = :avaluoId "
            + "ORDER BY e.fechaHoraInicio DESC")
    public List<EventoDTO> encontrarUltimoEventoAvaluo(@Param("avaluoId") Long avaluoId);
}
