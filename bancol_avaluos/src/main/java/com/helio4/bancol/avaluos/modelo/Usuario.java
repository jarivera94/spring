package com.helio4.bancol.avaluos.modelo;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Entity
public class Usuario {

	@EmbeddedId DocumentoIdentificacion id;
	@Column
	private String nombres;
	@Column
	private String apellidos;
	@Column(name="contrasena")
	private String contrasena;
	@Column(name="nombre_usuario")
	private String nombreUsuario;
	@Column
	private String email;
    
	@Column(name = "tipo_via")
	private String tipoVia;
	@Column(name = "numero_via")
	private String numeroVia;
	@Column(name = "complemento_via")
	private String complementoVia;
	@Column(name = "numero_via_generadora")
	private String numeroViaGeneradora;
	@Column(name = "complemento_via_generadora")
	private String complementoViaGeneradora;
	@Column(name = "placa")
	private String placa;
	@Column(name = "complemento_placa")
	private String complementoPlaca;
	@Column(name = "adiciona_direccion")
	private String adicional;
	@Column(name = "direccion")
	private String direccion;
	@Column(name = "registro")
	private String registro;
	@Column(name = "iva")
	private Boolean iva;
	@Column(name = "activo_para_asignacion")
	private Boolean activoParaAsignacion;
	@Column(name = "nombre_banco")
	private String nombreBanco;
	@Column(name = "tipo_cuenta_banco")
	private String tipoCuentaBanco;
	@Column(name = "numero_cuenta_banco")
	private String numeroCuentaBanco;
	@Column(name = "nombre_titular")
	private String nombreTitular;
	@Column(name = "tipo_identificacion_titular")
	private String tipoIdentificacionTitular;
	@Column(name = "numero_identificacion_titular")
	private String numeroIdentificacionTitular;
	@Column(name = "tipo_documento_coordinador_asignado")
	private Integer tipoDocumentoCoordinadorAsignado;
	@Column(name = "documento_coordinador_asignado")
	private Long numeroDocumentoCoordinadorAsignado;
	@Column(name = "celular")
	private String celular;
	@Column(name = "usuario_vigente")
	private Boolean usuarioVigente;
	@Column
	private BigDecimal calificacion;
	@ManyToOne
	@JoinColumn(name="rol_id", nullable = false)
	private Rol rol;
	@ManyToMany(cascade={CascadeType.ALL})
	@JoinTable(name="usuario_regional",
	joinColumns= {
			@JoinColumn(name = "tipo_documento_identificacion", referencedColumnName="tipo_documento_identificacion"),
			@JoinColumn(name = "numero_documento", referencedColumnName="numero_documento")},
	inverseJoinColumns= {@JoinColumn(name="regional", referencedColumnName="id")})
	private List<Regional> regionales;
	@ManyToMany(cascade={CascadeType.ALL})
	@JoinTable(name="usuario_entidad",
	joinColumns= {
			@JoinColumn(name = "tipo_documento_identificacion", referencedColumnName="tipo_documento_identificacion"),
			@JoinColumn(name = "numero_documento", referencedColumnName="numero_documento")},
	inverseJoinColumns= {@JoinColumn(name="entidad", referencedColumnName="entidad_id")})
	private List<Entidad> entidades;
	@ManyToMany(cascade={CascadeType.ALL})
	@JoinTable(name="usuario_tipo_avaluo",
	joinColumns= {
			@JoinColumn(name = "tipo_documento_identificacion", referencedColumnName="tipo_documento_identificacion"),
			@JoinColumn(name = "numero_documento", referencedColumnName="numero_documento")},
	inverseJoinColumns= {@JoinColumn(name="tipo_avaluo", referencedColumnName="tipo_avaluo_id")})
	private List<TipoAvaluo> tipoAvaluos;
	
	
	/**
	 * Se emplea para poder asignarle multiples divipolas a los peritos, para
	 * poder asignarle avaluos en multiples lugares.
	 * */
	@ManyToMany(cascade={CascadeType.ALL})
	@JoinTable(name="usuario_divipola",
	joinColumns= {
			@JoinColumn(name = "tipo_documento_identificacion", referencedColumnName="tipo_documento_identificacion"),
			@JoinColumn(name = "numero_documento", referencedColumnName="numero_documento")},
	inverseJoinColumns= {@JoinColumn(name="divipola", referencedColumnName="divipola_id")})
	private List<Divipola> divipolas;

