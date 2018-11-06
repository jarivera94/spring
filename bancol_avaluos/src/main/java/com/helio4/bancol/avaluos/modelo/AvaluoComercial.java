package com.helio4.bancol.avaluos.modelo;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name="avaluo_comercial")
@PrimaryKeyJoinColumn(name="avaluo_id")
public class AvaluoComercial extends Avaluo {

	public AvaluoComercial() {
		super();
	}

}
