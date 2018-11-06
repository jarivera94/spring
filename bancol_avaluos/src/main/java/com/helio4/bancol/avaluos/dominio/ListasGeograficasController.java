package com.helio4.bancol.avaluos.dominio;

import java.util.List;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.DivipolaDTO;
import com.helio4.bancol.avaluos.modelo.Pais;
import com.helio4.bancol.avaluos.servicio.datos.DivipolaService;
import com.helio4.bancol.avaluos.servicio.datos.PaisService;

@Component
public class ListasGeograficasController {

	@Autowired
	@Qualifier("repositoryDivipolaService")
	private DivipolaService divipolaService;

	@Autowired
	@Qualifier("repositoryPaisService")
	private PaisService paisService;

	public TreeMap<String, String> departamentos() {
		TreeMap<String, String> departamentos = new TreeMap<String, String>();
		for (String departamento:divipolaService.encontrarDepartamentos()) {
			departamentos.put(departamento, departamento);
		}
		return departamentos;
	}

	public List<DivipolaDTO> ciudadesEnDepartamento(String departamento) {
		return (departamento != null) ? divipolaService.encontrarPorDepartamento(departamento) : divipolaService.encontrarPorDepartamento("");
	}

	public List<DivipolaDTO> ciudadesPorDepartamento(String departamento) {
		return (departamento != null) ? divipolaService.encontrarPorDepartamento(departamento) : divipolaService.encontrarPorDepartamento("");
	}
	public List<DivipolaDTO> obtenerMunicipios() {
		return divipolaService.encontrarMunicipios();
	}

	public TreeMap<String, String> ciudad(String ciudad) {
		TreeMap<String, String> ciudades = new TreeMap<String, String>();
		for (String ciudad2:divipolaService.encontrarCiudades()) {
			if (ciudad.equals(ciudad2)) {
				ciudades.put(ciudad2, ciudad2);
			}
		}
		return ciudades;
	}

	public TreeMap<String, String> paises() {
		TreeMap<String, String> paises = new TreeMap<String, String>();
		for (Pais pais : paisService.encontrarTodos()) {
			paises.put(pais.getName(), pais.getId());
		}
		return paises;
	}

}
