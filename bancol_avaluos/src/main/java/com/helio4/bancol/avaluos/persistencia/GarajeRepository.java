package com.helio4.bancol.avaluos.persistencia;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.helio4.bancol.avaluos.dto.GarajeDTO;
import com.helio4.bancol.avaluos.modelo.Garaje;

@Repository
public interface GarajeRepository  extends JpaRepository<Garaje, Long> {
	
	@Query("SELECT  NEW com.helio4.bancol.avaluos.dto.GarajeDTO(g.id,g.numeroGaraje,g.matriculaInmobiliaria,g.doble,g.cubierto,g.tipo, g.avaluo.id, g.servidumbre, g.paralelo) FROM Garaje g WHERE g.avaluo.id =:avaluoId ")
	public List<GarajeDTO> encontrarGarajes(@Param("avaluoId")  Long avaluoId);

}