	@Embeddable
	public static class DocumentoIdentificacion implements Serializable {
		/**
		 *
		 */
		private static final long serialVersionUID = -2042199388163840319L;
		@Column(name="tipo_documento_identificacion")
		protected Integer tipoDocumentoIdentificacion;
		@Column(name="numero_documento")
		protected Long numeroDocumento;

		public DocumentoIdentificacion() {}

		public DocumentoIdentificacion(Integer tipoDocumentoIdentificacion, Long numeroDocumento) {
			this.tipoDocumentoIdentificacion = tipoDocumentoIdentificacion;
			this.numeroDocumento = numeroDocumento;
		}

		public Integer getTipoDocumentoIdentificacion() {
			return this.tipoDocumentoIdentificacion;
		}

		public Long getNumeroDocumento() {
			return this.numeroDocumento;
		}

		public void setTipoDocumentoIdentificacion(Integer tipoDocumentoIdentificacion) {
			this.tipoDocumentoIdentificacion = tipoDocumentoIdentificacion;
		}

		public void setNumeroDocumento(Long numeroDocumento) {
			this.numeroDocumento = numeroDocumento;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime
					* result
					+ ((numeroDocumento == null) ? 0 : numeroDocumento
							.hashCode());
			result = prime
					* result
					+ ((tipoDocumentoIdentificacion == null) ? 0
							: tipoDocumentoIdentificacion.hashCode());
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
			DocumentoIdentificacion other = (DocumentoIdentificacion) obj;
			if (numeroDocumento == null) {
				if (other.numeroDocumento != null)
					return false;
			} else if (!numeroDocumento.equals(other.numeroDocumento))
				return false;
			if (tipoDocumentoIdentificacion == null) {
				if (other.tipoDocumentoIdentificacion != null)
					return false;
			} else if (!tipoDocumentoIdentificacion
					.equals(other.tipoDocumentoIdentificacion))
				return false;
			return true;
		}

	}

	public Usuario() {
		this.id = new DocumentoIdentificacion();
	}

	public DocumentoIdentificacion getId() {
		return id;
	}

