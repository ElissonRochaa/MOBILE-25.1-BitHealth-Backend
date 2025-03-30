package br.com.bitwise.bithealth.modules.unidade_saude.endereco.model;

import br.com.bitwise.bithealth.modules.unidade_saude.model.UnidadeSaude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "EnderecoUnidades")
@ToString
@Table(name = "enderecos_unidades")
public class EnderecoUnidades {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(
            name = "unidade_id",
            referencedColumnName = "id",
            nullable = false
    )
    private UnidadeSaude unidadeSaude;

    @Column(name = "logradouro", nullable = false)
    private String logradouro;

    @Column(name = "numero", nullable = false)
    private String numero;

    @Column(name = "complemento")
    private String complemento;

    @Column(name = "bairro", nullable = false)
    private String bairro;

    @Column(name = "cidade", nullable = false)
    private String cidade;

    @Column(name = "estado", nullable = false)
    private String estado;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "cep", nullable = false)
    private String cep;

    public EnderecoUnidades(String logradouro, String numero, String complemento, String bairro,
                           String cidade, String estado, String latitude, String longitude,
                           String cep, UnidadeSaude unidadeSaude) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.latitude = latitude;
        this.longitude = longitude;
        this.cep = cep;
        this.unidadeSaude = unidadeSaude;
    }
}
