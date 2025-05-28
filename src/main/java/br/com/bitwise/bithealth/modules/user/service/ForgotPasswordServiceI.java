package br.com.bitwise.bithealth.modules.user.service;

public interface ForgotPasswordServiceI {
    void initiatePasswordResetProcess(String email);
    void finalizePasswordReset(String token, String newPassword);
}
