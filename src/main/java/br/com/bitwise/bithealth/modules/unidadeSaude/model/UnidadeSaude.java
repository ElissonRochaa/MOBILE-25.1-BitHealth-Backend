package br.com.bitwise.bithealth.modules.unidadeSaude.model;

import br.com.bitwise.bithealth.modules.unidadeSaude.model.ENUMS.TipoUnidade;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "unidades_saude")
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

    public UnidadeSaude() {
    }

    public UnidadeSaude(String nome, TipoUnidade tipoUnidade, String horarioInicioAtendimento, String horarioFimAtendimento) {
        this.nome = nome;
        this.tipoUnidade = tipoUnidade;
        this.horarioInicioAtendimento = horarioInicioAtendimento;
        this.horarioFimAtendimento = horarioFimAtendimento;
    }

    public UnidadeSaude(UUID id, String nome, TipoUnidade tipoUnidade, String horarioInicioAtendimento, String horarioFimAtendimento) {
        this.id = id;
        this.nome = nome;
        this.tipoUnidade = tipoUnidade;
        this.horarioInicioAtendimento = horarioInicioAtendimento;
        this.horarioFimAtendimento = horarioFimAtendimento;
    }

    public UnidadeSaude(UUID id, String nome, TipoUnidade tipoUnidade, String horarioInicioAtendimento, String horarioFimAtendimento, LocalDateTime criadoEm) {
        this.id = id;
        this.nome = nome;
        this.tipoUnidade = tipoUnidade;
        this.horarioInicioAtendimento = horarioInicioAtendimento;
        this.horarioFimAtendimento = horarioFimAtendimento;
        this.criadoEm = criadoEm;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoUnidade getTipoUnidade() {
        return tipoUnidade;
    }

    public void setTipoUnidade(TipoUnidade tipoUnidade) {
        this.tipoUnidade = tipoUnidade;
    }

    public String getHorarioInicioAtendimento() {
        return horarioInicioAtendimento;
    }

    public void setHorarioInicioAtendimento(String horarioInicioAtendimento) {
        this.horarioInicioAtendimento = horarioInicioAtendimento;
    }

    public String getHorarioFimAtendimento() {
        return horarioFimAtendimento;
    }

    public void setHorarioFimAtendimento(String horarioFimAtendimento) {
        this.horarioFimAtendimento = horarioFimAtendimento;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }
}
