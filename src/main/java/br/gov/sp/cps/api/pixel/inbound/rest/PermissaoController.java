package br.gov.sp.cps.api.pixel.inbound.rest;

import br.gov.sp.cps.api.pixel.core.domain.dto.SalvarPermissoesGrupoDTO;
import br.gov.sp.cps.api.pixel.core.domain.entity.Permissao;
import br.gov.sp.cps.api.pixel.core.usecase.PermissaoUC;
import br.gov.sp.cps.api.pixel.core.usecase.SalvarPermissoesGruposUC;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/permissoes")
@RequiredArgsConstructor
public class PermissaoController {

    private final PermissaoUC permissaoUC;
    private final SalvarPermissoesGruposUC salvarPermissoesGruposUC;

    @GetMapping
    public List<Permissao> listar() {
        return permissaoUC.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Permissao> buscarPorId(@PathVariable int id) {
        Optional<Permissao> permissao = permissaoUC.buscarPorId(id);
        return permissao.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> salvarPermissoes(@RequestBody SalvarPermissoesGrupoDTO data) {
        salvarPermissoesGruposUC.salvarPermissoes(data.getGrupoId(), data.getPermissoesIds());
        return ResponseEntity.ok().build();
    }
}
