package com.helio4.bancol.avaluos.servicio.datos;

import com.helio4.bancol.avaluos.modelo.Divipola;
import com.helio4.bancol.avaluos.modelo.TiempoComercializacion;
import com.helio4.bancol.avaluos.modelo.TipoInmueble;
import com.helio4.bancol.avaluos.servicio.excepciones.TiempoComercializacionNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service that performs additional logic when bringing data from database
 * for {@link TiempoComercializacion}
 * @author <a href="mailto:gersonsosa@gmail.com">Gerson Sosa</a>
 */
@Service
public interface TiempoComercializacionService {
    /**
     * @return a list of all {@link TiempoComercializacion}
     */
    List<TiempoComercializacion> encontrarTodos();

    /**
     * Finds the {@link TiempoComercializacion}
     * @param divipola the city to query for
     * @param tipoInmueble the property type
     * @param estrato the strata of the property
     * @return a list of matching {@link TiempoComercializacion}
     */
    List<TiempoComercializacion> encontrarPor(Divipola divipola, TipoInmueble tipoInmueble, int estrato);

    /**
     * Finds the matching time of commercialization
     * This can be done due to the index in the table that does not allow duplicates
     * Will throw {@link RuntimeException} if find duplicates
     * @throws TiempoComercializacionNotFoundException if there is no matching found
     * @param divipolaId the if of the city to query for
     * @param tipoInmuebleId the id of the property type
     * @param estrato the strata of the property
     * @return the matching {@link Double tiempoComercializacion}
     */
    Double encontrarPor(long divipolaId, long tipoInmuebleId, int estrato) throws TiempoComercializacionNotFoundException;
}
