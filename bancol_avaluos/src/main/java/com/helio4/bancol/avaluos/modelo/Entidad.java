package com.helio4.bancol.avaluos.modelo;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "entidad")
@Cacheable(true)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "bancolEntityCache")
public class Entidad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "entidad_id")
	private Long id;
	@Column(name = "nombre", nullable = false)
	private String nombre;
	@Column(name = "nit")
	private String nit;
	@Column(name = "prefijo")
	private String prefijo;
	@Column(name = "porcentaje_valor_asegurable")
	private Double porcentajeValorAsegurable;
	@Column(name = "fecha_creacion")
	private Date fechaCreacion;
	@Column(name = "activo")
	private Boolean activo;
	@Column(name = "cobrado_por_bancol")
	private Boolean cobradoPorBancol;
	@Column(name = "codigo_BUA")
	private Integer codigoBUA;
	@Column(name = "duracion_semaforo_verde")
	private Long duracionSemaforoVerde;
	@Column(name = "duracion_semaforo_amarillo")
	private Long duracionSemaforoAmarillo;
	@Column(name = "duracion_semaforo_rojo")
	private Long duracionSemaforoRojo;
	
	@ManyToMany(mappedBy = "entidades", fetch = FetchType.LAZY)
	private List<Usuario> usuarios;

	@Column(name = "codigo_tinsa")
	private Integer codigoTinsa;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "entidad", fetch = FetchType.LAZY)
	private List<Motivo> motivos;

	public Entidad() {
	}

	public Entidad(Long id, String nombre, String nit, String prefijo, Double porcentajeValorAsegurable,
			Date fechaCreacion, Boolean activo, Long duracionSemaforoVerde, Long duracionSemaforoAmarillo,
			Long duracionSemaforoRojo/*
										 * , Set<Sucursal> sucursales,
										 * Set<Tarifa> tarifas, Set<Segmento>
										 * segmentos
										 */) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.nit = nit;
		this.prefijo = prefijo;
		this.porcentajeValorAsegurable = porcentajeValorAsegurable;
		this.fechaCreacion = fechaCreacion;
		this.activo = activo;
		this.duracionSemaforoVerde = duracionSemaforoVerde;
		this.duracionSemaforoAmarillo = duracionSemaforoAmarillo;
		this.duracionSemaforoRojo = duracionSemaforoRojo;
		/*
		 * this.sucursales = sucursales; this.tarifas = tarifas; this.segmentos
		 * = segmentos;
		 */
	}

	public void update(Long id, String nombre, String nit, String prefijo, Double porcentajeValorAsegurable,
			Date fechaCreacion, Boolean activo, Long duracionSemaforoVerde, Long duracionSemaforoAmarillo,
			Long duracionSemaforoRojo/*
										 * , Set<Sucursal> sucursales,
										 * Set<Segmento> segmentos, Set<Avaluo>
										 * avaluos
										 */) {
		this.id = id;
		this.nombre = nombre;
		this.nit = nit;
		this.prefijo = prefijo;
		this.porcentajeValorAsegurable = porcentajeValorAsegurable;
		this.fechaCreacion = fechaCreacion;
		this.activo = activo;
		this.duracionSemaforoVerde = duracionSemaforoVerde;
		this.duracionSemaforoAmarillo = duracionSemaforoAmarillo;
		this.duracionSemaforoRojo = duracionSemaforoRojo;
		/*
		 * this.sucursales = sucursales; this.segmentos = segmentos;
		 */
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getPrefijo() {
		return prefijo;
	}

	public void setPrefijo(String prefijo) {
		this.prefijo = prefijo;
	}

	public Double getPorcentajeValorAsegurable() {
		return porcentajeValorAsegurable;
	}

	public void setPorcentajeValorAsegurable(Double porcentajeValorAsegurable) {
		this.porcentajeValorAsegurable = porcentajeValorAsegurable;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Boolean getCobradoPorBancol() {
		return cobradoPorBancol;
	}

	public void setCobradoPorBancol(Boolean cobraAvaluo) {
		this.cobradoPorBancol = cobraAvaluo;
	}

	public Integer getCodigoBUA() {
		return codigoBUA;
	}

	public void setCodigoBUA(Integer codigoBUA) {
		this.codigoBUA = codigoBUA;
	}

	public Long getDuracionSemaforoVerde() {
		return duracionSemaforoVerde;
	}

	public void setDuracionSemaforoVerde(Long duracionSemaforoVerde) {
		this.duracionSemaforoVerde = duracionSemaforoVerde;
	}

	public Long getDuracionSemaforoAmarillo() {
		return duracionSemaforoAmarillo;
	}

	public void setDuracionSemaforoAmarillo(Long duracionSemaforoAmarillo) {
		this.duracionSemaforoAmarillo = duracionSemaforoAmarillo;
	}

	public Long getDuracionSemaforoRojo() {
		return duracionSemaforoRojo;
	}

	public void setDuracionSemaforoRojo(Long duracionSemaforoRojo) {
		this.duracionSemaforoRojo = duracionSemaforoRojo;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Motivo> getMotivos() {
		return motivos;
	}

	public void setMotivos(List<Motivo> motivos) {
		this.motivos = motivos;
	}

	public Integer getCodigoTinsa() {
		return codigoTinsa;
	}

	public void setCodigoTinsa(Integer codigoTinsa) {
		this.codigoTinsa = codigoTinsa;
	}

	@Override
	public String toString() {
		return nombre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		Entidad other = (Entidad) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

}
