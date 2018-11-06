package com.helio4.bancol.avaluos.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.helio4.bancol.avaluos.modelo.Cliente;
import com.helio4.bancol.avaluos.modelo.Usuario.DocumentoIdentificacion;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, DocumentoIdentificacion> {

	@Query("SELECT c FROM Cliente c left join fetch c.avaluos WHERE c.id = :clienteId")
	public Cliente encontrarClienteConAvaluos(@Param("clienteId") DocumentoIdentificacion clienteId);
	
	@Query("SELECT count(c) FROM Cliente c WHERE c.id = :clienteId")
	public Long numeroAvaluosPorUsuario(@Param("clienteId") DocumentoIdentificacion clienteId);
	
	@Query("SELECT c FROM Cliente c WHERE c.id = :clienteId")
	public Cliente encontrarPorNumeroDocumento(@Param("clienteId") DocumentoIdentificacion clienteId);
	
	@Query("SELECT c FROM Cliente c WHERE c.id.numeroDocumento = :numeroDocumento")
	public Cliente encontrarPorNumeroDocumento(@Param("numeroDocumento") Long numeroDocumento);
	
	@Modifying 
	@Query(value ="UPDATE avaluos.cliente "
			+ "	SET numero_documento =:numeroDocumentoNuevo, tipo_documento_identificacion=:tipoDocumentoNuevo "
			+ " WHERE numero_documento=:numeroDocumentoAntiguo AND tipo_documento_identificacion=:tipoDocumentoAntiguo ",nativeQuery=true)
	public void actualizarCliente(
			@Param("numeroDocumentoNuevo") Long numeroDocumentoNuevo,
			@Param("numeroDocumentoAntiguo") Long numeroDocumentoAntiguo,  @Param("tipoDocumentoNuevo") int tipoDocumentoNuevo ,@Param("tipoDocumentoAntiguo") int tipoDocumentoAntiguo );

}
