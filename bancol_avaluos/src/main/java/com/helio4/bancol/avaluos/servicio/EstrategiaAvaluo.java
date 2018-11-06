package com.helio4.bancol.avaluos.servicio;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import com.helio4.bancol.avaluos.dto.AvaluoDTO;
import com.helio4.bancol.avaluos.dto.UsuarioDTO;
import com.helio4.bancol.avaluos.modelo.Avaluo;
import com.helio4.bancol.avaluos.modelo.Avaluo_;
import com.helio4.bancol.avaluos.modelo.Cliente;
import com.helio4.bancol.avaluos.modelo.Cliente_;
import com.helio4.bancol.avaluos.modelo.Divipola;
import com.helio4.bancol.avaluos.modelo.Divipola_;
import com.helio4.bancol.avaluos.modelo.Entidad;
import com.helio4.bancol.avaluos.modelo.Entidad_;
import com.helio4.bancol.avaluos.modelo.EstadoAvaluo;
import com.helio4.bancol.avaluos.modelo.EstadoAvaluo_;
import com.helio4.bancol.avaluos.modelo.Motivo;
import com.helio4.bancol.avaluos.modelo.Motivo_;
import com.helio4.bancol.avaluos.modelo.Regional;
import com.helio4.bancol.avaluos.modelo.Regional_;
import com.helio4.bancol.avaluos.modelo.Segmento;
import com.helio4.bancol.avaluos.modelo.Segmento_;
import com.helio4.bancol.avaluos.modelo.TipoAvaluo;
import com.helio4.bancol.avaluos.modelo.TipoAvaluo_;
import com.helio4.bancol.avaluos.modelo.TipoInmueble;
import com.helio4.bancol.avaluos.modelo.TipoInmueble_;
import com.helio4.bancol.avaluos.modelo.Usuario;
import com.helio4.bancol.avaluos.modelo.Usuario_;
import com.helio4.bancol.avaluos.servicio.util.Constantes.Estado;

public abstract class EstrategiaAvaluo {

