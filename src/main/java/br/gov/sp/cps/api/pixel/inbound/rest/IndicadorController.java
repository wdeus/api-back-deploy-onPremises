package br.gov.sp.cps.api.pixel.inbound.rest;

import br.gov.sp.cps.api.pixel.core.domain.dto.command.IndicadorCommand;
import br.gov.sp.cps.api.pixel.core.domain.entity.Indicador;
import br.gov.sp.cps.api.pixel.core.usecase.IndicadorUC;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/indicadores")
public class IndicadorController {
    private final IndicadorUC indicadorUC;

    public IndicadorController(IndicadorUC indicadorUC) {
        this.indicadorUC = indicadorUC;
    }

    @PostMapping
    public ResponseEntity<Indicador> criarIndicador(@RequestBody IndicadorCommand indicadorcommond) {
        Indicador novoIndicador = indicadorUC.postarr(indicadorcommond);
        return ResponseEntity.ok(novoIndicador);
    }
}
