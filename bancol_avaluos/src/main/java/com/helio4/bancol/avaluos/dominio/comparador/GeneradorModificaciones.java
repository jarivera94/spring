package com.helio4.bancol.avaluos.dominio.comparador;

import com.helio4.bancol.avaluos.dto.ModificacionDTO;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;

/**
 * Clase que se encarga de construir la cadena de responsabilidad
 * para generar las modificaciones personalizadas.
 * */
public class GeneradorModificaciones {
	
	protected UsuarioDTO usuario;
	protected Long avaluoId;
	protected Object anterior;
	protected Object nuevo;
	protected String campo;
	
	/**
	 * Constructor que recibe la información basica para generar
	 * la modificación.
	 * */
	public GeneradorModificaciones(String campo,Object anterior, Object nuevo,Long avaluoId,UsuarioDTO usuario){
		this.campo = campo;
		this.anterior = anterior;
		this.nuevo = nuevo;
		this.avaluoId = avaluoId;
		this.usuario = usuario;
	}
	/**
	 * Obtiene la modificacion personalizada.
	 * */
	public ModificacionDTO getModificacion() {
		ListaDesplegableGeneradorModificaciones listaDesplegable =  new ListaDesplegableGeneradorModificaciones(campo, anterior, nuevo, avaluoId, usuario);
		DivipolaGeneradorModificaciones divipolaComparador = new DivipolaGeneradorModificaciones(campo, anterior, nuevo, avaluoId, usuario);
		SegmentoGeneradorModificaciones segmentoComparador = new SegmentoGeneradorModificaciones(campo, anterior, nuevo, avaluoId, usuario);
		BooleanGeneradorModificaciones booleanComparador  = new BooleanGeneradorModificaciones(campo, anterior, nuevo, avaluoId, usuario);
		DateGeneradorModificaciones dateComparador = new DateGeneradorModificaciones(campo, anterior, nuevo, avaluoId, usuario);
		MonedaGeneradorModificaciones valorRazonableComparador = new MonedaGeneradorModificaciones(campo, anterior, nuevo, avaluoId, usuario);
		AreaGeneradorModificaciones areaComparador = new AreaGeneradorModificaciones(campo, anterior, nuevo, avaluoId, usuario);
		PorcentajeGeneradorModificaciones porcentajeComparador = new PorcentajeGeneradorModificaciones(campo, anterior, nuevo, avaluoId, usuario);
		DefaultGeneradorModificaciones defaultComparador = new DefaultGeneradorModificaciones(campo, anterior, nuevo, avaluoId, usuario);

		
		/**El orden de succesioon influye en los resultados(modificaciones repetidas...)*/
		areaComparador.setNext(listaDesplegable);
		listaDesplegable.setNext(divipolaComparador);
		divipolaComparador.setNext(segmentoComparador );
		segmentoComparador.setNext(booleanComparador);
		booleanComparador.setNext(dateComparador );
		dateComparador.setNext(valorRazonableComparador);
		//el comparador por defualt debe ser el ultimo en ser 
				//asignado, ya que este no verifica nada.
		valorRazonableComparador.setNext(porcentajeComparador);
		porcentajeComparador.setNext(defaultComparador);
		return areaComparador.getModificacion();
	}
}
