package com.helio4.bancol.avaluos.persistencia;

import com.helio4.bancol.avaluos.modelo.SalarioMinimo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for class {@link SalarioMinimo}
 * @author <a href="mailto:gersonsosa@gmail.com">Gerson Sosa</a>
 */
public interface SalarioMinimoRepository extends JpaRepository<SalarioMinimo, Long> {
    SalarioMinimo findFirstByOrderByFechaVigenciaDesc();
}
