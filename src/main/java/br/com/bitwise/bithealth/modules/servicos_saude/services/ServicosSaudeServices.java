package br.com.bitwise.bithealth.modules.servicos_saude.services;

import br.com.bitwise.bithealth.modules.servicos_saude.dto.ServicosSaudeRequest;
import br.com.bitwise.bithealth.modules.servicos_saude.dto.ServicosSaudeResponse;

import java.util.List;

public interface ServicosSaudeServices {

    ServicosSaudeResponse createServicosSaude(ServicosSaudeRequest servicosSaudeRequest);
    List<ServicosSaudeResponse> getAllServicosSaude();
    void deleteServicosSaude(String tokenId);


}

