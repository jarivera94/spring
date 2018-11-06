package com.helio4.bancol.avaluos.servicio.datos;

import java.util.List;

import com.helio4.bancol.avaluos.servicio.excepciones.EntidadNotFoundException;
import org.springframework.stereotype.Service;

import com.helio4.bancol.avaluos.dto.EntidadDTO;

@Service
public interface EntidadService {

	/**
	 * Encuentra todos las entidades.
	 * @return Una lista con todos las entidades.
	 */
	public List<EntidadDTO> encontrarTodos();
	
	/**
	 * Encuentra todos las entidades activas.
	 * @return Una lista con todos las entidades activas.
	 */
	public List<EntidadDTO> encontrarTodosActivos();
	
	/**
	 * Encuentra la entidad por el identificador.
	 * @param id El identificador de la entidad que se quiere encontrar.
	 * @return Objeto EntidadDTO
	 */
	public EntidadDTO encontrarPorId(Long id);
	
	/**
	 * Encuentra la Entidad por Nombre
	 * @param nombre Nombre de la Entidad
	 * @return Objeto EntidadDTO
	 */
	public EntidadDTO encontrarPorNombre(String nombre);

	/**
     * Encuentra las entidades del usuario que se pasa como parametro.
     * @param tipoDocumentoIdentificacion tipo de documento de indentificación del usuario.
     * @param numeroDocumento numero de documento del usuario.
     * @return una lista de DTO(Objetos de transferencia de datos de las
     * entidades del usuario.
     */
    public List<EntidadDTO> encontrarEntidadesPorUsuario(
            Integer tipoDocumentoIdentificacion, Long numeroDocumento);

	/**
	 * Crear la Entidad
	 * @param entidadDTO Objeto
	 * @return Confirmaci�n de la transacci�n
	 * @throws EntidadNotFoundException
	 */
	public Boolean crearEntidad(EntidadDTO entidadDTO) throws EntidadNotFoundException;

    String cargarPrefijo(Long entidadId);

	/**
	 * Actualizar la Entidad
	 * @param entidadDTO Objeto
	 * @return Confirmaci�n de la transacci�n
	 * @throws EntidadNotFoundException
	 */
	public Boolean actualizarTodo(EntidadDTO entidadDTO) throws EntidadNotFoundException;	
	
	/**
	 * Eliminar la Entidad
	 * @param entidadDTO Objeto
	 * @return Objeto EntidadDTO
	 * @throws EntidadNotFoundException
	 */
	public EntidadDTO eliminarEntidad(EntidadDTO entidadDTO) throws EntidadNotFoundException;

	/**
	 * Encuentra todas las entidades trayendo solamente el nombre y el id
	 * @return
	 */
	List<EntidadDTO> encontrarEntidades();
	
	/**
	 * Encuentra todas las entidades que tengan codigo BUA
	 * @return
	 */
	List<EntidadDTO> encontrarEntidadesConCodigoBUA();

	
	/**
	 * Encuentra Una entidad por codigo Tinsa codigo BUA
	 * @return
	 */
	public EntidadDTO encontrarPorCodigoTinsa(Integer codigo);
}
