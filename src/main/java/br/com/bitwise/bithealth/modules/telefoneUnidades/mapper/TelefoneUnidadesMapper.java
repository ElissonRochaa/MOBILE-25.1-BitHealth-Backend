package br.com.bitwise.bithealth.modules.telefoneUnidades.mapper;

import br.com.bitwise.bithealth.modules.telefoneUnidades.dto.TelefoneUnidadesRequestDTO;
import br.com.bitwise.bithealth.modules.telefoneUnidades.dto.TelefoneUnidadesResponseDTO;
import br.com.bitwise.bithealth.modules.telefoneUnidades.model.TelefoneUnidades;
import br.com.bitwise.bithealth.modules.unidade_saude.model.UnidadeSaude;
import org.springframework.stereotype.Component;

@Component
public class TelefoneUnidadesMapper {

    public TelefoneUnidades toEntity(TelefoneUnidadesRequestDTO telefoneUnidadesRequestDTO, UnidadeSaude unidadeSaude) {
        return new TelefoneUnidades(
                unidadeSaude,
                telefoneUnidadesRequestDTO.numero(),
                telefoneUnidadesRequestDTO.tipo()
        );
    }

    public TelefoneUnidadesResponseDTO toDto(TelefoneUnidades telefoneUnidades, String tokenId) {
        return new TelefoneUnidadesResponseDTO(
                tokenId,
                telefoneUnidades.getNumero(),
                telefoneUnidades.getTipo(),
                telefoneUnidades.getAtivo()
        );
    }
}
