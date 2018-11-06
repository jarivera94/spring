package com.helio4.bancol.avaluos.persistencia;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

import com.helio4.bancol.avaluos.dto.AvaluoDTO;
import com.helio4.bancol.avaluos.dto.ClienteDTO;
import com.helio4.bancol.avaluos.dto.RegionalDTO;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;
import com.helio4.bancol.avaluos.modelo.Avaluo;
import com.helio4.bancol.avaluos.modelo.EstadoAvaluo;
import com.helio4.bancol.avaluos.modelo.FormatoInforme;

@Repository
public interface AvaluoRepository extends JpaRepository<Avaluo, Long> {

	@Query("SELECT e FROM EstadoAvaluo e WHERE e.avaluo.id = :avaluoId AND e.estadoActual = true")
	public EstadoAvaluo encontrarEstadoActual(@Param("avaluoId") Long avaluoId);

	@Query("SELECT a FROM Avaluo a WHERE a.direccionInmueble = :direccion")
	public List<Avaluo> encontrarAvaluosPorDireccion(@Param("direccion") String direccion);

	@Query("SELECT COUNT(*) > 0 FROM Avaluo a WHERE a.entidad.id"
			+ " = :idEntidad AND a.codigoExterno = :codigoExterno")
	public Boolean encontrarPorCodigoExterno(@Param("codigoExterno") String codigoExterno,
			@Param("idEntidad") Long idEntidad);
	
	@Query("SELECT NEW com.helio4.bancol.avaluos.dto.AvaluoDTO(a.id," + 
			"			a.codigoExterno,a.avaluoSisgenId ) FROM Avaluo a WHERE a.entidad.id"
			+ " = :idEntidad AND a.codigoExterno = :codigoExterno")
	public AvaluoDTO encontrarAvaluoPorCodigoExterno(@Param("codigoExterno") String codigoExterno,
			@Param("idEntidad") Long idEntidad);
	

	

	@Query("SELECT COUNT(*) > 0 FROM Avaluo a WHERE a.avaluoSisgenId = :idSisgen")
	public Boolean encontrarPorIdSisgen(@Param("idSisgen") Long idSisgen);

	@Query("SELECT a.cambioGarantia FROM Avaluo a WHERE a.entidad.id"
			+ " = :idEntidad AND a.codigoExterno = :codigoExterno")
	public Boolean encontrarPorCodigoExternoAvaluo(@Param("codigoExterno") String codigoExterno,
			@Param("idEntidad") Long idEntidad);

	@Query("SELECT NEW com.helio4.bancol.avaluos.dto.AvaluoDTO(a.id, "
			+ "a.codigoExterno, e.fechaEstado, t.id, t.nombre, "
			+ " a.valorTotalAvaluo) FROM EstadoAvaluo e JOIN e.avaluo a " + "JOIN a.tipoAvaluo t JOIN a.cliente c "
			+ "WHERE e.estadoActual = true " + "AND c.id.tipoDocumentoIdentificacion = :clienteTipoDocumento "
			+ "AND c.id.numeroDocumento = :clienteNumeroDocumento " + "AND a.id <> :id AND e.estado = 11 ")
	public List<AvaluoDTO> encontrarAvaluosAnteriores(@Param("clienteTipoDocumento") Integer clienteTipoDocumento,
			@Param("clienteNumeroDocumento") Long clienteNumeroDocumento, @Param("id") Long id);

	@Query("SELECT a.formatoInforme FROM Avaluo a WHERE a.id = :id")
	public FormatoInforme cargarFormatoInforme(@Param("id") Long id);

	@Query("SELECT e.avaluo FROM EstadoAvaluo e WHERE e.estadoActual = true "
			+ " AND e.fechaEstado BETWEEN :fechaInicial AND :fechaFinal")
	public List<Avaluo> encontrarAvaluosEntreFechas(@Param("fechaInicial") Date fechaInicial,
			@Param("fechaFinal") Date fechaFinal);

	@Query(value = "SELECT a.nombre_persona_que_recibe_el_avaluo, a.telefono_persona_que_recibe_el_avaluo, a.correo_electronico_persona_que_recibe_el_avaluo"
			+ " FROM avaluos.avaluo a WHERE a.avaluo_id = :avaluoId", nativeQuery = true)
	public List<Object> cargarPersonaRecibeAvaluador(@Param("avaluoId") Long avaluoId);

