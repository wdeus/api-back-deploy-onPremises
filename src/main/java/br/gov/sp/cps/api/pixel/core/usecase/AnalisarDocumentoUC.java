package br.gov.sp.cps.api.pixel.core.usecase;

import br.gov.sp.cps.api.pixel.core.domain.repository.MapeamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnalisarDocumentoUC {

    private String json = """
            Aqui está a estrutura JSON com os dados da planilha:
             
             [
               {
                 "cargoParticipanteRH": "Gerente de RH",
                 "criadorProcessoSeletivo": "Pedro Rocha",
                 "descricaoFeedback": "Bom perfil",
                 "descricaoProcessoSeletivo": "Processo para desenvolvedor front-end",
                 "dtAberturaVaga": "8/30/24",
                 "dtAceiteOferta": "9/9/24",
                 "dtContratacao": "9/15/24",
                 "dtEntrevista": "9/10/24",
                 "dtFechamentoVaga": "9/25/24",
                 "dtFimProcessoSeletivo": "9/20/24",
                 "dtAcaoSeletiva": "9/10/24",
                 "dtInicioProcessoSeletivo": "9/1/24",
                 "entrevistador": "João Silva",
                 "localizacaoVaga": "São Paulo",
                 "nomeParticipanteRH": "Wagner",
                 "nomeProcessoSeletivo": "Processo para desenvolvedor front-end",
                 "nrCandidatosInscritos": null,
                 "nrContratacoes": null,
                 "nrEntrevistas": null,
                 "nrPosicoesAbertas": null,
                 "nrPosicoesVaga": null,
                 "requisitosVaga": "React, HTML, CSS",
                 "responsavelVaga": "Carla Santos",
                 "resultado": "Aprovado",
                 "salarioInicial": 5000.00,
                 "salarioInicialMedio": null,
                 "statusProcessoSeletivo": "Fechado",
                 "statusVaga": "Aberto",
                 "tempoMedioProcesso": null,
                 "tipo": "CLT",
                 "tipoContrato": 1,
                 "tituloVaga": "Desenvolvedor Front-end"
               },
               {
                 "cargoParticipanteRH": "Coordenador de RH",
                 "criadorProcessoSeletivo": "Lucas Lima",
                 "descricaoFeedback": "Precisa melhorar comunicação",
                 "descricaoProcessoSeletivo": "Processo para analista de marketing digital",
                 "dtAberturaVaga": "8/25/24",
                 "dtAceiteOferta": null,
                 "dtContratacao": null,
                 "dtEntrevista": "9/10/24",
                 "dtFechamentoVaga": "9/30/24",
                 "dtFimProcessoSeletivo": "9/22/24",
                 "dtAcaoSeletiva": "9/11/24",
                 "dtInicioProcessoSeletivo": "9/5/24",
                 "entrevistador": "Ana Pereira",
                 "localizacaoVaga": "Rio de Janeiro",
                 "nomeParticipanteRH": "Rafael Nunes",
                 "nomeProcessoSeletivo": "Processo para analista de marketing digital",
                 "nrCandidatosInscritos": null,
                 "nrContratacoes": null,
                 "nrEntrevistas": null,
                 "nrPosicoesAbertas": null,
                 "nrPosicoesVaga": null,
                 "requisitosVaga": "SEO, Google Ads",
                 "responsavelVaga": "Danillo",
                 "resultado": "Reprovado",
                 "salarioInicial": 4500.00,
                 "salarioInicialMedio": null,
                 "statusProcessoSeletivo": "Fechado",
                 "statusVaga": "Fechado",
                 "tempoMedioProcesso": null,
                 "tipo": "PJ",
                 "tipoContrato": 1,
                 "tituloVaga": "Analista Marketing"
               },
               {
                 "cargoParticipanteRH": "Especialista de RH",
                 "criadorProcessoSeletivo": "Eduardo Tavares",
                 "descricaoFeedback": "Excelente liderança",
                 "descricaoProcessoSeletivo": "Seleção para gerente de projetos em TI",
                 "dtAberturaVaga": "9/1/24",
                 "dtAceiteOferta": "9/13/24",
                 "dtContratacao": "9/25/24",
                 "dtEntrevista": "9/15/24",
                 "dtFechamentoVaga": null,
                 "dtFimProcessoSeletivo": "9/28/24",
                 "dtAcaoSeletiva": "9/15/24",
                 "dtInicioProcessoSeletivo": "9/2/24",
                 "entrevistador": "José Costa",
                 "localizacaoVaga": "Belo Horizonte",
                 "nomeParticipanteRH": "Alisson",
                 "nomeProcessoSeletivo": "Seleção para gerente de projetos em TI",
                 "nrCandidatosInscritos": null,
                 "nrContratacoes": null,
                 "nrEntrevistas": null,
                 "nrPosicoesAbertas": null,
                 "nrPosicoesVaga": null,
                 "requisitosVaga": "Gestão de Equipes",
                 "responsavelVaga": "Paulo Andrade",
                 "resultado": "Aprovado",
                 "salarioInicial": 6500.00,
                 "salarioInicialMedio": null,
                 "statusProcessoSeletivo": "Em andamento",
                 "statusVaga": "Aberto",
                 "tempoMedioProcesso": null,
                 "tipo": "CLT",
                 "tipoContrato": 1,
                 "tituloVaga": "Gerente de Projetos"
               }
             ]
             
             Observação: As datas foram mantidas na forma como estavam na planilha, ou seja, em formato de texto, e não foram convertidas para formato de data ISO. Além disso, os campos que não tinham valores foram deixados vazios (null).
           """;

    private final MapeamentoRepository mapeamentoRepository;

    public void executar(){
        mapeamentoRepository.popularEntidades(json);
    }
}
