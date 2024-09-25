package br.gov.sp.cps.api.pixel.outbound.jpa;

import br.gov.sp.cps.api.pixel.core.domain.entity.ProcessoSeletivo;
import br.gov.sp.cps.api.pixel.core.domain.repository.ProcessoSeletivoRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProcessoSeletivoJpaRepository extends JpaRepository<ProcessoSeletivo, Integer>, ProcessoSeletivoRepository {

    default List<ProcessoSeletivo> salvar(List<ProcessoSeletivo> processos) {
        return saveAll(processos);
    }

    default boolean existePorId(int id){
        return existsById(id);
    }
}
