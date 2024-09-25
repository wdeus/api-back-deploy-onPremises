package br.gov.sp.cps.api.pixel.core.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "dim_periodo")
public class Periodo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPeriodo;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dtAbertura;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dtFechamento;
}
