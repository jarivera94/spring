package com.helio4.bancol.avaluos.persistencia;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.helio4.bancol.avaluos.dto.AvaluoDTO;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;
import com.helio4.bancol.avaluos.modelo.Avaluo;
import com.helio4.bancol.avaluos.modelo.Avaluo_;
import com.helio4.bancol.avaluos.modelo.Cliente;
import com.helio4.bancol.avaluos.modelo.Cliente_;
import com.helio4.bancol.avaluos.modelo.Divipola;
import com.helio4.bancol.avaluos.modelo.Divipola_;
import com.helio4.bancol.avaluos.modelo.Entidad;
import com.helio4.bancol.avaluos.modelo.EstadoAvaluo;
import com.helio4.bancol.avaluos.modelo.EstadoAvaluo_;
import com.helio4.bancol.avaluos.modelo.Motivo;
import com.helio4.bancol.avaluos.modelo.Regional;
import com.helio4.bancol.avaluos.modelo.Segmento;
import com.helio4.bancol.avaluos.modelo.TipoAvaluo;
import com.helio4.bancol.avaluos.modelo.TipoInmueble;
import com.helio4.bancol.avaluos.modelo.Usuario;
import com.helio4.bancol.avaluos.servicio.EstrategiaAvaluo;

@Service("estrategiaRol")
public class EstrategiaAvaluosRol extends EstrategiaAvaluo implements EstrategiaConsultaAvaluo {

    private static Logger log = LoggerFactory
        .getLogger(EstrategiaAvaluosCreados.class);

    @PersistenceContext
    private EntityManager em;

    public long contarAvaluosPorFiltrosOrden(int primero,
            int tamanoPagina, String campoOrden, SortOrder orden,
            Map<String, Object> filtros, UsuarioDTO usuario) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Long> consultaContar = criteriaBuilder
            .createQuery(Long.class);
        Root<EstadoAvaluo> estadoAvaluo = consultaContar
            .from(EstadoAvaluo.class);
        Join<EstadoAvaluo, Avaluo> avaluo = estadoAvaluo
                .join(EstadoAvaluo_.avaluo);
        Join<Avaluo, Motivo> motivo = avaluo.join(Avaluo_.motivo,
                JoinType.LEFT);
        Join<Avaluo, Entidad> entidad = avaluo.join(Avaluo_.entidad);
        Join<Avaluo, TipoAvaluo> tipoAvaluo = avaluo.join(Avaluo_.tipoAvaluo);
        Join<Avaluo, Usuario> perito = avaluo.join(Avaluo_.perito,
                JoinType.LEFT);
        Join<Avaluo, Divipola> ciudad = avaluo.join(Avaluo_.divipola);
        Join<Avaluo, Cliente> cliente = avaluo.join(Avaluo_.cliente);
        Join<Divipola, Regional> regional = ciudad.join(Divipola_.regional);
        consultaContar.select(criteriaBuilder.count(estadoAvaluo));

