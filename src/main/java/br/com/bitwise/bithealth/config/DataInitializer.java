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
