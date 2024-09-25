package br.gov.sp.cps.api.pixel.core.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "fato_vaga")
public class FatoVaga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFatoVaga;

    @ManyToOne
    @JoinColumn(name = "id_vaga", nullable = false)
    private Vaga vaga;

    @ManyToOne
    @JoinColumn(name = "id_processo_seletivo", nullable = false)
    private ProcessoSeletivo processoSeletivo;

    @ManyToOne
    @JoinColumn(name = "Dim_Periodo_id_periodo", nullable = false)
    private Periodo periodo;

    @Column(nullable = false)
    private int nrPosicoesAbertas;

    @Column(nullable = false)
    private int nrCandidatosInscritos;

    @Column(nullable = false)
    private double salarioInicialMedio;

    @Column(nullable = false)
    private int tempoMedioProcesso;
}
