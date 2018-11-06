package com.helio4.bancol.avaluos.dto;

public class ConceptosFinalesDTO {

	private Long id;
	private String nomTipoInmueble;
	private Integer areaM2;
	private Integer valorM2;
	private Integer valorTotal;	
	
	public ConceptosFinalesDTO() {
		super();
	}

	public ConceptosFinalesDTO(Long id, String nomTipoInmueble, Integer areaM2, Integer valorM2, Integer valorTotal) {
		super();
		this.id = id;
		this.nomTipoInmueble = nomTipoInmueble;
		this.areaM2 = areaM2;
		this.valorM2 = valorM2;
		this.valorTotal = valorTotal;		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNomTipoInmueble() {
		return nomTipoInmueble;
	}

	public void setNomTipoInmueble(String nomTipoInmueble) {
		this.nomTipoInmueble = nomTipoInmueble;
	}

	public Integer getAreaM2() {
		return areaM2;
	}

	public void setAreaM2(Integer noDeCapitulo) {
		this.areaM2 = noDeCapitulo;
	}

	public Integer getValorM2() {
		return valorM2;
	}

	public void setValorM2(Integer valorM2) {
		this.valorM2 = valorM2;
	}

	public Integer getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Integer valorTotal) {
		this.valorTotal = valorTotal;
	}	
}
