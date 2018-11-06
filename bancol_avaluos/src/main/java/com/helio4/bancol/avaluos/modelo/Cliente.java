package com.helio4.bancol.avaluos.modelo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.helio4.bancol.avaluos.modelo.Usuario.DocumentoIdentificacion;

@Entity
@Table(name="cliente")
public class Cliente {
	
	@EmbeddedId DocumentoIdentificacion id;
	
	@Column(name = "primer_nombre_solicitante", nullable=false)
	private String primerNombre;
	@Column(name = "segundo_nombre_solicitante")
	private String segundoNombre;
	@Column(name = "primer_apellido_solicitante", nullable=false)
	private String primerApellido;
	@Column(name = "segundo_apellido_solicitante")
	private String segundoApellido;
	@Column(name = "pais_origen")
	private String paisOrigen;
	@Column(name = "clasificacion_fiscal")
	private String clasificacionFiscal;
	@Column(name = "razon_social")
	private String razonSocial;
	@Column
	private String regimen;
	@Column(name = "codigo_ciiu")
	private String codigoCIIU;
	@Column(name = "telefono_solicitante")
	private String telefonoSolicitante;
	@Column(name = "celular_solicitante")
	private String celularSolicitante;
	@Column(name = "correo_electronico_solicitante")
	private String correoElectronicoSolicitante;
	@Column(name = "direccion_de_contacto_solicitante")
	private String direccionDeContactoSolicitante;
	@ManyToOne
	@JoinColumn(name = "divipola")
	private Divipola divipola;
	
	@Column(name = "empleado_entidad")
	private boolean empleadoEntidad;
	@Column(name = "cliente_exterior")
	private boolean clienteExterior;
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name = "segmento")
	private Segmento segmento;
	@OneToMany(mappedBy="cliente")
	private Set<Avaluo> avaluos;

	public Cliente() {
	    super();
		//this.id = new DocumentoIdentificacion();
	}

	public void update(DocumentoIdentificacion id, String primerNombre,
			String segundoNombre, String primerApellido,
			String segundoApellido, String paisOrigen,
			String clasificacionFiscal, String razonSocial, String regimen,
			String codigoCIIU, String telefonoSolicitante,
			String celularSolicitante, String correoElectronicoSolicitante,
			String direccionDeContactoSolicitante,
			Divipola divipola,
			boolean empleadoEntidad,
			boolean clienteExterior, Segmento segmento,
			Set<Avaluo> avaluos) {
		this.id = id;
		this.primerNombre = primerNombre;
		this.segundoNombre = segundoNombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		this.paisOrigen = paisOrigen;
		this.clasificacionFiscal = clasificacionFiscal;
		this.razonSocial = razonSocial;
		this.regimen = regimen;
		this.codigoCIIU = codigoCIIU;
		this.telefonoSolicitante = telefonoSolicitante;
		this.celularSolicitante = celularSolicitante;
		this.correoElectronicoSolicitante = correoElectronicoSolicitante;
		this.direccionDeContactoSolicitante = direccionDeContactoSolicitante;
		this.divipola = divipola;
		this.empleadoEntidad = empleadoEntidad;
		this.clienteExterior = clienteExterior;
		this.segmento = segmento;
		this.avaluos = avaluos;
	}

	public DocumentoIdentificacion getId() {
		return id;
	}

	public void setId(DocumentoIdentificacion id) {
		this.id = id;
	}

	public Integer getTipoDocumentoIdentificacion() {
		return id.tipoDocumentoIdentificacion;
	}

	public void setTipoDocumentoIdentificacion(Integer tipoDocumentoIdentificacion) {
		this.id.tipoDocumentoIdentificacion = tipoDocumentoIdentificacion;
	}

	public Long getNumeroDocumento() {
		return id.numeroDocumento;
	}

	public void setNumeroDocumento(Long numeroDocumento) {
		this.id.numeroDocumento = numeroDocumento;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getPaisOrigen() {
		return paisOrigen;
	}

	public void setPaisOrigen(String paisOrigen) {
		this.paisOrigen = paisOrigen;
	}

	public String getClasificacionFiscal() {
		return clasificacionFiscal;
	}

	public void setClasificacionFiscal(String clasificacionFiscal) {
		this.clasificacionFiscal = clasificacionFiscal;
	}

	public String getRazonSocial() {
		return this.razonSocial;
	}

	public String getRegimen() {
		return this.regimen;
	}

	public String getCodigoCIIU() {
		return this.codigoCIIU;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public void setRegimen(String regimen) {
		this.regimen = regimen;
	}

	public void setCodigoCIIU(String codigoCIIU) {
		this.codigoCIIU = codigoCIIU;
	}

	public String getTelefonoSolicitante() {
		return telefonoSolicitante;
	}

	public void setTelefonoSolicitante(String telefonoSolicitante) {
		this.telefonoSolicitante = telefonoSolicitante;
	}

	public String getCelularSolicitante() {
		return celularSolicitante;
	}

	public void setCelularSolicitante(String celularSolicitante) {
		this.celularSolicitante = celularSolicitante;
	}

	public String getCorreoElectronicoSolicitante() {
		return correoElectronicoSolicitante;
	}

	public void setCorreoElectronicoSolicitante(String correoElectronicoSolicitante) {
		this.correoElectronicoSolicitante = correoElectronicoSolicitante;
	}

	public String getDireccionDeContactoSolicitante() {
		return direccionDeContactoSolicitante;
	}

	public void setDireccionDeContactoSolicitante(
			String direccionDeContactoSolicitante) {
		this.direccionDeContactoSolicitante = direccionDeContactoSolicitante;
	}

	public Divipola getDivipola() {
		return divipola;
	}

	public void setDivipola(Divipola divipola) {
		this.divipola = divipola;
	}

	public boolean isEmpleadoEntidad() {
		return empleadoEntidad;
	}

	public void setEmpleadoEntidad(boolean empleadoEntidad) {
		this.empleadoEntidad = empleadoEntidad;
	}

	public boolean isClienteExterior() {
		return clienteExterior;
	}

	public void setClienteExterior(boolean clienteExterior) {
		this.clienteExterior = clienteExterior;
	}

	public Segmento getSegmento() {
		return segmento;
	}

	public void setSegmento(Segmento segmento) {
		this.segmento = segmento;
	}

	public Set<Avaluo> getAvaluos() {
		return avaluos;
	}

	public void setAvaluos(Set<Avaluo> avaluos) {
		this.avaluos = avaluos;
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
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