    public Predicate construirPredicadoFiltro(CriteriaBuilder criteriaBuilder,
            Map<String, Object> filtros, Root<EstadoAvaluo> estadoAvaluo,
            Join<EstadoAvaluo, Avaluo> avaluo,
            Join<Avaluo, Entidad> entidad,
            Join<Avaluo, TipoAvaluo> tipoAvaluo,
            Join<Avaluo, Usuario> perito,
            Join<Avaluo, Divipola> ciudad,
            Join<Avaluo, Cliente> cliente,
            Join<Avaluo, Motivo> motivo) {
        /**
         * Agrega la condición para filtrar solo los avaluos en estados que se
         * le permitan ver al usuario según los permisos que tenga.
         */
        Set<Long> estados = new HashSet<>();
        //filter
        Predicate filterCondition = criteriaBuilder.conjunction();
        for (Map.Entry<String, Object> filter : filtros.entrySet()) {
            if (!filter.getValue().equals("")) {
                // try as string using like
                String filterValue = ((String) filter.getValue())
                    .toLowerCase();
                /**
                 * Se salta el filtro si es el numero de documento del
                 * cliente y no es númerico.
                 */
                if (filter.getKey().equals("cliente.numeroDocumento")
                        && !filterValue.matches("[0-9]+")) {
                    filterCondition = criteriaBuilder.and(filterCondition,
                            criteriaBuilder.isNull(cliente.get(Cliente_.id)
                                .get("numeroDocumento")));
                    continue;
                        }
                Path<String> pathFilter = getStringPath(filter.getKey(),
                        estadoAvaluo, avaluo, entidad, tipoAvaluo, perito,
                        ciudad, motivo);
                if (pathFilter != null) {
                    filterCondition = criteriaBuilder.and(filterCondition,
                            criteriaBuilder.like(
                                criteriaBuilder.lower(pathFilter),
                                "%" + filterValue + "%"));
                } else {
                    // try as non-string using equal
                    Path<?> pathFilterNonString = getPath(filter.getKey(),
                            estadoAvaluo, avaluo, cliente, entidad, 
                            tipoAvaluo, perito, ciudad);
                    if (pathFilterNonString.getJavaType().equals(Integer.class)
                            && filter.getKey()
                            .equals("estado.estado.value()")) {
                        estados = Stream.of(Estado.values())
                            .filter(e -> e.toString().toLowerCase()
                                    .contains(((String) filter
                                            .getValue()).toLowerCase()))
                            .map(e -> new Long(e.key()))
                            .collect(Collectors.toSet());
                        if (!estados.isEmpty())
                            filterCondition = criteriaBuilder
                                .and(filterCondition,
                                        estadoAvaluo
                                        .get(EstadoAvaluo_.estado)
                                        .in(estados));
                    } else if (pathFilterNonString.getJavaType()
                            .equals(Date.class)) {
                        filterCondition = criteriaBuilder.and(filterCondition,
                                criteriaBuilder.like(
                                    criteriaBuilder
                                    .lower(
                                        criteriaBuilder.function(
                                            "TO_CHAR",
                                            String.class,
                                            pathFilterNonString,
                                            criteriaBuilder
                                            .literal(
                                                "yyyy-MM-dd"))),
                                    "%" + filterValue + "%"));
                    } else {
                        filterCondition = criteriaBuilder.and(filterCondition,
                                criteriaBuilder.equal(pathFilterNonString,
                                    filterValue));
                    }
                }
            }
        }
        /**
         * Agrega la condicion basada en al variable de #estados
         * Esta condición se agrega solo si no hay ningun filtro
         * por estados es decir el conjunto de #estados esta vacio
         * y no estan aplicados los filtros de codigoExterno y
         * numeroDocumento que permiten traer los avaluos en cualquier
         * estado.
         */
        Predicate estadosIncluidos = criteriaBuilder.conjunction();
        if (estados.isEmpty()
                && !filtros.containsKey("codigoExterno")
                && !filtros.containsKey("cliente.numeroDocumento")) {
            estados.add(11l);
            estados.add(14l);
            estadosIncluidos = criteriaBuilder.not(
                    estadoAvaluo.get(EstadoAvaluo_.estado).in(estados));
        }
        return criteriaBuilder.and(filterCondition, estadosIncluidos);
    }

