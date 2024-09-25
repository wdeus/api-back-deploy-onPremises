package br.gov.sp.cps.api.pixel.core.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "dim_vaga")
public class Vaga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVaga;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private int nrPosicoes;

    @Column(nullable = false)
    private String requisitos;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String localizacao;

    @Column(nullable = false)
    private String responsavel;
}
