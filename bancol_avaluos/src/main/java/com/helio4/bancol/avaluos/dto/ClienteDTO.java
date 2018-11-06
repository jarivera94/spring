package com.helio4.bancol.avaluos.dto;

import com.helio4.bancol.avaluos.modelo.Cliente;

import java.io.Serializable;

public class ClienteDTO implements Cloneable, Serializable {

	/**
     * 
     */
    private static final long serialVersionUID = 1519443321200925176L;
    private Integer tipoDocumentoIdentificacion;
	private Long numeroDocumento;
	private String primerNombre;
	private String segundoNombre;
	private String primerApellido;
	private String segundoApellido;
    private String nombreCompleto;
	private String paisOrigen;
	private String clasificacionFiscal;
	private String razonSocial;
	private String regimen;
	private String codigoCIIU;
	private String telefonoSolicitante = "";
	private String celularSolicitante = "";
	private String correoElectronicoSolicitante = "";
	private String direccionDeContactoSolicitante;
	private DivipolaDTO divipola;
	private boolean empleadoEntidad;
	private boolean clienteExterior;
	private SegmentoDTO segmento;
	private String numeroDeRegistroPerito;

	public ClienteDTO () {
	}

	public ClienteDTO(Cliente c){
		this.tipoDocumentoIdentificacion = c.getTipoDocumentoIdentificacion();
		this.numeroDocumento = c.getNumeroDocumento();
		this.primerNombre = c.getPrimerNombre();
		this.segundoNombre = c.getSegundoNombre();
		this.primerApellido = c.getPrimerApellido();
		this.segundoApellido = c.getSegundoApellido();
		this.correoElectronicoSolicitante = c.getCorreoElectronicoSolicitante();
		this.telefonoSolicitante = c.getTelefonoSolicitante();
		this.celularSolicitante = c.getCelularSolicitante();
		this.razonSocial = c.getRazonSocial();
		this.divipola = new DivipolaDTO();
		this.divipola.setDepartamento( c.getDivipola()!=null ? c.getDivipola().getDepartamento() : "" );
		this.divipola.setCentroPoblado( c.getDivipola()!=null ? c.getDivipola().getCentroPoblado() : "" );
		this.direccionDeContactoSolicitante = c.getDireccionDeContactoSolicitante();
		
		SegmentoDTO segmento = new SegmentoDTO();
		segmento.setNombre( c.getSegmento()!=null ? c.getSegmento().getNombre() : "" );
	}

    public ClienteDTO(Integer tipoDocumentoIdentificacion,
            Long numeroDocumento, String primerNombre, String segundoNombre,
            String primerApellido, String segundoApellido, String email,
            String telefono, String celular, String nombreSegmento,
            String razonSocial, String departamento, String ciudad,
            String direccion) {

        this.tipoDocumentoIdentificacion = tipoDocumentoIdentificacion;
        this.numeroDocumento = numeroDocumento;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.correoElectronicoSolicitante = email;
        this.telefonoSolicitante = telefono;
        this.celularSolicitante = celular;
        this.razonSocial = razonSocial;
        this.divipola = new DivipolaDTO();
        this.divipola.setDepartamento(departamento);
        this.divipola.setCentroPoblado(ciudad);
        this.direccionDeContactoSolicitante = direccion;

        SegmentoDTO segmento = new SegmentoDTO();
        segmento.setNombre(nombreSegmento);

    }
	public ClienteDTO(Integer tipoDocumentoIdentificacion,
			Long numeroDocumento, String primerNombre, String segundoNombre,
			String primerApellido, String segundoApellido, String paisOrigen,
			String clasificacionFiscal, String razonSocial, String regimen,
			String codigoCIIU, String telefonoSolicitante,
			String celularSolicitante, String correoElectronicoSolicitante,
			String direccionDeContactoSolicitante,
			DivipolaDTO divipolaDTO, boolean empleadoEntidad,
			boolean clienteExterior, SegmentoDTO segmento,
			String numeroDeRegistroPerito) {
		super();
		this.tipoDocumentoIdentificacion = tipoDocumentoIdentificacion;
		this.numeroDocumento = numeroDocumento;
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
		this.divipola = divipolaDTO;
		this.empleadoEntidad = empleadoEntidad;
		this.clienteExterior = clienteExterior;
		this.segmento = segmento;
		this.numeroDeRegistroPerito = numeroDeRegistroPerito;
	}

	public Integer getTipoDocumentoIdentificacion() {
		return tipoDocumentoIdentificacion;
	}

	public void setTipoDocumentoIdentificacion(
			Integer tipoDocumentoIdentificacion) {
		this.tipoDocumentoIdentificacion = tipoDocumentoIdentificacion;
	}

	public Long getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(Long numeroDocumento) {
		if(numeroDocumento != null && numeroDocumento == 0){
			this.numeroDocumento = null;
 		}else{
 			this.numeroDocumento = numeroDocumento;
 		}
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		if(primerNombre != null){
			this.primerNombre = primerNombre.toUpperCase();
		}
		this.primerNombre = primerNombre;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		if(segundoNombre != null){
			this.segundoNombre = segundoNombre.toUpperCase();
		}
		this.segundoNombre = segundoNombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		if(primerApellido != null){
			this.primerApellido = primerApellido.toUpperCase();
		}
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		if(segundoApellido != null){
			this.segundoApellido = segundoApellido.toUpperCase();
		}
		this.segundoApellido = segundoApellido;
	}

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
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

	public DivipolaDTO getDivipola() {
		return divipola;
	}

	public void setDivipola(DivipolaDTO divipola) {
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

	public SegmentoDTO getSegmento() {
		return segmento;
	}

	public void setSegmento(SegmentoDTO segmento) {
		this.segmento = segmento;
	}

	public String getNumeroDeRegistroPerito() {
		return numeroDeRegistroPerito;
	}

	public void setNumeroDeRegistroPerito(String numeroDeRegistroPerito) {
		this.numeroDeRegistroPerito = numeroDeRegistroPerito;
	}

	@Override
    public Object clone() throws CloneNotSupportedException {
		DivipolaDTO cloneDivipola = this.divipola == null ? null : (DivipolaDTO) this.divipola.clone();
        return new ClienteDTO(tipoDocumentoIdentificacion, numeroDocumento,
                primerNombre, segundoNombre, primerApellido, segundoApellido,
                paisOrigen, clasificacionFiscal, razonSocial, regimen,
                codigoCIIU, telefonoSolicitante, celularSolicitante,
                correoElectronicoSolicitante, direccionDeContactoSolicitante,
                cloneDivipola, empleadoEntidad, clienteExterior, segmento,
                numeroDeRegistroPerito);
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		ClienteDTO other = (ClienteDTO) obj;
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
