package com.helio4.bancol.avaluos.servicio;

import com.helio4.bancol.avaluos.dto.DivipolaDTO;
import com.helio4.bancol.avaluos.dto.EstadoAvaluoDTO;
import com.helio4.bancol.avaluos.dto.RegionalDTO;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;
import com.helio4.bancol.avaluos.ensamblador.EstadoAvaluoEnsamblador;
import com.helio4.bancol.avaluos.ensamblador.UsuarioEnsamblador;
import com.helio4.bancol.avaluos.modelo.Divipola;
import com.helio4.bancol.avaluos.modelo.Usuario;
import com.helio4.bancol.avaluos.modelo.Usuario.DocumentoIdentificacion;
import com.helio4.bancol.avaluos.persistencia.DivipolaRepository;
import com.helio4.bancol.avaluos.persistencia.UsuarioRepository;
import com.helio4.bancol.avaluos.servicio.datos.UsuarioService;
import com.helio4.bancol.avaluos.servicio.excepciones.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component(value="repositoryUsuarioService")
@Transactional(readOnly = true)
public class RepositoryUsuarioService implements UsuarioService {

	private static Logger log = LoggerFactory.getLogger( RepositoryUsuarioService.class );

	private final UsuarioRepository usuarioRepository;

	private final UsuarioEnsamblador usuarioEnsamblador;

	private final EstadoAvaluoEnsamblador estadoAvaluoEnsamblador;
	
	private final DivipolaRepository divipolarepository;

	@Autowired
	public RepositoryUsuarioService(DivipolaRepository divipolarepository, UsuarioEnsamblador usuarioEnsamblador, UsuarioRepository usuarioRepository, EstadoAvaluoEnsamblador estadoAvaluoEnsamblador) {
		this.divipolarepository = divipolarepository;
		this.usuarioEnsamblador = usuarioEnsamblador;
		this.usuarioRepository = usuarioRepository;
		this.estadoAvaluoEnsamblador = estadoAvaluoEnsamblador;
	}

	@Transactional
	@Override
	public UsuarioDTO crear(UsuarioDTO usuarioDTO) {
		Usuario usuario = null;
		try {
			usuario = usuarioEnsamblador.crearUsuario(usuarioDTO);
		} catch (RolNotFoundException e) {
			log.error("RolNotFoundException: ", e);
		} catch (DivipolaNotFoundException e) {
		    log.error("DivipolaNotFoundException: ", e);
        } catch (EntidadNotFoundException e) {
		    log.error("EntidadNotFoundException: ", e);
        } catch (RegionalNotFoundException e) {
		    log.error("RegionalNotFoundException: ", e);
        } catch (TipoAvaluoNotFoundException e) {
		    log.error("TipoAvaluoNotFoundException: ", e);
        }
		usuarioRepository.save(usuario);
		return usuarioDTO;
	}

	@Transactional(rollbackFor = UsuarioNotFoundException.class)
	@Override
	public UsuarioDTO eliminar(Integer tipoDocumentoIdentificacion, Long numeroDocumento) throws UsuarioNotFoundException {
		Usuario deleted = usuarioRepository.findOne(new DocumentoIdentificacion(tipoDocumentoIdentificacion, numeroDocumento));
		if (deleted == null) {
			throw new UsuarioNotFoundException();
		}
		usuarioRepository.delete(deleted);
		return usuarioEnsamblador.escribirDTO(deleted);
	}

	@Transactional(readOnly = true)
	@Override
	public List<UsuarioDTO> encontrarTodos() {
		return usuarioEnsamblador.escribirListaDTO(usuarioRepository.findAll());
	}

	@Transactional(readOnly = true)
	@Override
	public List<UsuarioDTO> encontrarUsuarios() {
		return usuarioRepository.encontrarUsuarios();
	}

    @Transactional(readOnly = true)
    @Override
    public UsuarioDTO encontrarPorId(
            Integer tipoDocumentoIdentificacion, Long numeroDocumento) {
        return usuarioEnsamblador.escribirDTO(
                usuarioRepository.findOne(new DocumentoIdentificacion(
                        tipoDocumentoIdentificacion, numeroDocumento)));
    }

	@Transactional(readOnly = true)
	@Override
	public List<UsuarioDTO> encontrarUsuariosPorPermiso(String permiso) {
		return usuarioEnsamblador.escribirListaDTO(usuarioRepository.encontrarUsuariosPorPermiso(permiso));
	}

	@Transactional(readOnly = true)
	@Override
    public List<UsuarioDTO> encontrarUsuariosPorPermisoRegional(
            String permiso, Long regionalId) {
        return usuarioEnsamblador.escribirListaDTO(usuarioRepository.encontrarUsuariosPorPermisoRegional(permiso, regionalId));
    }

    @Transactional(readOnly = true)
	@Override
    public boolean verificarAliasExistente(String alias) {
        Boolean resultado = usuarioRepository
            .verificarAliasExistente(alias);
        return resultado == null ? false : resultado;
    }

