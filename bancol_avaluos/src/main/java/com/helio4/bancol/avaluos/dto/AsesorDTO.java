package com.helio4.bancol.avaluos.dto;

public class AsesorDTO {
	
	private Long id;
	private String nombre;
	private SucursalDTO sucursal;
	private String celular;
	private String correo;
	private String telefono;

    public AsesorDTO(){}

    public AsesorDTO(Long id, String nombre, SucursalDTO sucursal,
            String celular, String correo, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.sucursal = sucursal;
        this.celular = celular;
        this.correo = correo;
        this.telefono = telefono;
    }

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

	public SucursalDTO getSucursal() {
		return sucursal;
	}

	public void setSucursal(SucursalDTO sucursal) {
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
		if(correo != null){
		this.correo = correo.trim();
		}else{
			this.correo = "";
		}
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public String toString() {
		if (id != null) {
			return id.toString();
		}else{
			return "";
		}
	}
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AsesorDTO other = (AsesorDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

}