	/**
	 * Holman - Avaluos por Entidad
	 */
	@Query("SELECT a FROM Avaluo a WHERE a.entidad.id = :idEntidad")
	public Set<Avaluo> encontrarAvaluosPorEntidad(@Param("idEntidad") Long idEntidad);

	@Query(value = "SELECT a.avaluo_id, a.tipo_de_inmueble, h.estrato, a.barrio, a.direccion_inmueble, d.centro_poblado, d.departamento, a.latitud, a.longitud, "
			+ "h.estado_de_construccion, h.vetustez, h.habitaciones,COALESCE(h.area_privada, 0), a.valor_total_avaluo "
			+ "FROM avaluos.avaluo a, avaluos.avaluo_hipotecario h, avaluos.divipola d "
			+ "WHERE a.avaluo_id = h.avaluo_id " + "AND a.divipola = d.divipola_id "
			+ "AND a.tipo_de_inmueble = :tipoInmueble " + "AND a.divipola = :idDivipola " + "AND a.sector = :sector "
			+ "AND upper(a.barrio) LIKE :barrio " + "AND h.estrato BETWEEN :estratoMin AND :estratoMax "
			+ "AND a.tipo_via IN (:tiposVia) " + "AND cast(a.numero_via as int) BETWEEN :numViaMin AND :numViaMax "
			+ "AND cast(a.numero_via_generadora as int) BETWEEN :numViaGenMin AND :numViaGenMax "
			+ "AND a.latitud BETWEEN :latitudMin AND :latitudMax "
			+ "AND a.longitud BETWEEN :longitudMin AND :longitudMax " + "AND h.sometido_a_propiedad_horizontal = true "
			+ "AND h.area_privada BETWEEN :areaPrivadaMin AND :areaPrivadaMax "
			+ "AND h.habitaciones BETWEEN :habitacionesMin AND :habitacionesMax "
			+ "AND COALESCE(h.bano_privado, 0)+COALESCE(h.bano_social, 0)+COALESCE(h.bano_servicio, 0) BETWEEN :banosMin AND :banosMax "
			+ "AND COALESCE(h.garaje_privado, 0) BETWEEN :gPrivadoMin AND :gPrivadoMax "
			+ "AND COALESCE(h.garaje_exclusivo, 0) BETWEEN :gPublicoMin AND :gPublicoMax "
			+ "AND COALESCE(h.bahia_comunal, 0) BETWEEN :bahiaMin AND :bahiaMax "
			+ "AND h.estado_de_construccion BETWEEN :estadoMin AND :estadoMax "
			+ "AND h.vetustez BETWEEN :edadMin AND :edadMax "
			+ "AND a.valor_total_avaluo BETWEEN :valorTotalMin AND :valorTotalMax "
			+ "ORDER BY a.avaluo_id ", nativeQuery = true)
	public List<Object> encontrarAvaluosUrbanoPH(@Param("tipoInmueble") Long tipoInmueble,
			@Param("idDivipola") Long idDivipola, @Param("sector") Integer sector, @Param("barrio") String barrio,
			@Param("estratoMin") Integer estratoMin, @Param("estratoMax") Integer estratoMax,
			@Param("tiposVia") List<String> tiposVia, @Param("numViaMin") Integer numViaMin,
			@Param("numViaMax") Integer numViaMax, @Param("numViaGenMin") Integer numViaGenMin,
			@Param("numViaGenMax") Integer numViaGenMax, @Param("latitudMin") BigDecimal latitudMin,
			@Param("latitudMax") BigDecimal latitudMax, @Param("longitudMin") BigDecimal longitudMin,
			@Param("longitudMax") BigDecimal longitudMax, @Param("areaPrivadaMin") Integer areaPrivadaMin,
			@Param("areaPrivadaMax") Integer areaPrivadaMax, @Param("habitacionesMin") Integer habitacionesMin,
			@Param("habitacionesMax") Integer habitacionesMax, @Param("banosMin") Integer banosMin,
			@Param("banosMax") Integer banosMax, @Param("gPrivadoMin") Integer gPrivadoMin,
			@Param("gPrivadoMax") Integer gPrivadoMax, @Param("gPublicoMin") Integer gPublicoMin,
			@Param("gPublicoMax") Integer gPublicoMax, @Param("bahiaMin") Integer bahiaMin,
			@Param("bahiaMax") Integer bahiaMax, @Param("estadoMin") Integer estadoMin,
			@Param("estadoMax") Integer estadoMax, @Param("edadMin") Integer edadMin, @Param("edadMax") Integer edadMax,
			@Param("valorTotalMin") Long valorTotalMin, @Param("valorTotalMax") Long valorTotalMax);

