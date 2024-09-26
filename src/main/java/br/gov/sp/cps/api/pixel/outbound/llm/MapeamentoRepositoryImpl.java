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
                    .user(u -> u.text("Converta o json a seguir {json} para a entidade: {entidade}")
                            .params(params))
                    .call()
                    .entity(entidadeClass);

            entidades.add(entidade);
        }

        return entidades;
    }
}
