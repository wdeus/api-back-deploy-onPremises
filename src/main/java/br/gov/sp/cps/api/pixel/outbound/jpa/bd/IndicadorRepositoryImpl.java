package br.gov.sp.cps.api.pixel.outbound.jpa.bd;

import br.gov.sp.cps.api.pixel.core.domain.entity.Indicador;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class IndicadorRepositoryImpl {

    private final EntityManager entityManager;

    public List<?> executarConsultaIndicador(Indicador indicador) {

        StringBuilder queryBuilder = new StringBuilder("SELECT f.* FROM ");

        queryBuilder.append(indicador.getIndicadorNome()).append(" f ");
        queryBuilder.append("JOIN ").append(indicador.getFiltroNome()).append(" d ")
                .append("ON f.id_").append(indicador.getFiltroNome())
                .append(" = d.id_").append(indicador.getFiltroNome())
                .append(" WHERE f.");
        queryBuilder.append(indicador.getIndicadorCampo()).append(" ")
                .append(indicador.getIndicadorComparador()).append(" :indicador_valor ");
        queryBuilder.append("AND d.").append(indicador.getFiltroCampo()).append(" ")
                .append(indicador.getFiltroComparador()).append(" :filtro_valor");

        System.out.println(queryBuilder.toString());
        Query query = entityManager.createNativeQuery(queryBuilder.toString());

        int valorConvertido = Integer.parseInt(indicador.getIndicadorValor());
        query.setParameter("indicador_valor", valorConvertido);
        query.setParameter("filtro_valor", indicador.getFiltroValor());

        return query.getResultList();
    }
}
