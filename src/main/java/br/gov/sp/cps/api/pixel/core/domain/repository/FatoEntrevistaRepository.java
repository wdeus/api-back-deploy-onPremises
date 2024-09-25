package br.gov.sp.cps.api.pixel.core.domain.repository;

import br.gov.sp.cps.api.pixel.core.domain.entity.FatoEntrevista;

import java.util.List;

public interface FatoEntrevistaRepository {
    List<FatoEntrevista> salvar(List<FatoEntrevista> fatoEntrevistas);
}
