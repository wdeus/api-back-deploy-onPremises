package br.gov.sp.cps.api.pixel.inbound.rest;

import br.gov.sp.cps.api.pixel.core.usecase.AnalisarDocumentoUC;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("importacao")
@RequiredArgsConstructor
public class ImportacaoController {

    private final AnalisarDocumentoUC analisarDocumentoUC;

    @GetMapping
    public void importar(){
        analisarDocumentoUC.executar();
    }
}
