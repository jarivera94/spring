package com.helio4.bancol.avaluos.servicio.datos;

import java.util.Date;
import java.util.List;

import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;
import org.springframework.stereotype.Service;

import com.helio4.bancol.avaluos.dto.AvaluoConsultaDTO;
import com.helio4.bancol.avaluos.dto.AvaluoDTO;
import com.helio4.bancol.avaluos.dto.ClienteDTO;
import com.helio4.bancol.avaluos.dto.EstadoAvaluoDTO;
import com.helio4.bancol.avaluos.dto.EventoDTO;
import com.helio4.bancol.avaluos.dto.FormatoInformeDTO;
import com.helio4.bancol.avaluos.dto.RegionalDTO;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;

@Service
public interface AvaluoService {

	/**
	 * Crea un nuevo avaluo.
	 * 
	 * @param avaluoDTO
	 *            La información del nuevo avaluo.
	 * @return El valuo creado
	 */
	public AvaluoDTO crear(AvaluoDTO avaluoDTO, UsuarioDTO usuarioDTO);

	/**
	 * Elimina el avaluo
	 * 
	 * @param avaluoId
	 *            El identificador del avaluo que se va a eliminar.
	 * @throws AvaluoNotFoundException
	 *             Si el avaluo no existe.
	 * @return El avaluo que se eliminó.
	 */
	public AvaluoDTO eliminar(Long avaluoId) throws AvaluoNotFoundException;

	/**
	 * Elimina el tipo del avaluo
	 * 
	 * @param avaluoId
	 *            El identificador del avaluo que se va a eliminar.
	 * @throws AvaluoNotFoundException
	 *             Si el avaluo no existe.
	 * @return El avaluo que se eliminó.
	 */
	public AvaluoDTO cambiarTipoAvaluo(Long avaluoId, String nuevoTipoAvaluo) throws AvaluoNotFoundException;

	/**
	 * Encuentra todos los avaluos.
	 * 
	 * @return Una lista con todos los avaluos.
	 */
	public List<AvaluoDTO> encontrarTodos();

	/**
	 * Encuentra el avaluo por el identificador.
	 * 
	 * @param id
	 *            El identificador del avaluo que se quiere encontrar.
	 * @return
	 */
	public AvaluoDTO encontrarPorId(Long id);

	/**
	 * Actualiza la información de un avaluo.
	 * 
	 * @param actualizado
	 *            La información del avaluo actualizado
	 * @return El avaluuo actualizado
	 * @throws AvaluoNotFoundException
	 *             Si no hay un avaluo con el id dado.
	 */
	public AvaluoDTO actualizar(AvaluoDTO actualizado) throws AvaluoNotFoundException;

	/**
	 * Retorna el estado actual del avaluo
	 * 
	 * @param avaluoId
	 * @return
	 */
	EstadoAvaluoDTO encontrarEstadoActual(Long avaluoId);

	/**
	 * Encuentra el avaluo por la dirección
	 * 
	 * @param direccion
	 * @return
	 */
	List<AvaluoDTO> encontrarAvaluosPorDireccion(String direccion);

	/**
	 * Encuentra el avaluo por el código externo
	 * 
	 * @param codigoExterno
	 * @return
	 */
	boolean encontrarPorCodigoExterno(String codigoExterno, Long idEntidad);

	/**
	 * Encuentra el avaluo por el idSisgen
	 * 
	 * @param idSisgen
	 * @return
	 */
	boolean encontrarPorIdSisgen(Long idSisgen);

	/**
	 * Carga el formato de informe del avaluo con id
	 * 
	 * @param id
	 *            el identificador del avaluo
	 * @return el formato de informe del avaluo
	 */
	FormatoInformeDTO cargarFormatoInforme(Long id);

	/**
	 * Carga los datos de contacto de la persona que recibe al avaluador en el
	 * avaluoDTO pasado como parametro.
	 * 
	 * @param avaluoDTO
	 *            Avaluo al que se cargarán los datos
	 * @return el avaluo con los datos de la persona que recibe al avaluador
	 */
	AvaluoDTO cargarInformacionPersonaRecibeAvaluador(AvaluoDTO avaluoDTO);

	/**
	 * Encuentra los avaluos anteriores del cliente
	 * 
	 * @param cliente
	 * @return
	 */
	List<AvaluoDTO> encontrarAvaluosAnteriores(Integer tipoDocumentoIdentificacion, Long numeroDocumento, Long id);

	/**
	 * Encuentra los avaluos que coincidan con el criterio de busqueda para el
	 * Estudio de Mercado
	 * 
	 * @param AvaluoConsultaDTO
	 *            (el avaluo a estudiar)
	 * @return List<AvaluoDTO> (lista de avaluos similares encontrados)
	 */
	List<AvaluoConsultaDTO> encontrarAvaluosParaEstudios(AvaluoConsultaDTO avaluoConsultaDTO);

	/**
	 * Encuentra todos los casos abiertos del usuario
	 * 
	 * @param tipoDocumentoIdentificacionUsuario
	 * @param numeroDocumentoUsuario
	 * @return
	 */
	int encontrarCasosAbiertos(Integer tipoDocumentoIdentificacionUsuario, Long numeroDocumentoUsuario);

	/**
	 * Encuentra todos los casos abiertos del usuario en la semana actual
	 * 
	 * @param tipoDocumentoIdentificacionUsuario
	 * @param numeroDocumentoUsuario
	 * @return
	 */
	int encontrarCasosAbiertosSemana(Integer tipoDocumentoIdentificacionUsuario, Long numeroDocumentoUsuario);

