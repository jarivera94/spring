package com.helio4.bancol.avaluos.ensamblador;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.DivipolaDTO;
import com.helio4.bancol.avaluos.dto.EntidadDTO;
import com.helio4.bancol.avaluos.dto.EventoDTO;
import com.helio4.bancol.avaluos.dto.PaginaInicioDTO;
import com.helio4.bancol.avaluos.dto.PermisoDTO;
import com.helio4.bancol.avaluos.dto.RegionalDTO;
import com.helio4.bancol.avaluos.dto.RolDTO;
import com.helio4.bancol.avaluos.dto.TipoAvaluoDTO;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;
import com.helio4.bancol.avaluos.modelo.Divipola;
import com.helio4.bancol.avaluos.modelo.Entidad;
import com.helio4.bancol.avaluos.modelo.Evento;
import com.helio4.bancol.avaluos.modelo.PaginaInicio;
import com.helio4.bancol.avaluos.modelo.Permiso;
import com.helio4.bancol.avaluos.modelo.Regional;
import com.helio4.bancol.avaluos.modelo.Rol;
import com.helio4.bancol.avaluos.modelo.TipoAvaluo;
import com.helio4.bancol.avaluos.modelo.Usuario;
import com.helio4.bancol.avaluos.modelo.Usuario.DocumentoIdentificacion;
import com.helio4.bancol.avaluos.persistencia.DivipolaRepository;
import com.helio4.bancol.avaluos.persistencia.EntidadRepository;
import com.helio4.bancol.avaluos.persistencia.PaginaInicioRepository;
import com.helio4.bancol.avaluos.persistencia.PermisoRepository;
import com.helio4.bancol.avaluos.persistencia.RegionalRepository;
import com.helio4.bancol.avaluos.persistencia.RolRepository;
import com.helio4.bancol.avaluos.persistencia.TipoAvaluoRepository;
import com.helio4.bancol.avaluos.persistencia.UsuarioRepository;
import com.helio4.bancol.avaluos.servicio.excepciones.DivipolaNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.EntidadNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.PaginaInicioNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.PermisoNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.RegionalNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.RolNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.TipoAvaluoNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.UsuarioNotFoundException;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Component
public class UsuarioEnsamblador {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PaginaInicioRepository paginaInicioRepository;

    @Autowired
    private PermisoRepository permisoRepository;

    @Autowired
    private EntidadRepository entidadRepository;

    @Autowired
    private RegionalRepository regionalRepository;

    @Autowired
    private TipoAvaluoRepository tipoAvaluoRepository;

    @Autowired
    private EventoEnsamblador eventoEnsamblador;

    @Autowired
    private EntidadEnsamblador entidadEnsamblador;

    @Autowired
    private RegionalEnsamblador regionalEnsamblador;

    @Autowired
    private TipoAvaluoEnsamblador tipoAvaluoEnsamblador;

    @Autowired
    private DivipolaEnsamblador divipolaEnsamblador;

    @Autowired
    private DivipolaRepository divipolaRepository;

    public Usuario crearUsuario(UsuarioDTO usuarioDTO)
        throws RolNotFoundException, DivipolaNotFoundException,
        EntidadNotFoundException, RegionalNotFoundException, TipoAvaluoNotFoundException {
        return crearUsuario(new Usuario(), usuarioDTO);
    }

