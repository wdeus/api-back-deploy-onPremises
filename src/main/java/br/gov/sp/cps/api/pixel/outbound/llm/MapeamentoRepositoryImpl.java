package br.gov.sp.cps.api.pixel.outbound.llm;

import br.gov.sp.cps.api.pixel.core.domain.entity.*;
import br.gov.sp.cps.api.pixel.core.domain.repository.*;
import br.gov.sp.cps.api.pixel.outbound.llm.config.LlmConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class MapeamentoRepositoryImpl implements MapeamentoRepository {

    private final LlmConfig llmConfig;
    private final FormatadorRepository formatadorRepository;
    private final AcaoSeletivaRepository acaoSeletivaRepository;
    private final ContratacaoRepository contratacaoRepository;
    private final EntrevistaRepository entrevistaRepository;
    private final FatoEntrevistaRepository fatoEntrevistaRepository;
    private final FatoVagaRepository fatoVagaRepository;
    private final FeedbackRepository feedbackRepository;
    private final ParticipanteRHRepository participanteRHRepository;
    private final PeriodoRepository periodoRepository;
    private final ProcessoSeletivoRepository processoSeletivoRepository;
    private final VagaRepository vagaRepository;

    @Override
    public void popularEntidades(String mapeamentoDTO) {
        var chatModel = llmConfig.executar();
        List<String> jsons = formatadorRepository.extrairJsonObjects(mapeamentoDTO);

        List<AcaoSeletiva> acoesSeletivas = converterJsonParaEntidades(jsons, AcaoSeletiva.class, chatModel);
        acaoSeletivaRepository.salvar(acoesSeletivas);

        List<Contratacao> contratacoes = converterJsonParaEntidades(jsons, Contratacao.class, chatModel);
        contratacaoRepository.salvar(contratacoes);

        List<Entrevista> entrevistas = converterJsonParaEntidades(jsons, Entrevista.class, chatModel);
        entrevistaRepository.salvar(entrevistas);

        List<FatoEntrevista> fatoEntrevistas = converterJsonParaEntidades(jsons, FatoEntrevista.class, chatModel);

        for (FatoEntrevista fatoEntrevista : fatoEntrevistas) {
            if (entrevistaRepository.existePorId(fatoEntrevista.getEntrevista().getIdEntrevista()) &&
                    vagaRepository.existePorId(fatoEntrevista.getVaga().getIdVaga()) &&
                    contratacaoRepository.existePorId(fatoEntrevista.getContratacao().getIdContratacao())) {
                if ((fatoEntrevista.getFeedback() == null || feedbackRepository.existePorId((fatoEntrevista.getFeedback().getIdFeedback())) &&
                        (fatoEntrevista.getAcaoSeletiva() == null || acaoSeletivaRepository.existePorId(fatoEntrevista.getAcaoSeletiva().getIdAcao())) &&
                        (fatoEntrevista.getParticipanteRh() == null || participanteRHRepository.existePorId(fatoEntrevista.getParticipanteRh().getIdParticipanteRh())))){
                    fatoEntrevistaRepository.salvar(fatoEntrevistas);
                }
            }
        }

        List<FatoVaga> fatoVagas = converterJsonParaEntidades(jsons, FatoVaga.class, chatModel);

        for (FatoVaga fatoVaga : fatoVagas) {
            if (vagaRepository.existePorId(fatoVaga.getVaga().getIdVaga()) &&
                    processoSeletivoRepository.existePorId(fatoVaga.getProcessoSeletivo().getIdProcessoSeletivo()) &&
                    periodoRepository.existePorId(fatoVaga.getPeriodo().getIdPeriodo())) {
                fatoVagaRepository.salvar(fatoVagas);
            }
        }

        List<Feedback> feedbacks = converterJsonParaEntidades(jsons, Feedback.class, chatModel);
        feedbackRepository.salvar(feedbacks);

        List<ParticipanteRH> participantesRH = converterJsonParaEntidades(jsons, ParticipanteRH.class, chatModel);
        participanteRHRepository.salvar(participantesRH);

        List<Periodo> periodos = converterJsonParaEntidades(jsons, Periodo.class, chatModel);
        periodoRepository.salvar(periodos);

        List<ProcessoSeletivo> processos = converterJsonParaEntidades(jsons, ProcessoSeletivo.class, chatModel);
        processoSeletivoRepository.salvar(processos);

        List<Vaga> vagas = converterJsonParaEntidades(jsons, Vaga.class, chatModel);
        vagaRepository.salvar(vagas);
    }

    public <T> List<T> converterJsonParaEntidades(List<String> jsons, Class<T> entidadeClass, OpenAiChatModel chatModel) {
        List<T> entidades = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();

        for (String json : jsons) {
            params.put("json", json);
            params.put("entidade", entidadeClass);

            T entidade = ChatClient.create(chatModel).prompt()
                    .user(u -> u.text("\"Retorne apenas a estrutura JSON no formato: {format} de acordo com os dados fornecidos: {document}. \"\n" +
                                    "    + \"Você deve retornar um único item, observando os campos que são listas []. Para os campos que não são listas, \"\n" +
                                    "    + \"retorne apenas valores numéricos inteiros. Siga as seguintes regras:\\n\"\n" +
                                    "    + \"no campo **nrCandidatosInscritos**,retornar separadamente a quantidade de candidatos, retorne através de um \"\n" +
                                    "    + \"arrey de inteiros. No campo **nrContratacoes**, analise o resultado das entrevistas e retorne separadamente a \"\n" +
                                    "    + \"quantidade sendo 1 para resultado 'aprovado', 0 para 'reprovado' e retorne um array de inteiros.\\n\"\n" +
                                    "    + \"No campo **nrEntrevistas**, retornar separadamente a quantidade de entrevistas feitas, retornar através de um \"\n" +
                                    "    + \"arrey de inteiros.\\n\"\n" +
                                    "    + \"No campo **nrPosicoesAbertas**, considere o Nome do Processo Seletivo e o status do processo. Retorne '1' para \"\n" +
                                    "    + \"processos abertos e '0' para processos fechados, retornando os valores em um array de inteiros.\\n\"\n" +
                                    "    + \"No campo **nrPosicoesVagas**, retornar separadamente a quantidade de Responsável Vaga, retorne através de um \"\n" +
                                    "    + \"arrey de inteiros.\\n\"\n" +
                                    "    + \"No campo **tempoMedioProcesso**, calcule o tempo médio de duração de cada processo, subtraindo a Data de Início \"\n" +
                                    "    + \"do Processo da Data de Fim do Processo. Retorne os valores em um array de inteiros, representando o tempo médio \"\n" +
                                    "    + \"em dias para cada processo.\"")
                            .params(params))
                    .call()
                    .entity(entidadeClass);

            entidades.add(entidade);
        }

        return entidades;
    }
}
