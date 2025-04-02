package br.com.bitwise.bithealth.modules.doctors.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import br.com.bitwise.bithealth.modules.unidade_saude.model.UnidadeSaude;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "medicos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome", nullable = false, length = 150)
    private String nome;

    @Column(name = "crm", nullable = false, unique = true, length = 20)
    private String crm;

    @Column(name = "especialidade", nullable = false, length = 100)
    private String especialidade;

    @ManyToOne
    @JoinColumn(name = "unidade_saude_id", nullable = false)
    private UnidadeSaude unidadeSaude;

    @Column(name = "data_plantao", nullable = false)
    private String dataPlantao;

    @Column(name = "horario_inicio", nullable = false)
    private String horarioInicio;

    @Column(name = "horario_fim", nullable = false)
    private String horarioFim;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "criado_em", nullable = false)
    private LocalDateTime criadoEm;

    @PrePersist
    protected void prePersist() {
        criadoEm = LocalDateTime.now();
    }

    public Doctor(String nome, String crm, String especialidade, UnidadeSaude unidadeSaude,
                  String dataPlantao, String horarioInicio, String horarioFim, String tipo) {
        this.nome = nome;
        this.crm = crm;
        this.especialidade = especialidade;
        this.unidadeSaude = unidadeSaude;
        this.dataPlantao = dataPlantao;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.tipo = tipo;
    }
}
