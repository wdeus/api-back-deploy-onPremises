package br.gov.sp.cps.api.pixel.core.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "dim_acao_seletiva")
public class AcaoSeletiva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAcao;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dtHora;
}
