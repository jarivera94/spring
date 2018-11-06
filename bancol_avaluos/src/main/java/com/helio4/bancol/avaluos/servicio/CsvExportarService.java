package com.helio4.bancol.avaluos.servicio;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;

import com.helio4.bancol.avaluos.servicio.datos.ParametroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import org.supercsv.cellprocessor.FmtDate;
import org.supercsv.cellprocessor.FmtNumber;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.Token;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.prefs.CsvPreference;

import com.helio4.bancol.avaluos.dto.EstructuraReporte;
import com.helio4.bancol.avaluos.ensamblador.AvaluoEnsamblador;
import com.helio4.bancol.avaluos.persistencia.AvaluoCriteriaRepository;
import com.helio4.bancol.avaluos.servicio.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service(value="csvExportarService")
public class CsvExportarService implements ExportarService {

    private static Logger log = LoggerFactory.getLogger( CsvExportarService.class );

    private static final CsvPreference PIPE_DELIMITED =
        new CsvPreference.Builder('"', '|', "\n").build();
    private static final String fileSeparator =
        System.getProperty("file.separator");

    private DecimalFormatSymbols symbols;
    private DecimalFormat decimalFormat;

    private static final String formatoFecha = "yyyy-MM-dd HH:mm:ss";

    @Autowired
    @Qualifier("repositoryParametroService")
    private ParametroService parametroService;

    @Autowired
    private AvaluoCriteriaRepository avaluoCriteriaRepository;

    @Autowired
    private EstructuraReporteService estructuraReporteService;

    @Autowired
    private AvaluoEnsamblador avaluoEnsamblador;

    @PostConstruct
    public void init() {
        symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        decimalFormat = new DecimalFormat("#0,0000", symbols);
    }

    @Override
    public String exportarCsv(String nombreEstructura,
            String nombreArchivo, Date fechaInicialInforme,
            Date fechaFinalInforme) throws IOException {
        EstructuraReporte estructura = estructuraReporteService
            .cargarEstructuraReporte(nombreEstructura);
        return exportarCsv(estructura, nombreArchivo,
                fechaInicialInforme, fechaFinalInforme);
    }

    @Transactional(readOnly=true)
    @Override
    public String exportarCsv(EstructuraReporte estructura,
            String nombreArchivo, Date fechaInicialInforme,
            Date fechaFinalInforme) throws IOException {
        String rutaArchivosReporte = parametroService
                .encontrarPorNombre("ruta_archivos_reportes").getValor();
        Calendar c = Calendar.getInstance();
        c.setTime(fechaFinalInforme);
        c.add(Calendar.DATE, 1);
        fechaFinalInforme = c.getTime();
        log.info("fechaFinalInforme {}", fechaFinalInforme);
        final List<String> avaluos = avaluoCriteriaRepository
            .encontrarAvaluosConsultaNativa(estructura.getConsulta(),
                    fechaInicialInforme, fechaFinalInforme);
        List<String> lineasCsv = new ArrayList<>();
        lineasCsv.add(estructura.getCabecera());
        lineasCsv.addAll(removerCaracteresSaltoLinea(avaluos));
        try {
            Path file = Paths.get(rutaArchivosReporte+nombreArchivo);
            Files.write(file, lineasCsv, Charset.forName("UTF-8"));
        } catch (InvalidPathException e) {
            log.error("Error al encontrar la ruta {}",
                    rutaArchivosReporte+nombreArchivo, e);
        } catch (IOException e) {
            log.error("Error al escribir el archivo {}",
                    rutaArchivosReporte+nombreArchivo, e);
        }
        /*
        final List<AvaluoDTO> listadoAvaluos = avaluoEnsamblador
            .escribirListaDTO(avaluos);
        final CellProcessor[] procesadores =
            new CellProcessor[estructura.getProcesadoresCsv().length];
        for (int i = 0 ; i < estructura.getProcesadoresCsv().length ; i++) {
            procesadores[i] = obtenerProcesador(estructura
                    .getProcesadoresCsv()[i]);
        }
        ICsvDozerBeanWriter beanWriter = null;
        try {
            beanWriter = new CsvDozerBeanWriter(
                    new FileWriter(rutaArchivosReporte+nombreArchivo),
                    CsvPreference.STANDARD_PREFERENCE);
            // configure the mapping from the fields to the CSV columns
            beanWriter.configureBeanMapping(AvaluoDTO.class,
                    estructura.getMapeo());
            // write the header
            beanWriter.writeHeader(construirCabecera(estructura.getMapeo()));
            // write the beans
            for( final AvaluoDTO avaluo : listadoAvaluos ) {
                beanWriter.write(avaluo, procesadores);
            }
        } finally {
            if (beanWriter != null) {
                beanWriter.close();
            }
        }*/
        return rutaArchivosReporte + nombreArchivo;
    }
    
    private List<String> removerCaracteresSaltoLinea(List<String> lineas) {
    	
    	List<String> resultado =  new ArrayList<>();
    	String lineaTmp;
    	
    	for (String linea: lineas) {
    		
    		lineaTmp = linea.replaceAll("\n", " ");
    		lineaTmp = lineaTmp.replaceAll("\r", " ");
    		resultado.add(lineaTmp);
    	}
    	
    	return resultado;
    	
    }

    private CellProcessor obtenerProcesador(String nombreProcesador) {
        if ("Optional".equals(nombreProcesador)) {
            return new Optional();
        }
        if ("OptionalFmtDate".equals(nombreProcesador)) {
            return new Optional(new FmtDate(formatoFecha));
        }
        if ("NotNull".equals(nombreProcesador)) {
            return new NotNull();
        }
        if ("FmtDate".equals(nombreProcesador)) {
            return new FmtDate(formatoFecha);
        }
        if ("FmtNumber".equals(nombreProcesador)) {
            return new FmtNumber(decimalFormat);
        }
        if ("OptionalFmtNumber".equals(nombreProcesador)) {
            return new Optional(new FmtNumber(decimalFormat));
        }
        if ("TipoDocumento".equals(nombreProcesador)) {
            return new Token(new Integer(21), "C.C",
                    new Token(new Integer(22), "C.Ext.",
                        new Token(new Integer(23), "NIT.")));
        }
        if ("EstadoConstruccion".equals(nombreProcesador)) {
            return new Optional(new Token(new Integer(1), "Nueva",
                    new Token(new Integer(2), "Usada")));
        }
        return null;
    }

    private String[] construirCabecera(String[] mapeo) {
        String[] cabecera = new String[mapeo.length];
        for (int i=0; i < mapeo.length; i++) {
            cabecera[i] = StringUtils.deshacerCamelCase(mapeo[i]);
        }
        return cabecera;
    }

}