    public Usuario crearUsuario(Usuario usuario, UsuarioDTO usuarioDTO)
        throws RolNotFoundException,
        EntidadNotFoundException, RegionalNotFoundException,
        TipoAvaluoNotFoundException, DivipolaNotFoundException {
        usuario.setId(new DocumentoIdentificacion(usuarioDTO
                    .getTipoDocumentoIdentificacion(), usuarioDTO
                    .getNumeroDocumento()));
        usuario.setNombres(usuarioDTO.getNombres());
        usuario.setApellidos(usuarioDTO.getApellidos());
        usuario.setContrasena(usuarioDTO.getContrasenaEncriptada());
        usuario.setNombreUsuario(usuarioDTO.getNombreUsuario());
        usuario.setEmail(usuarioDTO.getEmail());
        
        usuario.setTipoVia(usuarioDTO.getTipoVia());
        usuario.setNumeroVia(usuarioDTO.getNumeroVia());
        usuario.setComplementoVia(usuarioDTO.getComplementoVia());
        usuario.setNumeroViaGeneradora(usuarioDTO.getNumeroViaGeneradora());
        usuario.setComplementoViaGeneradora(usuarioDTO
                .getComplementoViaGeneradora());
        usuario.setPlaca(usuarioDTO.getPlaca());
        usuario.setComplementoPlaca(usuarioDTO.getComplementoPlaca());
        usuario.setAdicional(usuarioDTO.getAdicional());
        usuario.setDireccion(usuarioDTO.getTipoVia() + " "
                + usuarioDTO.getNumeroVia() + " "
                + usuarioDTO.getComplementoVia() != null ?
                usuarioDTO.getComplementoVia() : "" + " #"
                + usuarioDTO.getNumeroViaGeneradora() + " "
                + usuarioDTO.getComplementoViaGeneradora() != null ?
                usuarioDTO.getComplementoViaGeneradora() : "" + " "
                + usuarioDTO.getPlaca()
                + usuarioDTO.getAdicional());
        usuario.setRegistro(usuarioDTO.getRegistro());
        usuario.setIva(usuarioDTO.getIva());
        usuario.setActivoParaAsignacion(usuarioDTO.getActivoParaAsignacion());
        usuario.setUsuarioVigente(usuarioDTO.getUsuarioVigente());
        usuario.setNombreBanco(usuarioDTO.getNombreBanco());
        usuario.setTipoCuentaBanco(usuarioDTO.getTipoCuentaBanco());
        usuario.setNumeroCuentaBanco(usuarioDTO.getNumeroCuentaBanco());
        usuario.setNombreTitular(usuarioDTO.getNombreTitular());
        usuario.setTipoIdentificacionTitular(usuarioDTO.getTipoIdentificacionTitular());
        usuario.setNumeroIdentificacionTitular(usuarioDTO.getNumeroIdentificacionTitular());
        usuario.setCelular(usuarioDTO.getCelular());
        usuario.setCalificacion(usuarioDTO.getCalificacion());
		
        Rol rol = rolRepository.findOne(usuarioDTO.getRol().getId());
        if (rol == null) {
            throw new RolNotFoundException();
        }
       
        usuario.setRol(rol);
        usuario.setTipoDocumentoCoordinadorAsignado(usuarioDTO.getTipoDocumentoCoordinador());
        usuario.setNumeroDocumentoCoordinadorAsignado(usuarioDTO.getNumeroDocumentoCoordinador());
        if (usuarioDTO.getEntidades() != null
                && !usuarioDTO.getEntidades().isEmpty()) {
            cargarEntidades(usuario, usuarioDTO.getEntidades());
        }
        if (usuarioDTO.getRegionales() != null
                && !usuarioDTO.getRegionales().isEmpty()) {
            cargarRegionales(usuario, usuarioDTO.getRegionales());
        }
        if (usuarioDTO.getTipoAvaluos() != null
                && !usuarioDTO.getTipoAvaluos().isEmpty()) {
            cargarTipoAvaluos(usuario, usuarioDTO.getTipoAvaluos());
        }
        if (usuarioDTO.getDivipolas() !=null && !usuarioDTO.getDivipolas().isEmpty()) {
        	cargarDivipolas(usuario,usuarioDTO.getDivipolas());
        }
        return usuario;
    }

    private void cargarDivipolas(Usuario usuario,List<DivipolaDTO> divipolasDTO) throws DivipolaNotFoundException {
    	List<Divipola> divipolas = new ArrayList<Divipola>();
    	for (DivipolaDTO divipolaDTO : divipolasDTO) {
    		if(divipolaDTO!=null){
    			Divipola divipola = this.divipolaRepository.findOne(divipolaDTO.getId());
        		if (divipola == null) {
        			throw new DivipolaNotFoundException();
        		}
        		divipolas.add(divipola);
    		}
    	}
    	usuario.setDivipolas(divipolas);    	
    }
    private void cargarEntidades(Usuario usuario, List<EntidadDTO> entidadesDTO) throws EntidadNotFoundException {
        List<Entidad> entidades = new ArrayList<Entidad>();
        for (EntidadDTO entidadDTO : entidadesDTO) {
            Entidad entidad = entidadRepository.findOne(entidadDTO.getId());
            if (entidad == null) {
                throw new EntidadNotFoundException();
            }
            entidades.add(entidad);
        }
        usuario.setEntidades(entidades);
    }

