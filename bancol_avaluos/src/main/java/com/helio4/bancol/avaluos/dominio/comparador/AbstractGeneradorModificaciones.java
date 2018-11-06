package com.helio4.bancol.avaluos.dominio.comparador;

import com.helio4.bancol.avaluos.dto.ModificacionDTO;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;

/**
 * Clase padre que inicia la cadena de responsabilidad de los 
 * comparadores personalizados.
 * */
public abstract class AbstractGeneradorModificaciones {
	
	/**
	 * Apuntador al siguiente elemento en la cadena de responsablidad.
	 * */
	private AbstractGeneradorModificaciones next;
	/**
	 * Usuario que esta realizando la modificación
	 * */
	protected UsuarioDTO usuario;
	/**
	 * Identificador del avaluo sobre el cual se esta realizando 
	 * la modificación.
	 * */
	protected Long avaluoId;
	/**
	 * Objeto en el estado anterior de la modificación
	 * */
	protected Object anterior;
	/**
	 * Objeto en el estado nuevo de la modificación
	 * */
	protected Object nuevo;
	/**
	 * Nombre del campo sobre el cual se esta realizando la modificación
	 * */
	protected String campo;
	
	/**
	 * Constructor que recibe la información basica para generar
	 * la modificación.
	 * */
	public AbstractGeneradorModificaciones(String campo,Object anterior, Object nuevo,Long avaluoId,UsuarioDTO usuario){
		this.campo = campo;
		this.anterior = anterior;
		this.nuevo = nuevo;
		this.avaluoId = avaluoId;
		this.usuario = usuario;
	}
	/**
	 * Método que es implementado para personalizar la creación
	 * de una modificación teniendo el cuenta el tipo del campo.
	 * */
	public abstract ModificacionDTO getModificacion();

	/**
	 * Obtiene el el siguiente elemento en la cadena de responsabilidad
	 * */
	public AbstractGeneradorModificaciones getNext() {
		return next;
	}
	
	/**
	 * Establece el siguiente elemento en la cadena de responsabilidad
	 * */
	public void setNext(AbstractGeneradorModificaciones next) {
		this.next = next;
	}
	
}
