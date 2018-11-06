package com.helio4.bancol.avaluos.dominio;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import org.springframework.security.core.context.SecurityContextHolder;

import com.helio4.bancol.avaluos.dto.AvaluoDTO;
import com.helio4.bancol.avaluos.dto.MotivoDTO;
import com.helio4.bancol.avaluos.dto.PermisoDTO;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;
import com.helio4.bancol.avaluos.modelo.Motivo;
import com.helio4.bancol.avaluos.persistencia.EstrategiaConsultaAvaluo;
import com.helio4.bancol.avaluos.servicio.ConsultaAvaluosService;
import com.helio4.bancol.avaluos.servicio.datos.MotivoService;
import com.helio4.bancol.avaluos.servicio.util.Constantes;

/**
 * Modelo de cargue perezoso de la lista de avaluos.
 */
@Component
@Scope("session")
public class AvaluosLazyDataModel extends LazyDataModel<AvaluoDTO> {

    private static final long serialVersionUID = 5490245992297187496L;
    private List<AvaluoDTO> listadoAvaluos;
    private UsuarioDTO usuario;

    private EstrategiaConsultaAvaluo estrategia;

    @Autowired
    @Qualifier("estrategiaAsignados")
    private EstrategiaConsultaAvaluo estrategiaAsignados;

    @Autowired
    @Qualifier("estrategiaCreados")
    private EstrategiaConsultaAvaluo estrategiaCreados;

    @Autowired
    @Qualifier("estrategiaRol")
    private EstrategiaConsultaAvaluo estrategiaRol;
    
    @Autowired
	private MotivoService motivoService;

    @PostConstruct
    public void init() {
        usuario = (UsuarioDTO) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        estrategia = estrategiaRol;
        if (usuario.getRol().getPermisos().contains(
                new PermisoDTO(Constantes.PERMISO_ACEPTAR_CASO))) {
            estrategia = estrategiaAsignados;
        }else if (usuario.getRol().getPermisos().contains(
                new PermisoDTO(Constantes.PERMISO_CREAR_NUEVA_SOLICITUD))
                && !usuario.getRol().getPermisos().contains(
                        new PermisoDTO(Constantes.PERMISO_DEVOLVER_CASO))) {
            estrategia = estrategiaCreados;
        }
    }

    @Override
    public AvaluoDTO getRowData(String rowKey) {
        for(AvaluoDTO avaluo : listadoAvaluos) {
            if(avaluo.getId().equals(Long.parseLong(rowKey)))
                return avaluo;
        }
        return null;
    }

    @Override
    public Object getRowKey(AvaluoDTO avaluo) {
        return avaluo.getId();
    }

    @Override
    public List<AvaluoDTO> load(int first, int pageSize,
            String sortField, SortOrder sortOrder,
            Map<String,Object> filters) {
        long dataSize =  estrategia.contarAvaluosPorFiltrosOrden(
                first, pageSize, sortField, sortOrder, filters, usuario);
        /*
         * Consulta los avaluos si existe algún filtro.
         */
        listadoAvaluos = estrategia.encontrarAvaluosPorFiltrosOrden(
                first, pageSize, sortField, sortOrder, filters, usuario);
        this.setRowCount(Math.toIntExact(dataSize));
        
        //cargar motivo en cada avaluo
        
        for (AvaluoDTO avaluo: listadoAvaluos){
    		Motivo motivo= motivoService.getMotivosById(avaluo.getMotivo().longValue());
    		avaluo.setMotivoAux(motivo);
        	
        } 
        
        return listadoAvaluos;
    }

    public class AvaluosComparator implements Comparator<AvaluoDTO> {

        public AvaluosComparator(String sortField, SortOrder sortOrder) {
        }

        @Override
        public int compare(AvaluoDTO o1, AvaluoDTO o2) {
            return 0;
        }
    }
}
