package com.helio4.bancol.avaluos.controlador;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.helio4.bancol.avaluos.dominio.AvaluoController;
import com.helio4.bancol.avaluos.dominio.EstadoAvaluoController;
import com.helio4.bancol.avaluos.dto.EstadoAvaluoDTO;
import com.helio4.bancol.avaluos.servicio.excepciones.EstadoAvaluoNotFoundException;

/**
 * @author Gerson Sosa
 */
@Controller
@Scope("view")
@Qualifier("actualizarFechaAprobacionBean")
public class ActualizarFechaAprobacionBean {
    private final EstadoAvaluoController estadoAvaluoController;
    private final AvaluoController avaluoController;
    private final String MENSAJE_NO_ENCONTRADO = "No se encontró el avalúo con ese código externo o el avalúo no se encuentra en estado aprobado";
    private final String MENSAJE_ACTUALIZADO = "La fecha de aprobación fue actualizada satisfactoriamente";
    private final String MENSAJE_ERROR_ACTUALIZAR = "La fecha no se pudo actualizar el estado ya no existe.";
    private final String MENSAJE_ERROR_BORRAR_PDF = "El PDF no pudo borrarse.";
    private String rutaImagenes;
    private EstadoAvaluoDTO estadoAvaluoDTO;

    @Autowired
    public ActualizarFechaAprobacionBean(
            EstadoAvaluoController estadoAvaluoController, AvaluoController avaluoController) {
        this.estadoAvaluoController = estadoAvaluoController;
        this.avaluoController = avaluoController;
    }

    public void buscar(String codigoExterno) {
        estadoAvaluoDTO = estadoAvaluoController
                .buscarPorCodigoExterno(codigoExterno);
        if (estadoAvaluoDTO == null) {
            mostrarMensaje(MENSAJE_NO_ENCONTRADO);
        }
    }

    public void guardar() {
        try {
            estadoAvaluoController.actualizar(estadoAvaluoDTO);
            if(!avaluoController.borrarPDF(estadoAvaluoDTO.getAvaluoId())) {
                mostrarMensaje(MENSAJE_ERROR_BORRAR_PDF);
            }
            mostrarMensaje(MENSAJE_ACTUALIZADO);
        } catch (EstadoAvaluoNotFoundException e) {
            mostrarMensaje(MENSAJE_ERROR_ACTUALIZAR);
        }
    }

    private void mostrarMensaje(String mensaje) {
        FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage(mensaje));
    }

    public EstadoAvaluoDTO getEstadoAvaluoDTO() {
        return estadoAvaluoDTO;
    }

    public void setEstadoAvaluoDTO(EstadoAvaluoDTO estadoAvaluoDTO) {
        this.estadoAvaluoDTO = estadoAvaluoDTO;
    }
}
