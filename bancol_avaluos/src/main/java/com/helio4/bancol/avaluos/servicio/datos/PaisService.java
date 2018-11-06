package com.helio4.bancol.avaluos.servicio.datos;

import java.util.List;

import org.springframework.stereotype.Service;

import com.helio4.bancol.avaluos.modelo.Pais;

@Service
public interface PaisService {
	
	public List<Pais> encontrarTodos();
	
	public Pais encontrarPorId(String id);

}
