package com.helio4.bancol.avaluos.servicio.util;

import com.helio4.bancol.avaluos.dto.AvaluoDTO;

public class StringUtils {

    public static String deshacerCamelCase(String camelCase) {
        String resultado = "";
        for (int x=0 ; x<camelCase.length() ; x++) {
            if (x > 0 && Character.isUpperCase(camelCase.charAt(x))) {
                resultado = resultado+" "+camelCase.substring(x, x+1);
            } else {
                resultado = resultado + camelCase.substring(x, x+1);
            }
        }
        return resultado;
    }

    public static String obtenerDireccion(AvaluoDTO avaluo) {
        return avaluo.getDireccionInmuebleInforme() != null
            && avaluo.getAdicionalDireccionInforme() != null ?
                avaluo.getDireccionInmuebleInforme().contains(avaluo.getAdicionalDireccionInforme()) ?
                    avaluo.getDireccionInmuebleInforme()
                    : avaluo.getDireccionInmuebleInforme() + " " + avaluo.getAdicionalDireccionInforme()
                : avaluo.getDireccionInmuebleInforme() != null ?
                    avaluo.getDireccionInmuebleInforme()
                    : avaluo.getAdicionalDireccionInforme();
    }

    /**
     * <p>Elimina caracteres especiales de la cadena que se envia
     * como parametro obteniendo solo los numeros contenidos en la cadena </p>
     * */
    public static String obtenerNumeros(Object value) {
    	String valor = String.valueOf(value);
    	String procesado =  valor.replaceAll("[^0-9]", "");
    	return procesado;
    }
}
