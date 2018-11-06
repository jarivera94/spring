package com.helio4.bancol.avaluos.servicio;

import com.helio4.bancol.avaluos.dto.SubsidioDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service that performs additional logic when bringing data from database
 * @author <a href="mailto:gersonsosa@gmail.com">Gerson Sosa</a>
 */
@Service
public interface SubsidioService {
    /**
     * Builds a list of all {@link com.helio4.bancol.avaluos.modelo.Subsidio}
     * and multiply its value to get current cash minimum and maximum.
     * @return a list contaning all {@link SubsidioDTO}
     */
    List<SubsidioDTO> encontrarTodos();
}
