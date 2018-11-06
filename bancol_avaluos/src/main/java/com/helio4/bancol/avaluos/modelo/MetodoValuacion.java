package com.helio4.bancol.avaluos.modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.helio4.bancol.avaluos.dto.MetodoValuacionDTO;

@Entity
@Table(name = "metodo_valuacion")
@Inheritance(strategy=InheritanceType.JOINED)
public class MetodoValuacion  {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "metodo_valuacion_id")
	private Long id;
	@Column(name = "metodo_usado")
	private Integer metodoUsado;
	@Column(name = "concepto_del_metodo")
	private String conceptoDelMetodo;
	
	@ManyToOne
	@JoinColumn(name = "avaluo_id", nullable=false)
	private Avaluo avaluo;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="metodoValuacion")
	private List<Oferta> ofertas;
	
	public MetodoValuacion() {
	}

	public MetodoValuacion(Integer metodoUsado, String conceptoDelMetodo,
			AvaluoComercial avaluo) {
		super();
		this.metodoUsado = metodoUsado;
		this.conceptoDelMetodo = conceptoDelMetodo;
		this.avaluo = avaluo;
	}

	public void update(Integer metodoUsado, String conceptoDelMetodo,
			AvaluoComercial avaluo) {
		this.metodoUsado = metodoUsado;
		this.conceptoDelMetodo = conceptoDelMetodo;
		this.avaluo = avaluo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getMetodoUsado() {
		return metodoUsado;
	}

	public void setMetodoUsado(Integer metodoUsado) {
		this.metodoUsado = metodoUsado;
	}

	public String getConceptoDelMetodo() {
		return conceptoDelMetodo;
	}

	public void setConceptoDelMetodo(String conceptoDelMetodo) {
		this.conceptoDelMetodo = conceptoDelMetodo;
	}

	public Avaluo getAvaluo() {
		return avaluo;
	}

	public void setAvaluo(Avaluo avaluo) {
		this.avaluo = avaluo;
	}

	public List<Oferta> getOfertas() {
		return ofertas;
	}

	public void setOfertas(List<Oferta> ofertas) {
		this.ofertas = ofertas;
	}
	
	public void cargarMetodoDTO(MetodoValuacionDTO metodo){

		this.metodoUsado = metodo.getMetodoUsado().getKey();
		this.conceptoDelMetodo = metodo.getConceptoDelMetodo();
		
	}

	
}
