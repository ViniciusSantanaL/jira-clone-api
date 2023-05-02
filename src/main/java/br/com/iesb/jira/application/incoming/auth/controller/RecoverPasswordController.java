package br.com.iesb.jira.application.incoming.auth.controller;

import br.com.iesb.jira.domain.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/recover-password")
public class RecoverPasswordController {

    private final EmailService emailService;

    @Autowired
    public RecoverPasswordController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping
    public void recoverPassowrd() {
        emailService.sendSimpleMessage("viniciussantanaleao@gmail.com","Teste", "oiiiii");
    }
}
