package br.com.bitwise.bithealth.modules.user.controller;

import br.com.bitwise.bithealth.modules.user.resetPassword.dto.ForgotPasswordRequest;
import br.com.bitwise.bithealth.modules.user.resetPassword.dto.ResetPasswordRequest;
import br.com.bitwise.bithealth.modules.user.resetPassword.exceptions.InvalidTokenException;
import br.com.bitwise.bithealth.modules.user.resetPassword.exceptions.TokenExpiredException;
import br.com.bitwise.bithealth.modules.user.service.ForgotPasswordServiceI;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reset")
@RequiredArgsConstructor
public class ResetPasswordController {
    private final ForgotPasswordServiceI forgotPasswordService;

    private static final Logger logger = LoggerFactory.getLogger(ResetPasswordController.class);

    @PostMapping("/forgot-password")
    public ResponseEntity<String> handleForgotPassword(@RequestBody @Valid ForgotPasswordRequest forgotPasswordRequest) {
        try {
            forgotPasswordService.initiatePasswordResetProcess(forgotPasswordRequest.email());
            return ResponseEntity.ok("Se o seu e-mail estiver cadastrado em nosso sistema, você receberá um link para redefinir sua senha.");
        } catch (Exception e) {
            logger.error("Erro inesperado durante a solicitação de redefinição de senha para o email [PROTEGIDO]: ", e);
            return ResponseEntity.ok("Se o seu e-mail estiver cadastrado em nosso sistema, você receberá um link para redefinir sua senha.");
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> handleResetPassword(@RequestBody @Valid ResetPasswordRequest resetPasswordRequest) {
        try {
            forgotPasswordService.finalizePasswordReset(resetPasswordRequest.token(), resetPasswordRequest.newPassword());
            return ResponseEntity.ok("Sua senha foi redefinida com sucesso.");
        } catch (InvalidTokenException | TokenExpiredException e) {
            logger.warn("Tentativa de redefinição de senha falhou: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            logger.error("Erro inesperado ao tentar redefinir a senha com o token [PROTEGIDO]: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao tentar redefinir sua senha. Por favor, tente novamente mais tarde.");
        }
    }
}
