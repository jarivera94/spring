package com.helio4.bancol.avaluos.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.helio4.bancol.avaluos.controlador.ListaDesplegable;
import com.helio4.bancol.avaluos.modelo.Motivo;
import com.helio4.bancol.avaluos.persistencia.AvaluoRepository;
import com.helio4.bancol.avaluos.servicio.util.Constantes;

public class AvaluoDTO implements Serializable, Cloneable {

	private static final long serialVersionUID = 1267992209247358121L;
	private Long id;
	private boolean soloAvaluo = false;
	private EntidadDTO entidad;
	private String codigoExterno;
	private TipoInmuebleDTO tipoDeInmueble;
	private TipoAvaluoDTO tipoAvaluo;
	private ObjetoAvaluoDTO objetoDelAvaluo;
	private Integer motivo;
	private String descripcionMotivo;
	private Integer cambioGarantia = 0;
	private Long cambioGarantiaAvaluo;

	private DivipolaDTO divipola;
	private ListaSector sector;
	private String conjunto = "";
	private String barrio;
	private String matriculaInmobiliariaPrincipal1;
	private String matriculaInmobiliariaPrincipal2 = "";

	/** Dirección */
	private String tipoVia;
	private String numeroVia;
	private String complementoVia;
	private String numeroViaGeneradora;
	private String complementoViaGeneradora;
	private String placa;
	private String complementoPlaca;
	private String adicionalDireccion;
	private String direccionInmueble;

	private String tipoViaInforme;
	private String numeroViaInforme;
	private String complementoViaInforme;
	private String numeroViaGeneradoraInforme;
	private String complementoViaGeneradoraInforme;
	private String placaInforme = "";
	private String adicionalDireccionInforme;
	private String direccionInmuebleInforme;
	private DivipolaDTO divipolaInforme;

	private String nombreRecibe;
	private String telefonoRecibe;
	private String correoElectronicoRecibe;
	private String nombreAsesor;
	private String sucursalAsesor;
	private String celularAsesor;
	private String correoElectronicoAsesor;
	private String telefonoAsesor;
	private String observacionesSolicitante;
	private BigDecimal latitud;
	private BigDecimal longitud;
	private BigDecimal valorSolicitado;
	private BigDecimal valorTotalAvaluo;
	private BigDecimal areaTotal;
	private BigDecimal valorUvr;
	private BigDecimal valorAvaluoEnUvr;
	private ListaCalificacionGarantia calificacionGarantia;
	private BigDecimal valorAsegurable;
	private BigDecimal valorLiquidacion;
	private BigDecimal valorHonorarios;
	private BigDecimal gastosTranslado;
	private String vereda = "";
	private String nombrePredio = "";
	private Set<AreaDTO> areas;
	private Set<FotografiaDTO> fotografias;
	private List<MetodoValuacionDTO> metodosValuacion;
	private EstadoAvaluoDTO estado;
	private Date fechaEstado;
	private Date fechaEnviado;
	private ClienteDTO cliente;
	private UsuarioDTO perito;
	private SemaforoDTO semaforo;
	private Long duracionPausaSemaforo;
	private BigDecimal iva;
	private UsuarioDTO creador;
	private Date fechaCreacion;
	private String tipoCredito;
	private Integer estadoDeConstruccion;
	private FormatoInformeDTO formatoInforme;
	private boolean tieneMetodologiasCalculadoras;
	private SubsidioDTO tipoSubsidio;

	private List<GarajeDTO> garajes;

	private String proposito;
	private String tipo;
	private BigDecimal latitudInicial;
	private BigDecimal longitudInicial;

	private String propietario;
	private String cedulaCatastral;

	private List<MatriculaDTO> matriculas;

	private AreaLoteAvaluoDTO areaLoteAvaluoDTO;
	private AreaConstruccionAvaluoDTO areaConstruccionAvaluoDTO;

	private Motivo motivoAux;
	private String nombreMotivo;
	private Long avaluoSisgenId;

	public AvaluoDTO() {
	}

