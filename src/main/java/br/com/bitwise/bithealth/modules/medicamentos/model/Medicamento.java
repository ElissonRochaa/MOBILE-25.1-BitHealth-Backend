package br.com.bitwise.bithealth.modules.medicamentos.model;

import br.com.bitwise.bithealth.modules.medicamentos.model.ENUMS.TipoMedicamentoEnum;
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
@Table(name = "medicamentos")
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "quantidade")
    private Integer quantidade;

    @Column(name = "tipo_medicamento")
    @Enumerated(EnumType.STRING)
    private TipoMedicamentoEnum tipoMedicamento;

    @ManyToOne
    @JoinColumn(
            name = "unidade_saude_id",
            referencedColumnName = "id",
            nullable = false
    )
    private UnidadeSaude unidadeSaude;

    public Medicamento(String nome, String descricao, Integer quantidade, TipoMedicamentoEnum tipoMedicamento, UnidadeSaude unidadeSaude) {
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.tipoMedicamento = tipoMedicamento;
        this.unidadeSaude = unidadeSaude;
    }
}