	@Query(value = "SELECT a.avaluo_id, a.tipo_de_inmueble, h.estrato, a.barrio, a.direccion_inmueble, d.centro_poblado, d.departamento, a.latitud, a.longitud, "
			+ "h.estado_de_construccion, h.vetustez, h.habitaciones,COALESCE(h.area_privada, 0), a.valor_total_avaluo "
			+ "FROM avaluos.avaluo a, avaluos.avaluo_hipotecario h, avaluos.divipola d "
			+ "WHERE a.avaluo_id = h.avaluo_id " + "AND a.divipola = d.divipola_id "
			+ "AND a.tipo_de_inmueble = :tipoInmueble " + "AND a.divipola = :idDivipola " + "AND a.sector = :sector "
			+ "AND upper(a.barrio) LIKE :barrio " + "AND h.estrato BETWEEN :estratoMin AND :estratoMax "
			+ "AND a.tipo_via IN (:tiposVia) " + "AND cast(a.numero_via as int) BETWEEN :numViaMin AND :numViaMax "
			+ "AND cast(a.numero_via_generadora as int) BETWEEN :numViaGenMin AND :numViaGenMax "
			+ "AND a.latitud BETWEEN :latitudMin AND :latitudMax "
			+ "AND a.longitud BETWEEN :longitudMin AND :longitudMax " + "AND h.sometido_a_propiedad_horizontal = true "
			+ "AND a.area_total BETWEEN :areaTotalMin AND :areaTotalMax "
			+ "AND h.area_construida BETWEEN :areaConstMin AND :areaConstMax "
			+ "AND h.habitaciones BETWEEN :habitacionesMin AND :habitacionesMax "
			+ "AND COALESCE(h.bano_privado, 0)+COALESCE(h.bano_social, 0)+COALESCE(h.bano_servicio, 0) BETWEEN :banosMin AND :banosMax "
			+ "AND COALESCE(h.garaje_privado, 0) BETWEEN :gPrivadoMin AND :gPrivadoMax "
			+ "AND COALESCE(h.garaje_exclusivo, 0) BETWEEN :gPublicoMin AND :gPublicoMax "
			+ "AND COALESCE(h.bahia_comunal, 0) BETWEEN :bahiaMin AND :bahiaMax "
			+ "AND h.estado_de_construccion BETWEEN :estadoMin AND :estadoMax "
			+ "AND h.vetustez BETWEEN :edadMin AND :edadMax "
			+ "AND a.valor_total_avaluo BETWEEN :valorTotalMin AND :valorTotalMax "
			+ "ORDER BY a.avaluo_id ", nativeQuery = true)
	public List<Object> encontrarAvaluosUrbanoNPH(@Param("tipoInmueble") Long tipoInmueble,
			@Param("idDivipola") Long idDivipola, @Param("sector") Integer sector, @Param("barrio") String barrio,
			@Param("estratoMin") Integer estratoMin, @Param("estratoMax") Integer estratoMax,
			@Param("tiposVia") List<String> tiposVia, @Param("numViaMin") Integer numViaMin,
			@Param("numViaMax") Integer numViaMax, @Param("numViaGenMin") Integer numViaGenMin,
			@Param("numViaGenMax") Integer numViaGenMax, @Param("latitudMin") BigDecimal latitudMin,
			@Param("latitudMax") BigDecimal latitudMax, @Param("longitudMin") BigDecimal longitudMin,
			@Param("longitudMax") BigDecimal longitudMax, @Param("areaTotalMin") Integer areaTotalMin,
			@Param("areaTotalMax") Integer areaTotalMax, @Param("areaConstMin") Integer areaConstMin,
			@Param("areaConstMax") Integer areaConstMax, @Param("habitacionesMin") Integer habitacionesMin,
			@Param("habitacionesMax") Integer habitacionesMax, @Param("banosMin") Integer banosMin,
			@Param("banosMax") Integer banosMax, @Param("gPrivadoMin") Integer gPrivadoMin,
			@Param("gPrivadoMax") Integer gPrivadoMax, @Param("gPublicoMin") Integer gPublicoMin,
			@Param("gPublicoMax") Integer gPublicoMax, @Param("bahiaMin") Integer bahiaMin,
			@Param("bahiaMax") Integer bahiaMax, @Param("estadoMin") Integer estadoMin,
			@Param("estadoMax") Integer estadoMax, @Param("edadMin") Integer edadMin, @Param("edadMax") Integer edadMax,
			@Param("valorTotalMin") Long valorTotalMin, @Param("valorTotalMax") Long valorTotalMax);

