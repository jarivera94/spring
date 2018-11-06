package com.helio4.bancol.avaluos.persistencia;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.helio4.bancol.avaluos.modelo.Entidad;
import com.helio4.bancol.avaluos.dto.EntidadDTO;

@Repository
public interface EntidadRepository extends JpaRepository<Entidad, Long> {

    @Query("SELECT New com.helio4.bancol.avaluos.dto.EntidadDTO(e.id, e.nombre)"
            + " FROM Entidad e WHERE LOWER(e.nombre) = LOWER(:nombre)")
    public EntidadDTO encontrarPorNombre(@Param("nombre") String nombre);
    
    @Query("SELECT e FROM Entidad e left join fetch e.usuarios WHERE e.id = :entidadId")
    public Entidad encºontrarEntidadConUsuarios(@Param("entidadId") Long entidadId);
    
    @Query("SELECT NEW com.helio4.bancol.avaluos.dto.EntidadDTO(e.id, e.nombre) FROM Entidad e JOIN e.usuarios u WHERE u.id.tipoDocumentoIdentificacion = :tipoDocumentoIdentificacion"
            + " AND u.id.numeroDocumento = :numeroDocumento "
            + "AND e.activo = true")
    public List<EntidadDTO> encontrarEntidadesPorUsuario(@Param("tipoDocumentoIdentificacion") Integer tipoDocumentoIdentificacion,
            @Param("numeroDocumento") Long numeroDocumento);

	@Query("SELECT e.cobradoPorBancol FROM Entidad e WHERE e.id = :id")
	public Boolean cargarCobraAvaluo(@Param("id") Long entidadId);
	
    @Query("SELECT e.prefijo FROM Entidad e WHERE e.id = :id")
    public String cargarPrefijo(@Param("id") Long entidadId);

    @Query("SELECT NEW com.helio4.bancol.avaluos.dto.EntidadDTO(e.id, e.nombre) FROM Entidad e")
    public List<EntidadDTO> encontrarEntidades();
    
    @Query("SELECT e FROM Entidad e WHERE e.activo = true")
    public List<Entidad> encontrarTodoActivo();

    @Query("SELECT NEW com.helio4.bancol.avaluos.dto.EntidadDTO(e.id, e.nombre) FROM Entidad e WHERE e.codigoBUA != 0 AND e.codigoBUA != null")
    public List<EntidadDTO> encontrarEntidadesConCodigoBUA();
    
    @Query("SELECT NEW com.helio4.bancol.avaluos.dto.EntidadDTO(e.id, e.nombre) FROM Entidad e WHERE e.codigoTinsa = :codigoTinsa ")
    public EntidadDTO encontrarEntidadPorCodigoTinsa(@Param("codigoTinsa") Integer codigo);
}
