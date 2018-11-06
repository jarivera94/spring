package com.helio4.bancol.avaluos.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "asesor", uniqueConstraints = @UniqueConstraint(columnNames= {"correo"}))
@SqlResultSetMapping(
    name = "AsesorConSucursal",
    entities = {
        @EntityResult(
            entityClass = Asesor.class,
            fields = {
                @FieldResult(name = "id", column = "id"),
                @FieldResult(name = "nombre", column = "nombre"),
                @FieldResult(name = "celular", column = "celular"),
                @FieldResult(name = "correo", column = "correo"),
                @FieldResult(name = "telefono", column = "telefono")}),
        @EntityResult(
            entityClass = Sucursal.class,
            fields = {
                @FieldResult(name = "id", column = "sucursal_id"),
                @FieldResult(name = "nombreCompuesto", column = "nombreCompuesto")})
    })
@NamedNativeQueries({
    @NamedNativeQuery(
        name = "Asesor.encontrarAsesor",
        query = "SELECT a.id, a.nombre, s.sucursal_id, s.codigo || ' ' || s.nombre AS nombreCompuesto,"
            + " a.celular, a.correo, a.telefono FROM avaluos.asesor a"
            + " INNER JOIN avaluos.sucursal s ON (a.sucursal = s.sucursal_id)"
            + " WHERE a.nombre = :nombre "
            + " AND s.entidad_id = :idEntidad"
            + " AND s.codigo = :codigo AND a.celular = :celular"
            + " AND a.correo = :correo AND a.telefono = :telefono",
        resultSetMapping="AsesorConSucursal", resultClass=Asesor.class)
})
public class Asesor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "nombre")
	private String nombre;

	@ManyToOne
	@JoinColumn(name = "sucursal")
	private Sucursal sucursal;

	@Column(name = "celular")
	private String celular;

	@Column(name = "correo")
	private String correo;

	@Column(name = "telefono")
	private String telefono;

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

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
}
