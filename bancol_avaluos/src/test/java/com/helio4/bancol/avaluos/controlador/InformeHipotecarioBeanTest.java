package com.helio4.bancol.avaluos.controlador;

import com.helio4.bancol.avaluos.dominio.*;
import com.helio4.bancol.avaluos.dto.*;
import com.helio4.bancol.avaluos.servicio.datos.MotivoService;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.context.MessageSource;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * @author <a href="mailto:gersonsosa@gmail.com">Gerson Sosa</a>
 */
public class InformeHipotecarioBeanTest {

    private static final int AREA1 = 120;
    private static final int UNIT_VALUE1 = 1200000;
    private static final int AREA2 = 130;
    private static final int UNIT_VALUE2 = 1250000;
    private static final int AREA3 = 1;
    private static final int UNIT_VALUE3 = 6000000;
    private static final int TOTAL_VALUE_PH = 312500000;
    private static final int TERRAIN_PERCENTAGE = 20;
    private static final int VALOR_PROPORCIONAL_TERRENO_PH = 62500000;
    private static final int VALOR_PROPORCIONAL_CONSTUCCION_PH = TOTAL_VALUE_PH - VALOR_PROPORCIONAL_TERRENO_PH;
    private static final BigDecimal VALOR_INTEGRAL_CONSTRUCCION = new BigDecimal(1250000).setScale(4, BigDecimal.ROUND_HALF_EVEN);

    private static final int TOTAL_VALUE_NO_PH = 638440000;
    private static final int AREA4 = 100;
    private static final int UNIT_VALUE4 = 1500000;
    private static final int AREA5 = 110;
    private static final int UNIT_VALUE5 = 1654000;
    private static final BigDecimal PROPORTIONAL_CONSTRUCTION_VALUE_NO_PH = new BigDecimal(331940000);
    private static final BigDecimal TERRAIN_PERCENTAGE_NO_PH = new BigDecimal(54.347826087, new MathContext(9));
    private static final BigDecimal TERRAIN_PROPORTIONAL_VALUE_NO_PH = new BigDecimal(306500000);
    private static final BigDecimal INTEGRAL_CONSTRUCTION_VALUE_NO_PH = new BigDecimal(3040190.4762).setScale(4, BigDecimal.ROUND_HALF_EVEN);
    private static final BigDecimal INTEGRAL_TERRAIN_VALUE_NO_PH = new BigDecimal(2553760);

    private static final int INSURABLE_PERCENTAGE = 70;
    private static final int AREA_OTHER = 30;
    private static final int UNIT_VALUE_OTHER = 1250000;
    private static final BigDecimal TOTAL_CONSTRUCTED_AREAS_WITH_OTHERS_NO_PH = new BigDecimal(369440000);
    private static final BigDecimal TOTAL_VALUE_OTHER = new BigDecimal(AREA_OTHER * UNIT_VALUE_OTHER);
    private static final BigDecimal TOTAL_VALUE_NO_PH_WITH_OTHER = new BigDecimal(TOTAL_VALUE_NO_PH).add(TOTAL_VALUE_OTHER);
    private static final BigDecimal PROPORTIONAL_CONSTRUCTION_VALUE_OTHERS_NO_PH = new BigDecimal(369440000);
    private static final BigDecimal TERRAIN_INTEGRAL_VALUE_NO_PH = new BigDecimal(2703760);
    private static final BigDecimal TOTAL_VALUE_PH_WITH_OTHER = new BigDecimal(350000000);
    private static final BigDecimal TOTAL_PRIVATE_AREAS_WITH_OTHERS_PH = new BigDecimal(344000000);

    @Mock
    private ComparacionMercadoBean comparacionMercadoBean;
    @Mock
    private AvaluoController avaluoController;
    @Mock
    private InformeHipotecarioController informeHipotecarioController;
    @Mock
    private ListasGeograficasController listasGeograficasController;
    @Mock
    private ListadoAvaluosBean listadoAvaluosBean;
    @Mock
    private TiposAvaluoController tiposAvaluoController;
    @Mock
    private ListasController listasController;
    @Mock
    private FotografiasController fotografiasController;
    @Mock
    private AprobarAvaluoController aprobarAvaluoController;
    @Mock
    private ModificacionController modificacionController;
    @Mock
    private EstadoAvaluoController estadoAvaluoController;
    @Mock
    private ParametrosController parametrosController;
    @Mock
    private EditarClienteController clienteController;
    @Mock
    private MessageSource messageSource;
    private InformeHipotecarioBean informeHipotecarioBean;