    @Transactional(readOnly = true)
	@Override
    public boolean verificarNumeroTipoDocumentoExistente(
            Integer tipoDocumentoIdentificacion, Long numeroDocumento) {
        Boolean resultado = usuarioRepository.verificarNumeroTipoDocumentoExistente(
                tipoDocumentoIdentificacion, numeroDocumento);
        return resultado == null ? false : resultado;
    }

    @Transactional(rollbackFor = UsuarioNotFoundException.class)
    @Override
    public UsuarioDTO actualizar(UsuarioDTO actualizado) {
        try {
            usuarioEnsamblador.actualizarUsuario(
                    actualizado.getTipoDocumentoIdentificacion(),
                    actualizado.getNumeroDocumento(), actualizado);
        } catch (UsuarioNotFoundException e) {
            log.debug("UsuarioNotFoundException: Error el usuario {}, no se encontró.",
                    actualizado );
            return null;
        } catch (RolNotFoundException e) {
            log.debug("RolNotFoundException: Error el rol {} no se encontró.",
                    actualizado.getRol());
            return null;
        } catch (EntidadNotFoundException e) {
            log.debug("EntidadNotFoundException: Una entidad {} no se encontró.",
                    actualizado.getEntidades());
            return null;
        } catch (RegionalNotFoundException e) {
            log.debug("RegionalNotFoundException: una regional {} no se encontró.",
                    actualizado.getRegionales());
            return null;
        } catch (TipoAvaluoNotFoundException e) {
            log.debug("TipoAvaluoNotFoundException: un tipo avaluo {} no se encontró.",
                    actualizado.getTipoAvaluos());
            return null;
        } catch (DivipolaNotFoundException e) {
			e.printStackTrace();
		}
        return actualizado;
    }

    @Transactional(rollbackFor = UsuarioNotFoundException.class)
    @Override
    public UsuarioDTO actualizarContrasena(UsuarioDTO actualizado) {
    	actualizar(actualizado);
        return actualizado;
    }

	@Transactional(readOnly = true)
	@Override
	public UsuarioDTO encontrarPorNombreUsuario(String nombreUsuario) {
		return usuarioEnsamblador.escribirDTO(usuarioRepository.encontrarPor(nombreUsuario));
	}

	@Transactional(readOnly = true)
	@Override
	public List<UsuarioDTO> buscarPeritosDisponibles(Long divipolaId) {
		return usuarioEnsamblador.escribirListaDTO(usuarioRepository.buscarPeritosDisponibles(divipolaId));
	}

	@Transactional(readOnly = true)
	@Override
	public List<UsuarioDTO> buscarPeritosDisponibles(Long divipolaId,
            String tipoVia, int cuadrasViaAdelante, int cuadrasViaAtras,
			int cuadrasViaGenAdelante, int cuadrasViaGenAtras) {
		return usuarioEnsamblador.escribirListaDTO(usuarioRepository.buscarPeritosDisponibles(divipolaId, tipoVia, cuadrasViaAdelante, cuadrasViaAtras, cuadrasViaGenAdelante, cuadrasViaGenAtras));
	}

	@Transactional(readOnly = true)
	@Override
	public List<EstadoAvaluoDTO> encontrarEstadosActuales(Integer tipoDocumentoIdentificacion, Long numeroDocumento) {
		return estadoAvaluoEnsamblador.escribirListaDTO(usuarioRepository.encontrarEstadosActuales(tipoDocumentoIdentificacion, numeroDocumento));
	}

