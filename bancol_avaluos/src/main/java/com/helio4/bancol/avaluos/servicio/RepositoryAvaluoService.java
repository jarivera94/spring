package com.helio4.bancol.avaluos.servicio;

import com.google.common.collect.Iterables;
import com.helio4.bancol.avaluos.dto.*;
import com.helio4.bancol.avaluos.ensamblador.AvaluoEnsamblador;
import com.helio4.bancol.avaluos.ensamblador.EstadoAvaluoEnsamblador;
import com.helio4.bancol.avaluos.ensamblador.FormatoInformeEnsamblador;
import com.helio4.bancol.avaluos.modelo.*;
import com.helio4.bancol.avaluos.modelo.Usuario.DocumentoIdentificacion;
import com.helio4.bancol.avaluos.persistencia.*;
import com.helio4.bancol.avaluos.servicio.datos.*;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.EventoNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.FormatoInformeNotFoundException;
import com.helio4.bancol.avaluos.servicio.util.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.resource.spi.IllegalStateException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@Component(value = "repositoryAvaluoService")
@Transactional(readOnly = true)
public class RepositoryAvaluoService implements AvaluoService {

    private final ReentrantLock asignarPeritoLock;
    private final ReentrantLock aceptarCasoLock;
    private final ReentrantLock rechazarCasoLock;
    private final ReentrantLock programarCitaLock;
    private final ReentrantLock reProgramarCitaLock;
    private final ReentrantLock solicitarDevolucionLock;
    private final ReentrantLock devolverLock;
    private final ReentrantLock reactivarLock;
    private final ReentrantLock confirmarVisitaLock;
    private final ReentrantLock cancelarAvaluoLock;
    private final ReentrantLock confirmarDocumentosLock;
    private final ReentrantLock confirmarPagoLock;
    private final ReentrantLock notificarHonorariosLock;
    private final ReentrantLock enviarLock;
    private final ReentrantLock solicitarCorreccionesLock;
    private final ReentrantLock enviarComiteLock;
    private final ReentrantLock aprobarLock;
    private final ReentrantLock crearInformeLock;

    private static Logger log = LoggerFactory
            .getLogger(RepositoryAvaluoService.class);

    private final AvaluoRepository avaluoRepository;

    private final SegmentoRepository segmentoRepository;

    private final EntidadRepository entidadRepository;

    private final FormatoInformeRepository formatoInformeRepository;

    private final AvaluoHipotecarioService avaluoHipotecarioService;

    private final AvaluoComercialService avaluoComercialService;

    private final AvaluoConstructorService avaluoConstructorService;

    private final FormatoInformeService formatoInformeService;

    private final AvaluoRemateService avaluoRemateService;

    private final AvaluoEnsamblador avaluoEnsamblador;

    private final EstadoAvaluoEnsamblador estadoAvaluoEnsamblador;

    private final FormatoInformeEnsamblador formatoInformeEnsamblador;

    private final UsuarioRepository usuarioRepository;

    private final EstadoAvaluoRepository estadoAvaluoRepository;

    private final NotificacionesService notificacionesService;

    private final EventoService eventoService;

    @Autowired
    public RepositoryAvaluoService(AvaluoRepository avaluoRepository,
                                   @Qualifier("repositoryFormatoInformeService") FormatoInformeService formatoInformeService,
                                   @Qualifier("repositoryAvaluoRemateService") AvaluoRemateService avaluoRemateService,
                                   EntidadRepository entidadRepository, UsuarioRepository usuarioRepository,
                                   @Qualifier("repositoryAvaluoConstructorService") AvaluoConstructorService avaluoConstructorService,
                                   EstadoAvaluoRepository estadoAvaluoRepository,
                                   @Qualifier("repositoryEventoService") EventoService eventoService,
                                   @Qualifier("repositoryAvaluoHipotecarioService") AvaluoHipotecarioService avaluoHipotecarioService,
                                   FormatoInformeEnsamblador formatoInformeEnsamblador,
                                   NotificacionesService notificacionesService,
                                   @Qualifier("repositoryAvaluoComercialService") AvaluoComercialService avaluoComercialService,
                                   AvaluoEnsamblador avaluoEnsamblador,
                                   EstadoAvaluoEnsamblador estadoAvaluoEnsamblador,
                                   SegmentoRepository segmentoRepository,
                                   FormatoInformeRepository formatoInformeRepository) {
        this.avaluoRepository = avaluoRepository;
        this.formatoInformeService = formatoInformeService;
        this.avaluoRemateService = avaluoRemateService;
        this.entidadRepository = entidadRepository;
        this.usuarioRepository = usuarioRepository;
        this.avaluoConstructorService = avaluoConstructorService;
        this.estadoAvaluoRepository = estadoAvaluoRepository;
        this.eventoService = eventoService;
        this.avaluoHipotecarioService = avaluoHipotecarioService;
        this.formatoInformeEnsamblador = formatoInformeEnsamblador;
        this.notificacionesService = notificacionesService;
        this.avaluoComercialService = avaluoComercialService;
        this.avaluoEnsamblador = avaluoEnsamblador;
        this.estadoAvaluoEnsamblador = estadoAvaluoEnsamblador;
        this.segmentoRepository = segmentoRepository;
        this.formatoInformeRepository = formatoInformeRepository;

        /*
         * Se inicializan los bloqueos para asegurar que
         * la lógica sea a prueba de hilos.
         */
        asignarPeritoLock = new ReentrantLock();
        aceptarCasoLock = new ReentrantLock();
        rechazarCasoLock = new ReentrantLock();
        programarCitaLock = new ReentrantLock();
        reProgramarCitaLock = new ReentrantLock();
        solicitarDevolucionLock = new ReentrantLock();
        devolverLock = new ReentrantLock();
        reactivarLock = new ReentrantLock();
        confirmarVisitaLock = new ReentrantLock();
        cancelarAvaluoLock = new ReentrantLock();
        confirmarDocumentosLock = new ReentrantLock();
        confirmarPagoLock = new ReentrantLock();
        notificarHonorariosLock = new ReentrantLock();
        enviarLock = new ReentrantLock();
        solicitarCorreccionesLock = new ReentrantLock();
        enviarComiteLock = new ReentrantLock();
        aprobarLock = new ReentrantLock();
        crearInformeLock = new ReentrantLock();
    }

    public AvaluoDTO crear(AvaluoDTO avaluoDTO, UsuarioDTO usuarioDTO) {
        AvaluoDTO avaluoResultDTO = null;

        log.debug("Creando un nuevo avaluo");
        if (avaluoDTO.getTipoAvaluo().getNombre().equals("Hipotecario")) {
            log.debug("El avaluo es de tipo hipotecario");
            AvaluoHipotecarioDTO avaluoHipotecarioDTO = new AvaluoHipotecarioDTO(
                    avaluoDTO);
            avaluoResultDTO = avaluoHipotecarioService.crear(
                    avaluoHipotecarioDTO, usuarioDTO);
        }

        if (avaluoDTO.getTipoAvaluo().getNombre().equals("Comercial Rural")
                || avaluoDTO.getTipoAvaluo().getNombre()
                        .equals("Comercial Urbano")) {
            log.debug("El avaluo es de tipo comercial");
            AvaluoComercialDTO avaluoComercialDTO = new AvaluoComercialDTO(
                    avaluoDTO);
            avaluoResultDTO = avaluoComercialService.crear(avaluoComercialDTO,
                    usuarioDTO);
        }

        if (avaluoDTO.getTipoAvaluo().getNombre().equals("Constructor")) {
            AvaluoConstructorDTO avaluoConstructorDTO = new AvaluoConstructorDTO(
                    avaluoDTO);
            avaluoResultDTO = avaluoConstructorService.crear(
                    avaluoConstructorDTO, usuarioDTO);
        }

        /*if (avaluoDTO.getTipoAvaluo().getNombre().equals("Proyectos")) {
        }*/
        if (avaluoDTO.getTipoAvaluo().getNombre().equals("Remates")) {
            avaluoResultDTO = avaluoRemateService.crear((AvaluoRemateDTO) avaluoDTO, usuarioDTO);
        }
        /*if (avaluoDTO.getTipoAvaluo().getNombre().equals("Actualizaciones")) {
        }*/

        return avaluoResultDTO;
    }