	@Query(value = "SELECT a.avaluo_id, a.tipo_de_inmueble,a.direccion_inmueble, d.centro_poblado, d.departamento, a.latitud, a.longitud, c.vereda, c.distacia_partiendo_del_casco_urbano, "
			+ "c.area_construida, c.area_cultivos_avaluables, c.area_infraestructura, a.valor_total_avaluo "
			+ "FROM avaluos.avaluo a, avaluos.avaluo_comercial c,avaluos.divipola d "
			+ "WHERE a.avaluo_id = c.avaluo_id " + "AND a.avaluo_id = d.divipola_id "
			+ "AND a.tipo_de_inmueble = :tipoInmueble " + "AND a.divipola = :idDivipola " + "AND a.sector = :sector "
			+ "AND a.latitud BETWEEN :latitudMin AND :latitudMax "
			+ "AND a.longitud BETWEEN :longitudMin AND :longitudMax "
			+ "AND a.valor_total_avaluo BETWEEN :valorTotalMin AND :valorTotalMax "
			+ "AND c.area_construida BETWEEN :areaConstMin AND :areaConstMax "
			+ "AND c.area_infraestructura BETWEEN :areaInfraestMin AND :areaInfraestMax "
			+ "AND c.area_cultivos_avaluables BETWEEN :areaCultivosAvalMin AND :areaCultivosAvalMax "
			+ "AND c.distacia_partiendo_del_casco_urbano BETWEEN :distCascoMin AND :distCascoMax "
			+ "AND c.condiciones_agronomicas BETWEEN :condAgronomicasMin AND :condAgronomicasMax "
			+ "AND c.condiciones_agrologicas BETWEEN :condAgrologicasMin AND :condAgrologicasMax "
			+ "AND a.valor_total_avaluo BETWEEN :valorTotalMin AND :valorTotalMax "
			+ "ORDER BY a.avaluo_id ", nativeQuery = true)
	public List<Object> encontrarAvaluosRural(@Param("tipoInmueble") Long tipoInmueble,
			@Param("idDivipola") Long idDivipola, @Param("sector") Integer sector,
			@Param("latitudMin") BigDecimal latitudMin, @Param("latitudMax") BigDecimal latitudMax,
			@Param("longitudMin") BigDecimal longitudMin, @Param("longitudMax") BigDecimal longitudMax,
			@Param("valorTotalMin") Integer areaTotalMin, @Param("valorTotalMax") Integer areaTotalMax,
			@Param("areaConstMin") Integer areaConstMin, @Param("areaConstMax") Integer areaConstMax,
			@Param("areaInfraestMin") Integer areaInfraestMin, @Param("areaInfraestMax") Integer areaInfraestMax,
			@Param("areaCultivosAvalMin") Integer areaCultivosAvalMin,
			@Param("areaCultivosAvalMax") Integer areaCultivosAvalMax, @Param("distCascoMin") Integer distCascoMin,
			@Param("distCascoMax") Integer distCascoMax, @Param("condAgronomicasMin") Integer condAgronomicasMin,
			@Param("condAgronomicasMax") Integer condAgronomicasMax,
			@Param("condAgrologicasMin") Integer condAgrologicasMin,
			@Param("condAgrologicasMax") Integer condAgrologicasMax, @Param("valorTotalMin") Long valorTotalMin,
			@Param("valorTotalMax") Long valorTotalMax);

	@Query("SELECT a.compraCartera FROM Avaluo a WHERE a.id = :id")
	public Boolean cargarCompraCartera(@Param("id") Long avaluoId);