	public void setId(DocumentoIdentificacion id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getTipoDocumentoIdentificacion() {
		return this.id.tipoDocumentoIdentificacion;
	}

	public Long getNumeroDocumento() {
		return this.id.numeroDocumento;
	}


	public String getTipoVia() {
		return this.tipoVia;
	}

	public String getNumeroVia() {
		return this.numeroVia;
	}

	public String getComplementoVia() {
		return this.complementoVia;
	}

	public String getNumeroViaGeneradora() {
		return this.numeroViaGeneradora;
	}

	public String getComplementoViaGeneradora() {
		return this.complementoViaGeneradora;
	}

	public String getPlaca() {
		return this.placa;
	}

	public String getComplementoPlaca() {
		return this.complementoPlaca;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setTipoDocumentoIdentificacion(Integer tipoDocumentoIdentificacion) {
		this.id.tipoDocumentoIdentificacion = tipoDocumentoIdentificacion;
	}

	public void setNumeroDocumento(Long numeroDocumento) {
		this.id.numeroDocumento = numeroDocumento;
	}

	public void setTipoVia(String tipoVia) {
		this.tipoVia = tipoVia;
	}

	public void setNumeroVia(String numeroVia) {
		this.numeroVia = numeroVia;
	}

	public void setComplementoVia(String complementoVia) {
		this.complementoVia = complementoVia;
	}

	public void setNumeroViaGeneradora(String numeroViaGeneradora) {
		this.numeroViaGeneradora = numeroViaGeneradora;
	}

	public void setComplementoViaGeneradora(String complementoViaGeneradora) {
		this.complementoViaGeneradora = complementoViaGeneradora;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public void setComplementoPlaca(String complementoPlaca) {
		this.complementoPlaca = complementoPlaca;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public Boolean getIva() {
		return iva;
	}

	public void setIva(Boolean iva) {
		this.iva = iva;
	}

	public BigDecimal getCalificacion() {
		return this.calificacion;
	}

	public void setCalificacion(BigDecimal calificacion) {
		this.calificacion = calificacion;
	}
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}



	/*public Set<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(Set<Evento> eventos) {
		this.eventos = eventos;
	}*/

	/*public List<Avaluo> getAvaluos() {
		return avaluos;
	}

	public void setAvaluos(List<Avaluo> avaluos) {
		this.avaluos = avaluos;
	}*/

	public String getAdicional() {
		return adicional;
	}

	public void setAdicional(String adicional) {
		this.adicional = adicional;
	}

	public List<Regional> getRegionales() {
		return regionales;
	}

	public void setRegionales(List<Regional> regionales) {
		this.regionales = regionales;
	}

	public List<Entidad> getEntidades() {
		return entidades;
	}

	public void setEntidades(List<Entidad> entidades) {
		this.entidades = entidades;
	}

	public List<TipoAvaluo> getTipoAvaluos() {
		return tipoAvaluos;
	}

	public void setTipoAvaluos(List<TipoAvaluo> tipoAvaluos) {
		this.tipoAvaluos = tipoAvaluos;
	}

	public String getNombreBanco() {
		return nombreBanco;
	}

	public void setNombreBanco(String nombreBanco) {
		this.nombreBanco = nombreBanco;
	}

	public String getTipoCuentaBanco() {
		return tipoCuentaBanco;
	}

	public void setTipoCuentaBanco(String tipoCuentaBanco) {
		this.tipoCuentaBanco = tipoCuentaBanco;
	}

	public String getNumeroCuentaBanco() {
		return numeroCuentaBanco;
	}

	public void setNumeroCuentaBanco(String numeroCuentaBanco) {
		this.numeroCuentaBanco = numeroCuentaBanco;
	}

	public String getNombreTitular() {
		return nombreTitular;
	}

	public void setNombreTitular(String nombreTitular) {
		this.nombreTitular = nombreTitular;
	}

	public String getTipoIdentificacionTitular() {
		return tipoIdentificacionTitular;
	}

	public void setTipoIdentificacionTitular(String tipoIdentificacionTitular) {
		this.tipoIdentificacionTitular = tipoIdentificacionTitular;
	}

	public String getNumeroIdentificacionTitular() {
		return numeroIdentificacionTitular;
	}

	public void setNumeroIdentificacionTitular(String numeroIdentificacionTitular) {
		this.numeroIdentificacionTitular = numeroIdentificacionTitular;
	}

	public Integer getTipoDocumentoCoordinadorAsignado() {
		return tipoDocumentoCoordinadorAsignado;
	}

	public void setTipoDocumentoCoordinadorAsignado(
			Integer tipoDocumentoCoordinadorAsignado) {
		this.tipoDocumentoCoordinadorAsignado = tipoDocumentoCoordinadorAsignado;
	}

	public Long getNumeroDocumentoCoordinadorAsignado() {
		return numeroDocumentoCoordinadorAsignado;
	}

	public void setNumeroDocumentoCoordinadorAsignado(
			Long numeroDocumentoCoordinadorAsignado) {
		this.numeroDocumentoCoordinadorAsignado = numeroDocumentoCoordinadorAsignado;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}
	

	public Boolean getActivoParaAsignacion() {
		return activoParaAsignacion;
	}

	public void setActivoParaAsignacion(Boolean activoParaAsignacion) {
		this.activoParaAsignacion = activoParaAsignacion;
	}

	public Boolean getUsuarioVigente(){
		return usuarioVigente;
	}

	public void setUsuarioVigente(Boolean usuarioVigente){
		this.usuarioVigente = usuarioVigente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((nombreUsuario == null) ? 0 : nombreUsuario.hashCode());
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
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nombreUsuario == null) {
			if (other.nombreUsuario != null)
				return false;
		} else if (!nombreUsuario.equals(other.nombreUsuario))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombres=" + nombres + ", apellidos="
				+ apellidos + ", contrasena=" + contrasena + ", nombreUsuario="
				+ nombreUsuario + ", email=" + email + ", tipoVia=" + tipoVia
				+ ", numeroVia=" + numeroVia + ", complementoVia="
				+ complementoVia + ", numeroViaGeneradora="
				+ numeroViaGeneradora + ", complementoViaGeneradora="
				+ complementoViaGeneradora + ", placa=" + placa
				+ ", complementoPlaca=" + complementoPlaca + ", direccion="
				+ direccion + ", calificacion=" + calificacion + ", rol=" + rol;
	}

	/**
	 * @return the divipolas
	 */
	public List<Divipola> getDivipolas() {
		return divipolas;
	}

	/**
	 * @param divipolas the divipolas to set
	 */
	public void setDivipolas(List<Divipola> divipolas) {
		this.divipolas = divipolas;
	}
}