    private void cargarRegionales(Usuario usuario, List<RegionalDTO> regionalesDTO) throws RegionalNotFoundException {
        List<Regional> regionales = new ArrayList<Regional>();
        for (RegionalDTO regionalDTO : regionalesDTO) {
            Regional regional = regionalRepository.findOne(regionalDTO.getId());
            if (regional == null) {
                throw new RegionalNotFoundException();
            }
            regionales.add(regional);
        }
        usuario.setRegionales(regionales);
    }

    private void cargarTipoAvaluos(Usuario usuario, List<TipoAvaluoDTO> tiposAvaluosDTO) throws TipoAvaluoNotFoundException {
        List<TipoAvaluo> tiposAvaluo = new ArrayList<TipoAvaluo>();
        for (TipoAvaluoDTO tipoAvaluoDTO : tiposAvaluosDTO) {
            TipoAvaluo tipoAvaluo = tipoAvaluoRepository.findOne(tipoAvaluoDTO.getId());
            if (tipoAvaluo == null) {
                throw new TipoAvaluoNotFoundException();
            }
            tiposAvaluo.add(tipoAvaluo);
        }
        usuario.setTipoAvaluos(tiposAvaluo);
    }

    public void actualizarUsuario(Integer tipoDocumentoIdentificacion,
            Long numeroDocumento, UsuarioDTO usuarioDTO)
        throws UsuarioNotFoundException, RolNotFoundException,
                          EntidadNotFoundException,
                          RegionalNotFoundException, TipoAvaluoNotFoundException, DivipolaNotFoundException {
        Usuario usuario = usuarioRepository.findOne(new DocumentoIdentificacion(tipoDocumentoIdentificacion, numeroDocumento));
        if (usuario == null) {
            throw new UsuarioNotFoundException();
        }
        crearUsuario(usuario, usuarioDTO);
    }

    public List<UsuarioDTO> escribirListaDTO(List<Usuario> usuarios) {
        List<UsuarioDTO> usuarioDTOs = new ArrayList<UsuarioDTO>();
        for (Usuario usuario : usuarios) {
            usuarioDTOs.add(escribirDTO(usuario));
        }
        return usuarioDTOs;
    }

