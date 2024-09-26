package br.gov.sp.cps.api.pixel.outbound.llm;

import br.gov.sp.cps.api.pixel.core.domain.repository.AnaliseRepository;
import br.gov.sp.cps.api.pixel.core.domain.repository.FormatadorRepository;
import br.gov.sp.cps.api.pixel.outbound.llm.config.LlmConfig;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AnaliseRepositoryImpl implements AnaliseRepository {

    private final LlmConfig llmConfig;

    private final FormatadorRepository formatadorRepository;

    @Override
    @SneakyThrows
    public String processarDados(String documento) {
        var chatModel = llmConfig.executar();
        Map<String, Object> params = new HashMap<>();
        params.put("document", documento);
        params.put("format", carregarPrompt());
        String mapeamentoDTO = ChatClient.create(chatModel).prompt()
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
                .content();
        return mapeamentoDTO;
    }

    public String carregarPrompt() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("prompt/prompt.json");
        return new String(inputStream.readAllBytes());
    }
}
