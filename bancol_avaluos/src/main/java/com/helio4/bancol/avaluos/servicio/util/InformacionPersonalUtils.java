package com.helio4.bancol.avaluos.servicio.util;

import org.apache.commons.lang3.StringUtils;

import com.helio4.bancol.avaluos.dto.ClienteDTO;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;

public class InformacionPersonalUtils {

    public static final String espacio = " ";
    public static final String espacioComa = " ,";

    public static String concatenarNombreCliente(ClienteDTO cliente) {
        String nombreCompleto = "";
        if(cliente != null && cliente.getTipoDocumentoIdentificacion()!=null){
            if(cliente.getTipoDocumentoIdentificacion().intValue()==23){
                if(cliente.getRazonSocial()!=null){
                    nombreCompleto += cliente.getRazonSocial();
                }
            }else{
                if(!StringUtils.isEmpty(cliente.getPrimerNombre())){
                    nombreCompleto += cliente.getPrimerNombre();
                }
                if(!StringUtils.isEmpty(cliente.getSegundoNombre())){
                    nombreCompleto += " "+cliente.getSegundoNombre();
                }
                if(!StringUtils.isEmpty(cliente.getPrimerApellido())){
                    nombreCompleto += " "+cliente.getPrimerApellido();
                }
                if(!StringUtils.isEmpty(cliente.getSegundoApellido())){
                    nombreCompleto += " "+cliente.getSegundoApellido();
                }
            }
        }
        return nombreCompleto;
    }

    public static String concatenarNombreCortoCliente(ClienteDTO cliente) {
        String nombreCorto = "";
        if(cliente != null && cliente.getTipoDocumentoIdentificacion()!=null){
            if(cliente.getTipoDocumentoIdentificacion().intValue()==23){
                if(cliente.getRazonSocial()!=null){
                    nombreCorto += cliente.getRazonSocial();
                    nombreCorto = nombreCorto.length() > 30 ?
                        nombreCorto.substring(0, 30) : nombreCorto;
                }
            }else{
                if(!StringUtils.isEmpty(cliente.getPrimerNombre())){
                    nombreCorto += cliente.getPrimerNombre();
                }
                if(!StringUtils.isEmpty(cliente.getSegundoNombre())){
                    nombreCorto += " "+cliente.getSegundoNombre();
                }
                if(!StringUtils.isEmpty(cliente.getPrimerApellido())){
                    nombreCorto += " "+cliente.getPrimerApellido();
                }
                if(!StringUtils.isEmpty(cliente.getSegundoApellido())){
                    nombreCorto += " "+cliente.getSegundoApellido();
                }
                nombreCorto = nombreCorto.length() > 30 ? cliente
                        .getPrimerNombre().concat(" ")
                        .concat(cliente.getPrimerApellido()).length() > 30 ? cliente
                        .getPrimerNombre().concat(" ")
                        .concat(cliente.getPrimerApellido()).substring(0, 30)
                        : cliente.getPrimerNombre().concat(" ")
                                .concat(cliente.getPrimerApellido())
                        : nombreCorto;
            }
        }
        return nombreCorto;
    }

    public static String concatenarNombreUsuario(UsuarioDTO usuario) {
        String[] strings = {usuario.getNombres(), usuario.getApellidos()};
        return StringUtils.join(strings, " ");
    }
}
