package com.helio4.bancol.avaluos.servicio;

import java.util.List;
import java.util.Set;

import com.helio4.bancol.avaluos.servicio.datos.DivipolaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helio4.bancol.avaluos.dto.DivipolaDTO;
import com.helio4.bancol.avaluos.ensamblador.DivipolaEnsamblador;
import com.helio4.bancol.avaluos.modelo.Divipola;
import com.helio4.bancol.avaluos.persistencia.DivipolaRepository;

@Component(value="repositoryDivipolaService")
@Transactional(readOnly = true)
public class RepositoryDivipolaService implements DivipolaService {

	@Autowired
	private DivipolaRepository divipolaRepository;

	@Autowired
	private DivipolaEnsamblador divipolaEnsamblador;

	@Transactional(readOnly = true)
	@Override
	public List<DivipolaDTO> encontrarTodos() {
		return divipolaEnsamblador.escribirListaDTO(divipolaRepository.findAll());
	}

	@Transactional(readOnly = true)
	@Override
	public DivipolaDTO encontrarPorId(Long id) {
		return divipolaEnsamblador.escribirDTO(divipolaRepository.findOne(id));
	}

	@Transactional(readOnly = true)
	@Override
	public Set<String> encontrarDepartamentos() {
		return divipolaRepository.encontrarDepartamentos();
	}

	@Transactional(readOnly = true)
	@Override
	public List<DivipolaDTO> encontrarPorDepartamento(String departamento) {
		return divipolaRepository.encontrarPor(departamento);
	}

	@Transactional(readOnly = true)
	@Override
	public Set<String> encontrarCiudades() {
		return divipolaRepository.encontrarCiudades();
	}

	@Transactional(readOnly = true)
	@Override
	public DivipolaDTO encontrarMunicipioPor(String nombre) {
		List<Divipola> resultado = divipolaRepository.encontrarMunicipioPor(nombre);
		if (!resultado.isEmpty()) {
			return divipolaEnsamblador.escribirDTO(resultado.get(0));
		}
		return null;
	}

	@Transactional(readOnly = true)
	@Override
	public DivipolaDTO encontrarDepartamentoPor(String nombre) {
		return divipolaEnsamblador.escribirDTO(divipolaRepository.encontrarDepartamentoPor(nombre).get(0));
	}

	@Transactional(readOnly = true)
    @Override
    public Long cargarRegionalId(Long divipolaId) {
        return divipolaRepository.cargarRegionalId(divipolaId);
    }

	@Transactional(readOnly = true)
	@Override
    public Integer encontrarCodigoBUA(String ciudad) {
        return divipolaRepository.encontrarCodigoBUA(ciudad);
    }

	@Transactional(readOnly = true)
	@Override
    public Integer encontrarDepartamentoBUA(String departamento) {
        return divipolaRepository.encontrarDepartamentoBUA(departamento);
    }

	@Transactional(readOnly = true)
	@Override
	public List<DivipolaDTO> encontrarMunicipios() {
		return divipolaRepository.encontrarMunicipio();
	}

	@Transactional(readOnly = true)
	@Override
	public List<DivipolaDTO> encontrarPorDepartamentoAndCentroPoblado(String departamento, String centroPoblado) {
		return divipolaRepository.encontrarPorDepartamentoAndCentroPoblado(departamento, centroPoblado);
	}

	@Transactional(readOnly = true)
	@Override
	public DivipolaDTO encontrarPorCodigoCentroPoblado(Integer codigo) {
			return divipolaRepository.encontrarPorCodigoCentroPoblado(codigo);
	}
	
	@Transactional(readOnly = true)
	@Override
	public DivipolaDTO encontrarPorIdentificacionTinsa(Integer departamentoId, Integer municipioId) {
			return divipolaRepository.encontrarPorIdentificacionTinsa(departamentoId, municipioId);
	}
 
}
