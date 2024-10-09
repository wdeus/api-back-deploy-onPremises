package br.gov.sp.cps.api.pixel.outbound.jpa.bd_Indicador;

import br.gov.sp.cps.api.pixel.core.domain.entity.Indicador;
import br.gov.sp.cps.api.pixel.core.domain.repository.IndicadorRepository;
import br.gov.sp.cps.api.pixel.outbound.jpa.IndicadorJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    @Override
    public List<Indicador> listarTodos() {
        return indicadorJpaRepository.findAll();
    }

    @Override
    public Optional<Indicador> buscarID(int id){
        return indicadorJpaRepository.findById(id);
    }
}
