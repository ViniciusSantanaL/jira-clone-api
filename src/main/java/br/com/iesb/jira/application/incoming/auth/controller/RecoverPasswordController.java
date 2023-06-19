package br.com.iesb.jira.application.incoming.auth.controller;

import br.com.iesb.jira.application.incoming.auth.controller.api.RecoverPasswordApi;
import br.com.iesb.jira.domain.email.service.EmailService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/recover-password")
public class RecoverPasswordController implements RecoverPasswordApi {

    private final EmailService emailService;

    public RecoverPasswordController(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void recoverPassowrd() {
        emailService.sendSimpleMessage("viniciussantanaleao@gmail.com","Teste", "oiiiii");
    }
}
