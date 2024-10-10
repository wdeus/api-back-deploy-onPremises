package br.gov.sp.cps.api.pixel.inbound.rest;

import br.gov.sp.cps.api.pixel.core.domain.dto.command.IndicadorCommand;
import br.gov.sp.cps.api.pixel.core.domain.entity.Indicador;
import br.gov.sp.cps.api.pixel.core.usecase.IndicadorUC;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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

    @GetMapping
    public ResponseEntity<List<IndicadorCommand>> listarTodosIndicadores() {
        List<Indicador> indicadores = indicadorUC.listar();
        List<IndicadorCommand> indicadoresDTO = indicadores.stream()
                .map(Indicador::toCommand)
                .collect(Collectors.toList());
        return ResponseEntity.ok(indicadoresDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IndicadorCommand> buscarIndicadorPorId(@PathVariable int id) {
        Optional<Indicador> indicadorOptional = indicadorUC.buscarporID(id);
        if (indicadorOptional.isPresent()) {
            IndicadorCommand indicadorCommand = indicadorOptional.get().toCommand();
            return ResponseEntity.ok(indicadorCommand);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
