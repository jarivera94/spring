package com.helio4.bancol.avaluos.persistencia;

import java.util.List;
import java.util.Map;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.primefaces.model.SortOrder;

import com.helio4.bancol.avaluos.dto.AvaluoDTO;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;
import com.helio4.bancol.avaluos.modelo.Avaluo;
import com.helio4.bancol.avaluos.modelo.Cliente;
import com.helio4.bancol.avaluos.modelo.Divipola;
import com.helio4.bancol.avaluos.modelo.Entidad;
import com.helio4.bancol.avaluos.modelo.EstadoAvaluo;
import com.helio4.bancol.avaluos.modelo.Motivo;
import com.helio4.bancol.avaluos.modelo.TipoAvaluo;
import com.helio4.bancol.avaluos.modelo.Usuario;

public interface EstrategiaConsultaAvaluo {

    public long contarAvaluosPorFiltrosOrden(int primero,
            int tamanoPagina, String campoOrden, SortOrder orden,
            Map<String, Object> filtros, UsuarioDTO usuario);


    public List<AvaluoDTO> encontrarAvaluosPorFiltrosOrden(int primero,
            int tamanoPagina, String campoOrden, SortOrder orden,
            Map<String, Object> filtros, UsuarioDTO usuario);

    public Path<?> getPath(String field, Root<EstadoAvaluo> estadoAvaluo,
            Join<EstadoAvaluo, Avaluo> avaluo, Join<Avaluo, Cliente> cliente,
            Join<Avaluo, Entidad> entidad, Join<Avaluo, TipoAvaluo> avaluoTipo,
            Join<Avaluo, Usuario> perito, Join<Avaluo, Divipola> ciudad);

    public Path<String> getStringPath(String field,
            Root<EstadoAvaluo> estadoAvaluo, Join<EstadoAvaluo, Avaluo> avaluo,
            Join<Avaluo, Entidad> entidad, Join<Avaluo, TipoAvaluo> avaluoTipo,
            Join<Avaluo, Usuario> perito, Join<Avaluo, Divipola> ciudad, Join<Avaluo, Motivo> motivo);
}
