package br.com.bitwise.bithealth.config;

import br.com.bitwise.bithealth.modules.calendario_vacinacao.model.CalendarioVacinacao;
import br.com.bitwise.bithealth.modules.calendario_vacinacao.model.ENUMS.StatusVacinacao;
import br.com.bitwise.bithealth.modules.calendario_vacinacao.repository.CalendarioVacinacaoRepository;
import br.com.bitwise.bithealth.modules.doctors.model.Doctor;
import br.com.bitwise.bithealth.modules.doctors.repository.DoctorRepository;
import br.com.bitwise.bithealth.modules.medicamentos.model.ENUMS.TipoMedicamentoEnum;
import br.com.bitwise.bithealth.modules.medicamentos.model.Medicamento;
import br.com.bitwise.bithealth.modules.medicamentos.repository.MedicamentosRepository;
import br.com.bitwise.bithealth.modules.news.model.News;
import br.com.bitwise.bithealth.modules.news.repository.NewsRepository;
import br.com.bitwise.bithealth.modules.servicos_saude.model.ServicosSaude;
import br.com.bitwise.bithealth.modules.servicos_saude.repository.ServicosSaudeRepository;
import br.com.bitwise.bithealth.modules.unidade_saude.endereco.model.EnderecoUnidades;
import br.com.bitwise.bithealth.modules.unidade_saude.endereco.repository.EnderecoUnidadesRepository;
import br.com.bitwise.bithealth.modules.unidade_saude.model.ENUMS.TipoUnidade;
import br.com.bitwise.bithealth.modules.unidade_saude.model.UnidadeSaude;
import br.com.bitwise.bithealth.modules.unidade_saude.repository.UnidadeSaudeRepository;
import br.com.bitwise.bithealth.modules.user.model.ENUM.TipoUsuario;
import br.com.bitwise.bithealth.modules.user.model.Usuario;
import br.com.bitwise.bithealth.modules.user.repository.UsuarioRepository;
import br.com.bitwise.bithealth.modules.vacinas.model.Enums.Doses;
import br.com.bitwise.bithealth.modules.vacinas.model.Enums.FaixaEtaria;
import br.com.bitwise.bithealth.modules.vacinas.model.Vacinas;
import br.com.bitwise.bithealth.modules.vacinas.repository.VacinasRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final MedicamentosRepository medicamentoRepository;
    private final UnidadeSaudeRepository unidadeSaudeRepository;
    private final EnderecoUnidadesRepository enderecoUnidadesRepository;
    private final DoctorRepository doctorRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;
    private final NewsRepository newsRepository;
    private final ServicosSaudeRepository servicosSaudeRepository;
    private final CalendarioVacinacaoRepository calendarioVacinacaoRepository;
    private final VacinasRepository vacinasRepository;

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
        System.out.println("Populando dados iniciais de usuarios");
        List<Usuario> usuarios = populateUsuario();
        System.out.println("Dados de usuário populados com sucesso");
        System.out.println("Populando dados iniciais de noticias");
        populateNoticias(usuarios.get(0));
        System.out.println("Dados de Notícias populados com sucesso");
        System.out.println("Populando dados iniciais de servicos");
        populateServicos(unidades);
        System.out.println("Dados de servicos populados com sucesso");
        System.out.println("Populando dados iniciais de vacinas");
        populateVacinas();
        System.out.println("Dados de vacinas populados com sucesso");
        System.out.println("Populando dados iniciais de campanhas");
        populateCalendarioVacinacao();
        System.out.println("Dados de campanhas populados com sucesso");


    }

    private void populateDoctors(List<UnidadeSaude> unidades) {
        Doctor medico1 = new Doctor(
                "Dr. José da Silva",
                "123456",
                "Cardiologia",
                unidades.get(0),
                "Segunda-feira",
                "09:00",
                "12:00",
                "Consultório Geral"
        );
        Doctor medico2 = new Doctor(
                "Dra. Maria Oliveira",
                "654321",
                "Pediatria",
                unidades.get(1),
                "Terça-feira",
                "09:00",
                "12:00",
                "Consultório Geral"
        );
        Doctor medico3 = new Doctor(
                "Dr. João Santos",
                "789012",
                "Dermatologia",
                unidades.get(2),
                "Quarta-feira",
                "09:00",
                "12:00",
                "Consultório Geral"
        );
        Doctor medico4 = new Doctor(
                "Dra. Ana Costa",
                "345678",
                "Clinica Geral",
                unidades.get(3),
                "Quinta-feira",
                "04:00",
                "23:00",
                "Plantonista"
        );
        Doctor medico5 = new Doctor(
                "Dr. Lucas Pereira",
                "901234",
                "Clinica Geral",
                unidades.get(0),
                "Sexta-feira",
                "04:00",
                "23:00",
                "Plantonista"
        );
        Doctor medico6 = new Doctor(
                "Dra. Fernanda Lima",
                "567890",
                "Clinica Geral",
                unidades.get(1),
                "Sábado",
                "04:00",
                "23:00",
                "Plantonista"
        );
        Doctor medico7 = new Doctor(
                "Dr. Carlos Almeida",
                "234567",
                "Clinica Geral",
                unidades.get(2),
                "Domingo",
                "04:00",
                "23:00",
                "Plantonista"
        );
        Doctor medico8 = new Doctor(
                "Dra. Beatriz Rocha",
                "890123",
                "Clinica Geral",
                unidades.get(3),
                "Segunda-feira",
                "04:00",
                "23:00",
                "Plantonista"
        );
        Doctor medico9 = new Doctor(
                "Dr. Rafael Martins",
                "456789",
                "Clinica Geral",
                unidades.get(0),
                "Terça-feira",
                "04:00",
                "23:00",
                "Plantonista"
        );
        Doctor medico10 = new Doctor(
                "Dra. Juliana Souza",
                "123789",
                "Clinica Geral",
                unidades.get(1),
                "Quarta-feira",
                "04:00",
                "23:00",
                "Plantonista"
        );
        doctorRepository.save(medico1);
        doctorRepository.save(medico2);
        doctorRepository.save(medico3);
        doctorRepository.save(medico4);
        doctorRepository.save(medico5);
        doctorRepository.save(medico6);
        doctorRepository.save(medico7);
        doctorRepository.save(medico8);
        doctorRepository.save(medico9);
        doctorRepository.save(medico10);
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

    private List<Usuario> populateUsuario(){

        Usuario usuarioCidadao = new Usuario(
                "João",
                "Silva",
                "111.111.111-11",
                "joao.silva@example.com",
                passwordEncoder.encode("senha123"),
                TipoUsuario.CIDADAO,
                "11987654321"
        );

        usuarioRepository.save(usuarioCidadao);

        Usuario usuarioAdmin = new Usuario(
                "Maria",
                "Souza",
                "222.222.222-22",
                "maria.souza@example.com",
                passwordEncoder.encode("admin123"),
                TipoUsuario.ADMINISTRADOR,
                "21912345678"
        );
        usuarioRepository.save(usuarioAdmin);

        List<Usuario> usuarios = new ArrayList<>();

        usuarios.add(usuarioCidadao);
        usuarios.add(usuarioAdmin);

        return usuarios;
    }

    private void populateNoticias(Usuario usuarioAdmin) {

        News news1 = new News(
                "Novos Protocolos de Atendimento Chegam ao BitHealth",
                "O BitHealth anuncia a implementação de novos protocolos de atendimento para otimizar a experiência dos usuários. Com foco na eficiência e na humanização, as atualizações prometem maior agilidade e precisão nos serviços.",
                usuarioAdmin
        );
        newsRepository.save(news1);

        News news2 = new News(
                "Campanha de Vacinação 'Saúde em Foco': Participe!",
                "Participe da nova campanha de vacinação 'Saúde em Foco' do BitHealth! Informações sobre postos de vacinação, horários e tipos de vacinas disponíveis. A prevenção é o melhor remédio.",
                usuarioAdmin
        );
        newsRepository.save(news2);

        News news3 = new News(
                "Dicas para uma Vida Saudável: Alimentação e Exercício",
                "Confira as melhores dicas do BitHealth para incorporar hábitos saudáveis no seu dia a dia. Aprenda sobre alimentação balanceada e a importância da atividade física para o bem-estar.",
                usuarioAdmin
        );
        newsRepository.save(news3);


        News news4 = new News(
                "BitHealth Lança Novo Aplicativo Móvel: Mais Saúde na Palma da Mão",
                "O BitHealth tem o prazer de anunciar o lançamento do seu novo aplicativo móvel. Agende consultas, acesse seu histórico e receba lembretes de saúde, tudo de forma prática e segura.",
                usuarioAdmin
        );
        newsRepository.save(news4);
    }

    private void populateServicos(List<UnidadeSaude> unidades) {

        ServicosSaude servico1 = new ServicosSaude(
                "Consulta Clínica Geral",
                "Atendimento com clínico geral para diagnósticos e acompanhamentos.",
                "08:00",
                "17:00",
                unidades.get(0)
        );
        ServicosSaude servico2 = new ServicosSaude(
                "Exames Laboratoriais",
                "Realização de diversos exames de sangue e urina.",
                "07:00",
                "16:00",
                unidades.get(1)
        );
        ServicosSaude servico3 = new ServicosSaude(
                "Vacinação",
                "Disponibilidade de vacinas de rotina e campanhas.",
                "09:00",
                "15:00",
                unidades.get(2)
        );
        ServicosSaude servico4 = new ServicosSaude(
                "Atendimento Odontológico",
                "Consultas e procedimentos odontológicos básicos.",
                "08:00",
                "17:00",
                unidades.get(0)
        );
        ServicosSaude servico5 = new ServicosSaude(
                "Pronto Atendimento",
                "Atendimento de urgência e emergência.",
                "24:00",
                "24:00",
                unidades.get(1)
        );

        servicosSaudeRepository.save(servico1);
        servicosSaudeRepository.save(servico2);
        servicosSaudeRepository.save(servico3);
        servicosSaudeRepository.save(servico4);
        servicosSaudeRepository.save(servico5);

    }

    private void populateVacinas() {
        vacinasRepository.save(new Vacinas("BCG", "Ao nascer", Doses.UNICA, "Tuberculose (formas graves)", FaixaEtaria.CRIANCA));
        vacinasRepository.save(new Vacinas("Hepatite B (Pediátrica)", "Ao nascer", Doses.PRIMEIRA, "Hepatite B", FaixaEtaria.CRIANCA));
        vacinasRepository.save(new Vacinas("Pentavalente", "2 meses", Doses.PRIMEIRA, "Difteria, Tétano, Coqueluche, Hepatite B, Haemophilus influenzae B", FaixaEtaria.CRIANCA));
        vacinasRepository.save(new Vacinas("Poliomielite (VIP)", "2 meses", Doses.PRIMEIRA, "Poliomielite", FaixaEtaria.CRIANCA));
        vacinasRepository.save(new Vacinas("Pneumocócica 10-valente", "2 meses", Doses.PRIMEIRA, "Pneumonias, meningites e otites", FaixaEtaria.CRIANCA));
        vacinasRepository.save(new Vacinas("Rotavírus Humano", "2 meses", Doses.PRIMEIRA, "Diarreia por rotavírus", FaixaEtaria.CRIANCA));
        vacinasRepository.save(new Vacinas("Meningocócica C (conjugada)", "3 meses", Doses.PRIMEIRA, "Meningite C", FaixaEtaria.CRIANCA));
        vacinasRepository.save(new Vacinas("Pentavalente", "4 meses", Doses.SEGUNDA, "Difteria, Tétano, Coqueluche, Hepatite B, Haemophilus influenzae B", FaixaEtaria.CRIANCA));
        vacinasRepository.save(new Vacinas("Poliomielite (VIP)", "4 meses", Doses.SEGUNDA, "Poliomielite", FaixaEtaria.CRIANCA));
        vacinasRepository.save(new Vacinas("Pneumocócica 10-valente", "4 meses", Doses.SEGUNDA, "Pneumonias, meningites e otites", FaixaEtaria.CRIANCA));
        vacinasRepository.save(new Vacinas("Rotavírus Humano", "4 meses", Doses.SEGUNDA, "Diarreia por rotavírus", FaixaEtaria.CRIANCA));
        vacinasRepository.save(new Vacinas("Meningocócica C (conjugada)", "5 meses", Doses.SEGUNDA, "Meningite C", FaixaEtaria.CRIANCA));
        vacinasRepository.save(new Vacinas("Pentavalente", "6 meses", Doses.TERCEIRA, "Difteria, Tétano, Coqueluche, Hepatite B, Haemophilus influenzae B", FaixaEtaria.CRIANCA));
        vacinasRepository.save(new Vacinas("Poliomielite (VOP)", "6 meses", Doses.TERCEIRA, "Poliomielite", FaixaEtaria.CRIANCA));
        vacinasRepository.save(new Vacinas("Covid-19 (Pediátrica)", "6 meses", Doses.PRIMEIRA, "COVID-19", FaixaEtaria.CRIANCA));
        vacinasRepository.save(new Vacinas("Covid-19 (Pediátrica)", "7 meses", Doses.SEGUNDA, "COVID-19", FaixaEtaria.CRIANCA));
        vacinasRepository.save(new Vacinas("Febre Amarela", "9 meses", Doses.UNICA, "Febre amarela", FaixaEtaria.CRIANCA));
        vacinasRepository.save(new Vacinas("Pneumocócica 10-valente", "12 meses", Doses.REFORCO, "Pneumonias, meningites e otites", FaixaEtaria.CRIANCA));
        vacinasRepository.save(new Vacinas("Meningocócica C (conjugada)", "12 meses", Doses.REFORCO, "Meningite C", FaixaEtaria.CRIANCA));
        vacinasRepository.save(new Vacinas("Tríplice Viral (SCR)", "12 meses", Doses.PRIMEIRA, "Sarampo, Caxumba, Rubéola", FaixaEtaria.CRIANCA));
        vacinasRepository.save(new Vacinas("DTP (Tríplice Bacteriana)", "15 meses", Doses.REFORCO, "Difteria, Tétano e Coqueluche", FaixaEtaria.CRIANCA));
        vacinasRepository.save(new Vacinas("Poliomielite (VOP)", "15 meses", Doses.REFORCO, "Poliomielite", FaixaEtaria.CRIANCA));
        vacinasRepository.save(new Vacinas("Hepatite A (Pediátrica)", "15 meses", Doses.UNICA, "Hepatite A", FaixaEtaria.CRIANCA));
        vacinasRepository.save(new Vacinas("Tetra Viral (SCRV) ou Varicela", "15 meses", Doses.PRIMEIRA, "Sarampo, Caxumba, Rubéola e Varicela", FaixaEtaria.CRIANCA));
        vacinasRepository.save(new Vacinas("DTP (Tríplice Bacteriana)", "4 anos", Doses.REFORCO, "Difteria, Tétano e Coqueluche", FaixaEtaria.CRIANCA));
        vacinasRepository.save(new Vacinas("Febre Amarela", "4 anos", Doses.REFORCO, "Febre amarela", FaixaEtaria.CRIANCA));
        vacinasRepository.save(new Vacinas("Varicela", "4 anos", Doses.UNICA, "Varicela (catapora)", FaixaEtaria.CRIANCA));
        vacinasRepository.save(new Vacinas("Febre Amarela", "5 anos", Doses.REFORCO, "Febre amarela", FaixaEtaria.CRIANCA));
        vacinasRepository.save(new Vacinas("Pneumocócica 23-valente", "5 anos", Doses.SEGUNDA, "Doenças pneumocócicas para grupos de risco", FaixaEtaria.CRIANCA));
        vacinasRepository.save(new Vacinas("Difteria e Tétano (dT)", "7 anos ou mais", Doses.REFORCO, "Difteria e Tétano", FaixaEtaria.CRIANCA));
        vacinasRepository.save(new Vacinas("HPV Quadrivalente", "9 a 14 anos", Doses.UNICA, "HPV, câncer do colo do útero, verrugas genitais", FaixaEtaria.ADOLESCENTE));
        vacinasRepository.save(new Vacinas("Hepatite B", "10 anos ou mais", Doses.TERCEIRA, "Hepatite B", FaixaEtaria.ADOLESCENTE));
        vacinasRepository.save(new Vacinas("Febre Amarela", "10 anos ou mais", Doses.REFORCO, "Febre amarela", FaixaEtaria.ADOLESCENTE));
        vacinasRepository.save(new Vacinas("Tríplice Viral (SCR)", "10 anos ou mais", Doses.SEGUNDA, "Sarampo, Caxumba, Rubéola", FaixaEtaria.ADOLESCENTE));
        vacinasRepository.save(new Vacinas("Meningocócica ACWY (conjugada)", "11 a 14 anos", Doses.UNICA, "Meningite ACWY", FaixaEtaria.ADOLESCENTE));
        vacinasRepository.save(new Vacinas("Difteria e Tétano (dT)", "20 anos ou mais", Doses.REFORCO, "Difteria e Tétano", FaixaEtaria.ADULTO));
        vacinasRepository.save(new Vacinas("HPV Quadrivalente", "20 a 45 anos", Doses.UNICA, "HPV, câncer do colo do útero, verrugas genitais", FaixaEtaria.ADULTO));
        vacinasRepository.save(new Vacinas("dTpa (Tríplice Bacteriana Acelular Adulto)", "18 anos ou mais", Doses.UNICA, "Difteria, Tétano e Coqueluche", FaixaEtaria.ADULTO));
        vacinasRepository.save(new Vacinas("Tríplice Viral (SCR)", "20 a 29 anos", Doses.REFORCO, "Sarampo, Caxumba, Rubéola", FaixaEtaria.ADULTO));
        vacinasRepository.save(new Vacinas("Tríplice Viral (SCR)", "30 a 59 anos", Doses.UNICA, "Sarampo, Caxumba, Rubéola", FaixaEtaria.ADULTO));
        vacinasRepository.save(new Vacinas("Pneumocócica 23-valente", "60 anos ou mais", Doses.SEGUNDA, "Doenças pneumocócicas para idosos", FaixaEtaria.GESTANTE));
        vacinasRepository.save(new Vacinas("Gripe (Influenza)", "Anual", Doses.REFORCO, "Gripe", FaixaEtaria.GESTANTE));
        vacinasRepository.save(new Vacinas("COVID-19 (Reforço)", "5 anos ou mais", Doses.REFORCO, "COVID-19", FaixaEtaria.GESTANTE));
    }

    private void populateCalendarioVacinacao() {
        CalendarioVacinacao campanha1 = new CalendarioVacinacao(
                "Vacina Dengue",
                1,
                100,
                "Campanha de vacinação contra a Dengue para ampla faixa etária.",
                "14/05/2025",
                "25/06/2025",
                StatusVacinacao.EMBREVE
        );
        calendarioVacinacaoRepository.save(campanha1);

        CalendarioVacinacao campanha2 = new CalendarioVacinacao(
                "Vacina Gripe (Idosos)",
                60,
                100,
                "Campanha anual de vacinação contra a gripe para a população idosa (60 anos ou mais).",
                "01/04/2025",
                "31/05/2025",
                StatusVacinacao.ANDAMENTO
        );
        calendarioVacinacaoRepository.save(campanha2);

        CalendarioVacinacao campanha3 = new CalendarioVacinacao(
                "Vacina HPV (Adolescentes)",
                9,
                14,
                "Campanha de vacinação contra o HPV para meninas de 9 a 14 anos e meninos de 11 a 14 anos.",
                "10/03/2025",
                "30/04/2025",
                StatusVacinacao.REALIZADO
        );
        calendarioVacinacaoRepository.save(campanha3);

        CalendarioVacinacao campanha4 = new CalendarioVacinacao(
                "Vacina Sarampo (Bloqueio)",
                1,
                49,
                "Campanha de bloqueio contra o Sarampo devido a surto localizado. Verifique as áreas de cobertura.",
                "01/06/2025",
                "15/06/2025",
                StatusVacinacao.EMBREVE
        );
        calendarioVacinacaoRepository.save(campanha4);
    }

}
