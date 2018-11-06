package com.helio4.bancol.avaluos.persistencia;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.helio4.bancol.avaluos.modelo.Segmento;
import com.helio4.bancol.avaluos.modelo.TipoInmueble;
import com.helio4.bancol.avaluos.dto.ObjetoAvaluoDTO;

public interface ListaRepository extends JpaRepository<Segmento, Long> {
	
	@Query("SELECT t FROM TipoInmueble t WHERE t.id = :id")
	public TipoInmueble encontrarTipoInmueblePorId(@Param("id") Long id);
	
	@Query("SELECT t FROM TipoInmueble t")
	public List<TipoInmueble> encontrarTiposInmueble();

    @Query("SELECT NEW com.helio4.bancol.avaluos.dto.ObjetoAvaluoDTO(o.id, o.nombre) FROM ObjetoAvaluo o")
    public List<ObjetoAvaluoDTO> encontrarTodosObjetosAvaluo();

    @Query("SELECT NEW com.helio4.bancol.avaluos.dto.ObjetoAvaluoDTO(o.id, o.nombre) FROM ObjetoAvaluo o WHERE o.nombre = :nombre")
    public ObjetoAvaluoDTO encontrarObjetoAvaluoPorNombre(@Param("nombre") String nombre);

    @Query("SELECT NEW com.helio4.bancol.avaluos.dto.ObjetoAvaluoDTO(o.id, o.nombre) FROM ObjetoAvaluo o WHERE o.id = :id")
    public ObjetoAvaluoDTO encontrarObjetoAvaluoPorId(@Param("id") Long id);
}
