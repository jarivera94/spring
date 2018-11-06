package com.helio4.bancol.avaluos.dominio;

import com.helio4.bancol.avaluos.dto.*;
import com.helio4.bancol.avaluos.servicio.SubsidioService;
import com.helio4.bancol.avaluos.servicio.datos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class ListasController {

    private final SegmentoService segmentoService;

    private final EntidadService entidadService;

    private final TipoAvaluoService tipoAvaluoService;

    private final RegionalService regionalService;

    private final ListaService listaService;

    private final SubsidioService subsidioService;

    private List<SubsidioDTO> tiposSubsidio;

    @Autowired
    public ListasController(@Qualifier("repositorySegmentoService") SegmentoService segmentoService, @Qualifier("repositoryEntidadService") EntidadService entidadService, @Qualifier("repositoryTipoAvaluoService") TipoAvaluoService tipoAvaluoService, @Qualifier("repositoryRegionalService") RegionalService regionalService, @Qualifier("repositoryListaService") ListaService listaService, SubsidioService subsidioService) {
        this.segmentoService = segmentoService;
        this.entidadService = entidadService;
        this.tipoAvaluoService = tipoAvaluoService;
        this.regionalService = regionalService;
        this.listaService = listaService;
        this.subsidioService = subsidioService;
    }

    public List<SegmentoDTO> segmentos() {
        return segmentoService.encontrarTodos();
    }

    public List<SegmentoDTO> segmentosPorEntidad(Long idEntidad) {
        return segmentoService.encontrarPorEntidad(idEntidad);
    }

    public List<TipoInmuebleDTO> tiposInmueble() {
        return listaService.encontrarTiposInmueble();
    }

    public List<ObjetoAvaluoDTO> encontrarTodosObjetosAvaluo() {
        return listaService.encontrarTodosObjetosAvaluo();
    }

    /**
     * Initialize the lists of the bean, this only happens once.
     */
    @PostConstruct
    public void initialize() {
        this.tiposSubsidio = subsidioService.encontrarTodos();
    }

    public ObjetoAvaluoDTO encontrarObjetoAvaluoPorNombre(String nombre) {
        return listaService.encontrarObjetoAvaluoPorNombre(nombre);
    }

    public List<EntidadDTO> encontrarEntidades() {
        UsuarioDTO usuarioActivo = (UsuarioDTO) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        if (!usuarioActivo.getEntidades().isEmpty()) {
            return usuarioActivo.getEntidades();
        }
        return entidadService.encontrarEntidades();
    }

    public List<RegionalDTO> encontrarRegionales() {
        return regionalService.encontrarRegionales();
    }

    public List<TipoAvaluoDTO> encontrarTiposAvaluo() {
        UsuarioDTO usuarioActivo = (UsuarioDTO) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        if (!usuarioActivo.getTipoAvaluos().isEmpty()) {
            return usuarioActivo.getTipoAvaluos();
        }
        return tipoAvaluoService.encontrarTiposAvaluo();
    }

    public List<SubsidioDTO> getTiposSubsidio() {
        return tiposSubsidio;
    }
}
