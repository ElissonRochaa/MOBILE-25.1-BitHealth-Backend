package br.com.bitwise.bithealth.modules.telefoneUnidades.model;

import br.com.bitwise.bithealth.modules.telefoneUnidades.model.ENUM.TipoTelefone;
import br.com.bitwise.bithealth.modules.unidade_saude.model.UnidadeSaude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "telefones_unidades")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TelefoneUnidades {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "unidade_id", nullable = false)
    private UnidadeSaude unidadeSaude;

    @Column(nullable = false, length = 20)
    private String numero;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TipoTelefone tipo;

    @Column(nullable = false)
    private Boolean ativo = true;

    public TelefoneUnidades(UnidadeSaude unidadeSaude, String numero, TipoTelefone tipo) {
        this.unidadeSaude = unidadeSaude;
        this.numero = numero;
        this.tipo = tipo;
    }

}