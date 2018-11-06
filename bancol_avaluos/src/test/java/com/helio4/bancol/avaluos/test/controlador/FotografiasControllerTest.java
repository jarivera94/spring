package com.helio4.bancol.avaluos.test.controlador;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableList;
import com.helio4.bancol.avaluos.dominio.FotografiasController;
import com.helio4.bancol.avaluos.dto.FotografiaDTO;
import com.helio4.bancol.avaluos.servicio.excepciones.FotografiaNotFoundException;
import com.helio4.bancol.avaluos.servicio.datos.FotografiaService;

@RunWith(MockitoJUnitRunner.class)
public class FotografiasControllerTest {

    private static Logger log = LoggerFactory
        .getLogger( FotografiasControllerTest.class );

    private final String rutaUbicacion =
        "/home/hghar/sandbox/avaluos/imagenes/image.jpg";
    private final String descripcion1 = "SampleDescription1";
    private final String descripcion2 = "SampleDescription2";
    private final String descripcion3 = "SampleDescription3";
    private final String descripcion4 = "SampleDescription4";
    private final String descripcion5 = "SampleDescription5";
    private final String descripcion6 = "SampleDescription6";
    private final String descripcion7 = "SampleDescription7";
    private final String descripcion8 = "SampleDescription8";
    private final String descripcion9 = "SampleDescription9";
    private final String descripcion10 = "SampleDescription10";
    private final String descripcion11 = "SampleDescription11";
    private final Long avaluoId = 1l;

    private FotografiasController fotografiasController;
    private FotografiaService fotografiaService;
    /** Usa los inidices en base 0 */
    private final int posicionActual = 10;
    private final int nuevaPosicion = 2;
    /* Pasar ultima posición a la anterior */

    @Before
    public void setUp() {
        fotografiasController = new FotografiasController();
        fotografiaService = mock(FotografiaService.class);
        try {
            when(fotografiaService.actualizar(isA(FotografiaDTO.class)))
                .thenAnswer(new Answer<Object>() {
                    public Object answer(InvocationOnMock invocation) {
                        return invocation.getArguments()[0];
                    }
                });
        } catch (FotografiaNotFoundException e) {
            log.error("Error al encontrar la fotografia", e);
        }
        fotografiasController.setFotografiaService(fotografiaService);
    }

    private List<FotografiaDTO> construirListaFotos() {
        List<FotografiaDTO> fotografias = new ArrayList<>();
        fotografias.add(new FotografiaDTO(rutaUbicacion, descripcion1,
                    avaluoId, 1l));
        fotografias.add(new FotografiaDTO(rutaUbicacion, descripcion2,
                    avaluoId, 2l));
        fotografias.add(new FotografiaDTO(rutaUbicacion, descripcion3,
                    avaluoId, 3l));
        fotografias.add(new FotografiaDTO(rutaUbicacion, descripcion4,
                    avaluoId, 4l));
        fotografias.add(new FotografiaDTO(rutaUbicacion, descripcion5,
                    avaluoId, 5l));
        fotografias.add(new FotografiaDTO(rutaUbicacion, descripcion6,
                    avaluoId, 6l));
        fotografias.add(new FotografiaDTO(rutaUbicacion, descripcion7,
                    avaluoId, 7l));
        fotografias.add(new FotografiaDTO(rutaUbicacion, descripcion8,
                    avaluoId, 8l));
        fotografias.add(new FotografiaDTO(rutaUbicacion, descripcion9,
                    avaluoId, 9l));
        fotografias.add(new FotografiaDTO(rutaUbicacion, descripcion10,
                    avaluoId, 10l));
        fotografias.add(new FotografiaDTO(rutaUbicacion, descripcion11,
                    avaluoId, 11l));
        return fotografias;
    }

    @Test
    public void testDebe_MoverFotoANuevaPosicion() {
        List<FotografiaDTO> fotografias = construirListaFotos();
        FotografiaDTO fotoMovida = fotografias.get(posicionActual);

        fotografias = fotografiasController
            .moverFoto(fotoMovida, posicionActual,
                nuevaPosicion, fotografias);
        assertEquals(fotografias.get(nuevaPosicion), fotoMovida);
        assertEquals(fotografias.get(nuevaPosicion).getOrden(),
                new Long(nuevaPosicion+1));
    }

    @Test
    public void testDebe_MoverFotosAEspacioVacio() {
        List<FotografiaDTO> fotografias = construirListaFotos();
        List<FotografiaDTO> fotografias2 = ImmutableList.copyOf(fotografias);
        FotografiaDTO fotoMovida = fotografias.get(posicionActual);

        fotografias = fotografiasController
            .moverFoto(fotoMovida, posicionActual,
                nuevaPosicion, fotografias);
        if (posicionActual > nuevaPosicion) {
            for (int i = posicionActual; i > nuevaPosicion; i--) {
                FotografiaDTO foto = fotografias2.get(i - 1);
                assertEquals(fotografias.get(i), foto);
                assertEquals(fotografias.get(i).getOrden(), new Long(i+1));
            }
        } else {
            for (int i = posicionActual; i < nuevaPosicion; i++) {
                FotografiaDTO foto = fotografias2.get(i + 1);
                assertEquals(fotografias.get(i), foto);
                assertEquals(fotografias.get(i).getOrden(),
                        new Long(i + 1));
            }
        }
    }

    @Test
    public void testDebe_SostenerLaPosicionDeLasDemasFotografias() {
        List<FotografiaDTO> fotografias = construirListaFotos();
        List<FotografiaDTO> fotografias2 = ImmutableList.copyOf(fotografias);
        FotografiaDTO fotoMovida = fotografias.get(posicionActual);

        fotografias = fotografiasController
            .moverFoto(fotoMovida, posicionActual,
                nuevaPosicion, fotografias);
        if (posicionActual > nuevaPosicion) {
            for (int i = posicionActual + 1; i < fotografias.size(); i++) {
                FotografiaDTO foto = fotografias.get(i);
                for(int j = posicionActual + 1; j < fotografias2.size(); j++) {
                    FotografiaDTO foto2 = fotografias2.get(j);
                    assertEquals(foto, foto2);
                    assertEquals(foto.getOrden(), foto2.getOrden());
                }
            }
        } else {
            for (int i = nuevaPosicion + 1; i < fotografias.size(); i++) {
                FotografiaDTO foto = fotografias.get(i);
                FotografiaDTO foto2 = fotografias2.get(i);
                assertEquals(foto, foto2);
                assertEquals(foto.getOrden(), foto2.getOrden());
            }
        }
    }
}
