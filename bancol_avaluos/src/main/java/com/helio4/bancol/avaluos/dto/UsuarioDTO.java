package com.helio4.bancol.avaluos.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.helio4.bancol.avaluos.modelo.Usuario;
import com.helio4.bancol.avaluos.servicio.util.Constantes;

public class UsuarioDTO {

	private Integer tipoDocumentoIdentificacion;
	private Long numeroDocumento;
	private String nombres;
	private String apellidos;
    private String nombreCompleto;
	private String contrasenaEncriptada;
	private String contrasenaNoEncriptada;
	private String nombreUsuario;
	private String email;
	private String departamento;
	//private DivipolaDTO ciudad;
	private String tipoVia;
	private String numeroVia;
	private String complementoVia;
	private String numeroViaGeneradora;
	private String complementoViaGeneradora;
	private String placa;
	private String complementoPlaca;
	private String adicional;
	private String direccion;
	private String registro;
	private Boolean iva;
	private Boolean activoParaAsignacion;
	private Boolean usuarioVigente;
	private BigDecimal calificacion;
	private RolDTO rol;
	private Integer tipoDocumentoCoordinador;
	private Long numeroDocumentoCoordinador;
	private String celular;
	private String telefono;
	private Set<EventoDTO> eventos;
	private List<RegionalDTO> regionales;
	private List<EntidadDTO> entidades;
	private List<TipoAvaluoDTO> tipoAvaluos;
	private String nombreBanco;
	private String tipoCuentaBanco;
	private String numeroCuentaBanco;
	private String nombreTitular;
	private String tipoIdentificacionTitular;
	private String numeroIdentificacionTitular;
	private List<DivipolaDTO> divipolas;

	public UsuarioDTO() {
		regionales = new ArrayList<RegionalDTO>();
		entidades = new ArrayList<EntidadDTO>();
		tipoAvaluos = new ArrayList<TipoAvaluoDTO>();

	}

    public UsuarioDTO(Boolean vigente, 
            Integer tipoDocumentoIdentificacion, Long numeroDocumento,
            String nombres, String apellidos, String email,
            String nombreUsuario, Long rolId, String nombreRol) {
        this.tipoDocumentoIdentificacion = tipoDocumentoIdentificacion;
        this.numeroDocumento = numeroDocumento;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.nombreUsuario = nombreUsuario;
        this.usuarioVigente = vigente;
        this.rol = new RolDTO();
        this.rol.setId(rolId);
        this.rol.setNombre(nombreRol);
    }

	public UsuarioDTO(Usuario usuario) {
		this.tipoDocumentoIdentificacion = usuario.getTipoDocumentoIdentificacion();
		this.numeroDocumento = usuario.getNumeroDocumento();
		this.nombres = usuario.getNombres();
		this.apellidos = usuario.getApellidos();
	}

	public UsuarioDTO(String nombres, String apellidos,String email, String telefono, String celular){
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.email = email;
		this.telefono = telefono;
		this.celular = celular;
	}

	public UsuarioDTO(Integer tipoDocumentoIdentificacion,
            Long numeroDocumento, String nombres,
            String apellidos, String email,
            String nombreUsuario, String celular,
            String direccion, Long rolId, String nombreRol) {
        this.tipoDocumentoIdentificacion = tipoDocumentoIdentificacion;
        this.numeroDocumento = numeroDocumento;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.email = email;
		this.nombreUsuario = nombreUsuario;
		this.celular = celular;
        this.direccion = direccion;
        this.rol = new RolDTO();
        this.rol.setId(rolId);
        this.rol.setNombre(nombreRol);
	}

	public Integer getTipoDocumentoIdentificacion() {
		return tipoDocumentoIdentificacion;
	}

	public Long getNumeroDocumento() {
		return numeroDocumento;
	}

