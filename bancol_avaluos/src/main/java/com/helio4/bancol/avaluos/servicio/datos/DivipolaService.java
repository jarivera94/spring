package com.helio4.bancol.avaluos.servicio.datos;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.helio4.bancol.avaluos.dto.DivipolaDTO;

@Service
public interface DivipolaService {
	
	public List<DivipolaDTO> encontrarTodos();
	
	public DivipolaDTO encontrarPorId(Long id);
	
	public Set<String> encontrarDepartamentos();
	
	public Set<String> encontrarCiudades();
	
	public List<DivipolaDTO> encontrarPorDepartamento(String departamento);
	
	public DivipolaDTO encontrarMunicipioPor(String nombre);
	public DivipolaDTO encontrarDepartamentoPor(String nombre);
	
	public List<DivipolaDTO> encontrarPorDepartamentoAndCentroPoblado(String departamento, String centroPoblado);
	
	public DivipolaDTO encontrarPorCodigoCentroPoblado(Integer codigo);

    Integer encontrarCodigoBUA(String ciudad);

    Integer encontrarDepartamentoBUA(String departamento);

    /**
     * Encuentra el identificador de la regional
     * basado en el identificador del objeto Divipola
     * @param divipolaId el identificador del objeto divipola
     * @return el identificador de la regional asociada
     */
    Long cargarRegionalId(Long divipolaId);

	public List<DivipolaDTO> encontrarMunicipios();
	
	public DivipolaDTO encontrarPorIdentificacionTinsa(Integer departamentoId, Integer municipioId);

}
