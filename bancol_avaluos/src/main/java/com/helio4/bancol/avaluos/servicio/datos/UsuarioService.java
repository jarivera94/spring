package com.helio4.bancol.avaluos.servicio.datos;

import java.util.List;

import com.helio4.bancol.avaluos.servicio.excepciones.DivipolaNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.UsuarioNotFoundException;
import org.springframework.stereotype.Service;

import com.helio4.bancol.avaluos.dto.DivipolaDTO;
import com.helio4.bancol.avaluos.dto.EstadoAvaluoDTO;
import com.helio4.bancol.avaluos.dto.RegionalDTO;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;

@Service
public interface UsuarioService {

    /**
     * Crea un nuevo usuario.
     * @param usuario La información del nuevo usuario.
     * @return El valuo creado
     */
    public UsuarioDTO crear(UsuarioDTO usuarioDTO);

    /**
     * Elimina el usuario
     * @param usuarioId El identificador del usuario que se va a eliminar.
     * @throws UsuarioNotFoundException Si el usuario no existe.
     * @return El usuario que se eliminó.
     */
    public UsuarioDTO eliminar(Integer tipoDocumentoIdentificacion, Long numeroDocumento) throws UsuarioNotFoundException;

    /**
     * Metodo para consultar la contraseña del UsuarioActivo
     * @param usuarioDTO El usuario del que se quiere consultar la constraseña
     * @return la contraseña del usuario
     */
    public String cargarContrasena(UsuarioDTO usuarioDTO);

    /**
     * Encuentra todos los usuarios.
     * @return Una lista con todos los usuarios.
     */
    public List<UsuarioDTO> encontrarTodos();

    /**
     * Encuentra todos los usuarios.
     * @return Una lista con todos los usuarios.
     */
    public List<UsuarioDTO> encontrarUsuarios();

    /**
     * Encuentra el usuario por el identificador
     * @param tipoDocumentoIdentificacion el tipo de documento de identificacion
     * del usuario
     * @param numeroDocumento el numero del documento del usuario
     * @return el usuario
     */
    UsuarioDTO encontrarPorId(Integer tipoDocumentoIdentificacion, Long numeroDocumento);

    /**
     * Encuentra todos los usuarios de acuerdo al Permiso parametrizado.
     * @return Una lista con todos los usuarios encontrados segun el permiso parametrizado.
     */
    public List<UsuarioDTO> encontrarUsuariosPorPermiso(String permiso);

    /**
     * Encuentra todos los usuarios de acuerdo al {@link #permiso} y a la {@link #regionalId}.
     * @return Una lista con todos los usuarios encontrados segun el permiso y regional especificada.
     */
    public List<UsuarioDTO> encontrarUsuariosPorPermisoRegional(
            String permiso, Long regionalId);

    public List<UsuarioDTO> encontrarPorRol(Long id);

    /**
     * Encuentra el usuario por el nombreUsario.
     * @param nombreUsuario El identificador del usuario que se quiere encontrar.
     * @return
     */
    public UsuarioDTO encontrarPorNombreUsuario(String nombreUsuario);

    /** Busca todos los peritos disponbiles y activos. **/
    public List<UsuarioDTO> buscarPeritosDisponibles();
    
    public List<UsuarioDTO> buscarPeritosDisponibles(Long divipolaId);

    public List<UsuarioDTO> buscarPeritosDisponiblesDias(Long divipolaId, String tipoVia,int diasProximaCita);

    public List<UsuarioDTO> buscarPeritosDisponibles(Long divipolaId, String tipoVia, int cuadrasViaAdelante, int cuadrasViaAtras, int cuadrasViaGenAdelante, int cuadrasViaGenAtras);


    public List<UsuarioDTO> buscarPeritosDisponibles(Long divipolaId, String tipoVia, int cuadrasViaAdelante, int cuadrasViaAtras, int cuadrasViaGenAdelante, int cuadrasViaGenAtras, int diasProximaCita);
    /**
     * Actualiza la información de un usuario.
     * @param actualizado La información del usuario actualizado
     * @return 	El usuario actualizado
     */
    public UsuarioDTO actualizar(UsuarioDTO actualizado);

