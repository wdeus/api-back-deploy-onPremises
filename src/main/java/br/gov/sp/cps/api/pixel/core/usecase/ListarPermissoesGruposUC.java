package br.gov.sp.cps.api.pixel.core.usecase;

import br.gov.sp.cps.api.pixel.core.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListarPermissoesGruposUC {

    private final UserRepository userRepository;

    public List<Object[]> listarPermissoes(Long grupoId) {
        return userRepository.buscarPermissoesPorGrupoId(grupoId);
    }
}
