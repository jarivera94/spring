package com.helio4.bancol.avaluos.persistencia;

import com.helio4.bancol.avaluos.modelo.Subsidio;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for entity {@link Subsidio}
 * @author <a href="mailto:gersonsosa@gmail.com">Gerson Sosa</a>
 */
public interface SubsidioRepository extends JpaRepository<Subsidio, Long> {
}
