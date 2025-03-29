package br.com.bitwise.bithealth.modules.servicos_saude.model;

import br.com.bitwise.bithealth.modules.unidade_saude.model.UnidadeSaude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "servicos_saude")
public class ServicosSaude {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "horario_inicio")
    private String horarioInicio;

    @Column(name = "horario_fim")
    private String horarioFim;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "unidade_saude_id",
            referencedColumnName = "id",
            nullable = false
    )
    private UnidadeSaude unidadeSaude;

    public ServicosSaude(String nome, String descricao, String horarioInicio, String horarioFim, UnidadeSaude unidadeSaude) {
        this.nome = nome;
        this.descricao = descricao;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.unidadeSaude = unidadeSaude;
    }
}
