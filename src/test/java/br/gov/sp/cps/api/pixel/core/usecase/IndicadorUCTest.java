package br.gov.sp.cps.api.pixel.core.usecase;

import br.gov.sp.cps.api.pixel.core.domain.dto.command.IndicadorCommand;
import br.gov.sp.cps.api.pixel.core.domain.entity.Indicador;
import br.gov.sp.cps.api.pixel.core.domain.repository.IndicadorRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class IndicadorUCTest {

    @Mock
    private IndicadorRepository indicadorRepository;

    @InjectMocks
    private IndicadorUC indicadorUC;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
    }

    @Test
    void postarr() throws JsonProcessingException {
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

        IndicadorCommand indicadorCommand = objectMapper.readValue(json, IndicadorCommand.class);
        Indicador indicadorRetornado = new Indicador();
        when(indicadorRepository.salvar(any(Indicador.class))).thenReturn(indicadorRetornado);
        Indicador resultado = indicadorUC.postarr(indicadorCommand);
        assertEquals(indicadorRetornado, resultado);
        verify(indicadorRepository).salvar(any(Indicador.class));
    }
}