package br.gov.sp.cps.api.pixel.outbound.llm.config;

import br.gov.sp.cps.api.pixel.core.domain.dto.MapeamentoDTO;
import br.gov.sp.cps.api.pixel.core.domain.repository.FormatadorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class FormatadorRepositoryImpl implements FormatadorRepository {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public MapeamentoDTO executar(String json) {
        int startIndex = json.indexOf("{");
        int endIndex = json.lastIndexOf("}");
        String response = "";

        if (startIndex != -1 && endIndex != -1) {
            response = json.substring(startIndex, endIndex + 1);
        }
        MapeamentoDTO mapeamentoDTO = new MapeamentoDTO();
        try {
            mapeamentoDTO = objectMapper.readValue(response, MapeamentoDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mapeamentoDTO;
    }

    @Override
    public List<String> extrairJsonObjects(String input) {
        List<String> jsonObjects = new ArrayList<>();
        int depth = 0; // Nível de profundidade de chaves
        StringBuilder currentJson = new StringBuilder(); // Armazena o JSON atual

        boolean insideJson = false;

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);

            if (currentChar == '{') {
                depth++;
                insideJson = true;
            }

            if (insideJson) {
                currentJson.append(currentChar);
            }

            if (currentChar == '}') {
                depth--;
                if (depth == 0) {
                    insideJson = false;
                    jsonObjects.add(currentJson.toString().trim());
                    currentJson.setLength(0); // Limpa o `StringBuilder` para o próximo JSON
                }
            }
        }

        return jsonObjects;
    }
}
