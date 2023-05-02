package br.com.iesb.jira.domain.email.service;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
}
