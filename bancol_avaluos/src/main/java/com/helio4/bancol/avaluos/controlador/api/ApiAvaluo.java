package com.helio4.bancol.avaluos.controlador.api;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.helio4.bancol.avaluos.dominio.AsignarPeritoController;
import com.helio4.bancol.avaluos.dominio.AvaluoController;
import com.helio4.bancol.avaluos.dominio.EstadoAvaluoController;
import com.helio4.bancol.avaluos.dominio.InformeHipotecarioController;
import com.helio4.bancol.avaluos.dominio.ListadoAvaluosController;
import com.helio4.bancol.avaluos.dominio.SolicitudUtils;
import com.helio4.bancol.avaluos.dto.AvaluoDTO;
import com.helio4.bancol.avaluos.dto.ClienteDTO;
import com.helio4.bancol.avaluos.dto.DivipolaDTO;
import com.helio4.bancol.avaluos.dto.EntidadDTO;
import com.helio4.bancol.avaluos.dto.EstadoAvaluoDTO;
import com.helio4.bancol.avaluos.dto.FormatoInformeHipotecarioDTO;
import com.helio4.bancol.avaluos.dto.ObjetoAvaluoDTO;
import com.helio4.bancol.avaluos.dto.TipoAvaluoDTO;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;
import com.helio4.bancol.avaluos.exception.GeneralFacesMessageException;
import com.helio4.bancol.avaluos.modelo.Motivo;
import com.helio4.bancol.avaluos.modelo.api.AvaluoRequest;
import com.helio4.bancol.avaluos.modelo.api.ResponseAvaluoApi;
import com.helio4.bancol.avaluos.servicio.datos.ClienteService;
import com.helio4.bancol.avaluos.servicio.datos.DivipolaService;
import com.helio4.bancol.avaluos.servicio.datos.EntidadService;
import com.helio4.bancol.avaluos.servicio.datos.ListaService;
import com.helio4.bancol.avaluos.servicio.datos.MotivoService;
import com.helio4.bancol.avaluos.servicio.datos.SegmentoService;
import com.helio4.bancol.avaluos.servicio.datos.TipoAvaluoService;
import com.helio4.bancol.avaluos.servicio.datos.UsuarioService;
import com.helio4.bancol.avaluos.servicio.excepciones.ClienteNotFoundException;
import com.helio4.bancol.avaluos.servicio.excepciones.EntidadNotFoundException;
import com.helio4.bancol.avaluos.servicio.util.Constantes;
import com.helio4.bancol.avaluos.servicio.util.Utils;

@RestController
public class ApiAvaluo extends ApiGeneral{

	private static Logger log = LoggerFactory.getLogger( ApiAvaluo.class );

	
	private AvaluoDTO avaluoDTO;

	private UsuarioDTO peritoAsociado;

	@Autowired
	private SolicitudUtils solicitudUtils;

	@Autowired
	@Qualifier("repositoryEntidadService")
	private EntidadService entidadService;

	@Autowired
	@Qualifier("repositorySegmentoService")
	private SegmentoService segmentoService;

	@Autowired
	@Qualifier("repositoryTipoAvaluoService")
	private TipoAvaluoService tipoAvaluoService;

	@Autowired
	private ListaService listaService;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	@Qualifier("repositoryDivipolaService")
	private DivipolaService divipolaService;

	@Autowired
	@Qualifier("repositoryUsuarioService")
	private UsuarioService usuarioService;

	@Autowired
	private AvaluoController avaluoController;

	@Autowired
	private AsignarPeritoController asignarPeritoController;

	@Autowired
	private InformeHipotecarioController informeHipotecarioController;
	
	@Autowired
	private EstadoAvaluoController estadoAvaluoController;

	@Autowired
	private MotivoService motivoService;
	
	@Autowired
	private ListadoAvaluosController listadoAvaluosController;

