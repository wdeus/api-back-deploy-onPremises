package br.gov.sp.cps.api.pixel.core.domain.repository;


import br.gov.sp.cps.api.pixel.core.domain.entity.Grupo;


import java.util.List;

public interface GrupoRepository {

    List<Grupo> listarTodos();
}
