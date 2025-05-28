package br.com.bitwise.bithealth.modules.user.service.impl;

import br.com.bitwise.bithealth.modules.user.model.Usuario;
import br.com.bitwise.bithealth.modules.user.repository.UsuarioRepository;
import br.com.bitwise.bithealth.modules.user.resetPassword.exceptions.InvalidTokenException;
import br.com.bitwise.bithealth.modules.user.resetPassword.exceptions.TokenExpiredException;
import br.com.bitwise.bithealth.modules.user.resetPassword.model.PasswordResetToken;
import br.com.bitwise.bithealth.modules.user.resetPassword.repository.PasswordResetTokenRepository;
import br.com.bitwise.bithealth.modules.user.resetPassword.service.EmailService;
import br.com.bitwise.bithealth.modules.user.service.ForgotPasswordServiceI;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ForgotPasswordServiceImpl implements ForgotPasswordServiceI {

    private final UsuarioRepository usuarioRepository;

    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final PasswordEncoder passwordEncoder;

    private final EmailService emailService;


    @Transactional
    @Override
    public void initiatePasswordResetProcess(String email) {
        Optional<Usuario> userOptional = usuarioRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            Usuario usuario = userOptional.get();
            passwordResetTokenRepository.deleteByUsuario(usuario);
            String tokenValue = UUID.randomUUID().toString();
            PasswordResetToken passwordResetToken = new PasswordResetToken(tokenValue, usuario);
            passwordResetTokenRepository.save(passwordResetToken);
            emailService.sendPasswordResetEmail(usuario.getEmail(), tokenValue);
        }
    }

    @Transactional
    @Override
    public void finalizePasswordReset(String token, String newPassword) {
        PasswordResetToken resetToken = passwordResetTokenRepository.findByToken(token)
                .orElseThrow(() -> new InvalidTokenException("Token de redefinição inválido ou não encontrado."));

        if (resetToken.isExpired()) {
            passwordResetTokenRepository.delete(resetToken);
            throw new TokenExpiredException("Seu token de redefinição de senha expirou. Por favor, solicite um novo.");
        }

        Usuario usuario = resetToken.getUsuario();
        usuario.setSenha(passwordEncoder.encode(newPassword));
        usuarioRepository.save(usuario);
        passwordResetTokenRepository.delete(resetToken);
    }
}
