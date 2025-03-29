package br.com.bitwise.bithealth.modules.servicos_saude.services;

import br.com.bitwise.bithealth.modules.servicos_saude.dto.ServicosSaudeRequest;
import br.com.bitwise.bithealth.modules.servicos_saude.dto.ServicosSaudeResponse;
import br.com.bitwise.bithealth.modules.servicos_saude.model.ServicosSaude;
import br.com.bitwise.bithealth.modules.servicos_saude.repository.ServicosSaudeRepository;
import br.com.bitwise.bithealth.modules.servicos_saude.services.mapper.MapperServicosSaude;
import br.com.bitwise.bithealth.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServicosSaudeServices {

    private final ServicosSaudeRepository servicosSaudeRepository;
    private final MapperServicosSaude mapperServicosSaude;
    private final TokenService tokenService;

    public ServicosSaudeResponse criarServicoSaude(ServicosSaudeRequest servicosSaudeRequest) {
        ServicosSaude servicosSaude = mapperServicosSaude.requestToModel(servicosSaudeRequest);
        ServicosSaude servicosSaudeSalvo = servicosSaudeRepository.save(servicosSaude);

        String tokenId = tokenService.generateTokenId(String.valueOf(servicosSaudeSalvo.getId()));

        return mapperServicosSaude.modelToResponse(servicosSaudeSalvo, tokenId);
    }
}