    /**
     * Construye la sentencia de selección de la consulta del listado de avaluos
     * @param criteriaBuilder
     * @param estadoAvaluo
     * @param avaluo
     * @param entidad
     * @param tipoInmueble
     * @param tipoAvaluo
     * @param perito
     * @param creador
     * @param ciudad
     * @param cliente
     * @param segmento
     * @param clienteDivipola
     * @param regional
     * @return La sentencia de selección de la consulta del listado de avaluos
     */
    public Selection<AvaluoDTO> construirSelectAvaluo(
            CriteriaBuilder criteriaBuilder, Root<EstadoAvaluo> estadoAvaluo,
            Join<EstadoAvaluo, Avaluo> avaluo, Join<Avaluo, Entidad> entidad,
            Join<Avaluo, TipoInmueble> tipoInmueble,
            Join<Avaluo, TipoAvaluo> tipoAvaluo,
            Join<Avaluo, Usuario> perito,
            Join<Avaluo, Usuario> creador,
            Join<Avaluo, Divipola> ciudad,
            Join<Avaluo, Cliente> cliente,
            Join<Cliente, Segmento> segmento,
            Join<Cliente, Divipola> clienteDivipola,
            Join<Divipola, Regional> regional,
            Join<Avaluo, Motivo> motivo) {
        return criteriaBuilder.construct(AvaluoDTO.class,
                avaluo.get(Avaluo_.id), estadoAvaluo.get(
                    EstadoAvaluo_.estado),
                estadoAvaluo.get(EstadoAvaluo_.fechaEstado),
                avaluo.get(Avaluo_.valorSolicitado),
                avaluo.get(Avaluo_.codigoExterno),
                tipoInmueble.get(TipoInmueble_.id),
                tipoInmueble.get(TipoInmueble_.nombre),
                tipoAvaluo.get(TipoAvaluo_.id),
                tipoAvaluo.get(TipoAvaluo_.nombre),
                avaluo.get(Avaluo_.tipoVia),
                avaluo.get(Avaluo_.numeroVia),
                avaluo.get(Avaluo_.complementoVia),
                avaluo.get(Avaluo_.numeroViaGeneradora),
                avaluo.get(Avaluo_.complementoViaGeneradora),
                avaluo.get(Avaluo_.placa),
                avaluo.get(Avaluo_.adicionalDireccion),
                avaluo.get(Avaluo_.conjunto),
                avaluo.get(Avaluo_.direccionInmueble),
                avaluo.get(Avaluo_.barrio), ciudad.get(Divipola_.id),
                ciudad.get(Divipola_.departamento),
                ciudad.get(Divipola_.municipio),
                ciudad.get(Divipola_.centroPoblado),
                entidad.get(Entidad_.id),
                entidad.get(Entidad_.nombre),
                cliente.get(Cliente_.id).get("numeroDocumento"),
                cliente.get(Cliente_.id).get("tipoDocumentoIdentificacion"),
                cliente.get(Cliente_.primerNombre),
                cliente.get(Cliente_.segundoNombre),
                cliente.get(Cliente_.primerApellido),
                cliente.get(Cliente_.segundoApellido),
                cliente.get(Cliente_.razonSocial),
                cliente.get(Cliente_.telefonoSolicitante),
                cliente.get(Cliente_.celularSolicitante),
                cliente.get(Cliente_.direccionDeContactoSolicitante),
                cliente.get(Cliente_.correoElectronicoSolicitante),
                segmento.get(Segmento_.id), segmento.get(Segmento_.nombre),
                clienteDivipola.get(Divipola_.id),
                clienteDivipola.get(Divipola_.departamento),
                clienteDivipola.get(Divipola_.centroPoblado),
                perito.get(Usuario_.id).get("numeroDocumento"),
                perito.get(Usuario_.id).get("tipoDocumentoIdentificacion"),
                perito.get(Usuario_.nombres), perito.get(Usuario_.apellidos),
                perito.get(Usuario_.celular), perito.get(Usuario_.email),
                avaluo.get(Avaluo_.nombreRecibe),
                avaluo.get(Avaluo_.telefonoRecibe),
                avaluo.get(Avaluo_.correoElectronicoRecibe),
                avaluo.get(Avaluo_.nombreAsesor),
                avaluo.get(Avaluo_.sucursalAsesor),
                avaluo.get(Avaluo_.celularAsesor),
                avaluo.get(Avaluo_.telefonoAsesor),
                avaluo.get(Avaluo_.observacionesSolicitante),
                entidad.get(Entidad_.duracionSemaforoVerde),
                entidad.get(Entidad_.duracionSemaforoAmarillo),
                entidad.get(Entidad_.duracionSemaforoRojo),
                avaluo.get(Avaluo_.duracionPausaSemaforo),
                avaluo.get(Avaluo_.fechaCreacion),
                avaluo.get(Avaluo_.cambioGarantia),
                avaluo.get(Avaluo_.compraCartera),
                avaluo.get(Avaluo_.motivoId),
                motivo.get(Motivo_.nombre),
                avaluo.get(Avaluo_.avaluoSisgenId));
    }

    public Predicate enEntidadesUsuario(Join<Avaluo, Entidad> entidad,
            UsuarioDTO usuario, CriteriaBuilder cb) {
        List<Long> entidades = usuario.getEntidades().stream()
            .map(e -> e.getId()).collect(Collectors.toList());
        return entidades.isEmpty() ? cb.conjunction() :
            entidad.get(Entidad_.id).in(entidades);
    }

    public Predicate enRegionalesUsuario(Join<Divipola, Regional> regional,
            UsuarioDTO usuario, CriteriaBuilder cb) {
        List<Long> regionales = usuario.getRegionales().stream()
                .map(r -> r.getId()).collect(Collectors.toList());
        return regionales.isEmpty() ? cb.conjunction() :
            regional.get(Regional_.id).in(regionales);
    }

