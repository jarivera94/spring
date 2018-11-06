package com.helio4.bancol.avaluos.persistencia;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.helio4.bancol.avaluos.modelo.Divipola;
import com.helio4.bancol.avaluos.dto.DivipolaDTO;

@Repository
public interface DivipolaRepository extends JpaRepository<Divipola, Long> {

	@Query(value = "SELECT DISTINCT departamento FROM avaluos.divipola ORDER BY departamento ASC", nativeQuery = true)
	public Set<String> encontrarDepartamentos();

	@Query(value = "SELECT DISTINCT municipio FROM avaluos.divipola ORDER BY municipio ASC", nativeQuery = true)
	public Set<String> encontrarCiudades();

	@Query("SELECT NEW com.helio4.bancol.avaluos.dto.DivipolaDTO"
			+ "(d.id, d.departamento, d.centroPoblado, d.municipio) FROM Divipola d "
			+ " WHERE LOWER(d.departamento) = LOWER(:departamento) "
			+ " AND d.tipoCentroPoblado = 'CM' ORDER BY d.capital, d.municipio")
	public List<DivipolaDTO> encontrarPor(@Param("departamento") String departamento);

	@Query("SELECT NEW com.helio4.bancol.avaluos.dto.DivipolaDTO"
			+ "(d.id, d.departamento, d.centroPoblado, d.municipio) FROM Divipola d "
			+ " WHERE LOWER(d.departamento) = LOWER(:departamento) "
			+ " AND	LOWER(d.centroPoblado) = LOWER(:centroPoblado)"
			+ " AND d.tipoCentroPoblado = 'CM' ORDER BY d.capital, d.municipio")
	public List<DivipolaDTO> encontrarPorDepartamentoAndCentroPoblado(@Param("departamento") String departamento,
			@Param("centroPoblado") String centroPoblado);

	@Query("SELECT d FROM Divipola d WHERE LOWER(d.municipio) = LOWER(:nombre)")
	public List<Divipola> encontrarMunicipioPor(@Param("nombre") String nombre);

	@Query("SELECT d FROM Divipola d WHERE LOWER(d.departamento) = LOWER(:nombre)")
	public List<Divipola> encontrarDepartamentoPor(@Param("nombre") String nombre);

	@Query("SELECT d.regional.id FROM Divipola d WHERE d.id = :id")
	public Long cargarRegionalId(@Param("id") Long divipolaId);

	@Query("SELECT d.municipioBUA FROM Divipola d WHERE LOWER(d.municipio) = LOWER(:ciudad)")
	public Integer encontrarCodigoBUA(@Param("ciudad") String ciudad);

	@Query("SELECT d.departamentoBUA FROM Divipola d WHERE LOWER(d.departamento) = LOWER(:departamento)")
	public Integer encontrarDepartamentoBUA(@Param("departamento") String departamento);

	@Query("SELECT  NEW com.helio4.bancol.avaluos.dto.DivipolaDTO(d.id, d.departamento, d.centroPoblado, d.municipio) "
			+ " FROM Divipola d WHERE d.codigoCentroPoblado=:codigo")
	public DivipolaDTO encontrarPorCodigoCentroPoblado(@Param("codigo") Integer codigo);
	
	@Query("SELECT  NEW com.helio4.bancol.avaluos.dto.DivipolaDTO(d.id, d.departamento, d.centroPoblado, d.municipio) "
			+ " FROM Divipola d WHERE d.tipoCentroPoblado='CM' ORDER BY d.departamento")
	public List<DivipolaDTO> encontrarMunicipio();
	
	@Query("SELECT  NEW com.helio4.bancol.avaluos.dto.DivipolaDTO(d.id, d.departamento, d.centroPoblado, d.municipio) "
			+ " FROM Divipola d WHERE d.departamentoIdTinsa=:departamentoId and d.municipioIdTinsa = :municipioId and d.tipoCentroPoblado='CM'")
	public DivipolaDTO encontrarPorIdentificacionTinsa(@Param("departamentoId") Integer departamentoId, @Param("municipioId") Integer municipioId);
}