	public AvaluoDTO(Long id, boolean soloAvaluo, EntidadDTO entidad, String codigoExterno,
			TipoInmuebleDTO tipoDeInmueble, TipoAvaluoDTO tipoAvaluo, ObjetoAvaluoDTO objetoDelAvaluo, Integer motivo,
			Integer cambioGarantia, Long cambioGarantiaAvaluo, DivipolaDTO divipola, ListaSector sector,
			String conjunto, String barrio, String tipoVia, String numeroVia, String complementoVia,
			String numeroViaGeneradora, String complementoViaGeneradora, String placa, String complementoPlaca,
			String adicionalDireccion, String direccionInmueble, String nombreRecibe, String telefonoRecibe,
			String correoElectronicoRecibe, String nombreAsesor, String sucursalAsesor, String celularAsesor,
			String correoElectronicoAsesor, String telefonoAsesor, String observacionesSolicitante, BigDecimal latitud,
			BigDecimal longitud, BigDecimal valorSolicitado, BigDecimal valorTotalAvaluo, BigDecimal valorUvr,
			BigDecimal valorAvaluoEnUvr, ListaCalificacionGarantia calificacionGarantia, BigDecimal valorAsegurable,
			BigDecimal valorLiquidacion, Set<AreaDTO> areas, Set<FotografiaDTO> fotografias,
			List<MetodoValuacionDTO> metodosValuacion, List<GarajeDTO> garajes, EstadoAvaluoDTO estado,
			Date fechaEstado, ClienteDTO cliente, UsuarioDTO perito, FormatoInformeDTO formatoInforme,
			BigDecimal valorHonorarios, BigDecimal gastosTranslado, String vereda, String nombrePredio, BigDecimal iva,
			BigDecimal areaTotal, String matriculaInmobiliariaPrincipal1, String matriculaInmobiliariaPrincipal2,
			SemaforoDTO semaforo, String descripcionMotivo, String direccionInmuebleInforme,
			DivipolaDTO divipolaInforme, String complementoViaInforme, String complementoViaGeneradoraInforme,
			String tipoViaInforme, String numeroViaGeneradoraInforme, String placaInforme, String numeroViaInforme,
			String adicionalDireccionInforme, String tipoCredito, Long avaluoSisgenId) {
		super();
		this.id = id;
		this.soloAvaluo = soloAvaluo;
		this.entidad = entidad;
		this.codigoExterno = codigoExterno;
		this.tipoDeInmueble = tipoDeInmueble;
		this.tipoAvaluo = tipoAvaluo;
		this.objetoDelAvaluo = objetoDelAvaluo;
		this.motivo = motivo;
		this.cambioGarantia = cambioGarantia;
		this.cambioGarantiaAvaluo = cambioGarantiaAvaluo;
		this.divipola = divipola;
		this.sector = sector;
		this.conjunto = conjunto;
		this.barrio = barrio;
		this.tipoVia = tipoVia;
		this.numeroVia = numeroVia;
		this.complementoVia = complementoVia;
		this.numeroViaGeneradora = numeroViaGeneradora;
		this.complementoViaGeneradora = complementoViaGeneradora;
		this.placa = placa;
		this.complementoPlaca = complementoPlaca;
		this.adicionalDireccion = adicionalDireccion;
		this.direccionInmueble = direccionInmueble;
		this.nombreRecibe = nombreRecibe;
		this.telefonoRecibe = telefonoRecibe;
		this.correoElectronicoRecibe = correoElectronicoRecibe;
		this.nombreAsesor = nombreAsesor;
		this.sucursalAsesor = sucursalAsesor;
		this.celularAsesor = celularAsesor;
		this.correoElectronicoAsesor = correoElectronicoAsesor;
		this.telefonoAsesor = telefonoAsesor;
		this.observacionesSolicitante = observacionesSolicitante;
		this.latitud = latitud;
		this.longitud = longitud;
		this.valorSolicitado = valorSolicitado;
		this.valorTotalAvaluo = valorTotalAvaluo;
		this.valorUvr = valorUvr;
		this.valorAvaluoEnUvr = valorAvaluoEnUvr;
		this.calificacionGarantia = calificacionGarantia;
		this.valorAsegurable = valorAsegurable;
		this.valorLiquidacion = valorLiquidacion;
		this.areas = areas;
		this.fotografias = fotografias;
		this.metodosValuacion = metodosValuacion;
		this.estado = estado;
		this.fechaEstado = fechaEstado;
		this.cliente = cliente;
		this.perito = perito;
		this.formatoInforme = formatoInforme;
		this.garajes = garajes;
		this.valorHonorarios = valorHonorarios;
		this.gastosTranslado = gastosTranslado;
		this.vereda = vereda;
		this.nombrePredio = nombrePredio;
		this.iva = iva;
		this.areaTotal = areaTotal;
		this.matriculaInmobiliariaPrincipal1 = matriculaInmobiliariaPrincipal1;
		this.matriculaInmobiliariaPrincipal2 = matriculaInmobiliariaPrincipal2 == null ? ""
				: matriculaInmobiliariaPrincipal2;
		this.semaforo = semaforo;
		this.descripcionMotivo = descripcionMotivo;
		this.direccionInmuebleInforme = direccionInmuebleInforme;
		this.divipolaInforme = divipolaInforme;
		this.complementoViaGeneradoraInforme = complementoViaGeneradoraInforme;
		this.complementoViaInforme = complementoViaInforme;
		this.tipoViaInforme = tipoViaInforme;
		this.numeroViaGeneradoraInforme = numeroViaGeneradoraInforme;
		this.placaInforme = placaInforme;
		this.numeroViaInforme = numeroViaInforme;
		this.adicionalDireccionInforme = adicionalDireccionInforme;
		this.tipoCredito = tipoCredito;
		this.avaluoSisgenId = avaluoSisgenId;

	}

	/**
	 * Constructor usado por la consulta Criteria para cargar el listado
	 */
	public AvaluoDTO(Long id, Integer estado, Date fechaEstado, BigDecimal valorSolicitado, String codigoExterno,
			Long tipoInmuebleId, String tipoInmueble, Long tipoAvaluoId, String tipoAvaluo, String tipoVia,
			String numeroVia, String complementoVia, String numeroViaGeneradora, String complementoViaGeneradora,
			String placa, String adicionalDireccion, String conjunto, String direccionInmueble, String barrio,
			Long divipolaId, String departamento, String municipio, String centroPoblado, Long entidadId,
			String nombreEntidad, Long numeroDocumentoCliente, Integer tipoIdCliente, String primerNombre,
			String segundoNombre, String primerApellido, String segundoApellido, String razonSocial, String telefono,
			String celular, String direccionDeContactoSolicitante, String correoElectronicoSolicitante, Long segmentoId,
			String segmento, Long divipolaClienteId, String departamentoCliente, String centroPobladoCliente,
			Long numeroDocumentoPerito, Integer tipoIdPerito, String nombresPerito, String apellidosPerito,
			String celularPerito, String emailPerito, String nombreRecibe, String telefonoRecibe,
			String correoElectronicoRecibe, String nombreAsesor, String sucursalAsesor, String celularAsesor,
			String telefonoAsesor, String observaciones, Long duracionSemaforoVerde, Long duracionSemaforoAmarillo,
			Long duracionSemaforoRojo, Long duracionPausaSemaforo, Date fechaInicial, Integer cambioGarantia,
			Boolean compraCartera, Long motivo, String nombreMotivo, Long avaluoSisgenId) {
		this.id = id;
		this.estado = new EstadoAvaluoDTO();
		this.estado.setEstado(Constantes.Estado.fromKey(estado));
		this.estado.setFechaEstado(fechaEstado);
		this.fechaEstado = fechaEstado;
		this.valorSolicitado = valorSolicitado;
		this.codigoExterno = codigoExterno;
		this.tipoDeInmueble = new TipoInmuebleDTO();
		this.tipoDeInmueble.setId(tipoInmuebleId);
		this.tipoDeInmueble.setNombre(tipoInmueble);
		this.tipoAvaluo = new TipoAvaluoDTO();
		this.tipoAvaluo.setId(tipoAvaluoId);
		this.tipoAvaluo.setNombre(tipoAvaluo);
		this.tipoVia = tipoVia;
		this.numeroVia = numeroVia;
		this.complementoVia = complementoVia;
		this.numeroViaGeneradora = numeroViaGeneradora;
		this.placa = placa;
		this.adicionalDireccion = adicionalDireccion;
		this.conjunto = conjunto;
		this.direccionInmueble = direccionInmueble;
		this.barrio = barrio;
		this.divipola = new DivipolaDTO();
		this.divipola.setId(divipolaId);
		this.divipola.setDepartamento(departamento);
		this.divipola.setMunicipio(municipio);
		this.divipola.setCentroPoblado(centroPoblado);

		this.nombreRecibe = nombreRecibe;
		this.telefonoRecibe = telefonoRecibe;
		this.correoElectronicoRecibe = correoElectronicoRecibe;
		this.nombreAsesor = nombreAsesor;
		this.sucursalAsesor = sucursalAsesor;
		this.celularAsesor = celularAsesor;
		this.telefonoAsesor = telefonoAsesor;
		this.observacionesSolicitante = observaciones;

		this.entidad = new EntidadDTO();
		this.entidad.setId(entidadId);
		this.entidad.setNombre(nombreEntidad);
		this.entidad.setDuracionSemaforoVerde(duracionSemaforoVerde);
		this.entidad.setDuracionSemaforoAmarillo(duracionSemaforoAmarillo);
		this.entidad.setDuracionSemaforoRojo(duracionSemaforoRojo);

		this.perito = new UsuarioDTO();
		this.perito.setTipoDocumentoIdentificacion(tipoIdPerito);
		this.perito.setNumeroDocumento(numeroDocumentoPerito);
		this.perito.setNombres(nombresPerito);
		this.perito.setApellidos(apellidosPerito);
		this.perito.setEmail(emailPerito);
		this.perito.setCelular(celularPerito);

		this.cliente = new ClienteDTO();
		this.cliente.setNumeroDocumento(numeroDocumentoCliente);
		this.cliente.setTipoDocumentoIdentificacion(tipoIdCliente);
		this.cliente.setPrimerNombre(primerNombre);
		this.cliente.setSegundoNombre(segundoNombre);
		this.cliente.setPrimerApellido(primerApellido);
		this.cliente.setSegundoApellido(segundoApellido);
		this.cliente.setRazonSocial(razonSocial);
		this.cliente.setTelefonoSolicitante(telefono);
		this.cliente.setCorreoElectronicoSolicitante(correoElectronicoSolicitante);
		this.cliente.setDireccionDeContactoSolicitante(direccionDeContactoSolicitante);

		SegmentoDTO segmentoDTO = new SegmentoDTO();
		segmentoDTO.setId(segmentoId);
		segmentoDTO.setNombre(segmento);
		this.cliente.setSegmento(segmentoDTO);

		DivipolaDTO divipolaClienteDTO = new DivipolaDTO();
		divipolaClienteDTO.setId(divipolaClienteId);
		divipolaClienteDTO.setDepartamento(departamentoCliente);
		divipolaClienteDTO.setCentroPoblado(centroPobladoCliente);
		this.cliente.setDivipola(divipolaClienteDTO);

		this.semaforo = new SemaforoDTO(fechaInicial, duracionPausaSemaforo, duracionSemaforoVerde,
				duracionSemaforoAmarillo, duracionSemaforoRojo);

		if (this.estado.getEstado().key() == 13) {
			this.semaforo.pausarSemaforo();
		}
		this.duracionPausaSemaforo = duracionPausaSemaforo;

		if (motivo == null) {

			this.motivo = (compraCartera != null && compraCartera) ? 4
					: (cambioGarantia != null && cambioGarantia > 0) ? 3 : 0;
			this.descripcionMotivo = (this.motivo != null ? (this.motivo == 0 ? "Garantía"
					: this.motivo == 3 ? "Cambio garantía" : this.motivo == 4 ? "Compra de Cartera" : "") : "");

		} else {
			this.motivo = motivo.intValue();
		}

		this.nombreMotivo = nombreMotivo;
		this.avaluoSisgenId = avaluoSisgenId;
	}

