package br.com.bitwise.bithealth.modules.user.endereco.repository;

import br.com.bitwise.bithealth.modules.user.endereco.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EnderecoRepository extends JpaRepository<Endereco, UUID> {
}

