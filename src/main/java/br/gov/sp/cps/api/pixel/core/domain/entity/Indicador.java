package br.gov.sp.cps.api.pixel.core.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "config_Indicador")
public class Indicador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_config_Indicador;

    @Column(name = "indicador_nome")
    private String indicadorNome;

    @Column(name = "indicador_campo")
    private String indicadorCampo;

    @Column(name = "indicador_comparador")
    private String indicadorComparador;

    @Column(name = "indicadorvalor")
    private String indicadorValor;

    @Column(name = "filtro_nome")
    private String filtroNome;

    @Column(name = "filtro_campo")
    private String filtroCampo;

    @Column(name = "filtro_comparador")
    private String filtroComparador;

    @Column(name = "filtro_valor")
    private String filtroValor;

    @Column(nullable = false)
    private String usuario ;
}