	public String getNombres() {
		return this.nombres;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public String getNombreUsuario() {
		return this.nombreUsuario;
	}

	public String getEmail() {
		return this.email;
	}

	public String getDepartamento() {
		return this.departamento;
	}
	public String getTipoVia() {
		return this.tipoVia;
	}

	public String getNumeroVia() {
		if(this.numeroVia == null){
			return "";
		}else{
			return this.numeroVia;
		}
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

	public BigDecimal getCalificacion() {
		return this.calificacion;
	}

	public RolDTO getRol() {
		return this.rol;
	}

	public Set<EventoDTO> getEventos() {
		return this.eventos;
	}

	public void setTipoDocumentoIdentificacion(
			Integer tipoDocumentoIdentificacion) {
		this.tipoDocumentoIdentificacion = tipoDocumentoIdentificacion;
	}

	public void setNumeroDocumento(Long numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
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

	public void setCalificacion(BigDecimal calificacion) {
		this.calificacion = calificacion;
	}

	public void setRol(RolDTO rol) {
		this.rol = rol;
	}

	public void setEventos(Set<EventoDTO> eventos) {
		this.eventos = eventos;
	}

	public List<RegionalDTO> getRegionales() {
		return regionales;
	}

	public void setRegionales(List<RegionalDTO> regionales) {
		this.regionales = regionales;
	}

	public List<EntidadDTO> getEntidades() {
		return entidades;
	}

	public void setEntidades(List<EntidadDTO> entidades) {
		this.entidades = entidades;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		//result = prime * result
		//		+ ((nombreUsuario == null) ? 0 : nombreUsuario.hashCode());
		result = prime * result
				+ ((numeroDocumento == null) ? 0 : numeroDocumento.hashCode());
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
		UsuarioDTO other = (UsuarioDTO) obj;
		/*if (nombreUsuario == null) {
			if (other.nombreUsuario != null)
				return false;
		} else if (!nombreUsuario.equals(other.nombreUsuario))
			return false;*/
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

	@Override
	public String toString() {
		return "UsuarioDTO [tipoDocumentoIdentificacion="
				+ tipoDocumentoIdentificacion + ", numeroDocumento="
				+ numeroDocumento + ", nombres=" + nombres + ", apellidos="
				+ apellidos + ", contrasena=" + contrasenaEncriptada + ", nombreUsuario="
				+ nombreUsuario + ", email=" + email + ", departamento="
				+ departamento + ", " +  ", tipoVia=" + tipoVia
				+ ", numeroVia=" + numeroVia + ", complementoVia="
				+ complementoVia + ", numeroViaGeneradora="
				+ numeroViaGeneradora + ", complementoViaGeneradora="
				+ complementoViaGeneradora + ", placa=" + placa
				+ ", complementoPlaca=" + complementoPlaca + ", direccion="
				+ direccion + ", calificacion=" + calificacion + ", rol=" + rol
				+ ", eventos=" + eventos + "]";
	}

	public List<TipoAvaluoDTO> getTipoAvaluos() {
		return tipoAvaluos;
	}

	public void setTipoAvaluos(List<TipoAvaluoDTO> tipoAvaluos) {
		this.tipoAvaluos = tipoAvaluos;
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

	public String getAdicional() {
		return adicional;
	}

	public void setAdicional(String adicional) {
		this.adicional = adicional;
	}

	public String getContrasenaEncriptada() {
		return contrasenaEncriptada;
	}

	public void setContrasenaEncriptada(String contrasenaEncriptada) {
		this.contrasenaEncriptada = contrasenaEncriptada;
	}

	public String getContrasenaNoEncriptada() {
		return contrasenaNoEncriptada;
	}

	public void setContrasenaNoEncriptada(String contrasenaNoEncriptada) {
		this.contrasenaNoEncriptada = contrasenaNoEncriptada;
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

	public Integer getTipoDocumentoCoordinador() {
		return tipoDocumentoCoordinador;
	}

	public void setTipoDocumentoCoordinador(Integer tipoDocumentoCoordinador) {
		this.tipoDocumentoCoordinador = tipoDocumentoCoordinador;
	}

	public Long getNumeroDocumentoCoordinador() {
		return numeroDocumentoCoordinador;
	}

	public void setNumeroDocumentoCoordinador(Long numeroDocumentoCoordinador) {
		this.numeroDocumentoCoordinador = numeroDocumentoCoordinador;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
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

	/**
	 * @return the divipolas
	 */
	public List<DivipolaDTO> getDivipolas() {
		return divipolas;
	}

	/**
	 * @param divipolas the divipolas to set
	 */
	public void setDivipolas(List<DivipolaDTO> divipolas) {
		this.divipolas = divipolas;
	}

	public boolean tienePermisoCrearRol() {
        return getRol().getPermisos().contains(new PermisoDTO(Constantes.PERMISO_CREAR_ROL));
    }

    public boolean tienePermisoCrearUsuario() {
        return getRol().getPermisos().contains(new PermisoDTO(Constantes.PERMISO_CREAR_USUARIO));
    }

    public boolean tienePermisoCrearNuevaSolicitudes() {
        return getRol().getPermisos().contains(new PermisoDTO(Constantes.PERMISO_CREAR_NUEVA_SOLICITUD));
    }

    public boolean tienePermisoEditarSolicitud() {
        return getRol().getPermisos().contains(new PermisoDTO(Constantes.PERMISO_EDITAR_SOLICITUD));
    }

    public boolean tienePermisoGuardarSolicitud() {
        return getRol().getPermisos().contains(new PermisoDTO(Constantes.PERMISO_GUARDAR_SOLICITUD));
    }

    public boolean tienePermisoAsignarSolicitud() {
        return getRol().getPermisos().contains(new PermisoDTO(Constantes.PERMISO_ASIGNAR_SOLICITUD));
    }

    public boolean tienePermisoReAsignarSolicitud() {
        return getRol().getPermisos().contains(new PermisoDTO(Constantes.PERMISO_REASIGNAR_SOLICITUD));
    }

    public boolean tienePermisoAceptarCaso() {
        return getRol().getPermisos().contains(new PermisoDTO(Constantes.PERMISO_ACEPTAR_CASO));
    }

    public boolean tienePermisoRechazarCaso() {
        return getRol().getPermisos().contains(new PermisoDTO(Constantes.PERMISO_RECHAZAR_CASO));
    }

    public boolean tienePermisoProgramarCita() {
        return getRol().getPermisos().contains(new PermisoDTO(Constantes.PERMISO_PROGRAMAR_CITA));
    }

    public boolean tienePermisoReProgramarCita() {
        return getRol().getPermisos().contains(new PermisoDTO(Constantes.PERMISO_REPROGRAMAR_CITA));
    }

    public boolean tienePermisoConfirmarVisita() {
        return getRol().getPermisos().contains(new PermisoDTO(Constantes.PERMISO_CONFIRMAR_VISITA));
    }

    public boolean tienePermisoConfirmarDocumentos() {
        return getRol().getPermisos().contains(new PermisoDTO(Constantes.PERMISO_CONFIRMAR_DOCUMENTOS));
    }

    public boolean tienePermisoConfirmarPago() {
        return getRol().getPermisos().contains(new PermisoDTO(Constantes.PERMISO_CONFIRMAR_PAGO));
    }

    public boolean tienePermisoIngresarInforme() {
        return getRol().getPermisos().contains(new PermisoDTO(Constantes.PERMISO_INGRESAR_INFORME));
    }

    public boolean tienePermisoRevisarGuardarAvaluo() {
        return getRol().getPermisos().contains(new PermisoDTO(Constantes.PERMISO_REVISAR_GUARDAR_AVALUO));
    }

    public boolean tienePermisoCargarFotos() {
        return getRol().getPermisos().contains(new PermisoDTO(Constantes.PERMISO_CARGAR_FOTOS));
    }

    public boolean tienePermisoEnviarNotificacionesHonorarios() {
        return getRol().getPermisos().contains(new PermisoDTO(Constantes.PERMISO_ENVIAR_NOTIFICACIONES_HONORARIOS));
    }

    public boolean tienePermisoEnviar() {
        return getRol().getPermisos().contains(new PermisoDTO(Constantes.PERMISO_ENVIAR));
    }

    public boolean tienePermisoVerCorreccionesSolicitadas() {
        return getRol().getPermisos().contains(new PermisoDTO(Constantes.PERMISO_VER_CORRECCIONES_SOLICITADAS));
    }

    public boolean tienePermisoSolicitarDevolucion() {
        return getRol().getPermisos().contains(new PermisoDTO(Constantes.PERMISO_SOLICITAR_DEVOLUCION));
    }

    public boolean tienePermisoDevolverCaso() {
        return getRol().getPermisos().contains(new PermisoDTO(Constantes.PERMISO_DEVOLVER_CASO));
    }

    public boolean tienePermisoReactivarCaso() {
        return getRol().getPermisos().contains(new PermisoDTO(Constantes.PERMISO_REACTIVAR_CASO));
    }

    public boolean tienePermisoSolicitarCorrecciones() {
        return getRol().getPermisos().contains(new PermisoDTO(Constantes.PERMISO_SOLICITAR_CORRECCIONES));
    }

    public boolean tienePermisoComite() {
        return getRol().getPermisos().contains(new PermisoDTO(Constantes.PERMISO_COMITE));
    }

    public boolean tienePermisoEnviarAComite() {
        return getRol().getPermisos().contains(new PermisoDTO(Constantes.PERMISO_ENVIAR_A_COMITE));
    }

    public boolean tienePermisoDescargarPDFSinFirmas() {
        return getRol().getPermisos().contains(new PermisoDTO(Constantes.PERMISO_DESCARGAR_PDF_SIN_FIRMAS));
    }

    public boolean tienePermisoVerInformacionDePerito() {
        return getRol().getPermisos().contains(new PermisoDTO(Constantes.PERMISO_VER_INFORMACION_DE_PERITO));
    }

    public boolean tienePermisoEditarAvaluoDespuesDeAprobado() {
        return getRol().getPermisos().contains(new PermisoDTO(Constantes.PERMISO_EDITAR_AVALUO_DESPUES_DE_APROBADO));
    }

    public boolean tienePermisoAprobarAvaluo() {
        return getRol().getPermisos().contains(new PermisoDTO(Constantes.PERMISO_APROBAR_AVALUO));
    }

    public boolean tienePermisoVerInformesSinEditar() {
        return getRol().getPermisos().contains(new PermisoDTO(Constantes.PERMISO_VER_INFORMES_SIN_EDITAR));
    }

    public boolean tienePermisoDescargarPDFConFirmas() {
        return getRol().getPermisos().contains(new PermisoDTO(Constantes.PERMISO_DESCARGAR_PDF_CON_FIRMAS));
    }

    public boolean tienePermisoDescargarPDFAnexoMetodologias(){
        return getRol().getPermisos().contains(new PermisoDTO(Constantes.PERMISO_DESCARGAR_PDF_ANEXO_METODOLOGIAS));
    }

    public boolean tienePermisoCancelarAvaluo() {
        return getRol().getPermisos().contains(new PermisoDTO(Constantes.PERMISO_CANCELAR_AVALUO));
    }

    public boolean tienePermisoExportarBUA() {
        return getRol().getPermisos().contains(new PermisoDTO(Constantes.PERMISO_EXPORTAR_BUA));
    }

    public boolean tienePermisoExportarReportes() {
        return getRol().getPermisos().contains(new PermisoDTO(Constantes.PERMISO_EXPORTAR_REPORTES));
    }

    public boolean tienePermisoVerAgenda() {
        return getRol().getPermisos().contains(new PermisoDTO(Constantes.PERMISO_VER_AGENDA));
    }

    public boolean tienePermisoEstudioMercado() {
        return getRol().getPermisos().contains(new PermisoDTO(Constantes.PERMISO_ESTUDIO_MERCADO));
    }

    public boolean tienePermisoRechazarDevolucion() {
        return getRol().getPermisos().contains(new PermisoDTO(Constantes.PERMISO_RECHAZAR_DEVOLUCION));
    }

    public boolean tienePermisoVerFotos() {
        return getRol().getPermisos().contains(new PermisoDTO(Constantes.PERMISO_VER_FOTOS));
    }

    public boolean tienePermisoCorregirInforme() {
        return getRol().getPermisos().contains(new PermisoDTO(Constantes.PERMISO_CORREGIR_INFORME));
    }

    public boolean tienePermisoCrearEntidades() {
        return getRol().getPermisos().contains(new PermisoDTO(Constantes.PERMISO_CREAR_ENTIDADES));
    }

    public boolean tienePermisoCrearSegmentos() {
        return getRol().getPermisos().contains(new PermisoDTO(Constantes.PERMISO_CREAR_SEGMENTOS));
    }

    public boolean tienePermisoCrearSucursales() {
        return getRol().getPermisos().contains(new PermisoDTO(Constantes.PERMISO_CREAR_SUCURSALES));
    }

    public boolean tienePermisoAbogado() {
        return getRol().getPermisos().contains(new PermisoDTO(Constantes.PERMISO_ABOGADO));
    }
    public boolean tienePermisoEditarCliente() {
    	return this.rol.getPermisos().contains(new PermisoDTO(Constantes.PERMISO_EDITAR_CLIENTE));
    }

	public boolean tienePermisoCambiarFechaAprobacion() {
		return this.rol.getPermisos().contains(new PermisoDTO(Constantes.PERMISO_CAMBIAR_FECHA_APROBACION));
	}
	
	public boolean tienePermisoCambiarCamposClaves() {
		return this.rol.getPermisos().contains(new PermisoDTO(Constantes.PERMISO_CAMBIAR_CAMPOS_CLAVES));
	}
	
	public boolean tienePermisoModificarIdSisgen() {
		return this.rol.getPermisos().contains(new PermisoDTO(Constantes.PERMISO_MODIFICAR_ID_SISGEN));
	}
}
