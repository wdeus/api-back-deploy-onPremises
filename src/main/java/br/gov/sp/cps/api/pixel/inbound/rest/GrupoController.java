package br.gov.sp.cps.api.pixel.inbound.rest;

import br.gov.sp.cps.api.pixel.core.domain.dto.SalvarPermissoesGrupoDTO;
import br.gov.sp.cps.api.pixel.core.domain.entity.Grupo;
import br.gov.sp.cps.api.pixel.core.usecase.GrupoUC;
import br.gov.sp.cps.api.pixel.core.usecase.SalvarPermissoesGruposUC;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios/grupos")
@RequiredArgsConstructor
public class GrupoController {

    private final GrupoUC grupoUC;
    private final SalvarPermissoesGruposUC salvarPermissoesGruposUC;

    @GetMapping
    public List<Grupo> listar() {
        return grupoUC.listar();
    }

    @PostMapping("/permissoes")
    public ResponseEntity<Void> salvarPermissoes(@RequestBody SalvarPermissoesGrupoDTO data) {
        salvarPermissoesGruposUC.salvarPermissoes(data.getGrupoId(), data.getPermissoesIds());
        return ResponseEntity.ok().build();
    }
}
