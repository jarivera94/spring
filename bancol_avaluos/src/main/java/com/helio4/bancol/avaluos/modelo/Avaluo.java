package com.helio4.bancol.avaluos.modelo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.helio4.bancol.avaluos.dto.EventoDTO;

@Entity
@Table(name = "avaluo", uniqueConstraints = @UniqueConstraint(columnNames = { "entidad_id", "codigo_externo",
		"cambio_garantia" }))
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Avaluo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "avaluo_id")
	private Long id;
	@Column(name = "solo_avaluo", nullable = false)
	private Boolean soloAvaluo;
	@ManyToOne
	@JoinColumn(name = "entidad_id", nullable = false)
	private Entidad entidad;
	@Column(name = "codigo_externo", nullable = false)
	private String codigoExterno;
	@ManyToOne
	@JoinColumn(name = "tipo_de_inmueble", nullable = false)
	private TipoInmueble tipoDeInmueble;
	@ManyToOne
	@JoinColumn(name = "tipo_avaluo", nullable = false)
	private TipoAvaluo tipoAvaluo;
	@Column(name = "objeto_del_avaluo")
	private Integer objetoDelAvaluo;
	@Column(name = "cambio_garantia", columnDefinition = "integer default 0")
	private Integer cambioGarantia;
	@Column(name = "cambio_garantia_avaluo_id")
	private Long cambioGarantiaAvaluo;
	@Column(name = "compra_cartera")
	private Boolean compraCartera;
	@ManyToOne
	@JoinColumn(name = "divipola", nullable = false)
	private Divipola divipola;
	@Column
	private Integer sector;
	@Column(name = "nombre_del_conjunto")
	private String conjunto;
	@Column
	private String barrio;
	@Column(name = "matricula_inmobiliaria_principal_1")
	private String matriculaInmobiliariaPrincipal1;
	@Column(name = "matricula_inmobiliaria_principal_2")
	private String matriculaInmobiliariaPrincipal2;
	@Column(name = "tipo_via", nullable = false)
	private String tipoVia;
	@Column(name = "numero_via", nullable = false)
	private String numeroVia;
	@Column(name = "complemento_via")
	private String complementoVia;
	@Column(name = "numero_via_generadora", nullable = false)
	private String numeroViaGeneradora;
	@Column(name = "complemento_via_generadora")
	private String complementoViaGeneradora;
	@Column
	private String placa;
	@Column(name = "adicional_direccion")
	private String adicionalDireccion;
	@Column(name = "direccion_inmueble")
	private String direccionInmueble;

	/** Direccion del informe */
	@Column(name = "tipo_via_informe")
	private String tipoViaInforme;
	@Column(name = "numero_via_informe")
	private String numeroViaInforme;
	@Column(name = "complemento_via_informe")
	private String complementoViaInforme;
	@Column(name = "numero_via_generadora_informe")
	private String numeroViaGeneradoraInforme;
	@Column(name = "complemento_via_generadora_informe")
	private String complementoViaGeneradoraInforme;
	@Column(name = "placa_informe")
	private String placaInforme;
	@Column(name = "adicional_direccion_informe")
	private String adicionalDireccionInforme;
	@Column(name = "direccion_inmueble_informe")
	private String direccionInmuebleInforme;
	@ManyToOne
	@JoinColumn(name = "divipola_informe")
	private Divipola divipolaInforme;

	@Column(name = "nombre_persona_que_recibe_el_avaluo", nullable = false)
	private String nombreRecibe;
	@Column(name = "telefono_persona_que_recibe_el_avaluo", nullable = false)
	private String telefonoRecibe;
	@Column(name = "correo_electronico_persona_que_recibe_el_avaluo", nullable = false)
	private String correoElectronicoRecibe;
	@Column(name = "nombre_asesor", nullable = false)
	private String nombreAsesor;
	@Column(name = "sucursal")
	private String sucursalAsesor;
	@Column(name = "celular_asesor", nullable = false)
	private String celularAsesor;
	@Column(name = "correo_electronico_asesor", nullable = false)
	private String correoElectronicoAsesor;
	@Column(name = "telefono_asesor", nullable = false)
	private String telefonoAsesor;
	@Column(name = "observaciones_solicitante")
	private String observacionesSolicitante;
	@Column
	private BigDecimal latitud;
	@Column
	private BigDecimal longitud;
	@Column(name = "valor_solicitado")
	private BigDecimal valorSolicitado;
	@Column(name = "valor_total_avaluo")
	private BigDecimal valorTotalAvaluo;
	@Column(name = "area_total")
	private BigDecimal areaTotal;
	@Column(name = "valor_uvr")
	private BigDecimal valorUvr;
	@Column(name = "valor_avaluo_en_uvr")
	private BigDecimal valorAvaluoEnUvr;
	@Column(name = "calificacion_garantia")
	private Integer calificacionGarantia;
	@Column(name = "valor_asegurable")
	private BigDecimal valorAsegurable;
	@Column(name = "valor_liquidacion")
	private BigDecimal valorLiquidacion;
	@Column(name = "valor_honorarios")
	private BigDecimal valorHonorarios;
	@Column(name = "gastos_translado")
	private BigDecimal gastosTranslado;
	@Column(name = "vereda")
	private String vereda;
	@Column(name = "nombre_predio")
	private String nombrePredio;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "avaluo", fetch = FetchType.LAZY)
	private Set<Area> areas;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "avaluo", fetch = FetchType.LAZY)
	private Set<Fotografia> fotografias;
	@OneToMany(mappedBy = "avaluo", fetch = FetchType.EAGER)
	private List<MetodoValuacion> metodosValuacion;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "avaluo", fetch = FetchType.LAZY)
	@OrderBy("fechaEstado")
	private Set<EstadoAvaluo> estadosAvaluo;
	@Transient
	private EstadoAvaluo estado;
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumns({ @JoinColumn(name = "cliente_tipo_documento", referencedColumnName = "tipo_documento_identificacion"),
			@JoinColumn(name = "cliente_numero_documento", referencedColumnName = "numero_documento") })
	private Cliente cliente;
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumns({ @JoinColumn(name = "perito_tipo_documento", referencedColumnName = "tipo_documento_identificacion"),
			@JoinColumn(name = "perito_numero_documento", referencedColumnName = "numero_documento") })
	private Usuario perito;
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumns({ @JoinColumn(name = "creador_tipo_documento", referencedColumnName = "tipo_documento_identificacion"),
			@JoinColumn(name = "creador_numero_documento", referencedColumnName = "numero_documento") })
	private Usuario creador;
	@Column(name = "fecha_creacion")
	private Date fechaCreacion;
	@Column(name = "duracion_pausa_semaforo")
	private Long duracionPausaSemaforo;
	@Column(name = "iva")
	private BigDecimal iva;
	@Column(name = "tipo_credito")
	private String tipoCredito;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "avaluo")
	private FormatoInforme formatoInforme;

	@OneToMany(mappedBy = "avaluo", fetch = FetchType.LAZY)
	private List<Garaje> garajes;

	@Column(name = "proposito")
	private String proposito;

	@Column(name = "tipo")
	private String tipo;

	@Column(name = "latitud_inicial")
	private BigDecimal latitudInicial;

	@Column(name = "longitud_inicial")
	private BigDecimal longitudInicial;

	@Column(name = "propietario")
	private String propietario;

	@Column(name = "cedula_catastral")
	private String cedulaCatastral;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "avaluo", fetch = FetchType.LAZY)
	private List<Matricula> matriculas;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "avaluo")
	private AreaConstruccionAvaluo areaConstruccionAvaluo;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "avaluo")
	private AreaLoteAvaluo areaLoteAvaluo;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "subsidio_id", referencedColumnName = "id")
	private Subsidio subsidio;

	@Column(name = "motivo_id")
	private Long motivoId;
	
	@ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.EAGER)
    @JoinColumn(name = "motivo_id", referencedColumnName = "codigo", insertable = false, updatable =  false)
    private Motivo motivo;

	@Column(name = "avaluo_sisgen_id")
	private Long avaluoSisgenId;

	public Avaluo() {
		estado = EstadoAvaluo.estadoInicial(this);
	}

	public void update(Long id, Entidad entidad, String codigoExterno, TipoInmueble tipoDeInmueble,
			TipoAvaluo tipoAvaluo, Integer objetoDelAvaluo, Integer cambioGarantia, Long cambioGarantiaAvaluo,
			Boolean compraCartera, Divipola divipola, Integer sector, String conjunto, String barrio,
			String matriculaInmobiliariaPrincipal1, String tipoVia, String numeroVia, String complementoVia,
			String numeroViaGeneradora, String complementoViaGeneradora, String placa, String adicionalDireccion,
			String direccionInmueble, String nombreRecibe, String telefonoRecibe, String correoElectronicoRecibe,
			String nombreAsesor, String sucursalAsesor, String celularAsesor, String correoElectronicoAsesor,
			String telefonoAsesor, String observacionesSolicitante, BigDecimal latitud, BigDecimal longitud,
			BigDecimal valorSolicitado, BigDecimal valorTotalAvaluo, BigDecimal areaTotal, BigDecimal valorUvr,
			BigDecimal valorAvaluoEnUvr, Integer calificacionGarantia, BigDecimal valorAsegurable,
			BigDecimal valorLiquidacion, BigDecimal gastosTranslado, BigDecimal valorHonorarios, Set<Area> areas,
			Set<Fotografia> fotografias, List<MetodoValuacion> metodosValuacion, List<Garaje> garajes,
			Set<EstadoAvaluo> estadosAvaluo, EstadoAvaluo estado, Cliente cliente, Usuario perito,
			Long duracionPausaSemaforo, BigDecimal iva, String tipoCredito, FormatoInforme formatoInforme) {
		this.id = id;
		this.entidad = entidad;
		this.codigoExterno = codigoExterno;
		this.tipoDeInmueble = tipoDeInmueble;
		this.tipoAvaluo = tipoAvaluo;
		this.objetoDelAvaluo = objetoDelAvaluo;
		this.compraCartera = compraCartera;
		this.cambioGarantia = cambioGarantia;
		this.cambioGarantiaAvaluo = cambioGarantiaAvaluo;
		this.divipola = divipola;
		this.sector = sector;
		this.conjunto = conjunto;
		this.barrio = barrio;
		this.matriculaInmobiliariaPrincipal1 = matriculaInmobiliariaPrincipal1;
		this.tipoVia = tipoVia;
		this.numeroVia = numeroVia;
		this.complementoVia = complementoVia;
		this.numeroViaGeneradora = numeroViaGeneradora;
		this.complementoViaGeneradora = complementoViaGeneradora;
		this.placa = placa;
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
		this.areaTotal = areaTotal;
		this.valorUvr = valorUvr;
		this.valorAvaluoEnUvr = valorAvaluoEnUvr;
		this.calificacionGarantia = calificacionGarantia;
		this.valorAsegurable = valorAsegurable;
		this.valorLiquidacion = valorLiquidacion;
		this.valorHonorarios = valorHonorarios;
		this.gastosTranslado = gastosTranslado;
		this.areas = areas;
		this.fotografias = fotografias;
		this.estadosAvaluo = estadosAvaluo;
		this.estado = estado;
		this.cliente = cliente;
		this.perito = perito;
		this.duracionPausaSemaforo = duracionPausaSemaforo;
		this.iva = iva;
		this.tipoCredito = tipoCredito;
		this.formatoInforme = formatoInforme;
		this.garajes = garajes;
	}

	public EventoDTO programarCita(Date horaInicio, Date horaFin, String nombreRecibeVisita, Usuario usuario)
			throws EstadoIlegalException {
		return estado.programarCita(horaInicio, horaFin, nombreRecibeVisita, usuario);
	}

	public void solicitarDevolucion(String justificacion, Usuario usuario) throws EstadoIlegalException {
		estado.solicitarDevolucion(justificacion, usuario);
	}

	public void devolver(String justificacion, Usuario usuario) throws EstadoIlegalException {
		estado.devolver(justificacion, usuario);
	}

	public void reactivar(Usuario usuario) throws EstadoIlegalException {
		estado.reactivar(usuario);
	}

	public void confirmarVisita(Usuario usuario) throws EstadoIlegalException {
		estado.confirmarVisita(usuario);
	}

	public void confirmarDocumentosCompletos(Usuario usuario) throws EstadoIlegalException {
		estado.confirmarDocumentosCompletos(usuario);
	}

	public void notificarHonorarios(Usuario usuario) throws EstadoIlegalException {
		estado.notificarHonorarios(usuario);
	}

	public void confirmarPago(Usuario usuario) throws EstadoIlegalException {
		estado.confirmarPago(usuario);
	}

	public void enviar(Usuario usuario) throws EstadoIlegalException {
		estado.enviar(usuario);
	}

	public void solicitarCorreciones(String correciones, Usuario usuario) throws EstadoIlegalException {
		estado.solicitarCorreciones(correciones, usuario);
	}

	public void enviarAComite(Usuario usuario) throws EstadoIlegalException {
		estado.enviarAComite(usuario);
	}

	public void aprobar(Usuario usuario) throws EstadoIlegalException {
		estado.aprobar(usuario);
	}

	public void cancelarAvaluo(Usuario usuario) throws EstadoIlegalException {
		estado.cancelarAvaluo(usuario);
	}

	@PreUpdate
	public void preUpdate() {
	}

	@PrePersist
	public void prePersist() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getSoloAvaluo() {
		return soloAvaluo;
	}

	public void setSoloAvaluo(Boolean soloAvaluo) {
		this.soloAvaluo = soloAvaluo;
	}

	public Entidad getEntidad() {
		return entidad;
	}

	public void setEntidad(Entidad entidad) {
		this.entidad = entidad;
	}

	public String getCodigoExterno() {
		return codigoExterno;
	}

	public void setCodigoExterno(String codigoExterno) {
		this.codigoExterno = codigoExterno;
	}

	public TipoAvaluo getTipoAvaluo() {
		return tipoAvaluo;
	}

	public void setTipoAvaluo(TipoAvaluo tipoAvaluo) {
		this.tipoAvaluo = tipoAvaluo;
	}

	public Integer getObjetoDelAvaluo() {
		return objetoDelAvaluo;
	}

	public void update(Long id, Entidad entidad, String codigoExterno, TipoInmueble tipoDeInmueble,
			TipoAvaluo tipoAvaluo, Integer objetoDelAvaluo, Integer cambioGarantia, Long cambioGarantiaAvaluo,
			Boolean compraCartera, Divipola divipola, Integer sector, String conjunto, String barrio,
			String matriculaInmobiliariaPrincipal1, String tipoVia, String numeroVia, String complementoVia,
			String numeroViaGeneradora, String complementoViaGeneradora, String placa, String adicionalDireccion,
			String direccionInmueble, String nombreRecibe, String telefonoRecibe, String correoElectronicoRecibe,
			String nombreAsesor, String sucursalAsesor, String celularAsesor, String correoElectronicoAsesor,
			String telefonoAsesor, String observacionesSolicitante, BigDecimal latitud, BigDecimal longitud,
			BigDecimal valorSolicitado, BigDecimal valorTotalAvaluo, BigDecimal areaTotal, BigDecimal valorUvr,
			BigDecimal valorAvaluoEnUvr, Integer calificacionGarantia, BigDecimal valorAsegurable,
			BigDecimal valorLiquidacion, BigDecimal gastosTranslado, BigDecimal valorHonorarios, Set<Area> areas,
			Set<Fotografia> fotografias, List<MetodoValuacion> metodosValuacion, List<Garaje> garajes,
			Set<EstadoAvaluo> estadosAvaluo, EstadoAvaluo estado, Cliente cliente, Usuario perito,
			Long duracionPausaSemaforo, BigDecimal iva, String tipoCredito, FormatoInforme formatoInforme,
			String proposito, String tipo, BigDecimal latitudInicial, BigDecimal longitudInicial, String propietario,
			String cedulaCatastral) {
		this.id = id;
		this.entidad = entidad;
		this.codigoExterno = codigoExterno;
		this.tipoDeInmueble = tipoDeInmueble;
		this.tipoAvaluo = tipoAvaluo;
		this.objetoDelAvaluo = objetoDelAvaluo;
		this.compraCartera = compraCartera;
		this.cambioGarantia = cambioGarantia;
		this.cambioGarantiaAvaluo = cambioGarantiaAvaluo;
		this.divipola = divipola;
		this.sector = sector;
		this.conjunto = conjunto;
		this.barrio = barrio;
		this.matriculaInmobiliariaPrincipal1 = matriculaInmobiliariaPrincipal1;
		this.tipoVia = tipoVia;
		this.numeroVia = numeroVia;
		this.complementoVia = complementoVia;
		this.numeroViaGeneradora = numeroViaGeneradora;
		this.complementoViaGeneradora = complementoViaGeneradora;
		this.placa = placa;
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
		this.areaTotal = areaTotal;
		this.valorUvr = valorUvr;
		this.valorAvaluoEnUvr = valorAvaluoEnUvr;
		this.calificacionGarantia = calificacionGarantia;
		this.valorAsegurable = valorAsegurable;
		this.valorLiquidacion = valorLiquidacion;
		this.valorHonorarios = valorHonorarios;
		this.gastosTranslado = gastosTranslado;
		this.areas = areas;
		this.fotografias = fotografias;
		this.estadosAvaluo = estadosAvaluo;
		this.estado = estado;
		this.cliente = cliente;
		this.perito = perito;
		this.duracionPausaSemaforo = duracionPausaSemaforo;
		this.iva = iva;
		this.tipoCredito = tipoCredito;
		this.formatoInforme = formatoInforme;
		this.garajes = garajes;
		this.proposito = proposito;
		this.tipo = tipo;
		this.latitudInicial = latitudInicial;
		this.longitudInicial = longitudInicial;
		this.propietario = propietario;
		this.cedulaCatastral = cedulaCatastral;
	}

	public EstadoAvaluo asignarPerito(Usuario perito, Usuario usuario) {
		this.setPerito(perito);
		return estado.asignarPerito(perito, usuario);
	}

	public void actualizarPerito(Usuario perito, Usuario usuario) {
		this.setPerito(perito);
	}

	
	public void aceptarCaso(Usuario usuario) throws EstadoIlegalException {
		estado.aceptarCaso(usuario);
	}

	public void rechazarCaso(String justificacion, Usuario usuario) throws EstadoIlegalException {
		estado.rechazarCaso(justificacion, usuario);
	}

	public void setObjetoDelAvaluo(Integer objetoDelAvaluo) {
		this.objetoDelAvaluo = objetoDelAvaluo;
	}

	public Integer getCambioGarantia() {
		return cambioGarantia;
	}

	public void setCambioGarantia(Integer cambioGarantia) {
		this.cambioGarantia = cambioGarantia;
	}

	public Long getCambioGarantiaAvaluo() {
		return cambioGarantiaAvaluo;
	}

	public void setCambioGarantiaAvaluo(Long cambioGarantiaAvaluo) {
		this.cambioGarantiaAvaluo = cambioGarantiaAvaluo;
	}

	public Boolean getCompraCartera() {
		return compraCartera;
	}

	public void setCompraCartera(Boolean compraCartera) {
		this.compraCartera = compraCartera;
	}

	public Divipola getDivipola() {
		return divipola;
	}

	public void setDivipola(Divipola divipola) {
		this.divipola = divipola;
	}

	public Integer getSector() {
		return sector;
	}

	public void setSector(Integer sector) {
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

	public String getMatriculaInmobiliariaPrincipal1() {
		return matriculaInmobiliariaPrincipal1;
	}

	public void setMatriculaInmobiliariaPrincipal1(String matriculaInmobiliariaPrincipal1) {
		this.matriculaInmobiliariaPrincipal1 = matriculaInmobiliariaPrincipal1;
	}

	public void setBarrio(String barrio) {
		this.barrio = barrio;
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

	public Divipola getDivipolaInforme() {
		return divipolaInforme;
	}

	public void setDivipolaInforme(Divipola divipolaInforme) {
		this.divipolaInforme = divipolaInforme;
	}

	public String getNombreRecibe() {
		return nombreRecibe;
	}

	public void setNombreRecibe(String nombreRecibe) {
		this.nombreRecibe = nombreRecibe;
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
		this.nombreAsesor = nombreAsesor;
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

	public TipoInmueble getTipoDeInmueble() {
		return tipoDeInmueble;
	}

	public void setTipoDeInmueble(TipoInmueble tipoDeInmueble) {
		this.tipoDeInmueble = tipoDeInmueble;
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

	public Integer getCalificacionGarantia() {
		return calificacionGarantia;
	}

	public void setCalificacionGarantia(Integer calificacionGarantia) {
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

	public Set<Area> getAreas() {
		return areas;
	}

	public void setAreas(Set<Area> areas) {
		this.areas = areas;
	}

	public Set<Fotografia> getFotografias() {
		return fotografias;
	}

	public void setFotografias(Set<Fotografia> fotografias) {
		this.fotografias = fotografias;
	}

	public List<MetodoValuacion> getMetodosValuacion() {
		return metodosValuacion;
	}

	public void setMetodosValuacion(List<MetodoValuacion> metodosValuacion) {
		this.metodosValuacion = metodosValuacion;
	}

	public Set<EstadoAvaluo> getEstadosAvaluo() {
		return estadosAvaluo;
	}

	public void setEstadosAvaluo(Set<EstadoAvaluo> estadosAvaluo) {
		this.estadosAvaluo = estadosAvaluo;
	}

	public EstadoAvaluo getEstado() {
		return estado;
	}

	public void setEstado(EstadoAvaluo estado) {
		this.estado = estado;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Usuario getCreador() {
		return creador;
	}

	public void setCreador(Usuario creador) {
		this.creador = creador;
	}

	public Usuario getPerito() {
		return perito;
	}

	public void setPerito(Usuario perito) {
		this.perito = perito;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Long getDuracionPausaSemaforo() {
		return duracionPausaSemaforo;
	}

	public void setDuracionPausaSemaforo(Long duracionPausaSemaforo) {
		this.duracionPausaSemaforo = duracionPausaSemaforo;
	}

	public FormatoInforme getFormatoInforme() {
		return formatoInforme;
	}

	public void setFormatoInforme(FormatoInforme formatoInforme) {
		this.formatoInforme = formatoInforme;
	}

	public String getTipoCredito() {
		return tipoCredito;
	}

	public void setTipoCredito(String tipoCredito) {
		this.tipoCredito = tipoCredito;
	}

	public BigDecimal getIva() {
		return iva;
	}

	public void setIva(BigDecimal iva) {
		this.iva = iva;
	}

	public List<Garaje> getGarajes() {
		return garajes;
	}

	public void setGarajes(List<Garaje> garajes) {
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

	public List<Matricula> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}

	public AreaConstruccionAvaluo getAreaConstruccionAvaluo() {
		return areaConstruccionAvaluo;
	}

	public void setAreaConstruccionAvaluo(AreaConstruccionAvaluo areaConstruccionAvaluo) {
		this.areaConstruccionAvaluo = areaConstruccionAvaluo;
	}

	public AreaLoteAvaluo getAreaLoteAvaluo() {
		return areaLoteAvaluo;
	}

	public void setAreaLoteAvaluo(AreaLoteAvaluo areaLoteAvaluo) {
		this.areaLoteAvaluo = areaLoteAvaluo;
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
		result = prime * result + ((cambioGarantia == null) ? 0 : cambioGarantia.hashCode());
		result = prime * result + ((compraCartera == null) ? 0 : compraCartera.hashCode());
		result = prime * result + ((observacionesSolicitante == null) ? 0 : observacionesSolicitante.hashCode());
		result = prime * result + ((placa == null) ? 0 : placa.hashCode());
		result = prime * result + ((sector == null) ? 0 : sector.hashCode());
		result = prime * result + ((tipoAvaluo == null) ? 0 : tipoAvaluo.hashCode());
		result = prime * result + ((tipoDeInmueble == null) ? 0 : tipoDeInmueble.hashCode());
		result = prime * result + ((tipoVia == null) ? 0 : tipoVia.hashCode());
		result = prime * result + ((valorAsegurable == null) ? 0 : valorAsegurable.hashCode());
		result = prime * result + ((valorAvaluoEnUvr == null) ? 0 : valorAvaluoEnUvr.hashCode());
		result = prime * result + ((valorLiquidacion == null) ? 0 : valorLiquidacion.hashCode());
		result = prime * result + ((gastosTranslado == null) ? 0 : gastosTranslado.hashCode());
		result = prime * result + ((valorTotalAvaluo == null) ? 0 : valorTotalAvaluo.hashCode());
		result = prime * result + ((areaTotal == null) ? 0 : areaTotal.hashCode());
		result = prime * result + ((valorUvr == null) ? 0 : valorUvr.hashCode());
		result = prime * result + ((proposito == null) ? 0 : proposito.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		result = prime * result + ((longitudInicial == null) ? 0 : longitudInicial.hashCode());
		result = prime * result + ((latitudInicial == null) ? 0 : latitudInicial.hashCode());
		result = prime * result + ((propietario == null) ? 0 : propietario.hashCode());
		result = prime * result + ((cedulaCatastral == null) ? 0 : cedulaCatastral.hashCode());

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
		Avaluo other = (Avaluo) obj;
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
		if (cambioGarantia == null) {
			if (other.cambioGarantia != null)
				return false;
		} else if (!cambioGarantia.equals(other.cambioGarantia))
			return false;
		if (compraCartera == null) {
			if (other.compraCartera != null)
				return false;
		} else if (!compraCartera.equals(other.compraCartera))
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
		if (areaTotal == null) {
			if (other.areaTotal != null)
				return false;
		} else if (!areaTotal.equals(other.areaTotal))
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
		return true;
	}

	public Subsidio getSubsidio() {
		return subsidio;
	}

	public void setSubsidio(Subsidio subsidio) {
		this.subsidio = subsidio;
	}

	public Long getMotivoId() {
		return motivoId;
	}

	public void setMotivoId(Long motivoId) {
		this.motivoId = motivoId;
	}

	public Long getAvaluoSisgenId() {
		return avaluoSisgenId;
	}

	public void setAvaluoSisgenId(Long avaluoSisgenId) {
		this.avaluoSisgenId = avaluoSisgenId;
	}
	
}
