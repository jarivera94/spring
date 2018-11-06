package com.helio4.bancol.avaluos.controlador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.google.common.io.ByteStreams;
import com.helio4.bancol.avaluos.dominio.ReportesController;
import com.helio4.bancol.avaluos.dto.EstructuraReporte;

@Controller
@Scope("view")
@Qualifier("reportesBean")
public class ReportesBean {

    private static Logger log = LoggerFactory.getLogger(ReportesBean.class);

    private Date fechaInicio;
    private Date fechaFinal;
    private StreamedContent archivoReporte;
    private List<EstructuraReporte> reportes;
    private EstructuraReporte reporte;

    @Autowired
    private ReportesController reportesController;

    private String resultado;

    @PostConstruct
    public void init() {
        
        try {
        log.debug("Inicializando ReportesBean: ");
        fechaInicio = new Date(System.currentTimeMillis());
        fechaFinal = new Date(System.currentTimeMillis());
        reportes = reportesController.cargarEstructurasReportes();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public void exportarReporte() {
        if (reporte != null) {
            if (fechaInicio.before(fechaFinal)
                    || fechaInicio.equals(fechaFinal)) {
                resultado = reportesController.exportarReporte(fechaInicio,
                        fechaFinal, reporte);
                if (resultado != null) {
                    // TODO Escribir archivo en la respuesta de la peticion
                    if (resultado == "") {
                        FacesContext
                                .getCurrentInstance()
                                .addMessage(
                                        "growl",
                                        new FacesMessage(
                                                "No se encontraron avaluos",
                                                "No existen avaluos en las fechas seleccionadas."));
                    } else {
                        try {
                            setArchivoReporte(new DefaultStreamedContent(
                                    new FileInputStream(new File(resultado)),
                                    "text/" + reporte.getExtension(), resultado));
                        } catch (FileNotFoundException e) {
                            FacesContext
                                    .getCurrentInstance()
                                    .addMessage(
                                            "growl",
                                            new FacesMessage(
                                                    "Error",
                                                    "El archivo de reportes con nombre "
                                                            + reportesController
                                                                    .getNombreArchivo()
                                                            + " no se puede encontrar"));
                        }
                        FacesContext
                                .getCurrentInstance()
                                .addMessage(
                                        "growl",
                                        new FacesMessage(
                                                "Archivo exportado",
                                                "El archivo de reportes con nombre "
                                                        + reportesController
                                                                .getNombreArchivo()
                                                        + " fue exportado exitosamente"));
                    }
                } else {
                    FacesContext
                            .getCurrentInstance()
                            .addMessage(
                                    "growl",
                                    new FacesMessage("Error",
                                            "El archivo de reportes no fue exportado."));
                }
            } else {
                FacesContext
                        .getCurrentInstance()
                        .addMessage(
                                "growl",
                                new FacesMessage("Error",
                                        "La fecha de inicio debe ser menor o igual a la fecha final"));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage("growl",
                    new FacesMessage("Error", "Debes seleccionar la entidad"));
        }
    }

    public void descargarArchivo() throws IOException {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();

        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(new File(resultado));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        ec.responseReset(); // Some JSF component library or some Filter might
                            // have set some headers in the buffer beforehand.
                            // We want to get rid of them, else it may collide.
        ec.setResponseContentType("text/csv"); // Check
                                               // http://www.iana.org/assignments/media-types
                                               // for all types. Use if
                                               // necessary
                                               // ExternalContext#getMimeType()
                                               // for auto-detection based on
                                               // filename.
        try {
            ec.setResponseContentLength((int) fileInputStream.getChannel()
                    .size());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } // Set it with the file size. This header is optional. It will work if
          // it's omitted, but the download progress will be unknown.
        ec.setResponseHeader("Content-Disposition", "attachment; filename=\""
                + resultado + "\""); // The Save As popup magic is done here.
                                     // You can give it any file name you want,
                                     // this only won't work in MSIE, it will
                                     // use current request URL as file name
                                     // instead.

        OutputStream output = ec.getResponseOutputStream();
        // Now you can write the InputStream of the file to the above
        // OutputStream the usual way.
        // ...
        ByteStreams.copy(fileInputStream, output);

        fc.responseComplete(); // Important! Otherwise JSF will attempt to
                               // render the response which obviously will fail
                               // since it's already written with a file and
                               // closed.
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public StreamedContent getArchivoReporte() {
        return archivoReporte;
    }

    public void setArchivoReporte(StreamedContent archivoReporte) {
        this.archivoReporte = archivoReporte;
    }

    public List<EstructuraReporte> getReportes() {
        return reportes;
    }

    public void setReportes(List<EstructuraReporte> reportes) {
        this.reportes = reportes;
    }

    public EstructuraReporte getReporte() {
        return reporte;
    }

    public void setReporte(EstructuraReporte reporte) {
        this.reporte = reporte;
    }

}
