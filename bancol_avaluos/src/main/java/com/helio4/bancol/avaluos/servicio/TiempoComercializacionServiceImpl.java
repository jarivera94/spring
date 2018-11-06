package com.helio4.bancol.avaluos.servicio;

import com.helio4.bancol.avaluos.modelo.Divipola;
import com.helio4.bancol.avaluos.modelo.TiempoComercializacion;
import com.helio4.bancol.avaluos.modelo.TipoInmueble;
import com.helio4.bancol.avaluos.persistencia.TiempoComercializacionRepository;
import com.helio4.bancol.avaluos.servicio.datos.TiempoComercializacionService;
import com.helio4.bancol.avaluos.servicio.excepciones.TiempoComercializacionNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the service {@link TiempoComercializacionService}
 *
 * @author <a href="mailto:gersonsosa@gmail.com">Gerson Sosa</a>
 */
@Service
public class TiempoComercializacionServiceImpl implements TiempoComercializacionService {
    private final TiempoComercializacionRepository tiempoComercializacionRepository;
    private List<TiempoComercializacion> tiempoComercializacionList;

    /**
     * Constructor that receives the repository for {@link TiempoComercializacion}
     *
     * @param tiempoComercializacionRepository repository
     */
    @Autowired
    public TiempoComercializacionServiceImpl(TiempoComercializacionRepository tiempoComercializacionRepository) {
        this.tiempoComercializacionRepository = tiempoComercializacionRepository;
        tiempoComercializacionList = tiempoComercializacionRepository.findAll();
    }

    /**
     * @see TiempoComercializacionService#encontrarTodos()
     */
    @Override
    public List<TiempoComercializacion> encontrarTodos() {
        return tiempoComercializacionList;
    }

    /**
     * @see TiempoComercializacionService#encontrarPor(Divipola, TipoInmueble, int)
     */
    @Override
    public List<TiempoComercializacion> encontrarPor(Divipola divipola, TipoInmueble tipoInmueble, int estrato) {
        return tiempoComercializacionList.stream()
                .filter(
                        tiempoComercializacion -> tiempoComercializacion.getDivipola().equals(divipola)
                                && tiempoComercializacion.getTipoInmueble().equals(tipoInmueble)
                                && tiempoComercializacion.getEstrato() == estrato
                )
                .collect(Collectors.toList());
    }

    /**
     * @see TiempoComercializacionService#encontrarPor(long, long, int)
     */
    @Override
    public Double encontrarPor(long divipolaId, long tipoInmuebleId, int estrato) throws TiempoComercializacionNotFoundException {
        final List<TiempoComercializacion> list = tiempoComercializacionList.stream()
                .filter(
                        tiempoComercializacion -> tiempoComercializacion.getDivipola().getId().equals(divipolaId)
                                && tiempoComercializacion.getTipoInmueble().getId().equals(tipoInmuebleId)
                                && tiempoComercializacion.getEstrato() == estrato
                )
                .collect(Collectors.toList());
        if (list.size() > 1) {
            throw new RuntimeException("El tiempo de comercializacion esta duplicado revisar tabla tiempo_comercializacion y su indice.");
        }
        if (list.isEmpty()) {
            throw new TiempoComercializacionNotFoundException(String.format("El tiempo de comercializacion no existe para divipolaId %d, tipoInmuebleId %d, estrato %d revisar tabla tiempo_comercializacion y su indice.", divipolaId, tipoInmuebleId, estrato));
        }
        return list.get(0).getTiempoComercializacion();
    }
}
