package com.helio4.bancol.avaluos.dominio;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.EntidadDTO;
import com.helio4.bancol.avaluos.dto.RolDTO;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;
import com.helio4.bancol.avaluos.servicio.datos.EntidadService;
import com.helio4.bancol.avaluos.servicio.NotificacionesService;
import com.helio4.bancol.avaluos.servicio.datos.RolService;
import com.helio4.bancol.avaluos.servicio.excepciones.UsuarioNotFoundException;
import com.helio4.bancol.avaluos.servicio.datos.UsuarioService;

@Component
public class UsuarioController {

    /** Logger */
    private static Logger log = LoggerFactory
        .getLogger(UsuarioController.class);

    @Autowired
    @Qualifier("repositoryRolService")
    private RolService rolService;

    @Autowired
    @Qualifier("repositoryUsuarioService")
    private UsuarioService usuarioService;

    @Autowired
    @Qualifier("repositoryEntidadService")
    private EntidadService entidadService;

	@Autowired
	private NotificacionesService notificacionesService;

    public UsuarioDTO cargarUsuario(Integer tipoDocumento, Long numeroDocumento) {
        return usuarioService.encontrarPorId(tipoDocumento, numeroDocumento);
    }

    public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO) {
        asignarContrasena(usuarioDTO);
        usuarioDTO = usuarioService.crear(usuarioDTO);
        return usuarioDTO;
    }

    public UsuarioDTO actualizarUsuario(Integer tipoDocumentoIdentificacion, Long numeroDocumento, UsuarioDTO usuarioDTOActualizado) {
    	
    	if(!tipoDocumentoIdentificacion.equals(usuarioDTOActualizado.getTipoDocumentoIdentificacion()) || !numeroDocumento.equals(usuarioDTOActualizado.getNumeroDocumento())){
    		usuarioService.actualizar(usuarioDTOActualizado.getTipoDocumentoIdentificacion(), usuarioDTOActualizado.getNumeroDocumento(), tipoDocumentoIdentificacion, numeroDocumento);
            usuarioDTOActualizado = usuarioService.encontrarPorId(usuarioDTOActualizado.getTipoDocumentoIdentificacion(), usuarioDTOActualizado.getNumeroDocumento());
    	}
        
        return usuarioService.actualizar(usuarioDTOActualizado);
    }

    public UsuarioDTO eliminarUsuario(UsuarioDTO usuarioDTO) {
        UsuarioDTO usuarioDTORes = new UsuarioDTO();
        try {
            usuarioDTORes = usuarioService.eliminar(
                    usuarioDTO.getTipoDocumentoIdentificacion(),
                    usuarioDTO.getNumeroDocumento());
        } catch (UsuarioNotFoundException e) {
            e.printStackTrace();
        }
        return usuarioDTORes;
    }

    public UsuarioDTO asignarContrasena(UsuarioDTO usuarioDTO){
        usuarioDTO.setContrasenaNoEncriptada(generarContrasena(8));
        usuarioDTO.setContrasenaEncriptada(
                encriptarContrasena(usuarioDTO.getContrasenaNoEncriptada()));
        return usuarioDTO;
    }

    public String generarContrasena(int longitud) {
        SecureRandom random = new SecureRandom();
        String contrasenaAleatoria = new BigInteger(130, random)
            .toString(32);
        return contrasenaAleatoria.substring(0, longitud);
    }

    public boolean guardarRol(RolDTO rolDTO) {
        rolService.crear(rolDTO);
        return true;
    }

    public String encriptarContrasena(String contrasenaNoEncriptada) {
        return BCrypt.hashpw(contrasenaNoEncriptada, BCrypt.gensalt());
    }

    public String buscarContrasena(UsuarioDTO usuarioDTO) {
        return usuarioService.cargarContrasena(usuarioDTO);
    }

    public void actualizarContrasena(UsuarioDTO usuarioDTO) {
        usuarioDTO = usuarioService.actualizarContrasena(usuarioDTO);
        notificarCambioContrasena(usuarioDTO); 
    }

    public void notificarUsuarioCreado(UsuarioDTO usuarioDTO) {
        notificacionesService.notificarUsuarioCreado(usuarioDTO);
    }

    public void notificarCambioContrasena(UsuarioDTO usuarioDTO) {
        notificacionesService.notificarCambioContrasena(usuarioDTO);
    }

    public List<UsuarioDTO> encontrarUsuarios() {
        return usuarioService.encontrarTodos();
    }

    public boolean verificarAliasExistente(String alias) {
        return usuarioService.verificarAliasExistente(alias);
    }

    public boolean verificarNumeroTipoDocumentoExistente(
            Integer tipoDocumentoIdentificacion, Long numeroDocumento) {
        return usuarioService.verificarNumeroTipoDocumentoExistente(
                tipoDocumentoIdentificacion, numeroDocumento);
    }

    public List<UsuarioDTO> encontarPorRol(Long id) {
        return usuarioService.encontrarPorRol(id);
    }

    public List<EntidadDTO> encontrarEntidadesPorUsuario(
            Integer tipoDocumentoIdentificacion, Long numeroDocumento) {
        return entidadService.encontrarEntidadesPorUsuario(
                tipoDocumentoIdentificacion, numeroDocumento);
    }

    public List<EntidadDTO> encontrarEntidades() {
        return entidadService.encontrarTodosActivos();
    }

    public List<UsuarioDTO> buscarCoordinadoresAbogados() {
        return usuarioService.buscarCoordinadoresAbogados();
    }

    public List<UsuarioDTO> buscarCoordinadoresAsesores() {
        return usuarioService.encontrarCoordinadoresAsesores();
    }

    public UsuarioDTO encontrarUsuarioAvaluoPorEstado(Long idAvaluo,
            Integer estado) {
        return usuarioService.encontrarUsuarioAvaluoPorEstado(idAvaluo, estado);
    }
}
