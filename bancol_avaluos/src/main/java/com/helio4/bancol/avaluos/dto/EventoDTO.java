package com.helio4.bancol.avaluos.dto;

import java.util.Date;

public class EventoDTO {

	private Long id;
	private Date fechaHoraInicio;
	private Date fechaHoraFin;
	private String descripcion;
	private Integer tipoDocumentoIdentificacionPerito;
	private Long numeroDocumentoPerito;
	private String nombresPerito;
	private String apellidosPerito;
	private String emailPerito;
	private Long avaluoId;
	private String codigoExterno;

	public EventoDTO() {
	}

	public EventoDTO(Long id, String descripcion, Date fechaHoraInicio,
            Date fechaHoraFin, String nombresPerito, String apellidosPerito) {
        this.id = id;
        this.descripcion = descripcion;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.nombresPerito = nombresPerito;
        this.nombresPerito = nombresPerito;
        this.apellidosPerito = apellidosPerito;
    }
	
	public EventoDTO(Long id, String descripcion, Date fechaHoraInicio,
            Date fechaHoraFin, String nombresPerito, String apellidosPerito, String codigoExterno) {
        this.id = id;
        this.descripcion = descripcion;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.nombresPerito = nombresPerito;
        this.nombresPerito = nombresPerito;
        this.apellidosPerito = apellidosPerito;
        this.codigoExterno = codigoExterno;
    }

	public EventoDTO(Long id, String descripcion,
	        Date fechaHoraInicio, Date fechaHoraFin,
	        Long avaluoId, Integer tipoDocumentoIdentificacionPerito,
            Long numeroDocumentoPerito) {
	    this.id = id;
	    this.descripcion = descripcion;
	    this.fechaHoraInicio = fechaHoraInicio;
	    this.fechaHoraFin = fechaHoraFin;
	    this.avaluoId = avaluoId;
        this.tipoDocumentoIdentificacionPerito = 
            tipoDocumentoIdentificacionPerito;
        this.numeroDocumentoPerito = 
            numeroDocumentoPerito;
	}

	public Long getId() {
		return this.id;
	}

	public Date getFechaHoraInicio() {
		return this.fechaHoraInicio;
	}

	public Date getFechaHoraFin() {
		return this.fechaHoraFin;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public Integer getTipoDocumentoIdentificacionPerito() {
		return tipoDocumentoIdentificacionPerito;
	}

	public Long getNumeroDocumentoPerito() {
		return numeroDocumentoPerito;
	}

	public String getNombresPerito() {
		return nombresPerito;
	}

	public String getApellidosPerito() {
		return apellidosPerito;
	}

	public String getEmailPerito() {
		return emailPerito;
	}

	public Long getAvaluoId() {
		return this.avaluoId;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFechaHoraInicio(Date fechaHoraInicio) {
		this.fechaHoraInicio = fechaHoraInicio;
	}

	public void setFechaHoraFin(Date fechaHoraFin) {
		this.fechaHoraFin = fechaHoraFin;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setTipoDocumentoIdentificacionPerito(
			Integer tipoDocumentoIdentificacionPerito) {
		this.tipoDocumentoIdentificacionPerito = tipoDocumentoIdentificacionPerito;
	}

	public void setNumeroDocumentoPerito(Long numeroDocumentoPerito) {
		this.numeroDocumentoPerito = numeroDocumentoPerito;
	}

	public void setNombresPerito(String nombresPerito) {
		this.nombresPerito = nombresPerito;
	}

	public void setApellidosPerito(String apellidosPerito) {
		this.apellidosPerito = apellidosPerito;
	}

	public void setEmailPerito(String emailPerito) {
		this.emailPerito = emailPerito;
	}

	public void setAvaluoId(Long avaluoId) {
		this.avaluoId = avaluoId;
	}

	public String getCodigoExterno() {
		return codigoExterno;
	}

	public void setCodigoExterno(String codigoExterno) {
		this.codigoExterno = codigoExterno;
	}


}
