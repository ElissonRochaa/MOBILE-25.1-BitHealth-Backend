package br.com.bitwise.bithealth.modules.calendario_vacinacao.model;

import br.com.bitwise.bithealth.modules.calendario_vacinacao.model.ENUMS.StatusVacinacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "calendario_vacinacao")
public class CalendarioVacinacao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "vacina")
    private String vacina;

    @Column(name = "idade_minima")
    private Integer idadeMinima;

    @Column(name = "idade_maxima")
    private Integer idadeMaxima;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "data_inicio")
    private String dataInicio;

    @Column(name = "data_fim")
    private String dataFim;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusVacinacao statusVacinacao;

    public CalendarioVacinacao(String vacina, Integer idadeMinima, Integer idadeMaxima, String descricao, String dataInicio, String dataFim, StatusVacinacao statusVacinacao) {
        this.vacina = vacina;
        this.idadeMinima = idadeMinima;
        this.idadeMaxima = idadeMaxima;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.statusVacinacao = statusVacinacao;
    }
}