	@Transactional(readOnly = true)
	@Override
	public List<UsuarioDTO> buscarPeritosDisponibles(Long divipolaId,
            String tipoVia, int cuadrasViaAdelante, int cuadrasViaAtras,
			int cuadrasViaGenAdelante, int cuadrasViaGenAtras,
			int diasProximaCita) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)-diasProximaCita);
		Date fechaInicial = calendar.getTime();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)+(diasProximaCita*2));
		Date fechaFinal = calendar.getTime();
		return usuarioEnsamblador.escribirListaDTO(usuarioRepository.buscarPeritosDisponibles(fechaInicial, fechaFinal, divipolaId, tipoVia, cuadrasViaAdelante, cuadrasViaAtras, cuadrasViaGenAdelante, cuadrasViaGenAtras));
	}

	@Transactional(readOnly = true)
	@Override
	public List<UsuarioDTO> buscarPeritosDisponiblesDias(Long divipolaId, String tipoVia,int diasProximaCita){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)-diasProximaCita);
		Date fechaInicial = calendar.getTime();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)+(diasProximaCita*2));
		Date fechaFinal = calendar.getTime();
		List<Usuario> usuarios = this.usuarioRepository.buscarPeritosDisponiblesDias(fechaInicial, fechaFinal, divipolaId, tipoVia);
		return usuarioEnsamblador.escribirListaDTO(usuarios);
	}

	@Transactional(readOnly = true)
	@Override
	public List<UsuarioDTO> encontrarPorRol(Long id) {
		return usuarioRepository.encontrarPorRol(id).stream().map(usuarioEnsamblador::escribirDTO).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	@Override
	public List<UsuarioDTO> encontrarUsuariosRegional(RegionalDTO regionalDTO) {
		return null;
	}

	@Transactional(readOnly = true)
	@Override
	public UsuarioDTO encontrarUsuarioConEntidades(
			Integer tipoDocumentoIdentificacion, Long numeroDocumento) {
		return usuarioEnsamblador.escribirDTO(usuarioRepository
                .encontrarUsuarioConEntidades(new DocumentoIdentificacion(
                        tipoDocumentoIdentificacion, numeroDocumento)));
	}

	@Transactional(readOnly = true)
	@Override
	public UsuarioDTO encontrarUsuarioConRegionales(
			Integer tipoDocumentoIdentificacion, Long numeroDocumento) {
		return usuarioEnsamblador.escribirDTO(usuarioRepository.encontrarUsuarioConRegionales(new DocumentoIdentificacion(tipoDocumentoIdentificacion, numeroDocumento)));
	}

	@Transactional(readOnly = true)
	@Override
	public List<UsuarioDTO> buscarPeritosDisponiblesPorRegional(Long regionalId) {
		return usuarioEnsamblador.escribirListaDTO(usuarioRepository.buscarPeritosDisponiblesPorRegional(regionalId));
	}

	@Transactional(readOnly = true)
	@Override
	public String cargarContrasena(UsuarioDTO usuarioDTO) {
		return usuarioRepository.encontrarPass(usuarioDTO.getNombreUsuario());
	}

	@Transactional(readOnly = true)
    @Override
	public UsuarioDTO encontrarUsuarioAvaluoPorEstado(Long idAvaluo, Integer estado) {
		UsuarioDTO usuario = null;
		List<Usuario> usuarios = usuarioRepository.encontrarUsuarioAvaluoPorEstado(idAvaluo, estado);

		if(usuarios!=null && !usuarios.isEmpty()){
			usuario = usuarioEnsamblador.escribirDTO(usuarios.get(0));
		}

		return usuario;
	}

	@Transactional(readOnly = true)
	@Override
	public List<UsuarioDTO> buscarCoordinadoresAbogados() {
		return usuarioEnsamblador.escribirListaDTO(usuarioRepository.encontrarCoordinadoresAbogados());
	}

	@Transactional(readOnly = true)
	@Override
	public List<UsuarioDTO> encontrarUsuariosPorCoordAbogados(Integer tipoDocCoordAsignado, Long docCoordAsignado) {
		return usuarioEnsamblador.escribirListaDTO(usuarioRepository.encontrarUsuariosPorCoordAbogados(tipoDocCoordAsignado, docCoordAsignado));
	}

	@Transactional(readOnly = true)
    @Override
    public List<UsuarioDTO> encontrarCoordinadoresAsesores() {
        return usuarioEnsamblador.escribirListaDTO(usuarioRepository.encontrarCoordinadoresAsesores());
    }

	@Transactional(readOnly = true)
	@Override
	public List<UsuarioDTO> buscarPeritosDisponibles() {
		return this.usuarioEnsamblador.escribirListaDTO( this.usuarioRepository.buscarPeritosDisponibles() );
	}

	@Transactional(readOnly = true)
	@Override
	public List<UsuarioDTO> buscarPeritosDisponibles(
			DivipolaDTO divipolaBusqueda) throws DivipolaNotFoundException {
		Divipola divipola = this.divipolarepository.findOne(divipolaBusqueda.getId());
		if (divipola == null) {
			throw new DivipolaNotFoundException();
		}
		return this.usuarioEnsamblador.escribirListaDTO( this.usuarioRepository.buscarPeritosDisponibles(divipola.getId()));
	}

	@Transactional(readOnly = true)
	@Override
	public List<UsuarioDTO> buscarPeritosDisponiblesDepartamento(
			String departamentoBusqueda) {
		return this.usuarioEnsamblador.escribirListaDTO(this.usuarioRepository.buscarPeritosDisponiblesDepartamento(departamentoBusqueda));
	}

	@Transactional(readOnly = true)
	@Override
	public List<UsuarioDTO> buscarDisponbilesNombre(String peritoABuscar) {
		return this.usuarioEnsamblador.escribirListaDTO(this.usuarioRepository.buscarPeritosDisponiblesNombres( "%"+peritoABuscar+"%"));
	}

	@Transactional(readOnly = true)
	@Override
	public UsuarioDTO buscarPorNumeroDocumento(Long numeroDocumento) {
		return this.usuarioRepository.encontrarPorNumeroDocumento(numeroDocumento);
	}
	
	@Transactional(rollbackFor = UsuarioNotFoundException.class)
	public void actualizar(int tipoDocumentoNuevo,Long numeroDocumentoNuevo,int tipoDocumentoAntiguo,Long numeroDocumentoAntiguo){
		this.usuarioRepository.actualizarUsuario( numeroDocumentoNuevo, numeroDocumentoAntiguo, tipoDocumentoNuevo,tipoDocumentoAntiguo);
		
	}
	
	
}
