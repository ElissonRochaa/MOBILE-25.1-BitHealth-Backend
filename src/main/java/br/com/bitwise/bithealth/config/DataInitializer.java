package br.com.bitwise.bithealth.config;

import br.com.bitwise.bithealth.modules.doctors.model.Doctor;
import br.com.bitwise.bithealth.modules.doctors.repository.DoctorRepository;
import br.com.bitwise.bithealth.modules.medicamentos.model.ENUMS.TipoMedicamentoEnum;
import br.com.bitwise.bithealth.modules.medicamentos.model.Medicamento;
import br.com.bitwise.bithealth.modules.medicamentos.repository.MedicamentosRepository;
import br.com.bitwise.bithealth.modules.unidade_saude.endereco.model.EnderecoUnidades;
import br.com.bitwise.bithealth.modules.unidade_saude.endereco.repository.EnderecoUnidadesRepository;
import br.com.bitwise.bithealth.modules.unidade_saude.model.ENUMS.TipoUnidade;
import br.com.bitwise.bithealth.modules.unidade_saude.model.UnidadeSaude;
import br.com.bitwise.bithealth.modules.unidade_saude.repository.UnidadeSaudeRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final MedicamentosRepository medicamentoRepository;
    private final UnidadeSaudeRepository unidadeSaudeRepository;
    private final EnderecoUnidadesRepository enderecoUnidadesRepository;
    private final DoctorRepository doctorRepository;

    @PostConstruct
    public void init() {
        if (doctorRepository.count() > 0) {
            return;
        }
        if (medicamentoRepository.count() > 0) {
            return;
        }
        if (unidadeSaudeRepository.count() > 0) {
            return;
        }

        System.out.println("Populando dados iniciais de unidades de saúde");
        List<UnidadeSaude> unidades = populateUnidadeSaude();
        System.out.println("Dados de unidades de saúde populados com sucesso");
        System.out.println("Populando dados iniciais de medicamentos");
        populateMedicamentos(unidades);
        System.out.println("Dados de medicamentos populados com sucesso");
        System.out.println("Populando dados iniciais de médicos");
        populateDoctors(unidades);
        System.out.println("Dados de médicos populados com sucesso");

    }

    private void populateDoctors(List<UnidadeSaude> unidades) {
        Doctor medico1 = new Doctor(
                "Dr. José da Silva",
                "123456",
                "Cardiologia",
                unidades.get(0),
                "Segunda-feira",
                "08:00",
                "17:00",
                "Plantonista"
        );
        Doctor medico2 = new Doctor(
                "Dra. Maria Oliveira",
                "654321",
                "Pediatria",
                unidades.get(1),
                "Terça-feira",
                "08:00",
                "17:00",
                "Plantonista"
        );
        Doctor medico3 = new Doctor(
                "Dr. João Santos",
                "789012",
                "Dermatologia",
                unidades.get(2),
                "Quarta-feira",
                "08:00",
                "17:00",
                "Plantonista"
        );
        doctorRepository.save(medico1);
        doctorRepository.save(medico2);
        doctorRepository.save(medico3);
    }

    private List<UnidadeSaude> populateUnidadeSaude() {

        UnidadeSaude unidadeSaude1 = new UnidadeSaude(
                "Unidade de Saúde A",
                TipoUnidade.UBS,
                "08:00",
                "17:00"
        );
        UnidadeSaude unidadeSaude2 = new UnidadeSaude(
                "Unidade de Saúde B",
                TipoUnidade.HOSPITAL,
                "08:00",
                "17:00"
        );
        UnidadeSaude unidadeSaude3 = new UnidadeSaude(
                "Unidade de Saúde C",
                TipoUnidade.FARMA,
                "08:00",
                "17:00"
        );
        UnidadeSaude unidadeSaude4 = new UnidadeSaude(
                "Unidade de Saúde D",
                TipoUnidade.UPA,
                "08:00",
                "17:00"
        );

        unidadeSaude1 = unidadeSaudeRepository.save(unidadeSaude1);
        unidadeSaude2 = unidadeSaudeRepository.save(unidadeSaude2);
        unidadeSaude3 = unidadeSaudeRepository.save(unidadeSaude3);
        unidadeSaude4 = unidadeSaudeRepository.save(unidadeSaude4);

        EnderecoUnidades endereco1 = new EnderecoUnidades(
                "Rua José Francisco dos Santos",
                "30",
                "Próximo ao mercado missena",
                "Centro",
                "Correntes",
                "Pernambuco",
                "-9.126351",
                "-36.3382825",
                "55315-000",
                unidadeSaude1
        );
        EnderecoUnidades endereco2 = new EnderecoUnidades(
                "Rua Coronel Francisco Santos",
                "63",
                "Proxíma ao colegio normal municipal",
                "Centro",
                "Correntes",
                "Pernambuco",
                "-9.1279715",
                "-36.3280616",
                "55315-000",
                unidadeSaude2
        );
        EnderecoUnidades endereco3 = new EnderecoUnidades(
                "Rua José Francisco dos Santos",
                "30",
                "Próximo ao mercado missena",
                "Centro",
                "Correntes",
                "Pernambuco",
                "-9.126351",
                "-36.3382825",
                "55315-000",
                unidadeSaude3
        );
        EnderecoUnidades endereco4 = new EnderecoUnidades(
                "Rua Coronel Francisco Santos",
                "63",
                "Proxíma ao colegio normal municipal",
                "Centro",
                "Correntes",
                "Pernambuco",
                "-9.1279715",
                "-36.3280616",
                "55315-000",
                unidadeSaude4
        );
        enderecoUnidadesRepository.save(endereco1);
        enderecoUnidadesRepository.save(endereco2);
        enderecoUnidadesRepository.save(endereco3);
        enderecoUnidadesRepository.save(endereco4);

        return List.of(unidadeSaude1, unidadeSaude2, unidadeSaude3, unidadeSaude4);
    }

    private void populateMedicamentos(List<UnidadeSaude> unidades) {
        String[] medicamentos = {
                "Paracetamol",
                "Ibuprofeno",
                "Amoxicilina",
                "Cetirizina"
        };
        String[] tipos = {
                "ORIGINAL",
                "GENERICO"
        };

        Medicamento medicamento1 = new Medicamento(
                medicamentos[0],
                "Analgésico e antipirético",
                100,
                TipoMedicamentoEnum.fromString(tipos[0]),
                unidades.get(0)
        );
        Medicamento medicamento2 = new Medicamento(
                medicamentos[1],
                "Anti-inflamatório",
                50,
                TipoMedicamentoEnum.fromString(tipos[1]),
                unidades.get(1)
        );
        Medicamento medicamento3 = new Medicamento(
                medicamentos[2],
                "Antibiótico",
                30,
                TipoMedicamentoEnum.fromString(tipos[0]),
                unidades.get(2)
        );
        Medicamento medicamento4 = new Medicamento(
                medicamentos[3],
                "Antialérgico",
                20,
                TipoMedicamentoEnum.fromString(tipos[1]),
                unidades.get(3)
        );

        medicamentoRepository.save(medicamento1);
        medicamentoRepository.save(medicamento2);
        medicamentoRepository.save(medicamento3);
        medicamentoRepository.save(medicamento4);
    }
}
