package com.helio4.bancol.avaluos.dominio;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.EstructuraReporte;
import com.helio4.bancol.avaluos.servicio.datos.AvaluoService;
import com.helio4.bancol.avaluos.servicio.EstructuraReporteService;
import com.helio4.bancol.avaluos.servicio.ExportarService;

@Component
@Scope("view")
public class ReportesController {

    private static Logger log = LoggerFactory.getLogger( ReportesController.class );

    private String nombreArchivo;
    private String[] nombresArchivos;

    @Autowired
    @Qualifier("csvExportarService")
    private ExportarService exportarService;

    @Autowired
    @Qualifier("repositoryAvaluoService")
    private AvaluoService avaluoService;

    @Autowired
    private EstructuraReporteService estructuraReporteService;

    public String exportarReporte(Date fechaInicial, Date fechaFinal,
            EstructuraReporte reporte) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYYMMdd");
        nombreArchivo = reporte.getNombre() + "_"
                + dateFormat.format(fechaInicial) + "_"
                + dateFormat.format(fechaFinal)
                + "." + reporte.getExtension();
        try {
            nombreArchivo = exportarService.exportarCsv(reporte,
                    nombreArchivo, fechaInicial, fechaFinal);
        } catch (IOException e) {
            log.error("Ocurrio un error al exportar el archivo de {} entre las fechas: {} y {} con el nombre de archivo {}",
                    reporte, fechaInicial, fechaFinal, nombreArchivo);
            return "";
        }
        return nombreArchivo;
    }

    public List<EstructuraReporte> cargarEstructurasReportes() {
        List<EstructuraReporte> resultado = null;
        try {
            resultado = estructuraReporteService.encontrarTodos();
        } catch (IOException e) {
            log.error("Ocurrio un eror al cargar las estructuras de reporte", e);
        }
        return resultado;
    }
    
    public EstructuraReporte cargarEstructuraReporteMensual(String nombre) throws IOException{
    	return estructuraReporteService.cargarEstructuraReporteMensual(nombre);
    	
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public String[] getNombresArchivos() {
        return nombresArchivos;
    }

}
