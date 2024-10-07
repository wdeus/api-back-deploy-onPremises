package br.gov.sp.cps.api.pixel.outbound.jpa.bd_Indicador;

import br.gov.sp.cps.api.pixel.core.domain.entity.Indicador;
import br.gov.sp.cps.api.pixel.core.domain.repository.IndicadorRepository;
import br.gov.sp.cps.api.pixel.outbound.jpa.IndicadorJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class IndicadorRepositoryBd implements IndicadorRepository {

    private final IndicadorJpaRepository indicadorJpaRepository;

    public IndicadorRepositoryBd(IndicadorJpaRepository indicadorJpaRepository) {
        this.indicadorJpaRepository = indicadorJpaRepository;
    }

    @Override
    public Indicador salvar(Indicador indicador) {
        return  indicadorJpaRepository.save(indicador);
    }
}
