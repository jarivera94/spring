package com.helio4.bancol.avaluos.servicio.datos;

import java.util.List;

import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.ClienteNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.EntidadNotFoundException;
import org.springframework.stereotype.Service;

import com.helio4.bancol.avaluos.dto.ClienteDTO;

@Service
public interface ClienteService {
	
	/**
	 * Crea un nuevo avaluo.
	 * @param avaluoDTO La información del nuevo avaluo.
	 * @return El valuo creado
	 * @throws EntidadNotFoundException
	 */
	public ClienteDTO crear(ClienteDTO clienteDTO) throws EntidadNotFoundException;
	
	/**
	 * Elimina el avaluo
	 * @param avaluoId El identificador del avaluo que se va a eliminar.
	 * @throws AvaluoNotFoundException Si el avaluo no existe.
	 * @return El avaluo que se eliminó.
	 */
	public ClienteDTO eliminar(Integer tipoDocumentoIdentificacion, Long numeroDocumento) throws ClienteNotFoundException;
	
	/**
	 * Encuentra todos los avaluos.
	 * @return Una lista con todos los avaluos.
	 */
	public List<ClienteDTO> encontrarTodos();
	
	/**
	 * Encuentra el avaluo por el identificador.
	 * @param id El identificador del avaluo que se quiere encontrar.
	 * @return
	 */
	public ClienteDTO encontrarPorId(Integer tipoDocumentoIdentificacion, Long numeroDocumento);
	
	/**
	 * Retorna el cliente con los avaluos (Cargue perezoso)
	 * @param clienteId
	 * @return
	 */
	public ClienteDTO encontrarClienteConAvaluos(Integer tipoDocumentoIdentificacion, Long numeroDocumento);
	
	/**
	 * Verifica si el usuario con el @param numeroDocumento existe en la base de datos.
	 * @param numeroDocumento el número de documento del usuario.
	 * @return El número de avaluos del cliente
	 */
	public Long numeroAvaluosPorCliente(Integer tipoDocumentoIdentificacion, Long numeroDocumento);
	
	/**
	 * Retirna el cliente buscando por Numero de documento
	 * @param numeroDocumento
	 * @return
	 */
	public ClienteDTO encontrarPorNumeroDocumento(Integer tipoDocumentoIdentificacion, Long numeroDocumento);
	
	/**
	 * Actualiza la información de un avaluo.
	 * @param actualizado La información del avaluo actualizado
	 * @return El avaluuo actualizado
	 * @throws EntidadNotFoundException 
	 * @throws AvaluoNotFoundException Si no hay un avaluo con el id dado.
	 */
	public ClienteDTO actualizar(ClienteDTO actualizado) throws ClienteNotFoundException, EntidadNotFoundException;
	/**
	 * Actualiza el numero y tipo de documento de un cliente.
	 * @param tipoDocumentoNuevo,
	 * @param numeroDocumentoNuevo,
	 * @param tipoDocumentoAntiguo,
	 * @param numeroDocumentoAntiguo
	 * */
	public void actualizar(int tipoDocumentoNuevo,Long numeroDocumentoNuevo,int tipoDocumentoAntiguo,Long numeroDocumentoAntiguo);

	/**
	 * Retirna el cliente buscando por Numero de documento
	 * @param numeroDocumento
	 * @return
	 */
	public ClienteDTO encontrarPorNumeroDocumento(Long numeroDocumento);
}
