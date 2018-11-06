package com.helio4.bancol.avaluos.dto;

import com.helio4.bancol.avaluos.controlador.ListaDesplegable;
import com.helio4.bancol.avaluos.servicio.util.Constantes;

public class GarajeDTO implements Cloneable{

	private Long id;
	private String numeroGaraje;
	private String matriculaInmobiliaria;
	private boolean doble;
	private boolean cubierto;
	private boolean paralelo;
	private boolean servidumbre;
	private Integer tipo;
	private Long avaluoId;

	public GarajeDTO(){}

	public GarajeDTO(Long id, String numeroGaraje, String matriculaInmobiliaria,
			Boolean doble, Boolean cubierto, Integer tipo,
			Long avaluoId, Boolean servidumbre, Boolean paralelo){
		this.id = id;
		this.tipo = tipo;
		this.avaluoId = avaluoId;
		this.numeroGaraje = numeroGaraje;
		this.matriculaInmobiliaria = matriculaInmobiliaria;
		this.servidumbre = servidumbre == null ? Boolean.FALSE : servidumbre ;
		this.paralelo 	 = paralelo == null ? Boolean.FALSE : paralelo;
		this.doble = doble == null ? Boolean.FALSE : doble;
		this.cubierto 	 = cubierto == null ? Boolean.FALSE : cubierto;
	}
	 /**
     * Enumeracion para las opciones de Tipos de garajes
     * */
    public enum ListaTipoGaraje implements  ListaDesplegable {
    	Seleccione(Constantes.NO_TIENE,0),
    	Privado(Constantes.TIPO_GARAJE_PRIVADO  , 1),
    	Exclusivo(Constantes.TIPO_GARAJE_EXCLUSIVO, 2),
    	Comunal(Constantes.TIPO_GARAJE_COMUNAL,3);
    	
 
		private final String value;
		private final int key;
		
		ListaTipoGaraje (String value, int key) {
			this.value = value;
			this.key = key;
		}
		
		public String getValue() {
			return value;
		}
		
		public int getKey() {
			return key;
		}
		
		public static ListaTipoGaraje  fromKey(int key) {
			switch (key) {
			case 0: return ListaTipoGaraje.Seleccione;
			case 1: return Privado;
			case 2: return Exclusivo;
			case 3: return Comunal;
			default:
				return null;
			}
		}
	
		@Override
		public String toString() {
			switch (this) {
			case Seleccione: return Constantes.SELECCCIONE;
			case Privado: return Constantes.TIPO_GARAJE_PRIVADO;
			case Exclusivo: return Constantes.TIPO_GARAJE_EXCLUSIVO;
			case Comunal: return Constantes.TIPO_GARAJE_COMUNAL ;
			default:
				return "";
			}
		}		
	}
	public Object clone() throws CloneNotSupportedException{
		GarajeDTO clone = new GarajeDTO(this.id,this.numeroGaraje,
					this.matriculaInmobiliaria,this.doble,this.cubierto,
					this.tipo,this.avaluoId,this.servidumbre,this.paralelo);	
		return clone;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getTipo() {
		return tipo;
	}
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	public String getNumeroGaraje() {
		return numeroGaraje;
	}
	public void setNumeroGaraje(String numeroGaraje) {
		this.numeroGaraje = numeroGaraje;
	}
	public String getMatriculaInmobiliaria() {
		return matriculaInmobiliaria;
	}
	public void setMatriculaInmobiliaria(String matriculaInmobiliaria) {
		this.matriculaInmobiliaria = matriculaInmobiliaria;
	}
	public boolean isDoble() {
		return doble;
	}
	public void setDoble(boolean doble) {
		this.doble = doble;
	}
	public boolean isCubierto() {
		return cubierto;
	}
	public void setCubierto(boolean cubierto) {
		this.cubierto = cubierto;
	}
	public boolean isParalelo() {
		return paralelo;
	}
	public void setParalelo(boolean paralelo) {
		this.paralelo = paralelo;
	}
	public boolean isServidumbre() {
		return servidumbre;
	}
	public void setServidumbre(boolean servidumbre) {
		this.servidumbre = servidumbre;
	}
	public Long getAvaluoId() {
		return avaluoId;
	}
	public void setAvaluoId(Long avaluoId) {
		this.avaluoId = avaluoId;
	}
	@Override
	public String toString(){
		String value ="[Garaje, id="+this.id+", avaluoId="+this.avaluoId
				+ ", tipo="+this.tipo+", numeroGaraje="+this.numeroGaraje+", matriculaInmobiliaria="+this.matriculaInmobiliaria
				+ ", servidumbre="+this.servidumbre+", paralelo="+this.paralelo+" ,doble="+this.doble+" ,cubierto="+this.cubierto+"]";
		return value;
	}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((avaluoId == null) ? 0 : avaluoId.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime
                * result
                + ((matriculaInmobiliaria == null) ? 0 : matriculaInmobiliaria
                        .hashCode());
        result = prime * result
                + ((numeroGaraje == null) ? 0 : numeroGaraje.hashCode());
        result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
        GarajeDTO other = (GarajeDTO) obj;
        if (avaluoId == null) {
            if (other.avaluoId != null)
                return false;
        } else if (!avaluoId.equals(other.avaluoId))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (matriculaInmobiliaria == null) {
            if (other.matriculaInmobiliaria != null)
                return false;
        } else if (!matriculaInmobiliaria.equals(other.matriculaInmobiliaria))
            return false;
        if (numeroGaraje == null) {
            if (other.numeroGaraje != null)
                return false;
        } else if (!numeroGaraje.equals(other.numeroGaraje))
            return false;
        if (tipo == null) {
            if (other.tipo != null)
                return false;
        } else if (!tipo.equals(other.tipo))
            return false;
        return true;
    }
}
