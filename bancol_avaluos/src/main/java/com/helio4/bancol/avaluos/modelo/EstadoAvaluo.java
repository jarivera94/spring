package com.helio4.bancol.avaluos.modelo;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
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
import javax.persistence.Table;

import com.google.common.collect.Iterables;
import com.helio4.bancol.avaluos.dto.EventoDTO;

@Entity
@Table(name = "estado_avaluo")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="estado", discriminatorType=DiscriminatorType.INTEGER)
public abstract class EstadoAvaluo {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="estado_avaluo_id")
    private Long id;

    @Column(name = "fecha_estado")
    private Date fechaEstado;

    @ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.EAGER)
        @JoinColumns ({
            @JoinColumn(name = "tipo_documento_perito", referencedColumnName = "tipo_documento_identificacion"),
            @JoinColumn(name = "numero_documento_perito", referencedColumnName = "numero_documento")
        })
    private Usuario perito;

    @Column(name="justificacion_rechazo")
    private String justificacionRechazo;

    @ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.EAGER)
        @JoinColumns ({
            @JoinColumn(name = "tipo_documento_usuario", referencedColumnName = "tipo_documento_identificacion"),
            @JoinColumn(name = "numero_documento_usuario", referencedColumnName = "numero_documento")
        })
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name="avaluo_id", nullable=false)
    private Avaluo avaluo;

    @Column(name="estado_actual")
    private Boolean estadoActual;

    @Column(name="estado", insertable = false, updatable = false)
    private Integer estado;

    public EstadoAvaluo() {}

    public EstadoAvaluo(Avaluo avaluo, Usuario usuario) {
        setAvaluo(avaluo);
        setFechaEstado(new Date(System.currentTimeMillis()));
        setUsuario(usuario);
        setEstadoActual(true);
    }

    public EstadoAvaluo(EstadoAvaluo estadoAvaluo, Usuario usuario) {
        setAvaluo(estadoAvaluo.getAvaluo());
        setFechaEstado(new Date(System.currentTimeMillis()));
        setPerito(getAvaluo().getPerito());
        setUsuario(usuario);
        setEstadoActual(true);
    }

    public static EstadoAvaluo estadoInicial(Avaluo avaluo) {
        return new EstadoPorAsignar(avaluo);
    }

    public void update(Date fechaEstado, Usuario perito, Avaluo avaluo, String justificacionRechazo,
            Boolean estadoActual, Usuario usuario) {
        this.fechaEstado = fechaEstado;
        this.perito = perito;
        this.avaluo = avaluo;
        this.justificacionRechazo = justificacionRechazo;
        this.estadoActual = estadoActual;
        this.usuario = usuario;
    }

    public EstadoAvaluo pasarEstado(Usuario usuario) {
        return null;
    }

    public EstadoAvaluo asignarPerito(Usuario perito, Usuario usuario) {
        getAvaluo().setEstado(getAvaluo().getSoloAvaluo() ?
                new EstadoEnProceso(this, usuario) : new EstadoAsignado(this, usuario));
        getAvaluo().getEstado().setPerito(perito);
        return getAvaluo().getEstado();
    }

    public void aceptarCaso(Usuario usuario) throws EstadoIlegalException {
        throw new EstadoIlegalException(
                "No se puede realizar esta operación en este estado: "
                + this.getClass().getName() + " avaluoId:  "
                + getAvaluo().getId());
    }

    public void rechazarCaso(String justificacion, Usuario usuario) throws EstadoIlegalException {
        throw new EstadoIlegalException(
                "No se puede realizar esta operación en este estado: "
                + this.getClass().getName() + " avaluoId:  "
                + getAvaluo().getId());
    }

    public EventoDTO programarCita(Date horaInicio, Date horaFin,
            String nombreRecibeVisita, Usuario usuario) throws EstadoIlegalException {
        throw new EstadoIlegalException("No se puede realizar esta operación en este estado: "
                + this.getClass().getName() + " avaluoId:  "
                + getAvaluo().getId());
    }

    public void solicitarDevolucion(String justificacion, Usuario usuario) throws EstadoIlegalException {
        throw new EstadoIlegalException("No se puede realizar esta operación en este estado: "
                + this.getClass().getName() + " avaluoId:  "
                + getAvaluo().getId());
    }

    public void devolver(String justificacion, Usuario usuario) throws EstadoIlegalException {
        EstadoDevuelto estadoDevuelto = new EstadoDevuelto(this, usuario);
        estadoDevuelto.setJustificacionRechazo(justificacion);
        getAvaluo().setEstado(estadoDevuelto);
    }

    public void reactivar(Usuario usuario) throws EstadoIlegalException {
    	 EstadoEnProceso estadoEnProceso = new EstadoEnProceso(this, usuario);
         getAvaluo().setEstado(estadoEnProceso);
    }

    public void confirmarVisita(Usuario usuario) throws EstadoIlegalException {
        throw new EstadoIlegalException("No se puede realizar esta operación en este estado: "
                + this.getClass().getName() + " avaluoId:  "
                + getAvaluo().getId());
    }

    public void confirmarDocumentosCompletos(Usuario usuario) throws EstadoIlegalException {
        throw new EstadoIlegalException("No se puede realizar esta operación en este estado: "
                + this.getClass().getName() + " avaluoId:  "
                + getAvaluo().getId());
    }

    public void notificarHonorarios(Usuario usuario) throws EstadoIlegalException {
        throw new EstadoIlegalException("No se puede realizar esta operación en este estado: "
                + this.getClass().getName() + " avaluoId:  "
                + getAvaluo().getId());
    }

    public void confirmarPago(Usuario usuario) throws EstadoIlegalException {
        throw new EstadoIlegalException("No se puede realizar esta operación en este estado: "
                + this.getClass().getName() + " avaluoId:  "
                + getAvaluo().getId());
    }

    public void enviar(Usuario usuario) throws EstadoIlegalException {
        throw new EstadoIlegalException("No se puede realizar esta operación en este estado: "
                + this.getClass().getName() + " avaluoId:  "
                + getAvaluo().getId());
    }

    public void solicitarCorreciones(String correciones, Usuario usuario) throws EstadoIlegalException {
        throw new EstadoIlegalException("No se puede realizar esta operación en este estado: "
                + this.getClass().getName() + " avaluoId:  "
                + getAvaluo().getId());
    }

    public void enviarAComite(Usuario usuario) throws EstadoIlegalException {
        throw new EstadoIlegalException("No se puede realizar esta operación en este estado: "
                + this.getClass().getName() + " avaluoId:  "
                + getAvaluo().getId());
    }

    public void aprobar(Usuario usuario) throws EstadoIlegalException {
        throw new EstadoIlegalException("No se puede realizar esta operación en este estado: "
                + this.getClass().getName() + " avaluoId:  "
                + getAvaluo().getId());
    }

    public void cancelarAvaluo(Usuario usuario) throws EstadoIlegalException {
        EstadoCancelado estadoCancelado = new EstadoCancelado(this, usuario);
        getAvaluo().setEstado(estadoCancelado);
    }

    EstadoAvaluo obtenerEstadoAnterior() {
        return Iterables.getLast(getAvaluo().getEstadosAvaluo());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaEstado() {
        return fechaEstado;
    }

    public void setFechaEstado(Date fechaEstado) {
        this.fechaEstado = fechaEstado;
    }

    public Usuario getPerito() {
        return perito;
    }

    public void setPerito(Usuario perito) {
        this.perito = perito;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Avaluo getAvaluo() {
        return avaluo;
    }

    public void setAvaluo(Avaluo avaluo) {
        this.avaluo = avaluo;
    }

    public String getJustificacionRechazo() {
        return justificacionRechazo;
    }

    public void setJustificacionRechazo(String justificacionRechazo) {
        this.justificacionRechazo = justificacionRechazo;
    }

    public Integer getEstado() {
        return estado;
    }
    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Boolean getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(Boolean estadoActual) {
        this.estadoActual = estadoActual;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((avaluo == null) ? 0 : avaluo.hashCode());
        result = prime * result
            + ((estadoActual == null) ? 0 : estadoActual.hashCode());
        result = prime * result
            + ((fechaEstado == null) ? 0 : fechaEstado.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime
            * result
            + ((justificacionRechazo == null) ? 0 : justificacionRechazo
                    .hashCode());
        result = prime * result + ((perito == null) ? 0 : perito.hashCode());
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
        EstadoAvaluo other = (EstadoAvaluo) obj;
        if (avaluo == null) {
            if (other.avaluo != null)
                return false;
        } else if (!avaluo.equals(other.avaluo))
            return false;
        if (estadoActual == null) {
            if (other.estadoActual != null)
                return false;
        } else if (!estadoActual.equals(other.estadoActual))
            return false;
        if (fechaEstado == null) {
            if (other.fechaEstado != null)
                return false;
        } else if (!fechaEstado.equals(other.fechaEstado))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (justificacionRechazo == null) {
            if (other.justificacionRechazo != null)
                return false;
        } else if (!justificacionRechazo.equals(other.justificacionRechazo))
            return false;
        if (perito == null) {
            if (other.perito != null)
                return false;
        } else if (!perito.equals(other.perito))
            return false;
        return true;
    }

}
