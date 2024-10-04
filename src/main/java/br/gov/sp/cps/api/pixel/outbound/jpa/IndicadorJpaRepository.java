package br.gov.sp.cps.api.pixel.outbound.jpa;

import br.gov.sp.cps.api.pixel.core.domain.entity.Indicador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndicadorJpaRepository extends JpaRepository<Indicador,Integer> {
}