    public UsuarioDTO escribirDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setTipoDocumentoIdentificacion(usuario
                .getTipoDocumentoIdentificacion());
        usuarioDTO.setNumeroDocumento(usuario.getNumeroDocumento());
        usuarioDTO.setNombres(usuario.getNombres());
        usuarioDTO.setApellidos(usuario.getApellidos());
        usuarioDTO.setContrasenaEncriptada(usuario.getContrasena());
        usuarioDTO.setNombreUsuario(usuario.getNombreUsuario());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setDivipolas(divipolaEnsamblador.escribirListaDTO(usuario.getDivipolas()));
        usuarioDTO.setTipoVia(usuario.getTipoVia());
        usuarioDTO.setNumeroVia(usuario.getNumeroVia());
        usuarioDTO.setComplementoVia(usuario.getComplementoVia());
        usuarioDTO.setNumeroViaGeneradora(usuario.getNumeroViaGeneradora());
        usuarioDTO.setComplementoViaGeneradora(usuario
                .getComplementoViaGeneradora());
        usuarioDTO.setPlaca(usuario.getPlaca());
        usuarioDTO.setComplementoPlaca(usuario.getComplementoPlaca());
        usuarioDTO.setAdicional(usuario.getAdicional());
        usuarioDTO.setDireccion(usuario.getDireccion());
        usuarioDTO.setIva(usuario.getIva());
        usuarioDTO.setActivoParaAsignacion(usuario.getActivoParaAsignacion());
        usuarioDTO.setUsuarioVigente(usuario.getUsuarioVigente());
        usuarioDTO.setRegistro(usuario.getRegistro());
        usuarioDTO.setNombreBanco(usuario.getNombreBanco());
        usuarioDTO.setTipoCuentaBanco(usuario.getTipoCuentaBanco());
        usuarioDTO.setNumeroCuentaBanco(usuario.getNumeroCuentaBanco());
        usuarioDTO.setNombreTitular(usuario.getNombreTitular());
        usuarioDTO.setTipoIdentificacionTitular(usuario.getTipoIdentificacionTitular());
        usuarioDTO.setNumeroIdentificacionTitular(usuario.getNumeroIdentificacionTitular());
        usuarioDTO.setCelular(usuario.getCelular());
        usuarioDTO.setCalificacion(usuario.getCalificacion());
        usuarioDTO.setRol(escribirDTO(usuario.getRol()));
        usuarioDTO.setTipoDocumentoCoordinador(usuario.getTipoDocumentoCoordinadorAsignado());
        usuarioDTO.setNumeroDocumentoCoordinador(usuario.getNumeroDocumentoCoordinadorAsignado());
        /*
         * if (usuario.getEventos() != null && !usuario.getEventos().isEmpty())
         * { escribirEventos(usuarioDTO, usuario.getEventos()); }
         */
        if (usuario.getEntidades() != null && !usuario.getEntidades().isEmpty()) {
            escribirEntidades(usuarioDTO, usuario.getEntidades());
        }
        if (usuario.getRegionales() != null
                && !usuario.getRegionales().isEmpty()) {
            escribirRegionales(usuarioDTO, usuario.getRegionales());
        }
        if (usuario.getTipoAvaluos() != null
                && !usuario.getTipoAvaluos().isEmpty()) {
            escribirTipoAvaluos(usuarioDTO, usuario.getTipoAvaluos());
        }
//        if (usuario.getDivipolas() !=null && !usuario.getDivipolas().isEmpty()) {
//        	escribirDivipolas(usuarioDTO, usuario.getDivipolas());
//        }
        return usuarioDTO;
    }

    private void escribirDivipolas(UsuarioDTO usuarioDTO,
			List<Divipola> divipolas) {
		List<DivipolaDTO> divipolasDTO = new ArrayList<DivipolaDTO>();
		for (Divipola divipola:divipolas) {
			divipolasDTO.add(this.divipolaEnsamblador.escribirDTO(divipola));
		} 
		usuarioDTO.setDivipolas(divipolasDTO);
	}

	private void escribirRegionales(UsuarioDTO usuarioDTO,
            List<Regional> regionales) {
        List<RegionalDTO> regionalDTOs = new ArrayList<RegionalDTO>();
        for (Regional regional : regionales) {
            regionalDTOs.add(regionalEnsamblador.escribirDTO(regional));
        }
        usuarioDTO.setRegionales(regionalDTOs);
    }

    private void escribirEventos(UsuarioDTO usuarioDTO, Set<Evento> eventos) {
        Set<EventoDTO> eventoDTOs = new HashSet<EventoDTO>();
        for (Evento evento : eventos) {
            eventoDTOs.add(eventoEnsamblador.escribirDTO(evento));
        }
        usuarioDTO.setEventos(eventoDTOs);
    }

    private void escribirEntidades(UsuarioDTO usuarioDTO,
            List<Entidad> entidades) {
        List<EntidadDTO> entidadDTOs = new ArrayList<EntidadDTO>();
        for (Entidad entidad : entidades) {
            entidadDTOs.add(entidadEnsamblador.escribirDTO(entidad));
        }
        usuarioDTO.setEntidades(entidadDTOs);
    }

    private void escribirTipoAvaluos(UsuarioDTO usuarioDTO,
            List<TipoAvaluo> tipoAvaluos) {
        List<TipoAvaluoDTO> tipoAvaluoDTOs = new ArrayList<TipoAvaluoDTO>();
        for (TipoAvaluo tipoAvaluo : tipoAvaluos) {
            tipoAvaluoDTOs.add(tipoAvaluoEnsamblador.escribirDTO(tipoAvaluo));
        }
        usuarioDTO.setTipoAvaluos(tipoAvaluoDTOs);
    }

    public RolDTO escribirDTO(Rol rol) {
        RolDTO rolDTO = new RolDTO();
        rolDTO.setId(rol.getId());
        rolDTO.setNombre(rol.getNombre());
        rolDTO.setPaginaInicio(escribirDTO(rol.getPaginaInicio()));
        //escribirPermisos(rolDTO, rol.getPermisos());
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder()
                .build();
        mapperFactory.classMap(Permiso.class, PermisoDTO.class)
                .byDefault()
                .register();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        rolDTO.setPermisos(mapper.mapAsList(rol.getPermisos(), PermisoDTO.class));
        return rolDTO;
    }

    public List<RolDTO> escribirListaRoles(List<Rol> roles) {
        List<RolDTO> rolesDTOs = new ArrayList<RolDTO>();
        for (Rol rol : roles) {
            rolesDTOs.add(escribirDTO(rol));
        }
        return rolesDTOs;
    }

    private void escribirPermisos(RolDTO rolDTO, List<Permiso> permisos) {
        List<PermisoDTO> permisoDTOs = new ArrayList<PermisoDTO>();
        for (Permiso permiso : permisos) {
            permisoDTOs.add(escribirDTO(permiso));
        }
        rolDTO.setPermisos(permisoDTOs);
    }

    public PermisoDTO escribirDTO(Permiso permiso) {
        if (permiso == null) {
            return null;
        }
        return new PermisoDTO(permiso.getId(), permiso.getNombre());
    }

    public List<PermisoDTO> escribirListaPermisos(List<Permiso> permisos) {
        List<PermisoDTO> permisoDTOs = new ArrayList<PermisoDTO>();
        for (Permiso permiso : permisos) {
            permisoDTOs.add(escribirDTO(permiso));
        }
        return permisoDTOs;
    }

    public PaginaInicioDTO escribirDTO(PaginaInicio paginaInicio) {
        PaginaInicioDTO paginaInicioDTO = new PaginaInicioDTO();
        paginaInicioDTO.setId(paginaInicio.getId());
        paginaInicioDTO.setNombre(paginaInicio.getNombre());
        paginaInicioDTO.setUrl(paginaInicio.getUrl());
        return paginaInicioDTO;
    }

    public List<PaginaInicioDTO> escribirListaPaginaInicio(
            List<PaginaInicio> paginasInicio) {
        List<PaginaInicioDTO> paginasInicioDTOs = new ArrayList<PaginaInicioDTO>();
        for (PaginaInicio paginaInicio : paginasInicio) {
            paginasInicioDTOs.add(escribirDTO(paginaInicio));
        }
        return paginasInicioDTOs;
            }

    public void actualizarPaginaInicio(Long paginaInicioId,
            PaginaInicioDTO paginaInicioDTO)
        throws PaginaInicioNotFoundException {
        PaginaInicio paginaInicio = paginaInicioRepository
            .findOne(paginaInicioId);
        if (paginaInicio == null) {
            throw new PaginaInicioNotFoundException();
        }
        paginaInicio.setNombre(paginaInicioDTO.getNombre());
        paginaInicio.setUrl(paginaInicioDTO.getUrl());
    }

    public Rol crearRol(RolDTO rolDTO) throws PaginaInicioNotFoundException,
           PermisoNotFoundException {
               Rol rol = new Rol();
               rol.setNombre(rolDTO.getNombre());
               PaginaInicio paginaInicio = paginaInicioRepository.findOne(rolDTO
                       .getPaginaInicio().getId());
               if (paginaInicio == null) {
                   throw new PaginaInicioNotFoundException();
               }
               rol.setPaginaInicio(paginaInicio);
               if (rolDTO.getPermisos() != null && !rolDTO.getPermisos().isEmpty()) {
                   List<Permiso> permisos = new ArrayList<Permiso>();
                   for (PermisoDTO permisoDTO : rolDTO.getPermisos()) {
                       Permiso permiso = permisoRepository.findOne(permisoDTO.getId());
                       if (permiso == null) {
                           throw new PermisoNotFoundException();
                       }
                       permisos.add(permiso);
                   }
                   rol.setPermisos(permisos);
               }
               return rol;
    }

    public void actualizarRol(Long roldId, RolDTO rolDTO)
        throws RolNotFoundException, PaginaInicioNotFoundException {
        Rol rol = rolRepository.findOne(roldId);
        if (rol == null) {
            throw new RolNotFoundException();
        }
        rol.setNombre(rolDTO.getNombre());
        PaginaInicio paginaInicio = paginaInicioRepository.findOne(rolDTO
                .getPaginaInicio().getId());
        if (paginaInicio == null) {
            throw new PaginaInicioNotFoundException();
        }
        rol.setPaginaInicio(paginaInicio);
        List<Permiso> permisos = new ArrayList<Permiso>();
        for (PermisoDTO permisoDTO : rolDTO.getPermisos()) {
            Permiso permiso = permisoRepository.findOne(permisoDTO.getId());
            permisos.add(permiso);
        }
        rol.setPermisos(permisos);
        /*System.out.println("Permisos para el rol modificado: " + rol.getNombre());
        for(Permiso permiso:rol.getPermisos()){
            System.out.println("- " + permiso.getNombre());
        }*/
    }

    public void actualizarPermiso(Long permisoId, PermisoDTO permisoDTO)
        throws PermisoNotFoundException, RolNotFoundException {
        Permiso permiso = permisoRepository.encontrarPermisoConRoles(permisoId);
        if (permiso == null) {
            throw new PermisoNotFoundException();
        }
        permiso.setNombre(permisoDTO.getNombre());
    }

}
