package com.helio4.bancol.avaluos.dto;

import java.io.Serializable;

public class FormatoInformeDTO implements Serializable, Cloneable {

	private static final long serialVersionUID = -5221118822569436258L;
	private Long id;
    private Long avaluoId;

    public FormatoInformeDTO(Long avaluoId) {
        this.avaluoId = avaluoId;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAvaluoId() {
        return avaluoId;
    }

    public void setAvaluoId(Long avaluoId) {
        this.avaluoId = avaluoId;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
