package com.helio4.bancol.avaluos.servicio;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;
import org.springframework.stereotype.Service;

import com.helio4.bancol.avaluos.dto.AvaluoDTO;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;
import com.helio4.bancol.avaluos.persistencia.EstrategiaConsultaAvaluo;

@Service
public class ConsultaAvaluosService {

    private EstrategiaConsultaAvaluo estrategiaConsultaAvaluo;

    public long contarAvaluosPorFiltrosOrden(int primero, int tamanoPagina,
            String campoOrden, SortOrder orden, Map<String, Object> filtros,
            UsuarioDTO usuario){
        return estrategiaConsultaAvaluo.contarAvaluosPorFiltrosOrden(primero,
                tamanoPagina, campoOrden, orden, filtros, usuario);
    }

    public List<AvaluoDTO> encontrarAvaluosPorFiltrosOrden(int primero,
            int tamanoPagina, String campoOrden, SortOrder orden,
            Map<String, Object> filtros, UsuarioDTO usuario){
        return estrategiaConsultaAvaluo.encontrarAvaluosPorFiltrosOrden(primero,
                tamanoPagina, campoOrden, orden, filtros, usuario);
    }

    public void setEstrategiaConsultaAvaluo(
            EstrategiaConsultaAvaluo estrategiaConsultaAvaluo) {
        this.estrategiaConsultaAvaluo = estrategiaConsultaAvaluo;
    }

}