	@Query("SELECT NEW com.helio4.bancol.avaluos.dto.AvaluoDTO(a.id, a.codigoExterno, "
			+ "c.id.numeroDocumento, c.id.tipoDocumentoIdentificacion, "
			+ "c.primerNombre, c.segundoNombre, c.primerApellido, "
			+ "c.segundoApellido, c.razonSocial, c.clienteExterior, " + "s.id, s.nombre, d.id, "
			+ "d.departamento, d.centroPoblado, a.cambioGarantia, "
			+ "a.cambioGarantiaAvaluo, e.estado, e.fechaEstado) "
			+ " FROM EstadoAvaluo e JOIN e.avaluo a LEFT JOIN a.cliente c "
			+ " LEFT JOIN c.segmento s LEFT JOIN c.divipola d " + " WHERE e.estadoActual = true "
			+ " AND a.codigoExterno = :codigoExterno " + " AND a.entidad.id = :entidadId "
			+ " ORDER BY a.fechaCreacion DESC")
	public List<AvaluoDTO> comprobarCambioGarantia(@Param("codigoExterno") String codigoExterno,
			@Param("entidadId") Long entidadId);

	@Query("SELECT m.metodoUsado = 23 FROM Avaluo a JOIN a.metodosValuacion m WHERE a.id = :id AND m.metodoUsado = 23")
	public Boolean tieneMetodologiaFito(@Param("id") Long id);

	@Query("SELECT m.metodoUsado = 21 FROM Avaluo a JOIN a.metodosValuacion m WHERE a.id = :id AND m.metodoUsado = 21")
	public Boolean tieneMetodologiaComparacion(@Param("id") Long id);

	@Query(value = "SELECT a.gastos_translado, a.iva,a.valor_liquidacion FROM avaluos.avaluo a WHERE a.avaluo_id = :id", nativeQuery = true)
	public List<Object> obtenerHonarariosPerito(@Param("id") Long id);

	@Query(value = "SELECT NEW com.helio4.bancol.avaluos.dto.RegionalDTO(a.divipola.regional.id,a.divipola.regional.nombre) FROM Avaluo a WHERE a.id = :id")
	public RegionalDTO buscarRegionalAvaluo(@Param("id") Long id);

	@Query(value = "SELECT a.entidad.prefijo FROM Avaluo a WHERE a.id = :id ")
	public String encontrarPrefijo(@Param("id") Long id);

	@Query(value = "SELECT NEW com.helio4.bancol.avaluos.dto.UsuarioDTO(a.nombreAsesor,a.nombreAsesor,a.correoElectronicoAsesor,a.telefonoAsesor,a.celularAsesor)"
			+ " FROM Avaluo a   WHERE a.id = :id ")
	public UsuarioDTO encontrarAsesor(@Param("id") Long id);

	@Query(value = "SELECT NEW com.helio4.bancol.avaluos.dto.UsuarioDTO(u.nombres,u.apellidos,u.email,u.celular,u.celular)"
			+ " FROM EstadoAvaluo e JOIN e.avaluo a JOIN e.usuario u WHERE a.id = :id AND e.estado=1")
	public UsuarioDTO encontrarAbogado(@Param("id") Long id);

	@Query(value = "SELECT NEW com.helio4.bancol.avaluos.dto.UsuarioDTO(p.nombres,p.apellidos,p.email,p.celular,p.celular) "
			+ "FROM Avaluo a LEFT JOIN a.perito p WHERE a.id = :id ")
	public UsuarioDTO encontrarPerito(@Param("id") Long id);

	@Query(value = "SELECT NEW com.helio4.bancol.avaluos.dto.ClienteDTO(c) "
			+ "FROM Avaluo a JOIN a.cliente c WHERE a.id = :id ")
	public ClienteDTO encontrarCliente(@Param("id") Long id);

	@Query(value = "SELECT NEW com.helio4.bancol.avaluos.dto.UsuarioDTO(a.nombreRecibe,a.nombreRecibe,a.correoElectronicoRecibe,a.telefonoRecibe,a.telefonoRecibe) "
			+ "FROM Avaluo a   WHERE a.id = :id ")
	public UsuarioDTO encontrarPersonaRecibe(@Param("id") Long id);

