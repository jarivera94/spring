package com.helio4.bancol.avaluos.servicio;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import com.helio4.bancol.avaluos.servicio.datos.ParametroService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.helio4.bancol.avaluos.dto.EstructuraReporte;

/**
 * Este servicio tiene un alcance de sesión porque
 * cargar los archivos desde el disco puede ser una
 * operación costosa.
 *
 * Adicionalmente algunos componentes JSF requieren
 * convertidores que consultan el servicio cada vez que
 * se procesa el campo al que estan asociados y es necesario
 * conservar la misma lista de elementos para que la validación
 * sea exitosa.
 */
@Service
public class EstructuraReporteService {

    private static Logger log = LoggerFactory.getLogger(
            EstructuraReporteService.class );

    private static final String fileSeparator =
        System.getProperty("file.separator");
    private String rutaReportes;
    /** Esta variable mantiene una lista de las estructuras
     * en memoria durante la sesión de tal forma que solo
     * se carga de nuevo cada que se inicia la sesión */
    private List<EstructuraReporte> estructurasReporte;

    @Autowired
    @Qualifier("repositoryParametroService")
    private ParametroService parametroService;

    @PostConstruct
    public void init() {
        this.rutaReportes = parametroService
            .encontrarPorNombre("ruta_estructuras_reportes").getValor();
    }

    public EstructuraReporte obtenerEstructuraReporte(
            String nombreEstructura) throws IOException {
        encontrarTodos();
        for (EstructuraReporte estructura : estructurasReporte) {
            if (estructura.getNombre().equals(nombreEstructura)) {
                return estructura;
            }
        }
        return null;
    }

    public EstructuraReporte cargarEstructuraReporte(
            String nombreEstructura) throws IOException {
        String contenidoArchivo = FileUtils.readFileToString(
                new File(this.rutaReportes + fileSeparator
                    + nombreEstructura));
        Gson gson = new Gson();
        EstructuraReporte estructura = gson.fromJson(contenidoArchivo,
                EstructuraReporte.class);
        return estructura;
    }
    
    public EstructuraReporte cargarEstructuraReporteMensual(
            String nombreEstructura) throws IOException {
        String contenidoArchivo = FileUtils.readFileToString(
                new File(this.rutaReportes + fileSeparator + "mensuales" + fileSeparator
                    + nombreEstructura));
        Gson gson = new Gson();
        EstructuraReporte estructura = gson.fromJson(contenidoArchivo,
                EstructuraReporte.class);
        return estructura;
    }

    public List<EstructuraReporte> encontrarTodos() throws IOException{
        if (estructurasReporte != null) {
            return estructurasReporte;
        }
        final File folder = new File(rutaReportes);
        estructurasReporte = new ArrayList<EstructuraReporte>();
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isFile()
                    && FilenameUtils.getExtension(fileEntry.getName()).equals(
                            "json")) {
                estructurasReporte.add(cargarEstructuraReporte(
                            fileEntry.getName()));
            } else {
                log.error("El archivo: {} no existe", fileEntry.getName());
                continue;
            }
        }
        return estructurasReporte;
    }

}
