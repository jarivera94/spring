package com.helio4.bancol.avaluos.servicio;

import com.helio4.bancol.avaluos.dto.SubsidioDTO;
import com.helio4.bancol.avaluos.modelo.SalarioMinimo;
import com.helio4.bancol.avaluos.modelo.Subsidio;
import com.helio4.bancol.avaluos.persistencia.SalarioMinimoRepository;
import com.helio4.bancol.avaluos.persistencia.SubsidioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the service interface {@link SubsidioService}
 *
 * @author <a href="mailto:gersonsosa@gmail.com">Gerson Sosa</a>
 */
@Service
public class SubsidioServiceImpl implements SubsidioService {
    private final SubsidioRepository subsidioRepository;
    private final SalarioMinimoRepository salarioMinimoRepository;

    @Autowired
    public SubsidioServiceImpl(SubsidioRepository subsidioRepository, SalarioMinimoRepository salarioMinimoRepository) {
        this.subsidioRepository = subsidioRepository;
        this.salarioMinimoRepository = salarioMinimoRepository;
    }

    @Override
    public List<SubsidioDTO> encontrarTodos() {
        final List<Subsidio> subsidios = subsidioRepository.findAll();
        final SalarioMinimo salarioVigente = salarioMinimoRepository.findFirstByOrderByFechaVigenciaDesc();
        return subsidios.stream().map(subsidio -> {
            SubsidioDTO subsidioDTO = new SubsidioDTO();
            subsidioDTO.setId(subsidio.getId());
            subsidioDTO.setNombre(subsidio.getNombre());
            subsidioDTO.setMinimo(salarioVigente.getMensual().multiply(BigDecimal.valueOf(subsidio.getMinimo())));
            subsidioDTO.setMaximo(salarioVigente.getMensual().multiply(BigDecimal.valueOf(subsidio.getMaximo())));
            subsidioDTO.setMinimoSalarios(subsidio.getMinimo());
            subsidioDTO.setMaximoSalarios(subsidio.getMaximo());
            return subsidioDTO;
        }).collect(Collectors.toList());
    }
}
