package com.helio4.bancol.avaluos.ensamblador;

import com.google.common.collect.Iterables;
import com.helio4.bancol.avaluos.dto.*;
import com.helio4.bancol.avaluos.dto.AvaluoDTO.ListaCalificacionGarantia;
import com.helio4.bancol.avaluos.dto.AvaluoDTO.ListaSector;
import com.helio4.bancol.avaluos.exception.GarajeNotFoundException;
import com.helio4.bancol.avaluos.modelo.*;
import com.helio4.bancol.avaluos.modelo.Usuario.DocumentoIdentificacion;
import com.helio4.bancol.avaluos.persistencia.*;
import com.helio4.bancol.avaluos.servicio.excepciones.*;
import com.helio4.bancol.avaluos.servicio.util.Constantes.Estado;
import com.helio4.bancol.avaluos.servicio.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class AvaluoEnsamblador {

	private static Logger log = LoggerFactory.getLogger(AvaluoEnsamblador.class);

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private EntidadRepository entidadRepository;

	@Autowired
	private ListaRepository listaRepository;

	@Autowired
	private DivipolaEnsamblador divipolaEnsamblador;

	@Autowired
	private AreaEnsamblador areaEnsamblador;

	@Autowired
	private FotografiaEnsamblador fotografiaEnsamblador;

	@Autowired
	private ClienteEnsamblador clienteEnsamblador;

	@Autowired
	private UsuarioEnsamblador usuarioEnsamblador;

	@Autowired
	private EntidadEnsamblador entidadEnsamblador;

	@Autowired
	private MetodoValuacionEnsamblador metodoValuacionEnsamblador;

	@Autowired
	private CronogramaObraRepository cronogramaObraRepository;

	@Autowired
	private AumentoPresupuestoEnsamblador aumentoPresupuestoEnsamblador;

	@Autowired
	private CronogramaObraEnsamblador cronogramaObraEnsamblador;

	@Autowired
	private ProrrogaEnsamblador prorrogaEnsamblador;

	@Autowired
	private AreaConstruccionAvaluoEnsamblador areaConstruccionAvaluoEnsamblador;

	@Autowired
	private AreaLoteAvaluoEnsamblador areaLoteAvaluoEnsamblador;

	@Autowired
	private TipoAvaluoRepository tipoAvaluoRepository;

	@Autowired
	private DivipolaRepository divipolaRepository;

	@Autowired
	private SucursalRepository sucursalRepository;

	@Autowired
	private SegmentoEnsamblador segmentoEnsamblador;

	@Autowired
	private AsesorRepository asesorRepository;

	@Autowired
	private EstadoAvaluoEnsamblador estadoAvaluoEnsamblador;

	@Autowired
	private FormatoInformeEnsamblador formatoInformeEnsamblador;

	@Autowired
	private GarajeRepository garajeRepository;

	@Autowired
	private MatriculaRepository matriculaRepository;
	@Autowired
	private SubsidioRepository subsidioRepository;
	@Autowired
	private SalarioMinimoRepository salarioMinimoRepository;
	private SalarioMinimo salarioMinimo;

	@PostConstruct
	public void initialize() {
		salarioMinimo = salarioMinimoRepository.findFirstByOrderByFechaVigenciaDesc();
	}

	/**
	 * Crea un nuevo {@link AvaluoHipotecario} a partir del
	 * {@link AvaluoHipotecarioDTO} El {@link Cliente}, la {@link Entidad}, y el
	 * {@link Usuario} perito deben existir en la base de datos de otra forma se
	 * lanzara la exepcion correspondiente.
	 * 
	 * @param avaluoHipotecarioDTO
	 * @throws ClienteNotFoundException
	 * @throws EntidadNotFoundException
	 * @throws UsuarioNotFoundException
	 * @throws FotografiaNotFoundException
	 * @throws AreaNotFoundException
	 * @throws TipoAvaluoNotFoundException
	 * @throws MetodoValuacionNotFoundException
	 * @throws GarajeNotFoundException
	 */
	public AvaluoHipotecario crearAvaluoHipotecario(AvaluoHipotecarioDTO avaluoHipotecarioDTO)
			throws ClienteNotFoundException, EntidadNotFoundException, UsuarioNotFoundException,
			TipoAvaluoNotFoundException {
		AvaluoHipotecario avaluoHipotecario = new AvaluoHipotecario();
		crearAvaluo(avaluoHipotecario, avaluoHipotecarioDTO);
		return avaluoHipotecario;
	}

	public AvaluoRemate crearAvaluoRemate(AvaluoRemateDTO avaluoRemateDTO) throws EntidadNotFoundException,
			ClienteNotFoundException, UsuarioNotFoundException, FotografiaNotFoundException, AreaNotFoundException,
			TipoAvaluoNotFoundException, MetodoValuacionNotFoundException, GarajeNotFoundException {
		AvaluoRemate avaluoRemates = new AvaluoRemate();
		crearAvaluo(avaluoRemates, avaluoRemateDTO);
		avaluoRemates.setNombreSecuestre(avaluoRemateDTO.getNombreSecuestre());
		avaluoRemates.setTelefonoSecuestre(avaluoRemateDTO.getTelefonoSecuestre());
		avaluoRemates.setCelularSecuestre(avaluoRemateDTO.getCelularSecuestre());
		avaluoRemates.setEmailSecuestre(avaluoRemateDTO.getEmailSecuestre());
		return avaluoRemates;
	}

	private void crearAvaluo(Avaluo avaluo, AvaluoDTO avaluoDTO) throws EntidadNotFoundException,
			ClienteNotFoundException, UsuarioNotFoundException, TipoAvaluoNotFoundException {
		avaluo.setSoloAvaluo(avaluoDTO.isSoloAvaluo());
		// La entidad nunca puede ser nula si es nula es un error.
		Entidad entidad = entidadRepository.findOne(avaluoDTO.getEntidad().getId());
		if (entidad == null) {
			throw new EntidadNotFoundException();
		}
		avaluo.setEntidad(entidad);
		Cliente cliente = clienteRepository.findOne(new DocumentoIdentificacion(
				avaluoDTO.getCliente().getTipoDocumentoIdentificacion(), avaluoDTO.getCliente().getNumeroDocumento()));
		if (cliente == null) {
			throw new ClienteNotFoundException();
		} else {
			try {
				cliente = this.clienteEnsamblador.actualizarCliente(cliente.getTipoDocumentoIdentificacion(),
						cliente.getNumeroDocumento(), avaluoDTO.getCliente());
			} catch (DivipolaNotFoundException e) {
				System.err.println("No se encontro el divipola del cliente a actualizar ");
			} catch (SegmentoNotFoundException e) {
				System.err.println("El cliente a actualizar NO tiene Segmento.");
			}
		}
		avaluo.setCliente(cliente);
		if (avaluoDTO.getPerito() != null) {
			Usuario perito = usuarioRepository
					.findOne(new DocumentoIdentificacion(avaluoDTO.getPerito().getTipoDocumentoIdentificacion(),
							avaluoDTO.getPerito().getNumeroDocumento()));
			if (perito == null) {
				throw new UsuarioNotFoundException();
			}
			avaluo.setPerito(perito);
		} else {
			avaluo.setPerito(null);
		}
		avaluo.setCodigoExterno(avaluoDTO.getCodigoExterno());
		TipoInmueble tipoInmueble = crearTipoInmueble(avaluoDTO.getTipoDeInmueble());
		avaluo.setTipoDeInmueble(tipoInmueble);
		// este método se utiliza para crear y tambíen para actualizar es necesario que
		// el tipo de avaluo este creado ya en este punto.
		TipoAvaluo tipoAvaluo = tipoAvaluoRepository.findOne(avaluoDTO.getTipoAvaluo().getId());
		if (tipoAvaluo == null) {
			throw new TipoAvaluoNotFoundException();
		}
		avaluo.setTipoAvaluo(tipoAvaluo);
		avaluo.setObjetoDelAvaluo(avaluoDTO.getObjetoDelAvaluo().getId().intValue());

		Divipola divipola;
		if (avaluoDTO.getDivipola() != null) {
			divipola = divipolaRepository.findOne(avaluoDTO.getDivipola().getId());
			avaluo.setDivipola(divipola);
		}
		if (avaluoDTO.getSector() != null) {
			avaluo.setSector(avaluoDTO.getSector().getKey());
		}
		avaluo.setConjunto(avaluoDTO.getConjunto());
		avaluo.setMatriculaInmobiliariaPrincipal1(avaluoDTO.getMatriculaInmobiliariaPrincipal1());
		avaluo.setMatriculaInmobiliariaPrincipal2(avaluoDTO.getMatriculaInmobiliariaPrincipal2());
		avaluo.setBarrio(avaluoDTO.getBarrio());
		avaluo.setTipoVia(avaluoDTO.getTipoVia());
		avaluo.setNumeroVia(avaluoDTO.getNumeroVia());
		avaluo.setComplementoVia(avaluoDTO.getComplementoVia());
		avaluo.setNumeroViaGeneradora(avaluoDTO.getNumeroViaGeneradora());
		avaluo.setComplementoViaGeneradora(avaluoDTO.getComplementoViaGeneradora());
		avaluo.setPlaca(avaluoDTO.getPlaca());
		avaluo.setAdicionalDireccion(avaluoDTO.getAdicionalDireccion());
		avaluo.setDireccionInmueble(avaluoDTO.getDireccionInmueble());

		avaluo.setTipoViaInforme(avaluoDTO.getTipoViaInforme());
		avaluo.setNumeroViaInforme(avaluoDTO.getNumeroViaInforme());
		avaluo.setComplementoViaInforme(avaluoDTO.getComplementoViaInforme());
		avaluo.setNumeroViaGeneradoraInforme(avaluoDTO.getNumeroViaGeneradoraInforme());
		avaluo.setComplementoViaGeneradoraInforme(avaluoDTO.getComplementoViaGeneradoraInforme());
		avaluo.setPlacaInforme(avaluoDTO.getPlacaInforme());
		avaluo.setAdicionalDireccionInforme(avaluoDTO.getAdicionalDireccionInforme());
		avaluo.setDireccionInmuebleInforme(avaluoDTO.getDireccionInmuebleInforme());
		if (avaluoDTO.getDivipolaInforme() != null) {
			Divipola divipolaInforme = divipolaRepository.findOne(avaluoDTO.getDivipolaInforme().getId());
			avaluo.setDivipolaInforme(divipolaInforme);
		}

		avaluo.setNombreRecibe(avaluoDTO.getNombreRecibe());
		avaluo.setTelefonoRecibe(avaluoDTO.getTelefonoRecibe());
		avaluo.setCorreoElectronicoRecibe(avaluoDTO.getCorreoElectronicoRecibe());
		avaluo.setNombreAsesor(avaluoDTO.getNombreAsesor());
		avaluo.setSucursalAsesor(avaluoDTO.getSucursalAsesor());
		avaluo.setCelularAsesor(avaluoDTO.getCelularAsesor());
		avaluo.setCorreoElectronicoAsesor(avaluoDTO.getCorreoElectronicoAsesor());
		avaluo.setTelefonoAsesor(avaluoDTO.getTelefonoAsesor());
		avaluo.setTipoDeInmueble(crearTipoInmueble(avaluoDTO.getTipoDeInmueble()));
		avaluo.setObservacionesSolicitante(avaluoDTO.getObservacionesSolicitante());
		avaluo.setLatitud(avaluoDTO.getLatitud());
		avaluo.setLongitud(avaluoDTO.getLongitud());
		avaluo.setValorSolicitado(avaluoDTO.getValorSolicitado());
		avaluo.setCompraCartera(avaluoDTO.getMotivo() != null && avaluoDTO.getMotivo() == 4);
		avaluo.setCambioGarantia(avaluoDTO.getCambioGarantia());
		avaluo.setCambioGarantiaAvaluo(avaluoDTO.getCambioGarantiaAvaluo());
		avaluo.setValorTotalAvaluo(avaluoDTO.getValorTotalAvaluo());
		avaluo.setAreaTotal(avaluoDTO.getAreaTotal());
		avaluo.setValorUvr(avaluoDTO.getValorUvr());
		avaluo.setIva(avaluoDTO.getIva() != null ? avaluoDTO.getIva() : BigDecimal.ZERO);
		avaluo.setValorAvaluoEnUvr(avaluoDTO.getValorAvaluoEnUvr());
		if (avaluoDTO.getCalificacionGarantia() != null) {
			avaluo.setCalificacionGarantia(avaluoDTO.getCalificacionGarantia().getKey());
		}
		avaluo.setValorAsegurable(avaluoDTO.getValorAsegurable());
		avaluo.setValorLiquidacion(avaluoDTO.getValorLiquidacion());
		/*
		 * if (avaluoDTO.getAreas() != null) { agregarAreas(avaluo,
		 * avaluoDTO.getAreas()); } if (avaluoDTO.getFotografias() != null) {
		 * agregarFotografias(avaluo, avaluoDTO.getFotografias()); } if
		 * (avaluoDTO.getMetodosValuacion() != null &&
		 * !avaluoDTO.getMetodosValuacion().isEmpty()) {
		 * agregarMetodosValuacion(avaluoDTO.getMetodosValuacion(), avaluo); } if
		 * (avaluoDTO.getGarajes() !=null && !avaluoDTO.getGarajes().isEmpty()) {
		 * avaluo.setGarajes(
		 * this.garajeEnsamblador.obtenerGarajes(avaluoDTO.getGarajes()) ); }
		 */
		avaluo.setValorHonorarios(avaluoDTO.getValorHonorarios());
		avaluo.setGastosTranslado(avaluoDTO.getGastosTranslado());
		avaluo.setVereda(avaluoDTO.getVereda());
		avaluo.setNombrePredio(avaluoDTO.getNombrePredio());
		avaluo.setTipoCredito(avaluoDTO.getTipoCredito());
		avaluo.setProposito(avaluoDTO.getProposito());
		avaluo.setTipo(avaluoDTO.getTipo());
		avaluo.setLatitudInicial(avaluoDTO.getLatitudInicial());
		avaluo.setLongitudInicial(avaluoDTO.getLongitudInicial());
		avaluo.setPropietario(avaluoDTO.getPropietario());
		avaluo.setCedulaCatastral(avaluoDTO.getCedulaCatastral());
		avaluo.setMotivoId(avaluoDTO.getMotivo().longValue());
		avaluo.setAvaluoSisgenId(avaluoDTO.getAvaluoSisgenId());

		if (avaluoDTO.getTipoSubsidio() != null) {
			avaluo.setSubsidio(subsidioRepository.findOne(avaluoDTO.getTipoSubsidio().getId()));
		}
	}

	/*
	 * private void agregarFotografias(Avaluo avaluo, Set<FotografiaDTO>
	 * fotografiasDTO) throws FotografiaNotFoundException { Set<Fotografia>
	 * fotografias = new HashSet<Fotografia>(); for (FotografiaDTO
	 * fotografiaDTO:fotografiasDTO) { Fotografia fotografia =
	 * fotografiaRepository.findOne(fotografiaDTO.getId()); if (fotografia == null)
	 * { throw new FotografiaNotFoundException(); } fotografias.add(fotografia); }
	 * avaluo.setFotografias(fotografias); }
	 * 
	 * private void agregarAreas(Avaluo avaluo, Set<AreaDTO> areaDTOs) throws
	 * AreaNotFoundException { Set<Area> areas = new HashSet<Area>(); for (AreaDTO
	 * areaDTO:areaDTOs) { Area area = areaRepository.findOne(areaDTO.getId()); if
	 * (area == null) { throw new AreaNotFoundException(); } areas.add(area); }
	 * avaluo.setAreas(areas); }
	 * 
	 * void agregarMetodosValuacion( List<MetodoValuacionDTO> metodosValuacionDTO,
	 * Avaluo avaluo) throws MetodoValuacionNotFoundException {
	 * List<MetodoValuacion> metodoValuacions = new ArrayList<MetodoValuacion>();
	 * for (MetodoValuacionDTO metodoValuacionDTO:metodosValuacionDTO) {
	 * MetodoValuacion metodoValuacion =
	 * metodoValuacionRepository.findOne(metodoValuacionDTO.getId()); if
	 * (metodoValuacion == null) { throw new MetodoValuacionNotFoundException(); }
	 * metodoValuacions.add(metodoValuacion); }
	 * avaluo.setMetodosValuacion(metodoValuacions); }
	 */

	private void escribirDTO(AvaluoDTO avaluoDTO, Avaluo avaluo) {
		if (avaluo != null) {
			avaluoDTO.setId(avaluo.getId());
			avaluoDTO.setSoloAvaluo(avaluo.getSoloAvaluo());
			avaluoDTO.setEntidad(entidadEnsamblador.escribirDTO(avaluo.getEntidad()));
			;

			avaluoDTO.setCodigoExterno(avaluo.getCodigoExterno());
			avaluoDTO.setTipoDeInmueble(escribirDTO(avaluo.getTipoDeInmueble()));
			avaluoDTO.setTipoAvaluo(escribirDTO(avaluo.getTipoAvaluo()));
			avaluoDTO.setObjetoDelAvaluo(
					listaRepository.encontrarObjetoAvaluoPorId(new Long(avaluo.getObjetoDelAvaluo())));
			if (avaluo.getDivipola() != null) {
				avaluoDTO.setDivipola(divipolaEnsamblador.escribirDTO(avaluo.getDivipola()));
			}
			if (avaluo.getSector() != null) {
				avaluoDTO.setSector(ListaSector.fromKey(avaluo.getSector()));
			}
			avaluoDTO.setConjunto(avaluo.getConjunto());
			avaluoDTO.setMatriculaInmobiliariaPrincipal1(avaluo.getMatriculaInmobiliariaPrincipal1());
			avaluoDTO.setMatriculaInmobiliariaPrincipal2(avaluo.getMatriculaInmobiliariaPrincipal2());

			avaluoDTO.setBarrio(avaluo.getBarrio());
			avaluoDTO.setTipoVia(avaluo.getTipoVia());
			avaluoDTO.setNumeroVia(avaluo.getNumeroVia());
			avaluoDTO.setComplementoVia(avaluo.getComplementoVia());
			avaluoDTO.setNumeroViaGeneradora(avaluo.getNumeroViaGeneradora());
			avaluoDTO.setComplementoViaGeneradora(avaluo.getComplementoViaGeneradora());
			avaluoDTO.setPlaca(avaluo.getPlaca());
			avaluoDTO.setAdicionalDireccion(avaluo.getAdicionalDireccion());
			avaluoDTO.setDireccionInmueble(avaluo.getDireccionInmueble());

			avaluoDTO.setTipoViaInforme(avaluo.getTipoViaInforme());
			avaluoDTO.setNumeroViaInforme(avaluo.getNumeroViaInforme());
			avaluoDTO.setComplementoViaInforme(avaluo.getComplementoViaInforme());
			avaluoDTO.setNumeroViaGeneradoraInforme(avaluo.getNumeroViaGeneradoraInforme());
			avaluoDTO.setComplementoViaGeneradoraInforme(avaluo.getComplementoViaGeneradoraInforme());
			avaluoDTO.setPlacaInforme(avaluo.getPlacaInforme());
			avaluoDTO.setAdicionalDireccionInforme(avaluo.getAdicionalDireccionInforme());
			avaluoDTO.setDireccionInmuebleInforme(avaluo.getDireccionInmuebleInforme());
			if (avaluo.getDivipolaInforme() != null) {
				avaluoDTO.setDivipolaInforme(divipolaEnsamblador.escribirDTO(avaluo.getDivipolaInforme()));
			}
			avaluoDTO.setTipoAvaluo(escribirDTO(avaluo.getTipoAvaluo()));
			avaluoDTO.setNombreRecibe(avaluo.getNombreRecibe());
			avaluoDTO.setTelefonoRecibe(avaluo.getTelefonoRecibe());
			avaluoDTO.setCorreoElectronicoRecibe(avaluo.getCorreoElectronicoRecibe());
			avaluoDTO.setNombreAsesor(avaluo.getNombreAsesor());
			avaluoDTO.setSucursalAsesor(avaluo.getSucursalAsesor());
			avaluoDTO.setCelularAsesor(avaluo.getCelularAsesor());
			avaluoDTO.setCorreoElectronicoAsesor(avaluo.getCorreoElectronicoAsesor());
			avaluoDTO.setTelefonoAsesor(avaluo.getTelefonoAsesor());
			avaluoDTO.setTipoDeInmueble(escribirDTO(avaluo.getTipoDeInmueble()));
			avaluoDTO.setObservacionesSolicitante(avaluo.getObservacionesSolicitante());
			avaluoDTO.setLatitud(avaluo.getLatitud());
			avaluoDTO.setLongitud(avaluo.getLongitud());
			avaluoDTO.setValorSolicitado(avaluo.getValorSolicitado());
			avaluoDTO.setValorTotalAvaluo(avaluo.getValorTotalAvaluo());

			if (avaluo.getMotivoId() != null) {
				avaluoDTO.setMotivo(avaluo.getMotivoId().intValue());
			} else {

				if (avaluo.getCompraCartera() != null && avaluo.getCompraCartera()) {
					avaluoDTO.setMotivo(4);
				} else if ((avaluo.getCambioGarantia() != null && avaluo.getCambioGarantia() > 0)
						|| (avaluo.getCambioGarantiaAvaluo() != null)) {
					avaluoDTO.setMotivo(3);
				} else {
					avaluoDTO.setMotivo(0);
				}

				avaluoDTO
						.setDescripcionMotivo(avaluoDTO.getMotivo() != null
								? (avaluoDTO.getMotivo() == 0 ? "Garantía"
										: avaluoDTO.getMotivo() == 3 ? "Cambio garantía"
												: avaluoDTO.getMotivo() == 4 ? "Compra de Cartera" : "Garantía")
								: "Garantía");
			}

			avaluoDTO.setCambioGarantia(avaluo.getCambioGarantia());
			avaluoDTO.setCambioGarantiaAvaluo(avaluo.getCambioGarantiaAvaluo());
			avaluoDTO.setAreaTotal(avaluo.getAreaTotal());
			avaluoDTO.setValorUvr(avaluo.getValorUvr());
			avaluoDTO.setValorAvaluoEnUvr(avaluo.getValorAvaluoEnUvr());
			if (avaluo.getCalificacionGarantia() != null) {
				avaluoDTO.setCalificacionGarantia(ListaCalificacionGarantia.fromKey(avaluo.getCalificacionGarantia()));
			}
			avaluoDTO.setValorAsegurable(avaluo.getValorAsegurable());
			avaluoDTO.setValorLiquidacion(avaluo.getValorLiquidacion());
			avaluoDTO.setIva(avaluo.getIva() != null ? avaluo.getIva() : BigDecimal.ZERO);
			if (avaluo.getAreas() != null && !avaluo.getAreas().isEmpty()) {
				escribirAreas(avaluo.getAreas(), avaluoDTO);
			}
			if (avaluo.getFotografias() != null && avaluo.getFotografias().isEmpty()) {
				escribirFotografias(avaluo.getFotografias(), avaluoDTO);
			}
			if (avaluo.getMetodosValuacion() != null && !avaluo.getMetodosValuacion().isEmpty()) {
				avaluoDTO
						.setMetodosValuacion(metodoValuacionEnsamblador.escribirListaDTO(avaluo.getMetodosValuacion()));
			}

			List<GarajeDTO> garajes = this.garajeRepository.encontrarGarajes(avaluo.getId());
			avaluoDTO.setGarajes(garajes);

			EstadoAvaluo estadoActual = obtenerEstadoActual(avaluo);
			avaluo.setEstado(estadoActual);
			EstadoAvaluo estadoFinal = estadoActual.getClass().equals(EstadoAprobado.class) ? estadoActual : null;
			EstadoAvaluoDTO estadoActualDTO = estadoAvaluoEnsamblador.escribirDTO(estadoActual);
			avaluoDTO.setEstado(estadoActualDTO);
			EstadoAvaluo estadoInicial = avaluo.getEstadosAvaluo().iterator().next();
			avaluoDTO.setFechaEstado(estadoActual.getFechaEstado());
			if (estadoActual.getEstado() >= 8 && estadoActual.getEstado() <= 11) {
				log.debug("Estado actual {} avaluo {}", avaluo.getId(), estadoActual.getEstado());
				EstadoAvaluo estadoEnviado = obtenerEstadoEnviado(avaluo);
				avaluoDTO.setFechaEnviado(estadoEnviado.getFechaEstado());
			}
			avaluoDTO.setCliente(clienteEnsamblador.escribirDTO(avaluo.getCliente()));
			avaluoDTO.setPerito(usuarioEnsamblador.escribirDTO(avaluo.getPerito()));
			avaluoDTO.setDuracionPausaSemaforo(avaluo.getDuracionPausaSemaforo());
			avaluoDTO.setValorHonorarios(avaluo.getValorHonorarios());
			avaluoDTO.setGastosTranslado(avaluo.getGastosTranslado());
			avaluoDTO.setVereda(avaluo.getVereda());
			avaluoDTO.setNombrePredio(avaluo.getNombrePredio());
			avaluoDTO.setCreador(usuarioEnsamblador.escribirDTO(avaluo.getCreador()));
			log.debug("Usuario creador {}", avaluoDTO.getCreador());
			avaluoDTO.setFechaCreacion(avaluo.getFechaCreacion());
			avaluoDTO.setTipoCredito(avaluo.getTipoCredito());
			FormatoInforme formatoInforme = avaluo.getFormatoInforme();
			if (formatoInforme != null) {
				avaluoDTO.setFormatoInforme(formatoInformeEnsamblador.escribirDTO(formatoInforme));
			}
			SemaforoDTO semaforoDTO;
			if (estadoFinal == null) {
				semaforoDTO = new SemaforoDTO(estadoInicial.getFechaEstado(), avaluo.getDuracionPausaSemaforo(),
						avaluo.getEntidad().getDuracionSemaforoVerde(),
						avaluo.getEntidad().getDuracionSemaforoAmarillo(),
						avaluo.getEntidad().getDuracionSemaforoRojo());
			} else {
				semaforoDTO = new SemaforoDTO(estadoInicial.getFechaEstado(), estadoFinal.getFechaEstado(),
						avaluo.getDuracionPausaSemaforo(), avaluo.getEntidad().getDuracionSemaforoVerde(),
						avaluo.getEntidad().getDuracionSemaforoAmarillo(),
						avaluo.getEntidad().getDuracionSemaforoRojo());
			}
			if (avaluoDTO.getEstado().getEstado().equals(Estado.Devuelto)) {
				semaforoDTO.pausarSemaforo();
			}
			avaluoDTO.setSemaforo(semaforoDTO);
			avaluoDTO.setProposito(avaluo.getProposito());
			avaluoDTO.setTipo(avaluo.getTipo());
			avaluoDTO.setLatitudInicial(avaluo.getLatitudInicial());
			avaluoDTO.setLongitudInicial(avaluo.getLongitudInicial());
			avaluoDTO.setPropietario(avaluo.getPropietario());
			avaluoDTO.setCedulaCatastral(avaluo.getCedulaCatastral());
			avaluoDTO.setAvaluoSisgenId(avaluo.getAvaluoSisgenId());

			// carga de matriculas
			List<MatriculaDTO> matriculasAvaluo = this.matriculaRepository.encontrarPorAvaluoId(avaluo.getId());
			List<MatriculaDTO> matriculasList = new ArrayList<>();

			for (int i = 0; i < 4; i++) {

				if (matriculasAvaluo != null && matriculasAvaluo.size() > i) {
					matriculasList.add(matriculasAvaluo.get(i));
				} else {
					matriculasList.add(new MatriculaDTO(avaluo.getId()));
				}

			}

			avaluoDTO.setMatriculas(matriculasList);

			// carga de areas lote y construccion

			if (avaluo.getAreaConstruccionAvaluo() != null) {
				avaluoDTO.setAreaConstruccionAvaluoDTO(
						areaConstruccionAvaluoEnsamblador.escribirDTO(avaluo.getAreaConstruccionAvaluo()));
			} else {
				avaluoDTO.setAreaConstruccionAvaluoDTO(new AreaConstruccionAvaluoDTO(avaluoDTO.getId()));
			}

			if (avaluo.getAreaLoteAvaluo() != null) {
				avaluoDTO.setAreaLoteAvaluoDTO(areaLoteAvaluoEnsamblador.escribirDTO(avaluo.getAreaLoteAvaluo()));
			} else {
				avaluoDTO.setAreaLoteAvaluoDTO(new AreaLoteAvaluoDTO(avaluoDTO.getId()));
			}

			if (avaluo.getSubsidio() != null) {
				final SubsidioDTO subsidio = new SubsidioDTO();
				subsidio.setId(avaluo.getSubsidio().getId());
				subsidio.setNombre(avaluo.getSubsidio().getNombre());
				subsidio.setMinimo(
						salarioMinimo.getMensual().multiply(BigDecimal.valueOf(avaluo.getSubsidio().getMinimo())));
				subsidio.setMaximo(
						salarioMinimo.getMensual().multiply(BigDecimal.valueOf(avaluo.getSubsidio().getMaximo())));
				subsidio.setMinimoSalarios(avaluo.getSubsidio().getMinimo());
				subsidio.setMaximoSalarios(avaluo.getSubsidio().getMaximo());
				avaluoDTO.setTipoSubsidio(subsidio);
			}
		} else {
			throw new NullPointerException();
		}

	}

	private EstadoAvaluo obtenerEstadoActual(Avaluo avaluo) {
		return Iterables.getLast(avaluo.getEstadosAvaluo());
	}

	private EstadoAvaluo obtenerEstadoEnviado(Avaluo avaluo) {
		List<EstadoAvaluo> estados = avaluo.getEstadosAvaluo().stream().filter(e -> e.getEstado().equals(8))
				.collect(Collectors.toList());
		if (estados.isEmpty()) {
			return avaluo.getEstadosAvaluo().stream().filter(e -> e.getEstado().equals(10)).collect(Collectors.toList())
					.get(0);
		}
		return estados.get(0);
	}

	public EntidadDTO escribirDTO(Entidad entidad) {
		EntidadDTO entidadDTO = null;
		if (entidad != null) {
			entidadDTO = new EntidadDTO();
			entidadDTO.setId(entidad.getId());
			entidadDTO.setNombre(entidad.getNombre());
			entidadDTO.setNit(entidad.getNit());
			entidadDTO.setPrefijo(entidad.getPrefijo());
			entidadDTO.setPorcentajeValorAsegurable(entidad.getPorcentajeValorAsegurable());
			entidadDTO.setFechaCreacion(entidad.getFechaCreacion());
			entidadDTO.setActivo(entidad.getActivo());
			/*
			 * if (entidad.getSucursales() != null && !entidad.getSucursales().isEmpty()) {
			 * escribirSucursales(entidadDTO, entidad.getSucursales()); } if
			 * (entidad.getTarifas() != null && !entidad.getTarifas().isEmpty()) {
			 * escribirTarifas(entidadDTO, entidad.getTarifas()); } if
			 * (entidad.getSegmentos() != null && !entidad.getSegmentos().isEmpty()) {
			 * escribirSegmentos(entidadDTO, entidad.getSegmentos()); }
			 */
		}

		return entidadDTO;
	}

	public TipoInmuebleDTO escribirDTO(TipoInmueble tipoInmueble) {
		TipoInmuebleDTO tipoInmuebleDTO = null;
		if (tipoInmueble != null) {
			tipoInmuebleDTO = new TipoInmuebleDTO();
			tipoInmuebleDTO.setId(tipoInmueble.getId());
			tipoInmuebleDTO.setNombre(tipoInmueble.getNombre());
		}
		return tipoInmuebleDTO;
	}

	public AsesorDTO escribirDTO(Asesor asesor) {
		AsesorDTO asesorDTO = null;
		if (asesor != null) {
			if (asesor.getId() != null) {
				asesorDTO = new AsesorDTO();
				asesorDTO.setCelular(asesor.getCelular());
				asesorDTO.setCorreo(asesor.getCorreo());
				asesorDTO.setId(asesor.getId());
				asesorDTO.setNombre(asesor.getNombre());
				asesorDTO.setSucursal(escribirDTO(asesor.getSucursal()));
				asesorDTO.setTelefono(asesor.getTelefono());
			}
		}
		return asesorDTO;
	}

	private void escribirSucursales(EntidadDTO entidadDTO, Set<Sucursal> sucursales) {
		Set<SucursalDTO> sucursalesDTO = new HashSet<SucursalDTO>();
		for (Sucursal sucursal : sucursales) {
			sucursalesDTO.add(escribirDTO(sucursal));
		}
		entidadDTO.setSucursales(sucursalesDTO);
	}

	public SucursalDTO escribirDTO(Sucursal sucursal) {
		SucursalDTO sucursalDTO = new SucursalDTO();
		if (sucursal != null) {
			sucursalDTO.setId(sucursal.getId());
			sucursalDTO.setCodigo(sucursal.getCodigo());
			sucursalDTO.setNombre(sucursal.getNombre());
			sucursalDTO.setNombreCompuesto(sucursal.getCodigo() + " " + sucursal.getNombre());
			if (sucursal.getFechaCreacion() != null)
				sucursalDTO.setFechaCreacion(DateUtils.getFechaFormateada(sucursal.getFechaCreacion(),
						DateUtils.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS));
			sucursalDTO.setActivo(sucursal.getActivo());
			if (sucursal.getEntidad() != null) {
				sucursalDTO.setEntidadId(sucursal.getEntidad().getId());
				sucursalDTO.setNombreEntidad(sucursal.getEntidad().getNombre());
			}
		}
		return sucursalDTO;
	}

	public void escribirSegmentos(EntidadDTO entidadDTO, Set<Segmento> segmentos) {
		Set<SegmentoDTO> segmentoDTO = new HashSet<SegmentoDTO>();
		for (Segmento segmento : segmentos) {
			segmentoDTO.add(segmentoEnsamblador.escribirDTO(segmento));
		}
		entidadDTO.setSegmentos(segmentoDTO);
	}

	public void escribirTipoAvaluos(TipoAvaluoDTO tipoAvaluoDTO, Set<TipoAvaluo> tipoAvaluos) {
		Set<TipoAvaluoDTO> tipoAvaluoDTOs = new HashSet<TipoAvaluoDTO>();
		for (TipoAvaluo tipoAvaluo : tipoAvaluos) {
			tipoAvaluoDTOs.add(escribirDTO(tipoAvaluo));
		}
	}

	public TipoAvaluoDTO escribirDTO(TipoAvaluo tipoAvaluo) {
		TipoAvaluoDTO tipoAvaluoDTO = null;
		if (tipoAvaluo != null) {
			tipoAvaluoDTO = new TipoAvaluoDTO();
			tipoAvaluoDTO.setId(tipoAvaluo.getId());
			tipoAvaluoDTO.setNombre(tipoAvaluo.getNombre());
		}
		return tipoAvaluoDTO;
	}

	public List<AvaluoDTO> escribirListaDTO(List<? extends Avaluo> avaluos) {
		List<AvaluoDTO> avaluoDTOs = new ArrayList<AvaluoDTO>();
		for (Avaluo avaluo : avaluos) {
			avaluoDTOs.add(escribirDTO(avaluo));
		}
		return avaluoDTOs;
	}

	public Set<AvaluoDTO> escribirListaDTO(Set<? extends Avaluo> avaluos) {
		Set<AvaluoDTO> avaluoDTOs = new LinkedHashSet<AvaluoDTO>();
		for (Avaluo avaluo : avaluos) {
			avaluoDTOs.add(escribirDTO(avaluo));
		}
		return avaluoDTOs;
	}

	public AvaluoDTO escribirDTO(Avaluo avaluo) {
		if (avaluo != null) {
			if (avaluo.getClass().equals(AvaluoHipotecario.class)) {
				return escribirDTO((AvaluoHipotecario) avaluo);
			} else if (avaluo.getClass().equals(AvaluoComercial.class)) {
				return escribirDTO((AvaluoComercial) avaluo);
			} else if (avaluo.getClass().equals(AvaluoConstructor.class)) {
				return escribirDTO((AvaluoConstructor) avaluo);
			} else if (avaluo.getClass().equals(AvaluoRemate.class)) {
				return escribirDTO((AvaluoRemate) avaluo);
			}
			return new AvaluoDTO();
		} else {
			return null;
		}
	}

	private void escribirFotografias(Set<Fotografia> fotografias, AvaluoDTO avaluoDTO) {
		Set<FotografiaDTO> fotografiaDTOs = new HashSet<FotografiaDTO>();
		for (Fotografia fotografia : fotografias) {
			fotografiaDTOs.add(fotografiaEnsamblador.escribirDTO(fotografia));
		}
		avaluoDTO.setFotografias(fotografiaDTOs);

	}

	private void escribirAreas(Set<Area> areas, AvaluoDTO avaluoDTO) {
		Set<AreaDTO> areaDTOs = new HashSet<AreaDTO>();
		for (Area area : areas) {
			areaDTOs.add(areaEnsamblador.escribirDTO(area));
		}
		avaluoDTO.setAreas(areaDTOs);
	}

	Set<AreaDTO> escribirAreas(Set<Area> areas) {
		Set<AreaDTO> areaDTOs = new HashSet<AreaDTO>();
		for (Area area : areas) {
			areaDTOs.add(areaEnsamblador.escribirDTO(area));
		}
		return areaDTOs;
	}

	public AvaluoHipotecarioDTO escribirDTO(AvaluoHipotecario avaluoHipotecario) {
		AvaluoHipotecarioDTO avaluoHipotecarioDTO = new AvaluoHipotecarioDTO();
		escribirDTO(avaluoHipotecarioDTO, avaluoHipotecario);
		return avaluoHipotecarioDTO;
	}

	/**
	 * Actualiza los valores del objeto.
	 * 
	 * @param avaluoHipotecarioDTO
	 * @throws AvaluoNotFoundException
	 * @throws MetodoValuacionNotFoundException
	 * @throws FormatoInformeNotFoundException
	 * @throws TipoAvaluoNotFoundException
	 * @throws AreaNotFoundException
	 * @throws FotografiaNotFoundException
	 * @throws UsuarioNotFoundException
	 * @throws ClienteNotFoundException
	 * @throws EntidadNotFoundException
	 * @throws GarajeNotFoundException
	 */
	public void actualizarAvaluoHipotecario(AvaluoHipotecario avaluoHipotecario,
			AvaluoHipotecarioDTO avaluoHipotecarioDTO)
			throws AvaluoNotFoundException, FormatoInformeNotFoundException, EntidadNotFoundException,
			ClienteNotFoundException, UsuarioNotFoundException, TipoAvaluoNotFoundException, DivipolaNotFoundException {
		log.debug("Actualizando avaluo hipotecario {}", avaluoHipotecarioDTO);
		actualizarAvaluo(avaluoHipotecario, avaluoHipotecarioDTO);
		FormatoInformeHipotecarioDTO formatoInformeDTO = (FormatoInformeHipotecarioDTO) avaluoHipotecarioDTO
				.getFormatoInforme();
		if (formatoInformeDTO == null) {
			return;
		}
		formatoInformeEnsamblador.actualizar(formatoInformeDTO.getId(), formatoInformeDTO);
	}

	private void actualizarAvaluo(Avaluo avaluo, AvaluoDTO avaluoDTO) throws EntidadNotFoundException,
			ClienteNotFoundException, UsuarioNotFoundException, TipoAvaluoNotFoundException {
		crearAvaluo(avaluo, avaluoDTO);
	}

	public AvaluoRemateDTO escribirDTO(AvaluoRemate avaluoRemate) {
		AvaluoRemateDTO avaluoRemateDTO = new AvaluoRemateDTO();
		escribirDTO(avaluoRemateDTO, avaluoRemate);
		avaluoRemateDTO.setNombreSecuestre(avaluoRemate.getNombreSecuestre());
		avaluoRemateDTO.setTelefonoSecuestre(avaluoRemate.getTelefonoSecuestre());
		avaluoRemateDTO.setCelularSecuestre(avaluoRemate.getCelularSecuestre());
		avaluoRemateDTO.setEmailSecuestre(avaluoRemate.getEmailSecuestre());
		return avaluoRemateDTO;
	}

	public AvaluoComercialDTO escribirDTO(AvaluoComercial avaluoComercial) {
		AvaluoComercialDTO avaluoComercialDTO = new AvaluoComercialDTO();
		escribirDTO(avaluoComercialDTO, avaluoComercial);
		return avaluoComercialDTO;
	}

	public AvaluoComercial crearAvaluoComercial(AvaluoComercialDTO avaluoComercialDTO)
			throws ClienteNotFoundException, EntidadNotFoundException, UsuarioNotFoundException,
			FotografiaNotFoundException, AreaNotFoundException, InmuebleNotFoundException, ServidumbreNotFoundException,
			ViaAccesoNotFoundException, MetodoValuacionNotFoundException, AreaConstruccionNotFoundException,
			TipoAvaluoNotFoundException, GarajeNotFoundException {
		AvaluoComercial avaluoComercial = new AvaluoComercial();
		crearAvaluo(avaluoComercial, avaluoComercialDTO);
		return avaluoComercial;
	}

	public void actualizarAvaluoComercial(AvaluoComercial avaluoComercial, AvaluoComercialDTO actualizado)
			throws AvaluoNotFoundException, InmuebleNotFoundException, ServidumbreNotFoundException,
			ViaAccesoNotFoundException, MetodoValuacionNotFoundException, AreaConstruccionNotFoundException,
			FormatoInformeNotFoundException, EntidadNotFoundException, ClienteNotFoundException,
			UsuarioNotFoundException, FotografiaNotFoundException, AreaNotFoundException, TipoAvaluoNotFoundException,
			GarajeNotFoundException {
		log.debug("Actualizando avaluo hipotecario {}", actualizado);
		actualizarAvaluo(avaluoComercial, actualizado);
		FormatoInformeComercialDTO formatoInformeComercialDTO = (FormatoInformeComercialDTO) actualizado
				.getFormatoInforme();
		formatoInformeEnsamblador.actualizar(formatoInformeComercialDTO.getId(), formatoInformeComercialDTO);
	}

	public AvaluoConstructor crearAvaluoConstructor(AvaluoConstructorDTO avaluoConstructorDTO)
			throws EntidadNotFoundException, ClienteNotFoundException, UsuarioNotFoundException,
			FotografiaNotFoundException, AreaNotFoundException, CronogramaObraNotFoundException,
			TipoAvaluoNotFoundException, MetodoValuacionNotFoundException, GarajeNotFoundException {
		AvaluoConstructor avaluoConstructor = new AvaluoConstructor();
		crearAvaluo(avaluoConstructor, avaluoConstructorDTO);
		avaluoConstructor.setTipoDeConstruccion(avaluoConstructorDTO.getTipoDeConstruccion());
		avaluoConstructor.setVereda(avaluoConstructorDTO.getVereda());
		avaluoConstructor.setNombreDePredio(avaluoConstructorDTO.getNombreDePredio());
		avaluoConstructor.setLocalizacion(avaluoConstructorDTO.getLocalizacion());
		avaluoConstructor.setDescripcionGeneralDelSector(avaluoConstructorDTO.getDescripcionGeneralDelSector());
		avaluoConstructor.setAlcantarilladoSector(avaluoConstructorDTO.getAlcantarilladoSector());
		avaluoConstructor.setAguaSector(avaluoConstructorDTO.getAguaSector());
		avaluoConstructor.setEnergiaSector(avaluoConstructorDTO.getEnergiaSector());
		avaluoConstructor.setGasSector(avaluoConstructorDTO.getGasSector());
		avaluoConstructor.setTelefonoSector(avaluoConstructorDTO.getTelefonoSector());
		avaluoConstructor.setObservacionesServiviosPublicos(avaluoConstructorDTO.getObservacionesServiviosPublicos());
		avaluoConstructor.setEstrato(avaluoConstructorDTO.getEstrato());
		avaluoConstructor.setZonaObjetivo(avaluoConstructorDTO.getZonaObjetivo());
		avaluoConstructor.setUsoPredominanteDelSector(avaluoConstructorDTO.getUsoPredominanteDelSector());
		avaluoConstructor.setObservacionesUsoPredominanteDelSector(
				avaluoConstructorDTO.getObservacionesUsoPredominanteDelSector());
		avaluoConstructor.setPerspectivasDeValorizacion(avaluoConstructorDTO.getPerspectivasDeValorizacion());
		avaluoConstructor.setObservacionesPerspectivasDeValorizacion(
				avaluoConstructorDTO.getObservacionesPerspectivasDeValorizacion());
		avaluoConstructor.setDescripcionInmueble(avaluoConstructorDTO.getDescripcionInmueble());
		avaluoConstructor.setLinderosParticularesDelInmueble(avaluoConstructorDTO.getLinderosParticularesDelInmueble());
		avaluoConstructor.setFuente(avaluoConstructorDTO.getFuente());
		avaluoConstructor.setNormatividadVigente(avaluoConstructorDTO.getNormatividadVigente());
		avaluoConstructor.setUbicacionInmediataDelInmueble(avaluoConstructorDTO.getUbicacionInmediataDelInmueble());
		avaluoConstructor.setObservacionesUbicacionInmediataDelInmueble(
				avaluoConstructorDTO.getObservacionesUbicacionInmediataDelInmueble());
		avaluoConstructor.setRelieve(avaluoConstructorDTO.getRelieve());
		avaluoConstructor.setFormaGeometrica(avaluoConstructorDTO.getFormaGeometrica());
		avaluoConstructor.setRegular(avaluoConstructorDTO.getRegular());
		avaluoConstructor.setIrregular(avaluoConstructorDTO.getIrregular());
		avaluoConstructor.setFrente(avaluoConstructorDTO.getFrente());
		avaluoConstructor.setFondo(avaluoConstructorDTO.getFondo());
		avaluoConstructor.setRelacionFrente(avaluoConstructorDTO.getRelacionFrente());
		avaluoConstructor.setUsoInmueble(avaluoConstructorDTO.getUsoInmueble());
		avaluoConstructor.setClaseInmueble(avaluoConstructorDTO.getClaseInmueble());
		avaluoConstructor.setEstadoInmueble(avaluoConstructorDTO.getEstadoInmueble());
		avaluoConstructor.setUbicacionLocal(avaluoConstructorDTO.getUbicacionLocal());
		avaluoConstructor.setRequiereReparaciones(avaluoConstructorDTO.getRequiereReparaciones());
		avaluoConstructor.setCualesReaparaciones(avaluoConstructorDTO.getCualesReaparaciones());
		avaluoConstructor.setAlcantarillado(avaluoConstructorDTO.getAlcantarillado());
		avaluoConstructor.setEnergia(avaluoConstructorDTO.getEnergia());
		avaluoConstructor.setAgua(avaluoConstructorDTO.getAgua());
		avaluoConstructor.setTelefono(avaluoConstructorDTO.getTelefono());
		avaluoConstructor.setConceptoDeComercializacion(avaluoConstructorDTO.getConceptoDeComercializacion());
		avaluoConstructor.setObservacionesConceptoDeComercializacion(
				avaluoConstructorDTO.getObservacionesConceptoDeComercializacion());
		avaluoConstructor.setSometidoAPropiedadHorizontal(avaluoConstructorDTO.getSometidoAPropiedadHorizontal());
		avaluoConstructor.setConjuntoOAgrupacion(avaluoConstructorDTO.getConjuntoOAgrupacion());
		avaluoConstructor.setNumeroDeEdificios(avaluoConstructorDTO.getNumeroDeEdificios());
		avaluoConstructor.setTotalUnidades(avaluoConstructorDTO.getTotalUnidades());
		avaluoConstructor.setUnidad(avaluoConstructorDTO.getUnidad());
		avaluoConstructor.setUnidadesPorPiso(avaluoConstructorDTO.getUnidadesPorPiso());
		avaluoConstructor.setUbicacionPropiedadHorizontal(avaluoConstructorDTO.getUbicacionPropiedadHorizontal());
		avaluoConstructor.setRegistroDePropiedadHorizontal(avaluoConstructorDTO.getRegistroDePropiedadHorizontal());
		avaluoConstructor.setZonasVerdes(avaluoConstructorDTO.getZonasVerdes());
		avaluoConstructor.setPiscina(avaluoConstructorDTO.getPiscina());
		avaluoConstructor.setSalonSocial(avaluoConstructorDTO.getSalonSocial());
		avaluoConstructor.setJuegosInfantiles(avaluoConstructorDTO.getJuegosInfantiles());
		avaluoConstructor.setAscensor(avaluoConstructorDTO.getAscensor());
		avaluoConstructor.setUbicacionDelSector(avaluoConstructorDTO.getUbicacionDelSector());
		avaluoConstructor.setVecindario(avaluoConstructorDTO.getVecindario());
		avaluoConstructor.setDesarrolloYProyeccionUrbana(avaluoConstructorDTO.getDesarrolloYProyeccionUrbana());
		avaluoConstructor.setViasDeAcceso(avaluoConstructorDTO.getViasDeAcceso());
		avaluoConstructor.setTrasporteUrbano(avaluoConstructorDTO.getTrasporteUrbano());
		avaluoConstructor.setAlumbradoYAlcantarillado(avaluoConstructorDTO.getAlumbradoYAlcantarillado());
		avaluoConstructor.setCallesYAceras(avaluoConstructorDTO.getCallesYAceras());
		avaluoConstructor.setProporcionZonaSocial(avaluoConstructorDTO.getProporcionZonaSocial());
		avaluoConstructor.setProporcionZonaServicios(avaluoConstructorDTO.getProporcionZonaServicios());
		avaluoConstructor.setAcabados(avaluoConstructorDTO.getAcabados());
		avaluoConstructor.setDisenoYDistribucion(avaluoConstructorDTO.getDisenoYDistribucion());
		avaluoConstructor.setEstadoGeneralDelInmueble(avaluoConstructorDTO.getEstadoGeneralDelInmueble());
		avaluoConstructor.setObservacionesAnalisisTecnico(avaluoConstructorDTO.getObservacionesAnalisisTecnico());
		avaluoConstructor.setBanoServicios(avaluoConstructorDTO.getBanoServicios());
		avaluoConstructor.setZonaDeRopas(avaluoConstructorDTO.getZonaDeRopas());
		avaluoConstructor.setNumeroDePisos(avaluoConstructorDTO.getNumeroDePisos());
		avaluoConstructor.setNumeroDeSotanos(avaluoConstructorDTO.getNumeroDeSotanos());
		avaluoConstructor.setAnoDeConstruccion(avaluoConstructorDTO.getAnoDeConstruccion());
		avaluoConstructor.setPisos(avaluoConstructorDTO.getPisos());
		avaluoConstructor.setMuros(avaluoConstructorDTO.getMuros());
		avaluoConstructor.setTechos(avaluoConstructorDTO.getTechos());
		avaluoConstructor.setBanos(avaluoConstructorDTO.getBanos());
		avaluoConstructor.setCocina(avaluoConstructorDTO.getCocina());

		avaluoConstructor.setHabitaciones(avaluoConstructorDTO.getHabitaciones());
		avaluoConstructor.setEstarHabitacion(avaluoConstructorDTO.getEstarHabitacion());
		avaluoConstructor.setCuartoServicio(avaluoConstructorDTO.getCuartoServicio());
		avaluoConstructor.setCloset(avaluoConstructorDTO.getCloset());
		avaluoConstructor.setSala(avaluoConstructorDTO.getSala());
		avaluoConstructor.setComedor(avaluoConstructorDTO.getComedor());
		avaluoConstructor.setBanoPrivado(avaluoConstructorDTO.getBanoPrivado());
		avaluoConstructor.setBanoSocial(avaluoConstructorDTO.getBanoSocial());
		avaluoConstructor.setEstudio(avaluoConstructorDTO.getEstudio());
		avaluoConstructorDTO.setBalcon(avaluoConstructorDTO.getBalcon());
		avaluoConstructorDTO.setTerraza(avaluoConstructorDTO.getTerraza());
		avaluoConstructorDTO.setPatioInterior(avaluoConstructorDTO.getPatioInterior());
		avaluoConstructorDTO.setJardin(avaluoConstructorDTO.getJardin());
		avaluoConstructorDTO.setZonaVerdePrivada(avaluoConstructorDTO.getZonaVerdePrivada());
		avaluoConstructorDTO.setLocal(avaluoConstructorDTO.getLocal());
		avaluoConstructorDTO.setOficina(avaluoConstructorDTO.getOficina());
		avaluoConstructorDTO.setBodega(avaluoConstructorDTO.getBodega());

		avaluoConstructor.setGarajePrivado(avaluoConstructorDTO.getGarajePrivado());
		avaluoConstructor.setGarajeExlusivo(avaluoConstructorDTO.getGarajeExlusivo());
		avaluoConstructor.setBahiaComunal(avaluoConstructorDTO.getBahiaComunal());
		avaluoConstructor.setGarajeDoble(avaluoConstructorDTO.getGarajeDoble());
		avaluoConstructor.setGarajeParalelo(avaluoConstructorDTO.getGarajeParalelo());
		avaluoConstructor.setMatriculaInmobiliariaGaraje1(avaluoConstructorDTO.getMatriculaInmobiliariaGaraje1());
		avaluoConstructor.setMatriculaInmobiliariaGaraje2(avaluoConstructorDTO.getMatriculaInmobiliariaGaraje2());
		avaluoConstructor.setMatriculaInmobiliariaGaraje3(avaluoConstructorDTO.getMatriculaInmobiliariaGaraje3());
		avaluoConstructor.setMatriculaInmobiliariaGaraje4(avaluoConstructorDTO.getMatriculaInmobiliariaGaraje4());
		avaluoConstructor.setMatriculaInmobiliariaGaraje5(avaluoConstructorDTO.getMatriculaInmobiliariaGaraje5());
		avaluoConstructor.setGarajeParalelo1(avaluoConstructorDTO.getGarajeParalelo1());
		avaluoConstructor.setGarajeParalelo2(avaluoConstructorDTO.getGarajeParalelo2());
		avaluoConstructor.setGarajeParalelo3(avaluoConstructorDTO.getGarajeParalelo3());
		avaluoConstructor.setGarajeParalelo4(avaluoConstructorDTO.getGarajeParalelo4());
		avaluoConstructor.setGarajeParalelo5(avaluoConstructorDTO.getGarajeParalelo5());
		avaluoConstructor.setGarajeCubierto1(avaluoConstructorDTO.getGarajeCubierto1());
		avaluoConstructor.setGarajeCubierto2(avaluoConstructorDTO.getGarajeCubierto2());
		avaluoConstructor.setGarajeCubierto3(avaluoConstructorDTO.getGarajeCubierto3());
		avaluoConstructor.setGarajeCubierto4(avaluoConstructorDTO.getGarajeCubierto4());
		avaluoConstructor.setGarajeCubierto5(avaluoConstructorDTO.getGarajeCubierto5());
		avaluoConstructor.setNumeroParqueadero1(avaluoConstructorDTO.getNumeroParqueadero1());
		avaluoConstructor.setNumeroParqueadero2(avaluoConstructorDTO.getNumeroParqueadero2());
		avaluoConstructor.setNumeroParqueadero3(avaluoConstructorDTO.getNumeroParqueadero3());
		avaluoConstructor.setNumeroParqueadero4(avaluoConstructorDTO.getNumeroParqueadero4());
		avaluoConstructor.setNumeroParqueadero5(avaluoConstructorDTO.getNumeroParqueadero5());
		avaluoConstructor.setGarajeServidumbre1(avaluoConstructorDTO.getGarajeServidumbre1());
		avaluoConstructor.setGarajeServidumbre2(avaluoConstructorDTO.getGarajeServidumbre2());
		avaluoConstructor.setGarajeServidumbre3(avaluoConstructorDTO.getGarajeServidumbre3());
		avaluoConstructor.setGarajeServidumbre4(avaluoConstructorDTO.getGarajeServidumbre4());
		avaluoConstructor.setGarajeServidumbre5(avaluoConstructorDTO.getGarajeServidumbre5());
		avaluoConstructor.setMatriculaInmobiliariaDeposito1(avaluoConstructorDTO.getMatriculaInmobiliariaDeposito1());
		avaluoConstructor.setMatriculaInmobiliariaDeposito2(avaluoConstructorDTO.getMatriculaInmobiliariaDeposito2());
		avaluoConstructor.setMatriculaInmobiliariaDeposito3(avaluoConstructorDTO.getMatriculaInmobiliariaDeposito3());
		avaluoConstructor.setMatriculaInmobiliariaDeposito4(avaluoConstructorDTO.getMatriculaInmobiliariaDeposito4());
		avaluoConstructor.setMatriculaInmobiliariaDeposito5(avaluoConstructorDTO.getMatriculaInmobiliariaDeposito5());
		avaluoConstructor.setNumeroDeposito1(avaluoConstructorDTO.getNumeroDeposito1());
		avaluoConstructor.setNumeroDeposito2(avaluoConstructorDTO.getNumeroDeposito2());
		avaluoConstructor.setNumeroDeposito3(avaluoConstructorDTO.getNumeroDeposito3());
		avaluoConstructor.setNumeroDeposito4(avaluoConstructorDTO.getNumeroDeposito4());
		avaluoConstructor.setNumeroDeposito5(avaluoConstructorDTO.getNumeroDeposito5());
		avaluoConstructor.setNumeroDePisos(avaluoConstructorDTO.getNumeroDePisos());
		avaluoConstructor.setNumeroDeSotanos(avaluoConstructorDTO.getNumeroDeSotanos());
		avaluoConstructor.setNumeroTotalDeGarajes(avaluoConstructorDTO.getNumeroTotalDeGarajes());
		avaluoConstructor.setTotalCuposDeParqueo(avaluoConstructorDTO.getTotalCuposDeParqueo());
		avaluoConstructor.setLoteProyectoM2(avaluoConstructorDTO.getLoteProyectoM2());
		avaluoConstructor.setValorLote(avaluoConstructorDTO.getValorLote());
		avaluoConstructor.setCostosDirectos(avaluoConstructorDTO.getCostosDirectos());
		avaluoConstructor.setPorcentajeCostosDirectos(avaluoConstructorDTO.getPorcentajeCostosDirectos());
		avaluoConstructor.setCostosIndirectos(avaluoConstructorDTO.getCostosIndirectos());
		avaluoConstructor.setPorcentajeCostosIndirectos(avaluoConstructorDTO.getPorcentajeCostosIndirectos());
		avaluoConstructor.setCostosDelProyecto(avaluoConstructorDTO.getCostosDelProyecto());
		avaluoConstructor.setPorcentajeDeCostosDelProyecto(avaluoConstructorDTO.getPorcentajeDeCostosDelProyecto());
		avaluoConstructor.setValorSolicitado(avaluoConstructorDTO.getValorSolicitado());
		avaluoConstructor.setPorcentajeAFinanciar(avaluoConstructorDTO.getPorcentajeAFinanciar());
		avaluoConstructor.setValorMaxAFinanciar(avaluoConstructorDTO.getValorMaxAFinanciar());
		avaluoConstructor.setPorcentajeFinanciado(avaluoConstructorDTO.getPorcentajeFinanciado());
		avaluoConstructor.setProgramacionEnMeses(avaluoConstructorDTO.getProgramacionEnMeses());
		avaluoConstructor.setFactor(avaluoConstructorDTO.getFactor());
		avaluoConstructor.setValorSolicitadoTotal(avaluoConstructorDTO.getValorSolicitadoTotal());
		avaluoConstructor.setCostosFinancieros(avaluoConstructorDTO.getCostosFinancieros());
		avaluoConstructor.setCostosTotalDelProyecto(avaluoConstructorDTO.getCostosTotalDelProyecto());
		avaluoConstructor.setObservacionesInformeTecnico(avaluoConstructorDTO.getObservacionesInformeTecnico());
		avaluoConstructor.setConceptoTecnico(avaluoConstructorDTO.getConceptoTecnico());
		avaluoConstructor.setDescripcionAmpliacion(avaluoConstructorDTO.getDescripcionAmpliacion());
		avaluoConstructor.setDescripcionDelProyecto(avaluoConstructorDTO.getDescripcionDelProyecto());
		avaluoConstructor.setConsideracionesDelAvaluo(avaluoConstructorDTO.getConsideracionesDelAvaluo());
		avaluoConstructor.setDocumentosConsultados(avaluoConstructorDTO.getDocumentosConsultados());
		avaluoConstructor.setFechaAporteDeDocumentos(avaluoConstructorDTO.getFechaAporteDeDocumentos());
		avaluoConstructor.setOtrosDocumentos(avaluoConstructorDTO.getOtrosDocumentos());
		avaluoConstructor.setObservacionesDeTitulacion(avaluoConstructorDTO.getObservacionesDeTitulacion());
		avaluoConstructor.setRemosionMasas(avaluoConstructorDTO.getRemosionMasas());
		avaluoConstructor.setInundacion(avaluoConstructorDTO.getInundacion());
		avaluoConstructor.setOrdenPublico(avaluoConstructorDTO.getOrdenPublico());
		avaluoConstructor.setOtro(avaluoConstructorDTO.getOrdenPublico());
		avaluoConstructor.setObsRemosionMasas(avaluoConstructorDTO.getObsRemosionMasas());
		avaluoConstructor.setObsInundacion(avaluoConstructorDTO.getObsInundacion());
		avaluoConstructor.setObsOrdenPublico(avaluoConstructorDTO.getObsOrdenPublico());
		avaluoConstructor.setObsOtro(avaluoConstructorDTO.getObsOtro());
		if (avaluoConstructorDTO.getCronogramaObra() != null) {
			CronogramaObra cronogramaObra = cronogramaObraRepository
					.findOne(avaluoConstructorDTO.getCronogramaObra().getId());
			if (cronogramaObra == null) {
				throw new CronogramaObraNotFoundException();
			}
			avaluoConstructor.setCronogramaObra(cronogramaObra);
		}
		return avaluoConstructor;
	}

	public AvaluoConstructorDTO escribirDTO(AvaluoConstructor avaluoConstructor) {
		AvaluoConstructorDTO avaluoConstructorDTO = new AvaluoConstructorDTO();
		escribirDTO(avaluoConstructorDTO, avaluoConstructor);
		avaluoConstructorDTO.setTipoDeConstruccion(avaluoConstructor.getTipoDeConstruccion());
		avaluoConstructorDTO.setVereda(avaluoConstructor.getVereda());
		avaluoConstructorDTO.setNombreDePredio(avaluoConstructor.getNombreDePredio());
		avaluoConstructorDTO.setLocalizacion(avaluoConstructor.getLocalizacion());
		avaluoConstructorDTO.setBarrio(avaluoConstructor.getBarrio());
		avaluoConstructorDTO.setDescripcionGeneralDelSector(avaluoConstructor.getDescripcionGeneralDelSector());
		avaluoConstructorDTO.setAlcantarilladoSector(avaluoConstructor.getAlcantarilladoSector());
		avaluoConstructorDTO.setAguaSector(avaluoConstructor.getAguaSector());
		avaluoConstructorDTO.setEnergiaSector(avaluoConstructor.getEnergiaSector());
		avaluoConstructorDTO.setGasSector(avaluoConstructor.getGasSector());
		avaluoConstructorDTO.setTelefonoSector(avaluoConstructor.getTelefonoSector());
		avaluoConstructorDTO.setObservacionesServiviosPublicos(avaluoConstructor.getObservacionesServiviosPublicos());
		avaluoConstructorDTO.setEstrato(avaluoConstructor.getEstrato());
		avaluoConstructorDTO.setZonaObjetivo(avaluoConstructor.getZonaObjetivo());
		avaluoConstructorDTO.setUsoPredominanteDelSector(avaluoConstructor.getUsoPredominanteDelSector());
		avaluoConstructorDTO
				.setObservacionesUsoPredominanteDelSector(avaluoConstructor.getObservacionesUsoPredominanteDelSector());
		avaluoConstructorDTO.setPerspectivasDeValorizacion(avaluoConstructor.getPerspectivasDeValorizacion());
		avaluoConstructorDTO.setObservacionesPerspectivasDeValorizacion(
				avaluoConstructor.getObservacionesPerspectivasDeValorizacion());
		avaluoConstructorDTO.setDescripcionInmueble(avaluoConstructor.getDescripcionInmueble());
		avaluoConstructorDTO.setLinderosParticularesDelInmueble(avaluoConstructor.getLinderosParticularesDelInmueble());
		avaluoConstructorDTO.setFuente(avaluoConstructor.getFuente());
		avaluoConstructorDTO.setNormatividadVigente(avaluoConstructor.getNormatividadVigente());
		avaluoConstructorDTO.setUbicacionInmediataDelInmueble(avaluoConstructor.getUbicacionInmediataDelInmueble());
		avaluoConstructorDTO.setObservacionesUbicacionInmediataDelInmueble(
				avaluoConstructor.getObservacionesUbicacionInmediataDelInmueble());
		avaluoConstructorDTO.setRelieve(avaluoConstructor.getRelieve());
		avaluoConstructorDTO.setFormaGeometrica(avaluoConstructor.getFormaGeometrica());
		avaluoConstructorDTO.setRegular(avaluoConstructor.getRegular());
		avaluoConstructorDTO.setIrregular(avaluoConstructor.getIrregular());
		avaluoConstructorDTO.setFrente(avaluoConstructor.getFrente());
		avaluoConstructorDTO.setFondo(avaluoConstructor.getFondo());
		avaluoConstructorDTO.setRelacionFrente(avaluoConstructor.getRelacionFrente());
		avaluoConstructorDTO.setUsoInmueble(avaluoConstructor.getUsoInmueble());
		avaluoConstructorDTO.setClaseInmueble(avaluoConstructor.getClaseInmueble());
		avaluoConstructorDTO.setEstadoInmueble(avaluoConstructor.getEstadoInmueble());
		avaluoConstructorDTO.setUbicacionLocal(avaluoConstructor.getUbicacionLocal());
		avaluoConstructorDTO.setRequiereReparaciones(avaluoConstructor.getRequiereReparaciones());
		avaluoConstructorDTO.setCualesReaparaciones(avaluoConstructor.getCualesReaparaciones());
		avaluoConstructorDTO.setAlcantarillado(avaluoConstructor.getAlcantarillado());
		avaluoConstructorDTO.setEnergia(avaluoConstructor.getEnergia());
		avaluoConstructorDTO.setAgua(avaluoConstructor.getAgua());
		avaluoConstructorDTO.setTelefono(avaluoConstructor.getTelefono());
		avaluoConstructorDTO.setConceptoDeComercializacion(avaluoConstructor.getConceptoDeComercializacion());
		avaluoConstructorDTO.setObservacionesConceptoDeComercializacion(
				avaluoConstructor.getObservacionesConceptoDeComercializacion());
		avaluoConstructorDTO.setSometidoAPropiedadHorizontal(avaluoConstructor.getSometidoAPropiedadHorizontal());
		avaluoConstructorDTO.setConjuntoOAgrupacion(avaluoConstructor.getConjuntoOAgrupacion());
		avaluoConstructorDTO.setNumeroDeEdificios(avaluoConstructor.getNumeroDeEdificios());
		avaluoConstructorDTO.setTotalUnidades(avaluoConstructor.getTotalUnidades());
		avaluoConstructorDTO.setUnidad(avaluoConstructor.getUnidad());
		avaluoConstructorDTO.setUnidadesPorPiso(avaluoConstructor.getUnidadesPorPiso());
		avaluoConstructorDTO.setUbicacionPropiedadHorizontal(avaluoConstructor.getUbicacionPropiedadHorizontal());
		avaluoConstructorDTO.setRegistroDePropiedadHorizontal(avaluoConstructor.getRegistroDePropiedadHorizontal());
		avaluoConstructorDTO.setZonasVerdes(avaluoConstructor.getZonasVerdes());
		avaluoConstructorDTO.setPiscina(avaluoConstructor.getPiscina());
		avaluoConstructorDTO.setSalonSocial(avaluoConstructor.getSalonSocial());
		avaluoConstructorDTO.setJuegosInfantiles(avaluoConstructor.getJuegosInfantiles());
		avaluoConstructorDTO.setAscensor(avaluoConstructor.getAscensor());
		avaluoConstructorDTO.setUbicacionDelSector(avaluoConstructor.getUbicacionDelSector());
		avaluoConstructorDTO.setVecindario(avaluoConstructor.getVecindario());
		avaluoConstructorDTO.setDesarrolloYProyeccionUrbana(avaluoConstructor.getDesarrolloYProyeccionUrbana());
		avaluoConstructorDTO.setViasDeAcceso(avaluoConstructor.getViasDeAcceso());
		avaluoConstructorDTO.setTrasporteUrbano(avaluoConstructor.getTrasporteUrbano());
		avaluoConstructorDTO.setAlumbradoYAlcantarillado(avaluoConstructor.getAlumbradoYAlcantarillado());
		avaluoConstructorDTO.setCallesYAceras(avaluoConstructor.getCallesYAceras());
		avaluoConstructorDTO.setProporcionZonaSocial(avaluoConstructor.getProporcionZonaSocial());
		avaluoConstructorDTO.setProporcionZonaServicios(avaluoConstructor.getProporcionZonaServicios());
		avaluoConstructorDTO.setAcabados(avaluoConstructor.getAcabados());
		avaluoConstructorDTO.setDisenoYDistribucion(avaluoConstructor.getDisenoYDistribucion());
		avaluoConstructorDTO.setEstadoGeneralDelInmueble(avaluoConstructor.getEstadoGeneralDelInmueble());
		avaluoConstructorDTO.setObservacionesAnalisisTecnico(avaluoConstructor.getObservacionesAnalisisTecnico());
		avaluoConstructorDTO.setBanoServicios(avaluoConstructor.getBanoServicios());
		avaluoConstructorDTO.setZonaDeRopas(avaluoConstructor.getZonaDeRopas());
		avaluoConstructorDTO.setNumeroDePisos(avaluoConstructor.getNumeroDePisos());
		avaluoConstructorDTO.setNumeroDeSotanos(avaluoConstructor.getNumeroDeSotanos());
		avaluoConstructorDTO.setAnoDeConstruccion(avaluoConstructor.getAnoDeConstruccion());
		avaluoConstructorDTO.setPisos(avaluoConstructor.getPisos());
		avaluoConstructorDTO.setMuros(avaluoConstructor.getMuros());
		avaluoConstructorDTO.setTechos(avaluoConstructor.getTechos());
		avaluoConstructorDTO.setBanos(avaluoConstructor.getBanos());
		avaluoConstructorDTO.setCocina(avaluoConstructor.getCocina());

		avaluoConstructorDTO.setHabitaciones(avaluoConstructor.getHabitaciones());
		avaluoConstructorDTO.setEstarHabitacion(avaluoConstructor.getEstarHabitacion());
		avaluoConstructorDTO.setCuartoServicio(avaluoConstructor.getCuartoServicio());
		avaluoConstructorDTO.setCloset(avaluoConstructor.getCloset());
		avaluoConstructorDTO.setSala(avaluoConstructor.getSala());
		avaluoConstructorDTO.setComedor(avaluoConstructor.getComedor());
		avaluoConstructorDTO.setBanoPrivado(avaluoConstructor.getBanoPrivado());
		avaluoConstructorDTO.setBanoSocial(avaluoConstructor.getBanoSocial());
		avaluoConstructorDTO.setEstudio(avaluoConstructor.getEstudio());
		avaluoConstructorDTO.setBalcon(avaluoConstructor.getBalcon());
		avaluoConstructorDTO.setTerraza(avaluoConstructor.getTerraza());
		avaluoConstructorDTO.setPatioInterior(avaluoConstructor.getPatioInterior());
		avaluoConstructorDTO.setJardin(avaluoConstructor.getJardin());
		avaluoConstructorDTO.setZonaVerdePrivada(avaluoConstructor.getZonaVerdePrivada());
		avaluoConstructorDTO.setLocal(avaluoConstructor.getLocal());
		avaluoConstructorDTO.setOficina(avaluoConstructor.getOficina());
		avaluoConstructorDTO.setBodega(avaluoConstructor.getBodega());

		avaluoConstructorDTO.setGarajePrivado(avaluoConstructor.getGarajePrivado());
		avaluoConstructorDTO.setGarajeExlusivo(avaluoConstructor.getGarajeExlusivo());
		avaluoConstructorDTO.setBahiaComunal(avaluoConstructor.getBahiaComunal());
		avaluoConstructorDTO.setGarajeDoble(avaluoConstructor.getGarajeDoble());
		avaluoConstructorDTO.setGarajeParalelo(avaluoConstructor.getGarajeParalelo());
		avaluoConstructorDTO.setMatriculaInmobiliariaGaraje1(avaluoConstructor.getMatriculaInmobiliariaGaraje1());
		avaluoConstructorDTO.setMatriculaInmobiliariaGaraje2(avaluoConstructor.getMatriculaInmobiliariaGaraje2());
		avaluoConstructorDTO.setMatriculaInmobiliariaGaraje3(avaluoConstructor.getMatriculaInmobiliariaGaraje3());
		avaluoConstructorDTO.setMatriculaInmobiliariaGaraje4(avaluoConstructor.getMatriculaInmobiliariaGaraje4());
		avaluoConstructorDTO.setMatriculaInmobiliariaGaraje5(avaluoConstructor.getMatriculaInmobiliariaGaraje5());
		avaluoConstructorDTO.setGarajeParalelo1(avaluoConstructor.getGarajeParalelo1());
		avaluoConstructorDTO.setGarajeParalelo2(avaluoConstructor.getGarajeParalelo2());
		avaluoConstructorDTO.setGarajeParalelo3(avaluoConstructor.getGarajeParalelo3());
		avaluoConstructorDTO.setGarajeParalelo4(avaluoConstructor.getGarajeParalelo4());
		avaluoConstructorDTO.setGarajeParalelo5(avaluoConstructor.getGarajeParalelo5());
		avaluoConstructorDTO.setGarajeCubierto1(avaluoConstructor.getGarajeCubierto1());
		avaluoConstructorDTO.setGarajeCubierto2(avaluoConstructor.getGarajeCubierto2());
		avaluoConstructorDTO.setGarajeCubierto3(avaluoConstructor.getGarajeCubierto3());
		avaluoConstructorDTO.setGarajeCubierto4(avaluoConstructor.getGarajeCubierto4());
		avaluoConstructorDTO.setGarajeCubierto5(avaluoConstructor.getGarajeCubierto5());
		avaluoConstructorDTO.setNumeroParqueadero1(avaluoConstructor.getNumeroParqueadero1());
		avaluoConstructorDTO.setNumeroParqueadero2(avaluoConstructor.getNumeroParqueadero2());
		avaluoConstructorDTO.setNumeroParqueadero3(avaluoConstructor.getNumeroParqueadero3());
		avaluoConstructorDTO.setNumeroParqueadero4(avaluoConstructor.getNumeroParqueadero4());
		avaluoConstructorDTO.setNumeroParqueadero5(avaluoConstructor.getNumeroParqueadero5());
		avaluoConstructorDTO.setGarajeServidumbre1(avaluoConstructor.getGarajeServidumbre1());
		avaluoConstructorDTO.setGarajeServidumbre2(avaluoConstructor.getGarajeServidumbre2());
		avaluoConstructorDTO.setGarajeServidumbre3(avaluoConstructor.getGarajeServidumbre3());
		avaluoConstructorDTO.setGarajeServidumbre4(avaluoConstructor.getGarajeServidumbre4());
		avaluoConstructorDTO.setGarajeServidumbre5(avaluoConstructor.getGarajeServidumbre5());
		avaluoConstructorDTO.setMatriculaInmobiliariaDeposito1(avaluoConstructor.getMatriculaInmobiliariaDeposito1());
		avaluoConstructorDTO.setMatriculaInmobiliariaDeposito2(avaluoConstructor.getMatriculaInmobiliariaDeposito2());
		avaluoConstructorDTO.setMatriculaInmobiliariaDeposito3(avaluoConstructor.getMatriculaInmobiliariaDeposito3());
		avaluoConstructorDTO.setMatriculaInmobiliariaDeposito4(avaluoConstructor.getMatriculaInmobiliariaDeposito4());
		avaluoConstructorDTO.setMatriculaInmobiliariaDeposito5(avaluoConstructor.getMatriculaInmobiliariaDeposito5());
		avaluoConstructorDTO.setNumeroDeposito1(avaluoConstructor.getNumeroDeposito1());
		avaluoConstructorDTO.setNumeroDeposito2(avaluoConstructor.getNumeroDeposito2());
		avaluoConstructorDTO.setNumeroDeposito3(avaluoConstructor.getNumeroDeposito3());
		avaluoConstructorDTO.setNumeroDeposito4(avaluoConstructor.getNumeroDeposito4());
		avaluoConstructorDTO.setNumeroDeposito5(avaluoConstructor.getNumeroDeposito5());
		avaluoConstructorDTO.setNumeroDePisos(avaluoConstructor.getNumeroDePisos());
		avaluoConstructorDTO.setNumeroDeSotanos(avaluoConstructor.getNumeroDeSotanos());
		avaluoConstructorDTO.setNumeroTotalDeGarajes(avaluoConstructor.getNumeroTotalDeGarajes());
		avaluoConstructorDTO.setTotalCuposDeParqueo(avaluoConstructor.getTotalCuposDeParqueo());
		avaluoConstructorDTO.setLoteProyectoM2(avaluoConstructor.getLoteProyectoM2());
		avaluoConstructorDTO.setValorLote(avaluoConstructor.getValorLote());
		avaluoConstructorDTO.setCostosDirectos(avaluoConstructor.getCostosDirectos());
		avaluoConstructorDTO.setPorcentajeCostosDirectos(avaluoConstructor.getPorcentajeCostosDirectos());
		avaluoConstructorDTO.setCostosIndirectos(avaluoConstructor.getCostosIndirectos());
		avaluoConstructorDTO.setPorcentajeCostosIndirectos(avaluoConstructor.getPorcentajeCostosIndirectos());
		avaluoConstructorDTO.setCostosDelProyecto(avaluoConstructor.getCostosDelProyecto());
		avaluoConstructorDTO.setPorcentajeDeCostosDelProyecto(avaluoConstructor.getPorcentajeDeCostosDelProyecto());
		avaluoConstructorDTO.setValorSolicitado(avaluoConstructor.getValorSolicitado());
		avaluoConstructorDTO.setPorcentajeAFinanciar(avaluoConstructor.getPorcentajeAFinanciar());
		avaluoConstructorDTO.setValorMaxAFinanciar(avaluoConstructor.getValorMaxAFinanciar());
		avaluoConstructorDTO.setPorcentajeFinanciado(avaluoConstructor.getPorcentajeFinanciado());
		avaluoConstructorDTO.setProgramacionEnMeses(avaluoConstructor.getProgramacionEnMeses());
		avaluoConstructorDTO.setFactor(avaluoConstructor.getFactor());
		avaluoConstructorDTO.setValorSolicitadoTotal(avaluoConstructor.getValorSolicitadoTotal());
		avaluoConstructorDTO.setCostosFinancieros(avaluoConstructor.getCostosFinancieros());
		avaluoConstructorDTO.setCostosTotalDelProyecto(avaluoConstructor.getCostosTotalDelProyecto());
		avaluoConstructorDTO.setObservacionesInformeTecnico(avaluoConstructor.getObservacionesInformeTecnico());
		avaluoConstructorDTO.setConceptoTecnico(avaluoConstructor.getConceptoTecnico());
		avaluoConstructorDTO.setDescripcionAmpliacion(avaluoConstructor.getDescripcionAmpliacion());
		avaluoConstructorDTO.setDescripcionDelProyecto(avaluoConstructor.getDescripcionDelProyecto());
		avaluoConstructorDTO.setConsideracionesDelAvaluo(avaluoConstructor.getConsideracionesDelAvaluo());
		avaluoConstructorDTO.setDocumentosConsultados(avaluoConstructor.getDocumentosConsultados());
		avaluoConstructorDTO.setFechaAporteDeDocumentos(avaluoConstructor.getFechaAporteDeDocumentos());
		avaluoConstructorDTO.setOtrosDocumentos(avaluoConstructor.getOtrosDocumentos());
		avaluoConstructorDTO.setObservacionesDeTitulacion(avaluoConstructor.getObservacionesDeTitulacion());
		avaluoConstructorDTO.setRemosionMasas(avaluoConstructor.getRemosionMasas());
		avaluoConstructorDTO.setInundacion(avaluoConstructor.getInundacion());
		avaluoConstructorDTO.setOrdenPublico(avaluoConstructor.getOrdenPublico());
		avaluoConstructorDTO.setOtro(avaluoConstructor.getOtro());
		avaluoConstructorDTO.setObsRemosionMasas(avaluoConstructor.getObsRemosionMasas());
		avaluoConstructorDTO.setObsInundacion(avaluoConstructor.getObsInundacion());
		avaluoConstructorDTO.setObsOrdenPublico(avaluoConstructor.getObsOrdenPublico());
		avaluoConstructorDTO.setObsOtro(avaluoConstructor.getObsOtro());
		if (avaluoConstructor.getCronogramaObra() != null) {
			avaluoConstructorDTO
					.setCronogramaObra(cronogramaObraEnsamblador.escribirDTO(avaluoConstructor.getCronogramaObra()));
		}
		if (avaluoConstructor.getAumentosPresupuesto() != null
				&& !avaluoConstructor.getAumentosPresupuesto().isEmpty()) {
			avaluoConstructorDTO.setAumentosPresupuesto(
					aumentoPresupuestoEnsamblador.escribirListaDTO(avaluoConstructor.getAumentosPresupuesto()));
		}
		if (avaluoConstructor.getProrrogas() != null && !avaluoConstructor.getProrrogas().isEmpty()) {
			avaluoConstructorDTO.setProrrogas(prorrogaEnsamblador.escribirListaDTO(avaluoConstructor.getProrrogas()));
		}
		return avaluoConstructorDTO;
	}

	public void actualizarAvaluoConstructor(AvaluoConstructor avaluoConstructor, AvaluoConstructorDTO actualizado)
			throws AvaluoNotFoundException, CronogramaObraNotFoundException, EntidadNotFoundException,
			ClienteNotFoundException, UsuarioNotFoundException, FotografiaNotFoundException, AreaNotFoundException,
			TipoAvaluoNotFoundException, MetodoValuacionNotFoundException, GarajeNotFoundException {
		log.debug("Actualizando avaluo constructor {}", actualizado);
		actualizarAvaluo(avaluoConstructor, actualizado);
		avaluoConstructor.setTipoDeConstruccion(actualizado.getTipoDeConstruccion());
		avaluoConstructor.setVereda(actualizado.getVereda());
		avaluoConstructor.setNombreDePredio(actualizado.getNombreDePredio());
		avaluoConstructor.setLocalizacion(actualizado.getLocalizacion());
		avaluoConstructor.setBarrio(actualizado.getBarrio());
		avaluoConstructor.setDescripcionGeneralDelSector(actualizado.getDescripcionGeneralDelSector());
		avaluoConstructor.setAlcantarilladoSector(actualizado.getAlcantarilladoSector());
		avaluoConstructor.setAguaSector(actualizado.getAguaSector());
		avaluoConstructor.setEnergiaSector(actualizado.getEnergiaSector());
		avaluoConstructor.setGasSector(actualizado.getGasSector());
		avaluoConstructor.setTelefonoSector(actualizado.getTelefonoSector());
		avaluoConstructor.setObservacionesServiviosPublicos(actualizado.getObservacionesServiviosPublicos());
		avaluoConstructor.setEstrato(actualizado.getEstrato());
		avaluoConstructor.setZonaObjetivo(actualizado.getZonaObjetivo());
		avaluoConstructor.setUsoPredominanteDelSector(actualizado.getUsoPredominanteDelSector());
		avaluoConstructor
				.setObservacionesUsoPredominanteDelSector(actualizado.getObservacionesUsoPredominanteDelSector());
		avaluoConstructor.setPerspectivasDeValorizacion(actualizado.getPerspectivasDeValorizacion());
		avaluoConstructor
				.setObservacionesPerspectivasDeValorizacion(actualizado.getObservacionesPerspectivasDeValorizacion());
		avaluoConstructor.setDescripcionInmueble(actualizado.getDescripcionInmueble());
		avaluoConstructor.setLinderosParticularesDelInmueble(actualizado.getLinderosParticularesDelInmueble());
		avaluoConstructor.setFuente(actualizado.getFuente());
		avaluoConstructor.setNormatividadVigente(actualizado.getNormatividadVigente());
		avaluoConstructor.setUbicacionInmediataDelInmueble(actualizado.getUbicacionInmediataDelInmueble());
		avaluoConstructor.setObservacionesUbicacionInmediataDelInmueble(
				actualizado.getObservacionesUbicacionInmediataDelInmueble());
		avaluoConstructor.setRelieve(actualizado.getRelieve());
		avaluoConstructor.setFormaGeometrica(actualizado.getFormaGeometrica());
		avaluoConstructor.setRegular(actualizado.getRegular());
		avaluoConstructor.setIrregular(actualizado.getIrregular());
		avaluoConstructor.setFrente(actualizado.getFrente());
		avaluoConstructor.setFondo(actualizado.getFondo());
		avaluoConstructor.setRelacionFrente(actualizado.getRelacionFrente());
		avaluoConstructor.setUsoInmueble(actualizado.getUsoInmueble());
		avaluoConstructor.setClaseInmueble(actualizado.getClaseInmueble());
		avaluoConstructor.setEstadoInmueble(actualizado.getEstadoInmueble());
		avaluoConstructor.setUbicacionLocal(actualizado.getUbicacionLocal());
		avaluoConstructor.setRequiereReparaciones(actualizado.getRequiereReparaciones());
		avaluoConstructor.setCualesReaparaciones(actualizado.getCualesReaparaciones());
		avaluoConstructor.setAlcantarillado(actualizado.getAlcantarillado());
		avaluoConstructor.setEnergia(actualizado.getEnergia());
		avaluoConstructor.setAgua(actualizado.getAgua());
		avaluoConstructor.setTelefono(actualizado.getTelefono());
		avaluoConstructor.setConceptoDeComercializacion(actualizado.getConceptoDeComercializacion());
		avaluoConstructor
				.setObservacionesConceptoDeComercializacion(actualizado.getObservacionesConceptoDeComercializacion());
		avaluoConstructor.setSometidoAPropiedadHorizontal(actualizado.getSometidoAPropiedadHorizontal());
		avaluoConstructor.setConjuntoOAgrupacion(actualizado.getConjuntoOAgrupacion());
		avaluoConstructor.setNumeroDeEdificios(actualizado.getNumeroDeEdificios());
		avaluoConstructor.setTotalUnidades(actualizado.getTotalUnidades());
		avaluoConstructor.setUnidad(actualizado.getUnidad());
		avaluoConstructor.setUnidadesPorPiso(actualizado.getUnidadesPorPiso());
		avaluoConstructor.setUbicacionPropiedadHorizontal(avaluoConstructor.getUbicacionPropiedadHorizontal());
		avaluoConstructor.setRegistroDePropiedadHorizontal(actualizado.getRegistroDePropiedadHorizontal());
		avaluoConstructor.setZonasVerdes(actualizado.getZonasVerdes());
		avaluoConstructor.setPiscina(actualizado.getPiscina());
		avaluoConstructor.setSalonSocial(actualizado.getSalonSocial());
		avaluoConstructor.setJuegosInfantiles(actualizado.getJuegosInfantiles());
		avaluoConstructor.setAscensor(actualizado.getAscensor());
		avaluoConstructor.setUbicacionDelSector(actualizado.getUbicacionDelSector());
		avaluoConstructor.setVecindario(actualizado.getVecindario());
		avaluoConstructor.setDesarrolloYProyeccionUrbana(actualizado.getDesarrolloYProyeccionUrbana());
		avaluoConstructor.setViasDeAcceso(actualizado.getViasDeAcceso());
		avaluoConstructor.setTrasporteUrbano(actualizado.getTrasporteUrbano());
		avaluoConstructor.setAlumbradoYAlcantarillado(actualizado.getAlumbradoYAlcantarillado());
		avaluoConstructor.setCallesYAceras(actualizado.getCallesYAceras());
		avaluoConstructor.setProporcionZonaSocial(actualizado.getProporcionZonaSocial());
		avaluoConstructor.setProporcionZonaServicios(actualizado.getProporcionZonaServicios());
		avaluoConstructor.setAcabados(actualizado.getAcabados());
		avaluoConstructor.setDisenoYDistribucion(actualizado.getDisenoYDistribucion());
		avaluoConstructor.setEstadoGeneralDelInmueble(actualizado.getEstadoGeneralDelInmueble());
		avaluoConstructor.setObservacionesAnalisisTecnico(actualizado.getObservacionesAnalisisTecnico());
		avaluoConstructor.setBanoServicios(actualizado.getBanoServicios());
		avaluoConstructor.setZonaDeRopas(actualizado.getZonaDeRopas());
		avaluoConstructor.setNumeroDePisos(actualizado.getNumeroDePisos());
		avaluoConstructor.setNumeroDeSotanos(actualizado.getNumeroDeSotanos());
		avaluoConstructor.setAnoDeConstruccion(actualizado.getAnoDeConstruccion());
		avaluoConstructor.setPisos(actualizado.getPisos());
		avaluoConstructor.setMuros(actualizado.getMuros());
		avaluoConstructor.setTechos(actualizado.getTechos());
		avaluoConstructor.setBanos(actualizado.getBanos());
		avaluoConstructor.setCocina(actualizado.getCocina());

		avaluoConstructor.setHabitaciones(actualizado.getHabitaciones());
		avaluoConstructor.setEstarHabitacion(actualizado.getEstarHabitacion());
		avaluoConstructor.setCuartoServicio(actualizado.getCuartoServicio());
		avaluoConstructor.setCloset(actualizado.getCloset());
		avaluoConstructor.setSala(actualizado.getSala());
		avaluoConstructor.setComedor(actualizado.getComedor());
		avaluoConstructor.setBanoPrivado(actualizado.getBanoPrivado());
		avaluoConstructor.setBanoSocial(actualizado.getBanoSocial());
		avaluoConstructor.setEstudio(actualizado.getEstudio());
		avaluoConstructor.setBalcon(actualizado.getBalcon());
		avaluoConstructor.setTerraza(actualizado.getTerraza());
		avaluoConstructor.setPatioInterior(actualizado.getPatioInterior());
		avaluoConstructor.setJardin(actualizado.getJardin());
		avaluoConstructor.setZonaVerdePrivada(actualizado.getZonaVerdePrivada());
		avaluoConstructor.setLocal(actualizado.getLocal());
		avaluoConstructor.setOficina(actualizado.getOficina());
		avaluoConstructor.setBodega(actualizado.getBodega());

		avaluoConstructor.setGarajePrivado(actualizado.getGarajePrivado());
		avaluoConstructor.setGarajeExlusivo(actualizado.getGarajeExlusivo());
		avaluoConstructor.setBahiaComunal(actualizado.getBahiaComunal());
		avaluoConstructor.setGarajeDoble(actualizado.getGarajeDoble());
		avaluoConstructor.setGarajeParalelo(actualizado.getGarajeParalelo());
		avaluoConstructor.setMatriculaInmobiliariaGaraje1(actualizado.getMatriculaInmobiliariaGaraje1());
		avaluoConstructor.setMatriculaInmobiliariaGaraje2(actualizado.getMatriculaInmobiliariaGaraje2());
		avaluoConstructor.setMatriculaInmobiliariaGaraje3(actualizado.getMatriculaInmobiliariaGaraje3());
		avaluoConstructor.setMatriculaInmobiliariaGaraje4(actualizado.getMatriculaInmobiliariaGaraje4());
		avaluoConstructor.setMatriculaInmobiliariaGaraje5(actualizado.getMatriculaInmobiliariaGaraje5());
		avaluoConstructor.setGarajeParalelo1(actualizado.getGarajeParalelo1());
		avaluoConstructor.setGarajeParalelo2(actualizado.getGarajeParalelo2());
		avaluoConstructor.setGarajeParalelo3(actualizado.getGarajeParalelo3());
		avaluoConstructor.setGarajeParalelo4(actualizado.getGarajeParalelo4());
		avaluoConstructor.setGarajeParalelo5(actualizado.getGarajeParalelo5());
		avaluoConstructor.setGarajeCubierto1(actualizado.getGarajeCubierto1());
		avaluoConstructor.setGarajeCubierto2(actualizado.getGarajeCubierto2());
		avaluoConstructor.setGarajeCubierto3(actualizado.getGarajeCubierto3());
		avaluoConstructor.setGarajeCubierto4(actualizado.getGarajeCubierto4());
		avaluoConstructor.setGarajeCubierto5(actualizado.getGarajeCubierto5());
		avaluoConstructor.setNumeroParqueadero1(actualizado.getNumeroParqueadero1());
		avaluoConstructor.setNumeroParqueadero2(actualizado.getNumeroParqueadero2());
		avaluoConstructor.setNumeroParqueadero3(actualizado.getNumeroParqueadero3());
		avaluoConstructor.setNumeroParqueadero4(actualizado.getNumeroParqueadero4());
		avaluoConstructor.setNumeroParqueadero5(actualizado.getNumeroParqueadero5());
		avaluoConstructor.setGarajeServidumbre1(actualizado.getGarajeServidumbre1());
		avaluoConstructor.setGarajeServidumbre2(actualizado.getGarajeServidumbre2());
		avaluoConstructor.setGarajeServidumbre3(actualizado.getGarajeServidumbre3());
		avaluoConstructor.setGarajeServidumbre4(actualizado.getGarajeServidumbre4());
		avaluoConstructor.setGarajeServidumbre5(actualizado.getGarajeServidumbre5());
		avaluoConstructor.setMatriculaInmobiliariaDeposito1(actualizado.getMatriculaInmobiliariaDeposito1());
		avaluoConstructor.setMatriculaInmobiliariaDeposito2(actualizado.getMatriculaInmobiliariaDeposito2());
		avaluoConstructor.setMatriculaInmobiliariaDeposito3(actualizado.getMatriculaInmobiliariaDeposito3());
		avaluoConstructor.setMatriculaInmobiliariaDeposito4(actualizado.getMatriculaInmobiliariaDeposito4());
		avaluoConstructor.setMatriculaInmobiliariaDeposito5(actualizado.getMatriculaInmobiliariaDeposito5());
		avaluoConstructor.setNumeroDeposito1(actualizado.getNumeroDeposito1());
		avaluoConstructor.setNumeroDeposito2(actualizado.getNumeroDeposito2());
		avaluoConstructor.setNumeroDeposito3(actualizado.getNumeroDeposito3());
		avaluoConstructor.setNumeroDeposito4(actualizado.getNumeroDeposito4());
		avaluoConstructor.setNumeroDeposito5(actualizado.getNumeroDeposito5());
		avaluoConstructor.setNumeroDePisos(actualizado.getNumeroDePisos());
		avaluoConstructor.setNumeroDeSotanos(actualizado.getNumeroDeSotanos());
		avaluoConstructor.setNumeroTotalDeGarajes(actualizado.getNumeroTotalDeGarajes());
		avaluoConstructor.setTotalCuposDeParqueo(actualizado.getTotalCuposDeParqueo());
		avaluoConstructor.setLoteProyectoM2(actualizado.getLoteProyectoM2());
		avaluoConstructor.setValorLote(actualizado.getValorLote());
		avaluoConstructor.setCostosDirectos(actualizado.getCostosDirectos());
		avaluoConstructor.setPorcentajeCostosDirectos(actualizado.getPorcentajeCostosDirectos());
		avaluoConstructor.setCostosIndirectos(actualizado.getCostosIndirectos());
		avaluoConstructor.setPorcentajeCostosIndirectos(actualizado.getPorcentajeCostosIndirectos());
		avaluoConstructor.setCostosDelProyecto(actualizado.getCostosDelProyecto());
		avaluoConstructor.setPorcentajeDeCostosDelProyecto(actualizado.getPorcentajeDeCostosDelProyecto());
		avaluoConstructor.setValorSolicitado(actualizado.getValorSolicitado());
		avaluoConstructor.setPorcentajeAFinanciar(actualizado.getPorcentajeAFinanciar());
		avaluoConstructor.setValorMaxAFinanciar(actualizado.getValorMaxAFinanciar());
		avaluoConstructor.setPorcentajeFinanciado(actualizado.getPorcentajeFinanciado());
		avaluoConstructor.setProgramacionEnMeses(actualizado.getProgramacionEnMeses());
		avaluoConstructor.setFactor(actualizado.getFactor());
		avaluoConstructor.setValorSolicitadoTotal(actualizado.getValorSolicitadoTotal());
		avaluoConstructor.setCostosFinancieros(actualizado.getCostosFinancieros());
		avaluoConstructor.setCostosTotalDelProyecto(actualizado.getCostosTotalDelProyecto());
		avaluoConstructor.setObservacionesInformeTecnico(actualizado.getObservacionesInformeTecnico());
		avaluoConstructor.setConceptoTecnico(actualizado.getConceptoTecnico());
		avaluoConstructor.setDescripcionAmpliacion(actualizado.getDescripcionAmpliacion());
		avaluoConstructor.setDescripcionDelProyecto(actualizado.getDescripcionDelProyecto());
		avaluoConstructor.setConsideracionesDelAvaluo(actualizado.getConsideracionesDelAvaluo());
		avaluoConstructor.setDocumentosConsultados(actualizado.getDocumentosConsultados());
		avaluoConstructor.setFechaAporteDeDocumentos(actualizado.getFechaAporteDeDocumentos());
		avaluoConstructor.setOtrosDocumentos(actualizado.getOtrosDocumentos());
		avaluoConstructor.setObservacionesDeTitulacion(actualizado.getObservacionesDeTitulacion());
		avaluoConstructor.setRemosionMasas(actualizado.getRemosionMasas());
		avaluoConstructor.setInundacion(actualizado.getInundacion());
		avaluoConstructor.setOrdenPublico(actualizado.getOrdenPublico());
		avaluoConstructor.setOtro(actualizado.getOtro());
		avaluoConstructor.setObsRemosionMasas(actualizado.getObsRemosionMasas());
		avaluoConstructor.setObsInundacion(actualizado.getObsInundacion());
		avaluoConstructor.setObsOrdenPublico(actualizado.getObsOrdenPublico());
		avaluoConstructor.setObsOtro(actualizado.getObsOtro());
		if (actualizado.getCronogramaObra() != null && actualizado.getCronogramaObra().getId() != null) {
			CronogramaObra cronogramaObra = cronogramaObraRepository.findOne(actualizado.getCronogramaObra().getId());
			if (cronogramaObra == null) {
				throw new CronogramaObraNotFoundException();
			}
			avaluoConstructor.setCronogramaObra(cronogramaObra);
		}
	}

	public void actualizarAvaluoRemate(AvaluoRemate avaluoRemate, AvaluoRemateDTO actualizado)
			throws EntidadNotFoundException, ClienteNotFoundException, UsuarioNotFoundException,
			FotografiaNotFoundException, AreaNotFoundException, TipoAvaluoNotFoundException,
			MetodoValuacionNotFoundException, FormatoInformeNotFoundException, DivipolaNotFoundException,
			GarajeNotFoundException {
		log.debug("Actualizando avaluo remate: {}", actualizado);
		actualizarAvaluo(avaluoRemate, actualizado);
		avaluoRemate.setNombreSecuestre(actualizado.getNombreSecuestre());
		avaluoRemate.setTelefonoSecuestre(actualizado.getTelefonoSecuestre());
		avaluoRemate.setCelularSecuestre(actualizado.getCelularSecuestre());
		avaluoRemate.setEmailSecuestre(actualizado.getEmailSecuestre());
		FormatoInformeHipotecarioDTO formatoInformeDTO = (FormatoInformeHipotecarioDTO) actualizado.getFormatoInforme();
		if (formatoInformeDTO == null) {
			return;
		}
		formatoInformeEnsamblador.actualizar(formatoInformeDTO.getId(), formatoInformeDTO);
	}

	public TipoInmueble crearTipoInmueble(TipoInmuebleDTO tipoInmuebleDTO) {
		TipoInmueble tipoInmueble = null;
		if (tipoInmuebleDTO != null) {
			tipoInmueble = new TipoInmueble();
			tipoInmueble.setId(tipoInmuebleDTO.getId());
			tipoInmueble.setNombre(tipoInmuebleDTO.getNombre());
		}
		return tipoInmueble;
	}

	public Asesor crearAsesor(AsesorDTO asesorDTO) throws EntidadNotFoundException, SucursalNotFoundException {
		Asesor asesor = null;
		if (asesorDTO != null) {
			asesor = new Asesor();
			asesor.setCelular(asesorDTO.getCelular());
			asesor.setCorreo(
					asesorDTO.getCorreo() != null && !asesorDTO.getCorreo().isEmpty() ? asesorDTO.getCorreo() : null);
			asesor.setId(asesorDTO.getId());
			asesor.setNombre(asesorDTO.getNombre());
			if (asesorDTO.getSucursal() != null) {
				Sucursal sucursal = sucursalRepository.findOne(asesorDTO.getSucursal().getId());
				if (sucursal == null) {
					throw new SucursalNotFoundException();
				}
				asesor.setSucursal(sucursal);
			} else {
				asesor.setSucursal(null);
			}
			asesor.setTelefono(asesorDTO.getTelefono());
		}
		return asesor;
	}

	public void actualizarAsesor(AsesorDTO asesorDTO)
			throws EntidadNotFoundException, ServidumbreNotFoundException, AsesorNotFoundException {
		Asesor asesor = asesorRepository.encontrarPorId(asesorDTO.getId());
		if (asesor == null) {
			throw new AsesorNotFoundException();
		}
		asesor.setCelular(asesorDTO.getCelular());
		asesor.setCorreo(asesorDTO.getCorreo());
		asesor.setNombre(asesorDTO.getNombre());
		Sucursal sucursal = sucursalRepository.findOne(asesorDTO.getSucursal().getId());
		if (sucursal == null) {
			throw new ServidumbreNotFoundException();
		}
		asesor.setSucursal(sucursal);
		asesor.setTelefono(asesorDTO.getTelefono());
	}

	public Bitacora crearBitacora(BitacoraDTO bitacoraDTO) {
		Bitacora bitacora = null;
		if (bitacoraDTO != null) {
			bitacora = new Bitacora();

			bitacora.setAvaluo(bitacoraDTO.getAvaluo());
			bitacora.setEstado(bitacoraDTO.getEstado().key());
			bitacora.setFecha(bitacoraDTO.getFecha());
			bitacora.setId(bitacoraDTO.getId());
			bitacora.setObservaciones(bitacoraDTO.getObservaciones());

			bitacora.setNotificacionAsesor(bitacoraDTO.getNotificacionAsesor());
			bitacora.setNotificacionCliente(bitacoraDTO.getNotificacionCliente());
			bitacora.setNotificacionCreador(bitacoraDTO.getNotificacionCreador());
			bitacora.setNotificacionPerito(bitacoraDTO.getNotificacionPerito());
			bitacora.setNotificacionPersonaRecibePerito(bitacoraDTO.getNotificacionPersonaRecibePerito());
			bitacora.setNotificacionRevisor(bitacoraDTO.getNotificacionRevisor());
			bitacora.setNotificacionSeguidor(bitacoraDTO.getNotificacionSeguidor());

			DocumentoIdentificacion documentoIdentificacion = new DocumentoIdentificacion();
			documentoIdentificacion.setNumeroDocumento(bitacoraDTO.getUsuario().getNumeroDocumento());
			documentoIdentificacion
					.setTipoDocumentoIdentificacion(bitacoraDTO.getUsuario().getTipoDocumentoIdentificacion());
			Usuario usuario = usuarioRepository.findOne(documentoIdentificacion);
			bitacora.setUsuario(usuario);
		}
		return bitacora;
	}

	public BitacoraDTO escribirDTO(Bitacora bitacora) {
		BitacoraDTO bitacoraDTO = null;
		if (bitacora != null) {
			bitacoraDTO = new BitacoraDTO();
			bitacoraDTO.setAvaluo(bitacora.getAvaluo());
			bitacoraDTO.setEstado(Estado.fromKey(bitacora.getEstado()));
			bitacoraDTO.setFecha(bitacora.getFecha());
			bitacoraDTO.setId(bitacora.getId());
			bitacoraDTO.setObservaciones(bitacora.getObservaciones());

			bitacoraDTO.setNotificacionAsesor(bitacora.getNotificacionAsesor());
			bitacoraDTO.setNotificacionCliente(bitacora.getNotificacionCliente());
			bitacoraDTO.setNotificacionCreador(bitacora.getNotificacionCreador());
			bitacoraDTO.setNotificacionPerito(bitacora.getNotificacionPerito());
			bitacoraDTO.setNotificacionPersonaRecibePerito(bitacora.getNotificacionPersonaRecibePerito());
			bitacoraDTO.setNotificacionRevisor(bitacora.getNotificacionRevisor());
			bitacoraDTO.setNotificacionSeguidor(bitacora.getNotificacionSeguidor());

			bitacoraDTO.setUsuario(usuarioEnsamblador.escribirDTO(bitacora.getUsuario()));
		}
		return bitacoraDTO;
	}

	public List<AvaluoConsultaDTO> encontrarAvaluosParaEstudioUrbanoPH(List<Object> avaluos) {
		List<AvaluoConsultaDTO> avaluosEncontrados = new ArrayList<AvaluoConsultaDTO>();
		for (Object avaluo : avaluos) {
			AvaluoConsultaDTO avaluoConsultaDTO = new AvaluoConsultaDTO();
			Object[] objects = (Object[]) avaluo;
			avaluoConsultaDTO.setResIdAvaluo((Integer) objects[0]);
			avaluoConsultaDTO.setEsUrbanoPH(true);
			avaluoConsultaDTO.setResTipoInmueble((Integer) objects[1]);
			avaluoConsultaDTO.setResUrbPHestrato((Integer) objects[2]);
			avaluoConsultaDTO.setResUrbPHbarrio(objects[3].toString());
			avaluoConsultaDTO.setResUrbPHdireccion(objects[4].toString());
			avaluoConsultaDTO.setResUrbPHciudad(objects[5].toString());
			avaluoConsultaDTO.setResUrbPHdepartamento(objects[6].toString());
			avaluoConsultaDTO.setResUrbPHlatitud((BigDecimal) objects[7]);
			avaluoConsultaDTO.setResUrbPHlongitud((BigDecimal) objects[8]);
			avaluoConsultaDTO.setResUrbPHestadoConstruccion((Integer) objects[9]);
			avaluoConsultaDTO.setResUrbPHedad((BigDecimal) objects[10]);
			avaluoConsultaDTO.setResUrbPHhabitaciones((Integer) objects[11]);
			avaluoConsultaDTO.setResUrbPHareaPrivada((BigDecimal) objects[12]);
			avaluoConsultaDTO.setResUrbPHvalorTotal((BigDecimal) objects[13]);
			// RoundingMode.HALF_UP sirve para "redondear" el BIGDECIMAL a 4 decimales,
			// puesto que la division no seria posible debido a la cantidad de decimales de
			// presicion
			avaluoConsultaDTO.setResUrbPHvalorMtrCuadrado(avaluoConsultaDTO.getResUrbPHvalorTotal()
					.divide(avaluoConsultaDTO.getResUrbPHareaPrivada(), 4, RoundingMode.HALF_UP));
			avaluosEncontrados.add(avaluoConsultaDTO);
		}
		return avaluosEncontrados;
	}

	// MODIFICAR POR NPH
	public List<AvaluoConsultaDTO> encontrarAvaluosParaEstudioUrbanoNPH(List<Object> avaluos) {
		List<AvaluoConsultaDTO> avaluosEncontrados = new ArrayList<AvaluoConsultaDTO>();
		for (Object avaluo : avaluos) {
			AvaluoConsultaDTO avaluoConsultaDTO = new AvaluoConsultaDTO();
			Object[] objects = (Object[]) avaluo;
			avaluoConsultaDTO.setResIdAvaluo((Integer) objects[0]);
			avaluoConsultaDTO.setEsUrbanoNPH(true);
			avaluoConsultaDTO.setResTipoInmueble((Integer) objects[1]);
			avaluoConsultaDTO.setResUrbNPHestrato((Integer) objects[2]);
			avaluoConsultaDTO.setResUrbNPHbarrio(objects[3].toString());
			avaluoConsultaDTO.setResUrbNPHdireccion(objects[4].toString());
			avaluoConsultaDTO.setResUrbNPHciudad(objects[5].toString());
			avaluoConsultaDTO.setResUrbNPHdepartamento(objects[6].toString());
			avaluoConsultaDTO.setResUrbNPHlatitud((BigDecimal) objects[7]);
			avaluoConsultaDTO.setResUrbNPHlongitud((BigDecimal) objects[8]);
			avaluoConsultaDTO.setResUrbNPHestadoConstruccion((Integer) objects[9]);
			avaluoConsultaDTO.setResUrbNPHedad((BigDecimal) objects[10]);
			avaluoConsultaDTO.setResUrbNPHhabitaciones((Integer) objects[11]);
			avaluoConsultaDTO.setResUrbNPHareaTotal((BigDecimal) objects[12]);
			avaluoConsultaDTO.setResUrbNPHvalorTotal((BigDecimal) objects[13]);
			// RoundingMode.HALF_UP sirve para "redondear" el BIGDECIMAL a 4 decimales,
			// puesto que la division no seria posible debido a la cantidad de decimales de
			// presicion
			avaluoConsultaDTO.setResUrbNPHvalorMtrCuadrado(avaluoConsultaDTO.getResUrbNPHvalorTotal()
					.divide(avaluoConsultaDTO.getResUrbNPHareaTotal(), 4, RoundingMode.HALF_UP));
			avaluosEncontrados.add(avaluoConsultaDTO);
		}
		return avaluosEncontrados;
	}

	// MODIFICAR POR RURAL
	public List<AvaluoConsultaDTO> encontrarAvaluosParaEstudioRural(List<Object> avaluos) {
		List<AvaluoConsultaDTO> avaluosEncontrados = new ArrayList<AvaluoConsultaDTO>();
		for (Object avaluo : avaluos) {
			AvaluoConsultaDTO avaluoConsultaDTO = new AvaluoConsultaDTO();
			Object[] objects = (Object[]) avaluo;
			avaluoConsultaDTO.setResIdAvaluo((Integer) objects[0]);
			avaluoConsultaDTO.setEsRural(true);
			avaluoConsultaDTO.setResTipoInmueble((Integer) objects[1]);
			avaluoConsultaDTO.setResRuralDireccion(objects[2].toString());
			avaluoConsultaDTO.setResRuralCiudad(objects[3].toString());
			avaluoConsultaDTO.setResRuralDepartamento(objects[4].toString());
			avaluoConsultaDTO.setResRurallatitud((BigDecimal) objects[5]);
			avaluoConsultaDTO.setResRurallongitud((BigDecimal) objects[6]);
			avaluoConsultaDTO.setResRuralVereda(objects[7].toString());
			avaluoConsultaDTO.setResRuralDistCascoUrbano((Integer) objects[8]);
			avaluoConsultaDTO.setResRuralAreaConst((BigDecimal) objects[9]);
			avaluoConsultaDTO.setResRuralvalorMtrCuadradoAreaConst(avaluoConsultaDTO.getResRuralvalorTotal()
					.divide(avaluoConsultaDTO.getResRuralAreaConst(), 4, RoundingMode.HALF_UP));
			avaluoConsultaDTO.setResRuralAreaCultivosAvaluables((BigDecimal) objects[10]);
			avaluoConsultaDTO.setResRuralvalorHtaCultivosAvaluables(avaluoConsultaDTO.getResRuralvalorTotal()
					.divide(avaluoConsultaDTO.getResRuralAreaCultivosAvaluables(), 4, RoundingMode.HALF_UP));
			avaluoConsultaDTO.setResRuralAreaTerreno((BigDecimal) objects[11]);
			avaluoConsultaDTO.setResRuralValorHtaTerreno(avaluoConsultaDTO.getResRuralvalorTotal()
					.divide(avaluoConsultaDTO.getResRuralAreaTerreno(), 4, RoundingMode.HALF_UP));
			avaluoConsultaDTO.setResRuralvalorTotal((BigDecimal) objects[12]);
			avaluosEncontrados.add(avaluoConsultaDTO);
		}
		return avaluosEncontrados;
	}

	public Modificacion crearModificacion(ModificacionDTO modificacionDTO) {
		Modificacion modificacion = null;

		if (modificacionDTO != null) {
			modificacion = new Modificacion();
			modificacion.setAnterior(modificacionDTO.getAnterior());
			modificacion.setAvaluo(modificacionDTO.getAvaluo());
			modificacion.setCampo(modificacionDTO.getCampo());
			modificacion.setFecha(modificacionDTO.getFecha());
			modificacion.setId(modificacionDTO.getId());
			modificacion.setNuevo(modificacionDTO.getNuevo());

			DocumentoIdentificacion documentoIdentificacion = new DocumentoIdentificacion();
			documentoIdentificacion.setNumeroDocumento(modificacionDTO.getUsuario().getNumeroDocumento());
			documentoIdentificacion
					.setTipoDocumentoIdentificacion(modificacionDTO.getUsuario().getTipoDocumentoIdentificacion());
			Usuario usuario = usuarioRepository.findOne(documentoIdentificacion);
			modificacion.setUsuario(usuario);
		}

		return modificacion;
	}

	public ModificacionDTO escribirDTO(Modificacion modificacion) {
		ModificacionDTO modificacionDTO = null;

		if (modificacion != null) {
			modificacionDTO = new ModificacionDTO();
			modificacionDTO.setAnterior(modificacionDTO.getAnterior());
			modificacionDTO.setAvaluo(modificacionDTO.getAvaluo());
			modificacionDTO.setCampo(modificacionDTO.getCampo());
			modificacionDTO.setFecha(modificacionDTO.getFecha());
			modificacionDTO.setId(modificacionDTO.getId());
			modificacionDTO.setNuevo(modificacionDTO.getNuevo());
			modificacionDTO.setUsuario(usuarioEnsamblador.escribirDTO(modificacion.getUsuario()));
		}

		return modificacionDTO;
	}
}