    /**
     * Actualiza la contrasena del {@link #actualizado}
     * @param actualizado el usuario actualizado
     * @return el usuario actualizado o nulo si la actualizacion falló
     */
    public UsuarioDTO actualizarContrasena(UsuarioDTO actualizado) ;

    /**
     * Retorna todos los estados actuales del Perito, esta es la cantidad de avaluos asignados
     * @param peritoId
     * @return
     */
    public List<EstadoAvaluoDTO> encontrarEstadosActuales(Integer tipoDocumentoIdentificacion, Long numeroDocumento);

    /**
     * Encuentra los usuarios que manejan esta regional {@link RegionalDTO}
     * @param regionalDTO
     * @return
     */
    List<UsuarioDTO> encontrarUsuariosRegional(RegionalDTO regionalDTO);

    UsuarioDTO encontrarUsuarioConEntidades(
            Integer tipoDocumentoIdentificacion, Long numeroDocumento);

    UsuarioDTO encontrarUsuarioConRegionales(
            Integer tipoDocumentoIdentificacion, Long numeroDocumento);

    public List<UsuarioDTO> buscarPeritosDisponiblesPorRegional(Long regionalId);

    public UsuarioDTO encontrarUsuarioAvaluoPorEstado(Long idAvaluo, Integer estado);

    /**
     * Encuentra los usuarios con ROL=Coordinador Abogado que existan
     * @return lista de Coordinadores de Abogados Encontrados
     */
    List<UsuarioDTO> buscarCoordinadoresAbogados();

    /**
     * Encuntra los usuarios coordinadores de asesores
     * @return
     */
    List<UsuarioDTO> encontrarCoordinadoresAsesores();

    /**
     * Encuentra todos los usuarios Abogados, asignados al coordinaodr_Abogado.
     * @return Una lista con todos los usuarios encontrados segun el permiso parametrizado.
     * @Param permiso: el permiso para establecer que es Rol=CoordAbogado
     * @Param tipoDocCoordAsignado: tipo documento de el CoordAbogado
     */
    List<UsuarioDTO> encontrarUsuariosPorCoordAbogados(
            Integer tipoDocCoordAsignado, Long docCoordAsignado);

    /**
     * Verifica si existe algun usuario con el tipo y numero de documento
     * @param tipoDocumentoIdentificacion El tipo de documento del usuario que se quiere encontrar.
     * @param numeroDocumento El numero de documento del usuario que se quiere encontrar.
     * @return true si el usuario existe false si el usuario no existe
     */
    boolean verificarNumeroTipoDocumentoExistente(
            Integer tipoDocumentoIdentificacion, Long numeroDocumento);

    /**
     * Verifica si existe algun usuario con el alias
     * @param alias el alias del usuario que se quiere encontrar.
     * @return true si existe un usuario con este alias false si el usuario no existe
     */
    boolean verificarAliasExistente(String alias);
    
    
    /**
     * Busca los peritos disponbiles de un municipio.
     * */
	public List<UsuarioDTO> buscarPeritosDisponibles(
			DivipolaDTO divipolaBusqueda)  throws DivipolaNotFoundException;

	/**
	 * Busca los peritos disponbiles en un departamento.
	 * */
	public List<UsuarioDTO> buscarPeritosDisponiblesDepartamento(
			String departamentoBusqueda);
	
	/**
	 * Busca los peritos disponibles donde contenga la ocurrencia del nombre.
	 * */
	public List<UsuarioDTO> buscarDisponbilesNombre(String peritoABuscar);
	
	public UsuarioDTO buscarPorNumeroDocumento(Long numeroDocumento);
	
	/**
	 * Actualiza el numero y tipo de documento de un usuario.
	 * @param tipoDocumentoNuevo,
	 * @param numeroDocumentoNuevo,
	 * @param tipoDocumentoAntiguo,
	 * @param numeroDocumentoAntiguo
	 * */
	public void actualizar(int tipoDocumentoNuevo,Long numeroDocumentoNuevo,int tipoDocumentoAntiguo,Long numeroDocumentoAntiguo);
	
}
