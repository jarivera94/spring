package com.helio4.bancol.avaluos.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class EntidadDTO implements Serializable, Cloneable {

    private static final long serialVersionUID = -4015246748332474200L;
    private Long id;
	private String nombre;
	private String nit;
	private String prefijo;
	private Double porcentajeValorAsegurable;
	private Date fechaCreacion;
	private Boolean activo;
	private Boolean cobradoPorBancol;
	private Integer codigoBUA;
	private Long duracionSemaforoVerde;
	private Long duracionSemaforoAmarillo;
	private Long duracionSemaforoRojo;
	private Set<SucursalDTO> sucursales;
	private Set<SegmentoDTO> segmentos;
	// Campo Adicional
	private String avaluosRelacionados;
	private String sucursalesRelacionadas;
	private String segmentosRelacionados;
	private String tarifasRelacionados;
	
	public EntidadDTO() {
	}

    public EntidadDTO(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

	public EntidadDTO(Long id, String nombre, String nit, String prefijo,
            Double porcentajeValorAsegurable, Date fechaCreacion,
            Boolean activo, Boolean cobradoPorBancol,
            Long duracionSemaforoVerde, Long duracionSemaforoAmarillo,
            Long duracionSemaforoRojo, Set<SucursalDTO> sucursales,
            Set<SegmentoDTO> segmentos,
            String avaluosRelacionados, String sucursalesRelacionadas,
            String segmentosRelacionados, String tarifasRelacionados) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.nit = nit;
        this.prefijo = prefijo;
        this.porcentajeValorAsegurable = porcentajeValorAsegurable;
        this.fechaCreacion = fechaCreacion;
        this.activo = activo;
        this.cobradoPorBancol = cobradoPorBancol;
        this.duracionSemaforoVerde = duracionSemaforoVerde;
        this.duracionSemaforoAmarillo = duracionSemaforoAmarillo;
        this.duracionSemaforoRojo = duracionSemaforoRojo;
        this.sucursales = sucursales;
        this.segmentos = segmentos;
        this.avaluosRelacionados = avaluosRelacionados;
        this.sucursalesRelacionadas = sucursalesRelacionadas;
        this.segmentosRelacionados = segmentosRelacionados;
        this.tarifasRelacionados = tarifasRelacionados;
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

	public Set<SucursalDTO> getSucursales() {
		return sucursales;
	}

	public void setSucursales(Set<SucursalDTO> sucursales) {
		this.sucursales = sucursales;
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

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean estado) {
		activo = estado;
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

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	public Set<SegmentoDTO> getSegmentos() {
		return segmentos;
	}

	public void setSegmentos(Set<SegmentoDTO> segmentos) {
		this.segmentos = segmentos;
	}
	
	// Campo adicional
	public String getAvaluosRelacionados() {
		return avaluosRelacionados;
	}

	public void setAvaluosRelacionados(String avaluosRelacionados) {
		this.avaluosRelacionados = avaluosRelacionados;
	}
	
	public String getSucursalesRelacionadas() {
		return sucursalesRelacionadas;
	}

	public void setSucursalesRelacionadas(String sucursalesRelacionadas) {
		this.sucursalesRelacionadas = sucursalesRelacionadas;
	}
	
	public String getSegmentosRelacionados() {
		return segmentosRelacionados;
	}

	public void setSegmentosRelacionados(String segmentosRelacionados) {
		this.segmentosRelacionados = segmentosRelacionados;
	}
	
	public String getTarifasRelacionados() {
		return tarifasRelacionados;
	}

	public void setTarifasRelacionados(String tarifasRelacionados) {
		this.tarifasRelacionados = tarifasRelacionados;
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
	
	@Override
	public String toString() {
		return String.format("%s[id=%d]", getClass().getSimpleName(), getId());
	}

	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        EntidadDTO other = (EntidadDTO) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        EntidadDTO clone = new EntidadDTO(id, nombre, nit, prefijo,
                porcentajeValorAsegurable, fechaCreacion, activo,
                cobradoPorBancol, duracionSemaforoVerde,
                duracionSemaforoAmarillo, duracionSemaforoRojo, sucursales,
                segmentos, avaluosRelacionados,
                sucursalesRelacionadas, segmentosRelacionados,
                tarifasRelacionados);
        return clone;
    }

}
