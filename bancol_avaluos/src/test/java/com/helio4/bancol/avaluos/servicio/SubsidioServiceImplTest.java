package com.helio4.bancol.avaluos.servicio;

import com.helio4.bancol.avaluos.dto.SubsidioDTO;
import com.helio4.bancol.avaluos.modelo.SalarioMinimo;
import com.helio4.bancol.avaluos.modelo.Subsidio;
import com.helio4.bancol.avaluos.persistencia.SalarioMinimoRepository;
import com.helio4.bancol.avaluos.persistencia.SubsidioRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Test class for {@link SubsidioServiceImpl}
 * @author <a href="mailto:gersonsosa@gmail.com">Gerson Sosa</a>
 */
public class SubsidioServiceImplTest {

    public static final long ID = 1L;
    public static final int MINIMO = 70;
    public static final int MAXIMO = 135;
    public static final String NOMBRE_SUBSIDIO = "VIS";
    public static final int SALARIO_VIGENTE = 737717;

    @Test
    public void shouldReturnSubsidioDTOWithCashValues() throws Exception {
        SubsidioRepository subsidioRepository = Mockito.mock(SubsidioRepository.class);
        SalarioMinimoRepository salarioMinimoRepository = Mockito.mock(SalarioMinimoRepository.class);
        final Subsidio subsidio = new Subsidio();
        subsidio.setId(ID);
        subsidio.setMinimo(MINIMO);
        subsidio.setMaximo(MAXIMO);
        subsidio.setNombre(NOMBRE_SUBSIDIO);
        Mockito.when(subsidioRepository.findAll()).thenReturn(Collections.singletonList(subsidio));
        final SalarioMinimo salarioMinimo = new SalarioMinimo();
        salarioMinimo.setId(ID);
        salarioMinimo.setMensual(new BigDecimal(SALARIO_VIGENTE));
        salarioMinimo.setFechaVigencia(new Date());
        Mockito.when(salarioMinimoRepository.findFirstByOrderByFechaVigenciaDesc()).thenReturn(salarioMinimo);
        SubsidioServiceImpl subsidioService = new SubsidioServiceImpl(subsidioRepository, salarioMinimoRepository);
        List<SubsidioDTO> result = subsidioService.encontrarTodos();
        Assertions.assertThat(result.size()).isEqualTo(1);
        Assertions.assertThat(result.get(0).getId()).isEqualTo(ID);
        Assertions.assertThat(result.get(0).getNombre()).isEqualTo(NOMBRE_SUBSIDIO);
        Assertions.assertThat(result.get(0).getMinimo()).isEqualTo(BigDecimal.valueOf(MINIMO * SALARIO_VIGENTE));
        Assertions.assertThat(result.get(0).getMaximo()).isEqualTo(BigDecimal.valueOf(MAXIMO * SALARIO_VIGENTE));
    }
}