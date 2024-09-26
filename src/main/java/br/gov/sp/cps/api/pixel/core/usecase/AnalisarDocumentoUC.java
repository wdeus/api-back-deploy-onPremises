package br.gov.sp.cps.api.pixel.core.usecase;

import br.gov.sp.cps.api.pixel.core.domain.repository.AnaliseRepository;
import br.gov.sp.cps.api.pixel.core.domain.repository.DocumentoRepository;
import br.gov.sp.cps.api.pixel.core.domain.repository.MapeamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class AnalisarDocumentoUC {

    private final AnaliseRepository analiseRepository;
    private final DocumentoRepository documentoRepository;
    private final MapeamentoRepository mapeamentoRepository;

    private String mapeamento = """
                      Aqui está a lista de objetos JSON de acordo com as informações fornecidas:

            ```json
            [
              {
                "cargoParticipanteRH": "Gerente de RH",
                "criadorProcessoSeletivo": "Pedro Rocha",
                "descricaoFeedback": "Bom perfil",
                "descricaoProcessoSeletivo": "Processo para desenvolvedor front-end",
                "dtAberturaVaga": "2024-08-30",
                "dtAceiteOferta": "2024-09-12",
                "dtContratacao": "2024-09-15",
                "dtEntrevista": "2024-09-09",
                "dtFechamentoVaga": "2024-09-25",
                "dtFimProcessoSeletivo": "2024-09-20",
                "dtAcaoSeletiva": "2024-09-10",
                "dtInicioProcessoSeletivo": "2024-09-01",
                "entrevistador": "João Silva",
                "localizacaoVaga": "São Paulo",
                "nomeParticipanteRH": "Maria Souza",
                "nomeProcessoSeletivo": "Dev Front-end",
                "nrCandidatosInscritos": 1,
                "nrContratacoes": 1,
                "nrEntrevistas": 1,
                "nrPosicoesAbertas": 1,
                "nrPosicoesVaga": 1,
                "requisitosVaga": "React, HTML, CSS",
                "responsavelVaga": "Carla Santos",
                "resultado": "Aprovado",
                "salarioInicial": 5000.00,
                "salarioInicialMedio": 5000.00,
                "statusProcessoSeletivo": "Fechado",
                "statusVaga": "Aberto",
                "tempoMedioProcesso": 1,
                "tipo": "Entrevista",
                "tipoContrato": 1,
                "tituloVaga": "Desenvolvedor Front-end",
                "tipoAcaoSeletiva": "Entrevista"
              },
              {
                "cargoParticipanteRH": "Coordenador de RH",
                "criadorProcessoSeletivo": "Lucas Lima",
                "descricaoFeedback": "Precisa melhorar comunicação",
                "descricaoProcessoSeletivo": "Processo para analista de marketing digital",
                "dtAberturaVaga": "2024-08-25",
                "dtAceiteOferta": "2024-09-18",
                "dtContratacao": "2024-09-20",
                "dtEntrevista": "2024-09-10",
                "dtFechamentoVaga": "2024-09-30",
                "dtFimProcessoSeletivo": "2024-09-22",
                "dtAcaoSeletiva": "2024-09-11",
                "dtInicioProcessoSeletivo": "2024-09-05",
                "entrevistador": "Ana Pereira",
                "localizacaoVaga": "Rio de Janeiro",
                "nomeParticipanteRH": "Marcos Pereira",
                "nomeProcessoSeletivo": "Marketing Digital",
                "nrCandidatosInscritos": 1,
                "nrContratacoes": 1,
                "nrEntrevistas": 1,
                "nrPosicoesAbertas": 1,
                "nrPosicoesVaga": 1,
                "requisitosVaga": "SEO, Google Ads",
                "responsavelVaga": "Rafael Nunes",
                "resultado": "Aprovado",
                "salarioInicial": 4500.00,
                "salarioInicialMedio": 4500.00,
                "statusProcessoSeletivo": "Aberto",
                "statusVaga": "Fechado",
                "tempoMedioProcesso": 1,
                "tipo": "Entrevista",
                "tipoContrato": 2,
                "tituloVaga": "Analista Marketing",
                "tipoAcaoSeletiva": "Entrevista"
              },
              {
                "cargoParticipanteRH": "Especialista de RH",
                "criadorProcessoSeletivo": "Eduardo Tavares",
                "descricaoFeedback": "Excelente liderança",
                "descricaoProcessoSeletivo": "Seleção para gerente de projetos em TI",
                "dtAberturaVaga": "2024-09-01",
                "dtAceiteOferta": "2024-09-22",
                "dtContratacao": "2024-09-25",
                "dtEntrevista": "2024-09-13",
                "dtFechamentoVaga": "2024-10-01",
                "dtFimProcessoSeletivo": "2024-09-28",
                "dtAcaoSeletiva": "2024-09-15",
                "dtInicioProcessoSeletivo": "2024-09-02",
                "entrevistador": "José Costa",
                "localizacaoVaga": "Belo Horizonte",
                "nomeParticipanteRH": "Camila Rodrigues",
                "nomeProcessoSeletivo": "Gerente de Projetos",
                "nrCandidatosInscritos": 1,
                "nrContratacoes": 1,
                "nrEntrevistas": 1,
                "nrPosicoesAbertas": 1,
                "nrPosicoesVaga": 1,
                "requisitosVaga": "Gestão de Equipes",
                "responsavelVaga": "Paulo Andrade",
                "resultado": "Aprovado",
                "salarioInicial": 6500.00,
                "salarioInicialMedio": 7250.00,
                "statusProcessoSeletivo": "Em andamento",
                "statusVaga": "Aberto",
                "tempoMedioProcesso": 1,
                "tipo": "Entrevista",
                "tipoContrato": 1,
                "tituloVaga": "Gerente de Projetos",
                "tipoAcaoSeletiva": "Entrevista"
              },
              {
                "cargoParticipanteRH": "Especialista de RH",
                "criadorProcessoSeletivo": "Eduardo Tavares",
                "descricaoFeedback": "Excelente didatica",
                "descricaoProcessoSeletivo": "Seleção para gerente de projetos em TI",
                "dtAberturaVaga": "2024-09-01",
                "dtAceiteOferta": "2024-09-22",
                "dtContratacao": "2024-09-18",
                "dtEntrevista": "2024-09-13",
                "dtFechamentoVaga": "2024-10-01",
                "dtFimProcessoSeletivo": "2024-09-28",
                "dtAcaoSeletiva": "2024-09-11",
                "dtInicioProcessoSeletivo": "2024-09-02",
                "entrevistador": "José Costa",
                "localizacaoVaga": "São Paulo",
                "nomeParticipanteRH": "Camila Rodrigues",
                "nomeProcessoSeletivo": "Gerente de Projetos",
                "nrCandidatosInscritos": 1,
                "nrContratacoes": 1,
                "nrEntrevistas": 1,
                "nrPosicoesAbertas": 1,
                "nrPosicoesVaga": 1,
                "requisitosVaga": "Gestão de Equipes",
                "responsavelVaga": "Paulo Andrade",
                "resultado": "Aprovado",
                "salarioInicial": 8000.00,
                "salarioInicialMedio": 7250.00,
                "statusProcessoSeletivo": "Em andamento",
                "statusVaga": "Aberto",
                "tempoMedioProcesso": 1,
                "tipo": "Entrevista",
                "tipoContrato": 1,
                "tituloVaga": "Gerente de Projetos",
                "tipoAcaoSeletiva": "Entrevista"
              }
            ]
            ```
                        """;


    public void executar(){
        //InputStream inputStream = getClass().getClassLoader().getResourceAsStream("excel_formatado.xlsx");
        //String documentoFormatado = documentoRepository.extrair(inputStream);
        //String mapeamento = analiseRepository.processarDados(documentoFormatado);
        mapeamentoRepository.popularEntidades(mapeamento);
    }
}
