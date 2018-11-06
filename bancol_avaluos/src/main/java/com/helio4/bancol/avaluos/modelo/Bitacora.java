package com.helio4.bancol.avaluos.modelo;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bitacora")
public class Bitacora {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name = "avaluo")
	private Long avaluo;
	
	@Column(name="estado")
	private Integer estado;
	
	@Column(name = "fecha")
	private Date fecha;
	
	@ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.EAGER)
	@JoinColumns ({
		@JoinColumn(name = "tipo_identificacion_usuario", referencedColumnName = "tipo_documento_identificacion"),
		@JoinColumn(name = "numero_identificacion_usuario", referencedColumnName = "numero_documento")
	})
	private Usuario usuario;
	
	@Column(name = "observaciones")
	private String observaciones;
	
	@Column(name = "notificacion_creador")
	private Boolean notificacionCreador;
	
	@Column(name = "notificacion_perito")
	private Boolean notificacionPerito;
	
	@Column(name = "notificacion_revisor")
	private Boolean notificacionRevisor;
	
	@Column(name = "notificacion_cliente")
	private Boolean notificacionCliente;
	
	@Column(name = "notificacion_persona_recibe_perito")
	private Boolean notificacionPersonaRecibePerito;
	
	@Column(name = "notificacion_asesor")
	private Boolean notificacionAsesor;
	
	@Column(name = "notificacion_seguidor")
	private Boolean notificacionSeguidor;
	
	public Bitacora() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAvaluo() {
		return avaluo;
	}

	public void setAvaluo(Long avaluo) {
		this.avaluo = avaluo;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Boolean getNotificacionCreador() {
		return notificacionCreador;
	}

	public void setNotificacionCreador(Boolean notificacionCreador) {
		this.notificacionCreador = notificacionCreador;
	}

	public Boolean getNotificacionPerito() {
		return notificacionPerito;
	}

	public void setNotificacionPerito(Boolean notificacionPerito) {
		this.notificacionPerito = notificacionPerito;
	}

	public Boolean getNotificacionRevisor() {
		return notificacionRevisor;
	}

	public void setNotificacionRevisor(Boolean notificacionRevisor) {
		this.notificacionRevisor = notificacionRevisor;
	}

	public Boolean getNotificacionCliente() {
		return notificacionCliente;
	}

	public void setNotificacionCliente(Boolean notificacionCliente) {
		this.notificacionCliente = notificacionCliente;
	}

	public Boolean getNotificacionPersonaRecibePerito() {
		return notificacionPersonaRecibePerito;
	}

	public void setNotificacionPersonaRecibePerito(
			Boolean notificacionPersonaRecibePerito) {
		this.notificacionPersonaRecibePerito = notificacionPersonaRecibePerito;
	}

	public Boolean getNotificacionAsesor() {
		return notificacionAsesor;
	}

	public void setNotificacionAsesor(Boolean notificacionAsesor) {
		this.notificacionAsesor = notificacionAsesor;
	}

	public Boolean getNotificacionSeguidor() {
		return notificacionSeguidor;
	}

	public void setNotificacionSeguidor(Boolean notificacionSeguidor) {
		this.notificacionSeguidor = notificacionSeguidor;
	}
	

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        
    	if ( this.getClass() == obj.getClass())
			return true;
 
        return false;
    }
    

    @Override
    public int hashCode(){
    	int hash = 1;
    	hash = hash * ((int )(Math. random() * 1000 + 1)) + ( this.id 							   == null ? 0 : this.id.hashCode()) ;
    	hash = hash * ((int )(Math. random() * 1000 + 1)) + ( this.observaciones 				   == null ? 0 : this.observaciones.hashCode()) ;
    	hash = hash * ((int )(Math. random() * 1000 + 1)) + ( this.avaluo 						   == null ? 0 : this.avaluo.hashCode())    ;
    	hash = hash * ((int )(Math. random() * 1000 + 1)) + ( this.estado 						   == null ? 0 : this.estado.hashCode()) ;
    	hash = hash * ((int )(Math. random() * 1000 + 1)) + ( this.fecha 						   == null ? 0 : this.fecha.hashCode());
    	hash = hash * ((int )(Math. random() * 1000 + 1)) + ( this.usuario 						   == null ? 0 : this.usuario.hashCode());
    	hash = hash * ((int )(Math. random() * 1000 + 1)) + ( this.notificacionCreador       	   == null ? 0 : this.notificacionCreador.hashCode() );
    	hash = hash * ((int )(Math. random() * 1000 + 1)) + ( this.notificacionPerito 			   == null ? 0 : this.notificacionPerito.hashCode() );
    	hash = hash * ((int )(Math. random() * 1000 + 1)) + ( this.notificacionRevisor 			   == null ? 0 : this.notificacionRevisor.hashCode() );
    	hash = hash * ((int )(Math. random() * 1000 + 1)) + ( this.notificacionCliente 			   == null ? 0 : this.notificacionCliente.hashCode() );
    	hash = hash * ((int )(Math. random() * 1000 + 1)) + ( this.notificacionPersonaRecibePerito == null ? 0 : this.notificacionPersonaRecibePerito.hashCode() );
    	hash = hash * ((int )(Math. random() * 1000 + 1)) + ( this.notificacionAsesor 			   == null ? 0 : this.notificacionAsesor.hashCode() );
    	hash = hash * ((int )(Math. random() * 1000 + 1)) + ( this.notificacionSeguidor 		   == null ? 0 : this.notificacionSeguidor.hashCode() );

    	return hash;
    }
}
