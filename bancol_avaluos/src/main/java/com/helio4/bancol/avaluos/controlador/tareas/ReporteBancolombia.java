package com.helio4.bancol.avaluos.controlador.tareas;

import java.io.IOException;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.helio4.bancol.avaluos.dto.EstructuraReporte;
import com.helio4.bancol.avaluos.servicio.EstructuraReporteService;
import com.helio4.bancol.avaluos.servicio.ExportarService;
import com.helio4.bancol.avaluos.servicio.datos.ParametroService;
import com.helio4.bancol.avaluos.servicio.util.Mail;

@Component
public class ReporteBancolombia {
		
	private static Logger log = LoggerFactory.getLogger( ReporteBancolombia.class );
	
    @Autowired
    @Qualifier("csvExportarService")
    private ExportarService exportarService;

    @Autowired
    private EstructuraReporteService estructuraReporteService;
    
    @Autowired
    @Qualifier("repositoryParametroService")
    private ParametroService parametroService;
    
	private EstructuraReporte estructuraReporte;

	private void init() {

		try {
			estructuraReporte = estructuraReporteService.cargarEstructuraReporteMensual("cron_reporte_bancolombia.json");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	//
	@Scheduled(cron= "0 0 1 1 * *" ) 
	public void logSendMailsState() throws IOException {
		Calendar fechaActual = Calendar.getInstance();
		log.info("EJECUCION DE REPORTE DE BANCOLOMBIA: FECHA: "+ fechaActual.getTime());
		init();

		Calendar fechaInicio = Calendar.getInstance();
		Calendar fechaFinal = Calendar.getInstance();

		fechaInicio.add(Calendar.MONTH, -1);
		fechaInicio.set(Calendar.DAY_OF_MONTH,1);
		
		fechaFinal.set(Calendar.DAY_OF_MONTH,1);
		
		log.info("EJECUCION DE REPORTE DE BANCOLOMBIA: FECHA INICIO: "+ fechaInicio.getTime());
		log.info("EJECUCION DE REPORTE DE BANCOLOMBIA: FECHA: FIN "+ fechaFinal.getTime());
		
		String ubicacionArchivo = exportarService.exportarCsv(estructuraReporte, "reportebancolombia", fechaInicio.getTime(), fechaFinal.getTime());
		String destinatariosEmail =  parametroService.encontrarPorNombre("destinatarios_reporte_bancolombia").getValor();
		
		for (String destinatario: destinatariosEmail.split(";")) {
			Mail mail = new Mail("Reporte bancolombia", "Reporte Bancolombia", destinatario);
			mail.adjuntarArchivo(ubicacionArchivo, "reporte.csv");
			mail.start();
		}
	}

}