	/**
	 * Constructor usado por la consulta
	 * {@link AvaluoRepository#comprobarCambioGarantia}
	 */
	public AvaluoDTO(Long id, String codigoExterno, Long numeroDocumentoCliente, Integer tipoIdCliente,
			String primerNombre, String segundoNombre, String primerApellido, String segundoApellido,
			String razonSocial, Boolean clienteExterior, Long segmentoId, String segmento, Long divipolaClienteId,
			String departamentoCliente, String centroPobladoCliente, Integer cambioGarantia, Long cambioGarantiaAvaluo,
			Integer estado, Date fechaEstado) {
		this.id = id;
		this.codigoExterno = codigoExterno;

		this.cliente = new ClienteDTO();
		this.cliente.setNumeroDocumento(numeroDocumentoCliente);
		this.cliente.setTipoDocumentoIdentificacion(tipoIdCliente);
		this.cliente.setPrimerNombre(primerNombre);
		this.cliente.setSegundoNombre(segundoNombre);
		this.cliente.setPrimerApellido(primerApellido);
		this.cliente.setSegundoApellido(segundoApellido);
		this.cliente.setRazonSocial(razonSocial);
		this.cliente.setClienteExterior(clienteExterior);

		SegmentoDTO segmentoDTO = new SegmentoDTO();
		segmentoDTO.setId(segmentoId);
		segmentoDTO.setNombre(segmento);
		this.cliente.setSegmento(segmentoDTO);

		DivipolaDTO divipolaClienteDTO = new DivipolaDTO();
		divipolaClienteDTO.setId(divipolaClienteId);
		divipolaClienteDTO.setDepartamento(departamentoCliente);
		divipolaClienteDTO.setCentroPoblado(centroPobladoCliente);
		this.cliente.setDivipola(divipolaClienteDTO);

		this.cambioGarantia = cambioGarantia;
		this.cambioGarantiaAvaluo = cambioGarantiaAvaluo;

		this.estado = new EstadoAvaluoDTO();
		this.estado.setEstado(Constantes.Estado.fromKey(estado));
		this.estado.setFechaEstado(fechaEstado);
	}

	/**
	 * Constructor usado por la consulta
	 * {@link AvaluoRepository#encontrarAvaluosAnteriores}
	 */
	public AvaluoDTO(Long id, String codigoExterno, Date fechaEstado, Long tipoAvaluoId, String nombreTipoAvaluo,
			BigDecimal valorTotalAvaluo) {
		this.id = id;
		this.codigoExterno = codigoExterno;
		this.fechaEstado = fechaEstado;
		this.tipoAvaluo = new TipoAvaluoDTO();
		this.tipoAvaluo.setId(tipoAvaluoId);
		this.tipoAvaluo.setNombre(nombreTipoAvaluo);
		this.valorTotalAvaluo = valorTotalAvaluo;
	}

	/**
	 * Constructor usado por la consulta
	 * {@link AvaluoRepository#encontrarAvaluosAprobadosDeCliente}
	 */
	public AvaluoDTO(Long id, String codigoExterno, String tipoAvaluoNombre) {
		this.id = id;
		this.codigoExterno = codigoExterno;
		this.tipoAvaluo = new TipoAvaluoDTO();
		this.tipoAvaluo.setNombre(tipoAvaluoNombre);
	}
	