    @Transactional(rollbackFor = AvaluoNotFoundException.class)
    @Override
    public AvaluoDTO eliminar(Long avaluoId) throws AvaluoNotFoundException {
        log.debug("Eliminando el avaluo con id:  {}",
                avaluoId);
        Avaluo avaluo = avaluoRepository.findOne(avaluoId);
        if (avaluo == null) {
            throw new AvaluoNotFoundException();
        }
        avaluoRepository.delete(avaluo);
        return avaluoEnsamblador.escribirDTO(avaluo);
    }

    @Transactional(rollbackFor = AvaluoNotFoundException.class)
    @Override
    public AvaluoDTO cambiarTipoAvaluo(Long avaluoId, String nuevoTipoAvaluo)
        throws AvaluoNotFoundException {
        log.debug("Eliminando el tipo de avaluo y creando el nuevo tipo de avaluo:  {}",
                avaluoId);
        Avaluo avaluo = avaluoRepository.findOne(avaluoId);
        if (avaluo == null) {
            throw new AvaluoNotFoundException();
        }
        /*if (avaluo.getClass().equals(AvaluoHipotecario.class)) {
            avaluoHipotecarioRepository.eliminarTipoAvaluo(avaluoId);
        } else if (avaluo.getClass().equals(AvaluoComercial.class)) {
            avaluoComercialRepository.eliminarTipoAvaluo(avaluoId);
        } else if (avaluo.getClass().equals(AvaluoConstructor.class)) {
            avaluoConstructorRepository.eliminarTipoAvaluo(avaluoId);
        } else if (avaluo.getClass().equals(AvaluoActualizacion.class)) {
            //avaluoActualizacionRepository.eliminarTipoAvaluo(avaluo);
        } else if (avaluo.getClass().equals(AvaluoRemate.class)) {
            avaluoCustomQueryRepository.eliminarTipoAvaluo(avaluoId);
        }
        if (nuevoTipoAvaluo.equals(Constantes.TIPO_AVALUO_HIPOTECARIO)) {
            avaluoCustomQueryRepository.crearTipoAvaluo(avaluoId);
        } else if (nuevoTipoAvaluo.equals(Constantes.TIPO_AVALUO_COMERCIAL_RURAL)
                || nuevoTipoAvaluo.equals(Constantes.TIPO_AVALUO_COMERCIAL_URBANO)) {
            avaluoComercialRepository.crearTipoAvaluo(avaluoId);
        } else if (nuevoTipoAvaluo.equals(Constantes.TIPO_AVALUO_CONSTRUCTOR)) {
            avaluoConstructorRepository.crearTipoAvaluo(avaluoId);
        } else if (nuevoTipoAvaluo.equals(Constantes.TIPO_AVALUO_PROYECTOS)) {
//            avaluoProyectosRepository.crearTipoAvaluo(avaluoId);
        } else if (nuevoTipoAvaluo.equals(Constantes.TIPO_AVALUO_REMATE)) {
            avaluoCustomQueryRepository.crearTipoAvaluo(avaluoId);
        }*/
        return avaluoEnsamblador.escribirDTO(avaluo);
    }

    @Transactional(readOnly = true)
    @Override
    public List<AvaluoDTO> encontrarTodos() {
        log.debug("Encontrando todos los avaluos");
        return avaluoEnsamblador.escribirListaDTO(avaluoRepository.findAll());
    }

    @Transactional(readOnly = true)
    @Override
    public AvaluoDTO encontrarPorId(Long id) {
        log.debug("Encontrando el avaluo con id:  {}",
                id);
        return avaluoEnsamblador.escribirDTO(avaluoRepository.findOne(id));
    }
    
    @Transactional(readOnly = true)
    @Override
    public AvaluoDTO encontrarPorIdTinsa(Long id) {
        log.debug("Encontrando el avaluo con id:  {}",
                id);
        return avaluoEnsamblador.escribirDTO(avaluoRepository.encontrarPorIdTinsa(id));
    }
    
    @Transactional(readOnly = true)
    @Override
    public AvaluoDTO encontrarAvaluoPorCodigoExternoAndNumeroDocumentoCliente( String codigoExterno, Long idEntidad, Long numeroDocumentoCliente ) {
        
        return avaluoEnsamblador.escribirDTO(avaluoRepository.encontrarAvaluoPorCodigoExternoAndNumeroDocumentoCliente(codigoExterno, idEntidad, numeroDocumentoCliente ));
    }

    @Override
    public AvaluoDTO actualizar(AvaluoDTO actualizado)
            throws AvaluoNotFoundException {
        log.debug("Actualizando el avaluo: {}",
                actualizado);
        Avaluo avaluo = avaluoRepository.findOne(actualizado.getId());
        comprobarInforme(avaluo.getFormatoInforme() != null ?
                avaluo.getFormatoInforme().getId() : null, actualizado);
        if (actualizado.getClass().equals(AvaluoHipotecarioDTO.class) ) {
            log.debug("Actualizar el avaluo de tipo hipotecario");
            return avaluoHipotecarioService
                    .actualizar((AvaluoHipotecario) avaluo, (AvaluoHipotecarioDTO) actualizado);
        }
        if (actualizado.getClass().equals( AvaluoComercialDTO.class)) {
            log.debug("Actualizar el avaluo de tipo comercial");
            return avaluoComercialService
                    .actualizar((AvaluoComercial) avaluo, (AvaluoComercialDTO) actualizado);
        }
        if ( actualizado.getClass().equals(AvaluoConstructorDTO.class)  ) {
            log.debug("Actualizar el avaluo de tipo constructor");
            return avaluoConstructorService
                    .actualizar((AvaluoConstructor) avaluo, (AvaluoConstructorDTO) actualizado);
        }
        if (actualizado.getClass().equals(AvaluoRemateDTO.class)) {
            return avaluoRemateService
                    .actualizar((AvaluoRemate) avaluo, (AvaluoRemateDTO) actualizado);
        }
        if (actualizado.getClass().equals( AvaluoActualizacionDTO.class) ) {
            return null;
        }
        return null;
    }

