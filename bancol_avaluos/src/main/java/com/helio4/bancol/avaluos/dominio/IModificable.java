package com.helio4.bancol.avaluos.dominio;

import java.util.List;

/**
 * Interface que permite personalizar las descripciones de las 
 * modificaciones que  generan para una clase en especifico.
 * */
public interface IModificable {

	/**
	 * Obtiene el Identificador de un objeto modificable
	 * */
	public Long getIdentificador();
	
	/**
	 * Lista de los metodos del objeto a Ignorar cuando se desea 
	 * realizar la verificacion de modificaciones sobre el objeto.
	 * */
	public List<String> camposIgnorar();
	/**
	 * Descripcion personalizada para el campo anterior cuando 
	 * se borra el objeto.
	 * */
	public String anteriorBorrar();
	/**
	 * Descripcion personalizada para el campo nuevo cuando se
	 * borra el objeto.
	 * */
	public String nuevoBorrar();
	/**
	 * Descripcion personalizada para el campo anterior cuando 
	 * se crea un objeto.
	 * */
	public String anteriorNuevo();
	/**
	 * Descripcion personalizada para el campo nuevo cuando 
	 * se crea un objeto.
	 * */
	public String nuevo();
	/**
	 * Obtiene la descripcion personalizada que se debe llenar en 
	 * en el campo modificado.
	 * */
	public String getCampo();
}
