package br.gov.sp.cps.api.pixel.core.usecase;

import br.gov.sp.cps.api.pixel.core.domain.repository.AnaliseRepository;
import br.gov.sp.cps.api.pixel.core.domain.repository.DocumentoRepository;
import br.gov.sp.cps.api.pixel.core.domain.repository.MapeamentoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.InputStream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AnalisarDocumentoUCTest {

    @Mock
    private AnaliseRepository analiseRepository;

    @Mock
    private DocumentoRepository documentoRepository;

    @Mock
    private MapeamentoRepository mapeamentoRepository;

    @InjectMocks
    private AnalisarDocumentoUC analisarDocumentoUC;

    public AnalisarDocumentoUCTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void devAnalisarDocumento() {
        when(documentoRepository.extrair(any())).thenReturn("documentoFormatado");
        when(analiseRepository.processarDados("documentoFormatado")).thenReturn("mapeamento");


        analisarDocumentoUC.executar();

        verify(documentoRepository, times(1)).extrair(any(InputStream.class));
        verify(analiseRepository, times(1)).processarDados("documentoFormatado");
        verify(mapeamentoRepository, times(1)).popularEntidades("mapeamento");

        // Verifica que nenhum outro método foi chamado inesperadamente
        verifyNoMoreInteractions(documentoRepository, analiseRepository, mapeamentoRepository);
    }
}