	@RequestMapping(value = "/avaluo", method = RequestMethod.POST)
	@ResponseBody
	public ResponseAvaluoApi getTest(HttpServletRequest request, @RequestBody AvaluoRequest avaluoRequest,
			@RequestHeader(value = "user-authentication") String userAuthentication) {

		log.info("primera peticion: "+avaluoRequest.toString());
		log.info("primer header: "+userAuthentication.toString());
		
		init();

		ResponseAvaluoApi response = validarInformacionUsuario(userAuthentication);

		if (response != null) {
			return response;
		}

		// validar accion
		if (Utils.isNull(avaluoRequest.getAccion())) {
			return getErrorResponse(-1L,
					"El parametro de acción es obligatorio.  Valores soportados: ['REGISTRAR', 'ACTUALIZAR']",
					avaluoRequest.toString());
		}
		
		if(avaluoRequest.getCodigoExterno()!= null){
			avaluoRequest.setCodigoExterno(avaluoRequest.getCodigoExterno().trim());
		}

		avaluoDTO.setSoloAvaluo(true);
		this.avaluoDTO.setTipoAvaluo(tipoAvaluoService.encontrarPorId(1L));
		this.avaluoDTO.setObjetoDelAvaluo(listaService.encontrarObjetoAvaluoPorId(1L));
		this.avaluoDTO.setTipoCredito("Vivienda");

		// validar entidad
		response = validarEntidad(avaluoRequest);

		if (response != null) {
			return response;
		}

		if (Constantes.ACCION_REGISTRAR.equals(avaluoRequest.getAccion())) {
			return registrarAvaluo(avaluoRequest);
		} else if (Constantes.ACCION_ACTUALIZAR.equals(avaluoRequest.getAccion())) {
			return actualizarAvaluo(avaluoRequest);
		} else {
			return getErrorResponse(-2L, "Acción no soportada. Valores soportados: ['REGISTRAR', 'ACTUALIZAR']",
					avaluoRequest.toString());
		}

	}

