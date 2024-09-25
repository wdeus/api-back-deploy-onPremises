package br.gov.sp.cps.api.pixel.core.domain.repository;

import br.gov.sp.cps.api.pixel.core.domain.entity.FatoVaga;

import java.util.List;

public interface FatoVagaRepository {
    List<FatoVaga> salvar(List<FatoVaga> fatoVagas);
}
