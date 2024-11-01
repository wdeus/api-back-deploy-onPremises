package br.gov.sp.cps.api.pixel.outbound.jpa;

import br.gov.sp.cps.api.pixel.core.domain.entity.Grupo;
import br.gov.sp.cps.api.pixel.core.domain.repository.GrupoRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GrupoJpaRepository extends JpaRepository<Grupo,Integer>, GrupoRepository {
    default List<Grupo> listarTodos(){
        return findAll();
    }
}
