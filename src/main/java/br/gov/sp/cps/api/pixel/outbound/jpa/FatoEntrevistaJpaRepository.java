package br.gov.sp.cps.api.pixel.outbound.jpa;

import br.gov.sp.cps.api.pixel.core.domain.entity.FatoEntrevista;
import br.gov.sp.cps.api.pixel.core.domain.repository.FatoEntrevistaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FatoEntrevistaJpaRepository extends JpaRepository<FatoEntrevista, Integer>, FatoEntrevistaRepository {

    default List<FatoEntrevista> salvar(List<FatoEntrevista> fatoEntrevistas) {
        return saveAll(fatoEntrevistas);
    }
}
