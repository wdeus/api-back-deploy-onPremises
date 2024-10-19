package br.gov.sp.cps.api.pixel.inbound.rest;

import br.gov.sp.cps.api.pixel.core.domain.dto.command.IndicadorCommand;
import br.gov.sp.cps.api.pixel.core.usecase.NotificacaoUC;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notificacoes")
public class NotificacaoController {

    private final NotificacaoUC notificacaoUC;

    public NotificacaoController(NotificacaoUC notificacaoUC) {
        this.notificacaoUC = notificacaoUC;
    }

    @PostMapping
    public void obterNotificacao(@RequestBody IndicadorCommand indicadorCommand) {
        String usuario = indicadorCommand.getUsuario();
        notificacaoUC.verificarIndicadores(usuario);
    }
}