	public AvaluoDTO(Long id, String codigoExterno,  Long avaluoSisgenId ) {
		this.id = id;
		this.codigoExterno = codigoExterno;
		this.avaluoSisgenId =avaluoSisgenId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isSoloAvaluo() {
		return soloAvaluo;
	}

	public void setSoloAvaluo(boolean soloAvaluo) {
		this.soloAvaluo = soloAvaluo;
	}

	public EntidadDTO getEntidad() {
		return entidad;
	}

	public void setEntidad(EntidadDTO entidad) {
		this.entidad = entidad;
	}
	
	public String getCodigoExterno() {
		return codigoExterno;
	}

	public void setCodigoExterno(String codigoExterno) {
		this.codigoExterno = codigoExterno;
	}

	public TipoInmuebleDTO getTipoDeInmueble() {
		return tipoDeInmueble;
	}

	public void setTipoDeInmueble(TipoInmuebleDTO tipoDeInmueble) {
		this.tipoDeInmueble = tipoDeInmueble;
	}

	public TipoAvaluoDTO getTipoAvaluo() {
		return tipoAvaluo;
	}

	public void setTipoAvaluo(TipoAvaluoDTO tipoAvaluo) {
		this.tipoAvaluo = tipoAvaluo;
	}

	public ObjetoAvaluoDTO getObjetoDelAvaluo() {
		return objetoDelAvaluo;
	}

	public void setObjetoDelAvaluo(ObjetoAvaluoDTO objetoDelAvaluo) {
		this.objetoDelAvaluo = objetoDelAvaluo;
	}

	public Integer getMotivo() {
		return motivo;
	}

	public void setMotivo(Integer motivo) {

		this.motivo = motivo;
	}

	public Integer getCambioGarantia() {
		return cambioGarantia;
	}

	public void setCambioGarantia(Integer cambioGarantia) {
		if (cambioGarantia == null) {
			cambioGarantia = 0;
		}
		this.cambioGarantia = cambioGarantia;
	}

	public Long getCambioGarantiaAvaluo() {
		return cambioGarantiaAvaluo;
	}

	public void setCambioGarantiaAvaluo(Long cambioGarantiaAvaluo) {
		this.cambioGarantiaAvaluo = cambioGarantiaAvaluo;
	}

	public DivipolaDTO getDivipola() {
		return divipola;
	}

	public void setDivipola(DivipolaDTO divipola) {
		this.divipola = divipola;
	}

	public ListaSector getSector() {
		return sector;
	}

	public void setSector(ListaSector sector) {
		this.sector = sector;
	}

	public String getConjunto() {
		return conjunto;
	}

	public void setConjunto(String conjunto) {
		this.conjunto = conjunto;
	}

	public String getBarrio() {
		return barrio;
	}

	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}

	public String getMatriculaInmobiliariaPrincipal1() {
		return matriculaInmobiliariaPrincipal1;
	}

	public void setMatriculaInmobiliariaPrincipal1(String matriculaInmobiliariaPrincipal1) {
		this.matriculaInmobiliariaPrincipal1 = matriculaInmobiliariaPrincipal1;
	}

	public String getTipoVia() {
		return tipoVia;
	}

	public void setTipoVia(String tipoVia) {
		this.tipoVia = tipoVia;
	}

	public String getNumeroVia() {
		return numeroVia;
	}

	public void setNumeroVia(String numeroVia) {
		if ("".equals(numeroVia)) {
			numeroVia = null;
		}
		this.numeroVia = numeroVia;
	}

	public String getComplementoVia() {
		return complementoVia;
	}

	public void setComplementoVia(String complementoVia) {
		this.complementoVia = complementoVia;
	}

	public String getNumeroViaGeneradora() {
		return numeroViaGeneradora;
	}

	public void setNumeroViaGeneradora(String numeroViaGeneradora) {
		if ("".equals(numeroViaGeneradora)) {
			numeroViaGeneradora = null;
		}
		this.numeroViaGeneradora = numeroViaGeneradora;
	}

	public String getComplementoViaGeneradora() {
		return complementoViaGeneradora;
	}

