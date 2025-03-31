package br.com.bitwise.bithealth.modules.servicos_saude.services.mapper;

import br.com.bitwise.bithealth.modules.servicos_saude.dto.ServicosSaudeRequest;
import br.com.bitwise.bithealth.modules.servicos_saude.dto.ServicosSaudeResponse;
import br.com.bitwise.bithealth.modules.servicos_saude.model.ServicosSaude;

public interface MapperServicosSaude {

    ServicosSaude requestToModel(ServicosSaudeRequest servicosSaudeRequest);

    ServicosSaudeResponse modelToResponse(ServicosSaude servicosSaudeSalvo, String tokenId);
}
