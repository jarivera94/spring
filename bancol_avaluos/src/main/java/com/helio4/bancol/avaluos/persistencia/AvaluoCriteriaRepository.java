package com.helio4.bancol.avaluos.persistencia;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.helio4.bancol.avaluos.dto.AvaluoDTO;

@Repository
public class AvaluoCriteriaRepository {

    private static Logger log = LoggerFactory
        .getLogger( AvaluoCriteriaRepository.class );

    @PersistenceContext
    private EntityManager em;

    public boolean eliminarTipoAvaluo(Long avaluoRemateId) {
        Query query = em.createQuery(
                "DELETE FROM AvaluoRemate a WHERE a.id = :avaluoRemateId");
        query.setParameter("avaluoRemateId", avaluoRemateId);
        int result = query.executeUpdate();
        return result > 0;
    }

    public boolean crearTipoAvaluo(Long avaluoRemateId) {
        Query query = em.createNativeQuery(
                "INSERT INTO avaluos.avaluo_hipotecario(avaluo_id) "
                +"VALUES (:avaluoRemateId)");
        query.setParameter("avaluoRemateId", avaluoRemateId);
        int result = query.executeUpdate();
        return result > 0;
    }

    public List<AvaluoDTO> encontrarAvaluos(String consulta,
            Date fechaInicialInforme, Date fechaFinalInforme) {
        Query query = em.createQuery(consulta);
        query.setParameter("fechaInicialInforme", fechaInicialInforme);
        query.setParameter("fechaFinalInforme", fechaFinalInforme);
        return (List<AvaluoDTO>) query.getResultList();
    }

    public List<String> encontrarAvaluosConsultaNativa(String consulta,
            Date fechaInicialInforme, Date fechaFinalInforme) {
        Query query = em.createNativeQuery(consulta);
        query.setParameter("fechaInicialInforme", fechaInicialInforme);
        query.setParameter("fechaFinalInforme", fechaFinalInforme);
        log.info("Consulta avaluos nativa: {}",
                query.unwrap(org.hibernate.Query.class).getQueryString());
        return (List<String>) query.getResultList();
    }
}
