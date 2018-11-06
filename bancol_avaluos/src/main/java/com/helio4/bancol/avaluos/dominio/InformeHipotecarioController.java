package com.helio4.bancol.avaluos.dominio;

import com.helio4.bancol.avaluos.dto.*;
import com.helio4.bancol.avaluos.servicio.datos.*;
import com.helio4.bancol.avaluos.servicio.excepciones.AvaluoNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.ClienteNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.EntidadNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.MetodoValuacionNotFoundException;
import com.helio4.bancol.avaluos.servicio.util.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class InformeHipotecarioController {

	private static Logger log = LoggerFactory.getLogger(InformeHipotecarioController.class);

	// private Map<Long, String> informesEnUso = new Hashtable<>();
	private final ReentrantLock lockOperacionGuardar = new ReentrantLock();

	@Autowired
	@Qualifier("repositoryAvaluoHipotecarioService")
	private AvaluoHipotecarioService avaluoHipotecarioService;

	@Autowired
	@Qualifier("repositoryClienteService")
	private ClienteService clienteService;

	@Autowired
	@Qualifier("repositoryAvaluoService")
	private AvaluoService avaluoService;

	@Autowired
	@Qualifier("repositoryUvrService")
	private UvrService uvrService;

	@Autowired
	private AvaluoController avaluoController;

	@Autowired
	@Qualifier("repositoryMetodoValuacionService")
	private MetodoValuacionService metodoValuacionService;

	@Autowired
	private GarajeService garajeService;

	@Autowired
	@Qualifier("repositoryAreaConstruccionAvaluoService")
	private AreaConstruccionAvaluoService areaConstruccionAvaluoService;

	@Autowired
	@Qualifier("repositoryAreaLoteAvaluoService")
	private AreaLoteAvaluoService areaLoteAvaluoService;

	public AvaluoHipotecarioDTO recuperarAvaluoHipotecario(AvaluoDTO avaluo) {
		AvaluoHipotecarioDTO avaluoHipotecarioDTO = null;
		if (avaluo != null) {
			avaluoHipotecarioDTO = avaluoHipotecarioService.encontrarAvaluoConAreas(avaluo.getId());
		}
		return avaluoHipotecarioDTO;
	}

	public EstadoAvaluoDTO recuperarUltimoEstado(AvaluoDTO avaluo) {
		return avaluoService.encontrarEstadoActual(avaluo.getId());
	}

	public HashMap<Integer, String> obtenerMetodologiaValuatoria(List<MetodoValuacionDTO> metodosDeValuacion) {
		HashMap<Integer, String> mapMetodologiaSeleccionada = new HashMap<Integer, String>();
		String metodosEncontrados = "";
		String justificacionMetodosEncontrados = "";
		if (metodosDeValuacion != null) {
			for (MetodoValuacionDTO metodoValuacionDTO : metodosDeValuacion) {
				metodosEncontrados += metodoValuacionDTO.getMetodoUsado().getKey() + ",";
				justificacionMetodosEncontrados += metodoValuacionDTO.getConceptoDelMetodo() + ". ";
			}
			switch (metodosDeValuacion.size()) {
			case 1:
				mapMetodologiaSeleccionada.put(metodosDeValuacion.iterator().next().getMetodoUsado().getKey(),
						metodosDeValuacion.iterator().next().getConceptoDelMetodo());
				break;
			case 2:
				if (metodosEncontrados.contains("21") && metodosEncontrados.contains("22")) {
					mapMetodologiaSeleccionada.put(25, justificacionMetodosEncontrados);
					break;
				} else if (metodosEncontrados.contains("21") && metodosEncontrados.contains("23")) {
					mapMetodologiaSeleccionada.put(26, justificacionMetodosEncontrados);
					break;
				} else if (metodosEncontrados.contains("21") && metodosEncontrados.contains("24")) {
					mapMetodologiaSeleccionada.put(41, justificacionMetodosEncontrados);
					break;
				} else if (metodosEncontrados.contains("22") && metodosEncontrados.contains("23")) {
					mapMetodologiaSeleccionada.put(42, justificacionMetodosEncontrados);
					break;
				} else if (metodosEncontrados.contains("22") && metodosEncontrados.contains("24")) {
					mapMetodologiaSeleccionada.put(43, justificacionMetodosEncontrados);
					break;
				} else if (metodosEncontrados.contains("23") && metodosEncontrados.contains("24")) {
					mapMetodologiaSeleccionada.put(44, justificacionMetodosEncontrados);
					break;
				}
				break;
			case 3:
				if (metodosEncontrados.contains("21") && metodosEncontrados.contains("22")
						&& metodosEncontrados.contains("23")) {
					mapMetodologiaSeleccionada.put(45, justificacionMetodosEncontrados);
					break;
				} else if (metodosEncontrados.contains("21") && metodosEncontrados.contains("23")
						&& metodosEncontrados.contains("24")) {
					mapMetodologiaSeleccionada.put(61, justificacionMetodosEncontrados);
					break;
				} else if (metodosEncontrados.contains("21") && metodosEncontrados.contains("22")
						&& metodosEncontrados.contains("24")) {
					mapMetodologiaSeleccionada.put(62, justificacionMetodosEncontrados);
					break;
				}
				break;
			case 4:
				if (metodosEncontrados.contains("21") && metodosEncontrados.contains("22")
						&& metodosEncontrados.contains("23") && metodosEncontrados.contains("24")) {
					mapMetodologiaSeleccionada.put(63, justificacionMetodosEncontrados);
					break;
				}
				break;
			default:
				break;
			}
		}
		return mapMetodologiaSeleccionada;
	}

	public UvrDTO getUvrActual() {
		return uvrService.encontrarUvrActual();
	}

	public UvrDTO getUvrPorFecha(Date date) {
		return uvrService.encontrarPor(date);
	}

	public Date fechaElaboracionInforme(AvaluoDTO avaluoDTO) {
		EstadoAvaluoDTO estadoInforme = avaluoService.encontrarEstadoActual(avaluoDTO.getId());
		if (estadoInforme == null || estadoInforme.getEstado() != Constantes.Estado.Enviado) {
			return null;
		}
		return estadoInforme.getFechaEstado();
	}

	public Integer actualizarVetusdez(Date fechaInforme, Integer anoDeConstruccion) {
		Integer vetusdez = 0;
		if (anoDeConstruccion != null && anoDeConstruccion != 0) {
			if (fechaInforme != null) {
				java.util.Calendar calendar = java.util.Calendar.getInstance();
				calendar.set(java.util.Calendar.YEAR, anoDeConstruccion);
				vetusdez = DateUtils.getDiferenciaEnAnos(calendar.getTime(), fechaInforme);
			} else {
				java.util.Calendar calendar = java.util.Calendar.getInstance();
				calendar.set(java.util.Calendar.YEAR, anoDeConstruccion);
				vetusdez = DateUtils.getDiferenciaEnAnos(calendar.getTime(), new Date(System.currentTimeMillis()));
			}
		} else {
			return null;
		}
		return vetusdez;
	}

	/**
	 * Calcula el valor total basado en las areas
	 *
	 * @param listaAreas
	 *            Lista actual de areas del avaluo
	 */
	public BigDecimal calcularValorTotal(Collection<AreaDTO> listaAreas) {
		BigDecimal valorTotalAvaluo = BigDecimal.ZERO;

		for (AreaDTO area : listaAreas) {
			if (area.getValorTotal() != null) {
				valorTotalAvaluo = valorTotalAvaluo.add(area.getValorTotal());
			}
		}
		return valorTotalAvaluo;
	}

	/**
	 * Calcula el valor en UVR del avaluo a partir del valor UVR actual y el
	 * valor total
	 *
	 * @param valorUvr
	 * @param valorTotalAvaluo
	 * @return
	 */
	public BigDecimal calcularValorEnUVR(BigDecimal valorUvr,
			BigDecimal valorTotalAvaluo) {
		if (valorUvr != null && !BigDecimal.ZERO.equals(valorUvr)) {
			return valorTotalAvaluo.divide(valorUvr, 2, RoundingMode.HALF_UP);
		} /*
			 * else { log.error("El valor del uvr es nulo!"); }
			 */
		return BigDecimal.ZERO;
	}

	public boolean confirmarPago(AvaluoDTO avaluoHipotecarioDTO, UsuarioDTO usuario) {
		return avaluoService.confirmarPago(avaluoHipotecarioDTO, usuario);
	}

	public boolean confirmarDocumentosCompletos(AvaluoDTO avaluoHipotecarioDTO, UsuarioDTO usuario) {
		return avaluoService.confirmarDocumentosCompletos(avaluoHipotecarioDTO, usuario);
	}

	public boolean enviarNotificacionHonorarios(AvaluoDTO modificado, AvaluoDTO original, UsuarioDTO usuario)
			throws AvaluoNotFoundException, ClienteNotFoundException {
		guardarSinEnviar(modificado);
		return avaluoService.notificarHonorarios(modificado, usuario);
	}

	/**
	 * Calcula el valor asegurable a partir del porcentaje del valor asegurable
	 * de la entidad y el valor total de avaluo
	 *
	 * @param entidadDTO
	 * @return
	 */
	public BigDecimal calcularValorAsegurable(EntidadDTO entidadDTO, BigDecimal valorBase) {
		if (valorBase != null) {
			if (entidadDTO.getPorcentajeValorAsegurable() != null) {
				return valorBase.multiply(new BigDecimal(entidadDTO.getPorcentajeValorAsegurable()));
			} else {
				log.error("El valor del porcentaje del valor asegurable de la entidad es nulo");
			}
		}
		return BigDecimal.ZERO;
	}

	public AvaluoDTO enviarAvaluo(AvaluoDTO modificado, AvaluoDTO original, UsuarioDTO usuario)
			throws AvaluoNotFoundException, ClienteNotFoundException {
		guardarSinEnviar(modificado);
		return avaluoService.enviar(modificado, usuario) ? modificado : null;
	}

	public boolean enviarAvaluo(AvaluoDTO avaluoDTO, UsuarioDTO usuario) {
		return avaluoService.enviar(avaluoDTO, usuario);
	}

	public AvaluoDTO guardarSinEnviar(AvaluoDTO modificado) throws AvaluoNotFoundException, ClienteNotFoundException {
		lockOperacionGuardar.lock();
		try {
			AvaluoDTO avaluoHipotecario = null;
			FormatoInformeHipotecarioDTO informeHipotecarioDTO = (FormatoInformeHipotecarioDTO) modificado
					.getFormatoInforme();

			Date fechaInforme = new Date(System.currentTimeMillis());
			UvrDTO uvrFecha = getUvrPorFecha(fechaInforme);
			if (uvrFecha != null) {
				modificado.setValorUvr(uvrFecha.getValor());
			} else {
				return null;
			}

			if (modificado.getAreas() != null) {
				modificado.setValorTotalAvaluo(calcularValorTotal(modificado.getAreas()));

				avaluoController.actualizarAreas(modificado.getId(), modificado.getAreas());
			}

			if (informeHipotecarioDTO != null) {
				informeHipotecarioDTO
						.setVetustez(actualizarVetusdez(fechaInforme, informeHipotecarioDTO.getAnoDeConstruccion()));
				HashMap<Integer, String> hashMap = obtenerMetodologiaValuatoria(modificado.getMetodosValuacion());
				if (hashMap != null) {
					hashMap.keySet().forEach(informeHipotecarioDTO::setMetodoDeValuacion);
					hashMap.values().forEach(informeHipotecarioDTO::setJustificacionDeMetodologia);
				}
			}

			if (modificado.getMatriculas() != null) {

				avaluoController.actualizarMatriculas(modificado.getId(), modificado.getMatriculas());
			}
			if (modificado.getMetodosValuacion() != null) {
				actualizarMetodosValuacion(modificado.getId(), modificado.getMetodosValuacion());
			}

			if (modificado.getGarajes() != null) {
				this.actulizarGarjes(modificado.getId(), modificado.getGarajes());
			}
			ClienteDTO cliente = null;
			try {
				cliente = clienteService.actualizar(modificado.getCliente());
			} catch (EntidadNotFoundException e) {
				e.printStackTrace();
			}

			if (modificado.getAreaConstruccionAvaluoDTO() != null) {
				areaConstruccionAvaluoService.crear(modificado.getAreaConstruccionAvaluoDTO());
			}
			if (modificado.getAreaLoteAvaluoDTO() != null) {
				areaLoteAvaluoService.crear(modificado.getAreaLoteAvaluoDTO());
			}

			avaluoHipotecario = avaluoService.actualizar(modificado);
			avaluoHipotecario.setCliente(cliente);
			return avaluoHipotecario;
		} finally {
			lockOperacionGuardar.unlock();
		}
	}

	@Transactional
	private void actualizarMetodosValuacion(Long avaluoId, List<MetodoValuacionDTO> metodosValuacion) {
		// Borra todos los métodos de valuación
		// metodoValuacionService.eliminarPor(avaluoId);

		List<MetodoValuacionDTO> metodosActuales = metodoValuacionService.encontrarPorAvaluoId(avaluoId);
		List<MetodoValuacionDTO> metodosActualesEliminar = new ArrayList<>(metodosActuales);
		for (MetodoValuacionDTO metodoDeValuacion : metodosValuacion) {

			boolean existe = false;

			for (MetodoValuacionDTO metodoDeValuacionActual : metodosActuales) {

				if (metodoDeValuacionActual.getMetodoUsado().getKey() == metodoDeValuacion.getMetodoUsado().getKey()) {
					try {
						metodoDeValuacion.setId(metodoDeValuacionActual.getId());
						metodoValuacionService.actualizar(metodoDeValuacion);
						metodosActualesEliminar.remove(metodoDeValuacionActual);
					} catch (MetodoValuacionNotFoundException e) {
						e.printStackTrace();
					}

					existe = true;
				}

			}

			if (!existe) {
				metodoValuacionService.crear(metodoDeValuacion);
			}

		}

		for (MetodoValuacionDTO metodoValuacionDTO : metodosActualesEliminar) {
			try {
				metodoValuacionService.eliminar(metodoValuacionDTO);
			} catch (MetodoValuacionNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	public void borrarMetodoValuacion(MetodoValuacionDTO metodoValuacionDTO) {
		try {
			if (metodoValuacionDTO.getId() != null) {
				metodoValuacionService.eliminar(metodoValuacionDTO);
			}
		} catch (MetodoValuacionNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void actulizarGarjes(Long avaluoId, List<GarajeDTO> nuevos) {
		// Borra los garajes en base de datos
		this.garajeService.eliminarPor(avaluoId);
		for (GarajeDTO garajeDTO : nuevos) {
			try {
				this.garajeService.crear(garajeDTO);
			} catch (AvaluoNotFoundException e) {
				log.error("No se encontró el avaluo para guardar el garaje {}", garajeDTO, e);
			}
		}

	}

	public boolean enviarAvaluoAComite(AvaluoDTO avaluoHipotecarioDTO, UsuarioDTO usuario) {
		return avaluoService.enviarAComite(avaluoHipotecarioDTO, usuario);
	}

	public AvaluoDTO encontrarAvaluo(Long avaluoId) {
		return avaluoService.encontrarPorId(avaluoId);
	}

	public AvaluoDTO encontrarAvaluoPorIdTinsa(Long avaluoId) {
		return avaluoService.encontrarPorIdTinsa(avaluoId);
	}

	/*
	 * public boolean bloquearInforme(String sessionId, Long avaluoId) { String
	 * bloqueado = informesEnUso.get(avaluoId); if (bloqueado == null) {
	 * informesEnUso.put(avaluoId, sessionId); log.info("informesEnUso {}",
	 * informesEnUso); return true; } return Objects.equals(bloqueado,
	 * sessionId); }
	 * 
	 * public void desBloquearInforme(Long avaluoId) {
	 * informesEnUso.remove(avaluoId); log.info("informesEnUso {}",
	 * informesEnUso); }
	 * 
	 * public void desBloquearInformePorSesion(String sessionId) {
	 * deleteItem(informesEnUso, sessionId); log.info("informesEnUso {}",
	 * informesEnUso); } private void deleteItem(Map<Long, String> map, String
	 * item) { Iterator<Map.Entry<Long, String>> it = map.entrySet().iterator();
	 * while (it.hasNext()) { Map.Entry<Long, String> entry = it.next();
	 * if(entry.getValue().equals(item)) { it.remove(); } } }
	 */
}
