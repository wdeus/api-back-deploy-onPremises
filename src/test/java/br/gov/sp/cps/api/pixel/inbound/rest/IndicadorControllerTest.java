package br.gov.sp.cps.api.pixel.inbound.rest;

import br.gov.sp.cps.api.pixel.core.domain.dto.command.IndicadorCommand;
import br.gov.sp.cps.api.pixel.core.domain.entity.Indicador;
import br.gov.sp.cps.api.pixel.core.usecase.IndicadorUC;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(IndicadorController.class)
class IndicadorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IndicadorUC indicadorUC;

    @Test
    void criarIndicador() throws Exception{
        String json = "{"
                + "\"indicador\": {"
                + "\"nome\": \"Indicador B\","
                + "\"campo\": \"campoB\","
                + "\"comparador\": \"<\","
                + "\"valor\": \"101\""
                + "},"
                + "\"filtro\": {"
                + "\"nome\": \"Filtro B\","
                + "\"campo\": \"campoFiltroB\","
                + "\"comparador\": \"+\","
                + "\"valor\": \"201\""
                + "},"
                + "\"usuario\": \"usuario2\""
                + "}";

        Indicador indicadorRetornado = new Indicador();
        when(indicadorUC.postarr(any(IndicadorCommand.class))).thenReturn(indicadorRetornado);
        mockMvc.perform(post("/indicadores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

        verify(indicadorUC).postarr(any(IndicadorCommand.class));

    }
}