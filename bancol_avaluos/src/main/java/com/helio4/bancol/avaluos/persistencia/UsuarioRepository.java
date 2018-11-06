package com.helio4.bancol.avaluos.persistencia;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;
import com.helio4.bancol.avaluos.modelo.EstadoAvaluo;
import com.helio4.bancol.avaluos.modelo.Usuario;
import com.helio4.bancol.avaluos.modelo.Usuario.DocumentoIdentificacion;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, DocumentoIdentificacion>{

	@Query("SELECT NEW com.helio4.bancol.avaluos.dto.UsuarioDTO( "
            + "u.usuarioVigente, u.id.tipoDocumentoIdentificacion, "
            + "u.id.numeroDocumento, u.nombres, u.apellidos, "
            + "u.email, u.nombreUsuario, "
            + "r.id, r.nombre) "
            + "FROM Usuario u INNER JOIN u.rol r")
	public List<UsuarioDTO> encontrarUsuarios();

	//@Query("SELECT u FROM Usuario u left join fetch u.eventos WHERE LOWER(u.nombreUsuario) = LOWER(:nombreUsuario)")
	@Query("SELECT u.contrasena FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario")
	public String encontrarPass(@Param("nombreUsuario") String nombreUsuario);

	@Query("SELECT u FROM Usuario u WHERE LOWER(u.nombreUsuario) = LOWER(:nombreUsuario)")
	public Usuario encontrarPor(@Param("nombreUsuario") String nombreUsuario);

    @Query("SELECT COUNT(*) > 0 FROM Usuario u WHERE LOWER(u.nombreUsuario) = LOWER(:alias)")
    public Boolean verificarAliasExistente(@Param("alias") String alias);

    @Query("SELECT COUNT(*) > 0 FROM Usuario u WHERE u.id.numeroDocumento = :numeroDocumento "
            + "AND u.id.tipoDocumentoIdentificacion = :tipoDocumentoIdentificacion")
    public Boolean verificarNumeroTipoDocumentoExistente(
            @Param("tipoDocumentoIdentificacion") Integer tipoDocumentoIdentificacion,
            @Param("numeroDocumento") Long numeroDocumento);

	@Query("SELECT u FROM Usuario u WHERE u.rol.id = :id")
	public List<Usuario> encontrarPorRol(@Param("id") Long id);

	@Query("select NEW com.helio4.bancol.avaluos.dto.UsuarioDTO( "
            + "u.usuarioVigente, u.id.tipoDocumentoIdentificacion, "
            + "u.id.numeroDocumento, u.nombres, u.apellidos, "
            + "u.email, u.nombreUsuario, "
            + "r.id, r.nombre) "
            + "FROM Usuario u INNER JOIN u.rol r WHERE u.id.numeroDocumento = :numeroDocumento")
	public UsuarioDTO encontrarPorNumeroDocumento(@Param("numeroDocumento") Long numeroDocumento);
	
	@Query("SELECT u FROM Usuario u WHERE u.id.numeroDocumento = :numeroDocumento")
	public Long numeroAvaluosAsignados(@Param("numeroDocumento") Integer numeroDocumento);

	@Query("SELECT u FROM Usuario u "
			+ "JOIN u.rol.permisos p "
			+ "LEFT JOIN u.divipolas d "
            + "WHERE p.nombre = 'Aceptar Caso'  "
            + "AND u.tipoVia = :tipoVia AND cast(u.numeroVia as integer) "
            + "BETWEEN :cuadrasViaAtras AND :cuadrasViaAdelante "
            + "AND cast(u.numeroViaGeneradora as integer) "
            + "BETWEEN :cuadrasViaGenAtras AND :cuadrasViaGenAdelante "
            + "AND u.activoParaAsignacion = true "
            + "AND u.usuarioVigente = true "
            + "AND  d.id = :ciudad" )
	public List<Usuario> buscarPeritosDisponibles(
            @Param("ciudad") Long divipolaId,
            @Param("tipoVia") String tipoVia,
            @Param("cuadrasViaAdelante") int cuadrasViaAdelante,
            @Param("cuadrasViaAtras") int cuadrasViaAtras,
            @Param("cuadrasViaGenAdelante") int cuadrasViaGenAdelante,
            @Param("cuadrasViaGenAtras") int cuadrasViaGenAtras);

	@Query("SELECT e.usuario FROM Evento e "
			+ "JOIN e.avaluo a "
            + "JOIN e.usuario u "
            + "JOIN u.rol.permisos p "
            + "LEFT JOIN u.divipolas d "
            + "WHERE a.numeroVia IS NOT NULL and a.numeroViaGeneradora IS NOT NULL "
            + "AND to_timestamp(to_char(e.fechaHoraInicio, 'YYYY-MM-DD'), "
            + "'YYYY-MM-DD') BETWEEN to_timestamp(:fechaInicial, 'YYYY-MM-DD') "
            + "AND to_timestamp(:fechaFinal, 'YYYY-MM-DD') "
            + "AND d.id = :ciudad "
            + "AND (p.nombre = 'Aceptar Caso' "
            + "AND a.tipoVia = :tipoVia "
            + "AND cast(a.numeroVia as integer) between :cuadrasViaAtras "
            + "AND :cuadrasViaAdelante AND cast(a.numeroViaGeneradora as integer) "
            + "between :cuadrasViaGenAtras AND :cuadrasViaGenAdelante) "
            + "AND u.activoParaAsignacion = true "
            + "AND u.usuarioVigente = true")
    public List<Usuario> buscarPeritosDisponibles(
            @Param("fechaInicial") Date fechaInicial,
            @Param("fechaFinal") Date fechaFinal,
            @Param("ciudad") Long divipolaId,
            @Param("tipoVia") String tipoVia,
            @Param("cuadrasViaAdelante") int cuadrasViaAdelante,
            @Param("cuadrasViaAtras") int cuadrasViaAtras,
            @Param("cuadrasViaGenAdelante") int cuadrasViaGenAdelante,
            @Param("cuadrasViaGenAtras") int cuadrasViaGenAtras);

	@Query("SELECT e.usuario  FROM Evento e "
			+ "JOIN e.avaluo a "
            + "JOIN e.usuario u "
            + "JOIN u.rol.permisos p "
            + "LEFT JOIN u.divipolas d "
            + "WHERE a.numeroVia IS NOT NULL "
            + "AND a.numeroViaGeneradora IS NOT NULL "
            + "AND to_timestamp(to_char(e.fechaHoraInicio, 'YYYY-MM-DD'), "
            + "'YYYY-MM-DD') between to_timestamp(:fechaInicial, 'YYYY-MM-DD') "
            + "AND to_timestamp(:fechaFinal, 'YYYY-MM-DD') "
            + "AND (p.nombre = 'Aceptar Caso' "
            + "AND a.tipoVia = :tipoVia) "
            + "AND u.activoParaAsignacion = true "
            + "AND u.usuarioVigente = true "
            + "AND d.id = :ciudad")
	public List<Usuario> buscarPeritosDisponiblesDias(
            @Param("fechaInicial") Date fechaInicial,
            @Param("fechaFinal") Date fechaFinal,
            @Param("ciudad") Long divipolaId,
            @Param("tipoVia") String tipoVia);

    @Query("SELECT u FROM Usuario u "
    		+ "JOIN u.rol.permisos p "
    		+ "JOIN u.divipolas d  "
            + "WHERE p.nombre = 'Aceptar Caso' "
            + "AND u.activoParaAsignacion = true "
            + "AND u.usuarioVigente = true "
            + "AND d.id = :ciudad")
	public List<Usuario> buscarPeritosDisponibles(@Param("ciudad") Long divipolaId);

	@Query("SELECT u FROM Usuario u JOIN u.regionales r "
            + "JOIN u.rol.permisos p WHERE p.nombre IN ('Aceptar Caso') AND r.id = :regionalId "
            + "AND u.activoParaAsignacion = true "
            + "AND u.usuarioVigente = true")
	public List<Usuario> buscarPeritosDisponiblesPorRegional(@Param("regionalId") Long regionalId);

	@Query("SELECT e FROM EstadoAvaluo e WHERE e.perito.id.tipoDocumentoIdentificacion = :tipoDocumentoIdentificacion AND e.perito.id.numeroDocumento = :numeroDocumento AND e.estadoActual = true")
	public List<EstadoAvaluo> encontrarEstadosActuales(@Param("tipoDocumentoIdentificacion") Integer tipoDocumentoIdentificacion, @Param("numeroDocumento") Long numeroDocumento);

	@Query("SELECT u FROM Usuario u left join fetch u.regionales WHERE u.id = :usuarioId")
	public Usuario encontrarUsuarioConRegionales(@Param("usuarioId") DocumentoIdentificacion usuarioId);

	@Query("SELECT u FROM Usuario u left join fetch u.entidades e WHERE (u.id = :usuarioId) AND (e.activo = true)")
	public Usuario encontrarUsuarioConEntidades(@Param("usuarioId") DocumentoIdentificacion usuarioId);

	@Query("SELECT e.usuario FROM EstadoAvaluo e WHERE e.avaluo.id = :idAvaluo AND e.class = :estado ORDER BY e.fechaEstado DESC")
	public List<Usuario> encontrarUsuarioAvaluoPorEstado(@Param("idAvaluo") Long idAvaluo, @Param("estado") Integer estado);

	@Query("SELECT u FROM Usuario u inner join u.rol r left join r.permisos p WHERE (p.nombre = :permiso)")
	public List<Usuario> encontrarUsuariosPorPermiso(@Param("permiso") String permiso);
    
    /*@Query("SELECT NEW com.helio4.bancol.avaluos.dto.UsuarioDTO( "
            + "u.id.tipoDocumentoIdentificacion, u.id.numeroDocumento, "
            + "u.nombres, u.apellidos, u.email, u.nombreUsuario, "
            + "u.celular, u.direccion, r.id, r.nombre) "
            + "FROM Usuario u INNER JOIN u.rol r "
            + "LEFT JOIN r.permisos p LEFT JOIN u.regionales re "
            + "WHERE (p.nombre = :permiso) AND re.id = :regionalId")*/

    @Query("SELECT u FROM Usuario u INNER JOIN u.rol r "
            + "LEFT JOIN r.permisos p LEFT JOIN u.regionales re "
            + "WHERE (p.nombre = :permiso) AND re.id = :regionalId")
	public List<Usuario> encontrarUsuariosPorPermisoRegional(
            @Param("permiso") String permiso,
            @Param("regionalId") Long regionalId);

	@Query("SELECT u FROM Usuario u INNER JOIN u.rol r WHERE r.nombre = 'COORDINADOR_ABOGADOS'")
	public List<Usuario> encontrarCoordinadoresAbogados();

	@Query("SELECT u FROM Usuario u INNER JOIN u.rol r WHERE r.nombre = 'COORDINADOR_ASESORES'")
    public List<Usuario> encontrarCoordinadoresAsesores();

	@Query("SELECT u FROM Usuario u WHERE "
            + "(u.tipoDocumentoCoordinadorAsignado = :tipoDocCoordAsignado) "
            + "AND (u.numeroDocumentoCoordinadorAsignado = :docCoordAsignado)")
	public List<Usuario> encontrarUsuariosPorCoordAbogados(
			@Param("tipoDocCoordAsignado") Integer tipoDocCoordAsignado,
			@Param("docCoordAsignado") Long docCoordAsignado);
	
	@Query("SELECT u FROM Usuario u "
            + " JOIN u.rol.permisos p WHERE p.nombre IN ('Aceptar Caso') "
            + " AND u.activoParaAsignacion = true "
            + "AND u.usuarioVigente = true " )
	public List<Usuario> buscarPeritosDisponibles( );

	@Query("SELECT DISTINCT(u) FROM Usuario u "
            + " JOIN u.rol.permisos p"
            + " JOIN u.divipolas d"
            + " WHERE p.nombre IN ('Aceptar Caso') "
            + " AND u.activoParaAsignacion = true "
            + "AND u.usuarioVigente = true "
            + "AND d.departamento = :departamento " )
	public List<Usuario> buscarPeritosDisponiblesDepartamento(@Param("departamento") String departamentoBusqueda);

	@Query("SELECT DISTINCT(u) FROM Usuario u "
            + " JOIN u.rol.permisos p WHERE p.nombre IN ('Aceptar Caso') "
            + " AND u.activoParaAsignacion = true "
            + "AND u.usuarioVigente = true "
            + "AND ( LOWER(u.nombres) LIKE LOWER(:perito) OR LOWER(u.apellidos) LIKE LOWER(:perito))" )
	public List<Usuario> buscarPeritosDisponiblesNombres( @Param("perito")String peritoABuscar);
	
	@Modifying 
	@Query(value ="UPDATE avaluos.usuario "
			+ "	SET numero_documento =:numeroDocumentoNuevo, tipo_documento_identificacion=:tipoDocumentoNuevo "
			+ " WHERE numero_documento=:numeroDocumentoAntiguo AND tipo_documento_identificacion=:tipoDocumentoAntiguo ",nativeQuery=true)
	public void actualizarUsuario(
			@Param("numeroDocumentoNuevo") Long numeroDocumentoNuevo,
			@Param("numeroDocumentoAntiguo") Long numeroDocumentoAntiguo,  @Param("tipoDocumentoNuevo") int tipoDocumentoNuevo ,@Param("tipoDocumentoAntiguo") int tipoDocumentoAntiguo );
	
}
