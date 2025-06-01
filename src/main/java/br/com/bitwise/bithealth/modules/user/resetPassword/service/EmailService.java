package br.com.bitwise.bithealth.modules.user.resetPassword.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public void sendPasswordResetEmail(String toEmail, String token) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setSubject("BitHealth - Redefinição de Senha");
        message.setText(
                "Olá,\n\n" +
                        "Você solicitou a redefinição da sua senha para a plataforma BitHealth.\n" +
                        "Para criar uma nova senha, por favor, informe o token abaixo. Este token é válido por 1 hora:\n" +
                        "Entre nesse link: http://localhost:53145/#/resetar-senha" + "\n\n" +
                        "Insira o token: " + token + "\n\n" +
                        "Se você não fez esta solicitação, pode ignorar este e-mail.\n\n" +
                        "Atenciosamente,\n" +
                        "Equipe BitHealth"
        );

        try {
            mailSender.send(message);
            logger.info("E-mail de redefinição de senha enviado para: {}", toEmail);
        } catch (Exception e) {
            logger.error("Falha ao enviar e-mail de redefinição de senha para {}: {}", toEmail, e.getMessage(), e);
        }
    }
}
