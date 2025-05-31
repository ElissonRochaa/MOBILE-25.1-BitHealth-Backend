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
        System.out.println("Populando dados iniciais de vacinacao");
        populateCalendarioVacinacao();
        System.out.println("Dados de vacinacao populados com sucesso");


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

    private void populateCalendarioVacinacao() {
        String dataInicioPadrao = "01/01/2025";
        String dataFimPadrao = "31/12/2025";
        StatusVacinacao statusPadrao = StatusVacinacao.ANDAMENTO;

        // Crianças
        calendarioVacinacaoRepository.save(new CalendarioVacinacao("BCG", 0, 0, "Ao nascer - Dose única. Protege contra formas graves de tuberculose.", dataInicioPadrao, dataFimPadrao, statusPadrao));
        calendarioVacinacaoRepository.save(new CalendarioVacinacao("Hepatite B (Pediátrica)", 0, 0, "Ao nascer - 1ª dose. Esquema de doses subsequentes (ex: 1 e 6 meses).", dataInicioPadrao, dataFimPadrao, statusPadrao));
        calendarioVacinacaoRepository.save(new CalendarioVacinacao("Pentavalente", 0, 0, "Aos 2 meses - 1ª dose (DTP/Hib/HB).", dataInicioPadrao, dataFimPadrao, statusPadrao));
        calendarioVacinacaoRepository.save(new CalendarioVacinacao("Poliomielite (VIP)", 0, 0, "Aos 2 meses - 1ª dose (Vacina Inativada Poliomielite).", dataInicioPadrao, dataFimPadrao, statusPadrao));
        calendarioVacinacaoRepository.save(new CalendarioVacinacao("Pneumocócica 10-valente", 0, 0, "Aos 2 meses - 1ª dose.", dataInicioPadrao, dataFimPadrao, statusPadrao));
        calendarioVacinacaoRepository.save(new CalendarioVacinacao("Rotavírus Humano", 0, 0, "Aos 2 meses - 1ª dose.", dataInicioPadrao, dataFimPadrao, statusPadrao));
        calendarioVacinacaoRepository.save(new CalendarioVacinacao("Meningocócica C (conjugada)", 0, 0, "Aos 3 meses - 1ª dose.", dataInicioPadrao, dataFimPadrao, statusPadrao));
        calendarioVacinacaoRepository.save(new CalendarioVacinacao("Pentavalente", 0, 0, "Aos 4 meses - 2ª dose (DTP/Hib/HB).", dataInicioPadrao, dataFimPadrao, statusPadrao));
        calendarioVacinacaoRepository.save(new CalendarioVacinacao("Poliomielite (VIP)", 0, 0, "Aos 4 meses - 2ª dose (Vacina Inativada Poliomielite).", dataInicioPadrao, dataFimPadrao, statusPadrao));
        calendarioVacinacaoRepository.save(new CalendarioVacinacao("Pneumocócica 10-valente", 0, 0, "Aos 4 meses - 2ª dose.", dataInicioPadrao, dataFimPadrao, statusPadrao));
        calendarioVacinacaoRepository.save(new CalendarioVacinacao("Rotavírus Humano", 0, 0, "Aos 4 meses - 2ª dose.", dataInicioPadrao, dataFimPadrao, statusPadrao));
        calendarioVacinacaoRepository.save(new CalendarioVacinacao("Meningocócica C (conjugada)", 0, 0, "Aos 5 meses - 2ª dose.", dataInicioPadrao, dataFimPadrao, statusPadrao));
        calendarioVacinacaoRepository.save(new CalendarioVacinacao("Pentavalente", 0, 0, "Aos 6 meses - 3ª dose (DTP/Hib/HB).", dataInicioPadrao, dataFimPadrao, statusPadrao));
        calendarioVacinacaoRepository.save(new CalendarioVacinacao("Poliomielite (VOP)", 0, 0, "Aos 6 meses - 3ª dose (Vacina Oral Poliomielite).", dataInicioPadrao, dataFimPadrao, statusPadrao));
        calendarioVacinacaoRepository.save(new CalendarioVacinacao("Covid-19 (Pediátrica)", 0, 4, "A partir de 6 meses - 1ª dose (conforme esquema aprovado e disponibilidade).", dataInicioPadrao, dataFimPadrao, StatusVacinacao.EMBREVE)); // Ajustar idadeMax e status conforme realidade
        calendarioVacinacaoRepository.save(new CalendarioVacinacao("Covid-19 (Pediátrica)", 0, 4, "A partir de 7 meses (ou conforme intervalo) - 2ª dose.", dataInicioPadrao, dataFimPadrao, StatusVacinacao.EMBREVE));
        calendarioVacinacaoRepository.save(new CalendarioVacinacao("Febre Amarela", 0, 0, "Aos 9 meses - 1 dose (para áreas com recomendação).", dataInicioPadrao, dataFimPadrao, statusPadrao));
        calendarioVacinacaoRepository.save(new CalendarioVacinacao("Pneumocócica 10-valente", 1, 1, "Aos 12 meses - Reforço.", dataInicioPadrao, dataFimPadrao, statusPadrao));
        calendarioVacinacaoRepository.save(new CalendarioVacinacao("Meningocócica C (conjugada)", 1, 1, "Aos 12 meses - Reforço.", dataInicioPadrao, dataFimPadrao, statusPadrao));
        calendarioVacinacaoRepository.save(new CalendarioVacinacao("Tríplice Viral (SCR)", 1, 1, "Aos 12 meses - 1ª dose (Sarampo, Caxumba, Rubéola).", dataInicioPadrao, dataFimPadrao, statusPadrao));
        calendarioVacinacaoRepository.save(new CalendarioVacinacao("DTP (Tríplice Bacteriana)", 1, 1, "Aos 15 meses - 1º reforço.", dataInicioPadrao, dataFimPadrao, statusPadrao));
        calendarioVacinacaoRepository.save(new CalendarioVacinacao("Poliomielite (VOP)", 1, 1, "Aos 15 meses - Reforço (Vacina Oral Poliomielite).", dataInicioPadrao, dataFimPadrao, statusPadrao));
        calendarioVacinacaoRepository.save(new CalendarioVacinacao("Hepatite A (Pediátrica)", 1, 1, "Aos 15 meses - Dose única.", dataInicioPadrao, dataFimPadrao, statusPadrao));
        calendarioVacinacaoRepository.save(new CalendarioVacinacao("Tetra Viral (SCRV) ou Varicela", 1, 1, "Aos 15 meses - 1 dose (Sarampo, Caxumba, Rubéola, Varicela).", dataInicioPadrao, dataFimPadrao, statusPadrao));
        calendarioVacinacaoRepository.save(new CalendarioVacinacao("DTP (Tríplice Bacteriana)", 4, 4, "Aos 4 anos - 2º reforço.", dataInicioPadrao, dataFimPadrao, statusPadrao));
        calendarioVacinacaoRepository.save(new CalendarioVacinacao("Febre Amarela", 4, 4, "Aos 4 anos - Reforço (se necessário, conforme histórico e área).", dataInicioPadrao, dataFimPadrao, statusPadrao));
        calendarioVacinacaoRepository.save(new CalendarioVacinacao("Varicela", 4, 4, "Aos 4 anos - 1 dose (se não vacinado com Tetra Viral ou Varicela anteriormente).", dataInicioPadrao, dataFimPadrao, statusPadrao));
        calendarioVacinacaoRepository.save(new CalendarioVacinacao("Febre Amarela", 5, 5, "Aos 5 anos - Dose conforme histórico vacinal.", dataInicioPadrao, dataFimPadrao, statusPadrao));
        calendarioVacinacaoRepository.save(new CalendarioVacinacao("Pneumocócica 23-valente", 5, 5, "A partir dos 5 anos - 2 doses (População Indígena ou condições específicas).", dataInicioPadrao, dataFimPadrao, StatusVacinacao.EMBREVE));
        calendarioVacinacaoRepository.save(new CalendarioVacinacao("Difteria e Tétano (dT)", 7, 9, "A partir dos 7 anos - Conforme situação vacinal / Reforços.", dataInicioPadrao, dataFimPadrao, statusPadrao));
        calendarioVacinacaoRepository.save(new CalendarioVacinacao("HPV Quadrivalente", 9, 14, "Meninas: 9 a 14 anos. Meninos: 11 a 14 anos. Dose única (ou conforme PNI vigente).", dataInicioPadrao, dataFimPadrao, statusPadrao));

        // Adolescentes (10-19 anos, algumas se sobrepõem ou continuam)
        calendarioVacinacaoRepository.save(new CalendarioVacinacao("Hepatite B", 10, 19, "Iniciar ou completar 3 doses (conforme sit. vacinal).", dataInicioPadrao, dataFimPadrao, statusPadrao));
        calendarioVacinacaoRepository.save(new CalendarioVacinacao("Difteria e Tétano (dT)", 10, 19, "Iniciar ou completar 3 doses | Reforços a cada 10 anos.", dataInicioPadrao, dataFimPadrao, statusPadrao));
        calendarioVacinacaoRepository.save(new CalendarioVacinacao("Febre Amarela", 10, 19, "Dose única ou reforço (conforme histórico e área de residência/viagem).", dataInicioPadrao, dataFimPadrao, statusPadrao));
        calendarioVacinacaoRepository.save(new CalendarioVacinacao("Tríplice Viral (SCR)", 10, 19, "Iniciar ou completar 2 doses (conforme sit. vacinal).", dataInicioPadrao, dataFimPadrao, statusPadrao));
        // HPV já coberto acima (9-14 anos)
        calendarioVacinacaoRepository.save(new CalendarioVacinacao("Meningocócica ACWY (conjugada)", 11, 14, "Uma dose (entre 11 e 14 anos, conforme PNI).", dataInicioPadrao, dataFimPadrao, statusPadrao));

        // Adultos (20 anos ou mais)
        calendarioVacinacaoRepository.save(new CalendarioVacinacao("Hepatite B", 20, 100, "3 doses (conforme histórico vacinal).", dataInicioPadrao, dataFimPadrao, statusPadrao));
        calendarioVacinacaoRepository.save(new CalendarioVacinacao("Difteria e Tétano (dT)", 20, 100, "3 doses (conforme histórico vacinal) | Reforços a cada 10 anos.", dataInicioPadrao, dataFimPadrao, statusPadrao));
        calendarioVacinacaoRepository.save(new CalendarioVacinacao("Febre Amarela", 20, 59, "Dose conforme histórico e área de residência/viagem.", dataInicioPadrao, dataFimPadrao, statusPadrao));
        calendarioVacinacaoRepository.save(new CalendarioVacinacao("Febre Amarela", 60, 100, "Avaliar risco/benefício (se não vacinado anteriormente ou sem comprovante).", dataInicioPadrao, dataFimPadrao, statusPadrao));
        calendarioVacinacaoRepository.save(new CalendarioVacinacao("HPV Quadrivalente", 20, 45, "Dose conforme orientação médica para grupos específicos (ex: imunossuprimidos).", dataInicioPadrao, dataFimPadrao, StatusVacinacao.EMBREVE));
        calendarioVacinacaoRepository.save(new CalendarioVacinacao("dTpa (Tríplice Bacteriana Acelular Adulto)", 18, 100, "1 dose a cada gestação | Profissionais de saúde (conforme recomendação).", dataInicioPadrao, dataFimPadrao, statusPadrao));
        calendarioVacinacaoRepository.save(new CalendarioVacinacao("Tríplice Viral (SCR)", 20, 29, "2 doses (se não vacinado anteriormente ou esquema incompleto).", dataInicioPadrao, dataFimPadrao, statusPadrao));
        calendarioVacinacaoRepository.save(new CalendarioVacinacao("Tríplice Viral (SCR)", 30, 59, "1 dose (se não vacinado anteriormente).", dataInicioPadrao, dataFimPadrao, statusPadrao));
        calendarioVacinacaoRepository.save(new CalendarioVacinacao("Pneumocócica 23-valente", 60, 100, "Para idosos (60+), especialmente institucionalizados ou com comorbidades (conforme recomendação).", dataInicioPadrao, dataFimPadrao, StatusVacinacao.EMBREVE));
        calendarioVacinacaoRepository.save(new CalendarioVacinacao("Gripe (Influenza)", 0, 100, "Campanha Anual para grupos prioritários (Crianças 6m-5a, Gestantes, Idosos 60+, etc). Datas variáveis.", "01/04/2025", "31/07/2025", StatusVacinacao.ANDAMENTO)); // Exemplo de campanha sazonal
        calendarioVacinacaoRepository.save(new CalendarioVacinacao("COVID-19 (Reforço)", 5, 100, "Doses de reforço conforme calendário nacional e tipo de vacina.", dataInicioPadrao, dataFimPadrao, StatusVacinacao.ANDAMENTO));
    }
}
