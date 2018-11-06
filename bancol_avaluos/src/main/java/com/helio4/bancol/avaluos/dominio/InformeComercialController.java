package com.helio4.bancol.avaluos.dominio;

import java.util.Set;

import org.primefaces.model.map.LatLng;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Sets;
import com.helio4.bancol.avaluos.dto.AreaConstruccionDTO;
import com.helio4.bancol.avaluos.dto.AreaDTO;
import com.helio4.bancol.avaluos.dto.AvaluoComercialDTO;
import com.helio4.bancol.avaluos.dto.AvaluoDTO;
import com.helio4.bancol.avaluos.dto.ClienteDTO;
import com.helio4.bancol.avaluos.dto.ExplotacionEconomicaDTO;
import com.helio4.bancol.avaluos.dto.FormatoInformeComercialDTO;
import com.helio4.bancol.avaluos.dto.InmuebleDTO;
import com.helio4.bancol.avaluos.dto.MetodoValuacionDTO;
import com.helio4.bancol.avaluos.dto.PropietarioDTO;
import com.helio4.bancol.avaluos.dto.ServidumbreDTO;
import com.helio4.bancol.avaluos.dto.ViaAccesoDTO;
import com.helio4.bancol.avaluos.ensamblador.AvaluoEnsamblador;
import com.helio4.bancol.avaluos.servicio.datos.AreaConstruccionService;
import com.helio4.bancol.avaluos.servicio.excepciones.AreaNotFoundException;
import com.helio4.bancol.avaluos.servicio.datos.AreaService;
import com.helio4.bancol.avaluos.servicio.datos.AvaluoComercialService;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;
import com.helio4.bancol.avaluos.servicio.datos.AvaluoService;
import com.helio4.bancol.avaluos.servicio.excepciones.ClienteNotFoundException;
import com.helio4.bancol.avaluos.servicio.datos.ClienteService;
import com.helio4.bancol.avaluos.servicio.excepciones.EntidadNotFoundException;
import com.helio4.bancol.avaluos.servicio.datos.EntidadService;
import com.helio4.bancol.avaluos.servicio.datos.EstadoAvaluoService;
import com.helio4.bancol.avaluos.servicio.excepciones.ExplotacionEconomicaNotFoundException;
import com.helio4.bancol.avaluos.servicio.datos.ExplotacionEconomicaService;
import com.helio4.bancol.avaluos.servicio.excepciones.InmuebleNotFoundException;
import com.helio4.bancol.avaluos.servicio.datos.InmuebleService;
import com.helio4.bancol.avaluos.servicio.excepciones.MetodoValuacionNotFoundException;
import com.helio4.bancol.avaluos.servicio.datos.MetodoValuacionService;
import com.helio4.bancol.avaluos.servicio.excepciones.PropietarioNotFoundException;
import com.helio4.bancol.avaluos.servicio.datos.PropietarioService;
import com.helio4.bancol.avaluos.servicio.excepciones.ServidumbreNotFoundException;
import com.helio4.bancol.avaluos.servicio.datos.ServidumbreService;
import com.helio4.bancol.avaluos.servicio.datos.SucursalService;
import com.helio4.bancol.avaluos.servicio.datos.UvrService;
import com.helio4.bancol.avaluos.servicio.excepciones.ViaAccesoNotFoundException;
import com.helio4.bancol.avaluos.servicio.datos.ViaAccesoService;

@Component
public class InformeComercialController {
	
	private static Logger log = LoggerFactory.getLogger( InformeComercialController.class );
	
	@Autowired
	@Qualifier("repositoryAvaluoComercialService")
	private AvaluoComercialService avaluoComercialService;
	
	@Autowired
	@Qualifier("repositoryClienteService")
	private ClienteService clienteService;
	
	@Autowired
	@Qualifier("repositoryEstadoAvaluoService")
	private EstadoAvaluoService estadoAvaluoService;
	
	@Autowired
	@Qualifier("repositoryEntidadService")
	private EntidadService entidadService;
	
	@Autowired
	@Qualifier("repositorySucursalService")
	private SucursalService sucursalService;

	@Autowired
	@Qualifier("repositoryAvaluoService")
	private AvaluoService avaluoService;
	
	@Autowired
	@Qualifier("repositoryInmuebleService")
	private InmuebleService inmuebleService;
	
	@Autowired
	@Qualifier("repositoryPropietarioService")
	private PropietarioService propietarioService;
	
	@Autowired
	@Qualifier("repositoryViaAccesoService")
	private ViaAccesoService viaAccesoService;
	
	@Autowired
	@Qualifier("repositoryServidumbreService")
	private ServidumbreService servidumbreService;
	