    private void comprobarInforme(Long formatoInformeId, AvaluoDTO actualizado) {
        FormatoInformeDTO formatoInformeDTO = actualizado.getFormatoInforme();
        if (formatoInformeDTO != null && formatoInformeId == null) {
            if (formatoInformeDTO.getId() == null) {
                actualizado.setFormatoInforme(formatoInformeService.crear(formatoInformeDTO));
            }
        }else if (formatoInformeDTO == null){
            if (formatoInformeId != null) {
                // TODO [Gerson] Borrar formato de informe de la base de datos.
                try {
                    formatoInformeService.eliminar(formatoInformeId);
                } catch (FormatoInformeNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    @Transactional(readOnly = true)
    @Override
    public EstadoAvaluoDTO encontrarEstadoActual(Long avaluoId) {
        return estadoAvaluoEnsamblador.escribirDTO(avaluoRepository
                .encontrarEstadoActual(avaluoId));
    }

    @Transactional(readOnly=true)
    @Override
    public List<AvaluoDTO> encontrarAvaluosPorDireccion(String direccion) {
        List<AvaluoDTO> list = null;

        if(direccion!=null && !"".equals(direccion)){
            list = avaluoEnsamblador.escribirListaDTO(avaluoRepository.encontrarAvaluosPorDireccion(direccion));
        }

        return list;
    }

    @Transactional(readOnly=true)
    @Override
    public boolean encontrarPorCodigoExterno(String codigoExterno,
            Long idEntidad) {
        long startTime = System.nanoTime();
        Boolean respuesta = avaluoRepository.encontrarPorCodigoExterno(
                codigoExterno, idEntidad);
        long endTime = System.nanoTime();
        double difference = (endTime - startTime)/1e6;
        log.debug("encontrarPorCodigoExterno:  {}",
                difference);
        return respuesta != null ? respuesta : false;
    }
    
    @Transactional(readOnly=true)
    @Override
    public AvaluoDTO encontrarAvaluoPorCodigoExterno(String codigoExterno,
            Long idEntidad) {
       return avaluoRepository.encontrarAvaluoPorCodigoExterno(
                codigoExterno, idEntidad);

    }
    
    @Transactional(readOnly=true)
    @Override
    public boolean encontrarPorIdSisgen(Long idSisgen) {
        Boolean respuesta = avaluoRepository.encontrarPorIdSisgen(idSisgen);
        return respuesta != null ? respuesta : false;
    }

    @Transactional(readOnly=true)
    @Override
    public FormatoInformeDTO cargarFormatoInforme(Long id) {
        return formatoInformeEnsamblador.escribirDTO(avaluoRepository.cargarFormatoInforme(id));
    }

    @Transactional(readOnly=true)
    @Override
    public AvaluoDTO cargarInformacionPersonaRecibeAvaluador(AvaluoDTO avaluoDTO) {
        List<Object> resultado = avaluoRepository.cargarPersonaRecibeAvaluador(avaluoDTO.getId());
        Object[] resultadoArray = (Object[]) resultado.get(0);
        String nombreRecibe = (String) resultadoArray[0];
        String telefonoRecibe = (String) resultadoArray[1];
        String correoElectronicoRecibe = (String) resultadoArray[2];
        avaluoDTO.setNombreRecibe(nombreRecibe);
        avaluoDTO.setTelefonoRecibe(telefonoRecibe);
        avaluoDTO.setCorreoElectronicoRecibe(correoElectronicoRecibe);
        return avaluoDTO;
    }

    @Transactional(readOnly=true)
    @Override
    public List<AvaluoDTO> encontrarAvaluosAnteriores(
            Integer tipoDocumentoIdentificacion, Long numeroDocumento,
            Long id) {
        if (id == null) {
            id = 0L;
        }
        return avaluoRepository.encontrarAvaluosAnteriores(
                tipoDocumentoIdentificacion, numeroDocumento, id);
    }

    @Transactional
    private EstadoAvaluo crear(EstadoAvaluo estadoAvaluo,
            EstadoAvaluo estadoAvaluoActual) {
        log.info("Creando un nuevo detalle de estadoAvaluo {}",
                estadoAvaluo);
        if (estadoAvaluoActual != null) {
            estadoAvaluoActual.setEstadoActual(false);
        }
        estadoAvaluo = estadoAvaluoRepository.save(estadoAvaluo);
        return estadoAvaluo;
    }

    private EstadoAvaluo obtenerEstadoActual(Avaluo avaluo) {
        return Iterables.getLast(avaluo.getEstadosAvaluo());
    }

	@Transactional(rollbackFor = AvaluoNotFoundException.class)
	private void actualizar(Avaluo avaluo) {
		avaluo.update(avaluo.getId(), avaluo.getEntidad(), avaluo.getCodigoExterno(), avaluo.getTipoDeInmueble(),
				avaluo.getTipoAvaluo(), avaluo.getObjetoDelAvaluo(), avaluo.getCambioGarantia(),
				avaluo.getCambioGarantiaAvaluo(), avaluo.getCompraCartera(), avaluo.getDivipola(), avaluo.getSector(),
				avaluo.getConjunto(), avaluo.getBarrio(), avaluo.getMatriculaInmobiliariaPrincipal1(),
				avaluo.getTipoVia(), avaluo.getNumeroVia(), avaluo.getComplementoVia(), avaluo.getNumeroViaGeneradora(),
				avaluo.getComplementoViaGeneradora(), avaluo.getPlaca(), avaluo.getAdicionalDireccion(),
				avaluo.getDireccionInmueble(), avaluo.getNombreRecibe(), avaluo.getTelefonoRecibe(),
				avaluo.getCorreoElectronicoRecibe(), avaluo.getNombreAsesor(), avaluo.getSucursalAsesor(),
				avaluo.getCelularAsesor(), avaluo.getCorreoElectronicoAsesor(), avaluo.getTelefonoAsesor(),
				avaluo.getObservacionesSolicitante(), avaluo.getLatitud(), avaluo.getLongitud(),
				avaluo.getValorSolicitado(), avaluo.getValorTotalAvaluo(), avaluo.getAreaTotal(), avaluo.getValorUvr(),
				avaluo.getValorAvaluoEnUvr(), avaluo.getCalificacionGarantia(), avaluo.getValorAsegurable(),
				avaluo.getValorLiquidacion(), avaluo.getGastosTranslado(), avaluo.getValorHonorarios(),
				avaluo.getAreas(), avaluo.getFotografias(), avaluo.getMetodosValuacion(), avaluo.getGarajes(),
				avaluo.getEstadosAvaluo(), avaluo.getEstado(), avaluo.getCliente(), avaluo.getPerito(),
				avaluo.getDuracionPausaSemaforo(), avaluo.getIva(), avaluo.getTipoCredito(), avaluo.getFormatoInforme(),
				avaluo.getProposito(), avaluo.getTipo(), avaluo.getLatitudInicial(), avaluo.getLongitudInicial(),
				avaluo.getPropietario(), avaluo.getCedulaCatastral());
	}

    /**
     * Carga el avaluo con el cliente, el perito, el usuario creador
     * cada usuario carga las regionales, entidades y tipos de avaluo
     * y el cliente carga los avaluos asociados.
     * @param avaluoDTO del avaluo que se quiere cargar
     * @return el avaluo
     */
    @Transactional(readOnly=true)
    private Avaluo cargarAvaluo(AvaluoDTO avaluoDTO) {
        Avaluo avaluo = avaluoRepository.findOne(avaluoDTO.getId());
        avaluo.setEstado(obtenerEstadoActual(avaluo));
        return avaluo;
    }

    @Transactional(readOnly=true)
    public List<AvaluoDTO> comprobarCambioGarantia(String codigoExterno,
            Long entidadId) {
        return avaluoRepository
            .comprobarCambioGarantia(codigoExterno, entidadId);
    }

    /**
     * Asigna el perito @link{perito} al avaluo @link{avaluoDTO}
     * @param avaluoDTO, el avaluo al cual se asignara el perito.
     * @param perito, el perito que se va a asignar.
     */
    @Transactional
    @Override
    public boolean asignarPerito(AvaluoDTO avaluoDTO, UsuarioDTO perito,
            UsuarioDTO usuario) {
        asignarPeritoLock.lock();
        try{
            Avaluo avaluo = cargarAvaluo(avaluoDTO);
            EstadoAvaluo estadoAvaluo = avaluo.asignarPerito(
                    usuarioRepository
                            .findOne(new DocumentoIdentificacion(perito
                                    .getTipoDocumentoIdentificacion(), perito
                                    .getNumeroDocumento())), usuarioRepository
                            .findOne(new DocumentoIdentificacion(usuario
                                    .getTipoDocumentoIdentificacion(), usuario
                                    .getNumeroDocumento())));
            /*
              Si no hay estados para este avaluo
              de otra forma se actualiza el estado actual
             */
            if (avaluo.getEstadosAvaluo().isEmpty()) {
                crear(estadoAvaluo, null);
            } else {
                crear(estadoAvaluo, obtenerEstadoActual(avaluo));
            }
            avaluo.setEstado(estadoAvaluo);
            actualizar(avaluo);
            notificacionesService.notificarAsignacionPerito(avaluo,
                    avaluo.getPerito());
            return true;
        }finally {
            asignarPeritoLock.unlock();
        }
    }
    
	/**
	 * Asigna el perito @link{perito} al avaluo @link{avaluoDTO}
	 * 
	 * @param avaluoDTO,
	 *            el avaluo al cual se asignara el perito.
	 * @param perito,
	 *            el perito que se va a asignar.
	 */
	@Transactional
	@Override
	public boolean actualizarPerito(AvaluoDTO avaluoDTO, UsuarioDTO perito, UsuarioDTO usuario) {
		asignarPeritoLock.lock();
		try {
			Avaluo avaluo = cargarAvaluo(avaluoDTO);

			avaluo.actualizarPerito(
					usuarioRepository.findOne(new DocumentoIdentificacion(perito.getTipoDocumentoIdentificacion(),
							perito.getNumeroDocumento())),
					usuarioRepository.findOne(new DocumentoIdentificacion(usuario.getTipoDocumentoIdentificacion(),
							usuario.getNumeroDocumento())));

			actualizar(avaluo);
			notificacionesService.notificarAsignacionPerito(avaluo, avaluo.getPerito());
			return true;
		} finally {
			asignarPeritoLock.unlock();
		}
	}

    @Override
    public boolean aceptarCaso(AvaluoDTO avaluoDTO, UsuarioDTO usuario) {
        aceptarCasoLock.lock();
        try {
            Avaluo avaluo = cargarAvaluo(avaluoDTO);
            try {
                avaluo.aceptarCaso(usuarioRepository
                        .findOne(new DocumentoIdentificacion(usuario
                                .getTipoDocumentoIdentificacion(), usuario
                                .getNumeroDocumento())));
            } catch (EstadoIlegalException e) {
                log.debug("Error al realizar operación sobre el avaluo {}",
                        avaluoDTO, e);
                return false;
            }
            crear(avaluo.getEstado(), obtenerEstadoActual(avaluo));
            avaluoDTO.setEstado(estadoAvaluoEnsamblador.escribirDTO(avaluo
                    .getEstado()));
            return true;
        } finally {
            aceptarCasoLock.unlock();
        }
    }

    @Override
    public boolean rechazarCaso(AvaluoDTO avaluoDTO, String justificacion,
            UsuarioDTO usuario) {
        rechazarCasoLock.lock();
        try {
            Avaluo avaluo = cargarAvaluo(avaluoDTO);
            try {
                avaluo.rechazarCaso(justificacion, usuarioRepository
                        .findOne(new DocumentoIdentificacion(usuario
                                .getTipoDocumentoIdentificacion(), usuario
                                .getNumeroDocumento())));
            } catch (EstadoIlegalException e) {
                log.debug("Error al realizar operación sobre el avaluo {}",
                        avaluoDTO, e);
                return false;
            }
            EstadoAvaluo estadoAvaluo = avaluo.getEstado();
            estadoAvaluo.setPerito(null);

            crear(estadoAvaluo, obtenerEstadoActual(avaluo));
            avaluoDTO.setEstado(estadoAvaluoEnsamblador.escribirDTO(avaluo
                    .getEstado()));
            notificacionesService.notificarRechazoSolicitud(avaluo,
                    avaluo.getPerito());
            avaluo.setPerito(null);
            actualizar(avaluo);
            this.avaluoEnsamblador.escribirDTO(avaluo);
            return true;
        } finally {
            rechazarCasoLock.unlock();
        }
    }

    @Override
    public boolean programarCita(AvaluoDTO avaluoDTO, Date horaInicio,
            Date horaFin, String nombreRecibeVisita, UsuarioDTO usuario) {
        programarCitaLock.lock();
        try {
            Avaluo avaluo = cargarAvaluo(avaluoDTO);
            EventoDTO eventoDTO;
            try {
                eventoDTO = avaluo.programarCita(horaInicio, horaFin,
                        nombreRecibeVisita, usuarioRepository
                        .findOne(new DocumentoIdentificacion(usuario
                                .getTipoDocumentoIdentificacion(), usuario
                                .getNumeroDocumento())));
            } catch (EstadoIlegalException e) {
                log.debug("Error al realizar operación sobre el avaluo {}",
                        avaluoDTO, e);
                return false;
            }
            eventoService.crear(eventoDTO);
            crear(avaluo.getEstado(), obtenerEstadoActual(avaluo));
            //avaluoDTO.setEstado(estadoAvaluoEnsamblador.escribirDTO(avaluo
            //        .getEstado()));
            notificacionesService.notificarCitaProgramada(avaluo,
                    avaluo.getPerito(), horaInicio);
            return true;
        } finally {
            programarCitaLock.unlock();
        }
    }

    @Override
    public boolean reProgramarCita(AvaluoDTO avaluoDTO, String nombreRecibeVisita,
            EventoDTO evento, UsuarioDTO usuario) {
        reProgramarCitaLock.lock();
        try {
            try {
                eventoService.actualizar(evento);
            } catch(EventoNotFoundException e) {
                log.debug("EventoNotFoundException: no se encontro el evento: {}",
                        evento);
                return false;
            }
            notificacionesService
                .notificarReprogramarCitaPerito(avaluoDTO, usuario,
                        evento.getFechaHoraInicio());
            return true;
        } finally {
            reProgramarCitaLock.unlock();
        }
    }

    @Override
    public boolean solicitarDevolucion(AvaluoDTO avaluoDTO, String justificacion,
            UsuarioDTO usuario) {
        solicitarDevolucionLock.lock();
        try {
            Avaluo avaluo = cargarAvaluo(avaluoDTO);
            try {
                avaluo.solicitarDevolucion(justificacion, usuarioRepository
                        .findOne(new DocumentoIdentificacion(usuario
                                .getTipoDocumentoIdentificacion(), usuario
                                .getNumeroDocumento())));
            } catch (EstadoIlegalException e) {
                log.debug("Error al realizar operación sobre el avaluo {}",
                        avaluoDTO, e);
                return false;
            }
            crear(avaluo.getEstado(), obtenerEstadoActual(avaluo));
            notificacionesService
                .notificarSolicitudDevolucion(avaluo,avaluo.getPerito());
            return true;
        } finally {
            solicitarDevolucionLock.unlock();
        }
    }

    @Override
    public boolean devolver(AvaluoDTO avaluoDTO, UsuarioDTO usuarioDto) {
        devolverLock.lock();
        try {
            Avaluo avaluo = cargarAvaluo(avaluoDTO);
            Usuario usuario = usuarioRepository
                .findOne(new DocumentoIdentificacion(usuarioDto
                            .getTipoDocumentoIdentificacion(), usuarioDto
                            .getNumeroDocumento()));
            String justificacion =
                (avaluo.getEstado().getJustificacionRechazo() == null) ? ""
                : avaluo.getEstado().getJustificacionRechazo();
            try {
                avaluo.devolver(justificacion, usuario);
            } catch (EstadoIlegalException e) {
                log.debug("Error al realizar operación sobre el avaluo {}",
                        avaluoDTO, e);
                return false;
            }
            // Pausar semaforo
            avaluoDTO.getSemaforo().pausarSemaforo();
            crear(avaluo.getEstado(), obtenerEstadoActual(avaluo));
            avaluoDTO.setEstado(estadoAvaluoEnsamblador.escribirDTO(avaluo
                        .getEstado()));
            notificacionesService.notificarDevolucion(avaluo, avaluo.getPerito());
            return true;
        } finally {
            devolverLock.unlock();
        }
    }

    @Override
    public boolean reactivar
            (AvaluoDTO avaluoDTO, UsuarioDTO usuario) {
        reactivarLock.lock();
        try {
            Avaluo avaluo = cargarAvaluo(avaluoDTO);
            Usuario usuarioActivador = usuarioRepository
                .findOne(new DocumentoIdentificacion(usuario
                            .getTipoDocumentoIdentificacion(), usuario
                            .getNumeroDocumento()));
            try {
                avaluo.reactivar(usuarioActivador);
            } catch (EstadoIlegalException e) {
                log.debug("Error al realizar operación sobre el avaluo {}",
                        avaluoDTO, e);
                return false;
            }
            if(!avaluoDTO.getEstado().getEstado().equals(Constantes.Estado.PendienteDevolucion)){
                try {
                    avaluoDTO.getSemaforo().actualizarEstado(
                            avaluo.getDuracionPausaSemaforo());
                } catch (IllegalStateException e) {
                    log.error(
                            "El semaforo ya termino no se puede pausar ",
                            e);
                }
            }
            crear(avaluo.getEstado(), obtenerEstadoActual(avaluo));
            actualizar(avaluo);
            avaluoDTO.setDuracionPausaSemaforo(avaluo.getDuracionPausaSemaforo());
            avaluoDTO.setEstado(estadoAvaluoEnsamblador.escribirDTO(avaluo
                        .getEstado()));

            Usuario usuarioNotificado = Iterables.get(avaluo.getEstadosAvaluo(),
                    avaluo.getEstadosAvaluo().size() - 1).getUsuario();

            notificacionesService.notificarReactivacion(avaluo, usuarioNotificado,
                    usuarioActivador);
            return true;
        } finally {
            reactivarLock.unlock();
        }
    }

    @Transactional
    @Override
    public boolean confirmarVisita(AvaluoDTO avaluoDTO,
                                   UsuarioDTO usuario) {
        confirmarVisitaLock.lock();
        try {
            Avaluo avaluo = cargarAvaluo(avaluoDTO);
            try {
                avaluo.confirmarVisita(usuarioRepository
                        .findOne(new DocumentoIdentificacion(usuario
                                .getTipoDocumentoIdentificacion(), usuario
                                .getNumeroDocumento())));
            } catch (EstadoIlegalException e) {
                log.debug("Error al realizar operación sobre el avaluo {}",
                        avaluoDTO, e);
                return false;
            }
            crear(avaluo.getEstado(), obtenerEstadoActual(avaluo));
            return true;
        } finally {
            confirmarVisitaLock.unlock();
        }
    }

    @Transactional
    @Override
    public boolean cancelarAvaluo(AvaluoDTO avaluoDTO,
                                  UsuarioDTO usuario) {
        cancelarAvaluoLock.lock();
        try {
            Avaluo avaluo;
            avaluo = cargarAvaluo(avaluoDTO);
            try {
                avaluo.cancelarAvaluo(usuarioRepository
                        .findOne(new DocumentoIdentificacion(usuario
                                .getTipoDocumentoIdentificacion(), usuario
                                .getNumeroDocumento())));
            } catch (EstadoIlegalException e) {
                log.debug("Error al realizar operación sobre el avaluo {}",
                        avaluoDTO, e);
                return false;
            }
            crear(avaluo.getEstado(), obtenerEstadoActual(avaluo));
            avaluoDTO.setEstado(estadoAvaluoEnsamblador.escribirDTO(avaluo
                    .getEstado()));
            return true;
        } finally {
            cancelarAvaluoLock.unlock();
        }
    }
    
    @Transactional
    @Override
    public boolean enProcesoAvaluo(AvaluoDTO avaluoDTO,
                                  UsuarioDTO usuario) {
        cancelarAvaluoLock.lock();
        try {
            Avaluo avaluo;
            avaluo = cargarAvaluo(avaluoDTO);
            try {
                avaluo.reactivar(usuarioRepository
                        .findOne(new DocumentoIdentificacion(usuario
                                .getTipoDocumentoIdentificacion(), usuario
                                .getNumeroDocumento())));
            } catch (EstadoIlegalException e) {
                log.debug("Error al realizar operación sobre el avaluo {}",
                        avaluoDTO, e);
                return false;
            }
            crear(avaluo.getEstado(), obtenerEstadoActual(avaluo));
            avaluoDTO.setEstado(estadoAvaluoEnsamblador.escribirDTO(avaluo
                    .getEstado()));
            return true;
        } finally {
            cancelarAvaluoLock.unlock();
        }
    }

    @Transactional
    @Override
    public boolean confirmarDocumentosCompletos(AvaluoDTO avaluoDTO, UsuarioDTO usuario) {
        confirmarDocumentosLock.lock();
        try {
            Avaluo avaluo;
            avaluo = cargarAvaluo(avaluoDTO);
            try {
                avaluo.confirmarDocumentosCompletos(usuarioRepository
                        .findOne(new DocumentoIdentificacion(usuario
                                .getTipoDocumentoIdentificacion(), usuario
                                .getNumeroDocumento())));
            } catch (EstadoIlegalException e) {
                log.debug("Error al realizar operación sobre el avaluo {}",
                        avaluoDTO, e);
                return false;
            }
            crear(avaluo.getEstado(), obtenerEstadoActual(avaluo));
            avaluoDTO.setEstado(estadoAvaluoEnsamblador.escribirDTO(avaluo
                  .getEstado()));
            return true;
        } finally {
            confirmarDocumentosLock.unlock();
        }
    }

    @Transactional
    @Override
    public boolean confirmarPago(AvaluoDTO avaluoDTO,
                                 UsuarioDTO usuario) {
        confirmarPagoLock.lock();
        try {
            Avaluo avaluo;
            avaluo = cargarAvaluo(avaluoDTO);
            try {
                avaluo.confirmarPago(usuarioRepository
                        .findOne(new DocumentoIdentificacion(usuario
                                .getTipoDocumentoIdentificacion(), usuario
                                .getNumeroDocumento())));
            } catch (EstadoIlegalException e) {
                log.debug("Error al realizar operación sobre el avaluo {}",
                        avaluoDTO, e);
                return false;
            }
            crear(avaluo.getEstado(), obtenerEstadoActual(avaluo));
            avaluoDTO.setEstado(estadoAvaluoEnsamblador.escribirDTO(avaluo
                .getEstado()));
            return true;
        } finally {
            confirmarPagoLock.unlock();
        }
    }

    @Transactional
    @Override
    public boolean notificarHonorarios(AvaluoDTO avaluoDTO, UsuarioDTO usuario) {
        notificarHonorariosLock.lock();
        try {
            Avaluo avaluo;
            avaluo = cargarAvaluo(avaluoDTO);
            try {
                avaluo.notificarHonorarios(usuarioRepository
                        .findOne(new DocumentoIdentificacion(usuario
                                .getTipoDocumentoIdentificacion(), usuario
                                .getNumeroDocumento())));
            } catch (EstadoIlegalException e) {
                log.debug("Error al realizar operación sobre el avaluo {}",
                        avaluoDTO, e);
                return false;
            }
            crear(avaluo.getEstado(), obtenerEstadoActual(avaluo));
            avaluoDTO.setEstado(estadoAvaluoEnsamblador.escribirDTO(avaluo
                        .getEstado()));
            notificacionesService.notificarHonorarios(avaluo);
            return true;
        } finally {
            notificarHonorariosLock.unlock();
        }
    }

    @Transactional
    @Override
    public boolean enviar(AvaluoDTO avaluoDTO,
                          UsuarioDTO usuario) {
        enviarLock.lock();
        try {
            Avaluo avaluo;
            avaluo = cargarAvaluo(avaluoDTO);
            try {
                avaluo.enviar(usuarioRepository
                        .findOne(new DocumentoIdentificacion(usuario
                                .getTipoDocumentoIdentificacion(), usuario
                                .getNumeroDocumento())));
            } catch (EstadoIlegalException e) {
                log.debug("Error al realizar operación sobre el avaluo {}",
                        avaluoDTO, e);
                return false;
            }
            crear(avaluo.getEstado(), obtenerEstadoActual(avaluo));
            return true;
        } finally {
            enviarLock.unlock();
        }
    }

    @Transactional
    @Override
    public boolean solicitarCorreciones(AvaluoDTO avaluoDTO,
                                        String correciones,
                                        UsuarioDTO usuario) {
        solicitarCorreccionesLock.lock();
        try {
            Avaluo avaluo = cargarAvaluo(avaluoDTO);
            try {
                avaluo.solicitarCorreciones(correciones, usuarioRepository
                        .findOne(new DocumentoIdentificacion(usuario
                                .getTipoDocumentoIdentificacion(), usuario
                                .getNumeroDocumento())));
            } catch (EstadoIlegalException e) {
                log.debug("Error al realizar operación sobre el avaluo {}",
                        avaluoDTO, e);
                return false;
            }
            crear(avaluo.getEstado(), obtenerEstadoActual(avaluo));
            actualizar(avaluo);
            notificacionesService.notificarCorreciones(avaluo, avaluo.getPerito());
            return true;
        } finally {
            solicitarCorreccionesLock.unlock();
        }
    }

    @Transactional
    @Override
    public boolean enviarAComite(AvaluoDTO avaluoDTO,
                                 UsuarioDTO usuario) {
        enviarComiteLock.lock();
        try {
            Avaluo avaluo = cargarAvaluo(avaluoDTO);
            try {
                avaluo.enviarAComite(usuarioRepository
                        .findOne(new DocumentoIdentificacion(usuario
                                .getTipoDocumentoIdentificacion(), usuario
                                .getNumeroDocumento())));
            } catch (EstadoIlegalException e) {
                log.debug("Error al realizar operación sobre el avaluo {}",
                        avaluoDTO, e);
                return false;
            }
            crear(avaluo.getEstado(), obtenerEstadoActual(avaluo));
            return true;
        } finally {
            enviarComiteLock.unlock();
        }
    }

    @Transactional
    @Override
    public boolean aprobar(AvaluoDTO avaluoDTO, UsuarioDTO usuario) {
        aprobarLock.lock();
        try {
            Avaluo avaluo = cargarAvaluo(avaluoDTO);
            avaluo.setValorLiquidacion(avaluoDTO.getValorLiquidacion());
            try {
                avaluo.aprobar(usuarioRepository.findOne(new DocumentoIdentificacion(
                                usuario.getTipoDocumentoIdentificacion(), usuario
                                .getNumeroDocumento())));
            } catch (EstadoIlegalException e) {
                log.debug("Error al realizar operación sobre el avaluo {}",
                        avaluoDTO, e);
                return false;
            }
            actualizar(avaluo);
            crear(avaluo.getEstado(), obtenerEstadoActual(avaluo));
            return true;
        } finally {
            aprobarLock.unlock();
        }
    }

    public void notificarAvaluoAprobado(AvaluoDTO avaluo, UsuarioDTO  usuario, String archivo){
        this.notificacionesService.notifiacarAvaluoAprobado(cargarAvaluo(avaluo), usuario, archivo);
    }

    @Transactional(readOnly=true)
    @Override
    public List<AvaluoConsultaDTO> encontrarAvaluosParaEstudios(
            AvaluoConsultaDTO avaluoConsultaDTO) {
        List<AvaluoConsultaDTO> avaluosEncontrados =
                new ArrayList<>();

        TipoInmuebleDTO tipoInmueble = avaluoConsultaDTO.getTipoInmuebleDTO();

        Integer sector = avaluoConsultaDTO.getSector();

        DivipolaDTO divipola = avaluoConsultaDTO.getDivipolaCiudad();
        BigDecimal latitud1, latitud2;
        if((avaluoConsultaDTO.getLatitud().doubleValue() > 0)
                ||(avaluoConsultaDTO.getLatitud().doubleValue() < 0)){
            latitud1 = avaluoConsultaDTO.getLatitud();
            latitud2 = latitud1;
        }else{
            latitud1 = BigDecimal.valueOf(-85.0);
            latitud2 = BigDecimal.valueOf(85.0);
        }
        BigDecimal longitud1, longitud2;
        if((avaluoConsultaDTO.getLatitud().doubleValue() > 0)||(avaluoConsultaDTO.getLatitud().doubleValue() < 0)){
            longitud1 = avaluoConsultaDTO.getLatitud();
            longitud2 = longitud1;
        }else{
            longitud1 = BigDecimal.valueOf(-180.0);
            longitud2 = BigDecimal.valueOf(180.0);
        }
        Long valorTotalAvaluoMin = Long.parseLong(avaluoConsultaDTO.getValorMin());
        Long valorTotalAvaluoMax = Long.parseLong(avaluoConsultaDTO.getValorMax());
        //--------------SECTOR URBANO-----------------------------------------
        if(avaluoConsultaDTO.getSector() == 1){
            String barrio = "";
            if(!"".equals(avaluoConsultaDTO.getBarrio())){
                barrio = "%";
                barrio += avaluoConsultaDTO.getBarrio().toUpperCase();
                barrio += "%";
            }else{
                barrio = "%%";
            }
            Integer estrato1;
            Integer estrato2;
            if(avaluoConsultaDTO.getEstrato() >=1){
                estrato1 = avaluoConsultaDTO.getEstrato();
                estrato2 = estrato1;
            }else{
                estrato1 = 1;
                estrato2 = 6;
            }
            List<String> tiposVia = new ArrayList<String>();
            if(!"".equals(avaluoConsultaDTO.getTipoVia())){
                tiposVia.add(avaluoConsultaDTO.getTipoVia());
            }else{
                tiposVia.add("CL");
                tiposVia.add("KR");
                tiposVia.add("DG");
                tiposVia.add("TV");
                tiposVia.add("AV");
                tiposVia.add("AC");
                tiposVia.add("AK");
                tiposVia.add("AU");
                tiposVia.add("CT");
                tiposVia.add("CQ");
                tiposVia.add("CN");
            }
            Integer numVia1, numVia2;
            if(avaluoConsultaDTO.getNumeroVia() >= 1){
                numVia1 = avaluoConsultaDTO.getNumeroVia();
                numVia2 = numVia1;
            }else{
                numVia1 = 1;
                numVia2 = 100;
            }
            if(avaluoConsultaDTO.getCuadrasRedonda() >= 1){
                if(numVia1 >= avaluoConsultaDTO.getCuadrasRedonda()){
                    numVia1 = numVia1 + avaluoConsultaDTO.getCuadrasRedonda();
                    numVia2 = numVia1 - avaluoConsultaDTO.getCuadrasRedonda();
                }
            }
            Integer numViaGen1, numViaGen2;
            if(avaluoConsultaDTO.getNumeroViaGeneradora() >= 1){
                numViaGen1 = avaluoConsultaDTO.getNumeroVia();
                numViaGen2 = numViaGen1;
            }else{
                numViaGen1 = 1;
                numViaGen2 = 100;
            }
            if(avaluoConsultaDTO.getCuadrasRedonda() >= 1){
                if(numViaGen1 >= avaluoConsultaDTO.getCuadrasRedonda()){
                    numViaGen1 = numViaGen1 + avaluoConsultaDTO.getCuadrasRedonda();
                    numViaGen2 = numViaGen1 - avaluoConsultaDTO.getCuadrasRedonda();
                }
            }

            String propiedadHorizontal = avaluoConsultaDTO.getPh();
            if(propiedadHorizontal.equals("si")){//---------------------------ES PH---------
                Integer phAreaPrivada1;
                Integer phAreaPrivada2;
                if((avaluoConsultaDTO.getUrbAreaPrivMinPH() > 0)&&(avaluoConsultaDTO.getUrbAreaPrivMaxPH() > 0)){
                    phAreaPrivada1 = avaluoConsultaDTO.getUrbAreaPrivMinPH();
                    phAreaPrivada2 = avaluoConsultaDTO.getUrbAreaPrivMaxPH();
                }else{
                    phAreaPrivada1 = 1;
                    phAreaPrivada2 = 2000;
                }
                Integer phHabitaciones1;
                Integer phHabitaciones2;
                if(avaluoConsultaDTO.getHabitacionesPH() >= 1){
                    phHabitaciones1 = avaluoConsultaDTO.getHabitacionesPH();
                    phHabitaciones2 = phHabitaciones1;
                }else{
                    phHabitaciones1 = 0;
                    phHabitaciones2 = 100;
                }
                Integer phBanos1;
                Integer phBanos2;
                if(avaluoConsultaDTO.getBanosPH() >= 1){
                    phBanos1 = avaluoConsultaDTO.getBanosPH();
                    phBanos2 = phBanos1;
                }else{
                    phBanos1 = 0;
                    phBanos2 = 15;
                }
                Integer phParqPrivado1 = 0;
                Integer phParqPrivado2 = 0;
                Integer phParqPublico1 = 0;
                Integer phParqPublico2 = 0;
                Integer phParqBahia1 = 0;
                Integer phParqBahia2 = 0;
                if(avaluoConsultaDTO.getTipoParqPH().equals("privado")){
                    if(avaluoConsultaDTO.getNumParqPH() >= 1){
                        phParqPrivado1 = avaluoConsultaDTO.getNumParqPH();
                        phParqPrivado2 = phParqPrivado1;
                    }else{
                        phParqPrivado1 = 0;
                        phParqPrivado2 = 100;
                    }
                }else if(avaluoConsultaDTO.getTipoParqPH().equals("publico")){
                    if(avaluoConsultaDTO.getNumParqPH() >= 1){
                        phParqPublico1 = avaluoConsultaDTO.getNumParqPH();
                        phParqPublico2 = phParqPublico1;
                    }else{
                        phParqPublico1 = 0;
                        phParqPublico2 = 100;
                    }
                }else if(avaluoConsultaDTO.getTipoParqPH().equals("bahia")){
                    if(avaluoConsultaDTO.getNumParqPH() >= 1){
                        phParqBahia1 = avaluoConsultaDTO.getNumParqPH();
                        phParqBahia2 = phParqBahia1;
                    }else{
                        phParqBahia1 = 0;
                        phParqBahia2 = 100;
                    }
                }
                Integer phEstadoInmueble1 = 0;
                Integer phEstadoInmueble2 = 0;
                if(avaluoConsultaDTO.getEstadoInmPH().equals("nuevo")){
                    phEstadoInmueble1 = 1;
                    phEstadoInmueble2 = phEstadoInmueble1;
                }else if(avaluoConsultaDTO.getEstadoInmPH().equals("usado")){
                    phEstadoInmueble1 = 2;
                    phEstadoInmueble2 = phEstadoInmueble1;
                }else{
                    phEstadoInmueble1 = 1;
                    phEstadoInmueble2 = 2;
                }
                Integer phEdadInmueble1 = 0;
                Integer phEdadInmueble2 = 0;
                if(avaluoConsultaDTO.getEdadInmueblePH() != null){
                if(avaluoConsultaDTO.getEdadInmueblePH().equals("0-5")){
                    phEdadInmueble1 = 0;
                    phEdadInmueble2 = 5;
                }else if(avaluoConsultaDTO.getEdadInmueblePH().equals("5-10")){
                    phEdadInmueble1 = 5;
                    phEdadInmueble2 = 10;
                }else if(avaluoConsultaDTO.getEdadInmueblePH().equals("10-20")){
                    phEdadInmueble1 = 10;
                    phEdadInmueble2 = 20;
                }else if(avaluoConsultaDTO.getEdadInmueblePH().equals("20-100")){
                    phEdadInmueble1 = 20;
                    phEdadInmueble2 = 100;
                }else{
                    phEdadInmueble1 = 0;
                    phEdadInmueble2 = 100;
                }
                }else{
                    phEdadInmueble1 = 0;
                    phEdadInmueble2 = 100;
                }
                avaluosEncontrados = avaluoEnsamblador.encontrarAvaluosParaEstudioUrbanoPH(avaluoRepository.encontrarAvaluosUrbanoPH(tipoInmueble.getId(),
                        divipola.getId(),sector,barrio,estrato1,estrato2,tiposVia,numVia1,numVia2,numViaGen1,numViaGen2,latitud1,latitud2,
                        longitud1,longitud2,phAreaPrivada1,phAreaPrivada2,phHabitaciones1,phHabitaciones2,phBanos1,phBanos2,phParqPrivado1,phParqPrivado2,
                        phParqPublico1,phParqPublico2,phParqBahia1,phParqBahia2,phEstadoInmueble1,phEstadoInmueble2,
                        phEdadInmueble1,phEdadInmueble2,valorTotalAvaluoMin,valorTotalAvaluoMax));
            }
            else if(propiedadHorizontal.equals("no")){//---------------------ES NPH--------
                Integer nphAreaTotal1;
                Integer nphAreaTotal2;
                if((avaluoConsultaDTO.getUrbAreaTotalMinNPH() > 0)&&(avaluoConsultaDTO.getUrbAreaTotalMaxNPH() > 0)){
                    nphAreaTotal1 = avaluoConsultaDTO.getUrbAreaTotalMinNPH();
                    nphAreaTotal2 = avaluoConsultaDTO.getUrbAreaTotalMaxNPH();
                }else{
                    nphAreaTotal1 = 1;
                    nphAreaTotal2 = 2000;
                }
                Integer nphAreaConst1;
                Integer nphAreaConst2;
                if((avaluoConsultaDTO.getUrbAreaConstMinNPH() > 0)&&(avaluoConsultaDTO.getUrbAreaConstMaxNPH() > 0)){
                    nphAreaConst1 = avaluoConsultaDTO.getUrbAreaConstMinNPH();
                    nphAreaConst2 = avaluoConsultaDTO.getUrbAreaConstMaxNPH();
                }else{
                    nphAreaConst1 = 1;
                    nphAreaConst2 = 2000;
                }
                Integer nphHabitaciones1;
                Integer nphHabitaciones2;
                if(avaluoConsultaDTO.getHabitacionesNPH() >= 1){
                    nphHabitaciones1 = avaluoConsultaDTO.getHabitacionesNPH();
                    nphHabitaciones2 = nphHabitaciones1;
                }else{
                    nphHabitaciones1 = 0;
                    nphHabitaciones2 = 100;
                }
                Integer nphBanos1;
                Integer nphBanos2;
                if(avaluoConsultaDTO.getBanosNPH() >= 1){
                    nphBanos1 = avaluoConsultaDTO.getBanosNPH();
                    nphBanos2 = nphBanos1;
                }else{
                    nphBanos1 = 0;
                    nphBanos2 = 15;
                }
                Integer nphParqPrivado1 = 0;
                Integer nphParqPrivado2 = 0;
                Integer nphParqPublico1 = 0;
                Integer nphParqPublico2 = 0;
                Integer nphParqBahia1 = 0;
                Integer nphParqBahia2 = 0;
                if(avaluoConsultaDTO.getTipoParqNPH().equals("privado")){
                    if(avaluoConsultaDTO.getNumParqNPH() >= 1){
                        nphParqPrivado1 = avaluoConsultaDTO.getNumParqNPH();
                        nphParqPrivado2 = nphParqPrivado1;
                    }else{
                        nphParqPrivado1 = 0;
                        nphParqPrivado2 = 100;
                    }
                }else if(avaluoConsultaDTO.getTipoParqNPH().equals("publico")){
                    if(avaluoConsultaDTO.getNumParqNPH() >= 1){
                        nphParqPublico1 = avaluoConsultaDTO.getNumParqNPH();
                        nphParqPublico2 = nphParqPublico1;
                    }else{
                        nphParqPublico1 = 0;
                        nphParqPublico2 = 100;
                    }
                }else if(avaluoConsultaDTO.getTipoParqNPH().equals("bahia")){
                    if(avaluoConsultaDTO.getNumParqNPH() >= 1){
                        nphParqBahia1 = avaluoConsultaDTO.getNumParqNPH();
                        nphParqBahia2 = nphParqBahia1;
                    }else{
                        nphParqBahia1 = 0;
                        nphParqBahia2 = 100;
                    }
                }
                Integer nphEstadoInmueble1 = 0;
                Integer nphEstadoInmueble2 = 0;
                if(avaluoConsultaDTO.getEstadoInmNPH().equals("nuevo")){
                    nphEstadoInmueble1 = 1;
                    nphEstadoInmueble2 = nphEstadoInmueble1;
                }else if(avaluoConsultaDTO.getEstadoInmNPH().equals("usado")){
                    nphEstadoInmueble1 = 2;
                    nphEstadoInmueble2 = nphEstadoInmueble1;
                }else{
                    nphEstadoInmueble1 = 1;
                    nphEstadoInmueble1 = 2;
                }
                Integer nphEdadInmueble1 = 0;
                Integer nphEdadInmueble2 = 0;
                if(avaluoConsultaDTO.getEdadInmuebleNPH() != null){
                if(avaluoConsultaDTO.getEdadInmuebleNPH().equals("0-5")){
                    nphEdadInmueble1 = 0;
                    nphEdadInmueble2 = 5;
                }else if(avaluoConsultaDTO.getEdadInmuebleNPH().equals("5-10")){
                    nphEdadInmueble1 = 5;
                    nphEdadInmueble2 = 10;
                }else if(avaluoConsultaDTO.getEdadInmuebleNPH().equals("10-20")){
                    nphEdadInmueble1 = 10;
                    nphEdadInmueble2 = 20;
                }else if(avaluoConsultaDTO.getEdadInmuebleNPH().equals("20-100")){
                    nphEdadInmueble1 = 20;
                    nphEdadInmueble2 = 100;
                }else{
                    nphEdadInmueble1 = 0;
                    nphEdadInmueble2 = 100;
                }
                }
                avaluosEncontrados = avaluoEnsamblador.encontrarAvaluosParaEstudioUrbanoPH(avaluoRepository.encontrarAvaluosUrbanoNPH(tipoInmueble.getId(),
                        divipola.getId(),sector,barrio,estrato1,estrato2,tiposVia,numVia1,numVia2,numViaGen1,numViaGen2,latitud1,latitud2,
                        longitud1,longitud2,nphAreaTotal1,nphAreaTotal2,nphAreaConst1,nphAreaConst2,nphHabitaciones1,nphHabitaciones2,nphBanos1,nphBanos2,nphParqPrivado1,nphParqPrivado2,
                        nphParqPublico1,nphParqPublico2,nphParqBahia1,nphParqBahia2,nphEstadoInmueble1,nphEstadoInmueble2,
                        nphEdadInmueble1,nphEdadInmueble2,valorTotalAvaluoMin,valorTotalAvaluoMax));

            }
        }
        //-----------------------SECTOR RURAL-----------------------------------------------------------
        if(avaluoConsultaDTO.getSector() == 2){
            Integer rurAreaTotal1;
            Integer rurAreaTotal2;
            if((avaluoConsultaDTO.getRurAreaTotalMin() > 0)&&(avaluoConsultaDTO.getRurAreaTotalMax() > 0)){
                rurAreaTotal1 = avaluoConsultaDTO.getRurAreaTotalMin();
                rurAreaTotal2 = avaluoConsultaDTO.getRurAreaTotalMax();
            }else{
                rurAreaTotal1 = 1;
                rurAreaTotal2 = 2000;
            }
            Integer rurAreaConst1;
            Integer rurAreaConst2;
            if((avaluoConsultaDTO.getRurAreaConstMin() > 0)&&(avaluoConsultaDTO.getRurAreaConstMax() > 0)){
                rurAreaConst1 = avaluoConsultaDTO.getRurAreaConstMin();
                rurAreaConst2 = avaluoConsultaDTO.getRurAreaConstMax();
            }else{
                rurAreaConst1 = 1;
                rurAreaConst2 = 2000;
            }
            Integer rurAreaInfr1;
            Integer rurAreaInfr2;
            if((avaluoConsultaDTO.getRurAreaInfrMin() > 0)&&(avaluoConsultaDTO.getRurAreaInfrMax() > 0)){
                rurAreaInfr1 = avaluoConsultaDTO.getRurAreaInfrMin();
                rurAreaInfr2 = avaluoConsultaDTO.getRurAreaInfrMax();
            }else{
                rurAreaInfr1 = 1;
                rurAreaInfr2 = 2000;
            }
            Integer rurAreaCult1;
            Integer rurAreaCult2;
            if((avaluoConsultaDTO.getRurAreaCultMin() > 0)&&(avaluoConsultaDTO.getRurAreaCultMax() > 0)){
                rurAreaCult1 = avaluoConsultaDTO.getRurAreaCultMin();
                rurAreaCult2 = avaluoConsultaDTO.getRurAreaCultMax();
            }else{
                rurAreaCult1 = 1;
                rurAreaCult2 = 2000;
            }
            Integer distanciaCascoUrbano1 = 0;
            Integer distanciaCascoUrbano2 = 0;
            if(avaluoConsultaDTO.getRurDistCascoUrb() == null){
                distanciaCascoUrbano1 = 0;
                distanciaCascoUrbano2 = 999;
            }else{
                if(avaluoConsultaDTO.getRurDistCascoUrb() >= 0){
                    distanciaCascoUrbano1 = avaluoConsultaDTO.getRurDistCascoUrb();
                    distanciaCascoUrbano2 = distanciaCascoUrbano1;
                }
            }
            Integer condicionesAgronomicas1;
            Integer condicionesAgronomicas2;
            if(avaluoConsultaDTO.getRurCondCultivos() > 0){
                condicionesAgronomicas1 = avaluoConsultaDTO.getRurCondCultivos();
                condicionesAgronomicas2 = condicionesAgronomicas1;
            }else{
                condicionesAgronomicas1 = 1;
                condicionesAgronomicas2 = 3;
            }
            Integer condicionesAgrologicas1;
            Integer condicionesAgrologicas2;
            if(avaluoConsultaDTO.getRurCondAgrolog() > 0){
                condicionesAgrologicas1 = avaluoConsultaDTO.getRurCondCultivos();
                condicionesAgrologicas2 = condicionesAgrologicas1;
            }else{
                condicionesAgrologicas1 = 1;
                condicionesAgrologicas2 = 3;
            }
            avaluosEncontrados = avaluoEnsamblador.encontrarAvaluosParaEstudioRural(avaluoRepository.encontrarAvaluosRural(tipoInmueble.getId(),
                    divipola.getId(),sector,latitud1,latitud2,longitud1,longitud2,rurAreaTotal1,rurAreaTotal2,rurAreaConst1,rurAreaConst2,
                    rurAreaInfr1,rurAreaInfr2,rurAreaCult1,rurAreaCult2,distanciaCascoUrbano1,distanciaCascoUrbano2,
                    condicionesAgronomicas1,condicionesAgronomicas2,condicionesAgrologicas1,condicionesAgrologicas2,valorTotalAvaluoMin,valorTotalAvaluoMax));
        }

        return avaluosEncontrados;
    }

    @Transactional(readOnly=true)
    @Override
    public boolean esCobradoPorBancol(AvaluoDTO avaluoDTO) {
        if(Constantes.TIPO_AVALUO_REMATE.equals(avaluoDTO.getTipoAvaluo().getNombre())){
            return true;
        }
        Boolean esCompraCartera = avaluoRepository.cargarCompraCartera(avaluoDTO.getId());
        Boolean segmentoCobradoAlBanco = null;
        if (avaluoDTO.getCliente().getSegmento() != null) {
            segmentoCobradoAlBanco = segmentoRepository
                    .cargarSegmentoCobradoAlBanco(avaluoDTO.getCliente()
                            .getSegmento().getId());
        }
        Boolean esCobradoPorBancol = entidadRepository
                .cargarCobraAvaluo(avaluoDTO.getEntidad().getId());
        return ((esCompraCartera != null && esCompraCartera)
                && "Bancolombia".equals(avaluoDTO.getEntidad().getNombre()) && (segmentoCobradoAlBanco != null && segmentoCobradoAlBanco))
                || (esCobradoPorBancol != null && esCobradoPorBancol);
    }

    @Transactional(readOnly=true)
    @Override
    public boolean avaluoEsPH(Long id) {
        Boolean resultado = formatoInformeRepository.esSometidoPropiedadHorizontal(id);
        return resultado == null ? false : resultado;
    }

    @Transactional
    @Override
    public boolean crearFormatoInforme(AvaluoDTO avaluo) {
        crearInformeLock.lock();
        try {
            comprobarInforme(formatoInformeService
                    .encontrarIdFormatoPorIdAvaluo(avaluo.getId()), avaluo);
            return true;
        } finally {
            crearInformeLock.unlock();
        }
    }

    @Transactional(rollbackFor = AvaluoNotFoundException.class)
    @Override
    public void actualizarCambioGarantia(AvaluoDTO avaluoCambioGarantia) {
        Avaluo avaluo = avaluoRepository.findOne(avaluoCambioGarantia.getId());
        avaluo.setCambioGarantia(avaluoCambioGarantia.getCambioGarantia());
    }

    @Transactional(readOnly=true)
    @Override
    public boolean tieneMetodologiaFito(Long id) {
        Boolean resultado = avaluoRepository.tieneMetodologiaFito(id);
        return resultado == null ? false : resultado;
    }

    @Transactional(readOnly=true)
    @Override
    public boolean tieneMetodologiaComparacion(Long id) {
        Boolean resultado = avaluoRepository.tieneMetodologiaComparacion(id);
        return resultado ==  null ? false : resultado;
    }

    @Transactional(readOnly=true)
    @Override
    public List<Object> obtenerHonorariosPerito(Long id) {
        return this.avaluoRepository.obtenerHonarariosPerito(id);
    }

    @Transactional(readOnly=true)
    @Override
    public RegionalDTO buscarRegionalAvaluo(Long id) {
        return this.avaluoRepository.buscarRegionalAvaluo(id);
    }

    @Transactional(readOnly=true)
    @Override
    public String encontrarPrefijo(Long id) {
        return this.avaluoRepository.encontrarPrefijo(id);
    }

    @Transactional(readOnly=true)
    @Override
    public UsuarioDTO buscarAsesor(Long id) {
        return this.avaluoRepository.encontrarAsesor(id);
    }

    @Transactional(readOnly=true)
    @Override
    public UsuarioDTO buscarPerito(Long id) {
        return this.avaluoRepository.encontrarPerito(id);
    }

    @Override
    public ClienteDTO buscarCliente(Long id) {
        return this.avaluoRepository.encontrarCliente(id);
    }

    @Transactional(readOnly=true)
    @Override
    public UsuarioDTO buscarPersonaRecibe(Long id) {
        return this.avaluoRepository.encontrarPersonaRecibe(id);
    }

    @Transactional(readOnly=true)
    @Override
    public UsuarioDTO buscarAbogado(Long id) {
        return this.avaluoRepository.encontrarAbogado(id);
    }

    @Transactional(readOnly=true)
    @Override
    public int encontrarCasosAbiertos(
            Integer tipoDocumentoIdentificacionUsuario,
            Long numeroDocumentoUsuario) {
        return avaluoRepository.encontrarCasosAbiertos(
                tipoDocumentoIdentificacionUsuario, numeroDocumentoUsuario);
    }

    @Transactional(readOnly=true)
    @Override
    public int encontrarCasosAbiertosSemana(
            Integer tipoDocumentoIdentificacionUsuario,
            Long numeroDocumentoUsuario) {
        return avaluoRepository.encontrarCasosAbiertosSemana(
                tipoDocumentoIdentificacionUsuario, numeroDocumentoUsuario);
    }

    @Transactional(readOnly=true)
    @Override
    public int encontrarCasosAbiertosMes(
            Integer tipoDocumentoIdentificacionUsuario,
            Long numeroDocumentoUsuario) {
        return avaluoRepository.encontrarCasosAbiertosMes(
                tipoDocumentoIdentificacionUsuario, numeroDocumentoUsuario);
    }

    @Transactional(readOnly=true)
    @Override
    public String cargarCorreoAsesor(Long id) {
        return avaluoRepository.cargarCorreoAsesor(id);
    }
    @Override
    public List<AvaluoDTO> encontrarAvaluosAprobadosDeCliente(int tipoDocumento,
            Long numeroDocumento) {
        return avaluoRepository.encontrarAvaluosAprobadosDeCliente(tipoDocumento,numeroDocumento);
    }

}