	@Query(value = " SELECT count(*) " + " FROM  avaluos.avaluo a " + " JOIN avaluos.estado_avaluo ea "
			+ "		ON ea.avaluo_id = a.avaluo_id  " + "		AND ea.estado_actual = true "
			+ "		AND ea.estado NOT IN(11,14) "
			+ " JOIN avaluos.usuario p on p. tipo_documento_identificacion  = a.perito_tipo_documento "
			+ "		AND p.numero_documento = a.perito_numero_documento  "
			+ "		AND p.numero_documento = :usuarioNumeroDocumento "
			+ "		AND p.tipo_documento_identificacion = :usuarioTipoIdentificacion", nativeQuery = true)
	public int encontrarCasosAbiertos(@Param("usuarioTipoIdentificacion") Integer usuarioTipoIdentificacion,
			@Param("usuarioNumeroDocumento") Long usuarioNumeroDocumento);

	@Query(value = "SELECT COUNT(*) " + " FROM avaluos.usuario u " + " JOIN avaluos.avaluo a  "
			+ "    ON a.perito_tipo_documento= u.tipo_documento_identificacion   "
			+ "    AND a.perito_numero_documento = u.numero_documento  " + " JOIN avaluos.estado_avaluo ea  "
			+ "    ON ea.avaluo_id = a.avaluo_id  " + "    AND ea.estado_actual = true "
			+ "    AND ea.fecha_estado BETWEEN LOCALTIMESTAMP - INTERVAL '6 days' AND LOCALTIMESTAMP "
			+ "    AND ea.estado NOT IN (11,14) "
			+ " WHERE u.tipo_documento_identificacion = :usuarioTipoIdentificacion "
			+ "    AND u.numero_documento = :usuarioNumeroDocumento ", nativeQuery = true)
	public int encontrarCasosAbiertosSemana(@Param("usuarioTipoIdentificacion") Integer usuarioTipoIdentificacion,
			@Param("usuarioNumeroDocumento") Long usuarioNumeroDocumento);

	@Query(value = " SELECT count(*) " + " FROM  avaluos.avaluo a " + " JOIN avaluos.estado_avaluo ea "
			+ "		ON ea.avaluo_id = a.avaluo_id  " + "		AND ea.estado_actual = true "
			+ "	    AND ea.fecha_estado BETWEEN LOCALTIMESTAMP - INTERVAL '1 month' AND LOCALTIMESTAMP"
			+ "		AND ea.estado NOT IN(11,14) "
			+ " JOIN avaluos.usuario p on p. tipo_documento_identificacion  = a.perito_tipo_documento "
			+ "		AND p.numero_documento = a.perito_numero_documento  "
			+ "		AND p.numero_documento = :usuarioNumeroDocumento "
			+ "		AND p.tipo_documento_identificacion = :usuarioTipoIdentificacion", nativeQuery = true)
	public int encontrarCasosAbiertosMes(@Param("usuarioTipoIdentificacion") Integer usuarioTipoIdentificacion,
			@Param("usuarioNumeroDocumento") Long usuarioNumeroDocumento);

	@Query("SELECT a.correoElectronicoAsesor FROM Avaluo a WHERE a.id = :id")
	public String cargarCorreoAsesor(@Param("id") Long id);

	@Query("SELECT a FROM Avaluo a WHERE a.avaluoSisgenId = :id")
	public Avaluo encontrarPorIdTinsa(@Param("id") Long id);
	
	@Query("SELECT a FROM Avaluo a WHERE a.entidad.id"
			+ " = :idEntidad AND a.codigoExterno = :codigoExterno AND a.cliente.id.numeroDocumento = :numeroDocumentoCliente ")
	public Avaluo encontrarAvaluoPorCodigoExternoAndNumeroDocumentoCliente(@Param("codigoExterno") String codigoExterno,
			@Param("idEntidad") Long idEntidad, @Param("numeroDocumentoCliente") Long numeroDocumentoCliente);

	@Query("SELECT  NEW com.helio4.bancol.avaluos.dto.AvaluoDTO(a.id,a.codigoExterno,a.tipoAvaluo.nombre) "
			+ "FROM EstadoAvaluo e JOIN e.avaluo a " + "WHERE e.estadoActual = true AND e.estado = 11"
			+ "AND a.cliente.id.tipoDocumentoIdentificacion=:tipoDocumento "
			+ "AND a.cliente.id.numeroDocumento=:numeroDocumento")
	public List<AvaluoDTO> encontrarAvaluosAprobadosDeCliente(@Param("tipoDocumento") int tipoDocumento,
			@Param("numeroDocumento") Long numeroDocumento);

}