	public void setComplementoViaGeneradora(String complementoViaGeneradora) {
		this.complementoViaGeneradora = complementoViaGeneradora;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getComplementoPlaca() {
		return complementoPlaca;
	}

	public void setComplementoPlaca(String complementoPlaca) {
		this.complementoPlaca = complementoPlaca;
	}

	public String getAdicionalDireccion() {
		return adicionalDireccion;
	}

	public void setAdicionalDireccion(String adicionalDireccion) {
		this.adicionalDireccion = adicionalDireccion;
	}

	public String getDireccionInmueble() {
		return direccionInmueble;
	}

	public void setDireccionInmueble(String direccionInmueble) {
		this.direccionInmueble = direccionInmueble;
	}

	public String getTipoViaInforme() {
		return tipoViaInforme;
	}

	public void setTipoViaInforme(String tipoViaInforme) {
		this.tipoViaInforme = tipoViaInforme;
	}

	public String getNumeroViaInforme() {
		return numeroViaInforme;
	}

	public void setNumeroViaInforme(String numeroViaInforme) {
		this.numeroViaInforme = numeroViaInforme;
	}

	public String getComplementoViaInforme() {
		return complementoViaInforme;
	}

	public void setComplementoViaInforme(String complementoViaInforme) {
		this.complementoViaInforme = complementoViaInforme;
	}

	public String getNumeroViaGeneradoraInforme() {
		return numeroViaGeneradoraInforme;
	}

	public void setNumeroViaGeneradoraInforme(String numeroViaGeneradoraInforme) {
		this.numeroViaGeneradoraInforme = numeroViaGeneradoraInforme;
	}

	public String getComplementoViaGeneradoraInforme() {
		return complementoViaGeneradoraInforme;
	}

	public void setComplementoViaGeneradoraInforme(String complementoViaGeneradoraInforme) {
		this.complementoViaGeneradoraInforme = complementoViaGeneradoraInforme;
	}

	public String getPlacaInforme() {
		return placaInforme;
	}

	public void setPlacaInforme(String placaInforme) {
		this.placaInforme = placaInforme;
	}

	public String getAdicionalDireccionInforme() {
		return adicionalDireccionInforme;
	}

	public void setAdicionalDireccionInforme(String adicionalDireccionInforme) {
		this.adicionalDireccionInforme = adicionalDireccionInforme;
	}

	public String getDireccionInmuebleInforme() {
		return direccionInmuebleInforme;
	}

	public void setDireccionInmuebleInforme(String direccionInmuebleInforme) {
		this.direccionInmuebleInforme = direccionInmuebleInforme;
	}

	public DivipolaDTO getDivipolaInforme() {
		return divipolaInforme;
	}

	public void setDivipolaInforme(DivipolaDTO divipolaInforme) {
		this.divipolaInforme = divipolaInforme;
	}

	public String getNombreRecibe() {
		return nombreRecibe;
	}

	public void setNombreRecibe(String nombreRecibe) {
		if (nombreRecibe != null) {
			this.nombreRecibe = nombreRecibe.toUpperCase();
		}
	}

	public String getTelefonoRecibe() {
		return telefonoRecibe;
	}

	public void setTelefonoRecibe(String telefonoRecibe) {
		this.telefonoRecibe = telefonoRecibe;
	}

	public String getCorreoElectronicoRecibe() {
		return correoElectronicoRecibe;
	}

	public void setCorreoElectronicoRecibe(String correoElectronicoRecibe) {
		this.correoElectronicoRecibe = correoElectronicoRecibe;
	}

	public String getNombreAsesor() {
		return nombreAsesor;
	}

	public void setNombreAsesor(String nombreAsesor) {
		if (nombreAsesor != null) {
			this.nombreAsesor = nombreAsesor.toUpperCase();
		}
	}

	public String getSucursalAsesor() {
		return sucursalAsesor;
	}

	public void setSucursalAsesor(String sucursalAsesor) {
		this.sucursalAsesor = sucursalAsesor;
	}

	public String getCelularAsesor() {
		return celularAsesor;
	}

	public void setCelularAsesor(String celularAsesor) {
		this.celularAsesor = celularAsesor;
	}

	public String getCorreoElectronicoAsesor() {
		return correoElectronicoAsesor;
	}

	public void setCorreoElectronicoAsesor(String correoElectronicoAsesor) {
		this.correoElectronicoAsesor = correoElectronicoAsesor;
	}

	public String getTelefonoAsesor() {
		return telefonoAsesor;
	}

	public void setTelefonoAsesor(String telefonoAsesor) {
		this.telefonoAsesor = telefonoAsesor;
	}

	public String getObservacionesSolicitante() {
		return observacionesSolicitante;
	}

	public void setObservacionesSolicitante(String observacionesSolicitante) {
		this.observacionesSolicitante = observacionesSolicitante;
	}

	public BigDecimal getLatitud() {
		return latitud;
	}

	public void setLatitud(BigDecimal latitud) {
		this.latitud = latitud;
	}

	public BigDecimal getLongitud() {
		return longitud;
	}

	public void setLongitud(BigDecimal longitud) {
		this.longitud = longitud;
	}

	public BigDecimal getValorSolicitado() {
		return valorSolicitado;
	}

	public void setValorSolicitado(BigDecimal valorSolicitado) {
		this.valorSolicitado = valorSolicitado;
	}

	public BigDecimal getValorTotalAvaluo() {
		return valorTotalAvaluo;
	}

	public void setValorTotalAvaluo(BigDecimal valorTotalAvaluo) {
		this.valorTotalAvaluo = valorTotalAvaluo;
	}

	public BigDecimal getAreaTotal() {
		return areaTotal;
	}

	public void setAreaTotal(BigDecimal areaTotal) {
		this.areaTotal = areaTotal;
	}

	public BigDecimal getValorUvr() {
		return valorUvr;
	}

	public void setValorUvr(BigDecimal valorUvr) {
		this.valorUvr = valorUvr;
	}

	public BigDecimal getValorAvaluoEnUvr() {
		return valorAvaluoEnUvr;
	}

	public void setValorAvaluoEnUvr(BigDecimal valorAvaluoEnUvr) {
		this.valorAvaluoEnUvr = valorAvaluoEnUvr;
	}

	public ListaCalificacionGarantia getCalificacionGarantia() {
		return calificacionGarantia;
	}

	public void setCalificacionGarantia(ListaCalificacionGarantia calificacionGarantia) {
		this.calificacionGarantia = calificacionGarantia;
	}

	public BigDecimal getValorAsegurable() {
		return valorAsegurable;
	}

	public void setValorAsegurable(BigDecimal valorAsegurable) {
		this.valorAsegurable = valorAsegurable;
	}

	public BigDecimal getValorLiquidacion() {
		return valorLiquidacion;
	}

	public void setValorLiquidacion(BigDecimal valorLiquidacion) {
		this.valorLiquidacion = valorLiquidacion;
	}

	public BigDecimal getValorHonorarios() {
		return valorHonorarios;
	}

	public void setValorHonorarios(BigDecimal valorHonorarios) {
		this.valorHonorarios = valorHonorarios;
	}

	public BigDecimal getGastosTranslado() {
		return gastosTranslado;
	}

	public void setGastosTranslado(BigDecimal gastosTranslado) {
		this.gastosTranslado = gastosTranslado;
	}

	public String getVereda() {
		return vereda;
	}

	public void setVereda(String vereda) {
		this.vereda = vereda;
	}

	public String getNombrePredio() {
		return nombrePredio;
	}

	public void setNombrePredio(String nombrePredio) {
		this.nombrePredio = nombrePredio;
	}

	public Set<AreaDTO> getAreas() {
		return areas;
	}

	public void setAreas(Set<AreaDTO> areas) {
		this.areas = areas;
	}

	public Set<FotografiaDTO> getFotografias() {
		return fotografias;
	}

	public void setFotografias(Set<FotografiaDTO> fotografias) {
		this.fotografias = fotografias;
	}

	public List<MetodoValuacionDTO> getMetodosValuacion() {
		return metodosValuacion;
	}

	public void setMetodosValuacion(List<MetodoValuacionDTO> metodosValuacion) {
		this.metodosValuacion = metodosValuacion;
	}

	public EstadoAvaluoDTO getEstado() {
		return estado;
	}

	public void setEstado(EstadoAvaluoDTO estado) {
		this.estado = estado;
	}

	public Date getFechaEstado() {
		return fechaEstado;
	}

	public void setFechaEstado(Date fechaEstado) {
		this.fechaEstado = fechaEstado;
	}

	public Date getFechaEnviado() {
		return fechaEnviado;
	}

	public void setFechaEnviado(Date fechaEnviado) {
		this.fechaEnviado = fechaEnviado;
	}

	public ClienteDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}

	public UsuarioDTO getPerito() {
		return perito;
	}

	public void setPerito(UsuarioDTO perito) {
		this.perito = perito;
	}

	public Long getDuracionPausaSemaforo() {
		return duracionPausaSemaforo;
	}

	public void setDuracionPausaSemaforo(Long duracionPausaSemaforo) {
		this.duracionPausaSemaforo = duracionPausaSemaforo;
	}

	public SemaforoDTO getSemaforo() {
		return semaforo;
	}