	@Autowired
	@Qualifier("repositoryExplotacionEconomicaService")
	private ExplotacionEconomicaService explotacionEconomicaService;
	
	@Autowired
	@Qualifier("repositoryAreaConstruccionService")
	private AreaConstruccionService areaConstruccionService;
	
	@Autowired
	@Qualifier("repositoryMetodoValuacionService")
	private MetodoValuacionService metodoValuacionService;
	
	@Autowired
	@Qualifier("repositoryAreaService")
	private AreaService areaService;
	
	@Autowired
	@Qualifier("repositoryUvrService")
	private UvrService uvrService;
	
	@Autowired
	private AvaluoController avaluoController;
	
	@Autowired
	private AvaluoEnsamblador avaluoEnsamblador;

	public AvaluoComercialDTO recuperarAvaluo(AvaluoDTO avaluo) {
		return avaluoComercialService.encontrarAvaluoConAreas(avaluo.getId());
	}

	public LatLng obtenerCoordenadasInmueble(
			AvaluoComercialDTO avaluoComercialDTO) {
		return (avaluoComercialDTO.getLatitud() == null || avaluoComercialDTO.getLongitud() == null) ? new LatLng(4.651042,-74.089114) : new LatLng(avaluoComercialDTO.getLatitud().doubleValue(), avaluoComercialDTO.getLongitud().doubleValue());
	}
	
