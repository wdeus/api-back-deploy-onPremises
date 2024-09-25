package br.gov.sp.cps.api.pixel.core.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "dim_participante_rh")
public class ParticipanteRH {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idParticipanteRh;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cargo;
}