	public void setSemaforo(SemaforoDTO semaforo) {
		this.semaforo = semaforo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public SubsidioDTO getTipoSubsidio() {
		return tipoSubsidio;
	}

	public void setTipoSubsidio(SubsidioDTO tipoSubsidio) {
		this.tipoSubsidio = tipoSubsidio;
	}

	public enum MBR {
		NoSelectOption("Seleccione...", 0), Bueno("Bueno", 1), Regular("Regular", 2), Malo("Malo", 3);

		private final String value;
		private final int key;

		MBR(String value, int key) {
			this.value = value;
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public int getKey() {
			return key;
		}

		public static MBR fromKey(int key) {
			switch (key) {
			case 0:
				return NoSelectOption;
			case 1:
				return Bueno;
			case 2:
				return Regular;
			case 3:
				return Malo;
			default:
				return null;
			}
		}

		@Override
		public String toString() {
			switch (this) {
			case NoSelectOption:
				return "Seleccione...";
			case Bueno:
				return "Bueno";
			case Regular:
				return "Regular";
			case Malo:
				return "Malo";
			default:
				return null;
			}
		}
	}

	public enum ListaSector implements ListaDesplegable {
		Seleccione(Constantes.SELECCCIONE, 0), Urbano(Constantes.TIPO_SECTOR_URBANO,
				1), Rural(Constantes.TIPO_SECTOR_RURAL, 2);

		private final String value;
		private final int key;

		ListaSector(String value, int key) {
			this.value = value;
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public int getKey() {
			return key;
		}

		public static ListaSector fromKey(int key) {
			switch (key) {
			case 0:
				return Seleccione;
			case 1:
				return Urbano;
			case 2:
				return Rural;
			default:
				return null;
			}
		}

		@Override
		public String toString() {
			switch (this) {
			case Seleccione:
				return Constantes.SELECCCIONE;
			case Urbano:
				return Constantes.TIPO_SECTOR_URBANO;
			case Rural:
				return Constantes.TIPO_SECTOR_RURAL;
			default:
				return "";
			}
		}
	}


    /**
     * Enumeracion para las opciones de estados de acabados
     * */
    public enum ListaCalificacionGarantia implements  ListaDesplegable {
        Favorable(Constantes.CALIFICACION_GARANTIA_FAVORABLE , 1),
    	Desfavorable(Constantes.CALIFICACION_GARANTIA_DESFAVORABLE , 2),
    	NoAplica(Constantes.CALIFICACION_GARANTIA_NO_APLICA , 3),
    	FavorableConLimitaciones(Constantes.FAVORABLE_CON_LIMITACIONES , 51);

		private final String value;
		private final int key;

		ListaCalificacionGarantia(String value, int key) {
			this.value = value;
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public int getKey() {
			return key;
		}

		public static ListaCalificacionGarantia fromKey(int key) {
			switch (key) {

			case 1: return Favorable;
			case 2: return Desfavorable;
			case 3: return NoAplica;
			case 51: return FavorableConLimitaciones;
			default:
				return null;
			}
		}

		@Override
		public String toString() {
			switch (this) {

			case Favorable: return Constantes.CALIFICACION_GARANTIA_FAVORABLE  ;
			case Desfavorable: return Constantes.CALIFICACION_GARANTIA_DESFAVORABLE ;
			case NoAplica: return Constantes.CALIFICACION_GARANTIA_NO_APLICA;
			case FavorableConLimitaciones: return Constantes.FAVORABLE_CON_LIMITACIONES;

			default:
				return "";
			}
		}
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adicionalDireccion == null) ? 0 : adicionalDireccion.hashCode());
		result = prime * result + ((barrio == null) ? 0 : barrio.hashCode());
		result = prime * result + ((calificacionGarantia == null) ? 0 : calificacionGarantia.hashCode());
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((codigoExterno == null) ? 0 : codigoExterno.hashCode());
		result = prime * result + ((complementoPlaca == null) ? 0 : complementoPlaca.hashCode());
		result = prime * result + ((complementoVia == null) ? 0 : complementoVia.hashCode());
		result = prime * result + ((complementoViaGeneradora == null) ? 0 : complementoViaGeneradora.hashCode());
		result = prime * result + ((conjunto == null) ? 0 : conjunto.hashCode());
		result = prime * result + ((direccionInmueble == null) ? 0 : direccionInmueble.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((latitud == null) ? 0 : latitud.hashCode());
		result = prime * result + ((longitud == null) ? 0 : longitud.hashCode());
		result = prime * result
				+ ((matriculaInmobiliariaPrincipal1 == null) ? 0 : matriculaInmobiliariaPrincipal1.hashCode());
		result = prime * result + ((numeroVia == null) ? 0 : numeroVia.hashCode());
		result = prime * result + ((numeroViaGeneradora == null) ? 0 : numeroViaGeneradora.hashCode());
		result = prime * result + ((objetoDelAvaluo == null) ? 0 : objetoDelAvaluo.hashCode());
		result = prime * result + ((motivo == null) ? 0 : motivo.hashCode());
		result = prime * result + ((cambioGarantia == null) ? 0 : cambioGarantia.hashCode());
		result = prime * result + ((observacionesSolicitante == null) ? 0 : observacionesSolicitante.hashCode());
		result = prime * result + ((placa == null) ? 0 : placa.hashCode());
		result = prime * result + ((sector == null) ? 0 : sector.hashCode());
		result = prime * result + ((tipoAvaluo == null) ? 0 : tipoAvaluo.hashCode());
		result = prime * result + ((tipoDeInmueble == null) ? 0 : tipoDeInmueble.hashCode());
		result = prime * result + ((tipoVia == null) ? 0 : tipoVia.hashCode());
		result = prime * result + ((valorAsegurable == null) ? 0 : valorAsegurable.hashCode());
		result = prime * result + ((valorAvaluoEnUvr == null) ? 0 : valorAvaluoEnUvr.hashCode());
		result = prime * result + ((valorLiquidacion == null) ? 0 : valorLiquidacion.hashCode());
		result = prime * result + ((valorTotalAvaluo == null) ? 0 : valorTotalAvaluo.hashCode());
		result = prime * result + ((valorUvr == null) ? 0 : valorUvr.hashCode());
		result = prime * result + ((proposito == null) ? 0 : proposito.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		result = prime * result + ((latitudInicial == null) ? 0 : latitudInicial.hashCode());
		result = prime * result + ((longitudInicial == null) ? 0 : longitudInicial.hashCode());
		result = prime * result + ((propietario == null) ? 0 : propietario.hashCode());
		result = prime * result + ((cedulaCatastral == null) ? 0 : cedulaCatastral.hashCode());
		result = prime * result + ((matriculas == null) ? 0 : matriculas.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AvaluoDTO other = (AvaluoDTO) obj;
		if (adicionalDireccion == null) {
			if (other.adicionalDireccion != null)
				return false;
		} else if (!adicionalDireccion.equals(other.adicionalDireccion))
			return false;
		if (barrio == null) {
			if (other.barrio != null)
				return false;
		} else if (!barrio.equals(other.barrio))
			return false;
		if (calificacionGarantia == null) {
			if (other.calificacionGarantia != null)
				return false;
		} else if (!calificacionGarantia.equals(other.calificacionGarantia))
			return false;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (codigoExterno == null) {
			if (other.codigoExterno != null)
				return false;
		} else if (!codigoExterno.equals(other.codigoExterno))
			return false;
		if (complementoPlaca == null) {
			if (other.complementoPlaca != null)
				return false;
		} else if (!complementoPlaca.equals(other.complementoPlaca))
			return false;
		if (complementoVia == null) {
			if (other.complementoVia != null)
				return false;
		} else if (!complementoVia.equals(other.complementoVia))
			return false;
		if (complementoViaGeneradora == null) {
			if (other.complementoViaGeneradora != null)
				return false;
		} else if (!complementoViaGeneradora.equals(other.complementoViaGeneradora))
			return false;
		if (conjunto == null) {
			if (other.conjunto != null)
				return false;
		} else if (!conjunto.equals(other.conjunto))
			return false;
		if (direccionInmueble == null) {
			if (other.direccionInmueble != null)
				return false;
		} else if (!direccionInmueble.equals(other.direccionInmueble))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (latitud == null) {
			if (other.latitud != null)
				return false;
		} else if (!latitud.equals(other.latitud))
			return false;
		if (longitud == null) {
			if (other.longitud != null)
				return false;
		} else if (!longitud.equals(other.longitud))
			return false;
		if (matriculaInmobiliariaPrincipal1 == null) {
			if (other.matriculaInmobiliariaPrincipal1 != null)
				return false;
		} else if (!matriculaInmobiliariaPrincipal1.equals(other.matriculaInmobiliariaPrincipal1))
			return false;
		if (numeroVia == null) {
			if (other.numeroVia != null)
				return false;
		} else if (!numeroVia.equals(other.numeroVia))
			return false;
		if (numeroViaGeneradora == null) {
			if (other.numeroViaGeneradora != null)
				return false;
		} else if (!numeroViaGeneradora.equals(other.numeroViaGeneradora))
			return false;
		if (objetoDelAvaluo == null) {
			if (other.objetoDelAvaluo != null)
				return false;
		} else if (!objetoDelAvaluo.equals(other.objetoDelAvaluo))
			return false;
		if (motivo == null) {
			if (other.motivo != null)
				return false;
		} else if (!motivo.equals(other.motivo))
			return false;
		if (cambioGarantia == null) {
			if (other.cambioGarantia != null)
				return false;
		} else if (!cambioGarantia.equals(other.cambioGarantia))
			return false;
		if (observacionesSolicitante == null) {
			if (other.observacionesSolicitante != null)
				return false;
		} else if (!observacionesSolicitante.equals(other.observacionesSolicitante))
			return false;
		if (placa == null) {
			if (other.placa != null)
				return false;
		} else if (!placa.equals(other.placa))
			return false;
		if (sector == null) {
			if (other.sector != null)
				return false;
		} else if (!sector.equals(other.sector))
			return false;
		if (tipoAvaluo == null) {
			if (other.tipoAvaluo != null)
				return false;
		} else if (!tipoAvaluo.equals(other.tipoAvaluo))
			return false;
		if (tipoDeInmueble == null) {
			if (other.tipoDeInmueble != null)
				return false;
		} else if (!tipoDeInmueble.equals(other.tipoDeInmueble))
			return false;
		if (tipoVia == null) {
			if (other.tipoVia != null)
				return false;
		} else if (!tipoVia.equals(other.tipoVia))
			return false;
		if (valorAsegurable == null) {
			if (other.valorAsegurable != null)
				return false;
		} else if (!valorAsegurable.equals(other.valorAsegurable))
			return false;
		if (valorAvaluoEnUvr == null) {
			if (other.valorAvaluoEnUvr != null)
				return false;
		} else if (!valorAvaluoEnUvr.equals(other.valorAvaluoEnUvr))
			return false;
		if (valorLiquidacion == null) {
			if (other.valorLiquidacion != null)
				return false;
		} else if (!valorLiquidacion.equals(other.valorLiquidacion))
			return false;
		if (valorTotalAvaluo == null) {
			if (other.valorTotalAvaluo != null)
				return false;
		} else if (!valorTotalAvaluo.equals(other.valorTotalAvaluo))
			return false;
		if (valorUvr == null) {
			if (other.valorUvr != null)
				return false;
		} else if (!valorUvr.equals(other.valorUvr))
			return false;
		if (proposito == null) {
			if (other.proposito != null)
				return false;
		} else if (!proposito.equals(other.proposito))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		if (latitudInicial == null) {
			if (other.latitudInicial != null)
				return false;
		} else if (!latitudInicial.equals(other.latitudInicial))
			return false;
		if (longitudInicial == null) {
			if (other.longitudInicial != null)
				return false;
		} else if (!longitudInicial.equals(other.longitudInicial))
			return false;
		if (propietario == null) {
			if (other.propietario != null)
				return false;
		} else if (!propietario.equals(other.propietario))
			return false;
		if (cedulaCatastral == null) {
			if (other.cedulaCatastral != null)
				return false;
		} else if (!cedulaCatastral.equals(other.cedulaCatastral))
			return false;
		if (matriculas == null) {
			if (other.matriculas != null)
				return false;
		} else if (!matriculas.equals(other.matriculas))
			return false;

		return true;
	}

	public UsuarioDTO getCreador() {
		return creador;
	}

	public void setCreador(UsuarioDTO creador) {
		this.creador = creador;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public BigDecimal getIva() {
		return iva;
	}

	public void setIva(BigDecimal iva) {
		this.iva = iva;
	}

	public FormatoInformeDTO getFormatoInforme() {
		return formatoInforme;
	}

	public void setFormatoInforme(FormatoInformeDTO formatoInforme) {
		this.formatoInforme = formatoInforme;
	}

	public String getTipoCredito() {
		return tipoCredito;
	}

	public void setTipoCredito(String tipoCredito) {
		if ("".equals(tipoCredito)) {
			tipoCredito = null;
		}
		this.tipoCredito = tipoCredito;
	}

	public Integer getEstadoDeConstruccion() {
		return estadoDeConstruccion;
	}

	public void setEstadoDeConstruccion(Integer estadoDeConstruccion) {
		this.estadoDeConstruccion = estadoDeConstruccion;
	}

	public boolean getTieneMetodologiasCalculadoras() {
		return tieneMetodologiasCalculadoras;
	}

	public void setTieneMetodologiasCalculadoras(boolean tieneMetodologiasCalculadoras) {
		this.tieneMetodologiasCalculadoras = tieneMetodologiasCalculadoras;
	}

	public String getDescripcionMotivo() {
		return descripcionMotivo;
	}

	public void setDescripcionMotivo(String descripcionMotivo) {
		this.descripcionMotivo = descripcionMotivo;
	}

	public List<GarajeDTO> getGarajes() {
		return garajes;
	}

	public void setGarajes(List<GarajeDTO> garajes) {
		this.garajes = garajes;
	}

	public String getMatriculaInmobiliariaPrincipal2() {
		return matriculaInmobiliariaPrincipal2;
	}

	public void setMatriculaInmobiliariaPrincipal2(String matriculaInmobiliariaPrincipal2) {
		this.matriculaInmobiliariaPrincipal2 = matriculaInmobiliariaPrincipal2;
	}

	public String getProposito() {
		return proposito;
	}

	public void setProposito(String proposito) {
		this.proposito = proposito;
	}

	public BigDecimal getLatitudInicial() {
		return latitudInicial;
	}

	public void setLatitudInicial(BigDecimal latitudInicial) {
		this.latitudInicial = latitudInicial;
	}

	public BigDecimal getLongitudInicial() {
		return longitudInicial;
	}

	public void setLongitudInicial(BigDecimal longitudInicial) {
		this.longitudInicial = longitudInicial;
	}

	public String getPropietario() {
		return propietario;
	}

	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}

	public String getCedulaCatastral() {
		return cedulaCatastral;
	}

	public void setCedulaCatastral(String cedulaCatastral) {
		this.cedulaCatastral = cedulaCatastral;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<MatriculaDTO> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(List<MatriculaDTO> matriculas) {

		this.matriculas = matriculas;
	}

	public AreaLoteAvaluoDTO getAreaLoteAvaluoDTO() {
		return areaLoteAvaluoDTO;
	}

	public void setAreaLoteAvaluoDTO(AreaLoteAvaluoDTO areaLoteAvaluoDTO) {
		this.areaLoteAvaluoDTO = areaLoteAvaluoDTO;
	}

	public AreaConstruccionAvaluoDTO getAreaConstruccionAvaluoDTO() {
		return areaConstruccionAvaluoDTO;
	}

	public void setAreaConstruccionAvaluoDTO(AreaConstruccionAvaluoDTO areaConstruccionAvaluoDTO) {
		this.areaConstruccionAvaluoDTO = areaConstruccionAvaluoDTO;
	}

	public Motivo getMotivoAux() {
		return motivoAux;
	}

	public void setMotivoAux(Motivo motivoAux) {
		this.motivoAux = motivoAux;
	}

	public Long getAvaluoSisgenId() {
		return avaluoSisgenId;
	}

	public void setAvaluoSisgenId(Long avaluoSisgenId) {
		this.avaluoSisgenId = avaluoSisgenId;
	}

	@Override
	public String toString() {
		return "AvaluoDTO [id=" + id + ", soloAvaluo=" + soloAvaluo + ", entidad=" + entidad + ", codigoExterno="
				+ codigoExterno + ", tipoDeInmueble=" + tipoDeInmueble + ", tipoAvaluo=" + tipoAvaluo
				+ ", objetoDelAvaluo=" + objetoDelAvaluo + ", motivo=" + motivo + ", descripcionMotivo="
				+ descripcionMotivo + ", cambioGarantia=" + cambioGarantia + ", cambioGarantiaAvaluo="
				+ cambioGarantiaAvaluo + ", divipola=" + divipola + ", sector=" + sector + ", conjunto=" + conjunto
				+ ", barrio=" + barrio + ", matriculaInmobiliariaPrincipal1=" + matriculaInmobiliariaPrincipal1
				+ ", matriculaInmobiliariaPrincipal2=" + matriculaInmobiliariaPrincipal2 + ", tipoVia=" + tipoVia
				+ ", numeroVia=" + numeroVia + ", complementoVia=" + complementoVia + ", numeroViaGeneradora="
				+ numeroViaGeneradora + ", complementoViaGeneradora=" + complementoViaGeneradora + ", placa=" + placa
				+ ", complementoPlaca=" + complementoPlaca + ", adicionalDireccion=" + adicionalDireccion
				+ ", direccionInmueble=" + direccionInmueble + ", tipoViaInforme=" + tipoViaInforme
				+ ", numeroViaInforme=" + numeroViaInforme + ", complementoViaInforme=" + complementoViaInforme
				+ ", numeroViaGeneradoraInforme=" + numeroViaGeneradoraInforme + ", complementoViaGeneradoraInforme="
				+ complementoViaGeneradoraInforme + ", placaInforme=" + placaInforme + ", adicionalDireccionInforme="
				+ adicionalDireccionInforme + ", direccionInmuebleInforme=" + direccionInmuebleInforme
				+ ", divipolaInforme=" + divipolaInforme + ", nombreRecibe=" + nombreRecibe + ", telefonoRecibe="
				+ telefonoRecibe + ", correoElectronicoRecibe=" + correoElectronicoRecibe + ", nombreAsesor="
				+ nombreAsesor + ", sucursalAsesor=" + sucursalAsesor + ", celularAsesor=" + celularAsesor
				+ ", correoElectronicoAsesor=" + correoElectronicoAsesor + ", telefonoAsesor=" + telefonoAsesor
				+ ", observacionesSolicitante=" + observacionesSolicitante + ", latitud=" + latitud + ", longitud="
				+ longitud + ", valorSolicitado=" + valorSolicitado + ", valorTotalAvaluo=" + valorTotalAvaluo
				+ ", areaTotal=" + areaTotal + ", valorUvr=" + valorUvr + ", valorAvaluoEnUvr=" + valorAvaluoEnUvr
				+ ", calificacionGarantia=" + calificacionGarantia + ", valorAsegurable=" + valorAsegurable
				+ ", valorLiquidacion=" + valorLiquidacion + ", valorHonorarios=" + valorHonorarios
				+ ", gastosTranslado=" + gastosTranslado + ", vereda=" + vereda + ", nombrePredio=" + nombrePredio
				+ ", areas=" + areas + ", fotografias=" + fotografias + ", metodosValuacion=" + metodosValuacion
				+ ", estado=" + estado + ", fechaEstado=" + fechaEstado + ", fechaEnviado=" + fechaEnviado
				+ ", cliente=" + cliente + ", perito=" + perito + ", semaforo=" + semaforo + ", duracionPausaSemaforo="
				+ duracionPausaSemaforo + ", iva=" + iva + ", creador=" + creador + ", fechaCreacion=" + fechaCreacion
				+ ", tipoCredito=" + tipoCredito + ", estadoDeConstruccion=" + estadoDeConstruccion
				+ ", formatoInforme=" + formatoInforme + ", tieneMetodologiasCalculadoras="
				+ tieneMetodologiasCalculadoras + ", tipoSubsidio=" + tipoSubsidio + ", garajes=" + garajes
				+ ", proposito=" + proposito + ", tipo=" + tipo + ", latitudInicial=" + latitudInicial
				+ ", longitudInicial=" + longitudInicial + ", propietario=" + propietario + ", cedulaCatastral="
				+ cedulaCatastral + ", matriculas=" + matriculas + ", areaLoteAvaluoDTO=" + areaLoteAvaluoDTO
				+ ", areaConstruccionAvaluoDTO=" + areaConstruccionAvaluoDTO + ", motivoAux=" + motivoAux
				+ ", avaluoSisgenId=" + avaluoSisgenId + "]";
	}
	
	

	public String getNombreMotivo() {
		return nombreMotivo;
	}

	public void setNombreMotivo(String nombreMotivo) {
		this.nombreMotivo = nombreMotivo;
	}

}
