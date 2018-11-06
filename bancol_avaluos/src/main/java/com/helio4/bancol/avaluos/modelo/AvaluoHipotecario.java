package com.helio4.bancol.avaluos.modelo;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="avaluo_hipotecario")
@PrimaryKeyJoinColumn(name="avaluo_id")
public class AvaluoHipotecario extends Avaluo {

	public AvaluoHipotecario() {
		super();
	}

}
