package br.gov.sp.cps.api.pixel.core.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "fato_entrevista")
public class FatoEntrevista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFatoEntrevista;

    @ManyToOne
    @JoinColumn(name = "id_entrevista", nullable = false)
    private Entrevista entrevista;

    @ManyToOne
    @JoinColumn(name = "id_vaga", nullable = false)
    private Vaga vaga;

    @ManyToOne
    @JoinColumn(name = "id_feedback")
    private Feedback feedback;

    @ManyToOne
    @JoinColumn(name = "id_acao_seletiva")
    private AcaoSeletiva acaoSeletiva;

    @ManyToOne
    @JoinColumn(name = "id_participante_rh")
    private ParticipanteRH participanteRh;

    @ManyToOne
    @JoinColumn(name = "id_contratacao", nullable = false)
    private Contratacao contratacao;

    @Column(nullable = false)
    private int nrEntrevistas;

    @Column(nullable = false)
    private int nrContratacoes;
}
