package br.com.bitwise.bithealth.modules.vacinas.model;


import br.com.bitwise.bithealth.modules.vacinas.model.Enums.Doses;
import br.com.bitwise.bithealth.modules.vacinas.model.Enums.FaixaEtaria;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vacinas")
public class Vacinas {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "vacina")
    private String vacina;

    @Column(name = "idade")
    private String idade;

    @Enumerated(EnumType.STRING)
    @Column(name = "doses")
    private Doses doses;

    @Column(name = "doencas_evitadas")
    private String doencasEvitadas;

    @Enumerated(EnumType.STRING)
    @Column(name = "faixa_etaria")
    private FaixaEtaria faixaEtaria;

    public Vacinas(String vacina, String idade, Doses doses, String doencasEvitadas, FaixaEtaria faixaEtaria) {
        this.vacina = vacina;
        this.idade = idade;
        this.doses = doses;
        this.doencasEvitadas = doencasEvitadas;
        this.faixaEtaria = faixaEtaria;
    }


}