	/**
	 * Encuentra todos los casos abiertos del usuario en el mes actual
	 * 
	 * @param tipoDocumentoIdentificacionUsuario
	 * @param numeroDocumentoUsuario
	 * @return
	 */
	int encontrarCasosAbiertosMes(Integer tipoDocumentoIdentificacionUsuario, Long numeroDocumentoUsuario);

	/**
	 *
	 * @param avaluoDTO
	 * @param perito
	 * @return
	 * @throws AvaluoNotFoundException
	 */
	boolean asignarPerito(AvaluoDTO avaluoDTO, UsuarioDTO perito, UsuarioDTO usuario);

	boolean aceptarCaso(AvaluoDTO avaluoDTO, UsuarioDTO usuario);

	boolean rechazarCaso(AvaluoDTO avaluoDTO, String justificacion, UsuarioDTO usuario);

	boolean programarCita(AvaluoDTO avaluoDTO, Date horaInicio, Date horaFin, String nombreRecibeVisita,
			UsuarioDTO usuario);

	boolean reProgramarCita(AvaluoDTO avaluoDTO, String nombreRecibeVisita, EventoDTO evento, UsuarioDTO usuario);

	boolean solicitarDevolucion(AvaluoDTO avaluoDTO, String justificacion, UsuarioDTO usuario);

	boolean devolver(AvaluoDTO avaluoDTO, UsuarioDTO usuario);

	boolean reactivar(AvaluoDTO avaluoDTO, UsuarioDTO usuario);

	boolean confirmarVisita(AvaluoDTO avaluoDTO, UsuarioDTO usuario);

	boolean confirmarDocumentosCompletos(AvaluoDTO avaluoDTO, UsuarioDTO usuario);

	boolean confirmarPago(AvaluoDTO avaluoDTO, UsuarioDTO usuario);

	boolean enviar(AvaluoDTO avaluoDTO, UsuarioDTO usuario);

	boolean solicitarCorreciones(AvaluoDTO avaluoDTO, String correciones, UsuarioDTO usuario);

	boolean enviarAComite(AvaluoDTO avaluoDTO, UsuarioDTO usuario);

	boolean aprobar(AvaluoDTO avaluoDTO, UsuarioDTO usuario);

	void notificarAvaluoAprobado(AvaluoDTO avaluo, UsuarioDTO usuario, String archivo);

	boolean notificarHonorarios(AvaluoDTO avaluoDTO, UsuarioDTO usuario);

	boolean cancelarAvaluo(AvaluoDTO avaluoDTO, UsuarioDTO usuario);
	
	boolean enProcesoAvaluo(AvaluoDTO avaluoDTO, UsuarioDTO usuario);

	boolean avaluoEsPH(Long id);

	boolean esCobradoPorBancol(AvaluoDTO avaluoDTO);

	boolean crearFormatoInforme(AvaluoDTO avaluo);

	List<AvaluoDTO> comprobarCambioGarantia(String codigoExterno, Long entidadId);

	void actualizarCambioGarantia(AvaluoDTO avaluoCambioGarantia);

	boolean tieneMetodologiaFito(Long id);

	boolean tieneMetodologiaComparacion(Long id);

	public List<Object> obtenerHonorariosPerito(Long id);

	/**
	 * Busca la regional de un avaluo en especifico.
	 */
	public RegionalDTO buscarRegionalAvaluo(Long id);

	public String encontrarPrefijo(Long id);

	/**
	 * Busca el asesor(quien crea el avaluo) de un avaluo.
	 */
	public UsuarioDTO buscarAsesor(Long id);

	/**
	 * Busca el perito de un avaluo.
	 */
	public UsuarioDTO buscarPerito(Long id);

	/**
	 * Busca el cliente de un avaluo
	 */
	public ClienteDTO buscarCliente(Long id);

	/**
	 * Busca la persona que recibira el avaluo.
	 */
	public UsuarioDTO buscarPersonaRecibe(Long id);

	/**
	 * Busca el abogado que creo un caso, cuando el avaluo es de tipo remate.
	 */
	public UsuarioDTO buscarAbogado(Long id);

	/**
	 * Carga el correo electrónico del asesor del avaluo
	 * 
	 * @param id
	 *            El identificador del avaluo
	 * @return el correo del asesor o vacio si no existe
	 */
	String cargarCorreoAsesor(Long id);

	/**
	 * Obtiene todos los avaluos aprobados de un cliente
	 * 
	 * @param tipo
	 *            de documento del cliente
	 * @param numero
	 *            de documento del cliente
	 * @return Lista con los identificadores de los avaluos aprobados asociados al
	 *         cliente.
	 */
	public List<AvaluoDTO> encontrarAvaluosAprobadosDeCliente(int tipoDocumento, Long numeroDocumento);

	public AvaluoDTO encontrarPorIdTinsa(Long id);

	public AvaluoDTO encontrarAvaluoPorCodigoExterno(String codigoExterno, Long idEntidad);

	public AvaluoDTO encontrarAvaluoPorCodigoExternoAndNumeroDocumentoCliente(String codigoExterno, Long idEntidad,
			Long numeroDocumentoCliente);
	
	public boolean actualizarPerito(AvaluoDTO avaluoDTO, UsuarioDTO perito,
            UsuarioDTO usuario);

}
