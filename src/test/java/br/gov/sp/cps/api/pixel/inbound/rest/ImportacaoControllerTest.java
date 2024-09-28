package br.gov.sp.cps.api.pixel.inbound.rest;

import br.gov.sp.cps.api.pixel.core.usecase.AnalisarDocumentoUC;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ImportacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AnalisarDocumentoUC analisarDocumentoUC;


    @Test
    public void devImportacaoController()throws Exception{
        mockMvc.perform(get("/importacao")) // Realiza a requisição GET
                .andExpect(status().isOk()); // Verifica se a resposta está OK

        verify(analisarDocumentoUC, times(1)).executar();


    }
}