	public ResponseAvaluoApi avaluoSoloAvaluo(AvaluoRequest avaluoRequest) {

		

		try {

			if (Constantes.ACCION_REGISTRAR.equals(avaluoRequest.getAccion())) {
				ResponseAvaluoApi response = validarCampos(avaluoRequest);

				if (response != null) {
					return response;
				}
				
				log.info("Crear avaluo avaluo: "+avaluoDTO.toString());
				
				avaluoDTO = avaluoController.crearSolicitud(avaluoDTO, usuario);

				if (avaluoDTO != null) {
					avaluoDTO.setFormatoInforme(new FormatoInformeHipotecarioDTO(avaluoDTO.getId()));
					avaluoController.crearFormatoInforme(avaluoDTO);
					// asignar perito
					if (!asignarPeritoController.asignarPerito(avaluoDTO, peritoAsociado, usuario)) {
						return getErrorResponse(-31L, "Error asignando avaluo.", avaluoRequest.toString());
					}

				} else {
					return getErrorResponse(-32L, "Error creando avaluo.", avaluoRequest.toString());
				}

				return getResponse(200L, "Avaluo creado exitosamente",
						"ID avaluo: " + avaluoDTO.getId() + ", codigo externo: " + avaluoDTO.getCodigoExterno());
			} else if (Constantes.ACCION_ACTUALIZAR.equals(avaluoRequest.getAccion())) {
				
				ResponseAvaluoApi response = validarCampos(avaluoRequest);

				if (response != null) {
					return response;
				}

				log.info("actualizar avaluo avaluo: "+avaluoDTO.toString());
				actualizarCliente(avaluoDTO);
				avaluoDTO = informeHipotecarioController.guardarSinEnviar(avaluoDTO);

				if (avaluoDTO != null) {

					// asignar perito
					if (!asignarPeritoController.actualizarPerito(avaluoDTO, peritoAsociado, usuario)) {
						return getErrorResponse(-31L, "Error asignando avaluo.", avaluoRequest.toString());
					}

				} else {
					return getErrorResponse(-33L, "Error Actualizando avaluo.", avaluoRequest.toString());
				}

				return getResponse(200L, "Avaluo actualizado exitosamente",
						"ID avaluo: " + avaluoDTO.getId() + ", codigo externo: " + avaluoDTO.getCodigoExterno());
			} else if (Constantes.ACCION_ACTUALIZAR_ID_SISGEN.equals(avaluoRequest.getAccion())) {
				
				log.info("actualizar id sisgen  avaluo: "+avaluoDTO.toString());
				
				EstadoAvaluoDTO estado = estadoAvaluoController.buscarEstadoActualPorCodigoExterno(avaluoDTO.getCodigoExterno(), avaluoRequest.getEntidad().getCodigoEntidad().intValue());
				
				if (estado.getEstado().key() == 8 || estado.getEstado().key() == 9 || estado.getEstado().key() == 10 || estado.getEstado().key() == 11) {
					
					avaluoDTO = informeHipotecarioController.guardarSinEnviar(avaluoDTO);
					
					return getResponse(200L, "Id sisgen actualizado exitosamente",
							"Estado de avaluo: "+estado.getEstado().value()+", ID avaluo: " + avaluoDTO.getId() + ", codigo externo: " + avaluoDTO.getCodigoExterno());
					
				}else {

					listadoAvaluosController.enProcesoAvaluo(avaluoDTO, usuario);
					
					ResponseAvaluoApi response = validarCampos(avaluoRequest);

					if (response != null) {
						return response;
					}
					avaluoDTO = informeHipotecarioController.guardarSinEnviar(avaluoDTO);
					
					// asignar perito
					if (!asignarPeritoController.actualizarPerito(avaluoDTO, peritoAsociado, usuario)) {
						return getErrorResponse(-31L, "Error asignando avaluo.", avaluoRequest.toString());
					}
					
					
					return getResponse(200L, "Id sisgen actualizado exitosamente",
							"ID avaluo: " + avaluoDTO.getId() + ", codigo externo: " + avaluoDTO.getCodigoExterno());
				}
				
			}else {
				return getErrorResponse(-2L, "Acción no soportada. Valores soportados: ['REGISTRAR', 'ACTUALIZAR']",
						avaluoRequest.toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
			return getErrorResponse(-11L, e.getMessage(), avaluoRequest.toString());
		}

	}

	private void actualizarCliente(AvaluoDTO avaluoDTO2) {
		Integer tipoDocumentoIdentificacion = avaluoDTO.getCliente().getTipoDocumentoIdentificacion();
		Long numeroDocumento = avaluoDTO.getCliente().getNumeroDocumento();
		if (numeroDocumento != null) {
			if (clienteService.encontrarPorNumeroDocumento(tipoDocumentoIdentificacion, numeroDocumento) == null) {
				try {
					clienteService.crear(avaluoDTO.getCliente());
				} catch (EntidadNotFoundException e) {
					e.printStackTrace();
				}
			} else {
				try {
					clienteService.actualizar(avaluoDTO.getCliente());
				} catch (ClienteNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (EntidadNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public ResponseAvaluoApi validarCampos(AvaluoRequest avaluoRequest) {
		// validar cliente
		ResponseAvaluoApi response = validarCliente(avaluoRequest);

		if (response != null) {
			return response;
		}

		// validar ciudad
		response = validarCiudad(avaluoRequest);

		if (response != null) {
			return response;
		}

		// validar datos del perito
		response = validarPerito(avaluoRequest);

		if (response != null) {
			return response;
		}
		return null;
	}

	public ResponseAvaluoApi validarCiudad(AvaluoRequest avaluoRequest) {
		// validar departamento
		if (Utils.isNull(avaluoRequest.getDepartamentoId())) {
			return getErrorResponse(-23L, "El codigo del departamento es obligatorio", avaluoRequest.toString());
		}

		// validar municipio
		if (Utils.isNull(avaluoRequest.getMunicipioId())) {
			return getErrorResponse(-24L, "El codigo de municipio es obligatorio", avaluoRequest.toString());
		}

		if (avaluoRequest.getDepartamentoId().equals(33) && avaluoRequest.getMunicipioId().equals(1124)) {
			avaluoRequest.setDepartamentoId(15);
			avaluoRequest.setMunicipioId(506);
		} else if (avaluoRequest.getDepartamentoId().equals(28L) && avaluoRequest.getMunicipioId().equals(1118L)) {
			avaluoRequest.setDepartamentoId(28);
			avaluoRequest.setMunicipioId(974);
		}

		DivipolaDTO divipola = divipolaService.encontrarPorIdentificacionTinsa(avaluoRequest.getDepartamentoId(),
				avaluoRequest.getMunicipioId());

		if (divipola != null) {
			avaluoDTO.setDivipola(divipola);
		} else {
			return getErrorResponse(-25L, "El municipio no existe: [departamentoId: "
					+ avaluoRequest.getDepartamentoId() + " ; municipio: " + avaluoRequest.getMunicipioId() + "]",
					avaluoRequest.toString());
		}

		return null;
	}

	public ResponseAvaluoApi validarEntidad(AvaluoRequest avaluoRequest) {

		// validar entidad
		if (Utils.isNull(avaluoRequest.getEntidad())) {
			return getErrorResponse(-3L, "La informacion de la entidad es obligatoria", avaluoRequest.toString());
		}

		if (Utils.isNull(avaluoRequest.getEntidad().getCodigoEntidad())) {
			return getErrorResponse(-4L, "El codigo de la entidad es obligatorio", avaluoRequest.toString());
		}

		EntidadDTO entidad = entidadService
				.encontrarPorCodigoTinsa(avaluoRequest.getEntidad().getCodigoEntidad().intValue());

		if (Utils.isNull(entidad)) {
			return getErrorResponse(-5L,
					"Entidad con codigo: " + avaluoRequest.getEntidad().getCodigoEntidad() + " No existe.",
					avaluoRequest.toString());
		}

		if (Utils.isNull(avaluoRequest.getEntidad().getCodigoMotivo())) {
			return getErrorResponse(-6L, "El codigo del motivo es obligatorio", avaluoRequest.toString());
		}

		Motivo motivo = motivoService.getMotivosById(avaluoRequest.getEntidad().getCodigoMotivo());

		if (Utils.isNull(motivo)) {
			return getErrorResponse(-7L, "El codigo del motivo no existe", avaluoRequest.toString());
		}

		if (!motivo.getEntidad().getId().equals(entidad.getId())) {
			return getErrorResponse(-8L, "El codigo del motivo no corresponde a la entidad seleccionada",
					avaluoRequest.toString());
		}

		avaluoDTO.setMotivo(motivo.getCodigo().intValue());
		avaluoDTO.setMotivoAux(motivo);
		avaluoDTO.setEntidad(entidad);

		return null;
	}

	public ResponseAvaluoApi validarCliente(AvaluoRequest avaluoRequest) {

		// validar objeto cliente
		if (Utils.isNull(avaluoRequest.getCliente())) {
			return getErrorResponse(-15L, "Los datos personales son obligatorios", avaluoRequest.toString());
		}

		// validar numero de documento
		if (Utils.isNull(avaluoRequest.getCliente().getNumeroDocumento())) {
			return getErrorResponse(-18L, "El numero de documento es obligatorio", avaluoRequest.toString());
		}

		Long numeroDocumento = avaluoRequest.getCliente().getNumeroDocumento();

		ClienteDTO cliente = clienteService.encontrarPorNumeroDocumento(numeroDocumento);

		if (Utils.isNotNull(cliente)) {
			
			this.avaluoDTO.setCliente(cliente);
			this.avaluoDTO.getCliente().setTipoDocumentoIdentificacion(cliente.getTipoDocumentoIdentificacion());
		}else {
			
			this.avaluoDTO.getCliente().setTipoDocumentoIdentificacion(Constantes.TipoDocumento.CC.key());
		}

		this.avaluoDTO.getCliente().setNumeroDocumento(numeroDocumento);

		// validar primer nombre
		if (Utils.isNull(avaluoRequest.getCliente().getNombre())) {
			return getErrorResponse(-20L, "El nombre es un campo obligatorio", avaluoRequest.toString());
		}

		if (avaluoRequest.getCliente().getNombre().split(" ").length < 2) {
			return getErrorResponse(-21L,
					"El nombre debe contener al menos el primer nombre y primer apellido del cliente.",
					avaluoRequest.toString());
		}

		descomponerNombre(avaluoRequest.getCliente().getNombre());

		return null;
	}

	public ResponseAvaluoApi validarPerito(AvaluoRequest avaluoRequest) {

		// validar objeto perito
		if (Utils.isNull(avaluoRequest.getPerito())) {
			return getErrorResponse(-26L, "Los datos del perito son obligatorios", avaluoRequest.toString());
		}

		// validar numero de documento
		if (Utils.isNull(avaluoRequest.getPerito().getNumeroDocumento())) {
			return getErrorResponse(-29L, "El numero de documento del perito es obligatorio", avaluoRequest.toString());
		}

		Long numeroDocumento = avaluoRequest.getPerito().getNumeroDocumento();

		UsuarioDTO usuarioPerito = usuarioService.buscarPorNumeroDocumento(numeroDocumento);

		if (Utils.isNull(usuarioPerito)) {
			return getErrorResponse(-30L, "El perito con  numero de identificación: "
					+ avaluoRequest.getPerito().getNumeroDocumento() + " no existe.", avaluoRequest.toString());
		} else {
			this.peritoAsociado = usuarioPerito;
		}

		return null;
	}

	private void descomponerNombre(String nombre) {

		String palabras[] = nombre.split(" ");

		if (palabras.length == 2) {
			this.avaluoDTO.getCliente().setPrimerNombre(palabras[0]);
			this.avaluoDTO.getCliente().setPrimerApellido(palabras[1]);
		} else if (palabras.length == 3) {
			this.avaluoDTO.getCliente().setPrimerNombre(palabras[0]);
			this.avaluoDTO.getCliente().setPrimerApellido(palabras[1]);
			this.avaluoDTO.getCliente().setSegundoApellido(palabras[2]);
		} else if (palabras.length == 4) {
			this.avaluoDTO.getCliente().setPrimerNombre(palabras[0]);
			this.avaluoDTO.getCliente().setSegundoNombre(palabras[1]);
			this.avaluoDTO.getCliente().setPrimerApellido(palabras[2]);
			this.avaluoDTO.getCliente().setSegundoApellido(palabras[3]);
		} else {
			this.avaluoDTO.getCliente().setPrimerNombre(palabras[0]);
			this.avaluoDTO.getCliente().setSegundoNombre(palabras[1]);
			this.avaluoDTO.getCliente().setPrimerApellido(palabras[2]);
			String restante[] = Arrays.copyOfRange(palabras, 2, palabras.length);
			this.avaluoDTO.getCliente().setSegundoApellido(String.join(" ", restante));
		}

		this.avaluoDTO.getCliente().setNombreCompleto(nombre);

	}

	public ResponseAvaluoApi registrarAvaluo(AvaluoRequest avaluoRequest) {

		log.info("registrar avaluo method");
		
		try {

			// validar id sisgen
			if (Utils.isNull(avaluoRequest.getIdAvaluoSisgen())) {
				return getErrorResponse(-9L, "El ID de SISGEN es obligatorio.", avaluoRequest.toString());
			}

			solicitudUtils.validarIdSisgem(avaluoRequest.getIdAvaluoSisgen());
			avaluoDTO.setAvaluoSisgenId(avaluoRequest.getIdAvaluoSisgen());

			// valida si existe un avaluo con codigo externo y numero de cliente pero sin id
			// tinsa
			AvaluoDTO avaluo = avaluoController.encontrarAvaluoPorCodigoExternoAndNumeroDocumentoCliente(
					avaluoRequest.getCodigoExterno(), avaluoDTO.getEntidad().getId(),
					avaluoRequest.getCliente().getNumeroDocumento());

			if (avaluo != null) {

				avaluoDTO.setId(avaluo.getId());

				avaluo.setCodigoExterno(avaluoRequest.getCodigoExterno());
				avaluo.setAvaluoSisgenId(avaluoRequest.getIdAvaluoSisgen());

				avaluoRequest.setAccion(Constantes.ACCION_ACTUALIZAR_ID_SISGEN);

				avaluoDTO = avaluo;

			} else {

				// validar codigo externo
				if (Utils.isNull(avaluoRequest.getCodigoExterno())) {
					return getErrorResponse(-10L, "El codigo externo es obligatorio.", avaluoRequest.toString());
				}

				avaluoDTO.setCodigoExterno(avaluoRequest.getCodigoExterno());
				solicitudUtils.validarCodigoExterno(avaluoDTO);

			}

		} catch (GeneralFacesMessageException e) {
			return getErrorResponse(-11L, e.getMessage(), avaluoRequest.toString());
		}

		return avaluoSoloAvaluo(avaluoRequest);
	}

	public ResponseAvaluoApi actualizarAvaluo(AvaluoRequest avaluoRequest) {

		log.info("actualizar avaluo method");
		
		// validar id sisgen
		if (Utils.isNull(avaluoRequest.getIdAvaluoSisgen())) {
			return getErrorResponse(-9L, "El ID de SISGEN es obligatorio.", avaluoRequest.toString());
		}

		/*if (!avaluoController.verificarDuplicadoIdSisgen(avaluoRequest.getIdAvaluoSisgen())) {
			return getErrorResponse(-13L,
					"El avaluo con el codigo sisgen: " + avaluoRequest.getIdAvaluoSisgen() + " no existe.",
					avaluoRequest.toString());
		}*/

		// validar codigo externo
		if (Utils.isNull(avaluoRequest.getCodigoExterno())) {
			return getErrorResponse(-10L, "El codigo externo es obligatorio.", avaluoRequest.toString());
		}

		AvaluoDTO avaluoCodigoExterno = avaluoController
				.encontrarAvaluoPorCodigoExterno(avaluoRequest.getCodigoExterno(), avaluoDTO.getEntidad().getId());

		if (avaluoCodigoExterno != null && avaluoCodigoExterno.getAvaluoSisgenId() != null
				&& !avaluoRequest.getIdAvaluoSisgen().equals(avaluoCodigoExterno.getAvaluoSisgenId())) {
			return getErrorResponse(-14L, "El codigo externo " + avaluoRequest.getCodigoExterno()
					+ " ya esta asignado a un avalúo diferente.", avaluoRequest.toString());
		}

		avaluoDTO.setCodigoExterno(avaluoRequest.getCodigoExterno());

		AvaluoDTO avaluoConIdSisgen = informeHipotecarioController
				.encontrarAvaluoPorIdTinsa(avaluoRequest.getIdAvaluoSisgen());
		
		if(avaluoConIdSisgen==null){
			
			avaluoConIdSisgen = avaluoController.encontrarAvaluoPorCodigoExternoAndNumeroDocumentoCliente(
					avaluoRequest.getCodigoExterno(), avaluoDTO.getEntidad().getId(),
					avaluoRequest.getCliente().getNumeroDocumento());
			
			if(avaluoConIdSisgen==null){
				
				return getErrorResponse(-13L,
						"El avaluo con el codigo sisgen: " + avaluoRequest.getIdAvaluoSisgen() + " no existe.",
						avaluoRequest.toString());
			}
		}else{
			AvaluoDTO avaluoConEntidadCodigoExternoYDocumentoCliente = avaluoController.encontrarAvaluoPorCodigoExternoAndNumeroDocumentoCliente(
					avaluoRequest.getCodigoExterno(), avaluoDTO.getEntidad().getId(),
					avaluoRequest.getCliente().getNumeroDocumento());
			
			if(avaluoConEntidadCodigoExternoYDocumentoCliente != null && !avaluoConIdSisgen.getId().equals(avaluoConEntidadCodigoExternoYDocumentoCliente.getId())){
				return getErrorResponse(-33L, "Ya existe otro avalúo con la información a actualizar", avaluoRequest.toString());
			}
		}
		

		avaluoConIdSisgen.setTipoAvaluo(tipoAvaluoService.encontrarPorId(1L));
		avaluoConIdSisgen.setObjetoDelAvaluo(listaService.encontrarObjetoAvaluoPorId(1L));
		avaluoConIdSisgen.setTipoCredito("Vivienda");
		avaluoConIdSisgen.setCodigoExterno(avaluoDTO.getCodigoExterno());
		avaluoConIdSisgen.setEntidad(avaluoDTO.getEntidad());
		avaluoConIdSisgen.setMotivoAux(avaluoDTO.getMotivoAux());
		avaluoConIdSisgen.setMotivo(avaluoDTO.getMotivo());
		avaluoConIdSisgen.setAvaluoSisgenId(avaluoRequest.getIdAvaluoSisgen());

		avaluoDTO = avaluoConIdSisgen;

		return avaluoSoloAvaluo(avaluoRequest);

	}

	public void init() {

		avaluoDTO = new AvaluoDTO();
		avaluoDTO.setCliente(new ClienteDTO());
		avaluoDTO.setTipoAvaluo(new TipoAvaluoDTO());
		avaluoDTO.setObjetoDelAvaluo(new ObjetoAvaluoDTO());

	}

}
