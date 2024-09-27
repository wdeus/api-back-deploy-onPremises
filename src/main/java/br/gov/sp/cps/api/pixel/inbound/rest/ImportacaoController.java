package br.gov.sp.cps.api.pixel.inbound.rest;

import br.gov.sp.cps.api.pixel.core.domain.dto.command.ImportarCommand;
import br.gov.sp.cps.api.pixel.core.usecase.AnalisarDocumentoUC;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("importacao")
@RequiredArgsConstructor
public class ImportacaoController {

    private final AnalisarDocumentoUC analisarDocumentoUC;

    @PostMapping
    public void importar(@RequestBody ImportarCommand document) {
        analisarDocumentoUC.executar(document.getDocument());
    }
}
