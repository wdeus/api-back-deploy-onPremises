package br.gov.sp.cps.api.pixel.core.domain.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "permissao")
public class Permissao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPermissao;

    @Column(name = "chave")
    private String chave;

    @Column(name = "descricao")
    private String descricao;
}
