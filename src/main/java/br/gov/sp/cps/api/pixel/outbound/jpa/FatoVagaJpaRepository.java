package br.gov.sp.cps.api.pixel.outbound.jpa;

import br.gov.sp.cps.api.pixel.core.domain.entity.FatoVaga;
import br.gov.sp.cps.api.pixel.core.domain.repository.FatoVagaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FatoVagaJpaRepository extends JpaRepository<FatoVaga, Integer>, FatoVagaRepository {
    default List<FatoVaga> salvar(List<FatoVaga> fatoVagas) {
        return saveAll(fatoVagas);
    }
}