    public Predicate enTiposAvaluoUsuario(Join<Avaluo, TipoAvaluo> tipoAvaluo,
            UsuarioDTO usuario, CriteriaBuilder cb) {
        List<Long> tiposAvaluo = usuario.getTipoAvaluos().stream()
                .map(ta -> ta.getId()).collect(Collectors.toList());
        return tiposAvaluo.isEmpty() ? cb.conjunction() :
            tipoAvaluo.get(TipoAvaluo_.id).in(tiposAvaluo);
    }

    public Path<?> getPath(String field, Root<EstadoAvaluo> estadoAvaluo,
            Join<EstadoAvaluo, Avaluo> avaluo, Join<Avaluo, Cliente> cliente,
            Join<Avaluo, Entidad> entidad, Join<Avaluo, TipoAvaluo> avaluoTipo,
            Join<Avaluo, Usuario> perito, Join<Avaluo, Divipola> ciudad) {
        // sort
        Path<?> path = null;
        if (field == null) {
            path = avaluo.get(Avaluo_.fechaCreacion);
        } else {
            switch (field) {
                case "estado.estado.value()":
                    path = estadoAvaluo.get(EstadoAvaluo_.estado);
                    break;
                case "cliente.numeroDocumento":
                    path = cliente.get(Cliente_.id).get("numeroDocumento");
                    break;
                case "codigoExterno":
                    path = avaluo.get(Avaluo_.codigoExterno);
                    break;
                case "fechaEstado":
                    path = estadoAvaluo.get(EstadoAvaluo_.fechaEstado);
                    break;
                case "tipoAvaluo.nombre":
                    path = avaluoTipo.get(TipoAvaluo_.nombre);
                    break;
                case "entidad.nombre":
                    path = entidad.get(Entidad_.nombre);
                    break;
                case "perito.nombres":
                    path = perito.get(Usuario_.nombres);
                    break;
                case "direccionInmueble":
                    path = avaluo.get(Avaluo_.direccionInmuebleInforme);
                    break;
                case "avaluoSisgenId":
                    path = avaluo.get(Avaluo_.avaluoSisgenId);
                    break;
                case "barrio":
                    path = avaluo.get(Avaluo_.barrio);
                    break;
                case "divipola":
                    path = ciudad.get(Divipola_.centroPoblado);
                    break;
            }
        }
        return path;
    }

    public Path<String> getStringPath(String field,
            Root<EstadoAvaluo> estadoAvaluo, Join<EstadoAvaluo, Avaluo> avaluo,
            Join<Avaluo, Entidad> entidad, Join<Avaluo, TipoAvaluo> avaluoTipo,
            Join<Avaluo, Usuario> perito, Join<Avaluo, Divipola> ciudad, Join<Avaluo, Motivo> motivo) {
        // sort
        Path<String> path = null;
        if (field == null) {
            // path = avaluo.get(Avaluo_.fechaCreacion);
        } else {
            switch (field) {
                case "estado.estado.value()":
                    // path = estadoAvaluo.get(EstadoAvaluo_.estado);
                    break;
                case "codigoExterno":
                    path = avaluo.get(Avaluo_.codigoExterno);
                    break;
                case "fechaEstado":
                    // path = estadoAvaluo.get(EstadoAvaluo_.fechaEstado);
                    break;
                case "tipoAvaluo.nombre":
                    path = avaluoTipo.get(TipoAvaluo_.nombre);
                    break;
                case "entidad.nombre":
                    path = entidad.get(Entidad_.nombre);
                    break;
                case "perito.nombres":
                    path = perito.get(Usuario_.nombres);
                    break;
                case "direccionInmueble":
                    path = avaluo.get(Avaluo_.direccionInmuebleInforme);
                    break;
                case "barrio":
                    path = avaluo.get(Avaluo_.barrio);
                    break;
                case "divipola":
                    path = ciudad.get(Divipola_.centroPoblado);
                    break;
                case "motivoAux.nombre":
                    path = motivo.get(Motivo_.nombre);
                    break;
            }
        }
        return path;
    }
}
