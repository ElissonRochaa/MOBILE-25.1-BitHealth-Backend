package br.com.bitwise.bithealth.modules.user;

import br.com.bitwise.bithealth.modules.user.dto.RegistroUsuarioDTO;
import br.com.bitwise.bithealth.modules.user.dto.UsuarioDTO;
import br.com.bitwise.bithealth.modules.user.endereco.dto.EnderecoDTO;
import br.com.bitwise.bithealth.modules.user.endereco.mapper.impl.EnderecoMapperImpl;
import br.com.bitwise.bithealth.modules.user.endereco.repository.EnderecoRepository;
import br.com.bitwise.bithealth.modules.user.exceptions.CPFAlreadyExistsException;
import br.com.bitwise.bithealth.modules.user.exceptions.EmailAlreadyExistsException;
import br.com.bitwise.bithealth.modules.user.mapper.UsuarioMapper;
import br.com.bitwise.bithealth.modules.user.model.ENUM.TipoUsuario;
import br.com.bitwise.bithealth.modules.user.model.Usuario;
import br.com.bitwise.bithealth.modules.user.repository.UsuarioRepository;
import br.com.bitwise.bithealth.modules.user.service.impl.RegistroServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class RegistroServiceTest {

    @Mock
    private UsuarioMapper usuarioMapper;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private EnderecoMapperImpl enderecoMapper;

    @Mock
    private EnderecoRepository enderecoRepository;

    @InjectMocks
    private RegistroServiceImpl registroService;

    private RegistroUsuarioDTO validRegistroDTO;
    private Usuario usuario;
    private UsuarioDTO usuarioDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        EnderecoDTO endereco1 = new EnderecoDTO(
                "Rua das Flores",
                "123",
                "Apto 101",
                "Jardim Primavera",
                "São Paulo",
                "SP",
                "01234-567"
        );

        validRegistroDTO = new RegistroUsuarioDTO(
                "Teste Usuario",
                "teste@email.com",
                "123.456.789-00",
                "string@email.com",
                "senhaencriptada",
                TipoUsuario.CIDADAO,
                "999999999",
                endereco1
        );

        usuario = new Usuario();
        usuario.setId(UUID.randomUUID());
        usuario.setNome("Teste Usuario");
        usuario.setEmail("teste@email.com");
        usuario.setCpf("123.456.789-00");
        usuario.setSenha("senhaencriptada");
        usuario.setAtivo(true);

        usuarioDTO = new UsuarioDTO(
                UUID.randomUUID(),
                "Teste Usuario",
                "teste@email.com",
                "123.456.789-00",
                "string@email.com",
                "senhaencriptada",
                TipoUsuario.CIDADAO,
                "9999999",
                true,
                LocalDateTime.now(),
                endereco1
        );
    }

    @Test
    @DisplayName("Deve registrar usuário com sucesso quando dados forem válidos")
    void registrarNovoUsuarioSucesso() {

        when(usuarioRepository.existsByEmail(anyString())).thenReturn(false);
        when(usuarioRepository.existsByCpf(anyString())).thenReturn(false);
        when(usuarioMapper.toEntity(validRegistroDTO)).thenReturn(usuario);
        when(passwordEncoder.encode(anyString())).thenReturn("senhaencriptada");
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
        when(usuarioMapper.toDto(usuario)).thenReturn(usuarioDTO);

        UsuarioDTO resultado = registroService.registrarNovoUsuario(validRegistroDTO);

        assertNotNull(resultado);
        assertEquals(usuarioDTO, resultado);

        verify(usuarioRepository).existsByEmail(validRegistroDTO.email());
        verify(usuarioRepository).existsByCpf(validRegistroDTO.cpf());
        verify(usuarioMapper).toEntity(validRegistroDTO);
        verify(passwordEncoder).encode(validRegistroDTO.senha());
        verify(usuarioRepository).save(usuario);
        verify(usuarioMapper).toDto(usuario);
    }

    @Test
    @DisplayName("Deve lançar exceção quando email já existe")
    void registrarNovoUsuarioEmailExistente() {

        when(usuarioRepository.existsByEmail(anyString())).thenReturn(true);

        EmailAlreadyExistsException exception = assertThrows(
                EmailAlreadyExistsException.class,
                () -> registroService.registrarNovoUsuario(validRegistroDTO)
        );

        assertEquals("Email já cadastrado", exception.getMessage());
        verify(usuarioRepository).existsByEmail(validRegistroDTO.email());
        verify(usuarioRepository, never()).existsByCpf(anyString());
        verify(usuarioMapper, never()).toEntity((RegistroUsuarioDTO) any());
    }

    @Test
    @DisplayName("Deve lançar exceção quando CPF já existe")
    void registrarNovoUsuarioCpfExistente() {

        when(usuarioRepository.existsByEmail(anyString())).thenReturn(false);
        when(usuarioRepository.existsByCpf(anyString())).thenReturn(true);

        CPFAlreadyExistsException exception = assertThrows(
                CPFAlreadyExistsException.class,
                () -> registroService.registrarNovoUsuario(validRegistroDTO)
        );

        assertEquals("CPF já cadastrado", exception.getMessage());
        verify(usuarioRepository).existsByEmail(validRegistroDTO.email());
        verify(usuarioRepository).existsByCpf(validRegistroDTO.cpf());
        verify(usuarioMapper, never()).toEntity((RegistroUsuarioDTO) any());
    }

    @Test
    @DisplayName("Deve configurar senha encriptada e status ativo ao criar usuário")
    void deveConfigurarSenhaEStatusAoRegistrar() {

        when(usuarioRepository.existsByEmail(anyString())).thenReturn(false);
        when(usuarioRepository.existsByCpf(anyString())).thenReturn(false);
        when(usuarioMapper.toEntity(validRegistroDTO)).thenReturn(usuario);
        when(passwordEncoder.encode("senhaencriptada")).thenReturn("senhaencriptada");
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
        when(usuarioMapper.toDto(usuario)).thenReturn(usuarioDTO);

        registroService.registrarNovoUsuario(validRegistroDTO);

        verify(passwordEncoder).encode("senhaencriptada");
        verify(usuarioRepository).save(usuario);
    }
}
