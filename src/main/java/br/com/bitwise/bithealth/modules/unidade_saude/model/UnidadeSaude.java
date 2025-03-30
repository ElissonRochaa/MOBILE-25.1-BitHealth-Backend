package br.com.bitwise.bithealth.modules.unidade_saude.model;

import br.com.bitwise.bithealth.modules.unidade_saude.model.ENUMS.TipoUnidade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "unidades_saude")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UnidadeSaude {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "tipo", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private TipoUnidade tipoUnidade;

    @Column(name = "horario_inicio_atendimento", length = 5, nullable = false)
    private String horarioInicioAtendimento;

    @Column(name = "horario_fim_atendimento", length = 5, nullable = false)
    private String horarioFimAtendimento;

    @Column(name = "criado_em", nullable = false)
    private LocalDateTime criadoEm;

    @PrePersist
    protected void prePersist() {
        criadoEm = LocalDateTime.now();
    }

    public UnidadeSaude(String nome, TipoUnidade tipoUnidade, String horarioInicioAtendimento, String horarioFimAtendimento) {
        this.nome = nome;
        this.tipoUnidade = tipoUnidade;
        this.horarioInicioAtendimento = horarioInicioAtendimento;
        this.horarioFimAtendimento = horarioFimAtendimento;
    }
}
