package com.helio4.bancol.avaluos.servicio.datos;


public interface FachadaServicios<T> {
	
	public T crear();
	
	public T eliminar(Long id) throws Exception;

}
