package com.helio4.bancol.avaluos.servicio;

import java.io.IOException;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.helio4.bancol.avaluos.dto.EstructuraReporte;

@Service
public interface ExportarService {

    public String exportarCsv(String nombreEstructura,
            String nombreArchivo, Date fechaInicialInforme,
            Date fechaFinalInforme)
        throws IOException;

    public String exportarCsv(EstructuraReporte estructura,
            String nombreArchivo, Date fechaInicialInforme,
            Date fechaFinalInforme)
        throws IOException;


}