        Predicate filtro = construirPredicadoFiltro(criteriaBuilder,
                filtros, estadoAvaluo, avaluo, entidad, tipoAvaluo,
                perito, ciudad, cliente, motivo);
        /**
         * Filtrar los avaluos del Usuario actual
         * Los estado actuales unicamente
         */
        Predicate estadoActual = criteriaBuilder.equal(
                estadoAvaluo.get(EstadoAvaluo_.estadoActual), true);
        /**
         * Se obtiene por medio de una expresión lambda una lista de los id de
         * las entidades del usuario y se hace una condición in
         **/
        Predicate entidadesUsuario = enEntidadesUsuario(
                entidad, usuario, criteriaBuilder);
        /**
         * Agrega la condición para filtrar solo las regionles del usuario
         */
        Predicate regionalesUsuario = enRegionalesUsuario(
                regional, usuario, criteriaBuilder);
        /**
         * Agrega la condición para filtrar solo los tipos de avaluo
         * del usuario
         */
        Predicate tiposAvaluoUsuario = enTiposAvaluoUsuario(
                tipoAvaluo, usuario, criteriaBuilder);
        consultaContar.where(criteriaBuilder
                .and(estadoActual,filtro,entidadesUsuario,
                    regionalesUsuario, tiposAvaluoUsuario));
        return em.createQuery(
            consultaContar).getSingleResult();
    }

    public List<AvaluoDTO> encontrarAvaluosPorFiltrosOrden(int primero,
            int tamanoPagina, String campoOrden, SortOrder orden,
            Map<String, Object> filtros, UsuarioDTO usuario) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<AvaluoDTO> listadoQuery = criteriaBuilder
                .createQuery(AvaluoDTO.class);
        Root<EstadoAvaluo> estadoAvaluo = listadoQuery
            .from(EstadoAvaluo.class);
        Join<EstadoAvaluo, Avaluo> avaluo = estadoAvaluo
                .join(EstadoAvaluo_.avaluo);
        Join<Avaluo, Entidad> entidad = avaluo.join(Avaluo_.entidad);
        Join<Avaluo, Motivo> motivo= avaluo.join(Avaluo_.motivo, JoinType.LEFT);
        Join<Avaluo, TipoInmueble> tipoInmueble = avaluo
                .join(Avaluo_.tipoDeInmueble, JoinType.LEFT);
        Join<Avaluo, TipoAvaluo> tipoAvaluo = avaluo.join(Avaluo_.tipoAvaluo);
        Join<Avaluo, Usuario> perito = avaluo.join(Avaluo_.perito,
                JoinType.LEFT);
        Join<Avaluo, Usuario> creador = avaluo.join(Avaluo_.creador,
                JoinType.LEFT);
        Join<Avaluo, Divipola> ciudad = avaluo.join(Avaluo_.divipola);
        Join<Avaluo, Cliente> cliente = avaluo.join(Avaluo_.cliente);
        Join<Cliente, Segmento> segmento = cliente.join(Cliente_.segmento,
                JoinType.LEFT);
        Join<Cliente, Divipola> clienteDivipola = cliente
                .join(Cliente_.divipola, JoinType.LEFT);
        Join<Divipola, Regional> regional = ciudad.join(Divipola_.regional);

        listadoQuery.select(construirSelectAvaluo(criteriaBuilder,
                    estadoAvaluo, avaluo, entidad, tipoInmueble,
                    tipoAvaluo, perito, creador, ciudad, cliente,
                    segmento, clienteDivipola, regional, motivo));

        Path<?> path = getPath(campoOrden, estadoAvaluo, avaluo, cliente,
                entidad, tipoAvaluo, perito, ciudad);
        if (orden == null){
            // No organizar
        }else if (orden.equals(SortOrder.ASCENDING)){
            listadoQuery.orderBy(criteriaBuilder.asc(path));
        }else if (orden.equals(SortOrder.DESCENDING)){
            listadoQuery.orderBy(criteriaBuilder.desc(path));
        }else if (orden.equals(SortOrder.UNSORTED)){
            // No organizar
        }else{
            // No organizar
        }

        Predicate filtro = construirPredicadoFiltro(criteriaBuilder,
                filtros, estadoAvaluo, avaluo, entidad, tipoAvaluo,
                perito, ciudad, cliente, motivo);
        /**
         * Filtrar los avaluos del Usuario actual Los estado actuales
         * unicamente
         */
        Predicate estadoActual = criteriaBuilder.equal(
                estadoAvaluo.get(EstadoAvaluo_.estadoActual), true);
        /**
         * Se obtiene por medio de una expresión lambda una lista de los id de
         * las entidades del usuario y se hace una condición in
         **/
        Predicate entidadesUsuario = enEntidadesUsuario(
                entidad, usuario, criteriaBuilder);
        /**
         * Agrega la condición para filtrar solo las regionles del usuario
         */
        Predicate regionalesUsuario = enRegionalesUsuario(
                regional, usuario, criteriaBuilder);
        /**
         * Agrega la condición para filtrar solo los tipos de avaluo
         * del usuario
         */
        Predicate tiposAvaluoUsuario = enTiposAvaluoUsuario(
                tipoAvaluo, usuario, criteriaBuilder);
        listadoQuery.where(criteriaBuilder
                .and(estadoActual,filtro,entidadesUsuario,
                    regionalesUsuario, tiposAvaluoUsuario));

        //pagination
        TypedQuery<AvaluoDTO> tq = em.createQuery(listadoQuery);
        if (tamanoPagina >= 0){
            tq.setMaxResults(tamanoPagina);
        }
        if (primero >= 0){
            tq.setFirstResult(primero);
        }
        log.info("Consulta estrategia rol: {}",
                tq.unwrap(org.hibernate.Query.class).getQueryString());
        tq.setHint("org.hibernate.cacheable", true);
        return tq.getResultList();
    }

}