	@Transactional(rollbackFor=AvaluoNotFoundException.class, noRollbackFor=AreaNotFoundException.class)
	public AvaluoComercialDTO guardarSinEnviar(AvaluoComercialDTO avaluoComercialDTO,
			Set<InmuebleDTO> lotesOriginales, Set<ViaAccesoDTO> viasAccesoOriginales,
			Set<ServidumbreDTO> servidumbresOriginales,
			Set<ExplotacionEconomicaDTO> explotacionesEconomicasOriginales,
			Set<AreaDTO> areasOriginales,
			Set<AreaConstruccionDTO> areasConstruccionOriginales,
			Set<MetodoValuacionDTO> metodosValuacionOriginales) throws AvaluoNotFoundException {
		AvaluoComercialDTO avaluoComercial = null;
		FormatoInformeComercialDTO informeComercialDTO = (FormatoInformeComercialDTO) avaluoComercialDTO.getFormatoInforme();
		if (avaluoComercialDTO.getAreas() != null) {
			persistirCambiosAreas(areasOriginales, avaluoComercialDTO.getAreas());
		}
		if (informeComercialDTO.getInmuebles() != null) {
			perisitirCambiosLotes(lotesOriginales,
					informeComercialDTO.getInmuebles());
		}
		if (informeComercialDTO.getViasAcceso() != null) {
			actualizarViasAcceso(viasAccesoOriginales,
					informeComercialDTO.getViasAcceso());
		}
		if (informeComercialDTO.getServidumbrez() != null) {
			actualizarServidumbres(servidumbresOriginales,
					informeComercialDTO.getServidumbrez());
		}
		if (informeComercialDTO.getExplotacionesEconomicas() != null) {
			actualizarExplotacionesEconomicas(explotacionesEconomicasOriginales,
					informeComercialDTO.getExplotacionesEconomicas());
		}
		if (informeComercialDTO.getMetodosValuacion() != null) {
			actualizarMetodosValuacion(metodosValuacionOriginales,
					informeComercialDTO.getMetodosValuacion());
		}
		ClienteDTO cliente = null;
		try {
			cliente = clienteService.actualizar(avaluoComercialDTO.getCliente());
		} catch (ClienteNotFoundException e) {
            log.error("No se encontró el cliente a actualizar {}",
                    avaluoComercialDTO.getCliente());
			try {
				cliente = clienteService.crear(avaluoComercialDTO.getCliente());
			} catch (EntidadNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (EntidadNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		avaluoComercial = avaluoComercialService.actualizar(avaluoComercialDTO);
		avaluoComercial.setCliente(cliente);
		return avaluoComercial;
	}

	private void persistirCambiosAreas(Set<AreaDTO> areasActuales,
			Set<AreaDTO> areas) {
		if (areasActuales != null && !areasActuales.isEmpty()) {
			Set<AreaDTO> lotesBorrar = Sets.difference(areasActuales, areas);
			if (lotesBorrar != null && !lotesBorrar.isEmpty()) {
				for (AreaDTO area:areasActuales) {
					try {
						areaService.eliminar(area.getId());
					} catch (AreaNotFoundException e) {
						log.error("AreaNotFoundException: No se encontró el area a eliminar",
                                area);
					}
				}
			}
		}
		for (AreaDTO area:areas) {
			if (area.getId() == null) {
				areaService.crear(area);
			}else {
				try {
					areaService.actualizar(area);
				} catch (AreaNotFoundException e) {
					areaService.crear(area);
				}
			}
		}
	}

	private void perisitirCambiosLotes(Set<InmuebleDTO> lotesActuales, Set<InmuebleDTO> lotes) {
		if (lotesActuales != null && !lotesActuales.isEmpty()) {
			Set<InmuebleDTO> lotesBorrar = Sets.difference(lotesActuales, lotes);
			if (lotesBorrar != null && !lotesBorrar.isEmpty()) {
				for (InmuebleDTO lote:lotesActuales) {
					try {
						eliminarPropietarios(lote);
						inmuebleService.eliminar(lote.getId());
					} catch (InmuebleNotFoundException e) {
						log.error("No se encontró el inmueble a eliminar",
                                lote);
					}
				}
			}
		}
		for (InmuebleDTO lote:lotes) {
			if (lote.getId() == null) {
				crearPropietarios(lote);
				inmuebleService.crear(lote);
			}else {
				persistirCambiosPropietarios(lote);
				try {
					inmuebleService.actualizar(lote);
				} catch (InmuebleNotFoundException e) {
					inmuebleService.crear(lote);
				}
			}
		}
	}

	private void crearPropietarios(InmuebleDTO lote) {
		if (lote.getPropietarios() != null) {
			for (PropietarioDTO propietario : lote.getPropietarios()) {
				if (propietarioService.encontrarPorId(
						propietario.getTipoDocumentoIdentificacion(),
						propietario.getNumeroDocumento()) != null) {
					lote.getPropietarios().remove(propietario);
					lote.getPropietarios().add(
							propietarioService.encontrarPorId(propietario
									.getTipoDocumentoIdentificacion(),
									propietario.getNumeroDocumento()));
				} else {
					propietarioService.crear(propietario);
				}
			}
		}
	}
	
	private void persistirCambiosPropietarios(InmuebleDTO lote) {
		Set<PropietarioDTO> propietariosActuales = lote.getPropietariosOriginales();
		if (lote.getPropietarios() != null && propietariosActuales != null && !propietariosActuales.isEmpty()) {
			Set<PropietarioDTO> propietariosABorrar = Sets.difference(
					propietariosActuales, lote.getPropietarios());
			if (!propietariosABorrar.isEmpty()) {
				for (PropietarioDTO propietario : propietariosABorrar) {
					try {
						propietarioService.eliminar(
								propietario.getTipoDocumentoIdentificacion(),
								propietario.getNumeroDocumento());
					} catch (PropietarioNotFoundException e) {
						log.error("No se encontró el propietario a borrar {}",
								propietario);
					}
				}
			}
		}
		for (PropietarioDTO propietario:lote.getPropietarios()) {
			if (propietarioService.encontrarPorId(propietario.getTipoDocumentoIdentificacion(), propietario.getNumeroDocumento()) != null) {
				try {
					propietarioService.actualizar(propietario);
				} catch (PropietarioNotFoundException e) {
					propietarioService.crear(propietario);
				}
			}else{
				propietarioService.crear(propietario);
			}
		}
	}

	private void eliminarPropietarios(InmuebleDTO lote) {
		for (PropietarioDTO propietario:lote.getPropietarios()) {
			try {
				propietarioService.eliminar(propietario.getTipoDocumentoIdentificacion(), propietario.getNumeroDocumento());
			} catch (PropietarioNotFoundException e) {
				log.error("No se encontró el propietario a eliminar");
			}
		}
	}
	
	private void actualizarViasAcceso(Set<ViaAccesoDTO> viasAccesoActuales,
			Set<ViaAccesoDTO> viasAcceso) {
		if (viasAccesoActuales != null && !viasAccesoActuales.isEmpty()) {
			Set<ViaAccesoDTO> viasAccesoBorradas = Sets.difference(
					viasAccesoActuales, viasAcceso);
			if (viasAccesoBorradas != null && !viasAccesoBorradas.isEmpty()) {
				for (ViaAccesoDTO viaAcceso : viasAccesoBorradas) {
					try {
						viaAccesoService.eliminar(viaAcceso.getId());
					} catch (ViaAccesoNotFoundException e) {
                        log.error("No se encontró la via de acceso {}",
                                viaAcceso);
					}
				}
			}
		}
		for (ViaAccesoDTO viaAcceso:viasAcceso) {
			if (viaAcceso.getId() == null) {
				viaAccesoService.crear(viaAcceso);
			}else{
				try {
					viaAccesoService.actualizar(viaAcceso);
				} catch (ViaAccesoNotFoundException e) {
                    log.error("No se encontró la via de acceso a actualizar {}",
                            viaAcceso);
					viaAccesoService.crear(viaAcceso);
				}
			}
		}
	}

	private void actualizarServidumbres(
			Set<ServidumbreDTO> servidumbresActuales, Set<ServidumbreDTO> servidumbres) {
		if (servidumbresActuales != null && !servidumbresActuales.isEmpty()) {
			Set<ServidumbreDTO> servidumbresBorradas = Sets.difference(
					servidumbresActuales, servidumbres);
			if (servidumbresBorradas != null && !servidumbresBorradas.isEmpty()) {
				for (ServidumbreDTO servidumbre : servidumbresBorradas) {
					try {
						servidumbreService.eliminar(servidumbre.getId());
					} catch (ServidumbreNotFoundException e) {
                        log.error("No se encontró la servidumbre {}",
                                servidumbre);
					}
				}
			}
		}
		for (ServidumbreDTO servidumbre:servidumbres) {
			if (servidumbre.getId() == null) {
				servidumbreService.crear(servidumbre);
			}else{
				try {
					servidumbreService.actualizar(servidumbre);
				} catch (ServidumbreNotFoundException e) {
                    log.error("No se encontró la servidumbre a actualizar {}",
                            servidumbre);
					servidumbreService.crear(servidumbre);
				}
			}
		}
	}

	private void actualizarExplotacionesEconomicas(
			Set<ExplotacionEconomicaDTO> explotacionesEconomicasActuales,
			Set<ExplotacionEconomicaDTO> explotacionesEconomicas) {
		if (explotacionesEconomicasActuales != null && !explotacionesEconomicasActuales.isEmpty()) {
			Set<ExplotacionEconomicaDTO> explotacionesEconomicasBorradas = Sets.difference(
					explotacionesEconomicasActuales, explotacionesEconomicas);
			if (explotacionesEconomicasBorradas != null && !explotacionesEconomicasBorradas.isEmpty()) {
				for (ExplotacionEconomicaDTO explotacionEconomica : explotacionesEconomicasBorradas) {
					try {
						explotacionEconomicaService.eliminar(explotacionEconomica.getId());
					} catch (ExplotacionEconomicaNotFoundException e) {
                        log.error("No se encontró la explotación económica {}",
                                explotacionEconomica);
					}
				}
			}
		}
		for (ExplotacionEconomicaDTO explotacionEconomica:explotacionesEconomicas) {
			if (explotacionEconomica.getId() == null) {
				explotacionEconomicaService.crear(explotacionEconomica);
			}else{
				try {
					explotacionEconomicaService.actualizar(explotacionEconomica);
				} catch (ExplotacionEconomicaNotFoundException e) {
                    log.error("No se encontró la explotación económica a actualizar {}",
                            explotacionEconomica);
					explotacionEconomicaService.crear(explotacionEconomica);
				}
			}
		}
	}

	private void actualizarMetodosValuacion(
			Set<MetodoValuacionDTO> metodosValuacionActuales,
			Set<MetodoValuacionDTO> metodosValuacion) {
		if (metodosValuacionActuales != null && !metodosValuacionActuales.isEmpty()) {
			Set<MetodoValuacionDTO> metodosValuacionBorrados = Sets.difference(
					metodosValuacionActuales, metodosValuacion);
			if (metodosValuacionBorrados != null && !metodosValuacionBorrados.isEmpty()) {
				for (MetodoValuacionDTO metodoDeValuacion : metodosValuacionBorrados) {
					try {
						metodoValuacionService.eliminar(metodoDeValuacion);
					} catch (MetodoValuacionNotFoundException e) {
                        log.error("No se encontró el método de valuación {}",
                                metodoDeValuacion);
					}
				}
			}
		}
		for (MetodoValuacionDTO metodoDeValuacion:metodosValuacion) {
			if (metodoDeValuacion.getId() == null) {
				metodoValuacionService.crear(metodoDeValuacion);
			}else{
				try {
					metodoValuacionService.actualizar(metodoDeValuacion);
				} catch (MetodoValuacionNotFoundException e) {
                    log.error("No se encontró el método de valuación a actualizar {}",
                            metodoDeValuacion);
					metodoValuacionService.crear(metodoDeValuacion);
				}
			}
		}
	}

	public AvaluoDTO enviarAvaluo(AvaluoComercialDTO avaluoComercialDTO,
			Set<InmuebleDTO> lotesBorrados, Set<ViaAccesoDTO> viasAccesoBorradas,
			Set<ServidumbreDTO> servidumbresBorradas,
			Set<ExplotacionEconomicaDTO> explotacionesEconomicasBorradas,
			Set<AreaDTO> areasBorradas,
			Set<AreaConstruccionDTO> areasConstruccionBorradas,
			Set<MetodoValuacionDTO> metodosValuacionBorrados) {
		// TODO Auto-generated method stub
		return null;
	}

}
