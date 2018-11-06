package com.helio4.bancol.avaluos.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="avaluo_remate")
@PrimaryKeyJoinColumn(name="avaluo_id")
public class AvaluoRemate extends Avaluo {

   	@Column(name = "nombre_secuestre")
	private String nombreSecuestre;
	@Column(name = "telefono_secuestre")
	private String telefonoSecuestre;
	@Column(name = "celular_secuestre")
	private String celularSecuestre;
	@Column(name = "email_secuestre")
	private String emailSecuestre;

	public AvaluoRemate() {
		super();
	}

	public String getNombreSecuestre() {
		return nombreSecuestre;
	}

	public void setNombreSecuestre(String nombreSecuestre) {
		this.nombreSecuestre = nombreSecuestre;
	}

	public String getTelefonoSecuestre() {
		return telefonoSecuestre;
	}

	public void setTelefonoSecuestre(String telefonoSecuestre) {
		this.telefonoSecuestre = telefonoSecuestre;
	}

	public String getCelularSecuestre() {
		return celularSecuestre;
	}

	public void setCelularSecuestre(String celularSecuestre) {
		this.celularSecuestre = celularSecuestre;
	}

	public String getEmailSecuestre() {
		return emailSecuestre;
	}

	public void setEmailSecuestre(String emailSecuestre) {
		this.emailSecuestre = emailSecuestre;
	}
}
