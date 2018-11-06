package com.helio4.bancol.avaluos.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "formato_informe")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class FormatoInforme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "formato_informe_id")
    private Long id;
    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "avaluo_id")
    private Avaluo avaluo;

    public FormatoInforme() {
    }

    public FormatoInforme(Avaluo avaluo) {
        this.avaluo = avaluo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Avaluo getAvaluo() {
        return avaluo;
    }

    public void setAvaluo(Avaluo avaluo) {
        this.avaluo = avaluo;
    }
}
