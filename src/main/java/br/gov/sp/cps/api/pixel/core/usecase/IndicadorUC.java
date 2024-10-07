package br.gov.sp.cps.api.pixel.core.usecase;

import br.gov.sp.cps.api.pixel.core.domain.dto.command.IndicadorCommand;
import br.gov.sp.cps.api.pixel.core.domain.entity.Indicador;
import br.gov.sp.cps.api.pixel.core.domain.repository.IndicadorRepository;
import org.springframework.stereotype.Service;

@Service
public class IndicadorUC {

    private final IndicadorRepository indicadorRepository;

    public IndicadorUC(IndicadorRepository indicadorRepository) {
        this.indicadorRepository = indicadorRepository;
    }

    public Indicador postarr(IndicadorCommand novaIndicadorCommand) {
        return indicadorRepository.salvar(Indicador.toEntity(novaIndicadorCommand));
    }
}
