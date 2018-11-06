package com.helio4.bancol.avaluos.dto;

public class EstructuraReporte {

    private String nombre;
    private String cabecera;
    private String consulta;
    private String extension;
    private String[] mapeo;
    private String[] procesadoresCsv;

    public EstructuraReporte() {
        super();
    }

    public String getNombre() {
        return nombre;
    }

    public String getCabecera() {
        return cabecera;
    }

    public String getConsulta() {
        return consulta;
    }

    public String getExtension() {
        return extension;
    }

    public String[] getMapeo() {
        return mapeo;
    }

    public String[] getProcesadoresCsv() {
        return procesadoresCsv;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCabecera(String cabecera) {
        this.cabecera = cabecera;
    }

    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public void setMapeo(String[] mapeo) {
        this.mapeo = mapeo;
    }

    public void setProcesadoresCsv(String[] procesadoresCsv) {
        this.procesadoresCsv = procesadoresCsv;
    }

    @Override
    public String toString() {
		return String.format("%s[id=%s]", getClass().getSimpleName(), getNombre());
    }

}