    @Mock
    private MotivoService motivoService;
    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        informeHipotecarioBean = new InformeHipotecarioBean(avaluoController,
                comparacionMercadoBean,
                informeHipotecarioController,
                listasGeograficasController,
                listadoAvaluosBean,
                tiposAvaluoController,
                parametrosController,
                listasController,
                fotografiasController,
                aprobarAvaluoController,
                modificacionController,
                clienteController,
                estadoAvaluoController,
                messageSource,
                motivoService);
    }

    @Test
    public void shouldCalculateValuesInZeroWithNoAreas() throws Exception {
        final List<AreaDTO> listaAreas = Collections.emptyList();
        final FormatoInformeHipotecarioDTO formatoInforme = givenAnAppraisalReport(0, Boolean.TRUE);
        final AvaluoHipotecarioDTO avaluoHipotecarioDTO = givenAnAppraisalWithAreas(listaAreas, formatoInforme, givenAnEntity());
        informeHipotecarioBean.setListaAreas(listaAreas);
        informeHipotecarioBean.setInformeHipotecarioDTO(formatoInforme);
        informeHipotecarioBean.setAvaluoHipotecarioDTO(avaluoHipotecarioDTO);
        informeHipotecarioBean.calcularValores();
        Assertions.assertThat(formatoInforme.getPorcentajeTerreno()).isNull();
        Assertions.assertThat(formatoInforme.getValorProporcionalConstruccion()).isZero();
    }

    @Test
    public void shouldCalculateValuesInZeroWithEmptyAreasPh() throws Exception {
        final List<AreaDTO> listaAreas = givenAListOfEmptyAreasPh();
        final FormatoInformeHipotecarioDTO formatoInforme = givenAnAppraisalReport(0, Boolean.TRUE);
        final AvaluoHipotecarioDTO avaluoHipotecarioDTO = givenAnAppraisalWithAreas(listaAreas, formatoInforme, givenAnEntity());
        informeHipotecarioBean.setListaAreas(listaAreas);
        informeHipotecarioBean.setInformeHipotecarioDTO(formatoInforme);
        informeHipotecarioBean.setAvaluoHipotecarioDTO(avaluoHipotecarioDTO);
        informeHipotecarioBean.calcularValores();
        Assertions.assertThat(formatoInforme.getPorcentajeTerreno()).isNull();
        Assertions.assertThat(formatoInforme.getValorProporcionalConstruccion()).isZero();
    }

    private List<AreaDTO> givenAListOfEmptyAreasPh() {
        return Arrays.asList(
                givenAnIncompleteArea(AreaDTO.DescripcionAreaPH.AreaPrivada),
                givenAnIncompleteArea(AreaDTO.DescripcionAreaPH.AreaLibre),
                givenAnIncompleteArea(AreaDTO.DescripcionAreaPH.Deposito),
                givenAnIncompleteArea(AreaDTO.DescripcionAreaPH.Garaje),
                givenAnIncompleteArea(AreaDTO.DescripcionAreaPH.Otro)
        );
    }

    @Test
    public void shouldCalculateValuesInZeroWithEmptyAreasNoPh() throws Exception {
        final List<AreaDTO> listaAreas = givenAListOfEmptyAreasNoPh();
        final FormatoInformeHipotecarioDTO formatoInforme = givenAnAppraisalReport(0, Boolean.TRUE);
        final AvaluoHipotecarioDTO avaluoHipotecarioDTO = givenAnAppraisalWithAreas(listaAreas, formatoInforme, givenAnEntity());
        informeHipotecarioBean.setListaAreas(listaAreas);
        informeHipotecarioBean.setInformeHipotecarioDTO(formatoInforme);
        informeHipotecarioBean.setAvaluoHipotecarioDTO(avaluoHipotecarioDTO);
        informeHipotecarioBean.calcularValores();
        Assertions.assertThat(formatoInforme.getPorcentajeTerreno()).isNull();
        Assertions.assertThat(formatoInforme.getValorProporcionalConstruccion()).isZero();
    }

    private List<AreaDTO> givenAListOfEmptyAreasNoPh() {
        return Arrays.asList(
                givenAnIncompleteArea(AreaDTO.DescripcionAreaNoPH.AreaTerreno),
                givenAnIncompleteArea(AreaDTO.DescripcionAreaNoPH.AreaConstruccion),
                givenAnIncompleteArea(AreaDTO.DescripcionAreaNoPH.Otros)
        );
    }

    @Test
    public void shouldInvokeTotalValueInController() throws Exception {
        final List<AreaDTO> listaAreas = givenAListOfAreasPh();
        final FormatoInformeHipotecarioDTO formatoInforme = givenAnAppraisalReport(0, Boolean.TRUE);
        final AvaluoHipotecarioDTO avaluoHipotecarioDTO = givenAnAppraisalWithAreas(listaAreas, formatoInforme, givenAnEntity());
        informeHipotecarioBean.setListaAreas(listaAreas);
        informeHipotecarioBean.setInformeHipotecarioDTO(formatoInforme);
        informeHipotecarioBean.setAvaluoHipotecarioDTO(avaluoHipotecarioDTO);
        informeHipotecarioBean.calcularValores();
        Mockito.verify(informeHipotecarioController).calcularValorTotal(listaAreas);
    }

    @Test
    public void shouldInvokeCalculateUvrValueInController() throws Exception {
        final List<AreaDTO> listaAreas = givenAListOfAreasPh();
        final FormatoInformeHipotecarioDTO formatoInforme = givenAnAppraisalReport(0, Boolean.TRUE);
        final EntidadDTO entidad = givenAnEntity();
        final AvaluoHipotecarioDTO avaluoHipotecarioDTO = givenAnAppraisalWithAreas(listaAreas, formatoInforme, entidad);
        final BigDecimal valorUvr = new BigDecimal(123.78);
        avaluoHipotecarioDTO.setValorUvr(valorUvr);
        final BigDecimal valorTotal = new BigDecimal(TOTAL_VALUE_PH);
        Mockito.when(informeHipotecarioController.calcularValorTotal(listaAreas)).thenReturn(valorTotal);
        informeHipotecarioBean.setListaAreas(listaAreas);
        informeHipotecarioBean.setInformeHipotecarioDTO(formatoInforme);
        informeHipotecarioBean.setAvaluoHipotecarioDTO(avaluoHipotecarioDTO);
        informeHipotecarioBean.calcularValores();
        Mockito.verify(informeHipotecarioController).calcularValorEnUVR(valorUvr, valorTotal);
    }

    @Test
    public void shouldCalculateTotalAreaPh() throws Exception {
        final List<AreaDTO> listaAreas = givenAListOfAreasPh();
        final FormatoInformeHipotecarioDTO formatoInforme = givenAnAppraisalReport(0, Boolean.TRUE);
        final EntidadDTO entidad = givenAnEntity();
        final AvaluoHipotecarioDTO avaluoHipotecarioDTO = givenAnAppraisalWithAreas(listaAreas, formatoInforme, entidad);
        informeHipotecarioBean.setListaAreas(listaAreas);
        informeHipotecarioBean.setInformeHipotecarioDTO(formatoInforme);
        informeHipotecarioBean.setAvaluoHipotecarioDTO(avaluoHipotecarioDTO);
        informeHipotecarioBean.calcularValores();
        Assertions.assertThat(avaluoHipotecarioDTO.getAreaTotal()).isEqualTo(new BigDecimal(AREA1+AREA2+AREA3));
    }

    @Test
    public void shouldCalculateContructionAreaNoPh() throws Exception {
        final List<AreaDTO> listaAreas = givenAListOfAreasNoPh();
        final FormatoInformeHipotecarioDTO formatoInforme = givenAnAppraisalReport(0, Boolean.TRUE);
        final EntidadDTO entidad = givenAnEntity();
        final AvaluoHipotecarioDTO avaluoHipotecarioDTO = givenAnAppraisalWithAreas(listaAreas, formatoInforme, entidad);
        informeHipotecarioBean.setListaAreas(listaAreas);
        informeHipotecarioBean.setInformeHipotecarioDTO(formatoInforme);
        informeHipotecarioBean.setAvaluoHipotecarioDTO(avaluoHipotecarioDTO);
        informeHipotecarioBean.calcularValores();
        Assertions.assertThat(formatoInforme.getAreaConstruida()).isEqualTo(new BigDecimal(AREA4+AREA5));
    }

    @Test
    public void shouldCalculateIntegralValueConstructionNoPh() throws Exception {
        final List<AreaDTO> listaAreas = givenAListOfAreasNoPh();
        Mockito.when(informeHipotecarioController.calcularValorTotal(listaAreas)).thenReturn(new BigDecimal(TOTAL_VALUE_NO_PH));
        final FormatoInformeHipotecarioDTO formatoInforme = givenAnAppraisalReport(0, Boolean.FALSE);
        final EntidadDTO entidad = givenAnEntity();
        final AvaluoHipotecarioDTO avaluoHipotecarioDTO = givenAnAppraisalWithAreas(listaAreas, formatoInforme, entidad);
        informeHipotecarioBean.setListaAreas(listaAreas);
        informeHipotecarioBean.setInformeHipotecarioDTO(formatoInforme);
        informeHipotecarioBean.setAvaluoHipotecarioDTO(avaluoHipotecarioDTO);
        informeHipotecarioBean.calcularValores();
        Assertions.assertThat(((FormatoInformeHipotecarioDTO) avaluoHipotecarioDTO.getFormatoInforme()).getValorIntegralConstruccion()).isEqualTo(INTEGRAL_CONSTRUCTION_VALUE_NO_PH);
    }

    @Test
    public void shouldCalculateProportionalValueOfConstructionNoPh() throws Exception {
        final List<AreaDTO> listaAreas = givenAListOfAreasNoPh();
        Mockito.when(informeHipotecarioController.calcularValorTotal(listaAreas)).thenReturn(new BigDecimal(TOTAL_VALUE_NO_PH));
        final FormatoInformeHipotecarioDTO formatoInforme = givenAnAppraisalReport(0, Boolean.FALSE);
        final EntidadDTO entidad = givenAnEntity();
        final AvaluoHipotecarioDTO avaluoHipotecarioDTO = givenAnAppraisalWithAreas(listaAreas, formatoInforme, entidad);
        informeHipotecarioBean.setListaAreas(listaAreas);
        informeHipotecarioBean.setInformeHipotecarioDTO(formatoInforme);
        informeHipotecarioBean.setAvaluoHipotecarioDTO(avaluoHipotecarioDTO);
        informeHipotecarioBean.calcularValores();
        Assertions.assertThat(((FormatoInformeHipotecarioDTO) avaluoHipotecarioDTO.getFormatoInforme()).getValorProporcionalConstruccion().compareTo(PROPORTIONAL_CONSTRUCTION_VALUE_NO_PH)).isEqualTo(0);
    }

    @Test
    public void shouldCalculateProportionalValueConstructionWithOtherAreasNoPh() throws Exception {
        final List<AreaDTO> listaAreas = givenAListOfAreasWithOtherNoPh();
        Mockito.when(informeHipotecarioController.calcularValorTotal(listaAreas)).thenReturn(TOTAL_VALUE_NO_PH_WITH_OTHER);
        final FormatoInformeHipotecarioDTO formatoInforme = givenAnAppraisalReport(0, Boolean.FALSE);
        final EntidadDTO entidad = givenAnEntity();
        final AvaluoHipotecarioDTO avaluoHipotecarioDTO = givenAnAppraisalWithAreas(listaAreas, formatoInforme, entidad);
        informeHipotecarioBean.setListaAreas(listaAreas);
        informeHipotecarioBean.setInformeHipotecarioDTO(formatoInforme);
        informeHipotecarioBean.setAvaluoHipotecarioDTO(avaluoHipotecarioDTO);
        informeHipotecarioBean.calcularValores();
        Assertions.assertThat(((FormatoInformeHipotecarioDTO) avaluoHipotecarioDTO.getFormatoInforme()).getValorProporcionalConstruccion().compareTo(PROPORTIONAL_CONSTRUCTION_VALUE_OTHERS_NO_PH)).isEqualTo(0);
    }

    @Test
    public void shouldCalculateProportionalValueTerrainNoPh() throws Exception {
        final List<AreaDTO> listaAreas = givenAListOfAreasWithOtherNoPh();
        Mockito.when(informeHipotecarioController.calcularValorTotal(listaAreas)).thenReturn(TOTAL_VALUE_NO_PH_WITH_OTHER);
        final FormatoInformeHipotecarioDTO formatoInforme = givenAnAppraisalReport(0, Boolean.FALSE);
        final EntidadDTO entidad = givenAnEntity();
        final AvaluoHipotecarioDTO avaluoHipotecarioDTO = givenAnAppraisalWithAreas(listaAreas, formatoInforme, entidad);
        informeHipotecarioBean.setListaAreas(listaAreas);
        informeHipotecarioBean.setInformeHipotecarioDTO(formatoInforme);
        informeHipotecarioBean.setAvaluoHipotecarioDTO(avaluoHipotecarioDTO);
        informeHipotecarioBean.calcularValores();
        Assertions.assertThat(((FormatoInformeHipotecarioDTO) avaluoHipotecarioDTO.getFormatoInforme()).getValorProporcionalTerreno().compareTo(TERRAIN_PROPORTIONAL_VALUE_NO_PH)).isEqualTo(0);
    }

    @Test
    public void shouldCalculateIntegralValueTerrainNoPh() throws Exception {
        final List<AreaDTO> listaAreas = givenAListOfAreasWithOtherNoPh();
        Mockito.when(informeHipotecarioController.calcularValorTotal(listaAreas)).thenReturn(TOTAL_VALUE_NO_PH_WITH_OTHER);
        final FormatoInformeHipotecarioDTO formatoInforme = givenAnAppraisalReport(0, Boolean.FALSE);
        final EntidadDTO entidad = givenAnEntity();
        final AvaluoHipotecarioDTO avaluoHipotecarioDTO = givenAnAppraisalWithAreas(listaAreas, formatoInforme, entidad);
        informeHipotecarioBean.setListaAreas(listaAreas);
        informeHipotecarioBean.setInformeHipotecarioDTO(formatoInforme);
        informeHipotecarioBean.setAvaluoHipotecarioDTO(avaluoHipotecarioDTO);
        informeHipotecarioBean.calcularValores();
        Assertions.assertThat(((FormatoInformeHipotecarioDTO) avaluoHipotecarioDTO.getFormatoInforme()).getValorIntegralTerreno().compareTo(TERRAIN_INTEGRAL_VALUE_NO_PH)).isEqualTo(0);
    }

    private List<AreaDTO> givenAListOfAreasWithOtherNoPh() {
        return Arrays.asList(
                givenAnArea(AREA1, UNIT_VALUE1, AreaDTO.DescripcionAreaNoPH.AreaTerreno),
                givenAnArea(AREA2, UNIT_VALUE2, AreaDTO.DescripcionAreaNoPH.AreaTerreno),
                givenAnArea(AREA4, UNIT_VALUE4, AreaDTO.DescripcionAreaNoPH.AreaConstruccion),
                givenAnArea(AREA5, UNIT_VALUE5, AreaDTO.DescripcionAreaNoPH.AreaConstruccion),
                givenAnIncompleteArea(AreaDTO.DescripcionAreaNoPH.AreaConstruccion),
                givenAnArea(AREA_OTHER, UNIT_VALUE_OTHER, AreaDTO.DescripcionAreaNoPH.Otros)
        );
    }

    @Test
    public void shouldCalculatePrivateAreaPh() throws Exception {
        final List<AreaDTO> listaAreas = givenAListOfAreasPh();
        final FormatoInformeHipotecarioDTO formatoInforme = givenAnAppraisalReport(0, Boolean.TRUE);
        final EntidadDTO entidad = givenAnEntity();
        final AvaluoHipotecarioDTO avaluoHipotecarioDTO = givenAnAppraisalWithAreas(listaAreas, formatoInforme, entidad);
        informeHipotecarioBean.setListaAreas(listaAreas);
        informeHipotecarioBean.setInformeHipotecarioDTO(formatoInforme);
        informeHipotecarioBean.setAvaluoHipotecarioDTO(avaluoHipotecarioDTO);
        informeHipotecarioBean.calcularValores();
        Assertions.assertThat(((FormatoInformeHipotecarioDTO) avaluoHipotecarioDTO.getFormatoInforme()).getAreaPrivada()).isEqualTo(new BigDecimal(AREA1+AREA2));
    }

    @Test
    public void shouldCalculateProportionalTerrainValuePh() throws Exception {
        final List<AreaDTO> listaAreas = givenAListOfAreasPh();
        final FormatoInformeHipotecarioDTO formatoInforme = givenAnAppraisalReport(TERRAIN_PERCENTAGE, Boolean.TRUE);
        final EntidadDTO entidad = givenAnEntity();
        final AvaluoHipotecarioDTO avaluoHipotecarioDTO = givenAnAppraisalWithAreas(listaAreas, formatoInforme, entidad);
        informeHipotecarioBean.setListaAreas(listaAreas);
        informeHipotecarioBean.setInformeHipotecarioDTO(formatoInforme);
        informeHipotecarioBean.setAvaluoHipotecarioDTO(avaluoHipotecarioDTO);
        informeHipotecarioBean.calcularValores();
        Assertions.assertThat(((FormatoInformeHipotecarioDTO) avaluoHipotecarioDTO.getFormatoInforme()).getValorProporcionalTerreno().compareTo(new BigDecimal(VALOR_PROPORCIONAL_TERRENO_PH))).isEqualTo(0);
    }

    @Test
    public void shouldCalculateProportionalConstructionValuePh() throws Exception {
        final List<AreaDTO> listaAreas = givenAListOfAreasPh();
        final FormatoInformeHipotecarioDTO formatoInforme = givenAnAppraisalReport(TERRAIN_PERCENTAGE, Boolean.TRUE);
        final EntidadDTO entidad = givenAnEntity();
        final AvaluoHipotecarioDTO avaluoHipotecarioDTO = givenAnAppraisalWithAreas(listaAreas, formatoInforme, entidad);
        informeHipotecarioBean.setListaAreas(listaAreas);
        informeHipotecarioBean.setInformeHipotecarioDTO(formatoInforme);
        informeHipotecarioBean.setAvaluoHipotecarioDTO(avaluoHipotecarioDTO);
        informeHipotecarioBean.calcularValores();
        Assertions.assertThat(((FormatoInformeHipotecarioDTO) avaluoHipotecarioDTO.getFormatoInforme()).getValorProporcionalConstruccion().compareTo(new BigDecimal(VALOR_PROPORCIONAL_CONSTUCCION_PH))).isEqualTo(0);
    }

    @Test
    public void shouldCalculateIntegralValueConstructionPh() throws Exception {
        final List<AreaDTO> listaAreas = givenAListOfAreasPh();
        final FormatoInformeHipotecarioDTO formatoInforme = givenAnAppraisalReport(TERRAIN_PERCENTAGE, Boolean.TRUE);
        final EntidadDTO entidad = givenAnEntity();
        final AvaluoHipotecarioDTO avaluoHipotecarioDTO = givenAnAppraisalWithAreas(listaAreas, formatoInforme, entidad);
        informeHipotecarioBean.setListaAreas(listaAreas);
        informeHipotecarioBean.setInformeHipotecarioDTO(formatoInforme);
        informeHipotecarioBean.setAvaluoHipotecarioDTO(avaluoHipotecarioDTO);
        informeHipotecarioBean.calcularValores();
        Assertions.assertThat(((FormatoInformeHipotecarioDTO) avaluoHipotecarioDTO.getFormatoInforme()).getValorIntegralConstruccion().compareTo(VALOR_INTEGRAL_CONSTRUCCION)).isEqualTo(0);
    }

    @Test
    public void shouldInvokeCalculateInsurableValueInControllerNoPh() throws Exception {
        final List<AreaDTO> listOfAreas = givenAListOfAreasWithOtherNoPh();
        Mockito.when(informeHipotecarioController.calcularValorTotal(listOfAreas)).thenReturn(TOTAL_VALUE_NO_PH_WITH_OTHER);
        final FormatoInformeHipotecarioDTO formatoInforme = givenAnAppraisalReport(0, Boolean.FALSE);
        final EntidadDTO entidad = givenAnEntity();
        final AvaluoHipotecarioDTO avaluoHipotecarioDTO = givenAnAppraisalWithAreas(listOfAreas, formatoInforme, entidad);
        informeHipotecarioBean.setInformeHipotecarioDTO(formatoInforme);
        informeHipotecarioBean.setListaAreas(listOfAreas);
        informeHipotecarioBean.setAvaluoHipotecarioDTO(avaluoHipotecarioDTO);

        informeHipotecarioBean.calcularValores();

        Mockito.verify(informeHipotecarioController).calcularValorAsegurable(entidad, TOTAL_CONSTRUCTED_AREAS_WITH_OTHERS_NO_PH);
    }

    @Test
    public void shouldInvokeCalculateInsurableValueInControllerPh() throws Exception {
        final List<AreaDTO> listOfAreas = givenAListOfAreasWithOtherPh();
        Mockito.when(informeHipotecarioController.calcularValorTotal(listOfAreas)).thenReturn(TOTAL_VALUE_PH_WITH_OTHER);
        final FormatoInformeHipotecarioDTO formatoInforme = givenAnAppraisalReport(0, Boolean.TRUE);
        final EntidadDTO entidad = givenAnEntity();
        final AvaluoHipotecarioDTO avaluoHipotecarioDTO = givenAnAppraisalWithAreas(listOfAreas, formatoInforme, entidad);
        informeHipotecarioBean.setInformeHipotecarioDTO(formatoInforme);
        informeHipotecarioBean.setListaAreas(listOfAreas);
        informeHipotecarioBean.setAvaluoHipotecarioDTO(avaluoHipotecarioDTO);

        informeHipotecarioBean.calcularValores();

        Mockito.verify(informeHipotecarioController).calcularValorAsegurable(entidad, TOTAL_PRIVATE_AREAS_WITH_OTHERS_PH);
    }

    private List<AreaDTO> givenAListOfAreasWithOtherPh() {
        return Arrays.asList(
                givenAnArea(AREA1, UNIT_VALUE1, AreaDTO.DescripcionAreaPH.AreaPrivada),
                givenAnArea(AREA2, UNIT_VALUE2, AreaDTO.DescripcionAreaPH.AreaPrivada),
                givenAnArea(AREA3, UNIT_VALUE3, AreaDTO.DescripcionAreaPH.Deposito),
                givenAnIncompleteArea(AreaDTO.DescripcionAreaPH.AreaPrivada),
                givenAnArea(AREA_OTHER, UNIT_VALUE_OTHER, AreaDTO.DescripcionAreaPH.Otro)
        );
    }

    private List<AreaDTO> givenAListOfAreasNoPh() {
        return Arrays.asList(
                givenAnArea(AREA1, UNIT_VALUE1, AreaDTO.DescripcionAreaNoPH.AreaTerreno),
                givenAnArea(AREA2, UNIT_VALUE2, AreaDTO.DescripcionAreaNoPH.AreaTerreno),
                givenAnArea(AREA4, UNIT_VALUE4, AreaDTO.DescripcionAreaNoPH.AreaConstruccion),
                givenAnArea(AREA5, UNIT_VALUE5, AreaDTO.DescripcionAreaNoPH.AreaConstruccion),
                givenAnIncompleteArea(AreaDTO.DescripcionAreaNoPH.AreaConstruccion)
        );
    }

    private AvaluoHipotecarioDTO givenAnAppraisalWithAreas(List<AreaDTO> listOfAreas, FormatoInformeHipotecarioDTO formatoInforme, EntidadDTO entidad) {
        final AvaluoHipotecarioDTO avaluoHipotecarioDTO = new AvaluoHipotecarioDTO();
        avaluoHipotecarioDTO.setEntidad(entidad);
        avaluoHipotecarioDTO.setFormatoInforme(formatoInforme);
        avaluoHipotecarioDTO.setAreas(new HashSet<>(listOfAreas));
        return avaluoHipotecarioDTO;
    }

    private EntidadDTO givenAnEntity() {
        final EntidadDTO entidad = new EntidadDTO();
        entidad.setNombre("Stub entity");
        entidad.setPorcentajeValorAsegurable((double) INSURABLE_PERCENTAGE);
        return entidad;
    }

    private FormatoInformeHipotecarioDTO givenAnAppraisalReport(int terrainPercentage, Boolean sometidoAPropiedadHorizontal) {
        final FormatoInformeHipotecarioDTO formatoInforme = new FormatoInformeHipotecarioDTO(1L);
        formatoInforme.setSometidoAPropiedadHorizontal(sometidoAPropiedadHorizontal);
        formatoInforme.setPorcentajeTerreno(new BigDecimal(terrainPercentage));
        return formatoInforme;
    }

    private List<AreaDTO> givenAListOfAreasPh() {
        return Arrays.asList(
                givenAnArea(AREA1, UNIT_VALUE1, AreaDTO.DescripcionAreaPH.AreaPrivada),
                givenAnArea(AREA2, UNIT_VALUE2, AreaDTO.DescripcionAreaPH.AreaPrivada),
                givenAnArea(AREA3, UNIT_VALUE3, AreaDTO.DescripcionAreaPH.Deposito),
                givenAnIncompleteArea(AreaDTO.DescripcionAreaPH.AreaPrivada)
        );
    }

    private AreaDTO givenAnArea(int area, int valorUnitario, ListaDesplegable descripcionNumerica) {
        final AreaDTO areaDTO = new AreaDTO();
        areaDTO.setArea(new BigDecimal(area));
        areaDTO.setValorUnitario(new BigDecimal(valorUnitario));
        areaDTO.setValorTotal(new BigDecimal(area * valorUnitario));
        areaDTO.setDescripcionNumerica(descripcionNumerica);
        return areaDTO;
    }

    private AreaDTO givenAnIncompleteArea(ListaDesplegable descripcionNumerica) {
        final AreaDTO areaDTO = new AreaDTO();
        areaDTO.setDescripcionNumerica(descripcionNumerica);
        return areaDTO;
    }

    @Test
    public void shouldReturnFalseWhenSubsidyTypeIsNull() throws Exception {
        informeHipotecarioBean.setAvaluoHipotecarioDTO(new AvaluoHipotecarioDTO());
        Assertions.assertThat(informeHipotecarioBean.valorTotalExcedeSubsidio()).isFalse();
    }

    @Test
    public void shouldReturnTrueWhenTotalValueExceedsSubsidy() throws Exception {
        final AvaluoHipotecarioDTO avaluoHipotecarioDTO = new AvaluoHipotecarioDTO();
        avaluoHipotecarioDTO.setValorTotalAvaluo(new BigDecimal(100));
        final SubsidioDTO tipoSubsidio = new SubsidioDTO();
        tipoSubsidio.setMaximo(new BigDecimal(99));
        tipoSubsidio.setMinimo(new BigDecimal(50));
        avaluoHipotecarioDTO.setTipoSubsidio(tipoSubsidio);
        informeHipotecarioBean.setAvaluoHipotecarioDTO(avaluoHipotecarioDTO);
        Assertions.assertThat(informeHipotecarioBean.valorTotalExcedeSubsidio()).isTrue();
    }

    @Test
    public void shouldReturnTrueWhenTotalValueIsLessThanSubsidyMinimum() throws Exception {
        final AvaluoHipotecarioDTO avaluoHipotecarioDTO = new AvaluoHipotecarioDTO();
        avaluoHipotecarioDTO.setValorTotalAvaluo(new BigDecimal(1));
        final SubsidioDTO tipoSubsidio = new SubsidioDTO();
        tipoSubsidio.setMaximo(new BigDecimal(99));
        tipoSubsidio.setMinimo(new BigDecimal(50));
        avaluoHipotecarioDTO.setTipoSubsidio(tipoSubsidio);
        informeHipotecarioBean.setAvaluoHipotecarioDTO(avaluoHipotecarioDTO);
        Assertions.assertThat(informeHipotecarioBean.valorTotalExcedeSubsidio()).isTrue();
    }

    @Test
    public void shouldNotUpdateTiempoComercializacionWhenNullDivipola() throws Exception {
        final AvaluoHipotecarioDTO avaluoHipotecarioDTO = new AvaluoHipotecarioDTO();
        avaluoHipotecarioDTO.setTipoDeInmueble(new TipoInmuebleDTO(2L, "Apartamento"));
        final FormatoInformeHipotecarioDTO formatoInforme = new FormatoInformeHipotecarioDTO(1L);
        formatoInforme.setEstrato(3);
        avaluoHipotecarioDTO.setFormatoInforme(formatoInforme);
        informeHipotecarioBean.setInformeHipotecarioDTO(formatoInforme);
        informeHipotecarioBean.setAvaluoHipotecarioDTO(avaluoHipotecarioDTO);
        informeHipotecarioBean.actualizarTiempoComercializacion();
        Assertions.assertThat(((FormatoInformeHipotecarioDTO) avaluoHipotecarioDTO.getFormatoInforme()).getTiempoEsperadoDeComercializacion()).isNull();
    }

    @Test
    public void shouldNotUpdateTiempoComercializacionWhenNullTipoInmueble() throws Exception {
        final AvaluoHipotecarioDTO avaluoHipotecarioDTO = new AvaluoHipotecarioDTO();
        avaluoHipotecarioDTO.setDivipolaInforme(new DivipolaDTO(908L, "Bogotá D.C", "Bogotá D.C", "Bogotá D.C"));
        final FormatoInformeHipotecarioDTO formatoInforme = new FormatoInformeHipotecarioDTO(1L);
        formatoInforme.setEstrato(3);
        avaluoHipotecarioDTO.setFormatoInforme(formatoInforme);
        informeHipotecarioBean.setInformeHipotecarioDTO(formatoInforme);
        informeHipotecarioBean.setAvaluoHipotecarioDTO(avaluoHipotecarioDTO);
        informeHipotecarioBean.actualizarTiempoComercializacion();
        Assertions.assertThat(((FormatoInformeHipotecarioDTO) avaluoHipotecarioDTO.getFormatoInforme()).getTiempoEsperadoDeComercializacion()).isNull();
    }

    @Test
    public void shouldNotUpdateTiempoComercializacionWhenNullEstrato() throws Exception {
        final AvaluoHipotecarioDTO avaluoHipotecarioDTO = new AvaluoHipotecarioDTO();
        avaluoHipotecarioDTO.setDivipolaInforme(new DivipolaDTO(908L, "Bogotá D.C", "Bogotá D.C", "Bogotá D.C"));
        final FormatoInformeHipotecarioDTO formatoInforme = new FormatoInformeHipotecarioDTO(1L);
        avaluoHipotecarioDTO.setFormatoInforme(formatoInforme);
        informeHipotecarioBean.setInformeHipotecarioDTO(formatoInforme);
        informeHipotecarioBean.setAvaluoHipotecarioDTO(avaluoHipotecarioDTO);
        informeHipotecarioBean.actualizarTiempoComercializacion();
        Assertions.assertThat(((FormatoInformeHipotecarioDTO) avaluoHipotecarioDTO.getFormatoInforme()).getTiempoEsperadoDeComercializacion()).isNull();
    }

    @Test
    public void shouldUpdateTiempoComercializacionWhenNotNullDivipolaTipoInmuebleAndEstrato() throws Exception {
        final AvaluoHipotecarioDTO avaluoHipotecarioDTO = new AvaluoHipotecarioDTO();
        avaluoHipotecarioDTO.setDivipolaInforme(new DivipolaDTO(908L, "Bogotá D.C", "Bogotá D.C", "Bogotá D.C"));
        avaluoHipotecarioDTO.setTipoDeInmueble(new TipoInmuebleDTO(2L, "Apartamento"));
        final FormatoInformeHipotecarioDTO formatoInforme = new FormatoInformeHipotecarioDTO(1L);
        formatoInforme.setEstrato(3);
        avaluoHipotecarioDTO.setFormatoInforme(formatoInforme);
        informeHipotecarioBean.setInformeHipotecarioDTO(formatoInforme);
        informeHipotecarioBean.setAvaluoHipotecarioDTO(avaluoHipotecarioDTO);
        Mockito.when(avaluoController.encontrarTiempoComercializacion(908L, 2L, 3)).thenReturn(4.0);
        informeHipotecarioBean.actualizarTiempoComercializacion();
        Assertions.assertThat(formatoInforme.getTiempoEsperadoDeComercializacion()).isEqualTo(4.0);
    }
}