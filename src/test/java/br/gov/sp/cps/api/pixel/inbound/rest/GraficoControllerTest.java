package br.gov.sp.cps.api.pixel.inbound.rest;

import br.gov.sp.cps.api.pixel.core.domain.dto.Eixo;
import br.gov.sp.cps.api.pixel.core.domain.dto.Filtro;
import br.gov.sp.cps.api.pixel.core.domain.dto.command.VisualizarGraficoCommand;
import br.gov.sp.cps.api.pixel.core.usecase.VisualizarGraficoUC;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CardController.class)
class GraficoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VisualizarGraficoUC visualizarGraficoUC;

    private VisualizarGraficoCommand command;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Criação dos objetos Eixo
        Eixo eixoX = new Eixo();
        eixoX.setNome("Eixo X Teste");
        eixoX.setCampo("campoX");

        Eixo eixoY = new Eixo();
        eixoY.setNome("Eixo Y Teste");
        eixoY.setCampo("campoY");

        // Criação de filtros
        Filtro filtro1 = new Filtro();
        filtro1.setNome("Filtro Categoria");
        filtro1.setCampo("categoria");
        filtro1.setComparador("igual a");
        filtro1.setValor("financeiro");

        List<Filtro> filtros = new ArrayList<>();
        filtros.add(filtro1);

        // Criação do comando
        command = new VisualizarGraficoCommand();
        command.setEixoX(eixoX);
        command.setEixoY(eixoY);
        command.setFiltros(filtros);
    }

    @Test
    public void devGraficoController() throws Exception {
        // Arrange

        when(visualizarGraficoUC.executar(command)).thenReturn(List.of());

        ObjectMapper objectMapper = new ObjectMapper();
        String commandJson = objectMapper.writeValueAsString(command);

        // Act & Assert
        mockMvc.perform(post("/visualizacao/grafico")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(commandJson))
                .andExpect(status().isOk());

        verify(visualizarGraficoUC, times(1)).executar(command);
    }
}