package com.helio4.bancol.avaluos.persistencia;

import com.helio4.bancol.avaluos.modelo.TiempoComercializacion;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for {@link TiempoComercializacion}
 * @author <a href="mailto:gersonsosa@gmail.com">Gerson Sosa</a>
 */
public interface TiempoComercializacionRepository extends JpaRepository<TiempoComercializacion, Long> {
}
